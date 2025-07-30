<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.bean.Room" %>

<%
    ArrayList<Room> rooms = (ArrayList<Room>) request.getAttribute("rooms");
%>




<table border="1">
    <tr>
        <th>ID</th>
        <th>Tên phòng</th>
        <th>Sức chứa</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
    </tr>
    <%
        if (rooms != null) {
            for (Room room : rooms) {
    %>
                <tr>
                    <td><%= room.getId() %></td>
                    <td><%= room.getRoomName() %></td>
                    <td><%= room.getComputerCount() %></td>
                    <td><%= room.getStatus() %></td>
                    <td>
                        <a href="/admin/rooms?action=editForm&id=<%= room.getId() %>">Sửa</a>
                        <a href="/admin/rooms?action=toggleStatus&id=<%= room.getId() %>">Chuyển trạng thái</a>
                    </td>
                </tr>
    <%
            }
        }
    %>
</table>