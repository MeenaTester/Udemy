package Framework;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.LoginSignUpPage;
import utils.BaseTest;

public class LoginSignUpTest extends BaseTest{
	public LoginSignUpPage loginSignUpPage;
	public LoginSignUpTest() throws IOException {
		super();
		
		// TODO Auto-generated constructor stub
	}
	
    @Test
	public void DoSignUp() throws InterruptedException, IOException
	{
    	loginSignUpPage = getLoginSignUpPageInstance();
    	loginSignUpPage.signUpNewUser(firstName,lastName,SignupEmail,SignUpPassword,Phone);
    	loginSignUpPage = null;
    	Assert.assertTrue(true);
	}
    @Test
    public void LoginUser() throws InterruptedException, IOException
    {
    	loginSignUpPage = getLoginSignUpPageInstance();
    	loginSignUpPage.LoginCurrentUser(LoginUserName, LoginPassword);
    	Assert.assertTrue(true);
    }
}


