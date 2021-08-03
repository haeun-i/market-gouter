import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnectionTest {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "Jdbc:mysql://database-1.csgon9khdash.ap-northeast-2.rds.amazonaws.com:3306/springstudy";
    private static final String USER = "admin";
    private static final String PW = "12345678";

    @Test
    public void testConnection() throws Exception{
        Class.forName(DRIVER);
        try(Connection con = DriverManager.getConnection(URL, USER, PW)){
            System.out.println(con);
        }
        catch(Exception e){
            System.out.println(e);

        }
    }
}
