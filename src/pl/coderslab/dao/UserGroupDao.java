package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import pl.coderslab.DbUtil;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserGroup;

public class UserGroupDao {
	private static final String CREATE_USER_GROUP_QUERY = "INSERT INTO user_group (name) VALUES (?)";
	private static final String READ_USER_GROUP_QUERY = "Select * from user_group where id = ?"; 
	private static final String UPDATE_USER_GROUP_QUERY = "UPDATE user_group SET name = ? WHERE id = ?";
	private static final String DELETE_USER_GROUP_QUERY = "DELETE FROM user_group where id = ?"; 
	private static final String READ_ALL_USER_GROUPS_QUERY = "SELECT * FROM user_group";

	
	public static void showUserGroup (UserGroup userGroup) {
		System.out.println("Id: " + userGroup.getId() + ", name: " + userGroup.getName());
	}
	
	public static void create(UserGroup userGroup) {
		try (Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(CREATE_USER_GROUP_QUERY);) {
		        statement.setString(1, userGroup.getName());
		        statement.executeUpdate();
		} catch (Exception e) { 
			System.out.println("Error: ");
			e.printStackTrace(); 
		}
	}
	
	
	public static UserGroup readById(int userGroupId) {
		UserGroup userGroup = new UserGroup();
		try (Connection connection = DbUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(READ_USER_GROUP_QUERY);) {
		statement.setInt(1, userGroupId);
		try (ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				userGroup.setId(resultSet.getInt("id")); 
				userGroup.setName(resultSet.getString("name")); 
				}
			}
		} catch (Exception e) { 
			System.out.println("Error: ");
			e.printStackTrace(); 
			}
		    return userGroup;
		}
	
	public static void update(UserGroup userGroup) {
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USER_GROUP_QUERY);) {
			statement.setInt(2, userGroup.getId()); 
			statement.setString(1, userGroup.getName()); 
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: ");
			e.printStackTrace();
			} 
		}
	
	
	public static void delete(int userGroupId) {
		try (Connection connection = DbUtil.getConnection(); 
				PreparedStatement statement = connection.prepareStatement(DELETE_USER_GROUP_QUERY);) { 
					statement.setInt(1, userGroupId);
					statement.executeUpdate(); 
		} catch (Exception e) {
			System.out.println("Error: "); 
			e.printStackTrace();
			}
		}
	
	public static UserGroup[] readAll() {
		ArrayList<UserGroup> userGroups = new ArrayList<UserGroup>();
		try (Connection connection = DbUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(READ_ALL_USER_GROUPS_QUERY);) {
			

			ResultSet resultSet = statement.executeQuery(); 
			while (resultSet.next()) {
				UserGroup loadedUserGroup = new UserGroup();
				loadedUserGroup.setId(resultSet.getInt("id")); 
				loadedUserGroup.setName(resultSet.getString("name")); 
				userGroups.add(loadedUserGroup);
				}
	
		} catch (Exception e) { 
			System.out.println("Error: ");
			e.printStackTrace(); 
			}
		// tworzą tablicę o takiej samej wielkosci co lista
		UserGroup[] uArray = new UserGroup[userGroups.size()]; 
		// przekopiowują listę do tablicy
		uArray = userGroups.toArray(uArray); 
		return uArray;
		}
}
