package com.qa.opencart.constant;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	// This Class is created to maintain all the constant variable which might used across the entire framework.
	
	public final static String LOGIN_PAGE_TITLE ="Account Login";
	public final static String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public final static String ACCOUNTS_PAGE_TITLE = "Account Login";
	public final static String ACCOUNTS_PAGE_URL_FRACTION ="route=account/account";
	public final static String ACCOUNTS_PAGE_HEADER ="Antropy";
	
	public final static List<String> EXPECTED_ACCOUNT_SECTION_LIST = Arrays.asList("My Account","My Orders","Newsletter");
	
	public final static String LOGOUT_SUCCESS_MESSAGE ="Account Logout";
	public final static String ACCOUNT_REGISTER_SUCCESS_MESSG ="Your Account Has Been Created";
	
	
	//*************************WAIT TIME ************************************************
	
	public final static int DEFAULT_ELEMENT_TIME_OUT =10;
	public final static int DEFAULT_TIME_OUT =5;
	
	//**************************SHEET NAMES *********************************************
	
	public final static String REGISTER_SHEET_NAME = "register";
	public final static String PRODUCT_SHEET_NAME = "product";

}
