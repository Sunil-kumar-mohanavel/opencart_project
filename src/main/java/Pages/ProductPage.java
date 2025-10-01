package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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

    // Navigate to Products page
    public void goToProductsPage() {
        WebElement catalogMenu = driver.findElement(By.xpath("//a[contains(text(),'Catalog')]"));
        catalogMenu.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productsLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Products')]")));
        productsLink.click();
    }

    // Locators
    private By addProductButton = By.xpath("//div[@class='pull-right']//a[@class='btn btn-primary']");
   
    private By deleteButtons = By.xpath("//button[@class='btn btn-danger']");
 
    private By successMessage = By.xpath("//div[contains(@class,'alert-success')]");

    // General Tab
    private By productName = By.xpath("//input[@id='input-name1']");
    private By productMetaTitle = By.xpath("//input[@id='input-meta-title1']");

    // Data Tab
    private By dataTab = By.xpath("//a[normalize-space()='Data']");
    private By productModel = By.xpath("//input[@id='input-model']");
    private By productQuantity = By.xpath("//input[@id='input-quantity']");
    private By productPrice = By.xpath("//input[@id='input-price']");

    private By saveButton = By.xpath("//button[@type='submit']");

    private By searchNameInput = By.name("filter_name");
    private By searchButton = By.id("button-filter");
    private By productRows = By.cssSelector("table.table tbody tr");
    private By noResultsRow = By.cssSelector("table.table tbody tr td");

    // Add Product
    public void addProduct(String name, String metaTitle, String model, String Quantity, String price) {
        elementUtil.click(driver.findElement(addProductButton));

        // General tab
        elementUtil.sendKeys(driver.findElement(productName), name);
        elementUtil.sendKeys(driver.findElement(productMetaTitle), metaTitle);

        // Data tab
        elementUtil.click(driver.findElement(dataTab));
        elementUtil.sendKeys(driver.findElement(productModel), model);

        WebElement categoryInput = driver.findElement(productQuantity);
        categoryInput.clear();
        categoryInput.sendKeys(Quantity);

        if(price != null && !price.isEmpty()) {
            elementUtil.sendKeys(driver.findElement(productPrice), price);
        }

        elementUtil.click(driver.findElement(saveButton));
    }

    
 // Edit product by name 
    public void editProductByName(String productNameToEdit, String newPrice) {
        List<WebElement> rows = driver.findElements(productRows);
        boolean found = false;
        for (WebElement row : rows) {
            if (row.getText().contains(productNameToEdit)) {
                WebElement editBtn = row.findElement(By.xpath(".//a[@data-original-title='Edit']"));
                elementUtil.click(editBtn);

                // Switch to Data tab
                elementUtil.click(driver.findElement(dataTab));

                WebElement priceInput = driver.findElement(productPrice);
                priceInput.clear();
                elementUtil.sendKeys(priceInput, newPrice);

                elementUtil.click(driver.findElement(saveButton));
                found = true;
                break;
            }
        }
        if (!found) {
            throw new RuntimeException("Product not found: " + productNameToEdit);
        }
    }

    // Delete product by name
    public void deleteProductByName(String productNameToDelete) {
        List<WebElement> rows = driver.findElements(productRows);
        boolean found = false;
        for (WebElement row : rows) {
            if (row.getText().contains(productNameToDelete)) {
                WebElement deleteCheckbox = row.findElement(By.xpath(".//input[@type='checkbox']"));
                elementUtil.click(deleteCheckbox);

                elementUtil.click(driver.findElement(deleteButtons));
                driver.switchTo().alert().accept(); 
                found = true;
                break;
            }
        }
        if (!found) {
            throw new RuntimeException("Product not found: " + productNameToDelete);
        }
    }


    // Get success message
    public String getSuccessMessage() {
        waitUtil.waitForElementVisible(driver.findElement(successMessage));
        return driver.findElement(successMessage).getText();
    }

    // Search product
    public void searchProduct(String name) {
        elementUtil.sendKeys(driver.findElement(searchNameInput), name);
        elementUtil.click(driver.findElement(searchButton));
    }

    // Filter products
    public void filterProducts(String name) {
        searchProduct(name);
    }

    // Get all product rows
    public List<WebElement> getAllProductRows() {
        waitUtil.waitForElementVisible(driver.findElement(productRows));
        return driver.findElements(productRows);
    }

    // Check no product found
    public boolean isNoProductFound() {
        String text = driver.findElement(noResultsRow).getText();
        return text.contains("No results");
    }
}
