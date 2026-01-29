package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import basepage.BaseTest;
import pages.CategoryPage;
import pages.HomePage;
import pages.ProductPage;

public class HomeTest  extends BaseTest {
    
    

	  @Test  
	public void categoryCheck() {
	 HomePage hp= new HomePage(driver);
	 System.out.println("===== Categories on Home Page =====");
	 hp.getAllCategories().forEach(System.out::println);
     System.out.println("===================================");
	  
	 
     CategoryPage cp = hp.selectRandomCategory(); 	 
     System.out.println("===== Selected Category =====");
     System.out.println(driver.getTitle());
     System.out.println("===================================");

     cp.selectRandomSubCategoryIfPresent();
     System.out.println("===== Selected SubCategory =====");
     System.out.println(driver.getTitle());
     System.out.println("===================================");
     
     
     Assert.assertTrue(cp.isPageLoaded(), "Category page not loaded");
     Assert.assertTrue(cp.getProductCount() >= 4, "Less than 4 products displayed");

     System.out.println("Product Count: " + cp.getProductCount());
	 
     
      
     
     
     
     } 

	  
}