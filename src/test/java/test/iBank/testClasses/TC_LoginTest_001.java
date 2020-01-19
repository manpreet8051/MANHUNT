package test.iBank.testClasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.iBank.pageObjects.BaseClass;
import com.iBank.pageObjects.LoginPage;


public class TC_LoginTest_001 extends BaseClass {
	
	
	@Test
	public void loginTest() throws IOException {
		LoginPage loginPage = new LoginPage(driver);
		logger.info("URL is entered");
		loginPage.setUserName(userN);
		logger.info("username is entered");
		loginPage.setPasswordName(passw);
		logger.info("password is entered");
		loginPage.clickSubmit();
		logger.info("button is clicked");
		String title = driver.getTitle();
		System.out.println(title);
		if(driver.getTitle().equals("GTPL Bank Manager HomePage1")) {
			Assert.assertTrue(true);
			logger.info("Test case passed");
		}
		else {
			
			logger.info("Test case failed");
			captureScreenShot(driver,"loginTest");
			Assert.assertTrue(false);
			
		}
	}
}
