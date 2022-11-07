package Framework;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.VisaPage;
import utils.BaseTest;

public class VisaSubmissionTest extends BaseTest{
   public VisaSubmissionTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

public  VisaPage visapage;
   
   @Test(dataProvider="getData")
   public void CheckVisaSubmission(HashMap<String,String> input) throws InterruptedException, IOException
   {
	   visapage = getVisaPageInstance();
	   String visaAcknowledgeMsg = visapage.VisaSubmission(input.get("fromCountry"),input.get("toCountry"),input.get("visaExpectedMonth"),input.get("visaExpectedDate"),firstName,lastName,SignupEmail,Phone);
       Assert.assertEquals(visaAcknowledgeMsg, "Your visa form has been submitted");
   }
   
   @DataProvider
	public Object[][] getData() throws IOException {
	   HashMap<String,String> map = new HashMap<String,String>();
		  map.put("fromCountry","India");
		  map.put("toCountry", "United States");
		  map.put("visaExpectedMonth", "January 2024");
		  map.put("visaExpectedDate", "26");
		  
		  HashMap<String,String> map1 = new HashMap<String,String>();
		  map1.put("fromCountry","United Arab Emirates");
		  map1.put("toCountry", "Albania");
		  map1.put("visaExpectedMonth", "December 2025");
		  map1.put("visaExpectedDate", "1");
		  
		  return new Object[][] {{map},{map1}};
   }
}
