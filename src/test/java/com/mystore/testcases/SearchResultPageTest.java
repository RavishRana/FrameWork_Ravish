/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.basepackage.BaseClass;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.SearchResultPage;

/**
 * @author ravish.rana
 *
 */
public class SearchResultPageTest extends BaseClass {

	IndexPage indexPage;
	SearchResultPage searchResultPage;

	@BeforeMethod
	public void setUp() {
		launchApp();
	}

	@AfterMethod
	public void teatDown() {
		getDriver().quit();
	}

	@Test
	public void productVisibility() throws Throwable {
		indexPage.searchProject("t-shirts");
		Assert.assertTrue(searchResultPage.isProductAvailable());
	}

}
