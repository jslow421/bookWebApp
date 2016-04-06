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

            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">JSlowik</a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a href="./">Home <span class="sr-only">(current)</span></a></li>
                            <li class="active"><a href="./AuthorController?action=list">Author List</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">CRUD <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="./addauthor.jsp">Add Author</a></li>
                                    <li><a href="./editauthor.jsp">Edit Author</a></li>

                                </ul>
                            </li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>


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
                                <!--<th>Date Added</th>-->
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
                                    <!--<td>
                                    <c:out value="${author.dateAdded}"/>
                                </td>-->
                                    <td class="crudButtons">
                                        <a class="btn btn-info btn-xs" href="?action=edit&id=<c:out value="${author.authorID}"/>" role="button"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp Edit</a>
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
