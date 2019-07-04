package automationpractice.SampleSite;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

import samplesite.utils.ConfigureConstants;
import samplesite.utils.TestDataReader;

public class BaseTest {
	static WebDriver driver;
	TestDataReader objTestDataReader;
	
	public static void launchApplication()
	{
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\Driver_Executables\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(ConfigureConstants.MIDTIME, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(ConfigureConstants.MIDTIME, TimeUnit.SECONDS);
		driver.get(ConfigureConstants.URL);
		driver.manage().window().maximize();
	}
	
	public static void stopEngine() {
        driver.close();
        driver.quit();
    }
	
	@DataProvider(name="excelTestData")
	public Object[][] loginData() throws Exception
	{
		objTestDataReader = new TestDataReader();
		return objTestDataReader.gConvertToArray(objTestDataReader.gFuncReadTestData("Sheet1"));

	}

}
