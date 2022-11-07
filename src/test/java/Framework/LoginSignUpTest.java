package Framework;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.LoginSignUpPage;
import utils.BaseTest;

public class LoginSignUpTest extends BaseTest{
	public LoginSignUpTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginSignUpPage loginSignUpPage;
    @Test
	public void DoSignUp() throws InterruptedException, IOException
	{
    	loginSignUpPage = getLoginSignUpPageInstance();
    	loginSignUpPage.signUpNewUser(firstName,lastName,SignupEmail,SignUpPassword,Phone);
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


