<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String user = (String) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <link rel="stylesheet" href="css/style.css">
    
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
    
    <h3>Chức năng nhân viên</h3>
    <ul>
        <li><a href="NhanVienServlet?action=addNV">➕ Thêm nhân viên</a></li>
        <li><a href="NhanVienServlet?action=editNV">✏️ Cập nhật nhân viên</a></li>
        <li><a href="NhanVienServlet?action=deleteNV">🗑️ Xóa nhân viên</a></li>
        <li><a href="NhanVienServlet?action=deleteAll">🗑️ Xóa nhiều nhân viên</a></li>
        <li><a href="NhanVienServlet?action=list">📋 Danh sách nhân viên</a></li>
    </ul>
    
    <h3>Chức năng phòng ban</h3>
    <ul>
        <li><a href="PhongBanServlet?action=addPB">➕ Thêm phòng ban</a></li>
        <li><a href="PhongBanServlet?action=editPB">✏️ Cập nhật phòng ban</a></li>   
        <li><a href="PhongBanServlet?action=deletePB">🗑️ Xóa phòng ban</a></li>
        <li><a href="PhongBanServlet?action=deleteAll">🗑️ Xóa nhiều phòng ban</a></li>
        <li><a href="PhongBanServlet?action=list">📋 Danh sách phòng ban</a></li>
    </ul>
    
    
    
</body>
</html>
