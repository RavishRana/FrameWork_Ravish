package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;

public class ShippingPage extends BaseClass{
	
	@FindBy(xpath = "//button/span[contains(text(),'Proceed to checkout')]")
	WebElement proceedToCheckOutBtn;
	
	@FindBy(id = "cgv")
	WebElement terms;
	
	public ShippingPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void checkTheTerms() {
		Action.click(driver, terms);
	}
	
	public PaymentPage clickOnProceedToCheckout() throws Throwable {
		Thread.sleep(3000);
		Action.click(driver, proceedToCheckOutBtn);
		return new PaymentPage();
	}
	

}
