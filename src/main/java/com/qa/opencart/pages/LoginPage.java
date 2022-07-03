package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	// Every Page Class should have a private WebDriver , because on each page class we will have to perform certain actions , that's why we need the WebDriver 
	// The WebDriver will be initialized by creating the  constructor of the page class 
	
	
	private WebDriver driver; // we declared the driver in this login page 
	private ElementUtil eleUtil; // we maintained this variable of ElemenUtil class type, to access all the method created in our element 
	
	//1. we will define the locator for login page , we will create Private locators , It's always recommended to keep our locator private , here where the encapsulation concepts comes into picture 
	
	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.xpath("//div[@class='form-group']//child::a[contains(text(),'Forgotten Password')]");
	private By registerLink = By.xpath("//div[@class='list-group']//child::a[contains(text(),'Register')]");
	private By logoutSuccessMesg = By.cssSelector("div#common-success h1");
	
	//2.we declared the contractor here : 
	public LoginPage(WebDriver driver) {
		this.driver=driver; // in here i initialized my driver using "This" Keyword 
		eleUtil = new ElementUtil(driver); // in here we are like creating an object of the ElementUtil class and parsed the browser in it 
	}
	
	
	//3.Page actions:
	@Step("getting login page title of open cart app...")
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	
	
	@Step("getting login page url of open cart app...")
	public String getLoginPageUrl() {
		return eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("user is able to login with username: {0} and password: {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("login creds are : " + un +":" +pwd);
		eleUtil.waitForElementVisible(emailID, Constants.DEFAULT_ELEMENT_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);// in here we did return accounts page because after clicking on login button , we will land in a new page page which accounts page
		
	}
	
	@Step("isForgotPwdLinkExist...")
	public boolean isForgotPwdExist() {
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}
	
	@Step("isRegisterLinkExist...")
	public boolean isRegisterLinkExist() {
		return eleUtil.isElementDisplayed(registerLink);
	}
	
	@Step("fetching success messg for logout...")
	public String getLogOutSuccessMesg() {
		return eleUtil.waitForElementVisible(logoutSuccessMesg, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
		
	}
	
	@Step("navigating to register page after clicking on register link....")
	public RegisterPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	

}
