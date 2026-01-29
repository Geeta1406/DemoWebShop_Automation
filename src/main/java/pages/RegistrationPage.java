package pages;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.JsUtil;
import utils.LoggerUtil;

public class RegistrationPage {
	private WebDriver driver;
	private WebDriverWait wait;

	private By registerLink = By.cssSelector("a[href='/register']");
	private By maleRadio = By.id("gender-male");
	private By femaleRadio = By.id("gender-female");
	private By FirstName= By.id("FirstName");
	private By LastName = By.id("LastName");
	private By Email = By.id("Email");
	private By Password = By.id("Password");
	private By ConfirmPass =By.id("ConfirmPassword");
	private By successMessage= By.className("result");
	private By RegisterBtn =By.id("register-button");
	private By errorMsg = By.className("validation-summary-errors");


    private static final Logger log =
            LoggerUtil.getLogger(RegistrationPage.class);



	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// open registration  form //
	public void OpenRegistration() {
		
		wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();

	}

	public void GenderSelection(String gender) {
		
		if(gender.equalsIgnoreCase("male")) {
			click(maleRadio);
		}
		else if(gender.equalsIgnoreCase("female")) {
			click(femaleRadio);
		}
		else {
			throw new IllegalArgumentException("Invalid gender: " +gender);
		}
	}

	private void click(By locator) {
		
		WebElement element =	wait.until(ExpectedConditions.elementToBeClickable(locator));
		JsUtil.highlightElement(driver, element);
		element.click();
        log.info("page is loaded" );
	}




	public void FieldRegistration(String fName, String lName ,String mail ,String Cpass, String pass)
	{
		driver.findElement(FirstName).clear();
		driver.findElement(FirstName).sendKeys(fName);
		driver.findElement(LastName).clear();
		driver.findElement(LastName).sendKeys(lName);
		driver.findElement(Email).clear();
		driver.findElement(Email).sendKeys(mail);
		driver.findElement(Password).clear();
		driver.findElement(Password).sendKeys(pass);
		driver.findElement(ConfirmPass).clear();
		driver.findElement(ConfirmPass).sendKeys(Cpass);	
	}

	// ===== Validations =====
	public boolean isRegistrationSuccessful() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText().contains("Your registration completed");
	}

	public boolean isErrorDisplayed() {
		
		boolean error =wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg)).isDisplayed();

		if (error) {
			log.error("Registration validation error displayed");
		}
		return error;
	}


}
