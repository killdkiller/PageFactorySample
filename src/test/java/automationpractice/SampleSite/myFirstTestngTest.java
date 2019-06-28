package automationpractice.SampleSite;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class myFirstTestngTest {
  @Test
  @Parameters({"url","browser"})
  public void f(String url, String browser) {
	  System.out.println("Running Test  with Url:"+url+"   and Browser :"+browser);
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Running Before Method");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Running After Method");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("Running Before Class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Running After Class");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Running brfore Test");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Running after test");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("Running Before Suite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("Running After Suite");
  }

}
