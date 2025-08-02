<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Model.bean.Booking, Model.bean.Room, Model.bean.User" %>
<%
    User user = (User) session.getAttribute("user");
	Booking booking = (Booking) request.getAttribute("booking");
	List<Room> rooms = (List<Room>) request.getAttribute("rooms");
	boolean isEditing = booking != null;
	String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Form</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
    <h2><%= isEditing ? "Edit Booking" : "Add New Booking" %></h2>
    
    	<%       
            if (error != null) {
        %>
            <p style="color:red;"><%= error %></p>
        <% 
            } 
        %>
        
    <form action="" method="post">
        <input type="hidden" name="action" value="<%= isEditing ? "updateBooking" : "addBooking" %>">
        <input type="hidden" name="userId" value="<%= user.getId() %>">
        <% if (isEditing) { %>
            <input type="hidden" name="id" value="<%= booking.getId() %>">
        <% } %>

        <label>Date:</label>
        <input type="date" name="date" value="<%= isEditing ? booking.getDate().toString() : "" %>" required>

        <label>From:</label>
		<select name="from" required>
		    <% for (int i = 1; i <= 10; i++) { %>
		        <option value="<%= i %>" <%= isEditing && booking.getFrom() == i ? "selected" : "" %>>
		            <%= i %>
		        </option>
		    <% } %>
		</select>
		
		<label>To:</label>
		<select name="to" required>
		    <% for (int i = 1; i <= 10; i++) { %>
		        <option value="<%= i %>" <%= isEditing && booking.getTo() == i ? "selected" : "" %>>
		            <%= i %>
		        </option>
		    <% } %>
		</select>


        <label>Room:</label>
        <select name="roomId" required>
            <% if (rooms != null) {
                for (Room r : rooms) { %>
                    <option value="<%= r.getId() %>" <%= isEditing && booking.getRoomId() == r.getId() ? "selected" : "" %>>
                        <%= r.getRoomName() %>
                    </option>
            <% } } %>
        </select>

        <label>Purpose:</label>
        <input type="text" name="purpose" value="<%= isEditing ? booking.getPurpose() : "" %>" required>

        <button type="submit"><%= isEditing ? "Update Booking" : "Add Booking" %></button>
    </form>

    <nav>
        <a href="<%= request.getContextPath() %>/AuthedServlet?action=listBookings">Quay lại</a>
    </nav>
</div>
</body>
</html>
