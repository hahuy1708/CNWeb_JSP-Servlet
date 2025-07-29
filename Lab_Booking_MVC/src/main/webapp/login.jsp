<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Đăng nhập hệ thống</title>
</head>
<body>
    <h2>Đăng nhập</h2>
    <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
        <div>
            <label>Tên đăng nhập:</label>
            <input type="text" name="username" required>
        </div>
        <div>
            <label>Mật khẩu:</label>
            <input type="password" name="password" required>
        </div>
        <button type="submit">Đăng nhập</button>
        <button type="reset">Reset </button>
        
    </form>
</body>
</html>