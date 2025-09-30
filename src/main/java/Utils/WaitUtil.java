package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class WaitUtil {

    private WebDriver driver;

    // Default timeout
    public static final int DEFAULT_TIMEOUT = 10;

    // Constructor
    public WaitUtil(WebDriver driver) {
        this.driver = driver;
    }

    // Explicit wait for element to be visible
    public WebElement waitForElementVisible(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Overloaded method with default timeout
    public WebElement waitForElementVisible(WebElement element) {
        return waitForElementVisible(element, DEFAULT_TIMEOUT);
    }

    // Explicit wait for element to be clickable
    public WebElement waitForElementClickable(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Overloaded method with default timeout
    public WebElement waitForElementClickable(WebElement element) {
        return waitForElementClickable(element, DEFAULT_TIMEOUT);
    }

    // Fluent wait for element
    public WebElement fluentWait(By locator, int timeoutInSeconds, int pollingInMillis) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofMillis(pollingInMillis))
                .ignoring(NoSuchElementException.class);

        return wait.until(d -> d.findElement(locator));
    }

    // Overloaded fluent wait with default timeout/polling
    public WebElement fluentWait(By locator) {
        return fluentWait(locator, DEFAULT_TIMEOUT, 500);
    }
}
