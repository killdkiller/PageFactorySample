package org.SampleSite.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	public static final int MINTIME = 10;
	public static final int MIDTIME = 30;
	public static final int MAXTIME = 60;
    WebDriver driver ;
    public BasePage(WebDriver driver)
    {
    	this.driver=driver;
    }
    
    public void waitForElementVisible(By by)
	{
		WebDriverWait wait = new WebDriverWait(driver, MIDTIME);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public void waitForElemenClickble(By by)
	{
		WebDriverWait wait = new WebDriverWait(driver, MIDTIME);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}
	
}
