package com.qa.testbase;

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


public class TestBasePrac {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener webEventListener;
	public FileInputStream fi;
	
	
	public TestBasePrac()
	{
		
		try {
			prop=new Properties();
			 fi=new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/crm"
					+ "/qa/config/config.properties");
			 prop.load(fi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}		
	}
	
	public static void init() {
		
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome"))
		{
			System.setProperty("WebDriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
			driver=new ChromeDriver();		
		}
		else if(browserName.equals("FF"))
		{
			System.setProperty("WebDriver.chrome.driver", "D:\\Softwares\\chromedriver.exe");
			driver=new FirefoxDriver();
			
		}
		else {
			System.out.println("browser name not found inconfig property file ");
		}
		
		e_driver = new EventFiringWebDriver(driver);
		webEventListener = new WebEventListener();
		e_driver.register(webEventListener);
		driver=e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(11, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);	
	}    
}
