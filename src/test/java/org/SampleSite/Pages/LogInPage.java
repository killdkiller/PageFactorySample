package org.SampleSite.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage  extends BasePage {
	
	private static final By UserName_By = By.id("email");
	private static final By Password_By = By.id("passwd");
	private static final By SignInForm_By = By.id("SubmitLogin");

	public LogInPage(WebDriver driver) {
		super(driver);	
	}
	
	public void enterUsername(String username)
	{
		driver.findElement(UserName_By).sendKeys(username);
	}
	public void enterPassword(String Password)
	{
		driver.findElement(Password_By).sendKeys(Password);
	}
	public void clickOnSignIn()
	{
		driver.findElement(SignInForm_By).click();
	}
	
	public void LoginToStore(String username, String password)
	{
		waitForElementVisible(UserName_By);
		enterUsername(username);
		waitForElementVisible(Password_By);
		enterPassword(password);
		waitForElemenClickble(SignInForm_By);
		clickOnSignIn();
	}
	
	public boolean isSignInEnabled()
	{
		return (driver.findElement(SignInForm_By).isEnabled());
	}
	

}
