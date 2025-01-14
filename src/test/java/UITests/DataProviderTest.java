package UITests;

import java.util.Map;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import ExtentReportUtility.ExtentReportManager;
import POMOfUI.AmazonPage;
import WebDriverUtility.DriverManager;

public class DataProviderTest extends BaseTest{

	@Test(description = "DataProvider Test",dataProvider = "getData",dataProviderClass =DataProviderUtility.DataProviderUtility.class)
	public void AmazonDropdownDataProv(Map<String, String> testData) throws InterruptedException
	{
		//DriverManager.getDriverInstance().getDriver().get("https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-surefire-plugin/3.5.2");
		DriverManager.getDriverInstance().getDriver().get("https://www.flipkart.com/");
		AmazonPage amazonPage=new AmazonPage(DriverManager.getDriverInstance().getDriver(),ExtentReportManager.getExtentReportInstance().getCurrentTest());
		amazonPage.enterProductToSearch(testData.get("FieldValue"));
	}
}
