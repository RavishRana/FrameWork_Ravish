package com.mystore.reporting;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.mystore.basepackage.BaseClass;

public class ExtentReportManager implements ITestListener {

	static ExtentReports extent;
	static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	BaseClass base = new BaseClass();

	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter spark = new ExtentSparkReporter(
				System.getProperty("user.dir") + "\\Maven_TestNG\\Reports\\FlipKartTestReport.html");
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("FlipKart test Automation Report : " + context.getClass());
		spark.config().setEncoding("utf-8");
		spark.config().setReportName("Flipkart Test Automation Test Results");

		extent = new ExtentReports();
		extent.attachReporter(spark);
		XmlSuite suite = context.getSuite().getXmlSuite();
		extent.setSystemInfo("OS", suite.getParameter("os"));
		extent.setSystemInfo("OS Version", suite.getParameter("os_version"));
		extent.setSystemInfo("Browser", suite.getParameter("browser"));
		extent.setSystemInfo("Browser Version", suite.getParameter("browser_version"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
	}

	public static ExtentReports createInstance(String fileName) {
		ExtentSparkReporter spark = new ExtentSparkReporter(fileName);
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

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest("Flipkart Test Run");
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().log(Status.FAIL, "Test failed");
		test.get().log(Status.FAIL, result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().log(Status.SKIP, "Test skipped");
	}
	
	public void logInfo(String info) {
		test.get().log(Status.INFO, info);
	}
}
