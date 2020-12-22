package TestComponent.oxo;


import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import TestLib.Common;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.MailAPI;
import Utilities.PropertiesReader;

public class OxoHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();
	static ExtenantReportUtils report;

	/*public void navigateLoginPage()
	{
		
	}*/	
	
	
	public void clickLogo(){
		try{
			Thread.sleep(3000);
		String url=	Common.getCurrentURL();
		if(url.contains("blog")){
			Sync.waitPresenceOfElementLocated("xpath", "//img[@alt='oxo']");
			Common.clickElement("xpath", "//img[@alt='oxo']");
			
		}
		Sync.waitPresenceOfElementLocated("xpath", "//img[@title='OXO']");
		Common.clickElement("xpath", "//img[@title='OXO']");
		}
		  catch(Exception |Error e) {
			  e.printStackTrace();
		 		ExtenantReportUtils.addFailedLog("verifying logo button","click logo button land on home page", "User failed to click logo", Common.getscreenShotPathforReport("oxologo"));
		 		Assert.fail();
		 		
		 	}
	}
	
	public void CreateNewAccount(String dataSet) throws Exception
	{
		try{
		Thread.sleep(3000);
		Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
		Common.findElement("xpath", "//a[@class='social-login']").click();
		Thread.sleep(3000);
		ExtenantReportUtils.addPassLog("verifying Sign in link","lands on the account creation popup", "User lands on the account creation popup", Common.getscreenShotPathforReport("signbutton"));
		}
		  catch(Exception |Error e) {
		 		ExtenantReportUtils.addFailedLog("verifying Sign in link","lands on the account creation popup", "User failed lands on the account creation popup", Common.getscreenShotPathforReport("signbutton"));
		 		Assert.fail();
		 		
		 	}

	try{	
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
		ExtenantReportUtils.addPassLog("verifying Create account button","lands on the signin popup", "User successfully lands signin popup", Common.getscreenShotPathforReport("creatnow"));
	}
	catch(Exception |Error e) {
		e.printStackTrace();
 		ExtenantReportUtils.addFailedLog("verifying Create account button","lands on the signin popup", "User failed lands signin popup", Common.getscreenShotPathforReport("creatnow"));
 		Assert.fail();
 		
 		
 	}
	try{
		Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("id", "email_address_create", data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "password-social", data.get(dataSet).get("Password"));
		Common.textBoxInput("id", "password-confirmation-social", data.get(dataSet).get("Password"));
		ExtenantReportUtils.addPassLog("verifying sign up page with fieldData", "User enter the FieldData", "successfully enter the data", Common.getscreenShotPathforReport("sigData"));
		
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
	
		Common.clickElement("id", "button-create-social");
		Common.actionsKeyPress(Keys.PAGE_UP);
		
		int errormessagetextSize= Common.findElements("xpath", "//div[contains (text(),'required')]").size();
		if(errormessagetextSize<=0){
		}
		else{
			
			ExtenantReportUtils.addFailedLog("verifying sign up page with valid field data", "see the fields populated with the data", "User failed to proceed signUp form", Common.getscreenShotPathforReport("signupissue"));
			Assert.fail();
		}
		
		Common.actionsKeyPress(Keys.ESCAPE);
	}
		catch(Exception |Error e) {
			
    		ExtenantReportUtils.addFailedLog("verifying sign up page to Create new account","Sign up popup with valid Data", "User failed to proceed signUp form ", Common.getscreenShotPathforReport("signupissue"));
    		Assert.fail();
    		
    	}
	}	

	
	
	
	
	public void validatingSearchBoxWithOutData() throws Exception{
		String expectedResult="Click on the search button Lands on Global search page should show search textbox";
		String url=Common.getCurrentURL();
		Thread.sleep(5000);
		try{
		Common.clickElement("className", "search-tool");
		
		
		Sync.waitElementPresent("id", "search");
		if(Common.findElement("id", "search")==null)
		{
			Common.mouseOverClick("className", "search-tool");
			Thread.sleep(2000);
		}
		ExtenantReportUtils.addPassLog("validating search box", expectedResult, "successfully Click on the search button Lands on Global search page", Common.getscreenShotPathforReport("faieldopensearchbox"));
		}
		catch(Exception |Error e) {
		    	ExtenantReportUtils.addFailedLog("validating search box",expectedResult, "user faield to Click on the search button",Common.getscreenShotPathforReport("faieldopensearchbox"));
				Assert.fail();
				
			}
		try{
			expectedResult="It Should redirect to home page";
		
		Common.textBoxInput("id", "search", "");
		Common.actionsKeyPress(Keys.ENTER);
		Sync.waitPageLoad();
		String  presenturl=Common.getCurrentURL();
	if(presenturl.contains("#")){
		 presenturl=presenturl.replace("#", "");
	}
	System.out.println(presenturl);
		Common.assertionCheckwithReport(presenturl.equals(url), "validating the search with empty data", expectedResult, "user enter empty data and it redirect to home page", Common.getscreenShotPathforReport("user faield eneter empty data in search or redirect to home page"));
		}
		catch(Exception |Error e) {
	    	ExtenantReportUtils.addFailedLog("validating the search with empty data",expectedResult, "user faield eneter empty data in search or redirect to home page",Common.getscreenShotPathforReport("emptysearch"));
			Assert.fail();
			
		}
	}
	
	public void validatingSearchBoxWithNumberText(String productName) throws Exception{
		String expectedResult="Click on the search button Lands on Global search page should show search textbox";
		Thread.sleep(5000);
		try{
		Common.clickElement("className", "search-tool");
		
		
		Sync.waitElementPresent("id", "search");
		if(Common.findElement("id", "search")==null)
		{
			Common.mouseOverClick("className", "search-tool");
			Thread.sleep(2000);
		}
		ExtenantReportUtils.addPassLog("validating search box", expectedResult, "successfully Click on the search button Lands on Global search page", Common.getscreenShotPathforReport("faieldopensearchbox"));
		}
		catch(Exception |Error e) {
		    	ExtenantReportUtils.addFailedLog("validating search box",expectedResult, "user faield to Click on the search button",Common.getscreenShotPathforReport("faieldopensearchbox"));
				Assert.fail();
				
			}
		
		
		try{
			expectedResult="It should allow both text and numeric";
		
		Common.textBoxInput("id", "search", productName);
		Common.actionsKeyPress(Keys.ENTER);
		Sync.waitPageLoad();
		String Classname=Common.findElement("xpath", "//div[@id='ajax-layer-product-list-container']/div[1]").getAttribute("class");
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.assertionCheckwithReport(Classname.contains("results"), "validating the search with combination of latter Numbers data", expectedResult, "successfully search allow both text and numeric", "failed allow both text and numeric");
		}
		catch(Exception |Error e) {
	    	ExtenantReportUtils.addFailedLog("validating the search with combination of latter Numbers data",expectedResult, "user faield allowing combination of latter Numbers data",Common.getscreenShotPathforReport("serchnumbertext"));
			Assert.fail();
			
		}
	}
		
	
public void  validatingSearchProductInformation(String productName) throws Exception{
	String expectedResult="Click on the search button Lands on Global search page should show search textbox";
	Thread.sleep(5000);
	try{
	Common.clickElement("className", "search-tool");
	
	
	Sync.waitElementPresent("id", "search");
	if(Common.findElement("id", "search")==null)
	{
		Common.mouseOverClick("className", "search-tool");
		Thread.sleep(2000);
	}
	ExtenantReportUtils.addPassLog("validating search box", expectedResult, "successfully Click on the search button Lands on Global search page", Common.getscreenShotPathforReport("faieldopensearchbox"));
	}
	catch(Exception |Error e) {
	    	ExtenantReportUtils.addFailedLog("validating search box",expectedResult, "user faield to Click on the search button",Common.getscreenShotPathforReport("faieldopensearchbox"));
			Assert.fail();
			
		}
	
	
	try{
		
	expectedResult="It should contines homeTab ";
	Common.textBoxInput("id", "search", productName);
	Common.actionsKeyPress(Keys.ENTER);
	Sync.waitPageLoad();
	Thread.sleep(8000);
	String textelemt=Common.getText("xpath", "//ul[@class='items']/li[1]/a");
	Common.assertionCheckwithReport(textelemt.contains("Home"), "validating the search rueslt contines homeTab ", expectedResult, "search result it showing home tab", Common.getscreenShotPathforReport("faield to diaply hometab"));
	}
	catch(Exception |Error e) {
    	ExtenantReportUtils.addFailedLog("validating the search rueslt contines homeTab",expectedResult, "faield to display hometab",Common.getscreenShotPathforReport("homeTab"));
		Assert.fail();
		
	}
	String errormessage="product Namesis";
	try{
		
		
		  expectedResult="in search result should show product image, product title, price add wishlist.";
		int totalproductscount=Common.findElements("xpath", "//li[@class='item product product-item ']").size();
		
		int producttitles=Common.findElements("xpath", "//a[@class='product-item-link']").size();
		Assert.assertTrue(totalproductscount==producttitles);
		errormessage="wishlist button";
		int totlWishList=Common.findElements("xpath", "//a[@class='action towishlist']").size();
		Assert.assertTrue(totalproductscount==totlWishList);
		
		errormessage="product price";
		int productprice=Common.findElements("xpath","//span[contains(@id,'product-price')]").size();
		Assert.assertTrue(totalproductscount==productprice);
	
		
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		ExtenantReportUtils.addPassLog("validating the search rueslt", expectedResult, "it display all images,price wishlist ", Common.getscreenShotPathforReport("resultissues"));
	}
	catch(Exception |Error e) {
    	ExtenantReportUtils.addFailedLog("validating the search rueslt",expectedResult, "faield to display "+errormessage+"",Common.getscreenShotPathforReport("resultissues"));
		Assert.fail();
		
	}
	
	
	
}
	
//a[@class='product-item-link']
//a[@class='action towishlist']
//img[@class='product-image-photo']
	
	
	
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
		Sync.waitPageLoad();
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(3000);
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
	
		
        Common.scrollIntoView("xpath", "//a[contains(text(),'"+productname+"')]");
        Sync.waitElementClickable("xpath", "//a[@class='product-item-link'and contains(text(),'"+productname+"')]");
		Common.clickElement("xpath", "//a[contains(text(),'"+productname+"')]");
		System.out.println(Common.getPageTitle());
		Thread.sleep(5000);
		System.out.println(Common.getPageTitle());
	    Common.assertionCheckwithReport(Common.getPageTitle().equals(productname), "validating the product details page", expectedResult, "sucessfully navigated to product details page", "faield to navigate product details page");
		}
		 catch(Exception |Error e) {
			 e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the product details page",expectedResult, "user faield to navigate product detiles page",  Common.getscreenShotPathforReport("faield product detiles page"));
				Assert.fail();
				
			}
		
	}
	public void  addproducts(String quantity) throws Exception{
		
		int elementsize=Common.findElements("xpath", "//input[@class='sf-stepper-input']").size();
		
		if(elementsize>0){
		try{
		Thread.sleep(6000);
		Sync.scrollDownToView("xpath", "//input[@class='sf-stepper-input']");
		Common.textBoxInput("xpath", "//input[@class='sf-stepper-input']", quantity);
		Sync.waitElementPresent("xpath", "//input[@class='sf-stepper-input']");
		Common.clickElement("xpath","//button[@id='product-addtocart-button']");
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//span[text()='close']");
		Common.clickElement("xpath", "//span[text()='close']");
		
		ExtenantReportUtils.addPassLog("validating the product to Cart", "user add the product to cart", "user successfully add the product to cart", Common.getscreenShotPathforReport("usertheproducttocart"));
		}
		catch(Exception |Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product to Cart","user add the product to cart", "user faield to add the product to cart",Common.getscreenShotPathforReport("user faield to add the product to cart"));
			Assert.fail();
			
		}
		
		}
		else{
			try{	
				Thread.sleep(6000);
			   // Sync.waitElementPresent("xpath", "//input[@class='sf-stepper-input']");
				Sync.scrollDownToView("xpath", "//select[@id='qty']");
				Sync.waitElementPresent("xpath", "//select[@id='qty']");
				Common.dropdown("xpath","//select[@id='qty']", Common.SelectBy.VALUE, quantity);
				Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
				Common.clickElement("xpath","//button[@id='product-addtocart-button']");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[text()='close']");
				Common.clickElement("xpath", "//span[text()='close']");
				
				ExtenantReportUtils.addPassLog("validating the product to Cart", "user add the product to cart", "user successfully add the product to cart", Common.getscreenShotPathforReport("user faield to add the product to cart"));
			}
			catch(Exception |Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the product to Cart","user add the product to cart", "user faield to add the product to cart",Common.getscreenShotPathforReport("user faield to add the product to cart"));
			    Assert.fail();
				
			}
	}
	}
		
	public void clickMiniCart() throws Exception{
		Thread.sleep(6000);
		try{
			Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "//a[@class='action showcart']");
    	Common.clickCheckBox("xpath", "//a[@class='action showcart']");
    	int Subtotal=Common.findElements("xpath", "//div[@class='subtotal']").size();
        int productdetails=Common.findElements("xpath", "//div[@class='product-item-details']").size();
        Common.assertionCheckwithReport(Subtotal>0&&productdetails>0, "validating the mini cart HomePage", " This page contains Subtotal and productDetails", "this page having Subtotla and ProductDetailes", "this page missing Subtotal and productDetails");
		}
    	catch(Exception |Error e) {
    		e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the mini cart icon","user click cartbutton", "user faield to click cart",Common.getscreenShotPathforReport("fairldcart"));
		    Assert.fail();
			
		}
	}
	
	public void clickViewCart() throws Exception{
		Thread.sleep(6000);
		try{
			
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//a[contains(@class,'viewcart')]");
			Common.javascriptclickElement("xpath", "//a[contains(@class,'viewcart')]");
			//Common.mouseOverClick("xpath", "//a[contains(@class,'viewcart')]");
			//Common.assertionCheckwithReport(Subtotal>0&&productdetails>0, "validating the mini cart HomePage", " This page contains Subtotal and productDetails", "this page having Subtotla and ProductDetailes", "this page missing Subtotal and productDetails");
			ExtenantReportUtils.addPassLog("validating viewCart button", "Click on View cart link and navigate to Cart page", "user click the View cart button ", Common.getscreenShotPathforReport("Viewcart"));
		}
    	catch(Exception |Error e) {
    		e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating viewCart button","Click on View cart link and navigate to Cart page", "user faield to click View cart",Common.getscreenShotPathforReport("Viewcart"));
		    Assert.fail();
			
		}
	}
	
	
	
	public void CheckPost() throws Exception {
		Thread.sleep(5000);
		Sync.waitPageLoad();
	String Expectedresult="page contains image SKUid Product title sub tota";
	String ErrorMessage="";
		try{
		ErrorMessage="failed to display image";
		int productimage=Common.findElements("xpath", "//tr[@class='item-info']/td[1]//img").size();
		Assert.assertTrue(productimage>0);
		
		 ErrorMessage="failed to display productName";
		String productName=Common.findElement("xpath", "//strong[@class='product-item-name']/a").getText();
		Assert.assertTrue(productName!=null);
		
		ErrorMessage="failed to display SkuID ";
		String SkuID=Common.findElement("xpath","//div[@class='item-sku']/span[2]").getText();
		Assert.assertTrue(SkuID!=null);
		
		ErrorMessage="failed to display sub tota ";
		int subtotlas=Common.findElements("xpath","//td[contains(@class,'subtotal')]").size();
		Assert.assertTrue(subtotlas>0);
		ExtenantReportUtils.addPassLog("validating the Cart page with image SKU id Product title sub tota", Expectedresult, "this page display image SKUid Product title sub tota", Common.getscreenShotPathforReport("fairldcarrrt"));
		}
	
		catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("validating the Cart page with image SKU id Product title sub tota",Expectedresult, ErrorMessage,Common.getscreenShotPathforReport("fairldcarrrt"));
		    Assert.fail();
			
		}
		
		
		try{
			 Expectedresult=" On clicking on Quantity Dropdown should.It should open dropdown to increase or decrease of products";
			 ErrorMessage="failed to open Quantity Dropdown";
			 Sync.waitElementVisible("xpath", "//select[contains(@id,'cart')]");
			 Common.clickElement("xpath", "//select[contains(@id,'cart')]");
			 ExtenantReportUtils.addPassLog("validating the Cart page with increase or decrease of products", Expectedresult, "it click the Quantity Dropdown", Common.getscreenShotPathforReport("dropdown"));
		}
		catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("validating the Cart page with increase or decrease of products",Expectedresult, ErrorMessage,Common.getscreenShotPathforReport("dropdown"));
		    Assert.fail();
			
		}
		
		
		try{
			 Expectedresult="page should contain Discount codes";
			 ErrorMessage="page missing Discount codes";
			Common.clickElement("xpath","//strong[@id='block-discount-heading']");
			int sizecoupon=Common.findElements("id", "coupon_code").size();
			Assert.assertTrue(sizecoupon>0);
			ExtenantReportUtils.addPassLog("validating the Cart page with Coupon Code", Expectedresult, "this page contain Discount codes ", Common.getscreenShotPathforReport("discount"));
		    }
		catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("validating the Cart page with Coupon Code",Expectedresult, ErrorMessage,Common.getscreenShotPathforReport("discount"));
		    Assert.fail();
			
		}
	}

	//div[@class='subtotal']
	//*[@class='product-item-details']
	
	//a[contains(@class,'viewcart')]
	
	
	//div[@id='block-discount']
	
		////div[@class='cart-summary']
	
	
	
    public void checkout() throws Exception{
    	Sync.waitElementPresent("xpath", "//a[@class='action showcart']");
    	Common.clickCheckBox("xpath", "//a[@class='action showcart']");
    	Sync.waitElementPresent("id", "top-cart-btn-checkout");
    	Common.clickElement("id", "top-cart-btn-checkout");
    }
		
	public void clickThreebadmenu(){
		
		Common.clickElement("xpath", "//span[@class='action nav-toggle']");
	}
	
    
	public void clickBaby_Toddler() throws Exception{
		
		clickThreebadmenu();
		String expectedResult="It Should be navigate to the Baby_Toddler category page.";
		try{
		Thread.sleep(4000);
		
		
		Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15184']");
		Common.mouseOverClick("xpath", "//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15184']");
		//Common.clickElement("xpath", "//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15184']");
		
		//Common.clickElement("xpath","//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15184']‌");
		//Common.clickElement("xpath", "//a[@data-menu='menu-15184']");
		try{
	   Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15186']");
		}
		catch(Exception e){
			Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15184']");
		    Common.clickElement("xpath", "//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15184']");
		    Thread.sleep(5000);
		}
		ExtenantReportUtils.addPassLog("verifying category Baby_Toddler","lands on Baby_Toddler options", "User lands on the Baby_Toddler options", Common.getscreenShotPathforReport("faield to click"));
		
		Common.mouseOverClick("xpath", "//a[@data-menu='menu-15186']");
		
		
		Common.mouseOverClick("xpath", "//a[@data-menu='menu-15186']");
		
		}
       catch(Exception |Error e) {
    	   e.printStackTrace();
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
	
	public void addNewAddress(String dataSet){
	int AddnewAddress=Common.findElements("xpath", "//button[contains(@class,'action-show-popup')]").size();
		if(AddnewAddress>0){
			Common.clickElement("xpath", "//button[contains(@class,'action-show-popup')]");
			
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
				
				Common.clickElement("xpath", "//button[contains(@class,'action-save-address')]");
				
				int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
			    Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying shipping addres filling ", "user will fill the all the shipping", "user fill the shiping address click save button", "faield to add new shipping address");
				
			}
				catch(Exception |Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping", "faield to add new shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
					Assert.fail();
					
				}  
			
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
	 	Sync.waitPageLoad();
	 	Thread.sleep(5000);
    	Sync.waitElementPresent("xpath", "//label[contains(@id,'bestway_tablerate')]");
    	Common.scrollIntoView("xpath", "//label[contains(@id,'bestway_tablerate')]");
		Common.javascriptclickElement("xpath", "//label[contains(@id,'bestway_tablerate')]");
	 	Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "//button[contains(@class,'continue primary')]");
		Common.clickElement("xpath", "//button[contains(@class,'continue primary')]");
		ExtenantReportUtils.addPassLog("verifying Shipping Methods", "user select the Ground shipping method", " selected the Ground shipping method", Common.getscreenShotPathforReport("faieldsshippingmethod"));
    	}
    	catch(Exception |Error e) {
    		e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Shipping Methods", "user select the Ground shipping method", "faield to select shippingmethod",Common.getscreenShotPathforReport("faieldsshippingmethod"));
			Assert.fail();
			
		} 
	}
    public void clickAcceptingaddress() throws Exception{
    	try{
    		Sync.implicitWait();
    		int alertsize=Common.findElements("xpath", "//button[contains(@class,'action-accept')]").size();
    	if(alertsize>0){
    		Sync.waitElementPresent("xpath", "//button[contains(@class,'action-accept')]");
    	Common.clickElement("xpath", "//button[contains(@class,'action-accept')]");
    	}
    	}
    	catch(Exception |Error e) {
    		e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying confirmation address", "user acceptance given address", "faield to acceptance given address",Common.getscreenShotPathforReport("faieldcceptance"));
			Assert.fail();
			
		} 
    	
    	
    	
    }
    
  /*  public void edit_BillingAddress_registeredUser(){
    	
    	
    	Common.findElements("xpath", "//select[@name='billing_address_id']")
    	
    }
  */
    
    public void Edit_BillingAddress(String dataSet)throws Exception{
    	
    	try{
    	Common.clickElement("xpath","//label[contains(@for,'billing-address-same-as-shipping-paymetric')]");
    	
        int sizes=Common.findElements("xpath", "//select[@name='billing_address_id']").size();
        Common.actionsKeyPress(Keys.PAGE_UP);
		if(sizes>0){
			Thread.sleep(5000);
			Common.scrollIntoView("xpath", "//label[contains(@for,'billing-address-same-as-shipping-paymetric')]");
		    Common.actionsKeyPress(Keys.PAGE_UP);
		    Thread.sleep(3000);
		    Common.dropdown("xpath", "//select[@name='billing_address_id']",Common.SelectBy.TEXT, "New Address");
		}
			
			
	
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
	
	public void loginOxo(String dataSet) throws Exception
	{
		Thread.sleep(3000);
		int name=Common.findElements("xpath", "//span[@class='customer-name']").size();
		if(name>0){
			
		}
		else{
		try{
		Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
		Common.findElement("xpath", "//a[@class='social-login']").click();
		Sync.waitElementClickable(30, By.id("email"));
		if(Common.findElement("id", "email")==null)
		{
			Common.mouseOverClick("xpath", "//a[@class='social-login']");
			Thread.sleep(2000);
		}
		ExtenantReportUtils.addPassLog("verifying Sign in link","lands on sign popup", "User lands on the sign popup", Common.getscreenShotPathforReport("signpop"));
		}
		  catch(Exception |Error e) {
		 		ExtenantReportUtils.addFailedLog("verifying Sign in link","lands on sign popup", "User failed lands on sign popup", Common.getscreenShotPathforReport("signpop"));
		 		Assert.fail();
		 		
		 	}
try{
		Common.textBoxInput("id", "email",data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "pass1",data.get(dataSet).get("Password"));
		ExtenantReportUtils.addPassLog("verifying login page with fieldData", "User enter the FieldData", "successfully enter the data", Common.getscreenShotPathforReport("logindata"));
		Common.clickElement("id", "bnt-social-login-authentication");
		Thread.sleep(8000);
		int errormessagetextSize= Common.findElements("xpath", "//div[contains (text(),'required')]").size();
		if(errormessagetextSize<=0){
		}
		else{
			
			ExtenantReportUtils.addFailedLog("verifying login page with fieldData", "see the fields populated with the data", "User failed to proceed login form", Common.getscreenShotPathforReport("logindata"));
			Assert.fail();
		}
		
		
}

catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying login page","lands on sign popup", "User failed lands on sign popup", Common.getscreenShotPathforReport("signpoptt"));
		Assert.fail();
		
	}
		}
		/*try{
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
	//	ExtenantReportUtils.addFailedLog("verifying login page with credentials",expectedResult, "User failed to login in account  ", Common.getscreenShotPathforReport("login faield"));
		Assert.fail();
    }
		
		Common.textBoxInput("id", "email",data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "pass1",data.get(dataSet).get("Password"));
		Common.clickElement("id", "bnt-social-login-authentication");*/
		
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

	
	public void payPal_payment(String dataSet){
		String expectedResult="It should open paypal site window.";
		try{
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//input[@id='paypal_express']");
		Common.clickElement("xpath", "//input[@id='paypal_express']");
		Thread.sleep(5000);
    	Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.switchFrames("xpath", "//iframe[contains(@class,'zoid-component-frame')]"); 	
		Sync.waitElementClickable("xpath", "//div[contains(@class,'paypal-button-label-container')]");
		int sizes=Common.findElements("xpath", "//div[@class='paypal-button-label-container']").size();
		
		System.out.println(sizes);
		Thread.sleep(5000);
		Common.clickElement("xpath", "//div[contains(@class,'paypal-button-label-container')]");
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
		
	int ButtonSize=Common.findElements("id", "confirmButtonTop").size();
	if(ButtonSize>0){
		Common.clickElement("id", "confirmButtonTop");
	}else{
		Common.clickElement("id", "payment-submit-btn");
	}
		Thread.sleep(8000);
		Common.switchToFirstTab();
		}
		catch(Exception |Error e) {
			e.printStackTrace();
    		ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult, "User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
    	    Assert.fail();
          }
	}
	
	
	
	public void VerifyaingConformationPage() throws Exception{
		Thread.sleep(10000);
		Sync.waitElementPresent("xpath", "//h1[@class='page-title']/span");
		String Sucessmessage=Common.getText("xpath", "//h1[@class='page-title']/span");
		System.out.println(Sucessmessage);
		Common.assertionCheckwithReport(Sucessmessage.equals("Thank you for your order!"), "verifying order success message", "displaying the confirmation message", "successfully displaying the confirmation message","Failed displaying the confirmation message");
		
		//Thank you for your order!
		
		/*Sync.waitElementPresent("xpath", "//div[@id='registration']/div[2]/a/span");
		String CreateAccount=Common.getText("xpath","//div[@id='registration']/div[2]/a/span");
		Common.assertionCheckwithReport(CreateAccount.equals("Create Account"), "verifying Creat account button", "displaying the Creat account button", "successfully displaying the Creat account button","Failed displaying Creat account button");
		*/
	
		//Common.findElements("xpath", "//div[@class='order-help-item']/h4");
		
		
		int number=Common.findElements("xpath","//p[@class='order-number-wrapper']//strong").size();
		if(number>0){
			String ordernumber=Common.getText("xpath", "//p[@class='order-number-wrapper']//strong");
			System.out.println(ordernumber);
			PropertiesReader prop=new PropertiesReader();
			prop.properUpdate("OxoOrder.properties","OrderNumber_"+Common.getCurrentDate(),Common.getText("xpath", "//p[@class='order-number-wrapper']//strong"));
		}
		
		else{
		String ordernumber=Common.getText("xpath", "//p[@class='order-number-wrapper']/span");
		System.out.println(ordernumber);
	
		PropertiesReader prop=new PropertiesReader();
		prop.properUpdate("OxoOrder.properties","OrderNumber_"+Common.getCurrentDate(),Common.getText("xpath", "//p[@class='order-number-wrapper']/span"));
		// //div[@id="registration"]/div[2]/a Createaccount
		}
		
		//h1[@class='page-title']/span
		//Thank you for your order!
		
		//div[@class='order-help-item']/h4
		
		
		
		//div[contains(@class,'signup-columns')]//h2  Stay in the Loop.
		//form[contains(@class,'form subscribe')] Email
	}
	
	 public int checkadd(){
		 
	     Common.actionsKeyPress(Keys.PAGE_UP);
	        Common.switchFrames("xpath", "//iframe[@id='attentive_creative']");
	        int elementsize=  Common.findElements("xpath", "//button[@id='closeIconContainer']").size();
	        Common.switchToDefault();
	        return elementsize;
	        }
public void closetheadd() throws Exception{
	
	
	ExtenantReportUtils.addPassLog("verifying home page","lands on home page", "User lands on the Home page", Common.getscreenShotPathforReport("homepage"));
	
	Common.actionsKeyPress(Keys.PAGE_UP);
        Thread.sleep(10000);
       // Common.actionsKeyPress(Keys.PAGE_UP);
       
     
    	 // System.out.println(elementsize);
        
        
        
    
       if( checkadd()>0){
        
        Common.switchFrames("xpath", "//iframe[@id='attentive_creative']");
        Sync.waitElementPresent("xpath", "//button[@id='closeIconContainer']");
		Common.clickElement("xpath", "//button[@id='closeIconContainer']");
	    Common.switchToDefault();
    
       }
	}
 public void ForgotPassword(String dataset) throws Exception {
	 
	 
	 Thread.sleep(3000);
		try{
		Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
		Common.findElement("xpath", "//a[@class='social-login']").click();
		Sync.waitElementClickable(30, By.id("email"));
		if(Common.findElement("id", "email")==null)
		{
			Common.mouseOverClick("xpath", "//a[@class='social-login']");
			Thread.sleep(3000);
		}
		ExtenantReportUtils.addPassLog("verifying Sign in link","lands on sign page", "User lands on the sign popup", Common.getscreenShotPathforReport("signpopf"));
		}
		  catch(Exception |Error e) {
		 		ExtenantReportUtils.addFailedLog("verifying Sign in link","lands on sign popup", "User failed lands on sign popup", Common.getscreenShotPathforReport("signpopf"));
		 		Assert.fail();
		 		
		 	}
	 
	Thread.sleep(3000);
	String expectedResult="Forgot password pop up is displayed to customer";
	try{
	Common.clickElement("xpath","//a[@class='action remind']");
	Thread.sleep(3000);
	Common.textBoxInput("id", "email_address_forgot",data.get(dataset).get("Email"));
	Common.clickElement("id","bnt-social-login-forgot");
	Thread.sleep(5000);
	String message=Common.getText("xpath", "//div[contains(@class,'message-success')]/div");
	System.out.println(message);
	int size=Common.findElements("xpath", "//div[contains(@class,'message-success')]/div").size();
	//int size=Common.findElements("xpath", "//input[@id='email_address_forgot']").size();
//	Common.assertionCheckwithReport(size>0, "verifying forgetpassword option", expectedResult,"Successfully Forgot password pop up is displayed to customer", "User faield to get  Forgot password pop");
	Common.assertionCheckwithReport(size>0, "verifying forgetpassword option", expectedResult,"Successfully Forgot password pop up is displayed to customer", "User faield to get  Forgot password pop");
    }
        catch(Exception |Error e) {
        	e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying forgetpassword option","clcik the forget password option", "User failed to clcik the forget password button", Common.getscreenShotPathforReport("forgetpasswordbuttonfaield"));
		Assert.fail();
	}
	
	/*String emailID="mahendra.lotuswave@gmail.com";
	String password="Mahendra@123";
	String fromAddress="Akhil Meesarakonda <meesarakonda.akhil9@gmail.com>";
	String toAddress="Mahendra.lotuswave@gmail.com";
	String subject="Fwd: Reset your OXO password";
	String content="Set a New Password";*/
	
	System.out.println(data.get(dataset).get("Email"));
	System.out.println(data.get(dataset).get("Password"));
	System.out.println(data.get(dataset).get("fromAddress"));
	System.out.println(data.get(dataset).get("toAddress"));
	System.out.println(data.get(dataset).get("subject"));
	System.out.println(data.get(dataset).get("content"));
	
	try{
	//String email=MailAPI.gmailAPI(data.get(dataset).get("Email"),data.get(dataset).get("Password"),data.get(dataset).get("fromAddress"), data.get(dataset).get("toAddress"), data.get(dataset).get("subject"), data.get(dataset).get("content"));
	//System.out.println(email.split("Set a New Password")[1].split("<")[1].split(">")[0].trim());
	
	}
	
	catch(Exception |Error e) {
    	e.printStackTrace();
	ExtenantReportUtils.addFailedLog("verifying forgetpassword email","clcik the forget password link in email", "User not getting EMail", Common.getscreenShotPathforReport("emailFaield"));
	Assert.fail();
	}
	
}




//span[@class='customer-name active']

 
 public void ProductRegistration() throws InterruptedException {
 	Thread.sleep(3000);
 	try {
 	Sync.scrollDownToView("xpath","//a[@data-menu='menu-14883']");
 	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14883']"));
 	ExtenantReportUtils.addPassLog("Validating ProductRegistration Link","ProductRegistration link should be able to click" ,"Clicked on ProductRegistration Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
 	Common.clickElement("xpath","//a[@data-menu='menu-14883']");
 	Thread.sleep(3000);
 	WebElement element=Common.findElement("xpath","//h2[contains(text(),'Select your product')]");
 	String text=element.getText();
 	if(text.contains("Select your product")){
       ExtenantReportUtils.addPassLog("Validating Webtext of Product Registration Page", "Expected text should be obtained","Expected text is obtained",Common.getscreenShotPathforReport("ProductRegistrationText") );
       }
   else{
         ExtenantReportUtils.addFailedLog("Validating Webelement of Product Registration Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation Product Registration");
          Assert.fail();            
      }
 	}
 	 catch(Exception e) {
  		ExtenantReportUtils.addFailedLog("Validating  of Product Registration Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation Product Registration");
  		Assert.fail();    
  	 }
 	
 	
	   Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https", "Product Registration URL should Contains https", "Product Registration URL contains https", "Product Registration URL missing https");
	   //Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/productregistration")&&Common.getPageTitle().equals("Product Registration"));
       Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/productregistration")&&Common.getPageTitle().equals("Product Registration"),"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
 
 	
    
 }
 
 public void ContactUS() throws InterruptedException {
	   	Thread.sleep(3000);
	   	clickLogo();
	   	try {
	   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-14881']");
	   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14881']"));
	   	ExtenantReportUtils.addPassLog("Validating ContactUS Link","ContactUS link should be present" ,"ContactUS Link is present",Common.getscreenShotPathforReport("ContactUSLink"));
	   	Common.clickElement("xpath","//a[@data-menu='menu-14881']");
	   	
	    WebElement element=Common.findElement("xpath","//h2[contains(text(),'Email us')]");
	    String text=element.getText();
	  
	    if(text.contains("Email us"))
	                {
	    	       
	    	        ExtenantReportUtils.addPassLog("Validating Webtext of ContactUS Page", "Expected text should be obtained","Expected text is obtained",Common.getscreenShotPathforReport("ContactUSText") );
	                }
	    	 else{
	               
	               ExtenantReportUtils.addFailedLog("Validating Webelement of ContactUS Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation ContactUS");
	               Assert.fail(); 
	    	 }
	    
		}
	   	
	       catch(Exception e) {
	    	   ExtenantReportUtils.addFailedLog("Validating Webelement of ContactUS Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation ContactUS");
	    	   Assert.fail(); 
	       }
	    
	    Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https", "ContactUS URL should Contains https", "ContactUS URL contains https", "ContactUS URL missing https");	
	   // Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL which contains https", "This URL Contains https", "give url contains https", "give url missing  https");
		 Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/contact-us")&&Common.getPageTitle().equals("Contact Us"),"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
	   	}
	   	
	      
 public void FAQ() throws InterruptedException {
	   	Thread.sleep(3000);
	   	try {
	   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-14882']");
	   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14882']"));
	   	ExtenantReportUtils.addPassLog("Validating FAQ Link","FAQ link should be present" ,"FAQ Link is present",Common.getscreenShotPathforReport("FAQLink"));
	   	//ExtenantReportUtils.addPassLog("Validating FAQ Link","FAQ link should be able to click" ,"Clicked on PFAQ Link",Common.getscreenShotPathforReport("FAQ Link"));
	   	Common.clickElement("xpath","//a[@data-menu='menu-14882']");
	    WebElement element=Common.findElement("xpath","//span[contains(text(),'Frequently Asked Questions')]");
	    String text=element.getText();
	    System.out.println("Text Obtained is..."+text);
	    if(text.contains("Frequently Asked Questions"))
	                {
	    	        System.out.println("Expected text is obtained");
	    	        ExtenantReportUtils.addPassLog("Validating Webtext of FAQ Page", "Expected text should be obtained","Expected text is obtained",Common.getscreenShotPathforReport("FAQText") );
	                }
	    	 else{
	              System.out.println("Expected text is not obtained");
	              ExtenantReportUtils.addFailedLog("Validating Webelement of FAQ Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation FAQ");
	              Assert.fail();
	    	 }}
	    catch(Exception e) {
	    	  ExtenantReportUtils.addFailedLog("Validating Webelement of FAQ Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation FAQ");
              Assert.fail();
	       }

	    
	    Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https", "FAQ URL should Contains https", "FAQ URL contains https", "FAQ URL missing https");	
	   // Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL which contains https", "This URL Contains https", "give url contains https", "give url missing  https");
			
	      // Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/faq")&&Common.getPageTitle().equals("FAQ"));
	       Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/faq")&&Common.getPageTitle().equals("FAQ"),"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
	   	}
	   	
 public void VoluntaryRecall() throws InterruptedException {
	   	Thread.sleep(3000);
	   	try {
	   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-14884']");
	   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14884']"));
	   	ExtenantReportUtils.addPassLog("Validating VoluntaryRecall Link","VoluntaryRecall link should be present" ,"VoluntaryRecall Link is present",Common.getscreenShotPathforReport("VoluntaryRecallLink"));
	   	//ExtenantReportUtils.addPassLog("Validating ProductRegistration Link","ProductRegistration link should be able to click" ,"Clicked on ProductRegistration Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
	   	Common.clickElement("xpath","//a[@data-menu='menu-14884']");
			//Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL which contains https", "This URL Contains https", "give url contains https", "give url missing  https");
	    WebElement element=Common.findElement("xpath","//span[contains(text(),'Tot Nest Booster Seat Voluntary Recall')]");
	    String text=element.getText();
	    System.out.println("Text Obtained is"+   text);
	    if(text.contains("Tot Nest Booster Seat Voluntary Recall"))
	                {
	            	 System.out.println("Expected text is obtained");
	            	 ExtenantReportUtils.addPassLog("Validating Webtext of VoluntaryRecall Page", "Expected text should be obtained","Expected text is obtained",Common.getscreenShotPathforReport("VoluntaryRecallText") );
	                }
	    	 else{
	             System.out.println("Expected text is not obtained");
	             ExtenantReportUtils.addFailedLog("Validating Webelement of VoluntaryRecall Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation VoluntaryRecall");
	         Assert.fail();    
	    	 }
	    
		}
	   	
	       catch(Exception e) {
	    	   ExtenantReportUtils.addFailedLog("Validating Webelement of VoluntaryRecall Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation VoluntaryRecall");
	    	   Assert.fail(); 
	       }
	      Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https", "VoluntaryRecall URL should Contains https", "VoluntaryRecall URL contains https", "VoluntaryRecall URL missing https");
		  Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/product-recall")&&Common.getPageTitle().equals("Product Recall"),"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
	   
	   }
 public void PrivacyPolicy() throws InterruptedException {
	   	Thread.sleep(3000);
	   	try {
	   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-14885']");
	   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14885']"));
		ExtenantReportUtils.addPassLog("Validating PrivacyPolicy Link","PrivacyPolicy link should be present" ,"PrivacyPolicy Link is present",Common.getscreenShotPathforReport("PrivacyPolicyLink"));
	   	//ExtenantReportUtils.addPassLog("Validating ProductRegistration Link","ProductRegistration link should be able to click" ,"Clicked on ProductRegistration Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
	   	Common.clickElement("xpath","//a[@data-menu='menu-14885']");
	    WebElement element=Common.findElement("xpath","//span[contains(text(),'Privacy Policy')]");
	    	    String text=element.getText();
	    	    System.out.println("Text Obtained is"+   text);
	    	    if(text.contains("Privacy Policy"))
	    	                {
	    	    	        System.out.println("Expected text is obtained");
	    	    	        ExtenantReportUtils.addPassLog("Validating Webtext of PrivacyPolicy Page", "Expected text should be obtained","Expected text is obtained",Common.getscreenShotPathforReport("PrivacyPolicyText") );
	    	                }
	    	    	 else{
	    	             System.out.println("Expected text is not obtained");
	    	             ExtenantReportUtils.addFailedLog("Validating Webelement of PrivacyPolicy Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation PrivacyPolicy");
	    	             Assert.fail();    
	    	    	 }
	   	}
	   	
	       catch(Exception e) {
	    	   ExtenantReportUtils.addFailedLog("Validating Webelement of PrivacyPolicy Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation PrivacyPolicy");
	    	   Assert.fail();
	    	   
	       }
	    	    
	    	    Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https", "PrivacyPolicy URL should Contains https", "PrivacyPolicy URL contains https", "PrivacyPolicy URL missing https");
			//Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL which contains https", "This URL Contains https", "give url contains https", "give url missing  https");
			
	     // Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/privacy")&&Common.getPageTitle().equals("Privacy Policy"));
	       Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/privacy")&&Common.getPageTitle().equals("Privacy Policy"),"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
	   	
	   }

 public void TermsandConditions() throws InterruptedException {
	   	Thread.sleep(3000);
	   	try {
	   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-14886']");
	   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14886']"));
	   	ExtenantReportUtils.addPassLog("TermsandConditions Link","TermsandConditions link should be present" ,"TermsandConditions Link is present",Common.getscreenShotPathforReport("TermsandConditionsLink"));
	   	//ExtenantReportUtils.addPassLog("Validating ProductRegistration Link","ProductRegistration link should be able to click" ,"Clicked on ProductRegistration Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
	   	Common.clickElement("xpath","//a[@data-menu='menu-14886']");
	    WebElement element=Common.findElement("xpath","//span[contains(text(),'Terms & Conditions')]");
	    String text=element.getText();
	    System.out.println("Text Obtained is"+   text);
	    if(text.contains("Terms & Conditions"))
	                {
	    	        System.out.println("Expected text is obtained");
	    	        ExtenantReportUtils.addPassLog("Validating Webtext of TermsandConditions Page", "Expected text should be obtained","Expected text is obtained",Common.getscreenShotPathforReport("TermsandConditionsText") );
	                }
	    	 else{
	             System.out.println("Expected text is not obtained");
	             ExtenantReportUtils.addFailedLog("Validating Webelement of TermsandConditions Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation TermsandConditions");
	             Assert.fail();    
	    	 }
	    
	   	}
	   	
	       catch(Exception e) {
	    	   ExtenantReportUtils.addFailedLog("Validating Webelement of TermsandConditions Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation TermsandConditions");
	    	   Assert.fail();
	       }
	    
	    Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https", "TermsandConditions URL should Contains https", "TermsandConditions URL contains https", "TermsandConditions URL missing https");
	   	   //Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL which contains https", "This URL Contains https", "give url contains https", "give url missing  https");
		
	     //  Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/Terms")&&Common.getPageTitle().equals("Terms and Conditions"));
	       Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/Terms")&&Common.getPageTitle().equals("Terms and Conditions"),"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
	   	
	   }

 public void TrackOrder() throws InterruptedException {
	   	Thread.sleep(3000);
	   	try {
	   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-14888']");
	   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14888']"));
	   	ExtenantReportUtils.addPassLog("TrackOrder Link","TrackOrder link should be present" ,"TrackOrder Link is present",Common.getscreenShotPathforReport("TrackOrderLink"));
	   	//ExtenantReportUtils.addPassLog("Validating ProductRegistration Link","ProductRegistration link should be able to click" ,"Clicked on ProductRegistration Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
	   	Common.clickElement("xpath","//a[@data-menu='menu-14888']");
	    WebElement element=Common.findElement("xpath","//span[contains(text(),'Track Order')]");
	    String text=element.getText();
	    System.out.println("Text Obtained is..."+   text);
	    if(text.contains("Track Order"))
	                {
	    	        System.out.println("Expected text is obtained");
	    	        ExtenantReportUtils.addPassLog("Validating Webtext of TrackOrder Page", "Expected text should be obtained","Expected text is obtained",Common.getscreenShotPathforReport("TrackOrderText") );
	                }
	    	 else{
	               System.out.println("Expected text is not obtained");
	               ExtenantReportUtils.addFailedLog("Validating Webelement of TrackOrder Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation TrackOrder");
	               Assert.fail();
	    	 }	
	    
	   	}
	   	
	       catch(Exception e) {
	    	   ExtenantReportUtils.addFailedLog("Validating Webelement of TrackOrder Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation TrackOrder");
	         Assert.fail();
	       }
	    Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https", "TrackOrder URL should Contains https", "TrackOrder URL contains https", "TrackOrder URL missing https");
	   	//Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL which contains https", "This URL Contains https", "give url contains https", "give url missing  https");
			System.out.println(Common.getPageTitle());
	       System.out.println(Common.getCurrentURL());
	    //  Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/sales/guest/form/")&&Common.getPageTitle().equals("Orders and Returns"));
	      Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/sales/guest/form/")&&Common.getPageTitle().equals("Orders and Returns"),"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
	   	
	   }





 public void ShippingInformation() throws InterruptedException {
	   	Thread.sleep(3000);
	   	try {
	   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-14889']");
	   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14889']"));
	   	ExtenantReportUtils.addPassLog("ShippingInformation Link","ShippingInformation link should be present" ,"ShippingInformation Link is present",Common.getscreenShotPathforReport("ShippingInformationLink"));
	   //ExtenantReportUtils.addPassLog("Validating ProductRegistration Link","ProductRegistration link should be able to click" ,"Clicked on ProductRegistration Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
	   	Common.clickElement("xpath","//a[@data-menu='menu-14889']");
	    WebElement element=Common.findElement("xpath","//h2[contains(text(),'Shipping Policies')]");
	     String text=element.getText();
	     System.out.println("Text Obtained is..."+   text);
	     if(text.contains("Shipping Policies"))
	                 {
	     	         System.out.println("Expected text is obtained");
	     	         ExtenantReportUtils.addPassLog("Validating Webtext of ShippingInformation Page", "Expected text should be obtained","Expected text is obtained",Common.getscreenShotPathforReport("ShippingInformationText") );
	                 }
	     	 else{
	             System.out.println("Expected text is not obtained");
	             ExtenantReportUtils.addFailedLog("Validating Webelement of ShippingInformation Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation ShippingInformation");
	             Assert.fail();  
	     	 }	
	   	}
	   	
	       catch(Exception e) {
	    	   ExtenantReportUtils.addFailedLog("Validating Webelement of ShippingInformation Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation ShippingInformation");
	           Assert.fail();
	       }
	     Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https", "ShippingInformation URL should Contains https", "ShippingInformation URL contains https", "ShippingInformation URL missing https");
	   	  //Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL which contains https", "This URL Contains https", "give url contains https", "give url missing  https");
		   System.out.println(Common.getPageTitle());
	       System.out.println(Common.getCurrentURL());
	      // Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/shipping")&&Common.getPageTitle().equals("Shipping"));
	       Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/shipping")&&Common.getPageTitle().equals("Shipping"),"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
	   	
	   }

 public void BetterGuarantee() throws InterruptedException {
	   	Thread.sleep(3000);
	   	try {
	   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-14890']");
	   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14890']"));
	   	ExtenantReportUtils.addPassLog("BetterGuarantee Link","BetterGuarantee link should be present" ,"BetterGuarantee Link is present",Common.getscreenShotPathforReport("BetterGuaranteeLink"));
	   	//ExtenantReportUtils.addPassLog("Validating ProductRegistration Link","ProductRegistration link should be able to click" ,"Clicked on ProductRegistration Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
	   	Common.clickElement("xpath","//a[@data-menu='menu-14890']");
	    WebElement element=Common.findElement("xpath","//span[contains(text(),'The OXO Better Guarantee*')]");
	    String text=element.getText();
	    System.out.println("Text Obtained is..."+   text);
	    if(text.contains("The OXO Better Guarantee*"))
	                {
	    	        System.out.println("Expected text is obtained");
	    	        ExtenantReportUtils.addPassLog("Validating Webtext of BetterGuarantee Page", "Expected text should be obtained","Expected text is obtained",Common.getscreenShotPathforReport("BetterGuaranteeText") );
	                }
	    	 else{
	             System.out.println("Expected text is not obtained");
	             
	             ExtenantReportUtils.addFailedLog("Validating Webelement of BetterGuarantee Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation BetterGuarantee");
	             Assert.fail();    
	    	 }	
	    
	   	}
	   	
	       catch(Exception e) {
	    	   ExtenantReportUtils.addFailedLog("Validating Webelement of BetterGuarantee Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation BetterGuarantee");
	             Assert.fail();
	       }
	    Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https", "BetterGuarantee URL should Contains https", "BetterGuarantee URL contains https", "BetterGuarantee URL missing https");
	  // 	Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL which contains https", "This URL Contains https", "give url contains https", "give url missing  https");
			System.out.println(Common.getPageTitle());
	       System.out.println(Common.getCurrentURL());
	    //   Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/guarantee")&&Common.getPageTitle().equals("The OXO Better Guarantee"));
	       Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/guarantee")&&Common.getPageTitle().equals("The OXO Better Guarantee"),"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
	   	
	   }

 public void GoodTipsBlog() throws InterruptedException {
	   	Thread.sleep(3000);
	   	clickLogo();
	   	try {
	   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-14892']");
	   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14892']"));
	   	ExtenantReportUtils.addPassLog("GoodTipsBlog Link","GoodTipsBlog link should be present" ,"GoodTipsBlog Link is present",Common.getscreenShotPathforReport("GoodTipsBlogLink"));
	   	//ExtenantReportUtils.addPassLog("Validating ProductRegistration Link","ProductRegistration link should be able to click" ,"Clicked on ProductRegistration Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
	   	Common.clickElement("xpath","//a[@data-menu='menu-14892']");
	   	Thread.sleep(5000);
	
	   	Sync.waitElementPresent("Xpath", "//h1[contains(text(),'Good Tips')]");
	    WebElement element=Common.findElement("xpath","//h1[contains(text(),'Good Tips')]");
	    String text=element.getText();
	    System.out.println("Text Obtained is..."+   text);
	    if(text.contains("Good Tips"))
	                {
	    	        System.out.println("Expected text is obtained");
	    	        ExtenantReportUtils.addPassLog("Validating Webtext of GoodTipsBlog Page", "Expected text should be obtained","Expected text is obtained",Common.getscreenShotPathforReport("GoodTipsBlogText") );
	                }
	    	 else{
	                      System.out.println("Expected text is not obtained");
	                      ExtenantReportUtils.addFailedLog("Validating Webelement of GoodTipsBlog Page", "Expected text should not be obtained","Expected text is not obtained", "GoodTipsBlog Product Registration");
	         Assert.fail();            
	    	 }
	    
	   	}
	   	
	       catch(Exception e) {
	    	   ExtenantReportUtils.addFailedLog("Validating Webelement of GoodTipsBlog Page", "Expected text should not be obtained","Expected text is not obtained", "GoodTipsBlog Product Registration");
		         Assert.fail();
	       }
	   	Thread.sleep(2000);
	   	Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https", "GoodTipsBlog URL should Contains https", "GoodTipsBlog URL contains https", "GoodTipsBlog URL missing https");
			//Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL which contains https", "This URL Contains https", "give url contains https", "give url missing  https");
			System.out.println(Common.getPageTitle());
	       System.out.println(Common.getCurrentURL());
	      // Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/blog/")&&Common.getPageTitle().equals("OXO Good Tips - The OXO Blog"));
	       Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/blog/")&&Common.getPageTitle().equals("OXO Good Tips - The OXO Blog"),"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
	   	
	   }



 
	
	public void InventorSubmissions() throws InterruptedException {
	   	Thread.sleep(3000);
	 	clickLogo();
	   	try {
	   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-14893']");
	   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14893']"));
	   	ExtenantReportUtils.addPassLog("InventorSubmissions Link","InventorSubmissions link should be present" ,"InventorSubmissions Link is present",Common.getscreenShotPathforReport("InventorSubmissionsLink"));
	   //	ExtenantReportUtils.addPassLog("Validating ProductRegistration Link","ProductRegistration link should be able to click" ,"Clicked on ProductRegistration Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
	   	Common.clickElement("xpath","//a[@data-menu='menu-14893']");
	    WebElement element=Common.findElement("xpath","//span[contains(text(),'Inventor Submission')]");
	    String text=element.getText();
	    System.out.println("Text Obtained is..."+   text);
	    if(text.contains("Inventor Submission"))
	                {
	    	        System.out.println("Expected text is obtained");
	    	        ExtenantReportUtils.addPassLog("Validating Webtext of InventorSubmissions Page", "Expected text should be obtained","Expected text is obtained",Common.getscreenShotPathforReport("InventorSubmissionsText") );
	                }
	    	 else{
	              System.out.println("Expected text is not obtained");
	              ExtenantReportUtils.addFailedLog("Validating Webelement of InventorSubmissions Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation InventorSubmissions");
	             Assert.fail();     
	    	 }	
	   	}
	   	
	       catch(Exception e) {
	    	   ExtenantReportUtils.addFailedLog("Validating Webelement of InventorSubmissions Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation InventorSubmissions");
	             Assert.fail();    
	       }
	    Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https", "InventorSubmissions URL should Contains https", "InventorSubmissions URL contains https", "InventorSubmissions URL missing https");
	   //	Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL which contains https", "This URL Contains https", "give url contains https", "give url missing  https");
			System.out.println(Common.getPageTitle());
	       System.out.println(Common.getCurrentURL());
	      // Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/inventor")&&Common.getPageTitle().equals("Inventor Submission"));
	       Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/inventor")&&Common.getPageTitle().equals("Inventor Submission"),"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
	   	
	   }
	
	public void Careers() throws InterruptedException {
	   	Thread.sleep(3000);
	 	clickLogo();
	   	try {
	   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-14894']");
	   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14894']"));
	   	ExtenantReportUtils.addPassLog("Careers Link","Careers link should be present" ,"Careers Link is present",Common.getscreenShotPathforReport("CareersLink"));
	   	//ExtenantReportUtils.addPassLog("Validating ProductRegistration Link","ProductRegistration link should be able to click" ,"Clicked on ProductRegistration Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
	   	Common.clickElement("xpath","//a[@data-menu='menu-14894']");
	   	Thread.sleep(2000);
	   	Common.switchWindows();
	   	Sync.waitElementPresent("xpath", "//span[contains(text(),'Search for Jobs')]");
	     WebElement element=Common.findElement("xpath","//span[contains(text(),'Search for Jobs')]");
	     String text=element.getText();
	     System.out.println("Text Obtained is..."+   text);
	     if(text.contains("Search for Jobs"))
	                 {
	     	         System.out.println("Expected text is obtained");
	     	        ExtenantReportUtils.addPassLog("Validating Webtext of Careers Page", "Expected text should be obtained","Expected text is obtained",Common.getscreenShotPathforReport("CareersText") );
	                 }
	     	 else{
	             System.out.println("Expected text is not obtained");
	             ExtenantReportUtils.addFailedLog("Validating Webelement of Careers Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation Careers");
	             Common.switchToFirstTab();
	             Assert.fail();
	     	 }
	   	}
	   	
	       catch(Exception e) {
	    	   ExtenantReportUtils.addFailedLog("Validating Webelement of Careers Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation Careers");
	    	   Common.switchToFirstTab();
	    	   Assert.fail();
	       }
	   	Thread.sleep(2000);
	   	Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https", "Careers URL should Contains https", "Careers URL contains https", "Careers URL missing https");
			//Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL which contains https", "This URL Contains https", "give url contains https", "give url missing  https");
		
	       Thread.sleep(6000);
	      // Assert.assertTrue(Common.getCurrentURL().equals("https://helenoftroy.wd1.myworkdayjobs.com/Main_HoT")&&Common.getPageTitle().equals("Search for Jobs"));
	       Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://helenoftroy.wd1.myworkdayjobs.com/Main_HoT")&&Common.getPageTitle().equals("Search for Jobs"),"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
	       Common.switchToFirstTab();
	   }



	public void InvestorRelations() throws InterruptedException {
	   	Thread.sleep(3000);
	 	clickLogo();
	   	try {
	   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-14895']");
	   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14895']"));
	   	ExtenantReportUtils.addPassLog("InvestorRelations Link","InvestorRelations link should be present" ,"InvestorRelations Link is present",Common.getscreenShotPathforReport("InvestorRelationsLink"));
	   	//ExtenantReportUtils.addPassLog("Validating ProductRegistration Link","ProductRegistration link should be able to click" ,"Clicked on ProductRegistration Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
	   	Common.clickElement("xpath","//a[@data-menu='menu-14895']");
	   	Thread.sleep(2000);
	   	Common.switchWindows();
	   	//Thread.sleep(4000);
	   	Sync.waitElementPresent("xpath", "//span[contains(text(),'Why Helen of Troy')]");
	    WebElement element=Common.findElement("xpath","//span[contains(text(),'Why Helen of Troy')]");
	    String text=element.getText();
	    System.out.println("Text Obtained is..."+   text);
	    if(text.contains("Why Helen of Troy"))
	                {
	    	        System.out.println("Expected text is obtained");
	    	        ExtenantReportUtils.addPassLog("Validating Webtext of InvestorRelations Page", "Expected text should be obtained","Expected text is obtained",Common.getscreenShotPathforReport("InvestorRelationsText") );
	                }
	    	 else{
	              System.out.println("Expected text is not obtained");
	              ExtenantReportUtils.addFailedLog("Validating Webelement of InvestorRelations Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation InvestorRelations");
	              Common.switchToFirstTab();
	              Assert.fail();    
	    	 }
	   	}
	   	
	       catch(Exception e) {
	    	   ExtenantReportUtils.addFailedLog("Validating Webelement of InvestorRelations Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation InvestorRelations");
	    	   Common.switchToFirstTab();
	    	   Assert.fail();
	       }
	    
	       Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https", "InvestorRelations URL should Contains https", "InvestorRelations URL contains https", "InvestorRelations URL missing https");
	       Thread.sleep(3000);
	       Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://investor.helenoftroy.com/overview/default.aspx")&&Common.getPageTitle().equals("Helen of Troy Limited - Overview"),"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
	   	   Common.switchToFirstTab();
	   }





	
public void logOut() throws Exception{
		
		Thread.sleep(5000);
	try{
		
	
		Sync.waitElementPresent("xpath", "//span[@class='customer-name']");
		Common.mouseOverClick("xpath", "//span[@class='customer-name']");
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//li[contains(@class,'customer-welcome')]//li[2]/a");
		Common.mouseOverClick("xpath", "//li[contains(@class,'customer-welcome')]//li[2]/a");
		ExtenantReportUtils.addPassLog("verifying logoout","Log out from aplication", "User log out from aplication", Common.getscreenShotPathforReport("logout"));
		
	}
	 catch(Exception |Error e) {
 		ExtenantReportUtils.addFailedLog("verifying logoout","user log from application", "User failed to log out from aplication", Common.getscreenShotPathforReport("logoutfailed"));
 		Assert.fail();
 		
 	}    
	
		}
public void Instagram() throws InterruptedException {
   	Thread.sleep(3000);
   	try {
   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-11486']");
   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-11486']"));
   	Common.clickElement("xpath","//a[@data-menu='menu-11486']");
   	Thread.sleep(2000);
	Common.switchWindows();
   	Thread.sleep(3000);
   	WebElement element=Common.findElement("xpath","//h2[contains(text(),'oxo')]");
   	String text=element.getText();
   	if(text.contains("oxo")){
   			    ExtenantReportUtils.addPassLog("Validating Webelement of Instagram Page", "Expected text should be obtained","Expected text is obtained", "Instagram LinkValidation ");
               }
   	       else{
                 ExtenantReportUtils.addFailedLog("Validating Webelement of Instagram Page", "Expected text should not be obtained","Expected text is not obtained", "Instagram LinkValidation");
                 Common.switchToFirstTab();
                 Assert.fail();        
   	         }
           	}
   	
       catch(Exception |Error e) {
    	   ExtenantReportUtils.addFailedLog("Validating Webelement of Instagram Page", "Expected text should not be obtained","Expected text is not obtained", "Instagram LinkValidation");
           Common.switchToFirstTab();
    	  
           }
	       Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating Instagram URL which contains https", "This URL Contains https", "give url contains https", "give url missing  https");
           Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://www.instagram.com/oxo/")&&Common.getPageTitle().equals("OXO (@oxo) • Instagram photos and videos"),"Validating Instagram Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
           Common.switchToFirstTab();
   	
   }
public void YouTube() throws InterruptedException {
   	Thread.sleep(3000);
   	try {
   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-11489']");
   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-11489']"));
   	ExtenantReportUtils.addPassLog("Validating YouTube Link","" ,"",Common.getscreenShotPathforReport("YouTubeLink"));
   	Common.clickElement("xpath","//a[@data-menu='menu-11489']");
   	
   	Thread.sleep(2000);
	Common.switchWindows();
   	Thread.sleep(3000);
   	}
   	/*WebElement element=Common.findElement("xpath","//div[contains(text(),'Playlists')]");
   	String text=element.getText();
   	System.out.println("Text Obtained is"+   text);
   	if(text.contains("Playlists")){
  	                 ExtenantReportUtils.addPassLog("Validating Webelement of YouTube Channel", "Expected text should be obtained","Expected text is obtained", "YouTube LinkValidation ");
               }
   	         else{
                     ExtenantReportUtils.addFailedLog("Validating Webelement of YouTube Channel", "Expected text should not be obtained","Expected text is not obtained", "YouTube LinkValidation");
                     Assert.fail();
   	         }
   	}*/
   	
       catch(Exception e) {
    	   ExtenantReportUtils.addFailedLog("Validating  of YouTube Channel", "Expected text should not be obtained","Expected text is not obtained", "YouTube LinkValidation");
    	   Assert.fail();
    	   Common.switchToFirstTab();
       }
      	//Assert.assertTrue(Common.getCurrentURL().contains("https"));
	   Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating  YouTube URL which contains https", "This URL Contains https", "give url contains https", "give url missing  https");
	   System.out.println(Common.getPageTitle());
      System.out.println(Common.getCurrentURL());
     // Assert.assertTrue(Common.getCurrentURL().equals("https://www.youtube.com/oxo")&&Common.getPageTitle().equals("OXO (@oxo) • Instagram photos and videos"));
    
   	Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://www.youtube.com/oxo")&&Common.getPageTitle().equals("OXO - YouTube"),"Validating Page  YouTube Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
   	Common.closeCurrentWindow();
	   Common.switchToFirstTab();  
}

	
public void pinterest() throws InterruptedException {
   	Thread.sleep(3000);
   	try {
   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-11488']");
   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-11488']"));
   	Common.clickElement("xpath","//a[@data-menu='menu-11488']");
   	Thread.sleep(2000);
	Common.switchToSecondTab();
   	Thread.sleep(3000);
   	WebElement element=Common.findElement("xpath","//span[contains(text(),'Following')]");
   	String text=element.getText();
   	System.out.println("Text Obtained is"+   text);
   	if(text.contains("Following")){
                 ExtenantReportUtils.addPassLog("Validating Webtext of pinterest", "Expected text should be obtained","Expected text is obtained", "pinterest LinkValidation ");
               }
   	         else{
                     ExtenantReportUtils.addFailedLog("Validating Webtext of pinterest", "Expected text should not be obtained","Expected text is not obtained", "pinterest LinkValidation");
                     Assert.fail();
   	         }
   	}
   	
       catch(Exception e) {
    	   e.printStackTrace();
    	   ExtenantReportUtils.addFailedLog("Validating Webtext of pinterest", "Expected text should not be obtained","Expected text is not obtained", "pinterest LinkValidation");
    	   Assert.fail();
       }
	   Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating pinterest URL which contains https", "This URL Contains https", "give url contains https", "give url missing  https");
      //Assert.assertTrue(Common.getCurrentURL().equals("https://in.pinterest.com/oxo/_shop/")&&Common.getPageTitle().equals("OXO (oxo) on Pinterest"));
   	Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://www.pinterest.com/oxo/")&&Common.getPageTitle().equals("OXO (oxo) on Pinterest"),"Validating pinterest Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
   	Common.closeCurrentWindow();
	Common.switchToFirstTab();
}


public void Twitter() throws InterruptedException {
   	Thread.sleep(3000);
   	try {
   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-11490']");
   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-11490']"));
   	Common.clickElement("xpath","//a[@data-menu='menu-11490']");
   	Thread.sleep(2000);
	Common.switchWindows();
   	Thread.sleep(3000);
   	WebElement element=Common.findElement("xpath","//span[contains(text(),'New to Twitter?')]");
   	String text=element.getText();
   	System.out.println("Text Obtained is"+   text);
   	if(text.contains("New to Twitter?")){
                 ExtenantReportUtils.addPassLog("Validating Webelement of Twitter Page", "Expected text should be obtained","Expected text is obtained", "Twitter LinkValidation ");
               }
   	         else{
                     ExtenantReportUtils.addFailedLog("Validating Webelement of Twitter Page", "Expected text should not be obtained","Expected text is not obtained", "Twitter LinkValidation");
                     Assert.fail();
                     }
          	}
   	
       catch(Exception e) {
    	   ExtenantReportUtils.addFailedLog("Validating Webelement of Twitter Page", "Expected text should not be obtained","Expected text is not obtained", "Twitter LinkValidation");
    	   Assert.fail();
       }
   	System.out.println(Common.getPageTitle());
	   Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating Twitter URL which contains https", "This URL Contains https", "given url contains https", "give url missing  https");
     // Assert.assertTrue(Common.getCurrentURL().equals("https://www.instagram.com/oxo/")&&Common.getPageTitle().equals("OXO (@oxo) • Instagram photos and videos"));
    	Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://twitter.com/OXO")&&Common.getPageTitle().equals("Profile / Twitter"),"Validating  Twitter Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
    	
    	Common.closeCurrentWindow();
 	   Common.switchToFirstTab();
}

public void Facebook() throws InterruptedException {
   	Thread.sleep(3000);
   	try {
   	Sync.scrollDownToView("xpath","//a[@data-menu='menu-11487']");
   	Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-11487']"));
   	Common.clickElement("xpath","//a[@data-menu='menu-11487']");
   	Thread.sleep(2000);
	Common.switchWindows();
   	Thread.sleep(3000);
   	WebElement element=Common.findElement("id","seo_h1_tag");
   	String text=element.getText();
   	//System.out.println("Text Obtained is"+   text);
   	if(text.contains("Kitchen/Cooking")){
   			
                 System.out.println("Expected text is obtained");
                 ExtenantReportUtils.addPassLog("Validating Webelement of Facebook Page", "Expected text should be obtained","Expected text is obtained", "Facebook LinkValidation ");
               }
   	         else{
                     System.out.println("Expected text is not obtained");
                     
                     ExtenantReportUtils.addFailedLog("Validating Webelement of Facebook Page", "Expected text should not be obtained","Expected text is not obtained", "Facebook LinkValidation");
                    Assert.fail();       
   	         }
   	}
   	
    catch(Exception e) {
    	e.printStackTrace();
    	 ExtenantReportUtils.addFailedLog("Validating Webelement of Facebook Page", "Expected text should not be obtained","Expected text is not obtained", "Facebook LinkValidation");
    	
    	Assert.fail();
    }
      	
	   Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating Facebook URL which contains https", "This URL Contains https", "give url contains https", "give url missing  https");
	   Common.assertionCheckwithReport(Common.getCurrentURL().equals("https://www.facebook.com/OXO")&&Common.getPageTitle().equals("OXO - Home | Facebook"),"Validating Facebook Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
	   Common.closeCurrentWindow();
	   Common.switchToFirstTab();
   	
   }




public void ClickMyAccount(){
	try{
		Sync.waitElementPresent("xpath", "//span[@class='customer-name']");
		Common.mouseOverClick("xpath", "//span[@class='customer-name']");
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//li[contains(@class,'customer-welcome')]//li[1]/a");
		Common.mouseOverClick("xpath", "//li[contains(@class,'customer-welcome')]//li[1]/a");
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "//h1[@data-element='hero_title']");
        String userName=Common.getText("xpah", "//h1[@data-element='hero_title']");
	    Common.assertionCheckwithReport(userName.concat("Hi"),"verifying my account link","User lands on the my accountpage","User lands on AccountPage", "Faield lo land o nmyaccount");
	
	}
	catch(Exception |Error e) {
		e.printStackTrace();
 		ExtenantReportUtils.addFailedLog("verifying my account link","lands on AccountPage", "User failed lands on my accountpage", Common.getscreenShotPathforReport("account"));
 		Assert.fail();
 		
 	}
	
	
	}

public void myaccountvalidation(){
try{
		
	Sync.waitElementPresent("xpath", "//div[@id='account-nav']//li[1]/a");
	Common.clickElement("xpath", "//div[@id='account-nav']//li[1]/a");
	Common.actionsKeyPress(Keys.PAGE_DOWN);
	Sync.waitElementPresent("xpath", "//h1[@class='page-title']/span");
    String accountinfro=Common.getText("xpath", "//h1[@class='page-title']/span");
	Common.assertionCheckwithReport(accountinfro.equals("Account Dashboard"), "Validating my account option","it open the my account Dashboard", "successfully open the my account Dashboard", "it faield open the myaccount dashboard");
	}
	 catch(Exception e) {
  	   ExtenantReportUtils.addFailedLog("Validating my account option", "it open the my account Dashboard", "it faield open the myaccount dashboard",Common.getscreenShot("faieldmyaccount"));
  	   Assert.fail();
     }
}


public void myaOrders(){
try{
		
	Sync.waitElementPresent("xpath", "//div[@id='account-nav']//li[2]/a");
	Common.clickElement("xpath", "//div[@id='account-nav']//li[2]/a");
	Common.actionsKeyPress(Keys.PAGE_DOWN);
	Sync.waitElementPresent("xpath", "//h1[@class='page-title']/span");
    String accountinfro=Common.getText("xpath", "//h1[@class='page-title']/span");
	Common.assertionCheckwithReport(accountinfro.equals("My Orders"), "Validating  my aOrders option","it open the myaOrders Dashboard", "successfully open the myaOrders Dashboard", "it faield open the myaOrders dashboard");
	}
	 catch(Exception e) {
  	   ExtenantReportUtils.addFailedLog("Validating myaOrders option", "it open the myaOrders Dashboard", "it faield open the myaOrders dashboard",Common.getscreenShot("faieldmyaOrders"));
  	   Assert.fail();
     }
}
/*
public void myaOrders(){
try{
		
	Sync.waitElementPresent("xpath", "//div[@id='account-nav']//li[3]/a");
	Common.clickElement("xpath", "//div[@id='account-nav']//li[3]/a");
	Common.actionsKeyPress(Keys.PAGE_DOWN);
	Sync.waitElementPresent("xpath", "//h1[@class='page-title']/span");
    String accountinfro=Common.getText("xpath", "//h1[@class='page-title']/span");
	Common.assertionCheckwithReport(accountinfro.equals("My Orders"), "Validating  my aOrders option","it open the myaOrders Dashboard", "successfully open the myaOrders Dashboard", "it faield open the myaOrders dashboard");
	}
	 catch(Exception e) {
  	   ExtenantReportUtils.addFailedLog("Validating myaOrders option", "it open the myaOrders Dashboard", "it faield open the myaOrders dashboard",Common.getscreenShot("faieldmyaOrders"));
  	   Assert.fail();
     }
}*/
public void pagewalidation() throws Exception{
	
	Thread.sleep(5000);
try{
	

	Sync.waitElementPresent("xpath", "//span[@class='customer-name']");
	Common.mouseOverClick("xpath", "//span[@class='customer-name']");
	Thread.sleep(5000);
	Sync.waitElementPresent("xpath", "//li[contains(@class,'customer-welcome')]//li[1]/a");
	Common.mouseOverClick("xpath", "//li[contains(@class,'customer-welcome')]//li[1]/a");
	ExtenantReportUtils.addPassLog("verifying logoout","Log out from aplication", "User log out from aplication", Common.getscreenShotPathforReport("logout"));
	
}
 catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying logoout","user log from application", "User failed to log out from aplication", Common.getscreenShotPathforReport("logoutfailed"));
		Assert.fail();
		
	}    
    }


///////////////////////////**********//////////////

public void clickGoodTipsBlog(){
	 String expectedResult="It Should be navigate Goodtipsblog page";
	try{
		Thread.sleep(5000);
		Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15196']");
		Common.clickElement("xpath", "//a[@data-menu='menu-15196']");
	   //Common.assertionCheckwithReport(Common.getCurrentURL().equals(data.get(dataSet).get("URL"))&&Common.getPageTitle().equals(data.get(dataSet).get("pageTitle")),"Validating  GoodTipsBlog page Title and URL", "it navigating to GoodTipsBlog page", "user navigated to GoodTipsBlog Page", "user faield to navigate to good tipes blog");
		ExtenantReportUtils.addPassLog("validating the GoodTipsBlog page.", expectedResult, "user click the GoodTipsblog", Common.getscreenShotPathforReport("passgood"));
	}
   catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("validating the GoodTipsBlog page.", expectedResult, "user failed to navigate GoodTipsBlog",Common.getscreenShotPathforReport("GoodTipsBlogfaield"));
		    Assert.fail();
		}
	
}

public void signButton_GoodTipsBlog() throws Exception{
    Thread.sleep(4000);
	String expectedResult="click the sign button navigating to signpage";
	try{
	Sync.waitElementClickable("xpath", "//a[@class='sign-in mob-hidden']");
	Common.clickElement("xpath","//a[@class='sign-in mob-hidden']");
	Sync.waitElementPresent("xpath", "//input[@id='email-social']");
    int emailsize=Common.findElements("xpath", "//input[@id='email-social']").size();
	
    Common.assertionCheckwithReport(emailsize>0, "validating the signButton", expectedResult, "user successfully click the sign button", "Faield to click the signbutton");
	}
	 catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("validating the GoodTipsBlog sign button.", expectedResult, "user failed to navigate GoodTipsBlog",Common.getscreenShotPathforReport("GoodTipsBlogfaield"));
		    Assert.fail();
		}
}
public void login_GoodTipsBlog(String dataSet) throws Exception{
	
	
	clickGoodTipsBlog();
	signButton_GoodTipsBlog();
	try{
		Common.textBoxInput("xpath", "//input[@id='email-social']",data.get(dataSet).get("Email"));
		Common.textBoxInput("xpath", "//input[@id='pass-social']",data.get(dataSet).get("Password"));
		
		ExtenantReportUtils.addPassLog("verifying login page with fieldData", "User enter the FieldData", "successfully enter the data", Common.getscreenShotPathforReport("logindatagood"));
	    Sync.waitPageLoad();
	    Thread.sleep(8000);
		Common.clickElement("xpath", "//button[contains(@class,'login primary')]");
		Thread.sleep(8000);
		int errormessagetextSize= Common.findElements("xpath", "//div[contains (text(),'required')]").size();
		if(errormessagetextSize<=0){
		}
		else{
			
			ExtenantReportUtils.addFailedLog("verifying login page with fieldData", "see the fields populated with the data", "User failed to proceed login form", Common.getscreenShotPathforReport("logindata"));
			Assert.fail();
		}
		
		
}

catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying login page","lands on sign popup", "User failed lands on sign popup", Common.getscreenShotPathforReport("signpoptt"));
		Assert.fail();
		
	}
}

public void CreateAccount_GoodTipsBlog(String dataSet) throws Exception{
	clickGoodTipsBlog();
	signButton_GoodTipsBlog();
	Thread.sleep(5000);
	try{
	Sync.waitElementClickable(30, By.xpath("//a[@class='action create primary']"));
	Common.clickElement("xpath", "//a[@class='action create primary']");
	
	ExtenantReportUtils.addPassLog("verifying Create account button","lands on the Creating account popup", "User successfully lands createaccount popup", Common.getscreenShotPathforReport("creatnow"));
	}
	catch(Exception |Error e) {
 		ExtenantReportUtils.addFailedLog("verifying Create account button","lands on the  Creating account", "User failed lands Creating account popup", Common.getscreenShotPathforReport("creatnow"));
 		Assert.fail();
	}
	  try {
			//Sync.waitElementClickable("xpath", "//span[text()='Create Account']");
		  Thread.sleep(5000);
			Common.textBoxInput("xpath", "//form[@id='form-validate']//input[@name='firstname']", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='form-validate']//input[@name='lastname']", data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='form-validate']//input[@id='email_address']", data.get(dataSet).get("Email"));
			Common.textBoxInput("xpath", "//form[@id='form-validate']//input[@id='password']", data.get(dataSet).get("Password"));
			Common.textBoxInput("xpath", "//form[@id='form-validate']//input[@id='password-confirmation']", data.get(dataSet).get("Password"));
			Common.actionsKeyPress(Keys.PAGE_DOWN);
		    Sync.waitPageLoad();
		    Thread.sleep(5000);
			Common.clickElement("xpath", "//button[contains(@class,'submit primary')]");
			int errormessagetextSize= Common.findElements("xpath", "//div[contains (text(),'required')]").size();
			if(errormessagetextSize<=0){
			}
			else{
				
				ExtenantReportUtils.addFailedLog("verifying Createaccount up page with valid field data", "see the fields populated with the data", "User failed to proceed Create account form", Common.getscreenShotPathforReport("signupissuecr"));
				Assert.fail();
			}
			
			Common.actionsKeyPress(Keys.ESCAPE);
		}
			catch(Exception |Error e) {
				e.printStackTrace();
				
	    		ExtenantReportUtils.addFailedLog("verifying sign up page to Create new account","Sign up popup with valid Data", "User failed to proceed Create account form ", Common.getscreenShotPathforReport("signupissuecr"));
	    		Assert.fail();
	    		
	    	}
		}	
 	




public void GoodTipsBlogPage() throws Exception {
	   String expectedResult="It Should be navigate to the selected category page.";
		try{
		Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15196']");
		Common.clickElement("xpath", "//a[@data-menu='menu-15196']");
		
		
		}
   catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("validating the category page.", expectedResult, "user failed to navigate",  Common.getscreenShotPathforReport("failed to navigate categorypage"));
		    Assert.fail();
		}
		
		//Thread.sleep(3000);
		Sync.waitElementClickable("xpath", "//a[@class='sign-in mob-hidden']");
		Common.clickElement("xpath","//a[@class='sign-in mob-hidden']");
}


public void forgetpasswordGoodTipsBlock(String dataset) throws Exception{
	clickGoodTipsBlog();
	signButton_GoodTipsBlog();
	Thread.sleep(3000);
	String expectedResult="Forgot password pop up is displayed to customer";
	try{
		
	Common.clickElement("xpath","//a[@class='action remind']");
	Sync.waitPageLoad();
	Thread.sleep(7000);
	Common.textBoxInput("id", "email_address_forgot",data.get(dataset).get("Email"));
	ExtenantReportUtils.addPassLog("verifying user enter email", "user enter email","User successfully enter email", Common.getscreenShotPathforReport("emailforg"));
	Common.clickElement("id","bnt-social-login-forgot");
	Thread.sleep(5000);
	String message=Common.getText("xpath", "//div[contains(@class,'message-success')]/div");
	System.out.println(message);
	int size=Common.findElements("xpath", "//div[contains(@class,'message-success')]/div").size();
	//int size=Common.findElements("xpath", "//input[@id='email_address_forgot']").size();
//	Common.assertionCheckwithReport(size>0, "verifying forgetpassword option", expectedResult,"Successfully Forgot password pop up is displayed to customer", "User faield to get  Forgot password pop");
	Common.assertionCheckwithReport(size>0, "verifying forgetpassword option", expectedResult,"Successfully Forgot password pop up is displayed to customer", "User faield to get  Forgot password pop");
    }
        catch(Exception |Error e) {
        	e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying forgetpassword option","clcik the forget password option", "User failed to clcik the forget password button", Common.getscreenShotPathforReport("forgetpasswordbuttonfaield"));
		Assert.fail();
	}
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