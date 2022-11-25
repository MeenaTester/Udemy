package Framework;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.FlightPage;
import PageObject.HotelPage;
import utils.BaseTest;

public class FlightBookingTest extends BaseTest{
	 public FlightBookingTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	public FlightPage flightPage;
	@Test(dataProvider = "getData",groups={"Regression"})
	public void FlightBookingTest(HashMap<String,String> input) throws InterruptedException, IOException
	{
		flightPage = getFlightPage();
		flightPage.BookFlight(input);
	}
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//resources//FlightBooking.json");
		return new Object[][]  {{data.get(0)},{data.get(1)}};
	}

}
