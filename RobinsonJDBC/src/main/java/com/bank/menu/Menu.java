package com.bank.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bank.beans.Users;
import com.bank.dao.UsersDao;
import com.bank.impl.UsersDaoImpl;
import com.bank.util.ConnFactory;

public class Menu {
	public static ConnFactory cf = ConnFactory.getInstance();
	public static Scanner scan = new Scanner(System.in);
	static int menuChoice;
	
	public static void mainMenu() {
		System.out.println("Welcome to the Bread Bank");
		System.out.println("\t (1) Log in");
		System.out.println("\t (2) Create new Account");
		System.out.println("\t (3) Exit");
		menuChoice = scan.nextInt();
		
		switch(menuChoice) {
			case 1: {
			loginValidation();
			System.out.println("Eventually call menus");
			break;
			}
			case 2: {
			//TODO create new User here
			//TODO give option to create new account here
			}
			default: {
			System.out.println("Thank you for Banking at the Bread Bank");
			break;
			}
		}
		
	}
	
	public static void customerMenu() {
		System.out.println("Customer Menu");
		System.out.println("\t (1) Account Summary"); //TODO call account Menu here
		System.out.println("\t (2) Apply for new Account");
		System.out.println("\t (3) Back to Main Menu");
		System.out.println("\t (4) Exit");
		menuChoice = scan.nextInt();
		//TODO add supporting switch statement
		//TODO show user details 
		//if username exists throw uniqueuser exception
		switch (menuChoice) {
		case 1:
			
			break;

		default:
			break;
		}
	}
	
	public static void accountMenu() {
		System.out.println("Account Menu");
		System.out.println("\t (1) Withdraw");
		System.out.println("\t (2) Deposit");
		System.out.println("\t (3) Close Account"); //TODO make sure account is empty
		System.out.println("\t (4) Update Account summary"); //TODO Maybe just show all accounts here?
		System.out.println("\t (5) Back to Customer Menu");
		System.out.println("\t (6) Back to Main Menu");
		menuChoice = scan.nextInt();
		//TODO add switch statements
	}
	
	public static void adminMenu() {
		System.out.println("Admin Menu");
		System.out.println("\t (1) Edit single account");
		System.out.println("\t (2) Edit all accounts");
		
		menuChoice = scan.nextInt();
		//TODO add switch statements
		System.out.println("Single Edit Menu");
		System.out.println("\t (3) Make account inactive"); //TODO Maybe just show all accounts here?
		System.out.println("\t (4) delete account");
		System.out.println("\t (4) Back to Main Menu");
		
		System.out.println("Mass Edit Menu");
		System.out.println("\t (1) view all accounts");
		System.out.println("\t (2) Edit all accounts");
		System.out.println("\t (3) Make account inactive"); //TODO Maybe just show all accounts here?
		System.out.println("\t (4) delete account");
		System.out.println("\t (4) Back to Main Menu");
		menuChoice = scan.nextInt();
	}
	
	public static boolean loginValidation() {
		UsersDaoImpl udi= new UsersDaoImpl(); 
		Connection conn = cf.getConnection();
		System.out.println("Please enter your username: ");
		scan.nextLine();
		String user = scan.nextLine();
		try {
			List<Users> uList = udi.viewAllUsers();
			
			boolean umatch = false;
			boolean pmatch = false;
			while(umatch!=true) {
				for (int i=0; i<uList.size(); i++) {
				Users thisUser = uList.get(i);
				System.out.println(thisUser);
					if(thisUser.getUsername().equals(user)) {
						umatch = true;
					
					//match pass
						System.out.println("Please enter your password: ");
						String pass = scan.nextLine();
						
						if(thisUser.getPassword().equals(pass)) {
							pmatch = true;
							break;
						}else {
							System.out.println("No match found");
							pmatch = true;
						}
						

					}else {
						System.out.println("No match found");
						umatch = true;
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return true;
		
		
		
		
	}
}
