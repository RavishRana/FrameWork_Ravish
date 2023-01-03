package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;

public class SearchResultPage extends BaseClass {

	@FindBy(xpath = "//img[@title='Faded Short Sleeve T-shirts']")
	WebElement productResult;
	WebDriver driver;
	public SearchResultPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isProductAvailable() {
		return Action.isDisplayed(productResult);
	}

	public AddToCartPage clickOnProduct() throws Throwable {
		Thread.sleep(10000);
		Action.click(getDriver(), productResult);
		return new AddToCartPage();
	}
}
