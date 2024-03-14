package com.ohrm.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentUtils {
	private static final String filePath = System.getProperty("user.dir") + "\\target\\extent-reports\\";
	private static ExtentReports extentReports;

	private ExtentUtils() {

	}

	public static ExtentReports getInstance() {
		if (extentReports == null) {
			getExtentReports();
		}
		return extentReports;
	}

	private static void getExtentReports() {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD-hh:mm:ss");
		String fileName = "ExtentReport-" + sdf.format(new Date());
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filePath);
		sparkReporter.config().setReportName(fileName);
		sparkReporter.config().setDocumentTitle(fileName);
		sparkReporter.config().setTheme(Theme.DARK);

		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
	}
}
