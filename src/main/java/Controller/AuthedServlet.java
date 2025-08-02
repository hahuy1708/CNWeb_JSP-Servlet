package Controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Model.bean.*;
import Model.bo.*;

@WebServlet("/AuthedServlet")
public class AuthedServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RoomBO roomBO;
    private BookingBO bookingBO;

    public void init() {
        roomBO = new RoomBO();
        bookingBO = new BookingBO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "dashboard";

        switch (action) {
            case "dashboard":
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
                break;
            case "listRooms":
                request.setAttribute("rooms", roomBO.getAll());
                request.getRequestDispatcher("rooms.jsp").forward(request, response);
                break;
            case "updateRoom":
                int idToEdit = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("room", roomBO.getById(idToEdit));
                request.getRequestDispatcher("room-form.jsp").forward(request, response);
                break;
            case "deleteRoom":
                int idToDelete = Integer.parseInt(request.getParameter("id"));
                roomBO.deleteRoom(idToDelete);
                response.sendRedirect("AuthedServlet?action=listRooms");
                break;
            case "addRoom":
                request.setAttribute("room", null);
                request.getRequestDispatcher("room-form.jsp").forward(request, response);
                break;
            case "listBookings":
            	handleListBookings(request, response);
                break;
            case "addBooking":
                request.setAttribute("rooms", roomBO.getAll());
                request.getRequestDispatcher("booking-form.jsp").forward(request, response);
                break;
            case "updateBooking":
            	idToEdit = Integer.parseInt(request.getParameter("id"));
            	request.setAttribute("booking", bookingBO.getById(idToEdit));
            	System.out.println("Booking found: " + bookingBO.getById(idToEdit).getUserId());
                request.setAttribute("rooms", roomBO.getAll());
                request.getRequestDispatcher("booking-form.jsp").forward(request, response);
                break;
            case "deleteBooking":
                idToDelete = Integer.parseInt(request.getParameter("id"));
                bookingBO.delete(idToDelete);
                response.sendRedirect("AuthedServlet?action=listBookings");
                break;
            default:
                response.sendRedirect("AuthedServlet?action=dashboard");
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        switch (action) {
            case "addRoom":
                handleAddRoom(request, response);
                break;
            case "updateRoom":
                handleUpdateRoom(request, response);
                break;
            case "deleteBooking":
                int bookingId = Integer.parseInt(request.getParameter("id"));
                bookingBO.delete(bookingId);
                response.sendRedirect("bookings.jsp");
                break;
            case "addBooking":
                handleAddBooking(request, response);
                break;
            case "updateBooking":
            	handleEditBooking(request, response);
                break;
            default:
                response.sendRedirect("AuthedServlet?action=dashboard");
                break;
        }
    }

    private void handleAddRoom(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    	
    	if (!hasRole(request, "admin")) {
            return;
        }
    	
        Room room = new Room();
        room.setRoomName(request.getParameter("roomName"));
        room.setComputerCount(Integer.parseInt(request.getParameter("computerCount")));
        room.setDescription(request.getParameter("description"));
        room.setStatus("AVAILABLE");

        try {
            boolean success = roomBO.addRoom(room);
            if (!success) {
                forwardWithError(request, response, "Không thể thêm phòng. Vui lòng thử lại.", "room-form.jsp");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            forwardWithError(request, response, "Lỗi hệ thống khi thêm phòng.", "room-form.jsp");
            return;
        }

        response.sendRedirect("AuthedServlet?action=listRooms");
    }

    private void handleUpdateRoom(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    	
    	if (!hasRole(request, "admin")) {
            return;
        }
    	
        Room room = new Room();
        room.setId(Integer.parseInt(request.getParameter("id")));
        room.setRoomName(request.getParameter("roomName"));
        room.setComputerCount(Integer.parseInt(request.getParameter("computerCount")));
        room.setDescription(request.getParameter("description"));
        room.setStatus(request.getParameter("status"));

        boolean success = roomBO.updateRoom(room);
        if (!success) {
            forwardWithError(request, response, "Không thể cập nhật phòng.", "room-form.jsp");
            return;
        }

        response.sendRedirect("AuthedServlet?action=listRooms");
    }
    
    private void handleListBookings(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomIdParam = request.getParameter("roomId");
        String dateParam = request.getParameter("date");

        Integer roomId = (roomIdParam != null && !roomIdParam.isEmpty()) ? Integer.parseInt(roomIdParam) : null;
        Date date = (dateParam != null && !dateParam.isEmpty()) ? Date.valueOf(dateParam) : null;

        List<Booking> bookings;
        if (roomId != null || date != null) {
            bookings = bookingBO.filter(roomId, date);
        } else {
            bookings = bookingBO.getAll();
        }

        request.setAttribute("rooms", roomBO.getAll());
        request.setAttribute("bookings", bookings);
        request.getRequestDispatcher("bookings.jsp").forward(request, response);
    }

    private void handleAddBooking(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    	
    	if (!hasRole(request, "teacher")) {
            return;
        }
    	
        Booking booking = new Booking();

        booking.setDate(Date.valueOf(request.getParameter("date")));
        booking.setFrom(Integer.parseInt(request.getParameter("from")));
        booking.setTo(Integer.parseInt(request.getParameter("to")));
        booking.setRoomId(Integer.parseInt(request.getParameter("roomId")));
        booking.setPurpose(request.getParameter("purpose"));
        booking.setUserId(Integer.parseInt(request.getParameter("userId")));

        boolean success = bookingBO.insert(booking);
        if (!success) {
            request.setAttribute("booking", booking);
            request.setAttribute("rooms", roomBO.getAll());
            forwardWithError(request, response, "Lịch đặt phòng bị trùng hoặc có lỗi. Vui lòng thử lại.", "booking-form.jsp");
            return;
        }

        response.sendRedirect("AuthedServlet?action=listBookings");
    }
    
    private void handleEditBooking(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    	
    	if (!hasRole(request, "teacher")) {
            return;
        }
    	
        Booking booking = new Booking();

        booking.setId(Integer.parseInt(request.getParameter("id")));
        booking.setDate(Date.valueOf(request.getParameter("date")));
        booking.setFrom(Integer.parseInt(request.getParameter("from")));
        booking.setTo(Integer.parseInt(request.getParameter("to")));
        booking.setRoomId(Integer.parseInt(request.getParameter("roomId")));
        booking.setPurpose(request.getParameter("purpose"));
        booking.setUserId(Integer.parseInt(request.getParameter("userId")));

        boolean success = bookingBO.update(booking);
        if (!success) {
            request.setAttribute("booking", booking);
            request.setAttribute("rooms", roomBO.getAll());
            forwardWithError(request, response, "Không thể cập nhật lịch đặt (có thể bị trùng).", "booking-form.jsp");
            return;
        }

        response.sendRedirect("AuthedServlet?action=listBookings");
    }

    private void forwardWithError(HttpServletRequest request, HttpServletResponse response, String message, String destination) 
            throws ServletException, IOException {
        request.setAttribute("error", message);
        request.getRequestDispatcher(destination).forward(request, response);
    }
    
    private boolean hasRole(HttpServletRequest request, String role) {
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        return user != null && role.equalsIgnoreCase(user.getRole());
    }
}
