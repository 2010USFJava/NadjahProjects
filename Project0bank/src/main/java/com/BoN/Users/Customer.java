package com.BoN.Users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.BoN.Persist.BankLogger;
import com.BoN.Persist.Filer;
import com.BoN.Persist.Lists;

public class Customer extends Users implements Serializable{
      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
      private UserType userType = UserType.customer;
      private boolean  appStatus = false;
      List<Account> associatedAcct = new ArrayList<Account>();
      
      
     //behavior 
     public void assocAccount (Account...acctNums) {
    	 Account thisOne;
    	 for (int i = 0; i < acctNums.length; i++) {
    		 thisOne = acctNums[i];
    		 this.associatedAcct.add(acctNums[i]);
    		 System.out.println("Account Number " + acctNums[thisOne.getAcctNumber()] + " has been associated with " + this.username);
	 
    	  
		}
     }

     //Constructors, getters and setter 
     
	public Customer(boolean appStatus, Account acct) {
		super();
		this.appStatus = appStatus;
		this.associatedAcct.add(acct);
	
		
		Lists.uMap.put(this.getUserId(),this);
		Filer.writeUserFile(Lists.uMap);
		BankLogger.LogIt("info", "New Customer " + this.userId + " associated with account" + acct + " was created ");
	}
	public Customer(boolean appStatus, Account ...acct) {
		super();
		this.appStatus = appStatus;
		for (int i = 0; i < acct.length; i++) {
			this.associatedAcct.add(acct[i]);
		}
		
		Lists.uMap.put(this.getUserId(),this);
		Filer.writeUserFile(Lists.uMap);
		BankLogger.LogIt("info", "New Customer " + this.userId + " with various accounts created:" + acct.toString());
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(boolean appStatus, List<Account> associatedAcct) {
		super();
		this.appStatus = appStatus;
		this.associatedAcct = associatedAcct;
		Lists.uMap.put(this.getUserId(),this);
		Filer.writeUserFile(Lists.uMap);
		BankLogger.LogIt("info", "New Customer " + this.getUserId() + " was created ");
	}



	public boolean isAppStatus() {
		return appStatus;
	}

	public void setAppStatus(boolean appStatus) {
		this.appStatus = appStatus;
	}

	public List<Account> getAssociatedAcct() {
		return associatedAcct;
	}

	public void setAssociatedAcct(List<Account> associatedAcct) {
		this.associatedAcct = associatedAcct;
	}

	@Override
	public String toString() {
		return "User: " + userType + "\n appStatus: " + appStatus + "\n Associated Accounts" 
				+ "\n username: " + username + "\n  password: " + password + "/n name: " + name + "\n userId: " + userId;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
     
   
     
}






















