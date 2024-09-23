package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.PostLoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDTest extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups={"Datadriven","Master"})//getting data provider from different class
	public void loginDataDrivenTest(String emailId, String pwd, String expResult)
	{
		logger.info("*****Started the TC003 DDTestCase user Login****");
		
		try
		{
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clkMyAccountLink();
		hp.clkLogInLink();
		
		//LoginPage
		LoginPage lp= new LoginPage(driver);
		lp.setEmailAddress(emailId);
		lp.setpassword(pwd);
		lp.clkLogin();
		
		//PostLoginPage
		PostLoginPage pstLoginPage = new PostLoginPage(driver);
		
		boolean status= pstLoginPage.isMyAccountdisplay();
		
		/* Data is Valid  - login success  - test pass  -logout
		 * 				  - login failed  - test fail
		 * 
		 * Data is Invalid - login success -test fail  -logout
		 * 					-login fail    - test pass
		 */
		
		if(expResult.equalsIgnoreCase("Valid"))
		{
			if(status==true)
			{
				pstLoginPage.clkMyAccount();
				pstLoginPage.clkLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(expResult.equalsIgnoreCase("Invalid"))
		{
			if(status==true)
			{
				pstLoginPage.clkMyAccount();
				pstLoginPage.clkLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		
		logger.info("*****Started the TC003 DDTestCase user Login****");
		
	}
	

}
