package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    // Locators
    private By myAccountDropdown = By.xpath("//span[text()='My Account']");
    private By loginLink = By.linkText("Login");
    private By registerLink = By.linkText("Register");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void clickMyAccount() {
        driver.findElement(myAccountDropdown).click();
    }

    public LoginPage goToLoginPage() {
        clickMyAccount();
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }

    public RegistrationPage goToRegistrationPage() {
        clickMyAccount();
        driver.findElement(registerLink).click();
        return new RegistrationPage(driver);
    }
}
