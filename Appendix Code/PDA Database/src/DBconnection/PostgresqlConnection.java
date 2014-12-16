package DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * @author sudhir
 */
public class PostgresqlConnection
{
    public static void main(String[] args)
    {
        Connection connection = null;
        //    public  PostgresqlConnection(){
        try
        {
            String userName = "sudhir3e14";
                String password = "s137239";
            String url = "jdbc:postgresql://goonhilly6.eitlab.ihk-edu.dk:5432/sudhir3e14";
            Class.forName("org.postgresql.Driver").newInstance();
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection successful.");
            {
            }
        } catch (Exception e)
        {
            System.out.println("Canot connect to the Database.");
            e.printStackTrace();
        } finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                    System.out.println("Database connection closed.");
                } catch (Exception e)
                {
                    System.out.println("Database connection canot be closed.");
                }
            }
        }
    }
}











