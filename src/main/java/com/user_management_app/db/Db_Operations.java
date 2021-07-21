package com.user_management_app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user_management_app.user.User;

public class Db_Operations {

	private Connection connection;
	private PreparedStatement prepareStatement;
	private List<User> List_Users;

	public Db_Operations(Connection con) {
		this.connection = con;

	}

//insert data to DB
	public boolean insertUser(User user) {

		boolean executed = false;

		String query = "insert into user(name,email,password,contact,address) values(?,?,?,?,?)";

		try {
			prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getEmail());
			prepareStatement.setString(3, user.getPassword());
			prepareStatement.setString(4, user.getContact());
			prepareStatement.setString(5, user.getAddress());
			prepareStatement.executeUpdate();
			executed = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return executed;

	}

//getting user by email & password	
	public User getUser(String email, String password) {

		PreparedStatement prepareStatement;
		User user = null;

		String query = "select * from user where email=? and password=?";

		try {
			prepareStatement = connection.prepareStatement(query);
			prepareStatement.setString(1, email);
			prepareStatement.setString(2, password);
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String user_email = resultSet.getString("email");
				String user_password = resultSet.getString("password");
				String contact = resultSet.getString("contact");
				String address = resultSet.getString("address");

				user = new User();
				user.setId(id);
				user.setName(name);
				user.setEmail(user_email);
				user.setPassword(user_password);
				user.setContact(contact);
				user.setAddress(address);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}

//getting all Users
	public List<User> getAllUsers() {
		List_Users = new ArrayList<User>();
		User user;

		String query = "select * from user";
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String psw = rs.getString("password");
				String contact = rs.getString("contact");
				String address = rs.getString("address");

				user = new User();
				user.setId(id);
				user.setName(name);
				user.setEmail(email);
				user.setPassword(psw);
				user.setContact(contact);
				user.setAddress(address);

				List_Users.add(user);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return List_Users;
	}

	public boolean deleteUser(int userId) throws SQLException {

		String Query = "DELETE FROM user WHERE id=?";

		boolean executed = false;

		PreparedStatement preparedStatement = connection.prepareStatement(Query);
		preparedStatement.setInt(1, userId);
		preparedStatement.executeUpdate();
		executed = true;

		return executed;

	}

	public int updateUser(User user) throws SQLException {

		int id = 0;

		String query = "UPDATE USER SET NAME=?,EMAIL=?,PASSWORD=?,CONTACT=?,ADDRESS=? WHERE ID=?";

		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setString(1, user.getName());
		prepareStatement.setString(2, user.getEmail());
		prepareStatement.setString(3, user.getPassword());
		prepareStatement.setString(4, user.getContact());
		prepareStatement.setString(5, user.getAddress());
		prepareStatement.setInt(6, user.getId());

		id = prepareStatement.executeUpdate();

		return id;

	}

	public User getUserbyId(int user_Id) throws SQLException {

		User user = null;

		String query = "SELECT * FROM USER WHERE ID=?";

		PreparedStatement prepareStatement = connection.prepareStatement(query);
		prepareStatement.setInt(1, user_Id);
		ResultSet resultSet = prepareStatement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");
			String contact = resultSet.getString("contact");
			String address = resultSet.getString("address");

			user = new User(id, name, email, password, contact, address);

		}

		return user;

	}

}
