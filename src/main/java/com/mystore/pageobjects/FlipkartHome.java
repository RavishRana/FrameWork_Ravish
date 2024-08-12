package com.mystore.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mystore.actiondriver.Action;

public class FlipkartHome {

	WebDriver driver;
	Action action;

	String loginClose = "//span[@class='_30XB9F']";
	String searchBar = "//input[@title='Search for Products, Brands and More']";
	
	String mobilesResults = "(//a[contains(@href,'/search?q=samsung+galaxy+s10+lite+mobile') or contains(@href,'/search?q=samsung+galaxy+s10+5g') or contains(@href,'/search?q=samsung+galaxy+s10+plus+phone')])[1]";
	String mobilesResults2 = "//a[contains(@href,'/search?q=samsung+galaxy+s10+lite+mobile') or contains(@href,'/search?q=samsung+galaxy+s10+5g')]";
	String selectSamsungCheckBox = "//*[text()='SAMSUNG']//preceding-sibling::input";
	String flipKartAssuredCheckBox = "//*[text()='SAMSUNG']//ancestor::section/following-sibling::section//img/../../preceding-sibling::input";
	String sortListHighLow = "//*[text()='Price -- High to Low']";
	String allProductList = "//a[@class='CGtC98']";
	String productName = ".//div[@class='KzDlHZ']";
	String productPrice = ".//div[@class='Nx9bqj _4b5DiR']";
	String productLink = "//a[@class='CGtC98']";

	public FlipkartHome(WebDriver driver) {
		this.driver = driver;
		this.action = new Action(driver);
	}

	public void searchProduct(String productName) throws Exception {
		closeLoginPopupIfNeeded();
		searchForProduct(productName);
		selectProductFilters();
	}

	private void closeLoginPopupIfNeeded() throws Exception {
		if (action.isDisplayed(loginClose)) {
			action.click(loginClose);
		}
	}

	private void searchForProduct(String productName) throws Exception {
		try {
			action.fill(searchBar, productName);
			action.click(mobilesResults);
		} catch (Exception e) {
			retrySearch(productName);
		}
	}

	private void retrySearch(String productName) throws Exception {
		closeLoginPopupIfNeeded();
		action.fill(searchBar, productName);
		action.click(mobilesResults);
	}

	private void selectProductFilters() throws Exception {
		try {
			action.click(selectSamsungCheckBox);
			action.click(flipKartAssuredCheckBox);
			action.click(sortListHighLow);
		} catch (Exception e) {
			retrySearch(productName);
			action.click(selectSamsungCheckBox);
			action.click(flipKartAssuredCheckBox);
			action.click(sortListHighLow);
		}

	}

	public List<WebElement> getProducts() {
		return driver.findElements(By.xpath(allProductList));
	}

	public String getProductName(WebElement productElement) {
		return productElement.findElement(By.xpath(productName)).getText();
	}

	public String getProductPrice(WebElement productElement) {
		return productElement.findElement(By.xpath(productPrice)).getText();
	}

	public String getProductLink(WebElement productElement) {
		return productElement.getAttribute("href");
	}

}
