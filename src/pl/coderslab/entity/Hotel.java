package pl.coderslab.entity;

public class Hotel {
	private int id;
	private String name;
	private String description;
	private String address;
	private String acceptsAnimals;
	
	
	
	public Hotel(String name, String description, String address, String acceptsAnimals) {
		this.name = name;
		this.description = description;
		this.address = address;
		this.acceptsAnimals = acceptsAnimals;
	}
	
	public Hotel () {
		
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAcceptsAnimals() {
		return acceptsAnimals;
	}
	public void setAcceptsAnimals(String acceptsAnimals) {
		this.acceptsAnimals = acceptsAnimals;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
