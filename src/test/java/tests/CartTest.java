package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import basepage.BaseTest;
import jdk.internal.org.jline.utils.Log;
import pages.CartPage;

public class CartTest extends BaseTest {
	
     @Test
	public void verifyCartHasProducts()
	{
		CartPage cp= new CartPage(driver);
		  int cartItemCount = cp.getCartItemCount();
		cp.openCart();
		cp.getCartProductNames();
		
		// check cart has product/item or not//
		 Assert.assertTrue( cartItemCount > 0, "Cart is empty, expected at least one product");
		 System.out.println("✅ Cart contains " + cartItemCount + " product(s)");
		 
		 cp.calculateExpectedTotal();
		cp.validateItemCount(3);
		
	}
     
    @Test
    public void removeProductFromCart() {
    	  CartPage cp = new CartPage(driver);
    	  int beforeRemove = cp.getCartItemCount();
    	  cp.removeProduct();
    	  
    	  int afterRemove = cp.getCartItemCount();
    	  cp.validateItemCount(0);
    	 
    	  Assert.assertTrue(afterRemove < beforeRemove || cp.isCartEmpty(),  "Remove from cart functionality FAILED");
    	  System.out.println(" Remove from cart validated successfully");
    	  Log.info("Remove from cart validated successfully");
    	  
    }
}

