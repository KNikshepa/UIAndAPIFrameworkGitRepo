package ListenerUtility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import ExtentReportUtility.ExtentReportManager;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started " + context.getName());
        ExtentReportManager.getExtentReportInstance().setUpExtentReport();
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Stopped " + context.getName());
        ExtentReportManager.getExtentReportInstance().flushReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started " + result.getMethod().getMethodName());
        ExtentReportManager.getExtentReportInstance().createTest(result.getMethod().getMethodName(),
                result.getMethod().getGroups().toString(), "Nikshepa K");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logTestResult(result, "Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logTestResult(result, "Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logTestResult(result, "Skipped");
    }

    // Helper method to log test result
    private void logTestResult(ITestResult result, String status) {
        ExtentTest test = ExtentReportManager.getExtentReportInstance().getCurrentTest();
        if (test != null) {
            String methodName = result.getMethod().getMethodName();
            switch (status) {
                case "Passed":
                    test.pass(methodName + " Test Case is Passed");
                    break;
                case "Failed":
                    test.fail(methodName + " Test Case is Failed due to the error " + result.getThrowable().getMessage());
                    break;
                case "Skipped":
                    test.skip(methodName + " Test Case is Skipped due to the error " + result.getThrowable().getMessage());
                    break;
            }
        }
    }
}
