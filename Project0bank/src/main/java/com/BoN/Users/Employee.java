package com.BoN.Users;

import java.io.Serializable;

import com.BoN.Persist.Lists;

public class Employee extends Users implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final UserType userType = UserType.employee;

	// read list of customers

	// behavior

	public void approveAcct(Customer user) {

		user.setAppStatus(true);

	}


	public Employee() {
		super();
		Lists.uMap.put(this.getUserId(),this);
		// TODO write and log
	}

	public UserType getUserType() {
		return userType;
	}


	@Override
	public String toString() {
		return "User Type: " + userType + "\n Username: " + username + "\n Password: " + password + "\n Name: " + name
				+ "\n UserId: " + userId;
	}

	
}
