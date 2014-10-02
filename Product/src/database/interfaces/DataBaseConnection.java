/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database.interfaces;

import java.sql.SQLException;

/**
 *
 * @author James
 */
public interface DataBaseConnection
{
    public abstract boolean transmitToDatabase() throws SQLException;
    public boolean doUpdate();
    public boolean doCreate();
    public boolean doFetch();
    public boolean doDelete();
}
