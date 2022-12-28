package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;

public class LoginPage extends BaseClass{
	
	@FindBy(name = "user-name")
	WebElement userName;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(id = "login-button")
	WebElement LoginBtn;
	
	@FindBy(name = "email_create")
	WebElement emailForNewAccount;
	
	@FindBy(name = "SubmitCreate")
	WebElement createAnAccountBtn;
	
	public LoginPage() {	
		PageFactory.initElements(driver, this);
	}
	
	public HomePage login(String uname, String pswd) throws InterruptedException {
		Action.fluentWait(driver, userName, 2);
		Action.type(userName, uname);
		Action.type(password, pswd);
		Action.fluentWait(driver, LoginBtn, 2);
		Action.click(driver, LoginBtn);
		return new HomePage();
	}
	
	public AddressPage login1(String uname, String pswd) {
		Action.type(userName, uname);
		Action.type(password, pswd);
		Action.click(driver, LoginBtn);
		return new AddressPage();
	}
	public AccountCreationPage createNewAccount(String newEmail) {
		Action.type(emailForNewAccount, newEmail);
		Action.click(driver, createAnAccountBtn);
		return new AccountCreationPage();
	}
}
