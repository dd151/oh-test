package com.ohrm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtils {

	private static PropertiesUtils propUtils = null;
	private Properties props = null;

	private PropertiesUtils() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(System.getProperty("user.dir") + "\\configs\\config.properties"));
			props = new Properties();
			props.load(fis);
		} catch (Exception e) {
			System.out.println("Error loading properties file");
			e.printStackTrace();
		}
	}

	public static PropertiesUtils getInstance() {
		if (propUtils == null)
			propUtils = new PropertiesUtils();
		return propUtils;
	}

	public String getBrowser() {
		return this.props.getProperty("browser");
	}

	public String getApplicationUrl() {
		return this.props.getProperty("url");
	}

	public String getUsername() {
		return this.props.getProperty("username");
	}

	public String getPassword() {
		return this.props.getProperty("password");
	}

}
