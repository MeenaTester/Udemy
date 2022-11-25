package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObject.FlightPage;
import PageObject.HotelPage;
import PageObject.LoginSignUpPage;
import PageObject.VisaPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public String downloadPath;

	public  String firstName;
	public String lastName;
	public  String SignupEmail;
	public  String Phone;
	public  String SignUpPassword;
	public  String LoginUserName;
	public  String LoginPassword;
	public String browserName;

	public LoginSignUpPage loginSignUpPage;
	public VisaPage visapage;
	public HotelPage hotelpage;
	public FlightPage flightpage;
	public String[] personalDetailsArray;
	public Properties prop;
    public BaseTest () throws IOException
    {
    	File filePath = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\global.properties");
		FileInputStream fis = new FileInputStream(filePath);
		prop = new Properties();
		prop.load(fis);
		browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		downloadPath = System.getProperty("user.dir");
		firstName = prop.getProperty("FirstName");
		lastName = prop.getProperty("LastName");
		SignupEmail = prop.getProperty("SignUpEmail");
		SignUpPassword = prop.getProperty("SignUppassword");
		Phone = prop.getProperty("Phone");
		personalDetailsArray = new String[5];
		personalDetailsArray[0]=firstName;
		personalDetailsArray[1]=lastName;
		personalDetailsArray[2]=SignupEmail;
		personalDetailsArray[3]=SignUpPassword;
		personalDetailsArray[4]=Phone;
		System.out.println(personalDetailsArray[3]+" :"+personalDetailsArray[4]);
    }
	public WebDriver initialize() throws IOException {
		
		LoginUserName = prop.getProperty("LoginUserName");
		LoginPassword = prop.getProperty("LoginPassword");
		if (browserName.equalsIgnoreCase("chrome")) {
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadPath);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);

		}
		if (browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions options1 = new FirefoxOptions();
			FirefoxProfile profile = new FirefoxProfile();
			// Instructing firefox to use custom download location
			profile.setPreference("browser.download.folderList", 2);
			// Setting custom download directory
			profile.setPreference("browser.download.dir", downloadPath);
			// Skipping Save As dialog box for types of files with their MIME
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"application/x-msexcel,application/excel,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/vnd.microsoft.portable-executable");
			options1.setProfile(profile);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(options1);
		}
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public void getDriverinstance() throws IOException {
		driver = initialize();
	}

	public LoginSignUpPage getLoginSignUpPageInstance() throws IOException {
		loginSignUpPage = new LoginSignUpPage(driver);
		return loginSignUpPage;
	}
	
	public FlightPage getFlightPage() throws IOException
	{
		flightpage = new FlightPage(driver);
		return flightpage;
	}

	public VisaPage getVisaPageInstance() throws IOException {
		visapage = new VisaPage(driver);
		return visapage;
	}

	public HotelPage getHotelPageInstance() throws IOException {
		hotelpage = new HotelPage(driver);
		return hotelpage;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        // String to HashMap- Jackson Databind
        ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
        // {map, map}

	}
	public String getSCreenShot(String testCaseName,WebDriver driver1) throws IOException
	{
		TakesScreenshot ts= (TakesScreenshot)driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+".\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(srcFile, destFile);
		return System.getProperty("user.dir")+".\\reports\\"+testCaseName+".png";
	}
	
	public String getpersonalDetailsArray()
	{
		
		System.out.println("get: "+firstName);
		return firstName;
	}
    
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() { 
		System.out.println("test over "+driver);
		driver.close();
		// driver=null;
	}
}
