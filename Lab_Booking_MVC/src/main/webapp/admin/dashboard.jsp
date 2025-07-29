<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    
</head>
<body>
    <h1>Chào mừng Admin!</h1>
    <p>Đây là trang dashboard dành cho quản trị viên.</p>
    <p><a href="<%= request.getContextPath() %>/UserServlet?action=logout">Đăng xuất</a></p>
</body>
</html>
