package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	
	// The base class Test was created to run the test of launching and closing the browser 
	// The base class Test is the parent of every page test in our framework , it will be inherited by every single page test
	
	public DriverFactory df; // in here we maintained this variable of a driverFactory class type , to create an object of the DriverFactory class we set up for launching the browser
	public WebDriver driver; // in here we maintained a WebDriver 
    protected LoginPage loginPage; // in here we maintained this variable of a LoginPage class type to create an object of it, the reason why we created this in this class is because LoginPage class has the methods for our test cases 
    // The login page test will inherit the baseTest class , so it will easily access all the methods of the LoginPage Class , instead of creating an object of LoginPage inside the LoginPageTest class
    public Properties prop;
    
    
    protected AccountsPage accPage;
    protected SearchResultsPage searchResPage;
    protected ProductInfoPage productInfoPage;
    protected RegisterPage registerPage;
    public SoftAssert softAssert;
	

	
	@BeforeTest
	
	public void setUp() { 
		
		df = new DriverFactory(); // this is the object if the DriverFactory Class 
		prop = df.init_prop(); // in here we called the properties method 
		driver = df.init_driver(prop); // in here we called the method which is responsible for launching out browser
		loginPage = new LoginPage(driver); // in here we created an object of the LoginPage class , so the LoginPageTest class will access all its method since it's going to inherit the baseTest Class, we also parsed the driver in it
		softAssert = new SoftAssert();
		
	}
	
	
	@AfterTest
	
	public void tearDown() {
		driver.quit();
	}

}
