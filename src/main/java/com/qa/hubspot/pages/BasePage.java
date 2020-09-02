package com.qa.hubspot.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	WebDriver driver;
	Properties prop;
	
	public WebDriver init_driver(String browserName) {
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			if(prop.getProperty("incognito").equals("yes")) {
				ChromeOptions co = new ChromeOptions();
				//co.addArguments("--headless");
				co.addArguments("--incognito");
				driver=new ChromeDriver(co);
			}
			else {
				driver = new ChromeDriver();
			}
		}
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		
		else {
			System.out.println("Please pass correct browser name ...");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		return driver;
		
	}
	
	public Properties init_properties() {
		prop = new Properties();
		String path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties";
		try {
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			System.out.println("Some issue with the config file ... Please check ...");
			//e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
}
