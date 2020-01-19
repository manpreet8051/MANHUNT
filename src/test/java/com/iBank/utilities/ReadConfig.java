package com.iBank.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties prop;

	public ReadConfig() {
		File src = new File("./Configurations/config.properties");
		try {
			FileInputStream readFile = new FileInputStream(src);
			prop = new Properties();
			prop.load(readFile);
			
		} catch (Exception e) {
			System.out.println("Exception is "+e.getMessage());
		}
	}
	
	public String getApplication() {
		String url = prop.getProperty("baseURL");
		return url;
	}
	
	public String getUserName() {
		String username = prop.getProperty("username");
		return username;
	}
	
	public String getPassoword() {
		String password = prop.getProperty("password");
		return password;
	}
	
	public String getGoogleChromePath() {
		String googleChromePath = prop.getProperty("googleChrome");
		return googleChromePath;
	}
	
	public String getInternetExplorerPath() {
		String internetExplorer = prop.getProperty("internetExplorer");
		return internetExplorer;
	}
}
