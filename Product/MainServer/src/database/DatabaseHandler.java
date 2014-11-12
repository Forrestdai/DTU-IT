/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import common.interfaces.ServerExecutable;
import execute.Server;
import execute.SimpleProcessorRequest;
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
import org.omg.CosNaming.NamingContextPackage.NotFound;
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
    private ChargeUserDatabase userDatabaseUpdater;

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
        userDatabaseUpdater = new ChargeUserDatabase();
        ServerExecutable toExecute = new CyclicalExecutor(userDatabaseUpdater, PUSH_INTERVAL);
        SimpleProcessorRequest process = new SimpleProcessorRequest(toExecute);
        Server.threadPool.schedule(process);
    }

    public void addUser(User user)
    {
        try
        {
            String addUserSQL = "INSERT INTO Users (CustomerID, First_Name, Last_Name, Password, balance "
                    + "VALUES ('?','?','?','?','?')";

            Object[] properties =
            {
                user.ID, user.firstName, user.lastName, user.passWord, user.balance
            };

            ResultSet userData = userDatabase.pushPreparedStatement(addUserSQL, properties);
        } catch (SQLException ex)
        {
        }
    }

    public User getUser(int userID) throws SQLException, NotFound
    {
        User user = new User();

        user.ID = -1;

        String userSQL = "SELECT * FROM Customers WHERE CustomerID = ?";

        Object[] properties =
        {
            userID
        };

        ResultSet userData = userDatabase.pushPreparedStatement(userSQL, properties);

        while (userData.next())
        {
            user.ID = userData.getInt("CUSTOMERID");
            user.firstName = userData.getString("FIRST_NAME");
            user.lastName = userData.getString("LAST_NAME");
            user.passWord = userData.getString("PASSWORD");
            user.balance = userData.getDouble("CREDIT");
        }

        if (user.ID == -1)
        {
            throw new NotFound();
        }

        return user;
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
            String userSQL = "UPDATE Customers SET First_Name = ?, Last_Name = ?, Password = ?, credit = ? WHERE CustomerID = ?";

            Object[] properties =
            {
                user.firstName, user.lastName, user.passWord, user.balance, user.ID
            };

            userDatabase.pushPreparedStatement(userSQL, properties);
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

        String getEdges = "SELECT *"
                + " FROM line_identity"
                + " INNER JOIN"
                + " paths lines ON (lines.line_ref = line_identity.line_ref)"
                + " AND from_stop_ref = ?";

        Integer[] properties =
        {
            fromStopReference
        };
        ResultSet stops = trafficDatabase.pushPreparedStatement(getEdges, properties);

        while (stops.next())
        {
            String type = stops.getString("TYPE");
            int fromStopRef = stops.getInt("FROM_STOP_REF");
            int toStopRef = stops.getInt("TO_STOP_REF");
            int transportID = stops.getInt("LINE_REF");
            String transportName = stops.getString("LINE_NAME");

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
        return edges;
    }

    class ChargeUserDatabase implements ExecutableCyclic
    {

        private Map<Integer, User> toPushToDatabase;
        private final ConcurrentMap<User, Double> usersToCharge;
        private final ConcurrentLinkedQueue<User> usersToUpdate;

        public ChargeUserDatabase()
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
            } catch (NotFound ex)
            {
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

        private void runUpdates() throws SQLException, NotFound
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

        private void runCharges() throws SQLException, NotFound
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
            User combined = new User();
            combined.ID = existing.ID;
            combined.firstName = adding.firstName;
            combined.lastName = adding.lastName;
            combined.passWord = adding.lastName;
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
