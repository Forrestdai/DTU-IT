/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficrouting;

import java.util.HashMap;
import java.util.Iterator;
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
    private DirectedGraph<TransportNode> graphToSearch;

    public AStarTraversal()
    {
        this.visitedNodes = new HashMap<>();
        this.heapGraphNodes = new HashMap<>();
        this.myHeap = new FibonacciHeap<>();
    }
    
    public void updateGraph(DirectedGraph<TransportNode> graphToSearch)
    {
        this.graphToSearch = graphToSearch;
    }

    public Map<TransportNode, Double> findShortestPaths(TransportNode startPoint, TransportNode goalNode)
    {
        //initialize the entries
        for (TransportNode node : graphToSearch)
        {
            heapGraphNodes.put(node, myHeap.enqueueElement(node, Double.POSITIVE_INFINITY));
        }

        if (startPoint.equals(goalNode))
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

            for (Iterator<Edge> iterator = currentlyVisitedNode.getNodeContents().iterator(); iterator.hasNext();)
            {
                Edge edge = iterator.next();

                if (!visitedNodes.containsKey(edge.toNode))
                {
                    Double newCost = currentlyVisitedNode.getNodePriority() + (edge.toNode.cost / 130) + edge.cost;

                    FibonacciHeap.HeapElement<TransportNode> destination = heapGraphNodes.get(edge.toNode);

                    if (newCost < destination.getNodePriority())
                    {
                        myHeap.decreaseKey(destination, newCost);
                        destination.getNodeContents().returnNode = edge.fromNode;
                    }
                }
            }
        } while (!myHeap.isEmpty() && !currentlyVisitedNode.equals(heapGraphNodes.get(goalNode)));
        return visitedNodes;
    }
}
