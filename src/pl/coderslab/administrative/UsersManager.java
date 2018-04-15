package pl.coderslab.administrative;

import java.sql.Connection;
import java.util.Scanner;

import pl.coderslab.DbUtil;
import pl.coderslab.dao.HotelDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.entity.Hotel;
import pl.coderslab.entity.User;

public class UsersManager {

	
	public static void userManager () {
		
		try (Connection connection = DbUtil.getConnection()) {
			
		// welcome to the program, show all excercises using simple for loop
			System.out.println("Welcome to the Users Management System. Here are all the current Users: ");
			User[] allUsers = UserDao.readAll();
				for (int i=0; i<allUsers.length; i++) {
				UserDao.showUser(UserDao.readAll()[i]);
				System.out.println("");
				}
		// show the options
			System.out.println("Please choose right command:\n"
					+ "add - to add a new user,\n"
					+ "edit - to edit existing user,\n"
					+ "delete - to remove existing user from a database,\n"
					+ "quit - to quit the hotel management system.");
			Scanner scan = new Scanner(System.in);
			String input1 = scan.nextLine();
			while (!input1.equalsIgnoreCase("add") && !input1.equalsIgnoreCase("edit") && 
				   !input1.equalsIgnoreCase("delete") && !input1.equalsIgnoreCase("quit")) {
				System.out.println("I don't understand. Please insert the right command: add/edit/delete/quit");
				input1 = scan.nextLine();
			}
		// putting everything in the while loop which ends when the input equals quit
			while (!input1.equalsIgnoreCase("quit")) {
				try {
		// if user's input is add - the program is asking for the data needed and uses the create method
					if (input1.equalsIgnoreCase("add")) {
						User userNew = new User();
						System.out.println("Please insert new user Name: ");
						String name = scan.nextLine();
						userNew.setName(name);
						System.out.println("Please insert new password: ");
						String password = scan.nextLine();
						userNew.setPassword(password);
						System.out.println("Please insert new email: ");
						String email = scan.nextLine();
						userNew.setEmail(email);
						UserDao.create(userNew);	
						
			// if the user's	 input is edit - the program is asking for the id of the user to edit, then asks for new data and uses edit
					} else if (input1.equalsIgnoreCase("edit")) {
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
						int userIdtoEdit;
						if (input2.equalsIgnoreCase("email")) {
							System.out.println("Insert the email of the user you want to edit: ");
							String email = scan.nextLine();
							userIdtoEdit = UserDao.readByEmail(email).getId();
						} else if (input2.equalsIgnoreCase("name")) {
							System.out.println("Insert the name of the user you want to edit (in format: name surname): ");
							String name = scan.nextLine();
							userIdtoEdit = UserDao.readByEmail(name).getId();
						} else {
							System.out.println("Please insert the id of the User You want to edit: ");
							userIdtoEdit = Integer.parseInt(scan.nextLine());
						}
						User userNew = UserDao.readById(userIdtoEdit);
						System.out.println("Please insert new user Name: ");
						String name = scan.nextLine();
						userNew.setName(name);
						System.out.println("Please insert new password: ");
						String password = scan.nextLine();
						userNew.setPassword(password);
						System.out.println("Please insert new email: ");
						String email = scan.nextLine();
						userNew.setEmail(email);
						UserDao.update(userNew);	
				
				//if the users input is delete - asks for the id nr and deletes the chosen hotel object		
					} else if (input1.equalsIgnoreCase("delete")) {
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
						int userIdtoEdit;
						if (input2.equalsIgnoreCase("email")) {
							System.out.println("Insert the email of the user you want to delete: ");
							String email = scan.nextLine();
							userIdtoEdit = UserDao.readByEmail(email).getId();
						} else if (input2.equalsIgnoreCase("name")) {
							System.out.println("Insert the name of the user you want to delete (in format: name surname): ");
							String name = scan.nextLine();
							userIdtoEdit = UserDao.readByEmail(name).getId();
						} else {
							System.out.println("Please insert the id of the User You want to delete: ");
							userIdtoEdit = Integer.parseInt(scan.nextLine());
						}
						
						User userNew = UserDao.readById(userIdtoEdit);
				// extra option for me - asks if the user is sure to delete all the data - with loop - wait for the answer yes/no
				//only after given the confirmation, deletes the user data with delete method
						System.out.println("Are you sure to delete all the data from this user?");
						UserDao.showUser(userNew);
						System.out.println("\nWrite yes to delete / no to abort: ");
						String decision = scan.nextLine();
						while (!decision.equalsIgnoreCase("yes") && !decision.equalsIgnoreCase("no")) {
							System.out.println("\nWrite yes to delete / no to abort: ");
							decision = scan.nextLine();
						
						if (decision.equalsIgnoreCase("yes")) {
							UserDao.delete(userNew.getId());
							System.out.println("You have deleted the chosen user.");
						} else {
							System.out.println("The user has not been deleted.");
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
				System.out.println("Here are all the current users: ");
				allUsers = UserDao.readAll();
				for (int i=0; i<allUsers.length; i++) {
					UserDao.showUser(UserDao.readAll()[i]);
					System.out.println("");
				}
			// ask if there is anything else the user would like to do - if quit - the program stops
				System.out.println("Would you like to do anything else? Please choose right command: add/edit/delete/quit: ");
				input1 = scan.nextLine();
				
			
			}
			
			// confirmation that the program has stopped.
			System.out.println("You have quit the users manager. See you later!");
			
		
		} catch (Exception e) {
			System.out.println("Error! Please try again later! "+ e.getMessage());
		}
	
	}	
}
