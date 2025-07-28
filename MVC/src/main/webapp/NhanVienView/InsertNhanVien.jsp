<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Nhân Viên</title>
    <link rel="stylesheet" href="css/style.css">
    
</head>
<body>

    <h2>Thêm Nhân Viên Mới</h2>

    <form method="post" action="<%= request.getContextPath() %>/NhanVienServlet?action=insert">
        <label for="idNV">Mã Nhân Viên:</label>
        <input type="text" name="idNV" id="idNV" required>

        <label for="hoTen">Tên Nhân Viên:</label>
        <input type="text" name="hoTen" id="hoTen" required>

       	<select name="idPB" id="idPB" required>
    <option value="">-- Chọn Phòng Ban --</option>
    <%
        ArrayList<Model.bean.PhongBan> dsPB = (ArrayList<Model.bean.PhongBan>) request.getAttribute("dsPB");
        if (dsPB != null) {
            for (Model.bean.PhongBan pb : dsPB) {
    %>
        <option value="<%= pb.getIdPB() %>"><%= pb.getIdPB() %> - <%= pb.getTenPB() %></option>
    <%
            }
        }
    %>
</select>
       	
       
        <label for="diaChi">Địa chỉ:</label>
        <input type="text" name="diaChi" id="diaChi">
		
        <input type="submit" value="Thêm Nhân Viên">
    </form>

    <div class="back-link">
        <a href="<%= request.getContextPath() %>/NhanVienServlet?action=list">← Quay lại danh sách</a>
    </div>
    
    <div class="back-link">
        <a href="<%= request.getContextPath() %>/home.jsp">← Quay lại trang chủ</a>
    </div>

</body>
</html>
