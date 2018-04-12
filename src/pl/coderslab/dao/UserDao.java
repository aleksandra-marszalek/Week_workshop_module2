package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pl.coderslab.DbUtil;
import pl.coderslab.entity.User;

public class UserDao {
		private static final String CREATE_USER_QUERY = "INSERT INTO users(name,email,password,user_group_id) VALUES (?,?,?,?)";
		private static final String READ_USER_QUERY = "Select * from users where id = ?"; 
		private static final String UPDATE_USER_QUERY = "UPDATE users SET name = ? , email = ?, password = ?, user_group_id =? WHERE id = ?";
		private static final String DELETE_USER_QUERY = "DELETE FROM users where id = ?"; 
		private static final String READ_ALL_USERS_QUERY = "SELECT * FROM users";
		
		
		public void delete(int userId) {
			try (Connection connection = DbUtil.getConnection(); 
					PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY);) { 
						statement.setInt(1, userId);
						statement.executeUpdate(); 
			} catch (Exception e) {
				System.out.println("Error: "); 
				e.printStackTrace();
				}
			}
		
		public User read(int userId) {
			User user = new User();
			try (Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(READ_USER_QUERY);) {
			statement.setInt(1, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					user.setId(resultSet.getInt("id")); 
					user.setName(resultSet.getString("name")); 
					user.setEmail(resultSet.getString("email")); 
					user.setPassword(resultSet.getString("password")); 
					user.setUserGroupId(resultSet.getInt("user_group_id")); 
					}
				}
			} catch (Exception e) { 
				System.out.println("Error: ");
				e.printStackTrace(); 
				}
			    return user;
			}
		
		
		public void update(User user) {
			try (Connection connection = DbUtil.getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY);) {
				statement.setInt(6, user.getId()); 
				statement.setString(1, user.getName()); 
				statement.setString(3, user.getEmail()); 
				statement.setString(4, user.getPassword());
				statement.setInt(5, user.getUserGroupId()); 
				statement.executeUpdate();
			} catch (Exception e) {
				System.out.println("Error: ");
				e.printStackTrace();
				} 
			}
			 
}
