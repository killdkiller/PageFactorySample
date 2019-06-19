package org.SampleSite.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LaunchPage extends BasePage {
	
	private static final By SignIn_By =By.className("login"); 
	

	public LaunchPage(WebDriver driver) {
		super(driver);
		
	}
	
	public void clickOnSignIn()
	{
		waitForElementVisible(SignIn_By);
		driver.findElement(SignIn_By).click();
	}

}
