package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
	
	//Constructors
	public RegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators

	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txt_firstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txt_lastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txt_telPhone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_pwd;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txt_cnfpwd;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chk_boxAgree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btn_submit;
	
	@FindBy(xpath="//div[@id='content']//h1")
	WebElement getsuccessmsg;
	
	//Action Method //div[@id='content']//h1
	
	public void setFirstName(String fName)
	{
		txt_firstName.sendKeys(fName);
	}
	
	public void setlastName(String lName)
	{
		txt_lastName.sendKeys(lName);
	}
	
	public void setEmail(String email)
	{
		txt_email.sendKeys(email);
	}
	
	public void setTelPhone(String phone)
	{
		txt_telPhone.sendKeys(phone);
	}
	
	public void setPwd(String pwd)
	{
		txt_pwd.sendKeys(pwd);
	}
	
	public void setCnfPwd(String pwd)
	{
		txt_cnfpwd.sendKeys(pwd);
	}
	
	public void selectAgreeCheckbox()
	{
		chk_boxAgree.click();
	}
	
	public void clkContinueBtn()
	{
		btn_submit.click();
	}
	
	public String getcnfmessage()
	{
		try
		{String message= getsuccessmsg.getText();
		return message;
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
	}
	
}
