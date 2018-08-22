/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentenrollment.utilities;


import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
//import javax.sql.rowset.CachedRowSet;
import com.sun.rowset.CachedRowSetImpl; // can cache 100 rows
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

/**
 *
 * @author bhadr
 */
public class DBUtil {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static Connection connection = null;
    private static final String conndb = "jdbc:mysql://localhost/studentenrollment";

    //connect to database
    public static void dbConnect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("MYSQL Driver not found!");
            e.printStackTrace();
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Driver Error");
            alert.setHeaderText("");
            alert.setContentText("MYSQL Driver not found!");
            alert.showAndWait();
            System.exit(1);
//            throw e;
        }
        System.out.println("JDBC Driver registered :)");

        try {
            connection = DriverManager.getConnection(conndb, "root", "");
        } catch (SQLException e) {
            System.out.println("Connection Faild! " + e);
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("DB Error");
            alert.setHeaderText("");
            alert.setContentText("Server not found");
            alert.showAndWait();
            System.exit(1);
//            throw e;
        }
    }

    // disconect from database
    public static void dbDisconnect() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
//            throw e;
        }
    }

    //insert delete or update operation
    public static void dbExcuteQuery(String sqlquery) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            dbConnect();
            stmt = connection.createStatement();
            stmt.executeUpdate(sqlquery);

        } catch(MySQLIntegrityConstraintViolationException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("");
            alert.setContentText("Duplicate Entry!!!");
            alert.showAndWait();
            
            System.out.println("Intergrity constrain occured " + e);
//            throw e;
            
        } catch (SQLException e) {
            System.out.println("Problem occured at dbExcuteQuery " + e);
//            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }
    // retrieving records from tables
    public static ResultSet dbExcute(String sqlquery) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;        //to store data in memory, that can handle data without live connection

        try {
            dbConnect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sqlquery);
            crs = new CachedRowSetImpl();
            crs.populate(rs);
        } catch (SQLException e) {
            System.out.println("Problem occured in dbExcute ");
            //throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();           
        }
        return crs;
    }
}
