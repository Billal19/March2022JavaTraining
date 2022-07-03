package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constant.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	
	public WebDriver driver ;
	public ElementUtil eleUtil;
	

	
	private By header = By.xpath("//img[@title='Antropy Demo Store']");
	private By logoutLink = By.xpath("//div[@class='list-group']//child::a[text()='Logout']");
	private By sectionHeader = By.cssSelector("div#content h2");
	private By search = By.cssSelector("input[name='search']");
	private By searchIcon = By.cssSelector("div#search button"); 
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver ;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public String getAccountPageTitle() {
		return eleUtil.waitForTitleIs(Constants.ACCOUNTS_PAGE_TITLE , Constants.DEFAULT_TIME_OUT);
	}
	
	public String getAccountPageURL() {
		return eleUtil.waitForUrlContains(Constants.ACCOUNTS_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	public String getAccountsPageHeader() {
		return eleUtil.doGetText(header);
	}
	
	public List<String> getAccountsPageSectionList() {
		List<WebElement> secList = eleUtil.getElements(sectionHeader);
		List<String> secValList = new ArrayList<>();
		for(WebElement e: secList) {
			String text = e.getText();
			secValList.add(text);
		}
		return secValList;
	}
	
	
	public boolean isLogoutLinkExist() {
		 return eleUtil.waitForElementVisible(logoutLink, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();	
	}
	
	public LoginPage clickOnLogout() {
		
		if(isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);	
		}
		return new LoginPage(driver);
	
	}
	
	public boolean isSearchFieldExist() {
		 return eleUtil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();	
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		if(isSearchFieldExist()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver); // this is called test driven development approach , where the method will return a class 
			
		}
		
		return null;
	}
	
	
	

	

}
