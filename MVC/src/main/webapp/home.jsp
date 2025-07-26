<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String user = (String) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
</head>
<body>
    <h2>Trang Home</h2>
    <%
        if (user != null) {
    %>
            <p>Chào mừng, <strong><%= user %></strong>!</p>
    <%
        } else {
    %>
            <p>Bạn chưa đăng nhập. Vui lòng <a href="login.jsp">đăng nhập</a>.</p>
    <%
        }
    %>
    
</body>
</html>
