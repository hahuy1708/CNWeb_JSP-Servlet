<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.bean.PhongBan" %>

<html>
<head>
    <title>Danh sách phòng ban</title>
</head>
<body>
    <h2>Danh sách phòng ban</h2>
    <table border="1" cellpadding="10">
        <tr>
            <th>ID</th>
            <th>Tên phòng ban</th>
            <th>Mô tả</th>
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
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
