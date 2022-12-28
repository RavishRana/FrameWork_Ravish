package com.mystore.pageobjects;

import org.apache.commons.math3.analysis.function.Add;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;

public class SearchResultPage extends BaseClass {

	@FindBy(xpath = "//img[@title='Faded Short Sleeve T-shirts']")
	WebElement productResult;

	public SearchResultPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean isProductAvailable() {
		return Action.isDisplayed(driver, productResult);
	}

	public AddToCartPage clickOnProduct() throws Throwable {
		Thread.sleep(10000);
		Action.click(driver, productResult);
		return new AddToCartPage();
	}
}
