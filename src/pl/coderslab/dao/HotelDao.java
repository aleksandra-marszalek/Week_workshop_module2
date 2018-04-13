package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import pl.coderslab.DbUtil;
import pl.coderslab.entity.Hotel;
import pl.coderslab.entity.User;

public class HotelDao {
	private static final String CREATE_HOTEL_QUERY = "INSERT INTO hotels (name, address, descrption, accepts_animals) VALUES (?, ?, ?, ?)";
	private static final String READ_HOTEL_QUERY = "Select * from hotels where id = ?"; 
	private static final String UPDATE_HOTEL_QUERY = "UPDATE hotels SET name = ? , address = ?, description = ?, accepts_animals = ? WHERE id = ?";
	private static final String DELETE_HOTEL_QUERY = "DELETE FROM hotels where id = ?"; 
	private static final String READ_ALL_HOTELS_QUERY = "SELECT * FROM hotels";
	
	public static void showHotels (Hotel hotel) {
		System.out.println("Id: " + hotel.getId() + ", name: " + hotel.getName() + ", address: " + hotel.getAddress() + ", description: " + hotel.getDescription() + ", accepts animals: " + hotel.getAcceptsAnimals());
	}
	
	public static void create(Hotel hotel) {
		try (Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(CREATE_HOTEL_QUERY);) {
		        statement.setString(1, hotel.getName());
		        statement.setString(2, hotel.getAddress());
		        statement.setString(3, hotel.getDescription());
		        statement.setString(4, hotel.getAcceptsAnimals());
		        statement.executeUpdate();
		} catch (Exception e) { 
			System.out.println("Error: ");
			e.printStackTrace(); 
		}
	}
	
	
	public static Hotel readById(int hotelId) {
		Hotel hotel = new Hotel();
		try (Connection connection = DbUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(READ_HOTEL_QUERY);) {
		statement.setInt(1, hotelId);
		try (ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				hotel.setId(resultSet.getInt("id")); 
				hotel.setName(resultSet.getString("name")); 
				hotel.setAddress(resultSet.getString("address")); 
				hotel.setDescription(resultSet.getString("description")); 
				hotel.setAcceptsAnimals(resultSet.getString("accepts_animals")); 
				}
			}
		} catch (Exception e) { 
			System.out.println("Error: ");
			e.printStackTrace(); 
			}
		    return hotel;
		}
	
	public static void update(Hotel hotel) {
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_HOTEL_QUERY);) {
			statement.setInt(5, hotel.getId()); 
			statement.setString(1, hotel.getName()); 
			statement.setString(2, hotel.getAddress()); 
			statement.setString(3, hotel.getDescription());
			statement.setString(4, hotel.getAcceptsAnimals()); 
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: ");
			e.printStackTrace();
			} 
		}
	
	
	public static void delete(int hotelId) {
		try (Connection connection = DbUtil.getConnection(); 
				PreparedStatement statement = connection.prepareStatement(DELETE_HOTEL_QUERY);) { 
					statement.setInt(1, hotelId);
					statement.executeUpdate(); 
		} catch (Exception e) {
			System.out.println("Error: "); 
			e.printStackTrace();
			}
		}
	
	public static Hotel[] readAll() {
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		try (Connection connection = DbUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(READ_ALL_HOTELS_QUERY);) {
			

			ResultSet resultSet = statement.executeQuery(); 
			while (resultSet.next()) {
				Hotel loadedHotel = new Hotel();
				loadedHotel.setId(resultSet.getInt("id")); 
				loadedHotel.setName(resultSet.getString("name")); 
				loadedHotel.setAddress(resultSet.getString("address")); 
				loadedHotel.setDescription(resultSet.getString("description")); 
				loadedHotel.setAcceptsAnimals(resultSet.getString("accepts_animals"));
				hotels.add(loadedHotel);
				}
	
		} catch (Exception e) { 
			System.out.println("Error: ");
			e.printStackTrace(); 
			}
		// tworzą tablicę o takiej samej wielkosci co lista
		Hotel[] uArray = new Hotel[hotels.size()]; 
		// przekopiowują listę do tablicy
		uArray = hotels.toArray(uArray); 
		return uArray;
		}
}
