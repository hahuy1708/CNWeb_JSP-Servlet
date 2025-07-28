<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<html>
<head><title>Cập nhật Nhân Viên</title></head>
<link rel="stylesheet" href="css/style.css">

<body>
  <h2>Cập nhật Nhân Viên</h2>
  <form action="NhanVienServlet?action=update" method="post">
    <label>Mã nhân viên:</label>
    <input type="text" name="idNV" value="${nhanVien.idNV}" readonly /><br/><br/>

    <label>Tên nhân viên:</label>
    <input type="text" name="hoTen" value="${nhanVien.hoTen}" required/><br/><br/>

    <label>Mã phòng ban:</label>
    <input type="text" name="idPB" value="${nhanVien.idPB}" required/><br/><br/>
	
	
    <label>Địa chỉ:</label>
    <input type="text" name="diaChi" value="${nhanVien.diaChi}" required/><br/><br/>
	
    <input type="submit" value="Cập nhật"/>
    <a href="NhanVienServlet?action=editNV">Quay lại</a>
  </form>
</body>
</html>
