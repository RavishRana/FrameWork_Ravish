package com.mystore.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.mystore.actiondriver.Action;

public class FlipkartHome{

	WebDriver driver;
	Action action;

	@FindBy(xpath = "//input[@title='Search for Products, Brands and More']")
	private WebElement SearchBar;

	@FindBy(xpath = "//*[text()='in Mobiles']//ancestor::a[contains(@href,'samsung+galaxy+s10')]")
	private WebElement mobiles_Results;

	@FindBy(xpath = "//*[text()='SAMSUNG']//preceding-sibling::input")
	private WebElement SelectSamsungCheckBox;

	@FindBy(xpath = "//*[text()='SAMSUNG']//ancestor::section/following-sibling::section//img/../../preceding-sibling::input")
	private WebElement FlipKartAssuredCheckBox;

	@FindBy(xpath = "//*[text()='Price -- High to Low']")
	private WebElement SortList_High_Low;

	@FindBy(xpath = "//a[@class='CGtC98']")
	private List<WebElement> AllProductList;

	@FindBy(xpath = "//div[@class='KzDlHZ")
	private WebElement ProductName;

	@FindBy(xpath = "//div[@class='Nx9bqj _4b5DiR']")
	private WebElement ProductPrice;

	@FindBy(xpath = "//a[@class='CGtC98']")
	private WebElement ProductLink;

	public FlipkartHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void searchProduct() throws Exception {
		action = new Action();
		action.fill(driver,SearchBar, "Samsung Galaxy S10");
		
		action.waitFor(2000);
		action.click(driver,mobiles_Results);
		action.waitFor(1000);
		action.click(driver,SelectSamsungCheckBox);
		action.waitFor(1000);
		action.click(driver,FlipKartAssuredCheckBox);
		action.waitFor(1000);
		action.click(driver,SortList_High_Low);
		action.waitFor(1000);
	}
	public List<WebElement> getProducts() {
		return AllProductList;
	}

	public String getProductName(WebElement productEle) {
			return productEle.findElement(By.xpath(".//div[@class='KzDlHZ']")).getText();
	}

	public String getProductPrice(WebElement productEle) {
		return productEle.findElement(By.xpath(".//div[@class='Nx9bqj _4b5DiR']")).getText();
	}

	public String getProductLink(WebElement productEle) {
		return productEle.getAttribute("href");
	}

}

