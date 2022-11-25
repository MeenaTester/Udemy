package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class ExtentReportsTestNg {
    
	public static ExtentReports getExtentReportsFromClass()
	{
		String path = System.getProperty("user.dir")+("\\target\\index.html");
		System.out.println("Report "+path);
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		spark.config().setDocumentTitle("PHP Travels Automation");
		spark.config().setReportName("PHPTravels Reports");
		
		ExtentReports reports = new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("Tester Name","Meena Kasi");
		reports.setSystemInfo("Project Name", "PHP Travels Website");
		return reports;
	}
}

                                                                                  