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

    
}
