<%-- 
    Document   : viewauthors
    Created on : Feb 7, 2016, 10:08:50 PM
    Author     : John Slowik <jslowik@my.wctc.edu>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/bootswatchcustom.css">
        <title>View All Authors</title>
    </head>
    <body>
        <div class="container">
            <div class="page-header">
                <h1>Authors <small>Listing all</small></h1>
            </div>
            <p><a class="btn btn-primary" href="./addauthor.jsp" role="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp Add Author</a></p>

            <div  class="col col-lg-12">
                <div id="answer">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Author ID</th>
                                <th>Author</th>
                                <th>Date Added</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="author" items="${authorList}">
                                <tr>
                                    <td>
                                        <c:out value="${author.authorID}" />
                                    </td>
                                    <td id="<c:out value="${author.authorName}"/>" class="authorname">
                                        <c:out value="${author.authorName}"/>
                                    </td>
                                    <td>
                                        <c:out value="${author.dateAdded}"/>
                                    </td>
                                    <td class="crudButtons">
                                        <a class="btn btn-info btn-xs" href="?action=edit" role="button"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp Edit</a>
                                        <a class="btn btn-danger btn-xs" href="?action=remove&id=<c:out value="${author.authorID}"/>" role="button"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <p>Records found: ${fn:length(authorList)}</p>
                </div>
            </div>
                
        </div>
        <script src ="https://code.jquery.com/jquery-2.2.0.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="authors.js"></script>
    </body>
</html>
