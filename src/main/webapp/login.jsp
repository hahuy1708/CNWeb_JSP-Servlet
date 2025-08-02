<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập hệ thống</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="container">
        <h2>Đăng nhập</h2>

        <% 
            String error = (String) request.getAttribute("error"); 
            if (error != null) {
        %>
            <p style="color:red;"><%= error %></p>
        <% 
            } 
        %>

        <form action="${pageContext.request.contextPath}/UserServlet?action=login" method="post">
            <label for="username">Tên đăng nhập:</label>
            <input type="text" name="username" id="username" required>

            <label for="password">Mật khẩu:</label>
            <input type="password" name="password" id="password" required>

            <button type="submit">Đăng nhập</button>
            <button type="reset">Reset</button>

            <p>
                Chưa có tài khoản? 
                <a href="UserServlet?action=register">Đăng ký</a>
            </p>
        </form>
    </div>
</body>
</html>
