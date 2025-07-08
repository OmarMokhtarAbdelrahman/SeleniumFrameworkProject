package Omar.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Omar.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	PurchaseInfoPage purchaseInfoPage;
	double actualTotal = 0;
	double price = 0;

	public CartPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	
	@FindBy(css ="[routerlink = '/dashboard/cart']")
	WebElement cartIcon;
	
	@FindBy(css = ".prodTotal p" )
	List<WebElement> productPrices;
	
	@FindBy(xpath = "//li[2]//span[2]")
	WebElement totalPrice;
	
	@FindBy(css = ".subtotal li button")
	WebElement checkoutButton;
	
	@FindBy(xpath = "//div[@aria-label='No Product in Your Cart']")
	WebElement emptyCartToast;
	
	By emptyCartToastBy = By.xpath("//div[@aria-label='No Product in Your Cart']");


	public boolean checkOnProductName(String productName) {

		boolean prod = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		return prod;
	}
	
	public double getPrintedTotal() {
		
		String printedTotalText = totalPrice.getText();
		double printedTotal = Double.parseDouble(printedTotalText.substring(1));
		
		return printedTotal;
	}
	
	public double verifyPricesAndTotal() {
		
		for(int i=0; i< productPrices.size(); i++) {
			
			String amount = productPrices.get(i).getText().substring(1);
			price = Double.parseDouble(amount);
			
			actualTotal += price;
			
		}
		
		return actualTotal;
	}
	
	public boolean verifyEmptyCart() {
		
		waitForElementToAppear(emptyCartToastBy);
		boolean isDisplayed = emptyCartToast.isDisplayed();
		
		return isDisplayed;
	}
	
	public PurchaseInfoPage goToPurchaseInfoPage() {
		

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkoutButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutButton);
		 purchaseInfoPage = new PurchaseInfoPage(driver);
		
		return purchaseInfoPage;
	}

	
}
