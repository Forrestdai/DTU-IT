/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.util.*;

/**
 * Efficiency: O(E + V * log(V))
 * @author JamesFoxes
 */
public class DijkstraTraversal<E>
{

    private final FibonacciHeap<E> myHeap;
    private final Map<E, FibonacciHeap.HeapElement<E>> heapGraphEntries;
    private final Map<E, Double> visitedNodes;

    public DijkstraTraversal()
    {
        this.visitedNodes = new HashMap<>();
        this.heapGraphEntries = new HashMap<>();
        this.myHeap = new FibonacciHeap<>();
    }

    public Map<E, Double> findShortestPaths(DirectedGraph<E> graphToSearch, E startPoint)
    {
        //initialize the entries
        for (E node : graphToSearch)
        {
            heapGraphEntries.put(node, myHeap.enqueueElement(node, Double.POSITIVE_INFINITY));
        }

        myHeap.decreaseKey(heapGraphEntries.get(startPoint), 0.0);

        while (!myHeap.isEmpty())
        {
            FibonacciHeap.HeapElement<E> currentlyVisitedNode = myHeap.dequeueMinElement();
            visitedNodes.put(currentlyVisitedNode.getNodeContents(), currentlyVisitedNode.getNodePriority());
            
            Set<Map.Entry<E, Double>> neighbourSet = graphToSearch.getEdgesFromNode(currentlyVisitedNode.getNodeContents()).entrySet();
            updateNeighbourPriorities(neighbourSet, currentlyVisitedNode);
        }
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
