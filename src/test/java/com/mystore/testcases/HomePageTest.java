/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.mystore.basepackage.BaseClass;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.LoginPage;

/**
 * @author ravish.rana
 *
 */
public class HomePageTest extends BaseClass {
	
	LoginPage loginPage;
	HomePage homePage;
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
	
	@BeforeMethod
	public void setUp() {
		launchApp();
	}
	@AfterMethod
	public void teatDown() throws InterruptedException {
		extent.flush();
		Thread.sleep(2000);
	}
	@AfterTest
	public void quitDriver() throws InterruptedException {
		driver.get().quit();
	}
	@Test
	public void f1() throws InterruptedException {
		Thread.sleep(2000);
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
		driver.get().manage().window().maximize();
		System.out.println("Home : Which Thread is getting executed : " + Thread.currentThread().getId());
		ExtentTest test = extent.createTest("HomePage").assignAuthor("Ravish Home").assignDevice("Windows 11");
		test.log(Status.PASS, "Home : Website Launched");
		try {
			Thread.sleep(2000);
			loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		} catch (InterruptedException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(Status.PASS, "User Logged In");
		String actualURL = homePage.getCurrentURL();
		String expectedURL = "https://www.saucedemo.com/inventory.html";
		test.info("Home : Assertion for the Application URL.");
		Assert.assertEquals(actualURL, expectedURL);
		/*
		 * String path = Action.TakeScreenShot("HomePage");
		 * test.pass(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		 */
	}
	
	/*
	 * @Test public void wishListTest() throws InterruptedException {
	 * loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	 * boolean wishlistVisible = homePage.validateMyWishlist();
	 * Assert.assertTrue(wishlistVisible); }
	 * 
	 * @Test public void addItemToKart() throws InterruptedException {
	 * loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	 * boolean itemVisible = homePage.validateItemVisible();
	 * Assert.assertTrue(itemVisible); }
	 */
}
