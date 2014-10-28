/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

/**
 *
 * @author JamesFoxes
 */
public class Graph
{

    private DirectedGraph<TransportNode> graph;
    private TransportNode goalNode;

    public Graph(TransportNode goalNode)
    {
        graph = new DirectedGraph<>();
        this.goalNode = goalNode;
    }

    public void addNodes(Iterable<TransportNode> nodes)
    {
        for (TransportNode node : nodes)
        {
            if (node == goalNode)
            {
                node.distanceFromGoal = 0.0;
            }

            node.distanceFromGoal = node.getDistanceTo(goalNode);
            node.cost = node.distanceFromGoal;
            graph.addNode(node);
        }
        addEdges();
    }

    private void addEdges()
    {
        for (TransportNode node : graph)
        {
            for (Edge edge : node)
            {
                edge.cost = calculateEdgeCost(edge);
                graph.addEdge(edge.fromNode, edge.toNode, edge.cost);
            }
        }
    }

    private Double calculateEdgeCost(Edge edge)
    {
        Double cost = Double.POSITIVE_INFINITY;
        switch (edge.edgeType)
        {
            case WALKING:
                cost = (edge.fromNode.getDistanceTo(edge.toNode)) / TransportSettings.SPEED_WALKING; //((distance in meters/100) / speed)
                break;
            case BUS:
                cost = (edge.fromNode.getDistanceTo(edge.toNode)) / TransportSettings.SPEED_BUS;
                break;
            case METRO:
                cost = (edge.fromNode.getDistanceTo(edge.toNode)) / TransportSettings.SPEED_METRO;
                break;
            case TRAIN:
                cost = (edge.fromNode.getDistanceTo(edge.toNode)) / TransportSettings.SPEED_TRAIN;
                break;
            case BOAT:
                cost = (edge.fromNode.getDistanceTo(edge.toNode)) / TransportSettings.SPEED_BOAT;
                break;
        }

        return cost;
    }

    public DirectedGraph<TransportNode> getGraph()
    {
        return graph;
    }
}
