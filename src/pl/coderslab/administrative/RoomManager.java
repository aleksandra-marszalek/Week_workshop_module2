package pl.coderslab.administrative;

import java.sql.Connection;
import java.util.Scanner;

import pl.coderslab.DbUtil;
import pl.coderslab.dao.HotelDao;
import pl.coderslab.dao.RoomDao;
import pl.coderslab.entity.Hotel;
import pl.coderslab.entity.Room;

public class RoomManager {
	
	public static void roomManager () {
	try (Connection connection = DbUtil.getConnection()) {
		Scanner scan = new Scanner(System.in);
		// welcome to the program, show all rooms using simple for loop
		System.out.println("Please insert the id of the hotel, which you would like to manage the rooms of: ");
		int hotelId = Integer.parseInt(scan.nextLine());
		Room[] allRooms = RoomDao.readAllByHotelId(hotelId);
		System.out.println("Here are all rooms of this hotel: ");
		for (int i=0; i<allRooms.length; i++) {
			RoomDao.showRoom(RoomDao.readAll()[i]);
			System.out.println("");
			}
		// show the options
			System.out.println("Please choose right command:\n"
					+ "add - to add a new room,\n"
					+ "edit - to edit existing room,\n"
					+ "delete - to remove existing hotel room from a database,\n"
					+ "quit - to quit the hotel room management system.");
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
						Room roomNew = new Room();
						System.out.println("Please insert new Room Inside nr: ");
						int insideNr = Integer.parseInt(scan.nextLine());
						roomNew.setInsideNr(insideNr);
						System.out.println("Please insert new description: ");
						String description = scan.nextLine();
						roomNew.setDescription(description);
						System.out.println("Please insert new capacity: ");
						int capacity = Integer.parseInt(scan.nextLine());
						roomNew.setCapacity(capacity);
						System.out.println("Please insert new price: ");
						Double price = Double.parseDouble(scan.nextLine());
						roomNew.setPrice(price);
						roomNew.setHotelId(hotelId);
						RoomDao.create(roomNew);	
					
			// if the user's	 input is edit - the program is asking for the id of the user to edit, then asks for new data and uses edit
					} else if (input1.equalsIgnoreCase("edit")) {
						System.out.println("Please insert the id of the Room You want to edit: ");
						int roomIdtoEdit = Integer.parseInt(scan.nextLine());
						Room roomNew = RoomDao.readById(roomIdtoEdit);
						System.out.println("Please insert new Room Inside nr: ");
						int insideNr = Integer.parseInt(scan.nextLine());
						roomNew.setInsideNr(insideNr);
						System.out.println("Please insert new description: ");
						String description = scan.nextLine();
						roomNew.setDescription(description);
						System.out.println("Please insert new capacity: ");
						int capacity = Integer.parseInt(scan.nextLine());
						roomNew.setCapacity(capacity);
						System.out.println("Please new price: ");
						Double price = Double.parseDouble(scan.nextLine());
						roomNew.setPrice(price);
						roomNew.setHotelId(hotelId);
						RoomDao.create(roomNew);	
				
				//if the users input is delete - asks for the id nr and deletes the chosen room object		
					} else if (input1.equalsIgnoreCase("delete")) {
						System.out.println("Please insert the id of the Room You want to delete: ");
						int roomIdtoDelete = Integer.parseInt(scan.nextLine());
						Room roomNew = RoomDao.readById(roomIdtoDelete);
				// extra option for me - asks if the user is sure to delete all the data - with loop - wait for the answer yes/no
				//only after given the confirmation, deletes the user data with delete method
						System.out.println("Are you sure to delete all the data from this room?");
						RoomDao.showRoom(roomNew);
						System.out.println("\nWrite yes to delete / no to abort: ");
						String decision = scan.nextLine();
						while (!decision.equalsIgnoreCase("yes") && !decision.equalsIgnoreCase("no")) {
							System.out.println("\nWrite yes to delete / no to abort: ");
							decision = scan.nextLine();
						}
						if (decision.equalsIgnoreCase("yes")) {
							RoomDao.delete(roomNew.getId());
							System.out.println("You have deleted the chosen room.");
						} else {
							System.out.println("The room has not been deleted.");
						}
						
					
		// After the chosen method, program again shows all the users data 		
		
					
					
			// try catch - not to break the program if the input is incorrect --> it sends the user to another command	
					}
					System.out.println("Here are all the current Rooms: ");
					allRooms = RoomDao.readAllByHotelId(hotelId);
					for (int i=0; i<allRooms.length; i++) {
						RoomDao.showRoom(RoomDao.readAll()[i]);
						System.out.println("");
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
			System.out.println("You have quit the room manager. See you later!");
			
		
		} catch (Exception e) {
			System.out.println("Error! Please try again later! "+ e.getMessage());
		}
	
	}	


}
