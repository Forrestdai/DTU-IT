/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author JamesFoxes
 */
public class AStarTraversal
{

    private final FibonacciHeap<TransportNode> myHeap;
    private final Map<TransportNode, FibonacciHeap.HeapElement<TransportNode>> heapGraphNodes;
    private final Map<TransportNode, Double> visitedNodes;

    public AStarTraversal()
    {
        this.visitedNodes = new HashMap<>();
        this.heapGraphNodes = new HashMap<>();
        this.myHeap = new FibonacciHeap<>();
    }

    public Map<TransportNode, Double> findShortestPaths(DirectedGraph<TransportNode> graphToSearch, TransportNode startPoint, TransportNode targetPoint)
    {
        //initialize the entries
        for (TransportNode node : graphToSearch)
        {
            heapGraphNodes.put(node, myHeap.enqueueElement(node, Double.POSITIVE_INFINITY));
        }

        if (startPoint.equals(targetPoint))
        {
            visitedNodes.put(startPoint, 0.0);
            return visitedNodes;
        }

        myHeap.decreaseKey(heapGraphNodes.get(startPoint), 0.0);

        FibonacciHeap.HeapElement<TransportNode> currentlyVisitedNode;
        
        do
        {
            currentlyVisitedNode = myHeap.dequeueMinElement();
            visitedNodes.put(currentlyVisitedNode.getNodeContents(), currentlyVisitedNode.getNodePriority());

            for (Edge edge : currentlyVisitedNode.getNodeContents())
            {
                Double newCost = currentlyVisitedNode.getNodePriority() + (edge.toNode.cost / 100) + edge.cost;

                FibonacciHeap.HeapElement<TransportNode> destination = heapGraphNodes.get(edge.toNode);

                if (newCost < destination.getNodePriority())
                {
                    myHeap.decreaseKey(destination, newCost);
                    destination.getNodeContents().returnNode = edge.fromNode;
                }
            }
        } while (!myHeap.isEmpty() && !currentlyVisitedNode.equals(heapGraphNodes.get(targetPoint)));
        return visitedNodes;
    }
}
