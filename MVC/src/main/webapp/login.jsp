<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<form action="CheckLoginServlet" method = "post">
	Username: <input type = "text" name = "username">
	Password: <input type = "password" name = "password">
	<br>
	<br>
	<input type = "submit" value = "Login">
	<input type = "reset" value = "Reset">
	</form>

</body>
</html>