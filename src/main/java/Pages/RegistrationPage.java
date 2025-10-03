package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
 
    private WebDriver driver;

    // Locators
    private By firstNameInput = By.id("input-firstname");
    private By lastNameInput = By.id("input-lastname");
    private By emailInput = By.id("input-email");
    private By telephoneInput = By.id("input-telephone");
    private By passwordInput = By.id("input-password");
    private By confirmPasswordInput = By.id("input-confirm");
    private By privacyPolicyCheckbox = By.name("agree");
    private By continueBtn = By.xpath("//input[@value='Continue']");
    private By successMessage = By.cssSelector("#content h1");

    // Constructor
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterTelephone(String telephone) {
        driver.findElement(telephoneInput).sendKeys(telephone);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        driver.findElement(confirmPasswordInput).sendKeys(confirmPassword);
    }

    public void acceptPrivacyPolicy() {
        driver.findElement(privacyPolicyCheckbox).click();
    }

    public void clickContinue() {
        driver.findElement(continueBtn).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    
    public void registerUser(String firstName, String lastName, String email,
                             String telephone, String password) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterTelephone(telephone);
        enterPassword(password);
        enterConfirmPassword(password);
        acceptPrivacyPolicy();
        clickContinue();
    }
}
