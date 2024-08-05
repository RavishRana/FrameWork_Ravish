package com.mystore.testcases;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;
import com.mystore.pageobjects.FlipkartHome;

public class FlipkartHomeTest extends BaseClass {

	FlipkartHome flipKartHomeObj;

	@Test
	public void printProducts() throws Throwable {
		flipKartHomeObj = new FlipkartHome(getDriver());
		getTest().info("Aplication has been launched..");
		getTest().info("Search product on Flipkart and get the list of Products");
		String screenshotPath = Action.TakeScreenShot(getDriver(), "Screenshot");
		getTest().addScreenCaptureFromPath(screenshotPath);
		flipKartHomeObj.searchProduct();
		getTest().info("User landed on the search result page and appliying filters.");
		List<WebElement> products = flipKartHomeObj.getProducts();
		getTest().info("Total Products listed on the page is  : " + products.size());

		for (WebElement product : products) {
			String name = flipKartHomeObj.getProductName(product);
			String price = flipKartHomeObj.getProductPrice(product);
			String link = flipKartHomeObj.getProductLink(product);

			// Print product details

			System.out.println("Name: " + name);
			System.out.println("Price: " + price);
			System.out.println("Link: " + link);
			System.out.println("-----------------------------");
			getTest().info("Printing Products...............");
			getTest().info("Name : " + name);
			getTest().info("Price : " + price);
			getTest().info("Link : " + link);

		}
	}

}
