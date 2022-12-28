package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;

public class OrderPage extends BaseClass {
	
	public OrderPage() {	
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//td[@data-title='Unit price']/span/span")
	WebElement unitPrice;

	@FindBy(xpath = "//span[@id='total_price']")
	WebElement totalPrice;
	
	@FindBy(xpath = "//span[text()='Proceed to checkout']")
	WebElement proceedToCheckOut;
	
	public double getUnitPrice() throws Throwable {
		Thread.sleep(5000);
		String UnitPriceVal = unitPrice.getText();
		String Unit = UnitPriceVal.replaceAll("[^a-zA-Z0-9]","");
		double finalUnitPrice = Double.parseDouble(Unit);
		return finalUnitPrice/100;
	}
	
	public double getTotalPrice() throws Throwable{
		Thread.sleep(5000);
		String TotalPriceVal = totalPrice.getText();
		String Total = TotalPriceVal.replaceAll("[^a-zA-Z0-9]","");
		double finalTotalPrice = Double.parseDouble(Total);
		return finalTotalPrice/100;
	}
	
	public LoginPage clickOnCheckout() {
		Action.click(driver, proceedToCheckOut);
		return new LoginPage();
	}
}
