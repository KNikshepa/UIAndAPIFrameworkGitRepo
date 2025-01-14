package ActionsUtility;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;

import ExtentReportUtility.ExtentReportManager;
import ScreenshotUtility.ScreenshotUtility;

public class ActionsUtility {

	private WebDriver driver;
	private WebDriverWait wait;
	private Actions actions;
	private JavascriptExecutor jse;
	private ExtentTest test;
	private static final Logger logger = LogManager.getLogger(ActionsUtility.class);

	protected ActionsUtility(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		actions = new Actions(driver);
		jse = ((JavascriptExecutor) driver);
	}

	protected void sendKeys_Custom(WebElement element,String valueToBeSent,String fieldName)
	{
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(valueToBeSent);
			Thread.sleep(2000);
			logger.info("Value: "+valueToBeSent+" has been sent to the field "+fieldName);
			test.log(Status.INFO, "Value has been sent to the field "+fieldName, MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotUtility.getInstance().takeScreenshot(driver).getAbsolutePath()).build());
			
		}catch (Exception e) {
			logger.error("Unable to send the value into input box");
			throw new RuntimeException("Unable to send the value into Input box due to "+e.getMessage());
		}
	}
}
