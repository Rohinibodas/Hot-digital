package TestComponent.Hydroflask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import TestLib.Common;
import TestLib.Sync;
import TestLib.Common.SelectBy;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;


public class HydroHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();
	 static ExtenantReportUtils report;
	

	public void navigateMyAccount() throws InterruptedException
	{	
		String expectedResult="User should land on the home page";
	    int size=Common.findElements("xpath", "//a[@class='logo']").size();
	    Common.assertionCheckwithReport(size>0, " verifying the home page", expectedResult, "Successfully landed on the home page", "User unabel to land on home page");
		//Common.assertionCheckwithReport(size>0, "Successfully landed on the home page", expectedResult,"User unabel to land on home page");
	  try{
	    Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
		Common.findElement("xpath", "//a[@class='social-login']").click();
		Thread.sleep(3000);
	  }
	  catch(Exception |Error e) {
  		ExtenantReportUtils.addFailedLog("verifying my account option","clcik the my account button", "User failed to clcik the my account button", Common.getscreenShotPathforReport("my account button"));
  		Assert.fail();
  		
  	}
		/*String expectedResult="Open login, register pop up";
		report.addPassLog(expectedResult,"click on the 'My Acount' link",Common.getscreenShotPathforReport("my account"));*/
	}

	public void acceptPrivecy()
	{
		Common.clickElementStale("xpath", "//button[text()='AGREE & PROCEED']");
	}
	public void CreateNewAccount(String dataSet) throws Exception
	{	
		
		    navigateMyAccount();
		   String expectedResult="opens Sign up pop up";
		
		
		
		try {
			Sync.waitElementClickable(30, By.xpath("//div[contains(text(),'Sign Up')]"));
			//Common.assertionCheckwithReport("", expectedResult, "");
		
			//report.addPassLog(expectedResult,"Successfully opeans Sign up pop_up page",Common.getscreenShotPathforReport("Successfully opeans Sign up pop_up page"));
			
			
		}catch (Exception e) {
			if(Common.findElement("xpath", "//div[contains(text(),'Sign Up')]")==null)
			{
				
				Common.clickElement("xpath", "//a[@class='social-login']");
				Thread.sleep(2000);
			}
		}
		
		String email=Common.genrateRandomEmail(data.get(dataSet).get("Email"));
		Common.clickElement("xpath", "//div[contains(text(),'Sign Up')]");
		
	
	
		
		//report.addPassLog(expectedResult,"Successfully opeans Sign up pop_up page",Common.getscreenShotPathforReport("Successfully opeans Sign up pop_up page"));
		//report.addPassLog("opens registration pop up",Common.getscreenShotPathforReport("register"));
		try{
		Common.textBoxInput("id", "social-login-popup-create-firstname", data.get(dataSet).get("FirstName"));
		expectedResult="opens Sign up pop up";
		int size= Common.findElements("id", "social-login-popup-create-firstname").size();
		
		Common.assertionCheckwithReport(size>0, "verifying sign up page ", expectedResult, "Successfully opeans Sign up pop up", "Faild to load the Sign popup");
		//Common.assertionCheckwithReport(size>0, "Successfully opeans Sign up pop_up page", expectedResult, "Faild to load the Sign popup");
		
		Common.textBoxInput("id", "social-login-popup-create-lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("id", "social-login-popup-create-email", email);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.textBoxInput("id", "social-login-popup-create-pass", data.get(dataSet).get("Password"));
		Common.textBoxInput("id", "password-confirmation-social", data.get(dataSet).get("Password"));
		Common.clickElement("id", "social-login-popup-create-is-subscribed");
		expectedResult="see the fields populated with the data";
	    ExtenantReportUtils.addPassLog("verifying sign up page with field data", expectedResult, "successfully fill the data in username email password",  Common.getscreenShotPathforReport("signup page issue"));
		Common.clickElement("xpath", "//button[@title='Sign Up']");
		int errormessagetextSize= Common.findElements("xpath", "//div[contains (@id,'error')]").size();
		if(errormessagetextSize<=0){
		}
		else{
			
			ExtenantReportUtils.addFailedLog("verifying sign up page with valid field data", expectedResult, "User failed to proceed signUp form", Common.getscreenShotPathforReport("signup issue"));
			//ExtenantReportUtils.addFailedLog("Sign usp popup with valid Data", "User failed to proceed signUp form ", Common.getscreenShotPathforReport("signup issue"));
		}
		
		}
		catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("verifying sign up page to Create new account","Sign up popup with valid Data", "User failed to proceed signUp form ", Common.getscreenShotPathforReport("signup issue"));
    		Assert.fail();
    		
    	}
		
	    Thread.sleep(2000);
	    
	    try{
		Sync.waitElementVisible("xpath", "//span[@data-ui-id='page-title-wrapper']");
		//Assert.assertEquals(Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']"), "My Account");
		expectedResult="it creates an account and logs in the user";
		String text=Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
		//Common.assertionCheckwithReport(text.equals("My Account"), "Successfully Created an account and logged in the application",expectedResult, "Unabel to create Account");
		
		Common.assertionCheckwithReport(text.equals("My Account"), "verifying new account creation confirmation ", expectedResult, "Successfully Created an account and logged in the application", "faield to Create New Account");		
		
		//report.addPassLog(expectedResult,"Successfully Created an account and logged in the application",Common.getscreenShotPathforReport("Successfully Created an account and logged in the application"));
		}
	    catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("verifying new account creation confirmation",expectedResult, "User failed to faield to Create New Account ", Common.getscreenShotPathforReport("signup faield"));
    		Assert.fail();
    		
    	}
		
			
	}
	
	public void loginHydroflaskAccount(String dataSet) throws Exception
	{
		Thread.sleep(3000);
		navigateMyAccount();
		String expectedResult="Opens login pop_up";
	
		Sync.waitElementClickable(30, By.id("social-login-popup-log-in-email"));
		if(Common.findElement("id", "social-login-popup-log-in-email")==null)
		{
			Common.clickElement("xpath", "//a[@class='social-login']");
			Thread.sleep(2000);
		}
		int size= Common.findElements("id", "social-login-popup-log-in-email").size();
	    
		Common.assertionCheckwithReport(size>0, "verifying  login pop up", expectedResult, "Successfully opeans Login pop up page", "Faild to load the Login pop up");
		//Common.assertionCheckwithReport(size>0, "Successfully opeans Login pop up page", expectedResult, "Faild to load the Login pop up");
	    
	    try{
	    Common.textBoxInput("id", "social-login-popup-log-in-email",data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "social-login-popup-log-in-pass",data.get(dataSet).get("Password"));
		
	    
	   
		expectedResult="see the fields populated with the data";
		int errormessagetextSize= Common.findElements("xpath", "//div[contains (@id,'error')]").size();
		Common.assertionCheckwithReport(errormessagetextSize<=0, "verifying login credentials", expectedResult, "Successfully Enter in the login data", "Required Field Data Missing");
		//(errormessagetextSize<=0, "Successfully Enter in the login data, email address and password", expectedResult,"Required Field Data Missing");
		
	
		Common.clickElement("id", "bnt-social-login-authentication");
		Thread.sleep(4000);
        Sync.waitElementPresent("xpath", "//span[@data-ui-id='page-title-wrapper']");
		//Assert.assertEquals(Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']"), "My Account");
		//expectedResult="it will successfully logs in and will see the customer name on the header and customer is redirected to 'My Account' page";
	         String text=Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
	 //   ExtenantReportUtils.addPassLog(expectedResult, "", Common.getscreenShotPathforReport(expectedResult));
	    Common.assertionCheckwithReport(text.equals("My Account"), "verifying login account", "customer is redirected to My Account page", "Logged in the application and customer is redirected to My Account page", "Unabel to login Account");
	    //(text.equals("My Account"), "Logged in the application and customer is redirected to My Account page",expectedResult, "Unabel to login Account");
	
		}
	
	    catch(Exception |Error e) {
 		ExtenantReportUtils.addFailedLog("verifying login page with credentials",expectedResult, "User failed to login in account  ", Common.getscreenShotPathforReport("login faield"));
 		Assert.fail();
 		
 	}
	}
	
		
		
		
	
/*	public void orderSubmit(String category) throws Exception
	{	
		report.addPassLog("Successfully landed on the home page",Common.getscreenShotPathforReport("Successfully landed on the home page"));
		Thread.sleep(5000);
		//Common.getDriver().switchTo().frame(0);
	//	Thread.sleep(2000);
		Sync.waitElementClickable("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]");
		System.out.println(Common.getText("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button"));
		Thread.sleep(4000);
		Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");	
		//Common.clickElement("css", "ul.megamenu-list > li:nth-of-type(1) > div:nth-of-type(1) > button");
		Thread.sleep(3000);
		try {
		Common.mouseOver("xpath", "//a[contains(text(),'"+category+"')]");
		
		report.addPassLog("click the"+category,Common.getscreenShotPathforReport("click the category as shop option as  "+category));
		}catch (Exception e) {
			// TODO: handle exception
			Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
			//report.addPassLog("click the option as shop in category",Common.getscreenShotPathforReport("click the category as shop "));		
			}
		report.addPassLog("Clicked shop option in category",Common.getscreenShotPathforReport("clicked shop option in category"));
		Thread.sleep(1000);
		Common.clickElement("xpath", "//a[contains(text(),'"+category+"')]");
		//Sync.waitElementVisible("xpath", "//div[text()='Drink in the adventure.']");
		Thread.sleep(4000);
		report.addPassLog("Selected the "+category+" category	",Common.getscreenShotPathforReport("click the category as shop option as  "+category));
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(4000);
		Sync.waitElementClickable("xpath", "//button[@title='Add to Cart']");
		Common.clickElement("xpath", "//button[@title='Add to Cart']");
		Thread.sleep(5000);
		report.addPassLog("Added Product to Cart",Common.getscreenShotPathforReport("Added Product to Cart"));
		}*/
	

	
	public void ClickCategory(){
		
	}
	
	
	
	public void orderSubmitt(String category){
		
	}
	
	
	
	
	public void orderSubmit(String category) throws Exception
	{
	    Thread.sleep(5000);
	 	String expectedResult="User should land on the home page";
	    int size=Common.findElements("xpath", "//a[@class='logo']").size();
	    Common.assertionCheckwithReport(size>0, "validating the home page ",expectedResult, "Successfully landed on the home page","User unabel to land on home page");
		//Common.assertionCheckwithReport(size>0, "Successfully landed on the home page", expectedResult,"User unabel to land on home page");
       
	    Sync.waitElementClickable("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]");
		Thread.sleep(4000);
		Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");	
		Thread.sleep(3000);
		expectedResult="User should click the"+category;
		try {
		Common.mouseOver("xpath", "//a[contains(text(),'"+category+"')]");
		}
		catch (Exception e) {
			Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
			}
		Thread.sleep(1000);
		Common.clickElement("xpath", "//a[contains(text(),'"+category+"')]");
		Thread.sleep(4000);
		expectedResult="User should select the "+category+"category";
		int sizebotteles=Common.findElements("xpath", "//a[contains(text(),'"+category+"')]").size();
		Common.assertionCheckwithReport(sizebotteles>0, "validating the product category as" +category+ "from navigation menu ", expectedResult, "Selected the "+category+" category", "User unabel to click"+category+"");
	    //Common.assertionCheckwithReport(sizebotteles>0, "Selected the "+category+" category", expectedResult,"User unabel to click"+category+"");
		
		try{
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(8000);
	    for(int i=0;i<=10;i++){
		   Thread.sleep(2000);
		   List<WebElement> webelementslist=Common.findElements("xpath", "//a[contains(@class,'product-colors-total-link')]");
		    String s = webelementslist.get(i).getAttribute("href");
		     if(s.isEmpty()){
			  
		     }
		     else{
		    	 break;
		     }
		   }
	    Sync.waitElementClickable("xpath", "//button[@title='Add to Cart']");
		Thread.sleep(4000);
	List<WebElement> element=Common.findElements("xpath", "//button[@title='Add to Cart']");
		
	     element.get(2).click();
	
	//	Common.clickElement("xpath", "//button[@title='Add to Cart']");
		//Common.assertionCheckwithReport(cartbuttonsize>0, "Added Product to Cart", expectedResult,"User unabel add product to cart");
		
		Thread.sleep(5000);
		
		
		String s=Common.getText("xpath", "//a[@aria-label='minicart']/following::span[3]");
		System.out.println();
		
        expectedResult="Product should add to Cart";
		
		int cartbuttonsize=Common.findElements("xpath", "//button[@title='Add to Cart']").size();
		Common.assertionCheckwithReport(cartbuttonsize>0, "validating the add product to cart", expectedResult, "Added Product to Cart", "User unabel add product to cart");
		//report.addPassLog(expectedResult,"Added Product to Cart",Common.getscreenShotPathforReport(expectedResult));
		}
catch(Exception |Error e) {
			
			ExtenantReportUtils.addFailedLog("validating the product add to cart", expectedResult, "User unabel to add product to cart",  Common.getscreenShotPathforReport("faield to add product"));
			//ExtenantReportUtils.addFailedLog("User click check out button", "User unabel click the checkout button", Common.getscreenShotPathforReport("check out miniCart"));
			Assert.fail();
			
		}
		}
	public void checkOut() throws Exception
	{
		String expectedResult ="it should land on the checkout intermediate page";
	    
		try{
		
		Common.clickElement("xpath", "//a[@aria-label='minicart']");
		Thread.sleep(3000);
		
		int size=Common.findElements("id", "top-cart-btn-checkout").size();
		
		ExtenantReportUtils.addPassLog("validating the product checkout", expectedResult, "User land Check out paga and click checkout button", Common.getscreenShotPathforReport("check out miniCart"));
		Common.clickElement("id", "top-cart-btn-checkout");
		
		
		//Common.assertionCheckwithReport(size>0, "Successfully Clicked the checkout button", expectedResult,"User unabel click the checkout button");
		
		
		//ExtenantReportUtils.addPassLog(expectedResult, "User land Check out paga and click checkout button", Common.getscreenShotPathforReport("check out miniCart"));
		//Sync.waitElementVisible("className", "checkout-step-title");
		Thread.sleep(3000);
		}
		catch(Exception |Error e) {
			
			ExtenantReportUtils.addFailedLog("validating the product checkout", expectedResult, "User unabel click the checkout button",  Common.getscreenShotPathforReport("check out miniCart"));
			//ExtenantReportUtils.addFailedLog("User click check out button", "User unabel click the checkout button", Common.getscreenShotPathforReport("check out miniCart"));
			Assert.fail();
			
		}
		
		}
		
		
		
	public void checkOutCart() throws Exception
	{
		try {
			checkOut();
		}
		catch (Exception e) {
			Common.refreshpage();
			Thread.sleep(6000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//button[@title='Add to Cart']");
			Common.clickElement("xpath", "//button[@title='Add to Cart']");
			Thread.sleep(5000);
			checkOut();
		}
	}

	
	public void closeFreeGift() throws Exception {
		 for(int i=0;i<10;i++){
				List<WebElement> webelemts=Common.findElements("xpath", "//div[@id='checkout-loader']");
				Thread.sleep(1000);
				int siz=webelemts.size();
			    if(siz==0){
					int size=Common.findElements("xpath", "//button[contains(@class,'ampromo-close')]").size();
					if(size!=0){
						Common.clickElement("xpath", "//button[contains(@class,'ampromo-close')]");
						break;
					}
					
					break;
				}
			   		 }
	}
	
	public void addDeliveryAddress_registerUser(String dataSet) throws Exception{
		
		  closeFreeGift();
		  String expectedResult="shipping address is entering in the fields";
	
		 
		   int size=Common.findElements(By.xpath("//span[contains(text(),'Add New Address')]")).size();
		      if(size>0){
		    	  
		    	  
		    	  try{
			    Common.clickElement("xpath", "//span[contains(text(),'Add New Address')]");
			    Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']", data.get(dataSet).get("Street"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(3000);
				try {
				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
				}catch(Exception e)
				{
					Common.actionsKeyPress(Keys.BACK_SPACE);
					Thread.sleep(1000);
					Common.actionsKeyPress(Keys.SPACE);
					Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");		
				}
				if(data.get(dataSet).get("StreetLine2")!=null)
				{
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if(data.get(dataSet).get("StreetLine3")!=null)
				{
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']", data.get(dataSet).get("City"));
				//Common.mouseOverClick("name", "region_id");
				try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));}
				catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(3000);
					Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']", data.get(dataSet).get("postcode"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']", data.get(dataSet).get("phone"));
				
				ExtenantReportUtils.addPassLog("validating the shipping address", expectedResult, "user add the shipping address",  Common.getscreenShotPathforReport("faield to add shipping address"));
				
				
			//	ExtenantReportUtils.addPassLog("enter the shipping address in to the fields without skipping any mandatory fields", expectedResult, "Filled the shipping address", Common.getscreenShotPathforReport("failed to add a address"));
				Common.clickElement("xpath", "//button[contains(@class,'save-address')]");
				
				int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
				
				Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying shipping addres filling ", "user will fill the all the shipping", "user fill the shiping address click save button", "faield to add new shipping address");
				
				//Common.assertionCheckwithReport(sizeerrormessage<=0, "Filled the shipping address", expectedResult, "failed to add a address");
					//Common.assertionCheckwithReport(sizeerrormessage<=0,"enter the shipping address in to the fields without skipping any mandatory fields", expectedResult, "Filled the shipping address", "failed to add a address");
				
				
				
				
			//	Common.assertionCheckwithReport(sizeerrormessage<=0, "Filled the shipping address", expectedResult, "failed to add a address");
				
	            Thread.sleep(3000);
	            Common.clickElement("xpath", "//input[@id='label_method_bestway']");
	            Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
					Thread.sleep(3000);
					/*
					 * need to implement 
					 * 
					 */
					
				//report.addPassLog("clicked on the proceed to payment section",Common.getscreenShotPathforReport("land on the payment section"));
		      
					  closeFreeGift();
		 }
		    	  catch(Exception |Error e) {
		  			
		  			ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult, "User unabel add shipping address",  Common.getscreenShotPathforReport("shipping address faield"));
		  			//ExtenantReportUtils.addFailedLog("User click check out button", "User unabel click the checkout button", Common.getscreenShotPathforReport("check out miniCart"));
		  			Assert.fail();
		  			
		  		}}
		 
		 
		 
		 
		 else
		    	
		    
		 {
			  try{
			    Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']", data.get(dataSet).get("Street"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(3000);
				try {
				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
				}catch(Exception e)
				{
					Common.actionsKeyPress(Keys.BACK_SPACE);
					Thread.sleep(1000);
					Common.actionsKeyPress(Keys.SPACE);
					Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");		
				}
				if(data.get(dataSet).get("StreetLine2")!=null)
				{
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if(data.get(dataSet).get("StreetLine3")!=null)
				{
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']", data.get(dataSet).get("City"));
				//Common.mouseOverClick("name", "region_id");
				try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));}
				catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(3000);
					Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("name", "postcode");
				Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

				ExtenantReportUtils.addPassLog("verifying shipping addres filling ", expectedResult, "user enter the shipping address ", Common.getscreenShotPathforReport("fill the shipping address first time"));
				
				
				Common.findElements("xpath", "").size();
			    expectedResult="shipping address is filled in to the fields";
				//report.addPassLog(expectedResult,"Filled the shipping address",Common.getscreenShotPathforReport("fill the shipping address"));
	            Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
	            Common.mouseOverClick("xpath", "//input[@id='label_method_bestway']");
	            Thread.sleep(3000);
	            
	            
	            
			  }
          catch(Exception |Error e) {
		  			
		  			ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult, "User unabel add shipping address",  Common.getscreenShotPathforReport("shipping address faield"));
		  			//ExtenantReportUtils.addFailedLog("User click check out button", "User unabel click the checkout button", Common.getscreenShotPathforReport("check out miniCart"));
		  			Assert.fail();
		  			
		  		}}
	            
	            
	            
				//report.addPassLog("clicked on the proceed to payment section",Common.getscreenShotPathforReport("land on the payment section"));
				  closeFreeGift();
		 }

		
		
	
	
	
	public void addDeliveryAddress(String dataSet) throws Exception
	{
		  try {	
			Sync.waitElementVisible("id", "customer-email-address");
			Common.textBoxInput("id", "customer-email-address",data.get(dataSet).get("Email"));
			}catch (NoSuchElementException e) {
				checkOut();
				Common.textBoxInput("id", "customer-email-address",data.get(dataSet).get("Email"));
				
			}
			Thread.sleep(3000);
			String  expectedResult="email field will have email address";
          try{
            Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']", data.get(dataSet).get("FirstName"));
			
            int size= Common.findElements("id", "customer-email-address").size();
    		
            
            Common.assertionCheckwithReport(size>0, "validating the email address field", expectedResult, "Filled Email address", "unabel to fill the email address");
            
            //Common.assertionCheckwithReport(size>0, "Filled Email address", expectedResult, "unabel to fill the email address");
            Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']", data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']", data.get(dataSet).get("Street"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.SPACE);
			Thread.sleep(3000);
			try {
			Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			}catch(Exception e)
			{
				Common.actionsKeyPress(Keys.BACK_SPACE);
				Thread.sleep(1000);
				Common.actionsKeyPress(Keys.SPACE);
				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");		
			}
			if(data.get(dataSet).get("StreetLine2")!=null)
			{
				Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
			}
			if(data.get(dataSet).get("StreetLine3")!=null)
			{
				Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
			}
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']", data.get(dataSet).get("City"));
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			try {
			Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));}
			catch (ElementClickInterceptedException e) {
			    Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("name", "postcode");
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
			
          }
          
          catch(Exception |Error e) {
  			
  			ExtenantReportUtils.addFailedLog("validating shipping address", "shipping address is filled in to the fields", "user faield to fill the shipping address",  Common.getscreenShotPathforReport("shipingaddressfaield"));
  			//ExtenantReportUtils.addFailedLog("User click check out button", "User unabel click the checkout button", Common.getscreenShotPathforReport("check out miniCart"));
  			Assert.fail();
  			
  		}
			
			
			
			expectedResult="shipping address is filled in to the fields";
			Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
			
			
            int errorsize= Common.findElements("xpath", "//div[contains(@id,'error')]").size();
           
            if(errorsize<=0){
            	ExtenantReportUtils.addPassLog("validating the shipping address field with valid Data", expectedResult, "Filled the shipping address", Common.getscreenShotPathforReport("shippingaddresspass"));
            }
            else{
            	ExtenantReportUtils.addFailedLog("validating the shipping address field with valid Datas", expectedResult,"failed to add a addres in the filled", Common.getscreenShotPathforReport("failed to add a address"));
        		Assert.fail();
            }
            
         //   Common.assertionCheckwithReport(errorsize<=0,"enter the shipping address in to the fields without skipping any mandatory fields", expectedResult, "Filled the shipping address", "failed to add a address");
            // Common.assertionCheckwithReport(errorsize<=0, "Filled the shipping address", expectedResult, "Missing the shipping address");
			Thread.sleep(3000);
			}

	
	public void addNewAddress_ShipPage(String dataSet) throws Exception{
		
		
		Common.clickElement("xpath", "//span[text()='Add New Address']");
		Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']", data.get(dataSet).get("LastName"));
		Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']", data.get(dataSet).get("Street"));
		if(data.get(dataSet).get("StreetLine2")!=null)
		{
			Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
		}
		if(data.get(dataSet).get("StreetLine3")!=null)
		{
			Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
		}
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(3000);
		//Common.mouseOverClick("name", "region_id");
		try {
		Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));}
		catch (ElementClickInterceptedException e) {
			Thread.sleep(3000);
			Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		}
		Thread.sleep(2000);
		Common.textBoxInputClear("name", "postcode");
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		
		Common.clickElement("xpath", "//span[text()='Save Address']");
		Thread.sleep(3000);
	}
	
	
	
	
	
	
	
	
	
	
	
	public void addPaymentDetails(String dataSet) throws Exception
	{
		//Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
		//Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");

		     Thread.sleep(4000);
                String expectedResult="land on the payment section";
            try{
                Sync.waitElementClickable("xpath", "//label[@for='ime_paymetrictokenize']");
				//int sizes=Common.findElements("xpath", "//label[@for='ime_paymetrictokenize']").size();
				
			   // Common.assertionCheckwithReport(sizes>0, "Successfully  land on the payment section", expectedResult,"User unabel to land on paymentpage");
				Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
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
                
            }
            
            catch(Exception |Error e) {
    			
    			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult, "faield  to fill the Credit Card infromation",  Common.getscreenShotPathforReport("Cardinfromationfail"));
    			//ExtenantReportUtils.addFailedLog("User click check out button", "User unabel click the checkout button", Common.getscreenShotPathforReport("check out miniCart"));
    			Assert.fail();
    			
    		}
           
                expectedResult="credit card fields are filled with the data";
		    	String errorTexts=	Common. findElement("xpath", "//div[contains(@id,'error')]").getText();
		        Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data", expectedResult, "Filled the Card detiles", "missing field data it showinng error");
		    	
		   
		}
		
		
		
	
	
	public void updatePaymentAndSubmitOrder(String dataSet) throws Exception
	{
		addPaymentDetails(dataSet);
		String expectedResult="It redirects to order confirmation page";
		
		
		
		if(Common.findElements("xpath", "//div[@class='message message-error']").size()>0)
		{	
			addPaymentDetails(dataSet);
		}
			
		Thread.sleep(3000);
		int placeordercount=Common.findElements("xpath", "//button[@title='Place Order']").size();
		if(placeordercount>1){
			Common.clickElement("xpath", "//button[@title='Place Order']");
		}
		
		    String sucessMessage=Common.getText("xpath", "//h1[@class='checkout-success-title']").trim();
			//Assert.assertEquals(sucessMessage, "Your order has been received","Sucess message validations");
		    int sizes=Common.findElements("xpath", "//h1[@class='checkout-success-title']").size();
		    Common.assertionCheckwithReport(sucessMessage.equals("Your order has been received"), "verifying the product confirmation", expectedResult, "Successfully It redirects to order confirmation page Order Placed", "User unabel to go orderconformation page");
			// Common.assertionCheckwithReport(sizes>0, "Successfully It redirects to order confirmation page Order Placed", expectedResult,"User unabel to go orderconformation page");
			
			 //report.addPassLog(expectedResult," ",Common.getscreenShotPathforRepoYour order has been received
	}
	public void payPal_Payment(String dataSet ) throws Exception{
		
		
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
    	
		String sucessMessage=Common.getText("xpath", "//h1[@class='checkout-success-title']").trim();
		Assert.assertEquals(sucessMessage, "Your order has been received","Sucess message validations");
		expectedResult="Verify order confirmation number which was dynamically generated";
		Common.assertionCheckwithReport(sucessMessage.equals("Your order has been received"), "Order Placed successfull", expectedResult, "faild to place order");
		
		}
		
	
	
	
	
	
	
	

	public void clickWarranty()throws Exception{
		
		
	 
		//report.addPassLog(expectedResult,"Successfully landed on the home page",Common.getscreenShotPathforReport("Successfully landed on the home page"));
		
		String expectedResult="User should land on the home page";
	    int size=Common.findElements("xpath", "//a[@class='logo']").size();
		Common.assertionCheckwithReport(size>0, "Successfully landed on the home page", expectedResult,"User unabel to land on home page");
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitElementPresent("xpath", "//a[text()='warranty']");
		Common.clickElement("xpath", "//a[text()='warranty']");
		String currenturl= Common.getCurrentURL();
		Common.assertionCheckwithReport(currenturl.contains("product_warranty"), "verifying warranty page", "It should land  warranty page", "Successfully land on warranty page", "User unabel on warranty page");
		//(currenturl.contains("product_warranty"), "Successfully land on warranty page", expectedResult,"User unabel on warranty page");
	}
	catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying warranty page", "It should land  warranty page", "User failed to click warrenty option", Common.getscreenShotPathforReport("warrenty button"));
	    Assert.fail();
      }
	
	}
	   
		
	
	
	public void submitWarranty(String dataSet) throws Exception{
		String expectedResult="It should land  warranty page ";
		
		Common.actionsKeyPress(Keys.END);
		clickWarranty();
		Thread.sleep(5000);
		try{
		Sync.waitElementPresent("xpath", "//div[@class='wararnty-cta']/a");
		Common.clickElement("xpath", "//div[@class='wararnty-cta']/a");
		Sync.waitElementPresent("id", "email");
		expectedResult="User is redirected to login page";
	    int sizeemail	=Common.findElements("id", "email").size();
		Common.assertionCheckwithReport(sizeemail>0, "verifying login page", "user redirected to login page", "Successfully User is redirected to login page", "User unabel redirected to login page");
		//(sizeemail>0, "Successfully User is redirected to login page", expectedResult,"User unabel redirected to login page");
		}
		
		catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying login page", "user redirected to login page", "User failed to redirected to login pag", Common.getscreenShotPathforReport("warrenty LoginPage"));
		    Assert.fail();
	      }
		
		try{
		
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		Sync.waitElementPresent("id", "pass");
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		//report.addPassLog("Enter the login Infromation ",Common.getscreenShotPathforReport("Loginto application"));
		Sync.waitElementPresent("xpath", "//button[@class='login-page-submit-action']");
		Common.clickElement("xpath", "//button[@class='login-page-submit-action']");
		ExtenantReportUtils.addPassLog("verifying login page", "User is login and able to view warranty form", "user Successfully login", Common.getscreenShotPathforReport("faield tologin warranty"));
		}
		
		
		catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying login page with credentials ", "User is logged in and able to view warranty form", "User failed to login or view warranty form", Common.getscreenShotPathforReport("warranty form"));
		    Assert.fail();
	      }
		
		try{
		Thread.sleep(6000);
		Sync.waitElementPresent("xpath", "//iframe[contains(@src,'warranty')]");
		Common.switchFrames("xpath", "//iframe[contains(@src,'warranty')]");
		int warrantyfistname=Common.findElements("xpath", "//input[contains(@name,'Contact.Name.First')]").size();
		expectedResult="User is logged in and able to view warranty form";
		Common.assertionCheckwithReport(warrantyfistname>0, "verifying warrenty from", expectedResult, "Successfully login user and able to view warranty form", "User failed open the warranty form");
		 //(warrantyfistname>0, "Successfully login user and able to view warranty form ", expectedResult,"User unabel see the warranty form");
		//ExtenantReportUtils.addPassLog("verifying login page with credentials", "User is logged in and able to view warranty form", "User successfully  open the warranty form", "User failed open the warranty form");
		}
		
		catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying warrenty from", "User is logged in and able to view warranty form", "User failed to view  warranty form", Common.getscreenShotPathforReport("warranty form view"));
		    Assert.fail();
	      }
		
		
		Thread.sleep(6000);
		//Submit a Warranty Claim form
		
	
		
		try {
		Sync.waitElementPresent("xpath", "//input[contains(@name,'Contact.Name.First')]");
		Common.textBoxInput("xpath", "//input[contains(@name,'Contact.Name.First')]", data.get(dataSet).get("FirstName"));
		}catch(Exception e)
		{
			Common.textBoxInput("xpath", "//input[contains(@name,'Contact.Name.First')]", data.get(dataSet).get("FirstName"));
		}
		try{
		Sync.waitElementPresent("xpath", "//input[@name='Contact.Name.Last']");
		Common.textBoxInput("xpath", "//input[@name='Contact.Name.Last']", data.get(dataSet).get("LastName"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]", data.get(dataSet).get("Email"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'Street')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'Street')]", data.get(dataSet).get("Street"));
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'City')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'City')]", data.get(dataSet).get("City"));
		
		Common.clickElement(By.xpath("//select[contains(@id,'Country')]"));

		Sync.waitElementPresent("xpath", "//select[contains(@id,'Country')]");
		Common.dropdown("xpath", "//select[contains(@id,'Country')]", SelectBy.TEXT, data.get(dataSet).get("Country"));
		
		
		Sync.waitElementPresent("xpath", "//select[contains(@id,'StateOrProvince')]");
		Common.clickElement(By.xpath("//select[contains(@id,'StateOrProvince')]"));
		Thread.sleep(5000);
		Common.dropdown("xpath", "//select[contains(@id,'StateOrProvince')]", SelectBy.TEXT,data.get(dataSet).get("State"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'PostalCode')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'PostalCode')]", data.get(dataSet).get("postcode"));
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'MOBILE')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'MOBILE')]", data.get(dataSet).get("phone"));
		
		Thread.sleep(3000);
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		
		Sync.waitElementPresent("xpath", "//span[text()='View All']");
		Common.clickElement(By.xpath("//span[text()='View All']"));
		
		Thread.sleep(5000);
	    List<WebElement> Productselemts=Common.findElements("xpath", "//div[contains(@class,'nameset')]");
		
	       for(int i=0;i<Productselemts.size();i++){
	    	   
	    	    if(Productselemts.get(i).getAttribute("title").equals( data.get(dataSet).get("Products"))){
	    	    	Productselemts.get(i).click();
	    	    	break;
	    	    }
	    	   
	       }
	
	     //input[contains(@class,'product_quantity')]
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@class,'product_quantity')]");
		Common.textBoxInput("xpath", "//input[contains(@class,'product_quantity')]", data.get(dataSet).get("ProductQuantity"));
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@class,'problem_description')]");
		Common.textBoxInput("xpath", "//input[contains(@class,'problem_description')]", data.get(dataSet).get("ProblemDescription"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'FileInput')]");
		String path=System.getProperty("user.dir")+("\\src\\test\\resources\\TestData\\Hydroflask\\TestScreen.jpg");
		try {
		Common.fileUpLoad("xpath", "//input[contains(@id,'FileInput')]", path);
		}
		catch(Exception e)
		{
			//ExtenantReportUtils.addFailedLog("warrenty from file upload ", "warrenty from unabel to uppload file", Common.getscreenShotPathforReport("warrenty from uploadfile"));
			//Assert.fail();
		}
		//expectedResult="No validation errors";
		//report.addPassLog(expectedResult,"Enter the warrenty from infromation with out any validation  ",Common.getscreenShotPathforReport("Filling the Warranty from "));
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//button[contains(@id,'CustomFormSubmit')]");
		Common.clickElement("xpath", "//button[contains(@id,'CustomFormSubmit')]");
		
		}
	 	catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying warranty form field data","warrenty from infromation with out any validation", "warrenty from with invalid data", Common.getscreenShotPathforReport("warrenty from invalid data"));
			e.printStackTrace();
			Assert.fail();
			
		}
		
		
		int sizeerrormessage=Common.findElements("xpath", "//div[contains(@id,'ErrorLocation')]").size();
		Common.assertionCheckwithReport(sizeerrormessage>0, "verifying warranty form field data", "Enter the warrenty from infromation with out any validation", "mandatory data missing in the from", Common.getscreenShotPathforReport("mandatory data missing in the from"));
		//(sizeerrormessage>0, "Enter the warrenty from infromation with out any validation ", expectedResult, "mandatory data missing in the from");
		Common.actionsKeyPress(Keys.HOME);
		Thread.sleep(6000);
		
		String sucessMessage=Common.getText("xpath", "//body[@id='rn_BlankBody']//h1").trim();
		//Assert.assertEquals(sucessMessage, "Your warranty request has been submitted!");
		expectedResult="User gets redirected to confirmation page, it includes a reference number and email is sent to email provided. No validation errors.";
		Common.assertionCheckwithReport (sucessMessage.equals("Your warranty request has been submitted!"), "warranty applied  successfull,and redirected to confirmation page", expectedResult, "submit the warranty but confirmation page  message missing");
	//	report.addPassLog(expectedResult,"warranty applied  successfull,and redirected to confirmation page",Common.getscreenShotPathforReport("warranty  submitted "));
		
		}
		
		

	
	public void clickContact()throws Exception{
		String expectedResult="User should land on the home page";
	    int size=Common.findElements("xpath", "//a[@class='logo']").size();
		Common.assertionCheckwithReport(size>0, "verifying home page", expectedResult, "Successfully landed on the home page", "User unabel to land on home page");
		//(size>0, "Successfully landed on the home page", expectedResult,"User unabel to land on home page");
		Common.actionsKeyPress(Keys.END);
		try{
		Sync.waitElementPresent("xpath", "//a[text()='Contact']");
		Common.clickElement("xpath", "//a[text()='Contact']");
		Thread.sleep(9000);	
		String contactpageurl=Common.getCurrentURL();
        expectedResult="It should land successfully on the explore/contact page";
     	Common.assertionCheckwithReport(contactpageurl.contains("contact"),"successfully land to contact page",expectedResult,"unabel to load the  contact page");
		}
		catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("validating contact us page",expectedResult, "unabel to load the contact page", Common.getscreenShotPathforReport("Contact us page link"));
			Assert.fail();
			
		}
	}
	
		

	
	public void contactUsPage(String dataSet) throws Exception{
		clickContact();
		String expectedResult ="Email us form is visible in tab";
     	
   	 try{
     	/*String contactpageurl=Common.getCurrentURL();
     	String expectedResult="It should land successfully on the explore/contact page";
     	Common.assertionCheckwithReport(contactpageurl.contains("contact"),"successfully land to contact page",expectedResult,"unabel to load the  contact page");*/
     
       for(int i=0;i<10;i++){
    	 Thread.sleep(5000);
    	 

    	 WebElement activeelemet=Common.findElement("xpath", "//*[@id='HNNEN6W']/div[1]");
    	 String className=activeelemet.getAttribute("class");
    	 if(className.contains("active")){
    		 
    		 ExtenantReportUtils.addPassLog("validating emil us button","Email us button displayed", "Dispalying EmailUs button", Common.getscreenShotPathforReport("EmailUsbutton"));
    		// Common.assertionCheckwithReport(contactpageurl.contains("contact"),"successfully land to contact page",expectedResult,"unabel to load the  contact page");
    		 
    		  Common.clickElement("xpath", "//*[@id='HNNEN6W']/div[2]/div[2]/div[1]/ul/li[3]/img[2]");
        	  Common.actionsKeyPress(Keys.PAGE_DOWN);
        	 break;
          }
        }
     
     
    	 Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");
    	 
    	 
    	 
    	//input[contains(@id,'Emails')]
    	
       
    	 
    	 Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");
    	 
    	int emailsize= Common.findElements("xpath", "//input[contains(@id,'Emails')]").size();
        Common.assertionCheckwithReport(emailsize>0,"Email us form is visible in tab",expectedResult,"unabel to load the  contacts form"); 
    	
  		Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]", data.get(dataSet).get("Email"));
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'First')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'First')]", data.get(dataSet).get("FirstName"));
 		
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'Last')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'Last')]", data.get(dataSet).get("LastName"));
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'company')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'company')]", data.get(dataSet).get("Company"));
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'MOBILE')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'MOBILE')]", data.get(dataSet).get("phone"));
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'Street')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'Street')]", data.get(dataSet).get("Street"));
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'City')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'City')]", data.get(dataSet).get("City"));
 		
 		Sync.waitElementPresent("xpath", "//select[contains(@id,'Country')]");
 		Common.dropdown("xpath", "//select[contains(@id,'Country')]", SelectBy.TEXT, data.get(dataSet).get("Country"));
 		
 		
 		Sync.waitElementPresent("xpath", "//select[contains(@id,'State')]");
 		Common.dropdown("xpath", "//select[contains(@id,'State')]", SelectBy.TEXT, data.get(dataSet).get("State"));
 		
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'PostalCode')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'PostalCode')]", data.get(dataSet).get("postcode"));
 		
 	
 		Sync.waitElementPresent("xpath", "//select[contains(@id,'SelectObject_lvl1')]");
 		Common.dropdown("xpath", "//select[contains(@id,'SelectObject_lvl1')]",SelectBy.TEXT, data.get(dataSet).get("Howcanwehelp"));
 		
 		
 		Thread.sleep(5000);
 		Sync.waitElementPresent("xpath", "//select[contains(@id,'SelectObject_lvl2')]");
 		Common.dropdown("xpath", "//select[contains(@id,'SelectObject_lvl2')]",SelectBy.TEXT, data.get(dataSet).get("category"));
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'ordernumber')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'ordernumber')]", data.get(dataSet).get("OrderNumber"));
 		
 		
 		
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'DateTimeUI')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'DateTimeUI')]", data.get(dataSet).get("OrderDate"));
 	
 		Thread.sleep(5000);
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'billing_name')]");
 		Common.clickElement("xpath", "//input[contains(@id,'billing_name')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'billing_name')]", data.get(dataSet).get("BillName"));
 		
 		
 		Sync.waitElementPresent("xpath", "//textarea[contains(@id,'TextInputPlaceholder_94')]");
 		Common.clickElement("xpath", "//textarea[contains(@id,'TextInputPlaceholder_94')]");
 		Common.textBoxInput("xpath", "//textarea[contains(@id,'TextInputPlaceholder_94')]", data.get(dataSet).get("Question*"));
 		Common.clickElement("xpath", "//button[@id='rn_FormSubmit_95_Button']");
        Thread.sleep(7000);
       }
	
   	catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying email us from","contact us form data enter without any error message", "Contact us page getting error ", Common.getscreenShotPathforReport("Contact us page"));
		Assert.fail();
		
	}
   	 
 		Common.actionsKeyPress(Keys.PAGE_UP);
 		String Text=Common.getText("xpath", "//div[@class='rn_Container']/h1");
 	    Assert.assertEquals(Text, "Your question has been submitted!","Conatct message sucess");
 	    expectedResult ="User gets confirmation under the same tab. It includes a reference number and email is sent to email provided. No validation errors.";
 	    Common.assertionCheckwithReport(Text.equals("Your question has been submitted!"), "verifying contact us conformation message", expectedResult, "User gets confirmation under the same tab", "unabel to load the confirmation form");
 	   // (Text.equals("Your question has been submitted!"),"User gets confirmation under the same tab",expectedResult,"unabel to load the confirmation form"); 
 	  }
     
     
     
     
     	
    /* int size=	Common.findElements("xpath","//*[@id='HNNEN6W']/div[2]/div[2]/div[1]/ul/li[3]/img[2]").size();
     System.out.println(size);
    		Sync.waitElementPresent("xpath", "//li[@data-tab-name='E-mail Us']");
    		
    		Common.clickElement("xpath","//li[@data-tab-name='E-mail Us']");
    		Common.clickElement("xpath", "//*[@id='HNNEN6W']/div[2]/div[2]/div[1]/ul/li[3]/img[2]");
    		
    		//*[@id="HNNEN6W"]//ul/li[3]
		
//		Common.clickElement("xpath","//li[@data-tab-name='E-mail Us']");
		
		report.addPassLog(" navigate to contact page ",Common.getscreenShotPathforReport("click email Us button "));
		//Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");
		1
		
		try{
			Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");
             Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]", data.get(dataSet).get("Email"));
		}
			catch(Exception e){
				Common.switchToDefault();
				Common.clickElement("xpath","//li[@data-tab-name='E-mail Us']");
				report.addPassLog(" navigate to contact page ",Common.getscreenShotPathforReport("click email Us button "));
				Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");
			}
		
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]", data.get(dataSet).get("Email"));
		//
	*/	
	
	
	public void clickProDeal()throws Exception{
		String expectedResult="User should land on the home page";
	    int size=Common.findElements("xpath", "//a[@class='logo']").size();
		Common.assertionCheckwithReport(size>0, "verifying home page", expectedResult, "Successfully landed on the home page", "User unabel to land on home page");
		//(size>0, "Successfully landed on the home page", expectedResult,"User unabel to land on home page");
		try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitElementPresent("xpath", "//a[text()='Pro Deal']");
		Common.clickElement("xpath", "//a[text()='Pro Deal']");
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Sign in or register']");
			 
			}catch(Exception e)
			{
				clickProDeal();
				Thread.sleep(3000);
			}
        String prodealname=Common.getCurrentURL();
		
		expectedResult="User is redirected to the Apply For Pro Deal page";
		Common.assertionCheckwithReport(prodealname.contains("prodeal"), "verifying Pro Deal page ", expectedResult, "Successfully redirected prodeal page", "User unabel to land on prodeal page");
		
		}
		catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying Pro Deal page","user click prodeal option", "user faield to click prodeal option", Common.getscreenShotPathforReport("prodeal page click faield"));
			Assert.fail();
			
		}
		}
	public void ProdelerPage(String dataSet) throws Exception{
		clickProDeal();
		
	
		
		
	String prodealname=Common.getCurrentURL();
	{
		/*String expectedResult="User is redirected to the Apply For Pro Deal page";
		Common.assertionCheckwithReport(prodealname.contains("prodeal"), "Successfully redirected prodeal page", expectedResult,"User unabel to land on prodeal page");*/
	    String expectedResult="User is redirected to login page";	

		Common.clickElement("xpath", "//a[@title='Sign in or register']");
		Sync.waitElementPresent("id", "email");
		int sizeemeil=Common.findElements("id", "email").size();
		Common.assertionCheckwithReport(sizeemeil>0, "Successfully redirected to login page", expectedResult,"User unabel to land on login page");
	
	
		try{
		
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		Sync.waitElementPresent("id", "pass");
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		Sync.waitElementPresent("xpath", "//button[@class='login-page-submit-action']");
		Common.clickElement("xpath", "//button[@class='login-page-submit-action']");
		
		
		Sync.waitElementPresent("xpath", "//a[contains(@class,'pro-deal-action-primary')]");
		Common.clickElement("xpath", "//a[contains(@class,'pro-deal-action-primary')]");
		
		}
		catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("ProDeal User login ", "User faield login aplication ", Common.getscreenShotPathforReport("prodeal aplication "));
    		Assert.fail();
    		
    	}
		
		Thread.sleep(3000);
		Common.switchWindows();
		Thread.sleep(3000);
		expectedResult="User is redirected to Pro Deal application page";
		try{
		
			Sync.waitElementPresent("id", "first_name");
		int fistnamesize=Common.findElements("id", "first_name").size();
		Common.assertionCheckwithReport(fistnamesize>0, "Successfully User is redirected to Pro Deal application page", expectedResult,"User unabel to redirected to Pro Deal application page");
		Thread.sleep(3000);
		Common.textBoxInput("id", "first_name", data.get(dataSet).get("FirstName"));
	    Sync.waitElementPresent("id", "last_name");
		Common.textBoxInput("id", "last_name", data.get(dataSet).get("LastName"));
		Sync.waitElementPresent("id", "association");
		Common.textBoxInput("id", "association", data.get(dataSet).get("Company"));
	    Sync.waitElementPresent("id", "association_email");
		Common.textBoxInput("id", "association_email", data.get(dataSet).get("AssociationEmailAddress"));
	    Common.actionsKeyPress(Keys.PAGE_DOWN);
		    Thread.sleep(6000);
		     String path=System.getProperty("user.dir")+("\\src\\test\\resources\\TestData\\Hydroflask\\TestScreen.jpg");
			//Sync.waitElementInvisible("xpath", "//input[@id='supporting_document']");
			Common.fileUpLoad("xpath", "//input[@id='supporting_document']", path);
		
		Sync.waitElementPresent("id", "group_id");
		
		System.out.println(data.get(dataSet).get("GropName"));
		Common.dropdown("xpath", "//select[@id='group_id']", SelectBy.TEXT, data.get(dataSet).get("GropName"));
		
		Sync.waitElementPresent("id", "comment");
		Common.textBoxInput("id", "comment",  data.get(dataSet).get("CommetsHydroflask"));
		
		
		Sync.waitElementPresent("xpath", "//button[@title='Submit']");
		Common.clickElement("xpath", "//button[@title='Submit']");
		
		}
		catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("ProDeal application from filling", "User faield to fill the prodeal aplication ", Common.getscreenShotPathforReport("prodeal aplication "));
    		Assert.fail();
    		
    	}
		
		
		//String text=Common.getText("xpath", "//div[@class='pro-deal-header']/h4");
		
		String text=Common.getText("xpath","//span[@class='base']");
		//Assert.assertEquals(text, "Pro Deal Application Complete", "pro Deal application completed");
		
		expectedResult="User gets redirected to confirmation page and email is sent to email provided.";
		
		Common.assertionCheckwithReport(text.equals("Pro Deal Application Complete"), "verifying Pro Deal conformation", expectedResult, "User redirected to confirmation page", "User unabel to redirected to confirmation page");
	
	}
		
       
	}

	
	
	
	
	public void stayIntouch(String dataSet) throws Exception{
		
		String expectedResult="User should land on the home page";
		try{
	    int size=Common.findElements("xpath", "//a[@class='logo']").size();
		Common.assertionCheckwithReport(size>0, "Successfully landed on the home page", expectedResult,"User unabel to land on home page");
		
		Common.actionsKeyPress(Keys.END);
		Thread.sleep(5000);
		
		Sync.waitElementPresent("id", "newsletter");
		Common.clickElement("id", "newsletter");
		
		String email=Common.genrateRandomEmail(data.get(dataSet).get("Email"));
		
		Common.textBoxInput("id", "newsletter", email);
		Thread.sleep(5000);
		Common.clickElement("xpath", "//button[contains(@class,'action subscribe primary')]");
		Thread.sleep(3000);
		String Text=Common.getText("xpath", "//input[@id='newsletter']//following::div[1]");
		expectedResult="User gets confirmation message that it was submitted";
		ExtenantReportUtils.addPassLog("verifying newsletter subscription","User get confirmation message if new email if it used mail it showing error message ", Text, Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));
		
		}
		catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("verifying newsletter subscription","NewsLetter Subscrption success", "User faield to subscrption for newLetter  ", Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));
    		Assert.fail();
    		
    	}
		// Text=Common.getTextBoxInput("xpath", "//input[@id='newsletter']//following::div[1]");
		
		
		//Common.assertionCheckwithReport(size>0, "confirmation message that it was submitted", expectedResult,Text);
		//.addPassLog(expectedResult,"confirmation message that it was submitted",Common.getscreenShotPathforReport(Text));
	}
		//Common.clickElement("xpath", "//div[contains(@class,'sign-button')]");
		//Thread.sleep(5000);
	   // System.out.println(Common.getText("class", "mage-success"));
		
		
	
	public void forgetpassword(String dataSet) throws Exception{
		navigateMyAccount();
		String expectedResult="Forgot password pop up is displayed to customer";
		try{
		Sync.waitElementPresent("xpath", "//a[contains(@class,'forgot-password')]");
		Common.clickElement("xpath","//a[contains(@class,'forgot-password')]");
	    Sync.waitElementPresent("xpath", "//input[contains(@id,'forgot-password-email')]");
	    int size=Common.findElements("xpath", "//input[contains(@id,'forgot-password-email')]").size();
	    Common.assertionCheckwithReport(size>0, "verifying forgetpassword option", expectedResult, "Successfully Forgot password pop up is displayed to customer", "User faield to get  Forgot password pop");
	    //(size>0, "Successfully Forgot password pop up is displayed to customer", expectedResult,"User faield to get  Forgot password pop");
		
		}
	    catch(Exception |Error e) {
	  		ExtenantReportUtils.addFailedLog("verifying forgetpassword option","clcik the forget password option", "User failed to clcik the forget password button", Common.getscreenShotPathforReport("forget password buttonfaield"));
	  		Assert.fail();
	  		
	  	}
	    
		try{
		
	    Common.textBoxInput("xpath", "//input[contains(@id,'forgot-password-email')]",data.get(dataSet).get("Email"));
		//Successfully Forgot password pop up is displayed to customer
		
		Common.clickElement("xpath", "//button[contains(text(),'Reset my Password')]");
		Thread.sleep(3000);
		expectedResult="Confirmation message is presented to customer saying if there is an associated account they would get email with instructions. Email is sent to customer.";
		report.addPassLog("verifying forgetpassword conformation message ",expectedResult,"Successfully Forgot password email is field",Common.getscreenShotPathforReport("foget password button"));
		}
		
		 catch(Exception |Error e) {
		  		ExtenantReportUtils.addFailedLog("verifying forgetpassword conformation message",expectedResult, "User failed to enter forget password", Common.getscreenShotPathforReport("forget password enterfaield"));
		  		Assert.fail();
		  		
		  	}
		
		//report.addPassLog(expectedResult,"user get confirmatin message",Common.getscreenShotPathforReport("reset my passowrd button"));
		//Common.actionsKeyPress(Keys.ESCAPE);
		}
		
	
	
    public void Customize_Bottle() throws Exception{

    	String expectedResult="User should land on the home page";
	    int size=Common.findElements("xpath", "//a[@class='logo']").size();
	    Common.assertionCheckwithReport(size>0, " verifying the home page", expectedResult, "Successfully landed on the home page", "User unabel to land on home page");
	    //(size>0, "validating the home page", expectedResult, "Successfully landed on the home page", "custombottlehomepage");
	    
	    
		//Common.assertionCheckwithReport(size>0, "Successfully landed on the home page", expectedResult,"User unabel to land on home page");
    	//report.addPassLog(expectedResult,"Successfully landed on the home page",Common.getscreenShotPathforReport("Successfully landed on the home page"));
    	try{
		Thread.sleep(8000);

    	Sync.waitElementPresent("xpath","//ul[@class='megamenu-list']/li[2]/div[1]/button");
        Thread.sleep(4000);

    	Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[2]/div[1]/button");
    	
    	Sync.waitElementPresent("xpath", "//span[contains(text(),'Create Yours Now')]");
        Thread.sleep(4000);
        Common.clickElement("xpath", "//span[contains(text(),'Create Yours Now')]");
    	
    	
        Thread.sleep(5000);
        
        expectedResult= "It should land successfully on my-hydro-landing page";
	    int sizes=Common.findElements("xpath", "//span[contains(text(),'Create Yours Now')]").size();
	    
	    Common.assertionCheckwithReport(sizes>0, "validating My hydro-Landing page", expectedResult, "successfully land  on my-hydro-landing page", "User unabel to land on my hydro landing page");
	    
		//Common.assertionCheckwithReport(sizes>0, "successfully land  on my-hydro-landing page", expectedResult,"User unabel to land on my-hydro-landing page ");
    	}
    	catch(Exception |Error e) {
    		
    		report.addFailedLog("validating My hydro-Landing page", expectedResult, "User Faield to select My Hydro option", Common.getscreenShotPathforReport("Myhydropage"));
    	//	ExtenantReportUtils.addFailedLog("click the my hydro button for customized productst", "User Faield to select My Hydro option ", Common.getscreenShotPathforReport("My hydro"));
    		Assert.fail();
    		
    	}
        
        try{
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementPresent("xpath", "//div[text()='Standard Mouth Bottle']/following::a[1]");
		WebElement linkEnabel=Common.findElementBy("xpath", "//div[text()='Standard Mouth Bottle']/following::a[1]");
			for(int i=1;i<10;i++){
    		Thread.sleep(2000);
    		String attribute=linkEnabel.getAttribute("href");
    		attribute.length();
    	     if(attribute.contains("hydro")){
    			expectedResult="It should land successfully on the my hydro configurator";
    			Sync.waitElementPresent("xpath", "//div[text()='Standard Mouth Bottle']/following::a[1]");
    			//int buttonsize=Common.findElements("xpath", "//div[text()='Standard Mouth Bottle']/following::a[1]").size();
    			report.addPassLog("validating my hydro configuration page", expectedResult, "successfully opean the  my hydro configurator page", " my-hydro-configurator page");
    			//Common.assertionCheckwithReport(buttonsize>0, "validating my hydro configuration page", expectedResult, "successfully opean the  my hydro configurator page", screenShotPath);
    			//Common.assertionCheckwithReport(buttonsize>0, "successfully opean the  my hydro configurator page", expectedResult,"User unabel to land on my-hydro-configurator page ");
    		     Common.clickElement("xpath", "//div[text()='Standard Mouth Bottle']/following::a[1]");
    			break;
    		}
    		
    	}
    	
        }
        catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("validating my hydro configuration page","It should land successfully on the my hydro configurator and select Standard Mouth Bottle ", "User Faield to select My Hydro configurator or botttle option", Common.getscreenShotPathforReport("My hydro options"));
    		Assert.fail();
    		
    	}
        
        
		
	    Thread.sleep(6000);
	    
	   
	    
    	/*Common.actionsKeyPress(Keys.PAGE_DOWN);
    	Sync.waitElementPresent("xpath", "//span[text()='Add To Cart']");
    	Common.clickElement("xpath", "//span[text()='Add To Cart']");
    	
    	report.addPassLog("add the product the cart",Common.getscreenShotPathforReport("click to add to Card"));*/
    	try{
    	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80251']");
    	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80251']");
    	
    	
    	Sync.waitElementPresent("xpath", "//div[contains(@aria-label,'24 oz Our')]");
    	Common.clickElement("xpath", "//div[contains(@aria-label,'24 oz Our')]");
    	
    	
    	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80262']");
    	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80262']");
    	
    	Sync.waitElementPresent("xpath", "//fieldset[@id='fc-ca-swatch-80262-fieldset']//div[5]/span");
    	Common.clickElement("xpath", "//fieldset[@id='fc-ca-swatch-80262-fieldset']//div[5]/span");
    	
    	
    	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80263']");
    	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80263']");
    	
    	Sync.waitElementPresent("xpath", "//fieldset[@id='fc-ca-swatch-80263-fieldset']//div[5]/span");
    	Common.clickElement("xpath", "//fieldset[@id='fc-ca-swatch-80263-fieldset']//div[5]/span");
    	
    	
      	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80268']");
    	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80268']");
    	
    	Sync.waitElementPresent("xpath", "//fieldset[@id='fc-ca-swatch-80268-fieldset']//div[5]/span");
    	Common.clickElement("xpath", "//fieldset[@id='fc-ca-swatch-80268-fieldset']//div[5]/span");
    	
    
    	
      	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80270']");
    	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80270']");
    	
    	Sync.waitElementPresent("xpath", "//fieldset[@id='fc-ca-swatch-80270-fieldset']//div[5]/span");
    	Common.clickElement("xpath", "//fieldset[@id='fc-ca-swatch-80270-fieldset']//div[5]/span");
    	expectedResult="chenage the bottle configuration size and colo";
    	
    	
    	int colorofbottle=Common.findElements("xpath", "//div[@id='fc-nav-flyout-header-80270']").size();
    	Assert.assertTrue(colorofbottle>0);
    	
    	}
    	catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("verifying myhydro configuration page",expectedResult, "User unabel to change bottele color ", Common.getscreenShotPathforReport("faield change the color myhydro"));
    		Assert.fail();
    		
    	}
    	//report.addPassLog(expectedResult, "Successfully chenage the bottle configuration size and color",Common.getscreenShotPathforReport("changing Configurations"));
    	try{
    	Sync.waitElementPresent("xpath", "//span[text()='Add To Cart']");
    	Common.clickElement("xpath", "//span[text()='Add To Cart']");
    	ExtenantReportUtils.addPassLog("verifying myhydro configuration page","user click add to cart button", "user click the add to cart button", Common.getscreenShotPathforReport("faield to click add to cart button"));
    	}
    	catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("verifying myhydro configuration page","click add to cart", "User faield to click add to cart ", Common.getscreenShotPathforReport("add to cart button"));
    		Assert.fail();
    		
    	}
    	Thread.sleep(6000);
//    	expectedResult="Item should be added to cart and user taken to cart page";
//    	report.addPassLog(expectedResult,"expectedResult the product the cart",Common.getscreenShotPathforReport("product page "));
    	checkOut();
    	}
    	
    	/*Sync.waitElementPresent("xpath", "//button[@id='top-cart-btn-checkout']");
    	Common.clickElement("xpath", "//button[@id='top-cart-btn-checkout']");
    	report.addPassLog("navigating  to check out page ",Common.getscreenShotPathforReport("click to check out"));*/
    
    
    
    public void shop_bottles() throws Exception{
    	Sync.waitElementPresent("xpath", "//ul[@class='megamenu-list']/li[ 1]/div[1]/button");
    	Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[ 1]/div[1]/button");
    	
    	Sync.waitElementPresent("xpath", "//ul[@class='megamenu-list-ancestor']//a[contains(text(),'Bottles')]");
    	Common.clickElement("xpath","//ul[@class='megamenu-list-ancestor']//a[contains(text(),'Bottles')]");
    	 }
public void review_bottles(String dataSet) throws Exception{
	 
	String expectedResult="It Shoud lands on the Product Listing Page";
	    try{
		
		Thread.sleep(8000);
		Sync.waitElementClickable("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]");
		Thread.sleep(4000);
		//Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
	
		//Common.clickElement("css", "ul.megamenu-list > li:nth-of-type(1) > div:nth-of-type(1) > button");
		Thread.sleep(3000);
		 expectedResult="It Shoud lands on the Product Listing Page";
		try {
		Common.mouseOver("xpath", "//a[contains(text(),'Bottles')]");
		}catch (Exception e) {
			
			Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
			Thread.sleep(1000);
		    Common.clickElement("xpath", "//a[contains(text(),'Bottles')]");
		
		}
		
       int sizeofbottle=Common.findElements("xpath", "//a[contains(text(),'Bottles')]").size();
		
		
       
	    }
	    catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("validating Bottles option ", "user selecct the bottle option", "user failed to click the bottle option",Common.getscreenShotPathforReport("bottleselectionmissing"));

    		Assert.fail();
    		
    	}
       
	
		
	  Thread.sleep(4000);
      Common.actionsKeyPress(Keys.PAGE_DOWN);
		try{	
		
		
    	Sync.waitElementPresent("xpath", "//img[contains(@src,'hibiscus')]");
		Common.clickElement("xpath","//img[contains(@src,'hibiscus')]");
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.actionsKeyPress(Keys.PAGE_DOWN);

		}
		  catch(Exception |Error e) {
			  e.printStackTrace();
    		ExtenantReportUtils.addFailedLog("Click on  product image/ product name", "User failed to to select the bottle product for review ", Common.getscreenShotPathforReport("bottleselectionmissing"));
    		Assert.fail();
    		
    	}
		Thread.sleep(8000);
		try{
		expectedResult="Click on  product image/ product name, it should  be redirect to the product details page";
	    Sync.waitElementPresent("id", "tab-title-reviews");
        int	tabelview=Common.findElements("id", "tab-title-reviews").size();
        Common.assertionCheckwithReport(tabelview>0, "redirect to the product details page", expectedResult,"User unabel to redirect the product details page");
        Common.clickElement("id", "tab-title-reviews");
		}
      
       
        
        
        catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("verifying bottele product",expectedResult, "User failed to select review option in product list page ", Common.getscreenShotPathforReport("bottle image"));
    		Assert.fail();
    		
    	}
        Thread.sleep(4000);
     
        try{
        
        Sync.waitElementPresent("xpath", "//button[contains(@class,'write-review')]");
        Common.clickElement("xpath", "//button[contains(@class,'write-review')]");
        }
        
        catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("verifying the review button","select the write review option", "User failed to click write review option  ", Common.getscreenShotPathforReport("write riew"));
    		Assert.fail();
    		
    	}
        
        
        
        
        try{
      //  report.addPassLog("click write review button ",Common.getscreenShotPathforReport("write review button"));
        Thread.sleep(4000);
        expectedResult ="It should shows My Review Pop-up";
        //report.addPassLog(expectedResult,"Review pop-up open ",Common.getscreenShotPathforReport("Review Page"));
        overallRating(data.get(dataSet).get("OverallRating"));
        
      
        
        Sync.waitElementPresent("id", "bv-text-field-title");
        
        int	reviewpagelview=Common.findElements("id", "bv-text-field-title").size();
        Common.assertionCheckwithReport(reviewpagelview>0,"verifying review page", "Review pop-up open", expectedResult, "User unabel to redirect Review pop-up");
       // (reviewpagelview>0, "Review pop-up open", expectedResult,"User unabel to redirect Review pop-up ");
        
        
        
        Common.textBoxInput("id", "bv-text-field-title", data.get(dataSet).get("Reviewtitle"));
        
        
        Common.textBoxInput("xpath", "//textarea[contains(@id,'reviewtext')]", data.get(dataSet).get("ReviewDescription"));
        
        
        
        Wouldyourecommendthiproduct(data.get(dataSet).get("recommendthiproduct"));
        
        //nicName
        Sync.waitElementPresent("xpath", "//input[contains(@id,'usernickname')]");
        Common.textBoxInput("xpath", "//input[contains(@id,'usernickname')]", data.get(dataSet).get("Nickname"));
      
        //User location
        Sync.waitElementPresent("xpath", "//input[contains(@id,'userlocation')]");
        Common.textBoxInput("xpath", "//input[contains(@id,'userlocation')]",data.get(dataSet).get("Location"));
        
        //user email
        
        Sync.waitElementPresent("xpath", "//input[contains(@id,'hostedauthentication')]");
        Common.textBoxInput("xpath","//input[contains(@id,'hostedauthentication')]",data.get(dataSet).get("Email"));    
        selectAgeforReview(Integer.valueOf(data.get(dataSet).get("Age")));
        selectGenderforReview(data.get(dataSet).get("gender"));
        qualityof_ProductRating(data.get(dataSet).get("ProductRating"));
        valueof_productRating(data.get(dataSet).get("valueofProductRating"));
        recommendHydroFlask( Integer.valueOf(data.get(dataSet).get("HydroFlaskOverallRating")));
        
        
        Common.textBoxInput("xpath", "//textarea[contains(@id,'netpromotercomment')]", data.get(dataSet).get("Pleasetelluswhy"));
        
        
        Common.clickElement("xpath", "//button[contains(@class,'form-actions-submit')]");
        
        
      //  expectedResult  ="Review pop-up page should display Overall Rating,Review Title,Review, Nickname, Email, age,gender,quality rating and value rating of the product in the My Review pop-up";
        //report.addPassLog(expectedResult,"Writting  and submit the review ",Common.getscreenShotPathforReport("submit the review "));
        }
        
        catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("verifying product review","User fill the all the infromation in reivew page", "User failed to fill the all the infromation in review page", Common.getscreenShotPathforReport("reviwPage"));
    		Assert.fail();
    		
    	}
        
        
        try{
        String sucesstext= Common.getText("xpath", "//span[contains(@class,'bv-submission-text')]");
        
       // Assert.assertEquals(sucesstext, "Your review was submitted!", "submit the Review");
        
        expectedResult="Click on post review button, it shouldshows Pop-up with text Your review was Submitted!";
        
       // int	tabelview=Common.findElements("id", "tab-title-reviews").size();
        Common.assertionCheckwithReport(sucesstext.equals("Your review was submitted!"), "verifying review success message", expectedResult, "Your review was submitted", "User missing filed valied data in review page");
        //(sucesstext.equals("Your review was submitted!"), "Your review was submitted", expectedResult,"User missing filed data in review page");
        
        //report.addPassLog(expectedResult,"Your review was Submitted",Common.getscreenShotPathforReport("Review"));
        }
        catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("verifying review success message","User submit the review", "User failed to fill the all the infromation in review page", Common.getscreenShotPathforReport("reviwPagesubmit"));
    		Assert.fail();
    		
    	}
	    }
        
	

   
   

	
     public void  recommendHydroFlask(int number){
	
	 int numbers=number+1;
	 Common.clickElement("xpath", "//span[contains(@class,'netpromoterscore-wrapper')]//li["+numbers+"]");
	}

	public void overallRating(String OverallRating) throws Exception{
		Thread.sleep(3000);
		System.out.println(OverallRating);
		switch (OverallRating) {
	
		case "poor":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-1')]");
			 Common.clickElement("xpath", "//a[contains(@id,'rating-1')]");
			break;
		case "Fair":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-2')]");
			 Common.clickElement("xpath", "//a[contains(@id,'rating-2')]");
			break;
		case "Average":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-3')]");
			 Common.clickElement("xpath", "//a[contains(@id,'rating-3')]");;
			 System.out.println("clicked");
			 
			break;
		case "Good":
			 Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-4')]");
			 Common.clickElement("xpath", "//a[contains(@id,'rating-4')]");
			break;
			
		case "Excellent":
			 Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-5')]");
			 Common.clickElement("xpath", "//a[contains(@id,'rating-5')]");
			break;
			
		
	}
			}
	
	public void qualityof_ProductRating(String ProductRating) throws Exception{
        
		
		switch (ProductRating) {
		
		case "poor":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'Quality-1')]");
			 Common.clickElement("xpath", "//a[contains(@id,'Quality-1')]");
			break;
		case "Fair":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'Quality-2')]");
			 Common.clickElement("xpath", "//a[contains(@id,'rating-2')]");
			break;
		case "Average":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'Quality-3')]");
			 Common.clickElement("xpath", "//a[contains(@id,'Quality-3')]");;
			break;
		case "Good":
			 Sync.waitElementPresent("xpath", "//a[contains(@id,'Quality-4')]");
			 Common.clickElement("xpath", "//a[contains(@id,'Quality-4')]");
			break;
			
		case "Excellent":
			 Sync.waitElementPresent("xpath", "//a[contains(@id,'Quality-5')]");
			 Common.clickElement("xpath", "//a[contains(@id,'Quality-5')]");
			break;
		}
		
	}
		
	
	
		public void valueof_productRating(String valueofProductRating) throws Exception{
	        
			
			switch (valueofProductRating) {
			
			case "poor":
				Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-1')]");
				 Common.clickElement("xpath", "//a[contains(@id,'rating_Value-1')]");
				break;
			case "Fair":
				Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-2')]");
				 Common.clickElement("xpath", "//a[contains(@id,'rating_Value-2')]");
				break;
			case "Average":
				Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-3')]");
				 Common.clickElement("xpath", "//a[contains(@id,'rating_Value-3')]");;
				break;
			case "Good":
				 Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-4')]");
				 Common.clickElement("xpath", "//a[contains(@id,'rating_Value-4')]");
				break;
				
			case "Excellent":
				 Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-5')]");
				 Common.clickElement("xpath", "//a[contains(@id,'rating_Value-5')]");
				break;
				
			
		}
			
			}
	public void selectAgeforReview(int Age) throws Exception{
		
		
		 		if(Age<=17){
		 			 Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");

		 			Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "17orUnder");
		         }
				
		 	   else if(Age<=24&&Age>17){
		 			 Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
	               Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "18to24");
	           }
              else if(Age>=25&&Age<=34){
		 			 Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
            	  Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "25to34");
                }

              else if(Age>=35&&Age<=44){
		 			 Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
            	  Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "35to44");
               }
		
              else if(Age>=45&&Age<=54){
		 			 Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
            	  Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "45to54");
              }
              else if(Age>=55&&Age<=64){
		 			 Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
            	  Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "55to64");
              }
              else if(Age<=65){
		 			 Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
            	  Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "65orOver");
              }
		  }
	
    public void selectGenderforReview(String gender) throws Exception{
    	
    	if(gender.equals("Male")){
			 Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Gender");
    		Common.dropdown("id", "bv-select-field-contextdatavalue_Gender", SelectBy.VALUE, "Male");
    	}
    	else if(gender.equals("Female")){
    		Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Gender");
    		Common.dropdown("id", "bv-select-field-contextdatavalue_Gender", SelectBy.VALUE, "Female");
    	}
    	 
    	 
    	
    }
	
	
	public void  Wouldyourecommendthiproduct(String recommendthiproduct) throws Exception{
		
		if(recommendthiproduct.equals("Yes")){
			Sync.waitElementPresent("xpath", "//label[contains(@id,'isrecommended-true')]");
			Common.clickElement("xpath", "//label[contains(@id,'isrecommended-true')]");
			
		
		}
		else if(recommendthiproduct.equals("No")){
			
		
			Sync.waitElementPresent("xpath", "//label[contains(@id,'isrecommended-false')]");
		Common.clickElement("xpath", "//label[contains(@id,'isrecommended-false')]");
	}}
	
	public void logOut() throws Exception{
		
		Thread.sleep(5000);
	try{
		String expectedResult="It should land on the signout page and redireted to homepage after 5 seconds.";
	
		Sync.waitElementPresent("xpath", "//li[contains(@class,'account-component')]/a");
		Common.mouseOverClick("xpath", "//li[contains(@class,'account-component')]/a");
		
		Sync.waitElementPresent("xpath", "//ul[contains(@class,'component-content')]/li[2]/a");
		Common.mouseOverClick("xpath", "//ul[contains(@class,'component-content')]/li[2]/a");
		ExtenantReportUtils.addPassLog("verifying logoout","Log out from aplication", "User log out from aplication", Common.getscreenShotPathforReport("logout"));
		
	}
	 catch(Exception |Error e) {
 		ExtenantReportUtils.addFailedLog("verifying logoout","user log from application", "User failed to log out from aplication", Common.getscreenShotPathforReport("logoutfailed"));
 		Assert.fail();
 		
 	}    
	/*	Thread.sleep(3000);
		int sizemessage=Common.findElements("xpath", "//div[@class='customer-logout-success-message']").size();
		
		Common.assertionCheckwithReport(sizemessage>0, "Successfully Log out from aplication", expectedResult,"User unabel logoutappliaction");*/
		
		}
		
	public void promationCode(String dataSet) throws Exception{
		
		String expectedResult="It should opens textbox input.";
	
		try{
		
		Sync.waitElementClickable("id", "discount-accordion");
		Common.clickElement("id", "discount-accordion");
		
		Sync.waitElementPresent("id","discount-code");
	
		
		Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Promationcode"));

	    int size=Common.findElements("id", "discount-code").size();
	    
	    Common.assertionCheckwithReport(size>0, "verifying the Promo Code label", expectedResult, "Successfully open the discount input box", "User unabel enter promationCode");
		//Common.assertionCheckwithReport(size>0, "Successfully open the discount input box", expectedResult,"User unabel enter promationCode");
		Sync.waitElementClickable("xpath", "//button[@class='action action-apply']");
		Common.clickElement("xpath", "//button[@class='action action-apply']");
		
		Thread.sleep(4000);
		expectedResult="It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
		String codetext=Common.getText("xpath", "//span[@class='rule-coupon-code']");
		Common.assertionCheckwithReport(codetext.equals(data.get(dataSet).get("Promationcode")), "verifying pomocode", expectedResult, "promotion code working as expected", "Promation code is not applied");
		
		//Common.assertionCheckwithReport(codetext.equals(data.get(dataSet).get("Promationcode")), "promotion code working as expected", expectedResult,"Promation code is not applied ");
		//Assert.assertEquals( data.get(dataSet).get("Promationcode"), codetext,"Promation code is not applied ");
		}
		
		catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("validating promo code", expectedResult, "User failed to proceed with promocode", Common.getscreenShotPathforReport("promocodefaield"));
    		//(expectedResult, "User failed to proceed with promocode ", Common.getscreenShotPathforReport(expectedResult));
    		Assert.fail();
    		
    	}
		
	//	report.addPassLog(expectedResult,"promotion code working as expected",Common.getscreenShotPathforReport("pomotion code"));
		}
		
		
	
	
	
	

	
	public void changeAddressIn_AddressBook(String dataSet) throws Exception{
		try{
		Sync.waitElementClickable("xpath", "//a[contains(text(),'Address Book')]");
		Common.clickElementStale("xpath", "//a[contains(text(),'Address Book')]");
		//report.addPassLog("click the my address book page ",Common.getscreenShotPathforReport("my address  book option"));
       
		ExtenantReportUtils.addPassLog("validating Address Book option", "navigate to my address book page", "successfully navigate to address book page",Common.getscreenShotPathforReport("addressbook pag"));
		
		}
		catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("validating Address Book option", "navigate to my address book page", "User failed to navigate my address book", Common.getscreenShotPathforReport("addressbook paget"));
    		//(expectedResult, "User failed to proceed with promocode ", Common.getscreenShotPathforReport(expectedResult));
    		Assert.fail();
    		
    	}
		 String PageTitle=Common.getText("xpath", "//h1[@class='page-title']/span");
        if(PageTitle.contains("New")){
        try{	
         Common.textBoxInput("xpath", "//input[@id='firstname']", data.get(dataSet).get("FirstName"));
		 Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(dataSet).get("LastName"));
		 Common.textBoxInput("xpath", "//input[@id='street[0]']", data.get(dataSet).get("Street"));
		try {
			Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));}
			catch (ElementClickInterceptedException e) {
			      Thread.sleep(3000);
				  Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("id", "postcode");
			Common.textBoxInput("id", "zip", data.get(dataSet).get("postcode"));
			Common.textBoxInput("id", "telephone", data.get(dataSet).get("phone"));
			Common.clickElement("xpath", "//button[@title='Save Address']");
			ExtenantReportUtils.addPassLog("validating Address Book from","Filled the shipping address for myaccount page",Common.getscreenShotPathforReport("fill the shipping address myaccount"));
     	}
        catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("validating Address Book from", "enter the valid address without any validation", "User failed to enter data in my address book", Common.getscreenShotPathforReport("faield to addressbook"));
    		//(expectedResult, "User failed to proceed with promocode ", Common.getscreenShotPathforReport(expectedResult));
    		Assert.fail();
    		
    	}
        }
		
	 else{
		Common.clickElementStale("xpath", "//a[contains(text(),'Change Billing Address')]");
		//report.addPassLog("click update button myaccount page",Common.getscreenShotPathforReport("edit the shipping address myaccount"));
		
		try{
		Common.textBoxInput("xpath", "//input[@id='firstname']", data.get(dataSet).get("FirstName"));
		
	//	ExtenantReportUtils.addPassLog("validating Address Book from","Filled the shipping address for myaccount page",Common.getscreenShotPathforReport("fill the shipping address myaccount"));
		
		
		Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(dataSet).get("LastName"));
		Common.textBoxInput("xpath", "//input[@id='street_1']", data.get(dataSet).get("Street"));
		try {
			Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));}
			catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			//Common.textBoxInputClear("id", "postcode");
			Common.textBoxInput("id", "zip", data.get(dataSet).get("postcode"));
			Common.textBoxInput("id", "telephone", data.get(dataSet).get("phone"));
			Common.clickElement("xpath", "//button[@title='Save Address']");
			Thread.sleep(6000);
			
			String Sucess=Common.getText("xpath","//div[contains(@class,'message-success')]/div");
			//Assert.assertEquals(Sucess, "You saved the address.", "Adress is saved");
			Common.assertionCheckwithReport(Sucess.equals("You saved the address."), "validating my address book with data", "enter the valid address without any validation", "successfully user enter the address", "User failed to enter data in my address book");
		}
			
		 catch(Exception |Error e) {
	    		ExtenantReportUtils.addFailedLog("validating my address book with data", "enter the valid address without any validation", "User failed to enter data in my address book", Common.getscreenShotPathforReport("faield to addressbookt"));
	    	    Assert.fail();
	    		
	    	}
		}
	}
	    
	
	
	public void SearchProduct(String dataSet) throws Exception{
		
		Sync.waitElementClickable("xpath", "//input[@id='search']");
		Common.clickElement("xpath", "//input[@id='search']");
		Common.textBoxInput("xpath", "//input[@id='search']",data.get(dataSet).get("sampleproduct"));
	    Common.clickElement("xpath", "//a[contains(text(),'"+data.get(dataSet).get("sampleproduct")+"')]");
		report.addPassLog("Clicking search button ",Common.getscreenShotPathforReport("search button"));
		
		
		
		
		
		//h4[text()='12 oz Cooler Cup']
		
		
		
		
	}
	
	public void myAccountInformation(){
		try{
		Sync.waitElementClickable("xpath", "//a[contains(text(),'Account Information')]");
	    Common.mouseOverClick("xpath", "//a[contains(text(),'Account Information')]");
	    ExtenantReportUtils.addPassLog("verifying my account button ","User click the my account button", "successfully click the my account button ", Common.getscreenShotPathforReport("my account button"));
	    
		}
		catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("verifying my account button", "User click the my account button", "faield to click the my account", Common.getscreenShotPathforReport("my account button"));
    		Assert.fail();
    		
    	}
	  
		
	}
	
	public void edit_Accountinfo(String dataSet) throws Exception{
		
		 CreateNewAccount(dataSet);
		 myAccountInformation();
		 Thread.sleep(5000);
		
		 try{
			 
			 Sync.waitElementClickable("xpath", "//button[@data-role='change-email']");
			 Thread.sleep(4000);
			 
		 Common.clickElement("xpath", "//button[@data-role='change-email']");
		 Thread.sleep(4000);
		 Sync.waitElementClickable("xpath", "//button[@data-role='change-password']");
		 Common.clickElement("xpath", "//button[@data-role='change-password']");
		 Thread.sleep(4000);
		 String change_Email=Common.genrateRandomEmail(data.get(dataSet).get("NewEmail"));
		 Common.textBoxInput("xpath", "//input[@id='email']", change_Email);
		 Common.textBoxInput("xpath", "//input[@id='current-password']", data.get(dataSet).get("Password"));
		 Common.textBoxInput("xpath", "//input[@id='password']", data.get(dataSet).get("Newpassword"));
		 Common.textBoxInput("xpath", "//input[@id='password-confirmation']", data.get(dataSet).get("Newpassword"));
		 ExtenantReportUtils.addPassLog("verifying the change password & email from","user enter the New logins", "Enter the new login infromation", Common.getscreenShotPathforReport("user enterchange password"));
		 Common.clickElement("xpath", "//button[contains(text(),'Save')]");
		 Common.textBoxInput("id", "email",change_Email);
		 }
		 catch(Exception |Error e) {
	    		ExtenantReportUtils.addFailedLog("verifying the change password & email from","User enter valid Email and password", "User failed to proceed to change email and passowrd ", Common.getscreenShotPathforReport("emailpasswordnew"));
	    		Assert.fail();
	    		
	    	}
		
		
	/*int size=	Common.findElements("xpath", "//input[@id='password-confirmation'").size();
		
		Common.assertionCheckwithReport(size>0, "Enter new email and password", "User enter new email and password", "failed to enter data");
		
		
		*/
		try{
		
		Common.textBoxInput("id", "pass",data.get(dataSet).get("Newpassword"));
	    Common.clickElement("xpath", "//button[@class='login-page-submit-action']");
		Thread.sleep(4000);
        Sync.waitElementPresent("xpath", "//span[@data-ui-id='page-title-wrapper']");
		Assert.assertEquals(Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']"), "My Account");
		Common.assertionCheckwithReport(Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']").equals("My Account"), "verifying new credentials", "user login with new login data", "User login with new logines", "unabel to login new user logines");
		//(Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']").equals("My Account"), "user login with new login data", "User login with new logines", Common.getscreenShotPathforReport("unabel to login new user logines"));
		 }
		 
		 catch(Exception |Error e) {
	    		ExtenantReportUtils.addFailedLog("verifying new credentials","User enter valid Email and password", "User failed to proceedchange email and passowrd ", Common.getscreenShotPathforReport("emailpassword"));
	    		Assert.fail();
	    		
	    	}
		
		//logOut();
		
		
		
		
		}
	
	public void minicart() throws Exception
	{
		Common.clickElement("xpath", "//a[@aria-label='minicart']");
		Thread.sleep(2000);
		Common.clickElement("xpath", "//a[contains(@class,'viewcart')]");
		
		Thread.sleep(10000);
		//div[contains(@class,'no-edit')]/a[2]
		//Sync.waitElementVisible("className", "checkout-step-title");
		//report.addPassLog("Clicked the checkout button",Common.getscreenShotPathforReport("checked out page"));
	   
		//div[contains(@class,'no-remove')]
		
		List<WebElement> elemtddds=Common.findElements("xpath", "//div[contains(@class,'no-remove')]/a[1]");
		
		elemtddds.get(elemtddds.size()-1).click();
		
		
		
		/*Common.clickElement("id", "block-discount-heading");
	Common.textBoxInput("id", "coupon_code", "h20");
	Common.clickElement("xpath", "//button[contains(@class,'cart-table-discount-apply')]");*/
	}
	
	public void EditProduct(){
		try{
			List<WebElement> elemtddds=Common.findElements("xpath", "//div[contains(@class,'no-remove')]/a[1]");
			
			elemtddds.get(elemtddds.size()-1).click();
		/*Thread.sleep(10000);
		Common.dropdown("xpath", "//select[@name='qty-mobile']", Common.SelectBy.VALUE, "2");
		ExtenantReportUtils.addPassLog("verifying Editproduct", "select the product quantity", "user select the Quntity", Common.getscreenShotPathforReport("passQuntitly"));
	   
		Common.clickElement("xpath", "//button[@title='Update Cart']");*/
		
		}
		 catch(Exception |Error e) {
			 e.printStackTrace();
	    		ExtenantReportUtils.addFailedLog("verifying Editproduct","User clickeditbutton navigate to product details page", "User failed to edit product or navigate to product detiles page", Common.getscreenShotPathforReport("editproduct"));
	    		Assert.fail();
	    		
	    	}
	}
	
	
	
	public void new_arrivals(String dataSet){
		
		
		Common.oppenURL(data.get(dataSet).get("newarrivals"));
		String bannerText=Common.getText("xpath", "//div[@class='description-banner-title']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));
		
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ", "give url contains https", "give url missing  https");

		Assert.assertTrue(bannerText.equals("New Arrivals"));
		
		Common.assertionCheckwithReport(bannerText.equals("New Arrivals"), "Give URL Contains Expected Templat ", "give url Navigating to new Arrivals link", "give url failed lo load");
		
		
		Assert.assertTrue(Common.getPageTitle().contains(bannerText));
		
		Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ", "Give URL Contains valid title ", "title checking");

		
	}
	public void trail_Series(String dataSet){
		Common.oppenURL(data.get(dataSet).get("trailseries"));
		String bannerText=Common.getText("xpath", "//div[@class='description-banner-title']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ", "give url contains https", "give url missing  https");;
		Assert.assertTrue(bannerText.equals("Less weight. More miles."));
		
		Common.assertionCheckwithReport(bannerText.equals("Less weight. More miles."), "Give URL Contains Expected Templat ", "give url Navigating to new trail Series link", "give url failed lo load");

		//System.out.println(Common.getPageTitle());
		
		
		
	//	Common.assertionCheckwithReport(Common.getPageTitle().contains("Hydro Flask | Trail Series"), "Give URL Contains Expected title ", "Give URL Contains valid title ", "title checking");		
		//report.addPassLog("Give URL Contains Expected title",Common.getscreenShotPathforReport("title checking"));
	}
	
	public void limited_edition(String dataSet){
		Common.oppenURL(data.get(dataSet).get("limitededition"));
		String bannerText=Common.getText("xpath", "//div[@class='description-banner-title']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));
		//report.addPassLog("this url contains https",Common.getscreenShotPathforReport("https"));
		
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ", "give url contains https", "give url missing  https");;
		
		//Assert.assertTrue(bannerText.equals("Limited Edition collections."));
		//report.addPassLog("Give URL Contains Expected Template",Common.getscreenShotPathforReport("Template checking"));
		
		Common.assertionCheckwithReport(bannerText.equals("Limited Edition collections."), "Give URL Contains Expected Templat ", "give url Navigating to new trail Series link", "give url failed lo load");
		
		
		System.out.println(Common.getPageTitle());
		//Assert.assertTrue(Common.getPageTitle().contains("Limited Edition Product | Hydro Flask"));
		
		
		
		Assert.assertTrue(Common.getPageTitle().contains("Limited Edition Product | Hydro Flask"));
		Common.assertionCheckwithReport(Common.getPageTitle().contains("Limited Edition Product | Hydro Flask"), "Give URL Contains Expected title ", "Give URL Contains valid title ", "title checking");		
		//report.addPassLog("Give URL Contains Expected title",Common.getscreenShotPathforReport("title checking"));
	}
	
	
	public void order_ppt(String category) throws Exception{
		    Thread.sleep(5000);
		 	String expectedResult="User should land on the home page";
		    int size=Common.findElements("xpath", "//a[@class='logo']").size();
		    Common.assertionCheckwithReport(size>0, "validating the home page ",expectedResult, "Successfully landed on the home page","User unabel to land on home page");
		    Sync.waitElementClickable("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]");
			Thread.sleep(4000);
			Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");	
			Thread.sleep(3000);
			expectedResult="User should click the"+category;
	}
	
	
	public void urls(String URL){
		Common.oppenURL(URL);
		String current=Common.getCurrentURL();
		String pagetitle =Common.getPageTitle();
		System.out.println(current+"---- present url");
		System.out.println(pagetitle+"--------page title");
	}

	public  HydroHelper(String datafile)
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
