package pl.coderslab.administrative;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Scanner;

import pl.coderslab.DbUtil;
import pl.coderslab.dao.ReservationDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.entity.Reservation;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserGroup;

public class ReservationManager {
public static void reservationManager () {
		
		try (Connection connection = DbUtil.getConnection()) {
			
		// welcome to the program, show all excercises using simple for loop
			System.out.println("Welcome to the Reservation Management System. Here are all the current User Reservations: ");
			Reservation[] allReservations = ReservationDao.readAll();
				for (int i=0; i<allReservations.length; i++) {
				ReservationDao.showReservation(ReservationDao.readAll()[i]);
				System.out.println("");
				}
		// show the options
			System.out.println("Please choose right command:\n"
					+ "add - to add a new reservation,\n"
					+ "edit - to edit existing reservation,\n"
					+ "delete - to remove existing reservation from a database,\n"
					+ "show - to show all the reservations made by a user,\n"
					+ "status - to update status of the chosen reservation,\n"
					+ "quit - to quit the user groups management system.");
			Scanner scan = new Scanner(System.in);
			String input1 = scan.nextLine();
			while (!input1.equalsIgnoreCase("add") && !input1.equalsIgnoreCase("edit") && 
				   !input1.equalsIgnoreCase("delete") && !input1.equalsIgnoreCase("quit") 
				   && !input1.equalsIgnoreCase("show") && !input1.equalsIgnoreCase("status")) {
				System.out.println("I don't understand. Please insert the right command: add/edit/delete/show/status/quit");
				input1 = scan.nextLine();
			}
		// putting everything in the while loop which ends when the input equals quit
			while (!input1.equalsIgnoreCase("quit")) {
				try {
		
		
		//ADD			
					
					if (input1.equalsIgnoreCase("add")) {
						Reservation reservationNew = new Reservation();
						System.out.println("Please insert user id: ");
						int userId = Integer.parseInt(scan.nextLine());
						reservationNew.setUserId(userId);
						System.out.println("Please insert room id: ");
						int roomId = Integer.parseInt(scan.nextLine());
						reservationNew.setRoomId(roomId);
						LocalDateTime datetime = LocalDateTime.now();
						String now = datetime.toString();
						reservationNew.setCreated(now);
						System.out.println("Please insert the date the reservations starts(RRRR-MM-DD): ");
						String dateFrom = scan.nextLine();
						reservationNew.setDateFrom(dateFrom);
						System.out.println("Please insert the date the reservations ends(RRRR-MM-DD): ");
						String dateTo = scan.nextLine();
						reservationNew.setDateTo(dateTo);
						System.out.println("Please insert any description: ");
						String description = scan.nextLine();
						reservationNew.setDescription(description);
						reservationNew.setStatus("accepted");
						ReservationDao.create(reservationNew);	
						
						
						
			// EDIT
						
					} else if (input1.equalsIgnoreCase("edit")) {
						System.out.println("Please insert the id of the reservation You want to edit: ");
						int resIdtoEdit = Integer.parseInt(scan.nextLine());
						Reservation reservationNew = ReservationDao.readById(resIdtoEdit);
						if (reservationNew.getId() == 0) {
							System.out.println("There is no such reservation.");
						} else {
							System.out.println("Please insert user id: ");
							int userId = Integer.parseInt(scan.nextLine());
							reservationNew.setUserId(userId);
							System.out.println("Please insert room id: ");
							int roomId = Integer.parseInt(scan.nextLine());
							reservationNew.setRoomId(roomId);
							LocalDateTime datetime = LocalDateTime.now();
							String now = datetime.toString();
							reservationNew.setCreated(now);
							System.out.println("Please insert the date the reservations starts(RRRR-MM-DD): ");
							String dateFrom = scan.nextLine();
							reservationNew.setDateFrom(dateFrom);
							System.out.println("Please insert the date the reservations ends(RRRR-MM-DD): ");
							String dateTo = scan.nextLine();
							reservationNew.setDateTo(dateTo);
							String description = scan.nextLine();
							reservationNew.setStatus("accepted");
							ReservationDao.update(reservationNew);	
						}
						
				//SHOW	
						
					} else if (input1.equalsIgnoreCase("show")) {
						System.out.println("Hi, you have chosen to display all the reservations by the chosen user.");
						System.out.println("Insert the way you would like to search for the user:\n"
								+ "Email - if you want to find the user by email,\n"
								+ "Id - if you want to find the user by id,\n"
								+ "Name - if you want to find the user by name and surname.");
						String input2 = scan.nextLine();
						while (!input2.equalsIgnoreCase("email") && !input2.equalsIgnoreCase("id") && 
								   !input2.equalsIgnoreCase("name")) {
								System.out.println("I don't understand. Please insert the right command: Email/Id/Name");
								input2 = scan.nextLine();
							}
						int userId;
						if (input2.equalsIgnoreCase("email")) {
							System.out.println("Insert the email of the user you want to see reservations of: ");
							String email = scan.nextLine();
							userId = UserDao.readByEmail(email).getId();
						} else if (input2.equalsIgnoreCase("name")) {
							System.out.println("Insert the name of the user you want to see the reservations of (in format: name surname): ");
							String name = scan.nextLine();
							userId = UserDao.readByEmail(name).getId();
						} else {
							System.out.println("Please insert the id of the user You want to see the reservations of: ");
							userId = Integer.parseInt(scan.nextLine());
						}
						User userNew = UserDao.readById(userId);
						if (userNew.getId() == 0) {
							System.out.println("There is no such user. ");
						} else {
							Reservation[] allByUserId = ReservationDao.readAllByUserId(userId);
							if (allByUserId.length == 0) {
								System.out.println("The user has no reservation.");
							} else {
								System.out.println("All reservations made by " + userNew.getName() + " are :");
								for (int i=0; i<allByUserId.length; i++) {
									ReservationDao.showReservation(ReservationDao.readAll()[i]);
									System.out.println("");
								}
							}
						}	
				// ASSIGN STATUS:
						
					} else if (input1.equalsIgnoreCase("status")) {
						System.out.println("Please insert the id of the reservation you want to change the status of ");
						int resIdtoChange = Integer.parseInt(scan.nextLine());
						Reservation reservationNew = ReservationDao.readById(resIdtoChange);
				// extra option for me - asks if the user is sure to delete all the data - with loop - wait for the answer yes/no
				//only after given the confirmation, deletes the user data with delete method
						if (reservationNew.getId()==0) {
							System.out.println("No such reservation id.");
						} else {
								
							System.out.println("Are you sure change the status of this reservation?");
							ReservationDao.showReservation(reservationNew);
							System.out.println("\nWrite yes to delete / no to abort: ");
							String decision = scan.nextLine();
							while (!decision.equalsIgnoreCase("yes") && !decision.equalsIgnoreCase("no")) {
								System.out.println("\nWrite yes to delete / no to abort: ");
								decision = scan.nextLine();
							}
							if (decision.equalsIgnoreCase("yes")) {
								System.out.println("Please choose the new status(accepted/paid/cancelled): ");
								String status = scan.nextLine();
								while (!status.equalsIgnoreCase("accepted") && !status.equalsIgnoreCase("paid") && 
									   !status.equalsIgnoreCase("cancelled")) {
									System.out.println("I don't understand. Please insert the right command: add/edit/delete/show/status/quit");
									status = scan.nextLine();
								}
								ReservationDao.changeReservationStatus(reservationNew, status);
								System.out.println("You have updated the chosen reservation status.");
							} else {
								System.out.println("The status of the reservation has not been changed.");
							}
						}
						
				//DELETE	
						
					} else if (input1.equalsIgnoreCase("delete")) {
						
						System.out.println("Please insert the id of the Reservation You want to delete: ");
						int resIdtoDelete = Integer.parseInt(scan.nextLine());
						Reservation reservationNew = ReservationDao.readById(resIdtoDelete);
						if (reservationNew.getId()==0) {
							System.out.println("No such reservation id.");
						} else {
				// extra option for me - asks if the user is sure to delete all the data - with loop - wait for the answer yes/no
				//only after given the confirmation, deletes the user data with delete method
							System.out.println("Are you sure to delete all the data from this reservation?");
							ReservationDao.showReservation(reservationNew);
							System.out.println("\nWrite yes to delete / no to abort: ");
							String decision = scan.nextLine();
							while (!decision.equalsIgnoreCase("yes") && !decision.equalsIgnoreCase("no")) {
								System.out.println("\nWrite yes to delete / no to abort: ");
								decision = scan.nextLine();
							}
							if (decision.equalsIgnoreCase("yes")) {
								ReservationDao.delete(resIdtoDelete);
								System.out.println("You have deleted the chosen reservation.");
							} else {
								System.out.println("The reservation has not been deleted.");
							}
						}
					
				
		
				
					
			// try catch - not to break the program if the input is incorrect --> it sends the user to another command	
					}	
				} catch (NumberFormatException e) {
					System.out.println("Incorrect input type. Please try again with correct data.");
				} catch (Exception e){
					System.out.println("Error: " + e.getMessage());
				} 
				
				
				// After the chosen method, program again shows all the users data 
				System.out.println("Here are all the current reservations: ");
				allReservations = ReservationDao.readAll();
				for (int i=0; i<allReservations.length; i++) {
					ReservationDao.showReservation(ReservationDao.readAll()[i]);
					System.out.println("");
				}
			// ask if there is anything else the user would like to do - if quit - the program stops
				System.out.println("Would you like to do anything else? Please choose right command: add/edit/delete/show/status/quit: ");
				input1 = scan.nextLine();
				
			
			}
			
			// confirmation that the program has stopped.
			System.out.println("You have quit the reservation manager. See you later!");
			
		
		} catch (Exception e) {
			System.out.println("Error! Please try again later! "+ e.getMessage());
		}
	
	}	
	
}
