package PageObject;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.AbstractClass;

public class FlightPage extends AbstractClass{
	public WebDriver driver;
	public BookingPage bookingPage;
	public FlightPage(WebDriver driver) throws IOException
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//a[@title='flights']")
	WebElement flight_but;
	
	By roundTripLocator = By.id("round-trip");
	By flyingFromLocator = By.xpath("//input[@placeholder='Flying From']");
	By flyingToLocator =By.xpath("//input[@placeholder='To Destination']");
	By departureDateLocator = By.id("departure");
	By travellerComboBoxLocator = By.xpath("//label[text()='Passengers ']/following-sibling::div");
	By adultsCountLocator = By.id("fadults");
	By adultsInclocator = By.xpath("//input[@id='fadults']/following-sibling::div[@class='qtyInc']");
	By adultsDeclocator = By.xpath("//input[@id='fadults']/parent::div/div[@class='qtyDec']");
	By childCountLocator = By.id("fchilds");
	By childInclocator = By.xpath("//input[@id='fchilds']/following-sibling::div[@class='qtyInc']");
	By childDeclocator = By.xpath("//input[@id='fchilds']/parent::div/div[@class='qtyDec']");
	By infantCountLocator = By.id("finfant");
	By infantInclocator = By.xpath("//input[@id='finfant']/following-sibling::div[@class='qtyInc']");
	By infantDeclocator = By.xpath("//input[@id='finfant']/parent::div/div[@class='qtyDec']");
	By searchButLocator = By.id("flights-search");
	By DetailsCheckTxtLocator = By.xpath("//strong[text()='Adults']/parent::p");
	By DatesTxtCheckLocator = By.xpath("//strong[contains(text(),'Dates')]/parent::p");
	By BookNowLoactor=By.xpath("//span[text()='Book Now ']");
	
	By first_namelocator=By.name("firstname");
	By last_namelocator=By.name("lastname");
	By emaillocator=By.name("email");
	By phonelocator=By.name("phone");
	By addresslocator = By.name("address");
	By submitlocator =By.id("submit");
	By paypallocator=By.id("gateway_paypal");
	By agreechblocator=By.id("agreechb");
	By confirmButtlocator=By.id("booking");
	By countrydropBoxLocator=By.cssSelector("span[id*='select2-country']");
	By nationalityLocator=By.cssSelector("span[id*='select2-nationality']");
	public void BookFlight(HashMap<String,String> input) throws InterruptedException, IOException
	{
		JavaScriptExecutorClick(flight_but);
		WebElement roundTrip = driver.findElement(roundTripLocator);
		ElementToBeClickable(roundTrip);
		
		WebElement flyingFromElem = driver.findElement(flyingFromLocator);
		flyingFromElem.sendKeys(input.get("flyingFrom").substring(0, 4).toString());
		selectDynamicDropDown(input.get("flyingFrom"),driver);
		WebElement flyingToElem = driver.findElement(flyingToLocator);
		flyingToElem.sendKeys(input.get("flyingTo").substring(0, 4).toString());
		selectDynamicDropDown(input.get("flyingTo"),driver);
		
		WebElement departureDate = driver.findElement(departureDateLocator);
		departureDate.click();
		By monthlocator1 = By.xpath("//div[@class='datepicker dropdown-menu'][1]/div/table[@class=' table-condensed']/thead/tr/th[@class='switch']");
		By nextlocator1 = By.xpath("//div[@class='datepicker dropdown-menu'][1]/div//table[@class=' table-condensed']/thead/tr/th[@class='next']");
		By dateslocator1 = By.xpath("//div[@class='datepicker dropdown-menu'][1]/div/table[@class=' table-condensed']/tbody/tr/td[@class='day ']");
		DateSelector(monthlocator1,nextlocator1,dateslocator1,input.get("departureExpectedMonth"),input.get("departureExpectedDate"));
		//WebElement checkOutdropBox = driver.findElement(checkOutdropBoxlocator);
		//checkOutdropBox.click();
		By monthlocator2 = By.xpath("//div[@class='datepicker dropdown-menu'][2]/div/table[@class=' table-condensed']/thead/tr/th[@class='switch']");
		By nextlocator2 = By.xpath("//div[@class='datepicker dropdown-menu'][2]/div//table[@class=' table-condensed']/thead/tr/th[@class='next']");
		By dateslocator2 = By.xpath("//div[@class='datepicker dropdown-menu'][2]/div/table[@class=' table-condensed']/tbody/tr/td[@class='day ']");
		DateSelector(monthlocator2,nextlocator2,dateslocator2,input.get("returnExpectedMonth"),input.get("returnExpectedDate"));
		
		WebElement travellerCOmboBox = driver
				.findElement(travellerComboBoxLocator);
		travellerCOmboBox.click();
		
		WebElement adultsCount = driver.findElement(adultsCountLocator);
		int adultsCurrentCount = Integer.parseInt(adultsCount.getAttribute("value"));
		TravellersDetails(adultsInclocator, adultsDeclocator, input.get("Adults"), adultsCurrentCount);
		
		WebElement childCount = driver.findElement(childCountLocator);
		int childCurrentCount = Integer.parseInt(childCount.getAttribute("value"));
		TravellersDetails(childInclocator, childDeclocator, input.get("Child"), childCurrentCount);

		WebElement infantCount = driver.findElement(infantCountLocator);
		int infantCurrentCount = Integer.parseInt(infantCount.getAttribute("value"));
	    TravellersDetails(infantInclocator, infantDeclocator, input.get("Infants"), infantCurrentCount);
        
	    WebElement searchButt = driver.findElement(searchButLocator);
		searchButt.click();
		
		if(input.get("Result").equalsIgnoreCase("Not Found"))
		{
			System.out.println("Hotels not found");
			
		}
		if(input.get("Result").equalsIgnoreCase("Found"))
		{
			selectFlight(input);
		}
		
		

		
	}
	public void selectFlight(HashMap<String,String> input) throws InterruptedException, IOException
	{
		WebElement DetailsTxtCheck = driver.findElement(DetailsCheckTxtLocator);
		String ExpectedTravellersText = "Adults " + input.get("Adults") + " Childs " + input.get("Child") + " Infants " + input.get("Infants");
		String ActualTravellersText = DetailsTxtCheck.getAttribute("innerText");
		System.out.println(ExpectedTravellersText + " :: " + ActualTravellersText);
		Assert.assertEquals(ActualTravellersText, ExpectedTravellersText);
		WebElement DatesTxtCheck = driver.findElement(DatesTxtCheckLocator);
		System.out.println(DatesTxtCheck.getAttribute("innerText"));
		
		WebElement BookNowButton = driver.findElement(BookNowLoactor);
		ElementToBeClickable(BookNowButton);
		WebElement first_name = driver.findElement(first_namelocator);

		VisibilityWebElementCheck(first_name);
		WebElement last_name = driver.findElement(last_namelocator);
		WebElement email = driver.findElement(emaillocator);
		WebElement phone = driver.findElement(phonelocator);
		WebElement address = driver.findElement(addresslocator);
		first_name.sendKeys(super.firstName1);
		last_name.sendKeys(super.lastName1);
		email.sendKeys(super.SignupEmail1);
		phone.sendKeys(super.Phone1);
		address.sendKeys("dummy address");
		    WebElement countryDropBox = driver.findElement(countrydropBoxLocator);
			WebElement nationalityDropBox = driver.findElement(nationalityLocator);
			
			if (countryDropBox.getText().equalsIgnoreCase(input.get("Nationality"))
					&& nationalityDropBox.getText().equalsIgnoreCase(input.get("Nationality"))) {
				System.out.println(" Personal country and nationality correct");

			} else {
				System.out.println(" Personal country and nationality wrong");
			}
			
			WebElement payPal = driver.findElement(paypallocator);
			WebElement agreechb = driver.findElement(agreechblocator);
			WebElement confirmBookingBut = driver.findElement(confirmButtlocator);
			System.out.println(payPal+ " ++ "+agreechb);
			JavaScriptExecutorClick(payPal);
			JavaScriptExecutorClick(agreechb);
			JavaScriptExecutorClick(confirmBookingBut);
			bookingPage = new BookingPage(driver);
			bookingPage.BookingDetailsPage(input);
	}
	
	public static void selectDynamicDropDown(String cityname,WebDriver driver) {
		List<WebElement> flyingFromList = driver
				.findElements(By.xpath("//div[contains(@class,'autocomplete-results troll intro')]/div"));
		for (int i = 1; i <= flyingFromList.size(); i++) {
		
			if (driver
					.findElement(By
							.xpath("//div[contains(@class,'autocomplete-results troll intro')]/div[" + i + "]/div[2]"))
					.getAttribute("innerText").equalsIgnoreCase(cityname)) {
				driver.findElement(
						By.xpath("//div[contains(@class,'autocomplete-results troll intro')]/div[" + i + "]/div[2]"))
						.click();
				break;
			}
		}
		
	}
	
}
