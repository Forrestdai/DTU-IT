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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import threading.executiontypes.CyclicalExecutor;
import threading.executiontypes.ExecutableCyclic;

/**
 *
 * @author James
 */
public class DatabaseHandler
{

    private Database trafficDatabase;
    private Database userDatabase;
    private final int PUSH_INTERVAL = 4000;
    private UpdateUserDatabase userDatabaseUpdater;

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
        userDatabaseUpdater = new UpdateUserDatabase();
        ServerExecutable toExecute = new CyclicalExecutor(userDatabaseUpdater, PUSH_INTERVAL);
        SimpleProcessorRequest process = new SimpleProcessorRequest(toExecute);
        Server.threadPool.schedule(process);
    }

    public User getUser(int userID) throws SQLException
    {
        User user = new User();

        user.ID = -1;

        String userSQL = "SELECT * FROM Customers WHERE CustomerID = ?";

        Integer[] properties =
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
            user.credit = userData.getDouble("CREDIT");
        }

        return user;
    }

    public void updateUser(User user)
    {
        userDatabaseUpdater.updateUser(user);
    }

    private void pushUpdates(Set<Entry<Integer, User>> users) throws SQLException
    {
        for (Entry<Integer, User> userEntry : users)
        {
            String userSQL = "UPDATE Customers SET First_Name = ?, Last_Name = ?, Password = ?, credit = ? WHERE CustomerID = ?";

            Object[] properties =
            {
                userEntry.getValue().firstName, userEntry.getValue().lastName, userEntry.getValue().passWord, userEntry.getValue().credit, userEntry.getKey()
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

            Position position = new Position(latitude, longetude);
            TransportNode nodeToAdd = new TransportNode(position, name, ref);
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

    class UpdateUserDatabase implements ExecutableCyclic
    {

        private ConcurrentMap<Integer, User> toPushToDatabase;
        private final ConcurrentLinkedQueue<User> usersToUpdate;

        public UpdateUserDatabase()
        {
            usersToUpdate = new ConcurrentLinkedQueue<>();
            toPushToDatabase = new ConcurrentHashMap<>();
        }

        @Override
        public void execute()
        {
            try
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
                    }
                    User combined = combineUserFields(existing, user);
                    toPushToDatabase.replace(user.ID, combined);
                    usersToUpdate.remove(user);
                }

                pushUpdates(toPushToDatabase.entrySet());
                toPushToDatabase.clear();
            } catch (SQLException ex)
            {
                
            }
        }

        private void updateUser(User user)
        {
            usersToUpdate.add(user);
        }

        private User combineUserFields(User one, User two)
        {
            User combined = new User();
            combined.ID = one.ID;
            combined.firstName = two.firstName;
            combined.lastName = two.lastName;
            combined.passWord = two.lastName;
            combined.credit = one.credit - two.credit;
            return combined;
        }

    }
}
