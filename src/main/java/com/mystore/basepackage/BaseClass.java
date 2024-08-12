package com.mystore.basepackage;

import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.mystore.actiondriver.Action;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static Properties prop;
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public void setDriver(WebDriver driverInstance) {
		this.driver.set(driverInstance);
	}

	public WebDriver getDriver() {
		return this.driver.get();
	}

	@BeforeTest
	public void loadConfig() {

		prop = new Properties();
		System.out.println("Super constructor invoked");
		try {
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\config.properties");
			prop.load(ip);
			System.out.println("Initial State of Driver= " + driver);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@BeforeClass
	@Parameters({ "os", "os_version", "browser", "browser_version" })
	public void launchAppBrowserStack(String os, String os_version, String browser, String browser_version)
			throws Exception {
		if (prop.getProperty("BrowserStack").equalsIgnoreCase("yes")) {
			String username = prop.getProperty("BROWSERSTACK_USERNAME");
			String accessKey = prop.getProperty("BROWSERSTACK_ACCESS_KEY");
			String browserstackURL = "p17l3ul2-hub.browserstack-ats.com/wd/hub";

			// Capabilities
			MutableCapabilities capabilities = new MutableCapabilities();
			HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
			capabilities.setCapability("browserName", browser);
			bstackOptions.put("os", os);
			bstackOptions.put("os_version", os_version);
			bstackOptions.put("browser_version", browser_version);
			bstackOptions.put("consoleLogs", "info");
			bstackOptions.put("browserstack.selenium_version", "3.14.0");
			bstackOptions.put("networkLogs", true);

			capabilities.setCapability("bstack:options", bstackOptions);
			capabilities.setCapability("name", "Parallel Test - Browser : " + browser + " OS : " + os);
			if (browser.equalsIgnoreCase("Edge") && prop.getProperty("BrowserStack").equalsIgnoreCase("no")) {
				EdgeOptions options = new EdgeOptions();
				options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
				options.addArguments("--remote-allow-origins=*");
				capabilities.setCapability(EdgeOptions.CAPABILITY, options);
			}
			if (browser.equalsIgnoreCase("Chrome") && prop.getProperty("BrowserStack").equalsIgnoreCase("no")
					&& os.equalsIgnoreCase("Windows")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=new");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			}
			if (browser.equalsIgnoreCase("Firefox") && prop.getProperty("BrowserStack").equalsIgnoreCase("no")
					&& os.equalsIgnoreCase("Windows")) {

				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("browser.download.folderList", 1);
				profile.setPreference("browser.download.manager.showWhenStarting", false);
				profile.setPreference("browser.download.manager.focusWhenStarting", false);
				profile.setPreference("browser.download.useDownloadDir", true);
				profile.setPreference("browser.helperApps.alwaysAsk.force", false);
				profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
				profile.setPreference("browser.download.manager.closeWhenDone", true);
				profile.setPreference("browser.download.manager.showAlertOnComplete", false);
				profile.setPreference("browser.download.manager.useWindow", false);
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--remote-allow-origins=*");
				options.setHeadless(true);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				options.setProfile(profile);
			}
			URL url = new URL("https://" + username + ":" + accessKey + "@" + browserstackURL);
			System.out.println("URL to hit is : " + url);
			setDriver(new RemoteWebDriver(url, capabilities));
			getDriver().manage().window().maximize();
			getDriver().manage().deleteAllCookies();
			System.out.println(getDriver() + " BrowserStack driver has been instantiated. ");

			Action.implicitWait(getDriver(), 10);
			Action.pageLoadTimeOut(getDriver(), 60);
			getDriver().get(prop.getProperty("url"));

		}

		// For Local Execution
		else {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			EdgeOptions optionsEdge = new EdgeOptions();
			optionsEdge.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
			optionsEdge.addArguments("--remote-allow-origins=*");
			if (browser.contains("Chrome")) {
				WebDriverManager.chromedriver().setup();
				setDriver(new ChromeDriver(options));
				getDriver().manage().window().maximize();
				System.out.println(getDriver() + " Driver has been instantiated. ");
			} else if (browser.contains("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				setDriver(new FirefoxDriver());
				getDriver().manage().window().maximize();
				System.out.println(getDriver() + " Driver has been instantiated. ");
			} else if (browser.contains("Edge")) {
				WebDriverManager.edgedriver().setup();
				setDriver(new EdgeDriver(optionsEdge));
				getDriver().manage().deleteAllCookies();
				getDriver().manage().window().maximize();
				System.out.println(getDriver() + " Driver has been instantiated. ");
			}
			Action.implicitWait(getDriver(), 10);
			Action.pageLoadTimeOut(getDriver(), 30);
			getDriver().get(prop.getProperty("url"));
		}
	}

	@AfterTest(alwaysRun = true)
	public void quitDriver() throws InterruptedException {
		WebDriver driver = getDriver();
		if (getDriver() != null) {
			System.out.println("Driver process found to close");
			driver.quit();
		} else {
			System.out.println("Driver instance is not closed.");
		}
	}
}
