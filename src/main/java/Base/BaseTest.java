package Base;

import org.apache.commons.io.output.TeeOutputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import Utils.ConfigReader;
import Utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BaseTest {
	
	private static final ByteArrayOutputStream consoleStream = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    static {
        // Create a combined PrintStream that writes to both console and memory
        System.setOut(new PrintStream(new TeeOutputStream(originalOut, consoleStream)));
    }

    public static String getConsoleOutput() {
        String output = consoleStream.toString();
        consoleStream.reset(); // clear after reading
        return output;
    }

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setUpSuite() {
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.initDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(2000);
            driver.quit();
        }
        if (test != null) {
            extent.flush(); // ensure report updates after each test
        }
    }

    //  Front-End 
    public void openFrontEnd() {
        driver.get(ConfigReader.get("baseUrl"));
    }

    //  Admin Panel
    public void openAdmin() {
        driver.get(ConfigReader.get("adminURL"));
    }

    public void loginToAdmin() {
        openAdmin(); // Navigate to admin URL
        driver.findElement(By.id("input-username")).sendKeys(ConfigReader.get("adminUsername"));
        driver.findElement(By.id("input-password")).sendKeys(ConfigReader.get("adminPassword"));
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    // Getter for listener
    public WebDriver getDriver() {
        return driver;
    }
}
