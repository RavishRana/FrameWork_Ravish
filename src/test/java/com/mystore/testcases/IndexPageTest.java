package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.basepackage.BaseClass;
import com.mystore.pageobjects.IndexPage;

public class IndexPageTest extends BaseClass{

	IndexPage indexPage;
	@BeforeMethod
	public void setUp() {
		launchApp();
	}
	
	@AfterMethod
	public void teatDown() {
		driver.quit();
	}
	
	@Test
	public void verifyLogo() {
		indexPage = new IndexPage();
		boolean result =indexPage.validateLogo();
		Assert.assertTrue(result);
	}
	
	@Test
	public void verifyTitle() {
		String actualTitle = indexPage.getMyStoreTitle();
		Assert.assertEquals(actualTitle, "My Store");
	}
}
