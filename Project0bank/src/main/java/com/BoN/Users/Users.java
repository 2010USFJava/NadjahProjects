package com.BoN.Users;

import java.io.Serializable;
import java.util.Random;

import com.BoN.Persist.Lists;

public abstract class Users implements Serializable {
	//TODO get rid of this
	private UserType userType;
	private static Random random = new Random();
	protected String username;
	protected String password;
	protected String name;
	protected int userId = random.nextInt(((2000-1000)+1)+ 1000);
	
	
	public void addU() {
		String u = this.username;
		String p = this.password;
		Lists.uAndP.put(u,p);
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "User: "+  name + "\n Username: " + username + "\n Password: " + password + "\n UserId: " + userId;
	}




	public UserType getUserType() {
		return userType;
	}
	
	
	
}
