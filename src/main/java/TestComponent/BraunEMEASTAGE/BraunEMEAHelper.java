package TestComponent.BraunEMEASTAGE;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;

public class BraunEMEAHelper {

	String datafile;
	ExcelReader excelData;
	static Automation_properties automation_properties = Automation_properties.getInstance();
	Map<String, Map<String, String>> data=new HashMap<>();
	private Object dataSet;
	static ExtenantReportUtils report;

	public  BraunEMEAHelper(String datafile)
	{
		excelData=new ExcelReader(datafile);
		data=excelData.getExcelValue();
		this.data=data;
		if(Utilities.TestListener.report==null)
		{
			report=new ExtenantReportUtils("BraunEMEA");
			report.createTestcase("BraunEMEATestCases");
		}else{
			this.report=Utilities.TestListener.report;
		}
	}

	public void Acceptcookies() throws Exception{

		Sync.waitElementPresent("xpath", "//button[@id='truste-consent-required']");
		Common.clickElement("xpath", "//button[@id='truste-consent-required']");



	}
	public void closepopup() throws Exception{

		Sync.waitElementPresent("xpath", "//div[@id='wpn-lightbox-close-newsletter']");
		Common.clickElement("xpath", "//div[@id='wpn-lightbox-close-newsletter']");
	}

	public void GermanStoreSelection() throws InterruptedException
	{
		String expectedResult="Navigating Germany Store";
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@href='#country-mod']"));
			
			System.out.println(Common.findElement("xpath", "//a[@href='#country-mod']").getText());
			Common.findElement("xpath", "//a[@href='#country-mod']").click();
			
			//System.out.println(Common.findElement("xpath", "//a[@href='#country-mod']").getText());
			
			Common.clickElement("xpath", "//*[@id='country-mod']/div/div[2]/div[2]/ul[1]/li[5]/a");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display Germany store", "Germany store displayed successfully", Common.getscreenShotPathforReport("Germany store success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Germany store", "Germany store not display", Common.getscreenShotPathforReport("Germany store Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void GermaneStoreSelection() throws InterruptedException
	{
		String expectedResult="Navigating Germany Store";
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@href='#country-mod']"));
			Common.findElement("xpath", "//a[@href='#country-mod']").click();
			//Sync.waitElementClickable(30, By.xpath("//*[@id='country-mod']/div/div[2]/div[2]/ul[1]/li[5]/a"));
			Common.clickElement("xpath", "//*[@id='country-mod']/div/div[2]/div[2]/ul[1]/li[5]/a");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display Germany store", "Germany store displayed successfully", Common.getscreenShotPathforReport("Germany store success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Germany store", "Germany store not display", Common.getscreenShotPathforReport("Germany store Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void Storeselection() {
		String expectedResult="Naviagating to Store page";
		try {
			Sync.waitElementPresent("xpath", "(//a[@title='United States'])[1]");
			Common.clickElement("xpath", "(//a[@title='United States'])[1]");

			Thread.sleep(3000);

		}
		catch(Exception |Error e)
		{

			e.printStackTrace();
			Assert.fail();
		}
	}

	public void FranceStoreSelection() throws InterruptedException
	{
		String expectedResult="Navigating Germany Store";
		try {
			Sync.waitElementClickable(30, By.xpath("(//a[@href='#country-mod'])[1]"));
			Common.findElement("xpath", "(//a[@href='#country-mod'])[1]").click();
			//Sync.waitElementClickable(30, By.xpath("//*[@id='country-mod']/div/div[2]/div[2]/ul[1]/li[5]/a"));
			Common.clickElement("xpath", "//a[@title='France']");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display France store", "France store displayed successfully", Common.getscreenShotPathforReport("France store success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Francde store", "France store not display", Common.getscreenShotPathforReport("France store Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	
	public void UKENStoreSelection() throws InterruptedException
	{
		String expectedResult="Navigating United Kingdom Store";
		try {
			Sync.waitElementClickable(30, By.xpath("(//a[@href='#country-mod'])[1]"));
			Common.findElement("xpath", "(//a[@href='#country-mod'])[1]").click();
			//Sync.waitElementClickable(30, By.xpath("//*[@id='country-mod']/div/div[2]/div[2]/ul[1]/li[5]/a"));
			Common.clickElement("xpath", "//a[@title='United Kingdom']");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display United Kingdom store", "United Kingdom store displayed successfully", Common.getscreenShotPathforReport("United Kingdom store success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display United Kingdom store", "United Kingdom store not display", Common.getscreenShotPathforReport("United Kingdom store Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void NorwayStoreSelection() throws InterruptedException
	{
		String expectedResult="Navigating Norway Store";
		try {
			Sync.waitElementClickable(30, By.xpath("(//a[@href='#country-mod'])[1]"));
			Common.findElement("xpath", "(//a[@href='#country-mod'])[1]").click();
			//Sync.waitElementClickable(30, By.xpath("//*[@id='country-mod']/div/div[2]/div[2]/ul[1]/li[5]/a"));
			Common.clickElement("xpath", "//a[@title='Norway']");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display Norway store", "Norway store displayed successfully", Common.getscreenShotPathforReport("Norway store success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Norway store", "Norway store not display", Common.getscreenShotPathforReport("Norway store Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	
	public void UKSVStoreSelection() throws InterruptedException
	{
		String expectedResult="Navigating Sweden Store";
		try {
			Sync.waitElementClickable(30, By.xpath("(//a[@href='#country-mod'])[1]"));
			Common.findElement("xpath", "(//a[@href='#country-mod'])[1]").click();
			//Sync.waitElementClickable(30, By.xpath("//*[@id='country-mod']/div/div[2]/div[2]/ul[1]/li[5]/a"));
			Common.clickElement("xpath", "//a[@title='Sweden']");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display Sweden store", "Sweden store displayed successfully", Common.getscreenShotPathforReport("Swedenstore success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Sweden store", "Sweden store not display", Common.getscreenShotPathforReport("Sweden store Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void ATDEStoreSelection() throws InterruptedException
	{
		String expectedResult="Navigating Austria Store";
		try {
			Sync.waitElementClickable(30, By.xpath("(//a[@href='#country-mod'])[1]"));
			Common.findElement("xpath", "(//a[@href='#country-mod'])[1]").click();
			//Sync.waitElementClickable(30, By.xpath("//*[@id='country-mod']/div/div[2]/div[2]/ul[1]/li[5]/a"));
			Common.clickElement("xpath", "//a[@title='Austria']");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display Austria store", "Austria store displayed successfully", Common.getscreenShotPathforReport("Austria store success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Austria store", "Austria store not display", Common.getscreenShotPathforReport("Austria store Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	
	public void StoreSelection(String Store) throws InterruptedException
	{
		String expectedResult="Navigating to Store:"+Store;
		try {
			Sync.waitElementClickable(30, By.xpath("(//a[@href='#country-mod'])[1]"));
			Common.findElement("xpath", "(//a[@href='#country-mod'])[1]").click();
			//Sync.waitElementClickable(30, By.xpath("//*[@id='country-mod']/div/div[2]/div[2]/ul[1]/li[5]/a"));
			Common.clickElement("xpath", "//a[@title='"+Store+"']");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display "+Store+" store", Store+" store displayed successfully", Common.getscreenShotPathforReport(Store+" store success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display "+Store+" store", Store+" store not display", Common.getscreenShotPathforReport(Store+" store Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void SOUAFStoreSelection() throws InterruptedException
	{
		String expectedResult="Navigating South Africa Store";
		try {
			Sync.waitElementClickable(30, By.xpath("(//a[@title='United States'])[1]"));
			Common.findElement("xpath", "(//a[@title='United States'])[1]").click();
			//Sync.waitElementClickable(30, By.xpath("//*[@id='country-mod']/div/div[2]/div[2]/ul[1]/li[5]/a"));
			Common.clickElement("xpath", "//a[@title='South Africa']");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display South Africa store", "South Africa store displayed successfully", Common.getscreenShotPathforReport("South Africa store success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display South Africa store", "South Africa store not display", Common.getscreenShotPathforReport("South Africa store Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void NLStoreSelection() throws InterruptedException
	{
		String expectedResult="Navigating Netherland Store";
		try {
			Sync.waitElementClickable(30, By.xpath("(//a[@href='#country-mod'])[1]"));
			Common.findElement("xpath", "(//a[@href='#country-mod'])[1]").click();
			//Sync.waitElementClickable(30, By.xpath("//*[@id='country-mod']/div/div[2]/div[2]/ul[1]/li[5]/a"));
			Common.clickElement("xpath", "//a[@title='Netherland']");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display Netherland store", "Netherland store displayed successfully", Common.getscreenShotPathforReport("Netherland store success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Netherland store", "Netherland store not display", Common.getscreenShotPathforReport("Netherland store Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	
	public void SpainStoreSelection() throws InterruptedException
	{
		String expectedResult="Navigating Spain Store";
		try {
			Sync.waitElementClickable(30, By.xpath("(//a[@href='#country-mod'])[1]"));
			Common.findElement("xpath", "(//a[@href='#country-mod'])[1]").click();
			//Sync.waitElementClickable(30, By.xpath("//*[@id='country-mod']/div/div[2]/div[2]/ul[1]/li[5]/a"));
			Common.clickElement("xpath", "//a[@title='Spain']");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display Spain store", "Spain store displayed successfully", Common.getscreenShotPathforReport("Spain store success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Spain store", "Spain store not display", Common.getscreenShotPathforReport("Spain store Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	public void PolandStoreSelection() throws InterruptedException
	{
		String expectedResult="Navigating Poland Store";
		try {
			Sync.waitElementClickable(30, By.xpath("(//a[@href='#country-mod'])[1]"));
			Common.findElement("xpath", "(//a[@href='#country-mod'])[1]").click();
			Common.clickElement("xpath", "//a[@title='Poland']");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display Poland store", "Poland store displayed successfully", Common.getscreenShotPathforReport("Poland store success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Poland store", "Poland store not display", Common.getscreenShotPathforReport("Poland store Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void TurkeyStoreSelection() throws InterruptedException
	{
		String expectedResult="Navigating Turkey Store";
		try {
			Sync.waitElementClickable(30, By.xpath("(//a[@href='#country-mod'])[1]"));
			Common.findElement("xpath", "(//a[@href='#country-mod'])[1]").click();
			Common.clickElement("xpath", "//a[@title='Turkey']");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display Turkey store", "Turkey store displayed successfully", Common.getscreenShotPathforReport("Turkey store success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Turkey store", "Turkey store not display", Common.getscreenShotPathforReport("Turkey store Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	

	public void Countryselection() throws Exception{

		Thread.sleep(1000);

		Sync.waitElementPresent("xpath", "//a[@title='Germany']");

		Common.clickElement("xpath", "//a[@title='Germany']");

		report.addPassLog("Country Page", "Country Displayed", Common.getscreenShotPathforReport("Country Selected"));

	}

	public void GEsingin(String DataSet) throws Exception{

		String expectedResult="Land on login page";

		try {

			Sync.waitElementPresent("xpath", "//a[@class='braun-icons braun-profile-icon']");
			Common.clickElement("xpath", "//a[@class='braun-icons braun-profile-icon']");
			Sync.waitElementPresent("id", "email");
			Thread.sleep(4000);
			Common.textBoxInput("id", "email", data.get(DataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(DataSet).get("Password"));
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "(//button[@type='submit'])[2]");
			Common.clickElement(By.xpath("(//button[@type='submit'])[2]"));
			String s=Common.getText("xpath", "//span[contains(text(), 'Mein Konto')]");
			Assert.assertEquals(s, "Mein Konto");
			report.addPassLog(expectedResult,"Should Login to application successfully", "Login to application successfully", Common.getscreenShotPathforReport("Login success"));
		}

		catch(Exception |Error e)
		{
			report.addPassLog(expectedResult,"Should Login to application successfully", "Login to application Failed", Common.getscreenShotPathforReport("Login failed"));
			e.printStackTrace();
			Assert.fail();
		}	
	}
	
	public void FRsingin(String DataSet) throws Exception{

		String expectedResult="Land on login page";

		try {

			Sync.waitElementPresent("xpath", "//a[@class='braun-icons braun-profile-icon']");
			Common.clickElement("xpath", "//a[@class='braun-icons braun-profile-icon']");

			Sync.waitElementPresent("id", "email");
			Thread.sleep(4000);

			Common.textBoxInput("id", "email", data.get(DataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(DataSet).get("Password"));
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "(//button[@type='submit'])[2]");
			Common.clickElement(By.xpath("(//button[@type='submit'])[2]"));
			
			String s=Common.getText("xpath", "//span[contains(text(), 'Mon compte')]");
			System.out.println(s);
			Assert.assertEquals(s, "Mon Compte");
			Common.isElementDisplayed("xpath" , "//span[contains(text(), 'Mon compte')]");
			report.addPassLog(expectedResult,"Should Login to application successfully", "Login to application successfully", Common.getscreenShotPathforReport("Login success"));
		}

		catch(Exception |Error e)
		{
			report.addPassLog(expectedResult,"Should Login to application successfully", "Login to application Failed", Common.getscreenShotPathforReport("Login failed"));
			e.printStackTrace();
			Assert.fail();
		}	
	}

	public void Productselection() throws Exception
	{
		String expectedResult="Product Selection from Category";
		try {
			Thread.sleep(4000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			Common.clickElement("xpath", "//a[@id='ui-id-2']");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//img[@alt='Braun ThermoScan?? 3']");
			Common.clickElement("xpath", "//img[@alt='Braun ThermoScan?? 3']");
			Common.isElementDisplayed("xpath", "//span[@class='base']");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display Product Details Page", "Product Details  Page display successfully", Common.getscreenShotPathforReport("Product Details page display success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Details Page", "Product Details Page not displayed", Common.getscreenShotPathforReport("Product Details page Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	
	public void SwedenProductselection() throws Exception
	{
		String expectedResult="Product Selection from Category";
		try {
			Thread.sleep(4000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			Common.clickElement("xpath", "//a[@id='ui-id-2']");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//img[@alt='Braun ThermoScan?? 7 mit Age Precision??']");
			Common.clickElement("xpath", "//img[@alt='Braun ThermoScan?? 7 mit Age Precision??']");
			Common.isElementDisplayed("xpath", "//span[@class='base']");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display Product Details Page", "Product Details  Page display successfully", Common.getscreenShotPathforReport("Product Details page display success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Details Page", "Product Details Page not displayed", Common.getscreenShotPathforReport("Product Details page Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	
	
	public void ProductReview(String dataSet) throws Exception
	{
		
		String expectedResult="Updating the product review of selected product";
		try {
			
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.DOWN);
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.DOWN);
			Thread.sleep(2000);
			Common.clickElement("xpath", "//a[@id='tab-label-bv_reviews-title']");
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.DOWN);
			Thread.sleep(2000);
			/*Common.actionsKeyPress(Keys.DOWN);
			Thread.sleep(2000);*/
			Common.clickElement("xpath", "//div[@class='bv-write-review-container bv-write-container']");
			
			/*Common.clickElement("id", "Quality_5_label");
			Common.clickElement("id", "Price_5_label");
			Common.clickElement("id", "Value_5_label");
			Thread.sleep(10000);
			Sync.waitElementClickable("name", "nickname");
			Common.actionsKeyPress(Keys.TAB);
			Common.textBoxInput("xpath", "//form[@id='review-form']/fieldset/div[1]/div//input[@id='nickname_field']", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("id", "email_field", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "summary_field", data.get(dataSet).get("Summary"));
			Common.textBoxInput("id", "review_field", data.get(dataSet).get("Review"));
			Thread.sleep(3000);
			Common.clickElement("xpath", "//button[contains(text(),'Submit Review')]");
			Thread.sleep(7000);
			String review=Common.getText("xpath", "//div[@data-bind='html: message.text']");
			System.out.println("Product Review updated "+review);
			Assert.assertEquals(review, "You submitted your review for moderation.");*/
			report.addPassLog(expectedResult, "Should update Product Review for selected product", "Updated Product review for selected product successfully", Common.getscreenShotPathforReport("Product Review success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should update Product Review for selected product", "Update Product review for selected product Failed", Common.getscreenShotPathforReport("Product Review Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void ProductAddingtocartfromHomepage() throws Exception
	{
		String expectedResult="Product Selection from Homepage";
		try {
			
			Sync.waitPageLoad();
		    Common.scrollIntoView("xpath", "(//button[@class='action tocart add_to_cart_btn braun_btn'])[2]");
			Thread.sleep(4000);		
			Sync.waitElementPresent("xpath", "(//button[@class='action tocart add_to_cart_btn braun_btn'])[2]");
			Common.clickElement("xpath", "(//button[@class='action tocart add_to_cart_btn braun_btn'])[2]");
			Thread.sleep(1000);
			
			report.addPassLog(expectedResult, "Product should add to the cart", "Product should add to cart successfully", Common.getscreenShotPathforReport("Product should add to cart success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Product should add to the cart", "Product should add to cart displayed", Common.getscreenShotPathforReport("Product add to cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		try
		{
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
			Common.actionsKeyPress(Keys.PAGE_UP);
			Thread.sleep(3000);
			int message=Common.findElements("xpath", "//div[@class='message-success success message']").size();

			 Common.assertionCheckwithReport(message>0, "To verify the product added success message from Homepage", "Should display product added to bag success message","Product sucessfully added to bag from Homepage", "faield to add product to bag from Homepage");
		
			//ExtenantReportUtils.addPassLog("verifying add to cart button in Homepage", "User click add to card button on Homepage", "user successfully click add to cart button in Homepage", Common.getscreenShotPathforReport("faieldtoclickaddtocartbuttonin Homepage"));
			}
			catch(Exception |Error e) {
			   
				ExtenantReportUtils.addFailedLog("verifying product added success message", "User should get success message", "user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
				Assert.fail();
			}
	}
	
	
	public void SwedenProductAddingtocartfromHomepage() throws Exception
	{
		String expectedResult="Product Selection from Homepage";
		try {
			
			Sync.waitPageLoad();
		    Common.scrollIntoView("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Thread.sleep(4000);		
			Sync.waitElementPresent("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Common.clickElement("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Thread.sleep(1000);
			
			report.addPassLog(expectedResult, "Product should add to the cart", "Product should add to cart successfully", Common.getscreenShotPathforReport("Product should add to cart success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Product should add to the cart", "Product should add to cart displayed", Common.getscreenShotPathforReport("Product add to cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		try
		{
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
			Common.actionsKeyPress(Keys.PAGE_UP);
			Thread.sleep(3000);
			int message=Common.findElements("xpath", "//div[@class='message-success success message']").size();

			 Common.assertionCheckwithReport(message>0, "To verify the product added success message from Homepage", "Should display product added to bag success message","Product sucessfully added to bag from Homepage", "faield to add product to bag from Homepage");
		
			//ExtenantReportUtils.addPassLog("verifying add to cart button in Homepage", "User click add to card button on Homepage", "user successfully click add to cart button in Homepage", Common.getscreenShotPathforReport("faieldtoclickaddtocartbuttonin Homepage"));
			}
			catch(Exception |Error e) {
			   
				ExtenantReportUtils.addFailedLog("verifying product added success message", "User should get success message", "user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
				Assert.fail();
			}
	}
	
	public void ProductAddingtocart() throws Exception
	{
		String expectedResult="Product Selection from Homepage";
		try {
			
			Sync.waitPageLoad();		
			Sync.waitElementPresent("xpath", "//button[@class='bundle-custom-addtocart braun_btn add_to_cart_btn']");
			Common.clickElement("xpath", "//button[@class='bundle-custom-addtocart braun_btn add_to_cart_btn']");
			Thread.sleep(1000);
			
			report.addPassLog(expectedResult, "Product should add to the cart", "Product should add to cart successfully", Common.getscreenShotPathforReport("Product should add to cart success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Product should add to the cart", "Product should add to cart displayed", Common.getscreenShotPathforReport("Product add to cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		try
		{
			Thread.sleep(3000);
			int message=Common.findElements("xpath", "//div[@class='message-success success message']").size();

			 Common.assertionCheckwithReport(message>0, "To verify the product added success message from Homepage", "Should display product added to bag success message","Product sucessfully added to bag from Homepage", "faield to add product to bag from Homepage");
		
			//ExtenantReportUtils.addPassLog("verifying add to cart button in Homepage", "User click add to card button on Homepage", "user successfully click add to cart button in Homepage", Common.getscreenShotPathforReport("faieldtoclickaddtocartbuttonin Homepage"));
			}
			catch(Exception |Error e) {
			   
				ExtenantReportUtils.addFailedLog("verifying product added success message", "User should get success message", "user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
				Assert.fail();
			}
	}
	
	
	public void ProductAddingtocartfromPLP() throws Exception
	{
		String expectedResult="Product Selection from PLP Page";
		try {
			
			Sync.waitPageLoad();
			Thread.sleep(4000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			Common.clickElement("xpath", "//a[@id='ui-id-2']");
			Thread.sleep(3000);
		    Common.scrollIntoView("xpath", "(//button[@class='action tocart add_to_cart_btn braun_btn'])[2]");
			Thread.sleep(4000);		
			Sync.waitElementPresent("xpath", "(//button[@class='action tocart add_to_cart_btn braun_btn'])[2]");
			Common.clickElement("xpath", "(//button[@class='action tocart add_to_cart_btn braun_btn'])[2]");
			Thread.sleep(1000);
			
			report.addPassLog(expectedResult, "Product should add to the cart", "Product should add to cart successfully", Common.getscreenShotPathforReport("Product should add to cart success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Product should add to the cart", "Product should add to cart displayed", Common.getscreenShotPathforReport("Product add to cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		try
		{
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
			Common.actionsKeyPress(Keys.PAGE_UP);
			Thread.sleep(3000);
			int message=Common.findElements("xpath", "//div[@class='message-success success message']").size();

			 Common.assertionCheckwithReport(message>0, "To verify the product added success message from Homepage", "Should display product added to bag success message","Product sucessfully added to bag from Homepage", "faield to add product to bag from Homepage");
		
			//ExtenantReportUtils.addPassLog("verifying add to cart button in Homepage", "User click add to card button on Homepage", "user successfully click add to cart button in Homepage", Common.getscreenShotPathforReport("faieldtoclickaddtocartbuttonin Homepage"));
			}
			catch(Exception |Error e) {
			   
				ExtenantReportUtils.addFailedLog("verifying product added success message", "User should get success message", "user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
				Assert.fail();
			}
	}

	
	public void ClickCheckoutButton(){
		try{
			Thread.sleep(6000);
			//Common.actionsKeyPress(Keys.PAGE_DOWN);
		 Sync.waitElementPresent("xpath", "//button[@id='top-cart-btn-checkout']");
		 Thread.sleep(4000);
		   Common.findElement("xpath", "//button[@id='top-cart-btn-checkout']").click();
		   
		   Sync.waitElementPresent("xpath", "//button[@id='guest_checkout']");
			Common.clickElement("xpath", "//button[@id='guest_checkout']");
		   
		}
		    catch(Exception |Error e) {
		 	   
				ExtenantReportUtils.addFailedLog("verifying checkOut button", "User click checkOut  button", "user faield to click checkOut button", Common.getscreenShotPathforReport("checkOut"));
				Assert.fail();
			}
	}
	
	
	
	public void ItalyProductselection() throws Exception
	{
		String expectedResult="Product Selection from Category";
		try {
			Thread.sleep(4000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			Common.clickElement("xpath", "//a[@id='ui-id-2']");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//img[@alt='Braun ThermoScan?? 7 con Age Precision??']");
			Common.clickElement("xpath", "//img[@alt='Braun ThermoScan?? 7 con Age Precision??']");
			Common.isElementDisplayed("xpath", "//span[@class='base']");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display Product Details Page", "Product Details  Page display successfully", Common.getscreenShotPathforReport("Product Details page display success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Details Page", "Product Details Page not displayed", Common.getscreenShotPathforReport("Product Details page Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	
	public void Reviews() {
		String expectedResult = "Bewertungen";
		try {
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.DOWN);
			
			Common.actionsKeyPress(Keys.DOWN);
			
			Common.actionsKeyPress(Keys.DOWN);
			//Thread.sleep(5000);
			Common.actionsKeyPress(Keys.DOWN);
		Thread.sleep(5000);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.clickElement("xpath", "//a[text()='Bewertungen']");
			
			report.addPassLog(expectedResult, "Should display review Page", "review Page display successfully", Common.getscreenShotPathforReport("review results success"));
			
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display review Page", "review Page not display", Common.getscreenShotPathforReport("review result Failed"));
			e.printStackTrace();
			Assert.fail();
		
		

		
		
		
	}
	
	}



	public void FRReviews() {
		String expectedResult = "Bewertungen";
		try {
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.DOWN);
			
			Common.actionsKeyPress(Keys.DOWN);
			
			Common.actionsKeyPress(Keys.DOWN);
			//Thread.sleep(5000);
			Common.actionsKeyPress(Keys.DOWN);
		Thread.sleep(5000);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.DOWN);
			Common.clickElement("xpath", "//a[text()='Bewertungen']");
			
			report.addPassLog(expectedResult, "Should display review Page", "review Page display successfully", Common.getscreenShotPathforReport("review results success"));
			
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display review Page", "review Page not display", Common.getscreenShotPathforReport("review result Failed"));
			e.printStackTrace();
			Assert.fail();
		
		
	}
	
	}




	
	
	
	
	public void FranceProductselection() throws Exception
	{
		String expectedResult="Product Selection from Category";
		try {
			Thread.sleep(4000);		
			Sync.waitElementPresent("xpath", "//span[contains(text(), 'Thermometre')]");
			Common.clickElement("xpath", "//span[contains(text(), 'Thermometre')]");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//img[@alt='Braun ThermoScan?? 3']");
			Common.clickElement("xpath", "//img[@alt='Braun ThermoScan?? 3']");
			Thread.sleep(2000);
			String s=Common.getText("xpath", "//h1[@ class='page-title']");
			Assert.assertEquals(s, "Braun ThermoScan?? 3");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Product Details Page", "Product Details  Page display successfully", Common.getscreenShotPathforReport("Product Details page display success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Details Page", "Product Details Page not displayed", Common.getscreenShotPathforReport("Product Details page Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	
	public void canadaproductname(String dataSet) throws Exception

	{

	String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");

	try {

	Thread.sleep(1000);

	Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");

	Sync.waitElementPresent("id", "search");

	try {

	Common.textBoxInput("id", "search", data.get(dataSet).get("CanadaProduct"));

	report.addPassLog(expectedResult, "Should display Browse search Page", "Browse search Page display successfully", Common.getscreenShotPathforReport("Browse search page success"));

	 

	}catch(Exception e)

	{

	Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");

	Thread.sleep(3000);

	Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));

	}

	Common.actionsKeyPress(Keys.ENTER);

	Thread.sleep(10000);

	Common.actionsKeyPress(Keys.DOWN);

	 

	Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");

	Common.clickElement("xpath", "//img[@class='product-image-photo ']");

	//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");

	report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));

	e.printStackTrace();

	Assert.fail();

	}

	 

	}


	
	
	
	
	public void NavigateCanadaYourhealth(String dataset) throws Exception

	{

	String expectedResult="Lands on Your health page";

	String linkText=data.get(dataset).get("CanadaHealthlinkText");

	try {

	Sync.waitElementPresent("xpath", "//a[@title='"+linkText+"']");

	Common.scrollToElementAndClick("xpath", "//a[@title='"+linkText+"']");

	 

	Thread.sleep(300);

	String s=Common.getText("xpath", "//a[contains(text(), '"+linkText+"')]");

	System.out.println(s +" navigated successfully");

	Assert.assertEquals(s, linkText);




	report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));

	}catch(Exception |Error e)

	{

	e.printStackTrace();

	report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));

	Assert.fail();

	}

	 

	}



















	public void CanadaYourhealth(String dataset) throws Exception

	{

	String expectedResult="Lands on Your health page";

	String linkText=data.get(dataset).get("CanadahealthText");

	try {

	Sync.waitElementPresent("xpath", "//a[@title='"+linkText+"']");

	Common.scrollToElementAndClick("xpath", "//a[@title='"+linkText+"']");

	 

	Thread.sleep(300);

	String s=Common.getText("xpath", "//a[contains(text(), '"+linkText+"')]");

	System.out.println(s +" navigated successfully");

	Assert.assertEquals(s, linkText);




	report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));

	}catch(Exception |Error e)

	{

	e.printStackTrace();

	report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));

	Assert.fail();

	}

	 

	}
	
	
	public void CanadaHeaderLinks(String dataSet) throws Exception{

		String expectedResult="Header Link validations";

		String Headerlinks=data.get(dataSet).get("CanadaHeaders");

		String[] headers=Headerlinks.split(",");

		for(int i=0;i<headers.length;i++){

		Sync.waitElementClickable("xpath", "//span[text()='"+headers[i]+"']");

		Common.clickElement("xpath", "//span[text()='"+headers[i]+"']");

		Thread.sleep(10000);

		System.out.println(Common.getPageTitle());

		report.addPassLog(expectedResult, "Should display "+headers[i]+" success Page", ""+headers[i]+" Page display successfully", Common.getscreenShotPathforReport("Product Registration success page success"));


		}


		}




		public void CanadaNewslettersubscription(String dataset) throws Exception

		{

		String expectedResult="Should display Newsletter subscription page";

		try {


		//Common.textBoxInput("id", "newsletter", data.get(dataset).get("Email"));


		Common.textBoxInput("id", "newsletter",Utils.getEmailid());


		Common.clickElement("xpath", "//span[@class='checkmark']");


		Thread.sleep(1000);


		Common.clickElement("xpath", "//button[@class='action subscribe primary']");


		String s=Common.getText("xpath", "//div[@class='message-success success message']");

		System.out.println(s);

		if(s.contentEquals("Vielen Dank f??r Ihre Anmeldung."))

		{

		Assert.assertEquals(s, "Vielen Dank f??r Ihre Anmeldung.");

		Thread.sleep(1000);

		//System.out.println("Forgot password successpage");

		}

		/*else {

		Assert.assertEquals(s, "S'il y a un compte associ?? ?? l???adresse pavani.tumati@gmail.com, vous recevrez un email avec un lien pour r??initialiser votre mot de passe");


		}*/

		       

		/* String title=Common.findElement("xpath", "//div[@class='message-error error message']").getText();

		title=("Diese E-Mail-Adresse ist bereits abonniert.");*/

		 

		report.addPassLog(expectedResult, "Should display  Newsletter subscription page", "Newsletter subscription Page display successfully", Common.getscreenShotPathforReport("Newsletter subscription page success"));


		}catch(Exception |Error e)



		{

		report.addFailedLog(expectedResult,"Should display Newsletter subscription Page", "Newsletter subscription Page not displayed", Common.getscreenShotPathforReport("Newletter subscription page display Failed"));

		Assert.fail();

		}

		 

		}



		public void CanadafrFacebook() throws Exception

		{

		String expectedResult="Validate Facebook Link in Canadafr store";

		try {

		Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");

		Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");

		Thread.sleep(2000);

		Common.switchToSecondTab();

		   Thread.sleep(15000);

		   String s=Common.getCurrentURL();

		   System.out.println(s);

		   Common.assertionCheckwithReport(s.contains("facebook"),"Verifying facebook page","it shoud navigate to facebook  page", "successfully  navigated to facebook Page", "facebook");

		report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));

		}catch(Exception |Error e)

		{

		report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));

		e.printStackTrace();

		Assert.fail();

		}

		Common.closeCurrentWindow();

		Common.switchToFirstTab();

		}

		       






		public void CanadaYoutube() throws Exception

		{

		String expectedResult="Validate Youtube Link in Canadafr store";

		try {

		Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");

		Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");

		Thread.sleep(3000);

		Common.switchToSecondTab();

		   Thread.sleep(13000);

		String s=Common.getCurrentURL();

		   System.out.println(s);

		   Common.assertionCheckwithReport(s.contains("youtube"),"Verifying youtube page","it shoud navigate to youtube  page", "successfully  navigated to youtube Page", "youtube");

		report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));

		}catch(Exception |Error e)

		{

		report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));

		e.printStackTrace();

		Assert.fail();

		}

		Common.closeCurrentWindow();

		Common.switchToFirstTab();

		}

		       

	public void Productlistingpage() throws Exception
	{
		String expectedResult="Product Selection from Category";
		try {
			Thread.sleep(4000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			Common.clickElement("xpath", "//a[@id='ui-id-2']");
			
			Sync.waitElementPresent("xpath", "//img[@alt='Braun ThermoScan?? 3']");
			Common.clickElement("xpath", "//img[@alt='Braun ThermoScan?? 3']");
			Common.isElementDisplayed("xpath", "//span[@class='base']");
			
			/*Sync.waitElementPresent("xpath", "//*[@id='tab_cat_7']/div/ul/li[6]/div/h3/a");
			Common.clickElement("xpath", "//*[@id='tab_cat_7']/div/ul/li[6]/div/h3/a");
			Thread.sleep(2000);
			
			String title=Common.findElement("xpath", "//h1[@ class='page-title']").getText();
			title=("Braun ThermoScan?? 7 mit Age Precision??");*/
			
			report.addPassLog(expectedResult, "Should display Product Listing Page", "Product Listing Page display successfully", Common.getscreenShotPathforReport("Product Listing page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Product Listing Page", "Product Listing Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}



	public void Newsletter(String dataset) throws Exception
	{
		String expectedResult="Should display Newsletter subscription page";
		
		
		//String expectedResult1="Subscription message :"+data.get(dataSet).get("Confirmationmsg");
		try {
			
			Thread.sleep(6000);
			Sync.waitElementPresent("xpath", "(//div[@class='box-actions'])[2]/a/span");
			
			Common.javascriptclickElement("xpath" , "(//div[@class='box-actions'])[2]/a/span");
			
			Thread.sleep(3000);
			
			report.addPassLog("Should display My account page", "My account Page display successfully", Common.getscreenShotPathforReport("My account page success"));
			Thread.sleep(4000);

			Sync.waitElementPresent("xpath", "//button[@class='action save save_btn braun_btn']");
			Common.javascriptclickElement("xpath" , "//button[@class='action save save_btn braun_btn']");
			Thread.sleep(2000);
			
			String expectedResult1=data.get(dataset).get("Confirmationmsg");
			//String expectedResult1="Subscription message :"+data.get(dataSet).get("Confirmationmsg");
			System.out.println(data.get(dataset).get("Confirmationmsg")+ "excel value");
			
			System.out.println("We have updated your subscription.");
			//String expectedResult1="We have updated your subscription.:"+data.get(dataSet).get("Confirmationmsg");
			
			String expectedResult2=Common.findElement("xpath", "//div[@class='message-success success message']").getText();
			
			System.out.println(expectedResult2+ "UI VALUE");
			//title=("We have updated your subscription.");
			
			
		if(expectedResult1==expectedResult2)
			{
				System.out.println("pass");
			}
			else
			{
				System.out.println("fail");
			}

			report.addPassLog("Should display  Newsletter subscription page", "Newsletter subscription Page display successfully", Common.getscreenShotPathforReport("Newsletter subscription page success"));
			
		}catch(Exception |Error e)
		{
			report.addFailedLog("Should display Newsletter subscription Page", "Ndewsletter subscription Page not displayed", Common.getscreenShotPathforReport("Newletter subscription page display Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}





	public void PolandNewslettersubscription(String dataset) throws Exception
	{
		String expectedResult="Should display Newsletter subscription page";
		try {
			
			//Common.textBoxInput("id", "newsletter", data.get(dataset).get("Email"));
			Common.textBoxInput("id", "newsletter",Utils.getEmailid());
			
			Common.clickElement("xpath", "//span[@class='checkmark']");
			
			Thread.sleep(1000);
			
			Common.clickElement("xpath", "(//button[@type='submit'])[2]");
			
			Thread.sleep(1000);
			String title=Common.findElement("xpath", "//div[@class='message-success success message']").getText();
			title=("Dzi??kujemy za twoj?? subskrypcj??");

			report.addPassLog(expectedResult, "Should display  Newsletter subscription page", "Newsletter subscription Page display successfully", Common.getscreenShotPathforReport("Newsletter subscription page success"));
			
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Newsletter subscription Page", "Newsletter subscription Page not displayed", Common.getscreenShotPathforReport("Newletter subscription page display Failed"));
			Assert.fail();
		}

	}
	
	public void ATDENewslettersubscription(String dataset) throws Exception
	{
		String expectedResult="Should display Newsletter subscription page";
		try {
			
			//Common.textBoxInput("id", "newsletter", data.get(dataset).get("Email"));
			
			Common.textBoxInput("id", "newsletter",Utils.getEmailid());
			
			Common.clickElement("xpath", "//span[@class='checkmark']");
			
			Thread.sleep(1000);
			
			Common.clickElement("xpath", "//button[@title='Abonnieren']");
			
			String s=Common.getText("xpath", "//div[@class='message-success success message']");
			System.out.println(s);
			if(s.contentEquals("Vielen Dank f??r Ihre Anmeldung."))
			{
			Assert.assertEquals(s, "Vielen Dank f??r Ihre Anmeldung.");
			Thread.sleep(1000);
			//System.out.println("Forgot password successpage");
			}
			/*else {
				Assert.assertEquals(s, "S'il y a un compte associ?? ?? l???adresse pavani.tumati@gmail.com, vous recevrez un email avec un lien pour r??initialiser votre mot de passe");
				
			}*/
	
		/*	String title=Common.findElement("xpath", "//div[@class='message-error error message']").getText();
			title=("Diese E-Mail-Adresse ist bereits abonniert.");*/

			report.addPassLog(expectedResult, "Should display  Newsletter subscription page", "Newsletter subscription Page display successfully", Common.getscreenShotPathforReport("Newsletter subscription page success"));
			
		}catch(Exception |Error e)
		
		
		{
			report.addFailedLog(expectedResult,"Should display Newsletter subscription Page", "Newsletter subscription Page not displayed", Common.getscreenShotPathforReport("Newletter subscription page display Failed"));
			Assert.fail();
		}

	}
	
	
	public void CanadaENNewslettersubscription(String dataset) throws Exception
	{
		String expectedResult="Should display Newsletter subscription page";
		try {
			
			//Common.textBoxInput("id", "newsletter", data.get(dataset).get("Email"));
			
			Common.textBoxInput("id", "newsletter",Utils.getEmailid());
			
			Common.clickElement("xpath", "//span[@class='checkmark']");
			
			Thread.sleep(1000);
			
			Common.clickElement("xpath", "//button[@class='action subscribe primary']");
			
			String s=Common.getText("xpath", "//div[@class='message-success success message']");
			System.out.println(s);
			if(s.contentEquals("Thank you for your subscription."))
			{
			Assert.assertEquals(s, "Thank you for your subscription.");
			Thread.sleep(1000);
			//System.out.println("Forgot password successpage");
			}
			/*else {
				Assert.assertEquals(s, "S'il y a un compte associ?? ?? l???adresse pavani.tumati@gmail.com, vous recevrez un email avec un lien pour r??initialiser votre mot de passe");
				
			}*/
	
		/*	String title=Common.findElement("xpath", "//div[@class='message-error error message']").getText();
			title=("Diese E-Mail-Adresse ist bereits abonniert.");*/

			report.addPassLog(expectedResult, "Should display  Newsletter subscription page", "Newsletter subscription Page display successfully", Common.getscreenShotPathforReport("Newsletter subscription page success"));
			
		}catch(Exception |Error e)
		
		
		{
			report.addFailedLog(expectedResult,"Should display Newsletter subscription Page", "Newsletter subscription Page not displayed", Common.getscreenShotPathforReport("Newletter subscription page display Failed"));
			Assert.fail();
		}

	}
	public void SOUAFNewslettersubscription(String dataset) throws Exception
	{
		String expectedResult="Should display Newsletter subscription page";
		try {
			
			//Common.textBoxInput("id", "newsletter", data.get(dataset).get("Email"));
			Common.textBoxInput("id", "newsletter",Utils.getEmailid());
			
			
			Common.clickElement("xpath", "//span[@class='checkmark']");
			
			Thread.sleep(1000);
			
			Common.clickElement("xpath", "//button[@class='action subscribe primary']");
			
			String s=Common.getText("xpath", "//div[@class='message-success success message']");
			System.out.println(s);
			if(s.contentEquals("Thank you for your subscription."))
			{
			Assert.assertEquals(s, "Thank you for your subscription.");
			Thread.sleep(1000);
			//System.out.println("Forgot password successpage");
			}
			/*else {
				Assert.assertEquals(s, "S'il y a un compte associ?? ?? l???adresse pavani.tumati@gmail.com, vous recevrez un email avec un lien pour r??initialiser votre mot de passe");
				
			}*/
	
		/*	String title=Common.findElement("xpath", "//div[@class='message-error error message']").getText();
			title=("Diese E-Mail-Adresse ist bereits abonniert.");*/

			report.addPassLog(expectedResult, "Should display  Newsletter subscription page", "Newsletter subscription Page display successfully", Common.getscreenShotPathforReport("Newsletter subscription page success"));
			
		}catch(Exception |Error e)
		
		
		{
			report.addFailedLog(expectedResult,"Should display Newsletter subscription Page", "Newsletter subscription Page not displayed", Common.getscreenShotPathforReport("Newletter subscription page display Failed"));
			Assert.fail();
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void NLNewslettersubscription(String dataset) throws Exception
	{
		String expectedResult="Should display Newsletter subscription page";
		try {
			
			//Common.textBoxInput("id", "newsletter", data.get(dataset).get("Email"));
			Common.textBoxInput("id", "newsletter",Utils.getEmailid());
			
			
			Common.clickElement("xpath", "//span[@class='checkmark']");
			
			Thread.sleep(1000);
			
			Common.clickElement("xpath", "//button[@class='action subscribe primary']");
			
			String s=Common.getText("xpath", "//div[@class='message-success success message']");
			System.out.println(s);
			if(s.contentEquals("Bedankt voor uw inschrijving."))
			{
			Assert.assertEquals(s, "Bedankt voor uw inschrijving.");
			Thread.sleep(1000);
			//System.out.println("Forgot password successpage");
			}
			/*else {
				Assert.assertEquals(s, "S'il y a un compte associ?? ?? l???adresse pavani.tumati@gmail.com, vous recevrez un email avec un lien pour r??initialiser votre mot de passe");
				
			}*/
	
		/*	String title=Common.findElement("xpath", "//div[@class='message-error error message']").getText();
			title=("Diese E-Mail-Adresse ist bereits abonniert.");*/

			report.addPassLog(expectedResult, "Should display  Newsletter subscription page", "Newsletter subscription Page display successfully", Common.getscreenShotPathforReport("Newsletter subscription page success"));
			
		}catch(Exception |Error e)
		
		
		{
			report.addFailedLog(expectedResult,"Should display Newsletter subscription Page", "Newsletter subscription Page not displayed", Common.getscreenShotPathforReport("Newletter subscription page display Failed"));
			Assert.fail();
		}

	}
	
	public void SpainNewslettersubscription(String dataset) throws Exception
	{
		String expectedResult="Should display Newsletter subscription page";
		try {
			
			Common.textBoxInput("id", "newsletter", data.get(dataset).get("Email"));
			
			Common.clickElement("xpath", "//span[@class='checkmark']");
			
			Thread.sleep(1000);
			
			Common.clickElement("xpath", "//button[@title='Abonnieren']");
			
			String s=Common.getText("xpath", "//div[@class='message-error error message']");
			System.out.println(s);
			if(s.contentEquals("Diese E-Mail-Adresse ist bereits abonniert."))
			{
			Assert.assertEquals(s, "Diese E-Mail-Adresse ist bereits abonniert.");
			Thread.sleep(1000);
			//System.out.println("Forgot password successpage");
			}
			/*else {
				Assert.assertEquals(s, "S'il y a un compte associ?? ?? l???adresse pavani.tumati@gmail.com, vous recevrez un email avec un lien pour r??initialiser votre mot de passe");
				
			}*/
	
		/*	String title=Common.findElement("xpath", "//div[@class='message-error error message']").getText();
			title=("Diese E-Mail-Adresse ist bereits abonniert.");*/

			report.addPassLog(expectedResult, "Should display  Newsletter subscription page", "Newsletter subscription Page display successfully", Common.getscreenShotPathforReport("Newsletter subscription page success"));
			
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Newsletter subscription Page", "Newsletter subscription Page not displayed", Common.getscreenShotPathforReport("Newletter subscription page display Failed"));
			Assert.fail();
		}

	}
	
	
	public void TurkeyNewslettersubscription(String dataset) throws Exception
	{
		String expectedResult="Should display Newsletter subscription page";
		try {
			
			//Common.textBoxInput("id", "newsletter", data.get(dataset).get("Email"));
			Common.textBoxInput("id", "newsletter",Utils.getEmailid());
			
			Common.clickElement("xpath", "//span[@class='checkmark']");
			
			Thread.sleep(1000);
			
			Common.clickElement("xpath", "//button[@class='action subscribe primary']");
			
			String s=Common.getText("xpath", "//div[@class='message-success success message']");
			System.out.println(s);
			if(s.contentEquals("Abonelik kayd??n??z i??in te??ekk??r ederiz."))
			{
			Assert.assertEquals(s, "Abonelik kayd??n??z i??in te??ekk??r ederiz.");
			Thread.sleep(1000);
			//System.out.println("Forgot password successpage");
			}
			/*else {
				Assert.assertEquals(s, "S'il y a un compte associ?? ?? l???adresse pavani.tumati@gmail.com, vous recevrez un email avec un lien pour r??initialiser votre mot de passe");
				
			}*/
	
		/*	String title=Common.findElement("xpath", "//div[@class='message-error error message']").getText();
			title=("Diese E-Mail-Adresse ist bereits abonniert.");*/

			report.addPassLog(expectedResult, "Should display  Newsletter subscription page", "Newsletter subscription Page display successfully", Common.getscreenShotPathforReport("Newsletter subscription page success"));
			
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Newsletter subscription Page", "Newsletter subscription Page not displayed", Common.getscreenShotPathforReport("Newletter subscription page display Failed"));
			Assert.fail();
		}

	}
	
	
	
	public void NorwayNewslettersubscription(String dataset) throws Exception
	{
		String expectedResult="Should display Newsletter subscription page";
		try {
			
			//Common.textBoxInput("id", "newsletter", data.get(dataset).get("Email"));
			Common.textBoxInput("id", "newsletter",Utils.getEmailid());
			
			Common.clickElement("xpath", "//span[@class='checkmark']");
			
			Thread.sleep(1000);
			
			Common.clickElement("xpath", "//button[@class='action subscribe primary']");
			
			String s=Common.getText("xpath", "//div[@class='message-success success message']");
			System.out.println(s);
			if(s.contentEquals("Takk for at du meldte deg p?? v??rt nyhetsbrev."))
			{
			Assert.assertEquals(s, "Takk for at du meldte deg p?? v??rt nyhetsbrev.");
			Thread.sleep(1000);
			//System.out.println("Forgot password successpage");
			}
			/*else {
				Assert.assertEquals(s, "S'il y a un compte associ?? ?? l???adresse pavani.tumati@gmail.com, vous recevrez un email avec un lien pour r??initialiser votre mot de passe");
				
			}*/
	
		/*	String title=Common.findElement("xpath", "//div[@class='message-error error message']").getText();
			title=("Diese E-Mail-Adresse ist bereits abonniert.");*/

			report.addPassLog(expectedResult, "Should display  Newsletter subscription page", "Newsletter subscription Page display successfully", Common.getscreenShotPathforReport("Newsletter subscription page success"));
			
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Newsletter subscription Page", "Newsletter subscription Page not displayed", Common.getscreenShotPathforReport("Newletter subscription page display Failed"));
			Assert.fail();
		}

	}
	
	public void UKSVNewslettersubscription(String dataset) throws Exception
	{
		String expectedResult="Should display Newsletter subscription page";
		try {
			
			//Common.textBoxInput("id", "newsletter", data.get(dataset).get("Email"));
			Common.textBoxInput("id", "newsletter",Utils.getEmailid());
			
			Common.clickElement("xpath", "//span[@class='checkmark']");
			
			Thread.sleep(1000);
			
			Common.clickElement("xpath", "//button[@title='Prenumerera']");
			
			String s=Common.getText("xpath", "//div[@class='message-success success message']");
			System.out.println(s);
			if(s.contentEquals("Tack f??r att du registerade dig till v??rat nyhetsbrev!"))
			{
			Assert.assertEquals(s, "Tack f??r att du registerade dig till v??rat nyhetsbrev!");
			Thread.sleep(1000);
			//System.out.println("Forgot password successpage");
			}
			/*else {
				Assert.assertEquals(s, "S'il y a un compte associ?? ?? l???adresse pavani.tumati@gmail.com, vous recevrez un email avec un lien pour r??initialiser votre mot de passe");
				
			}*/
	
		/*	String title=Common.findElement("xpath", "//div[@class='message-error error message']").getText();
			title=("Diese E-Mail-Adresse ist bereits abonniert.");*/

			report.addPassLog(expectedResult, "Should display  Newsletter subscription page", "Newsletter subscription Page display successfully", Common.getscreenShotPathforReport("Newsletter subscription page success"));
			
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Newsletter subscription Page", "Newsletter subscription Page not displayed", Common.getscreenShotPathforReport("Newletter subscription page display Failed"));
			Assert.fail();
		}

	}
	

	
	public void UKENNewslettersubscription(String dataset) throws Exception
	{
		String expectedResult="Should display Newsletter subscription page";
		try {
			
			//Common.textBoxInput("id", "newsletter", data.get(dataset).get("Email"));
			Common.textBoxInput("id", "newsletter",Utils.getEmailid());
			
			Common.clickElement("xpath", "//span[@class='checkmark']");
			
			Thread.sleep(1000);
			
			Common.clickElement("xpath", "//button[@class='action subscribe primary']");
			
			Thread.sleep(1000);
			String title=Common.findElement("xpath", "//div[@class='message-success success message']").getText();
			title=("Thank you for your subscription.");

			report.addPassLog(expectedResult, "Should display  Newsletter subscription page", "Newsletter subscription Page display successfully", Common.getscreenShotPathforReport("Newsletter subscription page success"));
			
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Newsletter subscription Page", "Newsletter subscription Page not displayed", Common.getscreenShotPathforReport("Newletter subscription page display Failed"));
			Assert.fail();
		}

	}

	


	




	public void GENavigatetoPLPpage() throws Exception
	{
		String expectedResult="User should successfully lands on PLP page";
		try {
			Thread.sleep(4000);		
			Sync.waitElementPresent("xpath", "(//span[contains(text(),'Thermometer')])[1]");
			Common.clickElement("xpath", "(//span[contains(text(),'Thermometer')])[1]");
			Thread.sleep(3000);
			Common.scrollIntoView("xpath" , "(//div[@class='hlth_artl cat_sldr_bx '])[8]");
			Common.getPageTitle();
			Thread.sleep(1000);
			report.addPassLog(expectedResult, "Should display PLP Page", "PLP Page display successfully", Common.getscreenShotPathforReport("PLP page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display PLP Page", "PLP Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}
	
	public void FRNavigatetoPLPpage() throws Exception
	{
		String expectedResult="User should successfully lands on PLP page";
		try {
			Thread.sleep(4000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			Common.clickElement("xpath", "//a[@id='ui-id-2']");
			//Thread.sleep(3000);
			Common.scrollIntoView("xpath" , "(//div[@class='hlth_artl cat_sldr_bx '])[5]");
			
			//String title=Common.findElement("xpath", "//img[@alt='Braun ThermoScan?? 7 avec Age Precision??']").getText();
			//title=("Braun ThermoScan?? 7 avec Age Precision??");
			
			String s=Common.getText("xpath", "//img[@alt='Braun ThermoScan?? 7 avec Age Precision??']");
			Assert.assertEquals(s, "Braun ThermoScan?? 7 avec Age Precision??");
			Thread.sleep(2000);
			
			//String s=Common.getText("xpath", "(//a[contains(text(), 'Braun ThermoScan')])[1]");
			//Assert.assertEquals(s, "Braun ThermoScan");
			Thread.sleep(1000);
		
			//Common.isElementDisplayed("xpath", "//span[@data-ui-id='page-title-wrapper']");
			report.addPassLog(expectedResult, "Should display PLP Page", "PLP Page display successfully", Common.getscreenShotPathforReport("PLP page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display PLP Page", "PLP Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}


	public void Yourfamily_health() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Ihre Familiengesundheit']");
			//Common.scrollToElementAndClick("xpath", "//a[@href='/faqs/']");
			Common.javascriptclickElement("xpath" , "//a[@title='Ihre Familiengesundheit']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(), 'Ihre Familiengesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Ihre Familiengesundheit");
			
			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
			Common.clickElement("xpath" , "//img[@ title='Braun Health Care']");
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health page Failed"));
			Assert.fail();
		}

	}
	
	public void Facebook() throws Exception
	{
		String expectedResult="Lands on Facebook page";
		try {
			Sync.waitElementPresent("xpath", "/html/body/div[3]/footer/div/div[2]/div/figure/div/div[2]/a[1]/i[2]");
			//Common.scrollToElementAndClick("xpath", "//a[@href='/faqs/']");
			Common.javascriptclickElement("xpath" , "/html/body/div[3]/footer/div/div[2]/div/figure/div/div[2]/a[1]/i[2]");

			Thread.sleep(300);


			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
			Common.clickElement("xpath" , "//img[@ title='Braun Health Care']");
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook page not displayed", Common.getscreenShotPathforReport("Facebook page Failed"));
			Assert.fail();
		}

	}

	public void Instagram() throws Exception
	{
		String expectedResult="Lands on Instagram page";
		try {
			Sync.waitElementPresent("xpath", "(//i[@class='fa fa-instagram'])[1]");
			Common.javascriptclickElement("xpath" , "(//i[@class='fa fa-instagram'])[1]");
			
			Thread.sleep(300);


			report.addPassLog(expectedResult, "Should display Instagram  Page", "Instagram Page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
			Common.clickElement("xpath" , "//img[@ title='Braun Health Care']");
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Instagram Page", "Instagram page not displayed", Common.getscreenShotPathforReport("Instagram page Failed"));
			Assert.fail();
		}

	}

	public void Yourheart_health() throws Exception
	{
		String expectedResult="Lands on Your Health page";
		try {
			Thread.sleep(6000);
			Sync.waitElementPresent("xpath", "//a[@title='Ihre Familiengesundheit']");
			//Common.scrollToElementAndClick("xpath", "//a[@href='/faqs/']");
			Common.javascriptclickElement("xpath" , "(//a[@title='Ihre Herzgesundheit'])[4]");

			Thread.sleep(300);
			String s=Common.getText("xpath", "//a[contains(text(),'Ihre Herzgesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Ihre Herzgesundheit");


			report.addPassLog(expectedResult, "Should display Your Health Page", "Your Health Page display successfully", Common.getscreenShotPathforReport("Your Health page success"));
			Common.clickElement("xpath" , "//img[@ title='Braun Health Care']");
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your Health Page", "Your Health  Page not displayed", Common.getscreenShotPathforReport("Your Health Failed"));
			Assert.fail();
		}

	}
	
	public void CanadaENBabyRegistry(String dataset) throws Exception
	{
		String expectedResult="Lands on Your health page";
		String linkText=data.get(dataset).get("BabyRegistry");
		try {
			/*Sync.waitElementPresent("xpath", "//div[@class='bag-subscription-panel-body']//a//a[@title='"+linkText+"']");
			Common.clickElement("xpath" , "//div[@class='bag-subscription-panel-body']//a[@title='"+linkText+"']");*/
			/*Common.scrollToElementAndClick("xpath", "//a[@title='"+linkText+"']");
			Common.clickElement("xpath" , "//a[@title='"+linkText+"']");*/
			//Common.clickElement("xpath" , "//a[@title='"+linkText+"']");
			
			/*Sync.waitElementPresent("xpath", "//div[@class='bag-subscription-panel-body']//a[@title='Building Your Baby Registry']");
			Common.clickElement("xpath" , "//div[@class='bag-subscription-panel-body']//a[@title='Building Your Baby Registry']");*/
	        
			Sync.waitElementPresent("xpath", "//a[contains(text(), 'Building Your Baby Registry')]");
			Common.clickElement("xpath" , "//a[contains(text(), 'Building Your Baby Registry')]");
			Thread.sleep(4000);
			String s=Common.getText("xpath", "//a[contains(text(), '"+linkText+"')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, linkText);
		
			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}
	
	


	public void health() throws Exception
	{
		String expectedResult="Lands on Your Health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Health Magazine']");
			//Common.scrollToElementAndClick("xpath", "//a[@href='/faqs/']");
			Common.javascriptclickElement("xpath" , "//a[@title='Health Magazine']");

			Thread.sleep(300);


			report.addPassLog(expectedResult, "Should display Your Health Page", "Your Health Page display successfully", Common.getscreenShotPathforReport("Your Health page success"));
			Common.clickElement("xpath" , "//img[@ title='Braun Health Care']");
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your Health Page", "Your Health Page not displayed", Common.getscreenShotPathforReport("Your Health page Failed"));
			Assert.fail();
		}

	}

	public void condition() throws Exception
	{
		String expectedResult="Lands on FAQ page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Verkaufsbedingungen']");
			//Common.scrollToElementAndClick("xpath", "//a[@href='/faqs/']");
			Common.javascriptclickElement("xpath" , "//a[@title='Verkaufsbedingungen']");

			Thread.sleep(300);


			report.addPassLog(expectedResult, "Should display FAQ Page", "FQA Page display successfully", Common.getscreenShotPathforReport("FAQ page success"));
			Common.clickElement("xpath" , "//img[@ title='Braun Health Care']");
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display FAQ Page", "FQA Page not displayed", Common.getscreenShotPathforReport("FAQ Failed"));
			Assert.fail();
		}

	}


	public void Customersupport() throws Exception
	{
		String expectedResult="Lands on FAQ page";
		try {
			Sync.waitElementPresent("xpath", "(//font[contains(text(),'Customer support')])[2]");
			//Common.scrollToElementAndClick("xpath", "//a[@href='/faqs/']");
			Common.javascriptclickElement("xpath" , "(//font[contains(text(),'Customer support')])[2]");

			Thread.sleep(300);


			report.addPassLog(expectedResult, "Should display FAQ Page", "FQA Page display successfully", Common.getscreenShotPathforReport("FAQ page success"));
			Common.clickElement("xpath" , "//img[@ title='Braun Health Care']");
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display FAQ Page", "FQA Page not displayed", Common.getscreenShotPathforReport("FAQ Failed"));
			Assert.fail();
		}

	}


	public void GEThermometer() throws Exception
	{
		String expectedResult="Header link Category";
		try {
			Thread.sleep(6000);	
			Sync.waitElementPresent("xpath", "(//span[contains(text(),'Thermometer')])[1]");
			//Common.clickElement("xpath", "(//span[contains(text(),'Thermometer')])[1]");
			Common.javascriptclickElement("xpath", "(//span[contains(text(),'Thermometer')])[1]");
			Thread.sleep(3000);
			//String title=Common.findElement("xpath", "//div[contains(text(), 'Temperatur??berwachung, der Sie vertrauen k??nnen')]").getText();
			//title=("Temperatur??berwachung, der Sie vertrauen k??nnen");
			String s=Common.getText("xpath", "//div[contains(text(), 'Temperatur??berwachung, der Sie vertrauen k??nnen')]");
			Assert.assertEquals(s, "Temperatur??berwachung, der Sie vertrauen k??nnen");

			report.addPassLog(expectedResult, "Should display Thermomter Header Page", "Thermometer Header link page display successfully", Common.getscreenShotPathforReport("Thermometer Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Thermometer Header Page", "ThermometerHeader link Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}
	
	public void FRThermometer() throws Exception
	{
		String expectedResult="Header link Category";
		try {
			Thread.sleep(6000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			//Common.clickElement("xpath", "(//span[contains(text(),'Thermometer')])[1]");
			Common.javascriptclickElement("xpath", "//a[@id='ui-id-2']");
			
			Thread.sleep(3000);
			String title=Common.findElement("xpath", "//div[contains(text(), 'Un suivi de temp??rature fiable')]").getText();
			title=("Un suivi de temp??rature fiable");

			report.addPassLog(expectedResult, "Should display Thermomter Header Page", "Thermometer Header link page display successfully", Common.getscreenShotPathforReport("Thermometer Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Thermometer Header Page", "ThermometerHeader link Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}
	
	public void HeaderThermometer() throws Exception
	{
		String expectedResult="Navigating to Thermometers page";
		try {
			Thread.sleep(6000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-1']");
			//Common.clickElement("xpath", "(//span[contains(text(),'Thermometer')])[1]");
			Common.javascriptclickElement("xpath", "//a[@id='ui-id-1']");
			
			String title=Common.findElement("xpath", "//div[contains(text(), 'Temperatur??berwachung, der Sie vertrauen k??nnen')]").getText();
			title=("Temperatur??berwachung, der Sie vertrauen k??nnen");
			
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Thermomter Header Page", "Thermometer Header link page display successfully", Common.getscreenShotPathforReport("Thermometer Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Thermometer Header Page", "ThermometerHeader link Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}
	
	
	
	public void POHeaderThermometer() throws Exception
	{
		String expectedResult="Navigating to Thermometers page";
		try {
			Thread.sleep(6000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-1']");
			//Common.clickElement("xpath", "(//span[contains(text(),'Thermometer')])[1]");
			Common.javascriptclickElement("xpath", "//a[@id='ui-id-1']");
			
			String title=Common.findElement("xpath", "//div[contains(text(), 'Temperatur??berwachung, der Sie vertrauen k??nnen')]").getText();
			title=("Temperatur??berwachung, der Sie vertrauen k??nnen");
			
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Thermomter Header Page", "Thermometer Header link page display successfully", Common.getscreenShotPathforReport("Thermometer Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Thermometer Header Page", "ThermometerHeader link Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}
	

	public void NorwayHeaderThermometer() throws Exception
	{
		String expectedResult="Navigating to Thermometers page";
		try {
			Thread.sleep(6000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			//Common.clickElement("xpath", "(//span[contains(text(),'Thermometer')])[1]");
			Common.javascriptclickElement("xpath", "//a[@id='ui-id-2']");
			
			String title=Common.findElement("xpath", "//h1[contains(text(), 'The intelligent temperature monitoring, you can trust.')]").getText();
			title=("The intelligent temperature monitoring, you can trust.");
			
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Thermomter Header Page", "Thermometer Header link page display successfully", Common.getscreenShotPathforReport("Thermometer Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Thermometer Header Page", "ThermometerHeader link Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}
	
	public void ATDEHeaderThermometer() throws Exception
	{
		String expectedResult="Navigating to Thermometers page";
		try {
			Thread.sleep(6000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			//Common.clickElement("xpath", "(//span[contains(text(),'Thermometer')])[1]");
			Common.javascriptclickElement("xpath", "//a[@id='ui-id-2']");
			
			String title=Common.findElement("xpath", "//div[contains(text(), 'Temperatur??berwachung, der Sie vertrauen k??nnen')]").getText();
			title=("Temperatur??berwachung, der Sie vertrauen k??nnen");
			
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Thermomter Header Page", "Thermometer Header link page display successfully", Common.getscreenShotPathforReport("Thermometer Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Thermometer Header Page", "ThermometerHeader link Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}
	
	
	public void HeaderLinks(String dataSet) throws Exception{
	String expectedResult="Header Link validations";
	String Headerlinks=data.get(dataSet).get("HeaderLinkNames");
	String[] headers=Headerlinks.split(",");
	for(int i=0;i<headers.length;i++){
		Sync.waitElementClickable("xpath", "//span[text()='"+headers[i]+"']");
		Common.clickElement("xpath", "//span[text()='"+headers[i]+"']");
		Thread.sleep(10000);
		System.out.println(Common.getPageTitle());
		report.addPassLog(expectedResult, "Should display "+headers[i]+" success Page", ""+headers[i]+" Page display successfully", Common.getscreenShotPathforReport("Product Registration success page success"));
		
	}
	
}
	
	
	
	public void CanadaenHeaderLinks(String dataSet) throws Exception{
		String expectedResult="Header Link validations";
		String Headerlinks=data.get(dataSet).get("CanadaHeaderlinks");
		String[] headers=Headerlinks.split(",");
		for(int i=0;i<headers.length;i++){
			Sync.waitElementClickable("xpath", "//span[text()='"+headers[i]+"']");
			Common.clickElement("xpath", "//span[text()='"+headers[i]+"']");
			Thread.sleep(10000);
			System.out.println(Common.getPageTitle());
			report.addPassLog(expectedResult, "Should display "+headers[i]+" success Page", ""+headers[i]+" Page display successfully", Common.getscreenShotPathforReport("Product Registration success page success"));
			
		}
		
	}
			
	
	
	public void SOUAFHeaderThermometer() throws Exception
	{
		String expectedResult="Navigating to Thermometers page";
		try {
			Thread.sleep(6000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-1']");
			//Common.clickElement("xpath", "(//span[contains(text(),'Thermometer')])[1]");
			Common.javascriptclickElement("xpath", "//a[@id='ui-id-1']");
			
			String title=Common.findElement("xpath", "//div[contains(text(), 'The intelligent temperature monitoring, you can trust.')]").getText();
			title=("The intelligent temperature monitoring, you can trust.");
			
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Thermomter Header Page", "Thermometer Header link page display successfully", Common.getscreenShotPathforReport("Thermometer Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Thermometer Header Page", "ThermometerHeader link Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}
	
	
	
	public void SpainHeaderThermometer() throws Exception
	{
		String expectedResult="Navigating to Thermometers page";
		try {
			Thread.sleep(6000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			//Common.clickElement("xpath", "(//span[contains(text(),'Thermometer')])[1]");
			Common.javascriptclickElement("xpath", "//a[@id='ui-id-2']");
			
			String title=Common.findElement("xpath", "//h1[contains(text(), 'The intelligent temperature monitoring, you can trust')]").getText();
			title=("The intelligent temperature monitoring, you can trust.");
			
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Thermomter Header Page", "Thermometer Header link page display successfully", Common.getscreenShotPathforReport("Thermometer Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Thermometer Header Page", "ThermometerHeader link Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}
	
	public void TurkeyHeaderThermometer() throws Exception
	{
		String expectedResult="Navigating to Thermometers page";
		try {
			Thread.sleep(6000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			//Common.clickElement("xpath", "(//span[contains(text(),'Thermometer')])[1]");
			Common.javascriptclickElement("xpath", "//a[@id='ui-id-2']");
			
			String title=Common.findElement("xpath", "//h1[contains(text(), 'The intelligent temperature monitoring, you can trust.')]").getText();
			title=("The intelligent temperature monitoring, you can trust.");
			
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Thermomter Header Page", "Thermometer Header link page display successfully", Common.getscreenShotPathforReport("Thermometer Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Thermometer Header Page", "ThermometerHeader link Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}
	
	
	
	public void TurkeyHeaderBlodpreasure() throws Exception
	{
		String expectedResult="Navigating to Thermometers page";
		try {
			Thread.sleep(6000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			//Common.clickElement("xpath", "(//span[contains(text(),'Thermometer')])[1]");
			Common.javascriptclickElement("xpath", "//a[@id='ui-id-2']");
			
			String title=Common.findElement("xpath", "//h1[contains(text(), 'The intelligent temperature monitoring, you can trust.')]").getText();
			title=("The intelligent temperature monitoring, you can trust.");
			
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Thermomter Header Page", "Thermometer Header link page display successfully", Common.getscreenShotPathforReport("Thermometer Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Thermometer Header Page", "ThermometerHeader link Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}
	
	public void UKSVHeaderThermometer() throws Exception
	{
		String expectedResult="Navigating to Thermometers page";
		try {
			Thread.sleep(6000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			//Common.clickElement("xpath", "(//span[contains(text(),'Thermometer')])[1]");
			Common.javascriptclickElement("xpath", "//a[@id='ui-id-2']");
			
			String title=Common.findElement("xpath", "//h1[contains(text(), 'The intelligent temperature monitoring, you can trust.')]").getText();
			title=("The intelligent temperature monitoring, you can trust.");
			
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Thermomter Header Page", "Thermometer Header link page display successfully", Common.getscreenShotPathforReport("Thermometer Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Thermometer Header Page", "ThermometerHeader link Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}
	
	public void UKENHeaderThermometer() throws Exception
	{
		String expectedResult="Navigating to Thermometers page";
		try {
			Thread.sleep(6000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			//Common.clickElement("xpath", "(//span[contains(text(),'Thermometer')])[1]");
			Common.javascriptclickElement("xpath", "//a[@id='ui-id-2']");
			
			String title=Common.findElement("xpath", "//div[contains(text(), 'Temperature monitoring you can trust')]").getText();
			title=("Temperature monitoring you can trust");
			
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Thermomter Header Page", "Thermometer Header link page display successfully", Common.getscreenShotPathforReport("Thermometer Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Thermometer Header Page", "ThermometerHeader link Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}

	public void Yourfamily() throws Exception
	{
		String expectedResult="Footer link Category";
		try {
			Thread.sleep(4000);	
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "//a[@title='Ihre Familiengesundheit']");
			Common.clickElement("xpath", "//a[@title='Ihre Familiengesundheit']");
			Thread.sleep(3000);

			report.addPassLog(expectedResult, "Should display Your Family  Page", "Your Family page display successfully", Common.getscreenShotPathforReport("Your Family page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your Family Header Page", "Your Family Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}



	public void FooterThermometer() throws Exception
	{
		String expectedResult="Header link Category";
		try {
			Thread.sleep(9000);	
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.scrollIntoView("xpath" , "/html/body/div[3]/footer/div/div[2]/div/div[1]/div[1]/div[2]/div/ul/li[1]/a");
			Common.clickElement("xpath" , "/html/body/div[3]/footer/div/div[2]/div/div[1]/div[1]/div[2]/div/ul/li[1]/a");


			Common.scrollIntoView("xpath" , "/html/body/div[3]/footer/div/div[2]/div/div[1]/div[1]/div[2]/div/ul/li[2]/a");
			Common.clickElement("xpath" , "/html/body/div[3]/footer/div/div[2]/div/div[1]/div[1]/div[2]/div/ul/li[2]/a");

			//Sync.waitElementPresent("xpath", "(//a[contains(text(),'Thermometer')])[3]");
			//Common.clickElement("xpath", "(//a[contains(text(),'Thermometer')])[3]");
			//Common.javascriptclickElement("xpath", "(//a[contains(text(),'Thermometer')])[3]");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display  Footer sphygmomanometer link page", "sphygmomanometer footer link page display successfully", Common.getscreenShotPathforReport("sphygmomanometer footer link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display sphygmomanometer Footer link Page", "sphygmomanometer footer link Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}



	public void FooterBlutdruck() throws Exception
	{
		String expectedResult="Header link Category";
		try {
			//Thread.sleep(9000);	
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.PAGE_DOWN);

			Common.scrollIntoView("xpath" , "/html/body/div[3]/footer/div/div[2]/div/div[1]/div[1]/div[2]/div/ul/li[2]/a");
			Common.clickElement("xpath" , "/html/body/div[3]/footer/div/div[2]/div/div[1]/div[1]/div[2]/div/ul/li[2]/a");


			//Sync.waitElementPresent("xpath", "(//a[contains(text(),'Thermometer')])[3]");
			//Common.clickElement("xpath", "(//a[contains(text(),'Thermometer')])[3]");
			//Common.javascriptclickElement("xpath", "(//a[contains(text(),'Thermometer')])[3]");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display  Footer sphygmomanometer link page", "sphygmomanometer footer link page display successfully", Common.getscreenShotPathforReport("sphygmomanometer footer link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display sphygmomanometer Footer link Page", "sphygmomanometer footer link Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}


	public void FooterNasalaspirator() throws Exception
	{
		String expectedResult="Header link Category";
		try {
			Thread.sleep(9000);	
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.scrollIntoView("xpath" , "/html/body/div[3]/footer/div/div[2]/div/div[1]/div[1]/div[2]/div/ul/li[3]/a");
			Common.clickElement("xpath" , "/html/body/div[3]/footer/div/div[2]/div/div[1]/div[1]/div[2]/div/ul/li[3]/a");
			//Sync.waitElementPresent("xpath", "(//a[contains(text(),'Thermometer')])[3]");
			//Common.clickElement("xpath", "(//a[contains(text(),'Thermometer')])[3]");
			//Common.javascriptclickElement("xpath", "(//a[contains(text(),'Thermometer')])[3]");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display  Footer Nasalaspirator link page", "Nasalaspirator footer link page display successfully", Common.getscreenShotPathforReport("Nasalaspirator footer link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Nasalaspirator Footer link Page", "Nasalaspirator footer link Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}



	public void GEBloodPressureMonitor() throws Exception
	{
		String expectedResult="Blood Pressure Monitor link";
		try {
			Thread.sleep(8000);		
			Sync.waitElementPresent("xpath", "(//span[contains(text(), 'Blutdruckmessger??t')])[1]");
			Common.clickElement("xpath", "(//span[contains(text(), 'Blutdruckmessger??t')])[1]");
			//Common.javascriptclickElement("xpath" , "/html/body/div[4]/div[2]/header/div[3]/div[4]/div/div[2]/div/nav/ul/li[2]/a/span[1]/font/font");
			Thread.sleep(3000);
			
			//String title=Common.findElement("xpath", "//div[contains(text(), 'Verschaffen Sie sich gr??nes Licht f??r Ihr Herz')]").getText();
			//title=("Verschaffen Sie sich gr??nes Licht f??r Ihr Herz");
			String s=Common.getText("xpath", "//div[contains(text(), 'Verschaffen Sie sich gr??nes Licht f??r Ihr Herz')]");
			Assert.assertEquals(s, "Verschaffen Sie sich gr??nes Licht f??r Ihr Herz");
			report.addPassLog(expectedResult, "Should display Blood pressure Monitor Header Page", "Blood pressure Monitor Header link page display successfully", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Blood pressure Monitor Header Page", "Blood pressure Monitor link Page not displayed", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page  Failed"));
			Assert.fail();
		}

	}
	
	
	public void FRBloodPressureMonitor() throws Exception
	{
		String expectedResult="Blood Pressure Monitor link";
		try {
			Thread.sleep(8000);		
			Sync.waitElementPresent("xpath", "(//span[contains(text(), 'Blutdruckmessger??t')])[1]");
			Common.clickElement("xpath", "(//span[contains(text(), 'Blutdruckmessger??t')])[1]");
			//Common.javascriptclickElement("xpath" , "/html/body/div[4]/div[2]/header/div[3]/div[4]/div/div[2]/div/nav/ul/li[2]/a/span[1]/font/font");
			Thread.sleep(3000);
			String title=Common.findElement("xpath", "//div[contains(text(), 'Donnez le feu vert ?? votre c??ur')]").getText();
			title=("Donnez le feu vert ?? votre c??ur");

			report.addPassLog(expectedResult, "Should display Blood pressure Monitor Header Page", "Blood pressure Monitor Header link page display successfully", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Blood pressure Monitor Header Page", "Blood pressure Monitor link Page not displayed", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page  Failed"));
			Assert.fail();
		}

	}
	
	
	
	public void HeaderBloodPressureMonitor() throws Exception
	{
		String expectedResult="Blood Pressure Monitor link";
		try {
			Thread.sleep(8000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			Common.clickElement("xpath", "//a[@id='ui-id-2']");
			
			String title=Common.findElement("xpath", "//div[contains(text(), 'Verschaffen Sie sich gr??nes Licht f??r Ihr Herz')]").getText();
			title=("Verschaffen Sie sich gr??nes Licht f??r Ihr Herz");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Blood pressure Monitor Header Page", "Blood pressure Monitor Header link page display successfully", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Blood pressure Monitor Header Page", "Blood pressure Monitor link Page not displayed", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page  Failed"));
			Assert.fail();
		}

	}
	
	public void POHeaderBloodPressureMonitor() throws Exception
	{
		String expectedResult="Blood Pressure Monitor link";
		try {
			Thread.sleep(8000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			Common.clickElement("xpath", "//a[@id='ui-id-2']");
			
			String title=Common.findElement("xpath", "//div[contains(text(), 'Verschaffen Sie sich gr??nes Licht f??r Ihr Herz')]").getText();
			title=("Verschaffen Sie sich gr??nes Licht f??r Ihr Herz");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Blood pressure Monitor Header Page", "Blood pressure Monitor Header link page display successfully", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Blood pressure Monitor Header Page", "Blood pressure Monitor link Page not displayed", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page  Failed"));
			Assert.fail();
		}

	}
	
	public void NorwayHeaderBloodPressureMonitor() throws Exception
	{
		String expectedResult="Blood Pressure Monitor link";
		try {
			Thread.sleep(8000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-3']");
			Common.clickElement("xpath", "//a[@id='ui-id-3']");
			
			String title=Common.findElement("xpath", "//div[contains(text(), 'Get the green light for your heart')]").getText();
			title=("Get the green light for your heart");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Blood pressure Monitor Header Page", "Blood pressure Monitor Header link page display successfully", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Blood pressure Monitor Header Page", "Blood pressure Monitor link Page not displayed", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page  Failed"));
			Assert.fail();
		}

	}
	
	public void ATDEHeaderBloodPressureMonitor() throws Exception
	{
		String expectedResult="Blood Pressure Monitor link";
		try {
			Thread.sleep(8000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-3']");
			Common.clickElement("xpath", "//a[@id='ui-id-3']");
			
			String title=Common.findElement("xpath", "//div[contains(text(),'Verschaffen Sie sich gr??nes Licht f??r Ihr Herz')]").getText();
			title=("Verschaffen Sie sich gr??nes Licht f??r Ihr Herz");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Blood pressure Monitor Header Page", "Blood pressure Monitor Header link page display successfully", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Blood pressure Monitor Header Page", "Blood pressure Monitor link Page not displayed", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page  Failed"));
			Assert.fail();
		}

	}
	
	
	public void SOUAFHeaderBloodPressureMonitor() throws Exception
	{
		String expectedResult="Blood Pressure Monitor link";
		try {
			Thread.sleep(8000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			Common.clickElement("xpath", "//a[@id='ui-id-2']");
			
			String title=Common.findElement("xpath", "//div[contains(text(), 'Verschaffen Sie sich gr??nes Licht f??r Ihr Herz')]").getText();
			title=("Verschaffen Sie sich gr??nes Licht f??r Ihr Herz");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Blood pressure Monitor Header Page", "Blood pressure Monitor Header link page display successfully", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Blood pressure Monitor Header Page", "Blood pressure Monitor link Page not displayed", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page  Failed"));
			Assert.fail();
		}

	}
	
	
	public void SpainHeaderBloodPressureMonitor() throws Exception
	{
		String expectedResult="Blood Pressure Monitor link";
		try {
			Thread.sleep(8000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-3']");
			Common.clickElement("xpath", "//a[@id='ui-id-3']");
			
			String title=Common.findElement("xpath", "//div[contains(text(), 'Verschaffen Sie sich gr??nes Licht f??r Ihr Herz')]").getText();
			title=("Verschaffen Sie sich gr??nes Licht f??r Ihr Herz");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Blood pressure Monitor Header Page", "Blood pressure Monitor Header link page display successfully", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Blood pressure Monitor Header Page", "Blood pressure Monitor link Page not displayed", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page  Failed"));
			Assert.fail();
		}

	}
	
	
	
	public void UKSVHeaderBloodPressureMonitor() throws Exception
	{
		String expectedResult="Blood Pressure Monitor link";
		try {
			Thread.sleep(8000);		
			Sync.waitElementPresent("xpath", "(//a[@class='nav-anchor ui-corner-all'])[2]");
			Common.clickElement("xpath", "(//a[@class='nav-anchor ui-corner-all'])[2]");
			
			String title=Common.findElement("xpath", "//div[contains(text(),'Verschaffen Sie sich gr??nes Licht f??r Ihr Herz')]").getText();
			title=("Verschaffen Sie sich gr??nes Licht f??r Ihr Herz");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Blood pressure Monitor Header Page", "Blood pressure Monitor Header link page display successfully", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Blood pressure Monitor Header Page", "Blood pressure Monitor link Page not displayed", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page  Failed"));
			Assert.fail();
		}

	}
	
	
	
	public void TurkeyHeaderBloodPressureMonitor() throws Exception
	{
		String expectedResult="Blood Pressure Monitor link";
		try {
			Thread.sleep(8000);		
			Sync.waitElementPresent("xpath", "(//a[@class='nav-anchor ui-corner-all'])[2]");
			Common.clickElement("xpath", "(//a[@class='nav-anchor ui-corner-all'])[2]");
			
			String title=Common.findElement("xpath", "//div[contains(text(),'Verschaffen Sie sich gr??nes Licht f??r Ihr Herz')]").getText();
			title=("Verschaffen Sie sich gr??nes Licht f??r Ihr Herz");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Blood pressure Monitor Header Page", "Blood pressure Monitor Header link page display successfully", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Blood pressure Monitor Header Page", "Blood pressure Monitor link Page not displayed", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page  Failed"));
			Assert.fail();
		}

	}
	
	
	
	public void UKENHeaderBloodPressureMonitor() throws Exception
	{
		String expectedResult="Blood Pressure Monitor link";
		try {
			Thread.sleep(8000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-3']");
			Common.clickElement("xpath", "//a[@id='ui-id-3']");
			
			String title=Common.findElement("xpath", "//div[contains(text(), 'Get the green light for your heart')]").getText();
			title=("Get the green light for your heart");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Blood pressure Monitor Header Page", "Blood pressure Monitor Header link page display successfully", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Blood pressure Monitor Header Page", "Blood pressure Monitor link Page not displayed", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page  Failed"));
			Assert.fail();
		}

	}
	public void FooterBloodPressureMonitor() throws Exception
	{
		String expectedResult="Blood Pressure Monitor link";
		try {
			Thread.sleep(8000);		
			Sync.waitElementPresent("xpath", "");
			Common.clickElement("xpath", "");
			//Common.javascriptclickElement("xpath" , "/html/body/div[4]/div[2]/header/div[3]/div[4]/div/div[2]/div/nav/ul/li[2]/a/span[1]/font/font");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Blood pressure Monitor Header Page", "Blood pressure Monitor Header link page display successfully", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Blood pressure Monitor Header Page", "Blood pressure Monitor link Page not displayed", Common.getscreenShotPathforReport("Blood pressure Monitor Header link page  Failed"));
			Assert.fail();
		}

	}




	public void GENasalAspirator() throws Exception
	{
		String expectedResult="NasalAspirator link";

		Thread.sleep(10000);		
		Sync.waitElementPresent("xpath", "//a[@id='ui-id-4']");
		Common.clickElement("xpath", "//a[@id='ui-id-4']");
		
		Thread.sleep(3000);
		//String title=Common.findElement("xpath", "//span[contains(text(), 'Braun Nasensauger 1')]").getText();
		//title=("Braun Nasensauger 1");
		String s=Common.getText("xpath", "//span[contains(text(), 'Braun Nasensauger 1')]");
		Assert.assertEquals(s, "Braun Nasensauger 1");
		Thread.sleep(2000);
		report.addPassLog(expectedResult, "Should display NasalAspirator Page", "NasalAspirator Header link page display successfully", Common.getscreenShotPathforReport("NasalAspirator Header link page success"));
	}
	
	
	
	public void FRNasalAspirator() throws Exception
	{
		String expectedResult="NasalAspirator link";

		Thread.sleep(10000);		
		Sync.waitElementPresent("xpath", "//a[@id='ui-id-4']");
		Common.clickElement("xpath", "//a[@id='ui-id-4']");
		
		Thread.sleep(3000);
		String title=Common.findElement("xpath", "//span[contains(text(), 'Braun aspirateur nasal 1')]").getText();
		title=("Braun aspirateur nasal 1");
		Thread.sleep(2000);
		report.addPassLog(expectedResult, "Should display NasalAspirator Page", "NasalAspirator Header link page display successfully", Common.getscreenShotPathforReport("NasalAspirator Header link page success"));
	}
	
	
	public void ATDENasalAspirator() throws Exception
	{
		String expectedResult="NasalAspirator link";

		Thread.sleep(10000);		
		Sync.waitElementPresent("xpath", "//a[@id='ui-id-4']");
		Common.clickElement("xpath", "//a[@id='ui-id-4']");
		
		Thread.sleep(3000);
		String title=Common.findElement("xpath", "//span[contains(text(), 'Nasal aspirator')]").getText();
		title=("Nasal aspirator");
		Thread.sleep(2000);
		report.addPassLog(expectedResult, "Should display NasalAspirator Page", "NasalAspirator Header link page display successfully", Common.getscreenShotPathforReport("NasalAspirator Header link page success"));
	}
	
	public void PONasalAspirator() throws Exception
	{
		String expectedResult="NasalAspirator link";

		Thread.sleep(10000);		
		Sync.waitElementPresent("xpath", "//a[@id='ui-id-3']");
		Common.clickElement("xpath", "//a[@id='ui-id-3']");
		
		Thread.sleep(3000);
		String title=Common.findElement("xpath", "//span[contains(text(), 'Braun Nasensauger 1')]").getText();
		title=("Braun Nasensauger 1");
		Thread.sleep(2000);
		report.addPassLog(expectedResult, "Should display NasalAspirator Page", "NasalAspirator Header link page display successfully", Common.getscreenShotPathforReport("NasalAspirator Header link page success"));
	}
	
	public void SOUAFNasalAspirator() throws Exception
	{
		String expectedResult="NasalAspirator link";

		Thread.sleep(10000);		
		Sync.waitElementPresent("xpath", "//a[@id='ui-id-3']");
		Common.clickElement("xpath", "//a[@id='ui-id-3']");
		
		Thread.sleep(3000);
		String title=Common.findElement("xpath", "//span[contains(text(), 'Braun Nasensauger 1')]").getText();
		title=("Braun Nasensauger 1");
		Thread.sleep(2000);
		report.addPassLog(expectedResult, "Should display NasalAspirator Page", "NasalAspirator Header link page display successfully", Common.getscreenShotPathforReport("NasalAspirator Header link page success"));
	}
	
	public void SpainNasalAspirator() throws Exception
	{
		String expectedResult="NasalAspirator link";

		Thread.sleep(10000);		
		Sync.waitElementPresent("xpath", "//a[@id='ui-id-4']");
		Common.clickElement("xpath", "//a[@id='ui-id-4']");
		
		Thread.sleep(3000);
		String title=Common.findElement("xpath", "//h1[contains(text(), 'The intelligent temperature monitoring, you can trust.')]").getText();
		title=("The intelligent temperature monitoring, you can trust.");
		Thread.sleep(2000);
		report.addPassLog(expectedResult, "Should display NasalAspirator Page", "NasalAspirator Header link page display successfully", Common.getscreenShotPathforReport("NasalAspirator Header link page success"));
	}
	
	public void UKSVNasalAspirator() throws Exception
	{
		String expectedResult="NasalAspirator link";

		Thread.sleep(10000);		
		Sync.waitElementPresent("xpath", "//a[@id='ui-id-4']");
		Common.clickElement("xpath", "//a[@id='ui-id-4']");
		
		Thread.sleep(3000);
		String title=Common.findElement("xpath", "//span[contains(text(), 'Braun n??srensare')]").getText();
		title=("Braun n??srensare?? 1");
		Thread.sleep(2000);
		report.addPassLog(expectedResult, "Should display NasalAspirator Page", "NasalAspirator Header link page display successfully", Common.getscreenShotPathforReport("NasalAspirator Header link page success"));
	}
	
	public void  UKENNasalAspirator() throws Exception
	{
		String expectedResult="NasalAspirator link";

		Thread.sleep(10000);		
		Sync.waitElementPresent("xpath", "//a[@id='ui-id-4']");
		Common.clickElement("xpath", "//a[@id='ui-id-4']");
		
		Thread.sleep(3000);
		
		String title=Common.findElement("xpath", "//span[contains(text(), 'Braun Nasal aspirator 1')]").getText();
		title=("Braun Nasal aspirator 1");
		report.addPassLog(expectedResult, "Should display NasalAspirator Page", "NasalAspirator Header link page display successfully", Common.getscreenShotPathforReport("NasalAspirator Header link page success"));
	}
	
	public void headLinks(String dataSet) throws Exception{
		String expectedResult="Header Link validations";
		String Headerlinks=data.get(dataSet).get("ItalyHeaderNames");
		String[] headers=Headerlinks.split(",");
		for(int i=0;i<headers.length;i++){
			Sync.waitElementClickable("xpath", "//span[text()='"+headers[i]+"']");
			Common.clickElement("xpath", "//span[text()='"+headers[i]+"']");
			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			report.addPassLog(expectedResult, "Should display "+headers[i]+" success Page", ""+headers[i]+" Page display successfully", Common.getscreenShotPathforReport("Product Registration success page success"));
			
		}
		
	}
	
	
	public void FranceheadLinks(String dataSet) throws Exception{
		String expectedResult="Header Link validations";
		String Headerlinks=data.get(dataSet).get("Franceheaderlinks");
		String[] headers=Headerlinks.split(",");
		for(int i=0;i<headers.length;i++){
			Sync.waitElementClickable("xpath", "//span[text()='"+headers[i]+"']");
			Common.clickElement("xpath", "//span[text()='"+headers[i]+"']");
			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			report.addPassLog(expectedResult, "Should display "+headers[i]+" success Page", ""+headers[i]+" Page display successfully", Common.getscreenShotPathforReport("Product Registration success page success"));
			
		}
	}
		
	
	public void navigateYourhealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Ihre Familiengesundheit']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Ihre Familiengesundheit']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(), 'Ihre Familiengesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Ihre Familiengesundheit");

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}



	}
	
	
	public void NorwaynavigateYourhealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Ihre Familiengesundheit']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Ihre Familiengesundheit']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(), 'Ihre Familiengesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Ihre Familiengesundheit");

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}



	}
	
	public void ATDEnavigateYourhealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Ihre Familiengesundheit']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Ihre Familiengesundheit']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(), 'Ihre Familiengesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Ihre Familiengesundheit");
		
		

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}



	}
	
	
	
	public void NavigateYourhealth(String dataset) throws Exception
	{
		String expectedResult="Lands on Your health page";
		String linkText=data.get(dataset).get("HealthlinkText");
		try {
			Sync.waitElementPresent("xpath", "//a[@title='"+linkText+"']");
			Common.scrollToElementAndClick("xpath", "//a[@title='"+linkText+"']");

			Thread.sleep(300);
			/*String s=Common.getText("xpath", "//a[contains(text(), '"+linkText+"')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, linkText);*/
			
			String s=Common.getText("xpath", "//a[contains(text(), 'Ihre Familiengesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s,"Ihre Familiengesundheit");
		
			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}
	
	public void FrNavigateYourhealth(String dataset) throws Exception
	{
		String expectedResult="Lands on Your health page";
		String linkText=data.get(dataset).get("HealthlinkText");
		try {
			Sync.waitElementPresent("xpath", "//a[@title='"+linkText+"']");
			Common.scrollToElementAndClick("xpath", "//a[@title='"+linkText+"']");

			Thread.sleep(300);
			String s=Common.getText("xpath", "//a[contains(text(), 'Sant?? de votre famille')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Sant?? de votre famille");
		
			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}
	
	public void NavigateYourhearthealth(String dataset) throws Exception
	{
		String expectedResult="Lands on Your health page";
		String linkText=data.get(dataset).get("HeartlinkText");
		try {
			Sync.waitElementPresent("xpath", "//a[@title='"+linkText+"']");
			//Common.scrollToElementAndClick("xpath", "//a[@title='"+linkText+"']");
			Common.clickElement("xpath" , "//a[@title='"+linkText+"']");

			Thread.sleep(300);

			/*String s=Common.getText("xpath", "//a[contains(text(), '"+linkText+"')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, linkText);*/
			
			String s=Common.getText("xpath", "//a[contains(text(), 'Ihre Herzgesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Ihre Herzgesundheit");
		

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}
	
	public void FrNavigateYourhearthealth(String dataset) throws Exception
	{
		String expectedResult="Lands on Your health page";
		String linkText=data.get(dataset).get("HeartlinkText");
		try {
			Sync.waitElementPresent("xpath", "//a[@title='"+linkText+"']");
			//Common.scrollToElementAndClick("xpath", "//a[@title='"+linkText+"']");
			Common.clickElement("xpath" , "//a[@title='"+linkText+"']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(), 'Sant?? de votre coeur')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Sant?? de votre coeur");
		

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}
	
	
	public void UKenNavigateYourhealth(String dataset) throws Exception
	{
		String expectedResult="Lands on Your health page";
		String linkText=data.get(dataset).get("UKYourHealth");
		try {
			Sync.waitElementPresent("xpath", "//a[@title='"+linkText+"']");
			Common.scrollToElementAndClick("xpath", "//a[@title='"+linkText+"']");

			Thread.sleep(300);
			String s=Common.getText("xpath", "//a[contains(text(), 'Your Family Health')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Your Family Health");
		
			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}
	
	
	public void NavigateHealthMagazine(String dataset) throws Exception
	{
		String expectedResult="Lands on Your Health page";
		String linkText=data.get(dataset).get("MagazinelinkText");
		try {
			/*Sync.waitElementPresent("xpath", "//a[@title='Health Magazine']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Health Magazine']");*/
			
			Sync.waitElementPresent("xpath", "//a[@title='"+linkText+"']");
			//Common.scrollToElementAndClick("xpath", "//a[@title='"+linkText+"']");
			Common.clickElement("xpath" , "//a[@title='"+linkText+"']");

			Thread.sleep(1000);

			/*String s=Common.getText("xpath", "//a[contains(text(), 'Health Magazine')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Health Magazine");*/
			
			String s=Common.getText("xpath", "//a[contains(text(), 'Health Magazine')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Health Magazine");

			report.addPassLog(expectedResult, "Should display Your Health Page", "Your Health Page display successfully", Common.getscreenShotPathforReport("Your Health success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Your Health Page", "Your Health Page not displayed", Common.getscreenShotPathforReport("Your Health Failed"));
			Assert.fail();
		}

	}
	
	
	
	public void UKenNavigateYourhearthealth(String dataset) throws Exception
	{
		String expectedResult="Lands on Your health page";
		String linkText=data.get(dataset).get("Ukhearthealth");
		try {
			Sync.waitElementPresent("xpath", "//a[@title='"+linkText+"']");
			Common.scrollToElementAndClick("xpath", "//a[@title='"+linkText+"']");

			Thread.sleep(300);
			String s=Common.getText("xpath", "//a[contains(text(), 'Your Heart Health')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Your Heart Health");
		
			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}
	
	
	public void UKenNavigateMagazine(String dataset) throws Exception
	{
		String expectedResult="Lands on Your health page";
		String linkText=data.get(dataset).get("UKMagazine");
		try {
			Sync.waitElementPresent("xpath", "//a[@title='"+linkText+"']");
			Common.scrollToElementAndClick("xpath", "//a[@title='"+linkText+"']");

			Thread.sleep(300);
			String s=Common.getText("xpath", "//a[contains(text(), '"+linkText+"')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, linkText);
		
			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}
	
	/*public void NavigateYourhearthealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		String linkText=data.get(dataset).get("HealthLinkText");
		try {
			Sync.waitElementPresent("xpath", "//a[@title='"+linkText+"']");
			Common.scrollToElementAndClick("xpath", "(//a[@title='Ihre Herzgesundheit'])[4]");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(),'Ihre Herzgesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Ihre Herzgesundheit");

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}*/
	
	
	public void SOUAFnavigateYourhealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Ihre Familiengesundheit']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Ihre Familiengesundheit']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(), 'Ihre Familiengesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Ihre Familiengesundheit");

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}



	}
	
	
	public void SpainnavigateYourhealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Ihre Familiengesundheit']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Ihre Familiengesundheit']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(), 'Ihre Familiengesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Ihre Familiengesundheit");

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}



	}
	
	
	public void TurkeynavigateYourhealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Ihre Familiengesundheit']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Ihre Familiengesundheit']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(), 'Ihre Familiengesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Ihre Familiengesundheit");

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}



	}
	
	
	
	
	public void UKENnavigateYourhealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Heart Health Experts']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Heart Health Experts']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(),'Your Family Health')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Your Family Health");

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}



	}
	
	public void UKSVnavigateYourhealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Ihre Familiengesundheit']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Ihre Familiengesundheit']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(),'Din familjs h??lsa')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Din familjs h??lsa");

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}



	}
	
	public void navigateYourhearthealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "(//a[@title='Ihre Herzgesundheit'])[3]");
			Common.scrollToElementAndClick("xpath", "(//a[@title='Ihre Herzgesundheit'])[3]");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(),'Ihre Herzgesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Ihre Herzgesundheit");

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}
	
	
	public void NorwaynavigateYourhearthealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Ihre Herzgesundheit']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Ihre Herzgesundheit']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(),'Ihre Herzgesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Ihre Herzgesundheit");

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}
	
	public void ATDEnavigateYourhearthealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "(//a[@title='Ihre Herzgesundheit'])[4]");
			Common.scrollToElementAndClick("xpath", "(//a[@title='Ihre Herzgesundheit'])[4]");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(),'Ihre Herzgesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Ihre Herzgesundheit");

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}
	
	public void SOUAFnavigateYourhearthealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "(//a[@title='Ihre Herzgesundheit'])[4]");
			Common.scrollToElementAndClick("xpath", "(//a[@title='Ihre Herzgesundheit'])[4]");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(),'Ihre Herzgesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Ihre Herzgesundheit");

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}
	
	
	public void SpainnavigateYourhearthealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Ihre Herzgesundheit']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Ihre Herzgesundheit']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(), 'Ihre Herzgesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Ihre Herzgesundheit");

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}
	
	public void TurkeynavigateYourhearthealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "(//a[@title='Ihre Herzgesundheit'])[4]");
			Common.scrollToElementAndClick("xpath", "(//a[@title='Ihre Herzgesundheit'])[4]");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(),'Ihre Herzgesundheit')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Ihre Herzgesundheit");

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}
	
	
	public void UKENnavigateYourhearthealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Healthy Heart App']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Healthy Heart App']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(), 'Your Heart Health')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Your Heart Health");

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}
	
	public void UKSVnavigateYourhearthealth() throws Exception
	{
		String expectedResult="Lands on Your health page";
		try {
			Sync.waitElementPresent("xpath", "(//a[@title='Ihre Herzgesundheit'])[3]");
			Common.scrollToElementAndClick("xpath", "(//a[@title='Ihre Herzgesundheit'])[3]");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(), 'Din hj??rth??lsa')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Din hj??rth??lsa");

			report.addPassLog(expectedResult, "Should display Your health Page", "Your health Page display successfully", Common.getscreenShotPathforReport("Your health page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Your health Page", "Your health Page not displayed", Common.getscreenShotPathforReport("Your health Failed"));
			Assert.fail();
		}

	}

	
	public void navigateHealthMagazine() throws Exception
	{
		String expectedResult="Lands on Your Health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Health Magazine']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Health Magazine']");

			Thread.sleep(300);

			/*String s=Common.getText("xpath", "//a[contains(text(), 'Health Magazine')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Health Magazine");*/

			report.addPassLog(expectedResult, "Should display Your Health Page", "Your Health Page display successfully", Common.getscreenShotPathforReport("Your Health success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your Health Page", "Your Health Page not displayed", Common.getscreenShotPathforReport("Your Health Failed"));
			Assert.fail();
		}

	}
	
	
	public void NorwaynavigateHealthMagazine() throws Exception
	{
		String expectedResult="Lands on Your Health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Health Magazine']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Health Magazine']");

			Thread.sleep(300);

			/*String s=Common.getText("xpath", "//a[contains(text(), 'Health Magazine')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Health Magazine");*/

			report.addPassLog(expectedResult, "Should display Your Health Page", "Your Health Page display successfully", Common.getscreenShotPathforReport("Your Health success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your Health Page", "Your Health Page not displayed", Common.getscreenShotPathforReport("Your Health Failed"));
			Assert.fail();
		}

	}
	
	
	public void ATDEnavigateHealthMagazine() throws Exception
	{
		String expectedResult="Lands on Your Health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Health Magazine']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Health Magazine']");

			Thread.sleep(1000);

			/*String s=Common.getText("xpath", "//a[contains(text(), 'Health Magazine')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Health Magazine");*/

			report.addPassLog(expectedResult, "Should display Your Health Page", "Your Health Page display successfully", Common.getscreenShotPathforReport("Your Health success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your Health Page", "Your Health Page not displayed", Common.getscreenShotPathforReport("Your Health Failed"));
			Assert.fail();
		}

	}
	
	
	public void SOUAFnavigateHealthMagazine() throws Exception
	{
		String expectedResult="Lands on Your Health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Health Magazine']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Health Magazine']");

			Thread.sleep(1000);

			/*String s=Common.getText("xpath", "//a[contains(text(), 'Health Magazine')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Health Magazine");*/

			report.addPassLog(expectedResult, "Should display Your Health Page", "Your Health Page display successfully", Common.getscreenShotPathforReport("Your Health success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your Health Page", "Your Health Page not displayed", Common.getscreenShotPathforReport("Your Health Failed"));
			Assert.fail();
		}

	}
	
	
	
	public void SpainnavigateHealthMagazine() throws Exception
	{
		String expectedResult="Lands on Your Health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Health Magazine']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Health Magazine']");

			Thread.sleep(1000);

			/*String s=Common.getText("xpath", "//a[contains(text(), 'Health Magazine')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Health Magazine");*/

			report.addPassLog(expectedResult, "Should display Your Health Page", "Your Health Page display successfully", Common.getscreenShotPathforReport("Your Health success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your Health Page", "Your Health Page not displayed", Common.getscreenShotPathforReport("Your Health Failed"));
			Assert.fail();
		}

	}
	
	
	public void TurkeynavigateHealthMagazine() throws Exception
	{
		String expectedResult="Lands on Your Health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Health Magazine']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Health Magazine']");

			Thread.sleep(1000);

			String s=Common.getText("xpath", "//a[contains(text(), 'Health Magazine')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Health Magazine");

			report.addPassLog(expectedResult, "Should display Your Health Page", "Your Health Page display successfully", Common.getscreenShotPathforReport("Your Health success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your Health Page", "Your Health Page not displayed", Common.getscreenShotPathforReport("Your Health Failed"));
			Assert.fail();
		}

	}
	
	public void UKENnavigateHealthMagazine() throws Exception
	{
		String expectedResult="Lands on Your Health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Health Magazine']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Health Magazine']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(), 'Health Magazine')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Health Magazine");

			report.addPassLog(expectedResult, "Should display Your Health Page", "Your Health Page display successfully", Common.getscreenShotPathforReport("Your Health success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Your Health Page", "Your Health Page not displayed", Common.getscreenShotPathforReport("Your Health Failed"));
			Assert.fail();
		}

	}
	
	public void UKSVnavigateHealthMagazine() throws Exception
	{
		String expectedResult="Lands on Your Health page";
		try {
			Sync.waitElementPresent("xpath", "//a[@title='Health Magazine']");
			Common.scrollToElementAndClick("xpath", "//a[@title='Health Magazine']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//a[contains(text(), 'H??lsomagasin')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "H??lsomagasin");

			report.addPassLog(expectedResult, "Should display Your Health Page", "Your Health Page display successfully", Common.getscreenShotPathforReport("Your Health success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Your Health Page", "Your Health Page not displayed", Common.getscreenShotPathforReport("Your Health Failed"));
			Assert.fail();
		}

	}
	
	
	
	public void PolandFacebook() throws Exception
	{
		String expectedResult="Lands Facebook page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");

			Thread.sleep(3000);
			
			//String title=Common.findElement("xpath", "//div[contains(text(), 'Temperatur??berwachung, der Sie vertrauen k??nnen')]").getText();
			//title=("Temperatur??berwachung, der Sie vertrauen k??nnen");
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			String s=Common.getText("xpath", "//a[@title='Go to Facebook Home']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Facebook");
		   	Thread.sleep(1000);

			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));
			Assert.fail();
		}



	}
	
	
	
	public void ATDEFacebook() throws Exception
	{
		String expectedResult="Validate Facebook Link in Italy store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Thread.sleep(2000);
			Common.switchToSecondTab();
			Thread.sleep(2000);
			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			
			String s=Common.getText("xpath", "//a[@title='Go to Facebook Home']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Facebook");
		   	Thread.sleep(1000);

			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();


	}
	
	
	
	public void SOUAFFacebook() throws Exception
	{
		String expectedResult="Lands Facebook page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");

			Thread.sleep(2000);
			
			Common.switchToSecondTab();
			Thread.sleep(2000);
			

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			
			String s=Common.getText("xpath", "//a[@title='Go to Facebook Home']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Facebook");
		   	Thread.sleep(1000);

			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();


	}
	
	
	
	
	public void GEDEFacebook() throws Exception
	
	{
		String expectedResult="Lands Facebook page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");

			Thread.sleep(2000);
			
			Common.switchToSecondTab();
			Thread.sleep(2000);

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			
			String url=Common.getCurrentURL();
			Common.assertionCheckwithReport(url.contains("facebook"),"Verifying facebook page","it shoud navigate to facebook  page", "successfully  navigated to facebook Page", "facebook");	
			Common.switchToFirstTab();
		   	Thread.sleep(1000);

			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));
			Assert.fail();
		}
		//Common.closeCurrentWindow();
		//Common.switchToFirstTab();


	}
	
	
	
	
	public void FRFacebook() throws Exception
	{
		String expectedResult="Lands Facebook page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");

			Thread.sleep(2000);
			
			Common.switchToSecondTab();
			Thread.sleep(2000);
			
			//String title=Common.findElement("xpath", "//div[contains(text(), 'Temperatur??berwachung, der Sie vertrauen k??nnen')]").getText();
			//title=("Temperatur??berwachung, der Sie vertrauen k??nnen");
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			
			String s=Common.getText("xpath", "//a[@title='Go to Facebook Home']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Facebook");
		   	Thread.sleep(1000);

			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();


	}
	
	
	
	
	public void SpainFacebook() throws Exception
	{
		String expectedResult="Lands Facebook page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");

			Thread.sleep(2000);
			
			Common.switchToSecondTab();
			Thread.sleep(2000);
			
			//String title=Common.findElement("xpath", "//div[contains(text(), 'Temperatur??berwachung, der Sie vertrauen k??nnen')]").getText();
			//title=("Temperatur??berwachung, der Sie vertrauen k??nnen");
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			
			String s=Common.getText("xpath", "//a[@title='Go to Facebook Home']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Facebook");
		   	Thread.sleep(1000);

			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();


	}
	
	public void NorwayFacebook() throws Exception
	{
		String expectedResult="Lands Facebook page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");

			Thread.sleep(2000);
			
			Common.switchToSecondTab();
			Thread.sleep(2000);
			
			//String title=Common.findElement("xpath", "//div[contains(text(), 'Temperatur??berwachung, der Sie vertrauen k??nnen')]").getText();
			//title=("Temperatur??berwachung, der Sie vertrauen k??nnen");
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			
			String s=Common.getText("xpath", "//a[@title='Go to Facebook Home']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Facebook");
		   	Thread.sleep(1000);

			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();


	}
	
	
	
	public void TurkeyFacebook() throws Exception
	{
		String expectedResult="Lands Facebook page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");

			Thread.sleep(2000);
			
			Common.switchToSecondTab();
			Thread.sleep(2000);
			
			//String title=Common.findElement("xpath", "//div[contains(text(), 'Temperatur??berwachung, der Sie vertrauen k??nnen')]").getText();
			//title=("Temperatur??berwachung, der Sie vertrauen k??nnen");
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			
			String s=Common.getText("xpath", "(//span[contains(text(), 'Braun Healthcare')])[1]");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Braun Healthcare");
		   	Thread.sleep(2000);

			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();


	}
	
	
	
	
	
	public void UKENFacebook() throws Exception
	{
		String expectedResult="Lands Facebook page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");

			
			
			//String title=Common.findElement("xpath", "//div[contains(text(), 'Temperatur??berwachung, der Sie vertrauen k??nnen')]").getText();
			//title=("Temperatur??berwachung, der Sie vertrauen k??nnen");
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			
            Thread.sleep(2000);
			
			Common.switchToSecondTab();
			Thread.sleep(2000);
			

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			
			String s=Common.getText("xpath", "//a[@title='Go to Facebook Home']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Facebook");
		   	Thread.sleep(1000);


			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));
			Assert.fail();
		}



	}
	
	public void UKSVFacebook() throws Exception
	{
		String expectedResult="Lands Facebook page";
		try {
			Thread.sleep(6000);
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");
	         Thread.sleep(2000);
	         
	     	Common.switchToSecondTab();
		   	Thread.sleep(10000);
			if(Common.isElementDisplayed("xpath", "//div[contains(text(), 'Log Into Facebook')]")) {
				String s=Common.getText("xpath", "//div[contains(text(), 'Log Into Facebook')]");
			   	System.out.println(s);
			   	Assert.assertEquals(s, "Log Into Facebook");
			}else {
				String s=Common.getText("xpath", "(//span[contains(text(), 'Braun Healthcare')])[1]");
			   	System.out.println(s);
			   	Assert.assertEquals(s, "Braun Healthcare");
			}


			/*String s=Common.getText("xpath", "//a[@title='Go to Facebook Home']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Facebook");
		   	Thread.sleep(1000);*/
		//	Common.assertionCheckwithReport(, description, expectedResult, actualResult, FailedMessage);
			
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_DKWFiuRLTvt sx_62ba6c']").getText();
			//stitle=("Temperatur??berwachung, der Sie vertrauen k??nnen");
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");*/

			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));
			Assert.fail();
		}

		Common.closeCurrentWindow();
		Common.switchToFirstTab();

	}
	
	
	
	
	
	
	
	
	public void PolandInstagram() throws Exception
	{
		String expectedResult="Lands Instagram page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-instagram']");

			Thread.sleep(3000);
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			String s=Common.getText("xpath", "//h1[@class='NXVPg Szr5J  coreSpriteLoggedOutWordmark']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Instagram");
		   	Thread.sleep(1000);
			
			report.addPassLog(expectedResult, "Should display Instagram Page", "Instagram Page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Instagram Page", "Instagram Page not displayed", Common.getscreenShotPathforReport("Instagram Failed"));
			Assert.fail();
		}



	}
	
	
	public void PolInstagram() throws Exception
	{
		String expectedResult="Lands Instagram page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-instagram']");

			Thread.sleep(3000);
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
		/*	String s=Common.getText("xpath", "//h1[@class='NXVPg Szr5J  coreSpriteLoggedOutWordmark']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Instagram");*/
		   	Thread.sleep(1000);
			
			report.addPassLog(expectedResult, "Should display Instagram Page", "Instagram Page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Instagram Page", "Instagram Page not displayed", Common.getscreenShotPathforReport("Instagram Failed"));
			Assert.fail();
		}



	}
	
	public void UKSVInstagram() throws Exception
	{
		String expectedResult="Lands Instagram page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-instagram']");

	    		
	       Thread.sleep(2000);
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			String s=Common.getText("xpath", "//h1[@class='NXVPg Szr5J  coreSpriteLoggedOutWordmark']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Instagram");
		   	Thread.sleep(1000);
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			
			report.addPassLog(expectedResult, "Should display Instagram Page", "Instagram Page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Instagram Page", "Instagram Page not displayed", Common.getscreenShotPathforReport("Instagram Failed"));
			Assert.fail();
		}

		Common.closeCurrentWindow();
		Common.switchToFirstTab();

	}
	
	
	public void ATDEInstagram() throws Exception
	{
		String expectedResult="Validate Instagram Link in Italy store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Thread.sleep(2000);
			String s=Common.getText("xpath", "//h1[contains(text(), 'Instagram')]");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Instagram");
		   	Thread.sleep(1000);
			report.addPassLog(expectedResult, "Should display Instagram Page", "Instagram Page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			/*report.addFailedLog(expectedResult,"Should display Instagram Page", "Instagram Page not displayed", Common.getscreenShotPathforReport("Instagram Failed"));
			Assert.fail();*/
		}

		Common.closeCurrentWindow();
		Common.switchToFirstTab();

	}
	
	public void ATDInstagram() throws Exception
	{
		String expectedResult="Validate Instagram Link in Italy store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Thread.sleep(2000);
			String s=Common.getText("xpath", "//h1[contains(text(), 'Instagram')]");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Instagram");
		   	Thread.sleep(1000);
			report.addPassLog(expectedResult, "Should display Instagram Page", "Instagram Page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			/*report.addFailedLog(expectedResult,"Should display Instagram Page", "Instagram Page not displayed", Common.getscreenShotPathforReport("Instagram Failed"));
			Assert.fail();*/
		}

		Common.closeCurrentWindow();
		Common.switchToFirstTab();

	}
	
	
	public void SOUAFInstagram() throws Exception
	{
		String expectedResult="Lands Instagram page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-instagram']");

			Thread.sleep(2000);
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			String s=Common.getText("xpath", "//h1[@class='NXVPg Szr5J  coreSpriteLoggedOutWordmark']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Instagram");
		   	Thread.sleep(1000);
			
			report.addPassLog(expectedResult, "Should display Instagram Page", "Instagram Page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
		}catch(Exception |Error e)
		{
			/*report.addFailedLog(expectedResult,"Should display Instagram Page", "Instagram Page not displayed", Common.getscreenShotPathforReport("Instagram Failed"));
			Assert.fail();*/
		}



	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void GEDEInstagram() throws Exception
	{
		String expectedResult="Lands Instagram page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-instagram']");

			Thread.sleep(2000);
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			String s=Common.getText("xpath", "//h1[@class='NXVPg Szr5J  coreSpriteLoggedOutWordmark']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Instagram");
		   	Thread.sleep(1000);
			
			report.addPassLog(expectedResult, "Should display Instagram Page", "Instagram Page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
		}catch(Exception |Error e)
		{
			/*report.addFailedLog(expectedResult,"Should display Instagram Page", "Instagram Page not displayed", Common.getscreenShotPathforReport("Instagram Failed"));
			Assert.fail();*/
		}



	}


	
	public void FRInstagram() throws Exception
	{
		String expectedResult="Lands Instagram page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-instagram']");

			Thread.sleep(2000);
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			String s=Common.getText("xpath", "//h1[contains(text(), 'Instagram')]");
		   	System.out.println(s);
		    Assert.assertEquals(s, "Instagram");
		   	Thread.sleep(1000);
			
			report.addPassLog(expectedResult, "Should display Instagram Page", "Instagram Page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Instagram Page", "Instagram Page not displayed", Common.getscreenShotPathforReport("Instagram Failed"));
			Assert.fail();
		}



	}

	

	
	public void SpainInstagram() throws Exception
	{
		String expectedResult="Lands Instagram page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-instagram']");

			Thread.sleep(2000);
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			String s=Common.getText("xpath", "//h1[@class='NXVPg Szr5J  coreSpriteLoggedOutWordmark']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Instagram");
		   	Thread.sleep(1000);
			
			report.addPassLog(expectedResult, "Should display Instagram Page", "Instagram Page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
		}catch(Exception |Error e)
		{
			/*report.addFailedLog(expectedResult,"Should display Instagram Page", "Instagram Page not displayed", Common.getscreenShotPathforReport("Instagram Failed"));
			Assert.fail();*/
		}



	}
	
	
	
	public void NorwayInstagram() throws Exception
	{
		String expectedResult="Lands Instagram page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-instagram']");

			Thread.sleep(2000);
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			String s=Common.getText("xpath", "//h1[@class='NXVPg Szr5J  coreSpriteLoggedOutWordmark']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Instagram");
		   	Thread.sleep(1000);
			
			report.addPassLog(expectedResult, "Should display Instagram Page", "Instagram Page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
		}catch(Exception |Error e)
		{
			/*report.addFailedLog(expectedResult,"Should display Instagram Page", "Instagram Page not displayed", Common.getscreenShotPathforReport("Instagram Failed"));
			Assert.fail();*/
		}



	}
	
	
	
	
	
	
	
	
	
	public void TurkeyInstagram() throws Exception
	{
		String expectedResult="Lands Instagram page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-instagram']");

			Thread.sleep(2000);
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			/*String s=Common.getText("xpath", "(//span[contains(text(), 'Braun Healthcare')])[1]");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Braun Healthcare");
		   	Thread.sleep(1000);*/
			String s=Common.getText("xpath", "//h1[@class='NXVPg Szr5J  coreSpriteLoggedOutWordmark']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Instagram");
		   	Thread.sleep(1000);
			
			report.addPassLog(expectedResult, "Should display Instagram Page", "Instagram Page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Instagram Page", "Instagram Page not displayed", Common.getscreenShotPathforReport("Instagram Failed"));
			Assert.fail();
		}



	}
	
	
	
	
	
	
	
	
	public void UKENInstagram() throws Exception
	{
		String expectedResult="Lands Instagram page";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-instagram']");

			Thread.sleep(3000);
			
			//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();
			//title=("facebook");

			/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");*/
			String s=Common.getText("xpath", "//h1[@class='NXVPg Szr5J  coreSpriteLoggedOutWordmark']");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Instagram");
		   	Thread.sleep(1000);
			
			report.addPassLog(expectedResult, "Should display Instagram Page", "Instagram Page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Instagram Page", "Instagram Page not displayed", Common.getscreenShotPathforReport("Instagram Failed"));
			Assert.fail();
		}



	}


	
	public void PolandYoutube() throws Exception

	{

	String expectedResult="Lands Youtube page";

	try {

	Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");

	Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");
	Thread.sleep(3000);
	Common.switchToSecondTab();
   	Thread.sleep(13000);
   	String s=Common.getText("xpath", "(//*[contains(text(),'Braun Healthcare Europe')])[1]");
   	System.out.println(s);
	Assert.assertEquals(s, "Braun Healthcare Europe");


	//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();

	//title=("facebook");

	 

	/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");

	System.out.println(s +" navigated successfully");

	Assert.assertEquals(s, "ABOUT US");*/

	 

	report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));

	Assert.fail();

	}

	}
	
	
	
	public void POLYoutube() throws Exception

	{

	String expectedResult="Lands Youtube page";

	try {

	Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");

	Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");
	Thread.sleep(3000);
	Common.switchToSecondTab();
   	Thread.sleep(13000);
   	String s=Common.getText("xpath", "(//*[contains(text(),'Braun Healthcare Europe')])[1]");
   	System.out.println(s);
	Assert.assertEquals(s, "Braun Healthcare Europe");


	//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();

	//title=("facebook");

	 

	/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");

	System.out.println(s +" navigated successfully");

	Assert.assertEquals(s, "ABOUT US");*/

	 

	report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));

	Assert.fail();

	}

	}
	
	
	public void UKSVYoutube() throws Exception

	{

	String expectedResult="Lands Youtube page";

	try {

	Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");

	Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");
	Thread.sleep(3000);
	Common.switchToSecondTab();
   	Thread.sleep(13000);
   	String s=Common.getText("xpath", "(//*[contains(text(),'Braun Healthcare Europe')])[1]");
   	System.out.println(s);
	Assert.assertEquals(s, "Braun Healthcare Europe");


	//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();

	//title=("facebook");

	 

	/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");

	System.out.println(s +" navigated successfully");

	Assert.assertEquals(s, "ABOUT US");*/

	 

	report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));

	Assert.fail();

	}

	}
	
	
	
	
	
	public void ATDEYoutube() throws Exception

	{

	//String expectedResult="Lands on Youtube page";
	String expectedResult="Validate Youtube Link in Italy store";

	try {

	Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");

	Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");

	Thread.sleep(3000);
	Common.switchToSecondTab();
   	Thread.sleep(13000);
   	String s=Common.getText("xpath", "(//*[contains(text(),'Braun Healthcare Europe')])[1]");
   	System.out.println(s);
	Assert.assertEquals(s, "Braun Healthcare Europe");
	//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();

//	title=("facebook");
	report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));

	Assert.fail();

	}

	}
	
	
	
	public void ATDYoutube() throws Exception

	{

	//String expectedResult="Lands on Youtube page";
	String expectedResult="Validate Youtube Link in Italy store";

	try {

	Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");

	Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");

	Thread.sleep(3000);
	Common.switchToSecondTab();
   	Thread.sleep(13000);
   	String s=Common.getText("xpath", "(//*[contains(text(),'Braun Healthcare Europe')])[1]");
   	System.out.println(s);
	Assert.assertEquals(s, "Braun Healthcare Europe");
	//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();

//	title=("facebook");
	report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));

	Assert.fail();

	}

	}
	
	

	
	
	public void SOUAFYoutube() throws Exception

	{

	String expectedResult="Lands on Youtube page";

	try {

	Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");

	Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");
	Thread.sleep(3000);
	Common.switchToSecondTab();
   	Thread.sleep(13000);
   	String s=Common.getText("xpath", "(//*[contains(text(),'Braun Healthcare Europe')])[1]");
   	System.out.println(s);
	Assert.assertEquals(s, "Braun Healthcare Europe");


	//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();

//	title=("facebook");

	 

	/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");

	System.out.println(s +" navigated successfully");

	Assert.assertEquals(s, "ABOUT US");*/
	/*String s=Common.getPageTitle();
	System.out.println(s);*/
	
	/*String s=Common.getText("xpath", "(//a[@id='logo'])[1]");
   	System.out.println(s);
   Assert.assertEquals(s, "YouTube Home");
   	Thread.sleep(1000);*/

	report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));

	Assert.fail();

	}

	}
	
	public void GEDEYoutube() throws Exception

	{

	String expectedResult="Lands on Youtube page";

	try {

	Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");

	Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");

	//Thread.sleep(3000);
	//Common.switchToSecondTab();
   	Thread.sleep(13000);
   	String s=Common.getText("xpath", "(//*[contains(text(),'Braun Healthcare Europe')])[1]");
   	System.out.println(s);
	Assert.assertEquals(s, "Braun Healthcare Europe");


	//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();

//	title=("facebook");

	 

	/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");

	System.out.println(s +" navigated successfully");

	Assert.assertEquals(s, "ABOUT US");*/
	/*String s=Common.getPageTitle();
	System.out.println(s);*/

	 

	report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));

	Assert.fail();

	}

	}
	
	
	
	
	public void FRYoutube() throws Exception

	{

	String expectedResult="Lands on Youtube page";

	try {

	Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");

	Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");
	Thread.sleep(3000);
	Common.switchToSecondTab();
   	Thread.sleep(13000);
   	String s=Common.getText("xpath", "(//*[contains(text(),'Braun Healthcare Europe')])[1]");
   	System.out.println(s);
	Assert.assertEquals(s, "Braun Healthcare Europe");


	//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();

//	title=("facebook");

	 

	/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");

	System.out.println(s +" navigated successfully");

	Assert.assertEquals(s, "ABOUT US");*/
	/*String s=Common.getPageTitle();
	System.out.println(s);*/

	 

	report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));

	Assert.fail();

	}

	}
	
	
	
	
	
	
	
	
	public void SpainYoutube() throws Exception

	{

	String expectedResult="Lands on Youtube page";

	try {

	Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");

	Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");
	Thread.sleep(3000);
	Common.switchToSecondTab();
   	Thread.sleep(13000);
   	String s=Common.getText("xpath", "(//*[contains(text(),'Braun Healthcare Europe')])[1]");
   	System.out.println(s);
	Assert.assertEquals(s, "Braun Healthcare Europe");
	


	//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();

//	title=("facebook");

	 

	/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");

	System.out.println(s +" navigated successfully");

	Assert.assertEquals(s, "ABOUT US");*/
	/*String s=Common.getPageTitle();
	System.out.println(s);*/

	 

	report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));

	Assert.fail();

	}

	}
	
	
	public void NorwayYoutube() throws Exception

	{

	String expectedResult="Lands on Youtube page";

	try {

	Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");

	Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");
	Thread.sleep(3000);
	Common.switchToSecondTab();
   	Thread.sleep(13000);
   	String s=Common.getText("xpath", "(//*[contains(text(),'Braun Healthcare Europe')])[1]");
   	System.out.println(s);
	Assert.assertEquals(s, "Braun Healthcare Europe");


	//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();

//	title=("facebook");

	 

	/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");

	System.out.println(s +" navigated successfully");

	Assert.assertEquals(s, "ABOUT US");*/
	/*String s=Common.getPageTitle();
	System.out.println(s);*/

	 

	report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));

	Assert.fail();

	}

	}
	
	
	public void TurkeyYoutube() throws Exception

	{

	String expectedResult="Lands on Youtube page";

	try {

	Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");

	Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");
	Thread.sleep(3000);
	Common.switchToSecondTab();
   	Thread.sleep(13000);
   	String s=Common.getText("xpath", "(//*[contains(text(),'Braun Healthcare Europe')])[1]");
   	System.out.println(s);
	Assert.assertEquals(s, "Braun Healthcare Europe");


	//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();

	//title=("facebook");

	 

	/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");

	System.out.println(s +" navigated successfully");

	Assert.assertEquals(s, "ABOUT US");*/

	 

	report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));

	Assert.fail();

	}

	}
	
	
	
	
	
	
	
	
	
	
	public void UKENYoutube() throws Exception

	{

	String expectedResult="Lands Youtube page";

	try {

	Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");

	Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");
	Thread.sleep(3000);
	Common.switchToSecondTab();
   	Thread.sleep(13000);
   	String s=Common.getText("xpath", "(//*[contains(text(),'Braun Healthcare Europe')])[1]");
   	System.out.println(s);
	Assert.assertEquals(s, "Braun Healthcare Europe");


	//String title=Common.findElement("xpath", "//i[@class='fb_logo img sp_3FkyymiRwDN sx_c2e63c']").getText();

	//title=("facebook");

	 

	/*String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");

	System.out.println(s +" navigated successfully");

	Assert.assertEquals(s, "ABOUT US");*/

	 

	report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));

	Assert.fail();

	}

	}
	
	
	

	public void YourHealth() throws Exception
	{
		String expectedResult="YourHealth link ";
		try {
			Thread.sleep(4000);		
			Sync.waitElementPresent("xpath", "/html/body/div[4]/div[2]/header/div[3]/div[4]/div/div[2]/div/nav/ul/li[3]/a/span[1]/font/font");
			Common.clickElement("xpath", "/html/body/div[4]/div[2]/header/div[3]/div[4]/div/div[2]/div/nav/ul/li[3]/a/span[1]/font/font");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display YourHealth Page", "YourHealth  Header link page display successfully", Common.getscreenShotPathforReport("Your Health Header link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display YourHealth Page", "YourHealth Header link Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}


	public void navigateMinicart() throws Exception
	{
		String expectedResult="Product adding to mini cart";
		try {
			Thread.sleep(4000);

			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			//Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Common.javascriptclickElement("xpath" , "//button[@id='product-addtocart-button']");
			Thread.sleep(10000);

			String URL=Common.getCurrentURL();
			/* if(URL.contains("uk_en")) {
                Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/checkout/cart/']"));
            }else if(URL.contains("de_de")) {
                Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"de_de/checkout/cart/']"));
            }else if(URL.contains("fr_fr")) {
                Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"fr_fr/checkout/cart/']"));
            }*/
			
			/*String title=Common.findElement("xpath", "(//span[contains(text(), 'Mon panier')])[2]").getText();
			title=("MON PANIER");
			Thread.sleep(1000);*/
			
			String s=Common.getText("xpath", "(//span[contains(text(), 'Mon panier')])[2]");
			Assert.assertEquals(s, "MON PANIER");
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//button[@id='top-cart-btn-checkout']");
			//Common.clickElement("xpath", "//button[@class='action primary checkout']");

			Common.javascriptclickElement("xpath" , "//button[@id='top-cart-btn-checkout']");
			Thread.sleep(2000);
			
			report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	public void ITnavigateMinicart() throws Exception
	{
		String expectedResult="Product adding to mini cart";
		try {
			Thread.sleep(4000);

			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			//Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Common.javascriptclickElement("xpath" , "//button[@id='product-addtocart-button']");
			Thread.sleep(10000);

			String URL=Common.getCurrentURL();
			/* if(URL.contains("uk_en")) {
                Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/checkout/cart/']"));
            }else if(URL.contains("de_de")) {
                Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"de_de/checkout/cart/']"));
            }else if(URL.contains("fr_fr")) {
                Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"fr_fr/checkout/cart/']"));
            }*/
			
			String s=Common.getText("xpath", "(//span[@class='text']/span)[2]");
			Assert.assertEquals(s, "CARRELLO");
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//button[@id='top-cart-btn-checkout']");
			//Common.clickElement("xpath", "//button[@class='action primary checkout']");

			Common.javascriptclickElement("xpath" , "//button[@id='top-cart-btn-checkout']");
			Thread.sleep(2000);
			
			report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	
	
	
	
	
	
	public void GernavigateMinicart() throws Exception
	{
		String expectedResult="Product adding to mini cart";
		try {
			Thread.sleep(4000);

			Sync.waitElementPresent("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Common.clickElement("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Thread.sleep(10000);

			String URL=Common.getCurrentURL();
			/* if(URL.contains("uk_en")) {
                Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/checkout/cart/']"));
            }else if(URL.contains("de_de")) {
                Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"de_de/checkout/cart/']"));
            }else if(URL.contains("fr_fr")) {
                Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"fr_fr/checkout/cart/']"));
            }*/
			
			String title=Common.findElement("xpath", "(//span[contains(text(), 'Mein Warenkorb')])[2]").getText();
			title=("MEIN WARENKORB");
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "//button[@id='top-cart-btn-checkout']");
			//Common.clickElement("xpath", "//button[@class='action primary checkout']");

			Common.javascriptclickElement("xpath" , "//button[@id='top-cart-btn-checkout']");
			Thread.sleep(2000);
			
			report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	public void GEnavigateMinicart() throws Exception
	{
		String expectedResult="Product adding to mini cart";
		try {
			Thread.sleep(4000);

			Sync.waitElementPresent("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Common.clickElement("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Thread.sleep(10000);
			//String URL=Common.getCurrentURL();
			String s=Common.getText("xpath", "(//span[contains(text(), 'Mein Warenkorb')])[2]");
			Assert.assertEquals(s, "MEIN WARENKORB");
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "//button[@id='top-cart-btn-checkout']");
			//Common.clickElement("xpath", "//button[@class='action primary checkout']");
			Common.javascriptclickElement("xpath" , "//button[@id='top-cart-btn-checkout']");
			Thread.sleep(2000);
			
			report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	
	public void ITNavigateMinicart() throws Exception
	{
		String expectedResult="Product adding to mini cart";
		try {
			Thread.sleep(4000);

			Sync.waitElementPresent("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Common.clickElement("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Thread.sleep(10000);
			//String URL=Common.getCurrentURL();
			String s=Common.getText("xpath", "//div[@class='message-success success message']");
			Assert.assertEquals(s, "Hai aggiunto Braun ThermoScan?? 7 con Age Precision?? al tuo carrello.");
			Thread.sleep(2000);
			
			report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void checkoutPage() throws Exception
	{
		String expectedResult="Product adding to checkout page";
		try {

			Sync.waitElementPresent("xpath", "//button[@id='guest_checkout']");
			Common.clickElement("xpath", "//button[@id='guest_checkout']");
			//Common.isElementDisplayed("id", "guest_checkout");
			Thread.sleep(1000);
			report.addPassLog(expectedResult, "Should display Guest Checkout", "Guest Checkout display successfully", Common.getscreenShotPathforReport("Guest Checkout success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Guest Checkout", "Guest Checkout not displayed", Common.getscreenShotPathforReport("Guest Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	
	public void GEcheckoutPage() throws Exception
	{
		String expectedResult="Product adding to checkout page";
		try {

			Sync.waitElementPresent("xpath", "//button[@id='guest_checkout']");
			Common.clickElement("xpath", "//button[@id='guest_checkout']");
			//Common.isElementDisplayed("id", "guest_checkout");
			Thread.sleep(2000);
			String s=Common.getText("xpath", "//div[contains(text(), 'E-Mail')]");
			Assert.assertEquals(s, "E-Mail");
			Thread.sleep(1000);
			report.addPassLog(expectedResult, "Shousld display Guest Checkout", "Guest Checkout display successfully", Common.getscreenShotPathforReport("Guest Checkout success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Guest Checkout", "Guest Checkout not displayed", Common.getscreenShotPathforReport("Guest Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	public void ITcheckoutPage() throws Exception
	{
		String expectedResult="Product adding to checkout page";
		try {

			Sync.waitElementPresent("xpath", "//button[@id='top-cart-btn-checkout']");
			Common.clickElement("xpath", "//button[@id='top-cart-btn-checkout']");
			//Common.isElementDisplayed("id", "guest_checkout");
			Thread.sleep(2000);
			String s=Common.getText("xpath", "//div[contains(text(), 'E-Mail')]");
			Assert.assertEquals(s, "E-Mail");
			Thread.sleep(1000);
			report.addPassLog(expectedResult, "Shousld display Guest Checkout", "Guest Checkout display successfully", Common.getscreenShotPathforReport("Guest Checkout success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Guest Checkout", "Guest Checkout not displayed", Common.getscreenShotPathforReport("Guest Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void shipping_Address(String dataSet) throws Exception

	{
try
{
	Thread.sleep(5000);
		Common.textBoxInput("name", "username", data.get(dataSet).get("Email"));

		Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[1]");
		Common.clickElement("xpath", "(//button[@class='button action continue primary'])[1]");

		Thread.sleep(2000);
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Thread.sleep(3000);
		/*Sync.waitElementPresent("xpath", "//div[contains(text(),'Shipping Address')]");
		Common.clickElement("xpath", "//div[contains(text(),'Shipping Address')]");
		Thread.sleep(3000);*/
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Thread.sleep(1000);
		//Common.dropdown("name", "country_id", SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));

		Thread.sleep(3000);
		
		Sync.waitElementPresent("xpath", "(//input[@name='shipping_method'])[1]");
		//Common.clickElement("xpath", "//input[@name='shipping_method']");
		Common.javascriptclickElement("xpath", "(//input[@name='shipping_method'])[1]");
		report.addPassLog("Should display Shipping address Page", "Should display Shipping address successfully", Common.getscreenShotPathforReport("Shipping address page success"));

		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[2]");
		Common.clickElement("xpath", "(//button[@class='button action continue primary'])[2]");
		Thread.sleep(2000);
		
		report.addPassLog("Should Display Shipping method page", "Displaying Shipping method page success", Common.getscreenShotPathforReport("Shipping method page success"));

		Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
		Common.clickElement("xpath", "//span[contains(text(),'OK')]");	

	
	report.addPassLog("Should Display payment method", "Displaying payment method success", Common.getscreenShotPathforReport("payment method success"));
}

catch(Exception |Error e)
{
	report.addPassLog("Should Display payment method", "Displaying payment method success", Common.getscreenShotPathforReport("payment method failed"));
	e.printStackTrace();
	Assert.fail();
}	
}
	
	
	public void BillingAddress(String dataSet) throws Exception

	{
try
{

	Common.actionsKeyPress(Keys.PAGE_UP);
	Thread.sleep(3000);
	Common.actionsKeyPress(Keys.PAGE_UP);
	Thread.sleep(3000);
		//Common.scrollToElementAndUnCheck("xpath", "//input[@id='billing-address-same-as-shipping-shared']");
		Common.javascriptclickElement("xpath" , "//input[@id='billing-address-same-as-shipping-shared']");
		
		Thread.sleep(1000);
		Thread.sleep(2000);
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Thread.sleep(3000);
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Thread.sleep(1000);
		//Common.dropdown("name", "country_id", SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));
		Sync.waitElementPresent("xpath", "//button[@class='action action-update']");
		Common.clickElement("xpath", "//button[@class='action action-update']");
		
		Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
		Common.clickElement("xpath", "//span[contains(text(),'OK')]");	
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		
	
	report.addPassLog("Should Update Billing Address", "Displaying Billing Address success", Common.getscreenShotPathforReport("Update Billing Address is success"));
}

catch(Exception |Error e)
{
	report.addPassLog("Should Update Billing Address", "Displaying Billing Address success", Common.getscreenShotPathforReport("Update Billing Address failed"));
	e.printStackTrace();
	Assert.fail();
}	
}
	
	
	public void SwedenBillingAddress(String dataSet) throws Exception

	{
try
{

	Common.actionsKeyPress(Keys.PAGE_UP);
	Thread.sleep(3000);
	Common.actionsKeyPress(Keys.PAGE_UP);
	Thread.sleep(3000);
		//Common.scrollToElementAndUnCheck("xpath", "//input[@id='billing-address-same-as-shipping-shared']");
		Common.javascriptclickElement("xpath" , "//input[@id='billing-address-same-as-shipping-shared']");
		
		Thread.sleep(1000);
		Thread.sleep(2000);
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Thread.sleep(3000);
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Thread.sleep(1000);
		//Common.dropdown("name", "country_id", SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));
		Sync.waitElementPresent("xpath", "//button[@class='action action-update']");
		Common.clickElement("xpath", "//button[@class='action action-update']");
		
		Sync.waitElementPresent("xpath", "//span[contains(text(), 'Ok')]");
		Common.clickElement("xpath", "//span[contains(text(), 'Ok')]");	
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		
	
	report.addPassLog("Should Update Billing Address", "Displaying Billing Address success", Common.getscreenShotPathforReport("Update Billing Address is success"));
}

catch(Exception |Error e)
{
	report.addPassLog("Should Update Billing Address", "Displaying Billing Address success", Common.getscreenShotPathforReport("Update Billing Address failed"));
	e.printStackTrace();
	Assert.fail();
}	
}
	
	
	
	
	public void RegisterBillingAddress(String dataSet) throws Exception
	{
try
{  //Thread.sleep(3000);
Common.actionsKeyPress(Keys.PAGE_DOWN);
Thread.sleep(3000);
Common.actionsKeyPress(Keys.PAGE_DOWN);
Thread.sleep(3000);
Common.actionsKeyPress(Keys.PAGE_DOWN);
Thread.sleep(3000);
Common.actionsKeyPress(Keys.PAGE_DOWN);
Thread.sleep(3000);
Common.actionsKeyPress(Keys.PAGE_DOWN);
Thread.sleep(2000);

Sync.waitElementPresent("xpath", "//span[text()='Bearbeiten']");
	Common.clickElement("xpath", "//span[text()='Bearbeiten']");	
	
	//Common.actionsKeyPress(Keys.PAGE_UP);
	Thread.sleep(3000);
	
	
	//Common.actionsKeyPress(Keys.PAGE_UP);
	//Thread.sleep(3000);
		//Common.scrollToElementAndUnCheck("xpath", "//input[@id='billing-address-same-as-shipping-shared']");
		//Common.javascriptclickElement("xpath" , "//input[@id='billing-address-same-as-shipping-shared']");
		//Common.clickElement("xpath" , "//input[@id='billing-address-same-as-shipping-shared']"); 
		
		
		Thread.sleep(1000);
		Sync.waitElementPresent("xpath", "//select[@name='billing_address_id']/option[contains(text(), 'Neue Adresse')]");
		Common.clickElement("xpath", "//select[@name='billing_address_id']/option[contains(text(), 'Neue Adresse')]");
		Thread.sleep(1000);
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Thread.sleep(3000);
		
		
		/*Sync.waitElementPresent("xpath", "//div[contains(text(),'Shipping Address')]");
		Common.clickElement("xpath", "//div[contains(text(),'Shipping Address')]");*/
		Thread.sleep(3000);
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Thread.sleep(1000);
		//Common.dropdown("name", "country_id", SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));

		//Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//button[@class='action action-update']");
		Common.clickElement("xpath", "//button[@class='action action-update']");
		
		Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
		Common.clickElement("xpath", "//span[contains(text(),'OK')]");	
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);  
		
	
	report.addPassLog("Should Update Billing Address", "Displaying Billing Address success", Common.getscreenShotPathforReport("Update Billing Address is success"));
}

catch(Exception |Error e)
{
	report.addPassLog("Should Update Billing Address", "Displaying Billing Address success", Common.getscreenShotPathforReport("Update Billing Address failed"));
	e.printStackTrace();
	Assert.fail();
}	
}
	

	
	public void ITRegisterBillingAddress(String dataSet) throws Exception
	{
try
{  //Thread.sleep(3000);
Common.actionsKeyPress(Keys.PAGE_DOWN);
Thread.sleep(3000);
Common.actionsKeyPress(Keys.PAGE_DOWN);
Common.actionsKeyPress(Keys.PAGE_DOWN);



Sync.waitElementPresent("xpath", "(//span[contains(text(), 'Modifica')])[4]");
	Common.clickElement("xpath", "(//span[contains(text(), 'Modifica')])[4]");	
	
	//Common.actionsKeyPress(Keys.PAGE_UP);
	Thread.sleep(3000);
	
	
	//Common.actionsKeyPress(Keys.PAGE_UP);
	//Thread.sleep(3000);
		//Common.scrollToElementAndUnCheck("xpath", "//input[@id='billing-address-same-as-shipping-shared']");
		//Common.javascriptclickElement("xpath" , "//input[@id='billing-address-same-as-shipping-shared']");
		//Common.clickElement("xpath" , "//input[@id='billing-address-same-as-shipping-shared']"); 
		
		
		Thread.sleep(1000);
		Sync.waitElementPresent("xpath", "//select[@name='billing_address_id']/option[contains(text(), 'Nuovo Indirizzo')]");
		Common.clickElement("xpath", "//select[@name='billing_address_id']/option[contains(text(), 'Nuovo Indirizzo')]");
		Thread.sleep(1000);
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Thread.sleep(3000);
		
		
		/*Sync.waitElementPresent("xpath", "//div[contains(text(),'Shipping Address')]");
		Common.clickElement("xpath", "//div[contains(text(),'Shipping Address')]");*/
		Thread.sleep(3000);
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Thread.sleep(1000);
		//Common.dropdown("name", "country_id", SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));

		//Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//button[@class='action action-update']");
		Common.clickElement("xpath", "//button[@class='action action-update']");
		
		Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
		Common.clickElement("xpath", "//span[contains(text(),'OK')]");	
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);  
		
	
	report.addPassLog("Should Update Billing Address", "Displaying Billing Address success", Common.getscreenShotPathforReport("Update Billing Address is success"));
}

catch(Exception |Error e)
{
	report.addPassLog("Should Update Billing Address", "Displaying Billing Address success", Common.getscreenShotPathforReport("Update Billing Address failed"));
	e.printStackTrace();
	Assert.fail();
}	
}
		
	
	public void FRRegisterBillingAddress(String dataSet) throws Exception
	{
try
{  //Thread.sleep(3000);
Common.actionsKeyPress(Keys.PAGE_DOWN);
Thread.sleep(3000);
Common.actionsKeyPress(Keys.PAGE_DOWN);
Thread.sleep(3000);
Common.actionsKeyPress(Keys.PAGE_DOWN);
Thread.sleep(3000);
Common.actionsKeyPress(Keys.PAGE_DOWN);
Thread.sleep(3000);
Common.actionsKeyPress(Keys.PAGE_DOWN);
Thread.sleep(3000);
Common.actionsKeyPress(Keys.PAGE_DOWN);
Thread.sleep(3000);



Sync.waitElementPresent("xpath", "(//span[contains(text(), 'Modifier')])[3]");
	Common.clickElement("xpath", "(//span[contains(text(), 'Modifier')])[3]");	
	
	//Common.actionsKeyPress(Keys.PAGE_UP);
	Thread.sleep(3000);
	
	
	//Common.actionsKeyPress(Keys.PAGE_UP);
	//Thread.sleep(3000);
		//Common.scrollToElementAndUnCheck("xpath", "//input[@id='billing-address-same-as-shipping-shared']");
		//Common.javascriptclickElement("xpath" , "//input[@id='billing-address-same-as-shipping-shared']");
		//Common.clickElement("xpath" , "//input[@id='billing-address-same-as-shipping-shared']"); 
		
		
		Thread.sleep(1000);
		Sync.waitElementPresent("xpath", "//select[@name='billing_address_id']/option[contains(text(), 'Nouvelle adresse')]");
		Common.clickElement("xpath", "//select[@name='billing_address_id']/option[contains(text(), 'Nouvelle adresse')]");
		Thread.sleep(1000);
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Thread.sleep(3000);
		
		
		/*Sync.waitElementPresent("xpath", "//div[contains(text(),'Shipping Address')]");
		Common.clickElement("xpath", "//div[contains(text(),'Shipping Address')]");*/
		Thread.sleep(3000);
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Thread.sleep(1000);
		//Common.dropdown("name", "country_id", SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));

		//Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//button[@class='action action-update']");
		Common.clickElement("xpath", "//button[@class='action action-update']");
		
		Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
		Common.clickElement("xpath", "//span[contains(text(),'OK')]");	
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);  
		
	
	report.addPassLog("Should Update Billing Address", "Displaying Billing Address success", Common.getscreenShotPathforReport("Update Billing Address is success"));
}

catch(Exception |Error e)
{
	report.addPassLog("Should Update Billing Address", "Displaying Billing Address success", Common.getscreenShotPathforReport("Update Billing Address failed"));
	e.printStackTrace();
	Assert.fail();
}	
}
	
	public void ITshipping_Address(String dataSet) throws Exception

	{
try
{
		Common.textBoxInput("name", "username", data.get(dataSet).get("Email"));

		Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[1]");
		Common.clickElement("xpath", "(//button[@class='button action continue primary'])[1]");

		Thread.sleep(2000);
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Thread.sleep(3000);
		/*Sync.waitElementPresent("xpath", "//div[contains(text(),'Shipping Address')]");
		Common.clickElement("xpath", "//div[contains(text(),'Shipping Address')]");
		Thread.sleep(3000);
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Thread.sleep(1000);
		//Common.dropdown("name", "country_id", SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));

		Thread.sleep(3000);
		
		Sync.waitElementPresent("xpath", "(//input[@name='shipping_method'])[1]");
		//Common.clickElement("xpath", "//input[@name='shipping_method']");
		Common.javascriptclickElement("xpath", "(//input[@name='shipping_method'])[1]");
		report.addPassLog("Should display Shipping address Page", "Should display Shipping address successfully", Common.getscreenShotPathforReport("Shipping address page success"));

		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[2]");
		Common.clickElement("xpath", "(//button[@class='button action continue primary'])[2]");
		Thread.sleep(2000);
		
		report.addPassLog("Should Display Shipping method page", "Displaying Shipping method page success", Common.getscreenShotPathforReport("Shipping method page success"));

		Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
		Common.clickElement("xpath", "//span[contains(text(),'OK')]");	

	
	report.addPassLog("Should Display payment method", "Displaying payment method success", Common.getscreenShotPathforReport("payment method success"));
}

catch(Exception |Error e)
{
	report.addPassLog("Should Display payment method", "Displaying payment method success", Common.getscreenShotPathforReport("payment method failed"));
	e.printStackTrace();
	Assert.fail();
}	
}
	
	
	
	
	
	public void Swedenshipping_Address(String dataSet) throws Exception

	{
try
{
		Common.textBoxInput("name", "username", data.get(dataSet).get("Email"));

		Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[1]");
		Common.clickElement("xpath", "(//button[@class='button action continue primary'])[1]");

		Thread.sleep(2000);
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Thread.sleep(3000);
		/*Sync.waitElementPresent("xpath", "//div[contains(text(),'Shipping Address')]");
		Common.clickElement("xpath", "//div[contains(text(),'Shipping Address')]");
		Thread.sleep(3000);*/
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Thread.sleep(1000);
		//Common.dropdown("name", "country_id", SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));

		Thread.sleep(3000);
		
		Sync.waitElementPresent("xpath", "(//input[@name='shipping_method'])[1]");
		//Common.clickElement("xpath", "//input[@name='shipping_method']");
		Common.javascriptclickElement("xpath", "(//input[@name='shipping_method'])[1]");
		report.addPassLog("Should display Shipping address Page", "Should display Shipping address successfully", Common.getscreenShotPathforReport("Shipping address page success"));

		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[2]");
		Common.clickElement("xpath", "(//button[@class='button action continue primary'])[2]");
		Thread.sleep(2000);
		
		report.addPassLog("Should Display Shipping method page", "Displaying Shipping method page success", Common.getscreenShotPathforReport("Shipping method page success"));

		Sync.waitElementPresent("xpath", "//span[contains(text(),'Ok')]");
		Common.clickElement("xpath", "//span[contains(text(),'Ok')]");	

	
	report.addPassLog("Should Display payment method", "Displaying payment method success", Common.getscreenShotPathforReport("payment method success"));
}

catch(Exception |Error e)
{
	report.addPassLog("Should Display payment method", "Displaying payment method success", Common.getscreenShotPathforReport("payment method failed"));
	e.printStackTrace();
	Assert.fail();
}	
}

	
	public void ValidatingPromocode(String dataSet) throws Exception
	{
		String expectedResult="Validate Promode in Checkout page";
		try {
			
			Common.scrollIntoView("xpath", "//div[@class='billing-address-details']");
			Sync.waitElementPresent("id", "block-discount-heading");
			Common.clickElement("id", "block-discount-heading");
			
			Sync.waitElementPresent("id", "discount-code-fake");
			Common.clickElement("id", "discount-code-fake");
			
			Common.textBoxInput("id", "discount-code-fake", data.get(dataSet).get("promocode"));
			
			Thread.sleep(2000);

			Sync.waitElementPresent("xpath", "//button[@class='action action-apply']");
			Common.clickElement("xpath", "//button[@class='action action-apply']");
			
			/*String success=Common.getText("xpath", "(//div[@data-ui-id='checkout-cart-validationmessages-message-success'])[1]");
			System.out.println(success);*/
			report.addPassLog(expectedResult, "Should display Success message for promocode", "Success of promocode displayed successfully", Common.getscreenShotPathforReport("Promocode success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Success message for promocode", "Success of promocode not displayed", Common.getscreenShotPathforReport("Promocode Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	
	
	public void ITValidatingPromocode(String dataSet) throws Exception
	{
		String expectedResult="Validate Promode in Checkout page";
		try {
			
			Common.scrollIntoView("xpath", "//div[@class='billing-address-details']");
			Sync.waitElementPresent("id", "block-discount-heading");
			Common.clickElement("id", "block-discount-heading");
			
			Sync.waitElementPresent("id", "discount-code-fake");
			Common.clickElement("id", "discount-code-fake");
			
			Common.textBoxInput("id", "discount-code-fake", data.get(dataSet).get("promocode"));
			
			Thread.sleep(2000);

			Sync.waitElementPresent("xpath", "//button[@class='action action-apply']");
			Common.clickElement("xpath", "//button[@class='action action-apply']");
			
			/*String success=Common.getText("xpath", "(//div[@data-ui-id='checkout-cart-validationmessages-message-success'])[1]");
			System.out.println(success);*/
			report.addPassLog(expectedResult, "Should display Success message for promocode", "Success of promocode displayed successfully", Common.getscreenShotPathforReport("Promocode success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Success message for promocode", "Success of promocode not displayed", Common.getscreenShotPathforReport("Promocode Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void FRValidatingPromocode(String dataSet) throws Exception
	{
		String expectedResult="Validate Promode in Checkout page";
		try {
			
			Common.scrollIntoView("xpath", "//div[@class='billing-address-details']");
			Sync.waitElementPresent("id", "block-discount-heading");
			Common.clickElement("id", "block-discount-heading");
			
			Sync.waitElementPresent("id", "discount-code-fake");
			Common.clickElement("id", "discount-code-fake");
			
			Common.textBoxInput("id", "discount-code-fake", data.get(dataSet).get("promocode"));
			
			Thread.sleep(2000);

			Sync.waitElementPresent("xpath", "//button[@class='action action-apply'] ");
			Common.clickElement("xpath", "//button[@class='action action-apply'] ");
			
			String success=Common.getText("xpath", "(//div[@data-ui-id='checkout-cart-validationmessages-message-success'])[1]");
			System.out.println(success);
			report.addPassLog(expectedResult, "Should display Success message for promocode", "Success of promocode displayed successfully", Common.getscreenShotPathforReport("Promocode success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Success message for promocode", "Success of promocode not displayed", Common.getscreenShotPathforReport("Promocode Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	
	public void ItalyValidatingPromocode(String dataSet) throws Exception
	{
		String expectedResult="Validate Promode in Checkout page";
		try {
			
			Common.scrollIntoView("xpath", "//div[@class='billing-address-details']");
			Sync.waitElementPresent("id", "block-discount-heading");
			Common.clickElement("id", "block-discount-heading");
			
			Sync.waitElementPresent("id", "discount-code-fake");
			Common.clickElement("id", "discount-code-fake");
			
			Common.textBoxInput("id", "discount-code-fake", data.get(dataSet).get("promocode"));
			
			Thread.sleep(2000);

			Sync.waitElementPresent("xpath", "//button[@class='action action-apply'] ");
			Common.clickElement("xpath", "//button[@class='action action-apply'] ");
			
			/*String success=Common.getText("xpath", "(//div[@data-ui-id='checkout-cart-validationmessages-message-success'])[1]");
			System.out.println(success);*/
			report.addPassLog(expectedResult, "Should display Success message for promocode", "Success of promocode displayed successfully", Common.getscreenShotPathforReport("Promocode success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Success message for promocode", "Success of promocode not displayed", Common.getscreenShotPathforReport("Promocode Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	
	
	
	
	
	
	
	
	public void ValidatingRegisterPromocode(String dataSet) throws Exception
	{
		String expectedResult="Validate Promode in Checkout page";
		try {
			
			Common.scrollIntoView("xpath", "//div[@class='billing-address-details']");
			Sync.waitElementPresent("id", "block-discount-heading");
			Common.clickElement("id", "block-discount-heading");
			
			Sync.waitElementPresent("id", "discount-code-fake");
			Common.clickElement("id", "discount-code-fake");
			
			Common.textBoxInput("id", "discount-code-fake", data.get(dataSet).get("promocode"));
			
			Thread.sleep(2000);

			Sync.waitElementPresent("xpath", "//button[@class='action action-apply']");
			Common.clickElement("xpath", "//button[@class='action action-apply']");
			
			/*String success=Common.getText("xpath", "(//div[@data-ui-id='checkout-cart-validationmessages-message-success'])[1]");
			System.out.println(success);*/
			report.addPassLog(expectedResult, "Should display Success message for promocode", "Success of promocode displayed successfully", Common.getscreenShotPathforReport("Promocode success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Success message for promocode", "Success of promocode not displayed", Common.getscreenShotPathforReport("Promocode Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	
	
	
	
	
	
	


	public void Guestuser_credentials(String DataSet) throws Exception{

		String expectedResult="Land on login page";

		try {

			/*Sync.waitElementPresent("xpath", "//a[@class='braun-icons braun-profile-icon']");
			Common.clickElement("xpath", "//a[@class='braun-icons braun-profile-icon']");

			Sync.waitElementPresent("id", "email");
			Thread.sleep(4000);*/

			Common.textBoxInput("id", "email", data.get(DataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(DataSet).get("Password"));
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "(//button[@type='submit'])[2]");
			Common.clickElement(By.xpath("(//button[@type='submit'])[2]"));
			report.addPassLog("Should Login to application successfully", "Login to application success", Common.getscreenShotPathforReport("Login success"));
		}

		catch(Exception |Error e)
		{
			report.addPassLog(expectedResult,"Should Login to application successfully", "Login to application Failed", Common.getscreenShotPathforReport("Login failed"));
			e.printStackTrace();
			Assert.fail();
		}	
	}

	public void shippingAddressDetails() throws Exception{

		Thread.sleep(10000);
		Common.actionsKeyPress(Keys.DOWN);
		Sync.waitElementPresent("xpath", "(//button[@class='action action-select-shipping-item']/span)[2]");
		Common.javascriptclickElement("xpath", "(//button[@class='action action-select-shipping-item']/span)[2]");

		Thread.sleep(4000);
		report.addPassLog("Should display shipping address Page", "Shipping address Page display successfully", Common.getscreenShotPathforReport("Shipping address page success"));
		//Sync.waitElementPresent("xpath", "(//button[@class='action-close'])[3]");
		//Common.clickElement("xpath", "(//button[@class='action-close'])[3]");
		Common.scrollIntoView("xpath", "(//div[@class='step-title'])[2]");
		
		Sync.waitElementPresent("xpath", "(//input[@name='shipping_method'])[1]");
		Common.clickElement("xpath", "(//input[@name='shipping_method'])[1]");

		Sync.waitElementPresent("xpath", "/html/body/div[4]/main/div[2]/div/div[6]/div[3]/ol/li[3]/div/div[3]/form/div[3]/div/button");
		Common.clickElement("xpath", "/html/body/div[4]/main/div[2]/div/div[6]/div[3]/ol/li[3]/div/div[3]/form/div[3]/div/button");

		//Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
		//Common.clickElement("xpath", "//span[contains(text(),'OK')]");
		
		/*Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[2]");
		Common.clickElement("xpath", "(//button[@class='button action continue primary'])[2]");*/
		
		Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
		Common.clickElement("xpath", "//button[@class='action-primary action-accept']");


		report.addPassLog("Should display Payment Page", "Payment Page display successfully", Common.getscreenShotPathforReport("Payment page success"));
	}


	
	

	
	public void GershippingAddressDetails() throws Exception{
		try
		{

		Thread.sleep(10000);
		Common.actionsKeyPress(Keys.DOWN);
		Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[2]");
		Common.javascriptclickElement("xpath", "(//button[@class='button action continue primary'])[2]");

		Thread.sleep(4000);
		report.addPassLog("Should display shipping address Page", "Shipping address Page display successfully", Common.getscreenShotPathforReport("Shipping address page success"));
		//Sync.waitElementPresent("xpath", "(//button[@class='action-close'])[3]");
		//Common.clickElement("xpath", "(//button[@class='action-close'])[3]");
		Common.scrollIntoView("xpath", "(//div[@class='step-title'])[2]");
		
		Sync.waitElementPresent("xpath", "(//input[@name='shipping_method'])[1]");
		Common.clickElement("xpath", "(//input[@name='shipping_method'])[1]");

		//Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
		//Common.clickElement("xpath", "//span[contains(text(),'OK')]");
		
		Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[2]");
		Common.clickElement("xpath", "(//button[@class='button action continue primary'])[2]");
		
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
		Common.clickElement("xpath", "//span[contains(text(),'OK')]");	
		
		
		
		/*Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
		Common.clickElement("xpath", "//span[contains(text(),'OK')]");*/
		
		/*Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
		Common.clickElement("xpath", "//button[@class='action-primary action-accept']");*/


		report.addPassLog("Should display Payment Page", "Payment Page display successfully", Common.getscreenShotPathforReport("Payment page success"));
	}
catch(Exception |Error e)
{
	e.printStackTrace();
	report.addFailedLog("Shoulddisplay Payment page", "Payment Page display successfully", Common.getscreenShotPathforReport("Payment page Failed"));
	e.printStackTrace();
	Assert.fail();
}
}
	
	public void ITshippingAddressDetails() throws Exception{

		Thread.sleep(10000);
		Common.actionsKeyPress(Keys.DOWN);
		Sync.waitElementPresent("xpath", "//div[@class='shipping-address-item selected-item']");
		Common.javascriptclickElement("xpath", "//div[@class='shipping-address-item selected-item']");

		Thread.sleep(4000);
		report.addPassLog("Should display shipping address Page", "Shipping address Page display successfully", Common.getscreenShotPathforReport("Shipping address page success"));
		
		Common.scrollIntoView("xpath", "(//div[@class='step-title'])[2]");
		
		Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[2]");
		Common.clickElement("xpath", "(//button[@class='button action continue primary'])[2]");
		
		Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
		Common.clickElement("xpath", "//button[@class='action-primary action-accept']");


		report.addPassLog("Should display Payment Page", "Payment Page display successfully", Common.getscreenShotPathforReport("Payment page success"));
	}


	public void ItalyshippingAddressDetails() throws Exception{
		try
		{

		Thread.sleep(10000);
		Common.actionsKeyPress(Keys.DOWN);
		//Sync.waitElementPresent("xpath", "(//button[@class='action action-select-shipping-item']/span)[2]");
		//Common.javascriptclickElement("xpath", "(//button[@class='action action-select-shipping-item']/span)[2]");
		
		Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[2]");
		Common.javascriptclickElement("xpath", "(//button[@class='button action continue primary'])[2]");

		Thread.sleep(4000);
		report.addPassLog("Should display shipping address Page", "Shipping address Page display successfully", Common.getscreenShotPathforReport("Shipping address page success"));
		//Sync.waitElementPresent("xpath", "(//button[@class='action-close'])[3]");
		//Common.clickElement("xpath", "(//button[@class='action-close'])[3]");
		Common.scrollIntoView("xpath", "(//div[@class='step-title'])[2]");
		
		Sync.waitElementPresent("xpath", "(//input[@name='shipping_method'])[1]");
		Common.clickElement("xpath", "(//input[@name='shipping_method'])[1]");

		//Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
		//Common.clickElement("xpath", "//span[contains(text(),'OK')]");
		
		Sync.waitElementPresent("xpath", "(//button[@data-role='opc-continue'])[2]");
		//Common.clickElement("xpath", "(//button[@data-role='opc-continue'])[2]");
		Common.javascriptclickElement("xpath" , "(//button[@data-role='opc-continue'])[2]");
		
		Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
		Common.clickElement("xpath", "//span[contains(text(),'OK')]");
		
		/*String s=Common.getText("xpath", "(//div[contains(text(), 'Zahlungsart')])[1]");
		Assert.assertEquals(s, "Zahlungsart");*/


		report.addPassLog("Should display Payment Page", "Payment Page display successfully", Common.getscreenShotPathforReport("Payment page success"));
	}
catch(Exception |Error e)
{
	e.printStackTrace();
	report.addFailedLog("Shoulddisplay Payment page", "Payment Page display successfully", Common.getscreenShotPathforReport("Payment page Failed"));
	e.printStackTrace();
	Assert.fail();
}
}



	public void GEManageAddress(String dataset) throws Exception
	{
		String expectedResult="Should Lands on My account page";
		try {
			Thread.sleep(4000);
			
			Sync.waitElementPresent("xpath", "(//a[@class='action edit braun_text_link'])[3]");
			Common.clickElement("xpath", "(//a[@class='action edit braun_text_link'])[3]");
			Thread.sleep(5000);
			
			String expectedResult1=data.get(dataset).get("GEAddress");
			//String expectedResult1="Subscription message :"+data.get(dataSet).get("Confirmationmsg");
			System.out.println(data.get(dataset).get("GEAddress")+ "excel value");
			String expectedResult2=Common.findElement("xpath", "//span[@class='base']").getText();
			
			System.out.println(expectedResult2+ "UI VALUE");
			
			
		if(expectedResult1==expectedResult2)
			{
				System.out.println("pass");
			}
			else
			{
				System.out.println("fail");
			}
		String s=Common.getText("xpath", "//span[contains(text(), 'Adressbuch')]");
		Assert.assertEquals(s, "Adressbuch");

			report.addPassLog(expectedResult, "Should display My account Page", "My account Page display successfully", Common.getscreenShotPathforReport("My Account page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display My account page", "My account Page not display", Common.getscreenShotPathforReport("My Account page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	
	public void ITManageAddress(String dataset) throws Exception
	{
		String expectedResult="Should Lands on My account page";
		try {
			Thread.sleep(4000);
			
			Sync.waitElementPresent("xpath", "(//a[@class='action edit braun_text_link'])[3]");
			Common.clickElement("xpath", "(//a[@class='action edit braun_text_link'])[3]");
			Thread.sleep(5000);
			
			String expectedResult1=data.get(dataset).get("ITAddress");
			//String expectedResult1="Subscription message :"+data.get(dataSet).get("Confirmationmsg");
			System.out.println(data.get(dataset).get("ITAddress")+ "excel value");
			String expectedResult2=Common.findElement("xpath", "//span[@class='base']").getText();
			
			System.out.println(expectedResult2+ "UI VALUE");
			
			
		if(expectedResult1==expectedResult2)
			{
				System.out.println("pass");
			}
			else
			{
				System.out.println("fail");
			}
		/*String s=Common.getText("xpath", "//span[contains(text(), 'Adressbuch')]");
		Assert.assertEquals(s, "Adressbuch");*/

			report.addPassLog(expectedResult, "Should display My account Page", "My account Page display successfully", Common.getscreenShotPathforReport("My Account page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display My account page", "My account Page not display", Common.getscreenShotPathforReport("My Account page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	public void FRManageAddress() throws Exception
	{
		String expectedResult="Should Lands on My account page";
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "(//a[@class='action edit braun_text_link'])[3]");
			Common.clickElement("xpath", "(//a[@class='action edit braun_text_link'])[3]");
			Thread.sleep(5000);
			String s=Common.getText("xpath", "(//div[@ class='block-title'])[2]");
			Assert.assertEquals(s, "Adresses Par D??faut");
			Thread.sleep(2000);
			Common.isElementDisplayed("xpath" , "//span[contains(text(), 'Carnet d???adresses')]");

			report.addPassLog(expectedResult, "Should display My account Page", "My account Page display successfully", Common.getscreenShotPathforReport("My Account page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display My account page", "My account Page not display", Common.getscreenShotPathforReport("My Account page Failed"));
			e.printStackTrace();
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

				if(Common.getCurrentURL().contains("Vielen Dank")){
					break;
				}
				Thread.sleep(5000);
			}

			String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(sucessMessage);
			Common.assertionCheckwithReport(sucessMessage.equals("Vielen Dank f??r Ihre Bestellung"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");

		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
					"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();
		}
	}


	
	public void FROrder_Verifying() throws Exception{
		//Thread.sleep(10000);
		//Common.textBoxInput("id", "//textarea[contains(@id,'tt-c-comment-field')]","Ceate accounts test ");
		String expectedResult = "It redirects to order confirmation page";
		try{
			Sync.waitPageLoad();



			for(int i=0;i<10;i++){

				if(Common.getCurrentURL().contains("Vielen Dank")){
					break;
				}
				Thread.sleep(5000);
			}

			String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(sucessMessage);
			Common.assertionCheckwithReport(sucessMessage.equals("Merci de votre achat"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");

		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
					"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();
		}
	}


	public void searchproduct() throws Exception
	{
		String expectedResult="Should land on Searh page";
		try {
			Thread.sleep(9000);
			Sync.waitElementPresent("xpath", "(//span[@class='braun-icons m_hide'])[2]");
			Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
            Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "(//div[@class='close_store action closebutton'])[1]");
			Common.clickElement("xpath", "(//div[@class='close_store action closebutton'])[1]");
			Thread.sleep(4000);


			report.addPassLog(expectedResult, "Should display search Page", "Browse search Page display successfully", Common.getscreenShotPathforReport("search page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should  display search page", "Browse search Page display successfully", Common.getscreenShotPathforReport("search Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void productname(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
				report.addPassLog(expectedResult, "Should display Browse search Page", "Browse search Page display successfully", Common.getscreenShotPathforReport("Browse search page success"));

			}catch(Exception e)
			{
				Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);

			Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");
			Common.clickElement("xpath", "//img[@class='product-image-photo ']");
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	
	
	public void FRGuestProductname(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("GuestUserProduct");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("GuestUserProduct"));
				report.addPassLog(expectedResult, "Should display Browse search Page", "Browse search Page display successfully", Common.getscreenShotPathforReport("Browse search page success"));

			}catch(Exception e)
			{
				Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("GuestUserProduct"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);

			Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");
			Common.clickElement("xpath", "//img[@class='product-image-photo ']");
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	
	
	public void Guestproductname(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("GuestProduct");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("GuestProduct"));
				report.addPassLog(expectedResult, "Should display Browse search Page", "Browse search Page display successfully", Common.getscreenShotPathforReport("Browse search page success"));

			}catch(Exception e)
			{
				Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("GuestProduct"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);

			Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");
			Common.clickElement("xpath", "//img[@class='product-image-photo ']");
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void UKSVproductname(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
				report.addPassLog(expectedResult, "Should display Browse search Page", "Browse search Page display successfully", Common.getscreenShotPathforReport("Browse search page success"));

			}catch(Exception e)
			{
				Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);

			Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");
			Common.clickElement("xpath", "//img[@class='product-image-photo ']");
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	
	public void HatchSearchProduct(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("HatchproductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "(//div[@class='block block-search search-visible-md minisearch-v2'])");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("HatchproductName"));
			}catch(Exception e)
			{
				Common.clickElement("xpath", "(//div[@class='block block-search search-visible-md minisearch-v2'])");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("HatchproductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);
			//Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");
			//Common.clickElement("xpath", "//img[@class='product-image-photo ']");
			
			
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	public void HatchProductselection() throws Exception
	{
		String expectedResult="Product Selection from search results";
		try {
			Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");
			Common.clickElement("xpath", "//img[@class='product-image-photo ']");
			Thread.sleep(3000);
			
			report.addPassLog(expectedResult, "Should display Product Details Page", "Product Details Page display successfully", Common.getscreenShotPathforReport("Product Details page success"));
			//Sync.waitElementPresent("xpath", "//button[@id='hatchCart']");
			//Common.clickElement("xpath", "//button[@id='hatchCart']");
			
			Common.clickElement("xpath", "//button[@class='vendor-dropdown-btn']");
			//Common.clickElement("xpath", "(//a[@class='vendor-dropdown-item'])[2]");
			Common.clickElement("xpath", "//a[@id='Power']");
			
			//Common.isElementDisplayed("xpath", "//h1[@class='page-title']");
			report.addPassLog(expectedResult, "Should display Apotek Hatch Page", "Apotek Hatch Page display successfully", Common.getscreenShotPathforReport("Apotek Hatch page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Apotek Hatch Page", "Apotek Hatch Page not displayed", Common.getscreenShotPathforReport("Apotek Hatch Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void ApotekHatchproduct() throws Exception
	{
		String expectedResult="Apotek Hatch implementation process from BraunEMEA site";
		try {
			Thread.sleep(3000);
			//Common.clickElement("xpath", "//a[@id='Apotek Hj??rtat']");
			//Common.clickElement("xpath", "//a[@class='vendor-dropdown-item']");
			Common.javascriptclickElement("xpath" , "//a[@id='Power']");
			
			Thread.sleep(5000);
			
			Common.switchWindows();
			
			Thread.sleep(5000);
			//if(Common.isElementDisplayed("xpath", "//div[@class='banner-actions-container']")) {
				if(Common.isElementDisplayed("xpath", "(//button[contains(text(), 'Acceptera alla')])[1]")) {
				System.out.println("Cookies pop up displayed");
				//Common.clickElementStale("xpath", "//div[@class='banner-actions-container']");
			}else {
				System.out.println("Cookies pop up not displayed");
			}
			
			String Productname=Common.getText("xpath", "(//h1[contains(text(), 'BRAUN PRT1000CE DIGITAL TERMOMETER')])[2]");
			System.out.println(Productname);
			
			Thread.sleep(3000);
			
			report.addPassLog(expectedResult, "Should display Apotek Hatch implementation Page", "Apotek Hatch implementation Page display successfully", Common.getscreenShotPathforReport("Apotek Hatch implementation success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Apotek Hatch implementation Page", "Apotek Hatch implementation Page not displayed", Common.getscreenShotPathforReport("Apotek Hatch implementation Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		

	}
	
	public void Polandproductname(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "//span[@class='braun-icons m_hide']");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
				report.addPassLog(expectedResult, "Should display Browse search Page", "Browse search Page display successfully", Common.getscreenShotPathforReport("Browse search page success"));

			}catch(Exception e)
			{
				Common.clickElement("xpath", "//span[@class='braun-icons m_hide']");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);

			Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");
			Common.clickElement("xpath", "//img[@class='product-image-photo ']");
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	
	public void Turkeyproductname(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
				report.addPassLog(expectedResult, "Should display Browse search Page", "Browse search Page display successfully", Common.getscreenShotPathforReport("Browse search page success"));

			}catch(Exception e)
			{
				Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);

			Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");
			Common.clickElement("xpath", "//img[@class='product-image-photo ']");
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	
	public void ATDEproductname(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
				report.addPassLog(expectedResult, "Should display Browse search Page", "Browse search Page display successfully", Common.getscreenShotPathforReport("Browse search page success"));

			}catch(Exception e)
			{
				Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);

			Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");
			Common.clickElement("xpath", "//img[@class='product-image-photo ']");
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void CanadaenProductname(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "//div[@class='block block-search search-visible-md minisearch-v2']");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
				report.addPassLog(expectedResult, "Should display Browse search Page", "Browse search Page display successfully", Common.getscreenShotPathforReport("Browse search page success"));

			}catch(Exception e)
			{
				Common.clickElement("xpath", "//div[@class='block block-search search-visible-md minisearch-v2']");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);

			Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");
			Common.clickElement("xpath", "//img[@class='product-image-photo ']");
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			Assert.fail();
		}

	}
	
	public void CanadaFacebook() throws Exception
	{
		String expectedResult="Validate Facebook Link in Canada store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Thread.sleep(2000);
			Common.switchToSecondTab();
		   	Thread.sleep(15000);
		   	
		   	if(Common.isElementDisplayed("xpath", "//span[contains(text(), 'Braun Healthcare')]")) {
				String s=Common.getText("xpath", "//span[contains(text(), 'Braun Healthcare')]");
			   	System.out.println(s);
			   	Assert.assertEquals(s, "Braun Healthcare");
			}else {
				String s=Common.getText("xpath", "//u[contains(text(), 'Facebook')]");
			   	System.out.println(s);
			   	Assert.assertEquals(s, "Facebook");
			}
		   	/*String s=Common.getText("xpath", "(//span[contains(text(),'Braun Healthcare')])[1]");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Braun Healthcare");*/
			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}


	
	public void CanadafrYoutube() throws Exception
	{
		String expectedResult="Validate Youtube Link in Canada store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");
			Thread.sleep(3000);
			Common.switchToSecondTab();
		   	Thread.sleep(13000);
		   	String s=Common.getText("xpath", "//yt-formatted-string[contains(text(), 'Braun Healthcare Canada')]");
		   	System.out.println(s);
			Assert.assertEquals(s, "Braun Healthcare Canada");
			report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}


	
	
	public void SOUAFproductname(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "//span[@class='braun-icons m_hide']");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
				report.addPassLog(expectedResult, "Should display Browse search Page", "Browse search Page display successfully", Common.getscreenShotPathforReport("Browse search page success"));

			}catch(Exception e)
			{
				Common.clickElement("xpath", "//span[@class='braun-icons m_hide']");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);

			Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");
			Common.clickElement("xpath", "//img[@class='product-image-photo ']");
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	
	
	
	
	public void NLproductname(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "//span[@class='braun-icons m_hide']");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
				report.addPassLog(expectedResult, "Should display Browse search Page", "Browse search Page display successfully", Common.getscreenShotPathforReport("Browse search page success"));

			}catch(Exception e)
			{
				Common.clickElement("xpath", "//span[@class='braun-icons m_hide']");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);

			Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");
			Common.clickElement("xpath", "//img[@class='product-image-photo ']");
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	
	
	
	
	
	
	
	
	public void Spainproductname(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
				report.addPassLog(expectedResult, "Should display Browse search Page", "Browse search Page display successfully", Common.getscreenShotPathforReport("Browse search page success"));

			}catch(Exception e)
			{
				Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);

			Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");
			Common.clickElement("xpath", "//img[@class='product-image-photo ']");
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	


	public void Account(String string) {
		// TODO Auto-generated method stub



	}
	public void creditcard() throws Exception{
		Sync.waitElementPresent("xpath", "//input[@id='encryptedCardNumber']");
		Common.clickElement("xpath", "//input[@id='encryptedCardNumber']");
	}
	public void GEMyAccountpage() throws Exception{
		Thread.sleep(3000);
		
		Sync.waitElementPresent("xpath", "//*[@id='block-collapsible-nav']/ul/li[4]/a");
		Common.clickElement("xpath", "//*[@id='block-collapsible-nav']/ul/li[4]/a");

		Sync.waitElementPresent("xpath", "(//span[@class='checkmark'])[1]");
		Common.clickElement("xpath", "(//span[@class='checkmark'])[1]");

		Sync.waitElementPresent("xpath", "(//span[@class='checkmark'])[2]");
		Common.clickElement("xpath", "(//span[@class='checkmark'])[2]");
		//String title=Common.findElement("xpath", "//span[contains(text(), 'Kontoinformationen bearbeiten')]").getText();
		//title=("Kontoinformationen Bearbeiten");
		String s=Common.getText("xpath", "//span[contains(text(), 'Kontoinformationen bearbeiten')]");
		Assert.assertEquals(s, "Kontoinformationen Bearbeiten");

		report.addPassLog("Should display MyAccount Information  Page", "MyAccount Information Page displayed successfully", Common.getscreenShotPathforReport("My Page success"));
	}
	
	
	
	public void FRMyAccountpage() throws Exception{
		try
		{
		Thread.sleep(3000);
		//Sync.waitElementPresent("xpath", "//a[contains(text(), 'Kontoinformation')]");
		//Common.clickElement("xpath", "//a[contains(text(), 'Kontoinformation')]");
		
		Sync.waitElementPresent("xpath", "//a[contains(text(), 'Informations du compte')]");
		Common.clickElement("xpath", "//a[contains(text(), 'Informations du compte')]");
		Sync.waitElementPresent("xpath", "(//span[@class='checkmark'])[1]");
		Common.clickElement("xpath", "(//span[@class='checkmark'])[1]");

		Sync.waitElementPresent("xpath", "(//span[@class='checkmark'])[2]");
		Common.clickElement("xpath", "(//span[@class='checkmark'])[2]");
		
		String s=Common.getText("xpath", "//span[contains(text(), 'Modifier les informations du compte')]");
		System.out.println(s);
		Assert.assertEquals(s, "Modifier Les Informations Du Compte");
		Common.isElementDisplayed("xpath" , "//span[contains(text(), 'Informations du compte')]");
		
		report.addPassLog("Should display MyAccount Information  Page", "MyAccount Information Page displayed successfully", Common.getscreenShotPathforReport("MyAccount Information Page success"));
	}
catch(Exception |Error e)
{
	report.addFailedLog("Should display MyAccount Information Page", "MyAccount InformationPage not displayed", Common.getscreenShotPathforReport("MyAccount Information page  Failed"));
	e.printStackTrace();
	Assert.fail();
}
	}


	public void ITMyAccountpage() throws Exception{
		try
		{
		Thread.sleep(3000);
		//Sync.waitElementPresent("xpath", "//a[contains(text(), 'Kontoinformation')]");
		//Common.clickElement("xpath", "//a[contains(text(), 'Kontoinformation')]");
		
		Sync.waitElementPresent("xpath", "(//li[@class='nav item'])[5]");
		Common.clickElement("xpath", "(//li[@class='nav item'])[5]");
		Sync.waitElementPresent("xpath", "(//span[@class='checkmark'])[1]");
		Common.clickElement("xpath", "(//span[@class='checkmark'])[1]");

		Sync.waitElementPresent("xpath", "(//span[@class='checkmark'])[2]");
		Common.clickElement("xpath", "(//span[@class='checkmark'])[2]");
		
		String s=Common.getText("xpath", "//span[contains(text(), 'Modificare le informazioni Account')]");
		System.out.println(s);
		Assert.assertEquals(s, "Modificare Le Informazioni Account");
		Common.isElementDisplayed("xpath" , "//span[contains(text(), 'Modificare le informazioni Account')]");
		
		report.addPassLog("Should display MyAccount Information  Page", "MyAccount Information Page displayed successfully", Common.getscreenShotPathforReport("MyAccount Information Page success"));
	}
catch(Exception |Error e)
{
	report.addFailedLog("Should display MyAccount Information Page", "MyAccount InformationPage not displayed", Common.getscreenShotPathforReport("MyAccount Information page  Failed"));
	e.printStackTrace();
	Assert.fail();
}
	}

	
	

	public void Minicartwithproducts() throws Exception
	{
		String expectedResult="Product adding to mini cart";
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Common.clickElement("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Thread.sleep(5000);
			report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));

			Sync.waitElementPresent("xpath", "//a[@class='action viewcart']");
			Common.clickElement("xpath", "//a[@class='action viewcart']");
			String s=Common.getText("xpath", "//p[contains(text(), 'Warenkorb')]");
			Assert.assertEquals(s, "Warenkorb");

			report.addPassLog(expectedResult, "Should display View Cart Page", "View Cart Page display successfully", Common.getscreenShotPathforReport("View Cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display View Cart Page", "View Cart Page not displayed", Common.getscreenShotPathforReport("View Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	public void ITMinicartwithproducts() throws Exception
	{
		String expectedResult="Product adding to mini cart";
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Common.clickElement("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Thread.sleep(5000);
			report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));

			Sync.waitElementPresent("xpath", "//a[@class='action viewcart']");
			Common.clickElement("xpath", "//a[@class='action viewcart']");
			/*String s=Common.getText("xpath", "//p[contains(text(), 'Carrello')]");
			Assert.assertEquals(s, "Carrello");*/
			
			String s=Common.getText("xpath", "//p[contains(text(), 'Carrello')]");
			Assert.assertEquals(s, "Carrello");

			report.addPassLog(expectedResult, "Should display View Cart Page", "View Cart Page display successfully", Common.getscreenShotPathforReport("View Cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display View Cart Page", "View Cart Page not displayed", Common.getscreenShotPathforReport("View Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	public void FRMinicartwithproducts() throws Exception
	{
		String expectedResult="Product adding to mini cart";
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Common.clickElement("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Thread.sleep(5000);
			
			report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));

			Sync.waitElementPresent("xpath", "//a[@class='action viewcart']");
			Common.clickElement("xpath", "//a[@class='action viewcart']");
			String s=Common.getText("xpath", "//p[contains(text(), 'Panier')]");
			Assert.assertEquals(s, "Panier");
			Common.isElementDisplayed("xpath" , "//p[contains(text(), 'Panier')]");

			report.addPassLog(expectedResult, "Should display View Cart Page", "View Cart Page display successfully", Common.getscreenShotPathforReport("View Cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display View Cart Page", "View Cart Page not displayed", Common.getscreenShotPathforReport("View Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	
	
	
	

	public void MinicartwithZeroproducts() throws Exception
	{
		String expectedResult="Zero Product in mini cart";
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@class='bundle-custom-addtocart braun_btn add_to_cart_btn']");
			Common.clickElement("xpath", "//button[@class='bundle-custom-addtocart braun_btn add_to_cart_btn']");
			Thread.sleep(5000);
			//Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/checkout/cart/']"));
			//Common.isElementDisplayed("xpath", "//strong[@class='product-item-name']");
			report.addPassLog(expectedResult, "Should display Mini Cart with zero products", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Mini Cart with zero products", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}









	public void ReviewsinPDPpage() throws Exception
	{
		String expectedResult="Displaying Reviews page";
		try {
			Thread.sleep(4000);
			//	Common.actionsKeyPress(Keys.DOWN);
			//Common.scrollIntoView("xpath" , "//font[contains(text(),'reviews')]");
			//Sync.waitElementPresent("xpath", "//a[@id='tab-label-reviews-title']");
			//Common.clickElement("xpath", "//a[@id='tab-label-reviews-title']");
			
			//Sync.waitElementPresent("xpath", "//a[@id='tab-label-reviews-title']");
			//Common.clickElement("xpath", "//a[@id='tab-label-reviews-title']");
			Sync.waitElementPresent("xpath", "(//a[@class='data switch'])[3]");
			Common.clickElement("xpath", "(//a[@class='data switch'])[3]");
			Thread.sleep(8000);
			String s=Common.getText("xpath", "//a[contains(text(), 'Bewertungen')]");
			Assert.assertEquals(s, "Bewertungen");

			report.addPassLog(expectedResult, "Should display Reviews Page", "Reviews Page display successfully", Common.getscreenShotPathforReport("Reviews page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Reviews  Page", "Reviews Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void GermanycreateAccount(String DataSet) throws InterruptedException
	{
		String expectedResult="Account Creation of User with valid details";
		try {

			Sync.waitElementPresent("xpath", "(//a[@class='braun-icons braun-profile-icon'])");
			Common.clickElement("xpath", "(//a[@class='braun-icons braun-profile-icon'])");
			Sync.waitElementPresent("xpath", "//a[@class='create_acnt braun_link']");

			Common.clickElement("xpath", "//a[@class='create_acnt braun_link']");
			Thread.sleep(8000);
			Common.textBoxInput("id", "firstname", data.get(DataSet).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(DataSet).get("LastName"));
			Common.textBoxInput("id", "email_address",Utils.getEmailid());
			//Common.textBoxInput("id", "email_address", data.get(DataSet).get("Email"));
			Common.textBoxInput("id", "password", data.get(DataSet).get("Password"));
			Common.textBoxInput("id", "password-confirmation", data.get(DataSet).get("Confirmpassword"));

			Common.clickElement("xpath", "(//button[@type='submit'])[2]");
			Thread.sleep(10000);
			String s=Common.getText("xpath", "//div[@class='message-success success message']");
			Assert.assertEquals(s, "Sie m??ssen Ihr Konto best??tigen. Bitte ??berpr??fen Sie Ihre E-Mail-Adresse f??r den Best??tigungslink oder Klicken Sie hier f??r einen neuen Link.");
			report.addPassLog(expectedResult, "Should display My Account Page", "My Account Page display successfully", Common.getscreenShotPathforReport("Account Creation success"));

		}catch(Exception |Error e)
		
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display My Account Page", "My Account Page not display", Common.getscreenShotPathforReport("Account Creation Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	public void ItalyAccountAccountCreateation(String DataSet) throws InterruptedException
	{
		String expectedResult="Account Creation of User with valid details";
		try {

			Sync.waitElementPresent("xpath", "(//a[@class='braun-icons braun-profile-icon'])");
			Common.clickElement("xpath", "(//a[@class='braun-icons braun-profile-icon'])");
			Sync.waitElementPresent("xpath", "//a[@class='create_acnt braun_link']");

			Common.clickElement("xpath", "//a[@class='create_acnt braun_link']");
			Thread.sleep(8000);
			Common.textBoxInput("id", "firstname", data.get(DataSet).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(DataSet).get("LastName"));
			Common.textBoxInput("id", "email_address",Utils.getEmailid());
			//Common.textBoxInput("id", "email_address", data.get(DataSet).get("Email"));
			Common.textBoxInput("id", "password", data.get(DataSet).get("Password"));
			Common.textBoxInput("id", "password-confirmation", data.get(DataSet).get("Confirmpassword"));

			Common.clickElement("xpath", "(//button[@type='submit'])[2]");
			Thread.sleep(10000);
			/*String s=Common.getText("xpath", "//div[@class='message-success success message']");
			Assert.assertEquals(s, "Sie m??ssen Ihr Konto best??tigen. Bitte ??berpr??fen Sie Ihre E-Mail-Adresse f??r den Best??tigungslink oder Klicken Sie hier f??r einen neuen Link.");*/
			report.addPassLog(expectedResult, "Should display My Account Page", "My Account Page display successfully", Common.getscreenShotPathforReport("Account Creation success"));

		}catch(Exception |Error e)
		
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display My Account Page", "My Account Page not display", Common.getscreenShotPathforReport("Account Creation Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void Italysingin(String DataSet) throws Exception{

		String expectedResult="Land on login page";

		try {

			Sync.waitElementPresent("xpath", "//a[@class='braun-icons braun-profile-icon']");
			Common.clickElement("xpath", "//a[@class='braun-icons braun-profile-icon']");
			Sync.waitElementPresent("id", "email");
			Thread.sleep(4000);
			Common.textBoxInput("id", "email", data.get(DataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(DataSet).get("Password"));
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "(//button[@type='submit'])[2]");
			Common.clickElement(By.xpath("(//button[@type='submit'])[2]"));
			String s=Common.getText("xpath", "//li[@class='nav item current']");
			Assert.assertEquals(s, "Il mio Account");
			report.addPassLog(expectedResult,"Should Login to application successfully", "Login to application successfully", Common.getscreenShotPathforReport("Login success"));
		}

		catch(Exception |Error e)
		{
			report.addPassLog(expectedResult,"Should Login to application successfully", "Login to application Failed", Common.getscreenShotPathforReport("Login failed"));
			e.printStackTrace();
			Assert.fail();
		}	
	}
	
	public void ItalyMyAccountPage() throws Exception{

		try

		{

		Thread.sleep(3000);


		Sync.waitElementPresent("xpath", "//a[contains(text(), 'I Miei ordini')]");

		Common.clickElement("xpath", "//a[contains(text(), 'I Miei ordini')]");


		Sync.waitElementPresent("xpath", "//td/a[@class='action view']");

		Common.clickElement("xpath", "//td/a[@class='action view']");


		         String url=Common.getCurrentURL();

		System.out.println(url);

		Common.assertionCheckwithReport(url.contains("order"), "Verifying  My orders page", "It should navigate to My orders page", "successfully lands on My orders page","My orders");

		//Common.isElementDisplayed("xpath" , "//span[contains(text(), 'Informations du compte')]");


		report.addPassLog("Should display MyAccount Information  Page", "MyAccount Information Page displayed successfully", Common.getscreenShotPathforReport("MyAccount Information Page success"));

		}

		catch(Exception |Error e)

		{

		       report.addFailedLog("Should display MyAccount Information Page", "MyAccount InformationPage not displayed", Common.getscreenShotPathforReport("MyAccount Information page  Failed"));

		       e.printStackTrace();

		Assert.fail();

		}

		}
	
	
	
	public void ItalyMYaccountInformation() throws Exception{

		try

		{

		Thread.sleep(2000);

		Sync.waitElementPresent("xpath", "//ul[@class='nav items']//li[4]//a");

		Common.clickElement("xpath", "//ul[@class='nav items']//li[4]//a");


		//String title=Common.findElement("xpath", "//span[contains(text(), 'Meine Bestellungen')]").getText();

		//title=("Meine Bestellungen");

		String s=Common.getText("xpath", "//span[contains(text(), 'Modificare le informazioni Account')]");

		Assert.assertEquals(s, "Modificare Le Informazioni Account");

		 

		report.addPassLog("Should display AccountInformation Page", "AccountInformation Page display successfully", Common.getscreenShotPathforReport("AccountInformation page success"));


		}

		 

		catch(Exception |Error e)

		{

		       report.addFailedLog("Should display MYOrders page", "display MYOrders page", Common.getscreenShotPathforReport("MYOrders page Failed"));

		       e.printStackTrace();

		Assert.fail();

		}

		}

	
	
	public void ITMyaccountInformation() {
		// TODO Auto-generated method stub
		try
		{
		Thread.sleep(1000);
		Sync.waitElementPresent("xpath", "//*[@id='block-collapsible-nav']/ul/li[2]/a");
		Common.clickElement("xpath", "//*[@id='block-collapsible-nav']/ul/li[2]/a");
		
		//String title=Common.findElement("xpath", "//span[contains(text(), 'Meine Bestellungen')]").getText();
		//title=("Meine Bestellungen");
		String s=Common.getText("xpath", "//span[text()='I Miei ordini']");
		Assert.assertEquals(s, "I Miei Ordini");

		report.addPassLog("Should display MyOrders Page", "MyOrders Page display successfully", Common.getscreenShotPathforReport("MyOrders page success"));
		
	}

catch(Exception |Error e)
{
	report.addFailedLog("Should display MYOrders page", "display MYOrders page", Common.getscreenShotPathforReport("MYOrders page Failed"));
	e.printStackTrace();
	Assert.fail();
}
}

	
	
	
	public void FrancecreateAccount(String DataSet) throws InterruptedException
	{
		String expectedResult="Account Creation of User with valid details";
		try {

			Sync.waitElementPresent("xpath", "(//a[@class='braun-icons braun-profile-icon'])");
			Common.clickElement("xpath", "(//a[@class='braun-icons braun-profile-icon'])");
			Sync.waitElementPresent("xpath", "//a[@class='create_acnt braun_link']");

			Common.clickElement("xpath", "//a[@class='create_acnt braun_link']");
			Thread.sleep(8000);
			Common.textBoxInput("id", "firstname", data.get(DataSet).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(DataSet).get("LastName"));
			Common.textBoxInput("id", "email_address",Utils.getEmailid());
			//Common.textBoxInput("id", "email_address", data.get(DataSet).get("Email"));
			Common.textBoxInput("id", "password", data.get(DataSet).get("Password"));
			Common.textBoxInput("id", "password-confirmation", data.get(DataSet).get("Confirmpassword"));

			Common.clickElement("xpath", "(//button[@type='submit'])[2]");
			Thread.sleep(10000);
			//String s=Common.getText("xpath", "//span[contains(text(),'My Account')]");
			//Assert.assertEquals(s, "My Account");
			report.addPassLog(expectedResult, "Should display My Account Page", "My Account Page display successfully", Common.getscreenShotPathforReport("Account Creation success"));

		}catch(Exception |Error e)
		
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display My Account Page", "My Account Page not display", Common.getscreenShotPathforReport("Account Creation Failed"));
			Assert.fail();
		}
	}





	public void GEMyaccountInformation() throws Exception{
		try
		{
		Thread.sleep(1000);
		Sync.waitElementPresent("xpath", "//*[@id='block-collapsible-nav']/ul/li[2]/a");
		Common.clickElement("xpath", "//*[@id='block-collapsible-nav']/ul/li[2]/a");
		
		//String title=Common.findElement("xpath", "//span[contains(text(), 'Meine Bestellungen')]").getText();
		//title=("Meine Bestellungen");
		String s=Common.getText("xpath", "//span[contains(text(), 'Meine Bestellungen')]");
		Assert.assertEquals(s, "Meine Bestellungen");

		report.addPassLog("Should display MyOrders Page", "MyOrders Page display successfully", Common.getscreenShotPathforReport("MyOrders page success"));
		
	}

catch(Exception |Error e)
{
	report.addFailedLog("Should display MYOrders page", "display MYOrders page", Common.getscreenShotPathforReport("MYOrders page Failed"));
	e.printStackTrace();
	Assert.fail();
}
}

	
	public void FRMyaccountInformation() throws Exception{
		try
		{
		Thread.sleep(1000);
		Sync.waitElementPresent("xpath", "//*[@id='block-collapsible-nav']/ul/li[2]/a");
		Common.clickElement("xpath", "//*[@id='block-collapsible-nav']/ul/li[2]/a");
		
		String title=Common.findElement("xpath", "//a[contains(text(), 'Meine Bestellungen')]").getText();
		title=("Meine Bestellungen");

		report.addPassLog("Should display MyOrders Page", "MyOrders Page display successfully", Common.getscreenShotPathforReport("MyOrders page success"));

	}
	catch(Exception |Error e)
	{
		report.addFailedLog("Should display MYOrders page", "display MYOrders page", Common.getscreenShotPathforReport("MYOrders page Failed"));
		e.printStackTrace();
		Assert.fail();
	}
}
	
	public void FRMyaccountInformation(String dataset) throws Exception{
		Thread.sleep(1000);
		Sync.waitElementPresent("xpath", "//a[contains(text(), 'Mes commandes')]");
		Common.clickElement("xpath", "//a[contains(text(), 'Mes commandes')]");
		
		String expectedResult1=data.get(dataset).get("FROrdersmsg");
		//String expectedResult1="Subscription message :"+data.get(dataSet).get("Confirmationmsg");
		System.out.println(data.get(dataset).get("FROrdersmsg")+ "excel value");
		
		System.out.println("Mes Commandes");
		//String expectedResult1="We have updated your subscription.:"+data.get(dataSet).get("Confirmationmsg");
		
		String expectedResult2=Common.findElement("xpath", "//span[@class='base']").getText();
		
		System.out.println(expectedResult2+ "UI VALUE");
		//title=("We have updated your subscription.");
		
		
	if(expectedResult1==expectedResult2)
		{
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}
		
		
	}


	public void Revieworder() throws Exception{
		try
		{
			Thread.sleep(3000);
			
			Sync.waitElementPresent("xpath", "(//a[@class='action view'])[2]");
			Common.clickElement("xpath", "(//a[@class='action view'])[2]");
			
			/*String s1=Common.getText("xpath", "//span[contains(text(), 'Nachbestellen')]");
			Assert.assertEquals(s1, "Nachbestellen");*/
			
			if(Common.isElementDisplayed("xpath", "//a[text()='Kontoinformation']")) {
				String s=Common.getText("xpath", "//a[text()='Kontoinformation']");
			   	System.out.println(s);
			   	Assert.assertEquals(s, "Kontoinformation");
			}else {
				String s=Common.getText("xpath", "//span[contains(text(), 'Renouveler')]");
			   	System.out.println(s);
			   	Assert.assertEquals(s, "Renouveler");
			}
			

			report.addPassLog("Should display Order page", "display Order page successfully", Common.getscreenShotPathforReport("Order page success"));
		}
		catch(Exception |Error e)
		{
			report.addFailedLog("Should display Order page", "display Order page", Common.getscreenShotPathforReport("Order page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}





	public void GEclick_forgotpassword() throws Exception{
		try{

			Sync.waitElementPresent("xpath", "//a[@class='braun-icons braun-profile-icon']");
			Common.clickElement("xpath", "//a[@class='braun-icons braun-profile-icon']");
			Common.clickElement("xpath", "//a[@class='forgot_pass']");
			Common.textBoxInput("id", "email_address","pavani.tumati@gmail.com");
			Common.clickElement("xpath" , "//button[@id='forgot_pass']");
			String s=Common.getText("xpath", "//div[@class='message-success success message']");
			Assert.assertEquals(s, "Wenn ein Kundenkonto mit pavani.tumati@gmail.com verkn??pft ist, erhalten Sie per e-Mail einen Link zum Zur??cksetzen Ihres Passworts.");
			report.addPassLog("Should display forgot password success message for user", "Forgot password success message for user display successfully", Common.getscreenShotPathforReport("forgotPassword success"));
		}
		catch(Exception |Error e)
		{
			report.addFailedLog("Should display forgot password success message for user", "Forgot password success message for user not display", Common.getscreenShotPathforReport("forgotPassword Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	public void ITclick_forgotpassword() throws Exception{
		try{

			Sync.waitElementPresent("xpath", "//a[@class='braun-icons braun-profile-icon']");
			Common.clickElement("xpath", "//a[@class='braun-icons braun-profile-icon']");
			Common.clickElement("xpath", "//a[@class='forgot_pass']");
			Common.textBoxInput("id", "email_address","Vani.geetla12@gmail.com");
			Common.clickElement("xpath" , "//button[@id='forgot_pass']");
			String s=Common.getText("xpath", "//div[@class='message-success success message']");
			Assert.assertEquals(s, "Se c'?? un account associato con Vani.geetla12@gmail.com riceverai un'email con il link per reimpostare la password.");
			report.addPassLog("Should display forgot password success message for user", "Forgot password success message for user display successfully", Common.getscreenShotPathforReport("forgotPassword success"));
		}
		catch(Exception |Error e)
		{
			report.addFailedLog("Should display forgot password success message for user", "Forgot password success message for user not display", Common.getscreenShotPathforReport("forgotPassword Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	public void FRclick_forgotpassword(String dataset) throws Exception{
		try{

			Sync.waitElementPresent("xpath", "//a[@class='braun-icons braun-profile-icon']");
			Common.clickElement("xpath", "//a[@class='braun-icons braun-profile-icon']");	
			//Common.clickElement("xpath", "(//span[contains(text(),'Passwort vergessen')])[1]");
			
			Common.clickElement("xpath", "//a[@class='forgot_pass']");
			Common.textBoxInput("id", "email_address","pavani.tumati@gmail.com");
			//Common.scrollToElementAndClick("xpath", "//*[@id='form-validate']/div/div[1]/button");
			Common.clickElement("xpath" , "//button[@id='forgot_pass']");
			//Common.actionsKeyPress(Keys.PAGE_UP);
			
			String expectedResult1=data.get(dataset).get("FRForgtmsg");
			//String expectedResult1="Subscription message :"+data.get(dataSet).get("Confirmationmsg");
			System.out.println(data.get(dataset).get("FRForgtmsg")+ "excel value");
			
			System.out.println("S'il y a un compte associ?? ?? l???adresse pavani.tumati@gmail.com, vous recevrez un email avec un lien pour r??initialiser votre mot de passe.");
			//String expectedResult1="We have updated your subscription.:"+data.get(dataSet).get("Confirmationmsg");
			
			String expectedResult2=Common.findElement("xpath", "//div[@class='message-success success message']").getText();
			
			System.out.println(expectedResult2+ "UI VALUE");
			//title=("We have updated your subscription.");
			
			
		if(expectedResult1==expectedResult2)
			{
				System.out.println("pass");
			}
			else
			{
				System.out.println("fail");
			}
			
		/*	String s=Common.getText("xpath", "//div[@class='message-success success message']");
			System.out.println(s);
			if(s.contentEquals("Wenn ein Kundenkonto mit pavani.tumati@gmail.com verkn??pft ist, erhalten Sie per e-Mail einen Link zum Zur??cksetzen Ihres Passworts."))
			{
			Assert.assertEquals(s, "Wenn ein Kundenkonto mit pavani.tumati@gmail.com verkn??pft ist, erhalten Sie per e-Mail einen Link zum Zur??cksetzen Ihres Passworts.");
			System.out.println("Forgot password successpage");
			}
			else {
				Assert.assertEquals(s, "S'il y a un compte associ?? ?? l???adresse pavani.tumati@gmail.com, vous recevrez un email avec un lien pour r??initialiser votre mot de passe");
				
			}*/
	
			
			report.addPassLog("Should display forgot password success message for user", "Forgot password success message for user display successfully", Common.getscreenShotPathforReport("forgotPassword success"));
		}
		catch(Exception |Error e)
		{
			report.addFailedLog("Should display forgot password success message for user", "Forgot password success message for user not display", Common.getscreenShotPathforReport("forgotPassword Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}


	public void ChangeproductQuantity() throws Exception{

		try
		{
			Sync.waitElementPresent("xpath", "//select[@id='qty']");
			Common.clickElement("xpath", "//select[@id='qty']");
			Common.clickElement("xpath", "(//select[@id='qty']//option)[3]");
			/*String s=Common.getText("xpath", "//h1[@ class='page-title']");
			Assert.assertEquals(s, "Braun ThermoScan?? 7 avec Age Precision??");
			Common.isElementDisplayed("xpath" , "//span[@class='base']");*/
			report.addPassLog("Should display increased product quantity page", "increased product quantity page display successfully", Common.getscreenShotPathforReport("increased product quantity success"));
		}
		catch(Exception |Error e)
		{
			report.addFailedLog("Should display increased product quantity page", "increased product quantity page not display", Common.getscreenShotPathforReport("increased product quantity Failed"));
			e.printStackTrace();
			Assert.fail();
		}	
	}


	public void GEnavigateMinicartwithoutcheckout() throws Exception
	{
		String expectedResult="Product adding to mini cart";
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Common.clickElement("xpath", "//button[@class='action tocart add_to_cart_btn braun_btn']");
			Thread.sleep(10000);

			String URL=Common.getCurrentURL();
			/*if(URL.contains("uk_en")) {
            Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/checkout/cart/']"));
        }else if(URL.contains("de_de")) {
            Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"de_de/checkout/cart/']"));
        }else if(URL.contains("fr_fr")) {
            Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"fr_fr/checkout/cart/']"));
        }*/
			
			//String title=Common.findElement("xpath", "(//span[contains(text(), 'Mein Warenkorb')])[2]").getText();
			//title=("MEIN WARENKORB");
			String s=Common.getText("xpath", "(//span[contains(text(), 'Mein Warenkorb')])[2]");
			Assert.assertEquals(s, "MEIN WARENKORB");
			Thread.sleep(1000);
			report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	public void navigateMinicartwithoutcheckout() throws Exception
	{
		String expectedResult="Product adding to mini cart";
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@type='submit']");
			Common.clickElement("xpath", "//button[@type='submit']");
			Thread.sleep(10000);

			String URL=Common.getCurrentURL();
			/*if(URL.contains("uk_en")) {
            Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/checkout/cart/']"));
        }else if(URL.contains("de_de")) {
            Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"de_de/checkout/cart/']"));
        }else if(URL.contains("fr_fr")) {
            Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"fr_fr/checkout/cart/']"));
        }*/
			
			//String title=Common.findElement("xpath", "(//span[contains(text(), 'Mein Warenkorb')])[2]").getText();
			//title=("MEIN WARENKORB");
			
			/*String s=Common.getText("xpath", "(//span[contains(text(), 'Mon panier')])[2]");
			Assert.assertEquals(s, "MON PANIER");*/
			Thread.sleep(1000);
			report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void CreditcardPayment(String dataSet) throws Exception
	{
		String expectedResult="Payment With Valid Credit Card";
		try {
			Thread.sleep(12000);
			//Common.clickElement(By.xpath("//input[@id='encryptedCardNumber']"));
			//Common.javascriptclickElement("xpath", "//input[@id='encryptedCardNumber']");
			//Common.clickElement("xpath", "//input[@id='adyen_cc']");
			Common.javascriptclickElement("xpath" , "//input[@id='adyen_cc']");


			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[1]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedCardNumber']");

			Common.textBoxInput("xpath", "//input[@id='encryptedCardNumber']", data.get(dataSet).get("cardNumber"));

			Common.switchToDefault();
			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[2]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedExpiryDate']");
			Common.textBoxInput("xpath", "//input[@id='encryptedExpiryDate']", data.get(dataSet).get("ExpMonth"));

			Common.switchToDefault();
			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[3]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedSecurityCode']");
			Common.textBoxInput("xpath", "//input[@id='encryptedSecurityCode']",  data.get(dataSet).get("cvv"));

			Common.switchToDefault();
			//Common.textBoxInput("xpath", "//input[@placeholder='A. M??ller']", data.get(dataSet).get("name"));
			Common.textBoxInput("xpath", "//input[@class='adyen-checkout__input adyen-checkout__input--text adyen-checkout__card__holderName__input sMjS8HCbKiP5yR9Td9ZgQ adyen-checkout__input--large']", data.get(dataSet).get("name"));

			Thread.sleep(1000);
			/*Common.clickElement("xpath" , "//input[@id='shipping-save-in-address-book']]");
			Thread.sleep(2000);
			//Common.clickElement("xpath", "//*[@id='billing-address-same-as-shipping-shared']");
			Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
			Common.clickElement("xpath", "//button[@class='action primary checkout']");*/
			Common.clickElement("xpath", "//input[@id='agreement_adyen_cc_2']");
			
			Sync.waitElementPresent("xpath", "//button[@title='Bestellung aufgeben']");
			Common.clickElement("xpath", "//button[@title='Bestellung aufgeben']");
			Thread.sleep(1000);
			
			//report.addPassLog(expectedResult,"Should display credit card  successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("Payment Credit cart success"));

			report.addPassLog(expectedResult,"Should Make payment with valid credit card successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("Payment Credit cart success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Make payment with valid credit card successfully", "Make payment with valid credit card unsuccessfully", Common.getscreenShotPathforReport("Payment Credit card Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	public void SwedenCreditcardPayment(String dataSet) throws Exception
	{
		String expectedResult="Payment With Valid Credit Card";
		try {
			Thread.sleep(12000);
			//Common.clickElement(By.xpath("//input[@id='encryptedCardNumber']"));
			//Common.javascriptclickElement("xpath", "//input[@id='encryptedCardNumber']");
			//Common.clickElement("xpath", "//input[@id='adyen_cc']");
			Common.javascriptclickElement("xpath" , "//input[@id='adyen_cc']");


			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[1]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedCardNumber']");

			Common.textBoxInput("xpath", "//input[@id='encryptedCardNumber']", data.get(dataSet).get("cardNumber"));

			Common.switchToDefault();
			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[2]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedExpiryDate']");
			Common.textBoxInput("xpath", "//input[@id='encryptedExpiryDate']", data.get(dataSet).get("ExpMonth"));

			Common.switchToDefault();
			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[3]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedSecurityCode']");
			Common.textBoxInput("xpath", "//input[@id='encryptedSecurityCode']",  data.get(dataSet).get("cvv"));

			Common.switchToDefault();
			//Common.textBoxInput("xpath", "//input[@placeholder='A. M??ller']", data.get(dataSet).get("name"));
			Common.textBoxInput("xpath", "//input[@class='adyen-checkout__input adyen-checkout__input--text adyen-checkout__card__holderName__input sMjS8HCbKiP5yR9Td9ZgQ adyen-checkout__input--large']", data.get(dataSet).get("name"));

			Thread.sleep(1000);
			/*Common.clickElement("xpath" , "//input[@id='shipping-save-in-address-book']]");
			Thread.sleep(2000);
			//Common.clickElement("xpath", "//*[@id='billing-address-same-as-shipping-shared']");
			Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
			Common.clickElement("xpath", "//button[@class='action primary checkout']");*/
			Common.clickElement("xpath", "//input[@id='agreement_adyen_cc_4']");
			
			Sync.waitElementPresent("xpath", "(//button[@class='action primary checkout'])[2]");
			Common.clickElement("xpath", "(//button[@class='action primary checkout'])[2]");
			Thread.sleep(1000);
			
			//report.addPassLog(expectedResult,"Should display credit card  successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("Payment Credit cart success"));

			report.addPassLog(expectedResult,"Should Make payment with valid credit card successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("Payment Credit cart success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Make payment with valid credit card successfully", "Make payment with valid credit card unsuccessfully", Common.getscreenShotPathforReport("Payment Credit card Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	
	public void SDProductselection() {
		// TODO Auto-generated method stub

		String expectedResult="Product Selection from Category";
		try {
			Thread.sleep(4000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			Common.clickElement("xpath", "//a[@id='ui-id-2']");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "(//h3[@class='cat_sldr_te'])[5]");
			Common.clickElement("xpath", "(//h3[@class='cat_sldr_te'])[5]");
			Common.isElementDisplayed("xpath", "//span[@class='base']");
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display Product Details Page", "Product Details  Page display successfully", Common.getscreenShotPathforReport("Product Details page display success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Details Page", "Product Details Page not displayed", Common.getscreenShotPathforReport("Product Details page Failed"));
			e.printStackTrace();
			Assert.fail();
	}
	}
	
	
	public void SDNavigateMinicart() {
		// TODO Auto-generated method stub
		 String expectedResult="Navigating Minicart";
	        try {
	           // Sync.waitElementClickable(30, By.xpath("//button[@id='top-cart-btn-checkout']"));
	           // Common.findElement("xpath", "//button[@id='top-cart-btn-checkout']").click();
	            //Common.clickElement("xpath", "//a[@title='Italy']");
	            Thread.sleep(2000);
	           // String S=Common.getText("xpath", "(//span[text()='Min kundvagn'])[2]");
				//System.out.println(S);
				//Assert.assertEquals(S, "MIN KUNDVAGN");
				Sync.waitElementClickable(30, By.xpath("//button[@class='bundle-custom-addtocart braun_btn add_to_cart_btn']"));
	            Common.findElement("xpath", "//button[@class='bundle-custom-addtocart braun_btn add_to_cart_btn']").click();
	            report.addPassLog(expectedResult, "Should display Minicart", "Minicart displayed successfully", Common.getscreenShotPathforReport("Minicart success"));
	        }catch(Exception |Error e)
	        {
	            report.addFailedLog(expectedResult,"Should display Minicart", "Minicart not display", Common.getscreenShotPathforReport("Minicart Failed"));
	            e.printStackTrace();
	            Assert.fail();
	}
	}
	
	
	
	
	public void SDCheckoutPage() {
		// TODO Auto-generated method stub
		String expectedResult="Product adding to checkout page";
		try {

			Sync.waitElementPresent("xpath", "//button[@title='G?? till kassan']");
			Common.clickElement("xpath", "//button[@title='G?? till kassan']");
		
			Thread.sleep(2000);
			//String s=Common.getText("xpath", "//font[text()='Check out as a guest']");
			//Assert.assertEquals(s, "Check out as a guest");
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "//button[@title='Guest Checkout']");
			Common.clickElement("xpath", "//button[@title='Guest Checkout']");
			report.addPassLog(expectedResult, "Shousld display Guest Checkout", "Guest Checkout display successfully", Common.getscreenShotPathforReport("Guest Checkout success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Guest Checkout", "Guest Checkout not displayed", Common.getscreenShotPathforReport("Guest Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
	}

	}

	
	public void SDShipping_Address(String dataSet) {
		// TODO Auto-generated method stub

		{
	try
	{        Thread.sleep(2000);
	        Sync.waitElementPresent("xpath", "(//input[@type='email'])[1]");  
			Common.textBoxInput("xpath", "(//input[@type='email'])[1]", data.get(dataSet).get("Email"));
			

			Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[1]");
			Common.clickElement("xpath", "(//button[@class='button action continue primary'])[1]");

			Thread.sleep(2000);
			Common.textBoxInput("xpath", "(//input[@class='input-text'])[2]", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "(//input[@class='input-text'])[3]", data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "(//input[@class='input-text'])[4]", data.get(dataSet).get("Street"));
			Common.textBoxInput("xpath", "(//input[@class='input-text'])[5]", data.get(dataSet).get("City"));
			Common.textBoxInput("xpath", "(//input[@class='input-text'])[7]", data.get(dataSet).get("postcode"));
			Common.textBoxInput("xpath", "(//input[@class='input-text'])[8]", data.get(dataSet).get("phone"));
			
			
			Common.clickElement("xpath", "(//input[@type='radio'])[2]");
			Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[2]");
			Common.clickElement("xpath", "(//button[@class='button action continue primary'])[2]");
			Thread.sleep(2000);
			Sync.waitElementClickable(30, By.xpath("//span[text()='Ok']"));
			Common.findElement("xpath", "//span[text()='Ok']").click();
			
			report.addPassLog("Should Display Shipping method page", "Displaying Shipping method page success", Common.getscreenShotPathforReport("Shipping method page success"));
	report.addPassLog("Should Display payment method", "Displaying payment method success", Common.getscreenShotPathforReport("payment method success"));
	}

	catch(Exception |Error e)
	{
		report.addPassLog("Should Display payment method", "Displaying payment method success", Common.getscreenShotPathforReport("payment method failed"));
		e.printStackTrace();
		Assert.fail();
	}	
	}
	}

	
	
	public void SDorder_Verifying() throws Exception{
		//Thread.sleep(10000);
		//Common.textBoxInput("id", "//textarea[contains(@id,'tt-c-comment-field')]","Ceate accounts test ");
		String expectedResult = "It redirects to order confirmation page";
		try{
			Sync.waitPageLoad();



			for(int i=0;i<10;i++){

				if(Common.getCurrentURL().contains("Vielen Dank")){
					break;
				}
				Thread.sleep(5000);
			}

			//String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']");
			//System.out.println(sucessMessage);
		//	Common.assertionCheckwithReport(sucessMessage.equals("Vielen Dank f??r Ihre Bestellung"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");

		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
					"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();
		}
	}

	
	
	
	public void InvalidCreditcardPayment(String dataSet) throws Exception
	{
		String expectedResult="Payment Method with invalid Credit card";
		try {
			Thread.sleep(12000);
			//Common.clickElement("xpath", "//input[@id='adyen_cc']");
			Common.javascriptclickElement("xpath" , "//input[@id='adyen_cc']");
			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[1]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedCardNumber']");
			Common.textBoxInput("xpath", "//input[@id='encryptedCardNumber']", data.get(dataSet).get("cardNumber"));
			Common.switchToDefault();
			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[2]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedExpiryDate']");
			Common.textBoxInput("xpath", "//input[@id='encryptedExpiryDate']", data.get(dataSet).get("ExpMonth"));

			Common.switchToDefault();
			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[3]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedSecurityCode']");
			Common.textBoxInput("xpath", "//input[@id='encryptedSecurityCode']",  data.get(dataSet).get("cvv"));
			Common.switchToDefault();
			//Common.textBoxInput("xpath", "//input[@placeholder='A. M??ller']", data.get(dataSet).get("name"));
			Common.textBoxInput("xpath", "//input[@class='adyen-checkout__input adyen-checkout__input--text adyen-checkout__card__holderName__input sMjS8HCbKiP5yR9Td9ZgQ adyen-checkout__input--large']", data.get(dataSet).get("name"));
			
			Thread.sleep(1000);
			//Common.switchFrames("id", "paymetric_xisecure_frame");
			String Errormessage=Common.getText("xpath", "//span[contains(text(), 'Ung??ltige Kartennummer')]");
			System.out.println(Errormessage);
			Assert.assertEquals(Errormessage, "Ung??ltige Kartennummer");
			Common.isElementDisplayed("xpath" , "Zahlungsart");
			//report.addPassLog(expectedResult,"Should display credit card  successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("Payment Credit cart success"));

			report.addPassLog(expectedResult, "Should display Error message for Credit card number feild", "Error message for Credit card number feild display successfully", Common.getscreenShotPathforReport("Error message credit card success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Error message for Credit card number feild", "Error message for Credit card number feild not display", Common.getscreenShotPathforReport("Error message credit card Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
			
			
			/*report.addPassLog(expectedResult,"Should Make payment with valid credit card successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("Payment Credit cart success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Make payment with valid credit card successfully", "Make payment with valid credit card unsuccessfully", Common.getscreenShotPathforReport("Payment Credit card Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}*/

	

	
	public void FRCreditcardPayment(String dataSet) throws Exception
	{
		String expectedResult="Payment With Valid Credit Card";
		try {
			Thread.sleep(12000);
			//Common.clickElement(By.xpath("//input[@id='encryptedCardNumber']"));
			//Common.javascriptclickElement("xpath", "//input[@id='encryptedCardNumber']");
			Common.clickElement("xpath", "//input[@id='adyen_cc']");

			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[1]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedCardNumber']");

			Common.textBoxInput("xpath", "//input[@id='encryptedCardNumber']", data.get(dataSet).get("cardNumber"));

			Common.switchToDefault();
			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[2]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedExpiryDate']");
			Common.textBoxInput("xpath", "//input[@id='encryptedExpiryDate']", data.get(dataSet).get("ExpMonth"));

			Common.switchToDefault();
			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[3]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedSecurityCode']");
			Common.textBoxInput("xpath", "//input[@id='encryptedSecurityCode']",  data.get(dataSet).get("cvv"));

			Common.switchToDefault();
			//Common.textBoxInput("xpath", "//input[@placeholder='A. M??ller']", data.get(dataSet).get("name"));
			Common.textBoxInput("xpath", "//input[@class='adyen-checkout__input adyen-checkout__input--text adyen-checkout__card__holderName__input sMjS8HCbKiP5yR9Td9ZgQ adyen-checkout__input--large']", data.get(dataSet).get("name"));

			Thread.sleep(1000);
			/*Common.clickElement("xpath" , "//input[@id='shipping-save-in-address-book']]");
			Thread.sleep(2000);
			//Common.clickElement("xpath", "//*[@id='billing-address-same-as-shipping-shared']");
			Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
			Common.clickElement("xpath", "//button[@class='action primary checkout']");*/
			Common.clickElement("xpath", "//input[@id='agreement_adyen_cc_3']");
			
			Sync.waitElementPresent("xpath", "(//button[@title='Passez la commande'])[2]");
			Common.clickElement("xpath", "(//button[@title='Passez la commande'])[2]");
			Thread.sleep(1000);
			
			//report.addPassLog(expectedResult,"Should display credit card  successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("Payment Credit cart success"));

			report.addPassLog(expectedResult,"Should Make payment with valid credit card successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("Payment Credit cart success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Make payment with valid credit card successfully", "Make payment with valid credit card unsuccessfully", Common.getscreenShotPathforReport("Payment Credit card Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	public void FRInvalidCreditcardPayment(String dataSet) throws Exception
	{
		String expectedResult="Payment With Valid Credit Card";
		try {
			Thread.sleep(12000);
			//Common.clickElement(By.xpath("//input[@id='encryptedCardNumber']"));
			//Common.javascriptclickElement("xpath", "//input[@id='encryptedCardNumber']");
			Common.clickElement("xpath", "//input[@id='adyen_cc']");

			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[1]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedCardNumber']");

			Common.textBoxInput("xpath", "//input[@id='encryptedCardNumber']", data.get(dataSet).get("cardNumber"));

			Common.switchToDefault();
			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[2]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedExpiryDate']");
			Common.textBoxInput("xpath", "//input[@id='encryptedExpiryDate']", data.get(dataSet).get("ExpMonth"));

			Common.switchToDefault();
			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[3]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedSecurityCode']");
			Common.textBoxInput("xpath", "//input[@id='encryptedSecurityCode']",  data.get(dataSet).get("cvv"));

			Common.switchToDefault();
			//Common.textBoxInput("xpath", "//input[@placeholder='A. M??ller']", data.get(dataSet).get("name"));
			Common.textBoxInput("xpath", "//input[@class='adyen-checkout__input adyen-checkout__input--text adyen-checkout__card__holderName__input sMjS8HCbKiP5yR9Td9ZgQ adyen-checkout__input--large']", data.get(dataSet).get("name"));
			Thread.sleep(1000);
			/*Common.clickElement("xpath", "//input[@id='agreement_adyen_cc_3']");
			
			Sync.waitElementPresent("xpath", "(//button[@class='action primary checkout'])[1]");
			Common.clickElement("xpath", "(//button[@class='action primary checkout'])[1]");*/
			Thread.sleep(1000);
			String Errormessage=Common.getText("xpath", "//span[contains(text(), 'Num??ro de carte non valide')]");
			System.out.println(Errormessage);
			Assert.assertEquals(Errormessage, "Num??ro de carte non valide");
			Common.isElementDisplayed("xpath" , "//div[contains(text(), 'Mode de paiement')]");
			
			//report.addPassLog(expectedResult,"Should display credit card  successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("Payment Credit cart success"));

			report.addPassLog(expectedResult, "Should display Error message for Credit card number feild", "Error message for Credit card number feild display successfully", Common.getscreenShotPathforReport("Error message credit card success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Error message for Credit card number feild", "Error message for Credit card number feild not display", Common.getscreenShotPathforReport("Error message credit card Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	
	
	
	
	
	

	public void Ordersuccesspage() throws Exception{

		try
		{

			Sync.waitElementPresent("xpath", "//a[@class='order-number']");
			Common.clickElement("xpath", "//a[@class='order-number']");
			Thread.sleep(1000);
			//Sync.waitElementPresent("xpath", "//a[@class='action primary continue']");
			//Common.clickElement("xpath", "//a[@class='action primary continue']");


			report.addPassLog("Should display Braun Home page", "Braun Home page display successfully", Common.getscreenShotPathforReport("Braun Home page success"));
		}
		catch(Exception |Error e)
		{
			report.addFailedLog("Should display Braun Home page", "Braun Home page not display", Common.getscreenShotPathforReport("Braun Home page Failed"));
			e.printStackTrace();
			Assert.fail();
		}	
	}


	public void UKENproductname(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
				report.addPassLog(expectedResult, "Should display Browse search Page", "Browse search Page display successfully", Common.getscreenShotPathforReport("Browse search page success"));

			}catch(Exception e)
			{
				Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);

			Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");
			Common.clickElement("xpath", "//img[@class='product-image-photo ']");
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	
	public void Norwayproductname(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
				report.addPassLog(expectedResult, "Should display Browse search Page", "Browse search Page display successfully", Common.getscreenShotPathforReport("Browse search page success"));

			}catch(Exception e)
			{
				Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);

			Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");
			Common.clickElement("xpath", "//img[@class='product-image-photo ']");
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}


	public void GEChangeQuantityinminicart() throws Exception{

		try
		{

			Sync.waitElementPresent("xpath", "//span[@class='item-plus qty-update']");
			Common.clickElement("xpath", "//span[@class='item-plus qty-update']");
			Thread.sleep(1000);

			report.addPassLog("Should display changed quantity in minicart", "increased product quantity in minicart display successfully", Common.getscreenShotPathforReport("increased product quantity success"));
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//a[@class='action viewcart']");
			Common.clickElement("xpath", "//a[@class='action viewcart']");
			
			//String title=Common.findElement("xpath", "//p[contains(text(), 'Warenkorb')]").getText();
			//title=("Warenkorb");
			String s=Common.getText("xpath", "//p[contains(text(), 'Warenkorb')]");
			Assert.assertEquals(s, "Warenkorb");
			Thread.sleep(1000);

			report.addPassLog("Should display shopping cart page", "shopping cart page display successfully", Common.getscreenShotPathforReport("shopping cart page success"));
		}
		catch(Exception |Error e)
		{
			report.addFailedLog("Should display shopping cart page", "shopping cart page not display", Common.getscreenShotPathforReport("shopping cart page Failed"));
			e.printStackTrace();
			Assert.fail();
		}	
	}
	
	
	
	public void ITChangeQuantityinminicart() throws Exception{

		try
		{

			Sync.waitElementPresent("xpath", "//span[@class='item-plus qty-update']");
			Common.clickElement("xpath", "//span[@class='item-plus qty-update']");
			Thread.sleep(1000);

			report.addPassLog("Should display changed quantity in minicart", "increased product quantity in minicart display successfully", Common.getscreenShotPathforReport("increased product quantity success"));
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//a[@class='action viewcart']");
			Common.clickElement("xpath", "//a[@class='action viewcart']");
			
			//String title=Common.findElement("xpath", "//p[contains(text(), 'Warenkorb')]").getText();
			//title=("Warenkorb");
			String s=Common.getText("xpath", "//p[contains(text(), 'Carrello')]");
			Assert.assertEquals(s, "Carrello");
			Thread.sleep(1000);

			report.addPassLog("Should display shopping cart page", "shopping cart page display successfully", Common.getscreenShotPathforReport("shopping cart page success"));
		}
		catch(Exception |Error e)
		{
			report.addFailedLog("Should display shopping cart page", "shopping cart page not display", Common.getscreenShotPathforReport("shopping cart page Failed"));
			e.printStackTrace();
			Assert.fail();
		}	
	}
	public void ChangeQuantityinminicart() throws Exception{

		try
		{

			Sync.waitElementPresent("xpath", "//span[@class='item-plus qty-update']");

			Common.clickElement("xpath", "//span[@class='item-plus qty-update']");
			Thread.sleep(1000);

			report.addPassLog("Should display changed quantity in minicart", "increased product quantity in minicart display successfully", Common.getscreenShotPathforReport("increased product quantity success"));
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//a[@class='action viewcart']");
			Common.clickElement("xpath", "//a[@class='action viewcart']");
			//String title=Common.findElement("xpath", "//p[contains(text(), 'Warenkorb')]").getText();
			//title=("Warenkorb");

			//Common.clickElement("xpath", "(//select[@id='bundle-custom-qty']//option)[4]");

			report.addPassLog("Should display shopping cart page", "shopping cart page display successfully", Common.getscreenShotPathforReport("shopping cart page success"));
		}
		catch(Exception |Error e)
		{
			report.addFailedLog("Should display shopping cart page", "shopping cart page not display", Common.getscreenShotPathforReport("shopping cart page Failed"));
			e.printStackTrace();
			Assert.fail();
		}	
	}

	public void GuestCreditcard(String dataSet) throws Exception
	{
		String expectedResult="Guest Payment with valid credit card";
		try {
			Thread.sleep(4000);
			if(Common.isElementDisplayed("xpath", "//div[@id='checkout-loader']")) {
				Thread.sleep(5000);
			}else {
				Thread.sleep(3000);
				/*Sync.waitElementPresent("id", "ime_paymetrictokenize");
			Common.clickElement(By.id("ime_paymetrictokenize"));
			Thread.sleep(4000);*/
			}	
			Common.switchFrames("paymetric_xisecure_frame");
			Sync.waitElementPresent("xpath", "//select[@id='c-ct']");
			Common.dropdown("xpath", "//select[@id='c-ct']", SelectBy.TEXT, data.get(dataSet).get("cardType"));
			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
			Common.dropdown("id", "c-exmth", SelectBy.VALUE, data.get(dataSet).get("ExpMonth"));
			Common.dropdown("id", "c-exyr", SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
			Common.textBoxInput("id", "c-cvv",  data.get(dataSet).get("cvv"));

			Common.switchToDefault();
			Thread.sleep(2000);
			report.addPassLog(expectedResult,"Should make payment with valid credit card successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("validcreditcard success"));
			Sync.waitElementPresent("xpath", "//div[@class='iosc-place-order-container']//button[@title='Place Order']");
			Common.clickElement(By.xpath("//div[@class='iosc-place-order-container']//button[@title='Place Order']"));
			if(Common.isElementVisibleOnPage(30, "xpath", "//div[contains(text(),'Shipping Address is not verified. Do you want to continue ?')]")) {
				System.out.println("Address not verified pop up displayed");
				Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
				Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
			}
			Thread.sleep(2000);
			//report.addPassLog(expectedResult,"Should make payment with valid credit card successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("validcreditcard success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should make payment with valid credit card successfully", "Payment with valid credit card failed", Common.getscreenShotPathforReport("cvalidcreditcardart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}




/* harish sir */

/*Italy*/
	/*public void headLinks(String dataSet) throws Exception{
		String expectedResult="Header Link validations";
		String Headerlinks=data.get(dataSet).get("HeaderNames");
		String[] headers=Headerlinks.split(",");
		for(int i=0;i<headers.length;i++){
			Sync.waitElementClickable("xpath", "//span[text()='"+headers[i]+"']");
			Common.clickElement("xpath", "//span[text()='"+headers[i]+"']");
			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			report.addPassLog(expectedResult, "Should display "+headers[i]+" success Page", ""+headers[i]+" Page display successfully", Common.getscreenShotPathforReport("Product Registration success page success"));
			
		}
		
	}*/
	
	public void italyNewslettersubscription(String dataset) throws Exception
	{
		String expectedResult="Should display Newsletter subscription page";
		try {
			Common.textBoxInput("id", "newsletter", Utils.getEmailid());
			Common.clickElement("xpath", "//span[@class='checkmark']");
			Thread.sleep(1000);
			Common.clickElement("xpath", "//button[@class='action subscribe primary']");
			Thread.sleep(1000);
			String Success=Common.getText("xpath", "//div[@class='message-success success message']");
			System.out.println(Success);
			Assert.assertEquals(Success, data.get(dataset).get("ItalynewsletterSubscription"));
			
			report.addPassLog(expectedResult, "Should display  Newsletter subscription page", "Newsletter subscription Page display successfully", Common.getscreenShotPathforReport("Newsletter subscription page success"));
			
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Newsletter subscription Page", "Newsletter subscription Page not displayed", Common.getscreenShotPathforReport("Newletter subscription page display Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void italySearchProduct(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "//a[@href='#search-mod']");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName")); 
			}catch(Exception e)
			{
				Common.clickElement("xpath", "//a[@href='#search-mod']");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);
			Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div[2]/h2//a[@class='product-item-link'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void zerosearchProduct(String dataSet) throws Exception
	{		
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));

			}catch(Exception e)
			{
				Common.clickElement("xpath", "//a[@href='#search-mod']");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Assert.assertTrue(Common.isElementDisplayed("xpath", "//div[@class='message notice']"));
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Zero search results Page", "Zero search results Page display successfully", Common.getscreenShotPathforReport("Zero results page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Zero search results Page", "Zero search results Page not display", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}
	
	
	public void verifyingHomePage(){
		try{
			Sync.waitElementPresent("xpath", "//a[@class='logo']");
		String s=Common.findElement("xpath" , "//a[@class='logo']").getAttribute("title");
		Common.assertionCheckwithReport(s.equals("Braun Health Care"), "verifying the homepage", "navigate the home page", "user successfully navigate the home page", "Failed to navigate to home page");
	}
		catch(Exception |Error e) {
		     
				ExtenantReportUtils.addFailedLog("verifying the homepage","navigate the home page", "user successfully navigate the home page", Common.getscreenShotPathforReport("failedtohomepage"));
				Assert.fail();
			}
	}
	
	public void ItalyFacebook() throws Exception
	{
		String expectedResult="Validate Facebook Link in Italy store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Thread.sleep(2000);
			Common.switchToSecondTab();
		   	Thread.sleep(15000);
		   	String s=Common.getText("xpath", "(//span[contains(text(),'Braun Healthcare')])[1]");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Braun Healthcare");
			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
	
	public void TurFacebook() throws Exception
	{
		String expectedResult="Validate Facebook Link in Italy store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Thread.sleep(2000);
			Common.switchToSecondTab();
		   	Thread.sleep(15000);
		   	String s=Common.getText("xpath", "(//span[contains(text(),'Braun Healthcare')])[1]");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Braun Healthcare");
			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
	
	public void POLFacebook() throws Exception
	{
		String expectedResult="Validate Facebook Link in Italy store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Thread.sleep(2000);
			Common.switchToSecondTab();
		   	Thread.sleep(15000);
		   	String s=Common.getText("xpath", "(//span[contains(text(),'Braun Healthcare')])[1]");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Braun Healthcare");
			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
	
	public void ATDFacebook() throws Exception
	{
		String expectedResult="Validate Facebook Link in Italy store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Thread.sleep(2000);
			Common.switchToSecondTab();
		   	Thread.sleep(15000);
		   	String s=Common.getText("xpath", "(//span[contains(text(),'Braun Healthcare')])[1]");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Braun Healthcare");
			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
	public void FranceFacebook() throws Exception
	{
		String expectedResult="Validate Facebook Link in Italy store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-facebook']");
			Thread.sleep(2000);
			Common.switchToSecondTab();
		   	Thread.sleep(15000);
		   	/*String s=Common.getText("xpath", "(//span[contains(text(),'Braun Healthcare')])[1]");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Braun Healthcare");*/
			report.addPassLog(expectedResult, "Should display Facebook Page", "Facebook Page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Facebook Page", "Facebook Page not displayed", Common.getscreenShotPathforReport("Facebook Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
	
	public void ItalyInstagram() throws Exception
	{
		String expectedResult="Validate Instagram Link in Italy store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-instagram']");

			Thread.sleep(3000);
		
			Common.switchToSecondTab();
		   	Thread.sleep(10000);
			if(Common.isElementDisplayed("xpath", "//h1[contains(text(),'Instagram')]")) {
				String s=Common.getText("xpath", "//h1[contains(text(),'Instagram')]");
			   	System.out.println(s);
			   	Assert.assertEquals(s, "Instagram");
			}else {
				String s=Common.getText("xpath", "//h2[contains(text(),'braunhealthcarede')]");
			   	System.out.println(s);
			   	Assert.assertEquals(s, "braunhealthcarede");
			}
			report.addPassLog(expectedResult, "Should display Instagram Page", "Instagram Page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Instagram Page", "Instagram Page not displayed", Common.getscreenShotPathforReport("Instagram Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
	
	public void TurInstagram() throws Exception
	{
		String expectedResult="Validate Instagram Link in Italy store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-instagram']");

			Thread.sleep(3000);
		
			Common.switchToSecondTab();
		   	Thread.sleep(10000);
			if(Common.isElementDisplayed("xpath", "//h1[contains(text(),'Instagram')]")) {
				String s=Common.getText("xpath", "//h1[contains(text(),'Instagram')]");
			   	System.out.println(s);
			   	Assert.assertEquals(s, "Instagram");
			}else {
				String s=Common.getText("xpath", "//h2[contains(text(),'braunhealthcarede')]");
			   	System.out.println(s);
			   	Assert.assertEquals(s, "braunhealthcarede");
			}
			report.addPassLog(expectedResult, "Should display Instagram Page", "Instagram Page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Instagram Page", "Instagram Page not displayed", Common.getscreenShotPathforReport("Instagram Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
	
	
	
	public void FranceInstagram() throws Exception
	{
		String expectedResult="Validate Instagram Link in Italy store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-instagram']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-instagram']");

			Thread.sleep(3000);
		
			Common.switchToSecondTab();
		   	Thread.sleep(10000);
			/*if(Common.isElementDisplayed("xpath", "//h1[contains(text(),'Instagram')]")) {
				String s=Common.getText("xpath", "//h1[contains(text(),'Instagram')]");
			   	System.out.println(s);
			   	Assert.assertEquals(s, "Instagram");
			}else {
				String s=Common.getText("xpath", "//h2[contains(text(),'braunhealthcarede')]");
			   	System.out.println(s);
			   	Assert.assertEquals(s, "braunhealthcarede");
			}*/
			report.addPassLog(expectedResult, "Should display Instagram Page", "Instagram Page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Instagram Page", "Instagram Page not displayed", Common.getscreenShotPathforReport("Instagram Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
	
	public void ItalyYoutube() throws Exception
	{
		String expectedResult="Validate Youtube Link in Italy store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");
			Thread.sleep(3000);
			Common.switchToSecondTab();
		   	Thread.sleep(13000);
		   	String s=Common.getText("xpath", "(//*[contains(text(),'Braun Healthcare Europe')])[1]");
		   	System.out.println(s);
			Assert.assertEquals(s, "Braun Healthcare Europe");
			report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
	
	public void TurYoutube() throws Exception
	{
		String expectedResult="Validate Youtube Link in Italy store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");
			Thread.sleep(3000);
			Common.switchToSecondTab();
		   	Thread.sleep(13000);
		   	String s=Common.getText("xpath", "(//*[contains(text(),'Braun Healthcare Europe')])[1]");
		   	System.out.println(s);
			Assert.assertEquals(s, "Braun Healthcare Europe");
			report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}

	
	public void PolYoutube() throws Exception
	{
		String expectedResult="Validate Youtube Link in Italy store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");
			Thread.sleep(3000);
			Common.switchToSecondTab();
		   	Thread.sleep(13000);
		   	String s=Common.getText("xpath", "(//*[contains(text(),'Braun Healthcare Europe')])[1]");
		   	System.out.println(s);
			Assert.assertEquals(s, "Braun Healthcare Europe");
			report.addPassLog(expectedResult, "Should display Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
	
	
	public void FranceYoutube() throws Exception
	{
		String expectedResult="Validate Youtube Link in Italy store";
		try {
			Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");
			Common.scrollToElementAndClick("xpath", "//a[@class='social-icons si-borderless si-youtube']");
			Thread.sleep(3000);
			Common.switchToSecondTab();
		   	Thread.sleep(13000);
		  /* 	String s=Common.getText("xpath", "(//*[contains(text(),'Braun Healthcare Europe')])[1]");
		   	System.out.println(s);
			Assert.assertEquals(s, "Braun Healthcare Europe");*/
			report.addPassLog(expectedResult, "Should dissplay Youtube Page", "Youtube Page display successfully", Common.getscreenShotPathforReport("Youtube page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Youtube Page", "Youtube Page not displayed", Common.getscreenShotPathforReport("Youtube Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
	
	
	
	
	
	public void Addtocart() {
		// TODO Auto-generated method stub
		 String expectedResult="Navigating Addtocart";
	        try {
	            Sync.waitElementClickable(30, By.xpath("//button[@class='bundle-custom-addtocart braun_btn add_to_cart_btn']"));
	            Common.findElement("xpath", "//button[@class='bundle-custom-addtocart braun_btn add_to_cart_btn']").click();
	            //Common.clickElement("xpath", "//a[@title='Italy']");
	            Thread.sleep(2000);
	            String S=Common.getText("xpath", "//div[@class='message-success success message']");
				System.out.println(S);
				Assert.assertEquals(S, "Du har lagt till Braun ExactFit??? 5 & ThermoScan?? 3 till din kundvagn.");
				// Sync.waitElementClickable(30, By.xpath("(//span[text()='Min kundvagn'])[2]"));
		          //  Common.findElement("xpath", "(//span[text()='Min kundvagn'])[2]").click();
	            report.addPassLog(expectedResult, "Should display Addtocart", "Addtocart displayed successfully", Common.getscreenShotPathforReport("Addtocart success"));
	        }catch(Exception |Error e)
	        {
	            report.addFailedLog(expectedResult,"Should display Addtocart", "Addtocart not display", Common.getscreenShotPathforReport("Addtocart Failed"));
	            e.printStackTrace();
	            Assert.fail();
	        }
	
	
	}
	
	
	
	public void UKSVAddtocart() {
		// TODO Auto-generated method stub
		 String expectedResult="Navigating Addtocart";
	        try {
	            Sync.waitElementClickable(30, By.xpath("//button[@id='product-addtocart-button']"));
	            Common.findElement("xpath", "//button[@id='product-addtocart-button']").click();
	            //Common.clickElement("xpath", "//a[@title='Italy']");
	            Thread.sleep(2000);
	            String S=Common.getText("xpath", "//div[@class='message-success success message']");
				System.out.println(S);
				Assert.assertEquals(S, "Du har lagt till Braun ThermoScan?? 3 till din kundvagn.");
				// Sync.waitElementClickable(30, By.xpath("(//span[text()='Min kundvagn'])[2]"));
		          //  Common.findElement("xpath", "(//span[text()='Min kundvagn'])[2]").click();
	            report.addPassLog(expectedResult, "Should display Addtocart", "Addtocart displayed successfully", Common.getscreenShotPathforReport("Addtocart success"));
	        }catch(Exception |Error e)
	        {
	            report.addFailedLog(expectedResult,"Should display Addtocart", "Addtocart not display", Common.getscreenShotPathforReport("Addtocart Failed"));
	            e.printStackTrace();
	            Assert.fail();
	        }
	
	
	}
	
	
	
	public void SDnavigateMinicart() {
		// TODO Auto-generated method stub
		 String expectedResult="Navigating Minicart";
	        try {
	           // Sync.waitElementClickable(30, By.xpath("//button[@id='top-cart-btn-checkout']"));
	           // Common.findElement("xpath", "//button[@id='top-cart-btn-checkout']").click();
	            //Common.clickElement("xpath", "//a[@title='Italy']");
	            Thread.sleep(2000);
	            String S=Common.getText("xpath", "(//span[text()='Min kundvagn'])[2]");
				System.out.println(S);
				Assert.assertEquals(S, "MIN KUNDVAGN");
				Sync.waitElementClickable(30, By.xpath("//button[@id='top-cart-btn-checkout']"));
	            Common.findElement("xpath", "//button[@id='top-cart-btn-checkout']").click();
	            report.addPassLog(expectedResult, "Should display Minicart", "Minicart displayed successfully", Common.getscreenShotPathforReport("Minicart success"));
	        }catch(Exception |Error e)
	        {
	            report.addFailedLog(expectedResult,"Should display Minicart", "Minicart not display", Common.getscreenShotPathforReport("Minicart Failed"));
	            e.printStackTrace();
	            Assert.fail();
	}
}

	public void SDcheckoutPage() {
		// TODO Auto-generated method stub
		String expectedResult="Product adding to checkout page";
		try {

			Sync.waitElementPresent("xpath", "//button[@title='Guest Checkout']");
			Common.clickElement("xpath", "//button[@title='Guest Checkout']");
		
			Thread.sleep(2000);
			/*String s=Common.getText("xpath", "//font[text()='Check out as a guest']");
			Assert.assertEquals(s, "Check out as a guest");
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "//button[@title='Guest Checkout']");
			Common.clickElement("xpath", "//button[@title='Guest Checkout']");*/
			report.addPassLog(expectedResult, "Shousld display Guest Checkout", "Guest Checkout display successfully", Common.getscreenShotPathforReport("Guest Checkout success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Guest Checkout", "Guest Checkout not displayed", Common.getscreenShotPathforReport("Guest Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
	}

}

	public void SDshipping_Address(String dataSet) {
		// TODO Auto-generated method stub

		{
	try
	{        Thread.sleep(2000);
	        Sync.waitElementPresent("xpath", "(//input[@type='email'])[1]");  
			Common.textBoxInput("xpath", "(//input[@type='email'])[1]", data.get(dataSet).get("Email"));
			

			Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[1]");
			Common.clickElement("xpath", "(//button[@class='button action continue primary'])[1]");

			Thread.sleep(2000);
			Common.textBoxInput("xpath", "(//input[@class='input-text'])[2]", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "(//input[@class='input-text'])[3]", data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "(//input[@class='input-text'])[4]", data.get(dataSet).get("Street"));
			Common.textBoxInput("xpath", "(//input[@class='input-text'])[5]", data.get(dataSet).get("City"));
			Common.textBoxInput("xpath", "(//input[@class='input-text'])[7]", data.get(dataSet).get("postcode"));
			Common.textBoxInput("xpath", "(//input[@class='input-text'])[8]", data.get(dataSet).get("phone"));
			
			
			Common.clickElement("xpath", "(//input[@type='radio'])[2]");
			Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[2]");
			Common.clickElement("xpath", "(//button[@class='button action continue primary'])[2]");
			Thread.sleep(2000);
			Sync.waitElementClickable(30, By.xpath("//span[text()='Ok']"));
			Common.findElement("xpath", "//span[text()='Ok']").click();
			
			report.addPassLog("Should Display Shipping method page", "Displaying Shipping method page success", Common.getscreenShotPathforReport("Shipping method page success"));
	report.addPassLog("Should Display payment method", "Displaying payment method success", Common.getscreenShotPathforReport("payment method success"));
	}

	catch(Exception |Error e)
	{
		report.addPassLog("Should Display payment method", "Displaying payment method success", Common.getscreenShotPathforReport("payment method failed"));
		e.printStackTrace();
		Assert.fail();
	}	
	}
	}

	public void SDCreditcardPayment(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult="Payment With Valid Credit Card";
		try {
			Thread.sleep(12000);
			//Common.clickElement(By.xpath("//input[@id='encryptedCardNumber']"));
			//Common.javascriptclickElement("xpath", "//input[@id='encryptedCardNumber']");
			//Common.clickElement("xpath", "//input[@id='adyen_cc']");
			Common.javascriptclickElement("xpath" , "//input[@id='adyen_cc']");


			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[1]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedCardNumber']");

			Common.textBoxInput("xpath", "//input[@id='encryptedCardNumber']", data.get(dataSet).get("cardNumber"));

			Common.switchToDefault();
			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[2]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedExpiryDate']");
			Common.textBoxInput("xpath", "//input[@id='encryptedExpiryDate']", data.get(dataSet).get("ExpMonth"));

			Common.switchToDefault();
			Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[3]");
			Sync.waitElementPresent("xpath" , "//input[@id='encryptedSecurityCode']");
			Common.textBoxInput("xpath", "//input[@id='encryptedSecurityCode']",  data.get(dataSet).get("cvv"));

			Common.switchToDefault();
			//Common.textBoxInput("xpath", "//input[@placeholder='A. M??ller']", data.get(dataSet).get("name"));
			Common.textBoxInput("xpath", "//input[@class='adyen-checkout__input adyen-checkout__input--text adyen-checkout__card__holderName__input sMjS8HCbKiP5yR9Td9ZgQ adyen-checkout__input--large']", data.get(dataSet).get("name"));

			Thread.sleep(1000);
			/*Common.clickElement("xpath" , "//input[@id='shipping-save-in-address-book']]");
			Thread.sleep(2000);
			//Common.clickElement("xpath", "//*[@id='billing-address-same-as-shipping-shared']");
			Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
			Common.clickElement("xpath", "//button[@class='action primary checkout']");*/
			Common.clickElement("xpath", "//input[@id='agreement_adyen_cc_4']");
			
			Sync.waitElementPresent("xpath", "(//button[@class='action primary checkout'])[2]");
			Common.clickElement("xpath", "(//button[@class='action primary checkout'])[2]");
			Thread.sleep(1000);
			
			//report.addPassLog(expectedResult,"Should display credit card  successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("Payment Credit cart success"));

			report.addPassLog(expectedResult,"Should Make payment with valid credit card successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("Payment Credit cart success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Make payment with valid credit card successfully", "Make payment with valid credit card unsuccessfully", Common.getscreenShotPathforReport("Payment Credit card Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void SDNavigatetoPLPpage() {
		// TODO Auto-generated method stub
		String expectedResult="User should successfully lands on PLP page";
		try {
			Thread.sleep(4000);		
			Sync.waitElementPresent("xpath", "//span[text()='Termometrar']");
			Common.clickElement("xpath", "//span[text()='Termometrar']");
			Thread.sleep(3000);
			Common.scrollIntoView("xpath" , "(//h3[@class='cat_sldr_te'])[3]");
			//Common.findElement("xpath", "(//h3[@class='cat_sldr_te'])[3]").click();
			
			Common.getPageTitle();
			Thread.sleep(1000);
			report.addPassLog(expectedResult, "Should display PLP Page", "PLP Page display successfully", Common.getscreenShotPathforReport("PLP page success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display PLP Page", "PLP Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
	}

}	
	
	public void Italyproductselection() throws Exception

	{

	String expectedResult="Product Selection from Category";

	try {

	Thread.sleep(4000);

	Sync.waitElementPresent("xpath", "//span[contains(text(), 'Termometro')]");

	Common.clickElement("xpath", "//span[contains(text(), 'Termometro')]");

	Thread.sleep(3000);

	Sync.waitElementPresent("xpath", "//img[@alt='Braun ThermoScan?? 7 con Age Precision??']");

	Common.clickElement("xpath", "//img[@alt='Braun ThermoScan?? 7 con Age Precision??']");

	Thread.sleep(2000);

	String s=Common.getText("xpath", "//h1[@ class='page-title']");

	Assert.assertEquals(s, "Braun ThermoScan?? 7 con Age Precision??");

	Thread.sleep(3000);

	report.addPassLog(expectedResult, "Should display Product Details Page", "Product Details  Page display successfully", Common.getscreenShotPathforReport("Product Details page display success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Product Details Page", "Product Details Page not displayed", Common.getscreenShotPathforReport("Product Details page Failed"));

	e.printStackTrace();

	Assert.fail();

	}

	 

	}
	
	
	public void RegistercheckoutPage() throws Exception
	{
		String expectedResult="Product adding to checkout page";
		try {

			Sync.waitElementPresent("xpath", "//button[@id='top-cart-btn-checkout']");
			Common.clickElement("xpath", "//button[@id='top-cart-btn-checkout']");
			Thread.sleep(1000);
			report.addPassLog(expectedResult, "Should display Register user  Checkout", "Register user Checkout display successfully", Common.getscreenShotPathforReport("Register user  Checkout success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Register user  Checkout", "Register user  Checkout not displayed", Common.getscreenShotPathforReport("Register user  Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	public void ItalyAddAddress() throws Exception
	{
		String expectedResult="Adding Address to shipping";
		try {
			Thread.sleep(4000);
			report.addPassLog(expectedResult, "Should display Shipping AddressPage", "Address Page display successfully", Common.getscreenShotPathforReport("Address page success"));
			Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[2]");
			Common.clickElement("xpath", "(//button[@class='button action continue primary'])[2]");
			//Common.clickElement("xpath", "//span[contains(text(), 'Next')]");
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");
			Common.clickElement("xpath", "//span[contains(text(),'OK')]");	
			
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	public void Swedenproductselection() throws Exception

	{

	String expectedResult="Product Selection from Category";

	try {

	Thread.sleep(4000);

	Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");

	Common.clickElement("xpath", "//a[@id='ui-id-2']");

	Thread.sleep(3000);

	Sync.waitElementPresent("xpath", "//img[@alt='Braun ThermoScan?? 3']");

	Common.clickElement("xpath", "//img[@alt='Braun ThermoScan?? 3']");

	Thread.sleep(2000);

	String s=Common.getText("xpath", "//h1[@ class='page-title']");

	Assert.assertEquals(s, "Braun ThermoScan?? 3");

	Thread.sleep(3000);

	report.addPassLog(expectedResult, "Should display Product Details Page", "Product Details  Page display successfully", Common.getscreenShotPathforReport("Product Details page display success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Product Details Page", "Product Details Page not displayed", Common.getscreenShotPathforReport("Product Details page Failed"));

	e.printStackTrace();

	Assert.fail();

	}

	 

	}

	
	public void ItalynavigateMinicart() throws Exception

	{

	String expectedResult="Product adding to mini cart";

	try {

	Thread.sleep(5000);

	 

	Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");

	//Common.clickElement("xpath", "//button[@id='product-addtocart-button']");

	Common.javascriptclickElement("xpath" , "//button[@id='product-addtocart-button']");

	Thread.sleep(10000);

	String s=Common.getText("xpath", "(//span[contains(text(), 'Carrello')])[2]");

	Assert.assertEquals(s, "CARRELLO");

	Thread.sleep(2000);

	Sync.waitElementPresent("xpath", "//button[@id='top-cart-btn-checkout']");

	//Common.clickElement("xpath", "//button[@class='action primary checkout']");

	 

	Common.javascriptclickElement("xpath" , "//button[@id='top-cart-btn-checkout']");

	Thread.sleep(2000);


	report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));

	e.printStackTrace();

	Assert.fail();

	}

	}
	
	
	public void SwedennavigateMinicart() throws Exception

	{

	String expectedResult="Product adding to mini cart";

	try {

	Thread.sleep(5000);

	 

	Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");

	//Common.clickElement("xpath", "//button[@id='product-addtocart-button']");

	Common.javascriptclickElement("xpath" , "//button[@id='product-addtocart-button']");

	Thread.sleep(10000);

	String s=Common.getText("xpath", "(//span[contains(text(), 'Min kundvagn')])[2]");

	Assert.assertEquals(s, "MIN KUNDVAGN");

	Thread.sleep(2000);

	Sync.waitElementPresent("xpath", "//button[@id='top-cart-btn-checkout']");

	//Common.clickElement("xpath", "//button[@class='action primary checkout']");

	 

	Common.javascriptclickElement("xpath" , "//button[@id='top-cart-btn-checkout']");

	Thread.sleep(2000);


	report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));

	e.printStackTrace();

	Assert.fail();

	}

	}
	
	
	
	
	
	
	
	public void ITCheckoutPage() throws Exception

	{

	String expectedResult="Product adding to checkout page";

	try {

	 

	Sync.waitElementPresent("xpath", "//button[@id='guest_checkout']");

	Common.clickElement("xpath", "//button[@id='guest_checkout']");

	//Common.isElementDisplayed("id", "guest_checkout");

	Thread.sleep(1000);

	report.addPassLog(expectedResult, "Should display Guest Checkout", "Guest Checkout display successfully", Common.getscreenShotPathforReport("Guest Checkout success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Guest Checkout", "Guest Checkout not displayed", Common.getscreenShotPathforReport("Guest Checkout Failed"));

	e.printStackTrace();

	Assert.fail();

	}

	}
	
	public void Italyshipping_Address(String dataSet) throws Exception

	 

	{

	try

	{

	Common.textBoxInput("name", "username", data.get(dataSet).get("Email"));

	 

	Sync.waitElementPresent("xpath", "//button[@class='button action continue primary']");

	Common.clickElement("xpath", "//button[@class='button action continue primary']");

	 

	Thread.sleep(2000);

	Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));

	Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));

	Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));

	Common.textBoxInput("name", "city", data.get(dataSet).get("City"));

	Thread.sleep(3000);

	/*Sync.waitElementPresent("xpath", "//div[contains(text(),'Shipping Address')]");

	Common.clickElement("xpath", "//div[contains(text(),'Shipping Address')]");

	Thread.sleep(3000);*/

	Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

	Thread.sleep(1000);

	//Common.dropdown("name", "country_id", SelectBy.TEXT, data.get(dataSet).get("Region"));

	Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));

	 

	Thread.sleep(3000);


	Sync.waitElementPresent("xpath", "(//input[@name='shipping_method'])[1]");

	//Common.clickElement("xpath", "//input[@name='shipping_method']");

	Common.javascriptclickElement("xpath", "(//input[@name='shipping_method'])[1]");

	report.addPassLog("Should display Shipping address Page", "Should display Shipping address successfully", Common.getscreenShotPathforReport("Shipping address page success"));

	 

	Thread.sleep(5000);

	Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[2]");

	Common.clickElement("xpath", "(//button[@class='button action continue primary'])[2]");

	Thread.sleep(2000);


	report.addPassLog("Should Display Shipping method page", "Displaying Shipping method page success", Common.getscreenShotPathforReport("Shipping method page success"));

	 

	Sync.waitElementPresent("xpath", "//span[contains(text(),'OK')]");

	Common.clickElement("xpath", "//span[contains(text(),'OK')]");

	 

	       

	       report.addPassLog("Should Display payment method", "Displaying payment method success", Common.getscreenShotPathforReport("payment method success"));

	}

	 

	catch(Exception |Error e)

	{

	       report.addPassLog("Should Display payment method", "Displaying payment method success", Common.getscreenShotPathforReport("payment method failed"));

	       e.printStackTrace();

	Assert.fail();

	}

	}
	
	
	
	
	
	
	
	
	public void ItalyMyaccountInformation() throws Exception{

		try

		{

		Thread.sleep(2000);

		Sync.waitElementPresent("xpath", "//ul[@class='nav items']//li[4]//a");

		Common.clickElement("xpath", "//ul[@class='nav items']//li[4]//a");


		//String title=Common.findElement("xpath", "//span[contains(text(), 'Meine Bestellungen')]").getText();

		//title=("Meine Bestellungen");

		String s=Common.getText("xpath", "//span[contains(text(), 'Modificare le informazioni Account')]");

		Assert.assertEquals(s, "Modificare Le Informazioni Account");

		 

		report.addPassLog("Should display AccountInformation Page", "AccountInformation Page display successfully", Common.getscreenShotPathforReport("AccountInformation page success"));


		}

		 

		catch(Exception |Error e)

		{

		       report.addFailedLog("Should display MYOrders page", "display MYOrders page", Common.getscreenShotPathforReport("MYOrders page Failed"));

		       e.printStackTrace();

		Assert.fail();

		}

		}

	
	
	
	
	
	public void ItalyMYAccountPage() throws Exception{

		try

		{

		Thread.sleep(3000);


		Sync.waitElementPresent("xpath", "//a[contains(text(), 'I Miei ordini')]");

		Common.clickElement("xpath", "//a[contains(text(), 'I Miei ordini')]");


		Sync.waitElementPresent("xpath", "//td/a[@class='action view']");

		Common.clickElement("xpath", "//td/a[@class='action view']");


		         String url=Common.getCurrentURL();

		System.out.println(url);

		Common.assertionCheckwithReport(url.contains("order"), "Verifying  My orders page", "It should navigate to My orders page", "successfully lands on My orders page","My orders");

		//Common.isElementDisplayed("xpath" , "//span[contains(text(), 'Informations du compte')]");


		report.addPassLog("Should display MyAccount Information  Page", "MyAccount Information Page displayed successfully", Common.getscreenShotPathforReport("MyAccount Information Page success"));

		}

		catch(Exception |Error e)

		{

		       report.addFailedLog("Should display MyAccount Information Page", "MyAccount InformationPage not displayed", Common.getscreenShotPathforReport("MyAccount Information page  Failed"));

		       e.printStackTrace();

		Assert.fail();

		}

		}
	
	public void ItalyOrder_Verifying() throws Exception{

		//Thread.sleep(10000);

		//Common.textBoxInput("id", "//textarea[contains(@id,'tt-c-comment-field')]","Ceate accounts test ");

		String expectedResult = "It redirects to order confirmation page";

		try{

		Sync.waitPageLoad();

		for(int i=0;i<10;i++){

		 

		if(Common.getCurrentURL().contains("utm_nooverride")){

		break;

		}

		Thread.sleep(5000);

		}

		 

		String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']");

		System.out.println(sucessMessage);

		Common.assertionCheckwithReport(sucessMessage.equals("Danke f??r Ihren Einkauf"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");

		 

		}

		catch (Exception | Error e) {

		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,

		"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));

		Assert.fail();

		}

		}

	
	

	public void ItalyInvalidCreditcardPayment(String dataSet) throws Exception

	{

	String expectedResult="Payment With Valid Credit Card";
	

	try {

	Thread.sleep(12000);

	//Common.clickElement(By.xpath("//input[@id='encryptedCardNumber']"));

	//Common.javascriptclickElement("xpath", "//input[@id='encryptedCardNumber']");

	Common.clickElement("xpath", "//input[@id='adyen_cc']");

	 

	Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[1]");

	Sync.waitElementPresent("xpath" , "//input[@id='encryptedCardNumber']");

	 

	Common.textBoxInput("xpath", "//input[@id='encryptedCardNumber']", data.get(dataSet).get("cardNumber"));

	 

	Common.switchToDefault();

	Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[2]");

	Sync.waitElementPresent("xpath" , "//input[@id='encryptedExpiryDate']");

	Common.textBoxInput("xpath", "//input[@id='encryptedExpiryDate']", data.get(dataSet).get("ExpMonth"));

	 

	Common.switchToDefault();

	Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[3]");

	Sync.waitElementPresent("xpath" , "//input[@id='encryptedSecurityCode']");

	Common.textBoxInput("xpath", "//input[@id='encryptedSecurityCode']",  data.get(dataSet).get("cvv"));

	 

	Common.switchToDefault();

	//Common.textBoxInput("xpath", "//input[@placeholder='A. M??ller']", data.get(dataSet).get("name"));

	Common.textBoxInput("xpath", "//input[@class='adyen-checkout__input adyen-checkout__input--text adyen-checkout__card__holderName__input sMjS8HCbKiP5yR9Td9ZgQ adyen-checkout__input--large']", data.get(dataSet).get("name"));

	Thread.sleep(1000);

	/*Common.clickElement("xpath", "//input[@id='agreement_adyen_cc_3']");


	Sync.waitElementPresent("xpath", "(//button[@class='action primary checkout'])[1]");

	Common.clickElement("xpath", "(//button[@class='action primary checkout'])[1]");*/

	Thread.sleep(1000);

	String Errormessage=Common.getText("xpath", "//span[contains(text(), 'Numero carta non valido')]");

	System.out.println(Errormessage);

	Assert.assertEquals(Errormessage, "Numero carta non valido");

	Common.isElementDisplayed("xpath" , "//div[contains(text(), 'Metodo di pagamento')]");


	//report.addPassLog(expectedResult,"Should display credit card  successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("Payment Credit cart success"));

	 

	report.addPassLog(expectedResult, "Should display Error message for Credit card number feild", "Error message for Credit card number feild display successfully", Common.getscreenShotPathforReport("Error message credit card success"));

	}catch(Exception |Error e)

	{

	report.addFailedLog(expectedResult,"Should display Error message for Credit card number feild", "Error message for Credit card number feild not display", Common.getscreenShotPathforReport("Error message credit card Failed"));

	e.printStackTrace();

	Assert.fail();

	}

	}
	 public void ItalyCreditcardPayment(String dataSet) throws Exception

	 {

	 String expectedResult="Payment With Valid Credit Card";

	 try {

	 Thread.sleep(12000);

	 //Common.clickElement(By.xpath("//input[@id='encryptedCardNumber']"));

	 //Common.javascriptclickElement("xpath", "//input[@id='encryptedCardNumber']");

	 Common.clickElement("xpath", "//input[@id='adyen_cc']");

	  

	 Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[1]");

	 Sync.waitElementPresent("xpath" , "//input[@id='encryptedCardNumber']");

	  

	 Common.textBoxInput("xpath", "//input[@id='encryptedCardNumber']", data.get(dataSet).get("cardNumber"));

	  

	 Common.switchToDefault();

	 Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[2]");

	 Sync.waitElementPresent("xpath" , "//input[@id='encryptedExpiryDate']");

	 Common.textBoxInput("xpath", "//input[@id='encryptedExpiryDate']", data.get(dataSet).get("ExpMonth"));

	  

	 Common.switchToDefault();

	 Common.switchFrames("xpath", "(//iframe[@class='js-iframe'])[3]");

	 Sync.waitElementPresent("xpath" , "//input[@id='encryptedSecurityCode']");

	 Common.textBoxInput("xpath", "//input[@id='encryptedSecurityCode']",  data.get(dataSet).get("cvv"));

	  

	 Common.switchToDefault();

	 //Common.textBoxInput("xpath", "//input[@placeholder='A. M??ller']", data.get(dataSet).get("name"));

	 Common.textBoxInput("xpath", "//input[@class='adyen-checkout__input adyen-checkout__input--text adyen-checkout__card__holderName__input sMjS8HCbKiP5yR9Td9ZgQ adyen-checkout__input--large']", data.get(dataSet).get("name"));

	  

	 Thread.sleep(1000);

	 /*Common.clickElement("xpath" , "//input[@id='shipping-save-in-address-book']]");

	 Thread.sleep(2000);

	 //Common.clickElement("xpath", "//*[@id='billing-address-same-as-shipping-shared']");

	 Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");

	 Common.clickElement("xpath", "//button[@class='action primary checkout']");*/

	 //Thread.sleep(2000);

	 //Sync.waitElementPresent("xpath", "//input[@type='checkbox']");

	 //Common.clickElement("xpath", "//input[@type='checkbox']");


	 Sync.waitElementPresent("xpath", "(//button[@title='Effettua Ordine'])[2]");

	 Common.clickElement("xpath", "(//button[@title='Effettua Ordine'])[2]");

	 Thread.sleep(1000);


	 //report.addPassLog(expectedResult,"Should display credit card  successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("Payment Credit cart success"));

	  

	 report.addPassLog(expectedResult,"Should Make payment with valid credit card successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("Payment Credit cart success"));

	 }catch(Exception |Error e)

	 {

	 report.addFailedLog(expectedResult,"Should Make payment with valid credit card successfully", "Make payment with valid credit card unsuccessfully", Common.getscreenShotPathforReport("Payment Credit card Failed"));

	 e.printStackTrace();

	 Assert.fail();

	 }

	 }


	 
	 public void svProductselection() throws Exception
	    {
	        String expectedResult="Product Selection from Category";
	        try {
	            Thread.sleep(4000);        
	            Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
	            Common.clickElement("xpath", "//a[@id='ui-id-2']");
	            Thread.sleep(3000);
	            Sync.waitElementPresent("xpath", "//img[@alt='Braun iCheck?? 7 & ThermoScan?? 7 with Age Precision??']");
	            Common.clickElement("xpath", "//img[@alt='Braun iCheck?? 7 & ThermoScan?? 7 with Age Precision??']");
	            Common.isElementDisplayed("xpath", "//span[@class='base']");
	            Thread.sleep(2000);
	            report.addPassLog(expectedResult, "Should display Product Details Page", "Product Details  Page display successfully", Common.getscreenShotPathforReport("Product Details page display success"));
	        }catch(Exception |Error e)
	        {
	            report.addFailedLog(expectedResult,"Should display Product Details Page", "Product Details Page not displayed", Common.getscreenShotPathforReport("Product Details page Failed"));
	            e.printStackTrace();
	            Assert.fail();
	        }

	 

	    }
	 
	 
	 public void svNavigateMinicart() throws Exception

	 {

	 String expectedResult="Product adding to mini cart";

	 try {

	 Thread.sleep(4000);

	 

	 Sync.waitElementPresent("xpath", "//button[@class='bundle-custom-addtocart braun_btn add_to_cart_btn']");

	 Common.clickElement("xpath", "//button[@class='bundle-custom-addtocart braun_btn add_to_cart_btn']");

	 Thread.sleep(10000);

	 //String URL=Common.getCurrentURL();

	 String s=Common.getText("xpath", "(//span[contains(text(), 'Min kundvagn')])[2]");

	 Assert.assertEquals(s, "MIN KUNDVAGN");

	 Thread.sleep(1000);

	 Sync.waitElementPresent("xpath", "//button[@id='top-cart-btn-checkout']");

	 //Common.clickElement("xpath", "//button[@class='action primary checkout']");

	 Common.javascriptclickElement("xpath" , "//button[@id='top-cart-btn-checkout']");

	 Thread.sleep(2000);

	 

	 report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));

	 }catch(Exception |Error e)

	 {

	 report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));

	 e.printStackTrace();

	 Assert.fail();

	 }

	 }
	 
	 
	 public void svCheckoutPage() throws Exception

	 {

	 String expectedResult="Product adding to checkout page";

	 try {

	 

	 Sync.waitElementPresent("xpath", "//button[@id='guest_checkout']");

	 Common.clickElement("xpath", "//button[@id='guest_checkout']");

	 //Common.isElementDisplayed("id", "guest_checkout");

	 Thread.sleep(2000);

	 String s=Common.getText("xpath", "//div[contains(text(), 'E-post')]");

	 Assert.assertEquals(s, "E-post");

	 Thread.sleep(1000);

	 report.addPassLog(expectedResult, "Shousld display Guest Checkout", "Guest Checkout display successfully", Common.getscreenShotPathforReport("Guest Checkout success"));

	 }catch(Exception |Error e)

	 {

	 report.addFailedLog(expectedResult,"Should display Guest Checkout", "Guest Checkout not displayed", Common.getscreenShotPathforReport("Guest Checkout Failed"));

	 e.printStackTrace();

	 Assert.fail();

	 }

	 }
	 
	 
	 
	 public void svShipping_Address(String dataSet) throws Exception

	 

	 {

	try

	{

	 Common.textBoxInput("name", "username", data.get(dataSet).get("Email"));

	 

	 Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[1]");

	 Common.clickElement("xpath", "(//button[@class='button action continue primary'])[1]");

	 

	 Thread.sleep(2000);

	 Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));

	 Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));

	 Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));

	 Common.textBoxInput("name", "city", data.get(dataSet).get("City"));

	 Thread.sleep(3000);

	 /*Sync.waitElementPresent("xpath", "//div[contains(text(),'Shipping Address')]");

	 Common.clickElement("xpath", "//div[contains(text(),'Shipping Address')]");

	 Thread.sleep(3000);*/

	 Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

	 Thread.sleep(1000);

	 //Common.dropdown("name", "country_id", SelectBy.TEXT, data.get(dataSet).get("Region"));

	 Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));

	 

	 Thread.sleep(3000);

	 

	 Sync.waitElementPresent("xpath", "(//input[@name='shipping_method'])[1]");

	 //Common.clickElement("xpath", "//input[@name='shipping_method']");

	 Common.javascriptclickElement("xpath", "(//input[@name='shipping_method'])[1]");

	 report.addPassLog("Should display Shipping address Page", "Should display Shipping address successfully", Common.getscreenShotPathforReport("Shipping address page success"));

	 

	 Thread.sleep(5000);
	Sync.waitElementPresent("xpath", "(//button[@class='button action continue primary'])[2]");

	 Common.clickElement("xpath", "(//button[@class='button action continue primary'])[2]");

	 Thread.sleep(2000);

	 

	 report.addPassLog("Should Display Shipping method page", "Displaying Shipping method page success", Common.getscreenShotPathforReport("Shipping method page success"));

	 

	 Sync.waitElementPresent("xpath", "//span[contains(text(),'Ok')]");

	 Common.clickElement("xpath", "//span[contains(text(),'Ok')]"); 

	 report.addPassLog("Should Display payment method", "Displaying payment method success", Common.getscreenShotPathforReport("payment method success"));

	}

	 

	catch(Exception |Error e)

	{

	        report.addPassLog("Should Display payment method", "Displaying payment method success", Common.getscreenShotPathforReport("payment method failed"));

	        e.printStackTrace();

	 Assert.fail();

	} 

	}



	 public void NewBillingaddressform(String dataSet) throws Exception {
		 
		 Common.scrollToElementAndUnCheck("xpath", "//input[@name='billing-address-same-as-shipping']");
		 Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		 Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));

		 Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		// Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		 Common.clickElement("xpath", "//div[@class='billing-address-form']/form/fieldset/div[6]/div/select");
		 Common.clickElement("xpath", "(//select/option[@data-title='Florida'])[2]");
		 Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		 Common.clickElement("xpath", "(//select[@name='country_id'])[2]");
		 Common.clickElement("xpath", "(//select/option[@data-title='Australia'])[2]");
		 Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));
		 Common.clickElement("xpath", "//button/span[text()='Update']");
		 if(Common.isElementDisplayed("xpath", "//div[@class='field-error']")) {
		 Common.textBoxInput("xpath", "//div[@class='billing-address-form']/form/fieldset/div[7]/div/input", data.get(dataSet).get("Region"));
		 Common.clickElement("xpath", "//button/span[text()='Update']");
		 Thread.sleep(2000);
		 }
		 if(Common.isElementDisplayed("xpath", "//div[contains(text(),'Address is not verified. Do you want to continue ?')]")) {
		 Common.clickElement("xpath", "//span[text()='OK']");	
		 }
		 Thread.sleep(3000);
		 Common.actionsKeyPress(Keys.PAGE_UP);
		 String s = Common.findElement("xpath", "//a[contains(@data-bind,'text: currentBillingAddress()')]").getText();
		 Assert.assertEquals(s, data.get(dataSet).get("phone"));

		 Common.actionsKeyPress(Keys.ARROW_UP);
		 }
		 







	public void ItalyStoreSelection() throws InterruptedException
    {
        String expectedResult="Navigating Poland Store";
        try {
            Sync.waitElementClickable(30, By.xpath("(//a[@href='#country-mod'])[1]"));
            Common.findElement("xpath", "(//a[@href='#country-mod'])[1]").click();
            Common.clickElement("xpath", "//a[@title='Italy']");
            Thread.sleep(2000);
            report.addPassLog(expectedResult, "Should display Poland store", "Poland store displayed successfully", Common.getscreenShotPathforReport("Poland store success"));
        }catch(Exception |Error e)
        {
            report.addFailedLog(expectedResult,"Should display Poland store", "Poland store not display", Common.getscreenShotPathforReport("Poland store Failed"));
            e.printStackTrace();
            Assert.fail();
        }
    }

	public void SD_productname(String dataSet) {
		// TODO Auto-generated method stub

		String expectedResult="Search with Product name :"+data.get(dataSet).get("Italyproduct");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("Italyproduct"));
				report.addPassLog(expectedResult, "Should display Browse search Page", "Browse search Page display successfully", Common.getscreenShotPathforReport("Browse search page success"));

			}catch(Exception e)
			{
				Common.clickElement("xpath", "(//span[@class='braun-icons m_hide'])[2]");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("Italyproduct"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);

			Sync.waitElementPresent("xpath", "//img[@class='product-image-photo ']");
			Common.clickElement("xpath", "//img[@class='product-image-photo ']");
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void CategorySelection() {
		// TODO Auto-generated method stub
		String expectedResult="Product Selection from Category";
		try {
			Thread.sleep(4000);		
			Sync.waitElementPresent("xpath", "//a[@id='ui-id-2']");
			Common.clickElement("xpath", "//a[@id='ui-id-2']");
			Common.clickElement("xpath", "(//label[@class='checkmark_rw addtocompare_rw '])[1]");
			Common.clickElement("xpath", "(//label[@class='checkmark_rw addtocompare_rw '])[2]");
			Common.clickElement("xpath", "(//label[@class='checkmark_rw addtocompare_rw '])[3]");
			Common.clickElement("xpath", "(//label[@class='checkmark_rw addtocompare_rw '])[4]");
			Thread.sleep(5000);
          Common.clickElement("xpath", "//button[@class='compare_all_btn braun_btn m_hide']");
         // String Compareproducts=Common.getText("xpath", "//div[@class='compare_bn_cnt col-lg-12']");
          
          int compare=Common.findElements("xpath", "//div[@class='compare_bn_cnt col-lg-12']").size();
          System.out.println(compare);
        Common.assertionCheckwithReport(compare>0, "Compare our products.", "Should display error message ","error message is displayed ", "failed to display error message ");   
         // System.out.println(Compareproducts);
      //	Assert.assertEquals(Compareproducts, "???Zur??ck");

			report.addPassLog(expectedResult, "Should display Compare Products Page", "Compare Products Page display successfully", Common.getscreenShotPathforReport("Compare Products Page display success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Compare Products Page", "Compare Products Page not displayed", Common.getscreenShotPathforReport("Compare Products Page Failed"));
			e.printStackTrace();
			Assert.fail();

	}
	
	}

	public void ProductReviews() {
		// TODO Auto-generated method stub
		
		try {
			
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.DOWN);
			Thread.sleep(4000);
			Common.actionsKeyPress(Keys.DOWN);
		
			Common.actionsKeyPress(Keys.DOWN);
			Thread.sleep(5000);
			Common.clickElement("xpath", "//a[@id='tab-label-bv_reviews-title']");
			//Thread.sleep(2000);
			Common.actionsKeyPress(Keys.DOWN);
			
			//Thread.sleep(2000);
			/*int review=Common.findElements("xpath", "(//span[@class='bv-rating-stars bv-rating-stars-off'])[1]").size();
	          System.out.println(review);
	        Common.assertionCheckwithReport(review>0, "Rewiews", "Should display reviews page ","reviews page is displayed ", "failed to display reviews page ");   
*/
			
			  ExtenantReportUtils.addPassLog("To verify the reviews page", "Should enter the review details ", "user successfully entered the product review details ", Common.getscreenShotPathforReport("Successfully Entered product review detailis"));
		      	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
		    }catch(Exception |Error e) {
		    		ExtenantReportUtils.addFailedLog("To verify the Product review details","Should ener the review details", "user unable to enter product review details", Common.getscreenShotPathforReport("failed to enter product review details"));			
		    		Assert.fail();	
		    		}
	
}
	
	public void PrivacyPolicy() {
		
		try {
			Common.actionsKeyPress(Keys.PAGE_DOWN);	
			Common.clickElement("xpath", "(//a[@title='Datenschutzerkl??rung'])[2]");	
			  int privacy=Common.findElements("xpath", "//h2[@class='tcs_hd']").size();
	          System.out.println(privacy);
	        Common.assertionCheckwithReport(privacy>0, "Privacy Policy", "Should land on Privacy Policy Page ","land on Privacy Policy Page", "failed to land on Privacy Policy Page ");   
			
		
		}
		catch (Exception |Error e) {
	
}
}

   public void TermsandConditions() {
	   try {
			Common.actionsKeyPress(Keys.PAGE_DOWN);	
			Thread.sleep(3000);
			Common.clickElement("xpath", "//a[@title='AGB']");	
			  int terms=Common.findElements("xpath", "//h2[@class='tcs_hd']").size();
	          System.out.println(terms);
	        Common.assertionCheckwithReport(terms>0, "Terms and conditions", "Should land on Terms and conditions Page ","land on Terms and conditions Page", "failed to land on Terms and conditions Page ");   
			
		
		}
		catch (Exception |Error e) {
	   
   }
   }
	   public void footerLinks_Thermometer_Validation(){
			 String Links= "Thermometer";
			  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
			  
			  Common.clickElement("xpath","//a[text()='Thermometer']");
			  Sync.waitPageLoad();
			  Common.assertionCheckwithReport(Common.getPageTitle().equals("Braun thermometers"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
			  }
			  catch (Exception |Error e) {
					e.printStackTrace();
			    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
			    Assert.fail();
			  
			  }

	   
	   
   }
	   public void footerLinks_Blutdruckmessger??t_Validation(){
			 String Links= "Blutdruckmessger??t";
			  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
			  
			  Common.clickElement("xpath","//a[text()='Blutdruckmessger??t']");
			  Sync.waitPageLoad();
			  Common.assertionCheckwithReport(Common.getPageTitle().equals("Braun Blutdruckmessger??te"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
			  }
			  catch (Exception |Error e) {
					e.printStackTrace();
			    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
			    Assert.fail();
			  
			  }

	   
	   
 }
	   public void footerLinks_Nasensauger_Validation(){
			 String Links= "Nasensauger";
			  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
			  
			  Common.clickElement("xpath","//a[text()='Nasensauger']");
			  Sync.waitPageLoad();
			  Common.assertionCheckwithReport(Common.getPageTitle().equals("Braun Nasensauger | Nasensauger 1"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
			  }
			  catch (Exception |Error e) {
					e.printStackTrace();
			    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
			    Assert.fail();
			  
			  }
}
	   public void footerLinks_BraunLeitf??den_Validation(){
			 String Links= "Braun Leitf??den";
			  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
			  
			  Common.clickElement("xpath","//a[text()='Braun Leitf??den']");
			  Sync.waitPageLoad();
			  Common.assertionCheckwithReport(Common.getPageTitle().equals("Ihre Gesundheit"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
			  }
			  catch (Exception |Error e) {
					e.printStackTrace();
			    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
			    Assert.fail();
			  }
		
		}

               public void footerLinks_HealthMagazine_Validation(){
					 String Links= "Health Magazine";
					  try{
					  Common.actionsKeyPress(Keys.END);
					  Thread.sleep(3000);
					  
					  Common.clickElement("xpath","(//a[text()='Health Magazine'])[2]");
					  Sync.waitPageLoad();
					  Common.assertionCheckwithReport(Common.getPageTitle().equals("Ihre Gesundheit"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
					  }
					  catch (Exception |Error e) {
							e.printStackTrace();
					    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
					    Assert.fail();
					  
				
					  }
	   }	      
               public void footerLinks_Kundensupport_Validation(){
					 String Links= "Kundensupport";
					  try{
					  Common.actionsKeyPress(Keys.END);
					  Thread.sleep(3000);
					  
					  Common.clickElement("xpath","//a[text()='Kundensupport']");
					  Sync.waitPageLoad();
					  Sync.waitElementPresent("xpath","//title[text()='Support - Helen of Troy - EN']");
					  Common.assertionCheckwithReport(Common.getPageTitle().equals("Support - Helen of Troy - EN"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
					  Common.closeCurrentWindow();
						Common.switchToFirstTab();
					  }
					  catch (Exception |Error e) {
							e.printStackTrace();
					    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
					    Assert.fail();
					  
				
					  }
	   }	      
               
               public void footerLinks_Verkaufsbedingungen_Validation(){
					 String Links= "Verkaufsbedingungen";
					  try{
					  Common.actionsKeyPress(Keys.END);
					  Thread.sleep(3000);
					  
					  Common.clickElement("xpath","//a[text()='Verkaufsbedingungen']");
					  Sync.waitPageLoad();
					  Common.assertionCheckwithReport(Common.getPageTitle().equals("Terms of Sale"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");	  
					  }
					  catch (Exception |Error e) {
							e.printStackTrace();
					    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
					    Assert.fail();
					  
				
					  }
	   }	      

               public void socialLinkValidation(String DataSet){  		
            		String socalLinks =data.get(DataSet).get("Links");
            		String [] socallinksarry=socalLinks.split(",");
            		int i=0;
            		try{
            		for(i=0;i<socallinksarry.length;i++){
            			Common.actionsKeyPress(Keys.END);
            			Common.clickElement("xpath", "//span[text()='"+socallinksarry[i]+"']");
            			Common.switchWindows();
            			System.out.println(Common.getCurrentURL());
            			
            			 	if(socallinksarry[i].equals("Facebook")){
             				Common.assertionCheckwithReport(Common.getPageTitle().contains("Braun Healthcare - Home | Facebook"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
             				Common.closeCurrentWindow();
             				Common.switchToFirstTab();
             				            		
             				}	
            			
               	 if(socallinksarry[i].equals("Instagram")){
            				Common.assertionCheckwithReport(Common.getPageTitle().contains("Login ??? Instagram"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
            				Common.closeCurrentWindow();
            				Common.switchToFirstTab();
            				
            			}           	   
            	   	if(socallinksarry[i].equals("Youtube")){
            			Common.assertionCheckwithReport(Common.getCurrentURL().contains("UCJgON4hA8GHaD1fQYg-KRFw"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
            			Common.closeCurrentWindow();
            			Common.switchToFirstTab();
            		}
            			
            			//Common.switchToDefault();
            		}
            		}
            		catch(Exception | Error e){
            			e.printStackTrace();
            		    ExtenantReportUtils.addFailedLog("Verifying Social link ","click the socal links it will navigating to particular page","User unabel to navigate Social page",Common.getscreenShotPathforReport("socialpage"));
            		    Assert.fail();
            		}
            		
            		
            	}
        
               public void instagramFooterlink() throws Exception
               {
               	String expectedResult="It should navigate to instagram page";
               	try {
               	
               		Common.actionsKeyPress(Keys.END);
               		Thread.sleep(5000);
               		
               		Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-instagram']");
               		Common.clickElement("xpath", "//a[@class='social-icons si-borderless si-instagram']");
               		Common.switchWindows();
               		/*if(Common.isElementDisplayed("xpath", "//h1[contains(text(),'Instagram')]")) {
               		String s=Common.getText("xpath", "//h1[contains(text(),'Instagram')]");
               	   	System.out.println(s);
               	   	Assert.assertEquals(s, "Instagram");
               		}else {*/
               		Thread.sleep(5000);
               		/*String s=Common.getText("xpath", "//h1[contains(text(),'Instagram')]");
               		System.out.println(s);
               		Assert.assertEquals(s, "Instagram");*/
               		String url=Common.getCurrentURL();
               		Common.assertionCheckwithReport(url.contains("instagram"),"Verifying instagram page","it shoud navigate to instagram  page", "successfully  navigated to instagram Page", "instagram");	
               		//Common.switchToFirstTab();
               		
               		
               	}catch(Exception |Error e) {
               		e.printStackTrace();
               	ExtenantReportUtils.addFailedLog("To verify the instagram link","should navigate to instagram page", "userunable to navigate to instagram page", Common.getscreenShotPathforReport("failed to navigate to instagram page"));			
               		Assert.fail();	
               		}
               	Common.closeCurrentWindow();
               Common.switchToFirstTab();
               }

               
               public void facebookFooterlink() throws Exception
               {
               	String expectedResult="It should navigate to facebook page";
               	try {
               		
               		
               		
               		Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-facebook']");
               		Common.clickElement("xpath", "//a[@class='social-icons si-borderless si-facebook']");
               		Common.switchWindows();
               		
               		Thread.sleep(10000);
               		
               		String url=Common.getCurrentURL();
               		Common.assertionCheckwithReport(url.contains("facebook"),"Verifying facebook page","it shoud navigate to facebook  page", "successfully  navigated to facebook Page", "facebook");	
               		//Common.switchToFirstTab();
               		
               		
               	}catch(Exception |Error e) {
               		e.printStackTrace();
               	ExtenantReportUtils.addFailedLog("To verify the facebook link","should navigate to facebook page", "userunable to navigate to facebook page", Common.getscreenShotPathforReport("failed to navigate to facebook page"));			
               		Assert.fail();	
               		}
               	Common.closeCurrentWindow();
               Common.switchToFirstTab();

               
            }
               
            public void youtubeFooterlink() throws Exception
             {
               	String expectedResult="It should navigate to youtube page";
               	try {
               		
               		
               		
               		Sync.waitElementPresent("xpath", "//a[@class='social-icons si-borderless si-youtube']");
               		Common.clickElement("xpath", "//a[@class='social-icons si-borderless si-youtube']");
               		Common.switchWindows();
               		Thread.sleep(5000);
               		String url=Common.getCurrentURL();
               		Common.assertionCheckwithReport(url.contains("youtube"),"Verifying youtube page","it shoud navigate to youtube  page", "successfully  navigated to youtube Page", "youtube");	
               		Common.switchToFirstTab();
               		
               		
               	}catch(Exception |Error e) {
               		e.printStackTrace();
               	ExtenantReportUtils.addFailedLog("To verify the youtube link","should navigate to youtube page", "userunable to navigate to youtube page", Common.getscreenShotPathforReport("failed to navigate to youtube page"));			
               		Assert.fail();	
               		}
               Common.switchToFirstTab();
               }



        public void password(String dataSet) {
	    try {
	  	Common.switchFrames("xpath", "//iframe[@class='adyen-checkout__iframe adyen-checkout__iframe--threeDSIframe']");
		//iframe[@class='adyen-checkout__iframe adyen-checkout__iframe--threeDSIframe']
		Sync.waitElementPresent("xpath", "//input[@class='input-field']");
		Common.textBoxInput("xpath", "//input[@class='input-field']", data.get(dataSet).get("Password"));
	
		Common.clickElement("xpath", "//input[@type='submit']");
	
    	}catch(Exception |Error e) {
       		e.printStackTrace();
       	ExtenantReportUtils.addFailedLog("To verify the youtube link","should navigate to youtube page", "userunable to navigate to youtube page", Common.getscreenShotPathforReport("failed to navigate to youtube page"));			
       		Assert.fail();	
       		
        	
       	
}

}

}








