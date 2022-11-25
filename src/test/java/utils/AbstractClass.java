package utils;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractClass extends BaseTest{
	public WebDriver driver;
	public WebDriverWait expWait;
	public  String firstName1;
	public String lastName1;
	public  String SignupEmail1;
	public  String Phone1;
	public String[] detailsArray;
	public AbstractClass(WebDriver driver)throws IOException {
		 this.driver = driver;
		 detailsArray = new String[5];
		expWait = new WebDriverWait(driver,Duration.ofSeconds(500));  
		 PageFactory.initElements(driver,this);
		 firstName1=super.personalDetailsArray[0];
		 lastName1=super.personalDetailsArray[1];
		 SignupEmail1=super.personalDetailsArray[2];
		 Phone1=super.personalDetailsArray[4];
		
	}
	
	
	 public void ElementToBeClickable(WebElement elem)                                
     {     
		 expWait = new WebDriverWait(driver,Duration.ofSeconds(100));
         expWait.until(ExpectedConditions.elementToBeClickable(elem)).click();;                  
     }
	 public void WaitForListToBeUpdated(By loc) throws InterruptedException                                
     {   
		 Thread.sleep(7000);
         //expWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(loc,10));                  
     }
	 public void frameToBeAvailableAndSwitchToIt(WebElement elem)                                
     {       		                                                                    
         expWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(elem));                  
     }
	 public void VisibilityWebElementCheck(WebElement elem) throws InterruptedException        
     {     
		  expWait.until(ExpectedConditions.visibilityOf(elem));
     }
	 public void DateSelector(By monthlocator,By nextlocator,By dateslocator,String ExpectedMonth,String ExpectedDate)
	 {
		    while (!driver.findElement(monthlocator).getAttribute("innerText").equalsIgnoreCase(ExpectedMonth)) {
		    	driver.findElement(nextlocator).click();
				// break;
			}
			List<WebElement> dates = driver.findElements(dateslocator);
			for (int i = 0; i < dates.size(); i++) {
				if (dates.get(i).getText().equalsIgnoreCase(ExpectedDate)) {
					dates.get(i).click();
					break;
				}
			}
	 }
	 public void DropBoxSelector(List<WebElement> arrayCollectionList,String ExpectedString)
	 {
		 for (int i = 0; i < arrayCollectionList.size(); i++) {
				if (arrayCollectionList.get(i).getText().equalsIgnoreCase(ExpectedString)) {
					arrayCollectionList.get(i).click();
					break;
				}
			}
	 }
	 public void JavaScriptExecutorClick(WebElement elem)
	 {
		 JavascriptExecutor jse = (JavascriptExecutor) driver;
		 jse.executeScript("arguments[0].click()", elem);
	 }
	 public void cookie_stopRemoval(By cookielocator) {
		 try {
				WebElement cookie_stop = driver.findElement(cookielocator);
				cookie_stop.click();
			} catch (Exception e) {

			}
	 }
	 
	 public void TravellersDetails(By Inclocator,By Declocator,String detailtype,int CurrentCount)
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
	 
}
