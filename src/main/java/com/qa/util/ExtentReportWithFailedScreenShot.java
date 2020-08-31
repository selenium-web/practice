package com.qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportWithFailedScreenShot {
	
	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	@BeforeTest
	public void setExtent()
	{		
    extent = new ExtentReports(System.getProperty("user.dir")+ "/test-output/ExtentReport.html",true);
	extent.addSystemInfo("User Name", "Abhijeet");
    extent.addSystemInfo("Host Name", "Window");
    extent.addSystemInfo("Environment", "QA");   
	}
	
	@AfterTest
	public void EndExtent()
	{
		extent.flush();
		extent.close();
	}
	
	public static String getScreenshoAs(WebDriver driver,String screenshotName) throws IOException
	{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	TakesScreenshot st = (TakesScreenshot) driver;
	File scrFile = st.getScreenshotAs(OutputType.FILE);
	String destination = (System.getProperty("user.dir")+"/FailedTestsScreenshot/" + screenshotName+dateName+".png");
	
	FileUtils.copyFile(scrFile, new File(destination));
	return destination;
		
	}
	
	@BeforeMethod
	public void setup(){
		
	}

	@AfterMethod
	
	public void teardown(ITestResult result) throws IOException
	{
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+ result.getName());
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
			String screenShotPath=null;
				
				screenShotPath=ExtentReportWithFailedScreenShot.getScreenshoAs(driver, result.getName());

			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenShotPath));
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			extentTest.log(LogStatus.SKIP,"TEST CASE SHKIP IS" +result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			extentTest.log(LogStatus.PASS, "TEST CASE PASS IS "+result.getName());
		}
		
		extent.endTest(extentTest);
		driver.quit();
	}
	{
		
	}
	
	
}
