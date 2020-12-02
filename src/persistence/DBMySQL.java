package persistence; // in

import java.nio.file.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBMySQL {

    private static DBMySQL firstInstance = null;
    private static Connection con;
//create from file and read from that == integrity - SHA56 for the output - powershell
    final private static String user = "root";
    final private static String pwd = "password3691!";
    final private String db_name = "project";
    final private String connection = "jdbc:mysql://localhost:3306/"+db_name;




    //prevents users from Instanitation
    private DBMySQL() {
    }

    public static DBMySQL getInstance() {
        if (firstInstance == null) {
            firstInstance = new DBMySQL();
        }
        return firstInstance;
    }

    public Connection getConnection() throws SQLException {

        if (con == null || con.isClosed()) {
            try {

                Properties props = new Properties();
                props.put("user", user);
                props.put("password", pwd);
                props.put("useUnicode", "true");
                props.put("useServerPrepStmts", "false"); // use client-side prepared statement
                props.put("characterEncoding", "UTF-8"); // ensure charset is utf8 here

                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(connection, props);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return con;
    }
}