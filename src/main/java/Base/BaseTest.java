package Base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import Utils.ConfigReader;


public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.initDriver();
        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        DriverFactory.quitDriver();
    }
}
