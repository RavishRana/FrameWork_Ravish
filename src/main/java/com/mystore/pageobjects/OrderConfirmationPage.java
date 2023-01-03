package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;

public class OrderConfirmationPage extends BaseClass {
	
	@FindBy(xpath = "//strong[@class='dark']")
	WebElement orderCofirmMessage;
	WebDriver driver;
	public OrderConfirmationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String validateConfirmMessage() throws Throwable {
		Thread.sleep(3000);
		String confirmMessageText = orderCofirmMessage.getText();
		return confirmMessageText;
	}

}
