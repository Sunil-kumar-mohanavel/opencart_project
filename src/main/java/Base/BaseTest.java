package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import Utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;

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
