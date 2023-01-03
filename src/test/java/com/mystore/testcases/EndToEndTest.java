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
	LoginPage loginPage = new LoginPage(getDriver());
	AddressPage addressPage = new AddressPage(getDriver());
	SearchResultPage searchResultPage = new SearchResultPage(getDriver());
	AddToCartPage addToCartPage = new AddToCartPage(getDriver());
	OrderPage orderPage = new OrderPage(getDriver());
	ShippingPage shippingPage = new ShippingPage(getDriver());
	PaymentPage paymentPage = new PaymentPage(getDriver());
	OrderSummaryPage orderSummary = new OrderSummaryPage(getDriver());
	OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(getDriver());

	@BeforeMethod
	public void setUp() {
		launchApp();
	}

	@AfterMethod
	public void teatDown() {
		getDriver().quit();
	}
	@Test
	public void endToEndTest() throws Throwable {
		searchResultPage.clickOnProduct();
		addToCartPage.enterQuantity("3");
		addToCartPage.selectSize("M");
		addToCartPage.clickOnAddToCart();
		addToCartPage.clickOnCheckOut();
		orderPage.clickOnCheckout();
		loginPage.login1(prop.getProperty("username"), prop.getProperty("password"));
		addressPage.clickOnCheckout();
		shippingPage.checkTheTerms();
		shippingPage.clickOnProceedToCheckout();
		paymentPage.clickOnPaymentMethod();
		orderSummary.clickOnConfirmOrder();
		String actualMsg = orderConfirmationPage.validateConfirmMessage();
		String excpectedMsg = "Your order on My Store is complete";
		Assert.assertEquals(excpectedMsg, actualMsg);
	}

}
