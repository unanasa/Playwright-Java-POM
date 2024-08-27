package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory
{

	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	Properties prop;

	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();

	public static Playwright getPlaywright()
	{
		return tlPlaywright.get();
	}

	public static Browser getBrowser()
	{
		return tlBrowser.get();
	}

	public static BrowserContext getBrowserContext()
	{
		return tlBrowserContext.get();
	}

	public static Page getPage()
	{
		return tlPage.get();
	}

	public Page initBrowser(Properties prop)
	{

		String browserName = prop.getProperty("browser").trim();

		System.out.println("Browser name is : " + browserName);

		// playwright = Playwright.create();
		tlPlaywright.set(Playwright.create());

		switch (browserName.toLowerCase())
		{
		case "chrome":
			// browser = playwright.chromium().launch(new
			// LaunchOptions().setChannel("chrome").setHeadless(false));
			tlBrowser.set(
					getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;

		case "firefox":
			// browser = playwright.firefox().launch(new
			// BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		case "safari":
			// browser = playwright.webkit().launch(new
			// BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		case "chromium":
			// browser = playwright.chromium().launch(new
			// BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;

		default:
			System.out.println("Please send the correct browser name...");
			break;
		}

		// browserContext = browser.newContext();
		tlBrowserContext.set(getBrowser().newContext());
		// page = browserContext.newPage();
		tlPage.set(getBrowserContext().newPage());
		// page.navigate(prop.getProperty("url").trim());
		getPage().navigate(prop.getProperty("url").trim());

		return getPage();

	}

	/**
	 * this method is used to initialize the properties from config file
	 */
	public Properties init_prop()
	{
		try
			{
				FileInputStream ip = new FileInputStream("./src/test/resource/config/config.properties");
				prop = new Properties();
				prop.load(ip);
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		return prop;

	}

	/**
	 * to take screenshots of passed/failed test cases
	 */
	public static String takeScreenshot()
	{
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		
		getPage().screenshot(new Page.ScreenshotOptions()
				.setPath(Paths.get(path))
				.setFullPage(true));
		
		return path;

	}

}
