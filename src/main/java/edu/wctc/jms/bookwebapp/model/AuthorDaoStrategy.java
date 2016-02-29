/*
 * This software was written by John Slowik
 * Please don't do anything he wouldn't do
 *
 */
package edu.wctc.jms.bookwebapp.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author John Slowik <jslowik@my.wctc.edu>
 */
public interface AuthorDaoStrategy {

    List<Author> getAuthorList() throws ClassNotFoundException, SQLException;
    int deleteAuthorByID(Object id) throws ClassNotFoundException, SQLException;
    public int addAuthor(String authName) throws ClassNotFoundException, SQLException;
    public int updateAuthorByID(List<String> colNames, List<Object> colValues, int id) throws ClassNotFoundException, SQLException;
    public DBStrategy getDb();
    public void setDb(DBStrategy db);
    public void initDao(String driver, String url, String userName, String password);
    public String getDriver();
    public void setDriver(String driver);
    public String getUrl();
    public void setUrl(String url);
    public String getUserName();
    public void setUserName(String userName);
    public String getPassword();
    public void setPassword(String password);
    Author getAuthorById(Integer authorId) throws ClassNotFoundException, SQLException;

    
}
