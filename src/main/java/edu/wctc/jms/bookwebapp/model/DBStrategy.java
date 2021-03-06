/*
 * This software was written by John Slowik
 * Please don't do anything he wouldn't do
 *
 */
package edu.wctc.jms.bookwebapp.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author John Slowik <jslowik@my.wctc.edu>
 */
public interface DBStrategy {

    public abstract void openConnection(String driverClass, String url,
            String userName, String password)
            throws ClassNotFoundException, SQLException;

    public abstract void closeConnection() throws SQLException;

    public abstract List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) throws SQLException;

    public abstract int deleteRecordByID(String tableName, String columnName, Object recordID) throws SQLException;

    public int updateRecords(String tableName, List<String> colNames, List<Object> colValues,
            String pkColName, Object value) throws SQLException, Exception;

    public abstract int insertRecord(String tableName, String authorName) throws SQLException;

    public Map<String, Object> findById(String tableName, String primaryKeyFieldName,
            int primaryKeyValue) throws SQLException;

    public void openConnection(DataSource ds) throws Exception;
}
