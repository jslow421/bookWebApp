/*
 * This software was written by John Slowik
 * Please don't do anything he wouldn't do
 *
 */
package edu.wctc.jms.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author John Slowik <jslowik@my.wctc.edu>
 */
public interface DBStrategy {

    public abstract void openConnection(String driverClass, String url,
            String userName, String password)
            throws ClassNotFoundException, SQLException;

    public abstract void closeConnection() throws SQLException;
    
    public abstract List<Map<String,Object>> findAllRecords(String tableName, int maxRecords) throws SQLException;
    
    public abstract int removeRecordByID(String tableName, String columnName, int recordID) throws SQLException;
}