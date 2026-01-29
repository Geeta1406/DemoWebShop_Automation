package utils;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsUtil {
	

	public static void highlightElement(WebDriver driver, WebElement element) {
		  JavascriptExecutor js = (JavascriptExecutor) driver;

	        js.executeScript(
	            "arguments[0].style.border='3px solid red';" +
	            "arguments[0].style.backgroundColor='yellow';",
	            element
	        );

	        pause();

	        js.executeScript(
	            "arguments[0].style.border=''; arguments[0].style.backgroundColor='';",
	            element
	        );
	    }

	    // Highlight list of elements
	    public static void highlightElement(WebDriver driver, List<WebElement> elements) {
	        for (WebElement e : elements) {
	            highlightElement(driver, e);
	        }
	    }

	    private static void pause() {
	        try {
	            Thread.sleep(250); // visual only
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }
	

}
