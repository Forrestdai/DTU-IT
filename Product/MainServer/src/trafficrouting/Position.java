/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficrouting;

import java.io.Serializable;

/**
 *
 * @author JamesFoxes
 */
public class Position implements Serializable
{
    double lat;
    double lon;

    public Position(double lat, double lon)
    {
        this.lat = lat;
        this.lon = lon;
    }
    
}
