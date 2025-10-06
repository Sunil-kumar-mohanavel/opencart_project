package UI;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.ProductPage;
import Utils.DBValidation;

public class ProductTests extends BaseTest {

    private ProductPage productPage;
    private DBValidation db;

    @BeforeMethod
    public void setUpTest() {
        openAdmin();
        loginToAdmin();

        System.out.println("Admin URL: " + driver.getCurrentUrl());

        productPage = new ProductPage(driver);
        productPage.goToProductsPage();

        db = new DBValidation();
    }

    // ------------------- DB_PC_01: Add Product -------------------
    @Test(priority = 1)
    public void testAddProduct() throws InterruptedException {
        String productName = "Test Product";
        String price = "100";

        productPage.addProduct(productName, "Test title", "T001", "15", price);

        // UI validation
        String uiMsg = productPage.getSuccessMessage();
        System.out.println("DB_PC_01_AddProduct - UI Message: " + uiMsg);
        Assert.assertTrue(uiMsg.contains("Success"), "Product not added successfully on UI!");

        // DB validation
        boolean dbExists = db.isProductPresent(productName, price);
        System.out.println("DB_PC_01_AddProduct - Product present in DB? " + dbExists);
        Assert.assertTrue(dbExists, "Product record not found in DB!");
    }

 // ------------------- DB_PC_02: Edit Product -------------------
    @Test(priority = 2)
    public void testEditProduct() throws InterruptedException {
        String productName = "Test Product";
        String updatedPrice = "150";

        productPage.editProductByName(productName, updatedPrice);

        // ---------- UI Validation ----------
        String uiMsg = productPage.getSuccessMessage();
        System.out.println("DB_PC_02_EditProduct - UI Message: " + uiMsg);
        Assert.assertTrue(uiMsg.contains("Success"), "Product not updated successfully on UI!");

        // ---------- DB Validation ----------
        boolean dbUpdated = db.isProductUpdated(productName, Double.parseDouble(updatedPrice));
        System.out.println("DB_PC_02_EditProduct - Updated product in DB? " + dbUpdated);
        Assert.assertTrue(dbUpdated, "Product price not updated in DB!");
    }

    // ------------------- DB_PC_03: Delete Product -------------------
    @Test(priority = 6)
    public void testDeleteProduct() throws InterruptedException {
        String productName = "Test Product";

        productPage.deleteProductByName(productName);

        // UI validation
        String uiMsg = productPage.getSuccessMessage();
        System.out.println("DB_PC_03_DeleteProduct - UI Message: " + uiMsg);
        Assert.assertTrue(uiMsg.contains("Success"), "Product not deleted successfully on UI!");

        // DB validation
        boolean dbExists = db.isProductPresent(productName, null);
        System.out.println("DB_PC_03_DeleteProduct - Product present in DB? " + dbExists);
        Assert.assertFalse(dbExists, "Deleted product still exists in DB!");
    }

    // ------------------- DB_PC_04: Search Product -------------------
    @Test(priority = 3)
    public void testSearchProduct() throws InterruptedException {
        String productName = "Test Product";

        productPage.searchProduct(productName);

        // UI validation
        List<WebElement> rows = productPage.getAllProductRows();
        Assert.assertTrue(rows.size() > 0, "No products found in UI search!");
        System.out.println("DB_PC_04_SearchProduct - UI Search Results:");
        for (WebElement row : rows) {
            System.out.println(row.getText());
        }

        // DB validation
        boolean dbExists = db.isProductPresent(productName, null);
        System.out.println("DB_PC_04_SearchProduct - Product present in DB? " + dbExists);
        Assert.assertTrue(dbExists, "Product not found in DB!");
    }

    // ------------------- DB_PC_05: Filter Products -------------------
    @Test(priority = 4)
    public void testFilterProducts() throws InterruptedException {
        String productName = "Canon EOS 5D";

        productPage.filterProducts(productName);

        // UI validation
        List<WebElement> rows = productPage.getAllProductRows();
        Assert.assertTrue(rows.size() > 0, "No products found after filter!");
        System.out.println("DB_PC_05_FilterProducts - Filtered UI Results:");
        for (WebElement row : rows) {
            System.out.println(row.getText());
        }

        // DB validation
        boolean dbExists = db.isProductPresent(productName, null);
        System.out.println("DB_PC_05_FilterProducts - Product present in DB? " + dbExists);
        Assert.assertTrue(dbExists, "Filtered product not found in DB!");
    }

 // ------------------- DB_PC_06: Invalid Search -------------------
    @Test(priority = 5)
    public void DB_PC_06_InvalidSearch() throws InterruptedException {
        String invalidProduct = "InvalidProduct";

        productPage.searchProduct(invalidProduct);

        // ---------- UI Validation ----------
        boolean noProductUI = productPage.isNoProductFound();
        System.out.println("DB_PC_06_InvalidSearch - UI shows no product? " + noProductUI);

        try {
            if (!noProductUI) {
                // Fail if invalid product appears in UI — take screenshot
                String screenshotPath = Utils.ScreenshotUtil.captureScreenshot(driver, "DB_PC_06_InvalidSearch_UIFail");
                System.out.println("Screenshot captured for UI validation failure: " + screenshotPath);
                Assert.fail("UI validation failed: Invalid product displayed in UI!");
            } else {
                System.out.println("UI validation passed: No product displayed for invalid search.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception during UI validation: " + e.getMessage());
        }

        // ---------- DB Validation ----------
        boolean dbExists = db.isProductPresent(invalidProduct);
        System.out.println("DB_PC_06_InvalidSearch - Product present in DB? " + dbExists);

        try {
            if (dbExists) {
                System.out.println("DB validation failed: Invalid product record found in DB!");
                Assert.fail("DB validation failed: Invalid product record found in DB!");
            } else {
                System.out.println("DB validation passed: No record found for invalid product.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Exception during DB validation: " + e.getMessage());
        }
    }

}
