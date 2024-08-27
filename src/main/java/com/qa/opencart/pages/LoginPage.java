package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage
{
	private Page page;

	//1. String locators
	private String emailIdTxt = "//input[@id='input-email']";
	private String passwordTxt = "//input[@id='input-password']";
	private String loginBtn = "//input[@value='Login']";
	private String forgotPwdLink = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
	private String logoutLink = "//a[@class='list-group-item'][normalize-space()='Logout1']";
	
	//2. page constructor
	public LoginPage(Page page)
	{
		this.page = page;
	}

	//3. page actions/methods
	public String getLoginPageTitle() {
		return page.title();
	}
	
	public boolean isForgotPwdLinkExist() {
		return page.isVisible(forgotPwdLink);
	}
	
	//simple TDD method if we assume the account page has not been developed yet.
	public boolean doLogin(String appUsername, String appPassword) {
		System.out.println("App credentials: "+appUsername+":"+appPassword);
		page.fill(emailIdTxt, appUsername);
		page.fill(passwordTxt, appPassword);
		page.click(loginBtn);
		page.waitForTimeout(1000);
		if(page.isVisible(logoutLink)) {
			System.out.println("User is logged in successfully........");
			return true;
		}
		return false;
	}
	
}








