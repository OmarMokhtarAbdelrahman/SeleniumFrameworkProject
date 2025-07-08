package Omar.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports report = ExtentReportsConfig.reportConfig();
	ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		
		test =report.createTest(result.getMethod().getMethodName());
		threadLocal.set(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		threadLocal.get().log(Status.PASS, "Test Passed");
	}
	
	
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		threadLocal.get().fail(result.getThrowable());
		String filePath = null;
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		
		try {
			filePath = getScreenshotPath(driver , result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadLocal.get().addScreenCaptureFromPath( filePath , result.getMethod().getMethodName());
		}
	
	
	
	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}
}
