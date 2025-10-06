package UI;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.LoginPage;
import Utils.DBValidation;

public class CheckoutTests extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private CheckoutPage checkoutPage;
    private DBValidation dbValidation;   

    private final String PRODUCT_NAME = "iPhone";
    private final String USER_EMAIL = "cart@cart.com";
    private final String USER_PASSWORD = "demo";

    @BeforeMethod
    public void setupTest() {
        homePage = new HomePage(driver);
        checkoutPage = new CheckoutPage(driver);
        dbValidation = new DBValidation();   // ✅ initialize DBValidation
        openFrontEnd();
        loginPage = homePage.goToLoginPage();
        loginPage.login(USER_EMAIL, USER_PASSWORD);
    }

    // ---------------- CO_01: Place Order ----------------
    @Test(priority = 1)
    public void testPlaceOrder() throws InterruptedException {
        checkoutPage.addProductToCart(PRODUCT_NAME);
        checkoutPage.navigateToCheckoutPage();
        checkoutPage.fillBillingDetails("tim", "Doe", "123 Street", "City", "12345", "United Kingdom", "Angus");
        checkoutPage.selectDeliveryAddress("jim", "angus", "987 Street", "City", "54321", "Spain", "Lugo");
        checkoutPage.enterDeliveryMethod("Please deliver ASAP");
        Thread.sleep(2000);
        checkoutPage.selectPaymentMethod("Free Checkout");
        Thread.sleep(2000);
        checkoutPage.confirmOrder();

        System.out.println("Order Success Message: " + checkoutPage.getOrderSuccessMessage());

        //  DB Validation for Place Order
        int customerId = dbValidation.getCustomerIdByEmail(USER_EMAIL);
        boolean orderCreated = dbValidation.isOrderCreatedForCustomer(customerId);
        System.out.println("DB Check - Order Created: " + orderCreated);
    }

    // ---------------- CO_02: View Order History ----------------
    @Test(priority = 2)
    public void testViewOrderHistory() {
        checkoutPage.navigateToOrderHistoryPage();
        System.out.println("Viewing Order Completed");

        //  DB Validation for View Order History
        int customerId = dbValidation.getCustomerIdByEmail(USER_EMAIL);
        boolean hasOrders = dbValidation.isOrderCreatedForCustomer(customerId);
        System.out.println("DB Check - Orders Exist for Customer: " + hasOrders);
    }

    // ---------------- CO_03: ReOrder ----------------
    @Test(priority = 3)
    public void testReorder() {
        checkoutPage.navigateToOrderHistoryPage();
        checkoutPage.navigateToorderid();
        checkoutPage.clickReorder();

        System.out.println("ReOrder Success Message: " + checkoutPage.getReOrderSuccessMessage());

        //  DB Validation for ReOrder
        int customerId = dbValidation.getCustomerIdByEmail(USER_EMAIL);
        int latestOrderId = dbValidation.getLatestOrderIdForCustomer(customerId);
        boolean newOrderCreated = dbValidation.isOrderCreated(latestOrderId);
        System.out.println("DB Check - New Order Created after ReOrder: " + newOrderCreated);
    }

    // ---------------- CO_04: Return ----------------
    @Test(priority = 4)
    public void testReturn() throws InterruptedException {
        Thread.sleep(2000);
        checkoutPage.navigateToOrderHistoryPage();
        checkoutPage.navigateToorderid();
        Thread.sleep(2000);
        checkoutPage.clickReturn();
        System.out.println("Return Message: " + checkoutPage.getReturnMessage());

        // DB Validation for Return   
        int customerId = dbValidation.getCustomerIdByEmail(USER_EMAIL);
        int latestOrderId = dbValidation.getLatestOrderIdForCustomer(customerId);
        boolean returnUpdated = dbValidation.isLatestOrderReturnedForCustomer(customerId);
        System.out.println("DB Check - Order Return Updated: " + returnUpdated);

    }

    // ---------------- CO_05: Invalid Checkout ----------------
    @Test(priority = 5)
    public void testInvalidCheckout() throws InterruptedException {
        checkoutPage.addProductToCart(PRODUCT_NAME);
        checkoutPage.navigateToCheckoutPage();
        checkoutPage.fillBillingDetails("test", "", "123 Street", "City", "12345", "United Kingdom", "Angus");

        System.out.println("Field error");

        //  DB Validation: Ensure no invalid order was inserted
        int customerId = dbValidation.getCustomerIdByEmail(USER_EMAIL);
        boolean orderExists = dbValidation.isOrderCreatedForCustomer(customerId);
        System.out.println("DB Check - No invalid order created: " + !orderExists);
    }
}
