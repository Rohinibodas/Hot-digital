package TestComponent.oxo;

import java.util.HashMap;
import java.util.Map;

import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;

public class OxoHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();
	static ExtenantReportUtils report;

	/*public void navigateLoginPage()
	{
		
	}*/	
	
	public void CreateNewAccount(String dataSet) throws Exception
	{
		
		Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
		Common.findElement("xpath", "//a[@class='social-login']").click();
		Thread.sleep(3000);
		try {
		Sync.waitElementClickable(30, By.xpath("//span[text()='Create Account']"));
		}catch (Exception e) {
			if(Common.findElement("xpath", "//span[text()='Create Account']")==null)
			{
				Common.clickElement("xpath", "//a[@class='social-login']");
				Thread.sleep(2000);
			}
		}
		Common.clickElement("xpath", "//span[text()='Create Account']");
		Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("id", "email_address_create", data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "password-social", data.get(dataSet).get("Password"));
		Common.textBoxInput("id", "password-confirmation-social", data.get(dataSet).get("Password"));
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.mouseOverClick("id", "button-create-social");
		Common.actionsKeyPress(Keys.ESCAPE);
	
	}	
	
	
	public void searchProductAndAddtoCart(String productName) throws Exception
	{
		Thread.sleep(10000);
		Common.actionsKeyPress(Keys.ESCAPE);
		Common.clickElement("className", "search-tool");
		Sync.waitElementPresent("id", "search");
		if(Common.findElement("id", "search")==null)
		{
			Common.mouseOverClick("className", "search-tool");
			Thread.sleep(2000);
		}
		Common.textBoxInput("id", "search", productName);
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(3000);
		//Sync.waitElementClickable("xpath", "//ol[@class='products list items product-items']/li[1]/following::button[1]");
		Common.clickElement("xpath", "//ol[@class='products list items product-items']/li[1]/following::button[1]");
		/*if(Common.findElement("xpath", "//button[@title='Add to Cart']")!=null)
		{
			Common.clickCheckBox("xpath", "//button[@title='Add to Cart']");
		}*/
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ESCAPE);
		Thread.sleep(2000);
			
	}
	
	public void checkOut() throws Exception
	{
		
			Common.actionsKeyPress(Keys.ESCAPE);
		Common.mouseOver("className", "minicart-wrapper");
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		try{
		Common.mouseOverClick("id", "top-cart-btn-checkout");
		}catch (Exception e) {
			Common.actionsKeyPress(Keys.ESCAPE);
			Common.mouseOver("className", "minicart-wrapper");
			Common.mouseOverClick("id", "top-cart-btn-checkout");
		}
		Thread.sleep(4000);
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		
	}
	
	public void addDeliveryAddress(String dataSet) throws Exception
	{
		Sync.waitElementPresent("name", "street[0]");
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		Common.clickElement("xpath", "//*[contains(@name,'ko_unique_')]");
		Common.mouseOverClick("xpath", "//button[@class='button action continue primary']");
		Thread.sleep(3000);
		Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
		Thread.sleep(3000);
	}
	
	
	public void selectproduct(String productname){
		String expectedResult="It Should navigate to the product details page";
		try{
		Sync.waitElementClickable("xpath", "//a[contains(text(),'"+productname+"')]");
		Common.clickElement("xpath", "//a[contains(text(),'Perch Booster Seat with Straps')]");
		Common.assertionCheckwithReport(Common.getPageTitle().equals(productname), "validating the product details page", expectedResult, "sucessfully navigated to product details page", "faield to navigate product details page");
		}
		 catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("validating the product details page",expectedResult, "user faield to navigate product detiles page",  Common.getscreenShotPathforReport("faield product detiles page"));
				Assert.fail();
				
			}
		
	}
	public void  addproducts(String quantity) throws Exception{
		
		int elementsize=Common.findElements("xpath", "//input[@class='sf-stepper-input']").size();
		
		if(elementsize>0){
		try{	
		Common.textBoxInput("xpath", "//input[@class='sf-stepper-input']", quantity);
		Sync.waitElementPresent("xpath", "//input[@class='sf-stepper-input']");
		Common.clickElement("xpath","//button[@id='product-addtocart-button']");
		Sync.waitElementPresent("xpath", "//span[text()='close']");
		Common.clickElement("xpath", "//span[text()='close']");
		
		ExtenantReportUtils.addPassLog("validating the product to Cart", "user add the product to cart", "user successfully add the product to cart", Common.getscreenShotPathforReport("usertheproducttocart"));
		}
		catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("validating the product to Cart","user add the product to cart", "user faield to add the product to cart",Common.getscreenShotPathforReport("user faield to add the product to cart"));
			Assert.fail();
			
		}
		
		}
		else{
			try{	
			   // Sync.waitElementPresent("xpath", "//input[@class='sf-stepper-input']");
			    Sync.waitElementPresent("xpath", "//select[@id='qty']");
				Common.dropdown("xpath","//select[@id='qty']", Common.SelectBy.VALUE, quantity);
				Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
				Common.clickElement("xpath","//button[@id='product-addtocart-button']");
				ExtenantReportUtils.addPassLog("validating the product to Cart", "user add the product to cart", "user successfully add the product to cart", Common.getscreenShotPathforReport("user faield to add the product to cart"));
			}
			catch(Exception |Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the product to Cart","user add the product to cart", "user faield to add the product to cart",Common.getscreenShotPathforReport("user faield to add the product to cart"));
				e.printStackTrace();
				Assert.fail();
				
			}
	}
	}
		
		
    public void checkout() throws Exception{
    	Sync.waitElementPresent("xpath", "//a[@class='action showcart']");
    	Common.clickCheckBox("xpath", "//a[@class='action showcart']");
    	Sync.waitElementPresent("id", "top-cart-btn-checkout");
    	Common.clickElement("id", "top-cart-btn-checkout");
    }
		
	
	
	public void clickBaby_Toddler() throws Exception{
		String expectedResult="It Should be navigate to the Baby_Toddler category page.";
		try{
		Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15184']");
		Common.clickElement("xpath", "//a[@data-menu='menu-15184']");
		Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15186']");
		Common.mouseOverClick("xpath", "//a[@data-menu='menu-15186']");
		}
       catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("validating the category page.", expectedResult, "user faield to navigate Baby Toddler category",  Common.getscreenShotPathforReport("faield to navgate categorypage"));
			Assert.fail();
			
		}                
		selectproduct("Perch Booster Seat with Straps");
		
		
	}
	
	public void VerifyingShippingpage() throws Exception{
		
		String expectedResult="The page should contain the shipping address along  with the order summary with total price";
		try{
		Sync.waitForLoad();
		String Order=Common.getText("xpath", "//div[@class='opc-block-summary']/span");
		
		
		Thread.sleep(4000);
		
		int shippingsize=Common.findElements("xpath", "//li[@id='shipping']").size();
		//int shippingsize=Common.findElements("xapth","//li[@id='shipping']").size();
		
		
		String totalamount=Common.getText("xpath","//tr[@class='grand totals']/td/strong/span");
	
		
	Common.assertionCheckwithReport(Order.equals("Order Summary")&&shippingsize>0&&totalamount!=null, "Verifying Shippingpage", expectedResult, "page contain shipping address ordersummary totalprice", "page missing shipping ordersummary totalprice");
		
		//Assert.assertEquals(actual, expected);
	}
	catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("Verifying Shippingpage", expectedResult, "page missing shipping ordersummary totalprice",  Common.getscreenShotPathforReport("faieldsshippingpage"));
		Assert.fail();
		
	}  
	}
	public void ShippingAddress(String dataSet) throws Exception{
		VerifyingShippingpage();
		try{
		Sync.waitElementPresent("xpath", "//div[@id='shipping-new-address-form']//input[@name='firstname']");
		Common.textBoxInput("xpath", "//div[@id='shipping-new-address-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
		
		Sync.waitElementPresent("xp	ath", "//div[@id='shipping-new-address-form']//input[@name='lastname']");
		Common.textBoxInput("xpath", "//div[@id='shipping-new-address-form']//input[@name='lastname']",data.get(dataSet).get("LastName"));
		
		
		Sync.waitElementPresent("xpath", "//div[@id='shipping-new-address-form']//input[@name='firstname']");
		Common.textBoxInput("xpath", "//div[@id='shipping-new-address-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
		
		Sync.waitElementPresent("xp	ath", "//div[@id='shipping-new-address-form']//input[@name='company']");
		Common.textBoxInput("xpath", "//div[@id='shipping-new-address-form']//input[@name='company']",data.get(dataSet).get("CompanyName"));
		
        Sync.waitElementPresent("name", "street[0]");
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		
		Sync.waitElementPresent("xpath", "//input[@id='customer-email']");
		Common.textBoxInput("xpath","//input[@id='customer-email']", data.get(dataSet).get("Email"));
		//Common.clickElement("xpath", "//button[contains(@class,'save-address')]");
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
    public void selectGroundShippingMethod() throws Exception{
    	try{
    	Common.actionsKeyPress(Keys.PAGE_DOWN);
    	Sync.waitElementPresent("xpath", "//label[contains(@id,'bestway_tablerate')]");
		Common.clickCheckBox("xpath", "//label[contains(@id,'bestway_tablerate')]");
		Sync.waitElementPresent("xpath", "//button[contains(@class,'continue primary')]");
		Common.clickCheckBox("xpath", "//button[contains(@class,'continue primary')]");
		ExtenantReportUtils.addPassLog("verifying Shipping Methods", "user select the Ground shipping method", " selected the Ground shipping method", Common.getscreenShotPathforReport("faieldsshippingmethod"));
    	}
    	catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying Shipping Methods", "user select the Ground shipping method", "faield to select shippingmethod",Common.getscreenShotPathforReport("faieldsshippingmethod"));
			Assert.fail();
			
		} 
	}
    public void clickAcceptingaddress() throws Exception{
    	try{
    	Sync.waitElementPresent("xpath", "//button[contains(@class,'action-accept')]");
    	Common.clickElement("xpath", "//button[contains(@class,'action-accept')]");
    	}
    	catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying confirmation address", "user acceptance given address", "faield to acceptance given address",Common.getscreenShotPathforReport("faieldcceptance"));
			Assert.fail();
			
		} 
    	
    	
    	
    }
  
    
    public void Edit_BillingAddress(String dataSet)throws Exception{
    	try{
    	
    		
        Common.clickElement("xpath","//label[contains(@for,'billing-address-same-as-shipping-paymetric')]");
       //Common.clickElement("Xpath","//span[contains(text(),'My billing and shipping address are the same')]");
    	Sync.waitElementPresent("xpath", "//div[@class='payment-method-billing-address']//input[@name='firstname']");
		Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
		Sync.waitElementPresent("xp	ath", "//div[@class='payment-method-billing-address']//input[@name='lastname']");
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
		
		int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
	    Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying Billing addres filling ", "user will fill the all the billing address", "user fill the shipping address click save button", "faield to add new shipping address");
    }
    	catch(Exception |Error e) {
    		e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Billing addres filling", "user will fill the all the Billing address", "faield to add new billing address",Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
			Assert.fail();
			
		}  
    }
    public void Click_CreditCard(){
    	try{
    		Sync.waitPageLoad();
    		Common.actionsKeyPress(Keys.PAGE_DOWN);
             Common.clickElement("xpath", "//label[@for='paymetric']");
              ExtenantReportUtils.addPassLog("verifying CreditCardbutton", "user click CreditCard ", "user clicked CreditCard option",Common.getscreenShotPathforReport("creditoption"));
    	}
    	catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying CreditCardbutton", "user click CreditCard ", "faield to click CreditCard option",Common.getscreenShotPathforReport("creditfaeild"));
			Assert.fail();
			
		} 
    }
    
	public void Selectproduct() throws Exception{
		
		Sync.waitElementPresent("xpath", "//a[contains(text(),'Splash')]");
		Common.clickElement("xpath", "//a[contains(text(),'Splash')]");
		Sync.waitElementPresent("xpath", "//div[@id='overview']/div[3]//h1/span");
		String Text=Common.getText("xpath", "//div[@id='overview']/div[3]//h1/span");
		//Common.assertionCheckwithReport(Text.equals(""), "verifying product details page", "It Should navigate to the product details page", "successfully navigated to product details page", "faield to navigate to product details page");
		//div[@id="overview"]/div[3]//h1/span
		Common.clickElement("id", "product-addtocart-button");
		Common.clickElement("xpath", "//a[contains(text(),'Checkout Now')]");
	}
	
	
	
	public void addPaymentDetails(String dataSet) throws Exception
	{
		Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
		Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
		Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
		Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
		Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
		Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));	
	}
	
	/*public void loginOxo(String dataSet) throws Exception
	{
		Thread.sleep(3000);
		try{
		Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
		Common.findElement("xpath", "//a[@class='social-login']").click();
		Sync.waitElementClickable(30, By.id("email"));
		if(Common.findElement("id", "email")==null)
		{
			Common.mouseOverClick("xpath", "//a[@class='social-login']");
			Thread.sleep(2000);
		}
	

	}

    catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying login page with credentials",expectedResult, "User failed to login in account  ", Common.getscreenShotPathforReport("login faield"));
		Assert.fail();
    }
		
		Common.textBoxInput("id", "email",data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "pass1",data.get(dataSet).get("Password"));
		Common.clickElement("id", "bnt-social-login-authentication");
		
	}*/
	
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

	public void payPal_payment(String dataSet){
		String expectedResult="It should open paypal site window.";
		try{
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//input[@id='paypal_express']");
		Common.clickElement("xpath", "//input[@id='paypal_express']");
		Thread.sleep(5000);
    	Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.switchFrames("xpath", "//iframe[contains(@class,'zoid-component-frame')]"); 	
		Sync.waitElementClickable("xpath", "//div[contains(@class,'paypal-button-container')]");
		int sizes=Common.findElements("xpath", "//div[contains(@class,'paypal-button-container')]").size();
		
		System.out.println(sizes);
	//	Common.clickElement("xpath", "//div[contains(@class,'paypal-button-container')]");
		Common.switchToDefault();
		Thread.sleep(5000);
		Common.switchWindows();
		int size=Common.findElements("id", "acceptAllButton").size();
		if(size>0){
			
			Common.clickElement("id", "acceptAllButton");
			
		}
	
		
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
		int sizeemail=Common.findElements("id", "email").size();
		
		Common.assertionCheckwithReport(sizeemail>0, "verifying the paypal payment ", expectedResult, "open paypal site window", "faild to open paypal account");
		Common.clickElement("id", "btnLogin");
		Thread.sleep(5000);
		Common.actionsKeyPress(Keys.END);
		Thread.sleep(5000);
		Common.clickElement("id", "payment-submit-btn");
		Thread.sleep(8000);
		Common.switchToFirstTab();
		}
		catch(Exception |Error e) {
			e.printStackTrace();
    		ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult, "User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
    	    Assert.fail();
          }
	}
	
	
public void closetheadd() throws Exception{
	
        Thread.sleep(40000);
     // int elementsize=  Common.findElements("xpath", "//h1[contains(@id,'bubbleheader')]").size();
      
     
    	  //System.out.println(elementsize);
        
        Common.switchFrames("xpath", "//iframe[@id='attentive_creative']");
        Sync.waitElementPresent("xpath", "//button[@id='closeIconContainer']");
		Common.clickElement("xpath", "//button[@id='closeIconContainer']");
	    Common.switchToDefault();
    
	
	}

	
	public  OxoHelper(String datafile)
	{
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
}
