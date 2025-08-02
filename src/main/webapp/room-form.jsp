<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.bean.Room" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Room Form</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2><%= request.getAttribute("room") != null ? "Edit Room" : "Add New Room" %></h2>
        <form action="AuthedServlet" method="post">
            <input type="hidden" name="action" value="<%= request.getAttribute("room") != null ? "updateRoom" : "addRoom" %>">
            <% Room room = (Room) request.getAttribute("room"); %>
            <% if (room != null) { %>
                <input type="hidden" name="id" value="<%= room.getId() %>">
            <% } %>

            <label>Room Name:</label>
            <input type="text" name="roomName" value="<%= room != null ? room.getRoomName() : "" %>" required>

            <label>Computer Count:</label>
            <input type="number" name="computerCount" value="<%= room != null ? room.getComputerCount() : "" %>" required>

            <label>Description:</label>
            <input type="text" name="description" required value="<%= room != null ? room.getDescription() : "" %>">

            <label>Status:</label>
            <select name="status" required>
                <option value="active" ${room.status == 'active' ? 'selected' : ''}>Active</option>
				<option value="inactive" ${room.status == 'inactive' ? 'selected' : ''}>Inactive</option>
            </select>

            <button type="submit"><%= room != null ? "Update Room" : "Add Room" %></button>
        </form>
        
        <nav>
        	<a href="<%= request.getContextPath() %>/AuthedServlet?action=listRooms">Quay lai</a>
        </nav>
    </div>
</body>
</html>
