package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;

public class OrderSummaryPage extends BaseClass{

	@FindBy(xpath = "//button[@type='submit'")
	WebElement confirmOrderBtn;
	WebDriver driver;
	public OrderSummaryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnConfirmOrder() throws Throwable {
		Thread.sleep(3000);
		Action.click(getDriver(), confirmOrderBtn);
	}
}
