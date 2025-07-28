<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List, Model.bean.NhanVien"%>
<html>
<head><title>Chọn nhân viên để cập nhật</title></head>
<link rel="stylesheet" href="css/style.css">

<body>
  <h2>Chọn nhân viên để cập nhật</h2>
  <table border="1" cellpadding="8">
    <tr>
      <th>ID</th><th>Tên NV</th><th>IDPB</th><th>Địa chỉ</th><th>Hành động</th>
    </tr>
    <%
      List<NhanVien> dsNV = (List<NhanVien>) request.getAttribute("dsNV");
      if (dsNV != null) {
        for (NhanVien nv : dsNV) {
    %>
    <tr>
      <td><%= nv.getIdNV() %></td>
      <td><%= nv.getHoTen() %></td>
      <td><%= nv.getIdPB() %></td>
      <td><%= nv.getDiaChi() %></td>
      <td>
        <a href="NhanVienServlet?action=editNV&id=<%= nv.getIdNV() %>">
          Cập nhật
        </a>
      </td>
    </tr>
    <%   }
      }
    %>
  </table>
  <p><a href="NhanVienServlet?action=list">← Quay lại danh sách nhân viên</a></p>
</body>
</html>
