<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, Model.bean.PhongBan" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Quản lý Xóa Phòng Ban</title>
  <link rel="stylesheet" href="css/style.css">
  
  <script>
    function confirmDelete(idPB, tenPB) {
      if (confirm("Bạn có chắc chắn muốn xóa phòng ban \"" + tenPB + "\" (ID=" + idPB + ")?")) {
        window.location = "PhongBanServlet?action=deletePB&id=" + idPB;
      }
    }
  </script>
</head>
<body>
  <h2>Danh sách Phòng Ban (Xóa)</h2>

  <!-- Thông báo nếu có -->
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
      <th>ID</th><th>Tên PB</th><th>Mô tả</th><th>Hành động</th>
    </tr>
    <%
      List<PhongBan> dsPB = (List<PhongBan>) request.getAttribute("dsPB");
      if (dsPB != null) {
        for (PhongBan pb : dsPB) {
    %>
    <tr>
      <td><%= pb.getIdPB() %></td>
      <td><%= pb.getTenPB() %></td>
      <td><%= pb.getMoTa() %></td>
      <td>
        <button type="button"
                onclick="confirmDelete('<%= pb.getIdPB() %>', '<%= pb.getTenPB().replace("'", "\\'") %>')">
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
        <a href="<%= request.getContextPath() %>/PhongBanServlet?action=list">← Quay lại danh sách</a>
    </div>
    
    <div class="back-link">
        <a href="<%= request.getContextPath() %>/home.jsp">← Quay lại trang chủ</a>
    </div>
	  
</body>
</html>
