/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author James
 */
public class Zone implements Iterable<Zone>, Serializable
{

    public int zone;
    private ArrayList<Zone> neighbours;

    public Zone(int zone)
    {
        neighbours = new ArrayList<>();
        this.zone = zone;
    }

    public void addNeighbour(Zone zone)
    {
        neighbours.add(zone);
    }

    @Override
    public Iterator<Zone> iterator()
    {
        return neighbours.iterator();
    }
}
