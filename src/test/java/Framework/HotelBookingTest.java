package Framework;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.HotelPage;
import utils.BaseTest;

public class HotelBookingTest extends BaseTest{
	 public HotelBookingTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	public HotelPage hotelpage;
	@Test(dataProvider = "getData")
	public void HotelBookingTest(HashMap<String,String> input) throws InterruptedException, IOException
	{
		hotelpage = getHotelPageInstance();
		hotelpage.BookHotel(input);
	}
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//resources//HotelBooking.json");
		return new Object[][]  {{data.get(0)},{data.get(1)}};
	}

}
