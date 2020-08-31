package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.testbase.TestBasePrac;

public class LoginPagePrac extends TestBasePrac{
	
	@FindBy(name="email")
	WebElement email;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//div[contains(text(),'Login')]")
	WebElement loginBtn;
	
	@FindBy(xpath="//a[@href='https://ui.freecrm.com/')]")
	WebElement contactLink;
	
	@FindBy(xpath="//font[@color='#0e3655'][contains(.,'crm')]")
	WebElement crmLogo;
	
	public LoginPagePrac()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean validateCRMlogo()
	{
		return crmLogo.isDisplayed();
	}
	
	public String validateTitle()
	{
		return driver.getTitle();
	}
	
	public HomePagePrac validateLogin(String us,String pass)
	{
		email.sendKeys(us);
		password.sendKeys(pass);
		loginBtn.click();
		
		return new HomePagePrac();
	}
	

}
