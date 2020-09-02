package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utils.AppConstants;
import com.qa.hubspot.utils.ElementUtil;

public class LoginPage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	//By Locators
	
	By username = By.id("username");
	By password = By.id("password");
	By login = By.id("loginBtn");
	By signUp = By.linkText("Sign up");
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
	}
	
	
	// Define Page Actions
	
	public String getPageTitle() {
		elementUtil.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE);
		return elementUtil.doGetTitle();
	}
	
	public boolean checkSignUpLink() {
		elementUtil.waitForElementPresent(signUp);
		return elementUtil.doIsDisplayed(signUp);
	}
	
	public HomePage doLogin(String uname, String pwd) {
	//	driver.findElement(username).sendKeys(uname);
	//	driver.findElement(password).sendKeys(pwd);
	//	driver.findElement(login).click();
		driver.navigate().refresh();
		elementUtil.doSendKeys(username, uname);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(login);
		return new HomePage(driver);
	}

}
