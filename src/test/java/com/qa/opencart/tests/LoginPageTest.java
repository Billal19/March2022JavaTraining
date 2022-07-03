package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic - 100: this epic is for login page of open cart application")
@Story("LOGIN - 101: design login page with various features")

public class LoginPageTest extends BaseTest {
	
	// This is the login page class where we have to write only our test cases with TestNG Annotation and Assertions for validation 
	// In this Login Page Test we will not declare the WebDriver here , because we will inherit it from the base class 
	
	@Description("login page title test....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("the Actual Title is : " + actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE); // In my Assertion , I used the Actual Title , and the Expected , the Expected title is saved in my constants class 
		// And what I did , i just Accessed the constant Variable name by writing the Constants class name.followed by the variable name
		
		
	}
	
	@Description("login page url test....")
	@Severity(SeverityLevel.NORMAL)
	
	@Test (priority =2)
	public void loginPageUrlTest() {
		String actURL = loginPage.getLoginPageUrl();
		System.out.println("the Actual URL is : " + actURL);
		Assert.assertTrue(actURL.contains(Constants.LOGIN_PAGE_URL_FRACTION));
		
	}
	
	@Description("forgot pwd link test....")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority =3)
	public void ForgetPwdLinkTest() {
		boolean pwdLink = loginPage.isForgotPwdExist(); 
		System.out.println("Is the password disabled " + pwdLink);
		Assert.assertTrue(pwdLink);
		
	}
	
	@Description("register link exist test....")
	@Severity(SeverityLevel.CRITICAL)
    @Test (priority =4)
	
	public void registerLinkTest() {
		boolean registerLink = loginPage.isForgotPwdExist(); 
		System.out.println("Is the password disabled " + registerLink);
		Assert.assertTrue(registerLink);
		
	}
	
	@Description("user is able to login to open cart application test....")
	@Severity(SeverityLevel.BLOCKER) 
    @Test (priority =5)
    
    public void loginTest() {
    	Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()).isLogoutLinkExist());
    	
    	
    	
    	
    	
    }

}
