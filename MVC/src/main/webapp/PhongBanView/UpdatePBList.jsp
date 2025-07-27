<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List, Model.bean.PhongBan"%>
<html>
<head><title>Chọn phòng ban để cập nhật</title></head>
<body>
  <h2>Chọn phòng ban để cập nhật</h2>
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
        <a href="PhongBanServlet?action=editPB&id=<%= pb.getIdPB() %>">
          Cập nhật
        </a>
      </td>
    </tr>
    <%   }
      }
    %>
  </table>
  <p><a href="PhongBanServlet?action=list">← Quay lại danh sách phòng ban</a></p>
</body>
</html>
