package tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import basepage.BaseTest;
import basepage.ProductDetails;
import pages.ProductPage;

public class ProductTest extends BaseTest {
	
	@Test
	public void productCheck() {
	ProductPage pp = new ProductPage(driver);
	
	WebElement product1 = pp.selectRandomProduct();
	ProductDetails details1 = pp.captureDetails(product1);
	pp.addProductToCart(product1);
	System.out.println("===== Selected product1 =====");
    System.out.println(driver.getTitle());
    System.out.println("===================================");

	WebElement product2 = pp.selectRandomProduct();
	ProductDetails details2 = pp.captureDetails(product2);
	pp.addProductToCart(product2);
	System.out.println("===== Selected product2 =====");
    System.out.println(driver.getTitle());
    System.out.println("===================================");
	}
}
