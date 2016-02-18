/*
 * This software was written by John Slowik
 * Please don't do anything he wouldn't do
 *
 * can delete author service and replace with this, other one should be "mock"
 * fix this later
 *
 */
package edu.wctc.jms.bookwebapp.model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author John Slowik <jslowik@my.wctc.edu>
 */
public class AuthorServices {
    private AuthorDaoStrategy dao = new AuthorDao();
    
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        
         return dao.getAuthorList();
    }
    public int deleveAuthorByID(Object id) throws ClassNotFoundException, SQLException{
        return dao.deleteAuthorByID(id);
    }
    
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        AuthorServices srv = new AuthorServices();
        List<Author> authors = srv.getAuthorList();
        
        
        
        System.out.println(authors);
    }
    
}
