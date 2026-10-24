package com.actitime.testscript;


import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.actitime.generic.BaseClass;
import com.actitime.generic.FileLib;
import com.actitime.pom.TaskListPage;

@Listeners(com.actitime.generic.ListenerImplementation.class)

public class CustomerModule extends BaseClass {

    @Test
    public void testCreateCustomer() throws InterruptedException, EncryptedDocumentException, IOException {
        Reporter.log("Create Customer", true);
        FileLib f = new FileLib();
        String cName=f.getExcelData("Customer", 1, 3);
        String cDescription=f.getExcelData("Customer", 1, 4);
TaskListPage t= new TaskListPage(driver);
        String expectcname = cName;
        t.setAddnewbtn();
        t.setNewcustmerbtn();
        t.setCustomerNametxt(cName);
        t.setCustomerDescription(cDescription);
        t.setSelectCustomerinfoDD();
        t.setOurcompanybtn();
        t.setCreatecustomerbtn();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(t.getActualcname(expectcname), expectcname));
        String actualnametext = t.getActualcname(expectcname).getText();
        Assert.assertEquals(actualnametext, expectcname);
        Reporter.log(actualnametext + " is created successfully", true);
        
    }

    @Test(enabled = false,dependsOnMethods = "testCreateCustomer")
public void testModifyCustomer() throws EncryptedDocumentException, IOException {
    Reporter.log("Modify Customer", true);
    FileLib f = new FileLib();
    String oldName = f.getExcelData("Customer", 1, 3);   // HDFC_001
    String newName = f.getExcelData("Customer", 2, 3);   // ICIC_002 (confirm row)

    TaskListPage t = new TaskListPage(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    t.setSearchtxt(oldName);
    wait.until(ExpectedConditions.visibilityOfElementLocated(t.customerRowLocator(oldName)));

    // hover the row so the gear icon appears, then click the gear
    Actions actions = new Actions(driver);
    actions.moveToElement(driver.findElement(t.customerRowLocator(oldName))).perform();
    wait.until(ExpectedConditions.elementToBeClickable(t.customerGearLocator(oldName))).click();

    wait.until(ExpectedConditions.visibilityOfElementLocated(t.customerNameLabelLocator()));
    t.setCustomerNameLabel();
    t.setCustomerNameEditInput(newName);

    // step 12: verify the label now shows the new name
    wait.until(ExpectedConditions.textToBe(t.customerNameLabelLocator(), newName));
    String actual = driver.findElement(t.customerNameLabelLocator()).getText();
    Assert.assertEquals(actual, newName);
    Reporter.log(oldName + " renamed to " + actual, true);
}
}