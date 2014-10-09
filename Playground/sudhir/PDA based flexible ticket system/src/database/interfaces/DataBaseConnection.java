

package database.interfaces;

import java.sql.SQLException;


public interface DataBaseConnection
{
    public boolean doUpdate(String toUpdate);
    public boolean doCreate();
    public boolean doFetch();
    public boolean doDelete();
}
