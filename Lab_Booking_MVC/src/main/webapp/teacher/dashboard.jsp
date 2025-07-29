<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%@ page import="Model.bean.User" %> 

<%
    User user = (User) session.getAttribute("user");
%>

<html>
<head>
    <title>Teacher Dashboard</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    
</head>
<body>
    <h1>Đây là trang dashboard dành cho giáo viên.</h1>

    <%
        if(user != null){
    %>
        <p>Chào mừng, <%= user.getFullname() %> (username: <%= user.getUsername() %>)</p>
        <p><a href="<%= request.getContextPath() %>/UserServlet?action=logout">Đăng xuất</a></p>
    <%
        } else {
    %>
        <p>Bạn chưa đăng nhập. <a href="login.jsp">Đăng nhập</a></p>
    <%
        }
    %>

</body>
</html>
