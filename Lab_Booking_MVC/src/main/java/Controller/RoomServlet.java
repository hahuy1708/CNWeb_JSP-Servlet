//package Controller;
//
//import Model.bean.Room;
//import Model.bean.User;
//import Model.bo.RoomBO;
//import java.io.IOException;
//import java.util.List;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebServlet({"/admin/room/*", "/teacher/room/*"})
//public class RoomServlet extends HttpServlet {
//    private RoomBO roomBO;
//
//    @Override
//    public void init() {
//        roomBO = new RoomBO();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//    	String uri = request.getRequestURI();
//    	boolean isAdminPath = uri.contains("/admin/");
//
//        String action = request.getParameter("action");
//        if (action == null) action = "list";
//
//        try {
//            isAdminPath = uri.startsWith("/admin");
//            
//            switch (action) {
//                case "list":
//                    handleRoomList(request, response, isAdminPath);
//                    break;
//                    
////                case "addForm":
////                    if (!isAdminPath) {
////                        redirectUnauthorized(response);
////                        return;
////                    }
////                    request.getRequestDispatcher("/admin/room_form.jsp").forward(request, response);
////                    break;
////                    
////                case "editForm":
////                    if (!isAdminPath) {
////                        redirectUnauthorized(response);
////                        return;
////                    }
////                    String editId = request.getParameter("id");
////                    Room editRoom = roomBO.getRoomById(editId);
////                    request.setAttribute("room", editRoom);
////                    request.getRequestDispatcher("/admin/room_form.jsp").forward(request, response);
////                    break;
////                    
////                case "bookForm":
////                    if (isAdminPath) {
////                        redirectUnauthorized(response);
////                        return;
////                    }
////                    String bookId = request.getParameter("id");
////                    Room bookRoom = roomBO.getRoomById(bookId);
////                    request.setAttribute("room", bookRoom);
////                    request.getRequestDispatcher("/teacher/booking_form.jsp").forward(request, response);
////                    break;
////                    
////                case "toggleStatus":
////                    if (!isAdminPath) {
////                        redirectUnauthorized(response);
////                        return;
////                    }
////                    String toggleId = request.getParameter("id");
////                    roomBO.toggleRoomStatus(toggleId);
////                    redirectToRoleDashboard(request, response, isAdminPath);
////                    break;
////                    
//                default:
//                    handleRoomList(request, response, isAdminPath);
//            }
//        } catch (Exception e) {
//            throw new ServletException(e);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//    	String uri = request.getRequestURI();
//    	boolean isAdminPath = uri.contains("/admin/");
//        String action = request.getParameter("action");
//        
//        if (action == null) {
//            redirectToRoleDashboard(request, response, isAdminPath);
//            return;
//        }
//////
//////        try {
//////            switch (action) {
//////                case "add":
//////                    if (!isAdminPath) {
//////                        redirectUnauthorized(response);
//////                        return;
//////                    }
//////                    addRoom(request, response);
//////                    break;
//////                    
//////                case "update":
//////                    if (!isAdminPath) {
//////                        redirectUnauthorized(response);
//////                        return;
//////                    }
//////                    updateRoom(request, response);
//////                    break;
//////                    
//////                case "book":
//////                    if (isAdminPath) {
//////                        redirectUnauthorized(response);
//////                        return;
//////                    }
//////                    bookRoom(request, response);
//////                    break;
//////                    
//////                default:
//////                    redirectToRoleDashboard(request, response, isAdminPath);
//////            }
////        } catch (Exception e) {
////            throw new ServletException(e);
////        }
//    }
//
//    // ==================== HANDLER METHODS ====================
//    
//    private void handleRoomList(HttpServletRequest request, HttpServletResponse response, boolean isAdminPath)
//            throws ServletException, IOException {
//        List<Room> rooms = roomBO.getAllRoom();
//        request.setAttribute("rooms", rooms);
//
//        
//        if (isAdminPath) {
//            request.getRequestDispatcher("/admin/room/room_list.jsp").forward(request, response);
//        } else {
//            request.getRequestDispatcher("/teacher/room/room_list.jsp").forward(request, response);
//        }
//    }
////    
////    private void addRoom(HttpServletRequest request, HttpServletResponse response)
////            throws IOException, ServletException {
////        Room room = extractRoomFromRequest(request);
////        if (roomBO.addRoom(room)) {
////            response.sendRedirect("rooms?action=list");
////        } else {
////            request.setAttribute("error", "Failed to add room");
////            request.getRequestDispatcher("/admin/room_form.jsp").forward(request, response);
////        }
////    }
////    
////    private void updateRoom(HttpServletRequest request, HttpServletResponse response)
////            throws IOException, ServletException {
////        String id = request.getParameter("id");
////        Room room = extractRoomFromRequest(request);
////        room.setRoomId(id);
////        
////        if (roomBO.updateRoom(room)) {
////            response.sendRedirect("rooms?action=list");
////        } else {
////            request.setAttribute("error", "Failed to update room");
////            request.setAttribute("room", room);
////            request.getRequestDispatcher("/admin/room_form.jsp").forward(request, response);
////        }
////    }
////    
////    private void bookRoom(HttpServletRequest request, HttpServletResponse response)
////            throws IOException, ServletException {
////        HttpSession session = request.getSession();
////        User user = (User) session.getAttribute("user");
////        
////        String roomId = request.getParameter("roomId");
////        String bookingDate = request.getParameter("bookingDate");
////        String period = request.getParameter("period");
////        String purpose = request.getParameter("purpose");
////        
////        if (roomBO.bookRoom(roomId, user.getUserId(), bookingDate, period, purpose)) {
////            response.sendRedirect("../teacher/dashboard.jsp?message=Booking+success");
////        } else {
////            request.setAttribute("error", "Failed to book room");
////            request.setAttribute("room", roomBO.getRoomById(roomId));
////            request.getRequestDispatcher("/teacher/booking_form.jsp").forward(request, response);
////        }
////    }
////    
////    // ==================== HELPER METHODS ====================
////    
////    private Room extractRoomFromRequest(HttpServletRequest request) {
////        Room room = new Room();
////        room.setRoomName(request.getParameter("roomName"));
////        room.setCapacity(Integer.parseInt(request.getParameter("capacity")));
////        room.setDescription(request.getParameter("description"));
////        room.setEquipment(request.getParameter("equipment"));
////        return room;
////    }
////    
////    private void redirectUnauthorized(HttpServletResponse response) throws IOException {
////        response.sendRedirect("../../unauthorized.jsp");
////    }
//    
//    private void redirectToRoleDashboard(HttpServletRequest request, HttpServletResponse response, boolean isAdmin) 
//            throws IOException {
//        String contextPath = request.getContextPath();
//        if (isAdmin) {
//            response.sendRedirect(contextPath + "/admin/dashboard.jsp");
//        } else {
//            response.sendRedirect(contextPath + "/teacher/dashboard.jsp");
//        }
//    }
//}