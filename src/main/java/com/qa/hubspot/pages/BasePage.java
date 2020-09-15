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

import com.qa.hubspot.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	public WebDriver init_driver(String browserName) {
		
		System.out.println("The browser name is: " + browserName);
		optionsManager = new OptionsManager(prop);
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
	//		driver = new ChromeDriver(optionsManager.getChromeOptions());==original line of code
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
	//		if(prop.getProperty("incognito").equals("yes")) {
	//			ChromeOptions co = new ChromeOptions();
	//			//co.addArguments("--headless");
	//			co.addArguments("--incognito");
	//			driver=new ChromeDriver(co);
	//		}
	//		else {
	//			driver = new ChromeDriver();
	//		}
		}
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			//driver=new FirefoxDriver();
		}
		
		else {
			System.out.println("Please pass correct browser name ...");
		}
		
		//driver.manage().deleteAllCookies();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
		
		//return driver;
		return getDriver();	
	}
	
	public Properties init_properties() {
		prop = new Properties();
		String path = null;
		String env = null;
		//String path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties";
		
		try {
			
			env = System.getProperty("env");
			if (env.equals("qa")) {
				path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\qa.config.properties";
			} else if (env.equals("stg")) {
				path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\stg.config.properties";
			}
		} catch (Exception e) {
			path = ".\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties";
		}
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
	
	public void getScreenshots() {
		
	}
}
