<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.bean.PhongBan" %>

<html>
<head>
    <title>Cập nhật phòng ban</title>
</head>
<body>
    <h2>Cập nhật phòng ban</h2>
    <table border="1" cellpadding="10">
        <tr>
            <th>ID</th>
            <th>Tên phòng ban</th>
            <th>Mô tả</th>
            <th>Hành động</th>
        </tr>
        <%
            ArrayList<PhongBan> dsPB = (ArrayList<PhongBan>) request.getAttribute("dsPB");
            if (dsPB != null) {
                for (PhongBan pb : dsPB) {
        %>
        <tr>
            <td><%= pb.getIdPB() %></td>
            <td><%= pb.getTenPB() %></td>
            <td><%= pb.getMoTa() %></td>
            <td>
            	<a href = "PhongBanServlet?action=showFormEdit&id=<%= pb.getIdPB() %>">Cập nhật</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <div class="back-link">	
        <a href="<%= request.getContextPath() %>/home.jsp">← Quay lại trang chủ</a>
    </div>
</body>
</html>
