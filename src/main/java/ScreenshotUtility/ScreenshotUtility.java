package ScreenshotUtility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

public final class ScreenshotUtility {

	// ThreadLocal to ensure thread-safety
	private static final ThreadLocal<ScreenshotUtility> screenshotThreadLocal = ThreadLocal
			.withInitial(ScreenshotUtility::new);

	// Private constructor for Singleton
	private ScreenshotUtility() {
	}

	// Get thread-local instance of ScreenshotUtility
	public static ScreenshotUtility getInstance() {
		return screenshotThreadLocal.get();
	}

	// Method for WebDriver screenshot
	public File takeScreenshot(WebDriver driver) {
		// Ensure the directory exists
		String screenshotDir = System.getProperty("user.dir") + "\\Screenshots";
		File screenshotFolder = new File(screenshotDir);
		if (!screenshotFolder.exists()) {
			screenshotFolder.mkdirs(); // Create the folder if it doesn't exist
		}

		// Capture screenshot using WebDriver (browser window screenshot)
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Generate a timestamp with milliseconds and thread ID for uniqueness
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date());
		String threadId = String.valueOf(Thread.currentThread().getId());

		// Define the destination file path
		File destination = new File(screenshotDir + "\\" + timestamp + "_Thread-" + threadId + ".png");

		try {
			// Save the screenshot to the file
			FileUtils.copyFile(screenshotFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destination;
	}

	// Method for taking screenshot using Robot (Full screen)
	public File takeScreenshotWithRobot() {
		// Ensure the directory exists
		String screenshotDir = System.getProperty("user.dir") + "\\Screenshots";
		File screenshotFolder = new File(screenshotDir);
		if (!screenshotFolder.exists()) {
			screenshotFolder.mkdirs(); // Create the folder if it doesn't exist
		}

		// Capture the full screen using Robot
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		// Capture screen dimensions
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rectangle = new Rectangle(dimension);
		BufferedImage screenCapture = robot.createScreenCapture(rectangle);

		// Generate timestamp for screenshot filename
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date());
		String threadId = String.valueOf(Thread.currentThread().getId());

		// Define the destination file path
		File destination = new File(screenshotDir + "\\" + timestamp + "_Thread-" + threadId + ".png");

		try {
			// Save the screenshot
			ImageIO.write(screenCapture, "png", destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destination;
	}
}
