/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author JamesFoxes
 */
public class GraphTest
{

    public GraphTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    //@Test
    public void testAddNodesOneDirectionManual()
    {
        Map<String, TransportNode> nodes = new HashMap<>();
        nodes.put("Start", new TransportNode(new Position(-3000, -100), "Start"));
        nodes.put("A", new TransportNode(new Position(-3000, 220), "A"));
        nodes.put("B", new TransportNode(new Position(-2400, 0), "B"));
        nodes.put("C", new TransportNode(new Position(-1850, 150), "C"));
        nodes.put("D", new TransportNode(new Position(-1300, 180), "D"));
        nodes.put("Goal", new TransportNode(new Position(-700, -50), "Goal"));

        nodes.get("Start").addEdge(new Edge(Edge.EdgeType.WALKING, nodes.get("Start"), nodes.get("A")));
        nodes.get("Start").addEdge(new Edge(Edge.EdgeType.WALKING, nodes.get("Start"), nodes.get("B")));

        nodes.get("A").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("A"), nodes.get("B")));
        nodes.get("A").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("A"), nodes.get("C")));

        nodes.get("B").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("B"), nodes.get("C")));
        nodes.get("B").addEdge(new Edge(Edge.EdgeType.TRAIN, nodes.get("B"), nodes.get("Goal")));

        nodes.get("C").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("C"), nodes.get("D")));
        nodes.get("C").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("C"), nodes.get("Goal")));

        nodes.get("D").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("D"), nodes.get("Goal")));

        Graph graph = new Graph(nodes.get("Goal").identity);
        graph.addNodes(nodes);

        DirectedGraph<TransportNode> graphToSearch = graph.getDirectedGraph();

        AStarTraversal graphTraverser = new AStarTraversal();
        Map<TransportNode, Double> result = graphTraverser.findShortestPath(graphToSearch, nodes.get("Start"), nodes.get("Goal"));

        for (Map.Entry<TransportNode, Double> entrySet : result.entrySet())
        {
            TransportNode key = entrySet.getKey();
            Double value = entrySet.getValue();

            System.out.println("key: " + key.identity);
            System.out.println("value: " + value);
            System.out.println("PATH:" + key.returnNode.identity);
            System.out.println("");
        }
    }

    //@Test
    public void testAddNodesBothDirectionsManual()
    {
        Map<String, TransportNode> nodes = new HashMap<>();
        nodes.put("Start", new TransportNode(new Position(-3000, -100), "Start"));
        nodes.put("A", new TransportNode(new Position(-3000, 220), "A"));
        nodes.put("B", new TransportNode(new Position(-2400, 0), "B"));
        nodes.put("C", new TransportNode(new Position(-1850, 150), "C"));
        nodes.put("D", new TransportNode(new Position(-1300, 180), "D"));
        nodes.put("Goal", new TransportNode(new Position(-700, -50), "Goal"));

        nodes.get("Start").addEdge(new Edge(Edge.EdgeType.WALKING, nodes.get("Start"), nodes.get("A")));
        nodes.get("Start").addEdge(new Edge(Edge.EdgeType.WALKING, nodes.get("Start"), nodes.get("B")));

        nodes.get("A").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("A"), nodes.get("B")));
        nodes.get("A").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("A"), nodes.get("C")));
        nodes.get("A").addEdge(new Edge(Edge.EdgeType.WALKING, nodes.get("A"), nodes.get("Start")));

        nodes.get("B").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("B"), nodes.get("C")));
        nodes.get("B").addEdge(new Edge(Edge.EdgeType.TRAIN, nodes.get("B"), nodes.get("Goal")));
        nodes.get("B").addEdge(new Edge(Edge.EdgeType.WALKING, nodes.get("B"), nodes.get("Start")));
        nodes.get("B").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("B"), nodes.get("A")));

        nodes.get("C").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("C"), nodes.get("D")));
        nodes.get("C").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("C"), nodes.get("Goal")));
        nodes.get("C").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("C"), nodes.get("A")));
        nodes.get("C").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("C"), nodes.get("B")));

        nodes.get("D").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("D"), nodes.get("Goal")));
        nodes.get("D").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("D"), nodes.get("C")));

        nodes.get("Goal").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("Goal"), nodes.get("D")));
        nodes.get("Goal").addEdge(new Edge(Edge.EdgeType.TRAIN, nodes.get("Goal"), nodes.get("B")));
        nodes.get("Goal").addEdge(new Edge(Edge.EdgeType.BUS, nodes.get("Goal"), nodes.get("C")));

        Graph graph = new Graph("Goal");
        graph.addNodes(nodes);

        DirectedGraph<TransportNode> graphToSearch = graph.getDirectedGraph();

        AStarTraversal graphTraverser = new AStarTraversal();
        Map<TransportNode, Double> result = graphTraverser.findShortestPath(graphToSearch, nodes.get("Start"), nodes.get("Goal"));

        for (Map.Entry<TransportNode, Double> entrySet : result.entrySet())
        {
            TransportNode key = entrySet.getKey();
            Double value = entrySet.getValue();

            System.out.println("key: " + key.identity);
            System.out.println("value: " + value);
            System.out.println("PATH:" + key.returnNode.identity);
            System.out.println("");
        }
    }
}
