package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	public WebDriver driver ;
	public ElementUtil eleUtil;
	
	private By productNameLink;
	
	private By searchResults = By.cssSelector("div.product-layout.product-grid");
	
	
	
	public SearchResultsPage(WebDriver driver) {
		this.driver= driver; 
		eleUtil = new ElementUtil(this.driver);
	}
	
	public int getSearchResultsCount() {
		return eleUtil.waitForElementsVisible(searchResults, Constants.DEFAULT_ELEMENT_TIME_OUT).size();
	}
	
	public ProductInfoPage selectProduct(String productName) { // this method is created to maintain the dynamic locator which is changing , that why we had to create a method for it instead of keeping it in the class level 
		productNameLink = By.linkText(productName); 
		eleUtil.doClick(productNameLink);
		return new ProductInfoPage(driver);
	}

}
