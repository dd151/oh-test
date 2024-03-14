package com.ohrm.utils;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ReportTestManager {

	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	public static ExtentReports extent = ExtentUtils.getInstance();

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) Thread.currentThread().getId());
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		extentTestMap.put((int) Thread.currentThread().getId(), test);
		return test;
	}

	public static synchronized void logMessage(String text) {
		getTest().log(Status.INFO, text);
		LogUtils.info(text);
	}
}
