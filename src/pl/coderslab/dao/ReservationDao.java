package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import pl.coderslab.DbUtil;
import pl.coderslab.entity.Reservation;
import pl.coderslab.entity.Room;

public class ReservationDao {

	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO reservations (user_id, room_id, created, date_from, date_to, description, status) VALUES (?, ?, NOW(), ?, ?, ?, ?)";
	private static final String READ_RESERVATION_QUERY = "Select * from reservations where id = ?"; 
	private static final String UPDATE_RESERVATION_QUERY = "UPDATE reservations SET user_id=?, room_id=?, date_from=?, date_to=?, description=?, status=? where id = ?";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM reservations where id = ?"; 
	private static final String READ_ALL_RESERVATIONS_QUERY = "SELECT * FROM reservations";
	private static final String READ_RESERVATION_BY_USER_ID = "Select * FROM reservations where user_id =?";
	private static final String CHANGE_RESERVATION_STATUS = "Update reservations SET status =? where id=? ";
	
	public static void showReservation (Reservation reservation) {
		System.out.println("Id: " + reservation.getId() + ", user Id: " + reservation.getUserId() + 
				", room Id: " + reservation.getUserId() + ", created: " + reservation.getCreated() + ", date from: " + reservation.getDateFrom() +
				", date to: " + reservation.getDateTo() + ", description: " + reservation.getDescription() + ", status: " + reservation.getStatus());
	}
	
	public static void create(Reservation reservation) {
		try (Connection connection = DbUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(CREATE_RESERVATION_QUERY);) {
		        statement.setInt(1, reservation.getUserId());
		        statement.setInt(2, reservation.getRoomId());
		        statement.setString(3, reservation.getDateFrom());
		        statement.setString(4, reservation.getDateTo());
		        statement.setString(5, reservation.getDescription());
		        statement.setString(6, reservation.getStatus());
		        statement.executeUpdate();
		} catch (Exception e) { 
			System.out.println("Error: ");
			e.printStackTrace(); 
		}
	}
	
	
	public static Reservation readById(int resId) {
		Reservation reservation = new Reservation();
		try (Connection connection = DbUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(READ_RESERVATION_QUERY);) {
		statement.setInt(1, resId);
		try (ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				reservation.setId(resultSet.getInt("id"));
				reservation.setUserId(resultSet.getInt("user_id"));
				reservation.setRoomId(resultSet.getInt("room_id"));
				reservation.setCreated(resultSet.getString("created"));
				reservation.setDateFrom(resultSet.getString("date_from"));
				reservation.setDateTo(resultSet.getString("date_to"));
				reservation.setDescription(resultSet.getString("description"));
				reservation.setStatus(resultSet.getString("status"));
				/*
				 * private int id;
				private int userId;
				private int roomId;
				private String created;
				private String dateFrom;
				private String dateTo;
				private String description;
				private String status;
				 */
				}
			}
		} catch (Exception e) { 
			System.out.println("Error: ");
			e.printStackTrace(); 
			}
		    return reservation;
		}
	
	public static void update(Reservation reservation) {
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_RESERVATION_QUERY);) {
			statement.setInt(7, reservation.getId());
			statement.setInt(1, reservation.getUserId());
	        statement.setInt(2, reservation.getRoomId());
	        statement.setString(3, reservation.getDateFrom());
	        statement.setString(4, reservation.getDateTo());
	        statement.setString(5, reservation.getDescription());
	        statement.setString(6, reservation.getStatus());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: ");
			e.printStackTrace();
			} 
		}
	
	
	public static void delete(int resId) {
		try (Connection connection = DbUtil.getConnection(); 
				PreparedStatement statement = connection.prepareStatement(DELETE_RESERVATION_QUERY);) { 
					statement.setInt(1, resId);
					statement.executeUpdate(); 
		} catch (Exception e) {
			System.out.println("Error: "); 
			e.printStackTrace();
			}
		}
	
	public static Reservation[] readAll() {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		try (Connection connection = DbUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(READ_ALL_RESERVATIONS_QUERY);) {
			

			ResultSet resultSet = statement.executeQuery(); 
			while (resultSet.next()) {
				Reservation loadedReservation = new Reservation();
				loadedReservation.setId(resultSet.getInt("id"));
				loadedReservation.setUserId(resultSet.getInt("user_id"));
				loadedReservation.setRoomId(resultSet.getInt("room_id"));
				loadedReservation.setCreated(resultSet.getString("created"));
				loadedReservation.setDateFrom(resultSet.getString("date_from"));
				loadedReservation.setDateTo(resultSet.getString("date_to"));
				loadedReservation.setDescription(resultSet.getString("description"));
				loadedReservation.setStatus(resultSet.getString("status"));
				reservations.add(loadedReservation);
				}
	
		} catch (Exception e) { 
			System.out.println("Error: ");
			e.printStackTrace(); 
			}
		// tworzą tablicę o takiej samej wielkosci co lista
		Reservation[] uArray = new Reservation[reservations.size()]; 
		// przekopiowują listę do tablicy
		uArray = reservations.toArray(uArray); 
		return uArray;
		}
	
	public static Reservation[] readAllByUserId(int userId) {
		
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		try (Connection connection = DbUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(READ_RESERVATION_BY_USER_ID);) {
			
			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery(); 
			while (resultSet.next()) {
				Reservation loadedReservation = new Reservation();
				loadedReservation.setId(resultSet.getInt("id"));
				loadedReservation.setUserId(resultSet.getInt("user_id"));
				loadedReservation.setRoomId(resultSet.getInt("room_id"));
				loadedReservation.setCreated(resultSet.getString("created"));
				loadedReservation.setDateFrom(resultSet.getString("date_from"));
				loadedReservation.setDateTo(resultSet.getString("date_to"));
				loadedReservation.setDescription(resultSet.getString("description"));
				loadedReservation.setStatus(resultSet.getString("status"));
				reservations.add(loadedReservation);
			}
				
			} catch (Exception e) { 
				System.out.println("Error: ");
				e.printStackTrace(); 
				}
			// tworzą tablicę o takiej samej wielkosci co lista
			Reservation[] uArray = new Reservation[reservations.size()]; 
			// przekopiowują listę do tablicy
			uArray = reservations.toArray(uArray); 
			return uArray;
			}
	
	public static void changeReservationStatus(Reservation reservation, String status) {
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(CHANGE_RESERVATION_STATUS);) {
			statement.setInt(2, reservation.getId()); 
	        statement.setString(1, status);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: ");
			e.printStackTrace();
			} 
		}
}

