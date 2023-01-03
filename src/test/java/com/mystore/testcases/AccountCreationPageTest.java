/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.basepackage.BaseClass;
import com.mystore.pageobjects.AccountCreationPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;

/**
 * @author ravish.rana
 *
 */
public class AccountCreationPageTest extends BaseClass {

	LoginPage loginPage = new LoginPage(getDriver());
	AccountCreationPage accountCreationPage = new AccountCreationPage(getDriver());

	@BeforeMethod
	public void setUp() {
		launchApp();
	}

	@AfterMethod
	public void teatDown() {
		getDriver().quit();
	}
	@Test
	public void verifyAccoounCreationPageTest() {
		loginPage.createNewAccount("kjdfhksd@gmail.com");
		boolean result = accountCreationPage.validateAccountCreatePage();
		Assert.assertTrue(result);
	}

}
