package UI;

import Base.BaseTest;
import Pages.ProductPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProductTests extends BaseTest {

    private ProductPage productPage;

    @BeforeMethod
    public void setUpTest() {
    	
    	openAdmin();
    	loginToAdmin();
        productPage = new ProductPage(driver);
    }

    @Test
    public void testAddProduct() {
        productPage.addProduct("Test Product", "Test Meta", "TP001", "100");
        String msg = productPage.getSuccessMessage();
        Assert.assertTrue(msg.contains("Success"), "Product not added successfully");
        System.out.println("Add Product: " + msg);
    }

    @Test
    public void testEditProduct() {
        productPage.editFirstProduct("150");
        String msg = productPage.getSuccessMessage();
        Assert.assertTrue(msg.contains("Success"), "Product not edited successfully");
        System.out.println("Edit Product: " + msg);
    }

    @Test
    public void testDeleteProduct() {
        productPage.deleteFirstProduct();
        String msg = productPage.getSuccessMessage();
        Assert.assertTrue(msg.contains("Success"), "Product not deleted successfully");
        System.out.println("Delete Product: " + msg);
    }

    @Test
    public void testSearchProduct() {
        productPage.searchProduct("Test Product");
        List<WebElement> rows = productPage.getAllProductRows();
        Assert.assertTrue(rows.size() > 0, "No products found!");
        System.out.println("Search Results:");
        for (WebElement row : rows) {
            System.out.println(row.getText());
        }
    }

    @Test
    public void testFilterProducts() {
        productPage.filterProducts("Test Product");
        List<WebElement> rows = productPage.getAllProductRows();
        Assert.assertTrue(rows.size() > 0, "No products found after filter!");
        System.out.println("Filtered Products:");
        for (WebElement row : rows) {
            System.out.println(row.getText());
        }
    }

    @Test
    public void testInvalidSearch() {
        productPage.searchProduct("InvalidProduct123");
        Assert.assertTrue(productPage.isNoProductFound(), "Invalid product was found!");
        System.out.println("Invalid search shows no products as expected.");
    }
}
