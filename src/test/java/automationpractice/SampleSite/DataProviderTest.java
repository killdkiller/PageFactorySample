package automationpractice.SampleSite;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
	
	 @DataProvider(name = "additionData")
	    public Object[][] additionalData()
	    {
	        return new Object[][]{{1,2,3},
	                {2,6,8},{5,7,12},{5,-10,-5}
	         };
	    }
	 
	 @Test(dataProvider ="additionData")
	    public void AdditionTest(long value1,long value2,long sumValue)
	    {
	        long iResult;
	        iResult = value1+value2;
	        Assert.assertEquals(iResult,sumValue);
	        System.out.println("After Assert");
	    }
	 @Test(dataProvider ="divisonData", expectedExceptions=ArithmeticException.class)
	 public void divison(int value1, int value2, int result)
	 {
		 long iResult;
	        iResult = value1/value2;
	        Assert.assertEquals(iResult,result);
	        System.out.println("After Divison Assert");
	 }
	 
	 @DataProvider(name = "divisonData")
	    public Object[][] divisonData()
	    {
	        return new Object[][]{{2,0,1},
	                {2,0,8}
	         };
	    }

}
