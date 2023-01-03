package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;

public class HomePage extends BaseClass {
	WebDriver driver;

	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	WebElement myKart;

	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	WebElement addItem;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean validateMyWishlist() {
		return Action.isDisplayed(myKart);
	}

	public boolean validateItemVisible() {
		return Action.isDisplayed(addItem);
	}

	public String getCurrentURL() {
		String homePageURL = driver.getCurrentUrl();
		return homePageURL;
	}

}
