/*
 * This software was written by John Slowik
 * Please don't do anything he wouldn't do
 *
 * can delete author service and replace with this, other one should be "mock"
 * fix this later
 *
 */
package edu.wctc.jms.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author John Slowik <jslowik@my.wctc.edu>
 */
@SessionScoped
public class AuthorServices implements Serializable{
    @Inject
    private AuthorDaoStrategy dao; //inject the interface
    //private AuthorDaoStrategy dao = new AuthorDao();
    
    /**
     * Default constructor required for injectable objects
     * 
     */
    public AuthorServices(){
        
    }

    //getters and setters for injection
    //I don't think we actually need the DAO one here, just the service one
    public AuthorDaoStrategy getDao() {
        return dao;
    }

    public void setDao(AuthorDaoStrategy dao) {
        this.dao = dao;
    }
    
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        
         return dao.getAuthorList();
    }
    public int deleteAuthorByID(Object id) throws ClassNotFoundException, SQLException{
        return dao.deleteAuthorByID(id);
    }
    
    public int addAuthor(String authName) throws ClassNotFoundException, SQLException{
        return dao.addAuthor(authName);
    }
    
     public Author getAuthorById(String authorId) throws ClassNotFoundException, SQLException {
        return dao.getAuthorById(Integer.parseInt(authorId));
    }
    
    /**
     * Testing method
     * 
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorServices srv = new AuthorServices();
        List<Author> authors = srv.getAuthorList();
        
        
        
        System.out.println(authors);
    }
    
}
