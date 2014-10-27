/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author JamesFoxes
 */
public class AStarTraversal<E extends transportNode_I>
{
    private final FibonacciHeap<E> myHeap;
    private final Map<E, FibonacciHeap.HeapElement<E>> heapGraphEntries;
    private final Map<E, Double> visitedNodes;

    public AStarTraversal()
    {
        this.visitedNodes = new HashMap<>();
        this.heapGraphEntries = new HashMap<>();
        this.myHeap = new FibonacciHeap<>();
    }

    public Map<E, Double> findShortestPaths(DirectedGraph<E> graphToSearch, E startPoint, E targetPoint)
    {
        //initialize the entries
        for (E node : graphToSearch)
        {
            heapGraphEntries.put(node, myHeap.enqueueElement(node, node.getCost()));
        }
        
        if (startPoint.equals(targetPoint))
        {
            visitedNodes.put(startPoint, 0.0);
            return visitedNodes;
        }

        //myHeap.decreaseKey(heapGraphEntries.get(startPoint), 0.0);

        FibonacciHeap.HeapElement<E> currentlyVisitedNode;
        do
        {
            currentlyVisitedNode = myHeap.dequeueMinElement();
            visitedNodes.put(currentlyVisitedNode.getNodeContents(), currentlyVisitedNode.getNodePriority());
            
            Set<Map.Entry<E, Double>> neighbourSet = graphToSearch.getEdgesFromNode(currentlyVisitedNode.getNodeContents()).entrySet();
            updateNeighbourPriorities(neighbourSet, currentlyVisitedNode);
            
        } while (!myHeap.isEmpty() && !currentlyVisitedNode.equals(targetPoint));
        return visitedNodes;
    }

    private void updateNeighbourPriorities(Set<Map.Entry<E, Double>> neighbourSet, FibonacciHeap.HeapElement<E> currentlyVisitedNode)
    {
        for (Map.Entry<E, Double> neighbour : neighbourSet)
        {
            // skip visited
            if (visitedNodes.containsKey(neighbour.getKey()))
            {
                continue;
            }

            // update neighbour
            double pathCost = currentlyVisitedNode.getNodePriority() + neighbour.getValue(); //map value is priority

            FibonacciHeap.HeapElement<E> destination = heapGraphEntries.get(neighbour.getKey());
            if (pathCost < destination.getNodePriority())
            {
                myHeap.decreaseKey(destination, pathCost);
            }
        }
    }
}
