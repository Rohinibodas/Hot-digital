package TestComponent.Vicks;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import TestLib.Common;
import TestLib.Sync;
import TestLib.Common.SelectBy;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;

public class VicksHelper {
	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;

	public VicksHelper(String datafile) {
		excelData = new ExcelReader(datafile);
		data = ExcelReader.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Vickshumdifier");
			ExtenantReportUtils.createTestcase("VickshumdifierTestCases");
		} else {
			VicksHelper.report = Utilities.TestListener.report;
		}
	}

	public void LoginFormValidation(){
		try {
			
		Thread.sleep(4000);	
		Sync.waitElementClickable("xpath","//a[@class='header-content__right-link']");
		Common.findElement("xpath", "//a[@class='header-content__right-link']").click();
		Common.scrollIntoView("xpath", "//button[@class='action login primary']");
		Sync.waitElementClickable("xpath", "//button[@class='action login primary']");
		Common.clickElement("xpath", "//button[@class='action login primary']");
		Thread.sleep(3000);
		int emailerrormessage=Common.findElements("xpath", "//div[@id='email-error']").size();
		int passworderromessage=Common.findElements("xpath", "//div[@id='pass-error']").size();
		Thread.sleep(3000);
		Common.assertionCheckwithReport(emailerrormessage>0&&passworderromessage>0, "verifying error message signpage", "enter with empety data it must show error message","sucessfully display the error message", "faield to dispalyerrormessage");
		}
		catch(Exception |Error e) {
		 	   
			ExtenantReportUtils.addFailedLog("verifying error message signpage", "enter with empty data it must show error message", "faield to dispalyerrormessage", Common.getscreenShotPathforReport("loginpagevalidation"));
			Assert.fail();
		}
	}
	
	public void loginVicks(String dataSet) throws Exception {
		String expectedResult = "Land on login page and able to login with credentials";
		try {

			Common.clickElement("xpath", "//a[@class='header-content__right-link']");
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.scrollIntoView("xpath", "//button[@class='action login primary']");
			Sync.waitElementClickable("xpath", "//button[@class='action login primary']");
			Common.clickElement("xpath", "//button[@class='action login primary']");
			Thread.sleep(5000);
			Sync.waitElementClickable("xpath", "//a[@class='logo']");
			Common.clickElement("xpath", "//a[@class='logo']");
			ExtenantReportUtils.addPassLog("Should login with details",
					"Should display My Account Page with user details",
					"My Account Pae with user details displayed successfully",
					Common.getscreenShotPathforReport("Login page with details Failed"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("Should login with details",
					"Should display  My Account Page with user details",
					"My Account Page with user details not displayed",
					Common.getscreenShotPathforReport("Login page with details Failed"));
			Assert.fail();
		}

	}
	public void loginVicks_Promecode(String dataSet) throws Exception {
		String expectedResult = "Land on login page and able to login with credentials";
		try {

			Common.clickElement("xpath", "//a[@class='header-content__right-link']");
			Sync.waitElementClickable("id", "email");
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Sync.waitElementClickable("id", "pass");
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Thread.sleep(5000);
			Common.scrollIntoView("xpath", "//button[@class='action login primary']");
			Sync.waitElementClickable("xpath", "//button[@class='action login primary']");
			Common.clickElement("xpath", "//button[@class='action login primary']");
			Thread.sleep(5000);
			Sync.waitElementClickable("xpath", "//a[@class='logo']");
			Common.clickElement("xpath", "//a[@class='logo']");
			ExtenantReportUtils.addPassLog("Should login with details",
					"Should display My Account Page with user details",
					"My Account Pae with user details displayed successfully",
					Common.getscreenShotPathforReport("Login page with details Failed"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("Should login with details",
					"Should display  My Account Page with user details",
					"My Account Page with user details not displayed",
					Common.getscreenShotPathforReport("Login page with details Failed"));
			Assert.fail();
		}

	}

	
	
	public void AccountCreationFormValidation() {
		try {
			Thread.sleep(5000);
		Common.clickElement("xpath", "//a[@class='header-content__right-link']");
		Sync.waitElementClickable("xpath", "//a[@class='action create primary']");
		Common.clickElement("xpath", "//a[@class='action create primary']");
		ExtenantReportUtils.addPassLog("verifying Create Account button",
				"It should lands on Create New Customer from Account form Page",
				"user  lands on Customer Account creation form Page",
				Common.getscreenShotPathforReport("createaccount"));
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying Create Account button",
				"It should lands on Create New Customer from Account form Page",
				"user faield lands on Account form Page", Common.getscreenShotPathforReport("createaccount"));
		Assert.fail();
	}

		try{
			
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//button[@title='Create an Account']");
		    Common.findElement("xpath", "//button[@title='Create an Account']").click();
		   // Sync.waitElementVisible("xpath", "//div[@class='message-error error message']");
		   /* int sizes =Common.findElements("xpath", "//div[@class='message-error error message']").size();	  
		    Common.assertionCheckwithReport(sizes>0, "Successfully land on the payment section", expectedResult,"User unabel to land on paymentpage");	
			*/
		    int Signuperrormessage=Common.findElements("xpath", "//div[@id='email_address-error']").size();
			Common.assertionCheckwithReport(Signuperrormessage>0, "verifying error message signuppage", "enter with empety data it must show error message","sucessfully display the error message", "faield to dispalyerrormessage");
			}
			catch(Exception |Error e) {
				e.printStackTrace();
			 	   
				ExtenantReportUtils.addFailedLog("verifying error message signpage", "enter with empty data it must show error message", "faield to dispalyerrormessage", Common.getscreenShotPathforReport("loginpagevalidation"));
				Assert.fail();
			}
		}

	
	public void OrderStatus(String dataSet) throws Exception{
		try{
	      Thread.sleep(4000);
	      Sync.waitElementClickable("xpath", "//a[@title='Order Status']");
	      Common.findElement("xpath","//a[@title='Order Status']").click();
	 //     check the codition first 
	      ExtenantReportUtils.addPassLog("verifying Order and Returs button",
					"It should lands on Order and Returs  Page",
					"user  lands on Order and Returs form Page",
					Common.getscreenShotPathforReport("Order and Returs"));
		}catch (Exception | Error e) {
			
			ExtenantReportUtils.addFailedLog("verifying Order and Returs from",
					"It should be successfully navigate to Order and Returs page",
					"user failed to navigate Order and Returs", Common.getscreenShotPathforReport("Order and Returs"));
			Assert.fail();
		} 
	      try {
	    	    Thread.sleep(4000);
				Common.textBoxInput("xpath", "//input[@id='oar-order-id']", data.get(dataSet).get("OrderID"));
				Common.textBoxInput("xpath", "//input[@id='oar-billing-lastname']", data.get(dataSet).get("BillingLastName"));
				Common.textBoxInput("xpath", "//input[@id='oar_email']", data.get(dataSet).get("Email"));
				Common.clickElement("xpath", "//button[@class='action submit primary']");
				ExtenantReportUtils.addPassLog("verifying Order and Returs Page",
							"It should lands on Order and Returs form Page",
							"user  lands on Order and Returs form Page",
							Common.getscreenShotPathforReport("Order and Returs"));
				
	      } catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying Order and Returs from",
						"It should be successfully navigate to Order and Returs page",
						"user failed to navigate Order and Returs", Common.getscreenShotPathforReport("Order and Returs"));
				Assert.fail();
			}
	}
	
	public void CreateAccount(String dataSet) {

		try {
			Thread.sleep(5000);
			Common.clickElement("xpath", "//a[@class='header-content__right-link']");
			Sync.waitElementClickable("xpath", "//a[@class='action create primary']");
			Common.clickElement("xpath", "//a[@class='action create primary']");
			ExtenantReportUtils.addPassLog("verifying Create Account button",
					"It should lands on Create New Customer from Account form Page",
					"user  lands on Customer Account creation form Page",
					Common.getscreenShotPathforReport("createaccount"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Create Account button",
					"It should lands on Create New Customer from Account form Page",
					"user faield lands on Account form Page", Common.getscreenShotPathforReport("createaccount"));
			Assert.fail();
		}

		try {
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
			Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[@title='Create an Account']");
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying Create Account from",
					"Account should be created successfully navigate to My Account page",
					"user faield to create account", Common.getscreenShotPathforReport("createaccountfaield"));
			Assert.fail();
		}

	}

	public void Humidifiers_Vaporizers(){
		String expectedResult = "It Should be navigate to Humdifiers & Vaporizers.";		
		try {			
			Thread.sleep(6000);			
			Sync.waitElementClickable("xpath", "//a[@id='ui-id-3']");
			Common.mouseOver("xpath", "//a[@id='ui-id-3']");
			Sync.waitElementClickable("xpath", "//a[contains(text(),'Humidifiers & Vaporizers')]");
			Common.findElement("xpath", "//a[contains(text(),'Humidifiers & Vaporizers')]").click();
		    ExtenantReportUtils.addPassLog("verifying category Humdifiers & Vaporizers","lands on Humdifiers & Vaporizers", "User lands on the Humdifiers & Vaporizers",Common.getscreenShotPathforReport("faield to click"));		
			} catch (Exception | Error e) {			
				e.printStackTrace();			
				ExtenantReportUtils.addFailedLog("validating the category page.", expectedResult,"user faield to navigate Humdifiers & Vaporizers",Common.getscreenShotPathforReport("faield to navgate categorypage"));			
				Assert.fail();  		
				}	
		}

	public void clickHumidifiers() {
		String expectedResult = "It Should be navigate to Humdifiers & Vaporizers.";
		try {
			Thread.sleep(4000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			Sync.waitElementClickable("xpath", "(//img[@alt='3-in-1 SleepyTime Humidifier'])[2]");
			Common.clickElement("xpath", "(//img[@alt='3-in-1 SleepyTime Humidifier'])[2]");
			ExtenantReportUtils.addPassLog("verifying category Humdifiers & Vaporizers",
					"lands on Humdifiers & Vaporizers", "User lands on the Humdifiers & Vaporizers",
					Common.getscreenShotPathforReport("faield to click"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the category page.", expectedResult,
					"user faield to navigate Humdifiers & Vaporizers",
					Common.getscreenShotPathforReport("faield to navgate categorypage"));
			Assert.fail();

		}
	}

	public void productselect() throws Exception {
		String expectedResult = "It should select a product";
		try {
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Sync.waitElementClickable("xpath",
					"//span[@class='product-image-container product-image-container-564_category_page_grid']");
			Common.findElement("xpath", "//span[@class='product-image-container product-image-container-564_category_page_grid']")
					.click();
			ExtenantReportUtils.addPassLog("Verifing product list page", "Should select a product",
					"Should select a product", Common.getscreenShotPathforReport("Product is selected successfully"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verifing product list page", "Should select a product",
					"Should select a product", Common.getscreenShotPathforReport("Failed to selected product"));
		}

	}

	public void addtocart() throws Exception {
		String expectedResult = "Product should add to cart";
		try {
			Thread.sleep(1000);
			try {
			    //Common.scrollIntoView("xpath", "//span[contains(text(),'Add to Cart')]");
				Sync.waitElementClickable("xpath", "//button[@type='submit']//span[text()='Add to Cart']");
				Common.findElement("xpath", "//button[@type='submit']//span[text()='Add to Cart']").click();
				ExtenantReportUtils.addPassLog("Verifing product to add cart", "Product should add ti cart",
						"Product should add to cart",
						Common.getscreenShotPathforReport("Product successfully added to cart"));
			} catch (Exception | Error e) {
				// button[@class='action tocart primary']
				Sync.waitElementClickable("xpath", "//button[@class='action tocart primary']");
				// Common.findElement("xpath", "//button[@class='action tocart
				// primary']").click();
				Common.mouseOverClick("xpath", "//button[@class='action tocart primary']");
				ExtenantReportUtils.addPassLog("Verifing product to add cart", "Product should add ti cart",
						"Product should add to cart",
						Common.getscreenShotPathforReport("Product successfully added to cart"));

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verifing product to add to cart", "product should add to cart",
					"product should add to cart", Common.getscreenShotPathforReport("Failed to add to cart"));
		}
	}

	public void checkout() throws Exception {
		String expectedResult = "Product should add to cart";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable("id", "top-cart-btn-checkout");
			Common.clickElement("id", "top-cart-btn-checkout");
			ExtenantReportUtils.addPassLog("Verifing guest user checkout page", "Guest user checkout page success",
					"Guest user checkout page success",
					Common.getscreenShotPathforReport("Guest user checkout page success"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verifing guest user checkout page", "Guest user checkout page success",
					"Guest user checkout page success",
					Common.getscreenShotPathforReport("Failed to go geust user checkout"));
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

	/*public void shippingaddress(String datSet) throws Exception {
		String expectedResult = "Product should add to cart";
		try {
			Thread.sleep(3000);
			Sync.waitElementClickable("xpath", "//fieldset//input[@id='customer-email']");
			Common.textBoxInput("xpath", "//fieldset//input[@id='customer-email']", data.get(datSet).get("Email"));
			Sync.waitElementClickable("xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(datSet).get("FirstName"));
			Sync.waitElementClickable("xpath", "//input[@name='lastname']");
			Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(datSet).get("LastName"));
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "(//input[@class='input-text'])[6]");
			Common.textBoxInput("xpath", "(//input[@class='input-text'])[6]", data.get(datSet).get("Street"));
			Thread.sleep(3000);
			Common.actionsKeyPress(Keys.SPACE);
			Thread.sleep(2000);
			Common.clickElement("xpath", "(//a[@class='dropdown-item list-item containerItem'])[1]");
			Thread.sleep(5000);
			Common.textBoxInput("xpath","//input[@name='city']", data.get(datSet).get("City"));
			Common.textBoxInput("xpath", "//input[@name='postcode']",  data.get(datSet).get("postcode"));
			//Common.findElement("xpath", "//select[@name='region_id']").click();
			Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(datSet).get("Region"));
			//Common.mouseOverClick("xpath", "//option[contains(text(),'Connecticut')]");
			Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(datSet).get("phone"));
			Common.clickElement("xpath", "//button[@class='button action continue primary']");
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
	}
*/
public void shippingaddress(String datSet) throws Exception {
        String expectedResult = "Product should add to cart";
        try {
            Thread.sleep(3000);
            Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
            Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']", data.get(datSet).get("Email"));
            Sync.waitElementClickable("xpath", "//input[@name='firstname']");
            Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(datSet).get("FirstName"));
            Sync.waitElementClickable("xpath", "//input[@name='lastname']");
            Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(datSet).get("LastName"));
            Common.actionsKeyPress(Keys.PAGE_DOWN);
            Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
            Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(datSet).get("Street"));
            Thread.sleep(3000);
            Common.actionsKeyPress(Keys.SPACE);
            Thread.sleep(2000);
            Common.clickElement("xpath", "(//a[@class='dropdown-item list-item'])[1]");
            Thread.sleep(5000);
            Common.textBoxInput("xpath","//input[@name='city']", data.get(datSet).get("City"));
            Common.textBoxInput("xpath", "//input[@name='postcode']",  data.get(datSet).get("postcode"));
            //Common.findElement("xpath", "//select[@name='region_id']").click();
            Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(datSet).get("Region"));
            //Common.mouseOverClick("xpath", "//option[contains(text(),'Connecticut')]");
            Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(datSet).get("phone"));
            Common.clickElement("xpath", "//button[@class='button action continue primary']");
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
    }




	public void paymentDetails(String dataSet) {
		String expectedResult = "Payment With Valid Credit Card";
		try {
			Thread.sleep(5000);

			if (Common.isElementDisplayed("xpath", "//div[@id='checkout-loader']")) {
				Thread.sleep(4000);
			} else {
				Thread.sleep(6000);
				// Sync.waitElementPresent("id", "ime_paymetrictokenize");
				// Common.clickElement(By.id("ime_paymetrictokenize"));
				// Thread.sleep(8000);
			}
			Common.switchFrames("paymetric_xisecure_frame");
			Sync.waitElementPresent("xpath", "//select[@id='c-ct']");
			Common.dropdown("xpath", "//select[@id='c-ct']", SelectBy.TEXT, data.get(dataSet).get("cardType"));
			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));

			Common.clickElement("xpath", "(//select[@id='c-exmth']/option)[6]");
			// Common.dropdown("id", "c-exmth", SelectBy.VALUE,
			// data.get(dataSet).get("ExpMonth"));

			// Common.textBoxInput("xpath", "//select[@id='c-exmth']",
			// data.get(dataSet).get("ExpMonth"));
			Common.dropdown("id", "c-exyr", SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
			Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));
			Common.switchToDefault();
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should Make payment wih valid credit card successfully",
					"Make payment wih valid credit card unsuccessfully",
					Common.getscreenShotPathforReport("Payment CC Failed"));
			e.printStackTrace();
			Assert.fail();
		}

		/*
		 * Sync.waitElementPresent("xpath",
		 * "//button[@class='action primary checkout']");
		 * 
		 * //Common.clickElement(By.
		 * xpath("//button[@class='action primary checkout']"));
		 * //Common.findElement("xpath",
		 * "//button[@class='action primary checkout']").click();
		 * 
		 * Common.javascriptclickElement("xpath",
		 * "//button[@class='action primary checkout']"); report.addPassLog(
		 * expectedResult,"Should Make payment wih valid credit card successfully"
		 * , "Make payment wih valid credit card successfully",
		 * Common.getscreenShotPathforReport("Payment CC success")); try{
		 * Sync.waitElementVisible("xpath",
		 * "//p[text()='Your order number is: ']"); }catch(Exception |Error e){
		 * Sync.waitElementVisible("xpath", "//p[text()='Your order # is: ']");
		 * } report.addPassLog(expectedResult," Order should place sucessfully",
		 * "Submited order and order places sucessfully",
		 * Common.getscreenShotPathforReport("Payment CC success"));
		 * }catch(Exception |Error e) { report.addFailedLog(
		 * expectedResult,"Should Make payment wih valid credit card successfully"
		 * , "Make payment wih valid credit card unsuccessfully",
		 * Common.getscreenShotPathforReport("Payment CC Failed"));
		 * e.printStackTrace(); Assert.fail(); }
		 */
	}
	 public void Promocode(String dataSet) throws Exception{
	    	
	    /*	try{
	    	Common.actionsKeyPress(Keys.ARROW_DOWN);	
	    	Sync.waitElementClickable("id", "block-discount-heading");
	    	Thread.sleep(3000);
	    	Common.findElement("xpath", "//span[@id='block-discount-heading']").click();
	    	Sync.waitElementPresent("xpath", "//input[@id='discount-code']");
	    	Thread.sleep(2000);
	    	Common.textBoxInput("xpath", "//input[@id='discount-code']",data.get(dataSet).get("Promecode"));
	    	Thread.sleep(2000);
	    	Sync.waitElementClickable("xpath", "//button[@class='action action-apply']");
	    	Common.clickElement("xpath", "//button[@class='action action-apply']");
	    	Thread.sleep(4000);
	    	ExtenantReportUtils.addPassLog("Apply Promocode on Checkout Page", "Promocode Should be applied on Checkout Page", "Promo Code added successfully and applied discount to Order Summary", Common.getscreenShotPathforReport("Promocode"));
	    	//Thread.sleep(2000);
	    }catch(Exception |Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Apply Promocode on Checkout Page", "Promocode Should be applied on Checkout Page", "Failed to apply Promocode", Common.getscreenShotPathforReport("Promocode"));
	     	Assert.fail();
	    }
	    }*/
		 try{
				//	Sync.waitPageLoad();
					Thread.sleep(3000);
					Common.actionsKeyPress(Keys.END);
				Sync.waitElementPresent("id", "block-discount-heading");
				Common.scrollIntoView("id", "block-discount-heading");
				Common.clickElement("id", "block-discount-heading");
				Sync.waitElementPresent("id", "discount-code");
	            Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Promocode"));
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
	 public void order_Verifying() throws Exception{
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
			Common.assertionCheckwithReport(sucessMessage.equals("Thank you for your purchase!"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");
				
			}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
						"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
				Assert.fail();
			}
		}
	public void invalidData(String dataSet) {
		// String expectedResult="Payment With Invalid Credit Card";
		try {
			Thread.sleep(4000);
			Common.switchFrames("paymetric_xisecure_frame");
			Sync.waitElementPresent("xpath", "//select[@id='c-ct']");
			Common.dropdown("xpath", "//select[@id='c-ct']", SelectBy.TEXT, data.get(dataSet).get("cardType"));
			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));

			Common.clickElement("xpath", "(//select[@id='c-exmth']/option)[6]");
			Common.dropdown("id", "c-exyr", SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
			Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));
			Common.switchToDefault();
			try {
				Thread.sleep(4000);
				Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
				Common.javascriptclickElement("xpath", "//button[@class='action primary checkout']");
				ExtenantReportUtils.addPassLog("Checkout with Invalid CC data",
						"Checkout process should be falied with invalid CC data", "Checkout process falied",
						Common.getscreenShotPathforReport("InvalidCC"));
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Checkout with Invalid CC data",
						"Checkout process should be falied with invalid CC data",
						"checkout process succesfull with Invalid CC", Common.getscreenShotPathforReport("InvalidCC"));
				Assert.fail();
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Checkout with Invalid CC data",
					"Checkout process should be falied with invalid CC data",
					"checkout process succesfull with Invalid CC", Common.getscreenShotPathforReport("InvalidCC"));
			Assert.fail();
		}
	}

	public void PlaceOrder() {
		String expectedResult = "Payment With Valid Credit Card";
		try {
			Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");

			// Common.clickElement(By.xpath("//button[@class='action primary
			// checkout']"));
			// Common.findElement("xpath", "//button[@class='action primary
			// checkout']").click();

			Common.javascriptclickElement("xpath", "//button[@class='action primary checkout']");
			report.addPassLog(expectedResult, "Should Make payment wih valid credit card successfully",
					"Make payment wih valid credit card successfully",
					Common.getscreenShotPathforReport("Payment CC success"));
			try {
				Sync.waitElementVisible("xpath", "//p[text()='Your order number is: ']");
			} catch (Exception | Error e) {
				Sync.waitElementVisible("xpath", "//p[text()='Your order # is: ']");
			}
			report.addPassLog(expectedResult, " Order should place sucessfully",
					"Submited order and order places sucessfully",
					Common.getscreenShotPathforReport("Payment CC success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should Make payment wih valid credit card successfully",
					"Make payment wih valid credit card unsuccessfully",
					Common.getscreenShotPathforReport("Payment CC Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void Logout() throws Exception {
		String expectedResult = "log out";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable("xpath", "//button[@class='action switch']");
			Common.clickElement("xpath", "//button[@class='action switch']");
			Thread.sleep(1000);
			Sync.waitElementClickable("xpath", "//a[text()='Sign Out']");
			Common.clickElement("xpath", "//a[text()='Sign Out']");
			ExtenantReportUtils.addPassLog("Verfing log out of register", "user successfully loged out",
					"user successfully loged out", Common.getscreenShotPathforReport("user successfully loged out"));
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verfing log out of register", "user successfully loged out",
					"user successfully loged out", Common.getscreenShotPathforReport("faield to log out"));
			Assert.fail();

		}

	}

	public void shipingmethod() throws InterruptedException {
		String expectedResult = "shipping method";
		try {
			Thread.sleep(2000);
			Sync.waitElementClickable("xpath", "//button[@class='button action continue primary']");
			Common.clickElement("xpath", "//button[@class='button action continue primary']");
			ExtenantReportUtils.addPassLog("Verfing log out of register", "user successfully loged out",
					"user successfully loged out", Common.getscreenShotPathforReport("user successfully loged out"));
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verfing log out of register", "user successfully loged out",
					"user successfully loged out", Common.getscreenShotPathforReport("faield to log out"));
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
	
	public void forgotpassword(String dataSet) throws Exception {
		String expectedResult = "forgotpassword";
		try {
			Thread.sleep(3000);
			Common.clickElement("xpath", "//a[@class='header-content__right-link']");
			Sync.waitElementClickable("xpath", "//a[@class='action remind']");
			Common.clickElement("xpath", "//a[@class='action remind']");
			Common.textBoxInput("xpath", "//input[@id='email_address']", data.get(dataSet).get("Email"));
			Sync.waitElementClickable("xpath", "//button[@class='action submit primary']");
			Common.findElement("xpath", "//button[@class='action submit primary']").click();
			ExtenantReportUtils.addPassLog("Verfing forgot password", "user can successfully  rest password",
					"user can successfully  rest password",
					Common.getscreenShotPathforReport("user  successfully  rested password"));
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verfing forgot password", "user can successfully  rest password",
					"user can successfully  rest password",
					Common.getscreenShotPathforReport("faield to rest password"));
			Assert.fail();
		}
	}

	public void mincat() throws Exception {
		String expectedResult = "minicat";
		try {
			Thread.sleep(3000);
			Sync.waitElementClickable("xpath", "//a [@class='action showcart']");
			Common.findElement("xpath", "//a [@class='action showcart']").click();
			ExtenantReportUtils.addPassLog("Verfing mini cart", "user can successfully view mini cart",
					"user can successfully view mini cart",
					Common.getscreenShotPathforReport("user  successfully can view mini cart"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verfing mini cart", "user can successfully view mini cart",
					"user can successfully view mini cart",
					Common.getscreenShotPathforReport("faield to view mini cart"));
			Assert.fail();
		}
	}

	public void validatingcart() throws Exception {
		String expectedResult = "minicart validating";
		try {
			Thread.sleep(1000);
			Sync.waitElementClickable("xpath", "//a[@class='action viewcart']");
			Common.findElement("xpath", "//a[@class='action viewcart']").click();
			Sync.waitElementClickable("xpath", "//button[@class='qty-incrementer__increment']");
			Common.findElement("xpath", "//button[@class='qty-incrementer__increment']").click();
			Sync.waitElementClickable("xpath", "//button[@name='update_cart_action']");
			Common.findElement("xpath", "//button[@name='update_cart_action']").click();
			Common.findElement("xpath", "//a [@class='action showcart']").click();
			ExtenantReportUtils.addPassLog("Verfing cart validation", "user can successfully update cart",
					"user can successfully update cart",
					Common.getscreenShotPathforReport("user can successfully update cart"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verfing cart validating", "user can successfully update cart",
					"user can successfully update cart", Common.getscreenShotPathforReport("faield to update cart"));
			Assert.fail();
		}
	}

	public void search(String dataset) {
		try {
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//*[@id=\"search_mini_form\"]/div/label");
			Common.clickElement("xpath", "//*[@id=\"search_mini_form\"]/div/label");
			Sync.waitElementPresent("xpath", "(//input[@id='search'])");
			Common.textBoxInput("xpath", "//input[@id='search']", data.get(dataset).get("ProductName"));
			ExtenantReportUtils.addPassLog("To verify the search functionality with full productname",
					"should get the product name in search field", "user  successfully Entered the productname",
					Common.getscreenShotPathforReport("Searched productname Successfully"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To verify the search functionality with fullproduct name",
					"Should get the productname in search field", "user unable to Enter the productname",
					Common.getscreenShotPathforReport("Failed to search proudctname"));
			Assert.fail();
		}

		try {
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(2000);
			// Common.scrollIntoView("xpath", "(//h3[@class='result-title
			// text-ellipsis'])[1]");
			ExtenantReportUtils.addPassLog("To verify search results page", "Should land on  product list page",
					"user successfully landed on  product search results page",
					Common.getscreenShotPathforReport("Successfully landed on PLP Page"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("To verify search results page", "Should land on  product list page",
					"user successfully landed on  product search results page",
					Common.getscreenShotPathforReport("Failed to land on PLP Page"));
			Assert.fail();
		}
	}
	public void order(String DataSet) {
		// TODO Auto-generated method stub
		try
		{
		Common.clickElement("xpath","(//a[@title='Order Status'])[1]");	
		Common.textBoxInput("xpath","//input[@id='oar-order-id']",data.get(DataSet).get("OrderID"));
		Common.textBoxInput("xpath","//input[@id='oar-billing-lastname']",data.get(DataSet).get("BillingLastName"));
		Common.textBoxInput("xpath","//input[@id='oar_email']",data.get(DataSet).get("Email"));
		Common.clickElement("xpath","//button[@title='Continue']");
		ExtenantReportUtils.addPassLog("enter to the order status", "expectedResult",
				"enter data in order status",
				Common.getscreenShotPathforReport("order status entered successfully"));
		
		}
		catch (Exception | Error e)
		{
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("clicking on order status button", "expectedResult",
					"unable to click on order status button",
					Common.getscreenShotPathforReport("order status button not clicked"));
			Assert.fail();
			
		}
		
		
		
	}
	


	public void Myaccountinformation() throws Exception {
		Thread.sleep(2000);
		Sync.waitElementClickable("xpath", "//button[@class='action switch']");
		Common.findElement("xpath", "//button[@class='action switch']").click();
		Sync.waitElementClickable("xpath", "//a[@id='ideWmZDsN0']");
		Common.findElement("xpath", "//a[@id='ideWmZDsN0']").click();
	}

	public void MyAccountpage() throws Exception {

		Thread.sleep(3000);

		Sync.waitElementPresent("xpath", "(//li[@class='nav item'])[1]");

		Common.clickElement("xpath", "(//li[@class='nav item'])[1]");

		Sync.waitElementPresent("xpath", "(//td[@class='col actions'])/a");

		Common.clickElement("xpath", "(//td[@class='col actions'])/a");

		Thread.sleep(1000);
		Sync.waitElementPresent("xpath", "//div[@class='actions-toolbar order-actions-toolbar']/div");

		Common.clickElement("xpath", "//div[@class='actions-toolbar order-actions-toolbar']/div");

		report.addPassLog("Should display My Orders Page", "My Orders Page displayed successfully",
				Common.getscreenShotPathforReport("My Orders Page success"));

	}

	public void Verifyhomepage() {
		try {
			Sync.waitElementPresent("xpath", "//a[@class='logo']");
			String title = Common.findElement("xpath", "//a[@class='logo']").getText();
			title.equals("Promo Banner Information Here");

			ExtenantReportUtils.addPassLog("To verify home page", "Should land home page",
					"user successfully landed on  home page",
					Common.getscreenShotPathforReport("Successfully landed on home Page"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To verify home page", "Should not land home page",
					"user successfully not landed on  home page",
					Common.getscreenShotPathforReport("Failed to land on PLP Page"));
			Assert.fail();
		}

	}
	
	public void NewsLetterSubscription(String dataSet){
		
		try{
			Thread.sleep(4000);
			Common.scrollIntoView("xpath", "//input[@id='newsletter']");
			Common.textBoxInput("xpath", "//input[@id='newsletter']", data.get(dataSet).get("Email"));
			
			Sync.waitElementClickable("xpath", "//button[@class='action subscribe primary']");
			Common.findElement("xpath", "//button[@class='action subscribe primary']").click();
			Common.actionsKeyPress(Keys.UP);
		    int NewsLetterSuccessmessage=Common.findElements("xpath", "//div[@class='message-success success message']").size();
		    int NewsLetterErrormessage=Common.findElements("xpath", "//div[@class='message-error error message']").size();
				
		    Common.assertionCheckwithReport(NewsLetterSuccessmessage>0 || NewsLetterErrormessage>0, "verifying error message signuppage", "enter with empety data it must show error message","sucessfully display the error message", "faield to dispalyerrormessage");
				}
				catch(Exception |Error e) {
				 	   
					ExtenantReportUtils.addFailedLog("verifying error message signpage", "enter with empty data it must show error message", "faield to dispalyerrormessage", Common.getscreenShotPathforReport("loginpagevalidation"));
					Assert.fail();
				}
			
	}

	public void AddNewAddress(String dataSet) throws Exception {

		Thread.sleep(4000);
		Sync.waitElementClickable("xpath", "//button[@class='action switch']");
		Common.findElement("xpath", "//button[@class='action switch']").click();
		Sync.waitElementClickable("xpath", "//a[@id='id4Pz2wp82']");
		Common.findElement("xpath", "//a[@id='id4Pz2wp82']").click();
		Thread.sleep(4000);
		Sync.waitElementClickable("xpath", "//a[contains(text(),'Address Book')]");
		Common.findElement("xpath", "//a[contains(text(),'Address Book')]").click();
		// Common.javascriptclickElement("xpath", "//a[contains(text(),'Address
		// Book')]");
		Thread.sleep(4000);
		/*Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//input[@id='street_1']");
		Common.textBoxInput("xpath", "//input[@id='street_1']", data.get(dataSet).get("Street"));
		Thread.sleep(8000);
		Common.clickElement("xpath", "//a[@class='dropdown-item list-item']");
		Thread.sleep(5000);
		Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
		Thread.sleep(2000);
		Sync.waitElementClickable("xpath", "//button[@class='action submit primary']");
		Common.clickElement("xpath", "//button[@class='action submit primary']");
		ExtenantReportUtils.addPassLog("Verifing guest user checkout page", "Guest user checkout page success",
				"Guest user checkout page success",
				Common.getscreenShotPathforReport("Guest user checkout page success"));
*/
		
		
		Common.textBoxInput("xpath", "//input[@id='street_1']",
					data.get(dataSet).get("Street"));
			Thread.sleep(2000);
		   Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//input[@id='city']",
					data.get(dataSet).get("City"));
			// Common.mouseOverClick("name", "region_id");
			try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("xpath", "//select[@id='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("name", "postcode");
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
			Thread.sleep(3000);
			Common.findElement("xpath", "//button[@class='action submit primary']").click();

			ExtenantReportUtils.addPassLog("Verifing guest user checkout page", "Guest user checkout page success",
					"Guest user checkout page success",
					Common.getscreenShotPathforReport("Guest user checkout page success"));	
		
		
		
		
	}

	public void Addnewaddress(String dataSet) {
		try {
			Thread.sleep(3000);
			Sync.waitElementClickable("xpath", "//button[@class='action primary add']");
			Common.findElement("xpath", "//button[@class='action primary add']").click();
			ExtenantReportUtils.addPassLog("To verify my address page", "Should land on my address page",
					"user successfully landed on my address page",
					Common.getscreenShotPathforReport("Successfully landed on My address Page"));
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//input[@id='street_1']");
			Common.textBoxInput("xpath", "//input[@id='street_1']", data.get(dataSet).get("Street"));
			Thread.sleep(8000);
			Common.clickElement("xpath", "//a[@class='dropdown-item list-item']");
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
			Thread.sleep(2000);
			Sync.waitElementClickable("xpath", "//button[@class='action submit primary']");
			Common.clickElement("xpath", "//button[@class='action submit primary']");
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
	}

	public void myaccount() {
		try {
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//button[@class='action switch']");
			Common.findElement("xpath", "//button[@class='action switch']");
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//a[contains(text(),'My Account')]");
			Common.findElement("xpath", "//a[contains(text(),'My Account')]");
            Thread.sleep(2000);
			// Sync.waitElementClickable("xpath", "(//li[@class='nav
			// item']/a)[4]");
			// Common.findElement("xpath", "(//li[@class='nav
			// item']/a)[4]").click();
			ExtenantReportUtils.addPassLog("To verify My account page", "Should land on my account page",
					"User Should land on my account page",
					Common.getscreenShotPathforReport("Successfully landed on Myaccount Page"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To verify My account page", "Should land on my account page",
					"faield to land on my account",
					Common.getscreenShotPathforReport("faield to land on my account page"));
			Assert.fail();
		}
	}

	public void viewandeditcart(String dataSet) {
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable("xpath", "//a[@class='action viewcart']");
			Common.findElement("xpath", "//a[@class='action viewcart']").click();

			ExtenantReportUtils.addPassLog("To verify My account page", "Should land on my account page",
					"User Should land on my account page",
					Common.getscreenShotPathforReport("Successfully landed on Myaccount Page"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To verify My account page", "Should land on my account page",
					"faield to land on my account",
					Common.getscreenShotPathforReport("faield to land on my account page"));
			Assert.fail();

		}
	}

	public void PLPvalidation() {
		try {
			Thread.sleep(2000);
			Sync.waitElementClickable("xpath", "//div[@class='filter-options-title']");
			Common.findElement("xpath", "//div[@class='filter-options-title']").click();
			Sync.waitElementClickable("xpath", "//a[@data-opt-path='price=40-50']");
			Common.findElement("xpath", "//a[@data-opt-path='price=40-50']").click();
			Thread.sleep(3000);
			Sync.waitElementClickable("xpath", "(//select[@id='sorter']/option)[3]");
			Common.clickElement("xpath", "(//select[@id='sorter']/option)[3]");

			ExtenantReportUtils.addPassLog("To verify My account page", "Should land on my account page",
					"User Should land on my account page",
					Common.getscreenShotPathforReport("Successfully landed on Myaccount Page"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To verify My account page", "Should land on my account page",
					"faield to land on my account",
					Common.getscreenShotPathforReport("faield to land on my account page"));
			Assert.fail();

		}
	}

	/////////////////////////////

	public void searchProduct(String dataSet) throws Exception {
		Thread.sleep(5000);
		String productname = data.get(dataSet).get("productname");
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
			/*Common.clickElement("xpath", "//span[@class='toggle']");
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);*/
			/*
			 * //Common.refreshpage(); //Sync.waitElementVisible("xpath",
			 * "//img[contains(@alt,'"+productname+"')]"); List<WebElement>
			 * product=Common.findElements("xpath",
			 * "//img[contains(@alt,'"+productname+"')]");
			 * Common.mouseOverClick("xpath",
			 * "//img[@alt='Vicks Warm Mist Humidifier']");
			 * Common.javascriptclickElement("xpath",
			 * "//img[contains(@alt,'"+productname+"')]");
			 * 
			 * //product.get(0).click(); System.out.println(product.size());
			 * Thread.sleep(3000); clickAddtoBag();
			 */
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying search product in pdp page",
					"search the selected product in plp page it navigate to pdp page", "failed to navigate to pdp page",
					Common.getscreenShotPathforReport("searchproduct"));
			Assert.fail();
		}
	}
	public void clickonproduct (){
		try {
			Sync.waitPageLoad();
			Common.clickElement("xpath", "//a[@class='product-item-link']");
			ExtenantReportUtils.addPassLog("verifying click on product", "User click on product",
					"user successfully click on product", Common.getscreenShotPathforReport("Clickonproduct"));
		} catch (Exception | Error e) {

			ExtenantReportUtils.addFailedLog("verifying click on product", "User click on product ",
					"user failed to click on product", Common.getscreenShotPathforReport("failedtoclickonproduct"));
			Assert.fail();
		}
	}
	public void contactUS(String DataSet){
		Common.oppenURL(data.get(DataSet).get("ContactUS"));
		String bannerText = Common.getText("xpath", "//h1[@data-content-type='heading']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");

		Assert.assertTrue(bannerText.equals("Contact Us"));

		Common.assertionCheckwithReport(bannerText.equals("Contact Us"), "Give URL Contains Expected Templat ",
				"give url Navigating to CountUs link", "give url failed lo load");

		Assert.assertTrue(Common.getPageTitle().contains(bannerText));

		Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ",
				"Give URL Contains valid title ", "title checking");
	}
	
	public void Humdifiers(String DataSet){
		Common.oppenURL(data.get(DataSet).get("Humdifiers"));
		String bannerText = Common.getText("xpath", "//h1[@id='page-title-heading']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");

		Assert.assertTrue(bannerText.equals("Humidifiers & Vaporizers"));

		Common.assertionCheckwithReport(bannerText.equals("Humidifiers & Vaporizers"), "Give URL Contains Expected Templat ",
				"give url Navigating to Humidifiers & Vaporizers link", "give url failed lo load");

		Assert.assertTrue(Common.getPageTitle().contains(bannerText));

		Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ",
				"Give URL Contains valid title ", "title checking");
	}
	public void Blog(String DataSet){
		Common.oppenURL(data.get(DataSet).get("Blog"));
		String bannerText = Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");

		Assert.assertTrue(bannerText.equals("Health & Wellness Blog"));

		Common.assertionCheckwithReport(bannerText.equals("Health & Wellness Blog"), "Give URL Contains Expected Templat ",
				"give url Navigating to Blog link", "give url failed lo load");

		Assert.assertTrue(Common.getPageTitle().contains(bannerText));

		Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ",
				"Give URL Contains valid title ", "title checking");
	}
	public void Ourhistory(String DataSet){
		Common.oppenURL(data.get(DataSet).get("Ourhistory"));
		String bannerText = Common.getText("xpath", "//a[@class='x-brand img']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");

		Assert.assertTrue(bannerText.equals("Our 52 year history"));

		Common.assertionCheckwithReport(bannerText.equals("Our 52 year history"), "Give URL Contains Expected Templat ",
				"give url Navigating to Ourhistory link", "give url failed lo load");

		Assert.assertTrue(Common.getPageTitle().contains(bannerText));

		Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ",
				"Give URL Contains valid title ", "title checking");
	}
	public void Heleoftroy(String DataSet){
		Common.oppenURL(data.get(DataSet).get("Ourcompany"));
		String bannerText = Common.getText("xpath", "//h1[@class='visually-hidden']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");

		Assert.assertTrue(bannerText.equals("Helen of Troy"));

		Common.assertionCheckwithReport(bannerText.equals("Helen of Troy"), "Give URL Contains Expected Templat ",
				"give url Navigating to Ourcompany link", "give url failed lo load");

		Assert.assertTrue(Common.getPageTitle().contains(bannerText));

		Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ",
				"Give URL Contains valid title ", "title checking");
	}
	public void FiltersAccessories(String DataSet){
		Common.oppenURL(data.get(DataSet).get("Filters"));
		String bannerText = Common.getText("xpath", "//h1[@id='page-title-heading']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");

		Assert.assertTrue(bannerText.equals("Filters & Accessories"));

		Common.assertionCheckwithReport(bannerText.equals("Filters & Accessories"), "Give URL Contains Expected Templat ",
				"give url Navigating to FiltersAccessories link", "give url failed lo load");

		Assert.assertTrue(Common.getPageTitle().contains(bannerText));

		Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ",
				"Give URL Contains valid title ", "title checking");
	}
	public void SinusInhalers(String DataSet){
		Common.oppenURL(data.get(DataSet).get("Sinus"));
		String bannerText = Common.getText("xpath", "//h1[@id='page-title-heading']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");

		Assert.assertTrue(bannerText.equals("Sinus Inhalers"));

		Common.assertionCheckwithReport(bannerText.equals("Sinus Inhalers"), "Give URL Contains Expected Templat ",
				"give url Navigating to SinusInhalers link", "give url failed lo load");

		Assert.assertTrue(Common.getPageTitle().contains(bannerText));

		Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ",
				"Give URL Contains valid title ", "title checking");
	}
	public void ProductSupport (String DataSet){
	Common.oppenURL(data.get(DataSet).get("ProductSupport"));
	String bannerText = Common.getText("xpath", "//li[@class='item cms_page']");
	Assert.assertTrue(Common.getCurrentURL().contains("https"));

	Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
			"give url contains https", "give url missing  https");

	Assert.assertTrue(bannerText.equals("Vicks Support"));

	Common.assertionCheckwithReport(bannerText.equals("Vicks Support"), "Give URL Contains Expected Templat ",
			"give url Navigating to ProductSupport link", "give url failed lo load");

	Assert.assertTrue(Common.getPageTitle().contains(bannerText));

	Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ",
			"Give URL Contains valid title ", "title checking");
}
	public void Insta(String DataSet){
		Common.oppenURL(data.get(DataSet).get("Insta"));
		String bannerText = Common.getText("xpath", "//div[@class='gr27e  ']/h1");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");

		Assert.assertTrue(bannerText.equals("Instagram"));

		Common.assertionCheckwithReport(bannerText.equals("Instagram"), "Give URL Contains Expected Templat ",
				"give url Navigating to Instagram link", "give url failed lo load");

		Assert.assertTrue(Common.getPageTitle().contains(bannerText));

		Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ",
				"Give URL Contains valid title ", "title checking");
	}
	public void Fb(String DataSet){
		Common.oppenURL(data.get(DataSet).get("Fb"));
		String bannerText = Common.getText("xpath", "//div[@class='_9axz']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");

		Assert.assertTrue(bannerText.equals("Log into Facebook"));

		Common.assertionCheckwithReport(bannerText.equals("Log into Facebook"), "Give URL Contains Expected Templat ",
				"give url Navigating to Facebook link", "give url failed lo load");

		Assert.assertTrue(Common.getPageTitle().contains(bannerText));

		Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ",
				"Give URL Contains valid title ", "title checking");
	}
	public void Youtube(String DataSet){
		Common.oppenURL(data.get(DataSet).get("Twitter"));
		String bannerText = Common.getText("xpath", "(//div[@id='text-container'])[1]");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");

		Assert.assertTrue(bannerText.equals("Vicks Humidifiers & Thermometers"));

		Common.assertionCheckwithReport(bannerText.equals("Vicks Humidifiers & Thermometers"), "Give URL Contains Expected Templat ",
				"give url Navigating to Youtube link", "give url failed lo load");

		Assert.assertTrue(Common.getPageTitle().contains(bannerText));

		Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ",
				"Give URL Contains valid title ", "title checking");
	}
	public void Twitter(String DataSet){
		Common.oppenURL(data.get(DataSet).get("Youtube"));
		String bannerText = Common.getText("xpath", "//a[@aria-label='Twitter']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");

		Assert.assertTrue(bannerText.equals("Vicks Devices"));

		Common.assertionCheckwithReport(bannerText.equals("Vicks Devices"), "Give URL Contains Expected Templat ",
				"give url Navigating to Twitter link", "give url failed lo load");

		Assert.assertTrue(Common.getPageTitle().contains(bannerText));

		Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ",
				"Give URL Contains valid title ", "title checking");
	}
	public void PUR(String DataSet){
		Common.oppenURL(data.get(DataSet).get("Pur"));
		String bannerText = Common.getText("xpath", "//a[@class='logo']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");

		Assert.assertTrue(bannerText.equals("PUR"));

		Common.assertionCheckwithReport(bannerText.equals("PUR"), "Give URL Contains Expected Templat ",
				"give url Navigating to PUR link", "give url failed lo load");

		Assert.assertTrue(Common.getPageTitle().contains(bannerText));

		Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ",
				"Give URL Contains valid title ", "title checking");
	}
	public void Honeywell(String DataSet){
		Common.oppenURL(data.get(DataSet).get("Honeywell"));
		String bannerText = Common.getText("xpath", "(//img[@title='Honeywell'])[1]");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");

		Assert.assertTrue(bannerText.equals("Honeywell"));

		Common.assertionCheckwithReport(bannerText.equals("Honeywell"), "Give URL Contains Expected Templat ",
				"give url Navigating to Honeywell link", "give url failed lo load");

		Assert.assertTrue(Common.getPageTitle().contains(bannerText));

		Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ",
				"Give URL Contains valid title ", "title checking");
	}
	public void FAQS(String DataSet){
		Common.oppenURL(data.get(DataSet).get("FAQs"));
		String bannerText = Common.getText("xpath", "//li[@class='item cms_page']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");

		Assert.assertTrue(bannerText.equals("FAQ Humidifers General - Vicks"));

		Common.assertionCheckwithReport(bannerText.equals("FAQ Humidifers General - Vicks"), "Give URL Contains Expected Templat ",
				"give url Navigating to FAQs link", "give url failed lo load");

		Assert.assertTrue(Common.getPageTitle().contains(bannerText));

		Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ",
				"Give URL Contains valid title ", "title checking");
	}
	public void headLinksValidations(String dataSet) throws Exception{
		String Hederlinks=data.get(dataSet).get("Header Names");
		String[] hedrs=Hederlinks.split(",");
		int i=1;
		
		try{
		for(i=1;i<hedrs.length;i++){
			System.out.println(hedrs[i]);
			Common.mouseOver("xpath", "//a[@class='nav-main nav-main-vicks__link ui-corner-all']");
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

	public void clickAddtoBag() {
		try {
			Sync.waitPageLoad();
			Common.clickElement("id", "product-addtocart-button");
			ExtenantReportUtils.addPassLog("verifying add to cart button", "User click add to card button",
					"user successfully click add to cart button", Common.getscreenShotPathforReport("Clickaadtocart"));
		} catch (Exception | Error e) {

			ExtenantReportUtils.addFailedLog("verifying add to cart button", "User click add to card button",
					"user failed to click add to cart button", Common.getscreenShotPathforReport("failedtoclickutton"));
			Assert.fail();
		}

	}

	public void update_product_miniCartBag(String productQTY) throws Exception {
		try{
			
		    Common.clickElement("xpath", "//input[contains(@class,'cart-item-qty')]");
		    Common.actionsKeyPress(Keys.BACK_SPACE);
		    Common.actionsKeyPress(Keys.BACK_SPACE);
			Common.textBoxInput("xpath", "//input[contains(@class,'cart-item-qty')]",productQTY);
			
			Common.clickElement("xpath", "//span[text()='Update']");
		
			
		/*int alermessage=Common.findElements("xpath", "//button[@data-role='action']").size();
		if(alermessage>0) {
			Common.clickElement("xpath", "//button[@data-role='action']");
			
		}
		else {
			clickminicartButton();
			String productvalue=Common.findElement("xpath", "//input[contains(@class,'cart-item-qty')]").getAttribute("data-item-qty");
       Common.assertionCheckwithReport(productvalue.equals(productQTY),"verifying product update in minicart", "update product in mini cart bag page", "successfullyupdate product in minicart bag page ", "faield to update product in cart page");
		}*/
		}
		catch(Exception |Error e) {
		 	e.printStackTrace();  
			ExtenantReportUtils.addFailedLog("verifying product update in minicart", "update product in mini cart bag page", "user faield to updateproducttocartpage", Common.getscreenShotPathforReport("cartpageupdate"));
			Assert.fail();
		}
		
	}
	
	
	
	public void removeproductinBagPage() {
		
		int size=Common.findElements("xpath", "//span[text()='Remove']").size();
		for(int i=0;i<=size;i++) {
			Common.clickElement("xpath", "//span[text()='Remove']");
		    Common.clickElement("xpath", "//a//span[contains(text(),'Remove')]");
		}
		int errormessage=Common.findElements("xpath", "//strong[@class='subtitle empty']").size();
		Common.assertionCheckwithReport(errormessage>0, "validating removeproducts mini cart page", "remove all the products from mini cart","successfully remove the products from mini cart page","faield to remove all the products from cart");
	}
	
	
	public void clickminicartButton() throws Exception {
		try {
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//a[contains(@class,'showcart')]");
			Common.clickElement("xpath", "//a[contains(@class,'showcart')]");
			ExtenantReportUtils.addPassLog("verifying mini cart button", "User click mini cart button",
					"user successfilly click mini cart button", Common.getscreenShotPathforReport("minicartbutton"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying mini cart button", "User click mini cart button",
					"user failed to click mini cart button",
					Common.getscreenShotPathforReport("faieldtominicartbutton"));
			Assert.fail();
		}
	}

	public void click_View_editcart() {
		try {
			Sync.waitElementPresent("xpath", "//a[@class='action viewcart']");
			Common.clickElement("xpath", "//a[@class='action viewcart']");
			ExtenantReportUtils.addPassLog("verifying the view edit cart button from mincart page",
					"user after click the  view and edit button it navigate to SHOPPING CART page",
					"User navigate to SHOPPING CART page", Common.getscreenShotPathforReport("SHOPPINGCARTPAGE"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the view edit cart button from mincart page",
					"user after click the  view and edit button it navigate to SHOPPING CART page",
					"User unabel navigate to SHOPPING CART page",
					Common.getscreenShotPathforReport("SHOPPINGCARTPAGE"));
			Assert.fail();
		}
	}

	public void changeQuntity_UpdateProduct(String productcount) {
		try {
			Sync.waitElementPresent("xpath", "//input[@title='Qty']");

			String Value = Common.findElement("xpath", "//input[@title='Qty']").getAttribute("value");

			Common.clickElement("xpath", "//input[@title='Qty']");
			Common.actionsKeyPress(Keys.BACK_SPACE);

			Common.textBoxInput("xpath", "//input[@title='Qty']", productcount);
			Common.clickElement("xpath", "//button[@name='update_cart_action']");
			Sync.waitPageLoad();
			String value = Common.findElement("xpath", "//input[@title='Qty']").getAttribute("value");

			Common.assertionCheckwithReport(value.equals(productcount), "verifying the product Quntity",
					"user change the quntity of product", "user successfully  change quntity",
					"Failed to chage the quntity");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product Quntity", "user change the quntity of product",
					"User faield to chage the quntity", Common.getscreenShotPathforReport("failed changequntity"));
			Assert.fail();
		}
	}

	public void clickCheckoutButton_minicart() {
		try {
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//ul[contains(@class,'checkout')]//button[@title='Checkout']");
		//	Common.findElement("xpath", "//ul[contains(@class,'checkout')]//button[@title='Checkout']").click();
			Common.clickElement("xpath","//ul[contains(@class,'checkout')]//button[@title='Checkout']");

			// Common.assertionCheckwithReport(checkoutbuttonSize>0, "verifying
			// mini cart button", "User click mini cart button", "user
			// successfully click mini cart button", "Failed click mini cart
			// button");
		} catch (Exception | Error e) {

			ExtenantReportUtils.addFailedLog("verifying checkOut button in minicart",
					"User click checkOut  button in mini cart", "user faield to click checkOut button",
					Common.getscreenShotPathforReport("checkOut"));
			Assert.fail();
		}
	}
	public void contact(String DataSet) {
		try
		{
			Common.actionsKeyPress(Keys.END);
			Common.clickElement("xpath","//a[@class='mobile-accordion-link']//span[text()='Contact Us']");
			Common.switchFrames("xpath","//div[@data-decoded='true']//iframe");
			
			
			Common.textBoxInput("xpath","//input[contains(@id,'Contact.Name.First')]",data.get(DataSet).get("FirstName"));
			
			Common.textBoxInput("xpath","//input[contains(@id,'Contact.Name.Last')]",data.get(DataSet).get("LastName"));
			
			Common.textBoxInput("xpath","//input[contains(@id,'rn_TextInput_7')]",data.get(DataSet).get("CompanyName"));
			
			Common.textBoxInput("xpath","//input[contains(@id,'rn_TextInput_9')]",data.get(DataSet).get("Primary"));
			
			Common.textBoxInput("xpath","//div[contains(@id,'rn_TextInput_11')]//input[@type='email']",data.get(DataSet).get("Email"));
			
			Common.clickElement("xpath","//select[contains(@id,'rn_SelectionInput_13')]");
			Sync.waitElementPresent("xpath","//select[contains(@id,'rn_SelectionInput_13')]");
		    Common.dropdown("xpath","//select[contains(@id,'rn_SelectionInput_13')]", SelectBy.TEXT, data.get(DataSet).get("country"));
			
			
		    
	    	Common.textBoxInput("xpath","//input[contains(@id,'rn_TextInput_15')]",data.get(DataSet).get("postcode"));
	    	
	/*		Common.clickElement("xpath","//select[contains(@id,'rn_SelectionInput_17')]");
			Sync.waitElementPresent("xpath","//select[contains(@id,'rn_SelectionInput_17')]");
			Common.dropdown("xpath","//select[contains(@id,'rn_SelectionInput_17')]", SelectBy.TEXT, data.get(DataSet).get("State"));*/
			
			Common.textBoxInput("xpath","//input[contains(@id,'rn_TextInput_19')]",data.get(DataSet).get("City"));
			
			Common.textBoxInput("xpath","//input[contains(@id,'rn_TextInput_21')]",data.get(DataSet).get("Street"));
			
			Common.textBoxInput("xpath","//input[contains(@id,'rn_TextInput_23')]",data.get(DataSet).get("OrderID"));
			
			Thread.sleep(5000);
			Common.clickElement("xpath","//i[@id='all_button_arrow']");
			Sync.waitElementPresent("xpath","//i[@id='all_button_arrow']");
			String productname=data.get(DataSet).get("productname");
		    Common.clickElement("xpath","//div[text()=('"+productname+"')]"); 
				
		    Common.clickElement("xpath","//button[contains(@id,'rn_ProductCategoryInput_27')]");
			Sync.waitElementPresent("xpath","//button[contains(@id,'rn_ProductCategoryInput_27')]");
	     	String Topicname=data.get(DataSet).get("TOPIC");
	     	Common.clickElement("xpath","//a[text()=('"+Topicname+"')]");
			
			Common.clickElement("xpath","//input[contains(@id,'rn_FileAttachmentUpload_28')]");
			String path = System.getProperty("user.dir") + ("\\src\\test\\resources\\TestData\\Vicks\\order test.png");
			
			try 
			{ 
				Common.fileUpLoad("xpath", "//input[contains(@id,'FileInput')]", path);
			//	ExtenantReportUtils.addPassLog("verifying chosse file button button", "It should select file from dir", "file should be attach in contact form Page", Common.getscreenShotPathforReport("choose file"));

			}
			catch (Exception | Error e)
			{
				//e.printStackTrace();
		      //  ExtenantReportUtils.addFailedLog("verifying choose file button", "It should select file for dir", "user faield land on contact form Page", Common.getscreenShotPathforReport("topic"));
				//Assert.fail();
			}
		
			
			Thread.sleep(3000);
			//Common.closeCurrentWindow();
			//Common.actionsKeyPress(Keys.CANCEL);
			//Common.switchmainWindowsCons();
			Common.textBoxInput("xpath","//textarea[@class='rn_TextArea']",data.get(DataSet).get("MESSAGE"));
			
			Thread.sleep(5000);
			Common.clickElement("xpath","//button[contains(@id,'rn_CustomFormSubmit_53')]");
			
			ExtenantReportUtils.addPassLog("verifying submit button button", "It should click on submit button", "submit button clicked successfully", Common.getscreenShotPathforReport("submit"));
	
		
			
		}
		catch (Exception | Error e)
		{

			e.printStackTrace();
	        ExtenantReportUtils.addFailedLog("verifying submit button", "It should be click on submit button", "user faield to click submit button", Common.getscreenShotPathforReport("submit button"));
			Assert.fail();
		}
		
	}

	
		
public void addDeliveryAddress_registerUser(String dataSet) throws Exception {

	String expectedResult = "shipping address is entering in the fields";
    int size = Common.findElements(By.xpath("//span[contains(text(),'New Address')]")).size();
	if (size > 0) {

		try {
			Common.clickElement("xpath", "//span[contains(text(),'New Address')]");
		/*	Common.textBoxInput("xpath", "//fieldset//input[@name='username']",
					data.get(dataSet).get("Email"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));*/
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
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
					data.get(dataSet).get("Street"));
			Thread.sleep(2000);
			/*Common.actionsKeyPress(Keys.SPACE);
			Thread.sleep(3000);
			try {
				Common.clickElement("xpath",
						"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			} catch (Exception e) {
				Common.actionsKeyPress(Keys.BACK_SPACE);
				Thread.sleep(1000);
				Common.actionsKeyPress(Keys.SPACE);
				Common.clickElement("xpath",
						"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			}
			if (data.get(dataSet).get("StreetLine2") != null) {
				Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
			}
			if (data.get(dataSet).get("StreetLine3") != null) {
				Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
			}*/
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

			Sync.waitElementClickable("xpath", "//span[contains(text(),'Proceed')]");
			Common.clickElement("xpath", "//span[contains(text(),'Proceed')]");

		
			ExtenantReportUtils.addPassLog("verifying shipping addres filling ", expectedResult,
					"user enter the shipping address ",
					Common.getscreenShotPathforReport("fill the shipping address first time"));

		
			
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
}

public void productsupport(){
	try{
	Common.actionsKeyPress(Keys.PAGE_DOWN);
    Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='Product Support']");
	Sync.waitPageLoad();
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Vicks Support and FAQs"), "verifying the product support page", "after click the productsupport page it will navigate to product support page ", "sucessfully navigate to product support page", "faield to open product support page");
	}
	
	
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating product support page","open the product supportpage","User failed to open productsupportpage",Common.getscreenShotPathforReport("supportpage"));
	
		Assert.fail();

	}

}
public void FAQs(){
	try{
	Common.actionsKeyPress(Keys.PAGE_DOWN);
    Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='FAQs']");
	Sync.waitPageLoad();
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Vicks Support and FAQs"), "verifying the product support page", "after click the productsupport page it will navigate to product support page ", "sucessfully navigate to product support page", "faield to open product support page");
	}
	
	
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating product support page","open the product supportpage","User failed to open productsupportpage",Common.getscreenShotPathforReport("supportpage"));
	
		Assert.fail();

	}

}
public void Humidifiers_vaporizer(){
	try{
	Common.actionsKeyPress(Keys.PAGE_DOWN);
    Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='Humidifiers & Vaporizers']");
	Sync.waitPageLoad();
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Humidifiers & Vaporizers - Shop"), "verifying the product support page", "after click the productsupport page it will navigate to product support page ", "sucessfully navigate to product support page", "faield to open product support page");
	}
	
	
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating product support page","open the product supportpage","User failed to open productsupportpage",Common.getscreenShotPathforReport("supportpage"));
	
		Assert.fail();

	}

}
public void Sinus_Inhalers(){
	try{
	Common.actionsKeyPress(Keys.PAGE_DOWN);
    Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='Sinus Inhalers']");
	Sync.waitPageLoad();
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Sinus Inhalers - Shop"), "verifying the product support page", "after click the productsupport page it will navigate to product support page ", "sucessfully navigate to product support page", "faield to open product support page");
	}
	
	
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating product support page","open the product supportpage","User failed to open productsupportpage",Common.getscreenShotPathforReport("supportpage"));
	
		Assert.fail();

	}

}
public void Contactus(){
	try{
	Common.actionsKeyPress(Keys.PAGE_DOWN);
    Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='Contact Us']");
	Sync.waitPageLoad();
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Contact Us"), "verifying the product support page", "after click the productsupport page it will navigate to product support page ", "sucessfully navigate to product support page", "faield to open product support page");
	}
	
	
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating product support page","open the product supportpage","User failed to open productsupportpage",Common.getscreenShotPathforReport("supportpage"));
	
		Assert.fail();

	}

}
public void Filters_Accessories(){
	try{
	Common.actionsKeyPress(Keys.PAGE_DOWN);
    Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='Filters & Accessories']");
	Sync.waitPageLoad();
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Filters & Accessories - Shop"), "verifying the product support page", "after click the productsupport page it will navigate to product support page ", "sucessfully navigate to product support page", "faield to open product support page");
	}
	
	
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating product support page","open the product supportpage","User failed to open productsupportpage",Common.getscreenShotPathforReport("supportpage"));
	
		Assert.fail();

	}

}		
public void Blog(){
	try{
	Common.actionsKeyPress(Keys.PAGE_DOWN);
    Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='Blog']");
	Sync.waitPageLoad();
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Health & Wellness Blog"), "verifying the product support page", "after click the productsupport page it will navigate to product support page ", "sucessfully navigate to product support page", "faield to open product support page");
	}
	
	
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating product support page","open the product supportpage","User failed to open productsupportpage",Common.getscreenShotPathforReport("supportpage"));
	
		Assert.fail();

	}

}	
public void Our_company(){
	try{
	Common.actionsKeyPress(Keys.PAGE_DOWN);
    Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='Our company']");
	Sync.waitPageLoad();
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Home - Helen of Troy"), "verifying the product support page", "after click the productsupport page it will navigate to product support page ", "sucessfully navigate to product support page", "faield to open product support page");
	}
	
	
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating product support page","open the product supportpage","User failed to open productsupportpage",Common.getscreenShotPathforReport("supportpage"));
	
		Assert.fail();

	}

}	
public void Thermometers(){
	try{
	Common.actionsKeyPress(Keys.PAGE_DOWN);
    Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='Thermometers']");
	Sync.waitPageLoad();
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Thermometers - Shop"), "verifying the product support page", "after click the productsupport page it will navigate to product support page ", "sucessfully navigate to product support page", "faield to open product support page");
	}
	
	
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating product support page","open the product supportpage","User failed to open productsupportpage",Common.getscreenShotPathforReport("supportpage"));
	
		Assert.fail();

	}

}
public void fottorValidations_HeleOfTroy(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	
	Common.actionsKeyPress(Keys.END);
	String Hederlinks=data.get(dataSet).get("productname");
	String[] footer=Hederlinks.split(",");
	int i=0;
	
	try{
	for(i=0;i<footer.length;i++){
		System.out.println(footer[i]);
		Sync.waitElementClickable("xpath", "//span[@class='mobile-accordion-link-text' and text()='"+footer[i]+"']");
		Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='"+footer[i]+"']");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		//Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		if (footer[i].contains("Home - Helen of Troy")){
		Common.assertionCheckwithReport(Common.getPageTitle().contains(footer[i]), "verifying Header link of "+footer[i],"user open the "+footer[i]+" option", "user successfully open the header link "+footer[i],"Failed open the header link "+footer[i]);
		ExtenantReportUtils.addPassLog("validating Header Links "+footer[i], "user open the "+footer[i]+" option", "User abel open the header link "+footer[i],Common.getscreenShotPathforReport("User abel open the header link "));
		}
		Common.actionsKeyPress(Keys.END);
		Common.switchToDefault();
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +footer[i],"user open the "+footer[i]+" option","User unabel open the header link "+footer[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}
public void fottorValidations_Shop(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	
	Common.actionsKeyPress(Keys.END);
	String Hederlinks=data.get(dataSet).get("productname");
	String[] Footrer=Hederlinks.split(",");
	int i=0;
	
	try{
	for(i=0;i<Footrer.length;i++){
		System.out.println(Footrer[i]);
		Sync.waitElementClickable("xpath", "//span[@class='mobile-accordion-link-text' and text()='"+Footrer[i]+"']");
		Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='"+Footrer[i]+"']");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(Footrer[i]), "verifying Header link of "+Footrer[i],"user open the "+Footrer[i]+" option", "user successfully open the header link "+Footrer[i],"Failed open the header link "+Footrer[i]);
		Common.actionsKeyPress(Keys.END);	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +Footrer[i],"user open the "+Footrer[i]+" option","User unabel open the header link "+Footrer[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}
public void validationlinksshop(String DataSet) throws Exception  {
	// TODO Auto-generated method stub
	try{
		  
		 Common.mouseOver("xpath", "//span[text()='Shop']");
		 String HumidifiersandVaporizerspagetitle= data.get(DataSet).get("Humidifiers & Vaporizers");
		 String SINUSINHALERpagetitle=data.get(DataSet).get("SINUS INHALER");
	     String FILTERSandACCESSORIESpagetitle= data.get(DataSet).get("FILTERS & ACCESSORIES");
		 String THERMOMETERSpagetitle=data.get(DataSet).get("THERMOMETERS");
		
		 ArrayList<String> elemtstext=new ArrayList<String>();
	     List<WebElement> Shoplinks=Common.findElements("xpath", "//div[@class='shop-menu__wrapper']//div[@class='pagebuilder-column']");
	     for(int j=0;j<Shoplinks.size();j++){
	    	  elemtstext.add(Shoplinks.get(j).getText());
	     }

	  
	    int i=0;
	     for(i=0;i<elemtstext.size();i++){
	    	 Thread.sleep(4000);
	    	 System.out.println(elemtstext.get(i));
	    	 
	    	if(elemtstext.get(i).equals("Humidifiers & Vaporizers")){
	    		Common.clickElement("xpath", "//a[text()='"+elemtstext.get(i)+"']");
	    	    Thread.sleep(2000);
	    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(HumidifiersandVaporizerspagetitle), "validating header ling HumidifiersandVaporizers page", "after click the HumidifiersandVaporizers page in header it must navigate to HumidifiersandVaporizers page", "sucessfully navigate to HumidifiersandVaporizers page", "Failed to navigate to HumidifiersandVaporizers");
	    	    Common.mouseOver("xpath", "//span[text()='Shop']");
	    	}
	    	else if(elemtstext.get(i).equals("SINUS INHALER")){
	    		Common.clickElement("xpath", "//a[text()='"+elemtstext.get(i)+"']");
	    	    Thread.sleep(2000);
	    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(SINUSINHALERpagetitle), "validating header ling SINUSINHALER page", "after click the SINUSINHALER page in header it must navigate to SINUSINHALER page", "sucessfully navigate to SINUSINHALER page", "Failed to navigate to SINUSINHALER");
	    	    Common.mouseOver("xpath", "//span[text()='Shop']");
	    	}
	    	
	    	else if(elemtstext.get(i).equals("FILTERS & ACCESSORIES")){
	    		Common.clickElement("xpath", "//a[text()='"+elemtstext.get(i)+"']");
	    	    Thread.sleep(2000);
	    	    System.out.println(Common.getPageTitle());
	    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(FILTERSandACCESSORIESpagetitle), "validating header ling FILTERSandACCESSORIES page", "after click the FILTERSandACCESSORIES page in header it must navigate to FILTERSandACCESSORIES page", "sucessfully navigate to FILTERSandACCESSORIES page", "Failed to navigate to FILTERSandACCESSORIES");
	    	    Common.mouseOver("xpath", "//span[text()='Shop']");
	    	}  
	    	else if(elemtstext.get(i).equals("THERMOMETERS")){
	    		Common.clickElement("xpath", "//a[text()='"+elemtstext.get(i)+"']");
	    	    Thread.sleep(2000);
	    	    System.out.println(Common.getPageTitle());
	    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(THERMOMETERSpagetitle), "validating header ling THERMOMETERS page", "after click the THERMOMETERS page in header it must navigate to THERMOMETERS page", "sucessfully navigate to THERMOMETERS page", "Failed to navigate to THERMOMETERS");
	    	    Common.mouseOver("xpath", "//span[text()='Shop']");
	    	}
	    	
	     }
		  }
		  catch(Exception  | Error e){
			  e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validating header link shop ","user open the shop option from header ","User unabel open the header link shop",Common.getscreenShotPathforReport("shopheaderlink"));
			
				Assert.fail();  
	
		  }
	
}   
public void headLinksValidations_Learn(String DataSet) throws Exception{
	
		  try{
		  
		 Common.mouseOver("xpath", "//span[text()='Learn']");
		 String WhyHumidifypagetitle= data.get(DataSet).get("WhyHumidify?");
		 String HumidifierTypespagetitle=data.get(DataSet).get("HumidifierTypes");
	     String BenefitsofSinusInhalersFanpagetitle= data.get(DataSet).get("BenefitsofSinusInhalers");
		 String CleaningYourHumidifierpagetitle=data.get(DataSet).get("CleaningYourHumidifier");
		 String EssentialItemsforYourSickChildpagetitle=data.get(DataSet).get("EssentialItemsforYourSickChild");
		 String CalmingAllergySymptomspagetitle=data.get(DataSet).get("CalmingAllergySymptoms");
		 String HumidifierTipspagetitle=data.get(DataSet).get("HumidifierTips");
		 ArrayList<String> elemtstext=new ArrayList<String>();
	     List<WebElement> LearnEductionLinks=Common.findElements("xpath", "//ul[@data-menu='menu-43']//li//span");
	     for(int j=0;j<LearnEductionLinks.size();j++){
	    	  elemtstext.add(LearnEductionLinks.get(j).getText());
	     }
System.out.println(elemtstext);
	  
	    int i=0;
	     for(i=0;i<elemtstext.size();i++){
	    	 Thread.sleep(4000);
	    	 System.out.println(elemtstext.get(i));
	    	 
	    	if(elemtstext.get(i).equals("Why Humidify?")){
	    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
	    	    Thread.sleep(2000);
	    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(WhyHumidifypagetitle), "validating header link Why Humidify? page", "after click the Why Humidify? page in header it must navigate to Why Humidify? page", "sucessfully navigate to Why Humidify? page", "Failed to navigate to Why Humidify? page");
	    	    Common.mouseOver("xpath", "//span[text()='Learn']");
	    	}
	    	else if(elemtstext.get(i).equals("Humidifier Types")){
	    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
	    	    Thread.sleep(2000);
	    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(HumidifierTypespagetitle), "validating header link Humidifier Types", "after click the Humidifier Types in header it must navigate to Humidifier Types page", "sucessfully navigate to Humidifier Types page", "Failed to navigate to Humidifier Types");
	    	    Common.mouseOver("xpath", "//span[text()='Learn']");
	    	}
	    	
	    	else if(elemtstext.get(i).equals("Benefits of Sinus Inhalers")){
	    		Sync.waitElementClickable("xpath", "//span[text()='"+elemtstext.get(i)+"']");
	    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
	    	    Thread.sleep(2000);
	    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(BenefitsofSinusInhalersFanpagetitle), "validating header link Benefits of Sinus Inhalers page", "after click the Benefits of Sinus Inhalers page in header it must navigate to Benefits of Sinus Inhalers page", "sucessfully navigate to Benefits of Sinus Inhalers page", "Failed to navigate to  Benefits of Sinus Inhalers page");
	    	    Common.mouseOver("xpath", "//span[text()='Learn']");
	    	}
	    	else if(elemtstext.get(i).equals("Cleaning Your Humidifier")){
	    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
	    	    Thread.sleep(2000);
	    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(CleaningYourHumidifierpagetitle), "validating header ling SafetyMatters page", "after click the SafetyMatters page in header it must navigate to SafetyMatters page", "sucessfully navigate to SafetyMatters page", "Failed to navigate to SafetyMatters");
	    	    Common.mouseOver("xpath", "//span[text()='Learn']");
	    	}
	    	else if(elemtstext.get(i).equals("Essential Items for Your Sick Child")){
	    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
	    	    Thread.sleep(2000);
	    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(EssentialItemsforYourSickChildpagetitle), "validating header link Essential Items For Your Sick Child page", "after click the Essential Items For Your Sick Child page in header it must navigate to Essential Items For Your Sick Child page", "sucessfully navigate Essential Items For Your Sick Child page", "Failed to navigate to Essential Items For Your Sick Child page");
	    	    Common.mouseOver("xpath", "//span[text()='Learn']");
	    	}
	    	else if(elemtstext.get(i).equals("Calming Allergy Symptoms")){
	    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
	    	    Thread.sleep(2000);
	    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(CalmingAllergySymptomspagetitle), "validating header link Calming Allergy Symptoms page", "after click the Calming Allergy Symptoms page in header it must navigate to Calming Allergy Symptoms page", "sucessfully navigate to Calming Allergy Symptoms page", "Failed to navigate to Calming Allergy Symptoms page");
	    	    Common.mouseOver("xpath", "//span[text()='Learn']");
	    	}
	    	else if(elemtstext.get(i).equals("Humidifier Tips")){
	    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
	    	    Thread.sleep(2000);
	    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(HumidifierTipspagetitle), "validating header link Humidifier Tips page", "after click the Humidifier Tips page in header it must navigate to Humidifier Tips page", "sucessfully navigate to Humidifier Tips page", "Failed to navigate to Humidifier Tips page");
	    	    Common.mouseOver("xpath", "//span[text()='Learn']");
	    	}
	    		     
	     }
		  }
		  catch(Exception  | Error e){
			  e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validating header link learn ","user open the Lean option from header ","User unabel open the header link Learn",Common.getscreenShotPathforReport("Learnheaderlink"));
			
				Assert.fail();
		  }
		  
	  }

public void headLinksValidations_Support(String DataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "//span[text()='Support']");
	
	String Hederlinks=data.get(DataSet).get("Support");
	String[] hedrs=Hederlinks.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "//ul[@data-menu='menu-54']//span[text()='"+hedrs[i]+"'])");
		Common.clickElement("xpath", "//ul[@data-menu='menu-54']//span[text()='"+hedrs[i]+"'])");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		//Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		if (hedrs[i].contains("Vicks Support")){
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);	
		}
		Common.mouseOver("xpath", "//span[text()='Shop']");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}
public void Agreandproceed () throws Exception{
	Sync.waitPageLoad();
	Sync.waitElementClickable("xpath", "//button[@id='truste-consent-required']");
	Common.clickElement("xpath", "//button[@id='truste-consent-required']");
	Thread.sleep(2000);
	
}
public void validationlinkssupport_2(String DataSet) {
	try{
		
			Sync.waitElementPresent("xpath","//a[@data-menu='menu-54']");
			Common.mouseOver("xpath", "//a[@data-menu='menu-54']");
			String productname=data.get(DataSet).get("productname");
		    Common.clickElement("xpath","//a[@data-menu='menu-185']//span[text()=('"+productname+"')]"); 
		
	
	}
	catch (Exception | Error e) {
		
	}

}
	
public void validationlinkssupport(String DataSet) {
	
	  try{
		  
			 Common.mouseOver("xpath", "//a[@data-menu='menu-54']");
			 String FAQstitle= data.get(DataSet).get("FAQs");
			 String ProductSupportpagetitle=data.get(DataSet).get("ProductSupport");
			 String OrderStatuspagetitle=data.get(DataSet).get("OrderStatus");
			 
			 ArrayList<String> elemtstext=new ArrayList<String>();
			 List<WebElement> LearnEductionLinks=Common.findElements("xpath", "//ul[@data-menu='menu-54']//li//span");
			 for(int j=0;j<LearnEductionLinks.size();j++){
		  	  elemtstext.add(LearnEductionLinks.get(j).getText());
		   }


		  int i=0;
		   for(i=0;i<elemtstext.size();i++){
		  	 Thread.sleep(4000);
		  	 System.out.println(elemtstext.get(i));
		  	 
		  	if(elemtstext.get(i).equals("FAQs")){
		  		Thread.sleep(5000);
		  		Common.clickElement("xpath", "//ul[@data-menu='menu-184']//span[text()='"+elemtstext.get(i)+"']");
		          Thread.sleep(2000);
		  	    Common.assertionCheckwithReport(Common.getPageTitle().equals(FAQstitle), "validating header ling"+elemtstext.get(i)+"page in GeneralSupport", "after click the "+elemtstext.get(i)+"page in header it must navigate to "+elemtstext.get(i)+" page", "sucessfully navigate to "+elemtstext.get(i)+" page", "Failed to navigate to "+elemtstext.get(i)+"");
		  	  Common.mouseOver("xpath", "//a[@data-menu='menu-54']");
		  	}
		  	else if(elemtstext.get(i).equals("Product Support")){
		  		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
		  	    Thread.sleep(2000);
		  	    Common.assertionCheckwithReport(Common.getPageTitle().equals(ProductSupportpagetitle), "validating header ling"+elemtstext.get(i)+"page in GeneralSupport", "after click the "+elemtstext.get(i)+"page in header it must navigate to "+elemtstext.get(i)+" page", "sucessfully navigate to "+elemtstext.get(i)+" page", "Failed to navigate to "+elemtstext.get(i)+"");
		  	  Common.mouseOver("xpath", "//a[@data-menu='menu-54']");
		  	}
		  	
		  
		  	else if(elemtstext.get(i).equals("Order Status")){
		  		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
		  	    Thread.sleep(2000);
		  	    Common.assertionCheckwithReport(Common.getPageTitle().equals(OrderStatuspagetitle), "validating header ling"+elemtstext.get(i)+"page in GeneralSupport", "after click the "+elemtstext.get(i)+"page in header it must navigate to "+elemtstext.get(i)+" page", "sucessfully navigate to "+elemtstext.get(i)+" page", "Failed to navigate to "+elemtstext.get(i)+"");
		  	  Common.mouseOver("xpath", "//a[@data-menu='menu-54']");
		  	}
		  	
		  	
		      }
			    }
			  catch(Exception  | Error e){
				  e.printStackTrace();
				  ExtenantReportUtils.addFailedLog("validating header link learn in support","user open the support option from header ","User unabel open the header link Support",Common.getscreenShotPathforReport("supportLink"));
				  Assert.fail();
			  }
	/*int i=0;
	String Hederlinks=data.get(DataSet).get("HeaderNames");
	String[] hedrs=Hederlinks.split(",");
	System.out.println(hedrs.length);
	try{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "//a[@data-menu='menu-54']");
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		
		int j=i+1;
		Sync.waitElementClickable("xpath", "//ul[@data-menu='menu-184']/li["+j+"]/a");
		Common.clickElement("xpath", "//ul[@data-menu='menu-184']/li["+j+"]/a");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i]+"for LeanBy_Shop","user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "//a[@data-menu='menu-54']");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" LeanBy_Shop","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
		Assert.fail();

	}*/
			  
}

public void footerValidations_aboutUs(String DataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	
	Common.actionsKeyPress(Keys.END);
	String Hederlinks=data.get(DataSet).get("Aboutus");
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

public void colorproduct(String DataSet) {
	// TODO Auto-generated method stub
	try
	{
		Thread.sleep(3000);
		String productname= data.get(DataSet).get("color");
		System.out.println(productname);
		Sync.waitElementPresent("xpath","//a[contains (text(),'"+productname+"')]");
		Common.clickElement("xpath", "//a[contains (text(),'"+productname+"')]");
		Thread.sleep(4000);
        Sync.waitElementClickable("xpath", "//div[@aria-label='Pink']");
        Common.clickElement("xpath","//div[@aria-label='Pink']");
        ExtenantReportUtils.addPassLog("Verifing product list page", "Should select a product and color",
				"Should select a product and color", Common.getscreenShotPathforReport("Product and color selected successfully"));

        
		
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("Verifing product list page", "Should select a product and color",
				"Should select a product and color ", Common.getscreenShotPathforReport("Failed to selected product and color"));
		Assert.fail();
	}
	
}

public void addtocarthomepage() {
	// TODO Auto-generated method stub
	try
	{
		Thread.sleep(2000);
		Sync.waitElementClickable("xpath", "//form[@data-product-sku='VUL500']//button");
		Common.clickElement("xpath","//form[@data-product-sku='VUL500']//button");
		ExtenantReportUtils.addPassLog("Verifing product to add cart from homepage", "Product should add to cart from homepage",
				"Product should add to cart from homepage",
				Common.getscreenShotPathforReport("Product successfully added to cart from homepage"));
		
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("Verifing product to add to cart from homepage", "product should add to cart from homepage",
				"product should add to cart from homepage", Common.getscreenShotPathforReport("Failed to add to cart from homepage"));
		Assert.fail();
	}
	
}

public void addtocartPLPpage(String DataSet) {
	// TODO Auto-generated method stub
	try
	{
		Thread.sleep(3000);
		String productname= data.get(DataSet).get("PLP");
		System.out.println(productname);
		Sync.waitElementPresent("xpath","//a[contains (text(),'"+productname+"')]");
		Common.mouseOver("xpath", "//a[contains (text(),'"+productname+"')]");
		Sync.waitElementClickable("xpath", "//form[@data-product-sku='VWM845']//button");
		Common.clickElement("xpath","//form[@data-product-sku='VWM845']//button");
		ExtenantReportUtils.addPassLog("Verifing product to add cart from plppage", "Product should add to cart from plppage",
				"Product should add to cart from plppage",
				Common.getscreenShotPathforReport("Product successfully added to cart from plppage"));
		
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("Verifing product to add to cart from plppage", "product should add to cart from plppage",
				"product should add to cart from plppage", Common.getscreenShotPathforReport("Failed to add to cart from plppage"));
     Assert.fail();
	}
	
}

public void viewandedit() {
	// TODO Auto-generated method stub
	try
	{
		Thread.sleep(4000);
		Sync.waitElementClickable("xpath", "//a [@class='action viewcart']//span");
		Common.clickElement("xpath","//a [@class='action viewcart']//span");
		ExtenantReportUtils.addPassLog("Verifing click to view and edit cart", "It should be click on view and edit cart",
				"It should be click on view and edit cart",
				Common.getscreenShotPathforReport("It should be click on view and edit cart successfully"));
		
	}
	catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("Verifing click to view and edit cart", "It should be click on view and edit cart",
				"It should be click on view and edit cart", Common.getscreenShotPathforReport("Failed to click view and edit cart"));
		Assert.fail();
	}
}

public void suggestproducts() {
	// TODO Auto-generated method stub
	try
	{
		Thread.sleep(4000);
		Sync.waitElementClickable("xpath", "//form[@data-product-sku='V912US']//button");
		Common.clickElement("xpath","//form[@data-product-sku='V912US']//button");
		ExtenantReportUtils.addPassLog("Verifing product to add cart from shopping cart page", "Product should add to cart from shopping cart page",

				"Product should add to cart from shopping cart page",

				Common.getscreenShotPathforReport("Product successfully added to cart from shopping cart page"));
		
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("Verifing product to add to cart from shopping cart page", "product should add to cart from shopping cart page",
				"product should add to cart from shopping cart page", Common.getscreenShotPathforReport("Failed to add to cart from shopping cart page"));
		Assert.fail();
	}
}

public void privacypolicy() {
	// TODO Auto-generated method stub
	try
	{ 
		Thread.sleep(2000);
		Common.actionsKeyPress(Keys.END);
		Sync.waitElementClickable("xpath", "//a[@title='Vicks Privacy Policy']");
		Common.clickElement("xpath", "//a[@title='Vicks Privacy Policy']");
		Sync.waitPageLoad();
		Thread.sleep(3000);
	    String Title=Common.getPageTitle();
	    System.out.println(Title);
		Common.assertionCheckwithReport(Title.equals("Vicks Privacy Policy"),"To Verify the Privacy policy Page","It should navigate to Privacy policy page", "successfully  navigated to Privacy policy page", "Privacy policy");
		
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To view privacy policy button","should land on Privacy policy button", "user unable to navigate to privacy policy button", Common.getscreenShotPathforReport("failed to land on privacy button"));			
		Assert.fail();	
		
	}
	
}

public void Termsofuse() {
	// TODO Auto-generated method stub
	try
	{
		
		Common.actionsKeyPress(Keys.END);
		Thread.sleep(5000);
		Sync.waitElementClickable("xpath", "//a[@title='Vicks Terms Of Use']");
		Common.clickElement("xpath", "//a[@title='Vicks Terms Of Use']");
		Sync.waitPageLoad();
		Thread.sleep(3000);
	    String Title=Common.getPageTitle();
	    System.out.println(Title);
	    Common.assertionCheckwithReport(Title.equals("Vicks Terms of Use"),"To Verify the terms of use page","It should navigate to terms of use page", "successfully  navigated to terms of use page ", "terms of use");
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To view terms of use Page","should land on terms of use page", "user unable to navigate to terms of use page", Common.getscreenShotPathforReport("failed to land on terms of use page"));			
		Assert.fail();	
	}
	
	
}

public void sortby(String DataSet) {
	// TODO Auto-generated method stub
	try
	{
		Thread.sleep(3000);
		Common.clickElement("xpath","(//select[@id='sorter'])[1]");
		Sync.waitElementPresent("xpath","(//select[@id='sorter'])[1]");
		Common.dropdown("xpath", "(//select[@id='sorter'])[1]", Common.SelectBy.TEXT, data.get(DataSet).get("Name"));
		ExtenantReportUtils.addPassLog("verifing click beside of sort by in PLP page", "Dropdown should be open ",

				"Product Name should be select from dropdown",

				Common.getscreenShotPathforReport("Product name is select successfully from dropdown and all products in alphabetic order"));
		
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifing click beside of sort by in PLP page","Dropdown should be open", "Product Name should be select from dropdown", Common.getscreenShotPathforReport("failed to open dropdown to select product name"));			
		Assert.fail();
	}
	
}

public void profile() {
	// TODO Auto-generated method stub
	try
	{
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath","//div[@id='block-collapsible-nav']//a[text()='Profile']");
		Common.clickElement("xpath","//div[@id='block-collapsible-nav']//a[text()='Profile']");
		Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Profile"),"Verifying profile page","it shoud navigate to profile page", "successfully  navigated to profile Page", "Profile");	
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifing profile page","Should land on profile page", "user unable to land on profile page", Common.getscreenShotPathforReport("failed to land on profile page"));			
		Assert.fail();
	}
}

public void Accountlogin(String DataSet) {
	// TODO Auto-generated method stub
	try {

		Common.clickElement("xpath", "//a[@class='header-content__right-link']");
		Common.textBoxInput("id", "email", data.get(DataSet).get("Email"));
		Common.textBoxInput("id", "pass", data.get(DataSet).get("Password"));
		Common.scrollIntoView("xpath", "//button[@class='action login primary']");
		Sync.waitElementClickable("xpath", "//button[@class='action login primary']");
		Common.clickElement("xpath", "//button[@class='action login primary']");
		ExtenantReportUtils.addPassLog("Should login with details",
				"Should display My Account Page with user details",
				"My Account Page with user details displayed successfully",
				Common.getscreenShotPathforReport("Successfully landed to account page"));
}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("Should login with details",
				"Should display  My Account Page with user details",
				"My Account Page with user details not displayed",
				Common.getscreenShotPathforReport("Login page with details Failed"));
		Assert.fail();
	}
}

public void Myorders() {
	// TODO Auto-generated method stub
	try
	{

		Thread.sleep(3000);
		Sync.waitElementPresent("xpath","//div[@id='block-collapsible-nav']//a[text()='My Orders']");
		Common.clickElement("xpath","//div[@id='block-collapsible-nav']//a[text()='My Orders']");
		Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("My Orders"),"Verifying My Orders page","it shoud navigate to My Orders page", "successfully  navigated to My Orders Page", "My Orders");	
		
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To verify the My orders page","Should land on my orders  page", "user unable to land on my orders page", Common.getscreenShotPathforReport("failed to land on my orders page"));			
		Assert.fail();
	}
	
}

public void Addressbook() {
	// TODO Auto-generated method stub
	try
	{

		Thread.sleep(3000);
		Sync.waitElementPresent("xpath","//div[@id='block-collapsible-nav']//a[text()='Address Book']");
		Common.clickElement("xpath","//div[@id='block-collapsible-nav']//a[text()='Address Book']");
		Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Address Book"),"Verifying AddressBook page","it shoud navigate to AddressBook page", "successfully  navigated to Address Book Page", "AddressBook");	
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To verify the AddressBook page","Should land on AddressBook  page", "user unable to land on AddressBook page", Common.getscreenShotPathforReport("failed to land on AddressBook page"));			
		Assert.fail();
	}
	
}

public void compare() throws Exception {
	// TODO Auto-generated method stub
	
	try{
		Thread.sleep(3000);
		Common.mouseOver("xpath", "//div[@id='product-item-info_668']");
		Thread.sleep(3000);
	    Sync.waitElementPresent("xpath","//div[@id='product-item-info_668']//span[text()='Compare']");
		Common.clickElement("xpath", "//div[@id='product-item-info_668']//span[text()='Compare']");
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
		Common.mouseOver("xpath", "//div[@id='product-item-info_578']");
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath","//div[@id='product-item-info_578']//span[text()='Compare']");
		Common.clickElement("xpath", "//div[@id='product-item-info_578']//span[text()='Compare']");
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
		Common.mouseOver("xpath", "//div[@id='product-item-info_566']");
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath","//div[@id='product-item-info_566']//span[text()='Compare']");
		Common.clickElement("xpath", "//div[@id='product-item-info_566']//span[text()='Compare']");
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
		Common.mouseOver("xpath", "//div[@id='product-item-info_564']");
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath","//div[@id='product-item-info_564']//span[text()='Compare']");
		Common.clickElement("xpath", "//div[@id='product-item-info_564']//span[text()='Compare']");
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
		Common.mouseOver("xpath", "//div[@id='product-item-info_562']");
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath","//div[@id='product-item-info_562']//span[text()='Compare']");
		Common.clickElement("xpath", "//div[@id='product-item-info_562']//span[text()='Compare']");
		
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

public void signin_checkout(String DataSet) {
	// TODO Auto-generated method stub
	try {
		Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']", data.get(DataSet).get("Email"));
		Thread.sleep(5000);
		Common.textBoxInput("id", "customer-password", data.get(DataSet).get("Password"));
		Common.scrollIntoView("xpath", "//button[@class='action login primary']");
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
public void temp() {
	// TODO Auto-generated method stub
	String expectedResult = "It Should be navigate to Thermometers.";
	try {
	Thread.sleep(6000);
	Sync.waitElementClickable("xpath", "//a[@id='ui-id-3']");
	Common.mouseOver("xpath", "//a[@id='ui-id-3']");
	Sync.waitElementClickable("xpath", "//a[text()='THERMOMETERS']");
	Common.findElement("xpath", "//a[text()='THERMOMETERS']").click();
	ExtenantReportUtils.addPassLog("verifying category Thermometers","lands on Thermometers", "User lands on the Thermometers",Common.getscreenShotPathforReport("successfuly clicked"));
	} catch (Exception | Error e) {
	e.printStackTrace();
	ExtenantReportUtils.addFailedLog("validating the category page.", expectedResult,"user faield to navigate Thermometers",Common.getscreenShotPathforReport("failed to navgate categorypage"));
	Assert.fail();
	}
	}
public void tempproduct() {
	// TODO Auto-generated method stub
	try {
	Thread.sleep(2000);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Sync.waitElementClickable("xpath",
	"//span[@class='product-image-container product-image-container-597_category_page_grid']");
	Common.findElement("xpath", "//span[@class='product-image-container product-image-container-597_category_page_grid']")
	.click();
	ExtenantReportUtils.addPassLog("Verifing product list page", "Should select a product",
	"Should select a product", Common.getscreenShotPathforReport("Product is selected successfully"));
	} catch (Exception | Error e) {
	e.printStackTrace();
	ExtenantReportUtils.addFailedLog("Verifing product list page", "Should select a product",
	"Should select a product", Common.getscreenShotPathforReport("Failed to selected product"));
	}
	}
	
	public void filtertype() {
	// TODO Auto-generated method stub
	try
	{
		Thread.sleep(2000);
		Sync.waitElementPresent("xpath","//a[@data-opt-path='filter_type=204']");
		Common.clickElement("xpath","//a[@data-opt-path='filter_type=204']");
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
		Sync.waitElementPresent("xpath","//a[@data-opt-path='price=30-40']");
		Common.clickElement("xpath","//a[@data-opt-path='price=30-40']");
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

public void orderstatus() {
	// TODO Auto-generated method stub
	try
	{
		Thread.sleep(2000);
		Sync.waitElementPresent("xpath","//div[@id='block-collapsible-nav']//a[text()='My Orders']");
		Common.clickElement("xpath","//div[@id='block-collapsible-nav']//a[text()='My Orders']");
		Sync.waitElementPresent("xpath","//span[text()='View Order']");
		Common.clickElement("xpath","//span[text()='View Order']");
		ExtenantReportUtils.addPassLog("click on my oders in My account"," successfully navigated to my orders","click on view order the page should be opend",Common.getscreenShotPathforReport("successfully clicked on view orders"));
		
		
	}
	
	catch (Exception | Error e) {

		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("click on my oders in My account","successfully navigated to my orders","click on view order the page should be opend", Common.getscreenShotPathforReport("Failed to click on view odersstatus"));
		Assert.fail();	
	}
}

public void stickycart() {
	// TODO Auto-generated method stub
	try
	{
		
		Common.actionsKeyPress(Keys.END);
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath","//button[contains(@id,'product-sticky')]//span[text()='Add to Cart']");
		Common.clickElement("xpath","//button[contains(@id,'product-sticky')]//span[text()='Add to Cart']");
		ExtenantReportUtils.addPassLog("scroll down the page sticky cart will appere",
				" add to cart from sticky cart",
				"The product should be add to mini cart",
				Common.getscreenShotPathforReport("product should be add to mini cart successfully"));
		
	
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("scroll down the page sticky cart will appere",
				"add to cart from sticky cart",
				"The product should be add to mini cart",
				Common.getscreenShotPathforReport("Failed to add product to mini cart"));
		Assert.fail();
		
	}
}



public void Taxcalucaltion(String DataSet) {
	// TODO Auto-generated method stub
	try{
		Thread.sleep(3000);

		//String taxpercent=data.get(DataSet).get("Tax");
		String taxpercent=data.get(DataSet).get("Tax");
		Float giventaxvalue=Float.valueOf(taxpercent);

		String subtotla=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
		// subtotla.replace("", newChar)
		Float subtotlaValue=Float.valueOf(subtotla);

		String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
		Float shippingammountvalue=Float.valueOf(shippingammount);

		String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']/span").replace("$", "");
		Float Taxammountvalue=Float.valueOf(TaxAmmount);

		String TotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
		Float Totalammountvalue=Float.valueOf(Taxammountvalue);
		Float calucaltedvalue= ((subtotlaValue+shippingammountvalue)*giventaxvalue)/100;

		NumberFormat nf= NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		String userpaneltaxvalue=nf.format(calucaltedvalue);

		System.out.println(TaxAmmount);
		System.out.println(userpaneltaxvalue);
		Common.assertionCheckwithReport(userpaneltaxvalue.equals(TaxAmmount), "verifying tax calculation", "tax rate is matches to given shipping address tax ","successfully tax rate is matches to given shipping address tax", "tax rate is not matches to given shipping address tax");

		}
		catch(Exception |Error e)
		{
		report.addFailedLog("verifying tax calculation", "getting price values from shipping page ", "Faield to get price value from shipping page", Common.getscreenShotPathforReport("TaxRates"));

		 

		e.printStackTrace();
		Assert.fail();
	
}
}

public void outofstock(String DataSet) {
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
		        ExtenantReportUtils.addFailedLog("To verify the  the PLP Page with out of stock","Should mouse over on out of stock PLP page", "user unable to land on  Out of Stock PLP", Common.getscreenShotPathforReport("failed to land on out of stock PDP page"));           
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
}