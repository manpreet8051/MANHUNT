package com.iBank.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	
	public ExtentHtmlReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //timeStamp
		String repName ="Test-Report"+timeStamp+".html";
		//specify report location
		htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		htmlreporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		extent = new ExtentReports();
		
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Host name","local host");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "manpreet");
		
		htmlreporter.config().setDocumentTitle("IBank Testing Project");
		htmlreporter.config().setReportName("Functional Test Report");
		//htmlreporter.config().setTestViewChartLocation(chartlocation.);
		htmlreporter.config().setTheme(Theme.STANDARD);	
	}
	
	public void onTestSuccessful(ITestContext testContext) {
		logger = extent.createTest(testContext.getName()); //create new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(testContext.getName(),ExtentColor.GREEN));
	}
	
	public void onTestFailer(ITestContext testContext) {
		logger = extent.createTest(testContext.getName()); //create new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(testContext.getName(),ExtentColor.RED));
		String screenShotPath = System.getProperty("user.dir")+"/Screenshots/"+testContext.getName()+".png";
		File file = new File(screenShotPath);
		if(file.exists()) {
			try {
				logger.fail("Screenshot is below: "+logger.addScreenCaptureFromPath(screenShotPath));
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void onTestSkip(ITestContext testContext) {
		logger = extent.createTest(testContext.getName()) ; //create new entry in report	
		logger.log(Status.SKIP, MarkupHelper.createLabel(testContext.getName(), ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();	
	}
}
