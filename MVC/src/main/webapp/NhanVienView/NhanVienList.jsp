<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.bean.NhanVien" %>

<html>
<head>
    <title>Danh sách nhân viên</title>
    <link rel="stylesheet" href="css/style.css">
    
</head>
<body>
    <h2>Danh sách nhân viên</h2>
    <table border="1" cellpadding="10">
        <tr>
            <th>ID</th>
            <th>Tên nhân viên</th>
            <th>Địa chỉ</th>
            <th>ID Phòng ban</th>
        </tr>
        <%
            ArrayList<NhanVien> dsNV = (ArrayList<NhanVien>) request.getAttribute("dsNV");
            if (dsNV != null) {
                for (NhanVien nv : dsNV) {
        %>
        <tr>
            <td><%= nv.getIdNV() %></td>
            <td><%= nv.getHoTen() %></td>
            <td><%= nv.getDiaChi() %></td>
            <td><%= nv.getIdPB() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <div class="back-link">
        <a href="<%= request.getContextPath() %>/home.jsp">← Quay lại trang chủ</a>
    </div>
</body>
</html>
