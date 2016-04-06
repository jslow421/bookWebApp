<%-- 
    Document   : editproduct
    Created on : Mar 22, 2016, 10:35:40 PM
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
        <title>Edit Product</title>
        <!--Include css links -->
        <%@ include file="/WEB-INF/jspf/cssLinks.jspf" %>
    </head>
    <body>
        <div class="container">
            <!--Include bootstrap nav bar -->
            <%@ include file="/WEB-INF/jspf/nav.jspf" %>
            
            <div class="page-header">
                <h1>Edit Product</h1>
            </div>
           <form action="./ProductController?action=save" method ="post">
                <div class="form-group">
                    <input type="hidden" class="form-control" id="id" name="id" value="<c:out value="${product.productID}"/>"><br />
                    <label for="name">Product Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="<c:out value="${product.productName}"/>">
                    
                    <label for="name">Product Price</label>
                    <input type="text" class="form-control" id="price" name="price" value="<c:out value="${product.price}"/>">
                    
                    <label for="name">Product Stock</label>
                    <input type="text" class="form-control" id="stock" name="stock" value="<c:out value="${product.stock}"/>">
                    
                    <label for="name">Product Image URL</label>
                    <input type="text" class="form-control" id="imgURL" name="imgURL" value="<c:out value="${product.imgURL}"/>">
                </div>
                
                <button type="submit" class="btn btn-warning">Save Product</button>
            </form>
            
            
            
            
            
        </div>
        
        <!--Include script links -->
        <%@include file="/WEB-INF/jspf/ScriptLinks.jspf" %>
    </body>
</html>
