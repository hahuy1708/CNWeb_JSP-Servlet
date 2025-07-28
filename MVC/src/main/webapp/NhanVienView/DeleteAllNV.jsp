<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, Model.bean.NhanVien" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Xóa nhiều Nhân Viên</title>
  <link rel="stylesheet" href="css/style.css">

  <script>
    function checkSubmit() {
      let checked = document.querySelectorAll('input[name="idNV"]:checked');
      if (checked.length === 0) {
        alert('Bạn chưa chọn nhân viên nào!');
        return false;
      }
      return confirm('Bạn có chắc muốn xóa ' + checked.length + ' nhân viên đã chọn?');
    }
  </script>
</head>
<body>
  <h2>Xóa nhiều Nhân Viên</h2>

  <% String msg = (String) request.getAttribute("msg");
     if (msg != null) { %>
    <p style="color: green; font-weight: bold;"><%= msg %></p>
  <% } %>

  <form method="post" action="NhanVienServlet" onsubmit="return checkSubmit();">
    <input type="hidden" name="action" value="deleteAll"/>

    <table border="1" cellpadding="8">
      <tr>
        <th>Chọn</th><th>ID</th><th>Họ Tên</th><th>ID PB</th><th>Địa Chỉ</th>
      </tr>
      <%
        List<NhanVien> dsNV = (List<NhanVien>) request.getAttribute("dsNV");
        if (dsNV != null) {
          for (NhanVien nv : dsNV) {
      %>
        <tr>
          <td><input type="checkbox" name="idNV" value="<%= nv.getIdNV() %>"/></td>
          <td><%= nv.getIdNV() %></td>
          <td><%= nv.getHoTen() %></td>
          <td><%= nv.getIdPB() %></td>
          <td><%= nv.getDiaChi() %></td>
        </tr>
      <%
          }
        }
      %>
    </table>
    <br/>
    <button type="submit">Xóa các nhân viên đã chọn</button>
  </form>

  <p><a href="home.jsp">← Quay lại trang chủ</a></p>
</body>
</html>
