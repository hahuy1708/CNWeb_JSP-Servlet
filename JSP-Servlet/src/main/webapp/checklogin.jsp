<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import = "javax.servlet.RequestDispatcher" %>

<%@page language="java" import = "javax.servlet.ServletException" %>
<%@page import = "java.sql.Connection" %>
<%@page import = "java.sql.*" %>
<%@page import = "java.sql.DriverManager" %>
<%@page import = "java.sql.Statement" %>
<%@page import = "java.sql.ResultSet" %>
<%@page import = "java.sql.ResultSetMetaData" %>


<%
String username = request.getParameter("username");
String password = request.getParameter("password");
String address = "192 Nguyen Luong Bang";
boolean ktra = false;
try{
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dulieu4","root","");
	Statement sm = conn.createStatement();
	String sql = "Select * from user";
	ResultSet rs = sm.executeQuery(sql);
	while(rs.next()){
		if(username.equals(rs.getString(1)) && password.equals(rs.getString(2))){
			ktra = true; break;
		}
	}
}
catch (Exception e) {System.out.print(e);}
if(ktra == true){
	request.setAttribute("address",address);
	RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
	rd.forward(request,response);
}
else{
	response.sendRedirect("login.jsp");
}

%>