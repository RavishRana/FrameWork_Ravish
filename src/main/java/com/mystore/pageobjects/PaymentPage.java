package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;

public class PaymentPage extends BaseClass {

	@FindBy(xpath = "//a[@title='Pay by bank wire")
	WebElement payByBankWireBtn;
	
	@FindBy(xpath = "//a[@title='Pay by check")
	WebElement payByCheckBtn;
	WebDriver driver;
	public PaymentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnPaymentMethod() throws Throwable {
		Thread.sleep(6000);
		Action.click(getDriver(), payByBankWireBtn);
	}

}
