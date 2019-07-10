package automationpractice.SampleSite;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import samplesite.pagefactory.pages.HomePage;
import samplesite.pagefactory.pages.LaunchPage;
import samplesite.pagefactory.pages.LoginPage;

public class LoginTests extends BaseTest {
	public static final String USERNAME = "shaik.sazid003@gmail.com";
    public static final String PASSWORD = "Test@1";
    public static final String SucessLogIn = "%s Successfully Logged In";
    public static final String SucessLogOut="%s Successfully Logged Out";
    
    LoginPage loginpageObj;
    HomePage homepageObj;
    LaunchPage launchpageObj;
    String accountName;
	
	@BeforeMethod
	public void intialize()
    {
                    launchApplication();
    }
	
	@Test(dataProvider="excelTestData")
	public void loginTest(Map<String, String> objMap)
	{
		launchpageObj = new LaunchPage(driver);
        launchpageObj.clickOnSignIn();
        
        loginpageObj = new LoginPage(driver);
        loginpageObj.LoginToStore(objMap.get("username"), objMap.get("password"));
        
        if(objMap.get("validLogin").equalsIgnoreCase("TRUE"))
        {
        	homepageObj = new HomePage(driver);
            accountName = homepageObj.getAccountName();
            Assert.assertTrue(homepageObj.isSignOutEnabled());                                     
            System.out.println(String.format(SucessLogIn, accountName));
            logout();
        }
        else
        {
        	Assert.assertTrue(loginpageObj.isAuthanticationFailedAlertPresent());
        	System.out.println("Invalid Credentials");
        }
        
        
	}
	
	@AfterMethod
    public void deIntialize()
    {
                    stopEngine();
    }
	
	public void logout()
    {				homepageObj = new HomePage(driver);
                    homepageObj.LogoutFromStore();
                    loginpageObj = new LoginPage(driver);
                    Assert.assertTrue(loginpageObj.isSignInEnabled());
                    System.out.println(String.format(SucessLogOut, accountName));
                    
    }

}
