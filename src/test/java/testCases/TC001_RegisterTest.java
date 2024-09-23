package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_RegisterTest extends BaseClass {
	
	String expectedMsg = "Your Account Has Been Created!";
	
	@Test(groups= {"Regression","Master"})
	public void testNavigateToRegisterPage() throws InterruptedException
	{
		logger.info("****Test is about to start*****");
		try {
		logger.info("Home page is loading");
		HomePage hp=new HomePage(driver);
		hp.clkMyAccountLink();
		logger.info("My account link is clicked on Home page");
		hp.clkRegisterLink();
		logger.info("Navigated to Register page");
		
		RegistrationPage regpage=new RegistrationPage(driver);
		regpage.setFirstName(randonString().toUpperCase());
		regpage.setlastName(randonString().toUpperCase());
		regpage.setEmail(randonString()+"@gmail.com");
		regpage.setTelPhone(randonNumber());
		String password = randonAlphaNumric();
		regpage.setPwd(password);
		regpage.setCnfPwd(password);
		regpage.selectAgreeCheckbox();
		logger.info("All data filled into Register page text fields");
		regpage.clkContinueBtn();	
		String acutalCnfMsg= regpage.getcnfmessage();
		if(acutalCnfMsg.equals(expectedMsg))
		{
			
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("***Test is Failed***");
			logger.debug("This is set to debug mode");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(acutalCnfMsg, expectedMsg);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("****Test is about to Complete*****");
		
	}
	
	
	
	
}
