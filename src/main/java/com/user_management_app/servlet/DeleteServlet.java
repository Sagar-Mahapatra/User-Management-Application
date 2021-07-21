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

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		int User_Id = Integer.parseInt(request.getParameter("userID"));

		boolean deleteUser = false;

		Db_Operations db = new Db_Operations(ConnectionProvider.getConnection());
		try {
			deleteUser = db.deleteUser(User_Id);
			if (deleteUser) {
				out.print("user successfully deleted...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("GetAllUserServlet");
				requestDispatcher.forward(request, response);
			} else {
				out.print("user can't deleted...");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
