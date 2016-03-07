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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author John Slowik <jslowik@my.wctc.edu>
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    private static final String ACTION_PARAM = "action";
    private static final String ADD_AUTHOR = "add";
    private static final String LIST_AUTHORS = "list";
    private static final String EDIT_AUTHOR = "edit";
    private static final String REMOVE_AUTHOR = "remove";
    private static final String SAVE_AUTHOR = "save";
    private static final String LIST_PAGE = "/viewauthors.jsp";
    private static final String EDIT_PAGE = "/editauthor.jsp";
    private static String LANDING = null;
    private static final String NO_PARAM_ERR_MSG = "No request parameter identified";

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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter(ACTION_PARAM);

        //use init parameters to config database connection
        configDbConnection();

        try {

            switch (action) {
                case ADD_AUTHOR:
                    String authName = request.getParameter("name");
                    srv.addAuthor(authName);
                    this.updateList(request, srv);
                    LANDING = LIST_PAGE;
                    break;
                case LIST_AUTHORS:
                    List<Author> authors = srv.getAuthorList();
                    request.setAttribute("authorList", authors);
                    
                    LANDING = LIST_PAGE;
                    
                    break;
                case EDIT_AUTHOR:
                    String editAuthID = request.getParameter("id");
                    Author auth = srv.getAuthorById(editAuthID);
                    request.setAttribute("author", editAuthID);
                    
                    
                    LANDING = EDIT_PAGE;
                    
                    break;
                case SAVE_AUTHOR:
                    String updateAuthorName = request.getParameter("name");
                    String updateAuthorId = request.getParameter("id");
                    LANDING = LIST_PAGE;
                    break;
                case REMOVE_AUTHOR:
                    String[] authorIds = request.getParameterValues("id");
                    for (String id : authorIds) {
                        srv.deleteAuthorByID(id);
                    }
                    this.updateList(request, srv);
                    LANDING = LIST_PAGE;
                    break;
                default:
            {
                
                request.setAttribute("errMsg", NO_PARAM_ERR_MSG);
            }
                    LANDING = LIST_PAGE;
                    break;
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(LANDING);
            dispatcher.forward(request, response);

        } catch (ClassNotFoundException | SQLException | ServletException | IOException e) {

        }
    }

    private void configDbConnection() {
        srv.getDao().initDao(driverClass, url, userName, password);
    }
    
    private void updateList(HttpServletRequest request, AuthorServices srv) throws Exception {
        List<Author> authors = srv.getAuthorList();
        request.setAttribute("authorList", authors);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     * Called after the constructor is called by the container. This is the
     * correct place to do one-time initialization.
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        //get init params from web.xml
        driverClass = getServletContext().getInitParameter("db.driver.class");
        url = getServletContext().getInitParameter("db.url");
        userName = getServletContext().getInitParameter("db.username");
        password = getServletContext().getInitParameter("db.password");
    }

}
