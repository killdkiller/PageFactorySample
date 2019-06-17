package automationpractice.SampleSite;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class MyStoreLogin {
	public static final String URL = "http://automationpractice.com/index.php";
	public static final int MINTIME = 10;
	public static final int MIDTIME = 30;
	public static final int MAXTIME = 60;
	public static final String USERNAME = "shaik.sazid003@gmail.com";
	public static final String PASSWORD = "Test@1";
	
	public static final String SucessLogIn = "%s Successfully Logged In";
	public static final String SucessLogOut="%s Successfully Logged Out";
	static WebDriver driver ;
	
	private static final By SignIn_By =By.className("login"); 
	private static final By UserName_By = By.id("email");
	private static final By Password_By = By.id("passwd");
	private static final By SignInForm_By = By.id("SubmitLogin");
	private static final By SignOutForm_By = By.className("logout");
	private static final By AccountName_By = By.className("account");
	
	public static void main(String args[])
	{
		try{
			System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\Driver_Executables\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(MIDTIME, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(MIDTIME, TimeUnit.SECONDS);
			
			driver.get(URL);
			driver.manage().window().maximize();
			
			driver.findElement(SignIn_By).click();
			
			waitForElementVisible(UserName_By);
			driver.findElement(UserName_By).sendKeys(USERNAME);
			waitForElementVisible(Password_By);
			driver.findElement(Password_By).sendKeys(PASSWORD);
			waitForElemenClickble(SignInForm_By);
			driver.findElement(SignInForm_By).click();
			
			waitForElementVisible(AccountName_By);
			String AccountName = driver.findElement(AccountName_By).getText();
			
			Assert.assertTrue(driver.findElement(SignOutForm_By).isEnabled());			
			System.out.println(String.format(SucessLogIn, AccountName));
			
			driver.findElement(SignOutForm_By).isEnabled();
			driver.findElement(SignOutForm_By).click();
			
			waitForElementVisible(SignInForm_By);
			Assert.assertTrue(driver.findElement(SignInForm_By).isEnabled());
			System.out.println(String.format(SucessLogOut, AccountName));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			driver.close();
		}
	}
	
	public static void waitForElementVisible(By by)
	{
		WebDriverWait wait = new WebDriverWait(driver, MIDTIME);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public static void waitForElemenClickble(By by)
	{
		WebDriverWait wait = new WebDriverWait(driver, MIDTIME);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

}
