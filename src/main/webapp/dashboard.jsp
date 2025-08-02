<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="Model.bean.User" %> 
<%@ page import="Model.bean.Room" %>

<%
    User user = (User) session.getAttribute("user");
%>
<html>
<head>
    <title>Dashboard</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="container">
        <h2>Dashboard</h2>

        <% if (user != null) { %>
            <p>Chào mừng, <%= user.getFullname() %> (username: <%= user.getUsername() %>)</p>
            <p>Vai trò: <%= user.getRole() %></p>
            <nav>
            <a href="<%= request.getContextPath() %>/UserServlet?action=logout">Đăng xuất</a>
            <a href="<%= request.getContextPath() %>/AuthedServlet?action=listRooms">Rooms</a>
            <a href="<%= request.getContextPath() %>/AuthedServlet?action=listBookings">Bookings</a>
            </nav>
        <% } else { %>
            <p>Bạn chưa đăng nhập. <a href="login.jsp">Đăng nhập</a></p>
        <% } %>
    </div>
</body>
</html>
