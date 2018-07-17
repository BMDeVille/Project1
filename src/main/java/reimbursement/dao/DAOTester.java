package reimbursement.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import reimbursement.application.Employee;

public class DAOTester {
	static ReimbursementDAO reimbTester;
	
	@Test
	public void selectShouldReturnNewEmployeeIfNoUser() {
		assertEquals("If no user, return should be basic employee", new Employee().getUsername(), reimbTester.selectUserUsername("").getUsername());
	}
	
	@Test
	public void updateShouldReturnOneIfSuccessful() {
		Employee updateTest = new Employee("Testy01", "test01", "Testy", "McTester", "testy@test.gmail.com", 1);
		assertEquals("If update works, return should be the value 1", 1, reimbTester.updateUser(1000, updateTest));
	}
	
	@Test
	public void insertShouldNotReturnOneIfUserExists() {
		Employee insertTest = new Employee("Testy01", "test01", "Testy", "McTester", "testy@test.gmail.com", 2);
		insertTest.setEmployeeID(1);
		assertNotEquals("If update works, return should be the value 1", 1, reimbTester.insertUser(insertTest));
	}
	
	@Before
	public void beforeMethod() {
		System.out.println("\n*****Before each method*****");
	}
	@After
	public void afterMethod() {
		System.out.println("*****After each method*****");
	}
	@BeforeClass
	public static void beforeClass() {
		System.out.println("\n*****BEFORE CLASS*****");
		reimbTester = new ReimbursementDAOImpl();
	}
	@AfterClass
	public static void afterClass() {
		System.out.println("\n*****AFTER CLASS*****");
	}
}
