package com.qa.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.pages.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.AppConstants;
import com.qa.hubspot.utils.ElementUtil;

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	
	
	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		System.out.println(browserName);
		driver=basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		
	}
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest() throws InterruptedException {
		//Thread.sleep(5000);
		String title=loginPage.getPageTitle();
		System.out.println("The title of the login page is " +title);
		//Assert.assertEquals(title, "HubSpot Login", "The title is incorrect");
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE, "The title is incorrect");
	}
	
	@Test(priority=2)
	public void signUpLinkTest() {
		boolean b=loginPage.checkSignUpLink();
		System.out.println("The specified boolean condition is " +b);
		Assert.assertTrue(b);
	}
	
	@DataProvider
	public Object[][] getInvalidCredentials() {
		Object data [][] = {{"trik@gmail.com", "tytytyty"},{"dha@gmail.com", "tytytyty"}};
		return data;
	}
	
	
	@Test(priority=3, dataProvider = "getInvalidCredentials", enabled=false)
	public void invalidLoginTest(String username, String password) {
		loginPage.doLogin(username, password);
	}
	
	@Test(priority=4)
	public void loginTest() {
		
		HomePage homePage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
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
