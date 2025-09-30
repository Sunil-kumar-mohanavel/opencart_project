package Utils;

import org.openqa.selenium.WebDriver;

public class BrowserUtil {

    // Maximize window
    public static void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }

    // Get current page title
    public static String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    // Navigate to URL
    public static void navigateToUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    // Refresh page
    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    // Navigate back
    public static void goBack(WebDriver driver) {
        driver.navigate().back();
    }

    // Navigate forward
    public static void goForward(WebDriver driver) {
        driver.navigate().forward();
    }
    
    // get current URL
     
    public static String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

}
