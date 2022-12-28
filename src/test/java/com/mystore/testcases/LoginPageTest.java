/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.LoginPage;

/**
 * @author ravish.rana
 *
 */
public class LoginPageTest extends BaseClass {
	
	LoginPage loginPage;
	HomePage homePage;
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
	
	@BeforeMethod
	public void setUp() {
		
		extent.attachReporter(spark);
	}

	@AfterMethod
	public void teatDown() {
		extent.flush();
		driver.close();
	}
	@Test
	public void loginTest() throws InterruptedException {
		launchApp();
		System.out.println("Which Thread is getting executed : " + Thread.currentThread().getId());
		ExtentTest test = extent.createTest("Login test").assignAuthor("Ravish").assignDevice("Windows 11");
		loginPage = new LoginPage();
		test.log(Status.PASS, "Website Launched");
		Thread.sleep(2000);
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		test.log(Status.PASS, "User Logged In");
		String actualURL = homePage.getCurrentURL();
		String expectedURL = "https://www.saucedemo.com/inventory.html";
		test.info("Assertion for the Application URL.");
		Assert.assertEquals(actualURL, expectedURL);
		String path  = Action.TakeScreenShot(driver, "HomePage");
		test.pass(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	}
	
	@Test
	public void f1() throws InterruptedException {
		Thread.sleep(2000);
		launchApp();
		System.out.println("Which Thread is getting executed : " + Thread.currentThread().getId());
		ExtentTest test = extent.createTest("Login test2").assignAuthor("Ravish1").assignDevice("Windows 11");
		loginPage = new LoginPage();
		test.log(Status.PASS, "Website Launched");
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		test.log(Status.PASS, "User Logged In");
		String actualURL = homePage.getCurrentURL();
		String expectedURL = "https://www.saucedemo.com/inventory.html";
		test.info("Assertion for the Application URL.");
		Assert.assertEquals(actualURL, expectedURL);
		String path  = Action.TakeScreenShot(driver, "HomePage");
		test.pass(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	}

}
