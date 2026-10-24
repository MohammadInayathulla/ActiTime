package com.actitime.generic;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.actitime.pom.EnterTimeTrackPage;
import com.actitime.pom.LoginPage;

public class BaseClass {
    public static WebDriver driver;

    @BeforeTest
    public void openBrowser() {
        Reporter.log("openBrowser", true);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @BeforeMethod
    public void login() throws IOException, InterruptedException {
        Reporter.log("login", true);
        FileLib f = new FileLib();
        String url = f.getPropertyData("url");
        String un = f.getPropertyData("username");
        String pw = f.getPropertyData("password");
        driver.get(url);
        LoginPage l = new LoginPage(driver);
        l.setLogin(un, pw);
        
        EnterTimeTrackPage e = new EnterTimeTrackPage(driver);
        Thread.sleep(2000);
        e.setTaskbar();        
    }
    
    @AfterMethod
    public void logout() throws InterruptedException {
        Reporter.log("logout", true);
        EnterTimeTrackPage e = new EnterTimeTrackPage(driver);
        Thread.sleep(2000);
        e.setLogOut();
    }
    @AfterTest
    public void closeBrowser() {
        Reporter.log("closeBrowser", true);
        if (driver != null) {
            driver.quit();
        }
    }
}