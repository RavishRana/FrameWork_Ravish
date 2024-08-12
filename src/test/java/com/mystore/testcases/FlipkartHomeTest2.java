package com.mystore.testcases;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;
import com.mystore.pageobjects.FlipkartHome;

public class FlipkartHomeTest2 extends BaseClass {

	FlipkartHome flipKartHomeObj;

	@Test
	public void printProducts() throws Throwable {
		flipKartHomeObj = new FlipkartHome(getDriver());
		System.out.println(getDriver().getWindowHandle());
		flipKartHomeObj.searchProduct("Samsung Galaxy S10");
		List<WebElement> products = flipKartHomeObj.getProducts();

		for (WebElement product : products) {
			String name = flipKartHomeObj.getProductName(product);
			String price = flipKartHomeObj.getProductPrice(product);
			String link = flipKartHomeObj.getProductLink(product);

			// Print product details

			System.out.println("Name: " + name);
			System.out.println("Price: " + price);
			System.out.println("Link: " + link);
			System.out.println("-----------------------------");

		}
	}

}
