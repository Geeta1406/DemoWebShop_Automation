package pages;



import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v127.page.model.Screenshot;
import utils.LoggerUtil;
import utils.ScreenshotUtil;
import utils.JsUtil;


public class HomePage    {
protected WebDriver driver ;
    private By categories = By.cssSelector("ul.top-menu > li > a");
    private By subCategories = By.cssSelector("div.sub-category-item h2 a");	
   
    private static final Logger log = LoggerUtil.getLogger(HomePage.class);
    
	public HomePage(WebDriver driver) {
	      this.driver= driver;
	}



	public List<String> getAllCategories() {
		List<WebElement> clist = driver.findElements( categories);
	
		  
		 categories.forEach(clist ->log.info("Category → " + clist.getText()));
		 log.info("Detected Categories:");
		  
		return clist.stream().map(WebElement::getText).toList();
		
				
	}
	public CategoryPage selectRandomCategory() {
		List<WebElement> clist = driver.findElements( categories);
        Random rand = new Random();
        WebElement randomCategory = clist.get(rand.nextInt(clist.size()));
        
        String categoryName = randomCategory.getText();
        
        // highlighting webelement 
        JsUtil.highlightElement(driver, randomCategory);
        
        randomCategory.click();
        ScreenshotUtil.capture(driver, categoryName);
        return new CategoryPage(driver, categoryName);
       
	}    
        
}
