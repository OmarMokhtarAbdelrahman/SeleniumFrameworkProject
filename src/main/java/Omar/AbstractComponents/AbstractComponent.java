package Omar.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Omar.PageObjects.CartPage;
import Omar.PageObjects.OrderHistoryPage;

public class AbstractComponent {

	WebDriverWait wait;
	WebDriver driver;
	
	

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
	}
	
	@FindBy(css = "button[routerlink = '/dashboard/cart']")
	WebElement cartIcon;
	
	@FindBy(css = "button[routerlink= '/dashboard/myorders'] ")
	WebElement orderHistoryBtn;

	public void waitForElementToAppear(By findBy) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	public void waitForElementToDisappear(WebElement element) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.invisibilityOf(element));

	}
	
	public CartPage goToCart() {
		cartIcon.click();
		CartPage cartPage = new CartPage(driver);
		
		return cartPage;
	}
	
	public OrderHistoryPage goToOrderHistory() {
		
		orderHistoryBtn.click();
		OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
		
		return orderHistoryPage;
	}
	
}
