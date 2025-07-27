<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, Model.bean.PhongBan" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Xóa nhiều Phòng Ban</title>
  <script>
    function checkSubmit() {
      let checked = document.querySelectorAll('input[name="idPB"]:checked');
      if (checked.length === 0) {
        alert('Bạn chưa chọn phòng ban nào!');
        return false;
      }
      return confirm('Bạn có chắc muốn xóa ' + checked.length + ' phòng ban đã chọn?');
    }
  </script>
</head>
<body>
  <h2>Xóa nhiều Phòng Ban</h2>

  <!-- Thông báo sau khi xóa -->
  <%
    String msg = (String) request.getAttribute("msg");
    if (msg != null) {
  %>
    <p style="color: green; font-weight: bold;"><%= msg %></p>
  <%
    }
  %>

  <form method="post" action="PhongBanServlet" onsubmit="return checkSubmit();">
    <!-- action chỉ định deleteMultiplePB -->
    <input type="hidden" name="action" value="deleteAll"/>

    <table border="1" cellpadding="8">
      <tr>
        <th>Chọn</th><th>ID</th><th>Tên PB</th><th>Mô tả</th>
      </tr>
      <%
        List<PhongBan> dsPB = (List<PhongBan>) request.getAttribute("dsPB");
        if (dsPB != null) {
          for (PhongBan pb : dsPB) {
      %>
      <tr>
        <td>
          <input type="checkbox" name="idPB" value="<%= pb.getIdPB() %>"/>
        </td>
        <td><%= pb.getIdPB() %></td>
        <td><%= pb.getTenPB() %></td>
        <td><%= pb.getMoTa() %></td>
      </tr>
      <%
          }
        }
      %>
    </table>
    <br/>
    <button type="submit">Xóa các phòng ban đã chọn</button>
  </form>

  <p><a href="home.jsp">← Quay lại trang chủ</a></p>
</body>
</html>
