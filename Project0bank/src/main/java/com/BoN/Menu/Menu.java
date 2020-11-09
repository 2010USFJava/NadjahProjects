package com.BoN.Menu;

import java.util.Scanner;

import com.BoN.Persist.BankLogger;
import com.BoN.Persist.Filer;
import com.BoN.Persist.Lists;
import com.BoN.Users.Account;
import com.BoN.Users.Customer;
import com.BoN.Users.UserType;
import com.BoN.Users.Users;

public class Menu {

	static Scanner scan = new Scanner(System.in);
	
	public static void openMenu() {
		System.out.println("Welcome to Bank of Nadjica Banking App");
		System.out.println("\t (1) Log in");
		System.out.println("\t (2)Create new Account");
		System.out.println("\t (3)Exit");
		int menuChoice = scan.nextInt();
		switch (menuChoice) {
		case 1: {
			scan.nextLine();
			System.out.println("Please enter your username");
			String user = scan.nextLine();
			System.out.println("Please enter your password");
			String pass = scan.nextLine();
			Users thisUser = Lists.loginValidation(user,pass);
				if(thisUser == null) {
					System.out.println("Invalid Input");
					System.out.println("Please enter your username");
					String user1 = scan.nextLine();
					System.out.println("Please enter your password");
					String pass1 = scan.nextLine();
					thisUser = Lists.loginValidation(user1,pass1);
					openMenu();
				}else {
					
						UserType menuType = thisUser.getUserType();
					
					callMenu(menuType);
				}
			break;
		}
		case 2: {
			createCustomer();
			break;
		}
		default: {
		  System.out.println("Thank you for choosing Bank of Najica for your all your banking needs!");
		  break;
		}
		
		}
	}
	
	public static void createCustomer() {
		scan.nextLine();
		System.out.println("Please enter the first and last name of the account holder: ");
		String name = scan.nextLine();
		System.out.println("Enter a Username: ");
		String uName = scan.nextLine();
		System.out.println("Enter a password for this user:");
		String pass = scan.nextLine();
		Account newAcct = null;
		Customer newCustomer = new Customer(false, newAcct);
		newCustomer.setUsername(uName);
		newCustomer.setPassword(pass);
		Lists.uAndP.put(uName, pass);
		
		BankLogger.LogIt("info", "New Log In 1"
				+ "registered: \n Username: " + uName + " Password: " + pass);
		System.out.println("Your user was successfully created! Apply for an account? y/n");
		String choice = scan.nextLine();
		if(choice.equalsIgnoreCase("y")) {
			 newAcct = new Account(false, 0, newCustomer);
		}
		System.out.println("Is this a joint account? y/n");
		String choiceJoint = scan.nextLine();
		if(choiceJoint.equalsIgnoreCase("y")) {
			System.out.println("Enter the userid of the user you would like to add to this account.");
			int userIds = scan.nextInt();
			scan.nextLine();//break
			System.out.println("Enter another id? y/n");
			String moreID = scan.nextLine();
			while (moreID.equalsIgnoreCase("y")) {
				userIds = scan.nextInt();
				System.out.println("Enter another id? y/n");
				scan.nextLine();
				moreID = scan.nextLine();
			}
			
		}
		System.out.println("Account creation successful! Returning to main menu...");
		openMenu();
	}
	
	public static void callMenu(UserType uType) {
		switch(uType){
		case admin :{
			adminMenu();
		}
		
		case employee: {
			employeeMenu();
		}
		default:{
			//TODO customer view own deets
			customerMenu();
		}
		}
		
	}
	public static void viewMenu(String findUser) {
		scan.nextLine();
		Users showUser = Lists.getUserByUsername(findUser);
		System.out.println(showUser);
		System.out.println("Would you like to do another operation?");
		String more = scan.nextLine();
		if(more.equalsIgnoreCase("y")) {
			adminMenu();
		}else {
			Filer.writeUserFile(Lists.uMap);
			System.out.println("Complete. Returning to Main Menu");
		}
	}
	
	public static void  editMenu() {
		scan.nextLine();
		System.out.println("Which account would you like to change?");
		String userPick = scan.nextLine();
		Users editing = Lists.getUserByUsername(userPick);
		System.out.println(editing);
		System.out.println("What would you like to edit?");
		
		System.out.println("\t (3) name");
		System.out.println("\t (4) userID");
		System.out.println("\t (5) User Type");
		System.out.println("\t (6) Application Status");
		System.out.println("\t (7) Account Details");
		System.out.println("\t (8) Exit");
		//TODO be able to delete accounts
		int attribute = scan.nextInt();
		Filer.writeUserFile(Lists.uMap);
		switch(attribute) {
		case 6 : {
			scan.nextLine();
			//TODO aoutmatic user Typer Detection
			if(editing.getUserType().equals(UserType.customer)) {
					Customer cEdit = (Customer) editing;
				System.out.println(cEdit.isAppStatus());
				System.out.println("Set user's status:");
				System.out.println("\t (1) approved");
				System.out.println("\t (2) denied");
				int setS = scan.nextInt();
				switch(setS) {
				case 1:{cEdit.setAppStatus(true); System.out.println("Account approved.");}
				case 2:{cEdit.setAppStatus(false); System.out.println("Account denied");}
				default:{System.out.println("No changes made, returning to menu.");
				editMenu();
				}
				}
				
			}else
				System.out.println("This User Type is not associated with accounts");
			 	editMenu();
		}
		case 3 : {
			scan.nextLine();
			System.out.println("Current user Name field:"+ editing.getName());
			System.out.println("Enter the new Name:");
			String newEntry = scan.nextLine();
			editing.setName(newEntry);
			Filer.writeUserFile(Lists.uMap);
		}
		case 8 : {
			System.out.println("Complete. Returning to Main Menu");
			openMenu();
		}
		default:{
			System.out.println("Would you like to do another operation?");
			String more = scan.nextLine();
			if(more.equalsIgnoreCase("y")) {
				editMenu();
			}else {
				Filer.writeUserFile(Lists.uMap);
				System.out.println("Complete. Returning to Main Menu");
				adminMenu();
			}
		}
		}
	}
	public static void adminMenu(){
		System.out.println("Admin Menu");
		System.out.println("\t (1) Edit User Accounts");
		System.out.println("\t (2) View User Accounts");
		System.out.println("\t (3)Exit");
		int menuChoice1 = scan.nextInt();
		switch(menuChoice1) {
		case 1:{
			editMenu();
		}
		case 2:{
			scan.nextLine();
			System.out.println("Which user would you like to view?");
			String findUser = scan.nextLine();
			viewMenu(findUser);
		}
		default:{
			scan.nextLine();
			System.out.println("Would you like to do more operations? y/n");
			String more = scan.nextLine();
			if(more.equalsIgnoreCase("y")) {
				adminMenu();
			}else {
				Filer.writeUserFile(Lists.uMap);
				System.out.println("Complete. Returning to Main Menu");
			}
		}
		
		}
	}

	public static void customerMenu() {
		//TODO see acct, withdraw, deposit transfer

	}

	public static void employeeMenu() {

	}
}
