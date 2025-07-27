<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<html>
<head><title>Cập nhật Phòng Ban</title></head>
<body>
  <h2>Cập nhật Phòng Ban</h2>
  <form action="PhongBanServlet?action=update" method="post">
    <label>Mã phòng ban:</label>
    <input type="text" name="idPB" value="${phongBan.idPB}" readonly /><br/><br/>

    <label>Tên phòng ban:</label>
    <input type="text" name="tenPB" value="${phongBan.tenPB}" required/><br/><br/>

    <label>Mô tả:</label><br/>
    <textarea name="moTa" rows="4" cols="50">${phongBan.moTa}</textarea><br/><br/>

    <input type="submit" value="Cập nhật"/>
    <a href="PhongBanServlet?action=editPB">Quay lại</a>
  </form>
</body>
</html>
