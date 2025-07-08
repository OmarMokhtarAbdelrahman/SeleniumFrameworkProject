package Omar.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Omar.PageObjects.CartPage;
import Omar.TestComponents.BaseTest;

public class errorValidationTest extends BaseTest {

	String validEmail = "mohamedkhan@gmail.com";
	 String validPassword = "mohamedKhan@123";
	 String invalidEmail = "mohamedkhan@gmil.com";
	 String invalidPassword = "mohamedKhan@1234";
	
	@Test
	public void emailErrorValidation() {
		
		loginPage.enterEmail(invalidEmail);
		loginPage.enterPassword(validPassword);
		loginPage.pressLogin();
		boolean result =loginPage.isErrorToastDisplayed();
		Assert.assertTrue(result);
		
	}
	
	@Test
	public void passwordErrorValidation() {
		
		loginPage.enterEmail(validEmail);
		loginPage.enterPassword(invalidPassword);
		loginPage.pressLogin();
		boolean result =loginPage.isErrorToastDisplayed();
		Assert.assertTrue(result);
		
	}
	
	@Test
	public void emptyCartErrorValidation() {
		
		loginPage.enterEmail(validEmail);
		loginPage.enterPassword(validPassword);
		loginPage.pressLogin();
		
		CartPage cartPage = loginPage.goToCart();
		boolean result = cartPage.verifyEmptyCart();

		Assert.assertTrue(result);
		
	}
}
