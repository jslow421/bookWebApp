/*
* This software was written by John Slowik
* Please don't do anything he wouldn't do
*
 */
package edu.wctc.jms.bookwebapp.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;


/**
 *
 * @author John Slowik <jslowik@my.wctc.edu>
 */
@SessionScoped
@Alternative
public class AuthorService implements AuthorDaoStrategy, Serializable{
    
    public ArrayList createAuthorArray(){
    Author authorGenerator = new Author();
    
    Author author1 = authorGenerator.createAuthor(1, "John Stevens", LocalDate.of(2015, Month.APRIL, 21));
    Author author2 = authorGenerator.createAuthor(2, "Jeff Bridges", LocalDate.of(2013, Month.JANUARY, 1));
    Author author3 = authorGenerator.createAuthor(3, "James Earl", LocalDate.of(2011, Month.SEPTEMBER, 30));
    
    ArrayList<Author> authorList = new ArrayList<>(Arrays.asList(author1, author2, author3));
    
    return authorList;
    }
    
    
    
    /**
     * Testing method -- not for use in web app.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        
        AuthorService as = new AuthorService();
        ArrayList<Author> authorList = as.createAuthorArray();
        
        for (int i = 0; i < authorList.size(); i++) {
            System.out.println(authorList.get(i));
            
        }
    }

    @Override
    public List<Author> getAuthorList() throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteAuthorByID(Object id) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DBStrategy getDb() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDb(DBStrategy db) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
