package PageObject;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.AbstractClass;

public class BookingPage extends AbstractClass {
	public WebDriver driver;
	public String downloadPath = "";

	public BookingPage(WebDriver driver) throws IOException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
    By Proceedbutlocator=By.id("form");
    By payPalConfirmationloactor= By.xpath("//div[@class='card-header']/small/strong");
    By backInvoicelocator=By.cssSelector("div[class*='btn']");
    By yessbuttlocator=By.xpath("//div[@class='btn-back']/a");
    By statusMsglocator=By.cssSelector("div[class*='infobox-danger']");
    By reservationMunlocator=By.xpath("//strong[@class='text-black mr-1']/parent::span");
    By downloadInvoicelocator=By.id("download");
    By Bookingcancellatioinlocator=By.xpath("//input[@value='Request Cancellation']");
    By alertMsglocator=By.cssSelector(".alert-danger");
	public void BookingDetailsPage(HashMap<String,String>input) throws InterruptedException
	{
		if(driver.findElement(By.xpath("//body")).getAttribute("innerText").equalsIgnoreCase("Booking Error Please Try Again."))
		{
			System.out.println("Booking Error");
			Assert.assertTrue(false, "Booking Error");
		}
		else
		{
		downloadPath = System.getProperty("user.dir");
		WebElement proceedButt = driver.findElement(Proceedbutlocator);
		//ElementToBeClickable(proceedButt);
		System.out.println("proceedButt "+proceedButt);
		WebElement payPalConfirmation = driver.findElement(payPalConfirmationloactor);
		VisibilityWebElementCheck(payPalConfirmation);
		if (payPalConfirmation.getText().split(" ")[0].equalsIgnoreCase("Paypal")) {
			WebElement backtoInvoice = driver.findElement(backInvoicelocator);
			backtoInvoice.click();
			WebElement Yesbutt = driver.findElement(yessbuttlocator);
			JavaScriptExecutorClick(Yesbutt);
			BackToBookingdetails(input);
		} else {
			System.out.println("Error : PayPal payment page is not reached ");
		}
		}
	}
	public void BackToBookingdetails(HashMap<String,String>input) throws InterruptedException {
		WebElement statusMsg = driver.findElement(statusMsglocator);
		System.out.println(statusMsg.getText());

		WebElement ReservatinNumElement = driver.findElement(reservationMunlocator);
		String ReservationNum = ReservatinNumElement.getAttribute("innerText").split(": ")[1];
		String InvoiceFileName = ReservationNum.split("-")[0] + "_" + ReservationNum.split("-")[1];

		WebElement downLoadInvoice = driver.findElement(downloadInvoicelocator);
		JavaScriptExecutorClick(downLoadInvoice);

		File f = new File(downloadPath + "/" + InvoiceFileName + ".jpg");
		Thread.sleep(2000);
		System.out.println("Invoice File Downloaded successfully : " + f.exists());

		WebElement bookingCancellation = driver.findElement(Bookingcancellatioinlocator);
		JavaScriptExecutorClick(bookingCancellation);
		driver.switchTo().alert().accept();
		WebElement alertMsg = driver.findElement(alertMsglocator);
		VisibilityWebElementCheck(alertMsg);
		System.out.println("Cancellation msg : " + alertMsg.getText());
	}
	
	
}
