package com.iBank.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver localdriver;
	
	public LoginPage(WebDriver rdriver){
		localdriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(name="uid")
	@CacheLookup
	WebElement userName;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement password;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	public void setUserName(String username) {
		userName.sendKeys(username);
	}
	
	public void setPasswordName(String pass) {
		password.sendKeys(pass);
	}
	
	public void clickSubmit() {
		btnLogin.click();
	}

}
