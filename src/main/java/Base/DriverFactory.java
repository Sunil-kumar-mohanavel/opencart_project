package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import Utils.ConfigReader;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver initDriver() {
        String browser = ConfigReader.get("browser");

        switch (browser.toLowerCase()) {
            case "chrome":
                
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setAcceptInsecureCerts(true); // Ignore SSL warnings
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
               
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setAcceptInsecureCerts(true); // Ignore SSL warnings
                driver = new FirefoxDriver(firefoxOptions);
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
        if (driver != null) {
            Thread.sleep(2000); // small wait before quitting
            driver.quit();
            driver = null;
        }
    }
}
