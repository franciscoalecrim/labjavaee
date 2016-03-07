<%-- 
    Document   : response
    Created on : Mar 6, 2016, 11:23:00 PM
    Author     : alecrim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="mybean" scope="session" class="org.mypackage.hello.NameHandler" />
	<jsp:setProperty name="mybean" property="name" />
	<h1> Hello <jsp:getProperty name="mybean" property="name" />!</h1>
    </body>
</html>
