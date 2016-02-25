<%-- 
    Document   : addauthor
    Created on : Feb 24, 2016, 6:32:40 PM
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
        <title>Add An Author</title>
    </head>
    <body>
        <div class="container">
            <div class="page-header">
                <h1>Add An Author</h1>
            </div>
            <form>
                <div class="form-group">
                    <label for="authorname">Author Name</label>
                    <input type="text" class="form-control" id="name">
                </div>
                <button type="submit" class="btn btn-default">Add Author</button>
            </form>
        </div>
        
        
        <script src ="https://code.jquery.com/jquery-2.2.0.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
