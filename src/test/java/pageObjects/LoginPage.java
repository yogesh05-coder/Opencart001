package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	
	//Constructor
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//input[@id='input-email']") 
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id='input-password']") 
	WebElement txt_password;
	
	@FindBy(xpath="//input[@value='Login']") 
	WebElement btn_login;
	
	public void setEmailAddress(String emailId)
	{
		txt_email.sendKeys(emailId);
	}
	
	public void setpassword(String password)
	{
		txt_password.sendKeys(password);
	}
	
	public void clkLogin()
	{
		btn_login.click();
	}

}
