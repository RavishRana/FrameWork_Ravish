package com.mystore.basepackage;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.mystore.actiondriver.Action;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static Properties prop;
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public void setDriver(WebDriver driverInstance) {
		this.driver.set(driverInstance);
	}

	public WebDriver getDriver() {
		return this.driver.get();
	}

	public static ExtentTest getTest() {
		return test.get();
	}

	public static void setTest(ExtentTest testInstance) {
		test.set(testInstance);
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
	@Parameters({ "browser" })
	public void launchApp(String browser) {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		// String browserName= prop.getProperty("browser");
		if (browser.contains("Chrome")) {
			WebDriverManager.chromedriver().setup();
			setDriver(new ChromeDriver(options));
			driver.get().manage().window().maximize();
			System.out.println(getDriver() + " Driver has been instantiated. ");
		} else if (browser.contains("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			setDriver(new FirefoxDriver());
			driver.get().manage().window().maximize();
			System.out.println(getDriver() + " Driver has been instantiated. ");
		} else if (browser.contains("Edge")) {
			WebDriverManager.edgedriver();
			setDriver(new EdgeDriver());
			System.out.println(getDriver() + " Driver has been instantiated. ");
		}
		Action.implicitWait(getDriver(), 10);
		Action.pageLoadTimeOut(getDriver(), 30);
		getDriver().get(prop.getProperty("url"));

		// Initialize ExtentReports and create a test instance
		if (extent == null) {
			ExtentSparkReporter spark = new ExtentSparkReporter(
					System.getProperty("user.dir") + "\\Maven_TestNG\\Reports\\FlipKartTestReport.html");
			spark.config().setDocumentTitle("Automation Report");
			spark.config().setReportName("Functional Testing");
			spark.config().setTheme(Theme.STANDARD);

			extent = new ExtentReports();
			spark.config().setDocumentTitle("Flipkart Product Scraper Report");
			spark.config().setReportName("Functional Test Report " + Action.generateRandomString());
			spark.config().setTheme(Theme.STANDARD);
			extent.attachReporter(spark);
			extent.setSystemInfo("Host Name", "Localhost");
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("User Name", "Ravish");
		}

		// Create a test node in ExtentReports
		ExtentTest testInstance = extent.createTest(getClass().getSimpleName() + " - " + browser);
		setTest(testInstance);
	}

	@AfterClass(alwaysRun = true)
	public void quitDriver() throws InterruptedException {
		WebDriver driver = getDriver();
		if (getDriver() != null) {
			System.out.println("Driver process found to close");
			driver.quit();
			this.driver.remove();
		} else {
			Assert.fail("Driver instance is not closed.");
		}
		if (extent != null) {
			extent.flush();
		}
	}

}
