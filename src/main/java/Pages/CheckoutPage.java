package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.ElementUtil;
import Utils.WaitUtil;

public class CheckoutPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    // -------------------- Locators --------------------
    private By searchInput = By.name("search");
    private By searchButton = By.xpath("//button[@class='btn btn-default btn-lg']");
    private By productLink = By.xpath("//div[@class='caption']/h4/a");
    private By billingFirstName = By.id("input-payment-firstname");
    private By addToCartBtn = By.id("button-cart");
    private By cartTotal = By.id("cart-total");
    private By checkoutbtn = By.xpath("//a[@title='Checkout']//i[@class='fa fa-share']");
    private By newadd = By.xpath("//input[@type='radio' and @name='payment_address' and @value='new']");
    
    private By shippingnew = By.xpath("//label[normalize-space()='I want to use a new address']//input[@name='shipping_address']");
    
    
    private By shippingFirstName = By.id("input-shipping-firstname");
    private By shippingLastName = By.id("input-shipping-lastname");
    private By shippingAddress = By.id("input-shipping-address-1");
    private By shippingCity = By.id("input-shipping-city");
    private By shippingPostcode = By.id("input-shipping-postcode");
    private By shippingCountry = By.id("input-shipping-country");
    private By shippingRegion = By.id("input-shipping-zone");
    
    
    
    
    private By billingLastName = By.id("input-payment-lastname");
    private By billingAddress = By.id("input-payment-address-1");
    private By billingCity = By.id("input-payment-city");
    private By billingPostcode = By.id("input-payment-postcode");
    private By billingCountry = By.id("input-payment-country"); 
    private By billingRegion = By.id("input-payment-zone");
    private By continueBillingBtn = By.xpath("//input[@id='button-payment-address']");
    private By shippingconbtn = By.xpath("//input[@id='button-shipping-address']");
    private By deliveryMethodTextArea = By.name("comment");
    private By continueDeliveryMethodBtn = By.id("button-shipping-method");

    private By paymentcomment = By.xpath("//div[@id='collapse-payment-method']//textarea[@name='comment']");
    private By agreeTermsCheckbox = By.name("agree");
    private By continuePaymentBtn = By.id("button-payment-method");

    private By confirmOrderBtn = By.xpath("//input[@id='button-confirm']");

    private By orderSuccessMessage = By.cssSelector("#content h1");
    
    private By ReorderSuccessMessage = By.xpath("//div[@class='alert alert-success alert-dismissible']");
    
    private By myAccountDropdown = By.xpath("//span[text()='My Account']");
    
    private By orderhis = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Order History']");

    
    private By orderidclick = By.xpath("//a[contains(@href,'order_id=54') and contains(@class,'btn-info')]");
   
    
   // private By orderHistoryLink = By.linkText("Order History");
    private By viewOrderBtn = By.xpath("//a[contains(text(),'View')]");

    // Reorder & Return
    private By reorderBtn = By.xpath("//td[@class='text-right']//a[@class='btn btn-primary']");
    private By returnBtn = By.xpath("//a[@class='btn btn-danger']");
    private By returnrea = By.xpath("//input[@value='3']");
    private By returnsubmit = By.xpath("//input[@value='Submit']");
    private By returnSuccessMsg = By.xpath("//h1[normalize-space()='Product Returns']");
    
    private By fielderror = By.xpath("//div[@class='text-danger' and contains(text(),'Last Name must be between 1 and 32 characters!')]");
    		

    // -------------------- Constructor --------------------
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);
    }

                           //Methods 

                        //  Add Product 
    
    
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

    
    
                        // Navigate
    
    public void navigateToCheckoutPage() {
    	 elementUtil.click(driver.findElement(checkoutbtn));
    }

   
    
    public void navigateToOrderHistoryPage() {
    	 driver.findElement(myAccountDropdown).click();
    	 driver.findElement(orderhis).click();
    	 
       
    }
    
    
   public void navigateToorderid() {
   	 driver.findElement(orderidclick).click();
   	 
   	 
      
   } 
    
   


    



                            //  Checkout Steps 
    
    public void fillBillingDetails(String firstName, String lastName,String address, String city, String postcode, String country, String region) throws InterruptedException {
    	 Thread.sleep(2000);
    	
    	
    	elementUtil.click(driver.findElement(newadd));
    	elementUtil.sendKeys(driver.findElement(billingFirstName), firstName);
        elementUtil.sendKeys(driver.findElement(billingLastName), lastName);
        elementUtil.sendKeys(driver.findElement(billingAddress), address);
        elementUtil.sendKeys(driver.findElement(billingCity), city);
        elementUtil.sendKeys(driver.findElement(billingPostcode), postcode);
        elementUtil.selectByVisibleText(driver.findElement(billingCountry), country);
        elementUtil.selectByVisibleText(driver.findElement(billingRegion), region);
        
        Thread.sleep(2000);
        elementUtil.click(driver.findElement(continueBillingBtn));
       
    }

   
    	 public void selectDeliveryAddress(String firstName, String lastName, String address, String city, String postcode, String country, String region) throws InterruptedException {
        	 Thread.sleep(2000);
        	
        
        	 
        	elementUtil.click(driver.findElement(shippingnew));
        	elementUtil.sendKeys(driver.findElement(shippingFirstName), firstName);
            elementUtil.sendKeys(driver.findElement(shippingLastName), lastName);
            elementUtil.sendKeys(driver.findElement(shippingAddress), address);
            elementUtil.sendKeys(driver.findElement(shippingCity), city);
            elementUtil.sendKeys(driver.findElement(shippingPostcode), postcode);
            
            elementUtil.selectByVisibleText(driver.findElement(shippingCountry), country);
            Thread.sleep(2000);
            elementUtil.selectByVisibleText(driver.findElement(shippingRegion), region);
            
            Thread.sleep(2000);
            elementUtil.click(driver.findElement(shippingconbtn));
            Thread.sleep(5000);
      
    }

    public void enterDeliveryMethod(String comment) {
        elementUtil.sendKeys(driver.findElement(deliveryMethodTextArea), comment);
        elementUtil.click(driver.findElement(continueDeliveryMethodBtn));
     
    }

    public void selectPaymentMethod(String method) {
       elementUtil.sendKeys(driver.findElement(paymentcomment),method);
        elementUtil.click(driver.findElement(agreeTermsCheckbox));
        elementUtil.click(driver.findElement(continuePaymentBtn));
      
    }

    public void confirmOrder() {
        elementUtil.click(driver.findElement(confirmOrderBtn));
     
    }

    public String getOrderSuccessMessage() {
        return elementUtil.getText(driver.findElement(orderSuccessMessage)).trim();
    }

    
    public String getReOrderSuccessMessage() {
        return elementUtil.getText(driver.findElement(ReorderSuccessMessage)).trim();
    }

                          //  Order History
    
    public void viewFirstOrder() {
        elementUtil.click(driver.findElement(viewOrderBtn));
    }

    public void clickReorder() {
        elementUtil.click(driver.findElement(reorderBtn));
    }

    public void clickReturn() {
        elementUtil.click(driver.findElement(returnBtn));
        elementUtil.click(driver.findElement(returnrea));
        elementUtil.click(driver.findElement(returnsubmit));
        
    }

    public String getReturnMessage() {
        return elementUtil.getText(driver.findElement(returnSuccessMsg)).trim();
    }
    
    
    public String getfieldMessage() {
        return elementUtil.getText(driver.findElement(fielderror)).trim();
    }
}
