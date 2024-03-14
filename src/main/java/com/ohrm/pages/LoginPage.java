package com.ohrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ohrm.base.BasePage;
import com.ohrm.utils.PropertiesUtils;

public class LoginPage extends BasePage {

	private WebDriver driver;

	@FindBy(name = "username")
	private WebElement usernameEl;
	@FindBy(name = "password")
	private WebElement passwordEl;
	@FindBy(xpath = "//button[normalize-space() = 'Login']")
	private WebElement loginEl;
	@FindBy(xpath = "//p[contains(@class,'oxd-alert-content-text') and text()='Invalid credentials']")
	private WebElement invalidCredentialsDialogEl;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterUsername(String username) {
		enterText(usernameEl, username, "Username");
	}

	public void enterPassword(String password) {
		enterText(passwordEl, password, "Password");
	}

	public void clickLoginButton() {
		click(loginEl, "Login");
	}

	public boolean isInvalidCredentialsDialogDisplayed() {
		return isDisplayed(invalidCredentialsDialogEl, "Invalid Credentials Dialog");
	}

	public DashboardPage loginApplication(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
		return new DashboardPage(driver);
	}

	public boolean isUsernameRequiredDisplayed() {
		return isAttrbutePresent(usernameEl, "Username", "class", "oxd-input--error");
	}

	public boolean isPasswordRequiredDisplayed() {
		return isAttrbutePresent(passwordEl, "Password", "class", "oxd-input--error");
	}

	public LoginPage refreshPage() {
		refreshPage(LoginPage.class.getSimpleName());
		waitForVisibility(loginEl, 10, "Login");
		return new LoginPage(driver);
	}

	public boolean isLoginButtonDisplayed() {
		return isDisplayed(loginEl, "Login");
	}

}
