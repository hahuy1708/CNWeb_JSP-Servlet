package Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.bean.Room;
import Model.bean.User;
import Model.bo.RoomBO;

@WebServlet("/RoomServlet")
public class RoomServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RoomBO roomBO;

    public void init() {
        roomBO = new RoomBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                if ("ADMIN".equals(user.getRole())) {
                    listRoomsForAdmin(request, response);
                } else {
                    listAvailableRoomsForTeacher(request, response);
                }
                break;
//            case "add":
//                if ("ADMIN".equals(user.getRole())) {
//                    showAddForm(request, response);
//                } else {
//                    response.sendRedirect("unauthorized.jsp");
//                }
//                break;
//            case "edit":
//                if ("ADMIN".equals(user.getRole())) {
//                    showEditForm(request, response);
//                } else {
//                    response.sendRedirect("unauthorized.jsp");
//                }
//                break;
//            case "delete":
//                if ("ADMIN".equals(user.getRole())) {
//                    deleteRoom(request, response);
//                } else {
//                    response.sendRedirect("unauthorized.jsp");
//                }
//                break;
//            case "book":
//                if ("TEACHER".equals(user.getRole())) {
//                    bookRoom(request, response);
//                } else {
//                    response.sendRedirect("unauthorized.jsp");
//                }
//                break;
            
            default:
                response.sendRedirect("login.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null || !"ADMIN".equals(user.getRole())) {
            response.sendRedirect("unauthorized.jsp");
            return;
        }

        String action = request.getParameter("action");
        if ("insert".equals(action)) {
            insertRoom(request, response);
        } else if ("update".equals(action)) {
            updateRoom(request, response);
        }
    }

    private void listRoomsForAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Room> rooms = roomBO.getAllRoom();
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("admin/room_list.jsp").forward(request, response);
    }

    private void listAvailableRoomsForTeacher(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Room> availableRooms = roomBO.getAvailableRooms();
        request.setAttribute("availableRooms", availableRooms);
        request.getRequestDispatcher("teacher/room_list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("admin/room_form.jsp").forward(request, response);
    }

//    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Room room = roomBO.getRoomById(id);
//        request.setAttribute("room", room);
//        request.getRequestDispatcher("admin/room_form.jsp").forward(request, response);
//    }

    private void insertRoom(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String roomName = request.getParameter("roomName");
        int computerCount = Integer.parseInt(request.getParameter("computerCount"));
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        Room newRoom = new Room(0, roomName, computerCount, description, status);
        roomBO.insertRoom(newRoom);
        response.sendRedirect("RoomServlet?action=list");
    }

    private void updateRoom(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String roomName = request.getParameter("roomName");
        int computerCount = Integer.parseInt(request.getParameter("computerCount"));
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        Room room = new Room(id, roomName, computerCount, description, status);
        roomBO.updateRoom(room);
        response.sendRedirect("RoomServlet?action=list");
    }

    private void deleteRoom(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        roomBO.deleteRoom(id);
        response.sendRedirect("RoomServlet?action=list");
    }
//
//    private void bookRoom(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        int roomId = Integer.parseInt(request.getParameter("id"));
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        roomBO.bookRoom(roomId, user.getId());
//        response.sendRedirect("RoomServlet?action=list");
//    }
}