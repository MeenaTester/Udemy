package StandAlone;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneFlightBooking {
	static WebDriver driver;
	static WebDriverWait expWait;
	// hotel

	static String flyingFrom = "Dubai, United Arab Emirates";
	static String flyingTo = "Kuwait, Kuwait";
	static String departureExpectedMonth = "March 2023";
	static String departureExpectedDate = "28";
	static String returnExpectedMonth = "April 2023";
	static String returnExpectedDate = "15";

	
	static String Adults = "2";
	static String Child = "1";
	static String Infants = "2";
	static String Nationality = "Andorra";

	static String HotelName = "Oasis Beach Tower";
	static String downloadPath = "";

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		downloadPath = System.getProperty("user.dir");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);

		// FirefoxOptions options1 = new FirefoxOptions();
		// FirefoxProfile profile = new FirefoxProfile();
		// Instructing firefox to use custom download location
		// profile.setPreference("browser.download.folderList", 2);
		// Setting custom download directory
		// profile.setPreference("browser.download.dir", downloadPath);
		// Skipping Save As dialog box for types of files with their MIME
		// profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
		// "application/x-msexcel,application/excel,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/vnd.microsoft.portable-executable");
		// options1.setProfile(profile);
		// WebDriverManager.firefoxdriver().setup();
		// driver = new FirefoxDriver(options1);

		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		driver.get("https://phptravels.net");
		driver.manage().window().maximize();

		flightBooking();

	}

	public static void flightBooking() throws InterruptedException {
		WebElement hotel_but = driver.findElement(By.xpath("//a[@title='flights']"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", hotel_but);

		WebElement roundTrip = driver.findElement(By.id("round-trip"));
		WebDriverWait expWait = new WebDriverWait(driver, Duration.ofSeconds(500));
		expWait.until(ExpectedConditions.elementToBeClickable(roundTrip)).click();

		WebElement flyingFromElem = driver.findElement(By.xpath("//input[@placeholder='Flying From']"));
		flyingFromElem.sendKeys(flyingFrom.substring(0, 4).toString());
		selectDynamicDropDown(flyingFrom);
		WebElement flyingToElem = driver.findElement(By.xpath("//input[@placeholder='To Destination']"));
		flyingToElem.sendKeys(flyingTo.substring(0, 4).toString());
		selectDynamicDropDown(flyingTo);
		
		WebElement departureDate = driver.findElement(By.id("departure"));
		departureDate.click();
		SelectDatesFromDropDown(departureExpectedMonth, departureExpectedDate, 1);
		SelectDatesFromDropDown(returnExpectedMonth, returnExpectedDate, 2);
		
		WebElement travellerCOmboBox=driver.findElement(By.xpath("//label[text()='Passengers ']/following-sibling::div"));
		travellerCOmboBox.click();
		
		WebElement adultsCount = driver.findElement(By.id("fadults"));
		int adultsCurrentCount = Integer.parseInt(adultsCount.getAttribute("value"));
		By adultsInclocator = By.xpath("//input[@id='fadults']/following-sibling::div[@class='qtyInc']");
		By adultsDeclocator =By.xpath("//input[@id='fadults']/parent::div/div[@class='qtyDec']");
		TravellersDetails(adultsInclocator,adultsDeclocator,Adults,adultsCurrentCount);
		
		WebElement childCount = driver.findElement(By.id("fchilds"));
		int childCurrentCount = Integer.parseInt(childCount.getAttribute("value"));
		By childInclocator = By.xpath("//input[@id='fchilds']/following-sibling::div[@class='qtyInc']");
		By childDeclocator =By.xpath("//input[@id='fchilds']/parent::div/div[@class='qtyDec']");
		TravellersDetails(childInclocator,childDeclocator,Child,childCurrentCount);
		
		WebElement infantCount = driver.findElement(By.id("finfant"));
		int infantCurrentCount = Integer.parseInt(childCount.getAttribute("value"));
		By infantInclocator = By.xpath("//input[@id='finfant']/following-sibling::div[@class='qtyInc']");
		By infantDeclocator =By.xpath("//input[@id='finfant']/parent::div/div[@class='qtyDec']");
		TravellersDetails(infantInclocator,infantDeclocator,Infants,infantCurrentCount);
		
		WebElement searchButt = driver.findElement(By.id("flights-search"));
		searchButt.click();
	}
	
	 public static void TravellersDetails(By Inclocator,By Declocator,String detailtype,int CurrentCount)
		{
			while (Integer.parseInt(detailtype) > CurrentCount) {
				driver.findElement(Inclocator).click();
				CurrentCount++;
			}
			while (Integer.parseInt(detailtype) < CurrentCount) {
				driver.findElement(Declocator).click();
				CurrentCount--;
			}
		}

	public static void selectDynamicDropDown(String cityname) {
		List<WebElement> flyingFromList = driver
				.findElements(By.xpath("//div[contains(@class,'autocomplete-results troll intro')]/div"));
		for (int i = 1; i <= flyingFromList.size(); i++) {
			if (driver.findElement(By.xpath("//div[contains(@class,'autocomplete-results troll intro')]/div[" + i + "]/div[2]")).getText().equalsIgnoreCase(cityname)) {
				driver.findElement(By.xpath("//div[contains(@class,'autocomplete-results troll intro')]/div[" + i + "]/div[2]")).click();
				break;
			}
		}
	}
	public static void SelectDatesFromDropDown(String ExpectedMonth, String ExpectedDate, int k) {
		while (!driver
				.findElement(By.xpath("//div[@class='datepicker dropdown-menu'][" + k
						+ "]/div/table[@class=' table-condensed']/thead/tr/th[@class='switch']"))
				.getText().equalsIgnoreCase(ExpectedMonth)) {
			if(driver
					.findElement(By.xpath("//div[@class='datepicker dropdown-menu'][" + k
							+ "]/div/table[@class=' table-condensed']/thead/tr/th[@class='switch']"))
					.getText()!="")
			{
				/*
				 * System.out.println(driver
				 * .findElement(By.xpath("//div[@class='datepicker dropdown-menu'][" + k +
				 * "]/div/table[@class=' table-condensed']/thead/tr/th[@class='switch']"))
				 * .getText()+"::"+ExpectedMonth);
				 */
			
				
			driver.findElement(By.xpath("//div[@class='datepicker dropdown-menu'][" + k
					+ "]/div/table[@class=' table-condensed']/thead/tr/th[@class='next']")).click();
			 //break;
			}
		
	}

		List<WebElement> dates = driver.findElements(By.xpath("//div[@class='datepicker dropdown-menu'][" + k
				+ "]/div/table[@class=' table-condensed']/tbody/tr/td[@class='day ']"));
		for (int i = 0; i < dates.size(); i++) {
			if (dates.get(i).getText().equalsIgnoreCase(ExpectedDate)) {
				dates.get(i).click();
				break;
			}
		}
	}
}
