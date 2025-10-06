package UI;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.RegistrationPage;
import Utils.DBValidation;

public class UserManagementTest extends BaseTest {

    private HomePage homePage; 
    private DBValidation db;

    @BeforeMethod
    public void setUpTest() {
        openFrontEnd(); // Navigate to front-end before starting tests
        homePage = new HomePage(driver);
        db = new DBValidation();
    }

    // ------------------- DB_UM_01: Verify Customer Login -------------------
    @Test
    public void DB_UM_01_VerifyCustomerLogin() throws InterruptedException {
        LoginPage loginPage = homePage.goToLoginPage();
        String email = "cart@cart.com";
        loginPage.login(email, "demo");

        // UI assertion
        boolean uiLoginPassed = driver.getTitle().contains("My Account");
        System.out.println("DB_UM_01_VerifyCustomerLogin - UI Login Passed? " + uiLoginPassed);
        Assert.assertTrue(uiLoginPassed, "Login failed on UI!");

        // DB validation
        boolean dbCustomerExists = db.isCustomerPresent(email);
        System.out.println("DB_UM_01_VerifyCustomerLogin - Customer present in DB? " + dbCustomerExists);
        Assert.assertTrue(dbCustomerExists, "Customer record NOT found in DB!");
    }

 // ------------------- DB_UM_02: Invalid Login Check -------------------
    @Test
    public void DB_UM_02_InvalidLoginCheck() throws InterruptedException {
        LoginPage loginPage = homePage.goToLoginPage();
        String invalidEmail = "wrong@mail.com";
        loginPage.login(invalidEmail, "wrongpass");

        // UI warning message
        String warning = loginPage.getWarningMessage();
        System.out.println("DB_UM_02_InvalidLoginCheck - UI Warning: " + warning);

        // Take screenshot if warning is not the standard invalid login warning
        try {
            String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
            if (!warning.equals(expectedWarning)) {
                // Capture screenshot for failure
               String screenshotPath = Utils.ScreenshotUtil.captureScreenshot(driver, "DB_UM_02_InvalidLoginCheck");
                System.out.println("Screenshot captured due to UI warning mismatch");

                // Fail the test intentionally for invalid login scenario
                Assert.fail("UI warning mismatch. Expected: '" + expectedWarning + "' but got: '" + warning + "'");
            } else {
                System.out.println("UI warning is correct for invalid login.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception occurred while validating UI warning: " + e.getMessage());
        }

        // DB validation: invalid email should not exist
        boolean dbCustomerExists = db.isCustomerPresent(invalidEmail);
        System.out.println("DB_UM_02_InvalidLoginCheck - Invalid customer present in DB? " + dbCustomerExists);

        if (dbCustomerExists) {
            System.out.println("DB validation failed: Invalid customer record found in DB!");
            Assert.fail("Invalid customer record found in DB!");
        } else {
            System.out.println("DB validation passed: No record found for invalid login.");
        }
    }


    // ------------------- DB_UM_03: New User Registration -------------------
    @Test 
    public void DB_UM_03_NewUserRegistration() throws InterruptedException {
        RegistrationPage registrationPage = homePage.goToRegistrationPage();
        String email = "test" + System.currentTimeMillis() + "@mail.com";
        registrationPage.registerUser("John", "Doe", email, "123123", "password123");

        // UI assertion
        String uiMessage = registrationPage.getSuccessMessage();
        System.out.println("DB_UM_03_NewUserRegistration - UI Success Message: " + uiMessage);
        Assert.assertEquals(uiMessage, "Your Account Has Been Created!");

        // DB validation
        boolean dbCustomerExists = db.isCustomerPresent(email);
        System.out.println("DB_UM_03_NewUserRegistration - Customer present in DB? " + dbCustomerExists);
        Assert.assertTrue(dbCustomerExists, "Newly registered customer NOT found in DB!");
    }
}
