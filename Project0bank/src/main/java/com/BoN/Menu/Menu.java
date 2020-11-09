package com.BoN.Menu;

import java.util.Scanner;

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
		System.out.println("Your user was successfully created! Apply for an account? y/n");
		String choice = scan.nextLine();
		if(choice.equalsIgnoreCase("y")) {
			 newAcct = new Account(false, 0, newCustomer, Account.genAcctNumber());
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
			//TODO admin menu 
		}
		
		case employee: {
			//TODO employee menu 
		}
		default:{
			//TODO customer menu 
		}
		}
		
	}
	public static void viewAccount() {
		
	}
	
	public static void  editAccount() {
		
	}
	public static void adminMenu(){
		
	}

	public static void customerMenu() {
		//TODO see acct, withdraw, deposit transfer

	}

	public static void employeeMenu() {

	}
}
