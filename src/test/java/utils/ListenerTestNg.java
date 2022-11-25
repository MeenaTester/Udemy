package utils;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



public class ListenerTestNg extends BaseTest implements ITestListener{
	public ListenerTestNg() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	ExtentTest test;
	ExtentReports reports = ExtentReportsTestNg.getExtentReportsFromClass();
	ThreadLocal<ExtentTest> thraedLocal = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(" ........................................Listener onStart "+result.getMethod().getMethodName());
		test = reports.createTest(result.getMethod().getMethodName());
		thraedLocal.set(test);
	}
	
	@Override
	public void onFinish(ITestContext context) {
		System.out.println(" ........................................Listener onFinish ");
		reports.flush();
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(" ........................................Listener onSuccess ");
		thraedLocal.get().log(Status.PASS, "Test Passed Succesfully");
	}
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(" ........................................Listener onFailure ");
		// TODO Auto-generated method stub
		thraedLocal.get().fail(result.getThrowable());//
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
			System.out.println("tryafter");
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String filePath = null;
		try {
			
			filePath = getSCreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("filePath "+filePath);
		thraedLocal.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
		//Screenshot, Attach to report
		
		
	}
}

                                                                                  