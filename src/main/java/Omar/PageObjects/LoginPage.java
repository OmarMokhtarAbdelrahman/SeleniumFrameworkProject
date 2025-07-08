package Omar.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Omar.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {

	WebDriver driver;
	ProductsPage productsPage;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = "[type = 'email']")
	WebElement emailField;
	
	@FindBy(css = "#userPassword")
	WebElement passwordField;
	
	@FindBy(css = ".btn")
	WebElement loginButton;
	
	@FindBy(css = "div[role = 'alert']")
	WebElement errorToast;;
	
	By errorToastBy = By.cssSelector("div[role = 'alert']");
	
	
	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public boolean isErrorToastDisplayed() {
		
		waitForElementToAppear(errorToastBy);
		return errorToast.isDisplayed();
	}
		
	
	public ProductsPage pressLogin() {
		loginButton.click();
		productsPage = new ProductsPage(driver);
		return productsPage;
		
	}
}
