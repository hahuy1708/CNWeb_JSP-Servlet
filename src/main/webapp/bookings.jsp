<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*, Model.bean.Booking, Model.bean.Room, Model.bean.User" %>
<%
    User user = (User) session.getAttribute("user");
    boolean isTeacher = "teacher".equalsIgnoreCase(user.getRole());
    List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
    List<Room> rooms = (List<Room>) request.getAttribute("rooms");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking List</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2>All Bookings</h2>
        <table>
            <thead>
                <tr>
                	<th>Teacher ID</th>
                    <th>Teacher</th>
                    <th>Room</th>
                    <th>Booking Date</th>
                    <th>Time Slot</th>
                    <th>Purpose</th>
                    <% if (isTeacher) { %>
                        <th>Delete</th>
                        <th>Edit</th>
                    <% } %>
                </tr>
            </thead>
            <tbody>
                <% if (bookings != null && !bookings.isEmpty()) {
                    for (Booking booking : bookings) { %>
                        <tr>
                        	<td><%= booking.getUserId() %></td>
                            <td><%= booking.getTeacherName() %></td>
                            <td><%= booking.getRoomName() %></td>
                            <td><%= booking.getDate() %></td>
                            <td><%= booking.getFrom() + " - " + booking.getTo() %></td>
                            <td><%= booking.getPurpose() %></td>
                            <% if (isTeacher && user.getId() == booking.getUserId()) { %>
							    <td>
							        <a href="AuthedServlet?action=deleteBooking&id=<%= booking.getId() %>">x</a>
							    </td>
							    <td>
							        <a href="AuthedServlet?action=updateBooking&id=<%= booking.getId() %>">x</a>
							    </td>
							<% } else if (isTeacher) { %>
							    <td>-</td>
							    <td>-</td>
							<% } %>
                        </tr>
                <%  }
                } else { %>
                    <tr>
                        <td colspan="<%= isTeacher ? "9" : "8" %>" style="text-align:center;">No bookings found.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        
        <form method="get" style="margin-bottom: 20px;">
        	<input type="hidden" name="action" value="listBookings">
		    <label for="filterRoom">Filter by Room:</label>
		    <select name="roomId" id="filterRoom">
		        <option value="">All Rooms</option>
		        <% if (rooms != null) {
		            for (Room r : rooms) { 
		                String selected = request.getParameter("roomId") != null && request.getParameter("roomId").equals(String.valueOf(r.getId())) ? "selected" : "";
		        %>
		            <option value="<%= r.getId() %>" <%= selected %>><%= r.getRoomName() %></option>
		        <% }} %>
		    </select>
		
		    <label for="filterDate">Filter by Date:</label>
		    <input type="date" name="date" id="filterDate" value="<%= request.getParameter("date") != null ? request.getParameter("date") : "" %>">
		
		    <button type="submit">Filter</button>
		</form>

        <nav>
            <a href="dashboard.jsp">Back to Dashboard</a>
            <% if (isTeacher) { %>
                <a href="AuthedServlet?action=addBooking">Add Booking</a>
            <% } %>
        </nav>
    </div>
</body>
</html>
