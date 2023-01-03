package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.internal.thread.ThreadExecutionException;

import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;

public class AddToCartPage extends BaseClass {
	WebDriver driver;
	public AddToCartPage(WebDriver driver) {
		this.driver=driver;
	}
	@FindBy(id = "quantity_wanted")
	WebElement quantity;
	
	@FindBy(name = "group_1")
	WebElement size;
	
	@FindBy(xpath = "//span[text()='Add to cart']")
	WebElement addToCardBtn;
	
	@FindBy(xpath = "//div[@id='layer_cart']//h2")
	WebElement successMessage;
	
	@FindBy(xpath = "//a[@id='button_order_cart']//i")
	WebElement proceedToCheckOut;
	
	public AddToCartPage() {	
		PageFactory.initElements(getDriver(), this);
	}
	
	public void enterQuantity(String quantityVal) throws Throwable {
		Thread.sleep(2000);
		Action.type(quantity, quantityVal);
	}
	
	public void selectSize(String sizeVal) throws Throwable {
		Thread.sleep(2000);
		Action.selectByVisibleText(sizeVal, size);
	}
	
	public void clickOnAddToCart() throws Throwable{
		Thread.sleep(2000);
		Action.click(getDriver(),addToCardBtn);
		Thread.sleep(4000);
	}
	
	public boolean validateAddToCart() throws Throwable {
		Thread.sleep(10000);
		return Action.isDisplayed(successMessage);
	}
	
	public void clickOnCheckOut() throws Throwable {
		Action.JSClick(getDriver(), proceedToCheckOut);
		Thread.sleep(3000);
	}

}
