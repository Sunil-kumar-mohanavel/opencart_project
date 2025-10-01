package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utils.ElementUtil;
import Utils.WaitUtil;

import java.time.Duration;
import java.util.List;

public class CartPage {

    private WebDriver driver;
    private ElementUtil elementUtil;
    private WebDriverWait wait;

    // Locators
    private By searchInput = By.name("search");
    private By searchButton = By.xpath("//button[@class='btn btn-default btn-lg']");
    private By productLink = By.xpath("//div[@class='caption']/h4/a");
    private By addToCartBtn = By.id("button-cart");
    private By cartTotal = By.id("cart-total");
    private By shoppingcart = By.xpath("//a[@title='Shopping Cart']//i[@class='fa fa-shopping-cart']");
    private By shoppingcartremove = By.xpath("//i[@class='fa fa-times-circle']");
    private By updatequantity = By.xpath("//input[contains(@name,'quantity')]");
    private By updatebutton = By.xpath("//i[@class='fa fa-refresh']");
    private By coupondrop = By.xpath("//a[normalize-space()='Use Coupon Code']");
    private By couponInput = By.xpath("//input[@id='input-coupon']");	
    private By applyCouponBtn = By.xpath("//input[@id='button-coupon']");
    private By checkout = By.xpath("//i[@class='fa fa-share']");
    private By couponSuccessMsg = By.cssSelector(".alert-success");
   
    
    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

   

    // -------------------- Add Product --------------------
    public void searchProduct(String productName) {
        elementUtil.sendKeys(driver.findElement(searchInput), productName);
        elementUtil.click(driver.findElement(searchButton));
    }

    public void selectProduct(String productName) {
        List<WebElement> products = driver.findElements(productLink);
        for (WebElement product : products) {
            if (product.getText().equalsIgnoreCase(productName)) {
                elementUtil.click(product);
                break;
            }
        }
    }

    public void addProductToCart(String productName) {
        searchProduct(productName);
        selectProduct(productName);
        elementUtil.click(driver.findElement(addToCartBtn));
        WaitUtil.waitForVisibility(driver, cartTotal, 5);
    }

    
    // -------------------- Remove Product --------------------
    public void removeProductFromCart() {
    	 elementUtil.click(driver.findElement(shoppingcart));
    	 elementUtil.click(driver.findElement(shoppingcartremove));
        
    }

    // -------------------- Update Quantity --------------------
    public void updateProductQuantity(String num) throws InterruptedException {
    	
    	 elementUtil.click(driver.findElement(shoppingcart));
    	 
    	 Thread.sleep(2000);
    	 
    	 elementUtil.sendKeys(driver.findElement(updatequantity), num);
    	 
    	 Thread.sleep(2000);
    	 
    	 elementUtil.click(driver.findElement(updatebutton));
    	 
        
    }

    

    
    //Coupon
    
    public void applyCoupon(String couponCode) throws InterruptedException {
    	elementUtil.click(driver.findElement(shoppingcart));
    	
    	elementUtil.click(driver.findElement(coupondrop));
    	
    	Thread.sleep(2000);
    	
        elementUtil.sendKeys(driver.findElement(couponInput), couponCode);
        Thread.sleep(2000);
        
        elementUtil.click(driver.findElement(applyCouponBtn));
       
    }

   
    // -------------------- Checkout --------------------
    public void EmptyCart() {
        elementUtil.click(driver.findElement(checkout));
        
    }


    // -------------------- Success Messages --------------------
    public String getCartSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(couponSuccessMsg)).getText().trim();
    }

    public String getCartUpdateMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='content']//p[contains(text(),'Your shopping cart is empty!')]"))).getText().trim();
    }
    
    public String updatecart() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success alert-dismissible']"))).getText().trim();
    }
    
    public String errorcart() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger alert-dismissible']"))).getText().trim();
    }
    
    public String checkoutcart() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='content']//p[contains(text(),'Your shopping cart is empty!')]"))).getText().trim();
    }
    
  
}
