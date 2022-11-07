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

public class Blog {
	static WebDriver driver;
	static WebDriverWait expWait;
	
	static String blogName="It Wasn’t Supposed to Rain";
	

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		driver.get("https://phptravels.net/");
		driver.manage().window().maximize();
		//Thread.sleep(4000);
		Blogs();
        
	}
	
	public static void Blogs() throws InterruptedException
	{
		WebElement blog_but = driver.findElement(By.xpath("//a[@title='blog']"));
		expWait = new WebDriverWait(driver, Duration.ofSeconds(225));
		expWait.until(ExpectedConditions.elementToBeClickable(blog_but));
		blog_but.click();
		Thread.sleep(4000);
		List<WebElement> blogList = driver.findElements(By.cssSelector("a[class*='author__title']"));
		for(int i=0;i<blogList.size();i++)
		{
			if(blogList.get(i).getAttribute("innerText").equalsIgnoreCase(blogName))
			{
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("arguments[0].click()", blogList.get(i));
				verifyBlogPage();
				break;
			}
		}
		
		
	}
	public static void verifyBlogPage()
	{

		WebElement pageTitle = driver.findElement(By.cssSelector("h3[class*='card-title']"));
		expWait = new WebDriverWait(driver, Duration.ofSeconds(225));
		expWait.until(ExpectedConditions.visibilityOf(pageTitle));
		System.out.println("pageTitle: "+pageTitle.getText());
		
	}
   
}
