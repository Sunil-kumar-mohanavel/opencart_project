package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import Utils.ScreenshotUtil;
import Utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import Base.BaseTest;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String consoleOutput = BaseTest.getConsoleOutput();
        getTest().pass("Test passed. Console Output:\n" + consoleOutput);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        String testName = result.getName();
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "FAILED_" + testName);

        String consoleOutput = BaseTest.getConsoleOutput();
        getTest().fail(result.getThrowable())
                 .addScreenCaptureFromPath(screenshotPath)
                 .fail("Console Output:\n" + consoleOutput);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().skip("Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    //  ADD THIS METHOD
    public static ExtentTest getTest() {
        return testThread.get();
    }
}
