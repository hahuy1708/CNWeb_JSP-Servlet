<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
    <h1>Chào mừng Admin!</h1>
    <p>Đây là trang dashboard dành cho quản trị viên.</p>
    <p><a href="<%= request.getContextPath() %>/LogoutServlet">Đăng xuất</a></p>
</body>
</html>
