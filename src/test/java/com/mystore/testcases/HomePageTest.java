/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.basepackage.BaseClass;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;

/**
 * @author ravish.rana
 *
 */
public class HomePageTest extends BaseClass {
	
	LoginPage loginPage;
	HomePage homePage;

	@BeforeMethod
	public void setUp() {
		launchApp();
	}

	@AfterMethod
	public void teatDown() {
		driver.quit();
	}
	
	@Test
	public void wishListTest() throws InterruptedException {
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		boolean wishlistVisible = homePage.validateMyWishlist();
		Assert.assertTrue(wishlistVisible);
	}
	@Test
	public void addItemToKart() throws InterruptedException {
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		boolean itemVisible = homePage.validateItemVisible();
		Assert.assertTrue(itemVisible);
	}
}
