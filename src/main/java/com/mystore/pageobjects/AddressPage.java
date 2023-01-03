package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;

public class AddressPage extends BaseClass{

	@FindBy(xpath = "//span[text()='Proceed to checkout']")
	WebElement proceedToCheckOut;
	
	WebDriver driver;
	public AddressPage(WebDriver driver) {	
		this.driver = driver;
		PageFactory.initElements(getDriver(), this);
	}
	
	public void clickOnCheckout() throws Throwable {
		Thread.sleep(3000);
		Action.click(getDriver(), proceedToCheckOut);
	}
}
