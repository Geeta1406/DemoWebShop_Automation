package basepage;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import utils.LoggerUtil;

public class BaseTest {
	  protected WebDriver driver;
	  WebDriverWait wait;
	  
	  private static final Logger log =
	            LoggerUtil.getLogger(BaseTest.class);
	    
	  
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/");
        log.info("website is opened ");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
	}
	
	@AfterMethod
	public void teardown() {
		if(driver!=null)
			driver.quit();
	}

}
