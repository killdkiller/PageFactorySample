package automationpractice.SampleSite;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
	
	 @DataProvider(name = "additionData")
	    public Object[][] additionalData()
	    {
	        return new Object[][]{{1,2,3},
	                {2,6,8},{5,7,12}
	         };
	    }
	 
	 @Test(dataProvider ="additionData")
	    public void AdditionTest(long value1,long value2,long sumValue)
	    {
	        long iResult;
	        iResult = value1+value2;
	        Assert.assertEquals(iResult,sumValue);
	    }

}
