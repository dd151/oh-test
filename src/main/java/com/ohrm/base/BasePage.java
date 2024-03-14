package com.ohrm.base;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ohrm.pages.LoginPage;
import com.ohrm.utils.PropertiesUtils;
import com.ohrm.utils.ReportTestManager;

public class BasePage {

	private WebDriver driver;

	@FindBy(xpath = "//li[contains(@class,'oxd-userdropdown')]")
	private WebElement profileDropdownEl;

	@FindBy(xpath = "//a[contains(text(),'Logout')]/..")
	private WebElement logOutEl;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	protected void click(WebElement el, String name) {
		WebElement visibleEl = waitForVisibility(el, 20, name);
		if (visibleEl != null) {
			String message = "Clicking on Element: " + name;
			ReportTestManager.logMessage(message);
			el.click();
		}else {
			String message = "Unable to Locate Element: " + name;
			ReportTestManager.logMessage(message);
			return;
		}
	}

	protected void enterText(WebElement el, String text, String name) {
		WebElement visibleEl = waitForVisibility(el, 20, name);
		if (visibleEl != null) {
			String message1 = "Clearing TextBox Element: " + name;
			String message2 = "Entering text: \"" + text + "\" in TextBox Element: " + name;
			ReportTestManager.logMessage(message1);
			el.clear();
			ReportTestManager.logMessage(message2);
			el.sendKeys(text);
		}else {
			String message = "Unable to Locate Element: " + name;
			ReportTestManager.logMessage(message);
			return;
		}
	}

	protected boolean isDisplayed(WebElement el, String name) {
		try {
			String message = "Waiting for Visibility of Element: " + name;
			ReportTestManager.logMessage(message);
			WebElement visibleEL = waitForVisibility(el, 10, name);
			return visibleEL.isDisplayed();
		} catch (TimeoutException e) {
			String message = "Unable to Locate Element: " + name;
			ReportTestManager.logMessage(message);
			return false;
		}
	}

	protected WebElement waitForVisibility(WebElement el, long seconds, String name) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.visibilityOf(el));
	}

	protected boolean isAttrbutePresent(WebElement el, String name, String attributeName, String attributeValue) {
		WebElement visibleEl = waitForVisibility(el, 20, name);
		if (visibleEl != null) {
			String message = "Verifying Element: " + name + " has attribute-name: \"" + attributeName
					+ "\" with attribute-value: \"" + attributeValue + "\"";
			ReportTestManager.logMessage(message);
			return el.getAttribute(attributeName).contains(attributeValue);
		}else {
			String message = "Unable to Locate Element: " + name;
			ReportTestManager.logMessage(message);
			return false;
		}
	}

	public LoginPage logOut() {
		click(profileDropdownEl, "Profile Dropdown");
		click(logOutEl, "Logout");
		return new LoginPage(driver);
	}

	public void refreshPage(String page) {
		String message = "Refreshing application page: " + page;
		ReportTestManager.logMessage(message);
		driver.get(PropertiesUtils.getInstance().getApplicationUrl());
	}
}
