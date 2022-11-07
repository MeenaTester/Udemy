package StandAlone;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Company {
	static WebDriver driver;
	static WebDriverWait expWait;
	
	static String companyName="How to Book";
	

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://phptravels.net/login");
		driver.manage().window().maximize();
		
        OpenHowToBookPage();
        Thread.sleep(2000);
        List<WebElement> CompanyList = driver.findElements(By.xpath("//a[@href='company']/following-sibling::ul/li/a"));
    	for(int i=0;i<CompanyList.size();i++)
    	{
    		if(CompanyList.get(i).getAttribute("innerText").equalsIgnoreCase(companyName))
    		{
    			CompanyList.get(i).click();
    			break;
    		}
    	}
    	Thread.sleep(2000);
    	WebElement pageTitle=driver.findElement(By.cssSelector("h3[class*='title']"));
    	System.out.println(companyName+" companyPageTitle: "+pageTitle.getAttribute("innerText"));
	}
	
	
    public static void OpenHowToBookPage() throws InterruptedException
    {
    	Thread.sleep(3000);
    	WebElement company = driver.findElement(By.xpath("//a[@href='company']"));
    	Actions act = new Actions(driver);
    	act.moveToElement(company).build().perform();
    	
    	
    }
}
