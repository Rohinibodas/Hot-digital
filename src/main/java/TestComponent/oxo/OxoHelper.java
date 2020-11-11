package TestComponent.oxo;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import TestLib.Common;
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
	
	
	
	public void clickBaby_Toddler(){
		String expectedResult="It Should be navigate to the selected category page.";
		try{
		Sync.waitElementClickable("xpath", "//a[contains(text(),'Baby & Toddler')]");
		Common.clickElement("xpath", "//a[contains(text(),'Baby & Toddler')]");
		Common.clickElement("xpath", "//a[contains(text(),'Baby & Toddler')]");
	String s=	Common.getPageTitle();
	System.out.println(s);
		}
       catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("validating the category page.", expectedResult, "user faield to navigate",  Common.getscreenShotPathforReport("faield to navgate categorypage"));
			//ExtenantReportUtils.addFailedLog("User click check out button", "User unabel click the checkout button", Common.getscreenShotPathforReport("check out miniCart"));
			Assert.fail();
			
		}
	}
	
	
	public void Selectproduct() throws Exception{
		
		
		Sync.waitElementPresent("xpath", "//a[contains(text(),'Splash')]");
		Common.clickElement("xpath", "//a[contains(text(),'Splash')]");
		
		
		Sync.waitElementPresent("xpath", "//div[@id='overview']/div[3]//h1/span");
		
		
		String Text=Common.getText("xpath", "//div[@id='overview']/div[3]//h1/span");
		
		System.out.println(Text);
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
	
	public void loginOxo(String dataSet) throws Exception
	{
		Thread.sleep(3000);
		Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
		Common.findElement("xpath", "//a[@class='social-login']").click();
		Sync.waitElementClickable(30, By.id("email"));
		if(Common.findElement("id", "email")==null)
		{
			Common.mouseOverClick("xpath", "//a[@class='social-login']");
			Thread.sleep(2000);
		}
		Common.textBoxInput("id", "email",data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "pass1",data.get(dataSet).get("Password"));
		Common.clickElement("id", "bnt-social-login-authentication");
		
	}
	
	public void creditCard_payment(String dataSet) throws Exception{
		
		try{
			Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
			Thread.sleep(2000);
			Common.switchFrames("id", "paymetric_xisecure_frame");
			int size=Common.findElements("xpath", "//select[@id='c-ct']").size();
			Common.switchToDefault();
			Common.assertionCheckwithReport(size>0, "veridying Creditcard option", "click the creadit card label", "clicking credit card label and open the card fields", "user faield to open credit card form");
			}
		   catch(Exception |Error e) {
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
		Common.clickElement("xpath", "//button[@title='Place Order']");
		
		String expectedResult="credit card fields are filled with the data";
	    String errorTexts=	Common. findElement("xpath", "//div[contains(@id,'error')]").getText();
	    Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data", expectedResult, "Filled the Card detiles", "missing field data it showinng error");
	    	
		
		}
		catch(Exception |Error e) {
		    ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", "", "faield  to fill the Credit Card infromation",  Common.getscreenShotPathforReport("Cardinfromationfail"));
			//ExtenantReportUtils.addFailedLog("User click check out button", "User unabel click the checkout button", Common.getscreenShotPathforReport("check out miniCart"));
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
		Common.clickElement("xpath", "//div[contains(@class,'paypal-button-container')]");
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
    		ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult, "User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
    	    Assert.fail();
          }
	}
	
	
public void closetheadd() throws Exception{
		
        Thread.sleep(20000);
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
