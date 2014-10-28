/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.util.*;

/**
 * 
 * @author JamesFoxes
 */
class DirectedGraph<E> implements Iterable<E>
{

    private final Map<E, Map<E, Double>> graph = new HashMap<>();

    public boolean addNode(E node)
    {
        if (graph.containsKey(node))
        {
            return false;
        }

        graph.put(node, new HashMap<E, Double>());
        return true;
    }

    public void addEdge(E start, E destination, double length)
    {
        if (!graph.containsKey(start) || !graph.containsKey(destination))
        {
            throw new NoSuchElementException("A node is missing from the graph.");
        }

        graph.get(start).put(destination, length);
    }

    public void removeEdge(E start, E destination)
    {
        if (!graph.containsKey(start) || !graph.containsKey(destination))
        {
            throw new NoSuchElementException("Both nodes must be in the graph.");
        }

        graph.get(start).remove(destination);
    }

    public Map<E, Double> getEdgesFromNode(E node)
    {
        Map<E, Double> arcs = graph.get(node);
        if (arcs == null)
        {
            throw new NoSuchElementException("Source node does not exist.");
        }

        return Collections.unmodifiableMap(arcs);
    }

    @Override
    public Iterator<E> iterator()
    {
        return graph.keySet().iterator();
    }
}
