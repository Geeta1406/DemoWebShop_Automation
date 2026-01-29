package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.JsUtil;
import utils.LoggerUtil;
import utils.ScreenshotUtil;

public class CartPage {

	private WebDriver driver;
	private WebDriverWait wait;

	private By cartLink = By.cssSelector("a[href='/cart']");
	private By cartRows = By.cssSelector("tr.cart-item-row");
	private By productNames = By.cssSelector("td.product a");
	private By unitPrices = By.cssSelector("span.product-unit-price");
	private By quantities = By.cssSelector("input.qty-input");
	private By subtotals = By.cssSelector("span.product-subtotal");
	private By cartTotal = By.cssSelector("span.value-summary strong");

	private By checkoutCheckbox = By.id("termsofservice");
	private By checkoutBtn = By.id("checkout");
	private By removeHeader = By.xpath("//th[normalize-space()='Remove']");
	private By removeQty= By.name("itemquantity6201464");
	private By emptyCartMsg = By.cssSelector("div.order-summary-content");

	private static final Logger log =
			LoggerUtil.getLogger(CartPage.class);



	public CartPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// ===== Open Cart =====
	public void openCart() {
		wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
	}

	// ===== Validate Item Count =====
	public void validateItemCount(int expectedCount) {
		List<WebElement> items = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(cartRows));

		// highlight each item count//
		items.forEach(item -> JsUtil.highlightElement(driver, items));
		Assert.assertEquals(items.size(), expectedCount,"Mismatch in cart item count");        
	}

	// ===== Get Cart Product Names =====
	public List<String> getCartProductNames() {
		List<String> names = new ArrayList<>();
		for (WebElement e : driver.findElements(productNames)) {
			names.add(e.getText());
		}
		return names;
	}

	// ===== Calculate Expected Total =====
	public double calculateExpectedTotal() {
		
		double total = 0.0;
		for (WebElement e : driver.findElements(subtotals)) 
		{
			total += Double.parseDouble( e.getText().replaceAll("[^0-9.]", "") );
		}
		log.info("Expected Total = " + total);
		return total;
		
	} 

	public int getCartItemCount() {
		return driver.findElements(cartRows).size();
	}


	// ===== Validate Cart Total =====
	public void validateTotalPrice() {
		double displayedTotal = Double.parseDouble( driver.findElement(cartTotal).getText().replaceAll("[^0-9.]", ""));
		Assert.assertEquals(displayedTotal, calculateExpectedTotal(), "Cart total mismatch");

		
		  log.info("Displayed Total = " +displayedTotal);


	}  


	// ===== Proceed to Checkout =====
	public void proceedToCheckout() {
		wait.until(ExpectedConditions.elementToBeClickable(checkoutCheckbox)).click();
		wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
	} 


	//-- remove items/product  from cart---//
	public void removeProduct()
	{
		int beforeCount = getCartItemCount();

		try {

			driver.findElement(By.name("removefromcart")).click();

			WebElement REmoveHeader= driver.findElement(removeHeader);

			//highlighting remove header //
			JsUtil.highlightElement(driver, REmoveHeader);
			REmoveHeader.click();

		} catch(Exception e) {
			System.out.println("remove cart does not work ,set the qty input =0");


			// Screenshot as remove fail with remove button//
			ScreenshotUtil.capture(driver, "RemoveCheckboxFailed");
		}

		if(getCartItemCount()== beforeCount) {

			WebElement qty = wait.until(ExpectedConditions.visibilityOfElementLocated(removeQty));
			qty.clear();
			qty.sendKeys("0");

			//keyword action enter key //
			qty.sendKeys(Keys.ENTER);
		}
	}

	//-- check after removal cart get empty or not--//
	public boolean isCartEmpty() {
		return driver.findElement(emptyCartMsg).getText().contains("Your Shopping Cart is empty");
	}
}
