package com.mouritech.servlet;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet implementation class ProductDetailsServlet
 */
@WebServlet("/ProductDetailsServlet")
public class ProductDetailsServlet extends HttpServlet {
	static Connection con; // = null;
	static PreparedStatement pstmt; // = null;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			response.setContentType("text/html");
			//Here the text to be printed on the browser
			PrintWriter out = response.getWriter();
			//read the data entered in the html
			String prodid = request.getParameter("prodid");
			String productId = request.getParameter("prodid");
			String productName = request.getParameter("prodname");
			String productCat = request.getParameter("prodcat");
			String productBrand = request.getParameter("prodbrand");
			String productPrice = request.getParameter("prodprice");
			out.println("<h1> Product id = " +productId + "product Name = "+productName+
					"Product Category = "+productCat + "Product Brand" + productBrand+"product price" + productPrice + "</h1>");
			con = DBConnection.getDBConnection();
			String insertCustomer = "insert into productDetails values(?,?,?,?,?);";
			
			pstmt = con.prepareStatement(insertCustomer);
			pstmt.setInt(1,Integer.parseInt(productId));
			pstmt.setString(1, productId);
			pstmt.setString(2, productName);
			pstmt.setString(3, productCat);
			pstmt.setString(4, productBrand);
			pstmt.setFloat(5, Float.parseFloat(productPrice));
			int i = pstmt.executeUpdate();
			if(i != 0) {
				out.println("inserted successfully");
			}else {
				out.println("Not inserted");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		}



		
	}