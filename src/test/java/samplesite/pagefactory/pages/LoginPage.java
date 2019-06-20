package samplesite.pagefactory.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
	
	@FindBy(id="email")
	private WebElement UserNameEle;
	
	@FindBy(id="passwd")
	private WebElement PasswordEle;
	
	@FindBy(id="SubmitLogin")
	private WebElement SignInFormEle;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, LoginPage.class);
	}
	
	public void enterUsername(String username)
	{
		UserNameEle.sendKeys(username);
	}
	public void enterPassword(String Password)
	{
		PasswordEle.sendKeys(Password);
	}
	public void clickOnSignIn()
	{
		SignInFormEle.click();
	}
	
	public void LoginToStore(String username, String password)
	{
		waitForElementVisible(UserNameEle);
		enterUsername(username);
		waitForElementVisible(PasswordEle);
		enterPassword(password);
		waitForElemenClickble(SignInFormEle);
		clickOnSignIn();
	}
	
	public boolean isSignInEnabled()
	{
		return (SignInFormEle.isEnabled());
	}

}