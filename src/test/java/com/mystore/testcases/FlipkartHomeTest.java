package com.mystore.testcases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.mystore.basepackage.BaseClass;
import com.mystore.pageobjects.FlipkartHome;
import com.mystore.reporting.ExtentReportManager;
import com.mystore.reporting.RetryAnalyzer;

public class FlipkartHomeTest extends BaseClass {

	FlipkartHome flipKartHomeObj;
	ExtentReportManager extentReportMananger = new ExtentReportManager();

	@Test()
	public void printProducts() throws Throwable {

		flipKartHomeObj = new FlipkartHome(getDriver());
		flipKartHomeObj.searchProduct("Samsung Galaxy S10 ");
		List<WebElement> products = flipKartHomeObj.getProducts();

		for (WebElement product : products) {
			String name = flipKartHomeObj.getProductName(product);
			String price = flipKartHomeObj.getProductPrice(product);
			String link = flipKartHomeObj.getProductLink(product);

			System.out.println("Name: " + name);
			System.out.println("Price: " + price);
			System.out.println("Link: " + link);
			System.out.println("-----------------------------");
			extentReportMananger.logInfo("Product Name : " + name + " ------------------ " + "Product Price : " + price
					+ " ------------------ " + "Product link : " + link);

		}

	}

}
