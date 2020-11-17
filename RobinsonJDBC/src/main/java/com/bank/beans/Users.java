package com.bank.beans;

public class Users {
	private int userId;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private boolean admin;
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Users(int userId, String firstname, String lastname, String username, String password, boolean admin) {
		super();
		this.userId = userId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.admin = admin;
	}

	

	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}



	@Override
	public String toString() {
		return "Users [userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname + ", username="
				+ username + ", password=" + password + ", admin=" + admin + "]";
	}

	
	


}
