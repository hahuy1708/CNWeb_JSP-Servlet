<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.bean.Room, Model.bean.User" %>
<%
    User user = (User) session.getAttribute("user");
    boolean isAdmin = "admin".equalsIgnoreCase(user.getRole());
    List<Room> roomList = (List<Room>) request.getAttribute("rooms");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Room List</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2>All Rooms</h2>
        <table >
            <thead>
			    <tr>
			        <th>ID</th>
			        <th>Room Name</th>
			        <th>Computer Count</th>
			        <th>Description</th>
			        <th>Status</th>
			        <% if (isAdmin) { %>
			            <th>Edit</th>
			            <th>Delete</th>
			        <% } %>
			    </tr>
			</thead>
            <tbody>
			    <% if (roomList != null && !roomList.isEmpty()) {
			        for (Room room : roomList) { %>
			            <tr>
			                <td><%= room.getId() %></td>
			                <td><%= room.getRoomName() %></td>
			                <td><%= room.getComputerCount() %></td>
			                <td><%= room.getDescription() %></td>
			                <td><%= room.getStatus() %></td>
			                <% if (isAdmin) { %>
			                    <td><a href="AuthedServlet?action=updateRoom&id=<%= room.getId() %>">x</a></td>
			                    <td><a href="AuthedServlet?action=deleteRoom&id=<%= room.getId() %>">x</a></td>
			                <% } %>
			            </tr>
			    <%  }
			    } else { %>
			        <tr>
			            <td colspan="<%= isAdmin ? "7" : "5" %>" style="text-align:center;">No rooms found.</td>
			        </tr>
			    <% } %>
			</tbody>

        </table>

        <nav>
		    <a href="dashboard.jsp">Back to Dashboard</a>
		    <% if (isAdmin) { %>
		        <a href="AuthedServlet?action=addRoom">Add Room</a>
		    <% } %>
		</nav>

    </div>
</body>
</html>
