package UI;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.RegistrationPage;
 
public class UserManagementTest extends BaseTest {

    private HomePage homePage; 

    @BeforeMethod
    public void setUpTest() {
    	
    	openFrontEnd(); // Navigate to front-end before starting tests
        homePage = new HomePage(driver);
    }

    @Test
    public void testValidLogin() throws InterruptedException {
    	Thread.sleep(2000);
    	
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login("cart@cart.com", "demo");
        Assert.assertTrue(driver.getTitle().contains("My Account"), "Login passed!");
    	Thread.sleep(2000);
    }
    @Test
    public void testInvalidLogin() throws InterruptedException {
        Thread.sleep(2000); 

        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login("wrong@mail.com", "wrongpass");

        String warning = loginPage.getWarningMessage();
        System.out.println("testInvalidLogin: Warning message = " + warning);
     
        Thread.sleep(2000);
        
    }


    @Test 
    public void testUserRegistration() throws InterruptedException {
    	Thread.sleep(2000);
        RegistrationPage registrationPage = homePage.goToRegistrationPage();
        String email = "test" + System.currentTimeMillis() + "@mail.com";
        registrationPage.registerUser("John", "Doe", email, "123123", "password123");
        Assert.assertEquals(registrationPage.getSuccessMessage(), "Your Account Has Been Created!");
    	Thread.sleep(2000);
    }
}
