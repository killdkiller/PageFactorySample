package samplesite.pagefactory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import samplesite.utils.ConfigureConstants;

public class BasePage {
	
	 WebDriver driver ;
	    public BasePage(WebDriver driver)
	    {
	    	this.driver=driver;
	    }
	    
	    public void waitForElementVisible(WebElement ele)
		{
			WebDriverWait wait = new WebDriverWait(driver, ConfigureConstants.MIDTIME);
			wait.until(ExpectedConditions.visibilityOf(ele));
		}
		
		public void waitForElemenClickble(WebElement ele)
		{
			WebDriverWait wait = new WebDriverWait(driver, ConfigureConstants.MIDTIME);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		}

}
