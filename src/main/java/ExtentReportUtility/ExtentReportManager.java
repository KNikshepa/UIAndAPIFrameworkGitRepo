package ExtentReportUtility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentReportManager {

	private static ExtentReportManager extentReportInstance;
	private static ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();
	private static ExtentReports extentReports;

	private ExtentReportManager() {
	}

	public static synchronized ExtentReportManager getExtentReportInstance() {
		if (extentReportInstance == null) {
			extentReportInstance = new ExtentReportManager();
		}
		return extentReportInstance;
	}

	public void setUpExtentReport() {
		if (extentReports == null) {
			// Ensure the "ExtentReports" folder inside the "test-output" directory exists
			File reportDir = new File(System.getProperty("user.dir") + "/test-output/ExtentReports/");
			if (!reportDir.exists()) {
				reportDir.mkdirs(); // Create the "ExtentReports" folder if it doesn't exist
			}

			// Initialize ExtentSparkReporter
			String reportPath = reportDir + "/ExtentReport_" + retriveDateAndTime() + ".html";
			System.out.println("Report Path: " + reportPath); // Debugging line
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
			sparkReporter.config().setDocumentTitle("Automation Test Report");
			sparkReporter.config().setReportName("Execution Report");

			// Initialize ExtentReports
			extentReports = new ExtentReports();
			extentReports.attachReporter(sparkReporter);
			extentReports.setSystemInfo("Environment", "QA-UAT");
		}
	}

	public void createTest(String testCaseName, String groups, String author) {
		if (extentReports == null) {
			throw new IllegalStateException("ExtentReports is not initialized. Call setUpExtentReport() first.");
		}
		ExtentTest test = extentReports.createTest(testCaseName).assignCategory(groups).assignAuthor(author);
		extentTestThread.set(test);
	}

	public ExtentTest getCurrentTest() {
		ExtentTest test = extentTestThread.get();
		if (test == null) {
			throw new IllegalStateException("No test is associated with the current thread.");
		}
		return test;
	}

	public void flushReports() {
		if (extentReports != null) {
			extentReports.flush();
		}
	}

	private String retriveDateAndTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		return formatter.format(new Date());
	}
}
