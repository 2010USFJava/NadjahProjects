package com.BoN.Users;

import java.io.Serializable;

public class Admin extends Users implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final UserType userType = UserType.admin;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserType getUserType() {
		return userType;
	}
	
	
}
