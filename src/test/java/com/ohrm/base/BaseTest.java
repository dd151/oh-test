package com.ohrm.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.ohrm.pages.LoginPage;
import com.ohrm.utils.LogUtils;
import com.ohrm.utils.PropertiesUtils;

public class BaseTest {

	public WebDriver driver = null;
	private String browser = null;
	private String url = null;
	protected LoginPage loginPage = null;

	public void initDriver() {
		browser = PropertiesUtils.getInstance().getBrowser();
		url = PropertiesUtils.getInstance().getApplicationUrl();

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
	}

	@BeforeTest(alwaysRun = true)
	public void setUp() {
		initDriver();
		LogUtils.info("Navigating to application url");
		driver.get(url);
		this.loginPage = new LoginPage(driver);
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		LogUtils.info("Closing application");
		driver.quit();
	}

	protected String captureScreenShot(String testName, WebDriver driver) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\screenshots\\" + testName + ".png";
		try {
			FileUtils.copyFile(srcFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
