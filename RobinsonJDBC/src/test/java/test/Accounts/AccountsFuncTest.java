package test.Accounts;


import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import com.bank.beans.Accounts;
import com.bank.dao.AccountsDao;
import com.bank.dao.User_AccountsDao;
import com.bank.dao.UsersDao;
import com.bank.impl.AccountsDaoImpl;
import com.bank.impl.User_AccountsDaoImpl;
import com.bank.impl.UsersDaoImpl;

public class AccountsFuncTest {
	AccountsDao newA = new AccountsDaoImpl(); 
	User_AccountsDao newUA = new User_AccountsDaoImpl();
	UsersDao newU = new UsersDaoImpl(); 
	
	double amount = 5.00;
	//increment get by values
	@Ignore
	public void removeDummies() {
		try {
			newA.deleteAccount(newA.getAccountById(12));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testDeposit() {
		try {
			newA.deposit(newA.getAccountById(12), amount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			double val = 35.00;
			assertEquals(BigDecimal.valueOf(val + 5.00),newA.getAccountById(12).getBalance());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
//	@Test
//	public void testDeleteAccount() {
//		fail("Not yet implemented");
//	}
//
	@Test
	public void testWithdraw() {
		try {
			newA.withdraw(newA.getAccountById(12), amount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			double val = 40.00;
			assertEquals(BigDecimal.valueOf(val - 5.00),newA.getAccountById(12).getBalance());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Test
	public void testAmountValidation() {
		try {
			newA.newAccount(newA.getAccountById(12));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			assertTrue(newA.amountValidation(newA.getAccountById(12), amount));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Test
	public void testSetStatus() {
		try {
			newA.setStatus(newA.getAccountById(12), false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertEquals(false,newA.getAccountById(12).getActive());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
