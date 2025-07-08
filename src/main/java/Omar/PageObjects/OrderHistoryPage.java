package Omar.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Omar.AbstractComponents.AbstractComponent;

public class OrderHistoryPage extends AbstractComponent {

	WebDriver driver;
	
	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderHistoryList;
	
	By orderHistoryListBy = By.cssSelector("tr td:nth-child(3)");
	
	public List<WebElement> getOrderHistory() {
		waitForElementToAppear(orderHistoryListBy);
		return orderHistoryList;
	}
	
	public boolean verifyOrderInHistory(String productName) {
		boolean match = getOrderHistory().stream().anyMatch(order -> order.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
		
	
}
