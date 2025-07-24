<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login </title>
</head>
<body>
<%
String temp = "temp";
session.setAttribute("temp",temp);
%>
<form name = form1 action="checklogin.jsp" method = "post" >
<input type = "text" name = "username" />
<input type = "password" name = "password" />
<input type = "submit" value = "Login" />
<input type = "reset" value = "Reset" />
</form>

</body>
</html>