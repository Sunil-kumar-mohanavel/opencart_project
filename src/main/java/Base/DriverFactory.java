package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import Utils.ConfigReader;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver initDriver() {
        String browser = ConfigReader.get("browser");

        switch(browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() throws InterruptedException {
        if(driver != null) {
        	
        	Thread.sleep(5000);
            driver.quit();
            driver = null;
        }
    }
}
