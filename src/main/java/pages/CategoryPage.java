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

import utils.JsUtil;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class CategoryPage {

	private String categoryName;
	private WebDriver driver;
	List<WebElement> products;
	
	 private By subCategories = By.cssSelector("div.sub-category-item h2 a");
	private By productCards = By.cssSelector(".product-item");

	private static final Logger log =
	        LoggerUtil.getLogger(CategoryPage.class);
	
	public CategoryPage(WebDriver driver, String categoryName) {
        this.driver = driver; 
        this.categoryName = categoryName;
    }
	
	
	
	 public void selectRandomSubCategoryIfPresent() {
		 Random random = new Random();
	        List<WebElement> subs = driver.findElements(subCategories);

	        if (!subs.isEmpty()) {
	            WebElement selected =
	                    subs.get(random.nextInt(subs.size()));
	            JsUtil.highlightElement(driver, selected);
	            selected.click();
	            ScreenshotUtil.capture(driver, categoryName);
	        }
	    }
	
	 public boolean isPageLoaded() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        return wait.until(ExpectedConditions.titleContains(categoryName));
	       
	    }
	 
	 public int getProductCount() {
	        
	        	int count= driver.findElements(productCards).size();
	        log.info("Product count in category [" +
	                categoryName + "] = " + count);
	        
	        return count;
	    }

	    public List<WebElement> getProductElements() {
	    	List<WebElement> products =
	                driver.findElements(productCards);
	        JsUtil.highlightElement(driver, products);//listWEBelemnet  check
	        return products;
	    }
		
	}


