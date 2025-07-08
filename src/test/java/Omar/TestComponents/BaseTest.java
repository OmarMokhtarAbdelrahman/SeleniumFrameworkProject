package Omar.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Omar.PageObjects.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initializeDriver() throws IOException {
		// Code to initialize the WebDriver instance
		// This could include setting up browser options, capabilities, etc.

		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Omar\\Resources\\GlobalData.properties");

		prop.load(fis);

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();
		}
		
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.password_manager_leak_detection", false);
		options.setExperimentalOption("prefs", prefs);
		
		options.addArguments("–-incognito"); // to avoid stored passwords
		options.addArguments("–-disable-save-password-bubble");
		options.addArguments("–-no-default-browser-check");
		options.addArguments("–-disable-autofill-keyboard-accessory-view");
		options.addArguments("user-data-dir=C:/temp/chrome-test-profile");




		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {

		driver = initializeDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		loginPage = new LoginPage(driver);

		return loginPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		driver.quit();
	}
	
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		//System.getProperty("user.dir") + "\\src\\test\\java\\TestData\\eCommerceTestData.json"
		
		// Read the JSON file content
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);

		// Parse the JSON content into a List of HashMaps
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}
	
		public String getScreenshotPath(WebDriver driver ,String testCaseName) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\reports" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
		
	}
	

}
