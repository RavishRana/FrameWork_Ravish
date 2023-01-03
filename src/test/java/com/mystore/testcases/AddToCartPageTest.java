/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.basepackage.BaseClass;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.SearchResultPage;

/**
 * @author ravish.rana
 *
 */
public class AddToCartPageTest extends BaseClass{
	
	IndexPage indexPage = new IndexPage(getDriver());
	SearchResultPage searchResultPage;
	AddToCartPage addToCartPage;
	
	@BeforeMethod
	public void setUp() {
		launchApp();
	}

	@AfterMethod
	public void teatDown() {
		getDriver().quit();
	}
	@Test
	public void addToCart() throws Throwable {
		indexPage.searchProject("t-shirts");
		addToCartPage = searchResultPage.clickOnProduct();
		addToCartPage.enterQuantity("3");
		addToCartPage.selectSize("M");
		addToCartPage.clickOnAddToCart();
		Assert.assertTrue(addToCartPage.validateAddToCart());
	}

}
