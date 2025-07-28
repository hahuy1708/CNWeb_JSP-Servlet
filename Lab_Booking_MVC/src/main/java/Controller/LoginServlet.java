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

// import Model.bo.CheckLoginBO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

//        CheckLoginBO loginBO = new CheckLoginBO();
//        boolean isValid = loginBO.checkLogin(username, password);
//
//        if (isValid) {
//            HttpSession session = request.getSession();
//            session.setAttribute("user", username);
//            response.sendRedirect("home.jsp");
//        } else {
//            response.sendRedirect("login.jsp?error=1");
//        }
    }
}
