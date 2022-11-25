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

import utils.AbstractClass;

public class HotelPage extends AbstractClass{
	public WebDriver driver;
	public BookingPage bookingPage;
	public HotelPage(WebDriver driver) throws IOException
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
    
	@FindBy(xpath = "//a[@title='hotels']")
	WebElement hotel_but;
	
	By cookiestoplocator=By.id("cookie_stop");
	By cityDropBoxlocator = By.id("select2-hotels_city-container");
	By cityTextArealocator = By.cssSelector(".select2-search__field");
	By cityListlocator=By.xpath("//ul[@id='select2-hotels_city-results']/li");
	By hotelSearchlocator=By.id("submit");
	By checkIndropBOxlocator=By.id("checkin");
	By checkOutdropBoxlocator =By.id("checkout");
	
	By travellersDropDownlocator = By.cssSelector("a[class*='travellers']");
	By nationalityDropBoxlocator=By.id("nationality");
	By roomslocator=By.id("rooms");
	By roomInclocator=By.cssSelector("div[class*='roomInc']");
	By roomDeclocator=By.cssSelector("div[class*='roomDec']");
	By adultslocator=By.id("adults");
	By adultInclocator=By.xpath("//input[@id='adults']/following-sibling::div[@class='qtyInc']");
	By adultDeclocator=By.xpath("//input[@id='adults']/parent::div/div[@class='qtyDec']");
	By childlocator=By.id("childs");
	By childInclocator=By.xpath("//input[@id='childs']/following-sibling::div[@class='qtyInc']");
	By childDeclocator=By.xpath("//input[@id='childs']/parent::div/div[@class='qtyDec']");
	By hotelsListPagelocator=By.cssSelector(".breadcrumb-wrap");
    By hotelSearchListlocator=By.xpath("//section[@id='data']/ul/li");
    By selectedHotelTitlelocator=By.xpath("//div[@class='row']/div/h3");
    By bookNowlocator =By.xpath("//button[@type='submit']");
    
    By first_namelocator=By.name("firstname");
	By last_namelocator=By.name("lastname");
	By emaillocator=By.name("email");
	By phonelocator=By.name("phone");
	By addresslocator = By.name("address");
	By submitlocator =By.id("submit");
	By paypallocator=By.id("gateway_paypal");
	By agreechblocator=By.id("agreechb");
	By confirmButtlocator=By.id("booking");
	By totalPricelocator=By.xpath("//span[text()='Total Price:']/following-sibling::strong");
	
	
	public void BookHotel(HashMap<String,String> input) throws InterruptedException, IOException
	{
		JavaScriptExecutorClick(hotel_but);
		//WebDriverWait expWait = new WebDriverWait(driver,Duration.ofSeconds(500));
		//expWait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfAllElementsLocatedBy(cityListlocator)));
		WebElement cityDropBox = driver.findElement(cityDropBoxlocator);
		ElementToBeClickable(cityDropBox);
		WebElement cityTextArea = driver.findElement(cityTextArealocator);
		System.out.println(input.get("cityname"));
		cityTextArea.sendKeys(input.get("cityname").substring(0, 4).toString());
		Thread.sleep(6000);
		List<WebElement> cityList = driver.findElements(By.xpath("//ul[@id='select2-hotels_city-results']/li"));
		
		for (int i = 0; i < cityList.size(); i++) {
			String currCity = cityList.get(i).getText();
			while (!cityList.get(i).getText().equalsIgnoreCase("Searching…")) {
				if (cityList.get(i).getText().equalsIgnoreCase(input.get("cityname"))) {
					cityList.get(i).click();
					
					break;

				}
				break;
			}
		}

		
		ContinueSearch(input);
		
	}
	public void ContinueSearch(HashMap<String,String> input) throws InterruptedException, IOException
	{
		WebElement hotelSearch = driver.findElement(hotelSearchlocator);
		WebElement checkIndropBox = driver.findElement(checkIndropBOxlocator);
		checkIndropBox.click();
		System.out.println(input.get("CheckInExpectedMonth")+input.get("CheckInExpectedDate")+" :: "+input.get("CheckOutExpectedMonth")+input.get("CheckOutExpectedDate"));
		By monthlocator1 = By.xpath("//div[@class='datepicker dropdown-menu'][1]/div/table[@class=' table-condensed']/thead/tr/th[@class='switch']");
		By nextlocator1 = By.xpath("//div[@class='datepicker dropdown-menu'][1]/div//table[@class=' table-condensed']/thead/tr/th[@class='next']");
		By dateslocator1 = By.xpath("//div[@class='datepicker dropdown-menu'][1]/div/table[@class=' table-condensed']/tbody/tr/td[@class='day ']");
		DateSelector(monthlocator1,nextlocator1,dateslocator1,input.get("CheckInExpectedMonth"),input.get("CheckInExpectedDate"));
		//WebElement checkOutdropBox = driver.findElement(checkOutdropBoxlocator);
		//checkOutdropBox.click();
		By monthlocator2 = By.xpath("//div[@class='datepicker dropdown-menu'][2]/div/table[@class=' table-condensed']/thead/tr/th[@class='switch']");
		By nextlocator2 = By.xpath("//div[@class='datepicker dropdown-menu'][2]/div//table[@class=' table-condensed']/thead/tr/th[@class='next']");
		By dateslocator2 = By.xpath("//div[@class='datepicker dropdown-menu'][2]/div/table[@class=' table-condensed']/tbody/tr/td[@class='day ']");
		DateSelector(monthlocator2,nextlocator2,dateslocator2,input.get("CheckOutExpectedMonth"),input.get("CheckOutExpectedDate"));
		WebElement travellersDropDown = driver.findElement(travellersDropDownlocator);
		travellersDropDown.click();
		WebElement nationalityDropBox = driver.findElement(nationalityDropBoxlocator);
		nationalityDropBox.click();
		Select natdropdown = new Select(nationalityDropBox);
		natdropdown.selectByVisibleText(input.get("Nationality"));
		WebElement roomsCount = driver.findElement(roomslocator);
		int roomCurrentCount = Integer.parseInt(roomsCount.getAttribute("value"));
		TravellersDetails(roomInclocator,roomDeclocator,input.get("Rooms"),roomCurrentCount);
		

		WebElement adultsCount = driver.findElement(adultslocator);
		int adultsCurrentCount = Integer.parseInt(adultsCount.getAttribute("value"));
		TravellersDetails(adultInclocator,adultDeclocator,input.get("Adults"),adultsCurrentCount);

		WebElement childCount = driver.findElement(By.id("childs"));
		int childCurrentCount = Integer.parseInt(childCount.getAttribute("value"));
		TravellersDetails(childInclocator,childDeclocator,input.get("Child"),childCurrentCount);
		
		travellersDropDown.click();
		JavaScriptExecutorClick(hotelSearch);
		if(input.get("Result").equalsIgnoreCase("Not Found"))
		{
			System.out.println("Hotels not found");
			
		}
		if(input.get("Result").equalsIgnoreCase("Found"))
		{
			selectHotel(input);
		}
		
	}
	public void selectHotel(HashMap<String,String> input) throws InterruptedException, IOException
	{
		WebElement hotelsListPage = driver.findElement(hotelsListPagelocator);
		VisibilityWebElementCheck(hotelsListPage);
		cookie_stopRemoval(cookiestoplocator);
		List<WebElement> hotelSearchList = driver.findElements(hotelSearchListlocator);
		for (int i = 0; i < hotelSearchList.size(); i++) {
			if (hotelSearchList.get(i).getAttribute("id").equalsIgnoreCase(input.get("HotelName"))) {

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
		confirmHotel(input);
	}
	public void confirmHotel(HashMap<String,String> input) throws InterruptedException, IOException {
		WebElement selectedHotelTitle = driver.findElement(selectedHotelTitlelocator);
		VisibilityWebElementCheck(selectedHotelTitle);
		if (selectedHotelTitle.getText().equalsIgnoreCase(input.get("HotelName"))) {

			WebElement bookNowBut = driver.findElement(bookNowlocator);

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click()", bookNowBut);
			System.out.println("call fill personal");
			
			fillDetails(input);

		} else {
			System.out.println("error in opening correct hotel page");
		}
	}
	public void fillDetails(HashMap<String,String>input) throws InterruptedException, IOException {
		WebElement first_name = driver.findElement(first_namelocator);
		
        VisibilityWebElementCheck(first_name);
		
		first_name.sendKeys(super.firstName1);
		WebElement last_name = driver.findElement(last_namelocator);
		WebElement email = driver.findElement(emaillocator);
		WebElement phone = driver.findElement(phonelocator);
		WebElement address = driver.findElement(addresslocator);
		
		last_name.sendKeys(super.lastName1);
		email.sendKeys(super.SignupEmail1);
		phone.sendKeys(super.Phone1);
		address.sendKeys("dummy address");
		WebElement totalPrice = driver.findElement(totalPricelocator);
		String displayedTotalPrice = totalPrice.getText();
		
			WebElement countryDropBox = driver.findElement(By.cssSelector("span[id*='select2-country']"));
			WebElement nationalityDropBox = driver.findElement(By.cssSelector("span[id*='select2-nationality']"));
			
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
}
