package TestComponent.DryBar;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.testng.Assert;

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
	Common.clickElement("xpath", "//span[text()='Create an Account']");
	ExtenantReportUtils.addPassLog("verifying Create Account button", "It should lands on Create New Customer from Account form Page", "user  lands on Customer Account creation form Page", Common.getscreenShotPathforReport("createaccount"));
	
	}
	 catch(Exception |Error e) {
	        ExtenantReportUtils.addFailedLog("verifying Create Account button", "It should lands on Create New Customer from Account form Page", "user faield lands on Account form Page", Common.getscreenShotPathforReport("createaccount"));
			Assert.fail();
		}
		
	
	
	try{
	
	Common.textBoxInput("id", "firstname",data.get(dataSet).get("FirstName"));
	
	Common.textBoxInput("id", "lastname",data.get(dataSet).get("LastName"));
	
	
	Common.clickElement("xpath", "//input[@id='is_subscribed']");
	
	Common.clickElement("xpath", "//input[@id='assistance_allowed_checkbox']");
	
	Common.textBoxInput("id", "email_address",data.get(dataSet).get("Email"));
	
	Common.textBoxInput("id", "password",data.get(dataSet).get("Password"));
	
	Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
	
	Common.clickElement("xpath", "//button[@title='Create an Account']");
	
	}
	 catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Create Account from", "Account should be created successfully navigate to My Account page", "user faield to create account", Common.getscreenShotPathforReport("createaccountfaield"));
			Assert.fail();
		}
	
}


public void clickHairProducts(){
	try{
	Common.clickElement("xpath", "//span[text()='Hair Products']");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Hair Products Drybar | Drybar"), "verifying Hair Product category","User navigate to hair product page","user successfully open the Hair Category page", "user faield to click the Hair Product");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Hair Product category", "User navigate to hair product page", "user faield to click the Hair Product", Common.getscreenShotPathforReport("hairproduct"));
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
	
	Common.clickElement("id", "product-addtocart-button");
	
	ExtenantReportUtils.addPassLog("verifying add to cart button", "User click add to card button", "user successfully click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
	}
	catch(Exception |Error e) {
	   
		ExtenantReportUtils.addFailedLog("verifying add to cart button", "User click add to card button", "user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
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
		Thread.sleep(3000);
	 Sync.waitElementPresent("id", "top-cart-btn-checkout");
	   Common.findElement("id", "top-cart-btn-checkout").click();
	   
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
	
	select_USPS_StandardGround_shippingMethod();
    Thread.sleep(5000);
	
	Common.clickElement("xpath","//span[text()='Next']");
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
     	Sync.waitElementPresent("xp	ath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']");
     	Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']",data.get(dataSet).get("CompanyName"));
     	Sync.waitElementPresent("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='street[0]']");
     	Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='street[0]']", data.get(dataSet).get("Street"));
     	Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='city']", data.get(dataSet).get("City"));
     	Common.dropdown("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
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
			
			Sync.waitElementPresent("xpath", "//td[text()='USPS - Standard Ground']");
		    Common.clickElement("xpath", "//td[text()='USPS - Standard Ground']");
			

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
    Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data", expectedResult, "Filled the Card detiles", "missing field data it showinng error");
    	
	
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
   
	   int size= Common.findElements("id", "okta-signin-username").size();
	   Common.assertionCheckwithReport(size>0, "verifying login page", "open the login page", "successfully open the login page", "Failed open the login page");
	   try {
			Common.textBoxInput("id", "okta-signin-username", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "okta-signin-password", data.get(dataSet).get("Password"));
			Common.clickElement("id", "okta-signin-submit");
			Thread.sleep(6000);
			
			String gettext=Common.findElement(By.xpath("//h1[@class='page-title']/span")).getText();
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
			Common.actionsKeyPress(Keys.SPACE);
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

			Sync.waitElementClickable("xpath", "//span[contains(text(),'Continue To Payment')]");
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
			Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
			
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
	Sync.waitElementPresent("xpath", "//span[text()='View and Edit Cart']");
	Common.clickElement("xpath", "//span[text()='View and Edit Cart']");
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
public void click_ContinueShopping(){
	
	Common.clickElement("xpath", "//a[@title='Continue Shopping']");
     System.out.println(Common.getPageTitle());
	
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
public void gitCard() throws Exception{
	try{
	Common.scrollIntoView("id", "block-giftcard-heading");
	Common.clickElement("id", "block-giftcard-heading");
	
	Common.textBoxInput("id","giftcard-code","123");
	
	Common.textBoxInput("id","giftcard-pin","123");
	
	
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
	Common.clickElement("xpath", "//a[@data-se='forgot-password']");
    Common.textBoxInput("id", "account-recovery-username","manoj@gm.com");
	Common.scrollToElementAndClick("xpath", "//a[text()='Send via Email']");
	Common.actionsKeyPress(Keys.PAGE_UP);
	int size=Common.findElements("xpath", "//p[contains(@class,'okta-form-subtitle')]").size();
	Common.assertionCheckwithReport(size>0, "validating forgot password","Email has been sent to given email with instructions on resetting your password.", "Successfully Email has been sent to given email with instructions on resetting your password.","Failed to send forgetpassword");
	}
	catch (Exception | Error e) {
        ExtenantReportUtils.addFailedLog("validating forgot password","Email has been sent to given email with instructions on resetting your password.","User faield to send forgetpassword",Common.getscreenShotPathforReport("forgetpassword"));
        Assert.fail();
        }
}


public void couponCode(){
	try{
	Common.clickElement("id","block-discount-heading");
    Common.textBoxInput("id", "discount-code","CouponCode");
    }
	catch (Exception | Error e) {
        ExtenantReportUtils.addFailedLog("validating forgot password","Email has been sent to given email with instructions on resetting your password.","User faield to send forgetpassword",Common.getscreenShotPathforReport("forgetpassword"));
        Assert.fail();
        }
}
public void order_Verifying() throws Exception{
	//Thread.sleep(10000);
	//Common.textBoxInput("id", "//textarea[contains(@id,'tt-c-comment-field')]","Ceate accounts test ");
	Thread.sleep(5000);
	String Text=Common.getText("xpath", "//h1[@class='page-title']/span");
	System.out.println(Text);
	
}


public void forgetpasswordPageValidation(String InvalidData){

		try{
			Common.clickElement("xpath", "//a[@data-se='forgot-password']");
		    Common.textBoxInput("id", "account-recovery-username",InvalidData);
			Common.scrollToElementAndClick("xpath", "//a[text()='Send via Email']");
			Common.actionsKeyPress(Keys.PAGE_UP);
			int errormessage=Common.findElements("xpath", "//span[contains(@class,'icon error')]/following::p[1]").size();
			
	Common.assertionCheckwithReport(errormessage>0, "Validating forget password from", "Enter in valid data it must dispaly error message", "User enter "+InvalidData+" in Forgetpassword field", "Failed to display error message ");		
	        }
		catch (Exception | Error e) {
	        ExtenantReportUtils.addFailedLog("validating forgot password form with  invalid data","I will showing error message","forgotpassword form faield dispaly  error message",Common.getscreenShotPathforReport("forgetpassword error message"));
	        Assert.fail();
	        }
	
}
public void hairProducts(){
/*	try{
	Sync.waitElementClickable("xpath", "//span[text()='Hair Products']");
	Common.clickElement("xpath", "//span[text()='Hair Products']");
	Common.assertionCheckwithReport(Common.getPageTitle().concat("Hair Products"), description, expectedResult, actualResult, FailedMessage);
	
	}
	catch(){
		
	}
}
*/	
}

}
