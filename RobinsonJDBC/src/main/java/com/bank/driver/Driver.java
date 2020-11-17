package com.bank.driver;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.bank.beans.Accounts;
import com.bank.beans.Users;
import com.bank.dao.AccountsDao;
import com.bank.dao.User_AccountsDao;
import com.bank.dao.UsersDao;
import com.bank.impl.AccountsDaoImpl;
import com.bank.impl.User_AccountsDaoImpl;
import com.bank.impl.UsersDaoImpl;
import com.bank.menu.Menu;

public class Driver {

	public static void main(String[] args) {
		//yay this worked fuck yeah
		//create new user test
		AccountsDao newA = new AccountsDaoImpl(); 
		User_AccountsDao newUA = new User_AccountsDaoImpl();
		UsersDao newU = new UsersDaoImpl(); 
		
//		try { 
//			newU.newUser(new Users(0, "a","b","c","d",true)); 
//			}
//		catch (SQLException e) { 
//			 // TODO Auto-generated catch block
//		 e.printStackTrace(); 
//		 }
		
		//test find user by username
//		try {
//			UsersDao pd = new UsersDaoImpl();
//			Users p;
//			p = pd.getUserByUsername("moon");
//			System.out.println(p.toString());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		//do this for Admin menu
//		UsersDaoImpl udi= new UsersDaoImpl(); 
//		try {
//			ArrayList<Users> uList = (ArrayList<Users>)udi.viewAllUsers(); 
//			System.out.println(uList.toString()); 
//		}catch (SQLException e) { 
//				 e.printStackTrace(); 
//		}
		//this shit works AY AY
		
		
		// test new relation
//		try { 
//			LocalDate l = LocalDate.now();
//			newA.newAccount(new Accounts(0,java.sql.Date.valueOf(l),0.00,false)); 
//			newUA.newRelation(1,2);
//			}
//		catch (SQLException e) { 
//			 e.printStackTrace(); 
//		}
		//this works AY AY
//		try { 
//			//not necessary for delete
//			//LocalDate l = LocalDate.now();
//			//newA.newAccount(new Accounts(0,java.sql.Date.valueOf(l),0.00,false)); 
//			newUA.deleteRelation(1,2);
//			}
//		catch (SQLException e) { 
//			 e.printStackTrace(); 
//		}
		
		//testing relation
//	
		
		//test update user works
//		try { 
//			System.out.println("From Update test pt.2");
//			Users thisU = newU.getUserById(4);
//			System.out.println(thisU);
//			newU.updateUser(thisU);
//			System.out.println(newU.getUserById(4));
//			}
//		catch (SQLException e) { 
//			 e.printStackTrace(); 
//		}
		//this worked 2 what it do
//		try{
//			AccountsDao ad = new AccountsDaoImpl();
//			Accounts p =ad.getAccountById(2);
//			ad.withdraw(p, 0.05);
//			//ad.deleteAccount(ad.getAccountById(1));
//			
//			
//			//System.out.println(p);
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
		
		//do this for Admin menu
//		AccountsDaoImpl adi= new AccountsDaoImpl(); 
//		try {
//			ArrayList<Accounts> aList = (ArrayList<Accounts>)adi.viewAllAccounts(); 
//			System.out.println(aList.toString()); 
//		}catch (SQLException e) { 
//				 e.printStackTrace(); 
//		}
		
		
		Menu.mainMenu();
	}

}
