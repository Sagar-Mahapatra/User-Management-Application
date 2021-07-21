package com.user_management_app.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user_management_app.db.ConnectionProvider;
import com.user_management_app.db.Db_Operations;
import com.user_management_app.user.User;

/**
 * Servlet implementation class GetAllUserServlet
 */
@WebServlet("/GetAllUserServlet")
public class GetAllUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Db_Operations db;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllUserServlet() {
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

		db = new Db_Operations(ConnectionProvider.getConnection());
		List<User> allUsers = db.getAllUsers();
		out.print("<h1 style='color:blue;text-align:center;'>All Users List</h1>");

		for (User user : allUsers) {

			out.print(
					" <form action=\"UpdateServlet\" method=\"POST\"> <table border=\"1px\" style=\"border-collapse: collapse;text-align: center;margin-left:100px;\">\r\n"
							+ "        <tr>\r\n"
							+ "            <td><input style=\"width: 50px;\" type='number' name='UId' readonly value= "
							+ user.getId() + "></td>"
							+ "            <td ><input style=\"width: 100px;\" type='text' name='UName' value= "
							+ user.getName() + "></td>\r\n"
							+ "            <td ><input style=\"width: 300px;\" type='email' name='UEmail' value= "
							+ user.getEmail() + " ></td>\r\n"
							+ "            <td ><input style=\"width: 100px;\" type='text' name='UPassword' value="
							+ user.getPassword() + " ></td>\r\n"
							+ "            <td ><input style=\"width: 100px;\" type='text' name='UContact' value="
							+ user.getContact() + " ></td>\r\n"
							+ "            <td ><input style=\"width: 100px;\" type='text' name='UAddress' value="
							+ user.getAddress() + " ></td>\r\n"
							+ "            <td style=\"width: 100px;\"><button type='submit'>Update</button></td>\r\n"
							+ "            <td style=\"width: 100px;\"><a href=\'DeleteServlet?userID=" + user.getId()
							+ "'>Delete</a></td>\r\n" + "        </tr>\r\n" + "    </table></form>");

		}
		out.print(
				"<h3 style='text-align:center;'><a href='userDetail.html' type=\"button\">Click here for Profile Page</a></h3>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		db = new Db_Operations(ConnectionProvider.getConnection());
		List<User> allUsers = db.getAllUsers();
		out.print("<h1 style='color:blue;text-align:center;'>All Users List</h1>");

		for (User user : allUsers) {

			out.print(
					" <form action=\"UpdateServlet\" method=\"POST\"> <table border=\"1px\" style=\"border-collapse: collapse;text-align: center;margin-left:100px;\">\r\n"
							+ "        <tr>\r\n"
							+ "            <td><input style=\"width: 50px;\" type='number' name='UId' readonly value= "
							+ user.getId() + "></td>"
							+ "            <td ><input style=\"width: 100px;\" type='text' name='UName' value= "
							+ user.getName() + "></td>\r\n"
							+ "            <td ><input style=\"width: 300px;\" type='email' name='UEmail' value= "
							+ user.getEmail() + " ></td>\r\n"
							+ "            <td ><input style=\"width: 100px;\" type='text' name='UPassword' value="
							+ user.getPassword() + " ></td>\r\n"
							+ "            <td ><input style=\"width: 100px;\" type='text' name='UContact' value="
							+ user.getContact() + " ></td>\r\n"
							+ "            <td ><input style=\"width: 100px;\" type='text' name='UAddress' value="
							+ user.getAddress() + " ></td>\r\n"
							+ "            <td style=\"width: 100px;\"><button type='submit'>Update</button></td>\r\n"
							+ "            <td style=\"width: 100px;\"><a href=\'DeleteServlet?userID=" + user.getId()
							+ "'>Delete</a></td>\r\n" + "        </tr>\r\n" + "    </table></form>");

		}
		out.print(
				"<h3 style='text-align:center;'><a href='userDetail.html' type=\"button\">Click here for Profile Page</a></h3>");

	}
}
