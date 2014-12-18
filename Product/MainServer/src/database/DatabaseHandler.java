/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import helpers.Zone;
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

    private Database database;
    private final int PUSH_INTERVAL = 4000;
    private UpdateUsers databaseUpdater;

    public DatabaseHandler()
    {
        try
        {
            initializeCyclicalPushing();
            database = new Database();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    private void initializeCyclicalPushing()
    {
        databaseUpdater = new UpdateUsers(this);
        ServerExecutable toExecute = new CyclicalExecutor(databaseUpdater, PUSH_INTERVAL);
        SimpleProcessorRequest process = new SimpleProcessorRequest(toExecute);
        Server.threadPool.schedule(process);
    }

    public void addUser(User user)
    {
        try
        {
            String addUserSQL = "INSERT INTO `USERS` (CustomerID, First_Name, Last_Name, Password, balance) "
                    + "VALUES (?,?,?,?,?)";

            Object[] properties =
            {
                user.ID, user.firstName, user.lastName, user.passWord, user.balance
            };

            database.pushPreparedStatement(addUserSQL, properties, true);
        } catch (SQLException ex)
        {
            LogPrinter.printError("Could not add user to database", ex);
        }
    }

    public void removeUser(int userID) throws SQLException
    {
        String userSQL = "Delete FROM `USERS` WHERE CustomerID = ?";

        Object[] properties =
        {
            userID
        };

        database.pushPreparedStatement(userSQL, properties, true);
    }

    public User getUser(int userID) throws SQLException
    {
        String userSQL = "SELECT * FROM `USERS` WHERE CustomerID = ?";

        Object[] properties =
        {
            userID
        };

        ResultSet userData = database.pushPreparedStatement(userSQL, properties, false);

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
                user.balance = userData.getInt("BALANCE");
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
        databaseUpdater.updateUser(user);
    }

    public void chargeUser(User user, Integer charge)
    {
        databaseUpdater.chargeUser(user, charge);
    }

    void pushUpdates(Collection<User> users) throws SQLException
    {
        for (User user : users)
        {
            String userSQL = "UPDATE `USERS` SET First_Name = ?, Last_Name = ?, Password = ?, balance = ? WHERE CustomerID = ?";

            Object[] properties =
            {
                user.firstName, user.lastName, user.passWord, user.balance, user.ID
            };

            database.pushPreparedStatement(userSQL, properties, true);
        }
    }

    public Map<Integer, TransportNode> getAllNodes() throws SQLException
    {
        Map<Integer, TransportNode> nodes = new HashMap<>();

        String SQL = "SELECT * FROM `STOPS_STATIONS`";
        ResultSet stops = database.pushStatement(SQL);

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

        String getNodeInformation = "SELECT * FROM `TRANSPORT_STOP_RELATION`"
                + " INNER JOIN `LINE_IDENTITY`"
                + " ON `TRANSPORT_STOP_RELATION`.transport_ref = `LINE_IDENTITY`.line_ref"
                + " INNER JOIN `STOPS_STATIONS`"
                + " ON `TRANSPORT_STOP_RELATION`.stop_ref = `STOPS_STATIONS`.ref"
                + " AND `TRANSPORT_STOP_RELATION`.stop_ref = ?";

        Integer[] nodeInformationProperties =
        {
            fromStopReference
        };
        ResultSet node = database.pushPreparedStatement(getNodeInformation, nodeInformationProperties, false);

        while (node.next())
        {
            String type = node.getString("TYPE");
            int fromStopRef = node.getInt("STOP_REF");
            int transportID = node.getInt("LINE_REF");
            String transportName = node.getString("LINE_NAME");
            int stopSequenceIndex = node.getInt("STOP_SEQUENCE");

            String toRef = "SELECT stop_ref FROM `TRANSPORT_STOP_RELATION`"
                    + " WHERE transport_ref = ?"
                    + " AND (stop_sequence = ?"
                    + " OR stop_sequence = ?)";

            Integer[] toRefProperties =
            {
                transportID, (stopSequenceIndex - 1), (stopSequenceIndex + 1)
            };

            ResultSet toRefResult = database.pushPreparedStatement(toRef, toRefProperties, false);

            while (toRefResult.next())
            {
                int toStopRef = toRefResult.getInt("STOP_REF");

                Edge.EdgeType edgeType = Edge.EdgeType.valueOf(type);
                Edge edge = new Edge(edgeType, nodes.get(fromStopRef), nodes.get(toStopRef), transportID, transportName);

                boolean addEdge = true;
                for (Edge existing : edges)
                {
                    if (shouldAddEdge(existing, edge))
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

    private boolean shouldAddEdge(Edge existing, Edge edge)
    {
        if (existing == null || edge == null || existing.toNode == null || edge.toNode == null)
        {
            return false;
        }
        return existing.toNode.referenceID == edge.toNode.referenceID && existing.transportLineReference == edge.transportLineReference;
    }

    public Map<Integer, Zone> getZoneMap() throws SQLException
    {
        String getZones = "SELECT * FROM `ZONEMAP`";
        Map<Integer, Zone> zones = new HashMap<>();

        ResultSet result = database.pushStatement(getZones);

        while (result.next())
        {
            int zoneNumber = result.getInt("ZONE");
            int neighbourNumber = result.getInt("NEIGHBOURZONES");

            if (zones.containsKey(zoneNumber))
            {
                zones.get(zoneNumber).addNeighbour(new Zone(neighbourNumber));
            } else
            {
                Zone temp = new Zone(zoneNumber);
                temp.addNeighbour(new Zone(neighbourNumber));
                zones.put(zoneNumber, temp);
            }
        }

        return zones;
    }

}
