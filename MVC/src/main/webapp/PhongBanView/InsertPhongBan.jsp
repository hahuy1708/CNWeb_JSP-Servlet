<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Phòng Ban</title>
<link rel="stylesheet" href="css/style.css">

<body>

    <h2>Thêm Phòng Ban Mới</h2>

    <form method="post" action="<%= request.getContextPath() %>/PhongBanServlet?action=insert">
        <label for="idPB">Mã Phòng Ban:</label>
        <input type="text" name="idPB" id="idPB" required>

        <label for="tenPB">Tên Phòng Ban:</label>
        <input type="text" name="tenPB" id="tenPB" required>

        <label for="moTa">Mô Tả:</label>
        <input type="text" name="moTa" id="moTa">

        <input type="submit" value="Thêm Phòng Ban">
    </form>

    <div class="back-link">
        <a href="<%= request.getContextPath() %>/PhongBanServlet?action=list">← Quay lại danh sách</a>
    </div>
    
    <div class="back-link">
        <a href="<%= request.getContextPath() %>/home.jsp">← Quay lại trang chủ</a>
    </div>

</body>
</html>
