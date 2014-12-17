/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection.udp;

import connection.udp.gps.GPS;
import connection.udp.gps.FauxGPSImplementation;
import threading.executiontypes.ExecuteOnImpulse;

/**
 *
 * @author JamesFoxes
 */
public class SetupUDP
{
    private ExecuteOnImpulse sendUDPSignalImpulse;
    private GPS gpsAPI;

    public SetupUDP()
    {
        sendUDPSignalImpulse = new BroadcastUDP();
        gpsAPI = new FauxGPSImplementation();
        gpsAPI.setInterface(sendUDPSignalImpulse);
        
        //gpsAPI.transmit();
    }
    
    public GPS getGPS()
    {
        return gpsAPI;
    }
    
}
