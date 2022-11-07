package StandAlone;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VisaVerification {
	static WebDriver driver;
	static WebDriverWait expWait;
	//Login
	static String userName = "user@phptravels.com";
	static String passwordTxt = "demouser";
	
	//Visa
	static String fromCountry = "India";
	static String toCountry="United States";
	static String visaExpectedMonth = "January 2024";
	static String visaExpectedDate = "26";
	

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://phptravels.net/login");
		driver.manage().window().maximize();
		System.out.println(fromCountry.substring(0, 3).toString());
		VisaVerification();

	}
	
	public static void VisaVerification() throws InterruptedException
	{
		WebElement visa_but = driver.findElement(By.xpath("//a[@title='visa']"));
		expWait = new WebDriverWait(driver, Duration.ofSeconds(225));
		expWait.until(ExpectedConditions.elementToBeClickable(visa_but));
		visa_but.click();
		
		WebElement fromCountryDropBox = driver.findElement(By.id("select2-from_country-container"));
		fromCountryDropBox.click();
		WebElement fromCountryTextArea = driver.findElement(By.cssSelector(".select2-search__field"));
		fromCountryTextArea.sendKeys("Ind");
		Thread.sleep(1000);
		List<WebElement> fromCountryList = driver.findElements(By.xpath("//ul[@id='select2-from_country-results']/li"));
		System.out.println("fromCountryList "+fromCountryList.size());
		for(int i=0;i<fromCountryList.size();i++)
		{
			if(fromCountryList.get(i).getText().equalsIgnoreCase(fromCountry))
			{
				fromCountryList.get(i).click();
				break;
			}
		}
		
		WebElement toCountryDropBox = driver.findElement(By.id("select2-to_country-container"));
		toCountryDropBox.click();
		WebElement toCountryTextArea = driver.findElement(By.cssSelector(".select2-search__field"));
		toCountryTextArea.sendKeys("Unit");
		List<WebElement> toCountryList = driver.findElements(By.xpath("//ul[@id='select2-to_country-results']/li"));
		
		for(int i=0;i<toCountryList.size();i++)
		{
			if(toCountryList.get(i).getText().equalsIgnoreCase(toCountry))
			{
				toCountryList.get(i).click();
				break;
			}
		}
		
		WebElement datedropBox = driver.findElement(By.id("date"));
		datedropBox.click();
		while(!driver.findElement(By.xpath("//table[@class=' table-condensed']/thead/tr/th[@class='switch']")).getText().equalsIgnoreCase(visaExpectedMonth))
		{
			driver.findElement(By.xpath("//table[@class=' table-condensed']/thead/tr/th[@class='next']")).click();
			//break;
		}
		List<WebElement> dates =  driver.findElements(By.cssSelector(".day"));
		for(int i=0;i<dates.size();i++)
		{
			if(dates.get(i).getText().equalsIgnoreCase(visaExpectedDate))
			{
				dates.get(i).click();
				break;
			}
		}
		
		WebElement visaSearch = driver.findElement(By.id("submit"));
		visaSearch.click();
		
		WebElement first_name = driver.findElement(By.name("first_name"));
		
		expWait = new WebDriverWait(driver, Duration.ofSeconds(25));
		expWait.until(ExpectedConditions.visibilityOf(first_name));
		WebElement last_name = driver.findElement(By.name("last_name"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement phone = driver.findElement(By.name("phone"));
		try {
		WebElement cookie_stop = driver.findElement(By.id("cookie_stop"));
		cookie_stop.click();
		}
		catch(Exception e)
		{
			
		}
		first_name.sendKeys("Meena");
		last_name.sendKeys("Kasi");
		email.sendKeys("hoomails@gmail.com");
		phone.sendKeys("9176327518");
		WebElement visaSubmit = driver.findElement(By.id("submit"));
		
		visaSubmit.click();
		WebElement visaacknowledge = driver.findElement(By.xpath("//div[@class='card-body my-5 text-center']/h2"));
		expWait = new WebDriverWait(driver, Duration.ofSeconds(25));
		expWait.until(ExpectedConditions.visibilityOf(visaacknowledge));
		System.out.println("visaacknowledge "+visaacknowledge.getText());
		
	}

}
