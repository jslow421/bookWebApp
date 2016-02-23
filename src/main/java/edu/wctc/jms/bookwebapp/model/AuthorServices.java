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
    private AuthorDaoStrategy dao;
    //private AuthorDaoStrategy dao = new AuthorDao();

    //getters and setters for injection
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
