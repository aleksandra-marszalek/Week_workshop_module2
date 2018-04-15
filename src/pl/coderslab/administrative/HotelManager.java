package pl.coderslab.administrative;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import pl.coderslab.DbUtil;
import pl.coderslab.dao.HotelDao;
import pl.coderslab.dao.RoomDao;
import pl.coderslab.entity.Hotel;
import pl.coderslab.entity.Room;

public class HotelManager {

	
	public static void main(String[] args) {
		
			
		}
		
		public static void hotelManager () {
			
			try (Connection connection = DbUtil.getConnection()) {
				
			// welcome to the program, show all excercises using simple for loop
				System.out.println("Welcome to the Hotel Management System. Here are all the current Hotels: ");
				Hotel[] allHotels = HotelDao.readAll();
					for (int i=0; i<allHotels.length; i++) {
					HotelDao.showHotels(HotelDao.readAll()[i]);
					System.out.println("");
					}
			// show the options
				System.out.println("Please choose right command:\n"
						+ "add - to add a new hotel,\n"
						+ "edit - to edit existing hotel,\n"
						+ "delete - to remove existing hotel from a database,\n"
						+ "rooms - to move to the Room managament system of the chosen hotel,\n"
						+ "quit - to quit the hotel management system.");
				Scanner scan = new Scanner(System.in);
				String input1 = scan.nextLine();
				while (!input1.equalsIgnoreCase("add") && !input1.equalsIgnoreCase("edit") && 
					   !input1.equalsIgnoreCase("delete") && !input1.equalsIgnoreCase("quit") && !input1.equalsIgnoreCase("rooms")) {
					System.out.println("I don't understand. Please insert the right command: add/edit/delete/quit");
					input1 = scan.nextLine();
				}
			// putting everything in the while loop which ends when the input equals quit
				while (!input1.equalsIgnoreCase("quit")) {
					try {
			// if user's input is add - the program is asking for the data needed and uses the create method
						if (input1.equalsIgnoreCase("add")) {
							Hotel hotelNew = new Hotel();
							System.out.println("Please insert new Hotel Name: ");
							String name = scan.nextLine();
							hotelNew.setName(name);
							System.out.println("Please insert new description: ");
							String description = scan.nextLine();
							hotelNew.setDescription(description);
							System.out.println("Please insert new address: ");
							String address = scan.nextLine();
							hotelNew.setAddress(address);
							System.out.println("Please insert if the hotel accepts animals (yes/no): ");
							String acceptsAnimals = scan.nextLine();
							hotelNew.setAcceptsAnimals(acceptsAnimals);
							HotelDao.create(hotelNew);	
						}  else if (input1.equalsIgnoreCase("rooms")) {
							RoomManager.roomManager();
							
							
				// if the user's	 input is edit - the program is asking for the id of the user to edit, then asks for new data and uses edit
						} else if (input1.equalsIgnoreCase("edit")) {
							System.out.println("Please insert the id of the Hotel You want to edit: ");
							int hotelIdtoEdit = Integer.parseInt(scan.nextLine());
							Hotel hotelNew = HotelDao.readById(hotelIdtoEdit);
							System.out.println("Please insert new Hotel Name: ");
							String name = scan.nextLine();
							hotelNew.setName(name);
							System.out.println("Please insert new description: ");
							String description = scan.nextLine();
							hotelNew.setDescription(description);
							System.out.println("Please insert new address: ");
							String address = scan.nextLine();
							hotelNew.setAddress(address);
							System.out.println("Please insert if the hotel accepts animals (yes/no): ");
							String acceptsAnimals = scan.nextLine();
							hotelNew.setAcceptsAnimals(acceptsAnimals);
							HotelDao.update(hotelNew);
					
					//if the users input is delete - asks for the id nr and deletes the chosen hotel object		
						} else if (input1.equalsIgnoreCase("delete")) {
							System.out.println("Please insert the id of the Hotel You want to delete: ");
							int hotelIdToDelete = Integer.parseInt(scan.nextLine());
							Hotel hotelNew = HotelDao.readById(hotelIdToDelete);
					// extra option for me - asks if the user is sure to delete all the data - with loop - wait for the answer yes/no
					//only after given the confirmation, deletes the user data with delete method
							System.out.println("Are you sure to delete all the data from this user?");
							HotelDao.showHotels(hotelNew);
							System.out.println("\nWrite yes to delete / no to abort: ");
							String decision = scan.nextLine();
							while (!decision.equalsIgnoreCase("yes") && !decision.equalsIgnoreCase("no")) {
								System.out.println("\nWrite yes to delete / no to abort: ");
								decision = scan.nextLine();
							
							if (decision.equalsIgnoreCase("yes")) {
								HotelDao.delete(hotelNew.getId());
								System.out.println("You have deleted the chosen hotel.");
							} else {
								System.out.println("The hotel has not been deleted.");
							}
							
						}
			// After the chosen method, program again shows all the users data 		
			
					System.out.println("Here are all the current Hotels: ");
					Hotel[] allHotels2 = HotelDao.readAll();
					for (int i=0; i<allHotels2.length; i++) {
					HotelDao.showHotels(HotelDao.readAll()[i]);
					System.out.println("");
					}
						
				// try catch - not to break the program if the input is incorrect --> it sends the user to another command	
						}	
					} catch (NumberFormatException e) {
						System.out.println("Incorrect input type. Please try again with correct data.");
					} catch (Exception e){
						System.out.println("Error: " + e.getMessage());
					} 
					
				// ask if there is anything else the user would like to do - if quit - the program stops
					System.out.println("Would you like to do anything else? Please choose right command: add/edit/delete/quit: ");
					input1 = scan.nextLine();
					
				
				}
				
				// confirmation that the program has stopped.
				System.out.println("You have quit the excercise manager. See you later!");
				
			
			} catch (Exception e) {
				System.out.println("Error! Please try again later! "+ e.getMessage());
			}
		
		}	
}
