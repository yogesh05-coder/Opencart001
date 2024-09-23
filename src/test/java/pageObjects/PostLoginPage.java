package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostLoginPage extends BasePage {
	
	//Constructor
		public PostLoginPage(WebDriver driver)
		{
			super(driver);
		}
		
		//Locators
		@FindBy(xpath="//h2[normalize-space()='My Account']") 
		WebElement txt_message;
		
		@FindBy(xpath="//span[text()='My Account']") 
		WebElement lnk_myAccount;
		
		@FindBy(xpath="//ul//a[text()='Logout']") 
		WebElement lnk_logout;
		
		
		//ActionMethods
		
		public boolean isMyAccountdisplay()
		{
			try
			{
				return (txt_message.isDisplayed());
			}
			catch(Exception e)
			{
				return false;
			}
		}
		
		public void clkMyAccount()
		{
			lnk_myAccount.click();
			//Integer.parseInt("765");
		}
		
		public void clkLogout()
		{
			lnk_logout.click();
		}

}
