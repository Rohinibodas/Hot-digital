package TestComponent.DryBar;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;

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
	Sync.waitElementPresent("xpath", "//a[@class='account-link top-link']");
	Common.clickElement("xpath", "//a[@class='account-link top-link']");
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
	//Common.clickElement("xpath", "//input[@id='assistance_allowed_checkbox']");
	Common.textBoxInput("id", "email_address",data.get(dataSet).get("Email"));
	Common.textBoxInput("id", "password",data.get(dataSet).get("Password"));
	Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
	Common.clickElement("xpath", "//button[@title='Create an Account']");
	ExtenantReportUtils.addPassLog("verifying Create My account Page", "It should lands on MyAccount  Page", "user lands on My Account  Page", Common.getscreenShotPathforReport("My Account page"));
	
	}
	 catch(Exception |Error e) {
	        ExtenantReportUtils.addFailedLog("verifying  MyAccount page", "Account should be created successfully navigate to My Account page", "user faield to create account", Common.getscreenShotPathforReport("createaccountfaield"));
			Assert.fail();
		}
	
}


public void clickHairProducts(){
	try{
		Sync.waitElementPresent("xpath", "//span[text()='Hair Products']");
	Common.clickElement("xpath", "//span[text()='Hair Products']");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Hair Products | Drybar"), "verifying Hair Product category","User navigate to hair product page","user successfully open the Hair Category page", "user faield to click the Hair Product");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Hair Product category", "User navigate to hair product page", "user faield to click the Hair Product", Common.getscreenShotPathforReport("hairproduct"));
			Assert.fail();
		}
	
}

public void Hair_ProductsMegamenuValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
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
		Sync.waitElementPresent("xpath", "(//li[@class='item category-item'])[8]");
	Common.clickElement("xpath", "(//li[@class='item category-item'])[8]");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Shampoos - Sulfate-Free Shampoo & Hair Products | Drybar"), "verifying Hair Product sub-category","User navigate to hair product sub-category page","user successfully open the Hair sub-Category page", "user faield to click the Hair Product sub-category");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Hair Product category", "User navigate to hair product page", "user faield to click the Hair Product", Common.getscreenShotPathforReport("hairproduct"));
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




public void Select_sort(String dataSet){
	
	
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.mouseOver("id", "sorter");
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("MostViewed"));
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("verifying Most Viewed sort option", "User should select Most Viewed sort", "user faield to Select the Most Viewed sort option", Common.getscreenShotPathforReport("Most Viewed"));
		
	 // Common.assertionCheckwithReport(name.equals("Blonde Ale Brightening Shampoo"), "verifying Hair Product sub-category","User navigate to hair product sub-category page","user successfully open the Hair sub-Category page", "user faield to click the Hair Product sub-category");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying  Most Viewed sort option", "User should  select Most Viewed sort option", "user Successfully Selected the Most Viewed Sort option", Common.getscreenShotPathforReport("Most Viewed"));
			Assert.fail();
		}
	
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("Top Rated"));
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("verifying Top Rated sort option", "User should select Top Rated sort", "user faield to Select the Top Rated sort option", Common.getscreenShotPathforReport("Top Rated"));
		
	 // Common.assertionCheckwithReport(name.equals("Blonde Ale Brightening Shampoo"), "verifying Hair Product sub-category","User navigate to hair product sub-category page","user successfully open the Hair sub-Category page", "user faield to click the Hair Product sub-category");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Top Rated sort option", "User should  select Top Rated sort option", "user Successfully selected the Top Rated Sort option", Common.getscreenShotPathforReport("Top Rated"));
			Assert.fail();
		}
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("New"));
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("verifying New sort option", "User should select New sort", "user faield to Select the New sort option", Common.getscreenShotPathforReport("New"));
		
	 // Common.assertionCheckwithReport(name.equals("Blonde Ale Brightening Shampoo"), "verifying Hair Product sub-category","User navigate to hair product sub-category page","user successfully open the Hair sub-Category page", "user faield to click the Hair Product sub-category");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying New sort option", "User should  select New sort option", "user Successfully Selected the  New Sort option", Common.getscreenShotPathforReport("New"));
			Assert.fail();
		}
	
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("low to high"));
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("verifying low to high sort option", "User should select low to high sort", "user faield to Select the low to high sort option", Common.getscreenShotPathforReport("low to high"));
		
	 // Common.assertionCheckwithReport(name.equals("Blonde Ale Brightening Shampoo"), "verifying Hair Product sub-category","User navigate to hair product sub-category page","user successfully open the Hair sub-Category page", "user faield to click the Hair Product sub-category");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying low to high sort option", "User should  select low to high sort option", "user faield to Select the low to high Sort option", Common.getscreenShotPathforReport("low to high"));
			Assert.fail();
		}
	
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("high to low"));
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("verifying high to low sort option", "User should select high to low sort", "user faield to Select the high to low sort option", Common.getscreenShotPathforReport("high to low"));
		
	 // Common.assertionCheckwithReport(name.equals("Blonde Ale Brightening Shampoo"), "verifying Hair Product sub-category","User navigate to hair product sub-category page","user successfully open the Hair sub-Category page", "user faield to click the Hair Product sub-category");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying high to low sort option", "User should  select high to low sort option", "user faield to Select the high to low Sort option", Common.getscreenShotPathforReport("high to low"));
			Assert.fail();
		}
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("Best Sellers"));
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("verifying Best Sellers sort option", "User should select Best Sellers sort", "user faield to Select the best sellers sort option", Common.getscreenShotPathforReport("Best Sellers"));
		
	 // Common.assertionCheckwithReport(name.equals("Blonde Ale Brightening Shampoo"), "verifying Hair Product sub-category","User navigate to hair product sub-category page","user successfully open the Hair sub-Category page", "user faield to click the Hair Product sub-category");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Best Sellers sort option", "User should  select Best sellers sort option", "user faield to Select the best sellers Sort option", Common.getscreenShotPathforReport("Best Sellers"));
			Assert.fail();
		}
	
}
public void Selectproduct(){
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "(//a[@class='product-item-link'])[1]");
	Common.clickElement("xpath", "(//a[@class='product-item-link'])[1]");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Liquid Glass Smoothing Shampoo | Drybar"), "verifying Liquid glass smooothing PLP","Should land on Liquid glass smoothing shampoo PDP","user successfully landed on Liquid glass smoothing shampoo PDP", "user faield to click on Liqui glass smoothing shampoo product");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Hair Product category PLP", "User should select liquid glass smoothing shampoo product", "user faield to select liquid glass smoothing shampoo product", Common.getscreenShotPathforReport("FAILED to select  liquid glass smoothing shampoo product"));
			Assert.fail();
		}
	
}
public void Select_Size() throws Exception {
	try {
		
		Sync.waitElementPresent("xpath", "(//div[(text()='Original')])");
		Common.clickElement("xpath", "(//div[(text()='Original')])");
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "(//div[(text()='Full')])");
		Common.clickElement("xpath", "(//div[(text()='Full')])");
		Sync.waitPageLoad();
		String FinalPrice=Common.getText("xpath", "(//span[@data-price-type='finalPrice'])[1]");
		Common.assertionCheckwithReport(FinalPrice.equals("$23.00"),"To Verify the  Swath option","It should select swath options", "successfully selected Swatch options", "Swatch options");
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


public void clickAddtoBag() throws Exception{
	try{
	Sync.waitPageLoad();
	Thread.sleep(5000);
	
	Common.clickElement("id", "product-addtocart-button");
	
	ExtenantReportUtils.addPassLog("verifying add to cart button", "User click add to card button", "user successfully click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
	}
	catch(Exception |Error e) {
	   
		ExtenantReportUtils.addFailedLog("verifying add to cart button", "User click add to card button", "user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
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
		
		//Sync.waitPageLoad();
		//Sync.waitElementPresent("xpath", "(//span[contains(text(),'contact@drybar.com')])");
		Common.clickElement("xpath", "(//button[@data-action='show-how-to'])");
		//Common.switchToSecondTab();
		//Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("To verify the How to use step by step Functionality","Should diaply How to use pop-up page", "user unable to  navigate to  How to Use pop-up page", Common.getscreenShotPathforReport("failed to land on How to Use pop-up page"));			
		
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the How to use step by step Functionality ","should land on How to Use pop-up page", "user unable to navigate to How to Use pop-up page", Common.getscreenShotPathforReport("failed to land on How to Use pop-up page"));			
			Assert.fail();	
			}
		}
		


public void clickminiCartButton() throws Exception{
	try{
		Thread.sleep(3000);
	Sync.waitElementPresent("xpath", "//a[contains(@class,'showcart')]");
    Common.clickElement("xpath", "//a[contains(@class,'showcart')]");
    
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
		Thread.sleep(6000);
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		//Sync.scrollSideToView("xpath", "(//button[@class='action primary checkout'])");
	 Sync.waitElementPresent("xpath", "(//button[@class='action primary checkout'])");
	 Thread.sleep(4000);
	   Common.findElement("xpath", "(//button[@class='action primary checkout'])").click();
	   
	  //Common.assertionCheckwithReport(checkoutbuttonSize>0, "verifying mini cart button", "User click mini cart button", "user successfully click mini cart button", "Failed click mini cart button");
	}
	    catch(Exception |Error e) {
	 	   
			ExtenantReportUtils.addFailedLog("verifying checkOut button", "User click checkOut  button", "user faield to click checkOut button", Common.getscreenShotPathforReport("checkOut"));
			Assert.fail();
		}
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
         Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
          ExtenantReportUtils.addPassLog("verifying PaymetricPaymentMethod", "user click PaymetricPaymentMethod ", "user clicked PaymetricPaymentMethod option",Common.getscreenShotPathforReport("PaymetricPaymentMethodoption"));
	}
	catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying PaymetricPaymentMethod", "user click PaymetricPaymentMethod ", "faield to click PaymetricPaymentMethod",Common.getscreenShotPathforReport("PaymetricPaymentMethodoption"));
		Assert.fail();
		
	} 
}
public void Edit_BillingAddress_PaymetricPaymentMethod(String dataSet)throws Exception{
	
	Thread.sleep(3000);
	
		int sizes=Common.findElements("xpath", "//div[@id='ime_paymetrictokenize_method_form']//button[contains(@class,'action-edit-address')]").size();
		if(sizes>0){
         Common.clickElement("xpath", "//div[@id='ime_paymetrictokenize_method_form']//button[contains(@class,'action-edit-address')]");
try{
	    int selectbutton=Common.findElements("xpath", "//select[@name='billing_address_id']").size();
	
	    if(selectbutton>0){
	    	Common.dropdown("xpath", "//select[@name='billing_address_id']", SelectBy.TEXT, "New Address");
	    }
	    
         Sync.waitElementPresent("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='firstname']");
     	Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='firstname']",data.get(dataSet).get("FirstName"));
     	Sync.waitElementPresent("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='lastname']");
     	Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='lastname']",data.get(dataSet).get("LastName"));
     	Sync.waitElementPresent("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='firstname']");
     	Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='firstname']",data.get(dataSet).get("FirstName"));
     	Sync.waitElementPresent("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']");
     	Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']",data.get(dataSet).get("CompanyName"));
     	Sync.waitElementPresent("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='street[0]']");
     	Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='street[0]']", data.get(dataSet).get("Street"));
     	Common.dropdown("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
     	Thread.sleep(4000);
     	Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='city']", data.get(dataSet).get("City"));
     	//Common.dropdown("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
     	//Thread.sleep(4000);
     	Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='postcode']", data.get(dataSet).get("postcode"));
     	Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='telephone']", data.get(dataSet).get("phone"));
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
	//Common.clickElement("xpath","//label[contains(@for,'billing-address-same-as-shipping-ime_paymetrictokenize')]");
	
  
	   /* Common.actionsKeyPress(Keys.PAGE_UP);
	    Thread.sleep(3000);
	    Common.*///dropdown("xpath", "//select[@name='billing_address_id']",Common.SelectBy.TEXT, "New Address");
	}
		
		

   //Common.clickElement("Xpath","//span[contains(text(),'My billing and shipping address are the same')]");
	public void select_USPS_StandardGround_shippingMethod() throws Exception{
		try{
			
			
			//td[text()='USPS - Standard Ground']
			
			
			Sync.waitElementPresent("xpath", "//input[@id='s_method_tablerate_bestway']");
		    Common.clickElement("xpath", "//input[@id='s_method_tablerate_bestway']");
			

		//Sync.waitElementPresent("xpath", "//label[contains(@for,' label_carrier_matrixrate_5_matrixrate')]");
	    //Common.clickElement("xpath", "//label[contains(@for,' label_carrier_matrixrate_5_matrixrate')]");
	
	}
		 catch(Exception |Error e) {
			   e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the shipping method USPS Standard Ground", "click the usps standard shipping methoad", "faield to click usps standard shipping methoad",  Common.getscreenShotPathforReport("faieldshipingmethoad"));
				Assert.fail();
				
			}
	}
public void creditCard_payment(String dataSet) throws Exception{
	
	try{
		
	//	Common.actionsKeyPress(Keys.PAGE_DOWN);
		//Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
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
	//Common.refreshpage();
	Common.switchFrames("id", "paymetric_xisecure_frame");
	Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
	Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
	Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
	Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
	Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));	
	Thread.sleep(2000);
	
	//Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.switchToDefault();
	Thread.sleep(1000);
	//Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
	Common.clickElement("xpath", "(//button[@title='Place Order'])");
	Common.switchFrames("id", "paymetric_xisecure_frame");
	String expectedResult="credit card fields are filled with the data";
    String errorTexts=	Common. findElement("xpath", "//div[contains(@id,'error')]").getText();
    Common.switchToDefault();
    Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data", expectedResult, "Filled the Card detiles", "missing field data it showinng error");
    	
	Sync.waitPageLoad();
	}
	catch(Exception |Error e) {
	    ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", "credit card fields are filled with the data", "faield  to fill the Credit Card infromation",  Common.getscreenShotPathforReport("Cardinfromationfail"));
		Assert.fail();
		
	}
	
	
}
	
public void creditCard_payment_invalid_CC(String dataSet) throws Exception{
	
	try{
		
	//	Common.actionsKeyPress(Keys.PAGE_DOWN);
		//Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
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
	 Common.assertionCheckwithReport(size>0, "Successfully landed on th home page", expectedResult,"User unabel to land on home page");
	try {
		Sync.waitPageLoad();
		Thread.sleep(5000);
		Sync.waitElementClickable(30, By.xpath("//ul[@class='header links']//li[2]/a"));
		Common.findElement("xpath", "//ul[@class='header links']//li[2]/a").click();
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

public void loginApplication(String dataSet){
	String expectedResult = "Opens login pop_up";
   
	   int size= Common.findElements("id", "email").size();
	   Common.assertionCheckwithReport(size>0, "verifying login page", "open the login page", "successfully open the login page", "Failed open the login page");
	   try {
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("id", "send2");
			//navigateMyAccount();
			
			Common.clickElement("xpath", "//ul[@class='header links']//li[2]/span");
			//ul[@class='header links']//li[2]/span
			Common.clickElement("xpath", "//ul[@class='header links']//li[2]//a[text()='My Account']");
			Thread.sleep(6000);
			
			String gettext=Common.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span")).getText();
			System.out.println(gettext);
			Common.assertionCheckwithReport(gettext.equals("MY ACCOUNT"), "verifying login account",
					"customer is redirected to My Account page",
					"Logged in the application and customer is redirected to My Account page",
					"Unabel to login Account");
	    
	   }
	   catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying login page with credentials", expectedResult,
					"User failed to login in account  ", Common.getscreenShotPathforReport("login faield"));
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

			ExtenantReportUtils.addPassLog("validating the shipping address", expectedResult,
					"user add the shipping address",
					Common.getscreenShotPathforReport("faield to add shipping address"));


			Common.clickElement("xpath", "//button[contains(@class,'save-address')]");

			int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

			Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
					"user will fill the all the shipping", "user fill the shiping address click save button",
					"faield to add new shipping address");
			
			
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
	Sync.waitElementPresent("xpath", "//a[@class='action viewcart']");
	Common.clickElement("xpath", "//a[@class='action viewcart']");
	ExtenantReportUtils.addPassLog("verifying the view wdit cart button from mincart page","user after click the  view and edit button it navigate to SHOPPING CART page","User navigate to SHOPPING CART page",Common.getscreenShotPathforReport("SHOPPINGCARTPAGE"));
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the view edit cart button from mincart page","user after click the  view and edit button it navigate to SHOPPING CART page","User unabel navigate to SHOPPING CART page",Common.getscreenShotPathforReport("SHOPPINGCARTPAGE"));
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
public void gitCard(String dataSet) throws Exception{
	try{
		Thread.sleep(5000);
	Common.scrollIntoView("id", "block-giftcard-heading");
	Common.clickElement("id", "block-giftcard-heading");
	
	Thread.sleep(5000);

Common.textBoxInput("id","giftcard-code",data.get(dataSet).get("GiftCardCode"));
	
	Common.textBoxInput("id","giftcard-pin",data.get(dataSet).get("GiftCardPin"));
	
	
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
	
}
public void click_place_order_button(){
	try{
	Common.clickElement("xpath", "//button[contains(@class,'checkout')]");
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


public void couponCode(String dataSet){
	try{
	Common.clickElement("id","block-discount-heading");
	System.out.println(data.get(dataSet).get("couponCode"));
	Thread.sleep(3000);
    Common.textBoxInput("id", "discount-code",data.get(dataSet).get("couponCode"));
    Thread.sleep(2000);
    Common.clickElement("xpath", "//button[@value='Apply Code']");
    
    Thread.sleep(3000);
	int size=Common.findElements("xpath", "//div[contains(@class,'message-success')]/div").size();
	Common.assertionCheckwithReport(size>0, "validating the offer code", "offer code was added.", "successfully offer code added","Failed to added offer code");
    }
	catch (Exception | Error e) {
		e.printStackTrace();
        ExtenantReportUtils.addFailedLog("validating offer code","offer code is applicable","User faield to add offer code",Common.getscreenShotPathforReport("offercode"));
        Assert.fail();
        }
}
public void order_Success() throws Exception{
	//Thread.sleep(10000);
	//Common.textBoxInput("id", "//textarea[contains(@id,'tt-c-comment-field')]","Ceate accounts test ");
	String expectedResult = "It redirects to order confirmation page";
	try{
	Sync.waitPageLoad();
	
	
	
	for(int i=0;i<10;i++){
		
		if(Common.getCurrentURL().contains("success")){
			break;
		}
		Thread.sleep(5000);
	}
	
	String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']/span");
	System.out.println(sucessMessage);
	Common.assertionCheckwithReport(sucessMessage.equals("THANK YOU FOR YOUR PURCHASE!"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");
		
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
				"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
		Assert.fail();
	}
}

public void order_verification() throws Exception {
	try {
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

public void Facebook() {
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
		Common.assertionCheckwithReport(Title.contains("Drybar (thedrybar) - Profile | Pinterest"),"To Verify the  Pinterest Article link","It should navigate to Pinterest page", "successfully  navigated to Pinterest page", "Pinterest");
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

public void Aggree_and_proceed() {
	
	try{
		Sync.waitElementPresent("xpath", "(//button[@id='truste-consent-required'])");
		Common.clickElement("xpath", "(//button[@id='truste-consent-required'])");

        }
	catch (Exception | Error e) {
  
        Assert.fail();
        }

}
	



public void checkorderstatus_footerlink() {
	try{
	    Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
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
		Common.clickElement("xpath","(//a[@title='Shipping & Delivery'])");
		Sync.waitPageLoad();
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
		Common.clickElement("xpath", "(//a[@title='Special Offers'])[2]");
		Sync.waitPageLoad();
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
		Common.clickElement("xpath","(//a[@title='Warranty'])");
		Sync.waitPageLoad();
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
		Common.clickElement("xpath","(//a[@title='Safety Data Sheets'])");
		Sync.waitPageLoad();
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
		Common.clickElement("xpath","(//a[@title='Glossary'])");
		Sync.waitPageLoad();
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
		Common.clickElement("xpath","(//a[@title=\"What's a Blowout\"])");
		Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("What's a Blowout? | Drybar"),"Verifying Blowout page","it shoud navigate to Blowout page", "successfully  navigated to Blowout page", "Blowout");	
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the blowout footer link","navigate to blowout footerlink", "user unable to  navigate to blowout foterlink", Common.getscreenShotPathforReport("failed to navigate to blowout page"));			
			Assert.fail();	
			}
		}

public void WheretoBuy_footerlink() {
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Common.clickElement("xpath","(//a[@title='Where to Buy'])[2]");
		Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Drybar | Premium Hair Care Created for the Perfect Blowout"),"Verifying Where to Buy page","it shoud navigate to Where to buy page", "successfully  navigated to Where to buy", "Where to Buy");	
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying the where to Buy footer link","should navigate to where to Buy footerlink", "user unable navigate to buy foterlink", Common.getscreenShotPathforReport("failed to navigate to wheretoBuy footerlink page"));			
			Assert.fail();	
			}
		}

public void aboutUs_footerlink() {
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Common.clickElement("xpath","(//a[@title='About Us'])");
		Sync.waitPageLoad();
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
		int message=Common.findElements("xpath", "(//div[@class='message-success success message'])[2]").size();

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
	    Common.scrollIntoView("xpath", "(//div[@class='product-item-info'])[3]");
	    Thread.sleep(2000);
	    Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[3]");
	    Thread.sleep(4000);
	   // Common.clickElement("xpath", "");
	    Sync.waitElementPresent("xpath", "(//button[@title='Add to Bag'])[1]");
		Common.mouseOverClick("xpath", "(//button[@title='Add to Bag'])[1]");
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
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		//Common.scrollIntoView("xpath", "(//div[@class='message-success success message'])[2]");
		Thread.sleep(10000);
		Sync.waitPageLoad();
		Thread.sleep(10000);
		int message=Common.findElements("xpath", "(//div[@class='message-success success message'])[2]").size();

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
		ExtenantReportUtils.addPassLog("To verify the PLP Page","should lnd on PLP Page","user  successfully landed on PLP Pagect", Common.getscreenShotPathforReport("user Successfully landed on PLP Page"));
		
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

public void Accept(){

try {

Thread.sleep(5000);

Sync.waitElementPresent("xpath", "//button[@id='truste-consent-required']");

      Common.clickElement("xpath", "//button[@id='truste-consent-required']");

} catch (Exception e) {

// TODO Auto-generated catch block

e.printStackTrace();

}

 

      

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
    
public void Close_popup() throws Exception {
	
	Sync.waitElementPresent("xpath", "(//a[@class='ltkpopup-close'])");
    Common.clickElement("xpath", "(//a[@class='ltkpopup-close'])");
}
 public void Verify_PDP(){

    try {
    	 Sync.waitPageLoad();
          Thread.sleep(2000);
         
  		String Title= Common.getPageTitle();
  		System.out.println(Title);
  		Common.assertionCheckwithReport(Title.equals("Liquid Glass Smoothing Shampoo | Drybar"),"Verifying PDP page","it shoud navigate to PDP page", "successfully  navigated to PDP Page", "PDP Page");
   }catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the  the PDP Page","Should land on PDP page", "user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on PDP page"));			
		Assert.fail();	
		}
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

 public void Click_View_Product(){

	    try {
	    	 Sync.waitPageLoad();
	          Thread.sleep(2000);
	          Common.clickElement("xpath", "(//span[text()='View Product'])[1]");
	         Sync.waitPageLoad();
	         Common.actionsKeyPress(Keys.ARROW_DOWN);
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

Thread.sleep(2000);
Common.clickElement("xpath", "(//a[@class='see-more-link clearfix'])[1]");
Thread.sleep(3000);
//Common.actionsKeyPress(Keys.PAGE_DOWN);
String OSS=Common.findElement("xpath","(//span[contains(text(),'Out of stock')])").getText();
Common.assertionCheckwithReport(OSS.equals("OUT OF STOCK"), "To verify the PDP with out of stock", "Should land on out of stock PDP page","User unable to land on Out of Stock PDP", "faield to land on out of stock PDP page");
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
          Common.actionsKeyPress(Keys.PAGE_DOWN);
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

public void Add_product_to_Wishlist() {
	
	try {
		Sync.waitElementPresent("xpath", "(//a[@class='action towishlist'])[1]");
		Common.clickElement("xpath", "(//a[@class='action towishlist'])[1]");
		Thread.sleep(4000);
		int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();

		 Common.assertionCheckwithReport(message>0, "To verify the product added to My Wishlist", "Should add product to  My wishlist page","Product sucessfully added to My wishlist", "faield to add product to Wishlist");
	}
	catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the  the PDP Page with out of stock","Should land on ou of stock PDP page", "user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on out of stock PDP page"));			
		Assert.fail();	
		}
	
}
public void remove_from_wishlist() throws Exception {
	
	Sync.waitElementPresent("xpath", "(//a[@class='product-item-link'])");
	Common.clickElement("xpath", "(//a[@class='product-item-link'])");
	Sync.waitPageLoad();
	Sync.waitElementPresent("xpath", "(//a[@class='action towishlist'])[1]");
	Common.clickElement("xpath", "(//a[@class='action towishlist'])[1]");
	
}
public void My_Orders(){

    try {
    	Common.clickElement("xpath", "(//a[text()='My Orders'])");
    	Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("My Orders | Drybar"),"Verifying My Orders page","it shoud navigate to My Orders page", "successfully  navigated to My Orders Page", "My Orders");	
		
       
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
		Common.assertionCheckwithReport(Title.equals("My Wish List | Drybar"),"Verifying My Wishlist page","it shoud navigate to My Wishlist page", "successfully  navigated to My Wishlist Page", "My Wishlist");	
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
		Common.assertionCheckwithReport(Title.equals("Address Book | Drybar"),"Verifying AddressBook page","it shoud navigate to AddressBook page", "successfully  navigated to Address Book Page", "AddressBook");	
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
		Common.assertionCheckwithReport(Title.equals("Account Information | Drybar"),"Verifying Account information page","it shoud navigate to Account informaion page", "successfully  navigated to Account information Page", "Account information");
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
	


public void PaymentMethods(){

    try {
    	Common.clickElement("xpath", "(//a[text()='Payment Methods'])");
    	Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Payment Methods | Drybar"),"Verifying Payment Methods page","it shoud navigate to Payment Methods page", "successfully  navigated to Payment Methods Page", "Payment Methods");
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
		Common.assertionCheckwithReport(Title.equals("Communication Preferences | Drybar"),"Verifying Communication preferences page","it should navigate to communication preferences page", "successfully  navigated to Communication preferences page", "Communication preferences");		
    }
    
    	catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Communication Preferences page","Should land on Communication Preferences  page", "user unable to land on Communication Preferences page", Common.getscreenShotPathforReport("failed to land on Communication Preferences page"));			
		Assert.fail();	
		}
	}
public void Subscribe_To_Communication_preferences(){

    try {
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

public void guestShipingAddress(String dataSet) throws Exception{
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
	}
	catch(Exception |Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping", "faield to add new shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
		Assert.fail();
		
	}  
}
	
public void Expediated_shippingmethod(){

    try {
    	Sync.waitElementPresent("xpath", "(//input[@class='radio'])[2]");
    	Thread.sleep(5000);
    	Common.scrollIntoView("xpath", "(//input[@class='radio'])[2]");
    	Thread.sleep(5000);
         Common.clickElement("xpath", "(//input[@class='radio'])[2]");
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
    	Sync.waitPageLoad();
    	Thread.sleep(10000);

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
    	
    	Sync.waitElementPresent("xpath", "(//input[@class='radio'])[3]");
    	Thread.sleep(3000);
    	Common.scrollIntoView("xpath", "(//input[@class='radio'])[3]");
    	Thread.sleep(5000);
         Common.clickElement("xpath", "(//input[@class='radio'])[3]");
          Thread.sleep(3000);
          ExtenantReportUtils.addPassLog("To verify the Express shipping method", "Should click on express shipping method", "user successfully clicked on Express shipping method", Common.getscreenShotPathforReport("Successfully clicked on Express shipping method"));
      	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
    }catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("To verify the Express shipping method","Should click on Express shipping method", "user unable to click on Express shipping method", Common.getscreenShotPathforReport("failed to click on express shipping method"));			
    		Assert.fail();	
    		}
}
  

public void Product_Review (String dataSet) throws Exception{

    try {
    	Thread.sleep(5000);
    	
    	Sync.waitElementPresent("xpath", "(//button[@class='TTteaser__write-review'])");
    	//Common.scrollIntoView("xpath", "(");
         Common.clickElement("xpath", "(//button[@class='TTteaser__write-review'])");
         
          Thread.sleep(3000);
          Common.clickElement("xpath", "(//span[contains(text(),'Select to rate 5 stars')])");
          
          Common.textBoxInput("id", "tt-review-form-text", data.get(dataSet).get("Review"));
          Common.textBoxInput("id", "tt-review-form-title", data.get(dataSet).get("Reviewtitle"));
          Common.clickElement("xpath", "(//span[contains(text(),'Select to rate 5 out of 5')])[1]");
          Common.clickElement("xpath", "(//span[contains(text(),'Select to rate 5 out of 5')])[2]");
          Common.clickElement("xpath", "(//span[contains(text(),'Thick/Coarse')])[2]");
          Common.clickElement("xpath", "(//span[contains(text(),'Curly')])[1]");
         Common.clickElement("xpath", "(//span[contains(text(),'Thin')])[1]");
          Common.clickElement("xpath","(//span[contains(text(),'Long')])[1]");
          Common.clickElement("xpath", "(//label[contains(text(),'Texture')])[1]");
          Common.clickElement("xpath", "(//label[contains(text(),'Hot')])[1]");
          
          
          
          ExtenantReportUtils.addPassLog("To verify the Product Review details", "Should enter the review details ", "user successfully entered the product review details ", Common.getscreenShotPathforReport("Successfully Entered product review detailis"));
      	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
    }catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("To verify the Product review details","Should ener the review details", "user unable to enter product review details", Common.getscreenShotPathforReport("failed to enter product review details"));			
    		Assert.fail();	
    		}
    try {
    	   Common.scrollIntoView("xpath", "(//button[@class='tt-c-review-form__submit tt-o-button tt-o-button--primary'])");
    	   Thread.sleep(5000);
          Common.clickElement("xpath", "(//button[@class='tt-c-review-form__submit tt-o-button tt-o-button--primary'])");
          //Thread.sleep(4000);
          //String message=Common.findElement("xpath", "(//div[@class='message-success success message'])").getText();
          //message.equals("We have saved your subscription.");
        // ExtenantReportUtils.addPassLog("To verify the payment page", "Should land on payment page", "user successfully landed on payment page", Common.getscreenShotPathforReport("Successfully land on payment page"));
         //Thread.sleep(6000);
	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		//ExtenantReportUtils.addFailedLog("To verify the payment page","Should land on payment page", "user unableto land on payment page", Common.getscreenShotPathforReport("failed to land on payment page"));			
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
		
		Common.scrollIntoView("xpath", "(//button[@class='tt-c-auth__email-submit tt-o-button tt-o-button--primary'])");
 	   Thread.sleep(5000);
       Common.clickElement("xpath", "(//button[@class='tt-c-auth__email-submit tt-o-button tt-o-button--primary'])");
		
		ExtenantReportUtils.addPassLog("verifying product review funcionality", " A confirmation message should sent to customer", "Successfully got a confirmation message", Common.getscreenShotPathforReport("Confirmation message"));
		
		}
		 catch(Exception |Error e) {
		        ExtenantReportUtils.addFailedLog("verifying product review functionality", "A confirmation message should present", "user faield to get confirmation message", Common.getscreenShotPathforReport("confirmation messge faield"));
				Assert.fail();
			}
		
	}

public void tax_calculation(String dataset) {
	
	try {
		String Tax1=data.get(dataset).get("Tax");
		
		Sync.waitPageLoad();
		String Tax2=Common.getText("xpath", "(//span[@class='price'])[9]");
		Common.assertionCheckwithReport((Tax1).equals(Tax2),"verifying the loginpage", "navigate the loginpage", "user successfully navigate the loginpage", "Failed to navigate to loginpage");
	
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
	Common.assertionCheckwithReport(HomepageTitle.equals("Dashboard / Magento Admin | Drybar"), "verifying the homepage", "navigate the home page", "user successfully navigate the home page", "Failed to navigate to home page");
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

///////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////

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
        Common.actionsKeyPress(Keys.ARROW_DOWN);
        Common.actionsKeyPress(Keys.ARROW_DOWN);
        Common.actionsKeyPress(Keys.ARROW_DOWN);
        Common.actionsKeyPress(Keys.ARROW_DOWN);
        Common.assertionCheckwithReport(("5").equals(Toolbar_number), "verifying Cleanse and condition filter in Benefits", "Should select Cleanse & Condition", "user successfully selected Cleanse & condition filter", "Failed to select cleanse & condition filter");
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
     Common.assertionCheckwithReport(("2").equals(Toolbar_number), "verifying $20.00 - $29.99 Price filter", "Should select $20.00 - $29.99 Price filter", "user successfully selected $20.00 - $29.99 Price filter", "Failed to select $20.00 - $29.99 Price filter");
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
   Common.assertionCheckwithReport(("2").equals(Toolbar_number), "verifying Phthalate Free filter in Clean benefit", "user should select Phthalate Free filter in Clean benefit", "user successfully  selected Phthalate Free filter in Clean benefit", "Failed to select Phthalate Free filter in Clean beneft");
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
   Common.assertionCheckwithReport(("2").equals(Toolbar_number), "verifying Honey free filter in clean benefit", "Should select Honey free filter in clean benefit", "user successfully selected Honey free filter in clean benefit", "Failed to select Honey free filter in clean benefit");
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
   Common.assertionCheckwithReport(("1").equals(Toolbar_number), "verifying $30.00 - $39.99 price filter", "User should  select $30.00 - $39.99 price filter", "user successfully selected  select $30.00 - $39.99 price filter ", "Failed to select  select $30.00 - $39.99 price filter");
}
 
catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying $30.00 - $39.99 price filter", "User should  select $30.00 - $39.99 price filter", "user faield to Select  select $30.00 - $39.99 price filter", Common.getscreenShotPathforReport("Price"));
Assert.fail();	
} 
}
}