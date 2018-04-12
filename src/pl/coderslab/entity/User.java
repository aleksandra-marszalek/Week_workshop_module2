package pl.coderslab.entity;

import pl.coderslab.BCrypt;

public class User {
	private int id;
	private String name;
	private String password;
	private String email;
	private int userGroupId;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	public String getPassword() {
		return password;
	}
	public int getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}
	
	public User(String name, String email,  String password, int userGrouId) {
		this.name = name;
		this.email = email;
		this.setPassword(password);
		this.userGroupId = userGroupId;
	}
	public User() {
		
	}
	
	
	
}
