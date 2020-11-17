package com.bank.beans;

public class UserNotUniqueException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotUniqueException() {
		System.out.println("This username is already in use.");
	}
}
