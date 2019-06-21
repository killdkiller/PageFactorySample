package automationpractice.SampleSite;


import org.SampleSite.Pages.LogInPage;

import junit.framework.Assert;
import samplesite.pagefactory.pages.HomePage;
import samplesite.pagefactory.pages.LaunchPage;
import samplesite.pagefactory.pages.LoginPage;

public class MyStoreLoginPGFTest extends BaseTest {
	
	public static final String USERNAME = "shaik.sazid003@gmail.com";
	public static final String PASSWORD = "Test@1";
	public static final String SucessLogIn = "%s Successfully Logged In";
	public static final String SucessLogOut="%s Successfully Logged Out";

	public static void main(String[] args) {
		try {
			LoginPage loginpageObj;
			HomePage homepageObj;
			LaunchPage launchpageObj;
			String accountName;
			
			launchApplication();
			
			launchpageObj = new LaunchPage(driver);
			launchpageObj.clickOnSignIn();
			
			loginpageObj = new LoginPage(driver);
			loginpageObj.LoginToStore(USERNAME, PASSWORD);
			
			homepageObj = new HomePage(driver);
			accountName = homepageObj.getAccountName();
			Assert.assertTrue(homepageObj.isSignOutEnabled());			
			System.out.println(String.format(SucessLogIn, accountName));
			
			homepageObj.LogoutFromStore();
			loginpageObj = new LoginPage(driver);
			Assert.assertTrue(loginpageObj.isSignInEnabled());
			System.out.println(String.format(SucessLogOut, accountName));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			stopEngine();
		}
		

	}

}
