package PageObject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.AbstractClass;

public class LoginSignUpPage extends AbstractClass{
	public WebDriver driver;
	public LoginSignUpPage(WebDriver driver) throws IOException {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(id="ACCOUNT")
	WebElement AccountButt;
	
	@FindBy(xpath="//a[text()='Customer Signup']")
	WebElement custSignButt;
	
	@FindBy(xpath="//a[text()='Customer Login']")
	WebElement custLogInButt;
	
	By first_namelocator=By.name("first_name");
	By last_namelocator=By.name("last_name");
	By emaillocator=By.name("email");
	By passwordlocator=By.name("password");
	By phonelocator=By.name("phone");
	By accDropDownlocator=By.id("account_type");
	By cookiestoplocator=By.id("cookie_stop");
	By captchaFrameloactor=By.xpath("//iframe[@title='reCAPTCHA']");
	By captchalocator=By.cssSelector("#recaptcha-anchor");
	By signUplocator=By.id("button");
	
	@FindBy(name="email")
	WebElement loginEmail;
	
	@FindBy(name="password")
	WebElement loginPassword;
	
	@FindBy(css="button[class*='btn-lg']")
	WebElement loginbutton;
	
	public void signUpNewUser(String firstName,String lastName,String SignupEmail,String SignUpPassword,String Phone) throws InterruptedException
	{
		JavaScriptExecutorClick(AccountButt);
		JavaScriptExecutorClick(custSignButt);
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(700,700)");
		WebElement first_name = driver.findElement(first_namelocator);
		WebElement last_name = driver.findElement(last_namelocator);
		WebElement email = driver.findElement(emaillocator);
		WebElement password = driver.findElement(passwordlocator);
		WebElement phone = driver.findElement(phonelocator);
		first_name.sendKeys(firstName);
		last_name.sendKeys(lastName);
		email.sendKeys(SignupEmail);
		password.sendKeys(SignUpPassword);
		phone.sendKeys(Phone);
		WebElement acc_dropdown = driver.findElement(accDropDownlocator);
		Select selectElem = new Select(acc_dropdown);
		selectElem.selectByVisibleText("Customer");
		cookie_stopRemoval(cookiestoplocator);
		WebElement captchaFrame=driver.findElement(captchaFrameloactor);
		frameToBeAvailableAndSwitchToIt(captchaFrame);
		WebElement captcha = driver.findElement(captchalocator);
		ElementToBeClickable(captcha);
		driver.switchTo().defaultContent();
		WebElement signUp = driver.findElement(signUplocator);
		ElementToBeClickable(signUp);
	}
	public void LoginCurrentUser(String Username,String Password) throws InterruptedException
	{
		ElementToBeClickable(AccountButt);
		JavaScriptExecutorClick(custLogInButt);
		Thread.sleep(2000);
		loginEmail.sendKeys(Username);
		loginPassword.sendKeys(Password);
		cookie_stopRemoval(cookiestoplocator);
		JavaScriptExecutorClick(loginbutton);
	}
}
