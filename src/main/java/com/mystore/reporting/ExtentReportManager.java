package com.mystore.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

	static ExtentReports extent = new ExtentReports(); 
	ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "Maven_TestNG/Reports/FlipKartTestReport.html"); 
	static ExtentTest test;

	public static ExtentReports createInstance(String fileName) {
		ExtentSparkReporter spark = new ExtentSparkReporter(fileName);
		ExtentReportManager.logTestStart("Flipkart Search Test");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setEncoding("utf-8");
		spark.config().setReportName("Automation Test Results");

		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Browser", "Chrome");
		return extent;
	}

	public static void logInfo(String message) {
		test.info(message);
	}

	public static void logTestStart(String testName) {
		test = extent.createTest(testName);
	}

	public static void logTestEnd() {
		extent.flush();
	}

}
