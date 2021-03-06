package TestComponent.DryBar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;
import Utilities.xmlReader;

public class DryBarHelper {
	

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();
public DryBarHelper(String datafile){
	
	excelData=new ExcelReader(datafile);
	data=excelData.getExcelValue();
	this.data=data;
	if(Utilities.TestListener.report==null)
	{
		report=new ExtenantReportUtils("Hydro");
		report.createTestcase("HydroTestCases");
	}else{
		this.report=Utilities.TestListener.report;
	}

	
}



public void ui(){
	String name =automation_properties.getInstance().getProperty("DeviceName");
	System.out.println(name);
}


public void verifyingHomePage(){
	try{
		
		Sync.waitElementPresent("xpath", "//a[@class='logo']");
	String HomepageTitle=Common.findElement("xpath", "//a[@class='logo']").getAttribute("title");
	Common.assertionCheckwithReport(HomepageTitle.equals("Drybar"), "verifying the homepage", "navigate the home page", "user successfully navigate the home page", "Failed to navigate to home page");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying the homepage","navigate the home page", "user successfully navigate the home page", Common.getscreenShotPathforReport("failedtohomepage"));
			Assert.fail();
		}
}


public void clickMyaccount() throws Exception{
	
	verifyingHomePage();
	try{
	Sync.waitElementPresent("xpath", "//li[@class='account-links-wrap'][1]");
	Common.clickElement("xpath", "//li[@class='account-links-wrap'][1]");
	ExtenantReportUtils.addPassLog("verifying my account button", "It should lands on Customer login page", "user successfully  lands on Customer login page", Common.getscreenShotPathforReport("myaccountpass"));
	}
	 catch(Exception |Error e) {
     
		ExtenantReportUtils.addFailedLog("verifying my account button", "It should lands on Customer login page", "user faield lands on Customer login page", Common.getscreenShotPathforReport("myaccountfaield"));
		Assert.fail();
	}
	
}

public void CreateAccount(String dataSet){
	
	try{
		Thread.sleep(5000);
		Common.clickElement("xpath", "(//a[@class='account-link top-link'])[1]");
		Thread.sleep(3000);
		ExtenantReportUtils.addPassLog("verifying login page", "It should lands on login page", "user  lands on Customer login form Page", Common.getscreenShotPathforReport("Login page"));
	Common.clickElement("xpath", "(//span[text()='Create an Account'])[1]");
	
	ExtenantReportUtils.addPassLog("verifying Create Account button", "It should lands on Create New Customer from Account form Page", "user  lands on Customer Account creation form Page", Common.getscreenShotPathforReport("createaccount"));
	}
	catch(Exception |Error e) {
		e.printStackTrace();
	        ExtenantReportUtils.addFailedLog("verifying Create Account button", "It should lands on Create New Customer from Account form Page", "user faield lands on Account form Page", Common.getscreenShotPathforReport("createaccount"));
			Assert.fail();
		}
	
	try{
	Thread.sleep(5000);
	Common.textBoxInput("id", "firstname",data.get(dataSet).get("FirstName"));
    Common.textBoxInput("id", "lastname",data.get(dataSet).get("LastName"));
	Common.clickElement("xpath", "//input[@id='is_subscribed']");
	Common.textBoxInput("id", "email_address", Utils.getEmailid());
	Common.textBoxInput("id", "password",data.get(dataSet).get("Password"));
	Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
	Common.mouseOverClick("xpath", "//button[@title='Create an Account']");
	Sync.waitPageLoad();
	Thread.sleep(4000);
	String title=Common.getPageTitle();
	String Head = Common.findElement("xpath", "(//h1[@class='page-title'])").getText();
	System.out.println(title);
	Common.assertionCheckwithReport(title.equals("My Account | Drybar"), "verifying Create My Account functionality","User navigate to My Account page","user successfully created new account and landed on My Account page", "user faield to create new account");
	//ExtenantReportUtils.addPassLog("verifying Create My account Page", "It should lands on MyAccount  Page", "user lands on My Account  Page", Common.getscreenShotPathforReport("My Account page"));
	
	}
	 catch(Exception |Error e) {
	        ExtenantReportUtils.addFailedLog("verifying  MyAccount page", "Account should be created successfully navigate to My Account page", "user faield to create account", Common.getscreenShotPathforReport("createaccountfaield"));
			Assert.fail();
		}
	
}


public void clickHairProducts() throws Exception {
	try {

		Sync.waitElementPresent("xpath", "//span[text()='Hair Products']");
		Common.clickElement("xpath", "//span[text()='Hair Products']");
		Sync.waitPageLoad();
		Close_popup();
		Common.assertionCheckwithReport(Common.getPageTitle().equals("Hair Products | Drybar"),
				"verifying Hair Product category", "User navigate to hair product page",
				"user successfully open the Hair Category page", "user faield to click the Hair Product");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Hair Product category", "User navigate to hair product page",
				"user faield to click the Hair Product", Common.getscreenShotPathforReport("hairproduct"));
		Assert.fail();
	}
	
}

public void click_Gifts_and_Kits(){
	try{
		
		Sync.waitElementPresent("xpath", "(//span[text()='Gifts & Kits'])[1]");
	Common.clickElement("xpath", "(//span[text()='Gifts & Kits'])[1]");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Gifts & Kits | Drybar"), "verifying Gifts & Kits category","User navigate to Gifts & Kits page","user successfully open the Gifts & Kits Category page", "user faield to click the Gifts & Kits");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Gifts & Kits category", "User navigate to Gifts & Kits page", "user faield to click the Gifts & Kits", Common.getscreenShotPathforReport("Gifts & Kits"));
			Assert.fail();
		}
	
}






public void Select_Bundle_product(){
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "(//a[contains(text(),'The Smooth & Shine Special Value Set')])");
	Common.clickElement("xpath", "(//a[contains(text(),'The Smooth & Shine Special Value Set')])");
	Thread.sleep(5000);
	Common.assertionCheckwithReport(Common.getPageTitle().contains("The Smooth & Shine Special Value Set"), "verifying The smoothing and shine special valueset PDP","Should land on The smoothing&shine special valueset PDP","user successfully landed on The smoothing&shine special valueset PDP", "user faield to click on The smoothing special valueset product");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Gifts&kits category PDP", "User should select The smoothingand shine special valueset product", "user faield to select The smoothing special valueset product", Common.getscreenShotPathforReport("FAILED to select  The smoothing&shine special valueset"));
			Assert.fail();
	}
}

public void Select_No_Aerosols_Bundle_product(){
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "(//a[contains(text(),'QA Test Bundle 1 - No Aerosols')])");
	Common.clickElement("xpath", "(//a[contains(text(),'QA Test Bundle 1 - No Aerosols')])");
	Thread.sleep(5000);
	Common.assertionCheckwithReport(Common.getPageTitle().equals("QA TEST SET 1 | Drybar"), "verifying No Aerosols special valueset PDP","Should land on No Aerosols special valueset PDP","user successfully landed on No Aerosols special valueset PDP", "user faield to click on No Aerosols special valueset product");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying No Aerosols special valueset PDP", "User should select No Aerosols special valueset product", "user faield to select No Aerosols special valueset product", Common.getscreenShotPathforReport("FAILED to select No Aerosols special valueset"));
			Assert.fail();
	}
}

public void Select_Aerosol_Bundle_product(){
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "(//a[contains(text(),'QA Test Bundle 2 - Aerosols')])");
	Common.clickElement("xpath", "(//a[contains(text(),'QA Test Bundle 2 - Aerosols')])");
	Thread.sleep(5000);
	Common.assertionCheckwithReport(Common.getPageTitle().equals("QA Test Set Bundle 2 | Drybar"), "verifying Aerosols special valueset PDP","Should land on Aerosols special valueset PDP","user successfully landed on Aerosols special valueset PDP", "user faield to click on Aerosols special valueset product");
}
	catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying Aerosols special valueset PDP", "User should select Aerosols special valueset product", "user faield to select Aerosols special valueset product", Common.getscreenShotPathforReport("FAILED to select Aerosols special valueset"));
			Assert.fail();
	}
}



public void Select_Default_Options_Bundle_product(){
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "(//a[contains(text(),'QA Test Bundle 3 - Default')])");
	Common.clickElement("xpath", "(//a[contains(text(),'QA Test Bundle 3 - Default')])");
	Thread.sleep(5000);
	Common.assertionCheckwithReport(Common.getPageTitle().equals("QA Test Set Bundle 3 | Drybar"), "verifying Default Bundle special valueset PDP","Should land on Default Bundle special valueset PDP","user successfully landed on Default Bundle special valueset PDP", "user faield to click on Default Bundle special valueset product");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Default Bundle special valueset PDP", "User should select Default Bundle special valueset product", "user faield to select Default Bundle special valueset product", Common.getscreenShotPathforReport("FAILED to select Default Bundle special valueset"));
			Assert.fail();
	}
}

public void ShampoosMegamenu()throws Exception{
try {

 Thread.sleep(3000);
Sync.waitPageLoad();
Common.mouseOver("xpath", "(//span[contains(text(),'Hair Products')])[1]");
Sync.waitElementClickable("xpath", "(//span[contains(text(),'Shampoos')])");
Common.clickElement("xpath", "(//span[contains(text(),'Shampoos')])");
Thread.sleep(3000);
Close_popup();
System.out.println(Common.getPageTitle());
Common.assertionCheckwithReport(Common.getPageTitle().contains("Shampoos"), "verifying Header link of Shampoos","user open the Shampoos option", "user successfully open the header link Shampoos","Failed open the header link Shampoos");
}
catch (Exception | Error e) {
e.printStackTrace();

 ExtenantReportUtils.addFailedLog("validating Header Links Shampoos","user open the Shampoos option","User unabel open the header link Shampoos",Common.getscreenShotPathforReport("user failed to open the Shampoos headerlink"));
Assert.fail();

 }}

public void Hair_ProductsMegamenuValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	ShampoosMegamenu();
	Common.mouseOver("xpath", "(//span[contains(text(),'Hair Products')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])");
		Common.clickElement("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])");
		Thread.sleep(3000);
		
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "(//span[contains(text(),'Hair Products')])[1]");	
	}
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Hair_ToolsMegamenuValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "(//span[contains(text(),'Hair Tools')])");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])");
		Common.clickElement("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "(//span[contains(text(),'Hair Tools')])[1]");	
	}
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Benefits_MegamenuValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "(//span[contains(text(),'Benefits')])");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Common.clickElement("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "(//span[contains(text(),'Benefits')])");	
	}
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Gifts_MegamenuValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "(//span[contains(text(),'Gifts')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Common.clickElement("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "(//span[contains(text(),'Gifts')])[1]");	
	}
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Inspo_MegamenuValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "(//span[contains(text(),'How To & Inspo')])");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Common.clickElement("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "(//span[contains(text(),'How To & Inspo')])");	
	}
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void New_MegamenuValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "(//span[contains(text(),'New')])");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//a[contains(text(),'"+hedrs[i]+"')])[1]");
		Common.clickElement("xpath", "(//a[contains(text(),'"+hedrs[i]+"')])[1]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "(//span[contains(text(),'New')])");	
	}
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}
	
	

	
public void SelectShampoos(){
	try{
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "((//div[@class='sidebar sidebar-main'])//div[@class='category-item-title'])[8]");
	Common.clickElement("xpath", "((//div[@class='sidebar sidebar-main'])//div[@class='category-item-title'])[8]");
	Sync.waitPageLoad();
	Thread.sleep(3000);
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Shampoos - Sulfate-Free Shampoo & Hair Products | Drybar"), "verifying Shampoos sub-category","User navigate to Shampoos PLP page","user successfully landed on Shampoos PLP", "user faield to click the Shampoos sub-category");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Shampoos sub-category", "User navigate to Shampoos PLP page", "user faield to click the Shampoos sub-category", Common.getscreenShotPathforReport("hairproduct"));
			Assert.fail();
		}
	
}

public void Select_DryShampoo(){
	try{
		Sync.waitPageLoad();
		Thread.sleep(5000);
		Close_popup();
		Sync.waitElementPresent("xpath", "(//li[@class='item category-item'])[10]");
	Common.clickElement("xpath", "(//li[@class='item category-item'])[10]");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Dry Shampoos - Cruelty & Sulfate Free Dry Conditioner & Shampoo | Drybar"), "verifying DryShampoos sub-category","User navigate to DryShampoos PLP page","user successfully landed on DryShampoos PLP", "user faield to click the DryShampoos sub-category");
	}
		catch(Exception |Error e) {
		     
				ExtenantReportUtils.addFailedLog("verifying DryShampoos sub-category", "User navigate to DryShampoos PLP page", "user faield to click the DryShampoos sub-category", Common.getscreenShotPathforReport("hairproduct"));
				Assert.fail();
			}
		
	}

/*public void Select_GiftCards_Category(){
	try{
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "(//li[@class='item category-item'])[9]");
		Thread.sleep(5000);
	Common.clickElement("xpath", "(//li[@class='item category-item'])[9]");
	String Card=Common.findElement("xpath", "(//a[@class='product-item-link'])").getText();
	Common.assertionCheckwithReport(Card.equals("EGift Card"), "verifying Hair Product sub-category","User navigate to E-Gift Card page","user successfully open the E-Gift Card page", "user faield to click the E-Gift Card sub-category");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying E-Gift Card category", "User navigate to E-Gift Card page", "user faield to click the E-Gift Card", Common.getscreenShotPathforReport("E-Gift Card"));
			Assert.fail();
		}
	
}*/
public void Select_GiftCards_Category(){
    try{
        Thread.sleep(5000);
        Sync.waitElementPresent("xpath", "(//div[contains(text(),'Gift Cards')])[2]");
        Thread.sleep(5000);
    Common.clickElement("xpath", "(//div[contains(text(),'Gift Cards')])[2]");
    Sync.waitPageLoad();
    Thread.sleep(2000);
    String Card=Common.findElement("xpath", "(//a[@class='product-item-link'])").getText();
    Common.assertionCheckwithReport(Card.equals("EGift Card"), "verifying Hair Product sub-category","User navigate to E-Gift Card page","user successfully open the E-Gift Card page", "user faield to click the E-Gift Card sub-category");
}
    catch(Exception |Error e) {
        
            ExtenantReportUtils.addFailedLog("verifying E-Gift Card category", "User navigate to E-Gift Card page", "user faield to click the E-Gift Card", Common.getscreenShotPathforReport("E-Gift Card"));
            Assert.fail();
        }
   
}

public void Enter_GiftCard_Details(String dataSet) {
	
	try {
		
		Common.textBoxInput("xpath", "(//input[(@id='giftcard-amount-input')])",data.get(dataSet).get("GiftCardAmount"));
		Common.textBoxInput("xpath", "(//input[(@id='giftcard_sender_name')])",data.get(dataSet).get("GiftCardSenderName"));
		Common.textBoxInput("xpath", "(//input[(@id='giftcard_sender_email')])",data.get(dataSet).get("GiftCardsenderEmail"));
		Common.textBoxInput("xpath", "(//input[(@id='giftcard_recipient_name')])",data.get(dataSet).get("GiftCardRecipientName"));
		Common.textBoxInput("xpath", "(//input[(@id='giftcard_recipient_email')])",data.get(dataSet).get("GiftCardRecipientEmail"));
		Common.textBoxInput("xpath", "(//textarea[(@id='giftcard-message')])",data.get(dataSet).get("GiftCardMessage"));
		Common.clickElement("xpath", "(//button[@class='ui-datepicker-trigger v-middle'])");
		Sync.waitElementPresent("xpath", "(//select[@class='ui-datepicker-month'])");
		Common.dropdown("xpath", "(//select[@class='ui-datepicker-month'])", Common.SelectBy.TEXT, data.get(dataSet).get("Month"));
		Sync.waitElementPresent("xpath", "(//a[contains(text(),'31')])");
		Common.clickElement("xpath", "(//a[contains(text(),'31')])");
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "(//a[@title='Happy Birthday'])");
		Common.clickElement("xpath", "(//a[@title='Happy Birthday'])");
		ExtenantReportUtils.addPassLog("faield  to fill the EGiftCard infromation", "EGiftcard fields Should  fill with the data", "User Successfully filled the EGiftCard infromation", Common.getscreenShotPathforReport("faield  to fill the EGiftCard infromation"));
	}catch(Exception |Error e) {
    
		ExtenantReportUtils.addFailedLog("validating the EGiftCard infromation", "EGiftcard fields Should  fill with the data", "faield  to fill the EGiftCard infromation", Common.getscreenShotPathforReport("faield  to fill the EGiftCard infromation"));
		Assert.fail();
	}

}

public void Hair_Products_CategoryValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "(//span[contains(text(),'Hair Products')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Common.clickElement("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.clickElement("xpath", "(//span[contains(text(),'Hair Products')])[1]");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Hair_Tools_CategoryValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "(//span[contains(text(),'Hair Tools')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Common.clickElement("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.clickElement("xpath", "(//span[contains(text(),'Hair Tools')])[1]");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Benefits_CategoryValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "(//span[contains(text(),'Benefits')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Common.clickElement("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.clickElement("xpath", "(//span[contains(text(),'Benefits')])[1]");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Gifts_CategoryValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "(//span[contains(text(),'Gifts & Kits')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Common.clickElement("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.clickElement("xpath", "(//span[contains(text(),'Gifts & Kits')])[1]");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Inspo_CategoryValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "(//span[contains(text(),'How To & Inspo')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Common.clickElement("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.clickElement("xpath", "(//span[contains(text(),'How To & Inspo')])[1]");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}


public void Select_sort(String dataSet) {

	try {
		Sync.waitPageLoad();
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.mouseOver("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("MostViewed"));
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[11]");
		Thread.sleep(3000);
		ExtenantReportUtils.addPassLog("verifying Most Viewed sort option", "User should select Most Viewed sort",
				"user faield to Select the Most Viewed sort option",
				Common.getscreenShotPathforReport("Most Viewed"));
		Thread.sleep(5000);
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying  Most Viewed sort option",
				"User should  select Most Viewed sort option",
				"user Successfully Selected the Most Viewed Sort option",
				Common.getscreenShotPathforReport("Most Viewed"));
		Assert.fail();
	}
	
	try {
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.PAGE_UP);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("New"));
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(5000);
		ExtenantReportUtils.addPassLog("verifying New sort option", "User should select New sort",
				"user faield to Select the New sort option", Common.getscreenShotPathforReport("New"));
		Thread.sleep(5000);
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying New sort option", "User should  select New sort option",
				"user Successfully Selected the  New Sort option", Common.getscreenShotPathforReport("New"));
		Assert.fail();
	}
	
	try {
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.PAGE_UP);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("low to high"));
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		
		//Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[11]");
		Thread.sleep(3000);
		ExtenantReportUtils.addPassLog("verifying low to high sort option", "User should select low to high sort",
				"user faield to Select the low to high sort option",
				Common.getscreenShotPathforReport("low to high"));
		Thread.sleep(5000);
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying low to high sort option",
				"User should  select low to high sort option", "user faield to Select the low to high Sort option",
				Common.getscreenShotPathforReport("low to high"));
		Assert.fail();
	}
	
	

	try {
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.PAGE_UP);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("high to low"));
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(5000);
		ExtenantReportUtils.addPassLog("verifying high to low sort option", "User should select high to low sort",
				"user faield to Select the high to low sort option",
				Common.getscreenShotPathforReport("high to low"));
		Thread.sleep(5000);
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying high to low sort option",
				"User should  select high to low sort option", "user faield to Select the high to low Sort option",
				Common.getscreenShotPathforReport("high to low"));
		Assert.fail();
	}

	try {
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.PAGE_UP);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("Top Rated"));
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		ExtenantReportUtils.addPassLog("verifying Top Rated sort option", "User should select Top Rated sort",
				"user faield to Select the Top Rated sort option", Common.getscreenShotPathforReport("Top Rated"));
		Thread.sleep(5000);	
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Top Rated sort option",
				"User should  select Top Rated sort option", "user Successfully selected the Top Rated Sort option",
				Common.getscreenShotPathforReport("Top Rated"));
		Assert.fail();
	}
	
	try {
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.PAGE_UP);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("Product Name"));
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(5000);
		List<WebElement> Products = Common.findElements("xpath", "//ol//div[2]//a");
		List<String> OrginalSort=Products.stream().map(s->s.getText()).collect(Collectors.toList());
		List<String> Sortedlist=OrginalSort.stream().sorted().collect(Collectors.toList());
		
		
		Common.assertionCheckwithReport(Sortedlist.equals(OrginalSort),
				"verifying Product Name sort option", "User should select Product Name sort","user faield to Select the Product Name sort option",Common.getscreenShotPathforReport("Product Name"));
		
		ExtenantReportUtils.addPassLog("verifying Product Name sort option", "User should select Product Name sort",
				"user faield to Select the Product Name sort option",
				Common.getscreenShotPathforReport("Product Name"));
		Thread.sleep(5000);
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Product Name sort option",
				"User should  select Product Name sort option",
				"user faield to Select the Product Name Sort option",
				Common.getscreenShotPathforReport("Product Name"));
		Assert.fail();
	}
	
	try {
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.PAGE_UP);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("Best Sellers"));
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[11]");
		Thread.sleep(3000);
		ExtenantReportUtils.addPassLog("verifying Best Sellers sort option", "User should select Best Sellers sort",
				"user faield to Select the best sellers sort option",
				Common.getscreenShotPathforReport("Best Sellers"));
		Thread.sleep(5000);
	} catch (Exception | Error e) {
		 e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying Best Sellers sort option",
				"User should  select Best sellers sort option",
				"user faield to Select the best sellers Sort option",
				Common.getscreenShotPathforReport("Best Sellers"));
		Assert.fail();
	}

}




public void Select_Configurable_product(){
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "(//a[contains(text(),'Sake Bomb Nourishing Shampoo')])");
	Common.clickElement("xpath", "(//a[contains(text(),'Sake Bomb Nourishing Shampoo')])");
	Sync.waitPageLoad();
	String Title=Common.getPageTitle();
	Common.assertionCheckwithReport(Title.equals("Drybar Sake Bomb Nourishing Shampoo | Drybar"), "verifying Configurable product PDP","Should land on Configurable product  PDP","user successfully landed on Configurable product PDP", "user faield to click on Configurable product");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Configurable product PDP", "User should land on  Configurable product", "user faield to select Configurable product", Common.getscreenShotPathforReport("FAILED to select Configurable product"));
			Assert.fail();
		}
	
}

public void Select_Another_Same_SKU_product() {
	try {
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "(//a[contains(text(),'Sake Bomb Nourishing Shampoo')])");
		Common.clickElement("xpath", "(//a[contains(text(),'Sake Bomb Nourishing Shampoo')])");
		Sync.waitPageLoad();
		Thread.sleep(4000);		
		Common.assertionCheckwithReport(Common.getPageTitle().equals("Drybar Sake Bomb Shampoo | Drybar"),"verifying Sake Bomb Nourishing Shampoo PDP", "Should land on Sake Bomb Nourishing Shampoo PDP",
				"user successfully landed on Sake Bomb Nourishing Shampoo PDP",
				"user faield to click on Sake Bomb Nourishing Shampoo product");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Sake Bomb Nourishing Shampoo PDP",
				"User should select Sake Bomb Nourishing Shampoo product",
				"user faield to select Sake Bomb Nourishing Shampoo product",
				Common.getscreenShotPathforReport("FAILED to select Sake Bomb Nourishing Shampoo product"));
		Assert.fail();
	}

}

public void Selectproduct() {
	try {
		
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "(//a[contains(text(),'One-Two Punch Water-Activated 2-in-1 Hair Wash')])");
		Common.clickElement("xpath", "(//a[contains(text(),'One-Two Punch Water-Activated 2-in-1 Hair Wash')])");
		Sync.waitPageLoad();
		Thread.sleep(4000);		
		Common.assertionCheckwithReport(Common.getPageTitle().contains("One-Two Punch Water-Activated 2-in-1 Hair Wash | Drybar"),"verifying Liquid glass smooothing PDP", "Should land on Liquid glass smoothing shampoo PDP",
				"user successfully landed on Liquid glass smoothing shampoo PDP",
				"user faield to click on Liqui glass smoothing shampoo product");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Hair Product category PDP",
				"User should select liquid glass smoothing shampoo product",
				"user faield to select liquid glass smoothing shampoo product",
				Common.getscreenShotPathforReport("FAILED to select  liquid glass smoothing shampoo product"));
		Assert.fail();
	}

}

public void Select_Aerosol_product() {
	try {
		Sync.waitPageLoad();
		Common.scrollIntoView("xpath", "(//a[contains(text(),'Detox Brunettes Dry Shampoo')])");
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "(//a[contains(text(),'Detox Brunettes Dry Shampoo')])");
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//a[contains(text(),'Detox Brunettes Dry Shampoo')])");
		Common.assertionCheckwithReport(
				Common.getPageTitle().equals("Detox Dry Shampoo for Brunettes | Drybar"),
				"verifying Aerosol product PDP", "Should land on Aerosol product PDP",
				"user successfully landed on Aerosol product PDP",
				"user faield to click on Aerosol product product");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Aerosol product PDP", "User should select Aerosol product",
				"user faield to select Aerosol product",
				Common.getscreenShotPathforReport("FAILED to select Aerosol  product"));
		Assert.fail();
	}

}

public void Select_EGiftCard(){
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "(//a[@class='product-item-link'])");
	Common.clickElement("xpath", "(//a[@class='product-item-link'])");
	String Title=Common.findElement("xpath", "(//h1[(@class='page-title')])").getText();
	Common.assertionCheckwithReport(Title.equals("EGift Card"), "verifying EGiftCard PDP","Should land on EGiftCard PDP","user successfully landed on EGiftCard PDP", "user faield to click on EGiftCard product in PLP");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying EGiftCard PDP", "Should land on EGiftCard PDP", "user faield to click on EGiftCard product in PLP", Common.getscreenShotPathforReport("FAILED to select EGift Card product in PLP"));
			Assert.fail();
		}
	
}

public void Select_Size() throws Exception {
	try {
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "(//div[(text()='Travel')])");
		Common.clickElement("xpath", "(//div[(text()='Travel')])");
		Thread.sleep(5000);
		String FinalPrice=Common.getText("xpath", "(//span[@data-price-type='finalPrice'])[1]");
		ExtenantReportUtils.addPassLog("To Verify the  Swath option","It should select swath options", "successfully selected Swatch options", "Swatch options");
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Swath options","should select Swath options", "user unable to select Swath options", Common.getscreenShotPathforReport("failed to select Swath options"));			
		Assert.fail();	
		}
}

public void Select_Full_Size() throws Exception {
	try {
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "(//div[(text()='Full')])");
		Common.clickElement("xpath", "(//div[(text()='Full')])");
		Thread.sleep(5000);
		ExtenantReportUtils.addPassLog("To Verify the  Swath option","It should select swath options", "successfully selected Swatch options", "Swatch options");
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Swath options","should select Swath options", "user unable to select Swath options", Common.getscreenShotPathforReport("failed to select Swath options"));			
		Assert.fail();	
		}
}
		
	
public void increaseProductQuantity(String Quantity) throws Exception{
	try{
	Sync.waitPageLoad();
	
	int size=Common.findElements("xpath", "//select[@id='qty']").size();
	
	if(size>0){
		Common.dropdown("xpath", "//select[@id='qty']",SelectBy.VALUE, Quantity);
	}
	else{
	for(int i=1;i<Integer.valueOf(Quantity);i++){
		Sync.waitElementPresent("id", "qty");
		Common.clickElement("xpath", "//input[@id='qty']/following::button[1]");
	    }
	
	}

	
	/*
	Common.findElement("id","qty").clear();
	Common.textBoxInput("id", "qty",Quantity);*/
	
	ExtenantReportUtils.addPassLog("verifying product Quntity", "User increaseProductQuantity", "user successfully increaseProductQuantity", Common.getscreenShotPathforReport("productIncr"));
	}
	catch(Exception |Error e) {
	     e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying Hair Product category", "User navigate to hair product page", "user faield to click the Hair Product", Common.getscreenShotPathforReport("productIncr"));
		Assert.fail();
	}
}


public void clickAddtoBag() throws Exception {
	try {
		Sync.waitPageLoad();
		Thread.sleep(5000);
		Common.clickElement("id", "product-addtocart-button");
		Thread.sleep(2000);
		Sync.waitElementPresent("xpath", "(//div[@class='message-success success message'])");
		int message = Common.findElements("xpath", "(//div[@class='message-success success message'])").size();
		Common.assertionCheckwithReport(message > 0, "verifying add to cart button",
				"User click add to card button", "user successfully click add to cart button",
				Common.getscreenShotPathforReport("faieldtoclickutton"));
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying add to cart button", "User click add to card button",
				"user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
		Assert.fail();
	}

}

public void Select_SpecialValueSets() {
	try {
		Thread.sleep(6000);
		Sync.waitElementPresent("xpath", "(//div[contains(text(),'Special Value Sets')])[2]");
		Common.clickElement("xpath", "(//div[contains(text(),'Special Value Sets')])[2]");
	
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Hair Care Product Bundles & Hair Styling Bundles | Drybar"), "verifying special value set sub-category","User navigate to Special value sets PLP page","user successfully landed on Special valuesets PLP", "user faield to click the Special valuesets sub-category");
}
	catch(Exception |Error e) {	
		ExtenantReportUtils.addFailedLog("verifying Special valuesets sub-category", "User navigate to Special valuesets PLP page", "user faield to click the Special valuesets sub-category", Common.getscreenShotPathforReport("Gifts&kits"));
		Assert.fail();
		
	}
	}

public void Speciavalueset(){
	try{
		Sync.waitPageLoad();
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//a[contains(text(),'The Tools Special Value Set #2')]");
		Thread.sleep(3000);
		Common.scrollIntoView("xpath", "//a[contains(text(),'The Tools Special Value Set #2')]");
		Thread.sleep(3000);
	Common.clickElement("xpath", "//a[contains(text(),'The Tools Special Value Set #2')]");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("The Tools Special Value Set #2 | Drybar"), "verifying Special Value Set PDP","Should land on Special Value Set PDP","user successfully landed on Special Value Set PDP", "user faield to click on ASpecial Value Set product");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Special Value Set PDP", "User should select Special Value Set product", "user faield to select Special Value Set product", Common.getscreenShotPathforReport("FAILED to select Special Value Set product"));
			Assert.fail();
		}
	
}


public void How_To_Use() throws Exception{
	try{
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("To view How to step by step button","Should display How to step by step button ", "user unable to view How to step by step button", Common.getscreenShotPathforReport("How to step by step button"));			
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the warranty footer link","Should navigate to warranty footerlink page", "userunable to  navigate to warranty foterlink", Common.getscreenShotPathforReport("failed to "));			
		Assert.fail();	
		}
	
	
	try {
		
		Common.clickElement("xpath", "(//button[@data-action='show-how-to'])");
		Thread.sleep(5000);
		ExtenantReportUtils.addPassLog("To verify the How to use step by step Functionality","Should diaply How to use pop-up page", "user unable to  navigate to  How to Use pop-up page", Common.getscreenShotPathforReport("failed to land on How to Use pop-up page"));			
		
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the How to use step by step Functionality ","should land on How to Use pop-up page", "user unable to navigate to How to Use pop-up page", Common.getscreenShotPathforReport("failed to land on How to Use pop-up page"));			
			Assert.fail();	
			}
		}
		


public void clickminiCartButton() throws Exception{
	try{
		Thread.sleep(5000);
	Sync.waitElementPresent("xpath", "//a[@class='action showcart desktop_only']");
    Common.clickElement("xpath", "//a[@class='action showcart desktop_only']");
    
    Sync.waitElementPresent("id", "top-cart-btn-checkout");
   int checkoutbuttonSize= Common.findElements("id", "top-cart-btn-checkout").size();
   
  Common.assertionCheckwithReport(checkoutbuttonSize>0, "verifying mini cart button", "User click mini cart button", "user successfully click mini cart button", "Failed click mini cart button");
}
    catch(Exception |Error e) {
 	   
		ExtenantReportUtils.addFailedLog("verifying mini cart button", "User click mini cart button", "user faield to click mini cart button", Common.getscreenShotPathforReport("faieldtominicartbutton"));
		Assert.fail();
	}
}


public void selectproduct(String dataSet)
{
	
try{
    String productname=data.get(dataSet).get("ProductName");
	Common.clickElement("xpath", "//a[contains(text(),'"+productname+"')]");
	String titletext=Common.findElement("xpath", "//h1[@class='page-title']/span").getText();
    Common.assertionCheckwithReport(titletext.equals(productname), "verifying product detail page", "user navigate to product detailepage", "user successfully navigate to product detail page", "User Failed navigate to product detail page");
	
}

catch(Exception |Error e) {
    e.printStackTrace();
	ExtenantReportUtils.addFailedLog("verifying product detail page", "user navigate to product detailepage", "user Failed navigate to product detail page", Common.getscreenShotPathforReport("productpage"));
	Assert.fail();
}
}

public void clickCheckoutButton(){
	try{
		 Sync.waitElementPresent("xpath", "(//button[@class='action primary checkout'])");
	 Thread.sleep(5000);
	   Common.findElement("xpath", "(//button[@class='action primary checkout'])").click();
	   
	  //Common.assertionCheckwithReport(checkoutbuttonSize>0, "verifying mini cart button", "User click mini cart button", "user successfully click mini cart button", "Failed click mini cart button");
	}
	    catch(Exception |Error e) {
	 	   
			ExtenantReportUtils.addFailedLog("verifying checkOut button", "User click checkOut  button", "user faield to click checkOut button", Common.getscreenShotPathforReport("checkOut"));
			Assert.fail();
		}
}

public void addDeliveryAddress_for_registerUser(String dataSet) throws Exception {

	String expectedResult = "shipping address is entering in the fields";
    int size = Common.findElements(By.xpath("//span[contains(text(),'New Address')]")).size();
	if (size > 0) {

		try {
			Common.clickElement("xpath", "//span[contains(text(),'New Address')]");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
					data.get(dataSet).get("Street"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.SPACE);
			Thread.sleep(5000);
			
			if (data.get(dataSet).get("StreetLine2") != null) {
				Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
			}
		if (data.get(dataSet).get("StreetLine3") != null) {
				Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
			}
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
					data.get(dataSet).get("City"));
			// Common.mouseOverClick("name", "region_id");
			try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']",
					data.get(dataSet).get("postcode"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
					data.get(dataSet).get("phone"));

			ExtenantReportUtils.addPassLog("validating the shipping address", expectedResult,
					"user add the shipping address",
					Common.getscreenShotPathforReport("faield to add shipping address"));


			Common.clickElement("xpath", "//button[contains(@class,'save-address')]");

			int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

			Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
					"user will fill the all the shipping", "user fill the shiping address click save button",
					"faield to add new shipping address");
			
			
			
			/*Common.actionsKeyPress(Keys.END);
			Sync.waitPageLoad();
			select_USPS_StandardGround_shippingMethod();
            Thread.sleep(7000);
			Common.mouseOverClick("xpath", "//div[@id='shipping-method-buttons-container']/div/button");*/
			
			
			
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
					"User unabel add shipping address",
					Common.getscreenShotPathforReport("shipping address faield"));
						Assert.fail();

		}
	}

	else

	{
		try {
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
					data.get(dataSet).get("Street"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.SPACE);
			Thread.sleep(3000);
			try {
				Common.clickElement("xpath",
						"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			} catch (Exception e) {
				Common.actionsKeyPress(Keys.BACK_SPACE);
				Thread.sleep(1000);
				Common.actionsKeyPress(Keys.SPACE);
				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div");
				//Common.clickElement("xpath","//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			}
			if (data.get(dataSet).get("StreetLine2") != null) {
				Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
			}
			if (data.get(dataSet).get("StreetLine3") != null) {
				Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
			}
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
					data.get(dataSet).get("City"));
			// Common.mouseOverClick("name", "region_id");
			try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("name", "postcode");
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

			//Sync.waitElementClickable("xpath", "//span[contains(text(),'Continue To Payment')]");
			Common.clickElement("xpath", "//span[contains(text(),'Continue To Payment')]");

		
			ExtenantReportUtils.addPassLog("verifying shipping addres filling ", expectedResult,
					"user enter the shipping address ",
					Common.getscreenShotPathforReport("fill the shipping address first time"));

			//Common.findElements("xpath", "").size();
			expectedResult = "shipping address is filled in to the fields";
			// report.addPassLog(expectedResult,"Filled the shipping
			// address",Common.getscreenShotPathforReport("fill the shipping
			// address"));
			
			//Common.mouseOverClick("xpath", "//input[@id='label_method_bestway']");
			//Sync.waitElementClickable("xpath","//input[@id='label_method_bestway']");
			//Common.clickElement("xpath", "//input[@id='label_method_bestway']");
			select_USPS_StandardGround_shippingMethod();
            Thread.sleep(5000);
			Common.clickElement("xpath", "//button[@data-role='opc-continue']");
			
			Thread.sleep(3000);

		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
					"User unabel add shipping address",
					Common.getscreenShotPathforReport("shipping address faield"));
			// ExtenantReportUtils.addFailedLog("User click check out
			// button", "User unabel click the checkout button",
			// Common.getscreenShotPathforReport("check out miniCart"));
			Assert.fail();

		}
	}

	// report.addPassLog("clicked on the proceed to payment
	// section",Common.getscreenShotPathforReport("land on the payment
	// section"));

}

public void click_GuestCheckOut() throws Exception{
	try{
	 Sync.waitElementPresent("xpath", "//span[text()='Checkout As Guest']");
	   Common.clickElement("xpath", "//span[text()='Checkout As Guest']");
	}
	   catch(Exception |Error e) {
	 	   
			ExtenantReportUtils.addFailedLog("verifying mini checkout as gust button", "User click checkout as gust button", "user faield to click checkout as gust button", Common.getscreenShotPathforReport("faieldcheckoutasgust"));
			Assert.fail();
		}
	
}

public void guestShippingAddress(String dataSet) throws Exception{
	try{
	Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
	Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",data.get(dataSet).get("Email"));

	
	Sync.waitElementPresent("xpath", "//input[@name='firstname']");
	Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
	
	Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
	
	/*Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));*/
	
	Sync.waitElementPresent("xpath", "//input[@name='company']");
	Common.textBoxInput("xpath", "//input[@name='company']", data.get(dataSet).get("CompanyName"));
	
	

	Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
	Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
	
/*	
	Sync.waitElementPresent("xpath", "//select[@name='country_id']");
	Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));*/
	
	
	Sync.waitElementPresent("xpath", "//select[@name='region_id']");
	Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));
	
	Sync.waitElementPresent("xpath", "//input[@name='city']");
	Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
	
	Sync.waitElementPresent("xpath", "//input[@name='postcode']");
	Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
	
	Sync.waitElementPresent("xpath", "//input[@name='telephone']");
	Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
	Thread.sleep(2000);
	Common.actionsKeyPress(Keys.ENTER);
	Thread.sleep(7000);
	select_USPS_StandardGround_shippingMethod();
    Thread.sleep(10000);
    Common.scrollIntoView("xpath", "(//span[contains(text(),'Next')])[2]");
    //Common.actionsKeyPress(Keys.END);
    //click_Next();
    Sync.waitElementPresent("xpath", "(//span[contains(text(),'Next')])[2]");
	Common.mouseOverClick("xpath","(//span[contains(text(),'Next')])[2]");
	//div[contains(@class,'error')]
	Sync.waitPageLoad();
	int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
    Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying shipping addres filling ", "user will fill the all the shipping", "user fill the shiping address click save button", "faield to add new shipping address");
   
}
	catch(Exception |Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping", "faield to add new shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
		Assert.fail();
		
	}  
}

public void Click_CreditCard_BrainTree(){
	try{
		Sync.waitPageLoad();
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementPresent("xpath", "//label[@for='braintree']");
         Common.clickElement("xpath", "//label[@for='braintree']");
          ExtenantReportUtils.addPassLog("verifying CreditCardbutton", "user click CreditCard ", "user clicked CreditCard option",Common.getscreenShotPathforReport("creditoption"));
	}
	catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying CreditCardbutton", "user click CreditCard ", "faield to click CreditCard option",Common.getscreenShotPathforReport("creditfaeild"));
		Assert.fail();
		
	} 
}

public void Edit_BillingAddress_BrainTree(String dataSet)throws Exception{
	
	try{
	
	
		Common.clickElement("xpath","//div[@class='billing-address-details']//button");
		

   //Common.clickElement("Xpath","//span[contains(text(),'My billing and shipping address are the same')]");
	Sync.waitElementPresent("xpath", "//div[@class='payment-method-billing-address']//input[@name='firstname']");
	Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
	Sync.waitElementPresent("xpath", "//div[@class='payment-method-billing-address']//input[@name='lastname']");
	Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='lastname']",data.get(dataSet).get("LastName"));
	Sync.waitElementPresent("xpath", "//div[@class='payment-method-billing-address']//input[@name='firstname']");
	Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
	Sync.waitElementPresent("xp	ath", "//div[@class='payment-method-billing-address']//input[@name='company']");
	Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='company']",data.get(dataSet).get("CompanyName"));
	Sync.waitElementPresent("xpath", "//div[@class='payment-method-billing-address']//input[@name='street[0]']");
	Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='street[0]']", data.get(dataSet).get("Street"));
	Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='city']", data.get(dataSet).get("City"));
	Common.dropdown("xpath", "//div[@class='payment-method-billing-address']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
	Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='postcode']", data.get(dataSet).get("postcode"));
	Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='telephone']", data.get(dataSet).get("phone"));
	Common.actionsKeyPress(Keys.PAGE_DOWN);
	
	Common.clickElement("xpath", "//button[contains(@class,'action-update')]");
	
	Thread.sleep(5000);
	int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
    System.out.println("error messagess    "+sizeerrormessage);
	Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying Billing addres filling ", "user will fill the all the billing address", "user fill the shipping address click save button", "faield to add new shipping address");

	}
	catch(Exception |Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying Billing addres filling", "user will fill the all the Billing address", "faield to add new billing address",Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
		Assert.fail();
		
	}  
}
public void Click_PaymetricPaymentMethod(){
	try{
		Sync.waitPageLoad();
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
        // Common.clickElement("xpath", "//label[@for='paymetric']");
         Common.clickElement("xpath", "(//span[text()='Credit Card'])");
          ExtenantReportUtils.addPassLog("verifying PaymetricPaymentMethod", "user click PaymetricPaymentMethod ", "user clicked PaymetricPaymentMethod option",Common.getscreenShotPathforReport("PaymetricPaymentMethodoption"));
	}
	catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying PaymetricPaymentMethod", "user click PaymetricPaymentMethod ", "faield to click PaymetricPaymentMethod",Common.getscreenShotPathforReport("PaymetricPaymentMethodoption"));
		Assert.fail();
		
	} 
}
public HashMap<String, String> Edit_BillingAddress_PaymetricPaymentMethod(String dataSet)throws Exception{
	HashMap<String, String> BillingAddress = new HashMap<String, String>();
	Thread.sleep(3000);// //button[contains(@class,'action-edit-address')]
	
		int sizes=Common.findElements("xpath", "//div[@class='billing-address-details']//button[contains(@class,'action-edit-address')]").size();
		if(sizes>0){
			Thread.sleep(5000);
         Common.clickElement("xpath", "//div[@class='billing-address-details']//button[contains(@class,'action-edit-address')]");
try{
	    int selectbutton=Common.findElements("xpath", "//select[@name='billing_address_id']").size();
	
	    if(selectbutton>0){
	    	Thread.sleep(5000);
	    	Common.dropdown("xpath", "//select[@name='billing_address_id']", SelectBy.TEXT, "New Address");
	    }
	    Thread.sleep(5000);
         Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='firstname']");
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='firstname']",data.get(dataSet).get("FirstName"));
     	Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='lastname']");
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='lastname']",data.get(dataSet).get("LastName"));
     	/*Sync.waitElementPresent("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']");
     	Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']",data.get(dataSet).get("CompanyName"));*/
     	Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='company']");
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='company']",data.get(dataSet).get("CompanyName"));
     	Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='street[0]']");
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='street[0]']", data.get(dataSet).get("Street"));
     	Common.dropdown("xpath", "//fieldset[contains(@class,'fieldset')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
     	Thread.sleep(5000);
    	String Billingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
    	String BillingState = Common.findElement("xpath", "//select[@name='region_id']//option[@value='" + Billingvalue + "']").getText();

    	BillingAddress.put("BillingState", BillingState);
     	Thread.sleep(4000);
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='city']", data.get(dataSet).get("City"));
     	//Common.dropdown("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
     	//Thread.sleep(4000);
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='postcode']", data.get(dataSet).get("postcode"));
     	Thread.sleep(5000);
    	String BillingZip = Common.findElement("name", "postcode").getAttribute("value");
    	System.out.println("*****" + BillingZip + "*******");
    	BillingAddress.put("BillingZip", BillingZip);
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='telephone']", data.get(dataSet).get("phone"));
     	Common.actionsKeyPress(Keys.PAGE_DOWN);
     	Thread.sleep(5000);
     	Common.scrollIntoView("xpath", "//button[contains(@class,'action-update')]");
     	Thread.sleep(5000);
     	Common.mouseOverClick("xpath", "//button[contains(@class,'action-update')]");
     	
     	Thread.sleep(5000);
     	int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
         System.out.println("error messagess    "+sizeerrormessage);
     	Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying Billing addres filling ", "user will fill the all the billing address", "user fill the shipping address click save button", "faield to add new shipping address");
}
     	
     	catch(Exception |Error e) {
     		e.printStackTrace();
     		ExtenantReportUtils.addFailedLog("verifying Billing addres filling", "user will fill the all the Billing address", "faield to add new billing address",Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
     		Assert.fail();
     		
     	}  
		}
		return BillingAddress;
	//Common.clickElement("xpath","//label[contains(@for,'billing-address-same-as-shipping-ime_paymetrictokenize')]");
	
  
	   /* Common.actionsKeyPress(Keys.PAGE_UP);
	    Thread.sleep(3000);
	    Common.*///dropdown("xpath", "//select[@name='billing_address_id']",Common.SelectBy.TEXT, "New Address");
	}
	

public HashMap<String, String> Edit_EgiftBillingAddress_PaymetricPaymentMethod(String dataSet)throws Exception{
	HashMap<String, String> BillingAddress = new HashMap<String, String>();
	Thread.sleep(3000);// //button[contains(@class,'action-edit-address')]
	
		try{
	 
	    Thread.sleep(5000);
	    Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
		Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",data.get(dataSet).get("Email"));

		
		
         Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='firstname']");
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='firstname']",data.get(dataSet).get("FirstName"));
     	Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='lastname']");
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='lastname']",data.get(dataSet).get("LastName"));
     	/*Sync.waitElementPresent("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']");
     	Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']",data.get(dataSet).get("CompanyName"));*/
     	Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='company']");
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='company']",data.get(dataSet).get("CompanyName"));
     	Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='street[0]']");
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='street[0]']", data.get(dataSet).get("Street"));
     	Common.dropdown("xpath", "//fieldset[contains(@class,'fieldset')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
     	Thread.sleep(5000);
    	String Billingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
    	String BillingState = Common.findElement("xpath", "//select[@name='region_id']//option[@value='" + Billingvalue + "']").getText();

    	BillingAddress.put("BillingState", BillingState);
     	Thread.sleep(4000);
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='city']", data.get(dataSet).get("City"));
     	//Common.dropdown("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
     	//Thread.sleep(4000);
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='postcode']", data.get(dataSet).get("postcode"));
     	Thread.sleep(5000);
    	String BillingZip = Common.findElement("name", "postcode").getAttribute("value");
    	System.out.println("*****" + BillingZip + "*******");
    	BillingAddress.put("BillingZip", BillingZip);
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='telephone']", data.get(dataSet).get("phone"));
     	Common.actionsKeyPress(Keys.PAGE_DOWN);
     	Thread.sleep(5000);
     	Common.scrollIntoView("xpath", "//button[contains(@class,'action-update')]");
     	Thread.sleep(5000);
     	Common.mouseOverClick("xpath", "//button[contains(@class,'action-update')]");
     	
     	Thread.sleep(5000);
     	int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
         System.out.println("error messagess    "+sizeerrormessage);
     	Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying Billing addres filling ", "user will fill the all the billing address", "user fill the shipping address click save button", "faield to add new shipping address");
}
     	
     	catch(Exception |Error e) {
     		e.printStackTrace();
     		ExtenantReportUtils.addFailedLog("verifying Billing addres filling", "user will fill the all the Billing address", "faield to add new billing address",Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
     		Assert.fail();
     		
     	}  
		return BillingAddress;
		}
		
	//Common.clickElement("xpath","//label[contains(@for,'billing-address-same-as-shipping-ime_paymetrictokenize')]");
	
  
	   /* Common.actionsKeyPress(Keys.PAGE_UP);
	    Thread.sleep(3000);
	    Common.*///dropdown("xpath", "//select[@name='billing_address_id']",Common.SelectBy.TEXT, "New Address");
	
public void Verify_Out_of_US_BillingAddress(String dataSet)throws Exception{
	
	Thread.sleep(5000);// //button[contains(@class,'action-edit-address')]
	
	int sizes=Common.findElements("xpath", "//div[@class='billing-address-details']//button[contains(@class,'action-edit-address')]").size();
	if(sizes>0){
     Common.clickElement("xpath", "//div[@class='billing-address-details']//button[contains(@class,'action-edit-address')]");
try{
    int selectbutton=Common.findElements("xpath", "//select[@name='billing_address_id']").size();

    if(selectbutton>0){
    	Thread.sleep(4000);
    	Common.dropdown("xpath", "//select[@name='billing_address_id']", SelectBy.TEXT, "New Address");
    }
    verify_Billing_Country();
     Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='firstname']");
 	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='firstname']",data.get(dataSet).get("FirstName"));
 	Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='lastname']");
 	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='lastname']",data.get(dataSet).get("LastName"));
 	/*Sync.waitElementPresent("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']");
 	Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']",data.get(dataSet).get("CompanyName"));*/
 	Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='company']");
 	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='company']",data.get(dataSet).get("CompanyName"));
 	Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='street[0]']");
 	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='street[0]']", data.get(dataSet).get("Street"));
 	Common.dropdown("xpath", "//fieldset[contains(@class,'fieldset')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
 	Thread.sleep(4000);
 	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='city']", data.get(dataSet).get("City"));
 	//Common.dropdown("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
 	//Thread.sleep(4000);
 	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='postcode']", data.get(dataSet).get("postcode"));
 	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='telephone']", data.get(dataSet).get("phone"));
 	Common.actionsKeyPress(Keys.PAGE_DOWN);
 	Thread.sleep(5000);
 	Common.scrollIntoView("xpath", "//button[contains(@class,'action-update')]");
 	Thread.sleep(2000);
 	Common.mouseOverClick("xpath", "//button[contains(@class,'action-update')]");
 	
 	Thread.sleep(5000);
 	int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
     System.out.println("error messagess    "+sizeerrormessage);
 	Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying Billing addres filling ", "user will fill the all the billing address", "user fill the shipping address click save button", "faield to add new shipping address");
}
 	
 	catch(Exception |Error e) {
 		e.printStackTrace();
 		ExtenantReportUtils.addFailedLog("verifying Billing addres filling", "user will fill the all the Billing address", "faield to add new billing address",Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
 		Assert.fail();
 		
 	}  
	}
}

  public void select_USPS_StandardGround_shippingMethod() throws Exception{
		try{
	    	Common.actionsKeyPress(Keys.END);
	    	Sync.waitElementPresent("xpath", "(//td[text()='Standard'])");
	    	Common.scrollIntoView("xpath", "(//td[text()='Standard'])");
	         Common.javascriptclickElement("xpath", "(//td[text()='Standard'])");
	          ExtenantReportUtils.addPassLog("To verify the Standard shipping method", "Should click on standard shipping method", "user successfully clicked on standard shipping method", Common.getscreenShotPathforReport("Successfully clicked on standard shipping method"));
	     }catch(Exception |Error e) {
	      		ExtenantReportUtils.addFailedLog("To verify the standard shipping method","Should click on standard shipping method", "user unable to click on standard shipping method", Common.getscreenShotPathforReport("failed to click on standard shipping method"));			
	      		Assert.fail();	
	      		}
	  }
	public HashMap<String, String> creditCard_payment(String dataSet) throws Exception {
		HashMap<String, String> Payment = new HashMap<String, String>();

		try {

			// Common.scrollIntoView("id", "paymetric_xisecure_frame");
			// Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
			Thread.sleep(5000);
			Common.switchFrames("id", "paymetric_xisecure_frame");
			int size = Common.findElements("xpath", "//select[@id='c-ct']").size();
			Common.switchToDefault();
			Common.assertionCheckwithReport(size > 0, "validating Creditcard option", "click the creadit card label",
					"clicking credit card label and open the card fields", "user faield to open credit card form");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Credit Card option", "click the creadit card label",
					"faield to click Credit Card option", Common.getscreenShotPathforReport("Cardinoption"));
			Assert.fail();

		}

		try {

			Thread.sleep(2000);
			Common.switchFrames("id", "paymetric_xisecure_frame");
			Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
			String Cardtype=Common.findElement("xpath", "//select[@id='c-ct']").getAttribute("value");
			String Card=Common.findElement("xpath","//select[@id='c-ct']//option[@value='"+Cardtype+"']").getText();
		    Payment.put("Card", Card);
		    System.out.println("******" +Card+ "*****");
			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
			Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT,data.get(dataSet).get("ExpMonth"));
			Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
			Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));
			Thread.sleep(2000);

			// Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.switchToDefault();
			
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "//span[contains(text(),'Place Order')]");
			Thread.sleep(6000);
			String URL = Common.getCurrentURL();
			Thread.sleep(4000);
			if (URL.equals("https://www.drybar.com/checkout/#payment")) {

				Common.getCurrentURL();
				System.out.println(URL);

			} else {

				Common.mouseOverClick("xpath", "//span[contains(text(),'Place Order')]");
			}

			// Common.clickElement("xpath", "(//button[@title='Place Order'])");

			Common.switchFrames("id", "paymetric_xisecure_frame");
			String expectedResult = "credit card fields are filled with the data";
			String errorTexts = Common.findElement("xpath", "//div[contains(@id,'error')]").getText();
			Common.switchToDefault();
			Common.assertionCheckwithReport(errorTexts.isEmpty(),
					"validating the credit card information with valid data", expectedResult, "Filled the Card detiles",
					"missing field data it showinng error");

			Sync.waitPageLoad();
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation",
					"credit card fields are filled with the data", "faield  to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));
			Assert.fail();

		}
		return Payment;

	}
	
public void creditCard_payment_invalid_CC(String dataSet) throws Exception{
	
	try{
		
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		//Common.clickElement("xpath", "//label[@for='paymetric']");
		Thread.sleep(2000);
		Common.switchFrames("id", "paymetric_xisecure_frame");
		int size=Common.findElements("xpath", "//select[@id='c-ct']").size();
		Common.switchToDefault();
		Common.assertionCheckwithReport(size>0, "validating Creditcard option", "click the creadit card label", "clicking credit card label and open the card fields", "user faield to open credit card form");
		}
	   catch(Exception |Error e) {
		   e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Credit Card option", "click the creadit card label", "faield to click Credit Card option",  Common.getscreenShotPathforReport("Cardinoption"));
			Assert.fail();
			
		}
	
	
	try{
	
	Thread.sleep(2000);
	Common.switchFrames("id", "paymetric_xisecure_frame");
	Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
	Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
	Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
	Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
	Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));	
	Thread.sleep(2000);
	
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.switchToDefault();
	Thread.sleep(1000);
	Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
	Common.switchFrames("id", "paymetric_xisecure_frame");
	
	ExtenantReportUtils.addPassLog("validating the invalid Credit Card infromation", "credit card fields are filled with the invalid data", "successfully user enter credit card data", Common.getscreenShotPathforReport("cardinformation"));
	
	
	/*String expectedResult="credit card fields are filled with the data";
    String errorTexts=	Common. findElement("xpath", "//div[contains(@id,'error')]").getText();
    Common.switchToDefault();
    Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data", expectedResult, "Filled the Card detiles", "missing field data it showinng error");
    	
	*/
	}
	catch(Exception |Error e) {
	    ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", "credit card fields are filled with the data", "faield  to fill the Credit Card infromation",Common.getscreenShotPathforReport("Cardinfromationfail"));
		Assert.fail();
		
	}
	
	
	
}




public void braintree_creditCard_payment(String dataSet) throws Exception{
	
	try{
		
	//	Common.actionsKeyPress(Keys.PAGE_DOWN);
		//Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
		Thread.sleep(2000);
		Common.switchFrames("id", "braintree-hosted-field-number");
		int size=Common.findElements("id", "credit-card-number").size();
		Common.switchToDefault();
		Common.assertionCheckwithReport(size>0, "validating Creditcard option", "click the creadit card label", "clicking credit card label and open the card fields", "user faield to open credit card form");
		}
	   catch(Exception |Error e) {
		   e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Credit Card option", "click the creadit card label", "faield to click Credit Card option",  Common.getscreenShotPathforReport("Cardinoption"));
			Assert.fail();
			
		}
	
	
	try{
	
		
	Thread.sleep(2000);
	Common.switchFrames("id", "braintree-hosted-field-number");
    Common.textBoxInput("id", "credit-card-number", data.get(dataSet).get("cardNumber"));
    
  String month= data.get(dataSet).get("ExpMonth");
  String year=data.get(dataSet).get("ExpYear");
  System.out.println(month+year);
  Common.switchToDefault();
  Common.switchFrames("id", "braintree-hosted-field-expirationDate");
  Common.textBoxInput("id", "expiration",month+year);
  Common.switchToDefault();
  Common.switchFrames("id","braintree-hosted-field-cvv");
  Common.textBoxInput("id", "cvv", data.get(dataSet).get("cvv"));
	/*Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
	Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));*/
	//Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));	
	Thread.sleep(2000);
	
	/*Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.switchToDefault();
	Thread.sleep(1000);
	Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
	Common.switchFrames("id", "paymetric_xisecure_frame");
	String expectedResult="credit card fields are filled with the data";
    String errorTexts=	Common. findElement("xpath", "//div[contains(@id,'error')]").getText();
    Common.switchToDefault();
    Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data", expectedResult, "Filled the Card detiles", "missing field data it showinng error");
    	*/
	
	}
	catch(Exception |Error e) {
		e.printStackTrace();
	    ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", "credit card fields are filled with the data", "faield  to fill the Credit Card infromation",  Common.getscreenShotPathforReport("Cardinfromationfail"));
		Assert.fail();
		
	}	
}

public void navigateMyAccount() throws InterruptedException {
	Thread.sleep(8000);
	Sync.waitPageLoad();
	String expectedResult = "User should land on the home page";
	int size =Common.findElements("xpath", "//a[@class='logo']").size();
	Common.assertionCheckwithReport(size > 0, " verifying the home page", expectedResult,"Successfully landed on the home page", "User unabel to land on home page");
	// Common.assertionCheckwithReport(size>0, "Successfully landed on th home page", expectedResult,"User unabel to land on home page");
	try {
		Sync.waitPageLoad();
		Sync.waitElementClickable(30, By.xpath("(//ul[@class='header links']//li[2])[1]"));
		Common.findElement("xpath", "(//ul[@class='header links']//li[2])[1]").click();
		int size1 = Common.findElements(By.xpath("(//ul[@class='account-menu top-link-submenu']//li)[1]")).size();
		if(size1>0) {
			
			Common.findElement("xpath", "(//ul[@class='account-menu top-link-submenu']//li)[1]").click();
		}
		
		//int sizeelement=Common.findElements("xpath", "//ul[@class='header links']//li[2]//a[text()='My Account']").size();
		//if(sizeelement>0){
			//Common.clickElement("xpath", "//ul[@class='header links']//li[2]//a[text()='My Account']");
		//}
		Thread.sleep(3000);
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying my account option", "clcik the my account button",
				"User failed to clcik the my account button",
				Common.getscreenShotPathforReport("my account button"));
		Assert.fail();

	
	}}

public void loginApplication(String dataSet){
	String expectedResult = "Opens login pop_up";
   
	   int size= Common.findElements("id", "email").size();
	   if(size>0) {
	   Common.assertionCheckwithReport(size>0, "verifying login page", "open the login page", "successfully open the login page", "Failed open the login page");
	   try {
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Thread.sleep(2000);
			Common.mouseOverClick("xpath", "(//span[text()='Sign In'])[1]");
			Common.clickElement("xpath", "//ul[@class='header links']//li[2]/span");
			
			Common.clickElement("xpath", "//ul[@class='header links']//li[2]//a[text()='My Account']");
			  }
	   catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying login page with credentials", expectedResult,
					"User failed to login in account  ", Common.getscreenShotPathforReport("login faield"));
			Assert.fail();
	   }
		}
	}

public void click_Same_Billing_Address() throws Exception{
	try{
	Sync.waitPageLoad();
	Thread.sleep(5000);
	
	Common.clickElement("xpath", "(//input[@name='billing-address-same-as-shipping'])");
	
	ExtenantReportUtils.addPassLog("verifying Billing Address", "The Billing and shipping should be same", "user successfully added shipping and billing address same", Common.getscreenShotPathforReport("faield to add same billing address"));
	}
	catch(Exception |Error e) {
	   
		ExtenantReportUtils.addFailedLog("verifying Billing Address", "The Billing and shipping should be same", "user failed to add same shipping and billing address", Common.getscreenShotPathforReport("faield to add same billing address"));
		Assert.fail();
	}

}

public void addDeliveryAddress_registerUser(String dataSet) throws Exception {

	String expectedResult = "shipping address is entering in the fields";
    int size = Common.findElements(By.xpath("//span[contains(text(),'New Address')]")).size();
	if (size > 0) {

		try {
			Common.clickElement("xpath", "//span[contains(text(),'New Address')]");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
					data.get(dataSet).get("Street"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.SPACE);
			Thread.sleep(3000);
			
			if (data.get(dataSet).get("StreetLine2") != null) {
				Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
			}
		if (data.get(dataSet).get("StreetLine3") != null) {
				Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
			}
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
					data.get(dataSet).get("City"));
			// Common.mouseOverClick("name", "region_id");
			try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']",
					data.get(dataSet).get("postcode"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
					data.get(dataSet).get("phone"));
            Sync.waitElementPresent("xpath", "(//span[text()='Save in address book'])");
            Common.clickElement("xpath", "(//span[text()='Save in address book'])");
			ExtenantReportUtils.addPassLog("validating the shipping address", expectedResult,
					"user add the shipping address",
					Common.getscreenShotPathforReport("faield to add shipping address"));


			Common.clickElement("xpath", "//button[contains(@class,'save-address')]");

			int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

			Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
					"user will fill the all the shipping", "user fill the shiping address click save button",
					"faield to add new shipping address");
			
			
			
			Common.actionsKeyPress(Keys.END);
			Sync.waitPageLoad();
			select_USPS_StandardGround_shippingMethod();
            Thread.sleep(5000);
			Common.mouseOverClick("xpath", "//div[@id='shipping-method-buttons-container']/div/button");
			
			
			
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
					"User unabel add shipping address",
					Common.getscreenShotPathforReport("shipping address faield"));
						Assert.fail();

		}
	}

	else

	{
		try {
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
					data.get(dataSet).get("Street"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.SPACE);
			Thread.sleep(3000);
			try {
				Common.clickElement("xpath",
						"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			} catch (Exception e) {
				Common.actionsKeyPress(Keys.BACK_SPACE);
				Thread.sleep(1000);
				Common.actionsKeyPress(Keys.SPACE);
				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div");
				//Common.clickElement("xpath","//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			}
			if (data.get(dataSet).get("StreetLine2") != null) {
				Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
			}
			if (data.get(dataSet).get("StreetLine3") != null) {
				Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
			}
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
					data.get(dataSet).get("City"));
			// Common.mouseOverClick("name", "region_id");
			try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("name", "postcode");
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

			//Sync.waitElementClickable("xpath", "//span[contains(text(),'Continue To Payment')]");
			//Common.clickElement("xpath", "//span[contains(text(),'Continue To Payment')]");

		
			ExtenantReportUtils.addPassLog("verifying shipping addres filling ", expectedResult,
					"user enter the shipping address ",
					Common.getscreenShotPathforReport("fill the shipping address first time"));

			//Common.findElements("xpath", "").size();
			expectedResult = "shipping address is filled in to the fields";
			// report.addPassLog(expectedResult,"Filled the shipping
			// address",Common.getscreenShotPathforReport("fill the shipping
			// address"));
			
			//Common.mouseOverClick("xpath", "//input[@id='label_method_bestway']");
			//Sync.waitElementClickable("xpath","//input[@id='label_method_bestway']");
			//Common.clickElement("xpath", "//input[@id='label_method_bestway']");
			select_USPS_StandardGround_shippingMethod();
            Thread.sleep(5000);
			Common.clickElement("xpath", "//button[@data-role='opc-continue']");
			
			Thread.sleep(3000);

		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
					"User unabel add shipping address",
					Common.getscreenShotPathforReport("shipping address faield"));
			// ExtenantReportUtils.addFailedLog("User click check out
			// button", "User unabel click the checkout button",
			// Common.getscreenShotPathforReport("check out miniCart"));
			Assert.fail();

		}
	}

	// report.addPassLog("clicked on the proceed to payment
	// section",Common.getscreenShotPathforReport("land on the payment
	// section"));

}
	//Email: xshzsmsmstzenzojra@mhzayt.online
//Email: xshzsmsmstzenzojra@mhzayt.onlineEmail: xshzsmsmstzenzojra@mhzayt.online
//Rajkumar@1155
	
public void headLinksValidations(String dataSet) throws Exception{
	String Hederlinks=data.get(dataSet).get("HeaderNames");
	String[] hedrs=Hederlinks.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "//span[text()='"+hedrs[i]+"']");
		Common.clickElement("xpath", "//span[text()='"+hedrs[i]+"']");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(Common.getPageTitle()), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}

	
}

public void Validation_of_categorylist(String dataSet) throws Exception{
	String Hederlinks=data.get(dataSet).get("MegaMenu");
	String[] hedrs=Hederlinks.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "//span[text()='"+hedrs[i]+"']");
		Common.clickElement("xpath", "//span[text()='"+hedrs[i]+"']");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(Common.getPageTitle()), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}


public void updateProductInMinicart(String Product){
	try {
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'cart-item')]");
		Common.findElement("xpath", "//input[contains(@id,'cart-item')]").sendKeys(Keys.BACK_SPACE);
		//Common.textBoxInput("xpath", "//input[contains(@id,'cart-item')]",Keys.BACK_SPACE);
		
		Common.textBoxInput("xpath", "//input[contains(@id,'cart-item')]","2");
		Common.clickElement("xpath", "//span[text()='Update']");
		ExtenantReportUtils.addPassLog("verifying the update product in mini cart page","user update the product in mini cart page","user successfully update the product in mini cart page", Common.getscreenShotPathforReport("updateproduct"));
		
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("verifying the update product in mini cart page","user update the product in mini cart page","User unabel update product in mini cart page",Common.getscreenShotPathforReport("userminicartupdate"));
	
		Assert.fail();

	}
	
	
}

public void click_View_editcart(){
	try{
		
		Thread.sleep(4000);
	Sync.waitElementPresent("xpath", "//a[@class='action viewcart']");
	Common.clickElement("xpath", "//a[@class='action viewcart']");
	Sync.waitPageLoad();
	Thread.sleep(4000);
	ExtenantReportUtils.addPassLog("verifying the view wdit cart button from mincart page","user after click the  view and edit button it navigate to SHOPPING CART page","User navigate to SHOPPING CART page",Common.getscreenShotPathforReport("SHOPPINGCARTPAGE"));
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the view edit cart button from mincart page","user after click the  view and edit button it navigate to SHOPPING CART page","User unabel navigate to SHOPPING CART page",Common.getscreenShotPathforReport("SHOPPINGCARTPAGE"));
        Assert.fail();
        }
	}

public void ClearMiniCart_Bag() throws InterruptedException {
	try {
		Thread.sleep(5000);
		Common.clickElement("xpath", "//a[@class='action showcart desktop_only']");
		int sizes = Common.findElements("xpath", "(//strong[text()='You have no items in your shopping bag.'])").size();
		if (sizes > 0) {
			Common.getCurrentURL();
		} else {
			
			int size = Common.findElements("xpath", "(//span[contains(text(),'$0.00')])").size();
			if (size > 0) {
				List<WebElement> Products = Common.findElements("xpath", "(//ol//li)");
				System.out.println(Products);
				int ProductCount = Products.size();
				int ProductCount2 = ProductCount++;
				System.out.println(ProductCount);
				// if (Common.findElement("xpath", "//a[@class='action showcart desktop_only']")
				// != null) {
				while (ProductCount2 > 0) {
					Common.clickElement("xpath", "(//span[text()='Remove'])");
					Sync.waitPageLoad();
					Thread.sleep(3000);
					Common.clickElement("xpath", "(//span[text()='OK'])");
					Sync.waitPageLoad();
					Thread.sleep(3000);
					ProductCount2--;

				}
			} else {

				Thread.sleep(5000);
				List<WebElement> Products = Common.findElements("xpath", "(//ol//li)");
				int ProductCount = Products.size();
				// int ProductCount2=ProductCount++;
				System.out.println(ProductCount);
				// if (Common.findElement("xpath", "//a[@class='action showcart desktop_only']")
				// != null) {
				while (ProductCount > 0) {
					Sync.waitElementClickable("xpath", "(//span[text()='Remove'])");
					Common.mouseOver("xpath", "(//span[text()='Remove'])");
					Common.clickElement("xpath", "(//span[text()='Remove'])");
					Sync.waitPageLoad();
					Thread.sleep(3000);
					Common.mouseOver("xpath", "(//span[text()='OK'])");
					Common.clickElement("xpath", "(//span[text()='OK'])");
					Thread.sleep(7000);
					ProductCount--;

				}

			}

		}
	} catch (Exception | Error e) {
		Assert.fail();
	}
}


public void edit_ShopingCart(){
	try{
	Sync.waitElementPresent("xpath", "//a[contains(@class,'action-edit')]");
	Common.clickElement("xpath", "//a[contains(@class,'action-edit')]");
	int updatebuttonsize=Common.findElements("id","product-updatecart-button").size();
	
	Common.assertionCheckwithReport(updatebuttonsize>0, "Verifying the edit button in cart page", "user after click the edit button in cart detail page it will navigate product detail page", "user successfully navigate to product detail page ", "Failed to navigate to product detail page");
	Common.clickElement("id","product-updatecart-button");
	}catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the EditShoping cart button","user after click the edit button in cart detail page it will navigate product detail page","User faield click edit button",Common.getscreenShotPathforReport("edit button"));
        Assert.fail();
        }
}

public void changeQuntity_UpdateProduct(String productcount){
	try{
	Sync.waitElementPresent("xpath", "//select[contains(@id,'cart')]");
	Common.dropdown("xpath", "//select[contains(@id,'cart')]", SelectBy.VALUE, productcount);
	Common.clickElement("xpath", "//button[@id='update_cart_action']");
	Sync.waitPageLoad();
	String value=Common.findElement("xpath", "//select[contains(@id,'cart')]").getAttribute("value");
	System.out.println(Common.getPageTitle());
	
	Common.assertionCheckwithReport(value.equals(productcount), "verifying the product Quntity","user change the quntity of product","user successfully  change quntity","Failed to chage the quntity");
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the product Quntity","user change the quntity of product","User faield to chage the quntity",Common.getscreenShotPathforReport("failed changequntity"));
        Assert.fail();
        }
}
public void click_ContinueShopping() throws Exception{
	
	Sync.waitElementPresent("xpath", "//a[@title='Continue Shopping']");
	Common.clickElement("xpath", "//a[@title='Continue Shopping']");
	//Thread.sleep(5000);
   //  System.out.println(Common.getPageTitle());
	
}
public void addproductInMiniCartPage() throws Exception{
	
	Common.textBoxInput("xpath", "//input[contains(@id,'cart-item')]","2");
	
	Common.clickElement("xpath", "//span[text()='Update']");
	Common.clickElement("xpath", "//span[text()='View and Edit Cart']");
	
	// Cart page update//
	
	Common.dropdown("xpath", "//select[contains(@id,'cart')]", SelectBy.VALUE, "10");
	
	
	
	//Disscount
	Common.clickElement("xpath", "//div[@id='block-discount']");
	
	//Countinu shopping
	
	//Common.clickElement("xpath", "//a[@title='Continue Shopping']");
	
	
	//Removeproduct
	
	
	
}
public HashMap<String, String> gitCard(String dataSet) throws Exception{
	HashMap<String, String> Payment = new HashMap<String, String>();
	try{
		Thread.sleep(5000);
	Common.scrollIntoView("id", "block-giftcard-heading");
	Common.clickElement("id", "block-giftcard-heading");
	
	Thread.sleep(5000);

Common.textBoxInput("id","giftcard-code",data.get(dataSet).get("GiftCardCode"));
	
	Common.textBoxInput("id","giftcard-pin",data.get(dataSet).get("GiftCardPin"));
	String GiftCard="GiftCard";
	Payment.put("GiftCard", GiftCard);
	
	Common.clickElement("xpath", "//button[@value='Apply']");
	
	Thread.sleep(3000);
	int size=Common.findElements("xpath", "//div[contains(@class,'message-success')]/div").size();
	Common.assertionCheckwithReport(size>0, "validating the gift card", "Gift Card was added.", "successfully gift card was added","Failed to add gift card");
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the gift card","Gift Card was added.","User faield to to add gift card",Common.getscreenShotPathforReport("gitcard"));
        Assert.fail();
        }
	return Payment;
	
}
public void click_place_order_button(){
	try{
		Thread.sleep(2000);
	Sync.waitElementPresent("xpath", "//span[contains(text(),'Place Order')]");	
	Common.findElement("xpath", "//span[contains(text(),'Place Order')]");
	Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
	Thread.sleep(2000);
	Sync.waitPageLoad();
	System.out.println(Common.getPageTitle());
	Common.assertionCheckwithReport(Common.getPageTitle().contains("Checkout"),"verifying place order button", "navigate order confirmation page", "User navigate order confirmation message","User faield to navigate order confirmation message");
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying place order button","navigate order confirmation page","User faield to navigate order confirmation message",Common.getscreenShotPathforReport("confirmationmessage"));
        Assert.fail();
        }
		
}

public void click_forgotpassword() throws Exception{
	try{
	Common.clickElement("xpath", "//a[@class='action remind']");
    Common.textBoxInput("id", "email_address","varaprasad.raikanti@gmail.com");
	Common.clickElement("xpath", "(//span[text()='Reset My Password'])");
	//Common.actionsKeyPress(Keys.PAGE_UP);
	int size=Common.findElements("xpath", "(//div[contains(@class,'message')])[1]").size();
	Common.assertionCheckwithReport(size>0, "validating forgot password","Email has been sent to given email with instructions on resetting your password.", "Successfully Email has been sent to given email with instructions on resetting your password.","Failed to send forgetpassword");
	}
	catch (Exception | Error e) {
        ExtenantReportUtils.addFailedLog("validating forgot password","Email has been sent to given email with instructions on resetting your password.","User faield to send forgetpassword",Common.getscreenShotPathforReport("forgetpassword"));
        Assert.fail();
        }
}

public void Search_Buttercup_Dryer() throws Exception{
	
	try {
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		 Common.textBoxInput("id", "search","Butter cup Blow dryer");
		Thread.sleep(2000);
		ExtenantReportUtils.addPassLog("verifying the search functionality","Should get the  product name in search field","user successfully Entered the product name in search field", Common.getscreenShotPathforReport("searched productname successfully"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog(" To verify the search functionality","should get the product name in search field along with suggested products","user successfully Entered the product name in search field", Common.getscreenShotPathforReport("Failed to search the product"));			
		Assert.fail();	
		}
try {
		Common.actionsKeyPress(Keys.ENTER);
	     Sync.waitPageLoad();
	    ///Common.scrollIntoView("xpath", "(//div[@class='result-content'])[1]");
	   // Common.clickElement("xpath", "//*[@id=\\\"instant-search-results-container\\\"]/div/div/ol/li[1]/div/div/div[2]/a/span\");");
	  //  Common.clickElement("xpath", "//*[@id=\\\"instant-search-results-container\\\"]/div/div/ol/li[1]/div/div/div[2]/a/span\"");
	    //String viewcartbutton=Common.findElement("xpath", "(//h3[@itemprop='name'])[1]").getText();
	   // Common.assertionCheckwithReport(viewcartbutton.equals("Liquid Glass Smoothing Shampoo"), "verifying the viewcartbutton", "navigate to PLP Page", "user successfully navigate to the viewcartbutton", "Failed to navigate to vie cart button");
	  ExtenantReportUtils.addPassLog("verifying product search results page","User should land on Product list page","user successfully land on product search results page of searched product", Common.getscreenShotPathforReport("Successfully landed on PLP Page"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying product search results page","User should land on product list page ","user successfully land on product list page of searched product", Common.getscreenShotPathforReport("Failed to land on PLP Page"));			
		Assert.fail();	
		}
}

public void Search_Special_Value_set_Product() throws Exception{
	
	try {
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		 Common.textBoxInput("id", "search","SPECIAL VALUE SET 1");
		Thread.sleep(2000);
		ExtenantReportUtils.addPassLog("verifying the search functionality","Should get the  product name in search field","user successfully Entered the product name in search field", Common.getscreenShotPathforReport("searched productname successfully"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog(" To verify the search functionality","should get the product name in search field along with suggested products","user successfully Entered the product name in search field", Common.getscreenShotPathforReport("Failed to search the product"));			
		Assert.fail();	
		}
try {
		Common.actionsKeyPress(Keys.ENTER);
	     Sync.waitPageLoad();
	    ///Common.scrollIntoView("xpath", "(//div[@class='result-content'])[1]");
	   // Common.clickElement("xpath", "//*[@id=\\\"instant-search-results-container\\\"]/div/div/ol/li[1]/div/div/div[2]/a/span\");");
	  //  Common.clickElement("xpath", "//*[@id=\\\"instant-search-results-container\\\"]/div/div/ol/li[1]/div/div/div[2]/a/span\"");
	    //String viewcartbutton=Common.findElement("xpath", "(//h3[@itemprop='name'])[1]").getText();
	   // Common.assertionCheckwithReport(viewcartbutton.equals("Liquid Glass Smoothing Shampoo"), "verifying the viewcartbutton", "navigate to PLP Page", "user successfully navigate to the viewcartbutton", "Failed to navigate to vie cart button");
	  ExtenantReportUtils.addPassLog("verifying product search results page","User should land on Product list page","user successfully land on product search results page of searched product", Common.getscreenShotPathforReport("Successfully landed on PLP Page"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying product search results page","User should land on product list page ","user successfully land on product list page of searched product", Common.getscreenShotPathforReport("Failed to land on PLP Page"));			
		Assert.fail();	
		}
}

public void Search_kit_Product() throws Exception{
	
	try {
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		 Common.textBoxInput("id", "search","The Bigger better blow out box");
		Thread.sleep(2000);
		ExtenantReportUtils.addPassLog("verifying the search functionality","Should get the  product name in search field","user successfully Entered the product name in search field", Common.getscreenShotPathforReport("searched productname successfully"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog(" To verify the search functionality","should get the product name in search field along with suggested products","user successfully Entered the product name in search field", Common.getscreenShotPathforReport("Failed to search the product"));			
		Assert.fail();	
		}
try {
		Common.actionsKeyPress(Keys.ENTER);
	     Sync.waitPageLoad();
	    ///Common.scrollIntoView("xpath", "(//div[@class='result-content'])[1]");
	   // Common.clickElement("xpath", "//*[@id=\\\"instant-search-results-container\\\"]/div/div/ol/li[1]/div/div/div[2]/a/span\");");
	  //  Common.clickElement("xpath", "//*[@id=\\\"instant-search-results-container\\\"]/div/div/ol/li[1]/div/div/div[2]/a/span\"");
	    //String viewcartbutton=Common.findElement("xpath", "(//h3[@itemprop='name'])[1]").getText();
	   // Common.assertionCheckwithReport(viewcartbutton.equals("Liquid Glass Smoothing Shampoo"), "verifying the viewcartbutton", "navigate to PLP Page", "user successfully navigate to the viewcartbutton", "Failed to navigate to vie cart button");
	  ExtenantReportUtils.addPassLog("verifying product search results page","User should land on Product list page","user successfully land on product search results page of searched product", Common.getscreenShotPathforReport("Successfully landed on PLP Page"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying product search results page","User should land on product list page ","user successfully land on product list page of searched product", Common.getscreenShotPathforReport("Failed to land on PLP Page"));			
		Assert.fail();	
		}
}

public void couponCode(String dataSet){
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("id","block-discount-heading");
		
	Common.clickElement("id","block-discount-heading");
	System.out.println(data.get(dataSet).get("couponCode"));
	Thread.sleep(3000);
    Common.textBoxInput("id", "discount-code",data.get(dataSet).get("couponCode"));
    Thread.sleep(2000);
    Common.clickElement("xpath", "//button[@value='Apply Code']");
    
    Thread.sleep(5000);
	int size=Common.findElements("xpath", "//div[contains(@class,'message-success')]/div").size();
	Common.assertionCheckwithReport(size>0, "validating the offer code", "offer code was added.", "successfully offer code added","Failed to added offer code");
    }
	catch (Exception | Error e) {
		e.printStackTrace();
        ExtenantReportUtils.addFailedLog("validating offer code","offer code is applicable","User faield to add offer code",Common.getscreenShotPathforReport("offercode"));
        Assert.fail();
        }
}
public String order_Success_page() throws Exception {

	String order="";
	//addPaymentDetails(dataSet);
	String expectedResult = "It redirects to order confirmation page";

	//if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
		//addPaymentDetails(dataSet);
	
	Sync.waitPageLoad();
	Thread.sleep(6000);
	//int placeordercount = Common.findElements("xpath", "//span[text()='Place Order']").size();
	//Juttriles code //("xpath", "//span[text()='Place Order']")
	////button[@title='Place Order']   stage
	
	//String url=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
	String url=Common.getCurrentURL();
	Thread.sleep(3000);
	System.out.println(url);
	
	
	if(!url.contains("staging")){
		Sync.waitPageLoad();
		//ExtenantReportUtils.addPassLog(description, expectedResult, actualResult, Common.getscreenShotPathforReport(expectedResult));
		/*int sizeofelement=Common.findElements("id", "email").size();
		Common.assertionCheckwithReport(sizeofelement > 0, "verifying the paypal payment ", expectedResult,"open paypal site window", "faild to open paypal account");*/
	}
	
	else{
		try{
	String sucessMessage = Common.getText("xpath", "//h1[@class='page-title']").trim();
	// Assert.assertEquals(sucessMessage, "Your order has been
	// received","Sucess message validations");
	int sizes = Common.findElements("xpath", "//h1[@class='page-title']").size();
	Common.assertionCheckwithReport(sucessMessage.equals("THANK YOU FOR YOUR PURCHASE!"),
			"verifying the product confirmation", expectedResult,
			"Successfully It redirects to order confirmation page Order Placed",
			"User unabel to go orderconformation page");
	
	if(Common.findElements("xpath", "(//a[@class='order-number'])//strong").size()>0) {
		order=Common.getText("xpath", "(//a[@class='order-number'])//strong");
	}
	
	
	if(Common.findElements("xpath","//a[@class='order-number']/strong").size()>0) {
		order=	Common.getText("xpath", "//a[@class='order-number']/strong");
		
	}
	System.out.println(order);
		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
					"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();
		}
		

}
	return order;
	   
	}


public void order_Success() throws Exception {

	Sync.waitPageLoad();
	Thread.sleep(5000);

	String URL = Common.getCurrentURL();
	if (URL.contains("checkout/onepage/success/")) {

		String expectedResult = "It redirects to order confirmation page";
		try {
			Sync.waitPageLoad();
			Thread.sleep(5000);
			for (int i = 0; i < 10; i++) {

				if (Common.getCurrentURL().contains("success")) {
					break;
				}
				Thread.sleep(7000);
			}

			String sucessMessage = Common.getText("xpath", "//h1[@class='page-title']/span");
			System.out.println(sucessMessage);
			Thread.sleep(4000);
			Common.assertionCheckwithReport(sucessMessage.equals("THANK YOU FOR YOUR PURCHASE!"),
					"verifying the product confirmation", expectedResult,
					"Successfully It redirects to order confirmation page Order Placed",
					"User unabel to go orderconformation page");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
					"User failed to navigate  to order confirmation page",
					Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();
		}
	} else {
		Common.scrollIntoView("xpath", "//span[contains(text(),'Place Order')]");
	}
}

public void Open_Drybar_URL() throws Exception {
	try {
		String url=Common.getCurrentURL();
		if(url.contains("staging")) {
			Common.oppenURL("https://jetrails-staging.drybar.com/");
			Sync.waitPageLoad();
			Thread.sleep(5000);
		}else {
			Common.oppenURL("https://www.drybar.com/");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			
		}
		Common.oppenURL("");
	String OrderID1=Common.findElement("xpath", "(//a[@class='order-number'])").getText();
	Common.clickElement("xpath", "(//a[@class='order-number'])");
	Sync.waitPageLoad();
	
	String OrderID2=Common.findElement("xpath", "(//span[@class='base'])").getText();
	Common.assertionCheckwithReport(OrderID2.contains(OrderID1),"Verifying My order page","The ordered id and my orders order id should be equal", "The ordered id and my orders order id is equal", " order id");
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying My order page","The ordered id and my orders order id should be equal", "The ordered id and my orders order id is not equal", Common.getscreenShotPathforReport("failed order id"));			
		Assert.fail();	
		}
	}


public void order_verification() throws Exception {
	try {
	String OrderID1=Common.findElement("xpath", "(//a[@class='order-number'])").getText();
	System.out.println(OrderID1);
	Common.clickElement("xpath", "(//a[@class='order-number'])");
	Sync.waitPageLoad();
	
	String OrderID2=Common.findElement("xpath", "(//span[@class='base'])").getText();
	Common.assertionCheckwithReport(OrderID2.contains(OrderID1),"Verifying My order page","The ordered id and my orders order id should be equal", "The ordered id and my orders order id is equal", " order id");
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying My order page","The ordered id and my orders order id should be equal", "The ordered id and my orders order id is not equal", Common.getscreenShotPathforReport("failed order id"));			
		Assert.fail();	
		}
	}

public void Facebook()  {
	try{
		 Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("To view the  Article links","should land at End of the page with displaying Article links", "Sucessfully displayed footer Article links", Common.getscreenShotPathforReport("Successfully landed at end of the page with displaying Article links"));			
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To view the  Article links","should land at End of the page with displaying Article links", "user unable to display footer Article links", Common.getscreenShotPathforReport("failed to land at end of the page with displaying Article links"));			
		Assert.fail();	
		}
	try {
		Common.clickElement("xpath", "(//a[@title='Like Us On Facebook'])[2]");
		Thread.sleep(5000);
		Common.switchToSecondTab();
		Sync.waitPageLoad();
		Thread.sleep(5000);
         String URL=Common.getCurrentURL();
		Common.assertionCheckwithReport(URL.contains("facebook"),"To Verify the  Facebook Article link","it shoud navigate to facebook page", "successfully  navigated to Facebook page", "Facebook");
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the Facebook Article link","should land on Facebook Articlelink page", "user unable to navigate to Facebook Articlelink", Common.getscreenShotPathforReport("failed to land on Facebook page"));			
			Assert.fail();	
			}
	Common.closeCurrentWindow();
	Common.switchToFirstTab();
		}
public void Instagram() {
	try{
		 //Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("To view the Article links","should land at End of the page with displaying Article links", "Sucessfully displayed footer Article links", Common.getscreenShotPathforReport("Successfully landed at end of the page with displaying Article links"));			
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To view the  Article links","should land at End of the page with displaying Article links", "user unable to display footer Article links", Common.getscreenShotPathforReport("failed to land at end of the page with displaying Article links"));			
		Assert.fail();	
		}
	try {
		Common.clickElement("xpath", "(//a[@title='Check Us Out On Instagram'])[2]");
		Common.switchToSecondTab();
		Sync.waitPageLoad();
		Thread.sleep(5000);
         String Title=Common.getPageTitle();
		Common.assertionCheckwithReport(Title.contains("Instagram"),"To Verify the  Instagram Article link","It should navigate to Instagram page", "successfully  navigated to Instagram page", "Instagram");
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the Instagram Article link","should land on Instagram Articlelink page", "user unable to navigate to Instagram Articlelink", Common.getscreenShotPathforReport("failed to land on Instagram page"));			
			Assert.fail();	
			}
	Common.closeCurrentWindow();
	Common.switchToFirstTab();
		}

public void Twitter () {
	try{
		 //Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("To view the Article links","should land at End of the page with displaying Article links", "Sucessfully displayed footer Article links", Common.getscreenShotPathforReport("Successfully landed at end of the page with displaying Article links"));			
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To view the  Article links","should land at End of the page with displaying Article links", "user unable to display footer Article links", Common.getscreenShotPathforReport("failed to land at end of the page with displaying Article links"));			
		Assert.fail();	
		}
	try {
		Common.clickElement("xpath", "(//a[@title='Follow Us On Twitter'])[2]");
		Common.switchToSecondTab();
		Sync.waitPageLoad();
		Thread.sleep(5000);
         String URL=Common.getCurrentURL();
		Common.assertionCheckwithReport(URL.contains("https://twitter.com/thedrybar"),"To Verify the  Twitter Article link","It should navigate to Twitter page", "successfully  navigated to Twitter page", "Twitter");
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the Twitter Article link","should land on Twitter Articlelink page", "user unable to navigate to Twitter Articlelink", Common.getscreenShotPathforReport("failed to land on Twitter page"));			
			Assert.fail();	
			}
	Common.closeCurrentWindow();
	Common.switchToFirstTab();
		}

public void Pinterest() {
	try{
		 //Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("To view the Article links","should land at End of the page with displaying Article links", "Sucessfully displayed footer Article links", Common.getscreenShotPathforReport("Successfully landed at end of the page with displaying Article links"));			
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To view the  Article links","should land at End of the page with displaying Article links", "user unable to display footer Article links", Common.getscreenShotPathforReport("failed to land at end of the page with displaying Article links"));			
		Assert.fail();	
		}
	try {
		Common.clickElement("xpath", "(//a[@title='Follow Us On Pinterest'])[2]");
		Common.switchToSecondTab();
		Sync.waitPageLoad();
		Thread.sleep(5000);
         String Title=Common.getPageTitle();
		Common.assertionCheckwithReport(Title.contains("Drybar (thedrybar) | Official Pinterest account"),"To Verify the  Pinterest Article link","It should navigate to Pinterest page", "successfully  navigated to Pinterest page", "Pinterest");
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the Pinterest Article link","should land on Pinterest Articlelink page", "user unable to navigate to Pinterest Articlelink", Common.getscreenShotPathforReport("failed to land on Pinterest page"));			
			Assert.fail();	
			}
	Common.closeCurrentWindow();
	Common.switchToFirstTab();
		}

public void Youtube() {
	try{
		 //Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("To view the Article links","should land at End of the page with displaying Article links", "Sucessfully displayed footer Article links", Common.getscreenShotPathforReport("Successfully landed at end of the page with displaying Article links"));			
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To view the  Article links","should land at End of the page with displaying Article links", "user unable to display footer Article links", Common.getscreenShotPathforReport("failed to land at end of the page with displaying Article links"));			
		Assert.fail();	
		}
	try {
		Common.clickElement("xpath", "(//a[@title='Check Us Out On YouTube'])[2]");
		Common.switchToSecondTab();
		Sync.waitPageLoad();
		Thread.sleep(5000);
         String Title=Common.getPageTitle();
         System.out.println(Title);
		Common.assertionCheckwithReport(Title.contains("Drybar - YouTube"),"To Verify the  Youtube Article link","It should navigate to Youtube page", "successfully  navigated to Youtube page", "Youtube");
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the Youtube Article link","should land on youtube Articlelink page", "user unable to navigate to Youtube Articlelink", Common.getscreenShotPathforReport("failed to land on Youtube page"));			
			Assert.fail();	
			}
	Common.closeCurrentWindow();
	Common.switchToFirstTab();
		}

public void privacy_and_policy() {
	try{
		Thread.sleep(5000);
		 Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.PAGE_UP);
		//Common.scrollIntoView("xpath", "(//a[@title='Privacy Policy'])");
		Thread.sleep(4000);
		ExtenantReportUtils.addPassLog("To view privacy policy button", "Should land on privacy policy button", "User successfully navigated to privacy policy button", Common.getscreenShotPathforReport("Successfully landed on privacy button"));
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To view privacy policy button","should land on Privacy policy button", "user unable to navigate to privacy policy button", Common.getscreenShotPathforReport("failed to land on privacy button"));			
			Assert.fail();	
			}

try{
	
	
	Common.clickElement("xpath", "(//a[@title='Privacy Policy'])");
	Sync.waitPageLoad();
	Thread.sleep(5000);
    String Title=Common.getPageTitle();
    System.out.println(Title);
	Common.assertionCheckwithReport(Title.equals("Privacy Policy | Drybar"),"To Verify the Privacy policy Page","It should navigate to Privacy policy page", "successfully  navigated to Privacy policy page", "Privacy policy");
	
	//ExtenantReportUtils.addPassLog("To view privacy policy button", "Should land on privacy policy button", "User successfully navigated to privacy policy button", Common.getscreenShotPathforReport("Successfully landed on privacy button"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To view privacy policy button","should land on Privacy policy button", "user unable to navigate to privacy policy button", Common.getscreenShotPathforReport("failed to land on privacy button"));			
		Assert.fail();	
		}

}

public void Terms_Of_Use() {
	try{
		 //Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Common.scrollIntoView("xpath", "(//a[@title='Privacy Policy'])");
		Thread.sleep(4000);
		ExtenantReportUtils.addPassLog("To view Terms of use button", "Should land on Terms of use button", "User successfully navigated to terms of use button", Common.getscreenShotPathforReport("Successfully landed on terms of use button"));
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To view terms of use button","should land on Terms of use button", "user unable to navigate to terms of use button", Common.getscreenShotPathforReport("failed to land on terms of use button"));			
			Assert.fail();	
			}

try{
	
	
	Common.clickElement("xpath", "(//a[@title='Terms of Use'])");
	Sync.waitPageLoad();
	Thread.sleep(5000);
    String Title=Common.getPageTitle();
    System.out.println(Title);
	Common.assertionCheckwithReport(Title.equals("Terms of Use | Drybar"),"To Verify the terms of use page","It should navigate to terms of use page", "successfully  navigated to terms of use page ", "terms of use");
	
	//ExtenantReportUtils.addPassLog("To view privacy policy button", "Should land on privacy policy button", "User successfully navigated to privacy policy button", Common.getscreenShotPathforReport("Successfully landed on privacy button"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To view terms of use Page","should land on terms of use page", "user unable to navigate to terms of use page", Common.getscreenShotPathforReport("failed to land on terms of use page"));			
		Assert.fail();	
		}

}





public void forgetpasswordPageValidation(){

		try{
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.clickElement("xpath", "(//span[contains(text(),'Forgot Your Password?')])[1]");
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Thread.sleep(3000);
		    Common.textBoxInput("id", "email_address","manojkoppanadam");
			Common.scrollToElementAndClick("xpath", "(//span[contains(text(),'Reset My Password')])[1]");
			//Common.actionsKeyPress(Keys.PAGE_UP);
			int errormessage=Common.findElements("id", "email_address-error").size();
			
	Common.assertionCheckwithReport(errormessage>0, "Validating forget password from", "Enter invalid data it must dispaly error message", "User enter Invalid data in Forgetpassword field", "Failed to display error message ");		
	        }
		catch (Exception | Error e) {
	        ExtenantReportUtils.addFailedLog("validating forgot password form with  invalid data","I will showing error message","forgotpassword form faield dispaly  error message",Common.getscreenShotPathforReport("forgetpassword error message"));
	        Assert.fail();
	        }
	
}

public void Aggree_and_proceed() throws Exception {

		Sync.waitElementPresent("xpath", "(//button[@id='truste-consent-required'])");
		Common.clickElement("xpath", "(//button[@id='truste-consent-required'])");

 
}
	



public void checkorderstatus_footerlink() {
	try{
	    Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath", "(//a[@title='Check Order Status'])[2]");
		Sync.waitPageLoad();
		String Title=Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Online Order | Drybar"),"Verifying online order page","it shoud navigate to online order page", "successfully  navigated to online order page", "online order");
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying the order status footer link","user should navigate to order status footerlink", "user unable to navigate to  order status foterlink", Common.getscreenShotPathforReport("failed to land on checkorderstatus page"));			
			Assert.fail();	
			}
		}
public void Returns_footerlink() {
	try{
		 Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath", "(//a[@title='Returns'])");
		Sync.waitPageLoad();
         String Title=Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Returns | Drybar"),"Verifying Returns page","it shoud navigate to returns page", "successfully  navigated to returns page", "Returns");
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the returns footer link","should land on returns footerlink page", "user unable to navigate to returns foterlink", Common.getscreenShotPathforReport("failed to land on returns page"));			
			Assert.fail();	
			}
		}

public void Shipping_Delivery_footerlink() {
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath","(//a[@title='Shipping & Delivery'])");
		Sync.waitPageLoad();
		 Close_popup();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Shipping | Drybar"),"Verifying shipping page","it shoud navigate to shipping page", "successfully  navigated to shipping page", "Shipping");	
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the shipping footer link","should land on shipping footerlink page", "user unable to navigate to shipping foterlink", Common.getscreenShotPathforReport("failed to land on ahipping page"));			
			Assert.fail();	
			}
		}

public void Special_offers_footerlink() {
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath", "(//a[@title='Special Offers'])[2]");
		Sync.waitPageLoad();
		 Close_popup();
		 String Title=Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Special Offers | Drybar"),"Verifying special offers page","it shoud navigate to special offers page", "successfully  navigated to special offers page", "Special offers");	
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify special offers footer link","should navigate to special offer footerlink", "user unable to navigate to special offers foterlink", Common.getscreenShotPathforReport("failed to  land on special offers page"));			
			Assert.fail();	
			}
		}

public void warranty_footerlink() {
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath","(//a[@title='Warranty'])");
		Sync.waitPageLoad();
		 Close_popup();
		String Title=Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Warranty | Drybar"),"Verifying Warranty page","it shoud navigate to warranty page", "successfully  navigated to warranty page", "Warranty");
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the warranty footer link","Should navigate to warranty footerlink page", "userunable to  navigate to warranty foterlink", Common.getscreenShotPathforReport("failed to land on warranty page"));			
			Assert.fail();	
			}
		}

public void safetydata_footerlink() {
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath","(//a[@title='Safety Data Sheets'])");
		Sync.waitPageLoad();
		 Close_popup();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Safety Data Sheets | Drybar"),"Verifying Safety data page","it shoud navigate to safety data page", "successfully  navigated to safety data page", "Safety data");	
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the safety data sheets footer link","Should navigate to safety data sheets footerlink", "user unable to navigate to safety data sheets foterlink", Common.getscreenShotPathforReport("failed to navigate to data sheets page"));			
			Assert.fail();	
			}
		}

public void Glossary_footerlink() {
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath","(//a[@title='Glossary'])");
		Sync.waitPageLoad();
		 Close_popup();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Glossary | Drybar"),"Verifying Glossary page","it shoud navigate to Glossary page", "successfully  navigated to Glossary page", "Glossary");	
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the glossary footer link","should navigate to glossary footerlink", "user unable to navigate to glossary foterlink", Common.getscreenShotPathforReport("failed to navigate to warranty page"));			
			Assert.fail();	
			}
		}

public void Blowout_footerlink() {
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath","(//a[@title=\"What's a Blowout\"])");
		Sync.waitPageLoad();
		 Close_popup();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("What's a Blowout? | Drybar"),"Verifying Blowout page","it shoud navigate to Blowout page", "successfully  navigated to Blowout page", "Blowout");	
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the blowout footer link","navigate to blowout footerlink", "user unable to  navigate to blowout foterlink", Common.getscreenShotPathforReport("failed to navigate to blowout page"));			
			Assert.fail();	
			}
		}

public void WheretoBuy_footerlink() {

	try {
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath", "(//a[text()='Where to Buy'])[2]");
		Thread.sleep(5000);
		Sync.waitPageLoad();
		String URL = Common.getCurrentURL();
		System.out.println(URL);
		Common.assertionCheckwithReport(URL.contains("where-to-buy"), "To Verify the Where to Buy Footerlink",
				"it shoud navigate to Drybar Shop page", "successfully  navigated to  Drybar Shop page",
				" Drybar Shop");
	} catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("To Verify the Where to Buy Footerlink",
				"should land on  Drybar Shop page", "user unable to navigate to  Drybar Shop page",
				Common.getscreenShotPathforReport("failed to land on  Drybar Shop page"));
		Assert.fail();
	}
}

public void aboutUs_footerlink() {
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath","(//a[@title='About Us'])");
		Sync.waitPageLoad();
		 Close_popup();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("About Us | Drybar"),"Verifying About US page","it shoud navigate to About US page", "successfully  navigated to About US Page", "About US");	
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the about us footer link","should navigate to about us footerlink", "userunable to navigate to about us foterlink", Common.getscreenShotPathforReport("failed to navigate to about us footerlinkpage"));			
			Assert.fail();	
			}
		}

public void Email_To__a_Friend(String dataset) {
	try {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		
		ExtenantReportUtils.addPassLog("To verify the Email a Friend icon","should display Email a Friend icon", "unable to display Email a Friend icon", Common.getscreenShotPathforReport("failed to display email a Friend icon"));			

	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Email a  Friend icon","should display Email a Friend icon", "userunable to view Email a Friend icon", Common.getscreenShotPathforReport("failed to view Email a Friend icon"));			
		Assert.fail();	
		}
	
	try {
		Sync.waitElementPresent("xpath", "(//a[@title='Share by Email'])");
		Common.clickElement("xpath", "(//a[@title='Share by Email'])");
		String Title=Common.getText("xpath", "(//span[contains(text(),'Email to a Friend')])");	
		Common.assertionCheckwithReport(Title.equals("EMAIL TO A FRIEND"),"Verifying Email to a Friend page","it shoud navigate to Email to a Friend page", "successfully  navigated to Email to a Friend Page", "Email to a Friend");	
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Email to a Friend icon ","should navigate to Email to a Friend page", "userunable to navigate to Email to a Friend page", Common.getscreenShotPathforReport("failed to navigate to Email to a Friend page"));			
		Assert.fail();	
		}
	try {
		Sync.waitElementPresent("id", "recipients-name0");
		Common.textBoxInput("id", "recipients-name0",data.get(dataset).get("FirstName"));
		Sync.waitElementPresent("id", "recipients-email0");
		Common.textBoxInput("id", "recipients-email0",data.get(dataset).get("Email"));
		Sync.waitElementPresent("id", "sender-message");
		Common.textBoxInput("id", "sender-message",data.get(dataset).get("Message"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the about us footer link","should navigate to about us footerlink", "userunable to navigate to about us foterlink", Common.getscreenShotPathforReport("failed to navigate to about us footerlinkpage"));			
		Assert.fail();	
		}
	try {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.clickElement("xpath", "(//span[contains(text(),'Send Email')])");
		Sync.waitPageLoad();
		int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();
        
		 Common.assertionCheckwithReport(message>0, "To verify the Email to a Friend success message", "Should Successfully send the Email with success message","sucessfully sent the message through Email a Friend", "faield to click sent email button");
	}catch(Exception |Error e) {
		Assert.fail();	
	}
}

public void add_to_cart() {
	
	try{
		Sync.waitPageLoad();
		Thread.sleep(5000);
	    Common.scrollIntoView("xpath", "(//span[contains(text(),'Liquid Glass Smoothing Shampoo')])");
	    Thread.sleep(2000);
	    Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[1]");
	    Thread.sleep(4000);
	   // Common.clickElement("xpath", "");
	    Sync.waitElementPresent("xpath", "(//button[@title='Add to Bag'])[1]");
		Common.mouseOverClick("xpath", "(//button[@title='Add to Bag'])[1]");
		Thread.sleep(5000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(5000);
		int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();

		 Common.assertionCheckwithReport(message>0, "To verify the product added to My Wishlist", "Should add product to  My wishlist page","Product sucessfully added to My wishlist", "faield to add product to Wishlist");
	
		//ExtenantReportUtils.addPassLog("verifying add to cart button in Homepage", "User click add to card button on Homepage", "user successfully click add to cart button in Homepage", Common.getscreenShotPathforReport("faieldtoclickaddtocartbuttonin Homepage"));
		}
		catch(Exception |Error e) {
		   
			ExtenantReportUtils.addFailedLog("verifying add to cart button", "User click add to card button", "user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
			Assert.fail();
		}
		
	}

public void add_to_cart_from_Homepage() {
	
	try{
		Sync.waitPageLoad();
		Thread.sleep(5000);
	    Common.scrollIntoView("xpath", "((//div[@class='slick-track'])[3]//div//a)[4]");
	    Thread.sleep(3000);
	    Common.actionsKeyPress(Keys.ARROW_UP);
	    Common.actionsKeyPress(Keys.ARROW_UP);
	    Common.actionsKeyPress(Keys.ARROW_UP);
	    Common.actionsKeyPress(Keys.ARROW_UP);
	    Common.actionsKeyPress(Keys.ARROW_UP);
	    Thread.sleep(5000);
	    Common.mouseOver("xpath", "(//div[@class='widget-carousel-wrapper featured_products-carousel'])[1]//div[@class='slick-slide slick-current slick-active']");
	    Thread.sleep(4000);
	   // Common.clickElement("xpath", "");
	    Sync.waitElementPresent("xpath", "(//div[@class='widget-carousel-wrapper featured_products-carousel'])[1]//div[@class='slick-slide slick-current slick-active']//button");
	    Thread.sleep(4000);
		Common.mouseOverClick("xpath", "(//div[@class='widget-carousel-wrapper featured_products-carousel'])[1]//div[@class='slick-slide slick-current slick-active']//button");
		Thread.sleep(5000);
		ExtenantReportUtils.addPassLog("verifying add to cart button", "User click add to card button", "user successfully click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
	}
	catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying add to cart button", "User click add to card button", "user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
		Assert.fail();
	}
	
	try {
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		/*Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);*/
		//Common.scrollIntoView("xpath", "(//div[@class='message-success success message'])[2]");
		Sync.waitPageLoad();
		Thread.sleep(2000);
		int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();

		 Common.assertionCheckwithReport(message>0, "To verify the product added success message from Homepage", "Should display product added to bag success message","Product sucessfully added to bag from Homepage", "faield to add product to bag from Homepage");
	
		//ExtenantReportUtils.addPassLog("verifying add to cart button in Homepage", "User click add to card button on Homepage", "user successfully click add to cart button in Homepage", Common.getscreenShotPathforReport("faieldtoclickaddtocartbuttonin Homepage"));
		}
		catch(Exception |Error e) {
		   
			ExtenantReportUtils.addFailedLog("verifying product added success message", "User should get success message", "user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
			Assert.fail();
		}
}



	public void search_product_fullname(String dataset) {
		
		try {
			Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
			Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
		    Sync.waitPageLoad();
			Common.textBoxInput("id", "search",data.get(dataset).get("ProductName"));
			ExtenantReportUtils.addPassLog("To verify the search functionality with full productname","should get the product name in search field","user  successfully Entered the productname", Common.getscreenShotPathforReport("Searched productname Successfully"));
			
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the search functionality with fullproduct name","Should get the productname in search field","user unable to Enter the productname", Common.getscreenShotPathforReport("Failed to search proudctname"));			
			Assert.fail();	
			}
	
	try {
		    Common.actionsKeyPress(Keys.ENTER);
		    Thread.sleep(2000);
		   // Common.scrollIntoView("xpath", "(//h3[@class='result-title text-ellipsis'])[1]");
		    ExtenantReportUtils.addPassLog("To verify search results page","Should land on  product list page","user successfully landed on  product search results page", Common.getscreenShotPathforReport("Successfully landed on PLP Page"));
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify search results page","Should land on  product list page","user successfully landed on  product search results page", Common.getscreenShotPathforReport("Failed to land on PLP Page"));			
			Assert.fail();	
			}
		}
public void search_product_Threeletters(String dataset) {
		
		try {
			Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
			Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
		    Sync.waitPageLoad();
			Common.textBoxInput("id", "search",data.get(dataset).get("Threedigitproductname"));
			Thread.sleep(2000);
		    ExtenantReportUtils.addPassLog("To verify productsearch suggessions","should open product suggessions after searching","user Should get the search related product suggesstions", Common.getscreenShotPathforReport("Searchedproduct suggesstions displayed Successfully"));
			
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify productsearch suggessions","should open product suggesstions after searching","user unable to get the product suggesstions", Common.getscreenShotPathforReport("Failed to get searched product suggesstions"));			
			Assert.fail();	
			}
		}

public void search_product_Fourletters(String dataset) {
	
	try {
		Common.actionsKeyPress(Keys.ENTER);
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("Fourdigitproductname"));
		Thread.sleep(4000);
		 ExtenantReportUtils.addPassLog("To verify search function by entering four letters of productname","user  should enter the four letters of productname","user successfully entred the four letters of productname",Common.getscreenShotPathforReport("Searched Successfully with four letters of productname"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify search function by entering four leters of productname","user should enter the four leters of product name","user unable to enter the four letters of productname", Common.getscreenShotPathforReport("Failed to search productname bu four letters"));			
		Assert.fail();	
		}
	
try {
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(2000);
	    //Common.scrollIntoView("xpath", "(//h3[@class='result-title text-ellipsis'])[1]");
	    ExtenantReportUtils.addPassLog("To verify  search results page by searching with four letters of productname","user should land on a product list page by searching with four lettes of productname","user successfully landed on PLP Page by searching with four letters of productname", Common.getscreenShotPathforReport("Searched Successfully with four letters of productname"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify search results page by searching with four letters of productname","user should land on productlist page by searching with four letters of productname","user successfully unable to land on search results page", Common.getscreenShotPathforReport("Failed to search with four letters of productname"));			
		Assert.fail();	
		}
	}
public void search_product_Dublicate(String dataset) {
	
	try {
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("dublicateproductname"));
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(2000);
	    //Common.scrollIntoView("xpath", "(//h3[@class='result-title text-ellipsis'])[1]");
	    ExtenantReportUtils.addPassLog("verifying search results page","navigate to product list page","user successfully navigate to product search results page", Common.getscreenShotPathforReport("Searched Successfully"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying search results page","navigate to product list page","user successfully navigate to product search results page", Common.getscreenShotPathforReport("Failed to search"));			
		Assert.fail();	
		}
	}

public void search_product_invalid(String dataset) {
	
	try {
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("invalidname"));
		ExtenantReportUtils.addPassLog("To verify search function with invalid product","user should enter invalid productname","user successfully entered invalid productname", Common.getscreenShotPathforReport("Searched Successfully with invalid productname"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify search function with invalid product","user should Enter invalid productname","user unable to enter invalid producrname", Common.getscreenShotPathforReport("Failed to  search with invalid product name"));			
		Assert.fail();	
		}
	
try{
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(2000);
	    //Common.scrollIntoView("xpath", "(//h3[@class='result-title text-ellipsis'])[1]");
	    ExtenantReportUtils.addPassLog("To verify search results page with invalid productname","Should land on a PLP Page with zero products","user successfully landed on PLP page with zero products", Common.getscreenShotPathforReport("Searched Successfully with invalid productname"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify seaarch results page with invalid productname ","should land on PLP page with zero products","user unable to land on  product search results page with zero products", Common.getscreenShotPathforReport("Failed to search with invalid productname"));			
		Assert.fail();	
		}
	}

public void verify_viewproduct_button(String dataset) {
	
	try {
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("ProductName"));
		ExtenantReportUtils.addPassLog("verifying search results page","navigate to product list page","user successfully navigate to product search results page", Common.getscreenShotPathforReport("viewed cart button Successfully"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying the view cart button","navigate to product list page","user successfully navigate to the view cart button", Common.getscreenShotPathforReport("Failed to view cart buttom"));			
		Assert.fail();	
		}
	
try {
		
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(2000);
	    Common.scrollIntoView("xpath", "//*[@id=\"instant-search-results-container\"]/div/div/ol/li[1]/div/div/div[2]/a/span");
	  //  Common.clickElement("xpath", "//*[@id=\\\"instant-search-results-container\\\"]/div/div/ol/li[1]/div/div/div[2]/a/span\"");
	    String viewcartbutton=Common.findElement("xpath", "(//h3[@itemprop='name'])[1]").getText();
	    Common.assertionCheckwithReport(viewcartbutton.equals("Liquid Glass Smoothing Shampoo"), "verifying the viewcartbutton", "navigate to PLP Page", "user successfully navigate to the viewcartbutton", "Failed to navigate to vie cart button");
	  //  ExtenantReportUtils.addPassLog("verifying search results page","navigate to product list page","user successfully navigate to product search results page", Common.getscreenShotPathforReport("viewed cart button Successfully"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying the view cart button","navigate to product list page","user successfully navigate to the view cart button", Common.getscreenShotPathforReport("Failed to view cart buttom"));			
		Assert.fail();	
		}
	}
public void Search_outofstock_productname(String dataset) {
	
	try {
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("OutProductName"));
		ExtenantReportUtils.addPassLog("verifying the search functionality","Should get the  product name in search field","user successfully Entered the product name in search field", Common.getscreenShotPathforReport("searched productname successfully"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog(" To verify the search functionality","should get the product name in search field along with suggested products","user successfully Entered the product name in search field", Common.getscreenShotPathforReport("Failed to search the product"));			
		Assert.fail();	
		}
try {
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(2000);
	    //Common.scrollIntoView("xpath", "(//span[@class='item-desc'])");
	   // Common.clickElement("xpath", "//*[@id=\\\"instant-search-results-container\\\"]/div/div/ol/li[1]/div/div/div[2]/a/span\");");
	  //  Common.clickElement("xpath", "//*[@id=\\\"instant-search-results-container\\\"]/div/div/ol/li[1]/div/div/div[2]/a/span\"");
	    //String viewcartbutton=Common.findElement("xpath", "(//h3[@itemprop='name'])[1]").getText();
	   // Common.assertionCheckwithReport(viewcartbutton.equals("Liquid Glass Smoothing Shampoo"), "verifying the viewcartbutton", "navigate to PLP Page", "user successfully navigate to the viewcartbutton", "Failed to navigate to vie cart button");
	  ExtenantReportUtils.addPassLog("verifying product search results page","User should land on Product list page","user successfully land on product search results page of searched product", Common.getscreenShotPathforReport("Successfully landed on PLP Page"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying product search results page","User should land on product list page ","user successfully land on product list page of searched product", Common.getscreenShotPathforReport("Failed to land on PLP Page"));			
		Assert.fail();	
		}
	}

public void searchresultspage_navigation(String dataset) {
	
	try {
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("Pname"));
		ExtenantReportUtils.addPassLog("To verify the search functionality with full productname","should get the product name in search field","user  successfully Entered the productname", Common.getscreenShotPathforReport("Searched productname Successfully"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the search functionality with fullproduct name","Should get the productname in search field","user unable to Enter the productname", Common.getscreenShotPathforReport("Failed to search proudctname"));			
		Assert.fail();	
		}
try {
		
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(2000);
		ExtenantReportUtils.addPassLog("To verify the PLP Page","should land on PLP Page","user  successfully landed on PLP Page", Common.getscreenShotPathforReport("user Successfully landed on PLP Page"));
		
		Sync.waitPageLoad();
	    Common.scrollIntoView("xpath", "//*[@id=\"instant-search-pagination-container\"]/div/ul/li[3]");
	    Thread.sleep(2000);
	    Common.clickElement("xpath", "//*[@id=\"instant-search-pagination-container\"]/div/ul/li[3]");
	    Common.scrollIntoView("xpath","//*[@id=\"instant-search-pagination-container\"]/div/ul/li[3]");
	    //Common.assertionCheckwithReport(viewcartbutton.equals("Liquid Glass Smoothing Shampoo"), "verifying the viewcartbutton", "navigate to PLP Page", "user successfully navigate to the viewcartbutton", "Failed to navigate to vie cart button");
	   ExtenantReportUtils.addPassLog("To verify search results second page","navigate to second product list page","user successfully navigated to second product search results page", Common.getscreenShotPathforReport("navigated to second PLP page Successfully"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying the search results second page","navigate to second product list page","user unable to navigate to second PLP Page", Common.getscreenShotPathforReport("Failed to navigate to second PLP page"));			
		Assert.fail();	
		}
	}

public void navigate_PDP_Page(String dataset) {
	
	try {
		Common.scrollIntoView("xpath", "(//span[@class='search-link top-link'])");
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
		Thread.sleep(2000);
	    Sync.waitPageLoad();
		Common.textBoxInput("name", "q",data.get(dataset).get("ProductName"));
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(2000);
	    Common.scrollIntoView("xpath", "//*[@id=\"instant-search-results-container\"]/div/div/ol/li[1]/div/div/div[2]/a/span");
	    Thread.sleep(2000);
	    Common.clickElement("xpath", "//*[@id=\\\"instant-search-results-container\\\"]/div/div/ol/li[1]/div/div/div[2]/a/span");
	    Sync.waitPageLoad();
	    Sync.waitElementPresent("xpath", "(//button[@title='Add to Bag'])[1]");
	    String addtocart=Common.findElement("xpath", "(//button[@title='Add to Bag'])[1]").getAttribute("type");
	    Common.assertionCheckwithReport(addtocart.equals("submit"), "verifying the viewcartbutton", "navigate to PLP Page", "user successfully navigate to the viewcartbutton", "Failed to navigate to vie cart button");
	  //  ExtenantReportUtils.addPassLog("verifying search results page","navigate to product list page","user successfully navigate to product search results page", Common.getscreenShotPathforReport("viewed cart button Successfully"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying the view cart button","navigate to product list page","user successfully navigate to the view cart button", Common.getscreenShotPathforReport("Failed to view cart buttom"));			
		Assert.fail();	
		}
	}

public void Accept() {

	try {

		Thread.sleep(5000);
		if (Common.findElement("xpath", "//button[@id='truste-consent-required']") != null) {

			Common.clickElement("xpath", "//button[@id='truste-consent-required']");
		} 
		}catch (Exception e) {

		e.printStackTrace();

	}
}

public void Verify_FreeGift() throws Exception {
	Sync.waitPageLoad();
	Thread.sleep(3000);
	int sizes = Common.findElements("xpath", "(//button[@title='Add to cart'])").size();
	if (sizes > 0) {
		Common.clickElement("xpath", "(//button[@title='Add to cart'])");
	} else {
		Thread.sleep(4000);
		System.out.println(Common.getCurrentURL());
	}
	Thread.sleep(4000);
}
   
public void vara(){

try {

Thread.sleep(5000);

Sync.waitElementPresent("xpath", "//button[@id='truste-consent-required']");

      Common.clickElement("xpath", "//button[@id='truste-consent-required']");

} catch (Exception e) {

// TODO Auto-generated catch block

e.printStackTrace();

}

 

      

}

       

       

       public void Productionselection(){

try {

Thread.sleep(5000);

Sync.waitElementPresent("xpath", "//button[@id='truste-consent-required']");

      Common.clickElement("xpath", "//button[@id='truste-consent-required']");

} catch (Exception e) {

// TODO Auto-generated catch block

e.printStackTrace();

}

 

      

}

       

 

public void Loginuser_PDP(){

       try {

Thread.sleep(2000);

      Sync.waitElementPresent("xpath", "(//span[contains(text(), 'Hair Products')])[1]");
      Common.clickElement("xpath", "(//span[contains(text(), 'Hair Products')])[1]");
      Sync.waitElementPresent("xpath", "(//div[contains(text(), 'Shampoos')])[2]");
      Common.clickElement("xpath", "(//div[contains(text(), 'Shampoos')])[2]");
      Sync.waitElementPresent("xpath", "(//h2[@class='product name product-item-name'])[1]");
      Common.clickElement("xpath", "(//h2[@class='product name product-item-name'])[1]");
      Sync.waitPageLoad();
      String Addtobag=Common.findElement("xpath","//button[@title='Add to Bag']").getAttribute("title");
		Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "verifying the Loginuser PDP", "navigate to Loginuser PDP", "user successfully navgate to Loginuser PDP");
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying the Loginuser PDP","navigate to Loginuser PDP", "user successfully navigate to Loginuser PDP", Common.getscreenShotPathforReport("failed to Verify login PDP"));			
			Assert.fail();	
			}
		}

      

    //  Sync.waitElementPresent("xpath", "//button[@title='Add to Bag']");

     // Common.clickElement("xpath", "//button[@title='Add to Bag']");

      

    //  Sync.waitElementPresent("xpath", "//a[@class='action showcart desktop_only']");

      //Common.clickElement("xpath", "//a[@class='action showcart desktop_only']");

      

      //Sync.waitElementPresent("xpath", "//button[@title='Proceed to Checkout']");

      //Common.clickElement("xpath", "//button[@title='Proceed to Checkout']");
    
public void Select_Sale_Category() {
	try {
		Sync.waitElementPresent("xpath", "//span[text()='Hair Products']");
		Common.mouseOver("xpath", "//span[text()='Hair Products']");
		ExtenantReportUtils.addPassLog("Verifying Hair products Sub-categories in Homepage",
				"Should display Hair products sub-categories under Hair products",
				"User Successfully displayed Hair products sub-categories under Hair products",
				Common.getscreenShotPathforReport(
						"faield  to display Hair products sub-categories under Hair products"));
		Thread.sleep(4000);
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("Verifying Hair products Sub-categories in Homepage",
				"Should display Hair products sub-categories under Hair products",
				"user faield to display Hair products sub-categories under Hair products",
				Common.getscreenShotPathforReport("display Hair products sub-categories under Hair products"));
		Assert.fail();
	}

	try {
		Sync.waitElementPresent("xpath", "(//a[@alt='Sale'])[1]");
		Common.mouseOverClick("xpath", "(//a[@alt='Sale'])[1]");
		Sync.waitPageLoad();
		Thread.sleep(5000);
		Close_popup();
		String Page = Common.findElement("xpath", "(//Strong[contains(text(),'Last Chance')])").getText();
		Common.assertionCheckwithReport(Page.equals("Last Chance"), "verifying Sale  Products category",
				"User Should navigate to Sale Products page", "user successfully landed on Sale Products page",
				"user faield to click Sale Category");
	}

	catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Sale Products category",
				"User Should navigate to Sale Products page", "user faield to land on Sale Products page",
				Common.getscreenShotPathforReport("Sale Products"));
		Assert.fail();
	}
}

public void Select_Sale_product() {
	try {
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "(//a[contains(text(),'Whiskey Fix Styling Paste')])");
		Common.clickElement("xpath", "(//a[contains(text(),'Whiskey Fix Styling Paste')])");
		Thread.sleep(2000);
		String OldPrice = Common.findElement("xpath", "(//span[@data-price-type='oldPrice'])[1]").getText();
		Thread.sleep(3000);
		System.out.println("Old Price=" + OldPrice);
		String SpecialPrice = Common.findElement("xpath", "(//span[@data-price-type='finalPrice'])[1]").getText();
		System.out.println("Special Price=" + SpecialPrice);
		Common.assertionCheckwithReport(SpecialPrice.equals("$13.50"), "verifying Sale product PDP",
				"Should land on sale Product PDP with displaying old price and special price",
				"user successfully landed on sale product PDP with displaying Old price and Special price",
				"user faield to land on Sale Product PDP Page");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Sale product PDP",
				"Should land on sale Product PDP with displaying old price and special price",
				"user faield to land on Sale Product PDP Page",
				Common.getscreenShotPathforReport("user faield to land on Sale Product PDP Page"));
		Assert.fail();
	}

}

public void Close_popup() {
try {
		  Thread.sleep(2000); if (Common.findElement("xpath","(//input[@id='ltkpopup-email'])") != null) {
		  
		  Common.clickElement("xpath", "(//a[@class='ltkpopup-close'])"); 
		  }
		  }catch
		  (Exception e) {
		  e.printStackTrace(); 
		  }
}
		 
	
	
public void Verify_PDP() {

	try {
		Sync.waitPageLoad();
		Thread.sleep(5000);
		String verifyaddtobag = Common.findElement("xpath", "(//button[@class='action primary tocart'])")
				.getAttribute("title");
		//Accept();
		String Title = Common.getPageTitle();
		System.out.println(Title);
	   // Close_popup();
		Common.assertionCheckwithReport(verifyaddtobag.equals("Add to Bag"), "Verifying PDP page",
				"it shoud navigate to PDP page", "successfully  navigated to PDP Page", "PDP Page");
	} catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("To verify the  the PDP Page", "Should land on PDP page",
				"user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on PDP page"));
		Assert.fail();
	}
}
 public void Verify_Single_USPS_Ground_Shpping_Method(){

	    try {
	    	 Sync.waitPageLoad();
	    	 Common.clickElement("xpath", "(//div[@class='step-title'])[2]");
	    	 Thread.sleep(3000);
	    	 Common.actionsKeyPress(Keys.END);
	          Thread.sleep(2000);
	          Common.clickElement("xpath", "(//label[@for='s_method_flatrate'])");
	        String Verify_Shipping_Method=Common.findElement("xpath", "(//td[@id='label_method_flatrate_flatrate'])").getAttribute("id");
	        String Title= Common.getPageTitle();
	  		System.out.println(Title);
	  		Common.assertionCheckwithReport(Verify_Shipping_Method.equals("label_method_flatrate_flatrate"),"Verifying Shipping Method for Aerosol Product with PO Box Shipping Address","Shoud display single USPS Ground shipping method ", "successfully displayed Single USPS Ground Shipping method", "Shipping methods");
	   }catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("Verifying Shipping Method for Aerosol Product with PO Box Shipping Address","Shoud display single USPS Ground shipping method ", "Failed to display Single USPS Ground Shipping method", Common.getscreenShotPathforReport("failed to display Single USPS Ground method"));			
			Assert.fail();	
			}
		}
 
 
 
 public void Verify_UPS_TwoDay_and_UPSNextDay_Shpping_Methods(){

	    try {
	    	 Sync.waitPageLoad();
	    	 Common.clickElement("xpath", "(//div[@class='step-title'])[2]");
	    	 Thread.sleep(3000);
	    	 Common.actionsKeyPress(Keys.END);
	          Thread.sleep(2000);
	         String Price= Common.findElement("xpath", "(//span[@class='price'])[1]").getText(); 
	       // String Verify_Shipping_Method=Common.findElement("xpath", "(//div[@id='checkout-shipping-method-load'])").getAttribute("id");
	        String Title= Common.getPageTitle();
	  		System.out.println(Title);
	  		Common.assertionCheckwithReport(Price.equals("$15.00"),"Verifying Shipping Method for Aerosol Product with PO Box Shipping Address","Shoud display single USPS Ground shipping method ", "successfully displayed Single USPS Ground Shipping method", "Shipping methods");
	   }catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("Verifying Shipping Method for Aerosol Product with PO Box Shipping Address","Shoud display single USPS Ground shipping method ", "Failed to display Single USPS Ground Shipping method", Common.getscreenShotPathforReport("failed to display Single USPS Ground method"));			
			Assert.fail();	
			}
		}
 
 public void Verify_Single_UPS_Ground_Shpping_Method(){

	    try {
	    	 Sync.waitPageLoad();
	    	 Common.clickElement("xpath", "(//div[@class='step-title'])[2]");
	    	 Thread.sleep(3000);
	    	 Common.actionsKeyPress(Keys.END);
	          Thread.sleep(2000);
	          Common.clickElement("xpath", "(//label[@for='s_method_bestway'])");
	        String Verify_Shipping_Method=Common.findElement("xpath", "(//td[@id='label_carrier_bestway_tablerate'])").getText();
	        String Title= Common.getPageTitle();
	  		System.out.println(Title);
	  		Common.assertionCheckwithReport(Verify_Shipping_Method.equals("5 - 7 Business Days (Monday-Friday)"),"Verifying Shipping Method for Aerosol and Non-Aerosol Products with Non-PO Box Shipping Address","Shoud display single UPS Ground shipping method ", "successfully displayed Single UPS Ground Shipping method", "Shipping methods");
	   }catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("Verifying Shipping Method for Aerosol and Non-Aerosol Products with Non-PO Box Shipping Address","Shoud display single UPS Ground shipping method ", "Failed to display Single USPS Ground Shipping method", Common.getscreenShotPathforReport("failed to display Single UPS Ground method"));			
			Assert.fail();	
			}
		}
 
 public void Verify_No_Shipping_Method(){

	    try {
	        Common.clickElement("xpath", "(//div[@class='step-title'])[2]");
	        Thread.sleep(3000);
	    	Common.actionsKeyPress(Keys.END);
	    	Thread.sleep(3000);
	    	int NoShippingAddressMessage=Common.findElements("xpath", "(//div[@class='no-quotes-block'])[1]").size();
	        Common.assertionCheckwithReport(NoShippingAddressMessage>0, "To verify Shipping Methods", "Should display Zero Shipping methods","Sucessfully displayed Zero shipping methods", "faield to display zero shipping methods");
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify Shipping Methods","Shoud display Zero shipping method ", "Failed to display zero Shipping method", Common.getscreenShotPathforReport("failed to display zero shipping method"));			
			Assert.fail();	
			}
		}
 
 
 
 public void Verify_tax(){

	    try {
	    	 Sync.waitPageLoad();
	          Thread.sleep(6000);
	        String verifyTax=Common.findElement("xpath", "(//th[text()='Tax'])").getText();
	        String Title= Common.getPageTitle();
	  		System.out.println(Title);
	  		Common.assertionCheckwithReport(verifyTax.equals("Tax"),"Verifying tax in Payment page","Should display tax at right of the Payment details page", "successfully  displayed tax at right of the Payment details page", "Payment Page");
	   }catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("Verifying tax in Payment page","Should display tax at right of the  Payment details page", "user unable to land on Payment page", Common.getscreenShotPathforReport("failed to land on Payment page"));			
			Assert.fail();	
			}
		}
 
 
 
 public void Verify_HoT_Employee_PDP(){

	    try {
	    	 Sync.waitPageLoad();
	          Thread.sleep(5000);
	        String DiscountPrice=Common.findElement("xpath", "(//span[contains(text(),'$90.00')])[1]").getText();
	        String Title= Common.getPageTitle();
	        Thread.sleep(2000);
	  		System.out.println(Title);
	  		Common.assertionCheckwithReport(DiscountPrice.equals("$90.00"),"Verifying PDP page of HoT Employee","It shoud navigate to PDP page with displaing Discount Price", "successfully  navigated to PDP Page of HoT Employee with displaying Discount Price", "PDP Page");
	   }catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("Verifying PDP page of HoT Employee","It shoud navigate to PDP page with displaing Discount Price", "Failed display HoT Employee PDP with  Discount Price", Common.getscreenShotPathforReport("failed to land on PDP page"));			
			Assert.fail();	
			}
		}
 
 
 public void Verify_Guest_Discount_PDP(){

	    try {
	    	 Sync.waitPageLoad();
	          Thread.sleep(2000);
	        String DiscountPrice=Common.findElement("xpath", "(//span[@class='price'])[1]").getText();
	        String Title= Common.getPageTitle();
	  		System.out.println(Title);
	  		Common.assertionCheckwithReport(DiscountPrice.equals("$90.00"),"Verifying PDP page of HoT Employee","It shoud navigate to PDP page with displaing Discount Price", "successfully  navigated to PDP Page of HoT Employee with displaying Discount Price", "PDP Page");
	   }catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("Verifying PDP page of HoT Employee","It shoud navigate to PDP page with displaing Discount Price", "Failed display HoT Employee PDP with  Discount Price", Common.getscreenShotPathforReport("failed to land on PDP page"));			
			Assert.fail();	
			}
		}
 
 
 public void Verify_GeneralUser_PDP(){

	    try {
	    	 Sync.waitPageLoad();
	          Thread.sleep(5000);
	        String Price=Common.findElement("xpath", "(//span[@class='price'])[1]").getText();
	        String Title= Common.getPageTitle();
	  		System.out.println(Title);
	  		Common.assertionCheckwithReport(Price.equals("$150.00"),"Verifying Product price for General user in PDP page","It should display Product price in PDP without any discount", "successfully displayed Product price in PDP without any discount");
	   }catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("Verifying Product price for General user in PDP page","It should display Product price in PDP without any discount", "Failed to  display Product price in PDP without any discount", Common.getscreenShotPathforReport("failed to land on 404 PDP page"));			
			Assert.fail();	
			}
		}
 
 public static String getEmailid() {
		String Email=null;
		try {
			long currentTimestamp = System.currentTimeMillis();
			 Email ="newuser"+currentTimestamp+"@gmail.com";
		}

		catch (Exception e) {

			e.printStackTrace();
		}
		return Email;
	}
   

  // Sync.waitElementPresent("xpath", "//button[@title='Add to Bag']");

  // Common.clickElement("xpath", "//button[@title='Add to Bag']");

   

  // Sync.waitElementPresent("xpath", "//a[@class='action showcart desktop_only']");

  // Common.clickElement("xpath", "//a[@class='action showcart desktop_only']");

   

 //  Sync.waitElementPresent("xpath", "//button[@title='Proceed to Checkout']");

  // Common.clickElement("xpath", "//button[@title='Proceed to Checkout']");
   //Sync.waitPageLoad();
   //Sync.waitElementPresent("xpath", "(//a[@class='link'])");

  // Common.clickElement("xpath", "(//a[@class='link'])");
 
 
 
 public void Select_Searched_Product() {
	 try {
	        Sync.waitPageLoad(); 
	          Close_popup(); 
	          //Accept();
	        Thread.sleep(5000);
	        Sync.waitElementPresent("xpath", "(//a[text()=' The Double Shot Oval Blow-Dryer Brush '])");
	        Thread.sleep(2000);
	        Common.mouseOver("xpath", "(//a[text()=' The Double Shot Oval Blow-Dryer Brush '])");
	        Thread.sleep(5000);
	        Common.clickElement("xpath", "(//a[text()=' The Double Shot Oval Blow-Dryer Brush '])");
	        Sync.waitPageLoad();
	        Thread.sleep(4000);
	        Sync.waitElementPresent("xpath", "(//button[@class='action primary tocart'])");
	        Thread.sleep(1000);
	        Common.actionsKeyPress(Keys.ARROW_DOWN);
	        String AddtoBag = Common.findElement("xpath", "(//button[@class='action primary tocart'])")
	                .getAttribute("title");
	        Common.assertionCheckwithReport(AddtoBag.equals("Add to Bag"), "Verifying PDP page",
	                "it shoud navigate to PDP page", "successfully  navigated to PDP Page", "PDP Page");
	    } catch (Exception | Error e) {
	        ExtenantReportUtils.addFailedLog("To verify the  the PDP Page", "Should land on PDP page",
	                "user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on PDP page"));
	        Assert.fail();
	    }
	}
 
 public void Select_Lash_Blowout_Product() {
	 try {
	        Sync.waitPageLoad(); 
	          Close_popup(); 
	          //Accept();
	        Thread.sleep(5000);
	        Sync.waitElementPresent("xpath", "(//a[text()=' Lash Blowout Mascara By IT Cosmetics '])");
	        Thread.sleep(2000);
	        Common.mouseOver("xpath", "(//a[text()=' Lash Blowout Mascara By IT Cosmetics '])");
	        Thread.sleep(5000);
	        Common.clickElement("xpath", "(//a[text()=' Lash Blowout Mascara By IT Cosmetics '])");
	        Sync.waitPageLoad();
	        Thread.sleep(4000);
	        Sync.waitElementPresent("xpath", "(//button[@class='action primary tocart'])");
	        Thread.sleep(1000);
	        Common.actionsKeyPress(Keys.ARROW_DOWN);
	        String AddtoBag = Common.findElement("xpath", "(//button[@class='action primary tocart'])")
	                .getAttribute("title");
	        Common.assertionCheckwithReport(AddtoBag.equals("Add to Bag"), "Verifying PDP page",
	                "it shoud navigate to PDP page", "successfully  navigated to PDP Page", "PDP Page");
	    } catch (Exception | Error e) {
	        ExtenantReportUtils.addFailedLog("To verify the  the PDP Page", "Should land on PDP page",
	                "user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on PDP page"));
	        Assert.fail();
	    }
	}
 
 public void Select_ButtercupDryer_Product() {
	 try {
	        Sync.waitPageLoad(); 
	          Close_popup(); 
	          //Accept();
	        Thread.sleep(5000);
	        Sync.waitElementPresent("xpath", "(//a[text()=' Buttercup Blow-Dryer '])");
	        Thread.sleep(2000);
	        Common.mouseOver("xpath", "(//a[text()=' Buttercup Blow-Dryer '])");
	        Thread.sleep(5000);
	        Common.clickElement("xpath", "(//a[text()=' Buttercup Blow-Dryer '])");
	        Sync.waitPageLoad();
	        Thread.sleep(4000);
	        Sync.waitElementPresent("xpath", "(//button[@class='action primary tocart'])");
	        Thread.sleep(1000);
	        Common.actionsKeyPress(Keys.ARROW_DOWN);
	        String AddtoBag = Common.findElement("xpath", "(//button[@class='action primary tocart'])")
	                .getAttribute("title");
	        Common.assertionCheckwithReport(AddtoBag.equals("Add to Bag"), "Verifying PDP page",
	                "it shoud navigate to PDP page", "successfully  navigated to PDP Page", "PDP Page");
	    } catch (Exception | Error e) {
	        ExtenantReportUtils.addFailedLog("To verify the  the PDP Page", "Should land on PDP page",
	                "user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on PDP page"));
	        Assert.fail();
	    }
	}
 
 public void Select_Specialvalueset_Product() {
	 try {
	        Sync.waitPageLoad(); 
	       
	          //Accept();
	        Thread.sleep(5000);
	        Sync.waitElementPresent("xpath", "(//a[text()=' The Tools Special Value Set #1 '])");
	        Thread.sleep(2000);
	        Common.mouseOver("xpath", "(//a[text()=' The Tools Special Value Set #1 '])");
	        Thread.sleep(5000);
	        Common.clickElement("xpath", "(//a[text()=' The Tools Special Value Set #1 '])");
	        Sync.waitPageLoad();
	        Thread.sleep(4000);
	        Sync.waitElementPresent("xpath", "(//button[@class='action primary tocart'])");
	        Thread.sleep(1000);
	        Common.actionsKeyPress(Keys.ARROW_DOWN);
	        String AddtoBag = Common.findElement("xpath", "(//button[@class='action primary tocart'])")
	                .getAttribute("title");
	        Common.assertionCheckwithReport(AddtoBag.equals("Add to Bag"), "Verifying PDP page",
	                "it shoud navigate to PDP page", "successfully  navigated to PDP Page", "PDP Page");
	    } catch (Exception | Error e) {
	        ExtenantReportUtils.addFailedLog("To verify the  the PDP Page", "Should land on PDP page",
	                "user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on PDP page"));
	        Assert.fail();
	    }
	}
 
 public void Select_Kit_Product() {
	 try {
	        Sync.waitPageLoad(); 
	       
	          //Accept();
	        Thread.sleep(5000);
	        Sync.waitElementPresent("xpath", "(//a[text()=' The Bigger Better Blowout Box Buttercup Blow-Dryer Kit '])");
	        Thread.sleep(2000);
	        Common.mouseOver("xpath", "(//a[text()=' The Bigger Better Blowout Box Buttercup Blow-Dryer Kit '])");
	        Thread.sleep(5000);
	        Common.clickElement("xpath", "(//a[text()=' The Bigger Better Blowout Box Buttercup Blow-Dryer Kit '])");
	        Sync.waitPageLoad();
	        Thread.sleep(4000);
	        Sync.waitElementPresent("xpath", "(//button[@class='action primary tocart'])");
	        Thread.sleep(1000);
	        Common.actionsKeyPress(Keys.ARROW_DOWN);
	        String AddtoBag = Common.findElement("xpath", "(//button[@class='action primary tocart'])")
	                .getAttribute("title");
	        Common.assertionCheckwithReport(AddtoBag.equals("Add to Bag"), "Verifying PDP page",
	                "it shoud navigate to PDP page", "successfully  navigated to PDP Page", "PDP Page");
	    } catch (Exception | Error e) {
	        ExtenantReportUtils.addFailedLog("To verify the  the PDP Page", "Should land on PDP page",
	                "user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on PDP page"));
	        Assert.fail();
	    }
	}
 
 
 
 

 /*public void Click_View_Product(){

	    try {
	    	 Sync.waitPageLoad();
	    	 Common.actionsKeyPress(Keys.ARROW_DOWN);
	          Thread.sleep(5000);
	          Common.clickElement("xpath", "(//span[text()='View Product'])[1]");
	         Sync.waitPageLoad();
	         Common.actionsKeyPress(Keys.ARROW_DOWN);
	         String AddtoBag=Common.findElement("xpath", "(//button[@class='action primary tocart'])").getAttribute("title");
	        Common.assertionCheckwithReport(AddtoBag.equals("Add to Bag"),"Verifying PDP page","it shoud navigate to PDP page", "successfully  navigated to PDP Page", "PDP Page");  
	         }catch(Exception |Error e) {		
	        	 ExtenantReportUtils.addFailedLog("To verify the  the PDP Page","Should land on PDP page", "user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on PDP page"));	
	        	 Assert.fail();			
	        	 } 
	    }*/

 public void View_Product(){

	    try {
	    	 Sync.waitPageLoad();
	    	 Common.actionsKeyPress(Keys.ARROW_DOWN);
	          Thread.sleep(2000);
	          Common.clickElement("xpath", "(//span[text()='View Product'])[1]");
	         Sync.waitPageLoad();
	         //Common.actionsKeyPress(Keys.ARROW_DOWN);
	         String AddtoBag=Common.findElement("xpath", "(//button[@class='action primary tocart'])").getAttribute("title");
	         Common.assertionCheckwithReport(AddtoBag.equals("Add to Bag"),"Verifying PDP page","it shoud navigate to PDP page", "successfully  navigated to PDP Page", "PDP Page");  
	         }catch(Exception |Error e) {		
	        	 ExtenantReportUtils.addFailedLog("To verify the  the PDP Page","Should land on PDP page", "user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on PDP page"));	
	        	 Assert.fail();			
	        	 } 
	    }   
		

public void search(String dataset) {
	try {
		Sync.waitElementPresent("xpath", "//*[@id=\"search_mini_form\"]/div/label");
		Common.clickElement("xpath", "//*[@id=\"search_mini_form\"]/div/label");
		Sync.waitElementPresent("xpath", "(//input[@id='search'])");
		Common.textBoxInput("id", "search",data.get(dataset).get("vicksproductname"));
		ExtenantReportUtils.addPassLog("To verify the search functionality with full productname","should get the product name in search field","user  successfully Entered the productname", Common.getscreenShotPathforReport("Searched productname Successfully"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the search functionality with fullproduct name","Should get the productname in search field","user unable to Enter the productname", Common.getscreenShotPathforReport("Failed to search proudctname"));			
		Assert.fail();	
		}

try {
	    Common.actionsKeyPress(Keys.ENTER);
	    Thread.sleep(2000);
	   // Common.scrollIntoView("xpath", "(//h3[@class='result-title text-ellipsis'])[1]");
	    ExtenantReportUtils.addPassLog("To verify search results page","Should land on  product list page","user successfully landed on  product search results page", Common.getscreenShotPathforReport("Successfully landed on PLP Page"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify search results page","Should land on  product list page","user successfully landed on  product search results page", Common.getscreenShotPathforReport("Failed to land on PLP Page"));			
		Assert.fail();	
		}
	}


public void Enter_EmployeeDisdount_productname(String dataset) {
	
	try {
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("ProductName"));
		Thread.sleep(5000);
		Common.actionsKeyPress(Keys.ENTER);
	     Thread.sleep(5000);
	     ExtenantReportUtils.addPassLog("verifying product search results page","User should land on Product list page with Displaying zero products","user successfully land on product search results page with displaying Zero Products", Common.getscreenShotPathforReport("Successfully landed on PLP Page with displaying Zero products"));
			
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying product search results page","User should land on product list page with Displaying zero products ","user successfully land on product list page of searched product with Displaying zero products", Common.getscreenShotPathforReport("Failed to land on PLP Page"));			
			Assert.fail();	
			}
		}	

public void Enter_EmployeeDisdount_productname_In_HyattGroupAcc(String dataset) {
	
	try {
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("ProductName"));
		Thread.sleep(2000);
		Common.actionsKeyPress(Keys.ENTER);
	     Sync.waitPageLoad();	
	     ExtenantReportUtils.addPassLog("verifying product search results page","User should land on Product list page with Displaying products","user successfully land on product search results page with displaying Products", Common.getscreenShotPathforReport("Successfully landed on PLP Page with displaying products"));
			
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying product search results page","User should land on product list page with Displaying products ","user successfully land on product list page of searched product with Displaying products", Common.getscreenShotPathforReport("Failed to land on PLP Page"));			
			Assert.fail();	
			}
		}	

		
public void Search_productname(String dataset) {
	
	try {
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("ProductName"));
		Thread.sleep(2000);
		ExtenantReportUtils.addPassLog("verifying the search functionality","Should get the  product name in search field","user successfully Entered the product name in search field", Common.getscreenShotPathforReport("searched productname successfully"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog(" To verify the search functionality","should get the product name in search field along with suggested products","user successfully Entered the product name in search field", Common.getscreenShotPathforReport("Failed to search the product"));			
		Assert.fail();	
		}
try {
		Common.actionsKeyPress(Keys.ENTER);
	     Sync.waitPageLoad();
	    ///Common.scrollIntoView("xpath", "(//div[@class='result-content'])[1]");
	   // Common.clickElement("xpath", "//*[@id=\\\"instant-search-results-container\\\"]/div/div/ol/li[1]/div/div/div[2]/a/span\");");
	  //  Common.clickElement("xpath", "//*[@id=\\\"instant-search-results-container\\\"]/div/div/ol/li[1]/div/div/div[2]/a/span\"");
	    //String viewcartbutton=Common.findElement("xpath", "(//h3[@itemprop='name'])[1]").getText();
	   // Common.assertionCheckwithReport(viewcartbutton.equals("Liquid Glass Smoothing Shampoo"), "verifying the viewcartbutton", "navigate to PLP Page", "user successfully navigate to the viewcartbutton", "Failed to navigate to vie cart button");
	  ExtenantReportUtils.addPassLog("verifying product search results page","User should land on Product list page","user successfully land on product search results page of searched product", Common.getscreenShotPathforReport("Successfully landed on PLP Page"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying product search results page","User should land on product list page ","user successfully land on product list page of searched product", Common.getscreenShotPathforReport("Failed to land on PLP Page"));			
		Assert.fail();	
		}
	}	
public void Verify_OutofStockPDP(){

    try {

Sync.waitPageLoad();
Thread.sleep(5000);
Sync.waitElementClickable("xpath", "(//a[contains(text(),' At First Crush Straightening Brush Kit ')])[2]");
Thread.sleep(3000);
Common.clickElement("xpath", "(//a[contains(text(),' At First Crush Straightening Brush Kit ')])[2]");
Sync.waitPageLoad();
Thread.sleep(3000);
//Common.actionsKeyPress(Keys.PAGE_DOWN);
String OSS=Common.findElement("xpath","(//span[contains(text(),'Sorry, we???ve sold out!')])").getText();
Common.assertionCheckwithReport(OSS.equals("SORRY, WE???VE SOLD OUT!"), "To verify the PDP with out of stock", "Should land on out of stock PDP page","User unable to land on Out of Stock PDP", "faield to land on out of stock PDP page");
//ExtenantReportUtils.addPassLog("To verify the Product description page with out of stock", "Should land on out of stock Product description page", "user successfully landed on out of stock product description page", Common.getscreenShotPathforReport("Successfully landed on  out of stock PDP page"));

	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the  the PDP Page with out of stock","Should land on out of stock PDP page", "user unable to land on  Out of Stock PDP", Common.getscreenShotPathforReport("failed to land on out of stock PDP page"));			
		Assert.fail();	
		}
	}

public void Out_Of_Stock_Subscription(String DataSet){

    try {
          Sync.waitPageLoad();
         // Common.actionsKeyPress(Keys.PAGE_DOWN);
          Sync.waitElementPresent("xpath", "(//input[@name='guest_email'])");
          Common.textBoxInput("xpath", "(//input[@name='guest_email'])",data.get(DataSet).get("Email"));
          Thread.sleep(3000);
         
         ExtenantReportUtils.addPassLog("To verify the Email text field", "Should enter the email address in the email text field", "user successfully entered the Email address", Common.getscreenShotPathforReport("Successfully Entered Email address"));

	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Email text field","Should enter the email address in the email text field ", "user unable to Enter Email address", Common.getscreenShotPathforReport("failed to enter Email address"));			
		Assert.fail();	
		}
try {
    Common.clickElement("xpath", "(//button[@class='action submit primary'])"); 
    Thread.sleep(4000);
    int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();

	 Common.assertionCheckwithReport(message>0, "To verify the product added to My Wishlist", "Should add product to  My wishlist page","Product sucessfully added to My wishlist", "faield to add product to Wishlist");

    }catch(Exception |Error e) {
	ExtenantReportUtils.addFailedLog("To verify the Email text field","Should enter the email address in the email text field ", "user unable to Enter Email address", Common.getscreenShotPathforReport("failed to enter Email address"));			
	Assert.fail();	
	}
}

public void RegisterUser_Out_Of_Stock_Subscription(){
	try {
	    Common.clickElement("xpath", "(//a[@title='Notify me when this product is in stock'])"); 
	    Thread.sleep(4000);
	    int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();

		 Common.assertionCheckwithReport(message>0, "To verify the product added to My Wishlist", "Should add product to  My wishlist page","Product sucessfully added to My wishlist", "faield to add product to Wishlist");

	    }catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Email text field","Should enter the email address in the email text field ", "user unable to Enter Email address", Common.getscreenShotPathforReport("failed to enter Email address"));			
		Assert.fail();	
		}
	}


public void Add_product_to_Wishlist() {
	   
	    try {
	        Thread.sleep(5000);
	        Sync.waitElementPresent("xpath", "((//div[@class='product-info-main'])//a)[1]");
	        Common.clickElement("xpath", "((//div[@class='product-info-main'])//a)[1]");
	        Thread.sleep(5000);
	        int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();
	        Common.assertionCheckwithReport(message>0, "To verify the product added to My Wishlist", "Should add product to  My wishlist page","Product sucessfully added to My wishlist", "faield to add product to Wishlist");
	    }
	    catch(Exception |Error e) {
	        ExtenantReportUtils.addFailedLog("To verify the  the PDP Page with out of stock","Should land on ou of stock PDP page", "user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on out of stock PDP page"));           
	        Assert.fail();   
	        }
	   
	}
public void remove_from_wishlist() throws Exception {
	
	Sync.waitElementPresent("xpath", "(//a[text()='here'])");
	Common.clickElement("xpath", "(//a[text()='here'])");
	Sync.waitPageLoad();
	Thread.sleep(5000);
	Sync.waitElementPresent("xpath", "(//span[text()='Remove item'])");
	Common.clickElement("xpath", "(//span[text()='Remove item'])");
	int message=Common.findElements("xpath", "(//div[@class='message info empty'])").size();
    
	
}
public void My_Orders(){

    try {
    	Common.clickElement("xpath", "(//a[text()='My Orders'])");
    	Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Thread.sleep(5000);
		Common.assertionCheckwithReport(Title.equals("My Orders | Drybar"),"Verifying My Orders page","it shoud navigate to My Orders page", "successfully  navigated to My Orders Page", "My Orders");	
		Thread.sleep(3000);
       
        }catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the My orders page","Should land on my orders  page", "user unable to land on my orders page", Common.getscreenShotPathforReport("failed to land on my orders page"));			
		Assert.fail();	
		}
	}
public void Wishlist(){

    try {
    	Common.clickElement("xpath", "(//a[text()='Wish List'])");
    	Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Thread.sleep(5000);
		Common.assertionCheckwithReport(Title.equals("My Wish List | Drybar"),"Verifying My Wishlist page","it shoud navigate to My Wishlist page", "successfully  navigated to My Wishlist Page", "My Wishlist");	
		Thread.sleep(5000);
    
    }catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the My wishlist page","Should land on my wishlist  page", "user unable to land on my wishlist page", Common.getscreenShotPathforReport("failed to land on my wishlist page"));			
		Assert.fail();	
		}
	}
public void AddressBook(){

    try {
    	Common.clickElement("xpath", "(//a[text()='Address Book'])");
    	Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Thread.sleep(5000);
		Common.assertionCheckwithReport(Title.equals("Address Book | Drybar"),"Verifying AddressBook page","it shoud navigate to AddressBook page", "successfully  navigated to Address Book Page", "AddressBook");	
		Thread.sleep(5000);
    }catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the AddressBook page","Should land on AddressBook  page", "user unable to land on AddressBook page", Common.getscreenShotPathforReport("failed to land on AddressBook page"));			
		Assert.fail();	
		}
	}

public void AccountInformation(){

    try {
    	Common.clickElement("xpath", "(//a[text()='Account Information'])");
    	Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Thread.sleep(5000);
		Common.assertionCheckwithReport(Title.equals("Account Information | Drybar"),"Verifying Account information page","it shoud navigate to Account informaion page", "successfully  navigated to Account information Page", "Account information");
		Thread.sleep(5000);
    }catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Account Information page","Should land on Account Information  page", "user unable to land on Account Information page", Common.getscreenShotPathforReport("failed to land on Account Information page"));			
		Assert.fail();	
		}
	}
public void change_Email(String dataSet) throws Exception {
	try {
		Sync.waitElementPresent("id", "change-email");
		Common.clickElement("id", "change-email");
		Sync.waitElementPresent("id", "email");
		Common.textBoxInput("id", "email",data.get(dataSet).get("Email"));
		Sync.waitElementPresent("id", "current-password");
		Common.textBoxInput("id", "current-password",data.get(dataSet).get("Password"));
		ExtenantReportUtils.addPassLog("To verify Whether the New Email  is Entered or not", "Should Enter the New Email in the TextField", "user successfully Entered the new Email in Textfield", Common.getscreenShotPathforReport("Successfully Entered New Email"));

		//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify whether the New Email is Entered or not ","Should enter the new email in textfield", "user unable to Enter New Email", Common.getscreenShotPathforReport("failed to enter new Email"));			
			Assert.fail();	
			}
		
try {
	Common.clickElement("xpath", "(//button[@title='Save'])");
	int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();

	 Common.assertionCheckwithReport(message>0, "To change the Email Address", "Should  change Email Address","Successfully changed the Email Address", "faield to change Email address");
}catch(Exception |Error e) {
	ExtenantReportUtils.addFailedLog("To change the Email Address","Should Change Email Address", "user unable change Email Address", Common.getscreenShotPathforReport("failed to change Email"));			
	Assert.fail();	
	}
}

public void change_Password(String dataSet) throws Exception {
	try {
		Sync.waitElementPresent("id", "change-password");
		Common.clickElement("id", "change-password");
		Sync.waitElementPresent("xpath", "(//input[@id='current-password'])");
		Common.textBoxInput("xpath", "(//input[@id='current-password'])",data.get(dataSet).get("Password"));
		Sync.waitElementPresent("xpath", "(//input[@id='password'])");
		Common.textBoxInput("xpath", "(//input[@id='password'])",data.get(dataSet).get("Password"));
		Sync.waitElementPresent("xpath", "(//input[@id='password-confirmation'])");
		Common.textBoxInput("xpath", "(//input[@id='password-confirmation'])",data.get(dataSet).get("Password"));
		ExtenantReportUtils.addPassLog("To verify Whether the New Password  is Entered or not", "Should Enter the New password in the TextField", "user successfully Entered the new password in Textfield", Common.getscreenShotPathforReport("Successfully Entered New Password"));

		//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify whether the New Password is Entered or not ","Should enter the new password in textfield", "user unable to Enter New Password", Common.getscreenShotPathforReport("failed to enter new password"));			
			Assert.fail();	
			}
		
try {
	Common.clickElement("xpath", "(//button[@title='Save'])");
	int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();

	 Common.assertionCheckwithReport(message>0, "To change the password of the user", "Should  change the password od the user","Successfully changed the Password", "faield to change the password");
}catch(Exception |Error e) {
	ExtenantReportUtils.addFailedLog("To change the password of the user","Should Change password of the user", "user unable change password", Common.getscreenShotPathforReport("failed to change the password"));			
	Assert.fail();	
	}
}
	
public void Add_NewAddress(String dataSet) throws Exception {
	
	try {
		Sync.waitPageLoad();
	Common.scrollIntoView("xpath","(//button[@title='Add New Address'])");
	Common.clickElement("xpath","(//button[@title='Add New Address'])");
	
	Sync.waitElementPresent("xpath", "//input[@name='firstname']");
	Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
	
	Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
	
	/*Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));*/
	
	Sync.waitElementPresent("xpath", "//input[@name='company']");
	Common.textBoxInput("xpath", "//input[@name='company']", data.get(dataSet).get("CompanyName"));
	
	Sync.waitElementPresent("xpath", "//input[@name='telephone']");
	Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
	
	

	Sync.waitElementPresent("xpath", "//input[@name='street[]'][1]");
	Common.textBoxInput("xpath", "//input[@name='street[]'][1]", data.get(dataSet).get("Street"));
	
/*	
	Sync.waitElementPresent("xpath", "//select[@name='country_id']");
	Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));*/
	
	
	Sync.waitElementPresent("xpath", "//select[@name='region_id']");
	Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));
	
	Sync.waitElementPresent("xpath", "//input[@name='city']");
	Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
	
	Sync.waitElementPresent("xpath", "//input[@name='postcode']");
	Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
	
	ExtenantReportUtils.addPassLog("To verify Whether the New addres Entered or not", "Should Enter the New Address in the TextField", "user successfully Entered the new Address in Textfield", Common.getscreenShotPathforReport("Successfully Entered New Address"));

	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify whether the New Address is Entered or not ","Should enter the new Address in textfield", "user unable to Enter New Address", Common.getscreenShotPathforReport("failed to enter new Address"));			
		Assert.fail();	
		}
	
try {
Common.clickElement("xpath", "(//button[@title='Save Address'])");
int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();

 Common.assertionCheckwithReport(message>0, "To add the new address for the user", "Should add the address for the user","Successfully added the new Address", "faield to add New Address");
}catch(Exception |Error e) {
ExtenantReportUtils.addFailedLog("To add the new Address for the user","Should add the new Addrss for the user", "user unable to Add new Address", Common.getscreenShotPathforReport("failed to add New Address"));			
Assert.fail();	
}
}
	
public void Select_TravelSize_Category() {
	try {
		Sync.waitElementPresent("xpath", "//span[text()='Hair Products']");
		Common.mouseOver("xpath", "//span[text()='Hair Products']");
		ExtenantReportUtils.addPassLog("Verifying Hair products Sub-categories in Homepage",
				"Should display Hair products sub-categories under Hair products",
				"User Successfully displayed Hair products sub-categories under Hair products",
				Common.getscreenShotPathforReport(
						"faield  to display Hair products sub-categories under Hair products"));
		Thread.sleep(4000);
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("Verifying Hair products Sub-categories in Homepage",
				"Should display Hair products sub-categories under Hair products",
				"user faield to display Hair products sub-categories under Hair products",
				Common.getscreenShotPathforReport("display Hair products sub-categories under Hair products"));
		Assert.fail();
	}

	try {
		Sync.waitElementPresent("xpath", "(//a[@alt='Travel Size'])[1]");
		Common.mouseOverClick("xpath", "(//a[@alt='Travel Size'])[1]");
		Sync.waitPageLoad();
		Thread.sleep(5000);
		Close_popup();
		String Page = Common.findElement("xpath", "(//Strong[contains(text(),'Travel Products')])").getText();
		Common.assertionCheckwithReport(Page.equals("Travel Products"), "verifying Travel Products category",
				"User Should navigate to Travel Products page", "user successfully landed on Travel Products page",
				"user faield to click Travel Size Cateory");
	}

	catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Travel Products category",
				"User Should navigate to Travel Products page", "user faield to land on Travel Products page",
				Common.getscreenShotPathforReport("Travel Products"));
		Assert.fail();
	}
}

public void Click_HowTo_and_Inspo() {
	try {

		Sync.waitElementPresent("xpath", "(//span[text()='How To & Inspo'])");
		Common.clickElement("xpath", "(//span[text()='How To & Inspo'])");
		Thread.sleep(4000);
		Common.assertionCheckwithReport(Common.getPageTitle().equals("How To & Inspo | Drybar"),
				"verifying How To & Inspo category", "User navigate to How To & Inspo page",
				"user successfully open the How To & Inspo Category page",
				"user faield to click the How To & Inspo");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying How To & Inspo category",
				"User navigate to How To & Inspo page", "user faield to click the How To & Inspo Category",
				Common.getscreenShotPathforReport("How To & Inspo"));
		Assert.fail();
	}

}

public void Click_Videos() {
	try {
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "(//div[@class='category-item-title'])[6]");
		Common.clickElement("xpath", "(//div[@class='category-item-title'])[6]");
		Sync.waitPageLoad();
		String Content = Common.findElement("xpath", "(//div[@class='videos-category-list'])").getAttribute("id");
		Common.assertionCheckwithReport(Content.equals("video_category_content"), "verifying Videos Page",
				"User navigate to Videos Page", "user successfully open the Videos Page",
				"user faield to click the Videos page");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Videos Page", "User navigate to Videos Page",
				"user faield to click the Videos Category", Common.getscreenShotPathforReport("Videos Page"));
		Assert.fail();
	}

}

public void Select_ProductVideos_SubCategory() {
	try {

		Sync.waitElementPresent("xpath", "(//a[contains(text(),'Product Videos')])");
		Common.clickElement("xpath", "(//a[contains(text(),'Product Videos')])");
		Sync.waitPageLoad();
		String PageTitle = Common.findElement("xpath", "(//span[@class='base'])").getText();
		Common.assertionCheckwithReport(PageTitle.equals("PRODUCT VIDEOS"), "verifying Product Videos  Page",
				"User Should navigate to Product  Videos Page", "user successfully landed on Product Videos Page",
				"user faield to click Product Videos Sub-category");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Product Videos Page",
				"User Should navigate to Product Videos Page",
				"user faield to click the Product Videos Sub-category",
				Common.getscreenShotPathforReport("Product Videos Sub-category Page"));
		Assert.fail();
	}

}

public void Select_DIY_SeriesVideos_SubCategory() {
	try {

		Sync.waitElementPresent("xpath", "(//a[contains(text(),'The DIY Series')])[2]");
		Common.clickElement("xpath", "(//a[contains(text(),'The DIY Series')])[2]");
		Sync.waitPageLoad();
		String PageTitle = Common.findElement("xpath", "(//span[@class='base'])").getText();
		Common.assertionCheckwithReport(PageTitle.equals("THE DIY SERIES"), "verifying The DIY Series Videos Page",
				"User Should navigate to The DIY Series Videos Page",
				"user successfully landed on The DIY Series Videos Page",
				"user faield to click The DIY Series Videos Sub-category");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying The DIY Series Videos Page",
				"User Should navigate to The DIY Series Videos Page",
				"user faield to click the The DIY Series Videos Sub-category",
				Common.getscreenShotPathforReport("The DIY Series videos Sub-category Page"));
		Assert.fail();
	}

}

public void Select_Drybar_Signature_Styles_Videos_SubCategory() {
	try {

		Sync.waitElementPresent("xpath", "(//a[contains(text(),'Drybar Signature Styles')])");
		Common.clickElement("xpath", "(//a[contains(text(),'Drybar Signature Styles')])");
		Sync.waitPageLoad();
		String PageTitle = Common.findElement("xpath", "(//span[@class='base'])").getText();
		Common.assertionCheckwithReport(PageTitle.equals("DRYBAR SIGNATURE STYLES"),
				"verifying Drybar Signature Styles Videos Page",
				"User Should navigate to Drybar Signature Styles Videos Page",
				"user successfully landed on  Drybar Signature Styles Videos Page",
				"user faield to click Drybar Signature Styles Videos Sub-category");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Drybar Signature Styles Videos Page",
				"User Should navigate to Drybar Signature Styles Videos Page",
				"user faield to click the Drybar Signature Styles Videos Sub-category",
				Common.getscreenShotPathforReport("Drybar Signature Styles videos Sub-category Page"));
		Assert.fail();
	}

}

public void Select_DrybarFans_Videos_SubCategory() {
	try {

		Sync.waitElementPresent("xpath", "(//a[contains(text(),'Drybar Fans')])");
		Common.clickElement("xpath", "(//a[contains(text(),'Drybar Fans')])");
		Sync.waitPageLoad();
		String PageTitle = Common.findElement("xpath", "(//span[@class='base'])").getText();
		Common.assertionCheckwithReport(PageTitle.equals("DRYBAR FANS"), "verifying Drybar Fans Videos Page",
				"User Should navigate to Drybar Fans Videos Page",
				"user successfully landed on  Drybar Fans Videos Page",
				"user faield to click Drybar Fans Videos Sub-category");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Drybar Fans Videos Page",
				"User Should navigate to Drybar Fans Videos Page",
				"user faield to click the Drybar Fans Videos Sub-category",
				Common.getscreenShotPathforReport("Drybar Fans videos Sub-category Page"));
		Assert.fail();
	}

}

public void Select_OurFavorite_Videos_SubCategory() {
	try {

		Sync.waitElementPresent("xpath", "(//a[contains(text(),'Our Favorites')])");
		Common.clickElement("xpath", "(//a[contains(text(),'Our Favorites')])");
		Sync.waitPageLoad();
		String PageTitle = Common.findElement("xpath", "(//span[@class='base'])").getText();
		Common.assertionCheckwithReport(PageTitle.equals("OUR FAVORITES"), "verifying Our Favorites Videos Page",
				"User Should navigate t0 Our Favorites Videos Page",
				"user successfully landed on Our Favorites Videos Page",
				"user faield to click Our Favorites Videos Sub-category");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Our Favorites Videos Page",
				"User Should navigate to Our Favorites Videos Page",
				"user faield to click the Our Favorites Videos Sub-category",
				Common.getscreenShotPathforReport("Our Favorites videos Sub-category Page"));
		Assert.fail();
	}

}

public void Select_a_Product_Video() {
	try {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementPresent("xpath", "(//a[contains(text(),'Hair Brush')])");
		Common.clickElement("xpath", "(//a[contains(text(),'Hair Brush')])");
		Sync.waitPageLoad();
		String VideoThumb = Common.findElement("xpath", "(//img[@alt='Which Hair Brush Should You Use?'])[1]")
				.getAttribute("class");
		Common.assertionCheckwithReport(VideoThumb.equals("image-desktop"),
				"verifying Which Hair Brush Should You Use? Video Page",
				"User should navigate to Which Hair Brush Should You Use? Video Page",
				"user successfully open the Which Hair Brush Should You Use? Video Page",
				"user faield to click Which Hair Brush Should You Use? Video");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Which Hair Brush Should You Use? Video Page",
				"User should navigate to Which Hair Brush Should You Use? Video Page",
				"user faield to click the Which Hair Brush Should You Use? Video",
				Common.getscreenShotPathforReport("Which Hair Brush Should You Use? Video"));
		Assert.fail();
	}

}

public void Select_DIY_Series_Video() {
	try {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementPresent("xpath", "(//a[contains(text(),'Special Occasion Hairstyle: Textured Pony')])");
		Common.clickElement("xpath", "(//a[contains(text(),'Special Occasion Hairstyle: Textured Pony')])");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		String VideoThumb = Common
				.findElement("xpath", "(//img[@alt='Special Occasion Hairstyle: Textured Pony'])[1]")
				.getAttribute("class");
		Common.assertionCheckwithReport(VideoThumb.equals("image-desktop"),
				"verifying Special Occasion Hairstyle: Textured Pony Video Page",
				"User should navigate to Special Occasion Hairstyle: Textured Pony page",
				"user successfully open the Special Occasion Hairstyle: Textured Pony Video Page",
				"Special Occasion Hairstyle: Textured Pony Video");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Special Occasion Hairstyle: Textured Pony Video Page",
				"User Should navigate to Special Occasion Hairstyle: Textured Pony Video Page",
				"user faield to click the Special Occasion Hairstyle: Textured Pony Video",
				Common.getscreenShotPathforReport("Special Occasion Hairstyle: Textured Pony Video"));
		Assert.fail();
	}

}

public void Select_Drybar_Signatue_Style_Video() {
	try {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(4000);
		Common.scrollIntoView("xpath", "(//a[contains(text(),'Drybar Signature Styles From Home: The Old Fashioned')])");
		Sync.waitElementPresent("xpath","(//a[contains(text(),'Drybar Signature Styles From Home: The Old Fashioned')])");
		Common.clickElement("xpath", "(//a[contains(text(),'Drybar Signature Styles From Home: The Old Fashioned')])");
		Sync.waitPageLoad();
		String VideoThumb = Common
				.findElement("xpath", "(//img[@alt='Drybar Signature Styles From Home: The Old Fashioned'])[1]")
				.getAttribute("class");
		Common.assertionCheckwithReport(VideoThumb.equals("image-desktop"),
				"verifying Drybar Signature Styles From Home: The Old Fashioned Video Page",
				"User should navigate to Drybar Signature Styles From Home: The Old Fashioned Video page",
				"user successfully open the Drybar Signature Styles From Home: The Old Fashioned Video Page",
				"Drybar Signature Styles From Home: The Old Fashioned Video");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Drybar Signature Styles From Home: The Old Fashioned Video Page",
				"User Should navigate to Drybar Signature Styles From Home: The Old Fashioned Video Page",
				"user faield to click the Drybar Signature Styles From Home: The Old Fashioned Video",
				Common.getscreenShotPathforReport("Drybar Signature Styles From Home: The Old Fashioned Video"));
		Assert.fail();
	}

}

public void Select_Drybar_Fans_Video() {
	try {
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Sync.waitElementPresent("xpath",
				"(//a[contains(text(),'National Hair Month Video: Textured to Smooth & Shiny')])");
		Common.clickElement("xpath",
				"(//a[contains(text(),'National Hair Month Video: Textured to Smooth & Shiny')])");
		Sync.waitPageLoad();
		String VideoThumb = Common
				.findElement("xpath", "(//img[@alt='National Hair Month Video: Textured to Smooth & Shiny'])[1]")
				.getAttribute("class");
		Common.assertionCheckwithReport(VideoThumb.equals("image-desktop"),
				"verifying National Hair Month Video: Textured to Smooth & Shiny Video Page",
				"User should navigate to National Hair Month Video: Textured to Smooth & Shiny Video page",
				"user successfully open the National Hair Month Video: Textured to Smooth & Shiny Video Page",
				"National Hair Month Video: Textured to Smooth & Shiny Video");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog(
				"verifying National Hair Month Video: Textured to Smooth & Shiny Video Page",
				"User Should navigate to National Hair Month Video: Textured to Smooth & Shiny Video Page",
				"user faield to click the National Hair Month Video: Textured to Smooth & Shiny Video",
				Common.getscreenShotPathforReport("National Hair Month Video: Textured to Smooth & Shiny Video"));
		Assert.fail();
	}

}

public void Select_OurFavourite_Video() {
	try {
		Sync.waitElementPresent("xpath", "(//a[contains(text(),'What Is Drybar?')])");
		Common.clickElement("xpath", "(//a[contains(text(),'What Is Drybar?')])");
		Sync.waitPageLoad();
		String VideoThumb = Common.findElement("xpath", "(//img[@alt='What Is Drybar?'])[1]").getAttribute("class");
		Common.assertionCheckwithReport(VideoThumb.equals("image-desktop"), "verifying What Is Drybar? Video Page",
				"User should navigate to What Is Drybar? Video page",
				"user successfully open the What Is Drybar?  Video Page", "What Is Drybar? Video");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying What Is Drybar? Video Page",
				"User Should navigate to What Is Drybar? Video Page",
				"user faield to click the What Is Drybar? Video",
				Common.getscreenShotPathforReport("What Is Drybar? Video"));
		Assert.fail();
	}

}

public void Play_Video() throws Exception {
	try {

		Sync.waitPageLoad();
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "(//img[@class='image-desktop'])");
		Common.clickElement("xpath", "(//img[@class='image-desktop'])");
		Sync.waitPageLoad();
		Thread.sleep(5000);
		Common.switchFrames("xpath", "(//iframe[@title='Video Content'])");
		Thread.sleep(5000);
		// Sync.waitElementPresent("xpath",
		// "(//div[@class='ytp-cued-thumbnail-overlay-image'])");
		Common.mouseOverClick("xpath", "(//button[@aria-label='Play'])");
		Thread.sleep(6000);
		ExtenantReportUtils.addPassLog("Verifying Whether Video playing or not", "Should play the Video ",
				"User Successfully Playing the Video",
				Common.getscreenShotPathforReport("faield  to play the Video"));
		Thread.sleep(4000);
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("Verifying Whether Video playing or not", "Should play the  Video ",
				"user faield to play the Video", Common.getscreenShotPathforReport("play the Video"));
		Assert.fail();
	}
	Common.switchToDefault();
	Thread.sleep(6000);
	Common.clickElement("xpath", "(//img[@class='image-desktop'])");

}

public void Select_Another_Bundle_product() {
	try {
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "(//a[contains(text(),'The Ultimate Smooth & Sleek Special Value Set')])");
		Common.clickElement("xpath", "(//a[contains(text(),'The Ultimate Smooth & Sleek Special Value Set')])");
		Thread.sleep(5000);
		Common.assertionCheckwithReport(
				Common.getPageTitle().contains("The Ultimate Smooth & Sleek Special Value Set"),
				"verifying The Ultimate Smooth & Sleek Special Value Set PDP",
				"Should land on The Ultimate Smooth & Sleek Special Value Set PDP",
				"user successfully landed The Ultimate Smooth & Sleek Special Value Set PDP",
				"user faield to click on The Ultimate Smooth & Sleek Special Value Set product");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Gifts&kits category PDP",
				"User should select The smoothingand shine special valueset product",
				"user faield to select The smoothing special valueset product",
				Common.getscreenShotPathforReport("FAILED to select  The smoothing&shine special valueset"));
		Assert.fail();
	}
}

public void Select_TravelSize_product() {
	try {
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath",
				"(//a[contains(text(),'Jump Start Quick Dry Blowout Serum Travel Size')])");
		Common.clickElement("xpath", "(//a[contains(text(),'Jump Start Quick Dry Blowout Serum Travel Size')])");
		Thread.sleep(2000);
		String Product_Size = Common.findElement("xpath", "(//span[@class='attribute-value'])[1]").getText();
		System.out.println(Product_Size);
		Common.assertionCheckwithReport(Product_Size.equals("Travel Size: 25mL/.84 fl. oz."),
				"verifying Jump Start Quick Dry Blowout Serum Travel Size PDP",
				"Should land on Jump Start Quick Dry Blowout Serum Travel Size PDP",
				"user successfully landed on Jump Start Quick Dry Blowout Serum Travel Size  PDP",
				"user faield to click on Jump Start Quick Dry Blowout Serum Travel Size product");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Jump Start Quick Dry Blowout Serum Travel Size PDP",
				"User Should land on Jump Start Quick Dry Blowout Serum Travel Size product PDP",
				"user faield to select Jump Start Quick Dry Blowout Serum Travel Size Product",
				Common.getscreenShotPathforReport(
						"FAILED to select Jump Start Quick Dry Blowout Serum Travel Size Product"));
		Assert.fail();
	}

}

public void PaymentMethods(){

    try {
    	Common.clickElement("xpath", "(//a[text()='Payment Methods'])");
    	Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Thread.sleep(5000);
		Common.assertionCheckwithReport(Title.equals("Payment Methods | Drybar"),"Verifying Payment Methods page","it shoud navigate to Payment Methods page", "successfully  navigated to Payment Methods Page", "Payment Methods");
		Thread.sleep(5000);   
    }catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Payment Methods page","Should land on Payment Methods  page", "user unable to land on Payment Methods page", Common.getscreenShotPathforReport("failed to land on Payment Methods page"));			
		Assert.fail();	
		}
	}

public void Communication_Preferences(){

    try {
		Common.clickElement("xpath","(//a[text()='Communication Preferences'])");
		Sync.waitPageLoad();
		 String Title=Common.getPageTitle();
		 Thread.sleep(5000);
		
		Common.assertionCheckwithReport(Title.equals("Communication Preferences | Drybar"),"Verifying Communication preferences page","it should navigate to communication preferences page", "successfully  navigated to Communication preferences page", "Communication preferences");		
		Thread.sleep(5000);
    }
    
    	catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Communication Preferences page","Should land on Communication Preferences  page", "user unable to land on Communication Preferences page", Common.getscreenShotPathforReport("failed to land on Communication Preferences page"));			
		Assert.fail();	
		}
	}

public void Notifications(){
    try {
        Sync.waitPageLoad();
        Thread.sleep(4000);
        Common.clickElement("xpath","(//a[text()='Notifications'])");
        Sync.waitPageLoad();
         String Title=Common.getPageTitle();
         Thread.sleep(5000);
        Common.assertionCheckwithReport(Title.equals("Notifications / My Account | Drybar"),"Verifying Notifications page","it should navigate to Notifications page", "successfully  navigated to Notifications page", "Notifications");        
         Thread.sleep(3000);
    }
    
        catch(Exception |Error e) {
        ExtenantReportUtils.addFailedLog("To verify the Notifications page","Should land on Notifications  page", "user unable to land on Notifications page", Common.getscreenShotPathforReport("failed to land on Notifications page"));            
        Assert.fail();    
        }
    }
public void Subscribe_To_Communication_preferences(){

    try {
    	Close_popup();
    	Sync.waitElementPresent("id", "subscription");
         Common.clickElement("id", "subscription");
          Thread.sleep(3000);
          Common.clickElement("xpath", "(//button[@type='submit'])[1]");
          Thread.sleep(4000);
          String message=Common.findElement("xpath", "(//div[@class='message-success success message'])").getText();
          message.equals("We have saved your subscription.");
         ExtenantReportUtils.addPassLog("To verify the Communication Preferences subscription message", "Should land on my account page with subscription", "user successfully landed on my acount page with subscription", Common.getscreenShotPathforReport("Successfully subscribed to Communication Preferences"));

	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Communication Preferences subscription message","Should land on my naccount page with subscription", "user unable subscribe to Communication Preferences page", Common.getscreenShotPathforReport("failed to subscribe to Communication Preferences"));			
		Assert.fail();	
		}
	}

public void Click_Wishlist(){

    try {
    	Sync.waitElementPresent("xpath", "(//a[text()='Wish List'])");
         Common.clickElement("xpath", "(//a[text()='Wish List'])");
          Sync.waitPageLoad();
         String Title=Common.getPageTitle();
	Common.assertionCheckwithReport(Title.equals("My Wish List | Drybar"), "To verify the My Wishlist Page", "Should land on  Wishlist page", "user successfully landed on Wishlist page", Common.getscreenShotPathforReport("Wishlist"));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the My Wishlist page","Should land on My wishlist page", "user unable to land on My Wishlist page", Common.getscreenShotPathforReport("failed to land on My Wishlist page"));			
		Assert.fail();	
		}
	}

public void Wishlist_AddtoBag() {
	try {
		Sync.waitPageLoad();
		//Common.mouseOver("xpath", "(//div[@class='product-item-info'])[1]");
		Sync.waitElementPresent("xpath", "(//span[text()='Add to Bag'])[1]");
		Common.clickElement("xpath", "(//span[text()='Add to Bag'])[1]");
		ExtenantReportUtils.addPassLog("verifying add to cart button", "User click add to card button", "user successfully click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
		
	
}catch(Exception |Error e) {
	   
		ExtenantReportUtils.addFailedLog("verifying add to cart button", "User click add to card button", "user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
		Assert.fail();
	}
	
}

public void MyAccount() {
	
	try {
			Sync.waitElementPresent("xpath", "(//span[contains(text(),'Account')])[1]");
			Common.clickElement("xpath", "(//span[contains(text(),'Account')])[1]");
			Sync.waitPageLoad();
		Sync.waitElementClickable(30, By.xpath("//ul[@class='header links']//li[1]/a"));
		Common.findElement("xpath", "//ul[@class='header links']//li[1]/a").click();
		int sizeelement=Common.findElements("xpath", "//ul[@class='header links']//li[2]//a[text()='My Account']").size();
		if(sizeelement>0){
			Common.clickElement("xpath", "//ul[@class='header links']//li[2]//a[text()='My Account']");
		}
		Thread.sleep(3000);
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying my account option", "clcik the my account button",
				"User failed to clcik the my account button",
				Common.getscreenShotPathforReport("my account button"));
		Assert.fail();

	
	}}
	

public void Signout() {
	
	try {
			
			Sync.waitPageLoad();
		Sync.waitElementClickable(30, By.xpath("//ul[@class='header links']//li[2]/a"));
		Common.findElement("xpath", "//ul[@class='header links']//li[2]/a").click();
		Thread.sleep(7000);
		Sync.waitPageLoad();
		verifyingHomePage();
		
		
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying Signout button functionality", "clcik the Signout button",
				"User failed to clcik the signout button",
				Common.getscreenShotPathforReport("signout button"));
		Assert.fail();

	
	}}
		



public HashMap<String, String> guestShipingAddress(String dataSet) throws Exception{
	HashMap<String, String> Shippingaddress = new HashMap<String, String>();
	try{
	Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
	Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",data.get(dataSet).get("Email"));

	
	Sync.waitElementPresent("xpath", "//input[@name='firstname']");
	Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
	
	Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
	
	/*Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));*/
	
	Sync.waitElementPresent("xpath", "//input[@name='company']");
	Common.textBoxInput("xpath", "//input[@name='company']", data.get(dataSet).get("CompanyName"));
	
	

	Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
	Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
	
/*	
	Sync.waitElementPresent("xpath", "//select[@name='country_id']");
	Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));*/
	
	Thread.sleep(3000);
	
	Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT,data.get(dataSet).get("Region"));
	Thread.sleep(5000);
	String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
	String Shippingstate = Common.findElement("xpath", "//select[@name='region_id']//option[@value='" + Shippingvalue + "']").getText();

	Shippingaddress.put("ShippingState", Shippingstate);
	
	

	Sync.waitElementPresent("xpath", "//input[@name='city']");
	Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
	
	Thread.sleep(2000);
	Common.textBoxInputClear("name", "postcode");
	Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
	Thread.sleep(5000);
	String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
	System.out.println("*****" + ShippingZip + "*******");
	Shippingaddress.put("ShippingZip", ShippingZip);
	Thread.sleep(5000);
	
	Sync.waitElementPresent("xpath", "//input[@name='telephone']");
	Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
	Thread.sleep(3000);
	Common.actionsKeyPress(Keys.ENTER);
	}
	catch(Exception |Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping", "faield to add new shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
		Assert.fail();
		
	}  
	System.out.println(Shippingaddress);
	return Shippingaddress;
}

public void verify_Country() {
	try {
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(1000);
		Common.clickElement("xpath", "//select[@name='country_id']");
		Thread.sleep(10000);
		ExtenantReportUtils.addPassLog("verifying Country in Shipping Address page", "Should display only Single country US ", "Successfully displayed Single country US", Common.getscreenShotPathforReport("FailedtodisplaysinglecountryUS"));
		//Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));
		Thread.sleep(5000);
		}
		catch(Exception |Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Country in Shipping Address page", "Should display only Single country US ", "faield to display Single country US",Common.getscreenShotPathforReport("FailedtodisplaysinglecountryUS"));
			Assert.fail();
			
		} }

public void verify_Billing_Country() {
	try {
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(1000);
		Common.clickElement("xpath", "//fieldset[contains(@class,'fieldset')]//select[@name='country_id']");
		Thread.sleep(10000);
		ExtenantReportUtils.addPassLog("verifying Country in Shipping Address page", "Should display only Single country US ", "Successfully displayed Single country US", Common.getscreenShotPathforReport("FailedtodisplaysinglecountryUS"));
		//Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));
		Thread.sleep(5000);
		}
		catch(Exception |Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Country in Shipping Address page", "Should display only Single country US ", "faield to display Single country US",Common.getscreenShotPathforReport("FailedtodisplaysinglecountryUS"));
			Assert.fail();
			
		} }


public void guest_Out_of_US_ShipingAddress(String dataSet) throws Exception{
	try{
	Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
	Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",data.get(dataSet).get("Email"));

	
	Sync.waitElementPresent("xpath", "//input[@name='firstname']");
	Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
	
	Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
	
	/*Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));*/
	
	Sync.waitElementPresent("xpath", "//input[@name='company']");
	Common.textBoxInput("xpath", "//input[@name='company']", data.get(dataSet).get("CompanyName"));
	
	try {
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//select[@name='country_id']");
		Sync.scrollDownToView("xpath", "//select[@name='country_id']");
		Thread.sleep(5000);
		Common.clickElement("xpath", "//select[@name='country_id']");
		Thread.sleep(5000);
		ExtenantReportUtils.addPassLog("verifying Country in Shipping Address page", "Should display only Single country US ", "Successfully displayed Single country US", Common.getscreenShotPathforReport("FailedtodisplaysinglecountryUS"));
		//Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));
		Thread.sleep(5000);
		}
		catch(Exception |Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Country in Shipping Address page", "Should display only Single country US ", "faield to display Single country US",Common.getscreenShotPathforReport("FailedtodisplaysinglecountryUS"));
			Assert.fail();
			
		} 

	Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
	Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
	Thread.sleep(3000);
	Sync.waitElementPresent("xpath", "//select[@name='country_id']");
	Common.clickElement("xpath", "//select[@name='country_id']");
	Thread.sleep(3000);
	Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));
	
	Sync.waitElementPresent("xpath", "//select[@name='region_id']");
	Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));
	
	Sync.waitElementPresent("xpath", "//input[@name='city']");
	Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
	
	Sync.waitElementPresent("xpath", "//input[@name='postcode']");
	Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
	
	Sync.waitElementPresent("xpath", "//input[@name='telephone']");
	Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
	}
	catch(Exception |Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying shipping addres filling with out of US", "user will fill the all the shipping with out of US", "faield to add Out of US shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
		Assert.fail();
		
	}  
}


	
public void Expediated_shippingmethod(){

    try {
    	Thread.sleep(3000);
    	Common.actionsKeyPress(Keys.END);
    	Thread.sleep(3000);
    	Sync.waitElementPresent("xpath", "(//td[text()='Expedited'])");
    	Thread.sleep(5000);
    	Common.scrollIntoView("xpath", "(//td[text()='Expedited'])");
    	Thread.sleep(5000);
         Common.clickElement("xpath", "(//td[text()='Expedited'])");
          Thread.sleep(3000);
          ExtenantReportUtils.addPassLog("To verify the Expedited shipping method", "Should click on expediated shipping method", "user successfully clicked on expediated shipping method", Common.getscreenShotPathforReport("Successfully clicked on Expediated shipping method"));
      	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
    }catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("To verify the Expediated shipping method","Should click on Expediated shipping method", "user unable to click on Expediated shipping method", Common.getscreenShotPathforReport("failed to click on expediated shipping method"));			
    		Assert.fail();	
    		}
}
public void click_Next() {
    try {
    	Thread.sleep(4000);
    	Common.actionsKeyPress(Keys.END);
    	Thread.sleep(2000);

    	   //click_Next();

    	   Sync.waitElementPresent("xpath", "(//span[contains(text(),'Next')])[2]");

    	Common.mouseOver("xpath","(//span[contains(text(),'Next')])[2]");
    	Thread.sleep(5000);
    	Common.clickElement("xpath", "(//span[contains(text(),'Next')])[2]");

    	       //div[contains(@class,'error')]

    	Sync.waitPageLoad();
          //String message=Common.findElement("xpath", "(//div[@class='message-success success message'])").getText();
          //message.equals("We have saved your subscription.");
         ExtenantReportUtils.addPassLog("To verify the payment page", "Should land on payment page", "user successfully landed on payment page", Common.getscreenShotPathforReport("Successfully land on payment page"));
Thread.sleep(6000);
	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the payment page","Should land on payment page", "user unableto land on payment page", Common.getscreenShotPathforReport("failed to land on payment page"));			
		Assert.fail();	
		}
	}


public void Taxcalucaltion(String taxpercent) throws Exception{
	try{
	
		
	
	Thread.sleep(5000);
	
	//String taxpercent=data.get(dataset).get("Tax");
	 Float giventaxvalue=Float.valueOf(taxpercent);
	
	String subtotal=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
	 // subtotla.replace("", newChar)
	Float subtotalValue=Float.valueOf(subtotal);
	
	String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
	Float shippingammountvalue=Float.valueOf(shippingammount);
	
	String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']/span").replace("$", "");
	Float Taxammountvalue=Float.valueOf(TaxAmmount);
	
	String TotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
	Float Totalammountvalue=Float.valueOf(TotalAmmount);
	 Float Total=(subtotalValue+shippingammountvalue);
	
    Float calucaltedvalue= (Total*giventaxvalue)/100;
   // System.out.println(subtotalValue);
   // System.out.println(Total);
   // System.out.println(giventaxvalue);
   NumberFormat nf= NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    String  userpaneltaxvalue=nf.format(calucaltedvalue);
  
    System.out.println(TaxAmmount);
    System.out.println(userpaneltaxvalue);      
    Common.assertionCheckwithReport(userpaneltaxvalue.equals(TaxAmmount), "verifying tax calculation", "tax rate is matches to given shipping address tax ","successfully tax rate is matches to given shipping address tax", "tax rate is not matches to given shipping address tax");
	
	}
 catch(Exception |Error e)
	{
		report.addFailedLog("verifying tax calculation", "getting price values from shipping page  ", "Faield to get price value from shipping page", Common.getscreenShotPathforReport("TaxRates"));

		e.printStackTrace();
		Assert.fail();
		
}
	
	
	
	}






public void select_CC(){
	try{
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "(//input[@type='radio'])[5]");
		Common.mouseOverClick("xpath", "(//input[@type='radio'])[5]");
		
	}catch(Exception |Error e) {
		//ExtenantReportUtils.addFailedLog("To verify the payment page","Should land on payment page", "user unableto land on payment page", Common.getscreenShotPathforReport("failed to land on payment page"));			
		Assert.fail();	
		}
	}


public void Express_shippingmethod(){

    try {
    	Thread.sleep(3000);
    	Common.actionsKeyPress(Keys.END);
    	Thread.sleep(3000);
    	Sync.waitElementPresent("xpath", "(//td[text()='Express'])");
    	Thread.sleep(3000);
    	Common.scrollIntoView("xpath", "(//td[text()='Express'])");
    	Thread.sleep(5000);
         Common.clickElement("xpath", "(//td[text()='Express'])");
          Thread.sleep(3000);
          ExtenantReportUtils.addPassLog("To verify the Express shipping method", "Should click on express shipping method", "user successfully clicked on Express shipping method", Common.getscreenShotPathforReport("Successfully clicked on Express shipping method"));
      	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
    }catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("To verify the Express shipping method","Should click on Express shipping method", "user unable to click on Express shipping method", Common.getscreenShotPathforReport("failed to click on express shipping method"));			
    		Assert.fail();	
    		}
}
  
public void Product_Review(String dataSet) throws Exception {

	try {
		Thread.sleep(3000);

		Sync.waitElementPresent("xpath", "(//button[@class='TTteaser__write-review'])");
		// Common.scrollIntoView("xpath", "(");
		Common.clickElement("xpath", "(//button[@class='TTteaser__write-review'])");

		Thread.sleep(5000);
		Common.clickElement("xpath", "(//span[contains(text(),'Select to rate 5 stars')])");

		Common.textBoxInput("id", "tt-review-form-text", data.get(dataSet).get("Review"));
		Common.textBoxInput("id", "tt-review-form-title", data.get(dataSet).get("Reviewtitle"));
		Common.clickElement("xpath", "(//span[contains(text(),'Select to rate 5 out of 5')])[1]");
		Common.clickElement("xpath", "(//span[contains(text(),'Select to rate 5 out of 5')])[2]");
		Thread.sleep(5000);
		Common.clickElement("xpath", "(//label[text()='No'])");
		Common.clickElement("xpath", "(//label[contains(text(),'Thick/Coarse')])");
		Common.dropdown("xpath", "(//div[@class='tt-o-selectbox'])//select", Common.SelectBy.TEXT,data.get(dataSet).get("Hairtype"));
		Sync.waitPageLoad();
		Common.clickElement("xpath", "(//label[contains(text(),'Thin')])");
		Common.clickElement("xpath", "(//label[contains(text(),'Long')])");
		Common.clickElement("xpath", "(//label[contains(text(),'Texture')])");
		Common.clickElement("xpath", "(//label[contains(text(),'Hot')])");

		ExtenantReportUtils.addPassLog("To verify the Product Review details", "Should enter the review details ",
				"user successfully entered the product review details ",
				Common.getscreenShotPathforReport("Successfully Entered product review detailis"));
		// Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the
		// PDP Page", "Should land on PDP page", "user successfully landed on PDP page",
		// Common.getscreenShotPathforReport(""));
	} catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Product review details", "Should ener the review details",
				"user unable to enter product review details",
				Common.getscreenShotPathforReport("failed to enter product review details"));
		Assert.fail();
	}
	try {
		Common.scrollIntoView("xpath", "(//button[text()='Submit'])");
		Thread.sleep(5000);
		Common.clickElement("xpath", "(//button[text()='Submit'])");
		// Thread.sleep(4000);
		// String message=Common.findElement("xpath", "(//div[@class='message-success
		// success message'])").getText();
		// message.equals("We have saved your subscription.");
		// ExtenantReportUtils.addPassLog("To verify the payment page", "Should land on
		// payment page", "user successfully landed on payment page",
		// Common.getscreenShotPathforReport("Successfully land on payment page"));
		// Thread.sleep(6000);
		// Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the
		// PDP Page", "Should land on PDP page", "user successfully landed on PDP page",
		// Common.getscreenShotPathforReport(""));
	} catch (Exception | Error e) {
		// ExtenantReportUtils.addFailedLog("To verify the payment page","Should land on
		// payment page", "user unableto land on payment page",
		// Common.getscreenShotPathforReport("failed to land on payment page"));
		Assert.fail();
	}
}

public void profile(String dataset) {
	try{
		Thread.sleep(5000);
		Common.textBoxInput("xpath", "(//input[@name='firstName'])",data.get(dataset).get("FirstName"));
	    Common.textBoxInput("xpath", "(//input[@name='lastName'])",data.get(dataset).get("LastName"));
		//Common.clickElement("xpath", "//input[@id='is_subscribed']");
		//Common.clickElement("xpath", "//input[@id='assistance_allowed_checkbox']");
		Common.textBoxInput("xpath","(//input[@name='email'])[2]",data.get(dataset).get("Email"));
		
		Common.scrollIntoView("xpath", "(//button[@class='tt-o-button tt-o-button--primary tt-c-auth__email-submit tt-c-auth__email-submit'])");
 	   Thread.sleep(5000);
       Common.clickElement("xpath", "(//button[@class='tt-o-button tt-o-button--primary tt-c-auth__email-submit tt-c-auth__email-submit'])");
		
		ExtenantReportUtils.addPassLog("verifying product review funcionality", " A confirmation message should sent to customer", "Successfully got a confirmation message", Common.getscreenShotPathforReport("Confirmation message"));
		
		}
		 catch(Exception |Error e) {
		        ExtenantReportUtils.addFailedLog("verifying product review functionality", "A confirmation message should present", "user faield to get confirmation message", Common.getscreenShotPathforReport("confirmation messge faield"));
				Assert.fail();
			}
		
	}

public void tax_calculation(String dataset) {
	   
    try {
        Thread.sleep(5000);
        String Taxrate=data.get(dataset).get("Tax");
       
         Float giventaxvalue=Float.valueOf(Taxrate);
       
        String subtotal=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
         // subtotla.replace("", newChar)
        Float subtotalValue=Float.valueOf(subtotal);
       
        String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
        Float shippingammountvalue=Float.valueOf(shippingammount);
       
        String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']/span").replace("$", "");
        Float Taxammountvalue=Float.valueOf(TaxAmmount);
       
        String TotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
        Float Totalammountvalue=Float.valueOf(TotalAmmount);
         Float Total=(subtotalValue+shippingammountvalue);
       
        Float calucaltedvalue= (Total*giventaxvalue)/100;
       // System.out.println(subtotalValue);
       // System.out.println(Total);
       // System.out.println(giventaxvalue);
       NumberFormat nf= NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        String  userpaneltaxvalue=nf.format(calucaltedvalue);
     
        System.out.println(TaxAmmount);
        System.out.println(userpaneltaxvalue);     
        Common.assertionCheckwithReport(userpaneltaxvalue.equals(TaxAmmount), "verifying tax calculation", "tax rate is matches to given shipping address tax ","successfully tax rate is matches to given shipping address tax", "tax rate is not matches to given shipping address tax");
       
       
       
        }catch(Exception |Error e) {
        
            ExtenantReportUtils.addFailedLog("verifying the loginpage","navigate the loginpage", "user successfully navigate the loginpage", Common.getscreenShotPathforReport("failedtologinpage"));
            Assert.fail();
        }
   
   
   
   
}
public void verifyingMagentoLoginPage(){//td[@class='amount'])[1]
	try{
		Sync.waitElementPresent("xpath", "//a[@class='logo']");
	String HomepageTitle=Common.getPageTitle();
	Common.assertionCheckwithReport(HomepageTitle.equals("Magento Admin | Drybar"), "verifying the loginpage", "navigate the loginpage", "user successfully navigate the loginpage", "Failed to navigate to loginpage");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying the loginpage","navigate the loginpage", "user successfully navigate the loginpage", Common.getscreenShotPathforReport("failedtologinpage"));
			Assert.fail();
		}
}
public void Admin_Login(String dataset) {
	
	try {
		Sync.waitElementPresent("id", "username");
		Common.textBoxInput("id","username",data.get(dataset).get("UserName"));
		Sync.waitElementPresent("id", "login");
		Common.textBoxInput("id","login",data.get(dataset).get("Password"));
		ExtenantReportUtils.addPassLog("verifying Whether login details entered or not", "Lgin details should enter successfully", "Successfully entered login details", Common.getscreenShotPathforReport("Failed to enter login details"));
		
	}
	 catch(Exception |Error e) {
	        ExtenantReportUtils.addFailedLog("verifying product review functionality", "A confirmation message should present", "user faield to get confirmation message", Common.getscreenShotPathforReport("confirmation messge faield"));
			Assert.fail();
	 }	
	try {
		Sync.waitElementPresent("xpath", "(//span[contains(text(),'Sign in')])");
		Common.clickElement("xpath", "(//span[contains(text(),'Sign in')])");
	}catch(Exception |Error e) {
		Assert.fail();
	}
}
	
public void verifyingMagentoHomepage(){
	try{
		Sync.waitElementPresent("xpath", "(//h1[@class='page-title'])");
	String HomepageTitle=Common.getPageTitle();
	Thread.sleep(2000);
	Common.assertionCheckwithReport(HomepageTitle.equals("Dashboard / Magento Admin | Drybar"), "verifying the homepage", "navigate the home page", "user successfully navigate the home page", "Failed to navigate to home page");
    Thread.sleep(2000);
	}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying the homepage","navigate the home page", "user successfully navigate the home page", Common.getscreenShotPathforReport("failedtohomepage"));
			Assert.fail();
		}
}

public void Salesmenu_navigations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "(//span[contains(text(),'Sales')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Common.clickElement("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.clickElement("xpath", "(//span[contains(text(),'Sales')])[1]");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Catalogmenu_navigations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "(//span[contains(text(),'Catalog')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Common.clickElement("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.clickElement("xpath", "(//span[contains(text(),'Catalog')])[1]");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void CMSpage() throws Exception{
	
	
	try{
		Sync.waitElementPresent("xpath", "(//span[contains(text(),'Stores')])");
		Common.clickElement("xpath", "(//span[contains(text(),'Stores')])");
		ExtenantReportUtils.addPassLog("To display stores sub-category list", "Should display stores sub-category list", "Successfully display stores sub-category list", Common.getscreenShotPathforReport("Failed to display stores sub-category list "));
      	//
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
	ExtenantReportUtils.addFailedLog("To display stores sub-category list ","Should display stores sub-category list","User unabel to display stores sub-category list ",Common.getscreenShotPathforReport("failed to display stores sub-category list"));
		Assert.fail();

}
	
	try{
		Sync.waitElementPresent("xpath", "(//span[contains(text(),'Configuration')])[18]");
		Common.clickElement("xpath", "(//span[contains(text(),'Configuration')])[18]");
		
		ExtenantReportUtils.addPassLog("To Verify configuration page", "Should land on configuration page", "Successfully landed on Confioguration page", Common.getscreenShotPathforReport("Failed to land on Configuration page"));
      	//
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
	ExtenantReportUtils.addFailedLog("To verify configuration page","Should land on configuration page","User unable to land on Configuration page",Common.getscreenShotPathforReport("failed to land onn Configuration page"));
		Assert.fail();

}
	try{
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementPresent("xpath", "(//strong[contains(text(),'General')])");
		Common.clickElement("xpath", "(//strong[contains(text(),'General')])");
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		ExtenantReportUtils.addPassLog("To verify expansion of General tab", "Should expand the General tab ", "Successfully Expanded General tab", Common.getscreenShotPathforReport("Failed to click on General tab"));
      	//
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
	ExtenantReportUtils.addFailedLog("To verify  expansion of General tab","Should expand the General tab","User unable to click the General tab",Common.getscreenShotPathforReport("failed to click on General tab"));
		Assert.fail();
}
	try{
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementPresent("xpath", "(//span[contains(text(),'Web')])");
		Common.clickElement("xpath", "(//span[contains(text(),'Web')])");
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		ExtenantReportUtils.addPassLog("To verify expansion of General tab", "Should expand the General tab ", "Successfully Expanded General tab", Common.getscreenShotPathforReport("Failed to click on General tab"));
      	//
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
	ExtenantReportUtils.addFailedLog("To verify  expansion of General tab","Should expand the General tab","User unable to click the General tab",Common.getscreenShotPathforReport("failed to click on General tab"));
		Assert.fail();
}
	try{
		Sync.waitElementPresent("xpath", "(//input[@id='web_default_cms_home_page_inherit'])");
		Common.clickElement("xpath", "(//input[@id='web_default_cms_home_page_inherit'])");
		ExtenantReportUtils.addPassLog("To verify CMS Homepage checkbox unchecked", "Should uncheck CMS Homepage", "Successfully unchecked CMS Homepage", Common.getscreenShotPathforReport("Failed to uncheck CMS Homepage"));
      	//
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
	ExtenantReportUtils.addFailedLog("To verify CMS Homepage checkbox unchecked","Should uncheck CMS Homepage","User unable to uncheck CMS Homepage",Common.getscreenShotPathforReport("failed to uncheck CMS Homepage"));
		Assert.fail();

}
	/*try{
		Thread.sleep(3000);
	
		Sync.waitElementPresent("id", "web_default_cms_home_page");
		Thread.sleep(5000);
		 Common.textBoxInput("id", "web_default_cms_home_page", data.get(dataSet).get("SelectCMS"));
         
		//Common.clickElement("xpath", "(//input[@id='web_default_cms_home_page_inherit'])");
		ExtenantReportUtils.addPassLog("To verify CMS Homepage checkbox unchecked", "Should uncheck CMS Homepage", "Successfully unchecked CMS Homepage", Common.getscreenShotPathforReport("Failed to uncheck CMS Homepage"));
      	//
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
	ExtenantReportUtils.addFailedLog("To verify CMS Homepage checkbox unchecked","Should uncheck CMS Homepage","User unable to uncheck CMS Homepage",Common.getscreenShotPathforReport("failed to uncheck CMS Homepage"));
		Assert.fail();

}*/
}
public void CMS_Block() {
	try{
		Sync.waitElementPresent("xpath", "(//span[contains(text(),'Content')])");
		Common.clickElement("xpath", "(//span[contains(text(),'Content')])");
		ExtenantReportUtils.addPassLog("To display Content sub-category list", "Should display Content sub-category list", "Successfully display Content sub-category list", Common.getscreenShotPathforReport("Failed to display Content sub-category list "));
      	//
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
	ExtenantReportUtils.addFailedLog("To display Content sub-category list ","Should display Content sub-category list","User unabel to display Content sub-category list ",Common.getscreenShotPathforReport("failed to display Content sub-category list"));
		Assert.fail();

}
	try{
		Sync.waitElementPresent("xpath", "(//span[contains(text(),'Blocks')])[1]");
		Common.clickElement("xpath", "(//span[contains(text(),'Blocks')])[1]");
		String Title=Common.findElement("xpath", "(//span[contains(text(),'Add New Block')])").getText();
		Common.assertionCheckwithReport(Title.equals("Add New Block"), "verifying the CMS Block page", "Should land on CMS Block page", "user successfully landed on CMS Block page", "Failed to land on CMS Block page");
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
	ExtenantReportUtils.addFailedLog("To verify CMS Block page","Should land on CMS Block page","User unable to land on CMS Bloack page",Common.getscreenShotPathforReport("failed to land on CMS Block page"));
		Assert.fail();

}
	
}

public void FilterOptionsPLP(){ 
    try{
        Sync.waitPageLoad();
        Sync.waitElementPresent("xpath", "(//div[contains(text(),'Benefit')])[2]");
        Sync.waitPageLoad();
        Common.clickElement("xpath", "(//span[contains(text(),'Cleanse & Condition')])[2]");
        Sync.waitPageLoad();
        //Thread.sleep(6000);
       // String Cleanse_and_condition_count=Common.findElement("xpath", "(//span[@class='count'])[4]").getText();
        Thread.sleep(4000);
        String Toolbar_number=Common.findElement("xpath", "(//span[@class='toolbar-number'])[1]").getText();
        Thread.sleep(5000);
       /* Common.actionsKeyPress(Keys.ARROW_DOWN);
        Common.actionsKeyPress(Keys.ARROW_DOWN);
        Common.actionsKeyPress(Keys.ARROW_DOWN);*/
        Common.actionsKeyPress(Keys.ARROW_DOWN);
        Common.assertionCheckwithReport(("7").equals(Toolbar_number), "verifying Cleanse and condition filter in Benefits", "Should select Cleanse & Condition", "user successfully selected Cleanse & condition filter", "Failed to select cleanse & condition filter");
    }
    
    catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying cleanse and condition filter in Benefits", "User should  select cleanse & condition filter", "user faield to Select cleanse & condition filter", Common.getscreenShotPathforReport("Cleanse & condition filter"));	
Assert.fail();	
}         
try{		
      Sync.waitPageLoad();	
     // Sync.waitElementPresent("xpath", "(//div[contains(text(),'Price')])");
      Common.clickElement("xpath", "(//div[contains(text(),'Price')])");
      Sync.waitPageLoad();
      Thread.sleep(6000);	
     Common.clickElement("xpath", "(//input[@type='radio'])[2]");
     Thread.sleep(4000);
     String Toolbar_number=Common.findElement("xpath", "(//span[@class='toolbar-number'])[1]").getText();
     Thread.sleep(5000);
     Common.actionsKeyPress(Keys.ARROW_DOWN);
     Common.actionsKeyPress(Keys.ARROW_DOWN);
     Common.actionsKeyPress(Keys.ARROW_DOWN);
     Common.actionsKeyPress(Keys.ARROW_DOWN);
     Common.assertionCheckwithReport(("3").equals(Toolbar_number), "verifying $20.00 - $29.99 Price filter", "Should select $20.00 - $29.99 Price filter", "user successfully selected $20.00 - $29.99 Price filter", "Failed to select $20.00 - $29.99 Price filter");
}
   
catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying $20.00 - $29.99 Price filter", "User should select $20.00 - $29.99 Price filter", "user faield to  Select $20.00 - $29.99 Price filter", Common.getscreenShotPathforReport("Price"));
Assert.fail();	
} 

try{		
    Sync.waitPageLoad();	
   // Sync.waitElementPresent("xpath", "(//div[contains(text(),'Price')])");
    Common.clickElement("xpath", "(//div[contains(text(),'Clean Benefit')])");
    Sync.waitPageLoad();
    Thread.sleep(6000);	
   Common.clickElement("xpath", "(//span[contains(text(),'Phthalate Free')])");
   Thread.sleep(4000);
   Common.actionsKeyPress(Keys.ARROW_UP);
   Common.actionsKeyPress(Keys.ARROW_UP);
   Common.actionsKeyPress(Keys.ARROW_UP);
   String Toolbar_number=Common.findElement("xpath", "(//span[@class='toolbar-number'])[1]").getText();
   Thread.sleep(5000);
   Common.assertionCheckwithReport(("3").equals(Toolbar_number), "verifying Phthalate Free filter in Clean benefit", "user should select Phthalate Free filter in Clean benefit", "user successfully  selected Phthalate Free filter in Clean benefit", "Failed to select Phthalate Free filter in Clean beneft");
}
 
catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying Phthalate Free filter in Clean benefit", "User should  select Phthalate Free filter in Clean benefit", "user faield to Select Phthalate Free filter in Clean benefit", Common.getscreenShotPathforReport("Phthalate Free filter in Clean benefit"));
Assert.fail();	
}   

try{		
    Sync.waitPageLoad();	
   // Sync.waitElementPresent("xpath", "(//div[contains(text(),'Price')])");
   // Common.clickElement("xpath", "(//div[contains(text(),'Clean Benefit')])");
    Sync.waitPageLoad();
    Thread.sleep(6000);	
   Common.clickElement("xpath", "(//span[contains(text(),'Honey Free')])");
   Thread.sleep(4000);
   Common.actionsKeyPress(Keys.PAGE_UP);
   Common.actionsKeyPress(Keys.PAGE_DOWN);
   Common.actionsKeyPress(Keys.ARROW_UP);
   String Toolbar_number=Common.findElement("xpath", "(//span[@class='toolbar-number'])[1]").getText();
   Thread.sleep(5000);
   Common.assertionCheckwithReport(("3").equals(Toolbar_number), "verifying Honey free filter in clean benefit", "Should select Honey free filter in clean benefit", "user successfully selected Honey free filter in clean benefit", "Failed to select Honey free filter in clean benefit");
}
 
catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying Honey free filter in clean benefit", "User should  select Honey free filter in clean benefit", "user faield to Select Honey free filter in clean benefit", Common.getscreenShotPathforReport("Honey free filter in clean benefit"));
Assert.fail();	
} 
try{		
    Sync.waitPageLoad();
    Common.actionsKeyPress(Keys.PAGE_UP);
    Common.actionsKeyPress(Keys.PAGE_UP);
   // Sync.waitElementPresent("xpath", "(//div[contains(text(),'Price')])");
    //Common.clickElement("xpath", "(//div[contains(text(),'Price')])");
    Sync.waitPageLoad();
    Thread.sleep(6000);	
   Common.clickElement("xpath", "(//input[@type='radio'])[1]");
   Thread.sleep(4000);
   String Toolbar_number=Common.findElement("xpath", "(//span[@class='toolbar-number'])[1]").getText();
   Thread.sleep(5000);
   Common.actionsKeyPress(Keys.ARROW_DOWN);
   Common.actionsKeyPress(Keys.ARROW_DOWN);
   Common.actionsKeyPress(Keys.ARROW_DOWN);
   Common.actionsKeyPress(Keys.ARROW_DOWN);
   Common.assertionCheckwithReport(("2").equals(Toolbar_number), "verifying $10.00 - $19.99 Price filter", "User should  select $10.00 - $19.99 Price filter", "user successfully  selected $10.00 - $19.99 Price filter", "Failed to select  $10.00 - $19.99 Price filter");
}
 
catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying $10.00 - $19.99 Price filter", "User should  select $10.00 - $19.99 Price filter", "user failed to select $10.00 - $19.99 Price filter", Common.getscreenShotPathforReport("Price"));
Assert.fail();	
} 

try{
    Sync.waitPageLoad();
    Sync.waitElementPresent("xpath", "(//div[contains(text(),'Benefit')])[2]");
    Sync.waitPageLoad();
    Common.clickElement("xpath", "(//span[contains(text(),'Cleanse & Condition')])[2]");
    Sync.waitPageLoad();
    //Thread.sleep(6000);
   // String Cleanse_and_condition_count=Common.findElement("xpath", "(//span[@class='count'])[4]").getText();
    Thread.sleep(4000);
    String Toolbar_number=Common.findElement("xpath", "(//span[@class='toolbar-number'])[1]").getText();
    Thread.sleep(5000);
    Common.actionsKeyPress(Keys.ARROW_DOWN);
    Common.actionsKeyPress(Keys.ARROW_DOWN);
    Common.actionsKeyPress(Keys.ARROW_DOWN);
    Common.actionsKeyPress(Keys.ARROW_DOWN);
    Common.assertionCheckwithReport(("2").equals(Toolbar_number), "verifying Cleanse & Condition filter in Benefit ", "Should unselect Cleanse & Condition filter in Benefit ", "user successfully unselected Cleanse & Condition filter in Benefit", "Failed to unselect Cleanse & Condition filter in Benefit");
}

catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying Cleanse & Condition filter in Benefit", "User should  unselect Cleanse & Condition filter in Benefit", "user faield to unselect Cleanse & Condition filter in Benefit", Common.getscreenShotPathforReport("Cleanse & Condition filter in Benefit"));	
Assert.fail();	
} 
try{		
    Sync.waitPageLoad();	
   // Sync.waitElementPresent("xpath", "(//div[contains(text(),'Price')])");
   // Common.clickElement("xpath", "(//div[contains(text(),'Clean Benefit')])");
    Sync.waitPageLoad();
    Thread.sleep(6000);	
   Common.clickElement("xpath", "(//span[contains(text(),'Phthalate Free')])");
   Thread.sleep(4000);
   Common.actionsKeyPress(Keys.ARROW_UP);
   Common.actionsKeyPress(Keys.ARROW_UP);
   Common.actionsKeyPress(Keys.ARROW_UP);
   String Toolbar_number=Common.findElement("xpath", "(//span[@class='toolbar-number'])[1]").getText();
   Thread.sleep(5000);
   Common.assertionCheckwithReport(("2").equals(Toolbar_number), "verifying Phthalate free filter in Benefit", "User should  unselect Phthalate free filter in Benefit", "user successfully unselected Phthalate free filter in Benefit", "Failed to unselect Phthalate free filter in Benefit");
}
 
catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying Phthalate free filter in Benefit", "User should  unselect Phthalate free filter in Benefit", "user faield to unselect Phthalate free filter in Benefit", Common.getscreenShotPathforReport("Phthalate free filter in Benefit"));
Assert.fail();	
}  
try{		
    Sync.waitPageLoad();
    Common.actionsKeyPress(Keys.PAGE_UP);
    Common.actionsKeyPress(Keys.PAGE_UP);
    Common.actionsKeyPress(Keys.PAGE_UP);
    Common.actionsKeyPress(Keys.PAGE_UP);
   // Sync.waitElementPresent("xpath", "(//div[contains(text(),'Price')])");
    //Common.clickElement("xpath", "(//div[contains(text(),'Price')])");
    Sync.waitPageLoad();
    Thread.sleep(6000);	
   Common.clickElement("xpath", "(//input[@type='radio'])[3]");
   Thread.sleep(4000);
   String Toolbar_number=Common.findElement("xpath", "(//span[@class='toolbar-number'])[1]").getText();
   Thread.sleep(5000);
   Common.actionsKeyPress(Keys.ARROW_DOWN);
   Common.actionsKeyPress(Keys.ARROW_DOWN);
   Common.actionsKeyPress(Keys.ARROW_DOWN);
   Common.actionsKeyPress(Keys.ARROW_DOWN);
   Common.assertionCheckwithReport(("2").equals(Toolbar_number), "verifying $30.00 - $39.99 price filter", "User should  select $30.00 - $39.99 price filter", "user successfully selected  select $30.00 - $39.99 price filter ", "Failed to select  select $30.00 - $39.99 price filter");
}
 
catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying $30.00 - $39.99 price filter", "User should  select $30.00 - $39.99 price filter", "user faield to Select  select $30.00 - $39.99 price filter", Common.getscreenShotPathforReport("Price"));
Assert.fail();	
} 
}


		

public void prepareTaxData(String fileName) {
	// TODO Auto-generated method stub

	try{
		
		
		File file=new File(System.getProperty("user.dir")+"/src/test/resources/"+fileName);
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		Row row;
		Cell cell;
		int rowcount;
		if(!(file.exists()))
		{
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("TaxDetails");
		CellStyle cs = workbook.createCellStyle();
		cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cs.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		Font f = workbook.createFont();
		f.setBold(true);
		cs.setFont(f);	 
		cs.setAlignment(HorizontalAlignment.RIGHT);
		row = sheet.createRow(0);
		cell = row.createCell(0);
		cell.setCellStyle(cs);
		cell.setCellValue("Orders Details");
		
		    
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellStyle(cs);
		cell.setCellValue("S.No");
		cell = row.createCell(1);
		cell.setCellStyle(cs);
		cell.setCellValue("UC No.");
		cell = row.createCell(2);
		cell.setCellStyle(cs);
		cell.setCellValue("Bussiness Segmet");
		cell = row.createCell(3);
		cell.setCellStyle(cs);
		cell.setCellValue("Test Phase");
		cell = row.createCell(4);
		cell.setCellStyle(cs);
		cell.setCellValue("Tester Name");
		cell = row.createCell(5);
		cell.setCellStyle(cs);
		cell.setCellValue("Web Type");
		cell = row.createCell(6);
		cell.setCellStyle(cs);
		cell.setCellValue("Website");
		
		
		cell = row.createCell(7);
		cell.setCellStyle(cs);
		cell.setCellValue("Web order Number");
		cell = row.createCell(8);
		cell.setCellStyle(cs);
		cell.setCellValue("SubTotal");
		cell = row.createCell(9);
		cell.setCellStyle(cs);
		cell.setCellValue("Shipping");
		cell = row.createCell(10);
		cell.setCellStyle(cs);
		cell.setCellValue("State");
		cell = row.createCell(11);
		cell.setCellStyle(cs);
		cell.setCellValue("Zipcode");
		cell = row.createCell(12);
		cell.setCellStyle(cs);
		cell.setCellValue("Tax");
		cell=row.createCell(13);
		cell.setCellStyle(cs);
		cell.setCellValue("Order Total");
		cell=row.createCell(14);
		cell.setCellStyle(cs);
		cell.setCellValue("Tax on Shiping (Y/N)");
		cell=row.createCell(15);
		cell.setCellStyle(cs);
		cell.setCellValue("Web Configured Tax Rate");
		cell=row.createCell(16);
		cell.setCellStyle(cs);
		cell.setCellValue("Expected TaxAmount");
		cell=row.createCell(17);
		cell.setCellStyle(cs);
		cell.setCellValue("Expected OrderTotal Amount");
		cell=row.createCell(18);
		cell.setCellStyle(cs);
		cell.setCellValue("Actual OrderTotal Amount");
		cell=row.createCell(19);
		cell.setCellStyle(cs);
		cell.setCellValue("Digital QA Status(PASS/FAIL)");
		rowcount=2;
		}
		
		else
		{
		workbook = new XSSFWorkbook(new FileInputStream(file));
		sheet=workbook.getSheet("TaxDetails");	
		rowcount=sheet.getLastRowNum()+1;
		}
		/*row = sheet.createRow(rowcount);
		cell = row.createCell(0);*/

		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
}

public void prepareOrdersData(String fileName) {
	// TODO Auto-generated method stub

	try{
		
		
		File file=new File(System.getProperty("user.dir")+"/src/test/resources/"+fileName);
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		Row row;
		Cell cell;
		int rowcount;
		if(!(file.exists()))
		{
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("OrderDetails");
		CellStyle cs = workbook.createCellStyle();
		cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cs.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		Font f = workbook.createFont();
		f.setBold(true);
		cs.setFont(f);	 
		cs.setAlignment(HorizontalAlignment.RIGHT);
		row = sheet.createRow(0);
		cell = row.createCell(0);
		cell.setCellStyle(cs);
		cell.setCellValue("Orders Details");
		
		    
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellStyle(cs);
		cell.setCellValue("S.No");
		cell = row.createCell(1);
		cell.setCellStyle(cs);
		cell.setCellValue("Website");
		cell = row.createCell(2);
		cell.setCellStyle(cs);
		cell.setCellValue("Tester");
		cell = row.createCell(3);
		cell.setCellStyle(cs);
		cell.setCellValue("Test scenario Description");
		cell = row.createCell(4);
		cell.setCellStyle(cs);
		cell.setCellValue("Web Order Number");
		cell = row.createCell(5);
		cell.setCellStyle(cs);
		cell.setCellValue("Order Confirnmation Message");
		cell = row.createCell(6);
		cell.setCellStyle(cs);
		cell.setCellValue("Order Status Magento");
		
		
		cell = row.createCell(7);
		cell.setCellStyle(cs);
		cell.setCellValue("Subtotal");
		cell = row.createCell(8);
		cell.setCellStyle(cs);
		cell.setCellValue("Shipping");
		cell = row.createCell(9);
		cell.setCellStyle(cs);
		cell.setCellValue("State");
		cell = row.createCell(10);
		cell.setCellStyle(cs);
		cell.setCellValue("Zipcode");
		cell = row.createCell(11);
		cell.setCellStyle(cs);
		cell.setCellValue("Tax");
		cell = row.createCell(12);
		cell.setCellStyle(cs);
		cell.setCellValue("Estimated Order Total");
		cell=row.createCell(13);
		cell.setCellStyle(cs);
		cell.setCellValue("Discount");
		cell=row.createCell(14);
		cell.setCellStyle(cs);
		cell.setCellValue("Actual Order Total");
		cell=row.createCell(15);
		cell.setCellStyle(cs);
		cell.setCellValue("Payment Method");
		rowcount=2;
		}
		
		else
		{
		workbook = new XSSFWorkbook(new FileInputStream(file));
		sheet=workbook.getSheet("OrderDetails");	
		rowcount=sheet.getLastRowNum()+1;
		}
		/*row = sheet.createRow(rowcount);
		cell = row.createCell(0);*/

		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
}
		
		

		public void writeResultstoXLSx(String Website,String OrderId,String subtotlaValue,String shippingammountvalue,String state,String Zipcode,String Taxammountvalue,String ActualTotalammountvalue, String ExpectedTotalammountvalue,String giventaxvalue,String calucaltedvalue)
		{
			//String fileOut="";
		try{
			
			File file=new File(System.getProperty("user.dir")+"/src/test/resources/DryBarTaxDetails_Guest.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet;
			Row row;
			Cell cell;
			int rowcount;
			sheet = workbook.getSheet("TaxDetails");
			
			if((workbook.getSheet("TaxDetails"))==null)
			{
			sheet = workbook.createSheet("TaxDetails");
			CellStyle cs = workbook.createCellStyle();
			cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cs.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
			Font f = workbook.createFont();
			f.setBold(true);
			cs.setFont(f);	 
			cs.setAlignment(HorizontalAlignment.RIGHT);
			row = sheet.createRow(0);
			cell = row.createCell(0);
			cell.setCellStyle(cs);
			cell.setCellValue("Orders Details");
			
			row = sheet.createRow(1);
			cell = row.createCell(0);
			cell.setCellStyle(cs);
			cell.setCellValue("Web Order Number");
			cell = row.createCell(1);
			cell.setCellStyle(cs);
			cell.setCellValue("SubTotal");
			cell = row.createCell(2);
			cell.setCellStyle(cs);
			cell.setCellValue("Shipping");
			cell=row.createCell(3);
			cell.setCellStyle(cs);
			cell.setCellValue("TaxRate");
			cell=row.createCell(4);
			cell.setCellStyle(cs);
			cell.setCellValue("Web Configured TaxRate");
			cell=row.createCell(5);
			cell.setCellStyle(cs);
			cell.setCellValue("Actual TaxAmount");
			cell=row.createCell(6);
			cell.setCellStyle(cs);
			cell.setCellValue("Expected TaxAmount");
			
			rowcount=2;
			
			}
			
			else
			{
			
			sheet=workbook.getSheet("TaxDetails");	
			rowcount=sheet.getLastRowNum()+1;
			}
			row = sheet.createRow(rowcount);
			
			cell = row.createCell(0);
			cell.setCellType(CellType.NUMERIC);
			int SNo=rowcount-1;
			cell.setCellValue(SNo);
			cell = row.createCell(1);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue("");
			cell = row.createCell(2);
			cell.setCellType(CellType.STRING);
			cell.setCellValue("Beauty");
			cell = row.createCell(3);
			cell.setCellType(CellType.STRING);
			cell.setCellValue("User");
			cell = row.createCell(4);
			cell.setCellType(CellType.STRING);
			cell.setCellValue("Lotuswave");
			cell = row.createCell(5);
			cell.setCellType(CellType.STRING);
			cell.setCellValue("B2C");
			cell = row.createCell(6);
			cell.setCellType(CellType.STRING);
			
			String Site;
			if(Website.contains("drybar"))
		     {
				
				Site="Drybar";
				
		}
			else
			{
				Site="";
			}
			cell.setCellValue(Site);
			
			cell = row.createCell(7);
			cell.setCellValue(OrderId);
			cell = row.createCell(8);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(subtotlaValue);
			cell = row.createCell(9);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(shippingammountvalue);
			cell = row.createCell(10);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(state);
			cell = row.createCell(11);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Zipcode);
			cell = row.createCell(12);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Taxammountvalue);
			cell = row.createCell(13);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(ActualTotalammountvalue);
			cell = row.createCell(14);
			cell.setCellType(CellType.STRING);
			String TaxonShipping;
			  if((state.equals("Illinois"))||(state.equals("Florida")))
		     {
				TaxonShipping="NO";	
		}
			else
			{
				TaxonShipping="YES";
			}
			cell.setCellValue(TaxonShipping);
			cell = row.createCell(15);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(giventaxvalue);
			cell = row.createCell(16);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(calucaltedvalue);
			cell = row.createCell(17);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(ExpectedTotalammountvalue);
			cell = row.createCell(18);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(ActualTotalammountvalue);
			cell = row.createCell(19);
			cell.setCellType(CellType.STRING);
			String status;
			if(calucaltedvalue.contains(Taxammountvalue)&&(ActualTotalammountvalue).contains(ExpectedTotalammountvalue))
		     {
				
				status="PASS";
				CellStyle style = workbook.createCellStyle();
				Font font= workbook.createFont();
				font.setColor(IndexedColors.GREEN.getIndex());
				font.setBold(true);
				style.setFont(font);
				cell.setCellStyle(style);
			}
			else
			{
				status="FAIL";
				CellStyle style = workbook.createCellStyle();
				Font font= workbook.createFont();
				font.setColor(IndexedColors.RED.getIndex());
				font.setBold(true);
				style.setFont(font);
				cell.setCellStyle(style);
				}
			
			
			cell.setCellValue(status);
			
			System.out.println(OrderId);
			//String subtotla = Float.toString(subtotlaValue);
			//System.out.println("String is: "+subtotla);
			System.out.println(subtotlaValue);
			//String shippingammount = Float.toString(shippingammountvalue);
			//System.out.println("String is: "+shippingammount);
			System.out.println(shippingammountvalue);
			//String Taxammount = Float.toString(Taxammountvalue);
			//System.out.println("String is: "+Taxammount);
			System.out.println(Taxammountvalue);
			//String Totalammount = Float.toString(Totalammountvalue);
			//System.out.println("String is: "+Totalammount);
			System.out.println(ActualTotalammountvalue);
			System.out.println(ExpectedTotalammountvalue);
			//String Actualtax = Float.toString(ActualTax);
			//System.out.println("String is: "+Actualtax);
			System.out.println(giventaxvalue);
			//String userpaneltax = Float.toString(userpaneltaxvalue);
			//System.out.println("String is: "+userpaneltax);
			System.out.println(calucaltedvalue);
			
				FileOutputStream fileOut = new FileOutputStream(file);
			
			workbook.write(fileOut);
		
			fileOut.flush();
			fileOut.close();
//return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
//		return fileOut;
//		return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);

		}
		
		public void writeResultstoXLSxReg(String Website,String OrderId,String subtotlaValue,String shippingammountvalue,String state,String Zipcode,String Taxammountvalue,String ActualTotalammountvalue, String ExpectedTotalammountvalue,String giventaxvalue,String calucaltedvalue)
		{
			//String fileOut="";
		try{
			
			File file=new File(System.getProperty("user.dir")+"/src/test/resources/DryBarTaxDetails_Register.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet;
			Row row;
			Cell cell;
			int rowcount;
			sheet = workbook.getSheet("TaxDetails");
			
			if((workbook.getSheet("TaxDetails"))==null)
			{
			sheet = workbook.createSheet("TaxDetails");
			CellStyle cs = workbook.createCellStyle();
			cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cs.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
			Font f = workbook.createFont();
			f.setBold(true);
			cs.setFont(f);	 
			cs.setAlignment(HorizontalAlignment.RIGHT);
			row = sheet.createRow(0);
			cell = row.createCell(0);
			cell.setCellStyle(cs);
			cell.setCellValue("Orders Details");
			
			row = sheet.createRow(1);
			cell = row.createCell(0);
			cell.setCellStyle(cs);
			cell.setCellValue("Web Order Number");
			cell = row.createCell(1);
			cell.setCellStyle(cs);
			cell.setCellValue("SubTotal");
			cell = row.createCell(2);
			cell.setCellStyle(cs);
			cell.setCellValue("Shipping");
			cell=row.createCell(3);
			cell.setCellStyle(cs);
			cell.setCellValue("TaxRate");
			cell=row.createCell(4);
			cell.setCellStyle(cs);
			cell.setCellValue("Web Configured TaxRate");
			cell=row.createCell(5);
			cell.setCellStyle(cs);
			cell.setCellValue("Actual TaxAmount");
			cell=row.createCell(6);
			cell.setCellStyle(cs);
			cell.setCellValue("Expected TaxAmount");
			
			rowcount=2;
			
			}
			
			else
			{
			
			sheet=workbook.getSheet("TaxDetails");	
			rowcount=sheet.getLastRowNum()+1;
			}
			row = sheet.createRow(rowcount);
			
			cell = row.createCell(0);
			cell.setCellType(CellType.NUMERIC);
			int SNo=rowcount-1;
			cell.setCellValue(SNo);
			cell = row.createCell(1);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue("");
			cell = row.createCell(2);
			cell.setCellType(CellType.STRING);
			cell.setCellValue("Beauty");
			cell = row.createCell(3);
			cell.setCellType(CellType.STRING);
			cell.setCellValue("User");
			cell = row.createCell(4);
			cell.setCellType(CellType.STRING);
			cell.setCellValue("Lotuswave");
			cell = row.createCell(5);
			cell.setCellType(CellType.STRING);
			cell.setCellValue("B2C");
			cell = row.createCell(6);
			cell.setCellType(CellType.STRING);
			
			String Site;
			if(Website.contains("drybar"))
		     {
				
				Site="Drybar";
				
		}
			else
			{
				Site="";
			}
			cell.setCellValue(Site);
			
			cell = row.createCell(7);
			cell.setCellValue(OrderId);
			cell = row.createCell(8);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(subtotlaValue);
			cell = row.createCell(9);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(shippingammountvalue);
			cell = row.createCell(10);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(state);
			cell = row.createCell(11);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Zipcode);
			cell = row.createCell(12);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Taxammountvalue);
			cell = row.createCell(13);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(ActualTotalammountvalue);
			cell = row.createCell(14);
			cell.setCellType(CellType.STRING);
			String TaxonShipping;
			  if((state.equals("Illinois"))||(state.equals("Florida")))
		     {
				TaxonShipping="NO";	
		}
			else
			{
				TaxonShipping="YES";
			}
			cell.setCellValue(TaxonShipping);
			cell = row.createCell(15);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(giventaxvalue);
			cell = row.createCell(16);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(calucaltedvalue);
			cell = row.createCell(17);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(ExpectedTotalammountvalue);
			cell = row.createCell(18);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(ActualTotalammountvalue);
			cell = row.createCell(19);
			cell.setCellType(CellType.STRING);
			String status;
			if(calucaltedvalue.contains(Taxammountvalue)&&(ActualTotalammountvalue).contains(ExpectedTotalammountvalue))
		     {
				
				status="PASS";
				CellStyle style = workbook.createCellStyle();
				Font font= workbook.createFont();
				font.setColor(IndexedColors.GREEN.getIndex());
				font.setBold(true);
				style.setFont(font);
				cell.setCellStyle(style);
			}
			else
			{
				status="FAIL";
				CellStyle style = workbook.createCellStyle();
				Font font= workbook.createFont();
				font.setColor(IndexedColors.RED.getIndex());
				font.setBold(true);
				style.setFont(font);
				cell.setCellStyle(style);
				}
			
			
			cell.setCellValue(status);
			
			System.out.println(OrderId);
			//String subtotla = Float.toString(subtotlaValue);
			//System.out.println("String is: "+subtotla);
			System.out.println(subtotlaValue);
			//String shippingammount = Float.toString(shippingammountvalue);
			//System.out.println("String is: "+shippingammount);
			System.out.println(shippingammountvalue);
			//String Taxammount = Float.toString(Taxammountvalue);
			//System.out.println("String is: "+Taxammount);
			System.out.println(Taxammountvalue);
			//String Totalammount = Float.toString(Totalammountvalue);
			//System.out.println("String is: "+Totalammount);
			System.out.println(ActualTotalammountvalue);
			System.out.println(ExpectedTotalammountvalue);
			//String Actualtax = Float.toString(ActualTax);
			//System.out.println("String is: "+Actualtax);
			System.out.println(giventaxvalue);
			//String userpaneltax = Float.toString(userpaneltaxvalue);
			//System.out.println("String is: "+userpaneltax);
			System.out.println(calucaltedvalue);
			
				FileOutputStream fileOut = new FileOutputStream(file);
			
			workbook.write(fileOut);
		
			fileOut.flush();
			fileOut.close();
//return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
//		return fileOut;
//		return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);

		}		
		
		
		
		
		public void guestShippingAddress(String Street,String City,String postcode,String Region) {
			// TODO Auto-generated method stub
			String expectedResult="Should navigate to Shipping address page";
			try {
			
				Thread.sleep(4000);
				//shippingaddress1("Address", Street, City, postcode, Region);
				shippingaddress1("ShippingAddress", Street, City, postcode, Region);
				Common.scrollIntoView("xpath", "//div[@class='checkout-shipping-method']/div");
				Thread.sleep(3000);
				Common.clickElement("xpath", "//*[@id='checkout-shipping-method-load']/table/tbody/tr/td[1]/label");
				Common.clickElement("xpath", "//button[@data-role='opc-continue']/span");
				report.addPassLog(expectedResult, "Should display Shipping address Page", "Shipping address page display successfully", Common.getscreenShotPathforReport("Shipping address page success"));
			}catch(Exception |Error e)
			{
				e.printStackTrace();
				report.addFailedLog(expectedResult,"Should display  Shipping address Page", "Shipping address Page not displayed", Common.getscreenShotPathforReport("Shipping address page Failed"));
				e.printStackTrace();
				Assert.fail();
			}
		}
		
		public void shippingaddress1(String dataSet,String Street,String City,String postcode,String Region) throws InterruptedException {
			 try {
			    	Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']", data.get(dataSet).get("Email"));
			    	Thread.sleep(3000);
			        Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
			        Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
			        Common.textBoxInput("xpath", "//input[@name='street[0]']", Street);
			        Thread.sleep(3000);
			        Common.actionsKeyPress(Keys.ESCAPE);
			        Common.textBoxInput("xpath", "//input[@name='city']", City);
			        Common.textBoxInput("xpath", "//input[@name='postcode']", postcode);
			        Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, Region);
			        Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
			        Thread.sleep(3000);       
			    }catch(Exception |Error e)
			    {
//			        e.printStackTrace();
//			        Assert.fail();
			    }
		}	
		
		
		
		 @SuppressWarnings("deprecation")
		public HashMap<String,String> taxValidation(String tax,String state) {
				// TODO Auto-generated method stub
				HashMap<String,String> data=new HashMap<String,String>();
				try{			    
					Thread.sleep(3000);
				
					/*
					 * NumberFormat n_f= NumberFormat.getInstance();
					 * n_f.setMaximumFractionDigits(2); String tax_percent=n_f.format(taxpercent);
					 */
					  Float giventaxvalue=Float.valueOf(tax);
					     String giventaxvalue1=Float.toString(giventaxvalue);
					     data.put("giventaxvalue",giventaxvalue1);
               
			     String subtotla=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
			     // subtotla.replace("", newChar)
			    Float subtotlaValue=Float.valueOf(subtotla);
			    data.put("subtotlaValue",subtotla);
			    
			   // Capabilities cap = (WebDriver).getCapabilities();
			   
			  String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
			    Float shippingammountvalue=Float.valueOf(shippingammount);
				data.put("shippingammountvalue",shippingammount);
				
				
			     String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']//span").replace("$", "");
			    Float Taxammountvalue=Float.valueOf(TaxAmmount);
				data.put("Taxammountvalue",TaxAmmount);
				/*
				 * NumberFormat n_f= NumberFormat.getInstance();
				 * n_f.setMaximumFractionDigits(2); String
				 * Tax_Amount=n_f.format(Taxammountvalue);
				 */
				
			     String ActualTotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
			    Float ActualTotalammountvalue=Float.valueOf(ActualTotalAmmount);
			    data.put("ActualTotalammountvalue",ActualTotalAmmount);
			   // Float Total=(subtotlaValue+shippingammountvalue);
			    
			    Float ExpectedTotalAmmount = subtotlaValue+shippingammountvalue+Taxammountvalue;
			    String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount);
			    data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
			  
			    if((state.equals("Illinois"))||(state.equals("Florida"))) {
			    Float calucaltedvalue= ((subtotlaValue)*giventaxvalue)/100;
			    String userpaneltaxvalue = new BigDecimal(calucaltedvalue).setScale(2, BigDecimal.ROUND_UP).toString();
			  
			    data.put("calculatedvalue",userpaneltaxvalue);
			    System.out.println(TaxAmmount);
			    System.out.println(userpaneltaxvalue);
			    
			    }
			    else {
			    	Float calucaltedvalue= ((subtotlaValue+shippingammountvalue)*giventaxvalue)/100;
				    String userpaneltaxvalue = new BigDecimal(calucaltedvalue).setScale(2, BigDecimal.ROUND_HALF_UP).toString();   
				  
				    data.put("calculatedvalue",userpaneltaxvalue);
				    System.out.println(TaxAmmount);
				    System.out.println(userpaneltaxvalue);
			    }	
			   
			 Common.assertionCheckwithReport(TaxAmmount.equals(TaxAmmount),"verifying tax calculation", "tax rate is matches to given shipping address tax ","successfully tax rate is matches to given shipping address tax", "tax rate is not matches to given shipping address tax");
				 		}
			 	 catch(Exception |Error e)
			 		{
			 			report.addFailedLog("verifying tax calculation", "getting price values from shipping page  ", "Faield to get price value from shipping page", Common.getscreenShotPathforReport("TaxRates"));

			 			e.printStackTrace();
			 			Assert.fail();
			 			
			 	}

						
			    return data;
			    
			}
		 
		 public HashMap<String,String> OrderSummaryValidation() {
				// TODO Auto-generated method stub
				HashMap<String,String> data=new HashMap<String,String>();
				try{			    
					Thread.sleep(3000);
					
					
					
					 
            
			     String subtotla=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
			     // subtotla.replace("", newChar)
			    Float subtotlaValue=Float.valueOf(subtotla);
			    data.put("subtotlaValue",subtotla);
			    
			   // Capabilities cap = (WebDriver).getCapabilities();
			   
			  String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
			    Float shippingammountvalue=Float.valueOf(shippingammount);
				data.put("shippingammountvalue",shippingammount);
				
				  String ActualTotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
				    Float ActualTotalammountvalue=Float.valueOf(ActualTotalAmmount);
				    data.put("ActualTotalammountvalue",ActualTotalAmmount);
				    
				    int SizesofDis= Common.findElements("xpath", "(//span[@data-th='checkout.sidebar.summary.totals.discount'])").size();
				    if(SizesofDis>0) {
				    String Discount=Common.getText("xpath", "(//span[@data-th='checkout.sidebar.summary.totals.discount'])").replace("-$", "");
				    Float Discountammountvalue=Float.valueOf(Discount);
					data.put("Discountammountvalue",Discount);
				    
				    }
				    else {
				    	
				    	String Discountammountvalue= "0.00";
						data.put("Discountammountvalue",Discountammountvalue);
				    	
				    }
				    
				    
				   int Sizes= Common.findElements("xpath", "//td[@data-th='Tax']//span").size();
				if(Sizes>0) {
					
					// Float Taxrate=data.get(dataset).get("tax");
					
			     String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']//span").replace("$", "");
			    Float Taxammountvalue=Float.valueOf(TaxAmmount);
				data.put("Taxammountvalue",TaxAmmount);
				
				
				  Float ExpectedTotalAmmount = subtotlaValue+shippingammountvalue+Taxammountvalue;
				    String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount);
				    data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
				    
				}
				else {
					 String Taxammountvalue= "0.00";
						data.put("Taxammountvalue",Taxammountvalue);
						
					  Float ExpectedTotalAmmount = subtotlaValue+shippingammountvalue;
					    String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount);
					    data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
				}
			
			   
				ExtenantReportUtils.addPassLog("verifying tax calculation", "tax rate is matches to given shipping address tax ","successfully tax rate is matches to given shipping address tax", "tax rate is not matches to given shipping address tax");
				 		}
			 	 catch(Exception |Error e)
			 		{
			 			report.addFailedLog("verifying tax calculation", "getting price values from shipping page  ", "Faield to get price value from shipping page", Common.getscreenShotPathforReport("TaxRates"));

			 			e.printStackTrace();
			 			Assert.fail();
			 			
			 	}

						
			    return data;
			    
			}
		 
		 public HashMap<String,String> Egift_Product_OrderSummaryValidation() {
				// TODO Auto-generated method stub
				HashMap<String,String> data=new HashMap<String,String>();
				try{			    
					Thread.sleep(3000);
         
			     String subtotla=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
			     // subtotla.replace("", newChar)
			    Float subtotlaValue=Float.valueOf(subtotla);
			    data.put("subtotlaValue",subtotla);
			    
			   // Capabilities cap = (WebDriver).getCapabilities();
			   
			  String shippingammount="";
				data.put("shippingammountvalue",shippingammount);
				
				  String ActualTotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
				    Float ActualTotalammountvalue=Float.valueOf(ActualTotalAmmount);
				    data.put("ActualTotalammountvalue",ActualTotalAmmount);
				    
				    int SizesofDis= Common.findElements("xpath", "(//span[@data-th='checkout.sidebar.summary.totals.discount'])").size();
				    if(SizesofDis>0) {
				    String Discount=Common.getText("xpath", "(//span[@data-th='checkout.sidebar.summary.totals.discount'])").replace("-$", "");
				    Float Discountammountvalue=Float.valueOf(Discount);
					data.put("Discountammountvalue",Discount);
				    
				    }
				    else {
				    	
				    	String Discountammountvalue= "0.00";
						data.put("Discountammountvalue",Discountammountvalue);
				    	
				    }
				    
				    int Sizes1= Common.findElements("xpath", "//span[@data-th='Shipping']").size();
					if(Sizes1>0) {
						 String shipingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
						    Float shippingammountvalue=Float.valueOf(shipingammount);
							data.put("shippingammountvalue",shipingammount);
					
					
					  Float ExpectedTotalAmmount = subtotlaValue;
					    String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount);
					    data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
					    
					}
					else {
						 String shipingammount= "0.00";
							data.put("shippingammountvalue",shipingammount);
							
						  Float ExpectedTotalAmmount = subtotlaValue;
						    String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount);
						    data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
					}
				    
				    
				   int Sizes= Common.findElements("xpath", "//td[@data-th='Tax']//span").size();
				if(Sizes>0) {
			     String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']//span").replace("$", "");
			    Float Taxammountvalue=Float.valueOf(TaxAmmount);
				data.put("Taxammountvalue",TaxAmmount);
				
				
				  Float ExpectedTotalAmmount = subtotlaValue;
				    String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount);
				    data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
				    
				}
				else {
					 String Taxammountvalue= "0.00";
						data.put("Taxammountvalue",Taxammountvalue);
						
					  Float ExpectedTotalAmmount = subtotlaValue;
					    String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount);
					    data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
				}
			
			   
				ExtenantReportUtils.addPassLog("verifying tax calculation", "tax rate is matches to given shipping address tax ","successfully tax rate is matches to given shipping address tax", "tax rate is not matches to given shipping address tax");
				 		}
			 	 catch(Exception |Error e)
			 		{
			 			report.addFailedLog("verifying tax calculation", "getting price values from shipping page  ", "Faield to get price value from shipping page", Common.getscreenShotPathforReport("TaxRates"));

			 			e.printStackTrace();
			 			Assert.fail();
			 			
			 	}

						
			    return data;
			    
			}
		
		public void addDeliveryAddress(String dataSet,String Street,String City,String postcode,String Region) throws Exception {
    		try {
    			Sync.waitElementVisible("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
    			//Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']", "qatest067@gmail.com");
    			 Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']","varaprasad.raikanti@gmail.com");
    		} catch (NoSuchElementException e) {
    			Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']", data.get(dataSet).get("Email"));

    		}
    		String expectedResult = "email field will have email address";
    		try {
    			Common.textBoxInput("xpath", "//input[@name='firstname']",data.get(dataSet).get("FirstName"));
                int size = Common.findElements("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']").size();
                Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,"Filled Email address", "unabel to fill the email address");
                Common.textBoxInput("xpath", "//input[@name='lastname']",data.get(dataSet).get("LastName"));
    			Common.textBoxInput("xpath", "//input[@name='street[0]']",Street);
    			String Text=Common.getText("xpath", "//input[@name='street[0]']");
    			
    			

    		/*	try {
    				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
    			} catch (Exception e) {
    				Common.actionsKeyPress(Keys.BACK_SPACE);
    				Thread.sleep(1000);
    				Common.actionsKeyPress(Keys.SPACE);
    				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
    			}
    			if (data.get(dataSet).get("StreetLine2") != null) {
    				Common.textBoxInput("name", "street[1]", Street);
    				
    				String text=Common.getText("name","street[1]");
    				System.out.println(text);
    				
    			}
    			if (data.get(dataSet).get("StreetLine3") != null) {
    				Common.textBoxInput("name", "street[2]",Street);
    			}*/
    			Sync.waitPageLoad();
    			Thread.sleep(5000);
    			Common.findElement("xpath", "//input[@name='city']").clear();
    			Common.textBoxInput("xpath", "//input[@name='city']",City);
    			
    			
    			Common.actionsKeyPress(Keys.PAGE_DOWN);
    			Thread.sleep(3000);
    			try {
    				Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT, Region);
    			} catch (ElementClickInterceptedException e) {
    				Thread.sleep(3000);
    				Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT, Region);
    			}
    			Thread.sleep(2000);
    			Common.textBoxInputClear("xpath", "//input[@name='postcode']");
    			Common.textBoxInput("xpath", "//input[@name='postcode']",postcode);
    			
    			Thread.sleep(5000);
    			
    			Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));

    		}

    		catch (Exception | Error e) {

    			ExtenantReportUtils.addFailedLog("validating shipping address",
    					"shipping address is filled in to the fields", "user faield to fill the shipping address",
    					Common.getscreenShotPathforReport("shipingaddressfaield"));
    			// ExtenantReportUtils.addFailedLog("User click check out button",
    			// "User unabel click the checkout button",
    			// Common.getscreenShotPathforReport("check out miniCart"));
    			Assert.fail();

    		}
    		Thread.sleep(5000);
    		int size=Common.findElements("xpath", "(//td[text()='Express'])").size();
    		if(size>0){
    			Common.clickElement("xpath", "(//td[text()='Express'])");
    		}

    		expectedResult = "shipping address is filled in to the fields";
    		click_Next();
    		//Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");

    		int errorsize = Common.findElements("xpath", "//div[contains(@id,'error')]").size();

    		if (errorsize <= 0) {
    			ExtenantReportUtils.addPassLog("validating the shipping address field with valid Data", expectedResult,
    					"Filled the shipping address", Common.getscreenShotPathforReport("shippingaddresspass"));
    		} else {
    			ExtenantReportUtils.addFailedLog("validating the shipping address field with valid Datas", expectedResult,
    					"failed to add a addres in the filled",
    					Common.getscreenShotPathforReport("failed to add a address"));
    			Assert.fail();
    		}

    		
    		Thread.sleep(3000);
    	}	
		
		
		public String  URL() throws InterruptedException {
			String Website="";
			Common.clickElement("xpath", "(//a[@class='logo'])");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Website=Common.getCurrentURL();
            
			return Website;
			
		}		
		
	
		
public String  Verify_order() throws InterruptedException {		
	 String Orderid="";
        Thread.sleep(5000);
        //Common.textBoxInput("id", "//textarea[contains(@id,'tt-c-comment-field')]","Ceate accounts test ");
        String expectedResult = "It redirects to order confirmation page";
        try{
        Sync.waitPageLoad();
        Thread.sleep(5000);
       
       
        for(int i=0;i<10;i++){
            Thread.sleep(5000);
            if(Common.getCurrentURL().contains("success")){
                break;
            }
           
        }
       
        String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']/span");
        System.out.println(sucessMessage);
        //String Orderid="";
        int size=Common.findElements("xpath", "(//a[@class='action print'])").size();
        if(size>0) {
        	
        Orderid=Common.getText("xpath", "(//div[@class='column main'])//div[@class='checkout-success']//a//strong");
        }
        else{
        Orderid=Common.getText("xpath", "((//div[@class='column main'])//span)[1]");
        }
        
    	System.out.println(Orderid);
        System.out.println("Your order number is:"+Orderid);
        Common.assertionCheckwithReport(sucessMessage.equals("THANK YOU FOR YOUR PURCHASE!"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");
           
        }
        catch (Exception | Error e) {
            e.printStackTrace();
            ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
                    "User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
            Assert.fail();
            
        }
       
	return Orderid;
    }

//public void addDeliveryAddress_LoginUser(String dataSet,String Street,String City,String postcode,String Region) throws Exception {
	public void addDeliveryAddress_LoginUser(String dataSet,String Street,String City,String postcode,String Region) throws Exception {
    	
	String expectedResult = "shipping address is entering in the fields";
    int size = Common.findElements(By.xpath("//span[contains(text(),'New Address')]")).size();
	if (size > 0) {

		try {
			Common.clickElement("xpath", "//span[contains(text(),'New Address')]");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",Street);
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.SPACE);
			Thread.sleep(3000);
			
			if (data.get(dataSet).get("StreetLine2") != null) {
				Common.textBoxInput("name", "street[1]",Street);
			}
		if (data.get(dataSet).get("StreetLine3") != null) {
				Common.textBoxInput("name", "street[2]",Street);
			}
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",City);
			// Common.mouseOverClick("name", "region_id");
			try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT,Region);
			} catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, Region);
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']",postcode);
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
					data.get(dataSet).get("phone"));
            Sync.waitElementPresent("xpath", "(//span[text()='Save in address book'])");
            Common.clickElement("xpath", "(//span[text()='Save in address book'])");
			ExtenantReportUtils.addPassLog("validating the shipping address", expectedResult,
					"user add the shipping address",
					Common.getscreenShotPathforReport("faield to add shipping address"));


			Common.clickElement("xpath", "//button[contains(@class,'save-address')]");

			int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

			Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
					"user will fill the all the shipping", "user fill the shiping address click save button",
					"faield to add new shipping address");
			
			
			
			Common.actionsKeyPress(Keys.END);
			Sync.waitPageLoad();
			select_USPS_StandardGround_shippingMethod();
            Thread.sleep(5000);
			Common.mouseOverClick("xpath", "//div[@id='shipping-method-buttons-container']/div/button");
			
			
			
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
					"User unabel add shipping address",
					Common.getscreenShotPathforReport("shipping address faield"));
						Assert.fail();

		}
	}

	else

	{
		try {
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
					data.get(dataSet).get("Street"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.SPACE);
			Thread.sleep(3000);
			try {
				Common.clickElement("xpath",
						"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			} catch (Exception e) {
				Common.actionsKeyPress(Keys.BACK_SPACE);
				Thread.sleep(1000);
				Common.actionsKeyPress(Keys.SPACE);
				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div");
				//Common.clickElement("xpath","//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			}
			if (data.get(dataSet).get("StreetLine2") != null) {
				Common.textBoxInput("name", "street[1]", data.get(dataSet).get(Street));
			}
			if (data.get(dataSet).get("StreetLine3") != null) {
				Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
			}
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
					data.get(dataSet).get(City));
			// Common.mouseOverClick("name", "region_id");
			try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get(Region));
			} catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get(Region));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("name", "postcode");
			Common.textBoxInput("name", "postcode", data.get(dataSet).get(postcode));
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

			//Sync.waitElementClickable("xpath", "//span[contains(text(),'Continue To Payment')]");
			//Common.clickElement("xpath", "//span[contains(text(),'Continue To Payment')]");

		
			ExtenantReportUtils.addPassLog("verifying shipping addres filling ", expectedResult,
					"user enter the shipping address ",
					Common.getscreenShotPathforReport("fill the shipping address first time"));

			//Common.findElements("xpath", "").size();
			expectedResult = "shipping address is filled in to the fields";
			// report.addPassLog(expectedResult,"Filled the shipping
			// address",Common.getscreenShotPathforReport("fill the shipping
			// address"));
			
			//Common.mouseOverClick("xpath", "//input[@id='label_method_bestway']");
			//Sync.waitElementClickable("xpath","//input[@id='label_method_bestway']");
			//Common.clickElement("xpath", "//input[@id='label_method_bestway']");
			select_USPS_StandardGround_shippingMethod();
            Thread.sleep(5000);
			Common.clickElement("xpath", "//button[@data-role='opc-continue']");
			
			Thread.sleep(3000);

		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
					"User unabel add shipping address",
					Common.getscreenShotPathforReport("shipping address faield"));
			// ExtenantReportUtils.addFailedLog("User click check out
			// button", "User unabel click the checkout button",
			// Common.getscreenShotPathforReport("check out miniCart"));
			Assert.fail();

		}
	}

	// report.addPassLog("clicked on the proceed to payment
	// section",Common.getscreenShotPathforReport("land on the payment
	// section"));

}
	
	public void writeOrderNumber(String Website, String OrderIdNumber,String Description, String subtotlaValue, String shippingammountvalue, String Taxammountvalue,String ActualTotalammountvalue,String ExpectedTotalammountvalue,String Discountammountvalue, String ShippingState,String ShippingZip, String Card) throws FileNotFoundException, IOException
	{
		//String fileOut="";
	try{
		
		File file=new File(System.getProperty("user.dir")+"/src/test/resources/DryBar_E2E_orderDetails.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
		XSSFSheet sheet;
		Row row;
		Cell cell;
		int rowcount;
		sheet = workbook.getSheet("OrderDetails");
		
		if((workbook.getSheet("OrderDetails"))==null)
		{
		sheet = workbook.createSheet("OrderDetails");
		CellStyle cs = workbook.createCellStyle();
		cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cs.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		Font f = workbook.createFont();
		f.setBold(true);
		cs.setFont(f);	 
		cs.setAlignment(HorizontalAlignment.RIGHT);
		row = sheet.createRow(0);
		cell = row.createCell(0);
		cell.setCellStyle(cs);
		cell.setCellValue("Orders Details");
		
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellStyle(cs);
		cell.setCellValue("Web Order Number");
		rowcount=2;
		
		}
		
		else
		{
		
		sheet=workbook.getSheet("OrderDetails");	
		rowcount=sheet.getLastRowNum()+1;
		}
		row = sheet.createRow(rowcount);
		cell = row.createCell(0);
		cell.setCellType(CellType.NUMERIC);
		int SNo=rowcount-1;
		cell.setCellValue(SNo);
		cell = row.createCell(1);
		cell.setCellType(CellType.STRING);
		String websitename;
		if(Website.contains("drybar"))
	     {
			websitename="Drybar";
	}
		else
		{
			websitename="";
		}
		cell.setCellValue(websitename);
		cell = row.createCell(2);
		cell.setCellType(CellType.STRING);
		cell.setCellValue("Lotuswave");
		cell = row.createCell(3);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(Description);
		cell = row.createCell(4);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(OrderIdNumber);
		cell = row.createCell(5);
		cell.setCellType(CellType.STRING);
		cell.setCellValue("Order placed Successfully");
		
		cell = row.createCell(6);
		cell.setCellType(CellType.STRING);
		cell.setCellValue("Processing");

		cell = row.createCell(7);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(subtotlaValue);
		
		cell = row.createCell(8);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(shippingammountvalue);
		
		cell = row.createCell(9);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(ShippingState);
		
		cell = row.createCell(10);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(ShippingZip);
		
		cell = row.createCell(11);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(Taxammountvalue);
		
		
		cell = row.createCell(12);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(ExpectedTotalammountvalue);
		
		cell = row.createCell(13);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(Discountammountvalue);
		
		cell = row.createCell(14);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(ActualTotalammountvalue);
		
		cell = row.createCell(15);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(Card);
		
		
		
		System.out.println(OrderIdNumber);
		//String subtotla = Float.toString(subtotlaValue);
		//System.out.println("String is: "+subtotla);
		System.out.println(subtotlaValue);
		//String shippingammount = Float.toString(shippingammountvalue);
		//System.out.println("String is: "+shippingammount);
		System.out.println(shippingammountvalue);
		//String Taxammount = Float.toString(Taxammountvalue);
		//System.out.println("String is: "+Taxammount);
		System.out.println(Taxammountvalue);
		//String Totalammount = Float.toString(Totalammountvalue);
		//System.out.println("String is: "+Totalammount);
		System.out.println(ActualTotalammountvalue);
		System.out.println(ExpectedTotalammountvalue);
		//String Actualtax = Float.toString(ActualTax);
		//System.out.println("String is: "+Actualtax);
		//System.out.println(giventaxvalue);
		//String userpaneltax = Float.toString(userpaneltaxvalue);
		//System.out.println("String is: "+userpaneltax);
		//System.out.println(calucaltedvalue);
		
			FileOutputStream fileOut = new FileOutputStream(file);
		
		workbook.write(fileOut);
	
		fileOut.flush();
		fileOut.close();
//return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);
        } catch (Exception e) {
            e.printStackTrace();
        }
//	return fileOut;
//	return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);

	}
	
	
	public String verifyingSucesspage() throws Exception {
		String order = "";
		String expectedResult = "It redirects to order confirmation page";

		Thread.sleep(8000);

		try {
			String sucessMessage = Common.getText("xpath", "//h1[@class='page-title']");

			int sizes = Common.findElements("xpath", "//h1[@class='page-title']").size();
			Common.assertionCheckwithReport(sucessMessage.equals("THANK YOU FOR YOUR PURCHASE!"),
					"verifying the product confirmation", expectedResult,
					"Successfully It redirects to order confirmation page Order Placed",
					"User unabel to go orderconformation page");
			// order=Common.getText("xpath", "//a[@class='order-number']/strong");

			order = Common.getText("xpath", "//div[@class='checkout-success']/p/span");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
					"User failed to navigate  to order confirmation page",
					Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();
		}

		return order;
	}

	public void DrybarAdminlogin(String dataSet) throws Exception {

		try {
			Thread.sleep(4000);
			Common.oppenURL("https://jetrails-staging.drybar.com/PUjbcJfzYQqqP6BeP");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//input[contains(@name,'username')]");
			Common.textBoxInput("xpath", "//input[contains(@name,'username')]", data.get(dataSet).get("UserName"));
			Common.textBoxInput("xpath", "//input[contains(@name,'password')]", data.get(dataSet).get("Password"));

			int username = Common.findElements("xpath", "//input[contains(@name,'username')]").size();

			Common.assertionCheckwithReport(username >= 1, "verifying Admin panel login page",
					"User name and password field data is populating", "Sucessfully enter username and password",
					"Faield to enter username and password");
			Common.clickElement("xpath", "//button[contains(@class,'action-primary')]");
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.ESCAPE);
			// Thread.sleep(300000);
		} catch (Exception | Error e) {
			report.addFailedLog("verifying Admin panel login page", "User name and password field data is populating",
					"Faield to enter username and password", Common.getscreenShotPathforReport("adminlogin"));

			e.printStackTrace();
			Assert.fail();

		}
	}
	
	public void Spice() throws Exception {

		try {
			Thread.sleep(4000);
			Common.oppenURL("https://www.cssscript.com/demo/generic-country-state-dropdown-list-countries-js/");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "(//select[@id='country'])");
			Select dropdown = new Select (Common.findElement("xpath", "(//select[@id='country'])"));
		List <WebElement> dd= dropdown.getOptions();
		System.out.println(dd.size());
		for (int j=0; j<dd.size(); j++) {
			System.out.println(dd.get(j).getText());
		}
		} catch (Exception | Error e) {
			report.addFailedLog("verifying Admin panel login page", "User name and password field data is populating",
					"Faield to enter username and password", Common.getscreenShotPathforReport("adminlogin"));

			e.printStackTrace();
			Assert.fail();

		}
	}
	
public void Navigate_Order_Details_Page(String order) {
		
	
       
		try {
			Thread.sleep(180000);
			Common.findElement("xpath", "//li[@id='menu-magento-sales-sales']").click();

			Thread.sleep(5000);

			Common.clickElement("xpath", "(//span[text()='Orders'])[1]");
			Sync.waitPageLoad();
			Thread.sleep(2000);
			Common.textBoxInput("xpath", "(//input[@id='fulltext'])[1]", order);
			Thread.sleep(3000);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.findElement("xpath", "(//div[text()='"+order+"'])");
			Thread.sleep(4000);
            Common.mouseOverClick("xpath", "(//div[text()='"+order+"'])");
            Sync.waitPageLoad();
			Common.assertionCheckwithReport(Common.getPageTitle().contains(order),
					"Validating Order page in admin", "User must land on Order details page in admin",
					"user sucessfully navigating to Order details page ", "fail to navigate to order details page");
	      
		} catch (Exception | Error e) {
            e.printStackTrace();
           ExtenantReportUtils.addFailedLog("Validating Order page in admin","User must land on Order details page in admin","fail to navigate to order details page");
            Assert.fail();
        }
		
    }
	
	public void selectManulExport(String orderid) {

		try {
			Common.findElement("xpath", "//li[@id='menu-magento-sales-sales']").click();
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//li[@data-ui-id='menu-xtento-orderexport-manual']");
			Common.clickElement("xpath", "//li[@data-ui-id='menu-xtento-orderexport-manual']");

			Common.assertionCheckwithReport(
					Common.getPageTitle().contains("Sales Export"),
					"Validating manual export option in admin", "User must land on Manual Export page in admin",
					"user sucessfully navigating to Manual Export page ", "fail to navigate Manual Export page");

			
			//Common.dropdown("xpath", "(//select[@name='profile_id'])", Common.SelectBy.TEXT, data.get(dataset).get("ProfileID"));
			
			Common.mouseOverClick("xpath", "//select[@id='profile_id']");
			
			// //td[@class='input-ele']//select[@name='profile_id']//optgroup//option[text()='B2C Export Profile (ID: 1)']
			 
			Common.clickElement("xpath", "(//select[@name='profile_id'])//optgroup//option[text()='B2C Export Profile (ID: 1)']");
			
			// Common.dropdown("xpath", "((//fieldset[@class='fieldset
			// admin__fieldset'])//select[@class='select'])[1]", Common.SelectBy.TEXT, "B2C
			// Export Profile(ID: 1)");
			// Common.textBoxInput("id", "email_address", "varaprasad.raikanti@gmail.com");
			
			Common.textBoxInput("xpath", "//input[@id='increment_from']", orderid);
			
			// starting ordernumber
			Common.textBoxInput("xpath", "//input[@id='increment_to']", orderid);

			Thread.sleep(5000);
			// select the orderstatusinexpoert
			Common.dropdown("id", "force_status", Common.SelectBy.TEXT, "Processing"); //

			
			Common.clickElement("xpath", "//input[@id='filter_new_only']");
			
			Common.clickCheckBox("xpath", "//input[@id='start_download']");
			Thread.sleep(5000);
			Common.clickElement("xpath", "//button[@id='export_button']");
			Thread.sleep(5000);
			report.addPassLog("validating the Manual Export order files",
					" enter all the field infromation manual export field",
					"User sucessfully enter all the manual export field data",
					Common.getscreenShotPathforReport("downloading"));

		}

		catch (Exception | Error e) {
			report.addFailedLog("validating the Manual Export order files",
					"enter all the field infromation manual export field", "Faield to enter manual export field data",
					Common.getscreenShotPathforReport("faielddownload"));

			e.printStackTrace();
			Assert.fail();

		}

	}
	
	public HashMap<String, HashMap<String, String>> productinfromation() throws Throwable {

		Sync.waitPageLoad();
		int value = 0;
		List<WebElement> cartproducts = Common.findElements("xpath", "(//td[@class='col-total last'])//span");
		HashMap<String, HashMap<String, String>> productinfromation = new HashMap<String, HashMap<String, String>>();
		//HashMap<String, String> singleproductinfromation;
		HashMap<String,String> singleproductinfromation=new HashMap<String,String>();

		try {

			for (int i = 0; i < cartproducts.size(); i++) {
				value = i + 1;
				singleproductinfromation = new HashMap<String, String>();

				Thread.sleep(5000);
					String FirstProductTotalValue=Common.findElement("xpath", "((//td[@class='col-total last'])//span)[1]").getText().replace("$", "");
					String FirstProductTotalSKU=Common.findElement("xpath", "(//div[@class='product-sku-block'])[1]").getText();
					singleproductinfromation.put("FirstProductTotalSKU", FirstProductTotalSKU);
					singleproductinfromation.put("FirstProductTotalValue", FirstProductTotalValue);
					
					System.out.println(FirstProductTotalValue);
					//(//div[@class='product-sku-block'])[1]
					
					String SecondProductTotalValue=Common.findElement("xpath", "((//td[@class='col-total last'])//span)[2]").getText().replace("$", "");;
					singleproductinfromation.put("SecondProductTotalValue", SecondProductTotalValue);
					String SecondProductTotalSKU=Common.findElement("xpath", "(//div[@class='product-sku-block'])[2]").getText();
					singleproductinfromation.put("SecondProductTotalSKU", SecondProductTotalSKU);
					System.out.println(SecondProductTotalValue);
					
					
					String ThirdProduct_Total=Common.getText("xpath", "(//div[@class='option-value'])[1]//span").replace("$", "");
					Float ThirdProductTotalAmount=Float.valueOf(ThirdProduct_Total);
					String Tax=Common.getText("xpath", "(//td[@class='col-tax-percent'])[3]").replace("%", "");
					System.out.println(Tax);
					Float TaxPercent=Float.valueOf(Tax);
					Float ThirdProductTax=((ThirdProductTotalAmount)*TaxPercent)/100;
					System.out.println(ThirdProductTax);
					Float Third_ProductTotal=ThirdProductTax+ThirdProductTotalAmount;
					String Third_ProductTotal_Amount = new BigDecimal(Third_ProductTotal).setScale(3, BigDecimal.ROUND_FLOOR).toString();
					singleproductinfromation.put("Third_ProductTotal_Amount", Third_ProductTotal_Amount);
					String ThirdProductTotalSKU=Common.findElement("xpath", "(//div[@class='product-sku-block'])[3]").getText();
					singleproductinfromation.put("ThirdProductTotalSKU", ThirdProductTotalSKU);
					System.out.println(Third_ProductTotal_Amount);
					
					String FourthProduct_Total=Common.getText("xpath", "(//div[@class='option-value'])[2]//span").replace("$", "");
					 Float FourthProductTotalAmount=Float.valueOf(FourthProduct_Total);
					Float FourthProductTax=((FourthProductTotalAmount)*TaxPercent)/100;
					System.out.println(FourthProductTax);
					Float Fourth_ProductTotal=FourthProductTax+FourthProductTotalAmount;
					 String Fourth_ProductTotal_Amount = new BigDecimal(Fourth_ProductTotal).setScale(3, BigDecimal.ROUND_FLOOR).toString();
					singleproductinfromation.put("Fourth_ProductTotal_Amount", Fourth_ProductTotal_Amount);
					String FourthProductTotalSKU=Common.findElement("xpath", "(//div[@class='product-sku-block'])[3]").getText();
					singleproductinfromation.put("FourthProductTotalSKU", FourthProductTotalSKU);
					System.out.println(Fourth_ProductTotal_Amount);
					
					String FifthProduct_Total=Common.getText("xpath", "(//div[@class='option-value'])[3]//span").replace("$", "");
					 Float FifthProductTotalAmount=Float.valueOf(FifthProduct_Total);
					Float FifthProductTax=((FifthProductTotalAmount)*TaxPercent)/100;
					System.out.println(FifthProductTax);
					Float Fifth_ProductTotal=FifthProductTax+FifthProductTotalAmount;
					 String Fifth_ProductTotal_Amount = new BigDecimal(Fifth_ProductTotal).setScale(3, BigDecimal.ROUND_FLOOR).toString();
					singleproductinfromation.put("Fifth_ProductTotal_Amount", Fifth_ProductTotal_Amount);
					String FifthProductTotalSKU=Common.findElement("xpath", "(//div[@class='product-sku-block'])[3]").getText();
					singleproductinfromation.put("FifthProductTotalSKU", FifthProductTotalSKU);
					System.out.println(Fifth_ProductTotal_Amount);
					
					String SixthProduct_Total=Common.getText("xpath", "((//td[@class='col-total last'])//span)[4]").replace("$", "");
					singleproductinfromation.put("SixthProduct_Total", SixthProduct_Total);
					String SixthProductTotalSKU=Common.findElement("xpath", "(//div[@class='product-sku-block'])[4]").getText();
					singleproductinfromation.put("SixthProductTotalSKU", SixthProductTotalSKU);
					System.out.println(SixthProduct_Total);
					
					String SeventhProduct_Total=Common.getText("xpath", "((//td[@class='col-total last'])//span)[5]").replace("$", "");
					singleproductinfromation.put("SeventhProduct_Total", SeventhProduct_Total);
					String SeventhProductTotalSKU=Common.findElement("xpath", "(//div[@class='product-sku-block'])[5]").getText();
					singleproductinfromation.put("SeventhProductTotalSKU", SeventhProductTotalSKU);
					System.out.println(SeventhProduct_Total);
					
					String Subtotal=Common.getText("xpath", "((//div[@class='admin__page-section-item order-totals'])//tr)[5]//span").replace("$", "");
					singleproductinfromation.put("Subtotal", Subtotal);
					
					String TotalTax=Common.getText("xpath", "((//div[@class='admin__page-section-item order-totals'])//tr)[7]//span").replace("$", "");
					singleproductinfromation.put("TotalTax", TotalTax);
					
					String ShippingCost=Common.getText("xpath", "((//div[@class='admin__page-section-item order-totals'])//tr)[8]//span").replace("$", "");
					singleproductinfromation.put("ShippingCost", ShippingCost);
					
					String DiscountAmount=Common.getText("xpath", "((//div[@class='admin__page-section-item order-totals'])//tr)[6]//span").replace("-$", "");
					singleproductinfromation.put("DiscountAmount", DiscountAmount);
					
					
					String ActualGrandTotal=Common.getText("xpath", "((//div[@class='admin__page-section-item order-totals'])//tr)[1]//span").replace("$", "");
					singleproductinfromation.put("ActualGrandTotal", ActualGrandTotal);
					
					 Float First_Product_TotalValue=Float.valueOf(FirstProductTotalValue);
					 Float Second_Product_TotalValue=Float.valueOf(SecondProductTotalValue);
					 Float Third_Product_TotalValue=Float.valueOf(Third_ProductTotal_Amount);
					 Float Fourth_Product_TotalValue=Float.valueOf(Fourth_ProductTotal_Amount);
					 Float Fifth_Product_TotalValue=Float.valueOf(Fifth_ProductTotal_Amount);
					 Float Sixth_Product_TotalValue=Float.valueOf(SixthProduct_Total);
					 Float Seventh_Product_TotalValue=Float.valueOf(SeventhProduct_Total);
					 Float Discount_Amount=Float.valueOf(DiscountAmount);
					
					 Float Total_Tax=Float.valueOf(TotalTax);
					 String TotalTaxValue = new BigDecimal(Total_Tax).setScale(2, BigDecimal.ROUND_UP).toString();
					 String  TotalTax_Value=String.valueOf(TotalTaxValue);
					 singleproductinfromation.put("TotalTax_Value", TotalTax_Value);
					 
					 
					 
					 Float Calculated_GrandTotal=((First_Product_TotalValue+Second_Product_TotalValue+Third_Product_TotalValue+Fourth_Product_TotalValue+Fifth_Product_TotalValue+Sixth_Product_TotalValue+Seventh_Product_TotalValue));
					 String Calculated_Grand_Total = new BigDecimal(Calculated_GrandTotal).setScale(2, BigDecimal.ROUND_UP).toString();
					 String  CalculatedGrandTotal=String.valueOf(Calculated_Grand_Total);
					 singleproductinfromation.put("CalculatedGrandTotal", CalculatedGrandTotal);
					
					
				
				Thread.sleep(3000);
				productinfromation.put("order" + value, singleproductinfromation);

				ExtenantReportUtils.addPassLog("Validating product infromation",
						"User get product name SKQ , QTY infroamtion   ",
						"User sucessfully get product infromation " + productinfromation,
						Common.getscreenShotPathforReport("productinfopass"));

			}

		} catch (Exception | Error e) {

			ExtenantReportUtils.addFailedLog("Validating product infromation",
					"User get product name SKQ , QTY infroamtion",
					"User failed to get product name SKQ , QTY infroamtion",
					Common.getscreenShotPathforReport("productinfail"));

			Assert.fail();
		}

		System.out.println(productinfromation + "**** total product infromation");

		return productinfromation;
	}
	
	public HashMap<String, HashMap<String, String>> Product_Total() throws Throwable {

		Sync.waitPageLoad();
		HashMap<String, HashMap<String, String>> productTotalPrice = new HashMap<String, HashMap<String, String>>();
		
		HashMap<String, String> singleproductinfromation;
//Common.findElements("xpath", "(//td[@class='col-total last'])//span");
		try {
			singleproductinfromation = new HashMap<String, String>();
			Thread.sleep(4000);
			String product_TotalPrice=Common.getText("xpath", "((//td[@class='col-total last'])//span)[1]");
				//String productTotalPrice = cartproductsTotal.get(i).getText();
				System.out.println(product_TotalPrice);
				ExtenantReportUtils.addPassLog("Validating product infromation",
						"User get product name SKQ , QTY infroamtion   ",
						"User sucessfully get product infromation " + productTotalPrice,
						Common.getscreenShotPathforReport("productinfopass"));
				singleproductinfromation.put("product_TotalPrice", product_TotalPrice);
			

		} catch (Exception | Error e) {

			ExtenantReportUtils.addFailedLog("Validating product infromation",
					"User get product name SKQ , QTY infroamtion",
					"User failed to get product name SKQ , QTY infroamtion",
					Common.getscreenShotPathforReport("productinfail"));

			Assert.fail();
		}

		//System.out.println(productTotalPrice + "****Product Total*******");

		return productTotalPrice;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public HashMap<String, HashMap<String, String>> Product_Totals() throws Throwable {

		Sync.waitPageLoad();
		int value = 0;
		List<WebElement> cartproductsTotal = Common.findElements("xpath", "(//td[@class='col-total last'])//span");
		HashMap<String, HashMap<String, String>> Product_Totals = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> singleproductinfromation;
//Common.findElements("xpath", "(//td[@class='col-total last'])//span");
		try {

			for (int i = 0; i < cartproductsTotal.size(); i++) {
				//String productname = "";
				value = i + 1;
				singleproductinfromation = new HashMap<String, String>();
				String productTotalPrice = cartproductsTotal.get(i).getText();
				System.out.println(productTotalPrice);
				ExtenantReportUtils.addPassLog("Validating product infromation",
						"User get product name SKQ , QTY infroamtion   ",
						"User sucessfully get product infromation " + Product_Totals,
						Common.getscreenShotPathforReport("productinfopass"));
				singleproductinfromation.put("productTotalPrice", productTotalPrice);
			}

		} catch (Exception | Error e) {

			ExtenantReportUtils.addFailedLog("Validating product infromation",
					"User get product name SKQ , QTY infroamtion",
					"User failed to get product name SKQ , QTY infroamtion",
					Common.getscreenShotPathforReport("productinfail"));

			Assert.fail();
		}

		System.out.println(Product_Totals + "****Product Total*******");

		return Product_Totals;
	}
	
	
	public void productinfromationvalidation(HashMap<String, HashMap<String, String>> SFproductinfromation,String Ordernumber) throws Throwable {
try {
		Thread.sleep(5000);
		String fileName = System.getProperty("user.dir") + "\\TestLogs\\Download\\Export_" + Ordernumber + ".xml";
		// String fileName="C:\\Users\\admin\\Downloads\\Export_4000420945A.xml";

		Map<String, Object> jsonInMap1 = xmlReader.fetchvalues(fileName);
		Map<String, Object> xml1 = xmlReader.stringToMapTest(jsonInMap1.get("OrderItems1").toString());
		String Producttotalwithtax1 = xml1.get("itemwithTax").toString();
		String ProductName1 = xml1.get("OrderedProductName").toString();
		
		String fileName2 = System.getProperty("user.dir") + "\\TestLogs\\Download\\Export_" + Ordernumber + ".xml";
		  Map<String, Object> jsonInMap2 = xmlReader.fetchvalues(fileName2);
		  Map<String, Object> xml2 =
		  xmlReader.stringToMapTest(jsonInMap2.get("OrderItems2").toString()); 
		  String Producttotalwithtax2 = xml2.get("itemwithTax").toString();
		  String ProductName2 = xml2.get("OrderedProductName").toString();
		  
		  
		  String fileName3 =System.getProperty("user.dir") + "\\TestLogs\\Download\\Export_" + Ordernumber + ".xml";
		  Map<String, Object> jsonInMap3 = xmlReader.fetchvalues(fileName3);
		  Map<String, Object> xml3 =
		  xmlReader.stringToMapTest(jsonInMap3.get("OrderItems3").toString()); String
		  Producttotalwithtax3 = xml3.get("itemwithTax").toString(); 
		  String ProductName3 = xml3.get("OrderedProductName").toString();
		  
		  String fileName4 = System.getProperty("user.dir") + "\\TestLogs\\Download\\Export_" +
		  Ordernumber + ".xml";
		   Map<String, Object> jsonInMap4 = xmlReader.fetchvalues(fileName4);
		  Map<String, Object> xml4 =xmlReader.stringToMapTest(jsonInMap4.get("OrderItems4").toString()); String
		  Producttotalwithtax4 = xml4.get("itemwithTax").toString();
		  String ProductName4 = xml4.get("OrderedProductName").toString();
		  
		  String fileName5 =System.getProperty("user.dir") + "\\TestLogs\\Download\\Export_" +Ordernumber + ".xml";
		  Map<String, Object> jsonInMap5 = xmlReader.fetchvalues(fileName5);
		  Map<String, Object> xml5 =xmlReader.stringToMapTest(jsonInMap5.get("OrderItems5").toString()); String
		  Producttotalwithtax5 = xml5.get("itemwithTax").toString(); 
		  String ProductName5 = xml5.get("OrderedProductName").toString();
		  
		  String fileName6 =System.getProperty("user.dir") + "\\TestLogs\\Download\\Export_" +Ordernumber + ".xml";
		  Map<String, Object> jsonInMap6 = xmlReader.fetchvalues(fileName6);
		  Map<String, Object> xml6 =xmlReader.stringToMapTest(jsonInMap6.get("OrderItems6").toString()); String
		  Producttotalwithtax6 = xml6.get("itemwithTax").toString();
		  String ProductName6 = xml6.get("OrderedProductName").toString();
		  
		  String fileName7 =System.getProperty("user.dir") + "\\TestLogs\\Download\\Export_" +Ordernumber + ".xml";
		  Map<String, Object> jsonInMap7 = xmlReader.fetchvalues(fileName7);
		  Map<String, Object> xml7 =xmlReader.stringToMapTest(jsonInMap7.get("OrderItems7").toString()); String
		  Producttotalwithtax7 = xml7.get("itemwithTax").toString();
		  String ProductName7 = xml7.get("OrderedProductName").toString();
		  
		  Float Product_totalwithtax1=Float.parseFloat(Producttotalwithtax1);
		  Float Product_totalwithtax2=Float.parseFloat(Producttotalwithtax2);
		  Float Product_totalwithtax3=Float.parseFloat(Producttotalwithtax3);
		  Float Product_totalwithtax4=Float.parseFloat(Producttotalwithtax4);
		  Float Product_totalwithtax5=Float.parseFloat(Producttotalwithtax5);
		  Float Product_totalwithtax6=Float.parseFloat(Producttotalwithtax6);
		  Float Product_totalwithtax7=Float.parseFloat(Producttotalwithtax7);
		  
		 
		  Float Cal_XMLGrandTotal=(Product_totalwithtax1+Product_totalwithtax2+Product_totalwithtax3+Product_totalwithtax4+Product_totalwithtax5+Product_totalwithtax6+Product_totalwithtax7);
		  String Cal_XML_GrandTotal = new BigDecimal(Cal_XMLGrandTotal).setScale(2, BigDecimal.ROUND_UP).toString();
		 // String CalXMLGrandTotal=Float.toString(Cal_XML_GrandTotal);
		  Map<String, Object> jsonInMap=xmlReader.fetchvalues(fileName);
		  String SubTotal= (String) jsonInMap.get("gross_tot");
		  String TotalTax= (String) jsonInMap.get("tax_amt");
		  String ShippingCost= (String) jsonInMap.get("OrderShippingCosts");
		  String GrandTotal= (String) jsonInMap.get("order_total");
		  
		  

		HashMap<String, String> order = SFproductinfromation.get("order1");//product_TotalPrice
		System.out.println(order);
		//String sfOrig_Sys_Name= "MAGENTO_DRYBAR";
		String SFProducttotalwithtax1 = order.get("FirstProductTotalValue");
		String SFProductSKU1 = order.get("FirstProductTotalSKU");
		System.out.println(SFProducttotalwithtax1);
		String SFProducttotalwithtax2 = order.get("SecondProductTotalValue");
		String SFProductSKU2 = order.get("SecondProductTotalSKU");
		System.out.println(SFProducttotalwithtax2);
		String SFProducttotalwithtax3 = order.get("Third_ProductTotal_Amount");
		String SFProductSKU3 = order.get("ThirdProductTotalSKU");
		System.out.println(SFProducttotalwithtax3);
		String SFProducttotalwithtax4 = order.get("Fourth_ProductTotal_Amount");
		String SFProductSKU4 = order.get("FourthProductTotalSKU");
		System.out.println(SFProducttotalwithtax4);
		String SFProducttotalwithtax5 = order.get("Fifth_ProductTotal_Amount");
		String SFProductSKU5 = order.get("FifthProductTotalSKU");
		System.out.println(SFProducttotalwithtax5);
		String SFProducttotalwithtax6 = order.get("SixthProduct_Total");
		String SFProductSKU6 = order.get("SixthProductTotalSKU");
		System.out.println(SFProducttotalwithtax6);
		String SFProducttotalwithtax7 = order.get("SeventhProduct_Total");
		String SFProductSKU7 = order.get("SeventhProductTotalSKU");
		System.out.println(SFProducttotalwithtax7);
		
		String SFSubTotal = order.get("Subtotal");
		String SFTotalTax = order.get("TotalTax");
		String SFShippingCost = order.get("ShippingCost");
		String Cal_GrandTotal = order.get("CalculatedGrandTotal");
		String Actual_GrandTotal = order.get("ActualGrandTotal");
		
		
		
		Common.oppenURL(fileName);
		orderxmlvalidations(ProductName1 +"  ("+SFProductSKU1+")", Producttotalwithtax1, SFProducttotalwithtax1);
		orderxmlvalidations(ProductName2 +"  ("+SFProductSKU2+")", Producttotalwithtax2, SFProducttotalwithtax2);
		orderxmlvalidations(ProductName3 +"  ("+SFProductSKU3+")", Producttotalwithtax3, SFProducttotalwithtax3);
		orderxmlvalidations(ProductName4 +"  ("+SFProductSKU4+")", Producttotalwithtax4, SFProducttotalwithtax4);
		orderxmlvalidations(ProductName5 +"  ("+SFProductSKU5+")", Producttotalwithtax5, SFProducttotalwithtax5);
		orderxmlvalidations(ProductName6 +"  ("+SFProductSKU6+")", Producttotalwithtax6, SFProducttotalwithtax6);
		orderxmlvalidations(ProductName7 +"  ("+SFProductSKU7+")", Producttotalwithtax7, SFProducttotalwithtax7);
		orderxmlvalidations("Sub Total", SubTotal, SFSubTotal);
		orderxmlvalidations("Total Tax",TotalTax, SFTotalTax);
		orderxmlvalidations("Shipping Cost", ShippingCost, SFShippingCost);
		orderxmlvalidations("Actual Grand Total", Actual_GrandTotal, GrandTotal);
		orderxmlvalidations("Calculated Grand Total", Cal_XML_GrandTotal, Cal_GrandTotal);
		
		
		
		/*
		 * orderxmlvalidations("ordernumber", OrderNumberxml, Ordernumber);
		 * orderxmlvalidations("OrigSysName", Orig_Sys_Name, sfOrig_Sys_Name);
		 * orderxmlvalidations("productname", OrderedProductNameXML, productname);
		 * orderxmlvalidations("product SKU", orderedProductSKUXML, productSKU);
		 * orderxmlvalidations("productprice", xmlproductprice, productPrice);
		 * orderxmlvalidations("product QTY", OrderedProductQTYXML, productQTY);
		 * orderxmlvalidations("itemwithTax", Producttotalwithtax, Singleproducttotal);
		 */
		Common.assertionCheckwithReport(
				Producttotalwithtax1.contains(SFProducttotalwithtax1)&& Producttotalwithtax2.contains(SFProducttotalwithtax2)&& Producttotalwithtax3.contains(SFProducttotalwithtax3)&& Producttotalwithtax4.contains(SFProducttotalwithtax4)&& Producttotalwithtax5.contains(SFProducttotalwithtax5)
				&& Producttotalwithtax6.contains(SFProducttotalwithtax6)&& Producttotalwithtax7.contains(SFProducttotalwithtax7),
				"validating xml product infromation", "order product inframtion matches to order xml product info",
				"sucessfully matches product infromation" + order + "is Equal to xml infromation" + xml1,
				"fail to match product infromatio with order xml iformation  product infromation=" + order
						+ "xmal infromation ==" + xml1); 
}catch (Exception | Error e) {
			e.printStackTrace();
}
}
	
	
	
	public void PaymentInformation(String Ordernumber) throws Throwable {
		try {
				Thread.sleep(5000);
				Thread.sleep(5000);
				String fileName = System.getProperty("user.dir") + "\\TestLogs\\Download\\Export_" + Ordernumber + ".xml";
				Map<String, Object> jsonInMap = xmlReader.fetchvalues(fileName);
				
				 String CouponCode= (String) jsonInMap.get("CouponCode");
				 String tax_amt= (String) jsonInMap.get("tax_amt");
				 String OrderShippingCosts= (String) jsonInMap.get("OrderShippingCosts");
				 String order_total= (String) jsonInMap.get("order_total");
				 
				 Map<String, Object> xml = xmlReader.stringToMapTest(jsonInMap.get("Payments").toString());//CardAmount
				String CardAmount = (String) xml.get("CardAmount");
				System.out.println(CardAmount);
				String Prepayment = (String) xml.get("Prepayment");
				System.out.println(Prepayment);
				String MerchantID = (String) xml.get("MerchantID");
				System.out.println(MerchantID);
				String TransactionID = (String) xml.get("TransactionID");
				System.out.println(TransactionID);
				String CardNumberToken = (String) xml.get("CardNumberToken");
				System.out.println(CardNumberToken);
				String AuthorizationCode = (String) xml.get("AuthorizationCode");
				System.out.println(AuthorizationCode);
				String AuthorizationDate = (String) xml.get("AuthorizationDate");
				System.out.println(AuthorizationDate);
				String payment_meth = (String) xml.get("payment_meth");
				System.out.println(payment_meth);
				String payment_meth_type = (String) xml.get("payment_meth_type");
				System.out.println(payment_meth_type);
				
					String CardType = (String) xml.get("CardType");
					System.out.println(CardType);
					String Last4 = (String) xml.get("Last4");
					System.out.println(Last4);
					String CardExpirationMonth = (String) xml.get("CardExpirationMonth");
					System.out.println(CardExpirationMonth);
					String CardExpirationYear = (String) xml.get("CardExpirationYear");
					System.out.println(CardExpirationYear);
			
					
					 Map<String, Object> item = xmlReader.stringToMapTest(jsonInMap.get("OrderItems1").toString());//CardAmount	  
					 String tax_amount = (String) item.get("tax_amount");
					 String SaleProductPrice = (String) item.get("SaleProductPrice");
					 String itemTax = (String) item.get("itemTax");
					 String itemwithTax = (String) item.get("itemwithTax");//CouponCode
				 
				
				
				
				Common.oppenURL(fileName);
				orderxmlvalidations_of_PaymentSection("CouponCode",CouponCode,"FREE" );
				orderxmlvalidations_of_PaymentSection("tax_amt",tax_amt,"0.0000" );
				orderxmlvalidations_of_PaymentSection("OrderShippingCosts", OrderShippingCosts,"0.0000");
				orderxmlvalidations_of_PaymentSection("order_total",order_total, "0.00" );
				orderxmlvalidations_of_PaymentSection("CardAmount",CardAmount, "" );
				orderxmlvalidations_of_PaymentSection("Prepayment",Prepayment, "" );
				orderxmlvalidations_of_PaymentSection("MerchantID",MerchantID, "" );
				orderxmlvalidations_of_PaymentSection("TransactionID",TransactionID, "" );
				orderxmlvalidations_of_PaymentSection("CardNumberToken",CardNumberToken, "" );
				orderxmlvalidations_of_PaymentSection("AuthorizationCode",AuthorizationCode, "" );
				orderxmlvalidations_of_PaymentSection("AuthorizationDate",AuthorizationDate, "" );
				orderxmlvalidations_of_PaymentSection("payment_meth",payment_meth,"No Payment Information Required" );
				orderxmlvalidations_of_PaymentSection("payment_meth_type", payment_meth_type,"free");
				orderxmlvalidations_of_PaymentSection("CardType", CardType,"" );
				orderxmlvalidations_of_PaymentSection("Last4",Last4, "" );
				orderxmlvalidations_of_PaymentSection("CardExpirationMonth",CardExpirationMonth, "" );
				orderxmlvalidations_of_PaymentSection("CardExpirationYear",CardExpirationYear, "" );
				orderxmlvalidations_of_PaymentSection("tax_amount",tax_amount, "0.0000" );
				orderxmlvalidations_of_PaymentSection("SaleProductPrice",SaleProductPrice, "0.0000" );
				orderxmlvalidations_of_PaymentSection("itemTax",itemTax, "0.0000" );
				orderxmlvalidations_of_PaymentSection("itemwithTax",itemwithTax, "0.0000" );
				
				
				
				
				
			
				Common.assertionCheckwithReport(
						"No Payment Information Required".contains(payment_meth)&& "free".contains(payment_meth_type),
						"validating xml product infromation", "order product inframtion matches to order xml product info",
						"sucessfully matches product infromation is Equal to xml infromation",
						"fail to match product infromatio with order xml iformation  product infromation"); 
		}catch (Exception | Error e) {
					e.printStackTrace();
		}
		}
public void product_infromationvalidation(HashMap<String, HashMap<String, String>> SF_productinfromation,String Ordernumber) throws Throwable {
try {
	Thread.sleep(5000);
	String fileName = System.getProperty("user.dir") + "\\TestLogs\\Download\\Export_" + Ordernumber + ".xml";

	Map<String, Object> jsonInMap = xmlReader.fetchvalues(fileName);
	Map<String, Object> xml = xmlReader.stringToMapTest(jsonInMap.get("OrderItems2").toString());
	String Producttotalwithtax = xml.get("itemwithTax").toString();
	
	HashMap<String, String> order = SF_productinfromation.get("order1");//product_TotalPrice
	System.out.println(order);
	String SFProducttotalwithtax = order.get("SecondProductTotal").replace("$", "");
	System.out.println(SFProducttotalwithtax);
	//Common.oppenURL(fileName);
	orderxmlvalidations("Product-2 ItemwithTax", Producttotalwithtax, SFProducttotalwithtax);
	Common.assertionCheckwithReport(Producttotalwithtax.contains(SFProducttotalwithtax),
			"validating xml product infromation", "order product inframtion matches to order xml product info",
			"sucessfully matches product infromation" + order + "is Equal to xml infromation" + xml,
			"fail to match product infromatio with order xml iformation  product infromation=" + order
					+ "xmal infromation ==" + xml); 
}catch (Exception | Error e) {
		e.printStackTrace();
}
	
	}
	
	public void orderxmlvalidations(String Details, String XML, String Adminpanel) {
		// String fileOut="";
		try {

			File file = new File(System.getProperty("user.dir") + "/src/test/resources/OrderValidation.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet;
			Row row;
			Cell cell;
			int rowcount;
			sheet = workbook.getSheet("Orderdetails");

			if ((workbook.getSheet("OrderValidationDetails")) == null) {
				sheet = workbook.createSheet("OrderValidationDetails");
				CellStyle cs = workbook.createCellStyle();
				cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				cs.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
				Font f = workbook.createFont();
				f.setBold(true);
				cs.setFont(f);
				cs.setAlignment(HorizontalAlignment.RIGHT);
				row = sheet.createRow(0);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("OrderValidationDetails");

				row = sheet.createRow(1);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("Details");
				cell = row.createCell(1);
				cell.setCellStyle(cs);
				cell.setCellValue("AdminPanel");
				cell = row.createCell(2);
				cell.setCellStyle(cs);
				cell.setCellValue("XML");
				cell = row.createCell(3);
				cell.setCellStyle(cs);
				cell.setCellValue("Status");
				rowcount = 2;

			}

			else {

				sheet = workbook.getSheet("OrderValidationDetails");
				rowcount = sheet.getLastRowNum() + 1;
			}
			row = sheet.createRow(rowcount);
			cell = row.createCell(0);
			cell.setCellValue(Details);
			cell = row.createCell(1);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Adminpanel);
			cell = row.createCell(2);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(XML);
			cell = row.createCell(3);
			cell.setCellType(CellType.STRING);

			String status;

			if (XML.contains(Adminpanel)) {
				Thread.sleep(4000);
				status="PASS";
				Thread.sleep(4000);
				CellStyle style = workbook.createCellStyle();
				Font font= workbook.createFont();
				font.setColor(IndexedColors.GREEN.getIndex());
				font.setBold(true);
				style.setFont(font);
				cell.setCellStyle(style);
			} else {
				status="FAIL";
				CellStyle style = workbook.createCellStyle();
				Font font= workbook.createFont();
				font.setColor(IndexedColors.RED.getIndex());
				font.setBold(true);
				style.setFont(font);
				cell.setCellStyle(style);
			}
			cell.setCellValue(status);
			FileOutputStream fileOut = new FileOutputStream(file);

			workbook.write(fileOut);

			fileOut.flush();
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public void orderxmlvalidations_of_PaymentSection(String Details, String XML, String Adminpanel) {
		// String fileOut="";
		try {

			File file = new File(System.getProperty("user.dir") + "/src/test/resources/XML_PaymentValidation.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet;
			Row row;
			Cell cell;
			int rowcount;
			sheet = workbook.getSheet("Orderdetails");

			if ((workbook.getSheet("XMLPaymentValidation")) == null) {
				sheet = workbook.createSheet("XMLPaymentValidation");
				CellStyle cs = workbook.createCellStyle();
				cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				cs.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
				Font f = workbook.createFont();
				f.setBold(true);
				cs.setFont(f);
				cs.setAlignment(HorizontalAlignment.RIGHT);
				row = sheet.createRow(0);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("Order XML Payment Details Validation");

				row = sheet.createRow(1);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("XML Segments");
				cell = row.createCell(1);
				cell.setCellStyle(cs);
				cell.setCellValue("Expected Result");
				cell = row.createCell(2);
				cell.setCellStyle(cs);
				cell.setCellValue("XML Output");
				cell = row.createCell(3);
				cell.setCellStyle(cs);
				cell.setCellValue("Status");
				rowcount = 2;

			}

			else {

				sheet = workbook.getSheet("XMLPaymentValidation");
				rowcount = sheet.getLastRowNum() + 1;
			}
			row = sheet.createRow(rowcount);
			cell = row.createCell(0);
			cell.setCellValue(Details);
			cell = row.createCell(1);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Adminpanel);
			cell = row.createCell(2);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(XML);
			cell = row.createCell(3);
			cell.setCellType(CellType.STRING);

			String status;

			if (XML.contains(Adminpanel)) {
				Thread.sleep(4000);
				status="PASS";
				Thread.sleep(4000);
				CellStyle style = workbook.createCellStyle();
				Font font= workbook.createFont();
				font.setColor(IndexedColors.GREEN.getIndex());
				font.setBold(true);
				style.setFont(font);
				cell.setCellStyle(style);
			} else {
				status="FAIL";
				CellStyle style = workbook.createCellStyle();
				Font font= workbook.createFont();
				font.setColor(IndexedColors.RED.getIndex());
				font.setBold(true);
				style.setFont(font);
				cell.setCellStyle(style);
			}
			cell.setCellValue(status);
			FileOutputStream fileOut = new FileOutputStream(file);

			workbook.write(fileOut);

			fileOut.flush();
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String, String> addDeliveryAddress_validation(String dataSet) throws Exception {
		HashMap<String, String> Shippingaddress = new HashMap<String, String>();

		try {
			Sync.waitElementVisible("id", "customer-email");
			Common.textBoxInput("id", "customer-email","varaprasad.raikanti@gmail.com");
			Thread.sleep(5000);
			String emailid = Common.findElement("id", "customer-email").getAttribute("value");
			System.out.println("*****" + emailid + "*******");
			Shippingaddress.put("emailid", emailid);

		} catch (NoSuchElementException e) {

			Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));

		}

		String expectedResult = "email field will have email address";
		try {
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
			Thread.sleep(3000);
			String ShippingFirstName = Common.findElement("xpath", "//input[@name='firstname']").getAttribute("value");
			System.out.println("*****" + ShippingFirstName + "*******");
			Shippingaddress.put("ShippingFisrtName", ShippingFirstName);
			int size = Common.findElements("id", "customer-email").size();
			Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,
					"Filled Email address", "unabel to fill the email address");
			Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
			Thread.sleep(2000);
			String ShippingLastName = Common.findElement("xpath", "//input[@name='lastname']").getAttribute("value");
			System.out.println("*****" + ShippingLastName + "*******");
			Shippingaddress.put("ShippingLastName", ShippingLastName);

			Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
			Thread.sleep(2000);
			String ShippingAddress1 = Common.findElement("xpath", "//input[@name='street[0]']").getAttribute("value");
			System.out.println("*****" + ShippingAddress1 + "*******");
			Shippingaddress.put("ShippingAddress1", ShippingAddress1);
			// String Text=Common.getText("xpath",
			// "//form[@id='co-shipping-form']//input[@name='street[0]']");

			try {
				Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT,
						data.get(dataSet).get("Region"));
				Thread.sleep(2000);
				String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
				String Shippingstate = Common.findElement("xpath", "//select[@name='region_id']//option[@value='" + Shippingvalue + "']")
						.getText();

				Shippingaddress.put("ShippingState", Shippingstate);
			} catch (ElementClickInterceptedException e) {
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}

			Thread.sleep(2000);
			Common.textBoxInputClear("name", "city");
			Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
			Thread.sleep(2000);
			String ShippingCity = Common.findElement("name", "city").getAttribute("value");
			System.out.println("*****" + ShippingCity + "*******");
			Shippingaddress.put("ShippingCity", ShippingCity);
			Thread.sleep(5000);

			Thread.sleep(2000);
			Common.textBoxInputClear("name", "postcode");
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Thread.sleep(2000);
			String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
			System.out.println("*****" + ShippingZip + "*******");
			Shippingaddress.put("ShippingZip", ShippingZip);
			Thread.sleep(5000);

			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
			Thread.sleep(2000);
			String ShippingPhone = Common.findElement("name", "telephone").getAttribute("value");
			System.out.println("*****" + ShippingPhone + "*******");
			Shippingaddress.put("ShippingPhone", ShippingPhone);

		}

		catch (Exception | Error e) {

			ExtenantReportUtils.addFailedLog("validating shipping address",
					"shipping address is filled in to the fields", "user faield to fill the shipping address",
					Common.getscreenShotPathforReport("shipingaddressfaield"));
			// ExtenantReportUtils.addFailedLog("User click check out button",
			// "User unabel click the checkout button",
			// Common.getscreenShotPathforReport("check out miniCart"));
			Assert.fail();

		}
		Thread.sleep(5000);
		int size = Common.findElements("xpath", "//label[@for='s_method_tablerate_bestway']").size();
		if (size > 0) {
			Common.clickElement("xpath", "//label[@for='s_method_tablerate_bestway']");
		}

		System.out.println(Shippingaddress);
		return Shippingaddress;
	}
	
	public HashMap<String,String> addBillingAddress_validation(String dataSet) throws Exception {
		HashMap<String, String> Billingaddress = new HashMap<String, String>();

		Sync.waitPageLoad();
		Thread.sleep(5000);// //button[contains(@class,'action-edit-address')]
        try {
		Common.findElements("xpath","(//label[@for='billing-address-same-as-shipping-ime_paymetrictokenize'])");
		Common.clickElement("xpath","(//label[@for='billing-address-same-as-shipping-ime_paymetrictokenize'])");
		Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='firstname']");
				Thread.sleep(5000);
				Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='firstname']",data.get(dataSet).get("FirstName"));
				Thread.sleep(3000);
				String BillingFirstName = Common.findElement("xpath", "//input[@name='firstname']").getAttribute("value");
				System.out.println("*****" + BillingFirstName + "*******");
				Billingaddress.put("BillingFisrtName", BillingFirstName);
				Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='lastname']");
				Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='lastname']",
						data.get(dataSet).get("LastName"));
				Thread.sleep(2000);
				String BillingLastName = Common.findElement("xpath", "//input[@name='lastname']").getAttribute("value");
				System.out.println("*****" + BillingLastName + "*******");
				Billingaddress.put("BillingLastName", BillingLastName);
				
				/*
				 * Sync.waitElementPresent("xpath",
				 * "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']"
				 * ); Common.textBoxInput("xpath",
				 * "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']"
				 * ,data.get(dataSet).get("CompanyName"));
				 */
				Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='company']");
				Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='company']",
						data.get(dataSet).get("CompanyName"));
				Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='street[0]']");
				Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='street[0]']",
						data.get(dataSet).get("Street"));
				String BillingAddress1 = Common.findElement("xpath", "//input[@name='street[0]']").getAttribute("value");
				System.out.println("*****" + BillingAddress1 + "*******");
				Billingaddress.put("BillingAddress1", BillingAddress1);
				Common.dropdown("xpath", "//fieldset[contains(@class,'fieldset')]//select[@name='region_id']",
						Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				Thread.sleep(2000);
				String Billingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
				String BillingState = Common
						.findElement("xpath", "//select[@name='region_id']//option[@value='" + Billingvalue + "']")
						.getText();

				Billingaddress.put("BillingState", BillingState);
				Thread.sleep(4000);
				Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='city']",
						data.get(dataSet).get("City"));
				Thread.sleep(2000);
				String BillingCity = Common.findElement("name", "city").getAttribute("value");
				System.out.println("*****" + BillingCity + "*******");
				Billingaddress.put("BillingCity", BillingCity);
				Thread.sleep(5000);
				
				// Common.dropdown("xpath",
				// "//div[contains(@name,'billingAddressime_paymetrictokenize')]//select[@name='region_id']",
				// Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				// Thread.sleep(4000);
				Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='postcode']",
						data.get(dataSet).get("postcode"));
				Thread.sleep(2000);
				String BillingZip = Common.findElement("name", "postcode").getAttribute("value");
				System.out.println("*****" + BillingZip + "*******");
				Billingaddress.put("BillingZip", BillingZip);
				Thread.sleep(5000);
				Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='telephone']",
						data.get(dataSet).get("phone"));
				Thread.sleep(2000);
				String BillingPhone = Common.findElement("name", "telephone").getAttribute("value");
				System.out.println("*****" + BillingPhone + "*******");
				Billingaddress.put("BillingPhone", BillingPhone);

				Thread.sleep(3000);
				//Common.clickElement("xpath", "(//li[@alt='American Express'])[1]");
				Common.scrollIntoView("xpath", "//button[contains(@class,'action-update')]");
				// Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(5000);
				Common.mouseOverClick("xpath", "//button[contains(@class,'action-update')]");

				Thread.sleep(5000);
				int sizeerrormessage = Common
						.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
				System.out.println("error messagess    " + sizeerrormessage);
				Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying Billing addres filling ",
						"user will fill the all the billing address",
						"user fill the shipping address click save button", "faield to add new shipping address");
			
		}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying Billing addres filling",
						"user will fill the all the Billing address", "faield to add new billing address",
						Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
				Assert.fail();

			}
			System.out.println(Billingaddress);
			//return Billingaddress;
		
		return Billingaddress;
	}
	
	public HashMap<String, String> orderamountinfromation() {
		// TODO Auto-generated method stub
		HashMap<String, String> data = new HashMap<String, String>();
		try {
			Thread.sleep(5000);
			String subtotla = Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
			System.out.println("***********"+subtotla+"*************");
			data.put("subtotlaValue", subtotla);
			String shippingammount = Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
			System.out.println("***********"+shippingammount+"*************");
			data.put("shippingammountvalue", shippingammount);
			String TaxAmmount = Common.getText("xpath", "//td[@data-th='Tax']//span").replace("$", "");
			System.out.println("***********"+TaxAmmount+"*************");
			data.put("Taxammountvalue", TaxAmmount);
			String TotalAmmount = Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
			System.out.println("***********"+TotalAmmount+"*************");
			data.put("TotalAmmount", TotalAmmount);

			Common.assertionCheckwithReport(!subtotla.equals(null), "verifying order amout detiles",
					"getting all the Billing ammount infromation",
					"successfully get the total billing amount infromation ", "faiel to get billing ammount");
		} catch (Exception | Error e) {
			report.addFailedLog("verifying tax billing amount", "getting price values from billing  page  ",
					"Faield to get price value from billing page",
					Common.getscreenShotPathforReport("TaxRatesbilling"));

			e.printStackTrace();
			Assert.fail();

		}
		return data;

	}
	
	public HashMap<String, String> add_PaymentDetails(String dataSet) throws Exception {

		HashMap<String, String> Payment = new HashMap<String, String>();
		Thread.sleep(8000);
		String expectedResult = "land on the payment section";

		try {

			Sync.waitElementClickable("xpath", "(//label[@for='ime_paymetrictokenize'])");
			int sizes = Common.findElements("xpath", "(//label[@for='ime_paymetrictokenize'])").size();
			Common.assertionCheckwithReport(sizes > 0, "Successfully land on the payment section", expectedResult,
					"User unabel to land on paymentpage");
			Common.clickElement("xpath", "(//label[@for='ime_paymetrictokenize'])");
			Thread.sleep(5000);
			Common.switchFrames("id", "paymetric_xisecure_frame");
			Thread.sleep(4000);
			Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
				String Cardtype=Common.findElement("xpath", "//select[@id='c-ct']").getAttribute("value");
				String Card=Common.findElement("xpath","//select[@id='c-ct']//option[@value='"+Cardtype+"']").getText();
			    Payment.put("Card", Card);
			    System.out.println("******" +Card+ "*****");
			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
			String input = Common.findElement("id", "c-cardnumber").getAttribute("value");
			String Cardnumber= input.substring(input.length() - 4);
			System.out.println("******" +Cardnumber+ "*****");
			Payment.put("Cardnumber", Cardnumber);
			Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT,data.get(dataSet).get("ExpMonth"));
			String ExpMonth = Common.findElement("xpath", "//select[@id='c-exmth']").getAttribute("value");
			System.out.println("*******" +ExpMonth+ "****");
			Payment.put("ExpMonth", ExpMonth);
			Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
			String ExpYear = Common.findElement("xpath", "//select[@id='c-exyr']").getAttribute("value");
			System.out.println("*******" +ExpYear+ "****");
			Payment.put("ExpYear", ExpYear);
			Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));
			String cvv = Common.findElement("id", "c-cvv").getAttribute("value");
			System.out.println("*******" +cvv+ "****");
			Payment.put("cvv", cvv);
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.switchToDefault();
			Thread.sleep(2000);
			Common.mouseOverClick("xpath", "//span[text()='Place Order']");

			// Common.clickElement("xpath", "//button[@title='Place Order']");

		}

		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
					"faield  to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));

			Assert.fail();
		}

		expectedResult = "credit card fields are filled with the data";
		String errorTexts = Common.findElement("xpath", "//div[contains(@id,'error')]").getText();
		Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data",
				expectedResult, "Filled the Card detiles", "missing field data it showinng error");
		return Payment;
	}
	
public String Transactionid(String Ordernumber) {
		
		{
			
       String TransactionID="";
       
		try {
			Thread.sleep(180000);
			Common.findElement("xpath", "//li[@id='menu-magento-sales-sales']").click();

			Thread.sleep(5000);

			Common.clickElement("xpath", "(//span[text()='Orders'])[1]");
			
			
			Common.findElement("xpath", "(//div[text()='"+Ordernumber+"'])");
            Common.clickElement("xpath", "(//div[text()='"+Ordernumber+"'])");
			Thread.sleep(5000);
			  Common.clickElement("xpath", "(//span[text()='Transactions'])[2]"); 
			  Sync.waitPageLoad();
			  Thread.sleep(3000);
			  TransactionID=Common.findElement("xpath", "(//td[@data-column='txn_id'])[2]").getText();
			  System.out.println(TransactionID);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Orders"),
					"Validating transaction id in admin", "User must land on transaction id in admin",
					"user sucessfully navigating to  transaction id page ", "fail to navigate transaction id page");

			/*
			 * Thread.sleep(5000); //Common.dropdown("xpath",
			 * "(//select[@name='profile_id'])", Common.SelectBy.TEXT,
			 * data.get(dataset).get("ProfileID")); Common.findElement("xpath",
			 * "(//td[@data-column='txn_id'])[2]").getText(); //Common.textBoxInput("id",
			 * "(//td[@data-column='txn_id'])[2]", "varaprasad.raikanti@gmail.com");
			 * Thread.sleep(5000); Common.actionsKeyPress(Keys.ENTER); Thread.sleep(5000);
			 * 
			 * TransactionID=Common.findElement("xpath",
			 * "(//div[@class='data-grid-cell-content'])[4]").getText();
			 * System.out.println(TransactionID);
			 */
	      
		} catch (Exception | Error e) {
            e.printStackTrace();
           // ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
            Assert.fail();
        }
		return TransactionID;
    }}

public void shippingvalidaing_GustUserXML(HashMap<String, String> shippingaddress, String ordernumber)
		throws IOException, Exception {
	ArrayList<String> orderxmlinfromation = new ArrayList<String>();
	try {
		Thread.sleep(5000);
		String fileName = System.getProperty("user.dir") + "\\TestLogs\\Download\\Export_" + ordernumber + ".xml";
		// String fileName="C:\\Users\\admin\\Downloads\\Export_4000420945A.xml";
		Map<String, Object> jsonInMap = xmlReader.fetchvalues(fileName);

		String ShippingFirstName = (String) jsonInMap.get("ShippingFirstName");
		String ShippingLastName = (String) jsonInMap.get("ShippingLastName");
		String ShippingAddress1 = (String) jsonInMap.get("ShippingAddress1");
		String ShippingCity = (String) jsonInMap.get("ShippingCity");
		String ShippingState = (String) jsonInMap.get("ShippingState");
		String ShippingZip = (String) jsonInMap.get("ShippingZip");
		String customermail = (String) jsonInMap.get("cust_to_email");

		// ArrayList<String> orderxmlinfromation=new ArrayList<String>();

		orderxmlinfromation.add("ShippingFirstName=" + ShippingFirstName);
		orderxmlinfromation.add("ShippingLastName=" + ShippingLastName);
		orderxmlinfromation.add("ShippingAddress1=" + ShippingAddress1);
		orderxmlinfromation.add("ShippingCity=" + ShippingCity);
		orderxmlinfromation.add("ShippingState=" + ShippingState);
		orderxmlinfromation.add("ShippingZip=" + ShippingZip);

		String SfCustomerEmail = shippingaddress.get("emailid");
		String Sffirstname = shippingaddress.get("ShippingFisrtName");
		String SfLastname = shippingaddress.get("ShippingLastName");
		String SfStreet = shippingaddress.get("ShippingAddress1");
		String Sfcity = shippingaddress.get("ShippingCity");
		String SfRegion = shippingaddress.get("ShippingState");
		String Sfpostcode = shippingaddress.get("ShippingZip");

		orderxmlvalidations("customerEmail", customermail, SfCustomerEmail);
		orderxmlvalidations("Shiiping first name", ShippingFirstName, Sffirstname);
		orderxmlvalidations("Shiiping last name", ShippingLastName, SfLastname);
		orderxmlvalidations("Shiiping Street address", ShippingAddress1, SfStreet);
		orderxmlvalidations("Shiiping City ", ShippingCity, Sfcity);
		orderxmlvalidations("Shiiping State", ShippingState, SfRegion);

		orderxmlvalidations("Shiiping shipping zip code", ShippingZip, Sfpostcode);

		Common.assertionCheckwithReport(
				SfCustomerEmail.contains(customermail) && ShippingFirstName.contains(Sffirstname)
						&& SfLastname.contains(ShippingLastName) && SfStreet.contains(ShippingAddress1)
						&& Sfcity.contains(ShippingCity) && ShippingZip.contains(Sfpostcode),
				"Validating orderxml infromation with order shipping address",
				"Order shipping address shoud match to order export xml adress",
				"sucessfully order xml infromation matches to export xml adress",
				" un match the storefront shipping address" + shippingaddress + "    with  "
						+ "   Orderxml infromation" + orderxmlinfromation);
	}

	catch (Exception | Error e) {

		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("Validating orderxml infromation with order shipping address",
				"Order shipping address shoud match to order export xml adress",
				"un match the storefront shipping address" + shippingaddress + "    with  "
						+ "   Orderxml infromation" + orderxmlinfromation,
				Common.getscreenShotPathforReport("shipingaddressfaieldxml"));
		// ExtenantReportUtils.addFailedLog("User click check out button",
		// "User unabel click the checkout button",
		// Common.getscreenShotPathforReport("check out miniCart"));
		Assert.fail();

	}

}

public void Billingaddress_GustUserXML_Validation(HashMap<String,String> Billingaddress,String ordernumber) throws IOException, Exception {
	 ArrayList<String> orderxmlinfromation=new ArrayList<String>();
try {	 
Thread.sleep(5000);
String fileName=System.getProperty("user.dir")+"\\TestLogs\\Download\\Export_"+ordernumber+".xml";

Map<String, Object> jsonInMap=xmlReader.fetchvalues(fileName);                           			
String BillingFirstName= (String) jsonInMap.get("BillingFirstName");
String BillingLastName= (String) jsonInMap.get("BillingLastName");
String BillingAddress1= (String) jsonInMap.get("BillingAddress1");
String BillingCity= (String) jsonInMap.get("BillingCity");
String BillingState= (String) jsonInMap.get("BillingState");
String BillingZip=(String) jsonInMap.get("BillingZip");


//ArrayList<String> orderxmlinfromation=new ArrayList<String>();

orderxmlinfromation.add("BillingFirstName="+BillingFirstName);
orderxmlinfromation.add("BillingLastName="+BillingLastName);
orderxmlinfromation.add("BillingAddress1="+BillingAddress1);
orderxmlinfromation.add("BillingCity="+BillingCity);
orderxmlinfromation.add("BillingState="+BillingState);
orderxmlinfromation.add("BillingZip="+BillingZip);

String Sffirstname=Billingaddress.get("BillingFisrtName");
String SfLastname =Billingaddress.get("BillingLastName");
String SfStreet =Billingaddress.get("BillingAddress1");
String Sfcity =Billingaddress.get("BillingCity");
String SfRegion =Billingaddress.get("BillingState");
String Sfpostcode =Billingaddress.get("BillingZip");


orderxmlvalidations("Billing first name",BillingFirstName,Sffirstname);
orderxmlvalidations("Billing last name",BillingLastName, SfLastname);
orderxmlvalidations("Billing Street address", BillingAddress1, SfStreet);
orderxmlvalidations("Billing City ", BillingCity, Sfcity);
orderxmlvalidations("Billing State", BillingState, SfRegion);

orderxmlvalidations("Billing  zip code", BillingZip, Sfpostcode);

Common.assertionCheckwithReport(BillingFirstName.contains(Sffirstname)&&SfLastname.contains(BillingLastName)&&SfStreet.contains(BillingAddress1)&&Sfcity.contains(BillingCity)&& BillingZip.contains(Sfpostcode), "Validating orderxml infromation with order Billing address","Order Billing address shoud match to order export xml adress","sucessfully order xml infromation matches to export xml  Billing adress"," un match the storefront Billing address"+Billingaddress+"    with  "+"   Orderxml infromation"+orderxmlinfromation);
}

catch (Exception | Error e) {

	e.printStackTrace();
		ExtenantReportUtils.addFailedLog("Validating orderxml infromation with order shipping address","Order shipping address shoud match to order export xml adress","un match the storefront shipping address"+Billingaddress+"    with  "+"   Orderxml infromation"+orderxmlinfromation,
				Common.getscreenShotPathforReport("shipingaddressfaieldxml"));
		
		Assert.fail();
		
	}

}	

public void TotalvalidationXML(HashMap<String, String> ordertaxammount, String Order) throws IOException, Exception {
	
	Thread.sleep(5000);
	ArrayList<String> orderxmlinfromation = new ArrayList<String>();
	String fileName = System.getProperty("user.dir") + "\\TestLogs\\Download\\Export_" + Order + ".xml";
	// String fileName="C:\\Users\\admin\\Downloads\\Export_4000420945A.xml";
	Map<String, Object> jsonInMap = xmlReader.fetchvalues(fileName);

	String OrderShippingCosts = (String) jsonInMap.get("OrderShippingCosts");
	String tax_amt = (String) jsonInMap.get("tax_amt");
	String order_total = (String) jsonInMap.get("order_total");
	
	orderxmlinfromation.add("OrderShippingCosts=" + OrderShippingCosts);
	orderxmlinfromation.add("tax_amt=" + tax_amt);
	orderxmlinfromation.add("order_total=" + order_total);
	

	String Sfshipping = ordertaxammount.get("shippingammountvalue");
	String SfTaxamount = ordertaxammount.get("Taxammountvalue");
	String Sforder = ordertaxammount.get("TotalAmmount");

	System.out.println(order_total.contains(Sforder));
	System.out.println(tax_amt.contains(SfTaxamount));
	System.out.println(Sfshipping.contains(OrderShippingCosts));
	
	orderxmlvalidations("OrderShippingCosts", OrderShippingCosts, Sfshipping);
	orderxmlvalidations("Tax Ammount", tax_amt, SfTaxamount);
	orderxmlvalidations("Order Total", order_total, Sforder);
	

	Common.assertionCheckwithReport(
			order_total.contains(Sforder) && tax_amt.contains(SfTaxamount)
					&& OrderShippingCosts.contains(Sfshipping),
			"verify Tax and total ammount shipping cost with on order xml", "Address must  matching to orderxml ",
			"product shiping tax and order total " + data + " Matches to xml infromation " + "OrderShippingCosts="
					+ OrderShippingCosts + " tax_amt=" + tax_amt + " prder_total " + order_total,

			"product shiping tax and order total " + data + " is not Matches to xml infromation "
					+ "OrderShippingCosts=" + OrderShippingCosts + " tax_amt=" + tax_amt + " prder_total "
					+ order_total);
}

public void card_details_validationXML(HashMap<String, String> Payment, String Order) throws IOException, Exception {
	
	ArrayList<String> orderxmlinfromation = new ArrayList<String>();
try {

Thread.sleep(5000);
String fileName = System.getProperty("user.dir") + "\\TestLogs\\Download\\Export_" + Order + ".xml";
Map<String, Object> jsonInMap = xmlReader.fetchvalues(fileName);
Map<String, Object> xml = xmlReader.stringToMapTest(jsonInMap.get("Payments").toString());
	//String payment_meth = (String) jsonInMap.get("payment_meth");
	String CardType = (String) xml.get("CardType");
	System.out.println(CardType);
	String Last4 = (String) xml.get("Last4");
	System.out.println(Last4);
	String CardExpirationMonth = (String) xml.get("CardExpirationMonth");
	System.out.println(CardExpirationMonth);
	String CardExpirationYear = (String) xml.get("CardExpirationYear");
	System.out.println(CardExpirationYear);
	// String cvv= (String) jsonInMap.get("cvv");
    
	orderxmlinfromation.add("CardType=" + CardType);
	orderxmlinfromation.add("Last4=" + Last4);
	orderxmlinfromation.add("CardLast4Digits=" + CardExpirationMonth);
	orderxmlinfromation.add("CardExpirationMonth=" + CardExpirationMonth);
	orderxmlinfromation.add("CardExpirationYear=" + CardExpirationYear);
	
	String Sfcardtype = Payment.get("Card");
	System.out.println(Sfcardtype);
	String Sfcardnumber = Payment.get("Cardnumber");
	System.out.println(Sfcardnumber);
	String Sfexpmonth = Payment.get("ExpMonth");
	System.out.println(Sfexpmonth);
	String Sfexpyear = Payment.get("ExpYear");
	System.out.println(Sfexpyear);
	// String Sfcvv =Payment.get("cvv");

	orderxmlvalidations("customer card type", CardType, Sfcardtype);
	orderxmlvalidations("Customer Card Last 4", Last4, Sfcardnumber);
	orderxmlvalidations("card expire month", CardExpirationMonth, Sfexpmonth);
	orderxmlvalidations("card expire year", CardExpirationYear, Sfexpyear);
	Common.assertionCheckwithReport(
			CardType.contains(Sfcardtype) && Sfcardnumber.equals(Last4) && CardExpirationMonth.contains(Sfexpmonth)
					&& CardExpirationYear.contains(Sfexpyear),
			"verify address should match on order xml",
			"store front application data matches to order xml infromatio",
			"sucessfully matchs the web infromaation with order xml",
			"faield to match web order infromation with order xml");
}catch (Exception | Error e) {



e.printStackTrace();
ExtenantReportUtils.addFailedLog("Validating orderxml infromation with order shipping address",
"Order shipping address shoud match to order export xml adress",
"un match the storefront shipping address" + Payment + " with "
+ " Orderxml infromation" + orderxmlinfromation,
Common.getscreenShotPathforReport("shipingaddressfaieldxml"));
//ExtenantReportUtils.addFailedLog("User click check out button",
//"User unabel click the checkout button",
//Common.getscreenShotPathforReport("check out miniCart"));
Assert.fail();

}

}

public void ValidatingTranctionIDInXML(String Ordernumber,String Transaction) {
	 try {
	 Thread.sleep(5000);
	 String fileName=System.getProperty("user.dir")+"\\TestLogs\\Download\\Export_"+Ordernumber+".xml";
	 Map<String, Object> jsonInMap=xmlReader.fetchvalues(fileName);
	 String XMLTrantionID=jsonInMap.get("TransactionID").toString();
	 orderxmlvalidations("TransactionID", XMLTrantionID, Transaction);
	 Common.assertionCheckwithReport(XMLTrantionID.contains(Transaction), "Validating  Weborder Trantion id with Order xml trantion id", "Admin web trantion is must match to xml trantion id", "success fully matches admin web order trantionid with xml trantion id", "fail to match web trantion id with orderxml trantion id");	 
	 }
	 catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("Validating  Weborder Trantion id with Order xml trantion id", "Admin web trantion is must match to xml trantion id ",
				"fail to match web trantion id with orderxml trantion id",
				Common.getscreenShotPathforReport("TrantionIDXMLValidation"));
		Assert.fail();
}
	 
	 }
}