package pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basepage.ProductDetails;
import utils.JsUtil;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class ProductPage {
	
	 private WebDriver driver;
	 private WebDriverWait wait;
	 
	 private By productCards = By.cssSelector(".product-item");
	 //product page loctor //
	 private By name = By.cssSelector("h2.product-title a");
	    private By price = By.cssSelector("span.price.actual-price");
	    private By addToCartBtn = By.cssSelector("input.product-box-add-to-cart-button");

		
	 // susseful addedto cart message Success notification//
	    private By successMsg = By.id("bar-notification");
	
	    private static final Logger log =
	            LoggerUtil.getLogger(ProductPage.class);
	    
	    
	public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

	
    // Wait until product page is loaded
    public boolean isPageLoaded() {
    	
    	 return wait.until(ExpectedConditions.visibilityOfElementLocated(name))
                 .isDisplayed();
    	 
    }
    
    // // ===== Get All Products =====//
    public List<WebElement> getAllProducts() {
        return wait.until( ExpectedConditions.visibilityOfAllElementsLocatedBy(productCards));
    }
    
    // radom selection for product//
    public WebElement selectRandomProduct() {
        	Random random = new Random();
        List<WebElement> products = getAllProducts();
        return products.get(random.nextInt(products.size()));
    }
    
    //capture deatils //
    public ProductDetails captureDetails(WebElement product) {
    	JsUtil.highlightElement(driver, product);
    	
    	String productName = driver.findElement(name).getText();
        String productPrice = driver.findElement(price).getText();
        String productUrl = driver.getCurrentUrl();

        return new ProductDetails(productName, productPrice, productUrl);
        
    }
    
    // ===== Add Selected Product to Cart =====
    public boolean addProductToCart(WebElement product) {
        try {
             WebElement addBtn = product.findElement(addToCartBtn);
            JsUtil.highlightElement(driver, addBtn);
            addBtn.click();
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(successMsg));
            
            log.info("Added to cart successfully");
            return true;
        } catch (Exception e) {
        
        	log.warn("Skipped: Add to Cart Not Available");
        	
            return false;
        }
      
    }
   }
