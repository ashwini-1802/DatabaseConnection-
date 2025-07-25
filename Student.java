package com.tka;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Student extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String fname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
         String mobno = request.getParameter("mobno");
	     long phoneno =Long.parseLong(mobno);
		 PrintWriter out = response.getWriter();

	     
	     
//		 display on the console
		System.out.println("fullname");
		System.out.println("email");
		System.out.println("pass");
		System.out.println("mobNo");
		
//		// display on the browser
	//    PrintWriter out = response.getWriter();
		out.println("<h1>"+fname+"</h1>");
		out.println("<h1>"+email+"</h1>");
		out.println("<h1>"+pass+"</h1>");
		out.println("<h1>"+mobno+"</h1>");

		try 
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/batch227","root","root");
		PreparedStatement ps= c.prepareStatement("insert into studentinfos(sname, email, password, phoneno)values(?,?,?,?)");
		ps.setString(1, fname);
		ps.setString(2, email);
		ps.setString(3, pass);
		ps.setLong(4, phoneno);
		int set= ps.executeUpdate();
	//int a=	ps.executeUpdate();
	
	if(set >0) 
	{
		out.print("<h1>"+"registration successfully.."+"</h1>");
		out.print("<a href = login.html>login here</a>");
	}
	else 
	{
		out.print("<h1>"+"registration unsuccessfully... "+"</h1>");
	}
	
	//	c.commit();
		c.close();
		
		} 
		catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
}
