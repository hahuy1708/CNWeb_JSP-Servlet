package Controller;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.bean.User;

import Model.bo.UserBO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        UserBO userBO = new UserBO();
        
        try {
			User user = userBO.checkAuth(username, password);
			if (user != null && "ACTIVE".equals(user.getStatus())) {
	            HttpSession session = request.getSession();
	            session.setAttribute("user", user);
	            
	            if ("ADMIN".equals(user.getRole())) {
	                response.sendRedirect("admin/dashboard.jsp");
	            } else {
	                response.sendRedirect("teacher/dashboard.jsp");
	            }
	        } else {
	            request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
	            request.getRequestDispatcher("/login.jsp").forward(request, response);
	        }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
