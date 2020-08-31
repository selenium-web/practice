package com.qa.practice;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReport {
	
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	
	@BeforeTest
	public void createExtentReportFile()
	{
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReports.html",true);
		extent.addSystemInfo("user name","abhijeet");
		extent.addSystemInfo("Environment", "window");
	}
	
	@AfterTest
	public void endReport()
	{
		extent.flush();
		extent.close();
	}
	
	@BeforeMethod
	public void initialization()
	{
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
	
		if(result.getStatus()==Failu)
		
		
	}
	
	}


