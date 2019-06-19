package org.SampleSite.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import samplesite.utils.ConfigureConstants;

public class BasePage {
    WebDriver driver ;
    public BasePage(WebDriver driver)
    {
    	this.driver=driver;
    }
    
    public void waitForElementVisible(By by)
	{
		WebDriverWait wait = new WebDriverWait(driver, ConfigureConstants.MIDTIME);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public void waitForElemenClickble(By by)
	{
		WebDriverWait wait = new WebDriverWait(driver, ConfigureConstants.MIDTIME);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}
	
}
