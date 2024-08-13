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
    private static Properties prop;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeTest
    public void loadConfig() {
        prop = new Properties();
        try (FileInputStream ip = new FileInputStream(
                System.getProperty("user.dir") + "\\Configuration\\config.properties")) {
            prop.load(ip);
            System.out.println("Configuration loaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    @Parameters({ "os", "os_version", "browser", "browser_version" })
    public void launchAppBrowserStack(String os, String os_version, String browser, String browser_version)
            throws Exception {
        if (isBrowserStackEnabled()) {
            launchBrowserStack(os, os_version, browser, browser_version);
        } else {
            launchLocalBrowser(browser, os);
        }
    }

    private boolean isBrowserStackEnabled() {
        return prop.getProperty("BrowserStack").equalsIgnoreCase("yes");
    }

    private void launchBrowserStack(String os, String os_version, String browser, String browser_version)
            throws Exception {
        String username = prop.getProperty("BROWSERSTACK_USERNAME");
        String accessKey = prop.getProperty("BROWSERSTACK_ACCESS_KEY");
        String browserstackURL = "c5u1kh5g-hub.browserstack-ats.com/wd/hub";

        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("os", os);
        bstackOptions.put("os_version", os_version);
        bstackOptions.put("browser_version", browser_version);
        bstackOptions.put("consoleLogs", "info");
        bstackOptions.put("browserstack.selenium_version", "3.14.0");
        bstackOptions.put("networkLogs", true);
        capabilities.setCapability("bstack:options", bstackOptions);
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("name", "Parallel Test - Browser : " + browser + " OS : " + os);

        setDriver(new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@" + browserstackURL), capabilities));
        setupBrowser();
    }

    private void launchLocalBrowser(String browser, String os) {
        WebDriver localDriver = null;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                localDriver = new ChromeDriver(getChromeOptions());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                localDriver = new FirefoxDriver(getFirefoxOptions(os));
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                localDriver = new EdgeDriver(getEdgeOptions());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        setDriver(localDriver);
        setupBrowser();
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return options;
    }

    private FirefoxOptions getFirefoxOptions(String os) {
        FirefoxOptions options = new FirefoxOptions();
        if (os.equalsIgnoreCase("Windows")) {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.folderList", 1);
            profile.setPreference("browser.download.manager.showWhenStarting", false);
            profile.setPreference("browser.helperApps.alwaysAsk.force", false);
            profile.setPreference("browser.download.manager.closeWhenDone", true);
            profile.setPreference("browser.download.manager.showAlertOnComplete", false);
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
            options.setProfile(profile);
        }
        return options;
    }

    private EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
        options.addArguments("--remote-allow-origins=*");
        return options;
    }

    private void setupBrowser() {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        Action.implicitWait(driver, 10);
        Action.pageLoadTimeOut(driver, 60);
        driver.get(prop.getProperty("url"));
    }

    @AfterTest(alwaysRun = true)
    public void quitDriver() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            System.out.println("Driver process terminated successfully.");
        } else {
            System.out.println("No driver instance found to close.");
        }
    }
}
