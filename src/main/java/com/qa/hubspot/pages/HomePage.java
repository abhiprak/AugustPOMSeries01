package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utils.AppConstants;
import com.qa.hubspot.utils.ElementUtil;

public class HomePage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	By header = By.xpath("//h1[@class='dashboard-selector__title']");
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getHomePageTitle() {
		elementUtil.waitForTitlePresent(AppConstants.HOME_PAGE_TITLE);
		return elementUtil.doGetTitle();
	}
	
	public String getHomePageHeader() {
	//	String pageHeader=driver.findElement(header).getText();
	//	elementUtil.doGetElement(header)
	//	System.out.println("The header of the Home Page is " + pageHeader);
		String pageHeader = elementUtil.doGetText(header);
		return pageHeader;
	}

}
