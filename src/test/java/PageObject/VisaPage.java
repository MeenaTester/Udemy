package PageObject;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.AbstractClass;

public class VisaPage extends AbstractClass {
	public WebDriver driver;

	public VisaPage(WebDriver driver) throws IOException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@title='visa']")
	WebElement visa_but;

	By fromCountrylocator = By.id("select2-from_country-container");
	By fromCountryTextArealocator = By.cssSelector(".select2-search__field");
	By fromCountryListlocator = By.xpath("//ul[@id='select2-from_country-results']/li");
	By toCountrylocator = By.id("select2-to_country-container");
	By toCountrytextArealocator = By.cssSelector(".select2-search__field");
	By toCountryListlocator = By.xpath("//ul[@id='select2-to_country-results']/li");
	By dateDropBoxlocator = By.id("date");
	By monthlocator = By.xpath("//table[@class=' table-condensed']/thead/tr/th[@class='switch']");
	By nextlocator = By.xpath("//table[@class=' table-condensed']/thead/tr/th[@class='next']");
	By dateslocator = By.cssSelector(".day");
	By visaSearchlocator = By.id("submit");
	By first_namelocator=By.name("first_name");
	By last_namelocator=By.name("last_name");
	By emaillocator=By.name("email");
	By phonelocator=By.name("phone");
	By cookiestoplocator=By.id("cookie_stop");
	By visaSubmitlocator =By.id("submit");
	By visaAcknowledgelocator = By.xpath("//div[@class='card-body my-5 text-center']/h2");

	public String VisaSubmission(String fromCountry, String toCountry, String visaExpectedMonth, String visaExpectedDate,String firstName,String lastName,String SignupEmail,String Phone)
			throws InterruptedException {
		JavaScriptExecutorClick(visa_but);
		Thread.sleep(4000);
		WebElement fromCountryDropBox = driver.findElement(fromCountrylocator);
		JavaScriptExecutorClick(fromCountryDropBox);
		WebElement fromCountryTextArea = driver.findElement(fromCountryTextArealocator);
		fromCountryTextArea.sendKeys(fromCountry.substring(0, 4).toString());
		Thread.sleep(1000);
		List<WebElement> fromCountryList = driver.findElements(fromCountryListlocator);
		DropBoxSelector(fromCountryList,fromCountry);
		WebElement toCountryDropBox = driver.findElement(toCountrylocator);
		JavaScriptExecutorClick(toCountryDropBox);
		WebElement toCountryTextArea = driver.findElement(toCountrytextArealocator);
		toCountryTextArea.sendKeys(toCountry.substring(0, 4).toString());
		List<WebElement> toCountryList = driver.findElements(toCountryListlocator);
		DropBoxSelector(toCountryList,toCountry);
		WebElement datedropBox = driver.findElement(dateDropBoxlocator);
		JavaScriptExecutorClick(datedropBox);
		DateSelector(monthlocator,nextlocator,dateslocator,visaExpectedMonth,visaExpectedDate);
		WebElement visaSearch = driver.findElement(visaSearchlocator);
		JavaScriptExecutorClick(visaSearch);

		WebElement first_name = driver.findElement(first_namelocator);
        VisibilityWebElementCheck(first_name);
		WebElement last_name = driver.findElement(last_namelocator);
		WebElement email = driver.findElement(emaillocator);
		WebElement phone = driver.findElement(phonelocator);
		cookie_stopRemoval(cookiestoplocator);
		first_name.sendKeys(firstName);
		last_name.sendKeys(lastName);
		email.sendKeys(SignupEmail);
		phone.sendKeys(Phone);
		WebElement visaSubmit = driver.findElement(visaSubmitlocator);
		JavaScriptExecutorClick(visaSubmit);
       
        
        WebElement visaacknowledge = driver.findElement(visaAcknowledgelocator);
        VisibilityWebElementCheck(visaacknowledge);
        return visaacknowledge.getText();
		
	}
}
