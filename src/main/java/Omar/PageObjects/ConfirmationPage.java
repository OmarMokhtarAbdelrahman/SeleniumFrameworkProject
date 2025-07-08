package Omar.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Omar.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	By toastMessage =  By.id("toast-container");
	
	@FindBy(css = ".hero-primary")
	WebElement confirmMsg;
	
	
	
	public String checkForCompleteOrder() {
		waitForElementToAppear(toastMessage);
		String conftimTest = confirmMsg.getText();
		
		return conftimTest;
	}
	
	
}
