package com.user_management_app.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user_management_app.db.ConnectionProvider;
import com.user_management_app.db.Db_Operations;
import com.user_management_app.user.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		User user;

//fetching request data
		String email = request.getParameter("email");
		String password = request.getParameter("password");

//calling getUser()			
		user = new Db_Operations(ConnectionProvider.getConnection()).getUser(email, password);

		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

//sending response to UserProfileServlet			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserProfileServlet");
			requestDispatcher.forward(request, response);
		} else {
			out.print(
					"<h3 style='color:red;text-align:center;background-color:white;'>Please Enter Correct Credentials...</h3>");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.html");
			requestDispatcher.include(request, response);
		}

	}

}
