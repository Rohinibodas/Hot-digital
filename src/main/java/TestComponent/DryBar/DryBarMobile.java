package TestComponent.DryBar;



import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;


	public class DryBarMobile {

	

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();
	static ExtenantReportUtils report;

public DryBarMobile(String datafile){
	
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

/*public void order_Verifying() throws Exception{
	//Thread.sleep(10000);
	//Common.textBoxInput("id", "//textarea[contains(@id,'tt-c-comment-field')]","Ceate accounts test ");
	Thread.sleep(5000);
	String Text=Common.getText("xpath", "//h1[@class='page-title']/span");
	System.out.println(Text);
	
}*/

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
	//Common.clickElement("xpath", "//input[@id='assistance_allowed_checkbox']");
	Common.textBoxInput("id", "email_address",Common.genrateRandomEmail(data.get(dataSet).get("Email")));
	Common.textBoxInput("id", "password",data.get(dataSet).get("Password"));
	Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
	Common.clickElement("xpath", "//button[@title='Create an Account']");
	
	}
	 catch(Exception |Error e) {
		 e.printStackTrace();
	        ExtenantReportUtils.addFailedLog("verifying Create Account from", "Account should be created successfully navigate to My Account page", "user faield to create account", Common.getscreenShotPathforReport("createaccountfaield"));
			Assert.fail();
		}
	
}
public void clicktreebarmenu(){
	try{
	Sync.waitElementClickable("xpath", "//header[@class='page-header']/div[2]/a[1]");
	Common.clickElement("xpath", "//header[@class='page-header']/div[2]/a[1]");
   }
	 catch(Exception |Error e) {
	        ExtenantReportUtils.addFailedLog("verifying ThreeBar menu", "after click three bar menu is display the header option", "user faield to ThreeBar menu", Common.getscreenShotPathforReport("ThreeBarmenu"));
			Assert.fail();
		}
}
	

public void clickHairProducts(){
	try{
		
	Common.clickElement("xpath", "//a[@class='action nav-toggle main']");
	Common.clickElement("xpath", "//span[text()='Hair Products']");
	//Common.clickElement("xpath", "//a[@id='ui-id-15']");
	
	int sizes=Common.findElements("xpath", "//a[@id='ui-id-15']").size();
	if(sizes>0){
		
		Common.clickElement("xpath", "//a[@id='ui-id-15']");
	}
	System.out.println(Common.getPageTitle());
	
	Common.assertionCheckwithReport((Common.getPageTitle().contains("Hair Care Products")||Common.getPageTitle().contains("Hair")), "verifying Hair Product category","User navigate to hair product page","user successfully open the Hair Category page", "user faield to click the Hair Product");
	//sHair Care Products, Hair Styling Tools & Hair Salon Products | Drybar Drybar
	
	
	}
	catch(Exception |Error e) {
	     e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Hair Product category", "User navigate to hair product page", "user faield to click the Hair Product", Common.getscreenShotPathforReport("hairproduct"));
			Assert.fail();
		}
	
}
public void increaseProductQuantity(String Quantity) throws Exception{
	try{
		Sync.waitPageLoad();
		
		int size=Common.findElements("xpath", "//select[contains(@title,'Qty')][1]").size();
		
		if(size>0){
			Common.dropdown("xpath", "//select[contains(@title,'Qty')][1]",SelectBy.VALUE, Quantity);
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
public void Update_Cart() throws Exception{
	try{
	Sync.waitPageLoad();
	
	Common.clickElement("id", "update_cart_action");
	
	ExtenantReportUtils.addPassLog("verifying update shopping  button", "User click update shopping button", "user successfully click update shopping button", Common.getscreenShotPathforReport("Successfully clicked update shopping button"));
	}
	catch(Exception |Error e) {
	   
		ExtenantReportUtils.addFailedLog("verifying add to cart button", "User click add to card button", "user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
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
		Thread.sleep(5000);
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
    Common.assertionCheckwithReport(titletext.contains(productname), "verifying product detail page", "user navigate to product detailepage", "user successfully navigate to product detail page", "User Failed navigate to product detail page");
	
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
		 Sync.scrollDownToView("id", "top-cart-btn-checkout");
		 Common.clickElement("id", "top-cart-btn-checkout");
	   
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
		Thread.sleep(6000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(2000);
         Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
          ExtenantReportUtils.addPassLog("verifying PaymetricPaymentMethod", "user click PaymetricPaymentMethod ", "user clicked PaymetricPaymentMethod option",Common.getscreenShotPathforReport("PaymetricPaymentMethodoption"));
	}
	catch(Exception |Error e) {
		e.printStackTrace();
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
	
	//Common.clickElement("Xpath","//span[contains(text(),'My billing and shipping address are the same')]");
		public void select_USPS_StandardGround_shippingMethod() throws Exception{
			try{
				
				
				//td[text()='USPS - Standard Ground']
				
				Sync.waitElementPresent("xpath", "//input[@id='s_method_tablerate_bestway']");
			    Common.clickElement("xpath", "//input[@id='s_method_tablerate_bestway']");
			    Sync.scrollDownToView("xpath", "(//button[@type='submit'])[1]");
			    Sync.waitElementPresent("xpath", "(//button[@type='submit'])[1]");
			    Common.clickElement("xpath", "(//button[@type='submit'])[1]");
				

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

public void iPadMyAccount() throws InterruptedException{
	Thread.sleep(3000);	
	Sync.waitElementInvisible("xpath", "//a[@class='account-link top-link']");
	int name=Common.findElements("xpath", "//a[@class='account-link top-link']").size();		
	if(name>0){					
    Thread.sleep(3000);
	Sync.waitElementClickable("xpath", "//a[@class='account-link top-link']");
	Common.clickElement("xpath", "//a[@class='account-link top-link']");
	}
}

public void HomePage() throws InterruptedException {
	Thread.sleep(8000);
	
	Sync.waitPageLoad();
	String expectedResult = "User should land on the home page";
	int size =Common.findElements("xpath", "//a[@class='logo']").size();
	Common.assertionCheckwithReport(size > 0, " verifying the home page", expectedResult,"Successfully landed on the home page", "User unabel to land on home page");
	 Common.assertionCheckwithReport(size>0, "Successfully landed on th home page", expectedResult,"User unabel to land on home page");
     Thread.sleep(6000);
     
    // Thread.sleep(3000);		
 	int name=Common.findElements("xpath", "//a[@class='action nav-toggle main']").size();		
 	if(name>0){	
 		Thread.sleep(3000);
     //navigateMyAccount();
 	}
}
   
    
    public void navigateMyAccount() throws InterruptedException {
	 Thread.sleep(6000);
	 try {
		Sync.waitPageLoad();
		Sync.waitElementClickable(30, By.xpath("//a[@class='action nav-toggle main']"));
		Common.clickElement("xpath", "//a[@class='action nav-toggle main']");
		Thread.sleep(2000);
		try {
		Sync.waitElementClickable(30, By.xpath("//ul[@class='account-links']//li[2]/a"));
		}catch(TimeoutException e)
		{
			Common.clickElement("xpath", "//a[@class='action nav-toggle main']");
		}
		Common.findElement("xpath", "//ul[@class='account-links']//li[2]/a").click();
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
			Thread.sleep(3000);
			Common.clickElement("xpath", "//a[@class='action nav-toggle main']");
			Thread.sleep(2000);
			int totalSize=Common.findElements(By.xpath("(//ul[@class='account-menu top-link-submenu']/li[1]/a)[2]")).size();
			System.out.println(totalSize);
			Common.assertionCheckwithReport(totalSize>0, "verifying login account",
					"customer is redirected to My Account page",
					"Logged in the application and customer is redirected to My Account page",
					"Unabel to login Account");
	    
	   }
	   catch (Exception | Error e) {
		   e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying login page with credentials", expectedResult,
					"User failed to login in account  ", Common.getscreenShotPathforReport("login faield"));
			Assert.fail();

		}
	}
public void addDeliveryAddress_registerUser(String dataSet) throws Exception {

	String expectedResult = "shipping address is entering in the fields";
	//Common.scrollIntoView("xpath", "//span[contains(text(),'New Address')]");
	//Common.scrollIntoView("xpath", "(//button[@class='action action-show-popup'])" );
	Thread.sleep(3000);
	
	
    int size = Common.findElements(By.xpath("//span[contains(text(),'New Address')]")).size();
	if (size > 0) {

		try {
			//Common.scrollIntoView("xpath", "(//button[@class='action action-show-popup'])");
			Thread.sleep(3000);
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

public void Expediated_shippingmethod(){

    try {
    	Sync.waitElementPresent("xpath", "(//input[@class='radio'])[2]");
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
    try {
         // Common.clickElement("xpath", "(//button[@type='submit'])[2]");
          //Thread.sleep(4000);
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

public void Express_shippingmethod(){

    try {
    	
    	Sync.waitElementPresent("xpath", "(//input[@class='radio'])[3]");
    	Common.scrollIntoView("xpath", "(//input[@class='radio'])[3]");
         Common.clickElement("xpath", "(//input[@class='radio'])[3]");
          Thread.sleep(3000);
          ExtenantReportUtils.addPassLog("To verify the Express shipping method", "Should click on express shipping method", "user successfully clicked on Express shipping method", Common.getscreenShotPathforReport("Successfully clicked on Express shipping method"));
      	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
    }catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("To verify the Express shipping method","Should click on Express shipping method", "user unable to click on Express shipping method", Common.getscreenShotPathforReport("failed to click on express shipping method"));			
    		Assert.fail();	
    		}
    try {
         // Common.clickElement("xpath", "(//button[@type='submit'])[2]");
          //Thread.sleep(4000);
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
public void click_ContinueShopping(){
	
Sync.scrollSideToView("xpath","(//a[@title='Continue Shopping'])");
	
	Common.clickElement("xpath","(//a[@title='Continue Shopping'])");
	
	
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
	
	Thread.sleep(3000);
	Common.textBoxInput("id","giftcard-code","0123");
	
	Common.textBoxInput("id","giftcard-pin","0123");
	
	Thread.sleep(2000);
	Common.scrollIntoView("xpath", "//button[@value='Apply']");
	Common.clickElement("xpath", "//button[@value='Apply']");
	Thread.sleep(2000);
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
	Thread.sleep(3000);
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
	Common.clickElement("xpath", "//a[@class='action remind']/span[text()='Forgot Your Password?']");
	Thread.sleep(2000);
    Common.textBoxInput("id", "email_address","manoj@gm.com");
	//Common.scrollToElementAndClick("xpath", "//a[text()='Send via Email']");
	//Common.actionsKeyPress(Keys.PAGE_UP);
    Common.clickElement("xpath", "//span[text()='Reset My Password']");
	//int size=Common.findElements("xpath", "//p[contains(@class,'okta-form-subtitle')]").size();
	//Common.assertionCheckwithReport(size>0, "validating forgot password","Email has been sent to given email with instructions on resetting your password.", "Successfully Email has been sent to given email with instructions on resetting your password.","Failed to send forgetpassword");
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

public void MastercreditCard_payment(String dataSet) throws Exception{
	
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
	Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType2"));
	Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("mastercardnumber"));
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
    	
	Sync.waitPageLoad();
	}
	catch(Exception |Error e) {
	    ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", "credit card fields are filled with the data", "faield  to fill the Credit Card infromation",  Common.getscreenShotPathforReport("Cardinfromationfail"));
		Assert.fail();
		
	}
	
	
	
}

public void order_Verifying() {
	//Thread.sleep(10000);
	//Common.textBoxInput("id", "//textarea[contains(@id,'tt-c-comment-field')]","Ceate accounts test ");
	String expectedResult = "It redirects to order confirmation page";
	try{
	Thread.sleep(5000);
	
	
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
public void forgetpasswordPageValidation(String InvalidData){

	try{
		Common.clickElement("xpath", "//a[@class='action remind']");
	    Common.textBoxInput("xpath", "//input[@id='email_address']",InvalidData);
		//Common.scrollToElementAndClick("xpath", "//a[text()='Send via Email']");
		Common.clickElement("xpath", "//button[@class='action submit primary']");
		//Common.actionsKeyPress(Keys.PAGE_UP);
		int errormessage=Common.findElements("xpath", "//div[@id='email_address-error']").size();
		
      Common.assertionCheckwithReport(errormessage>0, "Validating forget password from", "Enter in valid data it must dispaly error message", "User enter "+InvalidData+" in Forgetpassword field", "Failed to display error message ");		
        }
	catch (Exception | Error e) {
		e.printStackTrace();
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

public void DiscovercreditCard_payment(String dataSet) throws Exception{
	
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
	Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("Discovercard"));
	Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("Discovercardnumber"));
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
    	
	Sync.waitPageLoad();
	}
	catch(Exception |Error e) {
	    ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", "credit card fields are filled with the data", "faield  to fill the Credit Card infromation",  Common.getscreenShotPathforReport("Cardinfromationfail"));
		Assert.fail();
		
	}
	
	
	
}
public void Aggree_and_proceed() {
	
	try{
		Sync.waitElementPresent("xpath", "(//button[@type='button'])[9]");
		Common.clickElement("xpath", "(//button[@type='button'])[9]");

        }
	catch (Exception | Error e) {
  
        Assert.fail();
        }

}
	



public void checkorderstatus_footerlink() {
	try{
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//a[@title='Check Order Status'])[2]");
		Sync.waitElementPresent("xpath", "(//span[@class='base'])");
		String Orderstatustitle=Common.findElement("xpath", "(//h1[@class='page-title'])[1]").getText();
		Orderstatustitle.equals("ONLINE ORDER");
		ExtenantReportUtils.addPassLog("To verify the order status footer link", "user should land on  order status footerlink page", "user successfully landed on order status foterlink page", Common.getscreenShotPathforReport("Successfully landed on order status page"));
		
		//Common.assertionCheckwithReport(Orderstatustitle.equals("ONLINE ORDER"), "verifying the order status footer link", "User should navigate to order status footerlink", "user successfully navigated to order status foterlink");
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying the order status footer link","user should navigate to order status footerlink", "user unable to navigate to  order status foterlink", Common.getscreenShotPathforReport("failed to land on checkorderstatus page"));			
			Assert.fail();	
			}
		}
public void Returns_footerlink() {
	try{
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//a[@title='Returns'])");
		Sync.waitElementPresent("xpath", "(//span[@class='base'])");
		String Returnstitle=Common.findElement("xpath", "(//h1[@class='page-title'])[1]").getText();
		Returnstitle.equals("RETURNS");
		ExtenantReportUtils.addPassLog("To verify the Returns footer link", "Should land on  returns footerlink page", "user successfully landed on returns foterlink page", Common.getscreenShotPathforReport("Successfully landed on Returns page"));
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the returns footer link","should land on returns footerlink page", "user unable to navigate to returns foterlink", Common.getscreenShotPathforReport("failed to land on returns page"));			
			Assert.fail();	
			}
		}

public void Shipping_Delivery_footerlink() {
	try{
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath","(//a[@title='Shipping & Delivery'])");
		Sync.waitElementPresent("xpath", "(//span[@class='base'])");
		String Shipping_title=Common.findElement("xpath", "(//h1[@class='page-title'])[1]").getText();
		Shipping_title=("SHIPPING");
		ExtenantReportUtils.addPassLog("To verify the Shipping footer link", "Should land on  shipping footerlink page", "user successfully landed on shipping foterlink page", Common.getscreenShotPathforReport("Successfully landed on shipping page"));
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the shipping footer link","should land on shipping footerlink page", "user unable to navigate to shipping foterlink", Common.getscreenShotPathforReport("failed to land on ahipping page"));			
			Assert.fail();	
			}
		}

public void Special_offers_footerlink() {
	try{
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//h2[@class='footer-links-title'])[2]");
		Thread.sleep(2000);
		Common.clickElement("xpath","(//a[@title='Special Offers'])[2]");
		Sync.waitElementPresent("xpath", "(//span[@class='base'])");
		String Specialoffertitle=Common.findElement("xpath", "(//h1[@class='page-title'])[1]").getText();
		Specialoffertitle.equals("SPECIAL OFFERS");
		ExtenantReportUtils.addPassLog("To verify the Special offer footer link", "Should land on  special offer footerlink page", "user successfully landed on special offer foterlink page", Common.getscreenShotPathforReport("Successfully landed on special offer page"));
		 
		//Common.assertionCheckwithReport(Orderstatustitle.equals("SPECIAL OFFERS"), "verifying the special offers footer link", "navigate to special offers footerlink", "user successfully navgate to special offers foterlink");
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify special offers footer link","should navigate to special offer footerlink", "user unable to navigate to special offers foterlink", Common.getscreenShotPathforReport("failed to  land on special offers page"));			
			Assert.fail();	
			}
		}

public void warranty_footerlink() {
	try{
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//h2[@class='footer-links-title'])[2]");
		Thread.sleep(2000);
		Common.clickElement("xpath","(//a[@title='Warranty'])");
		Sync.waitElementPresent("xpath", "(//span[@class='base'])");
		String title=Common.findElement("xpath", "(//h1[@class='page-title'])[1]").getText();
		title.equals("LIMITED WARRANTY");
		ExtenantReportUtils.addPassLog("To verify the Warranty footer link", "Should land on Warranty footerlink page", "user successfully landed on warranty foterlink page", Common.getscreenShotPathforReport("Successfully landed on warranty page"));
		//Common.assertionCheckwithReport(Orderstatustitle.equals("LIMITED WARRANTY"), "verifying the warranty footer link", "navigate to warranty footerlink", "user successfully navgate to warranty foterlink");
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the warranty footer link","Should navigate to warranty footerlink page", "userunable to  navigate to warranty foterlink", Common.getscreenShotPathforReport("failed to land on warranty page"));			
			Assert.fail();	
			}
		}

public void safetydata_footerlink() {
	try{
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath","(//a[@title='Safety Data Sheets'])");
		Sync.waitElementPresent("xpath", "(//span[@class='base'])");
		String title=Common.findElement("xpath", "(//h1[@class='page-title'])[1]").getText();
		title.equals("SAFETY DATA SHEETS (SDS)");
		ExtenantReportUtils.addPassLog("To verify the Safetydata footer link", "Should land on safety data footerlink page", "user successfully landed on safety data foterlink page", Common.getscreenShotPathforReport("Successfully landed on safety data page"));
		//Common.assertionCheckwithReport(Orderstatustitle.equals("SAFETY DATA SHEETS (SDS)"), "verifying the safety data sheets footer link", "navigate to data sheets footerlink", "user successfully navgate to data sheets foterlink");
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the safety data sheets footer link","Should navigate to safety data sheets footerlink", "user unable to navigate to safety data sheets foterlink", Common.getscreenShotPathforReport("failed to navigate to data sheets page"));			
			Assert.fail();	
			}
		}

public void Glossary_footerlink() {
	try{
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//h2[@class='footer-links-title'])[2]");	
		Thread.sleep(2000);
		Common.clickElement("xpath","(//a[@title='Glossary'])");
		
		Sync.waitElementPresent("xpath", "(//div[@class='definition_promo-title hero-title'])[1]");
		String Glossarytitle=Common.findElement("xpath", "(//div[@class='definition_promo-title hero-title'])").getText();
		Glossarytitle.equals("OUR GLOSSARY");
		ExtenantReportUtils.addPassLog("To verify the Glossary footer link", "Should land on Glossary footerlink page", "user successfully landed on Glossary foterlink page", Common.getscreenShotPathforReport("Successfully landed on warranty page"));
		
		//Common.assertionCheckwithReport(Glossarytitle.equals("OUR GLOSSARY"), "verifying the glossary footer link", "navigate to glossary footerlink", "user successfully navgate glossary foterlink");
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the glossary footer link","should navigate to glossary footerlink", "user unable to navigate to glossary foterlink", Common.getscreenShotPathforReport("failed to navigate to warranty page"));			
			Assert.fail();	
			}
		}

public void Blowout_footerlink() {
	try{
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//h2[@class='footer-links-title'])[2]");		
		Thread.sleep(2000);
		Common.clickElement("xpath","(//a[@tabindex='0'])[22]");
		Sync.waitElementPresent("xpath", "//*[@id=\"definition_promo-main-definition_promo_2\"]/div[2]/div[1]/h2/span");
		String Blowouttitle=Common.findElement("xpath", "//*[@id=\"definition_promo-main-definition_promo_2\"]/div[2]/div[1]/h2/span").getText();
		Blowouttitle.equals("BLOWOUT");
		ExtenantReportUtils.addPassLog("To verify the Blowout footer link", "Should land on blowout footerlink page", "user successfully landed on blowout foterlink page", Common.getscreenShotPathforReport("Successfully landed on Blowout page"));
		
		//Common.assertionCheckwithReport(Blowouttitle.equals("BLOWOUT"), "verifying the blowout footer link", "navigate to blowout footerlink", "user successfully navgate blowout foterlink");
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the blowout footer link","navigate to blowout footerlink", "user unable to  navigate to blowout foterlink", Common.getscreenShotPathforReport("failed to navigate to blowout page"));			
			Assert.fail();	
			}
		}

public void WheretoBuy_footerlink() {
	try{
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//h2[@class='footer-links-title'])[3]");		
		Thread.sleep(2000);
		Common.clickElement("xpath","(//a[@tabindex='0'])[27]");
		Sync.waitElementPresent("xpath", "//*[@id=\"definition_promo-main-definition_promo_2\"]/div[2]/div[1]/h2/span");
		String WheretoBuytitle=Common.findElement("xpath", "//*[@id=\"definition_promo-main-definition_promo_2\"]/div[2]/div[1]/h2/span").getText();
		WheretoBuytitle.equals("NEXT LEVEL LUXE");
		ExtenantReportUtils.addPassLog("To verify the Where to buy footer link", "Should land where to buy footerlink page", "user successfully landed where to buy foterlink page", Common.getscreenShotPathforReport("Successfully landed on where to buy footerlink page"));
		
		//Common.assertionCheckwithReport(WheretoBuytitle.equals("NEXT LEVEL LUXE"), "verifying the Buy footer link", "navigate to buy footerlink", "user successfully navgate WheretoBuy foterlink");
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying the where to Buy footer link","should navigate to where to Buy footerlink", "user unable navigate to buy foterlink", Common.getscreenShotPathforReport("failed to navigate to wheretoBuy footerlink page"));			
			Assert.fail();	
			}
		}

public void aboutUs_footerlink() {
	try{
		Thread.sleep(5000);
		Sync.waitPageLoad();
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])");
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//h2[@class='footer-links-title'])[3]");		
		Thread.sleep(2000);
		Common.clickElement("xpath","(//a[@tabindex='0'])[72]");
		Sync.waitElementPresent("xpath", "(//span[@class='base'])");
		Sync.waitPageLoad();
		String title=Common.findElement("xpath", "(//span[@class='base'])").getText();
		title.equals("ABOUT US");
		ExtenantReportUtils.addPassLog("To verify the About us footerlink", "Should land on about us footerlink page", "user successfully landed on about us foterlink page", Common.getscreenShotPathforReport("Successfully landed on about us footerlink page"));
		
		//Common.assertionCheckwithReport(Orderstatustitle.equals("ABOUT US"), "verifying the about us footer link", "navigate to about us footerlink", "user successfully navgate about us foterlink");
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the about us footer link","should navigate to about us footerlink", "userunable to navigate to about us foterlink", Common.getscreenShotPathforReport("failed to navigate to about us footerlinkpage"));			
			Assert.fail();	
			}
		}

/*public void careers_footerlink() {
	try{
		Thread.sleep(2000);
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath","(//a[@title='Careers'])");
		Sync.waitElementPresent("xpath", "(//span[@class='base'])");
		String Orderstatustitle=Common.findElement("xpath", "(//div[@class='definition_promo-title hero-title'])").getText();
		Common.assertionCheckwithReport(Orderstatustitle.equals("CAREERS - COME JOIN OUR TEAM"), "verifying the careers footer link", "navigate to careers footerlink", "user successfully navgate careers foterlink");
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying the careers footer link","navigate to careers footerlink", "user successfully navigate to careers foterlink", Common.getscreenShotPathforReport("failed to navigate to careers page"));			
			Assert.fail();	
			}
		}*/
	/*	Thread.sleep(3000);
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//a[@title='Returns'])");
		Thread.sleep(3000);
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//a[@title='Shipping & Delivery'])");
		Thread.sleep(3000);
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//a[@title='Safety Data Sheets'])");
		Thread.sleep(3000);
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//a[@title='Special Offers'])[2]");
		Thread.sleep(3000);
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//a[@title='Warranty'])");
		Thread.sleep(3000);
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//a[@title='Glossary'])");
		Thread.sleep(3000);
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//a[@title='About Us'])");
		Thread.sleep(3000);
		Common.scrollIntoView("xpath", "(//a[@title='Privacy & Security'])" );
		Thread.sleep(3000);
		Common.clickElement("xpath", "(//a[@title='Where to Buy'])[2]");
		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying footerlinks","navigate footerlink page","User faield to navigate to footerlinks",Common.getscreenShotPathforReport("confirmationmessage"));
	        Assert.fail();
	        }
			
	}


}*/

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
	    Common.scrollIntoView("xpath", "//*[@id=\"instant-search-results-container\"]/div/div/ol/li[1]/div/div/div[2]/a/span");
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

public void AmexcreditCard_payment(String dataSet) throws Exception{
	
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
	Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("Amexcard"));
	Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("Amexcardnumber"));
	Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
	Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
	Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("amexcvv"));	
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
    	
	Sync.waitPageLoad();
	}
	catch(Exception |Error e) {
	    ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", "credit card fields are filled with the data", "faield  to fill the Credit Card infromation",  Common.getscreenShotPathforReport("Cardinfromationfail"));
		Assert.fail();
		
	}
	
	
	
}
    

 public void Verify_PDP(){

    try {

Thread.sleep(2000);
Common.clickElement("xpath", "(//a[@class='see-more-link clearfix'])[1]");

/*Sync.waitElementPresent("xpath", "(//span[contains(text(), 'Hair Products')])[1]");

   Common.clickElement("xpath", "(//span[contains(text(), 'Hair Products')])[1]");
   Sync.waitElementPresent("xpath", "(//div[contains(text(), 'Shampoos')])[2]");
   Common.clickElement("xpath", "(//div[contains(text(), 'Shampoos')])[2]");
   Sync.waitElementPresent("xpath", "(//h2[@class='product name product-item-name'])[1]");
   Common.clickElement("xpath", "(//h2[@class='product name product-item-name'])[1]");*/
   Sync.waitPageLoad();
  // String Addtobag=Common.findElement("xpath","//button[@title='Add to Bag']").getAttribute("title");
  // Addtobag.equals("Add to Bag");
   ExtenantReportUtils.addPassLog("To verify the Product description page", "Should land on Product description page", "user successfully landed on product description page", Common.getscreenShotPathforReport("Successfully landed on PDP page"));
	
	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
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
		ExtenantReportUtils.addPassLog("verifying the search functionality","Should get the  product name in search field","user successfully Entered the product name in search field", Common.getscreenShotPathforReport("searched productname successfully"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog(" To verify the search functionality","should get the product name in search field along with suggested products","user successfully Entered the product name in search field", Common.getscreenShotPathforReport("Failed to search the product"));			
		Assert.fail();	
		}
try {
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(2000);
	    Common.scrollIntoView("xpath", "//*[@id=\"instant-search-results-container\"]/div/div/ol/li[1]/div/div/div[2]/a/span");
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
//String Page=Common.findElement("xpath", "/html/body/div[3]").getText();
Common.scrollIntoView("xpath", "(//span[@class='attribute-value'])[2]");
ExtenantReportUtils.addPassLog("To verify the Product description page with out of stock", "Should land on out of stock Product description page", "user successfully landed on out of stock product description page", Common.getscreenShotPathforReport("Successfully landed on  out of stock PDP page"));

	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the  the PDP Page with out of stock","Should land on ou of stock PDP page", "user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on out of stock PDP page"));			
		Assert.fail();	
		}
	}

public void My_Orders(){

    try {
    	Sync.waitElementPresent("xpath", "(//a[text()='My Orders'])");
        Thread.sleep(2000);
         Common.clickElement("xpath", "(//a[text()='My Orders'])");
          Thread.sleep(3000);
          String title=Common.findElement("xpath", "(//span[text()='My Orders'])").getText();
          title.equals("MY ORDERS");
         ExtenantReportUtils.addPassLog("To verify the my orders page", "Should land on a my  orders page", "user successfully landed on My orders page", Common.getscreenShotPathforReport("Successfully landed on my orders page"));

	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the My orders page","Should land on my orders  page", "user unable to land on my orders page", Common.getscreenShotPathforReport("failed to land on my orders page"));			
		Assert.fail();	
		}
	}
public void Wishlist(){

    try {
    	Sync.waitElementPresent("xpath", "(//a[text()='Wish List'])");
        Thread.sleep(2000);
         Common.clickElement("xpath", "(//a[text()='Wish List'])");
          Thread.sleep(3000);
          String title=Common.findElement("xpath", "(//span[text()='My Wish List'])").getText();
          title.equals("MY WISH LIST");
         ExtenantReportUtils.addPassLog("To verify the my wishlist page", "Should land on a my wishlist page", "user successfully landed on My wishlist page", Common.getscreenShotPathforReport("Successfully landed on my wishlist page"));

	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the My wishlist page","Should land on my wishlist  page", "user unable to land on my wishlist page", Common.getscreenShotPathforReport("failed to land on my wishlist page"));			
		Assert.fail();	
		}
	}
public void AddressBook(){

    try {
    	Sync.waitElementPresent("xpath", "(//a[text()='Address Book'])");
        Thread.sleep(2000);
         Common.clickElement("xpath", "(//a[text()='Address Book'])");
          Thread.sleep(3000);
          String title=Common.findElement("xpath", "(//span[text()='Address Book'])").getText();
          title.equals("ADDRESS BOOK");
         ExtenantReportUtils.addPassLog("To verify the AddressBook page", "Should land on a AddressBook page", "user successfully landed on AddressBook page", Common.getscreenShotPathforReport("Successfully landed on AddressBook page"));

	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the AddressBook page","Should land on AddressBook  page", "user unable to land on AddressBook page", Common.getscreenShotPathforReport("failed to land on AddressBook page"));			
		Assert.fail();	
		}
	}

public void AccountInformation(){

    try {
    	Sync.waitElementPresent("xpath", "(//a[text()='Account Information'])");
        Thread.sleep(2000);
         Common.clickElement("xpath", "(//a[text()='Account Information'])");
          Thread.sleep(3000);
          String title=Common.findElement("xpath", "(//span[text()='Account Information'])").getText();
          title.equals("EDIT ACCOUNT INFORMATION");
         ExtenantReportUtils.addPassLog("To verify the Account Information page", "Should land on a Account Information page", "user successfully landed on Account Information page", Common.getscreenShotPathforReport("Successfully landed on Account Information page"));

	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Account Information page","Should land on Account Information  page", "user unable to land on Account Information page", Common.getscreenShotPathforReport("failed to land on Account Information page"));			
		Assert.fail();	
		}
	}

public void PaymentMethods(){

    try {
    	Sync.waitElementPresent("xpath", "(//a[text()='Payment Methods'])");
        Thread.sleep(2000);
         Common.clickElement("xpath", "(//a[text()='Payment Methods'])");
          Thread.sleep(3000);
          String title=Common.findElement("xpath", "(//span[text()='Payment Methods'])").getText();
          title.equals("PAYMENT METHODS");
         ExtenantReportUtils.addPassLog("To verify the Payment Methods page", "Should land on a Payment Methods page", "user successfully landed on Payment Methods page", Common.getscreenShotPathforReport("Successfully landed on Payment Methods page"));

	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Payment Methods page","Should land on Payment Methods  page", "user unable to land on Payment Methods page", Common.getscreenShotPathforReport("failed to land on Payment Methods page"));			
		Assert.fail();	
		}
	}

public void Communication_Preferences(){

    try {
    	Common.clickElement("xpath", "(//ul[@class='account-menu top-link-submenu']/li[1]/a)[2]");
    	Sync.waitElementPresent("xpath", "(//a[text()='Communication Preferences'])");
        Thread.sleep(2000);
         Common.clickElement("xpath", "(//a[text()='Communication Preferences'])");
          Thread.sleep(3000);
          String title=Common.findElement("xpath", "(//span[text()='Communication Preferences'])").getText();
          title.equals("COMMUNICATION PREFERENCES");
         ExtenantReportUtils.addPassLog("To verify the Communication Preferences page", "Should land on a Communication Preferences page", "user successfully landed on Communication Preferences page", Common.getscreenShotPathforReport("Successfully landed on Communication Preferences page"));

	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Communication Preferences page","Should land on Communication Preferences  page", "user unable to land on Communication Preferences page", Common.getscreenShotPathforReport("failed to land on Communication Preferences page"));			
		Assert.fail();	
		}
	}
public void Subscribe_To_CP(){

    try {
    	Sync.waitElementPresent("id", "subscription");
         Common.clickElement("id", "subscription");
          Thread.sleep(3000);
          Common.clickElement("xpath", "(//button[@type='submit'])[1]");
          Thread.sleep(4000);
          String message=Common.findElement("xpath", "(//div[@class='message-success success message'])").getText();
          message.equals("We have saved your subscription.");
         ExtenantReportUtils.addPassLog("To verify the Communication Preferences page", "Should land on a Communication Preferences page", "user successfully landed on Communication Preferences page", Common.getscreenShotPathforReport("Successfully landed on Communication Preferences page"));

	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Communication Preferences page","Should land on Communication Preferences  page", "user unable to land on Communication Preferences page", Common.getscreenShotPathforReport("failed to land on Communication Preferences page"));			
		Assert.fail();	
		}
    
    
	}
public void UnSubscribe_To_CP(){

    try {
    	Sync.waitElementPresent("id", "subscription");
         Common.clickElement("id", "subscription");
          Thread.sleep(3000);
          Common.clickElement("xpath", "(//button[@type='submit'])[1]");
          Thread.sleep(4000);
          String message=Common.findElement("xpath", "(//div[@class='message-success success message'])").getText();
          message.equals("We have saved your subscription.");
        // ExtenantReportUtils.addPassLog("To verify the Communication Preferences page", "Should land on a Communication Preferences page", "user successfully landed on Communication Preferences page", Common.getscreenShotPathforReport("Successfully landed on Communication Preferences page"));

	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		//ExtenantReportUtils.addFailedLog("To verify the Communication Preferences page","Should land on Communication Preferences  page", "user unable to land on Communication Preferences page", Common.getscreenShotPathforReport("failed to land on Communication Preferences page"));			
		Assert.fail();	
		}
    
    
	}


}

