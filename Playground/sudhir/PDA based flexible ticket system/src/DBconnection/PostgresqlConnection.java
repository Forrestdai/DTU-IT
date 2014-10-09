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
        Connection conn = null;
        //    public  PostgresqlConnection(){
        try
        {
            String userName = "sudhir3e14";
            String password = "s137239";
            String url = "jdbc:postgresql://goonhilly6.eitlab.ihk-edu.dk:5432/sudhir3e14";
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection successful.");
            {
            }
        } catch (Exception e)
        {
            System.out.println("Canot connect to the Database.");
            e.printStackTrace();
        } finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                    System.out.println("Database connection closed.");
                } catch (Exception e)
                {
                    System.out.println("Database connection canot be closed.");
                }
            }
        }
    }
}













//                String sql = "insert into customerinfo values(5,'sudhir','chaurasiya')";
//                Statement st = conn.createStatement();
//                st.execute(sql)
//                String sql = "insert into customerinfo values(5,'sudhir')";
//                System.out.println("Successfully inserted");
//                String sql = "delete from testing where id=5";
//                String sql = "UPDATE into testing SET id=1, name ='sudhir' where id = 5;
//                String sql = "create table person (id varchar(5), name varchar(50))";
