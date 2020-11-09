package com.BoN.Persist;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.BoN.Users.Users;

public class Lists implements Serializable {

	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	public static Map<String, String> uAndP = new HashMap<String, String>();
	public static Map<Integer, Users> uMap = new HashMap<Integer, Users>();
	// protected static Set <Employee> eList= new HashSet<Employee>();

	public static Users loginValidation(String u, String p) {
		Users isLogin = null;
		String thisPerson;
		if(uAndP.containsKey(u)) { //if uandp contains this username
			thisPerson = uAndP.get(u);
			if(thisPerson == p) { //if the password matches the username
				for (Iterator<Users> iterator = uMap.values().iterator(); iterator.hasNext();) { //for the values 
					Users thisOne = iterator.next();
					if(thisOne.getUsername() == u) {
						isLogin = thisOne;
				
					}else{ 
						System.out.println("System Error, User could not be logged in.");
					}
					
					
				}
				System.out.println("You have successfully logged in.");
			}
		}else {
			System.out.println("No matching user found.");
		}

		return isLogin;
	}

	public static Users getUserByID(Integer id) {
		Users thisUser = uMap.get(id);

		return thisUser;
	}
	
	public void readCustomers() {
		 
			//
			
		
	   }
}
