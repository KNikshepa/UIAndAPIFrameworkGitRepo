package BaseTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import WebDriverUtility.DriverManager;

public class BaseTest {

	@BeforeMethod
	public void setUp() {
		System.out.println("Thread ID at setUp: " + Thread.currentThread().getId());
		DriverManager.getDriverInstance().initBrowser();
	}

	@AfterMethod
	public void tearDown() {
		System.out.println("Thread ID at TearDown: " + Thread.currentThread().getId());
		DriverManager.getDriverInstance().quitBrowser();
	}
}
