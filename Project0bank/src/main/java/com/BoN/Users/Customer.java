package com.BoN.Users;

import java.util.ArrayList;
import java.util.List;

import com.BoN.Persist.Lists;

public class Customer extends Users{
      final private UserType uType = UserType.customer;
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
		//TODO write to file
		//TODO log
	}
	public Customer(boolean appStatus, Account ...acct) {
		super();
		this.appStatus = appStatus;
		for (int i = 0; i < acct.length; i++) {
			this.associatedAcct.add(acct[i]);
		}
		
		Lists.uMap.put(this.getUserId(),this);
		//TODO write to file
		//TODO log
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
		//TODO write to file
		//TODO log
	}

	public UserType getuType() {
		return uType;
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
     
   
     
}






















