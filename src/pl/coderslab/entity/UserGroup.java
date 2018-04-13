package pl.coderslab.entity;

public class UserGroup {
	
	private int id;
	private String name;
	
	
	public UserGroup(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}

}
