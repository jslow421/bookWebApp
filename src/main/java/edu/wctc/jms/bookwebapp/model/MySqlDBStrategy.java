/*
 * This software was written by John Slowik
 * Please don't do anything he wouldn't do
 *
 */
package edu.wctc.jms.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author John Slowik <jslowik@my.wctc.edu>
 */
public class MySqlDBStrategy implements DBStrategy {

    private Connection conn;

    public MySqlDBStrategy() {
    }

    public MySqlDBStrategy(String driverClass, String url, String userName, String password)
            throws ClassNotFoundException, SQLException {

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    @Override
    public void openConnection(String driverClass, String url, String userName, String password)
            throws ClassNotFoundException, SQLException {

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }

    @Override
    public void closeConnection() throws SQLException {
        conn.close();
    }

    // what would be the downside to using a constructor over this method
    /*
    using methods allows you to open connections to more than one database.
    using the constructor to create two db objects is a waste of memory.
    creating connections is expensive, minimize number, and length of time it is open.
     */
    //keep this generic!! Treat every record as a map so it isn't specific to any one thing
    /**
     * Make sure you open and close a connection when using this method!
     *
     * Future optimizations may include changing return type to an array to
     * preserve some server resources
     *
     * @param tableName
     * @param maxRecords - Limit records found to maxRecords value. If
     * maxRecords is zero (0) then there is no limit
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public List<Map<String, Object>> findAllRecords(String tableName, int maxRecords)
            throws SQLException {
        String sql;
        if (maxRecords < 1) {
            sql = "select * from " + tableName;
        } else {
            sql = "select * from " + tableName + " limit " + maxRecords;
        }
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData(); //get table meta data
        int columnCount = rsmd.getColumnCount(); //get column count from table meta data
        List<Map<String, Object>> records = new ArrayList<>();

        while (rs.next()) {
            Map<String, Object> record = new HashMap<>();
            for (int colNo = 1; colNo <= columnCount; colNo++) { // for loop is just for columns
                Object colData = rs.getObject(colNo); // iterates through and collects column 
                //data based on column number. Saves information as an object
                String colName = rsmd.getColumnName(colNo); //get column name
                record.put(colName, colData); // add column name string, and object column info
            }
            records.add(record); // add map to list (in other words add record to the return)
        }

        return records;
    }

    /**
     * Remove a database record by column id
     *
     * @param tableName
     * @param columnName
     * @param value
     * @return
     * @throws SQLException
     */
    @Override
    public int deleteRecordByID(String tableName, String columnName, Object value)
            throws SQLException {
        
        String sql = "Delete from " + tableName + " where " + columnName + "= ?";
        PreparedStatement psmt = conn.prepareStatement(sql);// this line sends to server. compiled
        psmt.setObject(1, value);
        
        return psmt.executeUpdate(); 
        
        
/* My attempt - not using prepared statement, but essentially same - no Object
        String sql = "Delete from " + tableName + " where " + columnName + "= " + value;
        Statement stmt = conn.createStatement();

        int recordsRemoved = stmt.executeUpdate(sql);

        return recordsRemoved;
        */
    }
    
    
    

    /**
     * Testing
     *
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBStrategy db = new MySqlDBStrategy();

        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book",
                "root", "admin");

        List<Map<String, Object>> rawData = db.findAllRecords("author", 0);

        //int updates = db.deleteRecordByID("author", "author_id", 2);

        db.closeConnection();

        System.out.println(rawData);
        //System.out.println("Records removed: " + updates);

    }
}
