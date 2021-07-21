package com.user_management_app.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user_management_app.db.ConnectionProvider;
import com.user_management_app.db.Db_Operations;
import com.user_management_app.user.User;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		User user = new User();
		Db_Operations db = null;

		String UName = request.getParameter("UName");
		String UEmail = request.getParameter("UEmail");
		String UPassword = request.getParameter("UPassword");
		String UContact = request.getParameter("UContact");
		String UAddress = request.getParameter("UAddress");

		int User_Id = Integer.parseInt(request.getParameter("UId"));

		db = new Db_Operations(ConnectionProvider.getConnection());
		try {
			user = db.getUserbyId(User_Id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		user.setName(UName);
		user.setEmail(UEmail);
		user.setPassword(UPassword);
		user.setContact(UContact);
		user.setAddress(UAddress);

		int updateUserId = 0;

		try {
			updateUserId = db.updateUser(user);
			if (updateUserId != 0) {
				out.print("successfully updated: " + updateUserId);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("GetAllUserServlet");
				requestDispatcher.forward(request, response);
			} else {
				out.print("not update");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
