package com.bank.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import com.bank.beans.Accounts;
import com.bank.beans.Users;
import com.bank.dao.AccountsDao;
import com.bank.dao.User_AccountsDao;
import com.bank.dao.UsersDao;
import com.bank.impl.AccountsDaoImpl;
import com.bank.impl.User_AccountsDaoImpl;
import com.bank.impl.UsersDaoImpl;
import com.bank.util.ConnFactory;
import com.bank.util.LogThis;

public class Menu {
	public static ConnFactory cf = ConnFactory.getInstance();
	public static Scanner scan = new Scanner(System.in);
	static int menuChoice;
	
	public static void mainMenu() {
		AccountsDao newA = new AccountsDaoImpl(); 
		User_AccountsDao newUA = new User_AccountsDaoImpl();
		UsersDao newU = new UsersDaoImpl();
		System.out.println("Welcome to the Bread Bank");
		System.out.println("\t (1) Log in");
		System.out.println("\t (2) Create new Account");
		System.out.println("\t (3) Exit");
		menuChoice = scan.nextInt();
		
		switch(menuChoice) {
			case 1: {
			Users logIn = loginValidation();
			
			if(logIn == null) {
				mainMenu();
			}
			else{
			 if(logIn.isAdmin()) {
				 adminMenu();
			 }else {
				 int id = logIn.getUserId();
				 LogThis.LogIt("info", "User " + id + " successfully logged in");
				 customerMenu(logIn);
				 
			 }
			}
			
			break;
			}
			case 2: {
				Users a = makeNewUser();
				System.out.println("Success! Returning to main menu");
				mainMenu();
				break;
			//TODO give option to create new account here
			}
			default: {
			System.out.println("Thank you for Banking at the Bread Bank");
			break;
			}
		}
		
	}
	
	public static void customerMenu(Users a) {
		AccountsDao newA = new AccountsDaoImpl(); 
		User_AccountsDao newUA = new User_AccountsDaoImpl();
		System.out.println("\nCustomer Menu");
		System.out.println("\t (1) Show User Details");
		System.out.println("\t (2) Show All Accounts");//TODO call account Menu here
		System.out.println("\t (3) Apply for new Account");
		System.out.println("\t (4) Deposit into existing accounts");
		System.out.println("\t (5) Withdraw existing accounts");
		System.out.println("\t (6) Delete Account");
		System.out.println("\t (7) Back to Main Menu");
		System.out.println("\t (8) Exit");
		menuChoice = scan.nextInt();
		switch (menuChoice) {
			case 1: {
			 System.out.println(a.toString());
			 customerMenu(a);
			break;
			}case 2:{
				List<Accounts> l =null;
				try {
					 l = newUA.getAccountByUserId(a.getUserId());
					
				} catch (SQLException e) {
					System.out.println("No accounts associated. Create new account by choosing that option in the customer menu");
					e.printStackTrace();
					customerMenu(a);
					
				}
				
				System.out.println(l);
				customerMenu(a);
				break;
			}case 3:{
				scan.nextLine();
				Accounts a1 = new Accounts(0, null, 0.00, false);
				try {
					int newKey = newA.newAccount(a1);
					newUA.newRelation(a.getUserId(), newKey);
					System.out.println("Success! New Account created ");
					System.out.println("Would you like to add another account holder to this account?");
					String nc = scan.nextLine();
					if(nc.equalsIgnoreCase("y")) {
						System.out.println("Enter the id for the user you would like to add");
						int nu =scan.nextInt();
						newUA.newRelation(nu, newKey);
						System.out.println("User added!");
					}
				} catch (SQLException e) {
					System.out.println("Oh no! An Error Occured. Try Again.");
					e.printStackTrace();
					customerMenu(a);
					
				}
				
				customerMenu(a);
				break;
				//TODO log this
			}case 4:{
				scan.nextLine();
				try {
					System.out.println(newUA.getAccountByUserId(a.getUserId()));
					
				} catch (SQLException e) {
					System.out.println("No accounts associated. Create new account by choosing that option in the customer menu");
					customerMenu(a);
				}
				System.out.println("Which Account would you like to deposit into ? Enter the corresponding Id:");
				int thisId =scan.nextInt();
				System.out.println("How much would you like to deposit ?");
				double amt = scan.nextDouble();
				Accounts t;
				try {
					t = newA.getAccountById(thisId);
					if(amt>=0) {
					newA.deposit(t, amt);
					System.out.println("Success! You deposited " + amt + " into account with id " + t.getAccountId());
					//TODO log this
					customerMenu(a);
					}
					else {
						System.out.println("Cannot deposit negative ammounts");
					}
				} catch (SQLException e) {
					System.out.println("Not Valid. Please try again.");
					customerMenu(a);
					
				}
				
				break;
			}case 5:{
				scan.nextLine();
				try {
					System.out.println(newUA.getAccountByUserId(a.getUserId()));
					
				} catch (SQLException e) {
					System.out.println("No accounts associated. Create new account by choosing that option in the customer menu");
					customerMenu(a);
				}
				System.out.println("Which Account would you like to withdraw from");
				int thisId =scan.nextInt();
				System.out.println("How much would you like to withdraw ?");
				double amt = scan.nextDouble();
				Accounts t;
				try {
					t = newA.getAccountById(thisId);
					newA.amountValidation(t, amt);
					newA.withdraw(t, amt);
					System.out.println("Success! You withdrew " + amt + " from account with id " + t.getAccountId());
					//TODO log this
				} catch (SQLException e) {
					System.out.println("Not Valid. Please try again.");
					customerMenu(a);
					
				}
				break;
			}case 6:{
				scan.nextLine();
				List<Accounts> l =null;
				try {
					 l = newUA.getAccountByUserId(a.getUserId());
					
				} catch (SQLException e) {
					System.out.println("No accounts associated. Create new account by choosing that option in the customer menu");
					e.printStackTrace();
					customerMenu(a);
					
				}
				
				System.out.println(l);
				
				System.out.println("Which account would you like to delete? Enter the id");
				int nu=scan.nextInt();
				try {
					newA.deleteAccount(newA.getAccountById(nu));
				} catch (SQLException e) {
					System.out.println("Not Valid. Please try again.");
					e.printStackTrace();
				}
				customerMenu(a);
				break;
			}case 7:{
				System.out.println("Successfully Logged Out");
				mainMenu();
				break;
				
			}default:
				System.out.println("Successfully Logged Out");
				break;
		}
	}
	
	
	public static void adminMenu() {
		AccountsDao newA = new AccountsDaoImpl(); 
		User_AccountsDao newUA = new User_AccountsDaoImpl();
		UsersDao newU = new UsersDaoImpl();
		System.out.println("Admin Menu");
		System.out.println("\t (1) View all accounts");
		System.out.println("\t (2) View all users");
		System.out.println("\t (3) Create new user");
		System.out.println("\t (4) Update User");
		System.out.println("\t (5) Set Account Status");
		System.out.println("\t (6) Delete Account");
		System.out.println("\t (7) Customer Menu Actions (deposit, withdraw, etc)");
		System.out.println("\t (8) Exit to Main Menu");
		System.out.println("\t (9) Log Out");
		menuChoice = scan.nextInt();
		switch(menuChoice) {
		
		case 1:{
			try {
				List<Users> l =newU.viewAllUsers();
				System.out.println(l);
			} catch (SQLException e) {
				System.out.println("Something went wrong!");
				e.printStackTrace();
				adminMenu();
			}
			adminMenu();
			break;
		}case 2:{
			try {
				List<Accounts> l =newA.viewAllAccounts();
				System.out.println(l);
			} catch (SQLException e) {
				System.out.println("Something went wrong!");
				e.printStackTrace();
				adminMenu();
			}
			adminMenu();
			break;
		}case 3:{
			Users a = makeNewUser();
			adminMenu();
			break;
		}case 4:{
			try {
				List<Users> l =newU.viewAllUsers();
				System.out.println(l);
			} catch (SQLException e) {
				System.out.println("Something went wrong!");
				e.printStackTrace();
				adminMenu();
			}
			System.out.println("Which user would you like to update? enter the id: ");
			try {
				Users this1 = newU.getUserById(scan.nextInt());
				newU.updateUser(this1);
			} catch (SQLException e) {
				System.out.println("Entry not valid. Please try again.");
				e.printStackTrace();
				adminMenu();
			}
			
			adminMenu();
			break;
		}case 5:{
			scan.nextLine();
			try {
				List<Accounts> l =newA.viewAllAccounts();
				System.out.println(l);
			} catch (SQLException e) {
				System.out.println("Something went wrong!");
				e.printStackTrace();
				adminMenu();
			}
			System.out.println("Which account would you like to update satus? enter the id: ");
			try {
				Accounts this1 = newA.getAccountById(scan.nextInt());
				System.out.println(this1);
				System.out.println("Set account status to active or inactive (true/false).");
				Boolean nb= scan.nextBoolean();
				newA.setStatus(this1, nb);
			} catch (SQLException e) {
				System.out.println("Entry not valid. Please try again.");
				e.printStackTrace();
				adminMenu();
			}
			
			adminMenu();
			break;
		}case 6:{
			try {
				List<Users> l =newU.viewAllUsers();
				System.out.println(l);
			} catch (SQLException e) {
				System.out.println("Something went wrong!");
				e.printStackTrace();
				adminMenu();
			}
			System.out.println("Which User would you like to delete? enter the id: ");
			try {
				Users this1 = newU.getUserById(scan.nextInt());
				newU.deleteUser(this1);
			} catch (SQLException e) {
				System.out.println("Could not delete, please try again.");
				e.printStackTrace();
				adminMenu();
			}
			
			adminMenu();
			break;
		}case 7:{
			try {
				List<Users> l =newU.viewAllUsers();
				System.out.println(l);
			} catch (SQLException e) {
				System.out.println("Something went wrong!");
				e.printStackTrace();
				adminMenu();
			}
			System.out.println("Which user would you like to make actions on? Enter the id here:");
			try {
				Users this1 = newU.getUserById(scan.nextInt());
				customerMenu(this1);
			} catch (SQLException e) {
				System.out.println("Not a valid user. Please try again");
				e.printStackTrace();
				adminMenu();
			}
			
			adminMenu();
			break;
		}case 8:{
			mainMenu();
			break;
		}default: {
			System.out.println("Successfully Logged Out");
			break;
		}
		
		
		}
		
	}
	
	public static Users loginValidation() {
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
					if(thisUser.getUsername().equals(user)) {
						umatch = true;
					
					//match pass
						System.out.println("Please enter your password: ");
						String pass = scan.nextLine();
						
						if(thisUser.getPassword().equals(pass)) {
							pmatch = true;
							return thisUser;
						
						}else {
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
		return null;
	
	}

	public static Users makeNewUser() {
		UsersDao newU = new UsersDaoImpl();
		scan.nextLine();
		System.out.println("Create User Menu");
		System.out.println("Enter first name:");
		String fname =scan.nextLine();
		System.out.println("Enter last name:");
		String lname =scan.nextLine();
		System.out.println("Enter username:");
		String uname =scan.nextLine();
		System.out.println("Enter pass:");
		String pass =scan.nextLine();
		Users u = new Users(0,fname,lname,uname,pass, false);
		try {
			newU.newUser(u);
		} catch (SQLException e) {
			System.out.println("Username in use. Please enter a different username. Press enter to continue");
			 makeNewUser();
		}
		return u;
	}
	

}
