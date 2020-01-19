package com.iBank.pageObjects;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.iBank.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readConfig = new ReadConfig();

	public String baseURL = readConfig.getApplication();
	public String userN = readConfig.getUserName();
	public String passw = readConfig.getPassoword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setUp(String setBrowser) {
		logger = Logger.getLogger("testingProjecr");
	    PropertyConfigurator.configure("Log4j.properties");
	    if(setBrowser.equals("chrome")) {
	    	System.setProperty("webdriver.chrome.driver", readConfig.getGoogleChromePath());
			driver = new ChromeDriver();
	    }
	    else if(setBrowser.equals("InternetExplorer")) {
	    	System.setProperty("webdriver.ie.driver", readConfig.getInternetExplorerPath());
			driver = new InternetExplorerDriver();	
	    }
	    driver.get(baseURL);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public void captureScreenShot(WebDriver driver, String name) throws IOException{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+name+".png");
		FileUtils.copyDirectory(source, target);
		System.out.println("Screenshot taken");
		
	}
	
}
