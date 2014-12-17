/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficrouting;

import helpers.Journey;
import helpers.LogPrinter;
import helpers.ServerData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JamesFoxes
 */
public class GraphTesting
{

    @Test
    public void addNodesToGraph()
    {
        TransportNode start = new TransportNode(new Position(55, 12), "A", 1, 1);
        TransportNode end = new TransportNode(new Position(55.5, 12.1), "B", 2, 1);
        GraphObject toReturn = getGraph(start, end);

        DirectedGraph<TransportNode> testGraph = toReturn.getGraph(5);

        for (Iterator<TransportNode> iterator = testGraph.iterator(); iterator.hasNext();)
        {
            TransportNode next = iterator.next();
            LogPrinter.print(next.identity);
        }
    }

    @Test
    public void findShortestPath()
    {
        TransportNode start = new TransportNode(new Position(55, 12), "A", 1, 1);
        TransportNode end = new TransportNode(new Position(54.3, 12.3), "E", 5, 2);

        GraphObject toReturn = getGraph(start, end);

        DirectedGraph<TransportNode> testGraph = toReturn.getGraph(5);
        AStarTraversal traversal = new AStarTraversal();
        Map<TransportNode, Double> result = traversal.findShortestPaths(testGraph, start, end);

        for (Map.Entry<TransportNode, Double> entrySet : result.entrySet())
        {
            TransportNode key = entrySet.getKey();
            Double value = entrySet.getValue();

            LogPrinter.print("key: " + key.identity);
            LogPrinter.print("value: " + value);
            LogPrinter.print("PATH:" + key.returnNode.identity);
            LogPrinter.print("");

            if (key.referenceID == 5)
            {
                TransportNode node = key;
                while (node.returnNode.referenceID != node.referenceID)
                {
                    System.out.print(node.identity + " -> ");
                    node = node.returnNode;
                }
                LogPrinter.print(node.identity);
                assertEquals(1, node.referenceID);

            }
        }
    }

    @Test
    public void journeyObject()
    {
        TransportNode start = new TransportNode(new Position(55, 12), "A", 1, 1);
        TransportNode end = new TransportNode(new Position(54.3, 12.3), "E", 5, 2);
        GraphObject toReturn = getGraph(start, end);
        DirectedGraph<TransportNode> testGraph = toReturn.getGraph(5);
        new AStarTraversal().findShortestPaths(testGraph, start, end);

        Stack<TransportNode> resultStack = fillStack(end);
        double cost = calculateCost(resultStack);
        double time = calculateTime(resultStack, end);
        
        assertTrue(time > start.cost / 130);
        assertTrue(cost >= 1.0);
        
        LogPrinter.print("Cost: " + cost);
        LogPrinter.print("Time: " + time + " min");
    }

    private Stack<TransportNode> fillStack(TransportNode endPoint)
    {
        Stack<TransportNode> journey = new Stack<>();
        TransportNode node = endPoint;

        while (node.returnNode != node)
        {
            journey.add(node);
            node = node.returnNode;
        }
        journey.add(node);
        return journey;
    }

    private double calculateCost(Stack<TransportNode> journey)
    {
        Stack<TransportNode> tempStack = (Stack<TransportNode>) journey.clone();

        int numberOfZonesGoneThrough = 0;
        ArrayList<Integer> zonesGoneThrough = new ArrayList<>();

        TransportNode node;
        while (!tempStack.empty())
        {
            node = tempStack.pop();
            if (!zonesGoneThrough.contains(node.zone))
            {
                ++numberOfZonesGoneThrough;
                zonesGoneThrough.add(node.zone);
            }
        }
        return ServerData.costPerZone * numberOfZonesGoneThrough;
    }

    private double calculateTime(Stack<TransportNode> journey, TransportNode endPoint)
    {
        double timeToTravel = 0;
        Stack<TransportNode> tempStack = (Stack<TransportNode>) journey.clone();

        TransportNode node;
        while (!tempStack.empty())
        {
            node = tempStack.pop();
            if (!node.equals(endPoint))
            {
                for (Edge edge : node)
                {
                    if (edge.toNode.equals(tempStack.peek()))
                    {
                        timeToTravel += edge.cost;
                        break;
                    }
                }
            }
        }
        return timeToTravel;
    }

    public static GraphObject getGraph(TransportNode start, TransportNode end)
    {
        Map<Integer, trafficrouting.TransportNode> nodes = new HashMap<>();
        nodes.put(1, start);
        nodes.put(2, new TransportNode(new Position(55.5, 12.1), "B", 2, 1));
        nodes.put(3, new TransportNode(new Position(55.9, 12.2), "C", 3, 2));
        nodes.put(4, new TransportNode(new Position(54.8, 12.7), "D", 4, 2));
        nodes.put(5, end);
        nodes.put(6, new TransportNode(new Position(55.2, 12.22), "F", 6, 2));
        nodes.put(7, new TransportNode(new Position(55, 12.1), "G", 7, 3));
        nodes.put(8, new TransportNode(new Position(55.5, 12.12), "H", 8, 3));
        nodes.put(9, new TransportNode(new Position(54, 12), "I", 9, 3));
        nodes.put(10, new TransportNode(new Position(53, 12.9), "J", 10, 3));

        for (Map.Entry<Integer, TransportNode> entrySet : nodes.entrySet())
        {
            Integer key = entrySet.getKey();
            TransportNode value = entrySet.getValue();
            for (int i = 0; i < 5; ++i)
            {
                int index = (int) (Math.random() * 10) + 1;
                if (index != key)
                {
                    value.addEdge(new Edge(Edge.EdgeType.BUS, value, nodes.get(index), 101, "A2"));
                }
            }
        }

        GraphObject toReturn = new GraphObject(new GraphObject.GraphTransmitObject(nodes, null));
        return toReturn;
    }
}
