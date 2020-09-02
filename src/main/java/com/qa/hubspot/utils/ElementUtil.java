package com.qa.hubspot.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIME_OUT);
	}
	
	public boolean waitForTitlePresent(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}
	
	public boolean waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}
	
	public boolean waitForElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}
	
	public String doGetTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			System.out.println("Some exception got occurred while fetching the title of the page");
			//e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This method is used to create the web element on the basis of By locator
	 * @param locator
	 * @return
	 */
	
	public WebElement doGetElement(By locator) {
		WebElement element = null;
		try {
			//if(waitForElementPresent(locator));
			element = driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("Some exception got occurred while creating the web element");
			//	e.printStackTrace();
		}
		return element;
	}
	
	public void doClick(By locator) {
		try {
			driver.findElement(locator).click();
		} catch (Exception e) {
			System.out.println("Some exception got occurred while clicking on the web element");
			//e.printStackTrace();
		}
	}
	
	public void doSendKeys(By locator, String value) {
		try {
			WebElement ele = doGetElement(locator);
			//ele.clear();
			ele.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
			ele.sendKeys(value);
		} catch (Exception e) {
			System.out.println("Some exception got occurred while entering value in the field");
			//e.printStackTrace();
		}
	}
	
	public boolean doIsDisplayed(By locator) {
		try {
			return doGetElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("Some exception got occurred while displaying the field");
			//e.printStackTrace();
		}
		return false;
	}
	
	public String doGetText(By locator) {
		try {
			return doGetElement(locator).getText();
		} catch (Exception e) {
			System.out.println("Some exception got occurred while getting the text of the WebElement");
			//e.printStackTrace();
		}
		return null;
	}

}
