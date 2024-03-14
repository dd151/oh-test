package com.ohrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ohrm.base.BasePage;

public class DashboardPage extends BasePage {

	private WebDriver driver;

	@FindBy(xpath = "//span[text() = 'Dashboard']/..")
	private WebElement dashBoardLinkEl;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isDashboardDisplayed() {
		return isDisplayed(dashBoardLinkEl, "DashBoard");
	}

}
