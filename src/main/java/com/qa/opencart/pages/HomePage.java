package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage
{
	Page page;
	//1. String locators
	private String search="input[name='search']";
	private String searchIcon = "div#search button";
	private String searchPageHeader = "div#content h1";
	private String loginLink = "a:text('Login')";
	private String myAccountLink = "a[title='My Account']";
	
	//2. page constructor
	public HomePage(Page page) {
		this.page=page;
	}
	
	//3. page actions/methods
	public String getHomePageTitle() {
		String title = page.title();
		System.out.println("Page title is : "+title);
		return title;
	}
	
	public String getHomePageURL() {
		String url = page.url();
		System.out.println("Page url is : "+url);
		return url;
	}
	
	public String doSearch(String productName) {
		page.fill(search, productName);
		page.click(searchIcon);
		String searchHeader=page.textContent(searchPageHeader);
		System.out.println("Search Header is : "+searchHeader);
		return searchHeader;
	}
	
	public LoginPage navigateToLoginPage() {
		page.click(myAccountLink);
		page.click(loginLink);
		return new LoginPage(page);
	}
			

}
