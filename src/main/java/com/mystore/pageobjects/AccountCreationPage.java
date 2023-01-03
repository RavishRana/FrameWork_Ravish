package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;

public class AccountCreationPage extends BaseClass {
	WebDriver driver;
	public AccountCreationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "h1[text()='Create an Account']")
	WebElement formTitle;
	
	public boolean validateAccountCreatePage() {
		return Action.isDisplayed(formTitle);
	}

}
