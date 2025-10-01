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
    	
    	System.out.println(driver.getCurrentUrl());

    	// navigate to Products page after login

        productPage = new ProductPage(driver);
        productPage.goToProductsPage();
    }

    
  @Test (priority =1)
    public void testAddProduct() throws InterruptedException {
        productPage.addProduct("Test Product", "Test title", "T001",  "15", "100");
        
       
        
        String msg = productPage.getSuccessMessage();
        Assert.assertTrue(msg.contains("Success"), "Product not added successfully");
        System.out.println("Add Product: " + msg);
        
        Thread.sleep(2000);
        
        
    }

    @Test (priority =2)
    public void testEditProduct() throws InterruptedException {
    	productPage.editProductByName("Test Product", "150");
        String msg = productPage.getSuccessMessage();
        Assert.assertTrue(msg.contains("Success"), "Product not edited successfully");
        System.out.println("Edit Product: " + msg);
        
        Thread.sleep(2000);
    }


   @Test (priority =3)
    public void testSearchProduct() throws InterruptedException  {
        productPage.searchProduct("Test Product");
        
        List<WebElement> rows = productPage.getAllProductRows();
        Assert.assertTrue(rows.size() > 0, "No products found!");
        System.out.println("Search Results:");
        for (WebElement row : rows) {
         
        	
        	System.out.println(row.getText());
        	
        	Thread.sleep(2000);
        	
        	
        }
    }

    
  @Test (priority =4)
    public void testFilterProducts() throws InterruptedException  {
        productPage.filterProducts("Canon EOS 5D");
        List<WebElement> rows = productPage.getAllProductRows();
        Assert.assertTrue(rows.size() > 0, "No products found after filter!");
        System.out.println("Filtered Products:");
        for (WebElement row : rows) {
            System.out.println(row.getText());
            
            Thread.sleep(2000);
        }
    }

  @Test (priority =5)
    public void testInvalidSearch() throws InterruptedException  {
        productPage.searchProduct("InvalidProduct");
        Assert.assertTrue(productPage.isNoProductFound(), "Invalid product was found!");
        System.out.println("Invalid product : Invalid search shows no products as expected.");
        Thread.sleep(2000);
      
    }
  
  

  @Test (priority =6)
  public void testDeleteProduct() throws InterruptedException {
  	productPage.deleteProductByName("Test Product");
      String msg = productPage.getSuccessMessage();
      Assert.assertTrue(msg.contains("Success"), "Product not deleted successfully");
      System.out.println("Delete Product: " + msg);
      Thread.sleep(2000);
  }
}
