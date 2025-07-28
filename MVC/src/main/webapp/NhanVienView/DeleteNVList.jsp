<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, Model.bean.NhanVien" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Quản lý Xóa Nhân Viên</title>
  <link rel="stylesheet" href="css/style.css">
  <script>
    function confirmDelete(idNV, tenNV) {
      if (confirm("Bạn có chắc chắn muốn xóa nhân viên \"" + tenNV + "\" (ID=" + idNV + ")?")) {
        window.location = "NhanVienServlet?action=deleteNV&id=" + idNV;
      }
    }
  </script>
</head>
<body>
  <h2>Danh sách Nhân Viên (Xóa)</h2>

  <!-- Hiển thị thông báo nếu có -->
  <%
    String msg = (String) request.getAttribute("msg");
    if (msg != null) {
  %>
    <p style="color: green; font-weight: bold;"><%= msg %></p>
  <%
    }
  %>

  <table border="1" cellpadding="8">
    <tr>
      <th>ID</th>
      <th>Họ tên</th>
      <th>ID Phòng ban</th>
      <th>Địa chỉ</th>
      <th>Hành động</th>
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
        <button type="button"
                onclick="confirmDelete('<%= nv.getIdNV() %>', '<%= nv.getHoTen().replace("'", "\\'") %>')">
          Xóa
        </button>
      </td>
    </tr>
    <%
        }
      }
    %>
  </table>

  <div class="back-link">
    <a href="<%= request.getContextPath() %>/NhanVienServlet?action=list">← Quay lại danh sách nhân viên</a>
  </div>

  <div class="back-link">
    <a href="<%= request.getContextPath() %>/home.jsp">← Quay lại trang chủ</a>
  </div>
</body>
</html>
