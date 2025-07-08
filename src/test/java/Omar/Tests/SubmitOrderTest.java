package Omar.Tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Omar.PageObjects.CartPage;
import Omar.PageObjects.ConfirmationPage;
import Omar.PageObjects.OrderHistoryPage;
import Omar.PageObjects.ProductsPage;
import Omar.PageObjects.PurchaseInfoPage;
import Omar.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

		
		 
		 String invalidEmail = "mohamedkhan@gmil.com";
		 String invalidPassword = "mohamedKhan@1234";
		 double actualTotal = 0;
		 double printedTotal = 0;
		
		
		@Test(dataProvider = "getData")
		public void submitOrder(HashMap<String , String> inputData) throws InterruptedException {
			
		loginPage.enterEmail(inputData.get("validEmail"));
		loginPage.enterPassword(inputData.get("validPassword"));
		
		ProductsPage prodPage = loginPage.pressLogin();		
		
		prodPage.addProductToCart(inputData.get("productName1"));
		prodPage.addProductToCart(inputData.get("productName2"));

		CartPage cartPage = prodPage.goToCart();
		
		Boolean match1 = cartPage.checkOnProductName(inputData.get("productName1"));
		Assert.assertTrue(match1);
		
		Boolean match2 = cartPage.checkOnProductName(inputData.get("productName2"));
		Assert.assertTrue(match2);
		
		printedTotal = cartPage.getPrintedTotal();
		actualTotal = cartPage.verifyPricesAndTotal();
		
		Assert.assertEquals(actualTotal, printedTotal);
		
		PurchaseInfoPage purchaseInfoPage = cartPage.goToPurchaseInfoPage();
		
		purchaseInfoPage.selectCountry(inputData.get("country"));
		ConfirmationPage confirmationPage = purchaseInfoPage.goToFinalPage();
		
		String ConfirmMsg = confirmationPage.checkForCompleteOrder();
		Assert.assertTrue(ConfirmMsg.equalsIgnoreCase("Thankyou for the order."));
		
	}
		
		@Test(dependsOnMethods = "submitOrder" , dataProvider = "getData")
		public void checkingOrderHistory(HashMap<String , String> inputData) {
			
			loginPage.enterEmail(inputData.get("validEmail"));
			loginPage.enterPassword(inputData.get("validPassword"));
			
			loginPage.pressLogin();
			
			OrderHistoryPage orderHistoryPage =loginPage.goToOrderHistory();
			
			boolean match1 = orderHistoryPage.verifyOrderInHistory(inputData.get("productName1"));
			Assert.assertTrue(match1);
			
			boolean match2 = orderHistoryPage.verifyOrderInHistory(inputData.get("productName2"));
			Assert.assertTrue(match2);
			
			
			
		}
		
		
		@DataProvider
		public Object[][] getData() throws IOException {
			
			List<HashMap<String , String>> data = getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\Omar\\TestData\\testData.json");
			return new Object[][] {{data.get(0)},{data.get(1)}};

		}

}
