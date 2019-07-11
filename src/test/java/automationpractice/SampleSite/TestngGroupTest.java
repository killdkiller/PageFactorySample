package automationpractice.SampleSite;

import org.testng.annotations.Test;

public class TestngGroupTest {
	
	@Test(groups= {"Regression","Smoke Test"},priority=0)
	public void testCaseOne()
	{
	System.out.println("Im in testCaseOne - And in Regression Group");
	}
	@Test(groups="Regression",priority=1)
	public void testCaseTwo(){
	System.out.println("Im in testCaseTwo - And in Regression Group");
	}
	@Test(groups="Smoke Test", priority=2)
	public void testCaseThree(){
	System.out.println("Im in testCaseThree - And in Smoke Test Group");
	}
	@Test(groups="Regression", priority=3)
	public void testCaseFour(){
	System.out.println("Im in testCaseFour - And in Regression Group");
	}

}
