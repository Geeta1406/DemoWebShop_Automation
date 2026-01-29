package tests;

import org.apache.commons.logging.Log;

import pages.HomePage;
import pages.ProductPage;
import pages.RegistrationPage;

public class RepeatFlow {
	
	public void repeatFlowWithoutCartRemoval() {

	    Log.info("Starting repeated flow (skip remove cart)");

	    HomePage hp = new HomePage(driver);
	    hp.getAllCategories().forEach(System.out::println);
	    ProductPage pp = new ProductPage(driver);
	    pp.selectProduct();
	    pp.addToCart();

	    RegistrationPage rp = new RegistrationPage(driver);
	    rp.registerUser();

	    Log.info("Repeated flow completed successfully");
	}

}
