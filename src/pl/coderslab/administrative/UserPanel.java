package pl.coderslab.administrative;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.Scanner;

import pl.coderslab.dao.ReservationDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Reservation;
import pl.coderslab.entity.User;

public class UserPanel {
	
	public static void userView () {

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshop_2?useSSL=false",
	            "root", "coderslab")) {
			
			System.out.println("Welcome to the User Program!");
			System.out.println("Please insert Your User Id: ");
			Scanner scan = new Scanner(System.in);
			while (!scan.hasNextInt()) {
				System.out.println("Please insert right User Id!");
				scan.next();
			}
			int userId = scan.nextInt();
			User[] usersIdList = UserDao.readAll();
			boolean isUserValid = false;
			for (int i=0; i<usersIdList.length; i++) {
				if (usersIdList[i].getId() == userId) {
					System.out.println("Welcome, " + usersIdList[i].getName());
					isUserValid = true;
				}
			}
	//  if there is no user in database with this id - it will switch the program off
			if (isUserValid) {
			
				System.out.println("Please choose right command: \n"
						+ "view - to view all your reservations\n"
						+ "add - to make new reservation\n"
						+ "update - to update existing reservation\n"
						+ "delete  - to delete existing reservation\n"
						+ "quit - to quit User Panel");
				
				scan.nextLine();
				String input1 = scan.nextLine();
				
				while (!input1.equalsIgnoreCase("add") && !input1.equalsIgnoreCase("view") && 
					   !input1.equalsIgnoreCase("quit") && !input1.equalsIgnoreCase("update")
					   && !input1.equalsIgnoreCase("delete")) {
					System.out.println("I don't understand. Please insert the right command: view/add/update/delete/quit");
					input1 = scan.nextLine();
				}
				
				
			// putting everything in the while loop which ends when the input equals quit
				while (!input1.equalsIgnoreCase("quit")) {
					try {
						
			
		//ADD				
					if (input1.equalsIgnoreCase("add")) {
						Reservation reservationNew = new Reservation();
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
						
						
						
			//VIEW			
					} else if (input1.equalsIgnoreCase("view")) {
						Reservation[] allReservations = ReservationDao.readAllByUserId(userId);
						if (allReservations.length == 0) {
							System.out.println("There are no reservations.");				
						} else {
							System.out.println("Here are all the reservations: ");
							for (int i=0; i<allReservations.length; i++) {
								ReservationDao.showReservation(allReservations[i]);
								System.out.println("");
							}
							
						}
						
						
					
						
		// UPDATE
					} else if (input1.equalsIgnoreCase("update")) {
						Reservation[] allReservations = ReservationDao.readAllByUserId(userId);
						if (allReservations.length == 0) {
							System.out.println("There are no reservations to update.");
						} else {			
							System.out.println("Please insert the id of the reservation You want to edit: ");
							int resIdtoEdit = Integer.parseInt(scan.nextLine());
							Reservation reservationNew = ReservationDao.readById(resIdtoEdit);
							if (reservationNew.getId() == 0) {
								System.out.println("There is no such reservation.");
							} else {
								reservationNew.setCreated(reservationNew.getCreated());
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
								ReservationDao.update(reservationNew);	
							}
						}
						
						
		// DELETE
					} else {
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
					}
						
					
					} catch (NumberFormatException e) {
						System.out.println("Incorrect input type. Please try again with correct data.");
					} catch (Exception e){
						System.out.println("Error: " + e.getMessage());
					} 
					
				// ask if there is anything else the user would like to do - if quit - the program stops
					System.out.println("Would you like to do anything else? Please choose right command: view/add/update/delete/quit: ");
					input1 = scan.nextLine();
					
				
				}
				
				// confirmation that the program has stopped.
				System.out.println("You have quit the User Panel. See you later!");
			} else {
				System.out.println("There is no user with such Id. Please try again later.");
			}
		
		} catch (Exception e) {
			System.out.println("Error! Please try again later! "+ e.getMessage());
		}
	}
	
}
