package com.actitime.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TaskListPage {
    
    @FindBy(xpath = "//div[text()='Add New']")
    private WebElement addnewbtn;
    
    @FindBy(xpath = "//div[text()='+ New Customer']")
    private WebElement newcustmerbtn;
    
    @FindBy(id = "customerLightBox_nameField")
    private WebElement customerNametxt;
    
    @FindBy(id = "customerLightBox_descriptionField")
    private WebElement customerDescription;
    
    @FindBy(xpath = "//button[contains(text(),'Select')]")
    private WebElement selectcustomerinfoDD;
    
    @FindBy(xpath = "//a[text()='Our Company']")
    private WebElement ourcompanybtn;
    
    @FindBy(xpath = "//span[text()='Create Customer']")
    private WebElement createcustomerbtn;
    
    public TaskListPage(WebDriver driver) {
    	   this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setAddnewbtn() {
        addnewbtn.click();
    }

    public void setNewcustmerbtn() {
        newcustmerbtn.click();
    }

    public void setCustomerNametxt(String custName) {
        customerNametxt.sendKeys(custName);
    }

    public void setCustomerDescription(String desc) {
        customerDescription.sendKeys(desc);
    }

    public void setSelectCustomerinfoDD() {
        selectcustomerinfoDD.click();
    }

    public void setOurcompanybtn() {
        ourcompanybtn.click();
    }

    public void setCreatecustomerbtn() {
        createcustomerbtn.click();
    }
    private WebDriver driver;

    // the new customer's name as it appears on the page after creation
    public WebElement getActualcname(String name) {
        return driver.findElement(By.xpath("//*[normalize-space(text())='" + name + "']"));
    }
    
    
    //Modify testcase
    @FindBy(xpath = "//input[@placeholder='Start typing name ...']")   // TODO: confirm in F12
    private WebElement searchtxt;

    @FindBy(xpath = "//div[contains(@class,'edit_customer_sliding_panel')]//div[@class='nameLabel']")
    private WebElement customerNameLabel;
    
 // the customer row in the left list (used for hovering)
    public By customerRowLocator(String name) {
        return By.xpath("//div[contains(@class,'customerNode')][.//div[@class='title'][contains(., '" + name + "')]]");
    }
    
 // gear icon of the customer whose title contains the name
    public By customerGearLocator(String name) {
        return By.xpath("//div[contains(@class,'customerNode')][.//div[@class='title'][contains(., '" + name + "')]]//div[contains(@class,'editButton')]");
    }

    @FindBy(xpath = "//div[contains(@class,'edit_customer_sliding_panel')]//div[@class='nameInput']/input")
    private WebElement customerNameEditInput;

    public void setSearchtxt(String name) {
        searchtxt.sendKeys(name);
    }

    public void setCustomerNameLabel() {
        customerNameLabel.click();
    }

    public void setCustomerNameEditInput(String newName) {
        customerNameEditInput.clear();
        customerNameEditInput.sendKeys(newName, Keys.ENTER);   // Enter commits the rename
    }

   
 // customer in the left list: text is split by the highlight span, so match on "."
    public By customerInListLocator(String name) {
        return By.xpath("//div[contains(@class,'customerNode')]//div[@class='title'][contains(., '" + name + "')]");
    }
 

    public By customerNameLabelLocator() {
        return By.xpath("//div[contains(@class,'edit_customer_sliding_panel')]//div[@class='nameLabel']");
    }
}