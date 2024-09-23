package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.PostLoginPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups= {"Sanity","Master"})
	public void loginTest()
	{
		logger.info("*****Started the TC002TestCase user Login****");
		
		try
		{
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clkMyAccountLink();
		hp.clkLogInLink();
		
		//LoginPage
		LoginPage lp= new LoginPage(driver);
		lp.setEmailAddress(prop.getProperty("email"));
		lp.setpassword(prop.getProperty("password"));
		lp.clkLogin();
		
		//PostLoginPage
		PostLoginPage pstLoginPage = new PostLoginPage(driver);
		
		boolean status= pstLoginPage.isMyAccountdisplay();
		
		Assert.assertEquals(status, true);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("*****Started the TC002TestCase user Login****");
		
	}
	

}
