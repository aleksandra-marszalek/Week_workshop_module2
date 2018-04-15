package pl.coderslab;

import java.util.Scanner;

import pl.coderslab.administrative.AdminManager;
import pl.coderslab.administrative.UserPanel;

public class BookingProgram {
	static public void bookingProgram() {
		System.out.println("Welcome to the booking program. Please insert: \n"
				+ "A - if you want to continue as an admin,\n"
				+ "U - if you want to continue as a regular user.\n"
				+ "Q - if you want to quit.");
		Scanner scan = new Scanner(System.in);
		String input1 = scan.nextLine();
		while (!input1.equalsIgnoreCase("a") && !input1.equalsIgnoreCase("u") && 
			   !input1.equalsIgnoreCase("q")) {
			System.out.println("I don't understand. Please insert the right command: A - admin / U - user / Q - quit: ");
			input1 = scan.nextLine();
		}
		while (!input1.equalsIgnoreCase("q")) {
			try {
				
				if (input1.equalsIgnoreCase("a")) {
					AdminManager.adminManager();
				} else if (input1.equalsIgnoreCase("u")){
					UserPanel.userView();
				}
				
				System.out.println("Would you like to do anything else? \n"
						+ "Please insert: \n"
						+ "A - if you want to continue as an admin,\n"
						+ "U - if you want to continue as a regular user.\n"
						+ "Q - if you want to quit.");
				input1 = scan.nextLine();
				
			
				
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());			}
			}
		}
}
