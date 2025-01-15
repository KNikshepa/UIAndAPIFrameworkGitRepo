package WebDriverUtility;

import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import ConfigurationUtility.ConfigEnumUtility;
import ConfigurationUtility.ConfigurationManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public final class DriverManager {

	private static DriverManager instance;
	private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

	private DriverManager() {
	}

	// Double-Checked Locking Singleton pattern
	public static synchronized DriverManager getDriverInstance() {
		if (instance == null) {
			instance = new DriverManager();
		}
		return instance;
	}

	public void initBrowser() {
		if (getDriver() == null) {
			switch (ConfigurationManager.getKeyValue(ConfigEnumUtility.browserType)) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driverThread.set(new ChromeDriver());
				break;
			case "edge":
				WebDriverManager.chromedriver().setup();
				driverThread.set(new EdgeDriver());
				break;
			default:
				System.out.println("Invalid Browser Type");
			}
			getDriver().manage().window().maximize();
		}
	}

	public void quitBrowser() {
		if (Objects.nonNull(getDriver())) {
			getDriver().quit();
			driverThread.remove();
		}
	}

	public WebDriver getDriver() {
		return driverThread.get();
	}
}
