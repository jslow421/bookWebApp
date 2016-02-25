/*
 * This software was written by John Slowik
 * Please don't do anything he wouldn't do
 *
 */
package edu.wctc.jms.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author John Slowik <jslowik@my.wctc.edu>
 */
@Dependent
public class AuthorDao implements AuthorDaoStrategy, Serializable {
    //need a method using customer terminology that performs the task we want
    //responsible for opening/closing connection
    //driver class name, url, username, and password have to come from object 
    //  outside strategy object

    @Inject
    private DBStrategy db;

    /**
     * Default constructor - necessary because it is being injected
     *
     */
    public AuthorDao() {
    }

    @Override
    public void initDao(String driver, String url, String userName, String password) {
        setDriver(driver);
        setUrl(url);
        setUserName(userName);
        setPassword(password);
    }

    private String driver;
    private String url;
    private String userName;
    private String password;
    //private DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        db.openConnection(driver, url, userName, password);

        List<Map<String, Object>> rawData = db.findAllRecords("author", 0);

        List<Author> authors = new ArrayList<>(); // create arrayList of authors

        for (Map rec : rawData) {
            Author author = new Author(); // using empty constructor to demo conversions
            int id = new Integer(rec.get("author_id").toString()); //one way to go
            //we know this is a PK, so it won't be null, everything after = could go into
            //constructor, and remove a line of code

            author.setAuthorID(id);
            String name = rec.get("author_name") == null ? "" : rec.get("author_name").toString();
            //must test for null!!!
            author.setAuthorName(name);

            //Date date = rec.get("date_added") == null ? null : (Date)rec.get("Date_added");
            //LocalDate date = rec.get("date_added"); == null ? null : rec.get("date_added");
            //author.setDateAdded(date);
            authors.add(author);

        }

        db.closeConnection();
        return authors;
    }

    //if you want to display records modified you should return an int - even if you don't use it
    /**
     * Delete an author record by ID
     *
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public int deleteAuthorByID(Object id) throws ClassNotFoundException, SQLException {
        db.openConnection(driver, url, userName, password);

        int result = db.deleteRecordByID("author", "author_id", id);

        db.closeConnection();

        return result;
    }

    //getters and setters for injected db property
    @Override
    public DBStrategy getDb() {
        return db;
    }

    @Override
    public void setDb(DBStrategy db) {
        this.db = db;
    }

    @Override
    public String getDriver() {
        return driver;
    }

    @Override
    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorDaoStrategy dao = new AuthorDao();
        List<Author> authors = dao.getAuthorList();
        System.out.println(authors);

    }

}
