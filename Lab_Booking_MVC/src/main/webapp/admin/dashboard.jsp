<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.bean.Room" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    
</head>
<body>
    <h1>Chào mừng Admin!</h1>
    <p>Đây là trang dashboard dành cho quản trị viên.</p>
     <a href="<%= request.getContextPath() %>/RoomServlet?action=list">Phòng Lab</a>
    <p><a href="<%= request.getContextPath() %>/UserServlet?action=logout">Đăng xuất</a></p>
</body>
</html>
