package com.qa.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import com.qa.util.WebEventListener;

public class base {
	
	
	public static WebDriver driver;
	public static Properties pro;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener webEventListener;
	
	
	public base()
	{
		pro=new Properties();
		try {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
		pro.load(fis);
		} catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
			
	}
	
	public static void init()
	{
	  String browserName=pro.getProperty("browser");
	  if(browserName.equalsIgnoreCase("chrome"))
	  {
		  System.setProperty("webdriver.chrome.driver","D:\\path");
		  driver = new ChromeDriver();
	  }
	  else if(browserName.equalsIgnoreCase("Firefox"))
	  {
		  System.setProperty("webdriver.gechodriver","D:\\");
		  driver = new FirefoxDriver();
	  }
	  else {
		  System.out.println("browser name not found or driver not found");
	  }
	  
	  e_driver=new EventFiringWebDriver(driver);
	  webEventListener =new WebEventListener();
	  e_driver.register(webEventListener);
	  driver = e_driver;
	  
	  driver.manage().window().maximize();
	  driver.manage().deleteAllCookies();
	  driver.get(pro.getProperty("URL"));
	  driver.manage().timeouts().pageLoadTimeout(4444, TimeUnit.SECONDS);
	  driver.manage().timeouts().implicitlyWait(9999, TimeUnit.SECONDS);
	  
	  
	}

}
