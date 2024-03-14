package com.ohrm.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ohrm.base.BaseTest;
import com.ohrm.pages.DashboardPage;
import com.ohrm.utils.ExcelUtils;
import com.ohrm.utils.LogUtils;

public class LoginTest extends BaseTest {

	private final String sheetName_test1 = "loginTest_ValidInvalidCreds";
	private final String sheetName_test2 = "loginTest_NoCreds";

	@Test(dataProvider = "loginValidInvalidCredsProvider", priority = 1, groups = { "Smoke" })
	public void loginTest_ValidInvalidCreds(String username, String password, String isValid) {
		LogUtils.startTestCase("loginTest_ValidInvalidCreds");

		DashboardPage dashboardPage = loginPage.loginApplication(username, password);
		if (Boolean.parseBoolean(isValid)) {
			Assert.assertTrue(dashboardPage.isDashboardDisplayed());
			loginPage = dashboardPage.logOut();
		} else {
			loginPage.isInvalidCredentialsDialogDisplayed();
		}

		LogUtils.endTestCase("loginTest_ValidInvalidCreds");
	}

	@Test(dataProvider = "loginMissingCredsProvider", priority = 2, groups = { "Smoke", "Sanity" }, enabled = false)
	public void loginTest_MissingCreds(String username, String password) {
		LogUtils.startTestCase("loginTest_MissingCreds");

		loginPage.loginApplication(username, password);
		if (username.isBlank())
			Assert.assertTrue(loginPage.isUsernameRequiredDisplayed());
		else
			Assert.assertFalse(loginPage.isUsernameRequiredDisplayed());

		if (password.isBlank())
			Assert.assertTrue(loginPage.isPasswordRequiredDisplayed());
		else
			Assert.assertFalse(loginPage.isPasswordRequiredDisplayed());
		loginPage = loginPage.refreshPage();

		LogUtils.endTestCase("loginTest_MissingCreds");
	}

	@DataProvider(name = "loginValidInvalidCredsProvider")
	public Object[][] loginDataProvider1() {
		ExcelUtils exUtils = new ExcelUtils();
		int rowCount = exUtils.getNoOfRows(sheetName_test1);
		int colCount = exUtils.getNoOfCols(sheetName_test1);
		System.out.println(rowCount + ", " + colCount);
		Object[][] data = new ExcelUtils().getSheetData(sheetName_test1, rowCount, colCount);
		return data;
	}

	@DataProvider(name = "loginMissingCredsProvider")
	public Object[][] loginDataProvider2() {
		ExcelUtils exUtils = new ExcelUtils();
		int rowCount = exUtils.getNoOfRows(sheetName_test2);
		int colCount = exUtils.getNoOfCols(sheetName_test2);
		System.out.println(rowCount + ", " + colCount);
		Object[][] data = new ExcelUtils().getSheetData(sheetName_test2, rowCount, colCount);
		return data;
	}
}
