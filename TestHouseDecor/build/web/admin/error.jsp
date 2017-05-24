<%-- 
    Document   : error
    Created on : May 23, 2017, 12:58:20 PM
    Author     : Lê Đại An
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>${requestScope.ERROR}</h1>
    </body>
</html>
