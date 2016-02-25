/*
* This software was written by John Slowik
* Please don't do anything he wouldn't do
*
 */
package edu.wctc.jms.bookwebapp.controller;

import edu.wctc.jms.bookwebapp.model.Author;
import edu.wctc.jms.bookwebapp.model.AuthorServices;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author John Slowik <jslowik@my.wctc.edu>
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    private static final String RESPONSE_URL = "/viewauthors.jsp";
    private static final String ACTION_PARAM = "action"; //example for later
    private static final String ADD_AUTHOR = "add";
    
    //db config init params from web.xml
    //remember, servlets are singletons, so these values are global and shared by everyone
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    
    @Inject
    private AuthorServices srv; //we dont' have an interface for this

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //use init parameters to config database connection
        configDbConnection();

        try {
            //AuthorServices srv = new AuthorServices(); redundant thanks to injection
            List<Author> authors = srv.getAuthorList();
            request.setAttribute("authorList", authors);
            RequestDispatcher view = request.getRequestDispatcher(RESPONSE_URL);
            view.forward(request, response);
        } catch (Exception e) {

        }
    }
    
    private void configDbConnection(){
        srv.getDao().initDao(driverClass, url, userName, password);
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Called after the constructor is called by the container.
     * This is the correct place to do one-time initialization.
     * 
     * @throws ServletException 
     */
    @Override
    public void init() throws ServletException{
        //get init params from web.xml
        driverClass = getServletContext().getInitParameter("db.driver.class");
        url = getServletContext().getInitParameter("db.url");
        userName = getServletContext().getInitParameter("db.username");
        password = getServletContext().getInitParameter("db.password");
    }
    
}