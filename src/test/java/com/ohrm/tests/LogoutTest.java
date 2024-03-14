package com.ohrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ohrm.base.BaseTest;
import com.ohrm.pages.DashboardPage;
import com.ohrm.utils.LogUtils;
import com.ohrm.utils.PropertiesUtils;

public class LogoutTest extends BaseTest{

	@Test(priority = 1, groups = {"Smoke"})
	public void logoutTest() {
		LogUtils.startTestCase("logOutTest");
		
		String username = PropertiesUtils.getInstance().getUsername();
		String password = PropertiesUtils.getInstance().getPassword();
		DashboardPage dashboardPage = loginPage.loginApplication(username, password);
		loginPage = dashboardPage.logOut();
		Assert.assertTrue(loginPage.isLoginButtonDisplayed());
		
		LogUtils.endTestCase("logOutTest");
	}
}
