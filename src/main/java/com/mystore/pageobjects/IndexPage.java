package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.basepackage.BaseClass;

public class IndexPage extends BaseClass{
	WebDriver driver;
	public IndexPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(getDriver(), this);
	}
	@FindBy(xpath = "//a[@class='login']")
	WebElement signinBtn;
	
	@FindBy(xpath = "//div[@class='login_logo']")
	WebElement myStoreLogo;
	
	@FindBy(id = "search_query_top")
	WebElement searchBox;
	
	@FindBy(name = "submit_search")
	WebElement searchBtn;
	
	
	public void clickOnSignIn() {
		Action.click(driver, signinBtn);
		
	}
	public boolean validateLogo() {
		return Action.isDisplayed(myStoreLogo);
	}
	
	public String getMyStoreTitle() {
		return getDriver().getTitle();
	}
	
	public void searchProject(String productname) throws InterruptedException {
		Action.fluentWait(getDriver(), searchBox, 20);
		Action.type(searchBox, productname);
		Thread.sleep(3000);
		Action.click(getDriver(), searchBtn);
	}
}
