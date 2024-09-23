package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public class BaseClass {
	
public static WebDriver driver;
public Logger logger;
public Properties prop;
	
    
	@Parameters({"os","browser"})
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	
	public void setUp(@Optional("windows") String os, @Optional("chrome")String browser) throws IOException
	{
		FileInputStream file =new FileInputStream("./src//test//resources//config.properties");
		prop = new Properties();
		prop.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		if(prop.getProperty("environment").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capability= new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows"))
			{
				capability.setPlatform(Platform.WINDOWS);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				capability.setPlatform(Platform.MAC);
			}
			else if (os.equalsIgnoreCase("linux"))
			{
				capability.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("invalid os declared");
			}
			
			switch(browser.toLowerCase())
			{
			case "chrome" : capability.setBrowserName("chrome"); break;
			case "firefox" : capability.setBrowserName("firefox"); break;
			case "edge" : capability.setBrowserName("MicrosoftEdge"); break;
			default : System.out.println("invalid browser name"); return;
			
			}
			driver =new RemoteWebDriver(new URL(prop.getProperty("gridurl")),capability);
		}
		
		
		if(prop.getProperty("environment").equals("local"))
		{
		switch(browser.toLowerCase())
		{
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		default : System.out.println("Invalid browser Name"); return;
		
		}
		}
		//driver = new ChromeDriver();
		
		//driver= new EdgeDriver();
		driver.manage().window().maximize();
		//driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(prop.getProperty("url"));
		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+ tname + "_" + timeStamp +".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
		
	}
	
	public String randonString()
	{
		String generateString= RandomStringUtils.randomAlphabetic(5);
		return generateString;
	}

	public String randonNumber()
	{
		String generateNumber= RandomStringUtils.randomNumeric(10);
		return generateNumber;
	}
	
	public String randonAlphaNumric()
	{
		String generateString= RandomStringUtils.randomAlphabetic(5);
		String generateNumber= RandomStringUtils.randomNumeric(4);
		String generateAlphaNum= (generateString+"@$"+generateNumber);
		return generateAlphaNum;
	}

}
