/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import common.interfaces.ServerExecutable;
import execute.Server;
import execute.SimpleProcessorRequest;
import helpers.LogPrinter;
import helpers.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import threading.executiontypes.CyclicalExecutor;
import threading.executiontypes.ExecutableCyclic;
import trafficrouting.Edge;
import trafficrouting.Position;
import trafficrouting.TransportNode;

/**
 *
 * @author James
 */
public class DatabaseHandler
{

    private Database trafficDatabase;
    private Database userDatabase;
    private final int PUSH_INTERVAL = 4000;
    private UpdateUsers userDatabaseUpdater;

    public DatabaseHandler()
    {
        try
        {
            initializeCyclicalPushing();
            trafficDatabase = new Database("jdbc:derby://localhost:1527/TrafficNetwork");
            userDatabase = new Database("jdbc:derby://localhost:1527/Users");
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    private void initializeCyclicalPushing()
    {
        userDatabaseUpdater = new UpdateUsers();
        ServerExecutable toExecute = new CyclicalExecutor(userDatabaseUpdater, PUSH_INTERVAL);
        SimpleProcessorRequest process = new SimpleProcessorRequest(toExecute);
        Server.threadPool.schedule(process);
    }

    public void addUser(User user)
    {
        try
        {
            String addUserSQL = "INSERT INTO users (CustomerID, First_Name, Last_Name, Password, balance) "
                    + "VALUES (?,?,?,?,?)";

            Object[] properties =
            {
                user.ID, user.firstName, user.lastName, user.passWord, user.balance
            };

            userDatabase.pushPreparedStatement(addUserSQL, properties, true);
        } catch (SQLException ex)
        {
            LogPrinter.printError("Could not add user to database", ex);
        }
    }

    public void removeUser(int userID) throws SQLException
    {
        String userSQL = "Delete FROM Users WHERE CustomerID = ?";

        Object[] properties =
        {
            userID
        };

        userDatabase.pushPreparedStatement(userSQL, properties, true);
    }

    public User getUser(int userID) throws SQLException
    {
        String userSQL = "SELECT * FROM Users WHERE CustomerID = ?";

        Object[] properties =
        {
            userID
        };

        ResultSet userData = userDatabase.pushPreparedStatement(userSQL, properties, false);

        User user = new User(0);

        if (!userData.next())
        {
            user = createDefaultUser(userID);
        } else
        {
            do
            {
                user.ID = userData.getInt("CUSTOMERID");
                user.firstName = userData.getString("FIRST_NAME");
                user.lastName = userData.getString("LAST_NAME");
                user.passWord = userData.getString("PASSWORD");
                user.balance = userData.getDouble("BALANCE");
            } while (userData.next());
        }
        return user;
    }

    private User createDefaultUser(int ID) throws SQLException
    {
        User user = new User(ID);
        addUser(user);
        return getUser(ID);
    }

    public void updateUser(User user)
    {
        userDatabaseUpdater.updateUser(user);
    }

    public void chargeUser(User user, double charge)
    {
        userDatabaseUpdater.chargeUser(user, charge);
    }

    private void pushUpdates(Collection<User> users) throws SQLException
    {
        for (User user : users)
        {
            String userSQL = "UPDATE Users SET First_Name = ?, Last_Name = ?, Password = ?, balance = ? WHERE CustomerID = ?";

            Object[] properties =
            {
                user.firstName, user.lastName, user.passWord, user.balance, user.ID
            };

            userDatabase.pushPreparedStatement(userSQL, properties, true);
        }
    }

    public Map<Integer, TransportNode> getAllNodes() throws SQLException
    {
        Map<Integer, TransportNode> nodes = new HashMap<>();

        String SQL = "SELECT * FROM STOPS_STATIONS";
        ResultSet stops = trafficDatabase.pushStatement(SQL);

        while (stops.next())
        {
            String name = stops.getString("STOP_NAME");
            int ref = stops.getInt("REF");
            double latitude = stops.getDouble("LAT");
            double longetude = stops.getDouble("LON");
            int zone = stops.getInt("PRICE_ZONE");

            Position position = new Position(latitude, longetude);
            TransportNode nodeToAdd = new TransportNode(position, name, ref, zone);
            nodes.put(ref, nodeToAdd);
        }
        return nodes;
    }

    public ArrayList<Edge> getEdgesFromStop(Integer fromStopReference, Map<Integer, TransportNode> nodes) throws SQLException
    {
        ArrayList<Edge> edges = new ArrayList<>();

        String getNodeInformation = "SELECT  * FROM transport_stop_relation"
                + " INNER JOIN line_identity"
                + " ON transport_stop_relation.transport_ref = line_identity.line_ref"
                + " INNER JOIN stops_stations"
                + " ON transport_stop_relation.stop_ref = stops_stations.ref"
                + " AND transport_stop_relation.stop_ref = ?";

        Integer[] nodeInformationProperties =
        {
            fromStopReference
        };
        ResultSet node = trafficDatabase.pushPreparedStatement(getNodeInformation, nodeInformationProperties, false);

        while (node.next())
        {
            String type = node.getString("TYPE");
            int fromStopRef = node.getInt("STOP_REF");
            int transportID = node.getInt("LINE_REF");
            String transportName = node.getString("LINE_NAME");
            int stopSequenceIndex = node.getInt("STOP_SEQUENCE");

            String toRef = "SELECT  stop_ref FROM transport_stop_relation"
                    + " WHERE transport_ref = ?"
                    + " AND (stop_sequence = ?"
                    + " OR stop_sequence = ?)";

            Integer[] toRefProperties =
            {
                transportID, (stopSequenceIndex - 1), (stopSequenceIndex + 1)
            };

            ResultSet toRefResult = trafficDatabase.pushPreparedStatement(toRef, toRefProperties, false);

            while (toRefResult.next())
            {
                int toStopRef = toRefResult.getInt("STOP_REF");

                Edge.EdgeType edgeType = Edge.EdgeType.valueOf(type);
                Edge edge = new Edge(edgeType, nodes.get(fromStopRef), nodes.get(toStopRef), transportID, transportName);

                boolean addEdge = true;
                for (Edge existing : edges)
                {
                    if (existing.toNode.referenceID == edge.toNode.referenceID && existing.transportLineReference == edge.transportLineReference)
                    {
                        addEdge = false;
                        break;
                    }
                }
                if (addEdge)
                {
                    edges.add(edge);
                }
            }
        }
        return edges;
    }

    class UpdateUsers implements ExecutableCyclic
    {

        private Map<Integer, User> toPushToDatabase;
        private final ConcurrentMap<User, Double> usersToCharge;
        private final ConcurrentLinkedQueue<User> usersToUpdate;

        public UpdateUsers()
        {
            usersToCharge = new ConcurrentHashMap<>();
            usersToUpdate = new ConcurrentLinkedQueue<>();
            toPushToDatabase = new HashMap<>();
        }

        @Override
        public void execute()
        {
            try
            {
                runUpdates();
                runCharges();

                pushUpdates(toPushToDatabase.values());
                toPushToDatabase.clear();
            } catch (SQLException ex)
            {
                LogPrinter.printError("SQL error", ex);
            }
        }

        private void chargeUser(User user, double amount)
        {
            usersToCharge.put(user, amount);
        }

        private void updateUser(User user)
        {
            usersToUpdate.add(user);
        }

        private void runUpdates() throws SQLException
        {
            for (User user : usersToUpdate)
            {
                User existing;
                if (toPushToDatabase.containsKey(user.ID))
                {
                    existing = toPushToDatabase.get(user.ID);
                } else
                {
                    existing = getUser(user.ID);
                    toPushToDatabase.put(existing.ID, existing);
                }
                User updated = updateUserFields(existing, user);
                toPushToDatabase.replace(user.ID, updated);
                usersToUpdate.remove(user);
            }
        }

        private void runCharges() throws SQLException
        {
            for (Entry<User, Double> userEntry : usersToCharge.entrySet())
            {
                User user = userEntry.getKey();
                User existing;
                if (toPushToDatabase.containsKey(user.ID))
                {
                    existing = toPushToDatabase.get(user.ID);
                } else
                {
                    existing = getUser(user.ID);
                    toPushToDatabase.put(existing.ID, existing);
                }
                User charged = chargeUserFields(existing, userEntry.getValue());
                toPushToDatabase.replace(user.ID, charged);
                usersToCharge.remove(user);
            }
        }

        private User updateUserFields(User existing, User adding)
        {
            User combined = new User(existing.ID);
            combined.firstName = adding.firstName;
            combined.lastName = adding.lastName;
            combined.passWord = adding.passWord;
            combined.balance = adding.balance;
            return combined;
        }

        private User chargeUserFields(User existing, Double charge)
        {
            User combined = existing;
            combined.balance = existing.balance - charge;
            return combined;
        }

    }
}
