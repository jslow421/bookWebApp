/*
* This software was written by John Slowik
* Please don't do anything he wouldn't do
*
 */
package edu.wctc.jms.bookwebapp.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author John Slowik <jslowik@my.wctc.edu>
 */
public class Author {

    private int authorID;
    private String authorName;
    private LocalDate dateAdded;

    public Author(int authorID, String authorName, LocalDate dateAdded) {
        this.authorID = authorID;
        this.authorName = authorName;
        this.dateAdded = dateAdded;
    }

    /**
     * Constructor accepting authorID as a parameter
     *
     * @param authorID
     */
    public Author(int authorID) {
        this.authorID = authorID;
    }

    /**
     * Empty constructor
     */
    public Author() {

    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.authorName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (!Objects.equals(this.authorName, other.authorName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Author{" + "authorID=" + authorID + ", authorName=" + authorName + ", dateAdded=" + dateAdded + '}';
    }
    /**
     * Create a new author object
     * 
     * @param id
     * @param name
     * @param date
     * @return 
     */
    public Author createAuthor(int id, String name, LocalDate date){
        
        Author author = new Author(id);
        author.setAuthorName(name);
        author.setDateAdded(date);
        
        return author;
    }

    void setDateAdded(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
