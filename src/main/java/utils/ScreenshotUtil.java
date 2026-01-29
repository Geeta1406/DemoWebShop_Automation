package utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	
	    public static void capture(WebDriver driver, String testName)
	    {
	    	  try 
	    	  {
	    		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	    		File dest = new File("screenshots/" + testName + "_" + timestamp + ".png");
	    		FileUtils.copyFile(src, dest);
	    		} catch (IOException e) 
	    	  {
	    		e.printStackTrace();
	    	  }
	    }
	

}
