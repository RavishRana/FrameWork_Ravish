package com.mystore.testcases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.mystore.basepackage.BaseClass;
import com.mystore.pageobjects.FlipkartHome;
import com.mystore.reporting.ExtentReportManager;

public class FlipkartHomeTest extends BaseClass {

	FlipkartHome flipKartHomeObj;
	ExtentReportManager extentReportMananger = new ExtentReportManager();

	@Test
	public void printProducts() throws Throwable {
		flipKartHomeObj = new FlipkartHome(getDriver());
		System.out.println(getDriver().getWindowHandle());
		flipKartHomeObj.searchProduct();
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
			extentReportMananger.logInfo("Product Name : " + name + " ------------------ " + "Product Price : " + price + " ------------------ " + "Product link : " + link );

		}
	}

}
