package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utils.ElementUtil;

public class HomePage {

    private WebDriver driver; 
    private ElementUtil elementUtil;

    // Locators - My Account
    private By myAccountDropdown = By.xpath("//span[text()='My Account']");
    private By loginLink = By.linkText("Login");
    private By registerLink = By.linkText("Register");

    // Locators - Search
    private By searchBox = By.name("search");
    private By searchButton = By.cssSelector("button.btn.btn-default.btn-lg");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);
    }

    // Account Methods 
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

    //  Product Search Methods 
    public void searchProduct(String productName) {
    	elementUtil.sendKeys(driver.findElement(searchBox), productName);
    	elementUtil.click(driver.findElement(searchButton));
    }

    public void selectProduct(String productName) {
        By productLink = By.linkText(productName);
        elementUtil.click(driver.findElement(searchButton));
    }
}
