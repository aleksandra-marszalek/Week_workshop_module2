package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pl.coderslab.DbUtil;
import pl.coderslab.entity.User;

public class UserDao {
		private static final String CREATE_USER_QUERY = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
		private static final String READ_USER_QUERY = "Select * from users where id = ?"; 
		private static final String UPDATE_USER_QUERY = "UPDATE users SET name = ? , email = ?, password = ?  WHERE id = ?";
		private static final String DELETE_USER_QUERY = "DELETE FROM users where id = ?"; 
		private static final String READ_ALL_USERS_QUERY = "SELECT * FROM users";
		private static final String READ_ALL_USERS_BY_GROUP_ID_QUERY = "Select * from users where user_group_id = ?";
		private static final String READ_USER_BY_EMAIL_QUERY = "Select * from users where email=?";
		private static final String ADD_USER_TO_GROUP_QUERY = "Update users SET user_group_id = ? where id = ?";
		private static final String READ_USER_BY_NAME_QUERY = "Select * from users where name LIKE ?";
		
		
		public static void showUser (User user) {
			System.out.println("Id: " + user.getId() + ", name: " + user.getName() + ", email: " + user.getEmail() + ", password: " + user.getPassword() + ", user_group_id: " + user.getUserGroupId());
		}
		
		public static void create(User user) {
			try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(CREATE_USER_QUERY);) {
			        statement.setString(1, user.getName());
			        statement.setString(2, user.getEmail());
			        statement.setString(3, user.getPassword());
			        statement.executeUpdate();
			} catch (Exception e) { 
				System.out.println("Error: ");
				e.printStackTrace(); 
			}
		}
		
		
		public static User readById(int userId) {
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
		
		public static void update(User user) {
			try (Connection connection = DbUtil.getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY);) {
				statement.setInt(4, user.getId()); 
				statement.setString(1, user.getName()); 
				statement.setString(2, user.getEmail()); 
				statement.setString(3, user.getPassword());
				statement.executeUpdate();
			} catch (Exception e) {
				System.out.println("Error: ");
				e.printStackTrace();
				} 
			}
		
		
		public static void delete(int userId) {
			try (Connection connection = DbUtil.getConnection(); 
					PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY);) { 
						statement.setInt(1, userId);
						statement.executeUpdate(); 
			} catch (Exception e) {
				System.out.println("Error: "); 
				e.printStackTrace();
				}
			}
		
		public static User[] readAll() {
			ArrayList<User> users = new ArrayList<User>();
			try (Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(READ_ALL_USERS_QUERY);) {
				

				ResultSet resultSet = statement.executeQuery(); 
				while (resultSet.next()) {
					User loadedUser = new User();
					loadedUser.setId(resultSet.getInt("id")); 
					loadedUser.setName(resultSet.getString("name")); 
					loadedUser.setPassword(resultSet.getString("password")); 
					loadedUser.setEmail(resultSet.getString("email")); 
					loadedUser.setUserGroupId(resultSet.getInt("user_group_id"));
					users.add(loadedUser);
					}
		
			} catch (Exception e) { 
				System.out.println("Error: ");
				e.printStackTrace(); 
				}
			// tworzą tablicę o takiej samej wielkosci co lista
			User[] uArray = new User[users.size()]; 
			// przekopiowują listę do tablicy
			uArray = users.toArray(uArray); 
			return uArray;
			}
		
		public static User[] readAllByGroupId(int groupId) {
			ArrayList<User> users = new ArrayList<User>();
			try (Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(READ_ALL_USERS_BY_GROUP_ID_QUERY);) {
				
				statement.setInt(1, groupId);
				ResultSet resultSet = statement.executeQuery(); 
				while (resultSet.next()) {
					User loadedUser = new User();
					loadedUser.setId(resultSet.getInt("id")); 
					loadedUser.setName(resultSet.getString("name")); 
					loadedUser.setPassword(resultSet.getString("password")); 
					loadedUser.setEmail(resultSet.getString("email")); 
					loadedUser.setUserGroupId(resultSet.getInt("user_group_id"));
					users.add(loadedUser);
					}
				
		
			} catch (Exception e) { 
				System.out.println("Error: ");
				e.printStackTrace(); 
				}
			 User[] uArray = new User[users.size()]; 
			 uArray = users.toArray(uArray);
			    return uArray;
		}
		
		public static User readByEmail(String userEmail) {
			String userEmailToLowerCase = userEmail.toLowerCase();
			User user = new User();
			try (Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(READ_USER_BY_EMAIL_QUERY);) {
			statement.setString(1, userEmailToLowerCase);
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
		
		public static User readByName(String userName) {
			String userNameToLowerCase = userName.toLowerCase();
			User user = new User();
			try (Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(READ_USER_BY_NAME_QUERY);) {
			statement.setString(1, userNameToLowerCase);
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
		
		public static void assignToGroup(User user, int groupId) {
			try (Connection connection = DbUtil.getConnection();
					PreparedStatement statement = connection.prepareStatement(ADD_USER_TO_GROUP_QUERY);) {
				statement.setInt(2, user.getId()); 
				statement.setInt(1, groupId); 
				statement.executeUpdate();
			} catch (Exception e) {
				System.out.println("Error: ");
				e.printStackTrace();
				} 
			}
		
		}
			
			
	
			 

