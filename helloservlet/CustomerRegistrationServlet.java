package com.mouritech.helloservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerRegistrationServlet
 */
@WebServlet("/CustomerRegistrationServlet123")
public class CustomerRegistrationServlet extends HttpServlet {
	static Connection con; // = null;
	static PreparedStatement pstmt; // = null;
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//set response content type
	try {
		response.setContentType("text/html");
		//Here the text to be printed on the browser
		PrintWriter out = response.getWriter();
		//read the data entered in the html
		String customerName = request.getParameter("cname");
		String customerEmail = request.getParameter("email");
		String customerPassword = request.getParameter("password");
		out.println("<h1> Customer Name = " +customerName + "Customer Email = "+customerEmail+
				"CustomerPassword = "+customerPassword + "</h1>");
		con = DBConnection.getDBConnection();
		String insertCustomer = "insert into customer_reg values(?,?,?);";
		
		pstmt = con.prepareStatement(insertCustomer);
		pstmt.setString(1, customerName);
		pstmt.setString(2, customerEmail);
		pstmt.setString(3, customerPassword);
		int i = pstmt.executeUpdate();
		if(i != 0) {
			out.println("inserted successfully");
			RequestDispatcher rd = request.getRequestDispatcher("SuccessServlet");
			rd.forward(request, response);
		}else {
			out.println("Not inserted");
			RequestDispatcher rd = request.getRequestDispatcher("CustomerRegistration.html");
			rd.include(request, response);
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	}
		
	}

}