package UI;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import Utils.DBValidation;

public class CartTests extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private CartPage cartPage;
    private DBValidation db;

    private final String PRODUCT_NAME = "MacBook";
    private final String VALID_COUPON = "155";
    private final String INVALID_COUPON = "WRONGCODE";
    private final String USER_EMAIL = "cart@cart.com";
    private final String USER_PASSWORD = "demo";

    private int CUSTOMER_ID = 0;
    private int PRODUCT_ID = 0;

    @BeforeMethod
    public void setupTest() {
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        db = new DBValidation();

        openFrontEnd();
        loginPage = homePage.goToLoginPage();
        loginPage.login(USER_EMAIL, USER_PASSWORD);

        // Get dynamic IDs from DB
        CUSTOMER_ID = db.getCustomerIdByEmail(USER_EMAIL);
        PRODUCT_ID = db.getProductIdByName(PRODUCT_NAME);
    }

    // 1️ Add to Cart
    @Test(priority = 1)
    public void testAddToCart() {
        cartPage.addProductToCart(PRODUCT_NAME);
        System.out.println("Add to Cart Message: " + cartPage.getCartSuccessMessage());

        boolean inDB = db.isCartUpdated(CUSTOMER_ID, PRODUCT_ID);
        System.out.println("DB_SC_01 - Cart updated in DB? " + inDB);
        Assert.assertTrue(inDB, "DB validation failed: Product not added to cart in DB!");
    }

    // 2️ Remove from Cart
    @Test(priority = 2)
    public void testRemoveFromCart() {
        cartPage.addProductToCart(PRODUCT_NAME);
        cartPage.removeProductFromCart();
        System.out.println("Remove from Cart Message: " + cartPage.getCartUpdateMessage());

        boolean inDB = db.isCartUpdated(CUSTOMER_ID, PRODUCT_ID);
        System.out.println("DB_SC_02 - Cart updated in DB after removal? " + inDB);
        Assert.assertFalse(inDB, "DB validation failed: Product not removed from cart in DB!");
    }

    // 3️ Update Quantity
    @Test(priority = 3)
    public void testUpdateQuantity() throws InterruptedException {
        cartPage.addProductToCart(PRODUCT_NAME);
        int newQty = 15;
        cartPage.updateProductQuantity(String.valueOf(newQty));
        System.out.println("Updated Quantity UI: " + cartPage.updatecart());

        boolean updated = db.isCartQuantityUpdated(CUSTOMER_ID, PRODUCT_ID, newQty);
        System.out.println("DB_SC_03 - Cart quantity updated in DB? " + updated);
        Assert.assertTrue(updated, "DB validation failed: Cart quantity not updated in DB!");
    }

    // 4️ Apply Valid Coupon
    @Test(priority = 4)
    public void testApplyValidCoupon() throws InterruptedException {
        cartPage.addProductToCart(PRODUCT_NAME);
        cartPage.applyCoupon(VALID_COUPON);
        System.out.println("Coupon Success Message: " + cartPage.getCartSuccessMessage());

        
    }

    // 5️ Apply Invalid Coupon
    @Test(priority = 5)
    public void testApplyInvalidCoupon() throws InterruptedException {
        cartPage.addProductToCart(PRODUCT_NAME);
        cartPage.applyCoupon(INVALID_COUPON);
        System.out.println("Wrong Coupon Message: " + cartPage.errorcart());

        
    }

    // 6️ Empty Cart Checkout
    @Test(priority = 6)
    public void testEmptyCartCheckout() throws InterruptedException {
        // Clear any previous orders for this customer
        db.clearCustomerOrders(CUSTOMER_ID);

        // Ensure cart is empty
        cartPage.addProductToCart(PRODUCT_NAME);
        cartPage.removeProductFromCart();
        Thread.sleep(2000);

        cartPage.EmptyCart();
        System.out.println("Empty Cart Checkout Message: " + cartPage.checkoutcart());

        boolean orderExists = db.isOrderCreatedForCustomer(CUSTOMER_ID);
        System.out.println("DB_SC_06 - Order created for customer with empty cart? " + orderExists);
        Assert.assertFalse(orderExists, "DB validation failed: No order should be created for empty cart!");
    }
}
