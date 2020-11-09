package com.BoN.Users;

import com.BoN.Persist.Lists;

public class Employee extends Users {
	final UserType uType = UserType.employee;

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

	public UserType getuType() {
		return uType;
	}


	@Override
	public String toString() {
		return "User Type: " + uType + "\n Username: " + username + "\n Password: " + password + "\n Name: " + name
				+ "\n UserId: " + userId;
	}

	
}
