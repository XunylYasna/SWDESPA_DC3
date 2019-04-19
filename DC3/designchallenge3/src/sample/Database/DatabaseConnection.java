package sample.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String url = "jdbc:mysql://localhost:3306/songhub?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "toor";//"Mionixsargas101!";

    static Connection conn = null;

    private DatabaseConnection(){

    }

    public static Connection getDatabaseConn(){
        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }


}
