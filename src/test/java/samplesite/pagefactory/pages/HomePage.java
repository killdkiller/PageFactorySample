package samplesite.pagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import samplesite.utils.ConfigureConstants;

public class HomePage extends BasePage {
	@FindBy(className="logout")
	private WebElement SignOutFormEle;
	
	@FindBy(className="account")
	private WebElement AccountNameEle;
	
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void waitForElementVisible(WebElement ele)
		{
			System.out.println("Calling Home Page wait for Element");
			WebDriverWait wait = new WebDriverWait(driver, ConfigureConstants.MIDTIME);
			wait.until(ExpectedConditions.visibilityOf(ele));
		}
	
	public void LogoutFromStore()
	{
		SignOutFormEle.isEnabled();
		SignOutFormEle.click();
	}
	
	public String getAccountName()
	{
		waitForElementVisible(AccountNameEle);
		return(AccountNameEle.getText());
	}
	
	public boolean isSignOutEnabled()
	{
		return(SignOutFormEle.isEnabled());
	}

}
