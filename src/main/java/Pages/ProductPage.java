package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import Utils.ElementUtil;
import Utils.WaitUtil;

public class ProductPage {

    private WebDriver driver;
    private ElementUtil elementUtil;
    private WaitUtil waitUtil;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
        
        waitUtil = new WaitUtil(driver);
    }

    // Locators 
    private By addProductButton = By.cssSelector("a[data-original-title='Add Product']");
    private By editButtons = By.cssSelector("a[data-original-title='Edit']");
    private By deleteButtons = By.cssSelector("button[data-original-title='Delete']");
    private By successMessage = By.cssSelector(".alert-success");

    private By productNameInput = By.id("input-name");
    private By productMetaTitleInput = By.id("input-meta-title1"); // Required field
    private By productModelInput = By.id("input-model");
    private By productPriceInput = By.id("input-price");
    private By saveButton = By.cssSelector("button[data-original-title='Save']");

    private By searchNameInput = By.name("filter_name");
    private By searchButton = By.id("button-filter");
    private By productRows = By.cssSelector("table.table tbody tr");

    private By noResultsRow = By.cssSelector("table.table tbody tr td");

    // Methods 

    // Add Product
    public void addProduct(String name, String metaTitle, String model, String price) {
        elementUtil.click(driver.findElement(addProductButton));
        elementUtil.sendKeys(driver.findElement(productNameInput), name);
        elementUtil.sendKeys(driver.findElement(productMetaTitleInput), metaTitle);
        elementUtil.sendKeys(driver.findElement(productModelInput), model);
        elementUtil.sendKeys(driver.findElement(productPriceInput), price);
        elementUtil.click(driver.findElement(saveButton));
    }

    // Edit first product
    public void editFirstProduct(String newPrice) {
        elementUtil.click(driver.findElement(editButtons)); // Assumes first edit button
        elementUtil.sendKeys(driver.findElement(productPriceInput), newPrice);
        elementUtil.click(driver.findElement(saveButton));
    }

    // Delete first product
    public void deleteFirstProduct() {
        elementUtil.click(driver.findElement(deleteButtons)); // Assumes first delete button
        driver.switchTo().alert().accept(); // Confirm deletion
    }

    // Get success message
    public String getSuccessMessage() {
        waitUtil.waitForElementVisible(driver.findElement(successMessage));
        return driver.findElement(successMessage).getText();
    }

    // Search product by name
    public void searchProduct(String name) {
        elementUtil.sendKeys(driver.findElement(searchNameInput), name);
        elementUtil.click(driver.findElement(searchButton));
    }

    // Filter products (by name only)
    public void filterProducts(String name) {
        searchProduct(name); // For simplicity, search acts as filter
    }

    // Get all product rows
    public List<WebElement> getAllProductRows() {
        waitUtil.waitForElementVisible(driver.findElement(productRows));
        return driver.findElements(productRows);
    }

    // Check if search result is empty (invalid search)
    public boolean isNoProductFound() {
        String text = driver.findElement(noResultsRow).getText();
        return text.contains("No results");
    }
}
