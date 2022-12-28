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
import com.mystore.pageobjects.AddressPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.OrderConfirmationPage;
import com.mystore.pageobjects.OrderPage;
import com.mystore.pageobjects.OrderSummaryPage;
import com.mystore.pageobjects.PaymentPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.pageobjects.ShippingPage;

/**
 * @author ravish.rana
 *
 */
public class EndToEndTest extends BaseClass {
	LoginPage loginPage;
	AddressPage addressPage;
	SearchResultPage searchResultPage;
	AddToCartPage addToCartPage;
	OrderPage orderPage;
	ShippingPage shippingPage;
	PaymentPage paymentPage;
	OrderSummaryPage orderSummary;
	OrderConfirmationPage orderConfirmationPage;

	@BeforeMethod
	public void setUp() {
		launchApp();
	}

	@AfterMethod
	public void teatDown() {
		driver.quit();
	}
	@Test
	public void endToEndTest() throws Throwable {
		loginPage = new LoginPage();
		addToCartPage = searchResultPage.clickOnProduct();
		addToCartPage.enterQuantity("3");
		addToCartPage.selectSize("M");
		addToCartPage.clickOnAddToCart();
		orderPage = addToCartPage.clickOnCheckOut();
		loginPage = orderPage.clickOnCheckout();
		addressPage =loginPage.login1(prop.getProperty("username"), prop.getProperty("password"));
		shippingPage =addressPage.clickOnCheckout();
		shippingPage.checkTheTerms();
		paymentPage = shippingPage.clickOnProceedToCheckout();
		orderSummary = paymentPage.clickOnPaymentMethod();
		orderConfirmationPage = orderSummary.clickOnConfirmOrder();
		String actualMsg = orderConfirmationPage.validateConfirmMessage();
		String excpectedMsg = "Your order on My Store is complete";
		Assert.assertEquals(excpectedMsg, actualMsg);
	}

}
