package com.ohrm.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ohrm.base.BaseTest;
import com.ohrm.utils.ReportTestManager;

public class TestListener extends BaseTest implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		ReportTestManager.startTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ReportTestManager.getTest().pass("PASSED: " + result.getMethod().getMethodName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
		ReportTestManager.getTest().fail(result.getThrowable());
		try {
			driver = (WebDriver) (result.getTestClass().getRealClass().getField("driver").get(result.getInstance()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String testName = result.getMethod().getMethodName() + System.currentTimeMillis();
		String ssPath = captureScreenShot(testName, driver);
		ReportTestManager.getTest().addScreenCaptureFromPath(ssPath, testName);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		ReportTestManager.extent.flush();

	}

}
