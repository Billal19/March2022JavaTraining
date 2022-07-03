package com.qa.opencart.tests;

import java.util.List;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.Constants;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;




@Epic("Epic - 200: this epic is for Accounts page of open cart application")
@Story("ACCPAGE - 201: design Accounts page with various features")

public class AccountPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetUp() {
		//accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		accPage = new AccountsPage(driver);
	}
	
	
	@Test
	
	public void accountPageTitleTest() {
		String actTitle = accPage.getAccountPageTitle();
		System.out.println("Acc page title is : " +actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
    @Test
	
	public void accountPageURLTest() {
		String actURL = accPage.getAccountPageURL();
		System.out.println("Acc page URL is : " +actURL);
		Assert.assertTrue(actURL.contains(Constants.ACCOUNTS_PAGE_URL_FRACTION));
	}
    
    @ Test
    public void accountPageHeaderTest() {
    	Assert.assertEquals(accPage.getAccountsPageHeader(), Constants.ACCOUNTS_PAGE_HEADER);
    }
    
    @Test
    public void accountsPageSectionsTest() {
    	List<String> actAccSecList = accPage.getAccountsPageSectionList();
    	System.out.println("Act section header list : " + actAccSecList);
    	Assert.assertEquals(actAccSecList, Constants.EXPECTED_ACCOUNT_SECTION_LIST);
    }
    
    @Test
    
    public void logoutLinkTest() {
    	Assert.assertTrue(accPage.isLogoutLinkExist() );
    }
    
   @Test
    
    public void searchExistTest() {
    	Assert.assertTrue(accPage.isSearchFieldExist());
    }
   
   @Test 
   public void logOutTest() {
	   Assert.assertEquals(accPage.clickOnLogout().getLogOutSuccessMesg(),Constants.LOGOUT_SUCCESS_MESSAGE);
   }
   
   @DataProvider
   public Object[][] getSearchKey() {
	   
	   return new Object[][] {
		   {"Macbook"},
		   {"Apple"},
		   {"Samsung"},
		   {"iMac"}
	   };
	   
   }
   
   @Test(dataProvider="getSearchKey")
   
   public void searchTest(String searchKey) {
	   searchResPage = accPage.doSearch(searchKey);
	   Assert.assertTrue(searchResPage.getSearchResultsCount()>0);
	   
   }
   
//   
//   @DataProvider
//   public Object[][] getProductName() {
//	   
//	   return new Object[][] {
//		   {"Macbook","MacBook Pro"},
//		   {"Macbook","MacBook Air"},
//		   {"Macbook","MacBook"},
//		   {"Apple","Apple Cinema 30\""},
//		   {"Samsung", "Samsung SyncMaster 941BW"},
//		   {"iMac", "iMac"}
//	   };
//	   
//   }
   
   @DataProvider
	public Object[][] getProductName(){
		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
	}
   
   @Test(dataProvider = "getProductName")
   public void selectProductTest(String searchKey, String productName) {
	   searchResPage = accPage.doSearch(searchKey);
	   productInfoPage = searchResPage.selectProduct(productName);
	   String productHeader = productInfoPage.getProductHeaderName();
	   System.out.println("product header : " + productHeader);
	   Assert.assertEquals(productHeader, productName);
	   
	   
   }
   
   
   
   @DataProvider
   public Object[][] getProductData() {
	   
	   return new Object[][] {
		   {"Macbook","MacBook Pro",4},
		   {"Samsung", "Samsung SyncMaster 941BW",1}
		   
	   };
	   
   }
   
   @Test(dataProvider = "getProductData")
   public void selectProductImageTest(String searchKey, String productName, int productImageCount) {
	   searchResPage = accPage.doSearch(searchKey);
	   productInfoPage = searchResPage.selectProduct(productName);
	   Assert.assertEquals(productInfoPage.getProductImagesCounts(), productImageCount );
	   
	   
   }
   
	@Test
	public void productInfoTest() {
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInformation();
		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
		softAssert.assertAll();
	}
	
	@Test
	public void productInfoDescriptionTest() {
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		softAssert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains("Latest Intel mobile architecture"));
		softAssert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains("new Core 2 Duo MacBook Pro is over 50%"));
		softAssert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains("Connect. Create. Communicate."));
		softAssert.assertAll();
	}
	
	@Test
	public void addToCartTest() {
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		String actSuccessMessg = productInfoPage
				.enterQty("1")
							.clickOnAddToCart()
									.getCartSuccessMessg();
		System.out.println("cart messg: " + actSuccessMessg);
		softAssert.assertTrue(actSuccessMessg.contains("MacBook Pro"));
		String actCartItemText = productInfoPage.getCartItemText();
		softAssert.assertTrue(actCartItemText.contains("1" + " item(s)"));
	}
	

}
