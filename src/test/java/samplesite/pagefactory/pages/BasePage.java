package samplesite.pagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import samplesite.utils.ConfigureConstants;

public class BasePage {
	    
	    public void waitForElementVisible(WebElement ele, WebDriver driver)
		{
			WebDriverWait wait = new WebDriverWait(driver, ConfigureConstants.MIDTIME);
			wait.until(ExpectedConditions.visibilityOf(ele));
		}
		
		public void waitForElemenClickble(WebElement ele,  WebDriver driver)
		{
			WebDriverWait wait = new WebDriverWait(driver, ConfigureConstants.MIDTIME);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		}

}
