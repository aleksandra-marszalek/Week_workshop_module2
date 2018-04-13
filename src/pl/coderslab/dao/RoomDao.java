package pl.coderslab.dao;



public class RoomDao {
	private static final String CREATE_ROOM_QUERY = "INSERT INTO rooms (description, capacity, price, hotel_id, inside_nr) VALUES (?, ?, ?, ?, ?)";
	private static final String READ_ROOM_QUERY = "Select * from rooms where id = ?"; 
	private static final String UPDATE_ROOM_QUERY = "UPDATE rooms SET description = ? , capacity = ?, price = ?, hotel_id = ?, inside_nr = ? WHERE id = ?";
	private static final String DELETE_ROOM_QUERY = "DELETE FROM rooms where id = ?"; 
	private static final String READ_ALL_ROOMS_QUERY = "SELECT * FROM rooms";
	private static final String READ_ROOM_BY_HOTEL_ID = "SELECT * from rooms where hotel_id = ?";
	private static final String ASSIGN_ROOM_TO_HOTEL = "UPDATE rooms SET hotel_id = ? WHERE id = ?";
	
	
}
