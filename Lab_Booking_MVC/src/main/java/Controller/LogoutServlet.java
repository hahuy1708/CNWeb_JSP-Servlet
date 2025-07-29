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

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	if(session != null) {
    		session.invalidate();
    	}
    	response.sendRedirect("login.jsp");
    }
}
