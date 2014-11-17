/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficrouting;

import helpers.Zone;
import execute.Server;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JamesFoxes
 */
public class GraphObject
{

    private GraphTransmitObject toTransfer;

    public GraphObject(Map<Integer, TransportNode> nodes)
    {
        try
        {
            Map<Integer, Zone> zoneMap = Server.database.getZoneMap();
            toTransfer = new GraphTransmitObject(nodes, zoneMap);
        } catch (SQLException ex)
        {
            Logger.getLogger(GraphObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public GraphTransmitObject getTransmitObject()
    {
        return toTransfer;
    }

    public static class GraphTransmitObject implements Serializable
    {

        public final Map<Integer, TransportNode> nodes;
        public final Map<Integer, Zone> zoneMap;

        public GraphTransmitObject(Map<Integer, TransportNode> nodes, Map<Integer, Zone> zoneMap)
        {
            this.nodes = nodes;
            this.zoneMap = zoneMap;
        }
    }
}
