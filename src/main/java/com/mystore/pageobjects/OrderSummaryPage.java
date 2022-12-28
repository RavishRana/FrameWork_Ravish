package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;

public class OrderSummaryPage extends BaseClass{

	@FindBy(xpath = "//button[@type='submit'")
	WebElement confirmOrderBtn;
	
	public OrderSummaryPage() {
		PageFactory.initElements(driver, this);
	}
	
	public OrderConfirmationPage clickOnConfirmOrder() throws Throwable {
		Thread.sleep(3000);
		Action.click(driver, confirmOrderBtn);
		return new OrderConfirmationPage();
	}
}
