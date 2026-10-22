package com.actitime.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.actitime.generic.BaseClass;

public class LoginPage extends BaseClass {
		@FindBy(id="username")
		private WebElement Untbx;
		
		@FindBy(name="pwd")
		private WebElement Pwtbx;
		
		@FindBy(xpath="//div[.='Login ']")
		private WebElement Lgtbn;
		
		public LoginPage(WebDriver driver) {
	        PageFactory.initElements(driver, this);  
	    }

	    public void setLogin(String un, String pw) {
	    	Untbx.sendKeys(un);
	    	Pwtbx.sendKeys(pw);
	    	Lgtbn.click();
	    }
}