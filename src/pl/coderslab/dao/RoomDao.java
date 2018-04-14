package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import pl.coderslab.DbUtil;
import pl.coderslab.entity.Hotel;
import pl.coderslab.entity.Room;
import pl.coderslab.entity.User;

public class RoomDao {
	private static final String CREATE_ROOM_QUERY = "INSERT INTO rooms (description, capacity, price, hotel_id, inside_nr) VALUES (?, ?, ?, ?, ?)";
	private static final String READ_ROOM_QUERY = "Select * from rooms where id = ?"; 
	private static final String UPDATE_ROOM_QUERY = "UPDATE rooms SET description = ? , capacity = ?, price = ?, hotel_id = ?, inside_nr = ? WHERE id = ?";
	private static final String DELETE_ROOM_QUERY = "DELETE FROM rooms where id = ?"; 
	private static final String READ_ALL_ROOMS_QUERY = "SELECT * FROM rooms";
	private static final String READ_ROOM_BY_HOTEL_ID = "SELECT * from rooms where hotel_id = ?";
	private static final String ASSIGN_ROOM_TO_HOTEL = "UPDATE rooms SET hotel_id = ? WHERE id = ?";
	//private static final String READ_ROOM_BY_ROOM_NR_AND_HOTEL_ID = "SELECT * FROM rooms where inside_nr = ?, hotel_id = ?";
	
	public static void showRoom (Room room) {
		System.out.println("Id: " + room.getId() + ", description: " + room.getDescription() + 
				 ", capacity: " + room.getCapacity() + ", price: " + room.getPrice() + ", hotel Id: " + room.getHotelId() + ", inside nr: " + room.getInsideNr());
	}
	
	public static void create(Room room) {
		try (Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(CREATE_ROOM_QUERY);) {
		        statement.setString(1, room.getDescription());
		        statement.setInt(2, room.getCapacity());
		        statement.setDouble(3, room.getPrice());
		        statement.setInt(4, room.getHotelId());
		        statement.setInt(5, room.getInsideNr());
		        statement.executeUpdate();
		} catch (Exception e) { 
			System.out.println("Error: ");
			e.printStackTrace(); 
		}
	}
	
	
	public static Room readById(int roomId) {
		Room room = new Room();
		try (Connection connection = DbUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(READ_ROOM_QUERY);) {
		statement.setInt(1, roomId);
		try (ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				room.setId(resultSet.getInt("id"));
				room.setDescription(resultSet.getString("description"));
				room.setCapacity(resultSet.getInt("capacity"));
				room.setPrice(resultSet.getDouble("price"));
				room.setHotelId(resultSet.getInt("hotel_id"));
				room.setInsideNr(resultSet.getInt("inside_nr"));
				}
			}
		} catch (Exception e) { 
			System.out.println("Error: ");
			e.printStackTrace(); 
			}
		    return room;
		}
	
	public static void update(Room room) {
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ROOM_QUERY);) {
			statement.setInt(6, room.getId()); 
			statement.setString(1, room.getDescription()); 
			statement.setInt(2, room.getCapacity());
	        statement.setDouble(3, room.getPrice());
	        statement.setInt(4, room.getHotelId());
	        statement.setInt(5, room.getInsideNr());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: ");
			e.printStackTrace();
			} 
		}
	
	
	public static void delete(int roomId) {
		try (Connection connection = DbUtil.getConnection(); 
				PreparedStatement statement = connection.prepareStatement(DELETE_ROOM_QUERY);) { 
					statement.setInt(1, roomId);
					statement.executeUpdate(); 
		} catch (Exception e) {
			System.out.println("Error: "); 
			e.printStackTrace();
			}
		}
	
	public static Room[] readAll() {
		ArrayList<Room> rooms = new ArrayList<Room>();
		try (Connection connection = DbUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(READ_ALL_ROOMS_QUERY);) {
			

			ResultSet resultSet = statement.executeQuery(); 
			while (resultSet.next()) {
				Room loadedRoom = new Room();
				loadedRoom.setId(resultSet.getInt("id"));
				loadedRoom.setDescription(resultSet.getString("description"));
				loadedRoom.setCapacity(resultSet.getInt("capacity"));
				loadedRoom.setPrice(resultSet.getDouble("price"));
				loadedRoom.setHotelId(resultSet.getInt("hotel_id"));
				loadedRoom.setInsideNr(resultSet.getInt("inside_nr"));
				rooms.add(loadedRoom);
				}
	
		} catch (Exception e) { 
			System.out.println("Error: ");
			e.printStackTrace(); 
			}
		// tworzą tablicę o takiej samej wielkosci co lista
		Room[] uArray = new Room[rooms.size()]; 
		// przekopiowują listę do tablicy
		uArray = rooms.toArray(uArray); 
		return uArray;
		}
	
	public static Room[] readAllByHotelId(int hotelId) {
		ArrayList<Room> rooms = new ArrayList<Room>();
		try (Connection connection = DbUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(READ_ROOM_BY_HOTEL_ID);) {
			
			statement.setInt(1, hotelId);
			ResultSet resultSet = statement.executeQuery(); 
			while (resultSet.next()) {
				Room loadedRoom = new Room();
				loadedRoom.setId(resultSet.getInt("id"));
				loadedRoom.setDescription(resultSet.getString("description"));
				loadedRoom.setCapacity(resultSet.getInt("capacity"));
				loadedRoom.setPrice(resultSet.getDouble("price"));
				loadedRoom.setHotelId(resultSet.getInt("hotel_id"));
				loadedRoom.setInsideNr(resultSet.getInt("inside_nr"));
				rooms.add(loadedRoom);
				}
			
	
		} catch (Exception e) { 
			System.out.println("Error: ");
			e.printStackTrace(); 
			}
		 Room[] uArray = new Room[rooms.size()]; 
		 uArray = rooms.toArray(uArray);
		    return uArray;
	}
	
	
	public static void assignRoomToHotel(Room room, int hotelId) {
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(ASSIGN_ROOM_TO_HOTEL);) {
			statement.setInt(2, room.getId()); 
	        statement.setInt(1, hotelId);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: ");
			e.printStackTrace();
			} 
		}
}
