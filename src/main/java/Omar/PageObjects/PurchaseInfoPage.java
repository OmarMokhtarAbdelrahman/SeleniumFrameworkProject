package Omar.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Omar.AbstractComponents.AbstractComponent;

public class PurchaseInfoPage extends AbstractComponent {

	WebDriver driver;
	ConfirmationPage confirmationPage;
	
	public PurchaseInfoPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = "input[placeholder = 'Select Country']")
	WebElement countryInput;
	
	@FindBy(css = ".ta-item")
	List<WebElement> countries;
	
	@FindBy(css = ".action__submit")
	WebElement placeOrderBtn;
	
	By countriesList = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		
		countryInput.sendKeys(countryName);
		waitForElementToAppear(countriesList);
		
		for(WebElement country : countries) {
			if(country.getText().equalsIgnoreCase(countryName)) {
				country.click();
				break;
			}
		}
		
	}
	
	
	public ConfirmationPage goToFinalPage() {
		placeOrderBtn.click();
		confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
		
	}
}
