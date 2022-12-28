package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;

public class IndexPage extends BaseClass{
	
	@FindBy(xpath = "//a[@class='login']")
	WebElement signinBtn;
	
	@FindBy(xpath = "//div[@class='login_logo']")
	WebElement myStoreLogo;
	
	@FindBy(id = "search_query_top")
	WebElement searchBox;
	
	@FindBy(name = "submit_search")
	WebElement searchBtn;
	
	public IndexPage() {	
		PageFactory.initElements(driver, this);
	}
	
	public LoginPage clickOnSignIn() {
		Action.click(driver, signinBtn);
		return new LoginPage();
	}
	public boolean validateLogo() {
		return Action.isDisplayed(driver, myStoreLogo);
	}
	
	public String getMyStoreTitle() {
		return driver.getTitle();
	}
	
	public SearchResultPage searchProject(String productname) throws InterruptedException {
		Action.fluentWait(driver, searchBox, 20);
		Action.type(searchBox, productname);
		Thread.sleep(3000);
		Action.click(driver, searchBtn);
		return new SearchResultPage();
	}
}
