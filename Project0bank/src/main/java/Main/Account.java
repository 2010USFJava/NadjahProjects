package Main;

public class Account {
	private boolean acctStatus = false;
	private float balance;
	private Customer [] acctHolder;
	
	//behavior
	public String withdraw(float amount) {
		
		float withdraw = this.balance - amount;
		
		if (amount > this.balance) {
		 System.out.println("Requested withdrawal amount is greater than available balance.");
		}
		
		this.balance = withdraw;
		String result = "Withdrawal successful. Your remaining balance is $" + this.balance;
		
		return result;
	}
}
