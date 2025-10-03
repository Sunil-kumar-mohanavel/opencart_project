package UI;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;

public class CartTests extends BaseTest {
 
    private HomePage homePage;
    private LoginPage loginPage;
    private CartPage cartPage;

    private final String PRODUCT_NAME = "MacBook";
    private final String VALID_COUPON = "155";
    private final String INVALID_COUPON = "WRONGCODE";
    private final String USER_EMAIL = "cart@cart.com";
    private final String USER_PASSWORD = "demo";

    @BeforeMethod
    public void setupTest() {
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        openFrontEnd();
        loginPage = homePage.goToLoginPage();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
    }

    // 1. Add to Cart
 
    @Test(priority = 1)
    public void testAddToCart() {
        cartPage.addProductToCart(PRODUCT_NAME);
       
       
        System.out.println("Add to Cart Message: " + cartPage.getCartSuccessMessage());
    }

    // 2. Remove Product from Cart
  
   @Test(priority = 2)
    public void testRemoveFromCart() {
        cartPage.addProductToCart(PRODUCT_NAME);
       
        cartPage.removeProductFromCart();
       
        System.out.println("Remove from Cart Message: " + cartPage.getCartUpdateMessage());
    }

    // 3. Update Quantity
   
    @Test(priority = 3)
    public void testUpdateQuantity() throws InterruptedException {
        cartPage.addProductToCart(PRODUCT_NAME);
       
        cartPage.updateProductQuantity("15");
        
        System.out.println("Updated Quantity: " +cartPage.updatecart());
    }

    // 4. Apply Valid Coupon
  
    @Test(priority = 4)
    public void testApplyValidCoupon() throws InterruptedException {
        cartPage.addProductToCart(PRODUCT_NAME);
        
        cartPage.applyCoupon(VALID_COUPON);
       
        System.out.println("Coupon Success Message: " + cartPage.getCartSuccessMessage());
    }

    // 5. Apply Invalid Coupon
    @Test(priority = 5)
    public void testApplyInvalidCoupon() throws InterruptedException {
    	
           cartPage.addProductToCart(PRODUCT_NAME);
        
        cartPage.applyCoupon(INVALID_COUPON);
       
        System.out.println("Wrong Coupon Message: " + cartPage.errorcart());
    }

    // 6. Navigate to Checkout with Empty Cart
   
    @Test(priority = 6)
    public void testEmptyCartCheckout() throws InterruptedException {
    	 cartPage.addProductToCart(PRODUCT_NAME);
    	 
    	 cartPage.removeProductFromCart();
    	 
    	 Thread.sleep(2000);
    	 
        cartPage.EmptyCart(); 
        
        System.out.println("Empty Cart Checkout Message: " +cartPage.checkoutcart() );
    }
}
