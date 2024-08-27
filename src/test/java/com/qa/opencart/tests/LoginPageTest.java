package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest
{
	@Test(priority = 1)
	public void loginPageNavigationTest() {
		loginPage = homePage.navigateToLoginPage();
		String actualLoginPageTitle = loginPage.getLoginPageTitle();
		System.out.println("Actual login page title is: "+actualLoginPageTitle);
		Assert.assertEquals(actualLoginPageTitle, AppConstants.Login_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test(priority = 3)
	public void appLoginTest() {
		Assert.assertTrue(loginPage.doLogin(
				prop.getProperty("username").trim(), prop.getProperty("password").trim()));
	}

}










