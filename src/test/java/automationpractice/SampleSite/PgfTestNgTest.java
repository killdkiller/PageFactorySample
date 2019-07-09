package automationpractice.SampleSite;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import samplesite.pagefactory.pages.HomePage;
import samplesite.pagefactory.pages.LaunchPage;
import samplesite.pagefactory.pages.LoginPage;

public class PgfTestNgTest extends BaseTest {
	
	public static final String USERNAME = "shaik.sazid003@gmail.com";
    public static final String PASSWORD = "Test@1";
    public static final String SucessLogIn = "%s Successfully Logged In";
    public static final String SucessLogOut="%s Successfully Logged Out";
    
    LoginPage loginpageObj;
    HomePage homepageObj;
    LaunchPage launchpageObj;
    String accountName;
    
    
    @BeforeClass
    public void intialize()
    {
                    launchApplication();
    }
    
    @Test 
    public void login()
    {
                    launchpageObj = new LaunchPage(driver);
                    launchpageObj.clickOnSignIn();
                    
                    loginpageObj = new LoginPage(driver);
                    loginpageObj.LoginToStore(USERNAME, PASSWORD);
                    
                    homepageObj = new HomePage(driver);
                    accountName = homepageObj.getAccountName();
                    Assert.assertTrue(homepageObj.isSignOutEnabled());                                     
                    System.out.println(String.format(SucessLogIn, accountName));
    }
    
    @Test(dataProvider="excelTestData")
    public void testExcelData(Map<String, String> objMap)
    {
                    
                    for(Map.Entry< String, String> temp : objMap.entrySet())
                    System.out.println("key :"+temp.getKey() +"   Value:"+temp.getValue());
    }
    
    @Test(dependsOnMethods="login") 
    public void logout()
    {
                    homepageObj.LogoutFromStore();
                    loginpageObj = new LoginPage(driver);
                    Assert.assertTrue(loginpageObj.isSignInEnabled());
                    System.out.println(String.format(SucessLogOut, accountName));
                    
    }
    
    @AfterClass
    public void deIntialize()
    {
                    stopEngine();
    }


}
