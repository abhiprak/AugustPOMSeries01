package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.pages.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.AppConstants;

public class HomePageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		System.out.println(browserName);
		driver=basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() throws InterruptedException {
		//Thread.sleep(5000);
		String title=homePage.getHomePageTitle();
		System.out.println("The title of the home page is " +title);
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE, "The title is incorrect");
	}
	
	@Test(priority=2)
	public void verifyHomePageHeaderTest() {
		String homePageHeader=homePage.getHomePageHeader();
		System.out.println("The header of the home page is " +homePageHeader);
		Assert.assertEquals(homePageHeader, AppConstants.HOME_PAGE_HEADER, "The home Page Header is incorrect");
	}
	
	@AfterTest
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}

}
