package samplesite.pagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LaunchPage extends BasePage {
	
	@FindBy(className="login")
	private WebElement SignInEle;
	
	private WebDriver driver;

	public LaunchPage(WebDriver driver) {
		this.driver=driver;		
	}
	
	public void clickOnSignIn()
	{
		waitForElementVisible(SignInEle, driver);
		SignInEle.click();
	}

}
