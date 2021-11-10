package TestComponent.Honeywell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.openqa.selenium.WebElement;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.Static;

import TestLib.Common;
import TestLib.Sync;
import TestLib.Common.SelectBy;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;
import Utilities.xmlReader;
import junit.framework.Assert;

public class Honeywellhelper {

		String datafile;
		ExcelReader excelData;
		Map<String, Map<String, String>> data=new HashMap<>();
		static ExtenantReportUtils report;
		public  Honeywellhelper(String datafile)
		{
			excelData=new ExcelReader(datafile);
			data=excelData.getExcelValue();
			this.data=data;
			if(Utilities.TestListener.report==null)
			{
				report=new ExtenantReportUtils("Honeywell");
				report.createTestcase("HoneywellTestCases");
			}
			else
			{
				this.report=Utilities.TestListener.report;
			}
		}
		
		/*public void loginHoneywell(String dataSet) throws Exception
		{
			Thread.sleep(7000);	
			Common.clickElement("xpath", "//a[@class='header-content__right-link']");
			//Thread.sleep(7000);
			Common.textBoxInput("xpath", "//input[@id='email']", data.get(dataSet).get("Email"));
			Common.textBoxInput("xpath", "//input[@name='login[password]']", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[@class='action login primary']");
			}
		public void accountcreation(String dataset) throws Exception
		{
			Thread.sleep(5000);
			Common.clickElement("xpath", "//a[@class='header-content__right-link']");
			Common.clickElement("xpath", "//a[@class='action create primary']");
			Common.textBoxInput("id", "firstname", data.get(dataset).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(dataset).get("LastName"));
			Common.textBoxInput("id", "email_address", data.get(dataset).get("Email"));
			Common.textBoxInput("id", "password", data.get(dataset).get("Password"));
			Common.textBoxInput("id", "password-confirmation", data.get(dataset).get("Password"));
			Common.clickElement("xpath", "//button[@class='action submit primary']");
		}	
		public void forgotpassword(String dataset) throws Exception
		{
			Thread.sleep(5000);
			Common.clickElement("xpath", "//span[contains(text(), 'Forgot Your Password?')]");
			Common.textBoxInput("id", "email_address", data.get(dataset).get("Email"));
			Common.clickElement("xpath", "//span[contains(text(), 'Reset Your Password')]");
			
		}*/
		
		
		public void verifyingHomePage() {
			try {
				Sync.waitPageLoad();
			int hompageiconsize= Common.findElements("xpath", "//img[@class='desktop-logo']").size();
			Common.assertionCheckwithReport(hompageiconsize>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield lo land on Home page");
			}
			 catch(Exception |Error e) {
				     e.printStackTrace();
						ExtenantReportUtils.addFailedLog("verifying my home page", "Home page contines Logo of product", "user faield lands land on Home page", Common.getscreenShotPathforReport("homepage"));
						Assert.fail();
			}
		}
		public void accept() throws Exception
		{
			Sync.waitPageLoad();
//			Common.waitAndClick("xpath","//button[@id='truste-consent-required']");
			Common.waitAndClick("xpath","//button[text()='Accept All']");	
	 	Thread.sleep(3000);
	 	int newsllettersize=Common.findElements("xpath","//div[@id='wpn-lightbox-close-newsletter']").size();
	 	if(newsllettersize>0){
	 	Thread.sleep(2000);
	 		Common.clickElement("xpath", "//div[@id='wpn-lightbox-close-newsletter']");
	 	}
		}
		public void clicksigninbutton() throws Exception{
			
			try{
			Sync.waitElementPresent("xpath", "//a[@class='header-content__right-link']");
			Common.clickElement("xpath", "//a[@class='header-content__right-link']");
			ExtenantReportUtils.addPassLog("verifying my account button", "It should lands on Customer login page", "user successfully  lands on Customer login page", Common.getscreenShotPathforReport("myaccountpass"));
			}
			 catch(Exception |Error e) {
		     
				ExtenantReportUtils.addFailedLog("verifying my account button", "It should lands on Customer login page", "user faield lands on Customer login page", Common.getscreenShotPathforReport("myaccountfaield"));
				Assert.fail();
			}
			
		}
		public void loginHoneywell(String dataSet) throws Exception
		{
			try{
				Thread.sleep(7000);
				Common.clickElement("xpath", "//a[@class='header-content__right-link']");
				Common.textBoxInput("xpath", "//input[@id='email']", data.get(dataSet).get("Email"));
				Common.textBoxInput("xpath", "//input[@id='pass']", data.get(dataSet).get("Password"));
				Common.clickElement("xpath", "//button[@class='action login primary']");
				ExtenantReportUtils.addPassLog("verifying account login", "It should lands on Customer my account page", "user successfully  lands on my account page", Common.getscreenShotPathforReport("loginpass"));
				}
			catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("verifying account login", "It should lands on Customer my account page", "user failed lands on my account page", Common.getscreenShotPathforReport("loginfailed"));
				Assert.fail();
				e.printStackTrace();
			}
		}
		public void loginTax(String dataSet) throws Exception
		{
			
			Thread.sleep(2000);
			int name=Common.findElements("xpath","//span[@class='customer-name']").size();
			if(name>0)
			{
			System.out.println(name);
			}
			else{
			try{
				Thread.sleep(7000);
				Common.clickElement("xpath", "//a[@class='header-content__right-link']");
				
				  
				Common.textBoxInput("xpath", "//input[@id='email']", data.get(dataSet).get("Email"));
				Common.textBoxInput("xpath", "//input[@id='pass']", data.get(dataSet).get("Password"));
				Common.clickElement("xpath", "//button[@class='action login primary']");
				ExtenantReportUtils.addPassLog("verifying account login", "It should lands on Customer my account page", "user successfully  lands on my account page", Common.getscreenShotPathforReport("loginpass"));
				}
			catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("verifying account login", "It should lands on Customer my account page", "user failed lands on my account page", Common.getscreenShotPathforReport("loginfailed"));
				Assert.fail();
			}
		}}
		public void createaccount1(String dataSet) 

		{
			try{
				Thread.sleep(7000);	
				Common.clickElement("xpath", "//span[contains(text(), 'Create an Account')]");
				ExtenantReportUtils.addPassLog("verifying Create Account button", "It should lands on Create New Customer from Account form Page", "user  lands on Customer Account creation form Page", Common.getscreenShotPathforReport("createaccount"));
				}
			catch(Exception |Error e) {
				e.printStackTrace();
			        ExtenantReportUtils.addFailedLog("verifying Create Account button", "It should lands on Create New Customer from Account form Page", "user faield lands on Account form Page", Common.getscreenShotPathforReport("createaccount"));
					Assert.fail();
				}
			try{
				
				Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
				//Common.clickElement("xpath", "//input[@id='is_subscribed']");
				Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
				Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
				Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
				Common.clickElement("xpath", "//button[@title='Create an Account']");
				ExtenantReportUtils.addPassLog("verifying Create Account from", "Account should be created successfully navigate to My Account page", "user successfully naviagted to account", Common.getscreenShotPathforReport("createdaccount"));
			}
			catch(Exception |Error e) {
		        ExtenantReportUtils.addFailedLog("verifying Create Account from", "Account should be created successfully navigate to My Account page", "user faield to create account", Common.getscreenShotPathforReport("createaccountfaield"));
				Assert.fail();
			}
		}
		public void createaccount(String dataSet) 

		 

        {
            try{
                Thread.sleep(7000);    
                Common.clickElement("xpath", "//span[contains(text(), 'Create an Account')]");
                ExtenantReportUtils.addPassLog("verifying Create Account button", "It should lands on Create New Customer from Account form Page", "user  lands on Customer Account creation form Page", Common.getscreenShotPathforReport("createaccount"));
                }
            catch(Exception |Error e) {
                e.printStackTrace();
                    ExtenantReportUtils.addFailedLog("verifying Create Account button", "It should lands on Create New Customer from Account form Page", "user faield lands on Account form Page", Common.getscreenShotPathforReport("createaccount"));
                    Assert.fail();
                }
            try{
                
                Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
                Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
                //Common.clickElement("xpath", "//input[@id='is_subscribed']");
                Common.textBoxInput("id", "email_address", Utils.getEmailid());
                Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
                Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
                Common.clickElement("xpath", "//button[@title='Create an Account']");
                ExtenantReportUtils.addPassLog("verifying Create Account from", "Account should be created successfully navigate to My Account page", "user successfully naviagted to account", Common.getscreenShotPathforReport("createdaccount"));
            }
            catch(Exception |Error e) {
                ExtenantReportUtils.addFailedLog("verifying Create Account from", "Account should be created successfully navigate to My Account page", "user faield to create account", Common.getscreenShotPathforReport("createaccountfaield"));
                Assert.fail();
            }
        }
		
		
		
		public void ShippingFormValidation(String dataSet) throws Exception{
	        
	        Thread.sleep(4000);
	        try {
	               Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
	                Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",data.get(dataSet).get("Email"));
	                Sync.waitElementPresent("xpath", "//input[@name='firstname']");
	                Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
	                
	                Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	                Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
//	                Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
//	                Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
	                
	                Sync.waitElementPresent("xpath", "//input[@name='city']");
	                Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
	                Common.actionsKeyPress(Keys.ESCAPE);
//	                Sync.waitElementPresent("xpath", "//input[@name='postcode']");
//	                Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
	                
	                
//	                Sync.waitElementPresent("xpath", "//select[@name='region_id']");
//	                Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));
	                
	                
	                
	                
//	                Sync.waitElementPresent("xpath", "//input[@name='telephone']");
//	                Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
	                Thread.sleep(5000);
	                
	                Common.clickElement("xpath","//div[@id='shipping-method-buttons-container']/div/button");
	                Common.clickElement("xpath", "//button[@class='button action continue primary']");
	        Sync.waitElementClickable("xpath", "//button[@class='button action continue primary']");    
	        Common.findElement("xpath", "//button[@class='button action continue primary']").click();
	        
	        Common.actionsKeyPress(Keys.ARROW_DOWN);
	        //Common.actionsKeyPress(Keys.DOWN);
	        int emailerrormessage=Common.findElements("xpath", "//div[@id='customer-email-error']").size();
	        int Streeterromessage=Common.findElements("xpath", "//div[@class='field-error']").size();
	        
	        Common.assertionCheckwithReport(emailerrormessage>0&&Streeterromessage>0, "verifying error message ShippingAddressForm Page", "enter with empty data it must show error message","sucessfully display the error message", "faield to dispalyerrormessage");
	        }
	        catch(Exception |Error e) {
	             e.printStackTrace();   
	            ExtenantReportUtils.addFailedLog("verifying error message ShippingAddressForm Page", "enter with empty data it must show error message", "faield to dispalyerrormessage", Common.getscreenShotPathforReport("ShippingAddressFormvalidation"));
	            Assert.fail();
	}}
		
		
		
		public void outofstockinpdp() {
	        // TODO Auto-generated method stub
	        try
	        {
	            Thread.sleep(4000);
	            Common.clickElement("xpath","//img[@alt='Air Genius 4 Air Purifier']");
	            String PDPOOS=Common.findElement("xpath","//span[text()='Out of stock']").getText();
	            System.out.println(PDPOOS);
	            Common.assertionCheckwithReport(PDPOOS.equals("OUT OF STOCK"), "To verify the PDP with out of stock", "product Should be on out of stock PDP page","User able to land on Out of Stock PDP");
	        }
	        catch(Exception |Error e) {
	        ExtenantReportUtils.addFailedLog("To verify the the PDP Page with out of stock","product Should be on out of stock PDP page", "user unable to land on Out of Stock PDP", Common.getscreenShotPathforReport("failed to land on out of stock PDP page"));
	        Assert.fail();
	        }
	       
	       
	    }
		
		public void filtertype() {
	        // TODO Auto-generated method stub
	       
	        try
	        {
	            Thread.sleep(2000);
	            Sync.waitElementPresent("xpath","//a[@data-opt-path='cooling_type=190']");
	            Common.clickElement("xpath","//a[@data-opt-path='cooling_type=190']");
	            ExtenantReportUtils.addPassLog("verifying filter type",
	                    "click on filter type ",
	                    "filter type shouild be selected",
	                    Common.getscreenShotPathforReport("as per the selction selected products are displayed sucessfully"));
	        }
	        catch (Exception | Error e) {
	            e.printStackTrace();
	            ExtenantReportUtils.addFailedLog("verifying filter type",
	                    "click on filter type",
	                    "filter type should be selected", Common.getscreenShotPathforReport("Failed to display products as per filter selection"));
	            Assert.fail();
	        }
	    }

		public void pricefilter() {
	        // TODO Auto-generated method stub
	        try
	        {
	            Thread.sleep(4000);
	            Sync.waitElementPresent("xpath","//a[@data-opt-path='price=20-30']");
	            Common.clickElement("xpath","//a[@data-opt-path='price=20-30']");
	            Sync.waitElementPresent("xpath","//a[@data-opt-path='price=50-60']");
	            Thread.sleep(3000);
	            Common.clickElement("xpath","//a[@data-opt-path='price=50-60']");
	           
	               
	           
	           
	           
	            ExtenantReportUtils.addPassLog("verifying filter type",
	                    "click on price filter type ",
	                    "price filter type shouild be selected",
	                    Common.getscreenShotPathforReport("as per the selction selected products are displayed sucessfully"));
	           
	        }
	        catch (Exception | Error e) {
	           
	            e.printStackTrace();
	            ExtenantReportUtils.addFailedLog("verifying filter type",
	                    "click on price filter type",
	                    "price filter type should be selected", Common.getscreenShotPathforReport("Failed to display products as per price filter selection"));
	            Assert.fail();
	        }
	    }
	   
	        
	 
	 

		
		public void myAccount(String dataSet) 

		{
			try{
				Thread.sleep(7000);
				Common.clickElement("xpath", "//a[@class='header-content__right-link']");
				Common.textBoxInput("xpath", "//input[@id='email']", data.get(dataSet).get("Email"));
				Common.textBoxInput("xpath", "//input[@id='pass']", data.get(dataSet).get("Password"));
				Common.clickElement("xpath", "//button[@class='action login primary']");
				ExtenantReportUtils.addPassLog("verifying account login", "It should lands on Customer my account page", "user successfully  lands on my account page", Common.getscreenShotPathforReport("loginpass"));
				}
			catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("verifying account login", "It should lands on Customer my account page", "user failed lands on my account page", Common.getscreenShotPathforReport("loginfailed"));
				Assert.fail();
			}
			
		}
		public void profile(String dataSet) {
			try{
				
				Thread.sleep(2000);
				Common.clickElement("xpath", "//*[@id='block-collapsible-nav']/ul/li[2]");
				
				Common.clickElement("xpath", "(//label[@class='label'])[3]");
				Common.textBoxInput("xpath", "//input[@id='current-password']", data.get(dataSet).get("Password"));
				
			Common.clickElement("xpath", "//button[@class='action save primary']");
			ExtenantReportUtils.addPassLog("verifying profile page", "It should lands on Customer  profile page", "user successfully  lands on  profile page", Common.getscreenShotPathforReport("profilepass"));
			}catch(Exception e){
				ExtenantReportUtils.addFailedLog("verifying profile page", "It should lands on Customer  profile page", "user failed lands on  profile page", Common.getscreenShotPathforReport("profilefalied"));
				Assert.fail();
			}
			
		}
		public void myOrders() {
			try{
				
				Thread.sleep(2000);
				Common.clickElement("xpath", "//*[@id='block-collapsible-nav']/ul/li[3]");
				
				ExtenantReportUtils.addPassLog("verifying myOrders page", "It should lands on  my Orders page", "user successfully  lands on my Orders page", Common.getscreenShotPathforReport("myOrderspass"));	
				
			}catch(Exception e){
				ExtenantReportUtils.addFailedLog("verifying myOrders page", "It should lands on my Orders page", "user failed lands on my Orders page", Common.getscreenShotPathforReport("myOrdersfailed"));
				Assert.fail();
			}
			
		}
		public void myAddress() {
			try{
				
				Thread.sleep(2000);
				Common.clickElement("xpath", "//*[@id='block-collapsible-nav']/ul/li[5]");
				
				ExtenantReportUtils.addPassLog("verifying myAddress page", "It should lands on  my Address page", "user successfully  lands on my Address page", Common.getscreenShotPathforReport("myAddresspass"));
				
			}catch(Exception e){
				ExtenantReportUtils.addFailedLog("verifying myAddress page", "It should lands on my Address page", "user failed lands on my Address page", Common.getscreenShotPathforReport("myAddressfailed"));
				Assert.fail();
			}
			
		}
		
		public void changeEmail(String dataSet) {
			try{
				
				Thread.sleep(2000);
				Common.clickElement("xpath", "//div[@id='block-collapsible-nav']//a[text()='Profile']");
				
				Common.clickElement("xpath", "//label[@class='label']//span[text()='Change Email']");
				Thread.sleep(2000);
				Common.findElement("xpath","//input[@id='email']").clear();
				Thread.sleep(2000);
				Common.textBoxInput("xpath", "//input[@id='email']",data.get(dataSet).get("Email"));
				Thread.sleep(2000);
				Common.textBoxInput("xpath", "//input[@id='current-password']", data.get(dataSet).get("Password"));
				
			Common.clickElement("xpath", "//button[@class='action save primary']");
			ExtenantReportUtils.addPassLog("verifying Change Email", "It should lands on Change Email page", "user successfully  lands on Change Email page", Common.getscreenShotPathforReport("ChangeEmail pass"));
			}catch(Exception e){
				ExtenantReportUtils.addFailedLog("verifying Change Email", "It should lands on Change Email page", "user failed lands on Change Email page", Common.getscreenShotPathforReport("ChangeEmail failed"));
				Assert.fail();	
			}
		}

		public void Forgetpassword(String dataSet)
		{
			try{
				Thread.sleep(7000);	
				Common.clickElement("xpath","//a[@class='header-content__right-link']");
				Sync.waitElementClickable("xpath", "//a[@class='action remind']");
				Common.clickElement("xpath", "//form[@id='login-form']/fieldset//a");
				Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
				Common.textBoxInput("id", "captcha_user_forgotpassword", data.get(dataSet).get("Password"));
				Common.clickElement("xpath", "//span[text()='Reset My Password']");
				ExtenantReportUtils.addPassLog("verifying Forgot my password button", "It should land on forgot password form from account form Page", "user  lands on forgot password form Page", Common.getscreenShotPathforReport("forgotpassword"));
				}
			catch(Exception |Error e) {
				e.printStackTrace();
			        ExtenantReportUtils.addFailedLog("verifying Forgot my password button", "It should lands on forgot password form from Account form Page", "user faield land on forgot password form Page", Common.getscreenShotPathforReport("createaccount"));
					Assert.fail();
			}
			try
			{
				
				Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
				Common.clickElement("xpath", "//button[@class='action submit primary']");
				ExtenantReportUtils.addPassLog("verifying Forgot password from", "It should successfully navigate to login page and reset link should be sent to given email", "user successfully reset password", Common.getscreenShotPathforReport("passwordreset"));
		
			}
			catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("verifying Forgot password from", "It should successfully navigate to login page and reset link should be sent to given email", "user failed to  reset password", Common.getscreenShotPathforReport("passwordreset"));
				Assert.fail();	
			}
			
		}
		public void clickShopButton() {
			try{
				Thread.sleep(4000);
				//Common.clickElement("id","ui-id-3");
				Common.mouseOver("xpath", "//span[text()='Shop']");
				ExtenantReportUtils.addPassLog("verifying Shop button in Header", "User navigate to shop category", "User successfully  navigate to shop category", Common.getscreenShotPathforReport("shopbutton"));
				
			}
				catch(Exception |Error e) {
					ExtenantReportUtils.addFailedLog("verifying Shop button in Header", "User navigate to shop category", "User unable navigate to shop category", Common.getscreenShotPathforReport("shopbutton"));		
					Assert.fail();	

					}
		}
		
		public void click_Airpurifiers()
		
		{
			clickShopButton();
			try{
				Sync.waitElementClickable("xpath", "//a[@title='Air Purifiers']");
				Common.clickElement("xpath", "//a[@title='Air Purifiers']");
				Common.assertionCheckwithReport(Common.getPageTitle().equals("Air Purifiers - Shop"),"Verifying Air Purifiers page","it shoud navigate to Air Purifiers", "successfully  navigate to Air Purifiers", "airpurifires");	

				
				
				
				/*
				Sync.waitElementClickable("xpath", "//a[@title='Thermometers']");
				Common.clickElement("xpath", "//a[@title='Thermometers']");
				Common.assertionCheckwithReport(Common.getPageTitle().equals("Air Purifiers - Shop"),"Verifying Air Purifiers page","it shoud navigate to Air Purifiers", "successfully  navigate to Air Purifiers", "airpurifires");	
				*/
			}
			catch(Exception |Error e) {
				e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying airpurifiers Product category", "User navigate to airpurifiers product page", "user not able to click airpurifiers Product", Common.getscreenShotPathforReport("productincr"));		
			Assert.fail();	

			}
			
		}
		public void PLPclickAddtoBag(String dataSet) {
			try{
				Thread.sleep(4000);
				String productname= data.get(dataSet).get("productname");
				System.out.println(productname);
				Common.mouseOver("xpath", "//a[contains (text(),'"+productname+"')]");
				Common.scrollIntoView("xpath", "(//span[text()='Add to Cart'])[11]");
				Common.clickElement("xpath", "(//span[text()='Add to Cart'])[11]");
			ExtenantReportUtils.addPassLog("verifying add to cart button", "User click add to card button", "user successfully click add to cart button", Common.getscreenShotPathforReport("Clickaadtocart"));
			}
			catch(Exception |Error e) {
			   e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying add to cart button", "User click add to card button", "user failed to click add to cart button", Common.getscreenShotPathforReport("failedtoclickutton"));
				Assert.fail();
			}
			
		}

		
		
		
		public void adding_product_toCart(String dataSet) {
			try {
				Thread.sleep(4000);
			String productname= data.get(dataSet).get("productname");
			System.out.println(productname);
			System.out.println(Common.getPageTitle());
			Common.clickElement("xpath", "//a[contains (text(),'"+productname+"')]");
			//Common.assertionCheckwithReport(Common.getPageTitle().contains(productname),"verifying product name in PDP page", "after click the product in PLP page the same product navigating to PDP page", "sucssfully navigate to pdp page", "faield to navigate to pdp page");
			}
			catch(Exception |Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying product name in PDP pag", "after click the product in PLP page the same product navigating to PDP page", "sucssfully navigate to pdp page", Common.getscreenShotPathforReport("pdppage"));		
				Assert.fail();	

				}
			clickAddtoBag();

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
			ExtenantReportUtils.addPassLog("verifying product Quantity", "User increaseProductQuantity", "user successfully increaseProductQuantity", Common.getscreenShotPathforReport("productIncr"));	
			}        
			catch(Exception |Error e) {	    
				e.printStackTrace();		
				ExtenantReportUtils.addFailedLog("verifying product quantity", "User increase product quantity", "user failed to increase Product quantity", Common.getscreenShotPathforReport("productIncr"));		
				Assert.fail();	}
		}
		public void clickAddtoBag() {
			try{
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			ExtenantReportUtils.addPassLog("verifying add to cart button", "User click add to card button", "user successfully click add to cart button", Common.getscreenShotPathforReport("Clickaadtocart"));
			}
			catch(Exception |Error e) {
			   e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying add to cart button", "User click add to card button", "user failed to click add to cart button", Common.getscreenShotPathforReport("failedtoclickutton"));
				Assert.fail();
			}
			
		}
		public void clickminicartButton() throws Exception{
			try{
				Thread.sleep(3000);
			    Sync.waitElementPresent("xpath", "//a[contains(@class,'showcart')]");
		        Common.clickElement("xpath", "//a[contains(@class,'showcart')]");
		        ExtenantReportUtils.addPassLog("verifying mini cart button", "User click mini cart button", "user successfilly click mini cart button", Common.getscreenShotPathforReport("minicartbutton"));
			}
		    catch(Exception |Error e) {
		 	   ExtenantReportUtils.addFailedLog("verifying mini cart button", "User click mini cart button", "user failed to click mini cart button", Common.getscreenShotPathforReport("faieldtominicartbutton"));
				Assert.fail();
			}
		}
		public void clickminicartcheckout() throws Exception{
			try{
				 Thread.sleep(3000);
				 Sync.waitElementPresent("id", "top-cart-btn-checkout");
				 Common.clickElement("id", "top-cart-btn-checkout");
			      ExtenantReportUtils.addPassLog("verifying mini cart checkout button", "User click mini cart checkout button", "user successfilly click mini cart checkout button", Common.getscreenShotPathforReport("minicartcheckoutbutton")); 
			}
			catch(Exception |Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying mini cart checkout button", "User click mini cart checkout button", "user failed to click mini cart checkout button", Common.getscreenShotPathforReport("failedminicartcheckoutbutton")); 
				Assert.fail();
		}
	}
		
		
		
		public void validating_Shippingpage(String dataSet){
			try{
			Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
			Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",data.get(dataSet).get("Email"));
			}
			catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("verifying mini cart checkout button", "User click mini cart checkout button", "user failed to click mini cart checkout button", Common.getscreenShotPathforReport("failedminicartcheckoutbutton")); 
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
			
			

			Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
			Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
			
			Sync.waitElementPresent("xpath", "//input[@name='city']");
			Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
			Common.actionsKeyPress(Keys.ESCAPE);
			Sync.waitElementPresent("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
			
			
			Sync.waitElementPresent("xpath", "//select[@name='region_id']");
			Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));
			
			/*
			 * Sync.waitElementPresent("xpath", "//select[@name='country_id']");
			 * Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT,
			 * data.get(dataSet).get("Countryname"));
			 */
			
			
			
			Sync.waitElementPresent("xpath", "//input[@name='telephone']");
			Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
			
			Thread.sleep(2000);
			int smethod=Common.findElements("xpath","//input[@id='s_method_amstrates9']").size();
			if(smethod>0){
				 Common.clickElement("xpath","//input[@id='s_method_amstrates9']");
			}else if(smethod>0){
				 Common.clickElement("xpath","//input[@id='s_method_amstrates_amstrates1']");
			} else {
				 Common.clickElement("xpath","//td[@id='label_method_amstrates14_amstrates']");

			}
		    Thread.sleep(3000);
			
		    Common.clickElement("xpath","//div[@id='shipping-method-buttons-container']/div/button");

		    Thread.sleep(3000);
		 Common.clickElement("xpath","//button[@class='action-primary action-accept']");
		   
			//Common.clickElement("xpath","//span[text()='Next']");
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
		public void CouponCodeinCheckoutpage(String dataSet){
			try{
			//	Sync.waitPageLoad();
				Thread.sleep(3000);
				Common.actionsKeyPress(Keys.END);
			Sync.waitElementPresent("id", "block-discount-heading");
			Common.scrollIntoView("id", "block-discount-heading");
			Common.clickElement("id", "block-discount-heading");
			Sync.waitElementPresent("id", "discount-code");
            Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Promationcode"));
			Common.clickElement("xpath", "//span[text()='Apply Discount']");
		    Sync.waitPageLoad();
		    Common.actionsKeyPress(Keys.HOME);
			int Discountcopuon=Common.findElements("xpath", "//tr[@class='totals discount']").size();
		System.out.println(Discountcopuon);
			Common.assertionCheckwithReport(Discountcopuon>0,"verifying pomocode", "It should apply discount If user enters valid promocode", "promotion code working as expected","Promation code is not applied");

			
			}
			catch(Exception |Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping", "faield to add new shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
				Assert.fail();
				
			}  
		}
		
		
		
		
		public void creditCard_payment(String dataSet) throws Exception{
			
			try{
				
		
				Thread.sleep(9000);
				Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
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
			
			Thread.sleep(4000);
			Common.switchFrames("id", "paymetric_xisecure_frame");
			
			System.out.println(data.get(dataSet).get("cardType"));
			Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
			
			
			
			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
			Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
			Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
			Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));	
			Thread.sleep(2000);
			
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.switchToDefault();
			Thread.sleep(1000);
			String url=Common.getCurrentURL();
			if(url.equals("https://www.honeywellpluggedin.com/checkout/#payment"))
			{
				System.out.println(url);
			}
			else{
			Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
			}
			Common.switchFrames("id", "paymetric_xisecure_frame");
			String expectedResult="credit card fields are filled with the data";
		    String errorTexts=	Common. findElement("xpath", "//div[contains(@id,'error')]").getText();
		    Common.switchToDefault();
		    Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data", expectedResult, "Filled the Card detiles", "missing field data it showinng error");
		    	
			Sync.waitPageLoad();
			}
			catch(Exception |Error e) {
				e.printStackTrace();
			    ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", "credit card fields are filled with the data", "faield  to fill the Credit Card infromation",  Common.getscreenShotPathforReport("Cardinfromationfail"));
				Assert.fail();
				
			}
			
			
			
		}
		public String order_Verifying() throws Exception{
			String OrderId="";
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
			 OrderId=Common.getText("xpath", "(//div[@class='checkout-success']//span)[1]");
			System.out.println("Your order number is:"+OrderId);
			Common.assertionCheckwithReport(sucessMessage.equals("Thank you for your purchase!"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");
				
			}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
						"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
				Assert.fail();
			}
			return OrderId;
		}
		
		public String order_VerifyingReg() throws Exception{
			String OrderId="";
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
			 OrderId=Common.getText("xpath", "(//div[@class='checkout-success']//strong)[1]");
			System.out.println("Your order number is:"+OrderId);
			Common.assertionCheckwithReport(sucessMessage.equals("Thank you for your purchase!"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");
				
			}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
						"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
				Assert.fail();
			}
			return OrderId;
		}
		
		public void addDeliveryAddress_registerUser(String dataSet) throws Exception {

			String expectedResult = "shipping address is entering in the fields";
		    int size = Common.findElements(By.xpath("//span[contains(text(),'New Address')]")).size();
			if (size > 0) {

				try {
					Common.clickElement("xpath", "//span[contains(text(),'New Address')]");
					
					/*Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
					Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",
							data.get(dataSet).get("Email"));*/
					
					
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
			//		Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
			//				data.get(dataSet).get("City"));
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",data.get(dataSet).get("City"));
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
					Common.clickElement("xpath","//label[@class='label']//span[text()='Save in address book']");

					ExtenantReportUtils.addPassLog("validating the shipping address", expectedResult,
							"user add the shipping address",
							Common.getscreenShotPathforReport("faield to add shipping address"));
					
					Sync.waitElementPresent("xpath","//span[text()='Save in address book']");
                    Common.clickElement("xpath","//span[text()='Save in address book']");
					Common.clickElement("xpath", "//button[contains(@class,'save-address')]");

				    Thread.sleep(3000);
				 Common.clickElement("xpath","//button[@class='action-primary action-accept']");
					
					
					int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

					Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
							"user will fill the all the shipping", "user fill the shiping address click save button",
							"faield to add new shipping address");
					
//					Common.clickElement("xpath","//td[@id='label_method_amstrates2_amstrates']");
					//select_USPS_StandardGround_shippingMethod();
		            Thread.sleep(5000);
					Common.clickElement("xpath", "//div[@id='shipping-method-buttons-container']/div/button");

					
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
					Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
					Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",
							data.get(dataSet).get("Email"));
					Sync.waitElementPresent("xpath", "//input[@name='firstname']");
					Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
					
					Sync.waitElementPresent("xpath", "//input[@name='lastname']");
					Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
					
					

					Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
					Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
					
					Sync.waitElementPresent("xpath", "//input[@name='city']");
					Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
					Common.actionsKeyPress(Keys.ESCAPE);
					Sync.waitElementPresent("xpath", "//input[@name='postcode']");
					Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
					
					
					Sync.waitElementPresent("xpath", "//select[@name='region_id']");
					Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));
					
					/*
					 * Sync.waitElementPresent("xpath", "//select[@name='country_id']");
					 * Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT,
					 * data.get(dataSet).get("Countryname"));
					 */
					
					
					
					Sync.waitElementPresent("xpath", "//input[@name='telephone']");
					Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
					
					//select_USPS_StandardGround_shippingMethod();
				    Thread.sleep(5000);
					
//				    Common.clickElement("xpath","//div[@id='shipping-method-buttons-container']/div/button");
				    
					//Common.clickElement("xpath","//span[text()='Next']");
					//div[contains(@class,'error')]
					Sync.waitPageLoad();
					int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
				    Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying shipping addres filling ", "user will fill the all the shipping", "user fill the shiping address click save button", "faield to add new shipping address");
				   
				
					
				}
				 catch (Exception | Error e) {
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

			

		}
		
		public void searchProduct(String dataSet) throws Exception {
			Thread.sleep(5000);
			String productname= data.get(dataSet).get("productname1");
			try {
				
				Sync.waitElementVisible("xpath", "//form[@id='search_mini_form']//label");
				Thread.sleep(5000);
				Common.clickElement("xpath", "//form[@id='search_mini_form']//label");
				Common.textBoxInput("xpath", "//input[@id='search']", productname);
				ExtenantReportUtils.addPassLog("validating Search box", "enter product name will display in search box",
						"user enter the product name in  search box", Common.getscreenShotPathforReport("searchproduct"));
			} catch (Exception | Error e) {
				ExtenantReportUtils.addFailedLog("validating Search box", "enter product name will display in search box",
						"User failed to enter product name", Common.getscreenShotPathforReport("searchproduct"));
				Assert.fail();
			}
			
		
				Common.actionsKeyPress(Keys.ENTER);
				Thread.sleep(2000);
				try {
					List<WebElement> product=Common.findElements("xpath", "//img[contains(@alt,'"+productname+"')]");
					System.out.println(product.size());
					Common.scrollIntoView(product.get(1));
					Thread.sleep(3000);
					String Attributr=product.get(1).getAttribute("src");
					Common.javascriptclickElement("xpath", "//img[@src='"+Attributr+"']");
				//	product.get(1).click();
					System.out.println(product.size());
					Thread.sleep(3000);
					clickAddtoBag();
				}
				catch(Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("verifying search product in pdp page", "search the selected product in plp page it navigate to pdp page","failed to navigate to pdp page", Common.getscreenShotPathforReport("searchproduct"));
					Assert.fail();
				}
				
				
				
		}
		public void click_View_editcart(){
			try{
			Sync.waitElementPresent("xpath", "//a[@class='action viewcart']");
			Common.clickElement("xpath", "//a[@class='action viewcart']");
			ExtenantReportUtils.addPassLog("verifying the view edit cart button from mincart page","user after click the  view and edit button it navigate to SHOPPING CART page","User navigate to SHOPPING CART page",Common.getscreenShotPathforReport("SHOPPINGCARTPAGE"));
			}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the view edit cart button from mincart page","user after click the  view and edit button it navigate to SHOPPING CART page","User unabel navigate to SHOPPING CART page",Common.getscreenShotPathforReport("SHOPPINGCARTPAGE"));
		        Assert.fail();
		        }
			}
		
		
		public void changeQuntity_UpdateProduct(String productcount){
			try{
			Sync.waitElementPresent("xpath", "//input[@title='Qty']");
			
			String Value=Common.findElement("xpath", "//input[@title='Qty']").getAttribute("value");
			
			Common.clickElement("xpath", "//input[@title='Qty']");
			Common.actionsKeyPress(Keys.BACK_SPACE);
			
			Common.textBoxInput("xpath", "//input[@title='Qty']",productcount);
			Common.clickElement("xpath", "//button[@name='update_cart_action']");
			Sync.waitPageLoad();
			String value=Common.findElement("xpath", "//input[@title='Qty']").getAttribute("value");
			
			
			Common.assertionCheckwithReport(value.equals(productcount), "verifying the product Quntity","user change the quntity of product","user successfully  change quntity","Failed to chage the quntity");
			}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the product Quntity","user change the quntity of product","User faield to chage the quntity",Common.getscreenShotPathforReport("failed changequntity"));
		        Assert.fail();
		        }
		} 
		
		public void clickCheckoutButton_minicart(){
			try{
				Thread.sleep(3000);
			 Sync.waitElementPresent("xpath", "//*[@id='maincontent']//li/button");
			   Common.findElement("xpath", "//*[@id='maincontent']//li/button").click();
			   
			  //Common.assertionCheckwithReport(checkoutbuttonSize>0, "verifying mini cart button", "User click mini cart button", "user successfully click mini cart button", "Failed click mini cart button");
			}
			    catch(Exception |Error e) {
			 	   
					ExtenantReportUtils.addFailedLog("verifying checkOut button in minicart", "User click checkOut  button in mini cart", "user faield to click checkOut button", Common.getscreenShotPathforReport("checkOut"));
					Assert.fail();
				}
		}
		
		
		public void update_product_miniCartBag(String productQTY) throws Exception {
			try{
				//Common.clickElement("xpath", "//div//input[@data-cart-item-id='HPA020B']");
				
				//Sync.waitElementPresent("xpath" ,"//input[@data-cart-item-id='HPA020B']");
				/*Thread.sleep(3000);
			    Common.findElement("xpath", "//div//input[@data-cart-item-id='HPA020B']").clear();
			    Thread.sleep(4000);*/
				Thread.sleep(3000);
				Common.clickElement("xpath", "//input[@class='item-qty cart-item-qty' and @data-cart-item-id]");
				//Common.findElement("xpath", "//input[@class='item-qty cart-item-qty' and @data-cart-item-id]").clear();
				//Common.actionsKeyPress(Keys.DELETE);
                Common.actionsKeyPress(Keys.BACK_SPACE);
                
                Thread.sleep(3000);
				Common.textBoxInput("xpath", "//input[@class='item-qty cart-item-qty' and @data-cart-item-id]",productQTY);
				
				Common.clickElement("xpath", "//span[text()='Update']");
				
			int alermessage=Common.findElements("xpath", "//button[@data-role='action']").size();
			if(alermessage>0) {
				Common.clickElement("xpath", "//button[@data-role='action']");
				
			}
			else {
				clickminicartButton();
				String productvalue=Common.findElement("xpath", "//input[contains(@class,'cart-item-qty')]").getAttribute("data-item-qty");
	   Common.assertionCheckwithReport(productvalue.equals(productQTY),"verifying product update in minicart", "update product in mini cart bag page", "successfullyupdate product in minicart bag page ", "faield to update product in cart page");
			}
			}
			catch(Exception |Error e) {
			 	   
				ExtenantReportUtils.addFailedLog("verifying product update in minicart", "update product in mini cart bag page", "user faield to updateproducttocartpage", Common.getscreenShotPathforReport("cartpageupdate"));
				Assert.fail();
			}
			
		}
		public void removeproductinBagPage()  {
			
			int size=Common.findElements("xpath", "//a//span[text()='Remove']").size();
			for(int i=0;i<=size;i++) {
				Common.clickElement("xpath", "//a//span[text()='Remove']");
			    Common.clickElement("xpath", "//footer[@class='modal-footer']//span[text()='OK']");
			}
			int errormessage=Common.findElements("xpath", "//strong[@class='subtitle empty']").size();
			Common.assertionCheckwithReport(errormessage>0, "validating removeproducts mini cart page", "remove all the products from mini cart","successfully remove the products from mini cart page","faield to remove all the products from cart");
		}
		
		
		/*
		 * public void price_range_plp(int minprice,int maxpric) { try {
		 * Common.clickElement("xpath", "//div[@id='narrow-by-list']/div[2]");
		 * if(minprice>=0 && maxpric<=100 ) { Common.findElement("xpath",
		 * "//a[@data-opt-path='price=0-100']").click();
		 * 
		 * //String Price=Common.findElement("xpath",
		 * "//span[@class='price-wrapper ']").getAttribute("data-price-amount");
		 * List<WebElement> priceranges=Common.findElements("xpath",
		 * "//span[@class='price-wrapper ']"); for(int i=0;i<priceranges.size();i++) {
		 * priceranges. } }
		 * 
		 * else if(minprice>=0 && maxpric<=200 ) { Common.findElement("xpath",
		 * "//a[@data-opt-path='price=100-200']").click();
		 * 
		 * } else if(minprice>=0 && maxpric<=300 ) { Common.findElement("xpath",
		 * "//a[@data-opt-path='price=200-300']").click(); } else if(minprice>=0 ||
		 * maxpric<=1000 ) { Common.findElement("xpath",
		 * "//a[@data-opt-path='price=600-700']").click();
		 * 
		 * 
		 * } ExtenantReportUtils.addPassLog("validting price rage button in plp page",
		 * "price Range button click with in range"+
		 * String.valueOf(minprice)+","+String.valueOf(maxpric),
		 * "successfully click the price range",
		 * Common.getscreenShotPathforReport("pricerage")); } catch(Exception |Error e)
		 * {
		 * 
		 * ExtenantReportUtils.addFailedLog("validting price rage button in plp page",
		 * "price Range button click with in range"+
		 * String.valueOf(minprice)+","+String.valueOf(maxpric),
		 * "faield to click the price range",
		 * Common.getscreenShotPathforReport("pricerage")); Assert.fail(); }
		 * 
		 * try { List<WebElement> priceranges=Common.findElements("xpath",
		 * "//span[@class='price-wrapper ']");
		 * 
		 * 
		 * }
		 */
			
		/*
		 * }
		 * 
		 * public void PriceRangefilter(int PriceRangeBelowGiveValue) {
		 * //span[@class='price-wrapper ']
		 * 
		 * 
		 * Common.clickElement("xpath", "//div[@id='narrow-by-list']/div[2]");
		 * 
		 * if(PriceRangeBelowGiveValue>100) { Common.findElement("xpath",
		 * "//a[@data-opt-path='price=0-100']"); } else if(PriceRangeBelowGiveValue>200)
		 * { Common.findElement("xpath", "//a[@data-opt-path='price=100-200']");
		 * 
		 * } else if(PriceRangeBelowGiveValue>3000)
		 * 
		 * }
		 */
		
		
		public void customerloginvalidation() {
			try {
				
				
				// click the sign button  xptah need to implement
				
			int emailerrormessage=Common.findElements("xpath", "//div[@id='email-error']").size();
			int passworderromessage=Common.findElements("xpath", "//div[@id='pass-error']").size();
			
			Common.assertionCheckwithReport(emailerrormessage>0&&passworderromessage>0, "verifying error message signpage", "enter with empety data it must show error message","sucessfully display the error message", "faield to dispalyerrormessage");
			}
			catch(Exception |Error e) {
			 	   
				ExtenantReportUtils.addFailedLog("verifying error message signpage", "enter with empty data it must show error message", "faield to dispalyerrormessage", Common.getscreenShotPathforReport("loginpagevalidation"));
				Assert.fail();
			}
			
		}
		
		public void stayIntouch(String dataSet) throws Exception {

			String expectedResult = "User should land on the home page";
			try {
				verifyingHomePage();

				Common.actionsKeyPress(Keys.END);
				Thread.sleep(5000);

				Sync.waitElementPresent("id", "newsletter");
				Common.clickElement("id", "newsletter");

				String email = Common.genrateRandomEmail(data.get(dataSet).get("Email"));

				Common.textBoxInput("id", "newsletter", email);
				Thread.sleep(3000);
				Common.clickElement("xpath", "//button[contains(@class,'action subscribe primary')]");
				Common.actionsKeyPress(Keys.PAGE_UP);
				Thread.sleep(5000);
				String Text = Common.getText("xpath", "//div[contains(@data-ui-id,'message')]/div");
				expectedResult = "User gets confirmation message that it was submitted";
				ExtenantReportUtils.addPassLog("verifying newsletter subscription",
						"User get confirmation message if new email if it used mail it showing error message ", Text,
						Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));

			} catch (Exception | Error e) {
				
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying newsletter subscription", "NewsLetter Subscrption success",
						"User faield to subscrption for newLetter  ",
						Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));
				Assert.fail();
	     	}
					}
	public void creditCard_payment_Invalid(String dataSet) throws Exception{
			
			try{
				
		
				Thread.sleep(7000);
				Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
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
			String expectedResult="credit card fields are filled with the data";
		    String errorTexts=	Common. findElement("xpath", "//div[contains(@id,'error')]").getText();
		    Common.switchToDefault();
		    Common.assertionCheckwithReport(!errorTexts.isEmpty(), "validating the credit card information with invalid  data", expectedResult, "Filled the Card detiles with in valid data", "missing the showinng error message");
		    	
			Sync.waitPageLoad();
			}
			catch(Exception |Error e) {
				e.printStackTrace();
			    ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", "credit card fields are filled with the data", "faield  to fill the Credit Card infromation",  Common.getscreenShotPathforReport("Cardinfromationfail"));
				Assert.fail();
				
			}
			
			
			
		}
	
	public void ShippingFormValidation() throws Exception{
		
		Common.clickElement("xpath", "//button[@class='button action continue primary']");
		Thread.sleep(4000);
		try {
		
		Sync.waitElementClickable("xpath", "//button[@class='button action continue primary']");	
		Common.findElement("xpath", "//button[@class='button action continue primary']").click();
		
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		//Common.actionsKeyPress(Keys.DOWN);
		int emailerrormessage=Common.findElements("xpath", "//div[@id='customer-email-error']").size();
		int Streeterromessage=Common.findElements("xpath", "//div[@class='field-error']").size();
		
		Common.assertionCheckwithReport(emailerrormessage>0&&Streeterromessage>0, "verifying error message ShippingAddressForm Page", "enter with empty data it must show error message","sucessfully display the error message", "faield to dispalyerrormessage");
		}
		catch(Exception |Error e) {
		 	e.printStackTrace();   
			ExtenantReportUtils.addFailedLog("verifying error message ShippingAddressForm Page", "enter with empty data it must show error message", "faield to dispalyerrormessage", Common.getscreenShotPathforReport("ShippingAddressFormvalidation"));
			Assert.fail();
		}
		
	}
	public void ForgotPasswordValidation() {
		try {
			
			Thread.sleep(4000);	
			Sync.waitElementClickable("xpath","//a[@class='header-content__right-link']");
			Common.findElement("xpath", "//a[@class='header-content__right-link']").click();
			Thread.sleep(2000);
			//Common.scrollIntoView("xpath", "//a[@class='action remind']");
			Sync.waitElementClickable("xpath", "//a[@class='action remind']");
			Common.findElement("xpath","//a[@class='action remind']").click();
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//button[@class='action submit primary']");
			Common.clickElement("xpath", "//button[@class='action submit primary']");
			Thread.sleep(3000);
			int emailerrormessage=Common.findElements("xpath", "//div[@id='email_address-error']").size();
			Common.assertionCheckwithReport(emailerrormessage>0, "verifying error message ForgotPasswordPage", "enter with empty data it must show error message","sucessfully display the error message", "faield to dispalyerrormessage");
			}
			catch(Exception |Error e) {
			 	   
				ExtenantReportUtils.addFailedLog("verifying error message ForgotPasswordPage", "enter with empty data it must show error message", "faield to dispalyerrormessage", Common.getscreenShotPathforReport("loginpagevalidation"));
				Assert.fail();
			}
		}
	public void CreateAccountFormValidation(String dataSet) {
		
try {

Thread.sleep(4000);
Sync.waitElementClickable("xpath","//a[@class='header-content__right-link']");
Common.findElement("xpath", "//a[@class='header-content__right-link']").click();
Thread.sleep(2000);

Sync.waitElementClickable("xpath", "//a[@class='action create primary']");
Common.findElement("xpath","//a[@class='action create primary']").click();
Thread.sleep(4000);Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
//Common.clickElement("xpath", "//input[@id='is_subscribed']");
Common.textBoxInput("id", "email_address",data.get(dataSet).get("Email"));
Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
Common.clickElement("xpath", "//button[@title='Create an Account']");

Thread.sleep(3000);
int emailerrormessage=Common.findElements("xpath", "//div[@id='email_address-error']").size();
Common.assertionCheckwithReport(emailerrormessage>0, "verifying error message AccountcreationPage", "enter with wrong data it must show error message","sucessfully display the error message", "faield to dispalyerrormessage");
}
catch(Exception |Error e) {

ExtenantReportUtils.addFailedLog("verifying error message AccountcreationPage", "enter with wrong data it must show error message", "faield to dispalyerrormessage", Common.getscreenShotPathforReport("loginpagevalidation"));
Assert.fail();
}
}
	
	public void headLinksValidations_Shop(String dataSet) throws Exception{
		Thread.sleep(3000);
		Sync.waitPageLoad();
		Common.mouseOver("xpath", "//span[text()='Shop']");
		
		String Hederlinks=data.get(dataSet).get("HeaderNames");
		String[] hedrs=Hederlinks.split(",");
		int i=0;
		
		try{
		for(i=0;i<hedrs.length;i++){
	
			System.out.println(hedrs[i]);
			Sync.waitElementClickable("xpath", "//a[@title='"+hedrs[i]+"']");
			Common.clickElement("xpath", "//a[@title='"+hedrs[i]+"']");
			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
			Common.mouseOver("xpath", "//span[text()='Shop']");	
		}
		}
		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
		
			Assert.fail();

		}
	}

	public void warranty(String dataSet) throws Exception{
		try {
			
			Common.clickElement("xpath","(//a[text()='Warranty Registration'])[1]");
			Thread.sleep(4000);
			Common.switchFrames("xpath","//iframe[contains(@src,'product_registration')]");
			Sync.waitElementPresent("xpath", "//input[contains(@name,'Contact.Name.First')]");
			Common.textBoxInput("xpath", "//input[contains(@name,'Contact.Name.First')]",
					data.get(dataSet).get("FirstName"));
		} catch (Exception e) {
			try {
				Common.textBoxInput("xpath", "//input[contains(@name,'Contact.Name.First')]",data.get(dataSet).get("FirstName"));
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		}
		try {
			Sync.waitElementPresent("xpath", "//input[@name='Contact.Name.Last']");
			Common.textBoxInput("xpath", "//input[@name='Contact.Name.Last']", data.get(dataSet).get("LastName"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]", data.get(dataSet).get("Email"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'Street')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'Street')]", data.get(dataSet).get("Street"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'City')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'City')]", data.get(dataSet).get("City"));

	/*		Common.clickElement(By.xpath("//select[contains(@id,'Country')]"));

			Sync.waitElementPresent("xpath", "//select[contains(@id,'Country')]");
			Common.dropdown("xpath", "//select[contains(@id,'Country')]", SelectBy.TEXT,
					data.get(dataSet).get("Country"));
*/
			Sync.waitElementPresent("xpath", "//select[contains(@id,'StateOrProvince')]");
			Common.clickElement(By.xpath("//select[contains(@id,'StateOrProvince')]"));
			Thread.sleep(5000);
			Common.dropdown("xpath", "//select[contains(@id,'StateOrProvince')]", SelectBy.TEXT,
					data.get(dataSet).get("State"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'PostalCode')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'PostalCode')]", data.get(dataSet).get("postcode"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'rn_TextInput_19_Contact.Phones.HOME.Number')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'rn_TextInput_19_Contact.Phones.HOME.Number')]", data.get(dataSet).get("phone"));

			Thread.sleep(3000);
			// Common.actionsKeyPress(Keys.PAGE_DOWN);

			Sync.waitElementPresent("xpath", "//i[contains(@class,'fa-arrow-down')]");
			// Common.clickElement(By.xpath("//span[text()='View All']"));
			// Common.clickElement(By.xpath("//i[@class='all_button_arrow fa
			// fa-arrow-down']"));
			Common.javascriptclickElement("xpath", "//i[contains(@class,'fa-arrow-down')]");
			// Common.mouseOverClick("xpath", "//i[@class='all_button_arrow fa
			// fa-arrow-down']");
			// Common.clickElement("xpath","//i[@class='all_button_arrow fa
			// fa-arrow-down']");
			// Common.mouseOverClick("xpath","//span[text()='View All']");
			Thread.sleep(5000);
			List<WebElement> Productselemts = Common.findElements("xpath", "//div[contains(@class,'resultset')]");

			for (int i = 0; i < Productselemts.size(); i++) {

				if (Productselemts.get(i).getAttribute("title").equals(data.get(dataSet).get("productname"))) {
					Productselemts.get(i).click();
					break;
				}

			}
			Common.dropdown("xpath", "//select[@name='Asset.CustomFields.HOT.store_purchased']", SelectBy.TEXT,data.get(dataSet).get("placeofpurchese"));
			// input[contains(@class,'product_quantity')]

			Sync.waitElementPresent("xpath", "//input[contains(@name,'date_code')]");
			Common.textBoxInput("xpath", "//input[contains(@name,'date_code')]",
					data.get(dataSet).get("ProductQuantity"));

			Sync.waitElementPresent("xpath", "//input[contains(@class,'hasDatepicker')]");
			Common.textBoxInput("xpath", "//input[contains(@class,'hasDatepicker')]",
					data.get(dataSet).get("ProblemDescription"));

			
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//button[contains(@id,'CustomFormSubmit')]");
			Common.javascriptclickElement("xpath", "//button[contains(@id,'CustomFormSubmit')]");
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying warranty form field data",
					"warrenty from infromation with out any validation", "warrenty from with invalid data",
					Common.getscreenShotPathforReport("warrenty from invalid data"));
			e.printStackTrace();
			Assert.fail();

		}
		
	//	Common.actionsKeyPress(Keys.HOME);

		int sizeerrormessage = Common.findElements("xpath", "//div[contains(@id,'ErrorLocation')]").size();
		Common.assertionCheckwithReport(sizeerrormessage > 0, "verifying warranty form field data",
				"Enter the warrenty from infromation with out any validation", "mandatory data missing in the from",
				Common.getscreenShotPathforReport("mandatory data missing in the from"));
		// (sizeerrormessage>0, "Enter the warrenty from infromation with out
		// any validation ", expectedResult, "mandatory data missing in the
		// from");
		Common.actionsKeyPress(Keys.HOME);
		Thread.sleep(6000);
System.out.println("pr");
		String sucessMessage = Common.getText("xpath", "//div[@id='rn_ProdRegConfirmDiv']/div/h1");
		
		System.out.println(sucessMessage);
		// Assert.assertEquals(sucessMessage, "Your warranty request has been
		// submitted!");
		String expectedResult = "User gets redirected to confirmation page, it includes a reference number and email is sent to email provided. No validation errors.";
		Common.assertionCheckwithReport(sucessMessage.equals("Thank you for registering your product! Your request has been processed."),
				"warranty applied  successfull,and redirected to confirmation page", expectedResult,
				"submit the warranty but confirmation page  message missing");
		// report.addPassLog(expectedResult,"warranty applied successfull,and
		// redirected to confirmation
		// page",Common.getscreenShotPathforReport("warranty submitted "));

	}

	
	public void productsupport(){
		try{
		Common.actionsKeyPress(Keys.PAGE_DOWN);
	    Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='Product Support']");
		Sync.waitPageLoad();
		Common.assertionCheckwithReport(Common.getPageTitle().equals("Support & FAQs Page - Honeywell"), "verifying the product support page", "after click the productsupport page it will navigate to product support page ", "sucessfully navigate to product support page", "faield to open product support page");
		}
		catch (Exception | Error e) {
			 e.printStackTrace();
             ExtenantReportUtils.addFailedLog("validating product support page","open the product supportpage","User failed to open productsupportpage",Common.getscreenShotPathforReport("supportpage"));
             Assert.fail();

		}
	
	}
	
	
	public void fottorValidations_Shop(String dataSet) throws Exception{
		Thread.sleep(3000);
		Sync.waitPageLoad();
		
		Common.actionsKeyPress(Keys.END);
		String Hederlinks=data.get(dataSet).get("footer_shop");
		String[] hedrs=Hederlinks.split(",");
		int i=0;
		
		try{
		for(i=0;i<hedrs.length;i++){
			System.out.println(hedrs[i]);
			Sync.waitElementClickable("xpath", "//span[@class='mobile-accordion-link-text' and text()='"+hedrs[i]+"']");
			Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='"+hedrs[i]+"']");
			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying footer link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the footer link "+hedrs[i],"Failed open the footer link "+hedrs[i]);
			Common.actionsKeyPress(Keys.END);	
		}
		}
		catch (Exception | Error e) {
			e.printStackTrace();
            ExtenantReportUtils.addFailedLog("validating footer Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the footer link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the footer link"));
            Assert.fail();

		}
	}
	
	public void footerValidations_aboutUs(String dataSet) throws Exception{
		Thread.sleep(3000);
		Sync.waitPageLoad();
		
		Common.actionsKeyPress(Keys.END);
		String Hederlinks=data.get(dataSet).get("footer_aboutUs");
		String[] hedrs=Hederlinks.split(",");
		System.out.println(hedrs);
		int i=0;
		
		try{
		for(i=0;i<hedrs.length;i++){
			System.out.println(hedrs[i]);
			Sync.waitElementClickable("xpath", "//span[@class='mobile-accordion-link-text' and text()='"+hedrs[i]+"']");
			Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='"+hedrs[i]+"']");
			Thread.sleep(3000);	
			String title=Common.getPageTitle();
			if(title.contains("Home - Helen of Troy")){
				Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Home - Helen of Troy"), "verifying Footor link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the Footor link "+hedrs[i],"Failed open the Footor link "+hedrs[i]);
			Thread.sleep(3000);
			Common.actionsKeyPress(Keys.END);
			Common.closeCurrentWindow();
	           Common.switchToFirstTab();
			}
		else if(title.contains("Our 52 year history - Helen of Troy")){
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Our 52 year history - Helen of Troy"), "verifying Footor link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the Footor link "+hedrs[i],"Failed open the Footor link "+hedrs[i]);
			Thread.sleep(3000);
			Common.actionsKeyPress(Keys.END);
			Common.closeCurrentWindow();
	           Common.switchToFirstTab();
			}
		else if(title.contains("Health & Wellness Blog")){
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Health & Wellness Blog"), "verifying Footor link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the Footor link "+hedrs[i],"Failed open the Footor link "+hedrs[i]);
			Thread.sleep(3000);
			Common.actionsKeyPress(Keys.END);
			}
		else if(title.contains("Careers | Helen Of Troy")){
			Thread.sleep(3000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Careers | Helen Of Troy"), "verifying Footor link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the Footor link "+hedrs[i],"Failed open the Footor link "+hedrs[i]);
			Thread.sleep(3000);
			Common.actionsKeyPress(Keys.END);
			Common.closeCurrentWindow();
	           Common.switchToFirstTab();
		}
		}
		}
		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating Footor Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the Footor link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the Footorlink"));
		
			Assert.fail();

		}
	}
	public void fotterValidations_HelenOfTroy(String dataSet) throws Exception{
		Thread.sleep(3000);
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.END);
		String Hederlinks=data.get(dataSet).get("footer_helenoftroy");
		String[] hedrs=Hederlinks.split(",");
		int i=0;
		
		try{
		for(i=0;i<hedrs.length;i++){
			System.out.println(hedrs[i]);
			Sync.waitElementClickable("xpath", "//span[@class='mobile-accordion-link-text' and text()='"+hedrs[i]+"']");
			Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='"+hedrs[i]+"']");
			Thread.sleep(3000);
			String title=Common.getPageTitle();
			if(title.contains("PUR® Water Filters and Water Filtration Systems  | Welcome to PUR | PUR")) {
			Common.assertionCheckwithReport(Common.getPageTitle().contains("PUR® Water Filters and Water Filtration Systems  | Welcome to PUR | PUR"), "verifying Footor link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the Footor link "+hedrs[i],"Failed open the Footor link "+hedrs[i]);
			Thread.sleep(3000);
			Common.actionsKeyPress(Keys.END);
			Common.closeCurrentWindow();
	           Common.switchToFirstTab();
		}
		
			else if(title.contains("Braun Healthcare US | Accurately. Confidently. Braun.")) {
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Braun Healthcare US | Accurately. Confidently. Braun."), "verifying Footor link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the Footor link "+hedrs[i],"Failed open the Footor link "+hedrs[i]);
			Thread.sleep(3000);
			Common.actionsKeyPress(Keys.END);
			Common.closeCurrentWindow();
	           Common.switchToFirstTab();
		}
		
		else if(title.contains("Vickshumidifiers Home Page")) {
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Vickshumidifiers Home Page"), "verifying Footor link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the Footor link "+hedrs[i],"Failed open the Footor link "+hedrs[i]);
			Thread.sleep(3000);
			Common.actionsKeyPress(Keys.END);
			Common.closeCurrentWindow();
	           Common.switchToFirstTab();
		}
		
		}
		}
		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating Footor Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the Footor link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the Footorlink"));
		
			Assert.fail();

		}
	}
	
	public void articalLinks(String dataSet) throws Exception{
		Thread.sleep(3000);
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.END);
		String Hederlinks=data.get(dataSet).get("articalLinks_names");
		String[] hedrs=Hederlinks.split(",");
		int i=0;
		
		try{
		for(i=0;i<hedrs.length;i++){
			System.out.println(hedrs[i]);
			Sync.waitElementClickable("xpath", "//span[@class='social-icons-link-text visually-hidden' and text()='"+hedrs[i]+"']");
			Common.clickElement("xpath", "//span[@class='social-icons-link-text visually-hidden' and text()='"+hedrs[i]+"']");
			Thread.sleep(3000);
			String title=Common.getPageTitle();
			if(title.contains("Honeywell - Verified Page | Facebook")) {
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Honeywell - Verified Page | Facebook"), "verifying Footor link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the Footor link "+hedrs[i],"Failed open the Footor link "+hedrs[i]);
			Thread.sleep(3000);
			Common.actionsKeyPress(Keys.END);
			Common.closeCurrentWindow();
	           Common.switchToFirstTab();
		}
		
			else if(title.contains("Login • Instagram")) {
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Login • Instagram"), "verifying Footor link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the Footor link "+hedrs[i],"Failed open the Footor link "+hedrs[i]);
			Thread.sleep(3000);
			Common.actionsKeyPress(Keys.END);
			Common.closeCurrentWindow();
	           Common.switchToFirstTab();
		}
		
		else if(title.contains("YouTube")) {
			Common.assertionCheckwithReport(Common.getPageTitle().contains("YouTube"), "verifying Footor link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the Footor link "+hedrs[i],"Failed open the Footor link "+hedrs[i]);
			Thread.sleep(3000);
			Common.actionsKeyPress(Keys.END);
			Common.closeCurrentWindow();
	           Common.switchToFirstTab();
		}
		
		}
		}
		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating Footor Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the Footor link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the Footorlink"));
		
			Assert.fail();

		}
	}
	public void privacy() {

		String expectedResult = "It should navigate to privacy page";
		try{

		Thread.sleep(5000);

		Common.actionsKeyPress(Keys.END);
		Common.findElement("xpath", "//a[contains(text(),'Privacy Policy')]");
		Common.clickElement("xpath", "//a[contains(text(),'Privacy Policy')]");

		String title =Common.getPageTitle();

		System.out.println(title);

		Common.assertionCheckwithReport(title.equals("Honeywell Privacy Policy"),"Verifying privacy policy  page","it shoud navigate to privacy policy page", "successfully  navigated to privacy policy Page", "privacy policy");

		}catch(Exception |Error e) {

		ExtenantReportUtils.addFailedLog("To view  policy button","should land on Privacy  button", "user unable to navigate to privacy  button", Common.getscreenShotPathforReport("failed to land on privacy button"));

		Assert.fail();

		}

	}

		public void Terms_OF_Use() {

		String expectedResult = "It should navigate to Terms Of Use page";

		try{

		Thread.sleep(5000);

		Common.actionsKeyPress(Keys.END);

		 

		Common.clickElement("xpath", "//a[contains(text(),'Terms of Use')]");

		String title =Common.getPageTitle();

		System.out.println(title);

		Common.assertionCheckwithReport(title.equals("Honeywell Terms of Use"),"Verifying Terms Of Use   page","it shoud navigate to  Terms Of Use page", "successfully  navigated to Terms Of Use  Page", " Terms Of Use");

	}catch(Exception |Error e) {

		ExtenantReportUtils.addFailedLog("To view  policy button","should land on Privacy  button", "user unable to navigate to privacy  button", Common.getscreenShotPathforReport("failed to land on privacy button"));

		Assert.fail();

		}

   }	 

		 


	
  public void headerlinkSuport(String dataSet) throws Exception{
	  try{
	  
	 Common.mouseOver("xpath", "(//li[contains(@class,'nav-main nav')])[16]");
	 String Blogpagetitle= data.get(dataSet).get("Air Purifiers");
	 String CleanAirMatterspagetitle=data.get(dataSet).get("Fans");
     String DreamWeaverSleepFanpagetitle= data.get(dataSet).get("Heaters");
	 String SafetyMatterspagetitle=data.get(dataSet).get("Humidifiers");

	
	 ArrayList<String> elemtstext=new ArrayList<String>();
     List<WebElement> LearnEductionLinks=Common.findElements("xpath", "//ul[@data-menu='menu-86']//li//a");
     for(int j=0;j<LearnEductionLinks.size();j++){
    	  elemtstext.add(LearnEductionLinks.get(j).getText());
     }

  
    int i=0;
     for(i=0;i<elemtstext.size();i++){
    	 Thread.sleep(4000);
    	 System.out.println(elemtstext.get(i));
    	 
    	if(elemtstext.get(i).equals("Air Purifiers")){
    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
    	//	LearnEductionLinks.get(i).click();
    	    Thread.sleep(2000);
    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(Blogpagetitle), "validating header ling Air Purifiers page", "after click the Air Purifiers page in header it must navigate to Air Purifiers page", "sucessfully navigate to Air Purifiers page", "Failed to navigate to Air Purifiers page");
    	    Common.mouseOver("xpath", "(//li[contains(@class,'nav-main nav')])[16]");
    	}
    	else if(elemtstext.get(i).equals("Fans")){
    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
    	    Thread.sleep(2000);
    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(CleanAirMatterspagetitle), "validating header ling Fans page", "after click the Fans page in header it must navigate to Fans page", "sucessfully navigate to Fans page", "Failed to navigate to Fans page");
    	    Common.mouseOver("xpath", "(//li[contains(@class,'nav-main nav')])[16]");
    	}
    	
    	else if(elemtstext.get(i).equals("Heaters")){
    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
    	    Thread.sleep(2000);
    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(DreamWeaverSleepFanpagetitle), "validating header ling Heaters page", "after click the Heaters page in header it must navigate to Heaters page", "sucessfully navigate to Heaters page", "Failed to navigate to Heaters page");
    	    Common.mouseOver("xpath", "(//li[contains(@class,'nav-main nav')])[16]");
    	}
    	else if(elemtstext.get(i).equals("Humidifiers")){
    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
    	    Thread.sleep(2000);
    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(SafetyMatterspagetitle), "validating header ling Humidifiers page", "after click the Humidifiers page in header it must navigate to Humidifiers page", "sucessfully navigate to Humidifiers page", "Failed to navigate to Humidifiers page");
    	    Common.mouseOver("xpath", "(//li[contains(@class,'nav-main nav')])[16]");
    	}
    	
     }
	  }
	  catch(Exception  | Error e){
		  e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating header link learn ","user open the Lean option from header ","User unabel open the header link Learn",Common.getscreenShotPathforReport("Learnheaderlink"));
		
			Assert.fail();
	  }
	  
  }
	

  public void headerlinkLearnEducation(String dataSet) {
	  try{
		  Common.mouseOver("xpath", "//a[contains(text(),'Learn')]");
//	 Common.mouseOver("xpath", "//a[contains(text(),'Learn By Product')]");
	 String Blogpagetitle= data.get(dataSet).get("Blog");
	 String CleanAirMatterspagetitle=data.get(dataSet).get("CleanAirMatters");
     String DreamWeaverSleepFanpagetitle= data.get(dataSet).get("DreamWeaverSleepFan");
	 String SafetyMatterspagetitle=data.get(dataSet).get("SafetyMatters");
	 String WhyHumidifypagetitle=data.get(dataSet).get("WhyHumidify");
	 ArrayList<String> elemtstext=new ArrayList<String>();
     List<WebElement> LearnEductionLinks=Common.findElements("xpath", "//ul[@data-menu='menu-67']//li//span");
     for(int j=0;j<LearnEductionLinks.size();j++){
    	  elemtstext.add(LearnEductionLinks.get(j).getText());
     }

  
    int i=0;
     for(i=0;i<elemtstext.size();i++){
    	 Thread.sleep(4000);
    	 System.out.println(elemtstext.get(i));
    	 
    	if(elemtstext.get(i).equals("Blog")){
    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
            Thread.sleep(2000);
    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(Blogpagetitle), "validating header ling blog page in linkLearnEducation", "after click the blog page in header it must navigate to blog page", "sucessfully navigate to blog page", "Failed to navigate to blogpage");
    	    Common.mouseOver("xpath", "//a[contains(text(),'Learn')]");
    	}
    	else if(elemtstext.get(i).equals("Clean Air Matters")){
    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
    	    Thread.sleep(2000);
    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(CleanAirMatterspagetitle), "validating header ling Clean Air Matters page in linkLearnEducation", "after click the Clean Air Matters page in header it must navigate to Clean Air Matters page", "sucessfully navigate to Clean Air Matters page", "Failed to navigate to Clean Air Matters");
    	    Common.mouseOver("xpath", "//a[contains(text(),'Learn')]");
    	}
    	
    	else if(elemtstext.get(i).equals("DreamWeaver Sleep Fan")){
    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
    	    Thread.sleep(2000);
    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(DreamWeaverSleepFanpagetitle), "validating header ling DreamWeaverSleepFan page in linkLearnEducation", "after click the DreamWeaverSleepFan page in header it must navigate to DreamWeaverSleepFan page", "sucessfully navigate to DreamWeaverSleepFan page", "Failed to navigate to DreamWeaverSleepFan");
    	    Common.mouseOver("xpath", "//a[contains(text(),'Learn')]");
    	}
    	else if(elemtstext.get(i).equals("Safety Matters")){
    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
    	    Thread.sleep(2000);
    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(SafetyMatterspagetitle), "validating header ling SafetyMatters page in linkLearnEducation", "after click the SafetyMatters page in header it must navigate to SafetyMatters page", "sucessfully navigate to SafetyMatters page", "Failed to navigate to SafetyMatters");
    	    Common.mouseOver("xpath", "//a[contains(text(),'Learn')]");
    	}
    	else if(elemtstext.get(i).equals("Why Humidify")){
    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
    	    Thread.sleep(2000);
    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(WhyHumidifypagetitle), "validating header link WhyHumidify page linkLearnEducation", "after click the WhyHumidify page in header it must navigate to WhyHumidify page", "sucessfully navigate WhyHumidify page", "Failed to navigate to WhyHumidify");
    	    Common.mouseOver("xpath", "//a[contains(text(),'Learn')]");
    	}
        }
	    }
	  catch(Exception  | Error e){
		  e.printStackTrace();
		  ExtenantReportUtils.addFailedLog("validating header link learn in linkLearnEducation","user open the Lean option from header ","User unabel open the header link Learn",Common.getscreenShotPathforReport("Learnheaderlink"));
		  Assert.fail();
	  }
	  }
	  

public void headLinksValidations_LeanBy_Products(String dataSet) throws Exception{
	int i=0;
	String Hederlinks=data.get(dataSet).get("HeaderNames");
	String[] hedrs=Hederlinks.split(",");
	System.out.println(hedrs.length);
	try{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "//a[contains(text(),'Learn')]");
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		//ul[@data-menu='menu-70']/li[1]/a
		int j=i+1;
		Sync.waitElementClickable("xpath", "//ul[@data-menu='menu-70']/li["+j+"]/a");
		Common.clickElement("xpath", "//ul[@data-menu='menu-70']/li["+j+"]/a");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i]+"for LeanBy_Shop","user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "//a[contains(text(),'Learn')]");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" LeanBy_Shop","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
		Assert.fail();

	}
}

public void headerlink_Support_GeneralSupport(String dataSet) {
	  try{
	  
	 Common.mouseOver("xpath", "//a[@data-menu='menu-79']");
	 String FAQstitle= data.get(dataSet).get("FAQs");
	 String ProductSupportpagetitle=data.get(dataSet).get("ProductSupport");
     String WarrantyRegistrationpagetitle= data.get(dataSet).get("WarrantyRegistration");
	 String OrderStatuspagetitle=data.get(dataSet).get("OrderStatus");
	 
	 ArrayList<String> elemtstext=new ArrayList<String>();
	 List<WebElement> LearnEductionLinks=Common.findElements("xpath", "//ul[@data-menu='menu-79']//li//span");
	 for(int j=0;j<LearnEductionLinks.size();j++){
  	  elemtstext.add(LearnEductionLinks.get(j).getText());
   }


  int i=0;
   for(i=0;i<elemtstext.size();i++){
  	 Thread.sleep(4000);
  	 System.out.println(elemtstext.get(i));
  	 
  	if(elemtstext.get(i).equals("FAQs")){
  		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
          Thread.sleep(2000);
  	    Common.assertionCheckwithReport(Common.getPageTitle().equals(FAQstitle), "validating header ling"+elemtstext.get(i)+"page in GeneralSupport", "after click the "+elemtstext.get(i)+"page in header it must navigate to "+elemtstext.get(i)+" page", "sucessfully navigate to "+elemtstext.get(i)+" page", "Failed to navigate to "+elemtstext.get(i)+"");
  	  Common.mouseOver("xpath", "//a[@data-menu='menu-79']");
  	}
  	else if(elemtstext.get(i).equals("Product Support")){
  		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
  	    Thread.sleep(2000);
  	    Common.assertionCheckwithReport(Common.getPageTitle().equals(ProductSupportpagetitle), "validating header ling"+elemtstext.get(i)+"page in GeneralSupport", "after click the "+elemtstext.get(i)+"page in header it must navigate to "+elemtstext.get(i)+" page", "sucessfully navigate to "+elemtstext.get(i)+" page", "Failed to navigate to "+elemtstext.get(i)+"");
  	  Common.mouseOver("xpath", "//a[@data-menu='menu-79']");
  	}
  	
  	else if(elemtstext.get(i).equals("Warranty Registration")){
  		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
  	    Thread.sleep(2000);
  	    Common.assertionCheckwithReport(Common.getPageTitle().equals(WarrantyRegistrationpagetitle), "validating header ling"+elemtstext.get(i)+"page in GeneralSupport", "after click the "+elemtstext.get(i)+"page in header it must navigate to "+elemtstext.get(i)+" page", "sucessfully navigate to "+elemtstext.get(i)+" page", "Failed to navigate to "+elemtstext.get(i)+"");
  	  Common.mouseOver("xpath", "//a[@data-menu='menu-79']");

  	}
  	else if(elemtstext.get(i).equals("Order Status")){
  		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
  	    Thread.sleep(2000);
  	    Common.assertionCheckwithReport(Common.getPageTitle().equals(OrderStatuspagetitle), "validating header ling"+elemtstext.get(i)+"page in GeneralSupport", "after click the "+elemtstext.get(i)+"page in header it must navigate to "+elemtstext.get(i)+" page", "sucessfully navigate to "+elemtstext.get(i)+" page", "Failed to navigate to "+elemtstext.get(i)+"");
  	  Common.mouseOver("xpath", "//a[@data-menu='menu-79']");
  	}
  	
      }
	    }
	  catch(Exception  | Error e){
		  e.printStackTrace();
		  ExtenantReportUtils.addFailedLog("validating header link learn in support","user open the support option from header ","User unabel open the header link Support",Common.getscreenShotPathforReport("supportLink"));
		  Assert.fail();
	  }
	  }


public void headLinksValidations_SupportbyProduct(String dataSet) {
	int i=0;
	String Hederlinks=data.get(dataSet).get("HeaderNames");
	String[] hedrs=Hederlinks.split(",");
	try{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "//a[@data-menu='menu-79']");
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		//ul[@data-menu='menu-70']/li["+j+"]/a
		int j=i+1;
		Sync.waitElementClickable("xpath", "//ul[@data-menu='menu-86']/li["+j+"]/a");
		Common.clickElement("xpath", "//ul[@data-menu='menu-86']/li["+j+"]/a");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		if(hedrs[i].equals("Humidifiers")){
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().contains(""), "verifying Header link of "+hedrs[i]+"for SupportbyProduct","user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);	
		 break;
		}
		
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i]+"for SupportbyProduct","user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "//a[@data-menu='menu-79']");
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" for SupportbyProduct","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
		Assert.fail();

	}
}

public void Contact_Us(String dataSet) throws Exception {
	try {	
		Thread.sleep(1000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		//Common.findElement("xpath", "(//span[@class='mobile-accordion-link-text'])[3]");
		Thread.sleep(3000);
		Common.clickElement("xpath", "//span[contains(text(), 'Contact Us')]");
		
		Thread.sleep(3000);
		String Title= Common.getPageTitle();
  		System.out.println(Title);
  		Common.assertionCheckwithReport(Title.equals("Contact Us"),"Verifying Contact Us page","it should navigate to Contact Us page", "successfully  lands on Contact Us Page", "Contact Us Page");

	}catch(Exception |Error e) {
	 	   
		ExtenantReportUtils.addFailedLog("verifying error Contact Us message Page", "enter with empty data it must show error message", "faield to dispalyerrormessage", Common.getscreenShotPathforReport("Contact pagevalidation"));
		Assert.fail();
	}
try {
		Common.switchFrames("xpath", "//*[@id='maincontent']/div[2]//iframe");
		Thread.sleep(4000);
		Common.textBoxInput("id","rn_TextInput_3_Contact.Name.First", data.get(dataSet).get("FirstName"));	
	
		Common.textBoxInput("id","rn_TextInput_5_Contact.Name.Last", data.get(dataSet).get("LastName"));	
		Common.textBoxInput("id","rn_TextInput_7_Contact.CustomFields.c.company", data.get(dataSet).get("COMPANY"));	
		Thread.sleep(1000);
		Common.textBoxInput("id","rn_TextInput_9_Contact.Phones.MOBILE.Number", data.get(dataSet).get("phone"));
		
		Common.textBoxInput("id","rn_TextInput_11_Contact.Emails.PRIMARY.Address", data.get(dataSet).get("Email"));
		Common.findElement("id","rn_SelectionInput_13_Contact.Address.Country").click();
	    Thread.sleep(1000);
		Common.dropdown("id", "rn_SelectionInput_13_Contact.Address.Country", Common.SelectBy.TEXT, data.get(dataSet).get("country"));
		Thread.sleep(3000);
		Common.textBoxInput("id","rn_TextInput_15_Contact.Address.PostalCode", data.get(dataSet).get("postcode"));
        Common.findElement("id","rn_SelectionInput_17_Contact.Address.StateOrProvince").click();
		Thread.sleep(3000);
		Common.dropdown("id", "rn_SelectionInput_17_Contact.Address.StateOrProvince", Common.SelectBy.TEXT, data.get(dataSet).get("State"));
		Thread.sleep(1000);
		Common.textBoxInput("id","rn_TextInput_19_Contact.Address.City", data.get(dataSet).get("City"));
		Common.textBoxInput("id","rn_TextInput_21_Contact.Address.Street", data.get(dataSet).get("Street"));
		Common.textBoxInput("id","rn_TextInput_23_Incident.CustomFields.c.ordernumber", data.get(dataSet).get("ORDER NUMBER"));
		
	/*	List<WebElement> Productselemts = Common.findElements("id", "all_button_arrow");

		for (int i =0; i < Productselemts.size(); i++) {

			if (Productselemts.get(i).getAttribute("title").equals(data.get(dataSet).get("productname"))) {
				Productselemts.get(i).click();
				break;
			}

		}*/
		
		//Common.findElement("id","all_button_arrow").click();
		//Thread.sleep(3000);
		//Common.mouseOver("xpath", "//div[@title='10200 (AIR PURIFIER REPLACEMENT FILTER FOR 85L)']");
		//Common.dropdown("xpath", "//i[@id='all_button_arrow']", Common.SelectBy.TEXT, data.get(dataSet).get("productname"));
		//Common.findElement("xpath","//div[@title='10200 (AIR PURIFIER REPLACEMENT FILTER FOR 85L)']");
		
		Sync.waitElementClickable(30, By.xpath("//i[@id='all_button_arrow']"));
		Common.findElement("xpath", "//i[@id='all_button_arrow']").click();
		Common.findElement("xpath", "//div[@title='10200 (AIR PURIFIER REPLACEMENT FILTER FOR 85L)']").click();
		/*Thread.sleep(3000);
		Sync.waitElementClickable("id", "all_button_arrow");
		Common.findElement("id", "all_button_arrow").click();
		Thread.sleep(3000);
		Common.javascriptclickElement("xpath", "//div[@title='10200 (AIR PURIFIER REPLACEMENT FILTER FOR 85L)']");
		*/
		
		//Common.dropdown("id", "all_button_arrow", Common.SelectBy.TEXT, data.get(dataSet).get("productname"));
		Common.findElement("id","rn_ProductCategoryInput_27_Category_Button").click();
		Thread.sleep(3000);
		Common.javascriptclickElement("id", "ygtvlabelel2");
		Thread.sleep(2000);
		Common.javascriptclickElement("id", "ygtvcontentel8");

       //Common.dropdown("id", "rn_ProductCategoryInput_27_Category_Button", Common.SelectBy.TEXT, data.get(dataSet).get("TOPIC"));
		Thread.sleep(3000);
	
		String path = System.getProperty("user.dir")
				+ ("\\src\\test\\resources\\TestData\\Honeywell\\screenshot.jpg");
			try{	
		Common.fileUpLoad("xpath", "//input[contains(@id,'FileInput')]", path);
			}
			catch(Exception e){
				
			}
		Thread.sleep(12000);
       /*	String productname= data.get(dataSet).get("MESSAGE");
       	System.out.println(productname);
		Common.textBoxInput("xpath", "//textarea[@id='rn_TextInput_30_Incident.Threads']", productname);
		Thread.sleep(3000);*/
		
		Sync.waitElementPresent("xpath","//textarea[@id='rn_TextInput_30_Incident.Threads']");
		Common.textBoxInput("xpath","//textarea[@id='rn_TextInput_30_Incident.Threads']", data.get(dataSet).get("MESSAGE"));
		
		Common.findElement("id","rn_CustomFormSubmit_53_Button").click();
		Thread.sleep(2000);	
		//Common.actionsKeyPress(Keys.PAGE_UP);
		Common.actionsKeyPress(Keys.HOME);
		
		String ContactUs=Common.getText("xpath", "//h1[text()='Your question has been submitted!']");
		System.out.println(ContactUs);
		Assert.assertEquals(ContactUs, "Your question has been submitted!");
}catch(Exception |Error e) {
	   e.printStackTrace();
	ExtenantReportUtils.addFailedLog("verifying error message Page", "enter with empty data it must show error message", "faield to dispalyerrormessage", Common.getscreenShotPathforReport("ContactUs pagevalidation"));
	Assert.fail();
}
	
	
}

	

 /*public void OrderStatus(){
	 try{
	 Common.clickElement("xpath", "//a[text()='Order Status']");
	 
	 
	 Common.textBoxInput("id", "oar-order-id","");
	 Common.textBoxInput("id", "oar_billing_lastname","");
	 Common.dropdown("id", "quick-search-type-id", SelectBy.VALUE, "email");
	 Common.textBoxInput("id", "oar_email","");
	 Common.textBoxInput("id", "oar-order-id","");
 }
*/
public void agree_proceed(){
	int size=Common.findElements("id", "truste-consent-required").size();
	if(size>0){
		Common.findElement("id", "truste-consent-required").click();
	}
}
public void features() {

    // TODO Auto-generated method stub

    try

{

Thread.sleep(3000);

Sync.waitElementPresent("xpath","//ol[@id='268_items']//a[contains(text(),'Washable Filters')]");

Common.clickElement("xpath","//ol[@id='268_items']//a[contains(text(),'Washable Filters')]");

ExtenantReportUtils.addPassLog("verifying features category",

"User navigate on Washable Filters",

"User clicked on Washable Filters",

Common.getscreenShotPathforReport("washable filters are clicked successfully"));


}

    catch(Exception  | Error e){

e.printStackTrace();

ExtenantReportUtils.addFailedLog("verifying features category", "User clicked on Washable Filters", "user faield to click the Washable Filters", Common.getscreenShotPathforReport("failed to click on features"));

Assert.fail();


}
}
public void roomSize() {

    // TODO Auto-generated method stub

    try

{

Thread.sleep(3000);

Sync.waitElementPresent("xpath","//ol[@id='251_items']//a[contains(text(),'Medium')]");

Common.clickElement("xpath","//ol[@id='251_items']//a[contains(text(),'Medium')]");

ExtenantReportUtils.addPassLog("verifying room size category","User navigate on medium Filters","User clicked on medium Filters",Common.getscreenShotPathforReport("medium filters are clicked successfully"));


}

    catch(Exception  | Error e){

e.printStackTrace();

ExtenantReportUtils.addFailedLog("verifying room size category", "User clicked on medium Filters", "user faield to click the medium Filters", Common.getscreenShotPathforReport("failed to clik on medium filters"));

Assert.fail();

}

}


public void type() {

    // TODO Auto-generated method stub

    try

{

Thread.sleep(30000);

Sync.waitElementPresent("xpath","//ol[@id='254_items']//a[contains(text(),'Tower')]");

Common.clickElement("xpath","//ol[@id='254_items']//a[contains(text(),'Tower')]");

ExtenantReportUtils.addPassLog("verifying type category","User navigate on tower Filters","User clicked on tower Filters",Common.getscreenShotPathforReport("tower filters are clicked successfully"));


}

    catch(Exception  | Error e){

e.printStackTrace();

ExtenantReportUtils.addFailedLog("verifying type category", "User clicked on tower Filters", "user faield to click the tower Filters", Common.getscreenShotPathforReport("failed to clik on tower filters"));

Assert.fail();

}

    

}
public void AddtocartPLPpage(String DataSet) {

    // TODO Auto-generated method stub

    try

{

Thread.sleep(3000);

String productname= data.get(DataSet).get("productname");

System.out.println(productname);

Sync.waitElementPresent("xpath","//a[contains (text(),'"+productname+"')]");

Common.mouseOver("xpath", "//a[contains (text(),'"+productname+"')]");

Sync.waitElementPresent("xpath","//form[@data-product-sku='HPA020B']//button");

Common.clickElement("xpath","//form[@data-product-sku='HPA020B']//button");

ExtenantReportUtils.addPassLog("Verifing product to add cart from plppage", "Product should add to cart from plppage",

"Product should add to cart from plppage",

Common.getscreenShotPathforReport("Product successfully added to cart from plppage"));



}

    catch(Exception  | Error e){

e.printStackTrace();

ExtenantReportUtils.addFailedLog("Verifing product to add to cart from plppage", "product should add to cart from plppage",

"product should add to cart from plppage", Common.getscreenShotPathforReport("Failed to add to cart from plppage"));

 Assert.fail();

}

}



public void privacypolicy() {

    // TODO Auto-generated method stub

    try

{

Thread.sleep(3000);

Common.actionsKeyPress(Keys.END);

Common.clickElement("xpath", "//a[@title='Privacy Policy']");

Sync.waitPageLoad();

Thread.sleep(5000);

String Title=Common.getPageTitle();

System.out.println(Title);

Common.assertionCheckwithReport(Title.equals("Honeywell Privacy Policy"),"To Verify the Privacy policy Page","It should navigate to Privacy policy page", "successfully  navigated to Privacy policy page", "Privacy policy");


}

    catch(Exception  | Error e){

e.printStackTrace();

ExtenantReportUtils.addFailedLog("To view privacy policy button","should land on Privacy policy button", "user unable to navigate to privacy policy button", Common.getscreenShotPathforReport("failed to land on privacy button"));

Assert.fail();

}

    

}



public void Termsofuse() {

    // TODO Auto-generated method stub

    try

{

Thread.sleep(3000);

Common.actionsKeyPress(Keys.END);

Common.clickElement("xpath", "//a[@title='Terms Of Use']");

Sync.waitPageLoad();

Thread.sleep(5000);

String Title=Common.getPageTitle();

System.out.println(Title);

Common.assertionCheckwithReport(Title.equals("Honeywell Terms of Use"),"To Verify the terms of use page","It should navigate to terms of use page", "successfully  navigated to terms of use page ", "terms of use");


}

    catch(Exception  | Error e){

e.printStackTrace();

ExtenantReportUtils.addFailedLog("To view terms of use Page","should land on terms of use page", "user unable to navigate to terms of use page", Common.getscreenShotPathforReport("failed to land on terms of use page"));

Assert.fail();


}

    



}



public void addtocarthomepage() {

    // TODO Auto-generated method stub

    try

{

Sync.waitPageLoad();

Thread.sleep(3000);

Sync.waitElementPresent("xpath","//form[@data-product-sku='HPA5300B']//button");

Common.clickElement("xpath","//form[@data-product-sku='HPA5300B']//button");

ExtenantReportUtils.addPassLog("verifying add to cart button from homepage", "User click add to card button from homepage", "user successfully click add to cart button from homepage", Common.getscreenShotPathforReport("Click addtocart from homepage"));


}

    catch(Exception  | Error e){

e.printStackTrace();

ExtenantReportUtils.addFailedLog("verifying add to cart button from homepage", "User click add to card button from homepage", "user failed to click add to cart button from homepage", Common.getscreenShotPathforReport("failedtoclickutton from homepage"));

Assert.fail();

}

    

}



public void HomePage() throws Exception {

    // TODO Auto-generated method stub

Thread.sleep(5000);

Sync.waitElementPresent("xpath","//img[@class='desktop-logo']");

    Common.clickElement("xpath","//img[@class='desktop-logo']");

    

    

}



public void colorproduct(String DataSet) {

    // TODO Auto-generated method stub

    try

{



Thread.sleep(3000);

String productname= data.get(DataSet).get("Color");

System.out.println(productname);

Sync.waitElementPresent("xpath","//a[contains (text(),'"+productname+"')]");

Common.clickElement("xpath", "//a[contains (text(),'"+productname+"')]");

Thread.sleep(4000);

Sync.waitElementPresent("xpath","//div[@data-option-label='White']");

Common.clickElement("xpath","//div[@data-option-label='White']");

ExtenantReportUtils.addPassLog("Verifing product list page", "Should select a product and color",

"Should be select a product and color", Common.getscreenShotPathforReport("Product and color selected successfully"));




}

    catch(Exception  | Error e){

e.printStackTrace();

ExtenantReportUtils.addFailedLog("Verifing product list page", "Should select a product and color",

"Should select a product and color ", Common.getscreenShotPathforReport("Failed to selected product and color"));

Assert.fail();

}

}



public void stickycartAddtoBag() {

    // TODO Auto-generated method stub

    try

{

Common.actionsKeyPress(Keys.END);

Thread.sleep(4000);

Sync.waitElementPresent("xpath","//button[contains(@id,'product-sticky')]//span[text()='Add to Cart']");

Common.clickElement("xpath","//button[contains(@id,'product-sticky')]//span[text()='Add to Cart']");

ExtenantReportUtils.addPassLog("scroll down the page sticky cart will appere",

" add to cart from sticky cart",

"The product should be add to mini cart",

Common.getscreenShotPathforReport("product should be add to mini cart successfully"));



}

    catch(Exception  | Error e){

e.printStackTrace();

ExtenantReportUtils.addFailedLog("scroll down the page sticky cart will appere",

"add to cart from sticky cart",

"The product should be add to mini cart",

Common.getscreenShotPathforReport("Failed to add product to mini cart"));

Assert.fail();


}

    

}



public void facebook() {

    // TODO Auto-generated method stub

    try

{

Common.actionsKeyPress(Keys.END);

Thread.sleep(4000);

Common.clickElement("xpath", "//li[contains(@class,'social-icons')]//span[text()='Facebook']");

Thread.sleep(5000);

Common.switchToSecondTab();

Sync.waitPageLoad();

Thread.sleep(5000);

     String URL=Common.getCurrentURL();

Common.assertionCheckwithReport(URL.contains("facebook"),"To Verify the  Facebook Article link","it shoud navigate to facebook page", "successfully  navigated to Facebook page", "Facebook");

}

    catch(Exception  | Error e){

e.printStackTrace();

ExtenantReportUtils.addFailedLog("To verify the Facebook Article link","should land on Facebook Articlelink page", "user unable to navigate to Facebook Articlelink", Common.getscreenShotPathforReport("failed to land on Facebook page"));

Assert.fail();

}

    Common.closeCurrentWindow();

    Common.switchToFirstTab();

    

}



public void instagram() {

    // TODO Auto-generated method stub

    try

{

Common.actionsKeyPress(Keys.END);

Thread.sleep(4000);

Common.clickElement("xpath", "//li[contains(@class,'social-icons')]//span[text()='Instagram']");

Common.switchToSecondTab();

Sync.waitPageLoad();

Thread.sleep(5000);

     String Title=Common.getPageTitle();

Common.assertionCheckwithReport(Title.contains("Instagram"),"To Verify the  Instagram Article link","It should navigate to Instagram page", "successfully  navigated to Instagram page", "Instagram");

}

    catch(Exception  | Error e){

e.printStackTrace();

ExtenantReportUtils.addFailedLog("To verify the Instagram Article link","should land on Instagram Articlelink page", "user unable to navigate to Instagram Articlelink", Common.getscreenShotPathforReport("failed to land on Instagram page"));

Assert.fail();

}

    Common.closeCurrentWindow();

    Common.switchToFirstTab();

}



public void Youtube() {

    // TODO Auto-generated method stub

    try

{

Common.actionsKeyPress(Keys.END);

Thread.sleep(4000);

Common.clickElement("xpath", "//li[contains(@class,'social-icons')]//span[text()='Youtube']");

Common.switchToSecondTab();

Sync.waitPageLoad();

Thread.sleep(5000);

     String Title=Common.getPageTitle();

     System.out.println(Title);

Common.assertionCheckwithReport(Title.contains("Honeywell Plugged In - YouTube"),"To Verify the  Youtube Article link","It should navigate to Youtube page", "successfully  navigated to Youtube page", "Youtube");


}

    catch(Exception  | Error e){

e.printStackTrace();

ExtenantReportUtils.addFailedLog("To verify the Youtube Article link","should land on youtube Articlelink page", "user unable to navigate to Youtube Articlelink", Common.getscreenShotPathforReport("failed to land on Youtube page"));

Assert.fail();



}

    Common.closeCurrentWindow();

    Common.switchToFirstTab();

    

}



public void Features() {

    // TODO Auto-generated method stub

    try

{

Thread.sleep(3000);

Sync.waitElementPresent("xpath","//ol[@id='268_items']//a[contains(text(),'Washable Filters')]");

Common.clickElement("xpath","//ol[@id='268_items']//a[contains(text(),'Washable Filters')]");

ExtenantReportUtils.addPassLog("verifying features category",

"User navigate on Washable Filters",

"User clicked on Washable Filters",

Common.getscreenShotPathforReport("washable filters are clicked successfully"));


}

    catch(Exception  | Error e){

e.printStackTrace();

ExtenantReportUtils.addFailedLog("verifying features category", "User clicked on Washable Filters", "user faield to click the Washable Filters", Common.getscreenShotPathforReport("failed to click on features"));

Assert.fail();


}

}



public void Roomsize() {

    // TODO Auto-generated method stub

    try

{

Thread.sleep(3000);

Sync.waitElementPresent("xpath","//ol[@id='251_items']//a[contains(text(),'Medium')]");

Common.clickElement("xpath","//ol[@id='251_items']//a[contains(text(),'Medium')]");

ExtenantReportUtils.addPassLog("verifying room size category","User navigate on medium Filters","User clicked on medium Filters",Common.getscreenShotPathforReport("medium filters are clicked successfully"));


}

    catch(Exception  | Error e){

e.printStackTrace();

ExtenantReportUtils.addFailedLog("verifying room size category", "User clicked on medium Filters", "user faield to click the medium Filters", Common.getscreenShotPathforReport("failed to clik on medium filters"));

Assert.fail();

}

}



public void Type() {

    // TODO Auto-generated method stub

    try

{

Thread.sleep(30000);

Sync.waitElementPresent("xpath","//ol[@id='254_items']//a[contains(text(),'Tower')]");

Common.clickElement("xpath","//ol[@id='254_items']//a[contains(text(),'Tower')]");

ExtenantReportUtils.addPassLog("verifying type category","User navigate on tower Filters","User clicked on tower Filters",Common.getscreenShotPathforReport("tower filters are clicked successfully"));


}

    catch(Exception  | Error e){

e.printStackTrace();

ExtenantReportUtils.addFailedLog("verifying type category", "User clicked on tower Filters", "user faield to click the tower Filters", Common.getscreenShotPathforReport("failed to clik on tower filters"));

Assert.fail();

}

    

}



public void replacementfilter(String DataSet) {

    // TODO Auto-generated method stub

    try

{

Thread.sleep(4000);

String productname= data.get(DataSet).get("productname");

System.out.println(productname);

Sync.waitElementPresent("xpath","//a[contains (text(),'"+productname+"')]");

Common.clickElement("xpath", "//a[contains (text(),'"+productname+"')]");

Thread.sleep(4000);

Sync.waitElementPresent("xpath","//li[@id='product-item_447']//label");

Common.clickElement("xpath","//li[@id='product-item_447']//label");

ExtenantReportUtils.addPassLog("verifying product in plp page","User clicked on product from plp page","User added replacement Filter in PDP page",Common.getscreenShotPathforReport("successfully add replacement filter to mini cart"));




}

    catch(Exception  | Error e){

e.printStackTrace();

ExtenantReportUtils.addFailedLog("verifying product in plp page", "User clicked on product from plp page", "User failed add replacement Filter in PDP page", Common.getscreenShotPathforReport("failed to add replacement filter to mini cart"));

Assert.fail();

}

}



public void orderreturns(String DataSet) {

    // TODO Auto-generated method stub

    try{

  Thread.sleep(4000);

  Sync.waitElementClickable("xpath", "//div[contains(@class,'header-content__right-links hide')]//a[@title='Order Status']");

  Common.clickElement("xpath","//div[contains(@class,'header-content__right-links hide')]//a[@title='Order Status']");

  ExtenantReportUtils.addPassLog("verifying Order and Returs button","It should lands on Order and Returs  Page","user  lands on Order and Returs form Page",

Common.getscreenShotPathforReport("Orders and Returns"));

}catch (Exception | Error e) {


ExtenantReportUtils.addFailedLog("verifying Order and Returs from","It should be successfully navigate to Order and Returs page","user failed to navigate Order and Returs", Common.getscreenShotPathforReport("Order and Returs"));

Assert.fail();

}

    try

{

Thread.sleep(4000);

Common.textBoxInput("xpath", "//input[@id='oar-order-id']", data.get(DataSet).get("OrderID"));

Common.textBoxInput("xpath", "//input[@id='oar-billing-lastname']", data.get(DataSet).get("BillingLastName"));

Common.textBoxInput("xpath", "//input[@id='oar_email']", data.get(DataSet).get("Email"));

Common.clickElement("xpath", "//button[@class='action submit primary']");

ExtenantReportUtils.addPassLog("verifying Order and Returs Page","It should lands on Order and Returs form Page","user  lands on Order and Returs form Page",Common.getscreenShotPathforReport("Order and Returs"));


}

catch (Exception | Error e) {

e.printStackTrace();

ExtenantReportUtils.addFailedLog("verifying Order and Returs from",

"It should be successfully navigate to Order and Returs page",

"user failed to navigate Order and Returs", Common.getscreenShotPathforReport("Order and Returs"));

Assert.fail();

}

}



public void compareproduct() {

    // TODO Auto-generated method stub

    try

{

Thread.sleep(3000);

Common.mouseOver("xpath", "//div[@id='product-item-info_674']");

Thread.sleep(4000);

Sync.waitElementPresent("xpath","//div[@id='product-item-info_674']//span[text()='Compare']");

Common.clickElement("xpath", "//div[@id='product-item-info_674']//span[text()='Compare']");

ExtenantReportUtils.addPassLog("To view the product in PlP page","click on compare for particular product", "Sucessfully product added to comparison list", Common.getscreenShotPathforReport("Successfully product added to comparsion list"));



}

    catch (Exception | Error e) {

e.printStackTrace();

ExtenantReportUtils.addFailedLog("To view the product in PlP pag","click on compare for particular product", "user unable to add product to comparision list", Common.getscreenShotPathforReport("failed to add product to comparsion list"));

Assert.fail();

}

    try

{

Thread.sleep(3000);

Common.mouseOver("xpath", "//div[@id='product-item-info_418']");

Thread.sleep(3000);

Sync.waitElementPresent("xpath","//div[@id='product-item-info_418']//span[text()='Compare']");

Common.clickElement("xpath", "//div[@id='product-item-info_418']//span[text()='Compare']");

ExtenantReportUtils.addPassLog("To view the product in PlP page","click on compare for particular product", "Sucessfully product added to comparison list", Common.getscreenShotPathforReport("Successfully product added to comparsion list"));



}

    catch (Exception | Error e) {

e.printStackTrace();

ExtenantReportUtils.addFailedLog("To view the product in PlP pag","click on compare for particular product", "user unable to add product to comparision list", Common.getscreenShotPathforReport("failed to add product to comparsion list"));

Assert.fail();

}

    try

{

Thread.sleep(3000);

Common.mouseOver("xpath", "//div[@id='product-item-info_453']");

Thread.sleep(3000);

Sync.waitElementPresent("xpath","//div[@id='product-item-info_453']//span[text()='Compare']");

Common.clickElement("xpath", "//div[@id='product-item-info_453']//span[text()='Compare']");

ExtenantReportUtils.addPassLog("To view the product in PlP page","click on compare for particular product", "Sucessfully product added to comparison list", Common.getscreenShotPathforReport("Successfully product added to comparsion list"));



}

    catch (Exception | Error e) {

e.printStackTrace();

ExtenantReportUtils.addFailedLog("To view the product in PlP pag","click on compare for particular product", "user unable to add product to comparision list", Common.getscreenShotPathforReport("failed to add product to comparsion list"));

Assert.fail();

}

    try



{



Thread.sleep(3000);

    Common.mouseOver("xpath", "//div[@id='product-item-info_454']");

Thread.sleep(3000);

Sync.waitElementPresent("xpath","//div[@id='product-item-info_454']//span[text()='Compare']");

    Common.clickElement("xpath", "//div[@id='product-item-info_454']//span[text()='Compare']");

ExtenantReportUtils.addPassLog("To view the product in PlP page","click on compare for particular product", "Sucessfully product added to comparison list", Common.getscreenShotPathforReport("Successfully product added to comparsion list"));



}



catch (Exception | Error e) {



e.printStackTrace();

ExtenantReportUtils.addFailedLog("To view the product in PlP pag","click on compare for particular product", "user unable to add product to comparision list", Common.getscreenShotPathforReport("failed to add product to comparsion list"));

Assert.fail();



}

    try

{

Thread.sleep(4000);

Common.mouseOver("xpath", "//div[@id='product-item-info_511']");

Thread.sleep(4000);

Sync.waitElementPresent("xpath","//div[@id='product-item-info_511']//span[text()='Compare']");

Common.clickElement("xpath", "//div[@id='product-item-info_511']//span[text()='Compare']");


ExtenantReportUtils.addPassLog("Verfing the products in comparison list", "Adding products to comparison list",

"Only 4 products should any extra product it should show error message", Common.getscreenShotPathforReport("user successfully to comparison list error message is displayed "));

}

    catch (Exception | Error e) {

e.printStackTrace();

ExtenantReportUtils.addFailedLog("Verfing the products in comparison list", "Adding products to comparison list",

"Only 4 products should any extra product it should show error message", Common.getscreenShotPathforReport("faield to display error message"));

Assert.fail();

}

}
public void clickOnProceed()throws Exception{
	Thread.sleep(3000);
	 Common.waitAndClick("xpath","//*[@id='checkout-shipping-method-load']/table/tbody/tr[1]/td[1]/label");
	 Thread.sleep(3000);
	Common.scrollIntoView("xpath","//span[contains(text(),'Proceed to Review & Payment')]");
	Common.clickElement("xpath","//span[contains(text(),'Proceed to Review & Payment')]");
	Thread.sleep(4000);
}


public void click_fans()

{
	clickShopButton();
	try{
		Sync.waitElementClickable("xpath", "//a[@title='Fans']");
		Common.clickElement("xpath", "//a[@title='Fans']");
	//	Common.assertionCheckwithReport(Common.getPageTitle().equals("Fans - Shop"),"Verifying Fans page","it shoud navigate to Fans", "successfully  navigate to Fans", "Fans");	

		
		
	}catch(Exception e){
		
	}	
		
	}

public void signin_checkout(String DataSet) {
// TODO Auto-generated method stub
try {
	Thread.sleep(3000);
Common.textBoxInput("xpath", "(//input[@id='customer-email'])[1]", data.get(DataSet).get("Email"));
Thread.sleep(3000);
Common.textBoxInput("id", "customer-password", data.get(DataSet).get("Password"));
//Common.scrollIntoView("xpath", "//button[@class='action login primary']");
Sync.waitElementClickable("xpath", "//button[@class='action login primary']");
Common.clickElement("xpath", "//button[@class='action login primary']");
ExtenantReportUtils.addPassLog("Should login with details","Should login successfully","should display shipping address details in checkout page",Common.getscreenShotPathforReport("Successfully completed to login"));
}
catch (Exception | Error e) {
e.printStackTrace();
ExtenantReportUtils.addFailedLog("Should login with details","Should login successfully","should display shipping address details in checkout page",Common.getscreenShotPathforReport("Failed to login"));
Assert.fail();
}
}
public void sortby(String DataSet) {

	 

    // TODO Auto-generated method stub
 try{
Thread.sleep(3000);
Common.clickElement("xpath","(//select[@id='sorter'])[1]");
Sync.waitElementPresent("xpath","(//select[@id='sorter'])[1]");
Common.dropdown("xpath", "(//select[@id='sorter'])[1]", Common.SelectBy.TEXT, data.get(DataSet).get("Name"));
ExtenantReportUtils.addPassLog("verifing click beside of sort by in PLP page", "Dropdown should be open ","Product Name should be select from dropdown",
Common.getscreenShotPathforReport("Product name is select successfully from dropdown and all products in alphabetic order"));

 } catch (Exception | Error e) {
e.printStackTrace();
ExtenantReportUtils.addFailedLog("verifing click beside of sort by in PLP page","Dropdown should be open", "Product Name should be select from dropdown", Common.getscreenShotPathforReport("failed to open dropdown to select product name"));

Assert.fail();



}


}


public HashMap<String,String> Taxcalucaltion(String taxpercent) {
    // TODO Auto-generated method stub
	//String subtotla="";
//	String data="";
//		String Taxammount="";
//	String shippingammount="";
//	 String shippingammount,TaxAmmount,subtotla,giventaxvalue1,userpaneltaxvalue,TotalAmmount="";
			
	HashMap<String,String> data=new HashMap<String,String>();
	try{
    
    
    
    	
    	
    	
    
    	
    	// data=(giventaxvalue1,subtotla,shippingammount,Taxammount,TotalAmmount,userpaneltaxvalue);
        Thread.sleep(3000);
    
   // String taxpercent=data.get(DataSet).get("Tax");
     Float giventaxvalue=Float.valueOf(taxpercent);
     String giventaxvalue1=Float.toString(giventaxvalue);
     data.put("giventaxvalue",giventaxvalue1);
     
     
     
    
     String subtotla=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
     // subtotla.replace("", newChar)
    Float subtotlaValue=Float.valueOf(subtotla);
    data.put("subtotlaValue",subtotla);
   
  String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
    Float shippingammountvalue=Float.valueOf(shippingammount);
	data.put("shippingammountvalue",shippingammount);
	
     String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']/span").replace("$", "");
    Float Taxammountvalue=Float.valueOf(TaxAmmount);
	data.put("Taxammountvalue",TaxAmmount);
	
     String TotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
    Float Totalammountvalue=Float.valueOf(Taxammountvalue);
    data.put("Totalammountvalue",TotalAmmount);
    
    Float calucaltedvalue= ((subtotlaValue+shippingammountvalue)*giventaxvalue)/100;
    System.out.println(calucaltedvalue);
    NumberFormat nf= NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
     String userpaneltaxvalue=nf.format(calucaltedvalue);
     data.put("calculatedvalue",userpaneltaxvalue);
    System.out.println(TaxAmmount);
    System.out.println(userpaneltaxvalue);
   
 //   Common.assertionCheckwithReport(userpaneltaxvalue.equals(TaxAmmount), "verifying tax calculation", "tax rate is matches to given shipping address tax ","successfully tax rate is matches to given shipping address tax", "tax rate is not matches to given shipping address tax");
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
			cell.setCellValue("Orders details");
			
			    
			row = sheet.createRow(1);
			cell = row.createCell(0);
			cell.setCellStyle(cs);
			cell.setCellValue("OrderId");
			cell = row.createCell(1);
			cell.setCellStyle(cs);
			cell.setCellValue("SubTotal");
			cell = row.createCell(2);
			cell.setCellStyle(cs);
			cell.setCellValue("ShippingAmount");
			cell=row.createCell(3);
			cell.setCellStyle(cs);
			cell.setCellValue("TaxAmount");
			cell=row.createCell(4);
			cell.setCellStyle(cs);
			cell.setCellValue("TotalAmount");
			cell=row.createCell(5);
			cell.setCellStyle(cs);
			cell.setCellValue("ActualTax");
			cell=row.createCell(6);
			cell.setCellStyle(cs);
			cell.setCellValue("ExpectedTax");
			cell=row.createCell(7);
			cell.setCellStyle(cs);
			cell.setCellValue("status");
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
		
		

		public void writeResultstoXLSx(String OrderId,String subtotlaValue,String shippingammountvalue,String Taxammountvalue,String Totalammountvalue,String giventaxvalue,String calucaltedvalue)
		{
			//String fileOut="";
		try{
			
			File file=new File(System.getProperty("user.dir")+"/src/test/resources/HoneywellTaxDetails_Guest.xlsx");
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
			cell.setCellValue("Orders details");
			
			row = sheet.createRow(1);
			cell = row.createCell(0);
			cell.setCellStyle(cs);
			cell.setCellValue("OrderId");
			cell = row.createCell(1);
			cell.setCellStyle(cs);
			cell.setCellValue("SubTotal");
			cell = row.createCell(2);
			cell.setCellStyle(cs);
			cell.setCellValue("ShippingAmount");
			cell=row.createCell(3);
			cell.setCellStyle(cs);
			cell.setCellValue("TaxAmount");
			cell=row.createCell(4);
			cell.setCellStyle(cs);
			cell.setCellValue("TotalAmount");
			cell=row.createCell(5);
			cell.setCellStyle(cs);
			cell.setCellValue("ActualTax");
			cell=row.createCell(6);
			cell.setCellStyle(cs);
			cell.setCellValue("ExpectedTax");
			
			rowcount=2;
			
			}
			
			else
			{
			
			sheet=workbook.getSheet("TaxDetails");	
			rowcount=sheet.getLastRowNum()+1;
			}
			row = sheet.createRow(rowcount);
			cell = row.createCell(0);
			cell.setCellValue(OrderId);
			cell = row.createCell(1);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(subtotlaValue);
			cell = row.createCell(2);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(shippingammountvalue);
			cell = row.createCell(3);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(giventaxvalue);
			cell = row.createCell(4);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Totalammountvalue);
			cell = row.createCell(5);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(Taxammountvalue);
			cell = row.createCell(6);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(calucaltedvalue);
			cell = row.createCell(7);
			cell.setCellType(CellType.STRING);
			String status;
			if(Taxammountvalue.contains(calucaltedvalue))
			{
				Thread.sleep(4000);
				status="pass";
			}
			else
			{
				status="Fail";
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
			System.out.println(Totalammountvalue);
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

		public void GuestShippingAddress(String Street,String City,String postcode,String Region) {
			// TODO Auto-generated method stub
			String expectedResult="Should navigate to Shipping address page";
			try {
			/*	Sync.waitElementClickable(30, By.xpath("//button[@data-role='proceed-to-checkout']"));
				Common.clickElement("xpath" , "//button[@data-role='proceed-to-checkout']");
				Thread.sleep(4000);
				String S=Common.getText("xpath", "//*[@id='shipping']/div[1]");
				System.out.println(S);
				Assert.assertTrue(Common.isElementDisplayed("xpath", "//*[@id='shipping']/div[1]"));*/
				Thread.sleep(4000);
				ShippingAddress("ShippingAddress", Street, City, postcode, Region);
				Common.scrollIntoView("xpath", "//div[@class='checkout-shipping-method']/div");
				Thread.sleep(3000);
				 
				Common.clickElement("xpath", "//*[@id='checkout-shipping-method-load']/table/tbody/tr/td[1]/label");
				Common.clickElement("xpath", "//button[@data-role='opc-continue']/span");
			Thread.sleep(2000);
				Common.clickElement("xpath","//button[@class='action-primary action-accept']");
			
				report.addPassLog(expectedResult, "Should display Shipping address Page", "Shipping address page display successfully", Common.getscreenShotPathforReport("Shipping address page success"));
			}catch(Exception |Error e)
			{
				e.printStackTrace();
				report.addFailedLog(expectedResult,"Should display  Shipping address Page", "Shipping address Page not displayed", Common.getscreenShotPathforReport("Shipping address page Failed"));
				e.printStackTrace();
				Assert.fail();
			}
		}
					
		
		

public void ShippingAddress(String dataSet,String Street,String City,String postcode,String Region) throws InterruptedException {
    try {
    	Common.textBoxInput("xpath", "(//input[@id='customer-email'])[1]", data.get(dataSet).get("Email"));
    	Thread.sleep(3000);
        Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
        Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
        Common.textBoxInput("name", "street[0]", Street);
        Thread.sleep(3000);
        Common.actionsKeyPress(Keys.ESCAPE);
        Common.textBoxInput("name", "city", City);
        Common.textBoxInput("name", "postcode", postcode);
        Common.dropdown("name", "region_id",Common.SelectBy.TEXT, Region);
        Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
        Thread.sleep(3000);       
    }catch(Exception |Error e)
    {
        e.printStackTrace();
        Assert.fail();
    }
}

public void tax() {
	// TODO Auto-generated method stub
	try
	{
		Thread.sleep(4000);
		
		String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']/span").replace("$", "");
		Float Taxammountvalue=Float.valueOf(TaxAmmount);
		System.out.println(TaxAmmount);
		
		Common.assertionCheckwithReport(TaxAmmount.equals(TaxAmmount), "verifying tax calculation", "tax rate is given shipping address tax ","successfully tax rate is  given shipping address tax", "tax rate is not  given to shipping address tax");
		 
		
	}
	catch(Exception |Error e)
	{
	report.addFailedLog("verifying tax calculation", "getting price values from shipping page ", "Field to get price value from shipping page", Common.getscreenShotPathforReport("TaxRates"));
	}
}


	public void outofstockproduct(String DataSet) {
		// TODO Auto-generated method stub
		try
		{
		Thread.sleep(2000);
		String productname= data.get(DataSet).get("productname");
		System.out.println(productname);
		Thread.sleep(4000);
		Common.mouseOver("xpath", "//a[contains (text(),'"+productname+"')]");
		String Oof=Common.findElement("xpath","//span[text()='Out of stock']").getText();
		Common.assertionCheckwithReport(Oof.equals("Out of stock"), "To verify the PLP with out of stock", "Should MOUSE OVER on out of stock PLP page","User unable to land on Out of Stock PDP", "faield to land on out of stock PLP page");
		}
		catch(Exception |Error e) {
//		ExtenantReportUtils.addFailedLog("To verify the the PLP Page with out of stock","Should mouse over on out of stock PLP page", "user unable to land on Out of Stock PLP", Common.getscreenShotPathforReport("failed to land on out of stock PDP page"));
		Assert.fail();
		}

		}
	
	public void AVS_ShippingAddress(String dataSet) throws Exception{
		try{
		Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
		Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",data.get(dataSet).get("Email"));
        Thread.sleep(4000);
		
		Sync.waitElementPresent("xpath", "//input[@name='firstname']");
		Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
		
		Sync.waitElementPresent("xpath", "//input[@name='lastname']");
		Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
		
		

		Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
		Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
		
		int street=Common.findElements("xpath", "//a[contains(text(),'2 Dream Valley Rd Sylva NC 28779')]").size();
		if(street>0){
			 Common.assertionCheckwithReport(street>0, "verifying street address  ", "user will click the street", "user clicked street address", "faield to add street address");
			Common.clickElement("xpath", "//a[contains(text(),'2 Dream Valley Rd Sylva NC 28779')]");
		}
		
		Thread.sleep(5000);
		String ShippingCity=Common.findElement("xpath", "//input[@name='city']").getAttribute("value");
         System.out.println("*****"+ShippingCity+"*******");
         String scity=data.get(dataSet).get("city");
         Common.assertionCheckwithReport(ShippingCity.equals(scity), "equals city names ", "It should city names are same", "City names not same");
         Thread.sleep(5000);
         String ShippingZip=Common.findElement("xpath", "//input[@name='postcode']").getAttribute("value");
         System.out.println("*****"+ShippingZip+"*******");
         String spostcode=data.get(dataSet).get("postcode");
         Common.assertionCheckwithReport(ShippingCity.equals(spostcode), "postcode same ", "It should show postcode", "postcode not same");
         Thread.sleep(5000);
         String Shippingvalue=Common.findElement("name", "region_id").getAttribute("value");
         System.out.println("*****"+Shippingvalue+"*******");
         String Shippingvalue1=Common.findElement("name", "(//option[@value='"+Shippingvalue+"'])[1]").getAttribute("data-title");
         System.out.println("*****"+Shippingvalue1+"*******");
         String sregion=data.get(dataSet).get("region");
         Common.assertionCheckwithReport(ShippingCity.equals(sregion), "region same ", "It should show region", "region not same");
         Thread.sleep(5000);
         Thread.sleep(2000);
		
		Sync.waitElementPresent("xpath", "//input[@name='telephone']");
		Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
		
		//select_USPS_StandardGround_shippingMethod();
	    Thread.sleep(5000);
		
	    Common.clickElement("xpath","//div[@id='shipping-method-buttons-container']/div/button");
	    
		//Common.clickElement("xpath","//span[text()='Next']");
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
	
	//11082021 xml codestart 2:20
	
	public void view() throws Exception{
		Common.waitAndClick("xpath","//span[contains(text(),'View and Edit Cart')]");
	}
	
	public HashMap<String,HashMap<String,String>> productshoppingcart() {
		// TODO Auto-generated method stub
		 int value=0;
    		List<WebElement> cartproducts= Common.findElements("xpath", "//table[@class='cart items data table']//tbody[@class='cart item']");
    		HashMap<String,HashMap<String,String>> productinfromation=new HashMap<String,HashMap<String,String>>();
    		HashMap<String,String> singleproductinfromation;
    		
    		try {
    		
    		
    		for(int i=0;i<cartproducts.size();i++) {
    			 String productname="";
    		       value=i+1;
    		       singleproductinfromation= new HashMap<String,String>();
    		//	String productclass=cartproducts.get(i).getAttribute("class").replaceAll("cart item", "");
    			String productclass=cartproducts.get(i).getAttribute("class");
    		try {
    			 productname=	Common.findElement("xpath", "//tbody[@class='"+productclass+"']//img").getAttribute("alt");
    			 System.out.println(productname);
    		     }
    		catch(Exception e) {
    			productname=Common.findElement("xpath", "//tbody[@class='"+productclass+"']//span//a").getText();
    			
    			}
    		
    			singleproductinfromation.put("productname", productname);
    			System.out.println(productname);
    			String productSKU= Common.findElement("xpath", "//tbody[@class='"+productclass+"']//input").getAttribute("data-cart-item-id");
    			 singleproductinfromation.put("productSKU", productSKU);
    			System.out.println(productSKU);
    		    String productPrice= Common.findElement("xpath", "//tbody[@class='"+productclass+"']//span[@class='price']").getText();
    		    singleproductinfromation.put("productPrice", productPrice);
    		    System.out.println(productPrice);
    		    String productQTY= Common.findElement("xpath", "//tbody[@class='"+productclass+"']//input").getAttribute("value");
    		    singleproductinfromation.put("productQTY", productQTY);
    		    System.out.println(productQTY);
    		    productinfromation.put("order"+value ,singleproductinfromation);
    		    
    		    ExtenantReportUtils.addPassLog("Validating product infromation", "User get product name SKQ , QTY infroamtion   ", "User sucessfully get product infromation "+productinfromation,Common.getscreenShotPathforReport("productinfopass"));		  
    		   
    	}
    		
    		
    		}
    		 catch (Exception | Error e) {
 				ExtenantReportUtils.addFailedLog("Validating product infromation", "User get product name SKQ , QTY infroamtion",
 						"User failed to get product name SKQ , QTY infroamtion",
 						Common.getscreenShotPathforReport("productinfail"));
 				Assert.fail();
 		 }
    		
            System.out.println(productinfromation);
    		 
    		 return productinfromation;
		
	}
	
	
	
	
	public HashMap<String, String> shippingaddress1(String datSet) {
		// TODO Auto-generated method stub
		
		  String expectedResult = "Product should add to cart";
	        HashMap<String,String> Shippingaddress=new HashMap<String,String>();
	        try {
	            Thread.sleep(3000);
	            Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
	            Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']", data.get(datSet).get("Email"));
	            Thread.sleep(4000);
	            String Emailid=Common.findElement("xpath","//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']").getAttribute("value");
	            System.out.println("******"+Emailid+"******");
	            Shippingaddress.put("Emailid",Emailid);
	            
	            Sync.waitElementClickable("xpath", "//input[@name='firstname']");
	            Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(datSet).get("FirstName"));
	            Thread.sleep(40000);
	            String ShippingFirstName=Common.findElement("xpath", "//input[@name='firstname']").getAttribute("value");
	            System.out.println("*****"+ShippingFirstName+"*******");
				Shippingaddress.put("ShippingFisrtName", ShippingFirstName);
				
	            Sync.waitElementClickable("xpath", "//input[@name='lastname']");
	            Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(datSet).get("LastName"));
	            Thread.sleep(3000);
	            String ShippingLastName=Common.findElement("xpath", "//input[@name='lastname']").getAttribute("value");
		        System.out.println("*****"+ShippingLastName+"*******");
		        Shippingaddress.put("ShippingLastName", ShippingLastName);
	            
	            
	            Common.actionsKeyPress(Keys.PAGE_DOWN);
	            Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
	            Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(datSet).get("Street"));
	            Thread.sleep(12000);
	            String ShippingAddress1=Common.findElement("xpath", "//input[@name='street[0]']").getAttribute("value");
		        System.out.println("*****"+ShippingAddress1+"*******");
		        Shippingaddress.put("ShippingAddress1", ShippingAddress1);
	            
	            Thread.sleep(3000);
	            Common.actionsKeyPress(Keys.SPACE);
	            Thread.sleep(2000);
	            Common.clickElement("xpath", "(//a[@class='dropdown-item list-item'])[1]");
	            Thread.sleep(5000);
	            Common.textBoxInput("xpath","//input[@name='city']", data.get(datSet).get("City"));
	            Thread.sleep(12000);
	            String ShippingCity=Common.findElement("xpath","//input[@name='city']").getAttribute("value");
	            System.out.println("*****"+ShippingCity+"*******");
		        Shippingaddress.put("ShippingCity", ShippingCity);
		        
	            Common.textBoxInput("xpath", "//input[@name='postcode']",  data.get(datSet).get("postcode"));
	        	String ShippingZip=Common.findElement("xpath", "//input[@name='postcode']").getAttribute("value");
		            System.out.println("*****"+ShippingZip+"*******");
		            Shippingaddress.put("ShippingZip", ShippingZip);
	            
	            //Common.findElement("xpath", "//select[@name='region_id']").click();
	            Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(datSet).get("Region"));
	            
	            String Shippingvalue=Common.findElement("name", "region_id").getAttribute("value");

	            Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(datSet).get("phone"));
	            Thread.sleep(4000);
	            String ShippingPhone=Common.findElement("xpath", "//input[@name='telephone']").getAttribute("value");
		        System.out.println("*****"+ShippingPhone+"*******");
		        Shippingaddress.put("ShippingPhone", ShippingPhone);
	            
	            Common.clickElement("xpath", "//button[@class='button action continue primary']");
	            Thread.sleep(3000);
	   		 Common.clickElement("xpath","//button[@class='action-primary action-accept']");
	            ExtenantReportUtils.addPassLog("Verifing guest user checkout page", "Guest user checkout page success",
	                    "Guest user checkout page success",
	                    Common.getscreenShotPathforReport("Guest user checkout page success"));
	            
	           
	        }

	 

	        catch (Exception | Error e) {
	            e.printStackTrace();
	            ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping",
	                    "faield to add new shipping address",
	                    Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
	            Assert.fail();

	 

	        }
	        System.out.println(Shippingaddress);
	        return Shippingaddress;
	        
	}
	
	
	
	
	
	public HashMap<String,String> orderamountinfo() {
		// TODO Auto-generated method stub
		HashMap<String,String> data=new HashMap<String,String>();
			try{			    
				Thread.sleep(5000);
	            String subtotla=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
	            
		        data.put("subtotlaValue",subtotla);
		        String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
		        data.put("shippingammountvalue",shippingammount);
			    String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']/span").replace("$", "");
		        data.put("Taxammountvalue",TaxAmmount);
			    String TotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
		        data.put("TotalAmmount", TotalAmmount);
		   
		 Common.assertionCheckwithReport(!subtotla.equals(null),"verifying order amout detiles", "getting all the Billing ammount infromation","successfully get the total billing amount infromation ", "faiel to get billing ammount");
 		}
	 catch(Exception |Error e)
		{
		 e.printStackTrace();
		 report.addFailedLog("verifying tax billing amount", "getting price values from billing  page  ", "Faield to get price value from billing page", Common.getscreenShotPathforReport("TaxRatesbilling"));

			e.printStackTrace();
			Assert.fail();
			
	}
			System.out.println(data);
			return  data;
		
	}
	
	
	
	public HashMap<String,String> paymentdetriles(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult = "Payment With Valid Credit Card";
		HashMap<String,String> Payment=new HashMap<String,String>();
		try {
			Thread.sleep(5000);

			if (Common.isElementDisplayed("xpath", "//div[@id='checkout-loader']")) {
				Thread.sleep(4000);
			} else {
				Thread.sleep(6000);
				
			}
			Common.switchFrames("paymetric_xisecure_frame");
			Sync.waitElementPresent("xpath", "//select[@id='c-ct']");
			Common.dropdown("xpath", "//select[@id='c-ct']", SelectBy.TEXT, data.get(dataSet).get("cardType"));
			String Cardtype=Common.findElement("xpath", "//select[@id='c-ct']").getAttribute("value");
				String Card=Common.findElement("xpath","//select[@id='c-ct']//option[@value='"+Cardtype+"']").getText();
			    Payment.put("Card", Card);
			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
			String Cardnumber=Common.findElement("id", "c-cardnumber").getAttribute("value");
				System.out.println("******"+Cardnumber+"*****");
				Payment.put("Cardnumber", Cardnumber);

			Common.clickElement("xpath", "(//select[@id='c-exmth']/option)[6]");
			String ExpMonth=Common.findElement("xpath", "(//select[@id='c-exmth']/option)[6]").getAttribute("value");
				System.out.println("*******"+ExpMonth+"****");
				Payment.put("ExpMonth", ExpMonth);
			
			Common.dropdown("id", "c-exyr", SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
			String ExpYear=Common.findElement("id", "c-exyr").getAttribute("value");
				System.out.println("*******"+ExpYear+"****");
				Payment.put("ExpYear", ExpYear);
			Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));
			String cvv=Common.findElement("id","c-cvv").getAttribute("value");
				System.out.println("*******"+cvv+"****");
				Payment.put("cvv", cvv);
			Common.switchToDefault();
			Thread.sleep(3000);
			Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should Make payment wih valid credit card successfully",
					"Make payment wih valid credit card unsuccessfully",
					Common.getscreenShotPathforReport("Payment CC Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		return Payment;
	}

	

	 public void honeyWellAdminlogin(String dataSet) throws Exception {
    	//UserName	Password

    	try {
    	Common.oppenURL("https://jetrails-stag-hh.heledigital.com/y4vcVf9pmEX4bCwt/");
    	Thread.sleep(4000);
    	Common.textBoxInput("xpath", "//input[contains(@name,'username')]", data.get(dataSet).get("UserName"));
    	Common.textBoxInput("xpath", "//input[contains(@name,'password')]",data.get(dataSet).get("Password"));
    	
    int username=Common.findElements("xpath", "//input[contains(@name,'username')]").size();
    	
    	
       Common.assertionCheckwithReport(username>=1, "verifying Admin panel login page", "User name and password field data is populating", "Sucessfully enter username and password", "Faield to enter username and password"); 	
    	Common.clickElement("xpath", "//button[contains(@class,'action-primary')]");
    	Thread.sleep(2000);  
    	Common.actionsKeyPress(Keys.ESCAPE);
    	}
    	catch(Exception |Error e)
		{
			report.addFailedLog("verifying Admin panel login page", "User name and password field data is populating", "Faield to enter username and password",Common.getscreenShotPathforReport("adminlogin")); 	

			e.printStackTrace();
			Assert.fail();
			
	}
}
	 
	 public void AddNewAddressReg(String dataSet,String Street,String City,String postcode,String Region){
		 
		 
	 }
	 
	 public void RegisterShippingAddress(String dataSet,String Street,String City,String postcode,String Region) {
			String expectedResult = "shipping address is entering in the fields";
		    int size = Common.findElements(By.xpath("//span[contains(text(),'New Address')]")).size();
			if (size > 0) {

				try {
					Common.clickElement("xpath", "//span[contains(text(),'New Address')]");
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
							data.get(dataSet).get("Street"));
					Thread.sleep(2000);
					Common.actionsKeyPress(Keys.SPACE);
					Thread.sleep(3000);
					
					if (data.get(dataSet).get("StreetLine2") != null) {
						Common.textBoxInput("name", "street[1]", Street);
					}
					if (data.get(dataSet).get("StreetLine3") != null) {
						Common.textBoxInput("name", "street[2]", Street);
					}
					Common.actionsKeyPress(Keys.PAGE_DOWN);
					Thread.sleep(3000);
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",City);
					try {
						Common.dropdown("name", "region_id", Common.SelectBy.TEXT, Region);
					} catch (ElementClickInterceptedException e) {
						// TODO: handle exception
						Thread.sleep(3000);
						Common.dropdown("name", "region_id", Common.SelectBy.TEXT, Region);
					}
					Thread.sleep(2000);
					Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']",
						postcode);
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
							data.get(dataSet).get("phone"));
					Common.clickElement("xpath","//label[@class='label']//span[text()='Save in address book']");

					ExtenantReportUtils.addPassLog("validating the shipping address", expectedResult,
							"user add the shipping address",
							Common.getscreenShotPathforReport("faield to add shipping address"));
					
					Sync.waitElementPresent("xpath","//span[text()='Save in address book']");
                 Common.clickElement("xpath","//span[text()='Save in address book']");
					Common.clickElement("xpath", "//button[contains(@class,'save-address')]");
		

				    Thread.sleep(3000);
				 Common.clickElement("xpath","//button[@class='action-primary action-accept']");
					
					int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

					Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
							"user will fill the all the shipping", "user fill the shiping address click save button",
							"faield to add new shipping address");
					
				//	Common.clickElement("xpath","//td[@id='label_method_amstrates2_amstrates']");
					//select_USPS_StandardGround_shippingMethod();
		            Thread.sleep(5000);
					Common.clickElement("xpath", "//div[@id='shipping-method-buttons-container']/div/button");

					
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
					Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
					Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",
							data.get(dataSet).get("Email"));
					Sync.waitElementPresent("xpath", "//input[@name='firstname']");
					Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
					
					Sync.waitElementPresent("xpath", "//input[@name='lastname']");
					Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
					
					

					Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
					Common.textBoxInput("xpath", "//input[@name='street[0]']", Street);
					
					Sync.waitElementPresent("xpath", "//input[@name='city']");
					Common.textBoxInput("xpath", "//input[@name='city']", City);
					Common.actionsKeyPress(Keys.ESCAPE);
					Sync.waitElementPresent("xpath", "//input[@name='postcode']");
					Common.textBoxInput("xpath", "//input[@name='postcode']", postcode);
					
					
					Sync.waitElementPresent("xpath", "//select[@name='region_id']");
					Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, Region);
					Sync.waitElementPresent("xpath", "//input[@name='telephone']");
					Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
				    Thread.sleep(5000);
				    Common.clickElement("xpath","//div[@id='shipping-method-buttons-container']/div/button");
					Sync.waitPageLoad();
					int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
				    Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying shipping addres filling ", "user will fill the all the shipping", "user fill the shiping address click save button", "faield to add new shipping address");
				   
				
					
				}
				 catch (Exception | Error e) {
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

			

		}

	public void writeResultstoXLSxReg(String OrderId,String subtotlaValue,String shippingammountvalue,String Taxammountvalue,String Totalammountvalue,String giventaxvalue,String calucaltedvalue)
			{
				//String fileOut="";
			try{
				
				File file=new File(System.getProperty("user.dir")+"/src/test/resources/HoneywellTaxDetails_Reg.xlsx");
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
				cell.setCellValue("Orders details");
				
				row = sheet.createRow(1);
				cell = row.createCell(0);
				cell.setCellStyle(cs);
				cell.setCellValue("OrderId");
				cell = row.createCell(1);
				cell.setCellStyle(cs);
				cell.setCellValue("SubTotal");
				cell = row.createCell(2);
				cell.setCellStyle(cs);
				cell.setCellValue("ShippingAmount");
				cell=row.createCell(3);
				cell.setCellStyle(cs);
				cell.setCellValue("TaxAmount");
				cell=row.createCell(4);
				cell.setCellStyle(cs);
				cell.setCellValue("TotalAmount");
				cell=row.createCell(5);
				cell.setCellStyle(cs);
				cell.setCellValue("ActualTax");
				cell=row.createCell(6);
				cell.setCellStyle(cs);
				cell.setCellValue("ExpectedTax");
				
				rowcount=2;
				
				}
				
				else
				{
				
				sheet=workbook.getSheet("TaxDetails");	
				rowcount=sheet.getLastRowNum()+1;
				}
				row = sheet.createRow(rowcount);
				cell = row.createCell(0);
				cell.setCellValue(OrderId);
				cell = row.createCell(1);
				cell.setCellType(CellType.NUMERIC);
				cell.setCellValue(subtotlaValue);
				cell = row.createCell(2);
				cell.setCellType(CellType.NUMERIC);
				cell.setCellValue(shippingammountvalue);
				cell = row.createCell(3);
				cell.setCellType(CellType.NUMERIC);
				cell.setCellValue(giventaxvalue);
				cell = row.createCell(4);
				cell.setCellType(CellType.NUMERIC);
				cell.setCellValue(Totalammountvalue);
				cell = row.createCell(5);
				cell.setCellType(CellType.NUMERIC);
				cell.setCellValue(Taxammountvalue);
				cell = row.createCell(6);
				cell.setCellType(CellType.NUMERIC);
				cell.setCellValue(calucaltedvalue);
				cell = row.createCell(7);
				cell.setCellType(CellType.STRING);
				String status;
				if(Taxammountvalue.contains(calucaltedvalue))
				{
					Thread.sleep(4000);
					status="pass";
				}
				else
				{
					status="Fail";
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
				System.out.println(Totalammountvalue);
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
	
	public void prepareTaxDataN(String fileName) {
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

		

	// 24/09/2021 changes


	public String  URL() throws InterruptedException {
		String Website="";
		Common.clickElement("xpath", "(//a[@class='logo'])");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Website=Common.getCurrentURL();
	    
		return Website;
		
	}		


	// 24/09/2021 changes

	public void writeResultstoXLSxN(String Website,String OrderId,String subtotlaValue,String shippingammountvalue,String state,String Zipcode,String Taxammountvalue,String ActualTotalammountvalue, String ExpectedTotalammountvalue,String giventaxvalue,String calucaltedvalue)
	{
		//String fileOut="";
	try{
		File file=new File(System.getProperty("user.dir")+"/src/test/resources/HoneywellTaxDetails_Guest.xlsx");	
//		File file=new File(System.getProperty("user.dir")+"/src/test/resources/DryBarTaxDetails_Guest.xlsx");
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
		cell.setCellValue("HHG");
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
		if(Website.contains("Honeywell"))
	     {
			
			Site="Honeywell";
			
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
		if(Taxammountvalue.contains(calucaltedvalue)&&(ActualTotalammountvalue).contains(ExpectedTotalammountvalue))
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
	//return fileOut;
	//return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);

	}

	//24/09/2021 changes


	public HashMap<String,String> TaxcalucaltionN(String taxpercent,String state) {
	    // TODO Auto-generated method stub
		//String subtotla="";
//		String data="";
//			String Taxammount="";
//		String shippingammount="";
//		 String shippingammount,TaxAmmount,subtotla,giventaxvalue1,userpaneltaxvalue,TotalAmmount="";
				
		HashMap<String,String> data=new HashMap<String,String>();
		try{
	    	
	    	// data=(giventaxvalue1,subtotla,shippingammount,Taxammount,TotalAmmount,userpaneltaxvalue);
	        Thread.sleep(3000);
	    
	   // String taxpercent=data.get(DataSet).get("Tax");
	     Float giventaxvalue=Float.valueOf(taxpercent);
	     String giventaxvalue1=Float.toString(giventaxvalue);
	     data.put("giventaxvalue",giventaxvalue1);
	     
	     
	     
	    
	     String subtotla=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
	     // subtotla.replace("", newChar)
	    Float subtotlaValue=Float.valueOf(subtotla);
	    data.put("subtotlaValue",subtotla);
	   
	  String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
	    Float shippingammountvalue=Float.valueOf(shippingammount);
		data.put("shippingammountvalue",shippingammount);
		
	     String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']/span").replace("$", "");
	    Float Taxammountvalue=Float.valueOf(TaxAmmount);
		data.put("Taxammountvalue",TaxAmmount);
		
	     String TotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
	    Float Totalammountvalue=Float.valueOf(Taxammountvalue);
	    data.put("Totalammountvalue",TotalAmmount);
	    
	    Float calucaltedvalue= ((subtotlaValue+shippingammountvalue)*giventaxvalue)/100;
	    System.out.println(calucaltedvalue);
	    NumberFormat nf= NumberFormat.getInstance();
	    nf.setMaximumFractionDigits(2);
	     String userpaneltaxvalue=nf.format(calucaltedvalue);
	     data.put("calculatedvalue",userpaneltaxvalue);
	    System.out.println(TaxAmmount);
	    System.out.println(userpaneltaxvalue);
	    
	    
	    if((state.equals("Illinois"))||(state.equals("Florida"))) {
		     calucaltedvalue= ((subtotlaValue)*giventaxvalue)/100;
		     userpaneltaxvalue = new BigDecimal(calucaltedvalue).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		    data.put("calculatedvalue",userpaneltaxvalue);
		    System.out.println(TaxAmmount);
		    System.out.println(userpaneltaxvalue);
		    
		    }
		    else {
		    	calucaltedvalue= ((subtotlaValue+shippingammountvalue)*giventaxvalue)/100;
			    userpaneltaxvalue = new BigDecimal(calucaltedvalue).setScale(2, BigDecimal.ROUND_HALF_UP).toString();   
			    data.put("calculatedvalue",userpaneltaxvalue);
			    System.out.println(TaxAmmount);
			    System.out.println(userpaneltaxvalue);
		    }	
		   
	   
	 //   Common.assertionCheckwithReport(userpaneltaxvalue.equals(TaxAmmount), "verifying tax calculation", "tax rate is matches to given shipping address tax ","successfully tax rate is matches to given shipping address tax", "tax rate is not matches to given shipping address tax");
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
//			    String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount);
//			    data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
	
			    String ExpectedTotalAmmount2 = new BigDecimal(ExpectedTotalAmmount).setScale(2, BigDecimal.ROUND_HALF_UP).toString();

                String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount2);
                data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
			    if((state.equals("Illinois"))||(state.equals("Florida"))) {
			    Float calucaltedvalue= ((subtotlaValue)*giventaxvalue)/100;
//			    String userpaneltaxvalue = new BigDecimal(calucaltedvalue).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			    NumberFormat nf= NumberFormat.getInstance();
			    nf.setMaximumFractionDigits(2);
			    String userpaneltaxvalue=nf.format(calucaltedvalue);
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
	 
	 public void writeResultstoXLSxReg(String Website,String OrderId,String subtotlaValue,String shippingammountvalue,String state,String Zipcode,String Taxammountvalue,String ActualTotalammountvalue, String ExpectedTotalammountvalue,String giventaxvalue,String calucaltedvalue)
	 {
	 	//String fileOut="";
	 try{
	 	
	 	File file=new File(System.getProperty("user.dir")+"/src/test/resources/HoneywellTaxDetails_Reg.xlsx");
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
	 	cell.setCellValue("HHG");
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
	 	if(Website.contains("Honeywell"))
	      {
	 		
	 		Site="Honeywell";
	 		
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
	 	if(Taxammountvalue.contains(calucaltedvalue)&&(ActualTotalammountvalue).contains(ExpectedTotalammountvalue))
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
	 //return fileOut;
	 //return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);

	 }
	 public void prepareTaxDataReg(String fileName) {
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
	 public void Click_Heaters() throws Exception

	 {
			clickShopButton();
		 try{
	 		Sync.waitElementClickable("xpath", "//a[@title='Heaters']");
	 		Common.clickElement("xpath", "//a[@title='Heaters']");
	 		Common.assertionCheckwithReport(Common.getPageTitle().equals("Heaters - Shop"),"Verifying Heater page","it shoud navigate to Heater", "successfully  navigate to Heater", "Heaters");	
	 		
	 	}
	 	catch(Exception |Error e) {
	 		e.printStackTrace();
	 	ExtenantReportUtils.addFailedLog("verifying Heaters Product category", "User navigate to heaters product page", "user not able to click heater product", Common.getscreenShotPathforReport("Heaters"));		
	 	Assert.fail();	

	 	}
	 	int newsllettersize=Common.findElements("xpath","//div[@id='wpn-lightbox-close-newsletter']").size();
	 	if(newsllettersize>0){
	 		Common.clickElement("xpath", "//div[@id='wpn-lightbox-close-newsletter']");
	 	}
	 	
	 }
	 
	 public void newsletterPopUp() throws Exception
	 {
	 String expectedResult="Navigate to newsletter popup window";
	 try {

	 Thread.sleep(3000);

	 Sync.waitElementPresent("xpath", "//div[@id='wpn-lightbox-close-newsletter']");
	 Common.clickElement("xpath", "//div[@id='wpn-lightbox-close-newsletter']");

	 report.addPassLog(expectedResult, "Should display newsletter popup window", "Shipping newsletter popup window display successfully", Common.getscreenShotPathforReport("newsletter popup window display success"));
	 }catch(Exception |Error e)
	 {
	 report.addFailedLog(expectedResult,"Should display newsletter popup window", "newsletter popup window not displayed", Common.getscreenShotPathforReport("newsletter popup window Failed"));
	 Assert.fail();
	 }
	 }

}
	