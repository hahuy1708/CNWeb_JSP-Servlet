<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký tài khoản</title>
<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
    <div class="container">
    	<h2>Đăng ký tài khoản giáo viên</h2>
	    <form action="UserServlet?action=register" method="post">
	        <input type="hidden" name="action" value="register" />
	        Username: <input type="text" name="username" required />
	        Fullname: <input type="text" name="fullname" required />
	        Password: <input type="password" name="password" required />
	        <button type="submit">Đăng ký</button>
	    </form>
	    <p><a href="login.jsp">Quay lại đăng nhập</a></p>
    </div>
</body>
</html>

