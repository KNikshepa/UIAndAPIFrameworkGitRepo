package UITests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import BaseTest.BaseTest;
import ExtentReportUtility.ExtentReportManager;
import POMOfUI.AmazonPage;
import WebDriverUtility.DriverManager;

public class AmazonDropdown extends BaseTest{

	@Test(description = "Test case 1")
	public void AmazonDropdown() throws InterruptedException
	{
		//DriverManager.getDriverInstance().getDriver().get("https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-surefire-plugin/3.5.2");
		DriverManager.getDriverInstance().getDriver().get("https://www.flipkart.com/");
		AmazonPage amazonPage=new AmazonPage(DriverManager.getDriverInstance().getDriver(),ExtentReportManager.getExtentReportInstance().getCurrentTest());
		amazonPage.enterProductToSearch("Iphone");
	}
	
	@Test
	public void AmazonDropdown1() throws InterruptedException
	{
		//DriverManager.getDriverInstance().getDriver().get("https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-surefire-plugin/3.5.2");
		DriverManager.getDriverInstance().getDriver().get("https://www.flipkart.com/");
		AmazonPage amazonPage=new AmazonPage(DriverManager.getDriverInstance().getDriver(),ExtentReportManager.getExtentReportInstance().getCurrentTest());
		amazonPage.enterProductToSearch("Silppers");
	}
	
	@Test
	public void AmazonDropdown2() throws InterruptedException
	{
		//DriverManager.getDriverInstance().getDriver().get("https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-surefire-plugin/3.5.2");
		DriverManager.getDriverInstance().getDriver().get("https://www.flipkart.com/");
		AmazonPage amazonPage=new AmazonPage(DriverManager.getDriverInstance().getDriver(),ExtentReportManager.getExtentReportInstance().getCurrentTest());
		amazonPage.enterProductToSearch("Lipstick");
	}
}
