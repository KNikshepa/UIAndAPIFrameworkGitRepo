package UITests;

import org.testng.annotations.Test;

public class MvnParameterTest {

	@Test
	public void test() {
		// Retrieve the username from system properties
		String username = System.getProperty("username");

		// Print the username for confirmation
		System.out.println("Username: " + username);
		
		// Retrieve the username from system properties
		String username1 = System.getProperty("defaultUsername");

				// Print the username for confirmation
		System.out.println("Username: " + username1);

	}
}
