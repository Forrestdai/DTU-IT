/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficrouting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.junit.Test;

/**
 *
 * @author JamesFoxes
 */
public class GraphTesting
{

    @Test
    public void addNodesToGraph()
    {
        GraphTransmitObject toReturn = getGraph();

        DirectedGraph<TransportNode> testGraph = toReturn.getGraph(5);

        for (Iterator<TransportNode> iterator = testGraph.iterator(); iterator.hasNext();)
        {
            TransportNode next = iterator.next();
            System.out.println(next.identity);
            for (Edge edge : next)
            {
                System.out.println("edge: " + edge.cost);
            }
        }
    }

    public static GraphTransmitObject getGraph()
    {
        Map<Integer, trafficrouting.TransportNode> nodes = new HashMap<>();
        nodes.put(1, new TransportNode(new Position(55, 12), "A", 1, 1));
        nodes.put(2, new TransportNode(new Position(55.5, 12.1), "B", 2, 1));
        nodes.put(3, new TransportNode(new Position(55.9, 12.2), "C", 3, 2));
        nodes.put(4, new TransportNode(new Position(54.8, 12.7), "D", 4, 2));
        nodes.put(5, new TransportNode(new Position(54.3, 12.3), "E", 5, 2));
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

        GraphTransmitObject toReturn = new GraphTransmitObject(nodes);
        return toReturn;
    }
}
