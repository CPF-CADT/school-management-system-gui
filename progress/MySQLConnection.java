package progress;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

import javax.naming.NameNotFoundException;

import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

public class MySQLConnection {
    private static Connection connection = null;
    private static final String URL = "jdbc:mysql://mysql-436bbed-student-f997.h.aivencloud.com:22721/defaultdb";
    private static final String USERNAME = "avnadmin";
    private static final String PASSWORD = "AVNS_ikPHseBNRutTggiBZ6w";

    // public static Connection getConnection() {
    //     if (connection == null) {
    //         try {
    //             connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    //             // System.out.println("Connected to MySQL successfully!");
    //         }catch (CJCommunicationsException e) {
    //             System.out.println("please check ur db server");
               
    //         }
    //         catch (CommunicationsException e) {
    //             System.out.println("please check ur db server");
                
    //         }
    //         catch (SQLSyntaxErrorException e) {
    //             System.out.println("Connection failed!");
                
    //         }
    //         catch (SQLException e) {
    //             System.out.println("Connection failed!");
                
    //         }
    //     }
    //     return connection;
    // }

    public static Connection getConnection() {
        String host, port, databaseName, userName, password;
        host = port = databaseName = userName = password = null;
        host = "mysql-436bbed-student-f997.h.aivencloud.com";
        port = "22721";
        databaseName ="school";
        userName = "avnadmin";
        password = "AVNS_ikPHseBNRutTggiBZ6w";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?sslmode=require", userName, password);
            
            return connection;
        }catch(ClassNotFoundException w){
            System.out.println("No dtiver Found");
        }catch (SQLException d){
            System.out.println("ERROR: Could not connect to the server. Please check your network connection.");
        }
        return null;
    }

    public static ResultSet executeQuery(String query) {
        try {
            Statement statement = getConnection().createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Query execution failed!");
        }
        return null;
    }

    // Execute an update (INSERT, UPDATE, DELETE)
    public static int executeUpdate(String query) {
        try {
            Statement statement = getConnection().createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Update execution failed! "+e.getMessage());
        }
        return 0;
    }

    // Close the connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                System.out.println("Failed to close the connection!");
            }
        }
    }
    public static void main(String[] args) {
        MySQLConnection.getConnection();
        
    }
}
