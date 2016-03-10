/*
 * This software was written by John Slowik
 * Please don't do anything he wouldn't do
 *
 */
package edu.wctc.jms.bookwebapp.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author John Slowik <jslowik@my.wctc.edu>
 */
public interface AuthorDaoStrategy {

    List<Author> getAuthorList() throws ClassNotFoundException, SQLException, Exception;
    int deleteAuthorByID(Object id) throws ClassNotFoundException, SQLException, Exception;
    public int addAuthor(String authName) throws ClassNotFoundException, SQLException, Exception;
    public int updateAuthor(Object id, String authorName) throws ClassNotFoundException, SQLException, Exception;
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
    Author getAuthorById(Integer authorId) throws ClassNotFoundException, SQLException, Exception;
    public abstract void initDao(DataSource ds) throws Exception;

    
}
