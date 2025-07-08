package Omar.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Omar.AbstractComponents.AbstractComponent;

public class ProductsPage extends AbstractComponent {

	WebDriver driver;
	CartPage cartPage;

	public ProductsPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".card-body")
	List<WebElement> products;


	By productsBy = By.cssSelector(".card-body");
	By addToCartButton = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	@FindBy(css = ".ng-animating")
	WebElement loadingPage;
	
	@FindBy(css ="[routerlink = '/dashboard/cart']")
	WebElement cartIcon;
	
	

	protected List<WebElement> getProductsList() {

		waitForElementToAppear(productsBy);
		return products;
	}

	protected WebElement getProductByName(String productName) {

		WebElement prod = getProductsList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		return prod;

	}

	public void addProductToCart(String productName) throws InterruptedException {

		WebElement prod = getProductByName(productName);
		prod.findElement(addToCartButton).click();
		Thread.sleep(1000); // Wait for the toast message to appear
		//waitForElementToAppear(toastMessage);
		//waitForElementToDisappear(loadingPage);
		
	}
	
	public CartPage goToCart() {
		cartIcon.click();
		cartPage = new CartPage(driver);
		
		return cartPage;
	}
	
}
