package POMOfUI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import ActionsUtility.ActionsUtility;

public class AmazonPage extends ActionsUtility{

	private WebDriver driver;
	private ExtentTest test;
	
	public AmazonPage(WebDriver driver,ExtentTest test) {
		super(driver,test);
		this.driver=driver;
		this.test=test;
		PageFactory.initElements(driver, this);
	}

	//@FindBy(xpath="//input[@placeholder='Search for groups, artifacts, categories']")
	@FindBy(xpath="//input[@placeholder='Search for Products, Brands and More']")
	WebElement inputBox;
	
	public void enterProductToSearch(String value)
	{
		sendKeys_Custom(inputBox, value, "Search Box");
	}
}
