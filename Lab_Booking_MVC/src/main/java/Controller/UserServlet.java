package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.bean.User;
import Model.bo.UserBO;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserBO userBO;

    public void init() {
        userBO = new UserBO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "register":
                request.getRequestDispatcher("register.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect("login.jsp");
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
    	
    	
        String action = request.getParameter("action");

        switch (action) {
        	case "register":
        		handleRegister(request,response);
        		break;
        	default:
        		break;
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
                request.setAttribute("error", "Đăng ký thất bại");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }
}
