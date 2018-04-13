package pl.coderslab.entity;

public class Room {
	private int id;
	private String description;
	private int capacity;
	private double price;
	private int hotelId;
	
	
	public Room () {
		
	}
	
	public Room(String description, int capacity, double price, int hotelId) {
		this.description = description;
		this.capacity = capacity;
		this.price = price;
		this.hotelId = hotelId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public int getId() {
		return id;
	}
	
	

}
