package org.SampleSite.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage  extends BasePage {
	private static final By SignOutForm_By = By.className("logout");
	private static final By AccountName_By = By.className("account");

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public void LogoutFromStore()
	{
		driver.findElement(SignOutForm_By).isEnabled();
		driver.findElement(SignOutForm_By).click();
	}
	
	public String getAccountName()
	{
		waitForElementVisible(AccountName_By);
		return(driver.findElement(AccountName_By).getText());
	}
	
	public boolean isSignOutEnabled()
	{
		return(driver.findElement(SignOutForm_By).isEnabled());
	}

}
