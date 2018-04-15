package pl.coderslab.administrative;

import java.sql.Connection;
import java.util.Scanner;

import pl.coderslab.DbUtil;
import pl.coderslab.dao.HotelDao;
import pl.coderslab.entity.Hotel;

public class AdminManager {


	public static void adminManager () {
		
		try (Connection connection = DbUtil.getConnection()) {
			
		// welcome to the program
			System.out.println("Welcome to the Administrative Panel!");
			System.out.println("Please choose right command:\n"
					+ "H - to enter hotels management system,\n"
					+ "U - to enter users management system,\n"
					+ "G - to enter user groups management system,\n"
					+ "R - to enter reservations management system,\n"
					+ "Q - to quit program.");
			Scanner scan = new Scanner(System.in);
			String input1 = scan.nextLine();
			while (!input1.equalsIgnoreCase("h") && !input1.equalsIgnoreCase("u") && 
				   !input1.equalsIgnoreCase("g") && !input1.equalsIgnoreCase("r") && !input1.equalsIgnoreCase("q")) {
				System.out.println("I don't understand. Please insert the right command: \n"
						+ "H - for hotels management,\n"
						+ "U - for users management,\n"
						+ "G - for user groups management,\n"
						+ "R - for reservations management,\n"
						+ "Q - to quit program.");
				input1 = scan.nextLine();
			}
		// putting everything in the while loop which ends when the input equals q
			while (!input1.equalsIgnoreCase("q")) {
				try {
		
					if (input1.equalsIgnoreCase("h")) {
						HotelManager.hotelManager();
						
					
					}  else if (input1.equalsIgnoreCase("u")) {
						UsersManager.userManager();
						
						
					} else if (input1.equalsIgnoreCase("g")) {
						UsersGroupManager.userGroupManager();
						
						
					} else if (input1.equalsIgnoreCase("r")) {
						ReservationManager.reservationManager();	
		
					}
					System.out.println("Would you like to do anything else? Please insert right command: \n"
							+ "H - for hotels management,\n"
							+ "U - for users management,\n"
							+ "G - for user groups management,\n"
							+ "R - for reservations management,\n"
							+ "Q - to quit program.");
					input1 = scan.nextLine();
					
			// try catch - not to break the program if the input is incorrect --> it sends the user to another command	
					
				} catch (NumberFormatException e) {
					System.out.println("Incorrect input type. Please try again with correct data.");
				} catch (Exception e){
					System.out.println("Error: " + e.getMessage());
				} 
				
			// ask if there is anything else the user would like to do - if quit - the program stops
				
				
			
			}
			
			
			// confirmation that the program has stopped.
			System.out.println("You have quit the administrative panel. See you later!");
			
		
		} catch (Exception e) {
			System.out.println("Error! Please try again later! "+ e.getMessage());
		}
	
	}	
}
