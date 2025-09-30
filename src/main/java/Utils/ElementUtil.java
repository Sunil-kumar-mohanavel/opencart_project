package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ElementUtil {

    private WebDriver driver;

    // Constructor
    public ElementUtil(WebDriver driver) {
        this.driver = driver;
    }

    // Click element
    public void click(WebElement element) {
        element.click();
    }

    // Send keys to input field
    public void sendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    // Get text from element
    public String getText(WebElement element) {
        return element.getText();
    }

    // Check if element is displayed
    public boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    // Check if element is enabled
    public boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }

    // Select dropdown by visible text
    public void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    // Select dropdown by value
    public void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    // Hover over element
    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}
