/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficrouting;

import execute.Server;
import helpers.Zone;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author James
 */
public class SearchZones
{

    private Zone startZone;
    private Zone goalZone;

    private final ArrayList<Integer> zonesSearched;
    private final Queue<Zone> toSearch;
    Map<Integer, Zone> allZones;

    public SearchZones()
    {
        allZones = Server.trafficGraph.getZones();
        zonesSearched = new ArrayList<>();
        toSearch = new LinkedList<>();
    }

    public int getZonesNeeded(TransportNode start, TransportNode goal)
    {
        this.startZone = allZones.get(start.zone);
        this.goalZone = allZones.get(goal.zone);

        return calculate();
    }

    private int calculate()
    {
        if (startZone.zone == goalZone.zone)
        {
            return 2;
        }
        int zonesNeeded = 1;

        toSearch.add(startZone);

        while (!toSearch.isEmpty())
        {
            ++zonesNeeded;
            Queue<Zone> circle = new LinkedList<>();
            circle.addAll(toSearch);
            toSearch.clear();
            runCircleSearch(circle);
        }

        return zonesNeeded;
    }

    private void runCircleSearch(Queue<Zone> search)
    {
        while (!search.isEmpty())
        {
            Zone current = search.poll();
            zonesSearched.add(current.zone);
            
            for (Zone neighbour : current)
            {
                neighbour = allZones.get(neighbour.zone);
                if (zonesSearched.contains(neighbour.zone) || search.contains(neighbour))
                {
                    continue;
                }
                if (neighbour.zone == goalZone.zone)
                {
                    toSearch.clear();
                    return;
                }

                toSearch.add(neighbour);
            }
        }
    }

}
