package Controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Model.bean.User;
import Model.bo.UserBO;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserBO userBO;

    @Override
    public void init() {
        userBO = new UserBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "login";

        switch (action) {
            case "register":
                request.getRequestDispatcher("register.jsp").forward(request, response);
                break;
            case "logout":
                handleLogout(request, response);
                break;
            default:
                response.sendRedirect("login.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) action = "login";

        switch (action) {
            case "register":
                handleRegister(request, response);
                break;
            case "logout":
                handleLogout(request, response);
                break;
            case "login":
            default:
                handleLogin(request, response);
                break;
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFullname(fullname);

        try {
            boolean success = userBO.registerUser(newUser);
            if (success) {
                response.sendRedirect("login.jsp?register=success");
            } else {
                forwardWithError(request, response, "Đăng ký thất bại", "register.jsp");
            }
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userBO.checkAuth(username, password);

            if (user == null) {
                forwardWithError(request, response, "Tên đăng nhập hoặc mật khẩu không đúng", "login.jsp");
                return;
            }

            if ("INACTIVE".equalsIgnoreCase(user.getStatus())) {
                forwardWithError(request, response, "Tài khoản của bạn đang bị khóa. Vui lòng liên hệ quản trị viên.", "login.jsp");
                return;
            }

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("dashboard.jsp");

        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }

    private void forwardWithError(HttpServletRequest request, HttpServletResponse response, String message, String destination) 
            throws ServletException, IOException {
        request.setAttribute("error", message);
        request.getRequestDispatcher(destination).forward(request, response);
    }
}
