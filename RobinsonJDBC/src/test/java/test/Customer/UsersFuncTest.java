package test.Customer;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.bank.beans.Users;
import com.bank.dao.AccountsDao;
import com.bank.dao.User_AccountsDao;
import com.bank.dao.UsersDao;
import com.bank.impl.AccountsDaoImpl;
import com.bank.impl.User_AccountsDaoImpl;
import com.bank.impl.UsersDaoImpl;

public class UsersFuncTest {
	AccountsDao newA = new AccountsDaoImpl(); 
	User_AccountsDao newUA = new User_AccountsDaoImpl();
	UsersDao newU = new UsersDaoImpl(); 
	Users a1 = new Users(0, "J", "Unit", "j", "unit", false);
	int testId = 8;

	@Test
	public void testNewUser() {
		try {
			newU.newUser(a1);
			assertEquals(testId+1, newU.getUserByUsername("j"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetUserByUsername() {
		try {
			Users a =newU.getUserByUsername("tUser");
			assertEquals(newU.getUserById(4), a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testGetUserById() {
		try {
			Users a =newU.getUserByUsername("tUser");
			assertEquals(a, newU.getUserById(4));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteUser() {
		try {
			Users a2 =newU.getUserByUsername("j");
			newU.deleteUser(a2);
			assertEquals(testId+1,a2.getUserId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
