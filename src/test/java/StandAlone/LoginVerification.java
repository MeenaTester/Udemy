package StandAlone;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginVerification {
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
		ValidateAccountButton();
		//checkSignUp();
        LoginWithCorrectCredentials();
		//Thread.sleep(5000);
		flightsBooking();

	}
	public static void ValidateAccountButton()
	{
		WebElement AccountButt = driver.findElement(By.id("ACCOUNT"));
		expWait = new WebDriverWait(driver, Duration.ofSeconds(125));
		expWait.until(ExpectedConditions.elementToBeClickable(AccountButt));
		AccountButt.click();
	}
	public static void checkSignUp() {
		WebElement custSignButt = driver.findElement(By.xpath("//a[text()='Customer Signup']"));
		custSignButt.click();
        System.out.println("init");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(700,700)");
		WebElement first_name = driver.findElement(By.name("first_name"));
		WebElement last_name = driver.findElement(By.name("last_name"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement phone = driver.findElement(By.name("phone"));
		first_name.sendKeys("Meena");
		last_name.sendKeys("Kasi");
		email.sendKeys("hoomails@gmail.com");
		password.sendKeys("Texas@123");
		phone.sendKeys("9176327518");
		WebElement acc_dropdown = driver.findElement(By.id("account_type"));
		Select selectElem = new Select(acc_dropdown);
		selectElem.selectByVisibleText("Customer");
		WebElement cookie_stop = driver.findElement(By.id("cookie_stop"));
		expWait = new WebDriverWait(driver, Duration.ofSeconds(25));
		expWait.until(ExpectedConditions.elementToBeClickable(cookie_stop)).click();
		expWait = new WebDriverWait(driver, Duration.ofSeconds(25));
		expWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='reCAPTCHA']")));
		WebElement captcha = driver.findElement(By.cssSelector("#recaptcha-anchor"));
		expWait.until(ExpectedConditions.elementToBeClickable(captcha)).click();
		driver.switchTo().defaultContent();
		WebElement signUp = driver.findElement(By.id("button"));
		expWait = new WebDriverWait(driver, Duration.ofSeconds(225));
		expWait.until(ExpectedConditions.elementToBeClickable(
				signUp)).click();
		
		System.out.println("SignedUpSuccessful");
	}
	public static void LoginWithCorrectCredentials() throws InterruptedException
	{
		
		//WebElement custLoginButt = driver.findElement(By.xpath("//a[text()='Customer Login']"));
		//custLoginButt.click();
		WebElement email = driver.findElement(By.name("email"));
		WebElement password = driver.findElement(By.name("password"));
		email.sendKeys(userName);
		password.sendKeys(passwordTxt);
		WebElement loginbutton = driver.findElement(By.cssSelector("button[class*='btn-lg']"));
		loginbutton.click();
		try {
			Alert alert=driver.switchTo().alert();
			String msg= alert.getText();
			System.out.println(msg);
			Thread.sleep(2000);
			alert.accept();
			
		}
		catch(Exception e)
		{


		}
		
	}
	public static void flightsBooking()
	{
		
	}
	

}
