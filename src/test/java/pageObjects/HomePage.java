package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	//Constructor
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//span[text()='My Account']") 
	WebElement lnk_myAccount;
	
	/*@FindBy(xpath="//span[normalize-space()='My Account']") 
	WebElement lnk_myAccount;*/
	
	@FindBy(xpath="//input[@placeholder='Search']") 
	WebElement txt_search;
	
	@FindBy(xpath="//span[normalize-space()='Wish List (0)']") 
	WebElement txt_wishlist;
	
	////input[@placeholder='Search'] //span[normalize-space()='Wish List (0)']
	
	@FindBy(xpath="//a[text()='Register']") 
	WebElement lnk_register;
	
	@FindBy(xpath="//a[contains(text(),'Login')]") 
	WebElement lnk_login;
	
	
	//Action Methods
	
	public void clkMyAccountLink()
	{
	
		lnk_myAccount.click();
	}
	
	public void txtSearchBox()
	{
		txt_search.sendKeys("Welcome");;
	}
	
	
	
	public void clkRegisterLink()
	{
		lnk_register.click();
	}
	
	public void clkLogInLink()
	{
		lnk_login.click();
	}
	
}
