/*
 * This software was written by John Slowik
 * Please don't do anything he wouldn't do
 *
 */
package edu.wctc.jms.bookwebapp.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;

/**
 * @author John Slowik <jslowik@my.wctc.edu>
 */
@SessionScoped
public class MySqlDBStrategy implements DBStrategy, Serializable {

    private Connection conn;

    /**
     * Default constructor for injection purposes
     * 
     */
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
     * <p>
     * Future optimizations may include changing return type to an array to
     * preserve some server resources
     *
     * @param tableName
     * @param maxRecords - Limit records found to maxRecords value. If
     *                   maxRecords is zero (0) then there is no limit
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
     * @param tableName  - name of table that contains information to be deleted
     * @param columnName - column name to pick value from
     * @param value      - value in column to select record for deletion
     * @return - returns int value for number of records effected
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

    public int updateRecordByID(String tableName, List<String> colNames, List<Object> colValues,
                                String pkColName, Object value) throws SQLException {

        PreparedStatement pstmt = null;
        int recsUpdated = 0;

        // do this in an excpetion handler so that we can depend on the
        // finally clause to close the connection
        try {
            pstmt = buildUpdateStatement(conn, tableName, colNames, pkColName);

            final Iterator i = colValues.iterator();
            int index = 1;
            Object obj = null;

            // set params for column values
            while (i.hasNext()) {
                obj = i.next();
                pstmt.setObject(index++, obj);
            }
            // and finally set param for wehere value
            pstmt.setObject(index, value);

            recsUpdated = pstmt.executeUpdate();

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                throw e;
            } // end try
        } // end finally

        return recsUpdated;

    }

    /*
     * Builds a java.sql.PreparedStatement for an sql update using only one where clause test
	 * @param conn - a JDBC <code>Connection</code> object
	 * @param tableName - a <code>String</code> representing the table name
	 * @param colDescriptors - a <code>List</code> containing the column descriptors for
	 * the fields that can be updated.
	 * @param whereField - a <code>String</code> representing the field name for the
	 * search criteria.
	 * @return java.sql.PreparedStatement
	 * @throws SQLException
     */
    private PreparedStatement buildUpdateStatement(Connection conn_loc, String tableName,
                                                   List colDescriptors, String whereField)
            throws SQLException {
        StringBuffer sql = new StringBuffer("UPDATE ");
        (sql.append(tableName)).append(" SET ");
        final Iterator i = colDescriptors.iterator();
        while (i.hasNext()) {
            (sql.append((String) i.next())).append(" = ?, ");
        }
        sql = new StringBuffer((sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")));//remove last comma
        ((sql.append(" WHERE ")).append(whereField)).append(" = ?");
        final String finalSQL = sql.toString();
        return conn_loc.prepareStatement(finalSQL);
    }

    /**
     * Insert a record into the database
     *
     * Need to get date working - using LocalDate, not java date
     *
     * @param tableName - name of table to add record to
     * @param authorName - name of author to be added
     * @return - returns number of records added
     * @throws SQLException
     */
    @Override
    public int insertRecord(String tableName, String authorName) throws SQLException {

        String sql = "insert into " + tableName + " (author_name) Values('" + authorName + "')";
        Statement stmt = conn.createStatement();



        return stmt.executeUpdate(sql);

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

        int insert = db.insertRecord("author", "John Poe");

        List<Map<String, Object>> rawData = db.findAllRecords("author", 0);

        //int updates = db.deleteRecordByID("author", "author_id", 2);

       // List<String> colNames = Arrays.asList("author_name");
       // List<Object> colValues = Arrays.asList("Lucifer");
        //int result = db.updateRecordByID("author", colNames, colValues, "author_id", 2);

        db.closeConnection();

        System.out.println(rawData);
        System.out.println("Added " + insert + " record(s)");
        //System.out.println("Records removed: " + updates);
        //System.out.println(result);

    }
}
