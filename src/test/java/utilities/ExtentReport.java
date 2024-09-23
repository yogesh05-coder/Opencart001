package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReport implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Reports-" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

		sparkReporter.config().setDocumentTitle("Opencart Automation Report");
		sparkReporter.config().setReportName("Opencart Functional Report");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();

		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Applicaiton", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("submodule", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operation System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + "Got Test Success");

	}
	
	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName() + "Got Test Failure");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		
		try {
		String imgPath = new BaseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath);
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
		
	}
	
	
	public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + "Got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
		
	}

}
