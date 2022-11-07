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

public class Bookings {
	static WebDriver driver;
	static WebDriverWait expWait;
	// hotel
	static String cityShortForm = "Dubai";
	static String cityname = "Dubai,United Arab Emirates";
	static String CheckInExpectedMonth = "June 2023";
	static String CheckInExpectedDate = "2";
	static String CheckOutExpectedMonth = "June 2023";
	static String CheckOutExpectedDate = "5";

	static String Rooms = "1";
	static String Adults = "2";
	static String Child = "1";
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
		driver.get("https://phptravels.net/login");
		driver.manage().window().maximize();

		hotelBooking();

	}

	public static void hotelBooking() throws InterruptedException {
		WebElement hotel_but = driver.findElement(By.xpath("//a[@title='hotels']"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", hotel_but);

		WebElement cityDropBox = driver.findElement(By.id("select2-hotels_city-container"));
		expWait = new WebDriverWait(driver, Duration.ofSeconds(225));
		expWait.until(ExpectedConditions.elementToBeClickable(cityDropBox));
		Thread.sleep(2000);
		cityDropBox.click();
		WebElement cityTextArea = driver.findElement(By.cssSelector(".select2-search__field"));
		cityTextArea.sendKeys(cityShortForm);
		Thread.sleep(5000);
		List<WebElement> cityList = driver.findElements(By.xpath("//ul[@id='select2-hotels_city-results']/li"));

		for (int i = 0; i < cityList.size(); i++) {
			String currCity = cityList.get(i).getText();
			System.out.println(currCity);
			while (!cityList.get(i).getText().equalsIgnoreCase("Searching…")) {
				System.out.println(cityname + " :: " + currCity);
				if (cityList.get(i).getText().equalsIgnoreCase(cityname)) {
					// System.out.println("selected "+cityTextArea.getText());
					cityList.get(i).click();
					ContinueSearch();
					break;

				}
				break;
			}

		}

	}

	public static void ContinueSearch() throws InterruptedException {
		System.out.println("continuesearch");
		WebElement hotelSearch = driver.findElement(By.id("submit"));
		// Thread.sleep(5000);
		WebElement checkIndropBox = driver.findElement(By.id("checkin"));
		checkIndropBox.click();
		SelectDatesFromDropDown(CheckInExpectedMonth, CheckInExpectedDate, 1);
		WebElement checkOutdropBox = driver.findElement(By.id("checkout"));
		// checkOutdropBox.click();
		SelectDatesFromDropDown(CheckOutExpectedMonth, CheckOutExpectedDate, 2);

		WebElement travellersDropDown = driver.findElement(By.cssSelector("a[class*='travellers']"));
		travellersDropDown.click();
		WebElement nationalityDropBox = driver.findElement(By.id("nationality"));
		nationalityDropBox.click();
		Select natdropdown = new Select(nationalityDropBox);
		natdropdown.selectByVisibleText(Nationality);
		WebElement roomsCount = driver.findElement(By.id("rooms"));
		int roomCurrentCount = Integer.parseInt(roomsCount.getAttribute("value"));
		System.out.println(Integer.parseInt(Rooms) + " : " + roomCurrentCount);
		while (Integer.parseInt(Rooms) > roomCurrentCount) {
			driver.findElement(By.cssSelector("div[class*='roomInc']")).click();
			roomCurrentCount++;
			System.out.println(Integer.parseInt(Rooms) + " inc " + roomCurrentCount);

		}
		while (Integer.parseInt(Rooms) < roomCurrentCount) {
			driver.findElement(By.cssSelector("div[class*='roomDec']")).click();
			roomCurrentCount--;
			System.out.println(Integer.parseInt(Rooms) + " dec " + roomCurrentCount);
		}

		WebElement adultsCount = driver.findElement(By.id("adults"));
		int adultsCurrentCount = Integer.parseInt(adultsCount.getAttribute("value"));
		System.out.println(Integer.parseInt(Adults) + " ad " + adultsCurrentCount);
		while (Integer.parseInt(Adults) > adultsCurrentCount) {
			driver.findElement(By.xpath("//input[@id='adults']/following-sibling::div[@class='qtyInc']")).click();
			adultsCurrentCount++;
		}
		while (Integer.parseInt(Adults) < adultsCurrentCount) {
			driver.findElement(By.xpath("//input[@id='adults']/parent::div/div[@class='qtyDec']")).click();
			adultsCurrentCount--;
		}

		WebElement childCount = driver.findElement(By.id("childs"));
		int childCurrentCount = Integer.parseInt(childCount.getAttribute("value"));
		System.out.println(Integer.parseInt(Child) + " chi " + childCurrentCount);
		while (Integer.parseInt(Child) > childCurrentCount) {
			driver.findElement(By.xpath("//input[@id='childs']/following-sibling::div[@class='qtyInc']")).click();
			childCurrentCount++;
		}
		while (Integer.parseInt(Child) < childCurrentCount) {
			driver.findElement(By.xpath("//input[@id='childs']/parent::div/div[@class='qtyDec']")).click();
			childCurrentCount--;
		}

		travellersDropDown.click();
		hotelSearch.click();
		BookHotel();

	}

	public static void BookHotel() throws InterruptedException {

		WebElement hotelsListPage = driver.findElement(By.cssSelector(".breadcrumb-wrap"));
		expWait = new WebDriverWait(driver, Duration.ofSeconds(225));
		expWait.until(ExpectedConditions.visibilityOf(hotelsListPage));
		try {
			WebElement cookie_stop = driver.findElement(By.id("cookie_stop"));
			System.out.println("remove cookie butt");
			cookie_stop.click();
			expWait = new WebDriverWait(driver, Duration.ofSeconds(225));
			expWait.until(ExpectedConditions.invisibilityOf(cookie_stop));
		} catch (Exception e) {

		}
		List<WebElement> hotelSearchList = driver.findElements(By.xpath("//section[@id='data']/ul/li"));
		for (int i = 0; i < hotelSearchList.size(); i++) {
			if (hotelSearchList.get(i).getAttribute("id").equalsIgnoreCase(HotelName)) {

				int j = i + 1;
				WebElement selectedIt = driver.findElement(By.xpath("//section[@id='data']/ul/li[" + j + "]"));
				int x = selectedIt.getLocation().x;
				int y = selectedIt.getLocation().y;

				WebElement selectedHotel = driver.findElement(By.xpath("//section[@id='data']/ul/li[" + j
						+ "]/div/div[@class='card-body p-0']/div/div[@class='col-4 p-2']/div/a"));

				String jsEngine = String.format("window.scrollBy(%d,%d)", x, y);
				((JavascriptExecutor) driver).executeScript(jsEngine);
				Thread.sleep(2000);

				selectedHotel.click();
				break;
			}
		}

		confirmHotel();

	}

	public static void confirmHotel() throws InterruptedException {
		WebElement selectedHotelTitle = driver.findElement(By.xpath("//div[@class='row']/div/h3"));
		expWait = new WebDriverWait(driver, Duration.ofSeconds(225));
		expWait.until(ExpectedConditions.visibilityOf(selectedHotelTitle));
		if (selectedHotelTitle.getText().equalsIgnoreCase(HotelName)) {

			WebElement bookNowBut = driver.findElement(By.xpath("//button[@type='submit']"));

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click()", bookNowBut);
			fillPersonalInformation();

		} else {
			System.out.println("error in opening correct hotel page");
		}

	}

	public static void fillPersonalInformation() throws InterruptedException {

		WebElement first_name = driver.findElement(By.name("firstname"));

		expWait = new WebDriverWait(driver, Duration.ofSeconds(225));
		expWait.until(ExpectedConditions.visibilityOf(first_name));
		WebElement last_name = driver.findElement(By.name("lastname"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement phone = driver.findElement(By.name("phone"));
		WebElement address = driver.findElement(By.name("address"));

		first_name.sendKeys("Meena");
		last_name.sendKeys("Kasi");
		email.sendKeys("hoomails@gmail.com");
		phone.sendKeys("9176327518");
		address.sendKeys("dummy address");
		WebElement countryDropBox = driver.findElement(By.cssSelector("span[id*='select2-country']"));
		WebElement nationalityDropBox = driver.findElement(By.cssSelector("span[id*='select2-nationality']"));
		WebElement totalPrice = driver.findElement(By.xpath("//span[text()='Total Price:']/following-sibling::strong"));
		String displayedTotalPrice = totalPrice.getText();
		if (countryDropBox.getText().equalsIgnoreCase(Nationality)
				&& nationalityDropBox.getText().equalsIgnoreCase(Nationality)) {
			System.out.println(" Personal country and nationality correct");

		} else {
			System.out.println(" Personal country and nationality wrong");
		}
		WebElement payPal = driver.findElement(By.id("gateway_paypal"));
		WebElement agreechb = driver.findElement(By.id("agreechb"));
		WebElement confirmBookingBut = driver.findElement(By.id("booking"));

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", payPal);
		jse.executeScript("arguments[0].click()", agreechb);
		jse.executeScript("arguments[0].click()", confirmBookingBut);

		BookingDetailsPage(displayedTotalPrice);

	}

	public static void BookingDetailsPage(String displayedTotalPrice) throws InterruptedException {
		WebElement proceedButt = driver.findElement(By.id("form"));
		expWait = new WebDriverWait(driver, Duration.ofSeconds(225));
		expWait.until(ExpectedConditions.elementToBeClickable(proceedButt));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", proceedButt);
		// proceedButt.click();

		WebElement payPalConfirmation = driver.findElement(By.xpath("//div[@class='card-header']/small/strong"));
		expWait = new WebDriverWait(driver, Duration.ofSeconds(225));
		expWait.until(ExpectedConditions.visibilityOf(payPalConfirmation));
		System.out.println(displayedTotalPrice + "::" + payPalConfirmation.getText().split(" ")[1]);
		if (payPalConfirmation.getText().split(" ")[0].equalsIgnoreCase("Paypal")) {
			System.out.println("PayPal payment page is reached " + payPalConfirmation.getText().split(" ")[0]);
			WebElement backtoInvoice = driver.findElement(By.cssSelector("div[class*='btn']"));
			backtoInvoice.click();
			WebElement Yesbutt = driver.findElement(By.xpath("//div[@class='btn-back']/a"));
			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("arguments[0].click()", Yesbutt);
			BackToBookingdetails();
		} else {
			System.out.println("Error : PayPal payment page is not reached ");
		}

	}

	public static void BackToBookingdetails() throws InterruptedException {
		WebElement statusMsg = driver.findElement(By.cssSelector("div[class*='infobox-danger']"));
		System.out.println(statusMsg.getText());

		WebElement ReservatinNumElement = driver
				.findElement(By.xpath("//strong[@class='text-black mr-1']/parent::span"));
		String ReservationNum = ReservatinNumElement.getAttribute("innerText").split(": ")[1];
		String InvoiceFileName = ReservationNum.split("-")[0] + "_" + ReservationNum.split("-")[1];

		// String ReservationNum1 =
		// driver.findElement(By.xpath("//strong[text()='Reservation
		// Number:']")).getText();
		WebElement downLoadInvoice = driver.findElement(By.id("download"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", downLoadInvoice);

		File f = new File(downloadPath + "/" + InvoiceFileName + ".jpg");
		Thread.sleep(2000);
		System.out.println("Invoice File Downloaded successfully : " + f.exists());

		WebElement bookingCancellation = driver.findElement(By.xpath("//input[@value='Request Cancellation']"));
		js.executeScript("arguments[0].click()", bookingCancellation);
		driver.switchTo().alert().accept();
		WebElement alertMsg = driver.findElement(By.cssSelector(".alert-danger"));
		expWait = new WebDriverWait(driver, Duration.ofSeconds(225));
		expWait.until(ExpectedConditions.visibilityOf(alertMsg));
		System.out.println("Cancellation msg : " + alertMsg.getText());
	}

	public static void SelectDatesFromDropDown(String ExpectedMonth, String ExpectedDate, int k) {

		while (!driver
				.findElement(By.xpath("//div[@class='datepicker dropdown-menu'][" + k
						+ "]/div/table[@class=' table-condensed']/thead/tr/th[@class='switch']"))
				.getText().equalsIgnoreCase(ExpectedMonth)) {
			driver.findElement(By.xpath("//div[@class='datepicker dropdown-menu'][" + k
					+ "]/div/table[@class=' table-condensed']/thead/tr/th[@class='next']")).click();
			// break;
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
