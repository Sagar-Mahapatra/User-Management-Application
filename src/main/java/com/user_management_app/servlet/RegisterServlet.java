package com.user_management_app.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user_management_app.db.ConnectionProvider;
import com.user_management_app.db.Db_Operations;
import com.user_management_app.user.User;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	/**
	 * Default constructor.
	 */
	public RegisterServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

//fetching request data
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String contact = request.getParameter("contact");
		String address = request.getParameter("address");

// inserting to User object
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setContact(contact);
		user.setAddress(address);

//calling getConnection() for DB connection
		connection = ConnectionProvider.getConnection();

//calling insertUser() for save data into DB		
		boolean insertUser = new Db_Operations(connection).insertUser(user);
		if (insertUser) {
			out.print("<div style=\"color: rgb(236, 89, 72);\"color:white;text-align:center;'>");
			out.print("<h1 style='text-align:center;'>Welcome " + user.getName() + "</h1>");
			out.print("<h1 style='text-align:center;'>Registration Successful...");
			out.print(" <div style='margin-top:30px;'>\r\n"
					+ "       <a style='padding:10px;width:50px;background:blue;color:white;border-radius:10px;text-decoration: none;' href='index.html' type=\"button\">Home</a>\r\n"
					+ "       <a style='padding:10px;width:50px;background:green;color:white;border-radius:10px;text-decoration: none;' href='login.html' type=\"button\">Login</a>\r\n"
					+ "   </div>");
			out.print("</div>");

		} else {
			out.print("<h1 style='color:red;text-align:center;'>Something went wrong,please try again later...</h1>");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.html");
			requestDispatcher.include(request, response);
		}

	}

}
