package samplesite.pagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LaunchPage extends BasePage {
	
	@FindBy(className="login")
	private WebElement SignInEle;

	public LaunchPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);	
	}
	
	public void clickOnSignIn()
	{
		waitForElementVisible(SignInEle);
		SignInEle.click();
	}

}
