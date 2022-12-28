package com.mystore.basepackage;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.mystore.actiondriver.Action;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static Properties prop;
	public static WebDriver driver;
	
	
	
	@BeforeTest
	public void loadConfig() {

		prop = new Properties();
		System.out.println("Super constructor invoked");
		try {
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\config.properties");
			prop.load(ip);
			System.out.println("Initial State of Driver= " + driver);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void launchApp() {
		WebDriverManager.chromedriver().setup();
		//WebDriverManager.firefoxdriver().setup();
		//WebDriverManager.iedriver().setup();
		String browserName= prop.getProperty("browser");
		if(browserName.contains("Chrome")) {
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			System.out.println(driver + " Driver has been instantiated. ");
		}
		else if(browserName.contains("Firefox")) {
			driver = new FirefoxDriver();
			System.out.println(driver + " Driver has been instantiated. ");
		}
		else if(browserName.contains("IE")) {
			driver = new InternetExplorerDriver();
			System.out.println(driver + " Driver has been instantiated. ");
		}
		Action.implicitWait(driver, 10);
		Action.pageLoadTimeOut(driver, 30);
		driver.get(prop.getProperty("url"));
	}
	
}
