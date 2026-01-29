package tests;

import org.testng.annotations.Test;

import basepage.BaseTest;
import pages.RegistrationPage;

public class RegistrationTest extends BaseTest {
	
	
   @Test
	public void RegistrationValidation() {
		
	RegistrationPage rp = new RegistrationPage(driver);
	rp.OpenRegistration();
	rp.FieldRegistration("", "", "test@test.com", "Password@123", "Password@123");
	
	rp.GenderSelection("female");
	

	}
}
