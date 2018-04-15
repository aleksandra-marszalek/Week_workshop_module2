package pl.coderslab.administrative;

import java.sql.Connection;
import java.util.Scanner;

import pl.coderslab.DbUtil;
import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserGroup;

public class UsersGroupManager {

public static void userGroupManager () {
		
		try (Connection connection = DbUtil.getConnection()) {
			
		// welcome to the program, show all excercises using simple for loop
			System.out.println("Welcome to the User Groups Management System. Here are all the current User Groups: ");
			UserGroup[] allUserGroups = UserGroupDao.readAll();
				for (int i=0; i<allUserGroups.length; i++) {
				UserGroupDao.showUserGroup(UserGroupDao.readAll()[i]);
				System.out.println("");
				}
		// show the options
			System.out.println("Please choose right command:\n"
					+ "add - to add a new group,\n"
					+ "edit - to edit existing group,\n"
					+ "delete - to remove existing group from a database,\n"
					+ "show - to show all the users from the existing group,\n"
					+ "quit - to quit the user groups management system.");
			Scanner scan = new Scanner(System.in);
			String input1 = scan.nextLine();
			while (!input1.equalsIgnoreCase("add") && !input1.equalsIgnoreCase("edit") && 
				   !input1.equalsIgnoreCase("delete") && !input1.equalsIgnoreCase("quit") && !input1.equalsIgnoreCase("show")) {
				System.out.println("I don't understand. Please insert the right command: add/edit/delete/show/quit");
				input1 = scan.nextLine();
			}
		// putting everything in the while loop which ends when the input equals quit
			while (!input1.equalsIgnoreCase("quit")) {
				try {
		
		
		//ADD			
					
					if (input1.equalsIgnoreCase("add")) {
						UserGroup groupNew = new UserGroup();
						System.out.println("Please insert new group Name: ");
						String name = scan.nextLine();
						groupNew.setName(name);
						UserGroupDao.create(groupNew);	
						
						
			// EDIT
						
					} else if (input1.equalsIgnoreCase("edit")) {
						System.out.println("Please insert the id of the Group You want to edit: ");
						int groupIdtoEdit = Integer.parseInt(scan.nextLine());
						UserGroup groupNew = UserGroupDao.readById(groupIdtoEdit);
						if (groupNew.getId()==0) {
							System.out.println("No such group id.");
						} else {
							System.out.println("Please insert new Group Name: ");
							String name = scan.nextLine();
							groupNew.setName(name);
							UserGroupDao.update(groupNew);	
						}
						
				//SHOW	
						
					} else if (input1.equalsIgnoreCase("show")) {
						System.out.println("Please insert the id of the Group You want to see the users of: ");
						int groupId = Integer.parseInt(scan.nextLine());
						UserGroup groupNew = UserGroupDao.readById(groupId);
						if (groupNew.getId()==0) {
							System.out.println("No such group id.");
						} else {
							System.out.println("All users in " + groupNew.getName() + " are :");
							
							User[] allUsersByGroupId = UserDao.readAllByGroupId(groupId);
							for (int i=0; i<allUsersByGroupId.length; i++) {
								UserDao.showUser(UserDao.readAll()[i]);
								System.out.println("");
							}
						}
						
						
				//DELETE	
						
					} else if (input1.equalsIgnoreCase("delete")) {
						
						System.out.println("Please insert the id of the Group You want to delete: ");
						int groupIdtoDelete = Integer.parseInt(scan.nextLine());
						UserGroup groupNew = UserGroupDao.readById(groupIdtoDelete);
						if (groupNew.getId()==0) {
							System.out.println("No such group id.");
						} else {
				// extra option for me - asks if the user is sure to delete all the data - with loop - wait for the answer yes/no
				//only after given the confirmation, deletes the user data with delete method
							System.out.println("Are you sure to delete all the data from this group?");
							UserGroupDao.showUserGroup(groupNew);
							System.out.println("\nWrite yes to delete / no to abort: ");
							String decision = scan.nextLine();
							while (!decision.equalsIgnoreCase("yes") && !decision.equalsIgnoreCase("no")) {
								System.out.println("\nWrite yes to delete / no to abort: ");
								decision = scan.nextLine();
							}
							if (decision.equalsIgnoreCase("yes")) {
								UserGroupDao.delete(groupIdtoDelete);
								System.out.println("You have deleted the chosen group.");
							} else {
								System.out.println("The group has not been deleted.");
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
				System.out.println("Here are all the current groups: ");
				allUserGroups = UserGroupDao.readAll();
				for (int i=0; i<allUserGroups.length; i++) {
					UserGroupDao.showUserGroup(UserGroupDao.readAll()[i]);
					System.out.println("");
				}
			// ask if there is anything else the user would like to do - if quit - the program stops
				System.out.println("Would you like to do anything else? Please choose right command: add/edit/delete/show/quit: ");
				input1 = scan.nextLine();
				
			
			}
			
			// confirmation that the program has stopped.
			System.out.println("You have quit the user groups manager. See you later!");
			
		
		} catch (Exception e) {
			System.out.println("Error! Please try again later! "+ e.getMessage());
		}
	
	}	
	
}
