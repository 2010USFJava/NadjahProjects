package com.BoN.Users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.BoN.Persist.BankLogger;
import com.BoN.Persist.Filer;
import com.BoN.Persist.Lists;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean acctStatus = false;
	private float balance;
	List<Customer> acctHolder = new ArrayList<Customer>();
	private int acctNumber;
	private static Random random = new Random();
	
	
	
	
	
	//behavior
	public static  int genAcctNumber() {
		 
		int newNumber = random.nextInt((10000-9000)+1)+ 9000;
		
		return newNumber;
	}

	public boolean amountValidation(float amount) {
		boolean accept = false;
		if (amount > this.balance) {
			System.out.println("Insuficient Funds: Requested amount is greater than available balance.");
			return accept;
		} else if (amount <= 0.00) {
			System.out.println("Invalid Amount: Please enter a non-zero non-negative number");
			return accept;
		} else {
			return accept;
		}

	}

	public void transfer(float amount, Account ... v) {
		Scanner choice = new Scanner(System.in);
		Account tAcct;
		
		for (int i = 0; i < v.length; i++) { // selects the account
			System.out.println("Would you like to transfer from this account? (y/n) ");
			System.out.println(v[i]);
			String clicked = choice.nextLine();
			if( clicked.equals("y")) {
				tAcct = v[i];
				System.out.println("How much would you like transfer? ");
					float tAmt = choice.nextFloat();
				 if((amountValidation(tAmt)==true)){
					 tAcct.balance -= tAmt;
					 System.out.println("Which one of your accounts will you be transferring to?");
					 for (int j = 0; i < v.length; j++) { // selects the account
							System.out.println("Would you like to transfer to this account? (y/n) ");
							System.out.println(v[j]);
							choice.nextLine();
							if( choice.equals("y")) {
								v[j].balance += tAmt;
								System.out.println("Transfer complete!");
								Filer.writeUserFile(Lists.uMap);
								BankLogger.LogIt("info", "Tranfer of " + amount + " was made from account "
								+ v[i].getAcctNumber() + " to account " + v[j].getAcctNumber());
								return;
							}else {
								break;
							}
					 }
				 }else {
					 //recall method
					 System.out.println("Re-enter the amount you'd like to transfer"); 
					 choice.nextFloat();
				 }
				break;
			}
			if(choice.equals("n") && i >= v.length-1) {
				System.out.println("Transfer cancelled");
				return;
				
			}
		}
	}
	
	public String deposit(float amount) {
		
		amountValidation(amount);
		this.balance += balance;
		String msg = "Deposit successful: your new balance is " + this.balance;
		BankLogger.LogIt("info", "Deposit of " + amount + " was made to account " + this.acctNumber);
		Filer.writeUserFile(Lists.uMap);
		return msg;
		
		
	}
	
	public String withdraw(float amount) {
		
		float withdraw = this.balance - amount;
		
		if (amount > this.balance) {
		 System.out.println("Requested withdrawal amount is greater than available balance.");
		}else if(amount <= 0.00) {
			System.out.println("Invalid Amount: Please enter a non-zero non-negative number");
		}
		
		this.balance -= withdraw;
		String result = "Withdrawal successful. Your remaining balance is $" + this.balance;
		BankLogger.LogIt("info", "Withdrawal of " + amount + " was made from account " + this.acctNumber);
		Filer.writeUserFile(Lists.uMap);	
		return result;
	}

	public Account(boolean acctStatus, float balance, List<Customer> acctHolder) {
		super();
		this.acctStatus = acctStatus;
		this.balance = balance;
		this.acctHolder = acctHolder;
		this.acctNumber = genAcctNumber();
		Lists.uMap.put(this.acctNumber,acctHolder.get(0));
		Filer.writeUserFile(Lists.uMap);
	}
	
	public Account(boolean acctStatus, float balance, Customer acctHolder) {
		super();
		this.acctStatus = acctStatus;
		this.balance = balance;
		this.acctHolder.add(acctHolder);
		this.acctNumber = genAcctNumber();
		Lists.uMap.put(this.acctNumber,acctHolder);
		Filer.writeUserFile(Lists.uMap);
	}
	
	public Account(boolean acctStatus, float balance, Customer ...acctHolder) {
		super();
		this.acctStatus = acctStatus;
		this.balance = balance;
		for (int i = 0; i < acctHolder.length; i++) {
			this.acctHolder.add(acctHolder[i]);
		}
		this.acctNumber = genAcctNumber();
		Lists.uMap.put(this.acctNumber,acctHolder[0]);
		Filer.writeUserFile(Lists.uMap);
	}
	public boolean isAcctStatus() {
		return acctStatus;
	}

	public void setAcctStatus(boolean acctStatus) {
		this.acctStatus = acctStatus;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public List<Customer> getAcctHolder() {
		
		return acctHolder;
	}
	
	public void addAcctHolder(Customer ...customer) {
		for (Customer customerI : customer) {
			
			this.acctHolder.add(customerI);
		}
		
	}
	public void addAcctHolder(Users ...user) {
		for (Users userI : user) {
			
			this.acctHolder.add((Customer) userI);
		}
		
	}
	public void setAcctHolder(List<Customer> acctHolder) {
		this.acctHolder = acctHolder;
	}

	public int getAcctNumber() {
		return acctNumber;
	}

	public void setAcctNumber(int acctNumber) {
		this.acctNumber = acctNumber;
	}


	
}
