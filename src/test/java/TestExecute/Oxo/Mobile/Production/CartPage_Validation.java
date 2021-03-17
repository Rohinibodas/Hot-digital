
	package TestExecute.Oxo.Mobile.Production;

	import org.testng.Assert;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Parameters;
	import org.testng.annotations.Test;

	import TestComponent.oxo.OxoHelper;
	import TestComponent.oxo.OxoMobileLiveHelper;
	import TestLib.BaseDriver;
	import TestLib.Common;
	import TestLib.Login;

	public class CartPage_Validation {
		
		String datafile = "oxo//OxoTestData.xlsx";	
		OxoMobileLiveHelper oxo=new OxoMobileLiveHelper(datafile);
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	  public void CartPage_Validation() {
			try{
				oxo.closetheadd();
				oxo.AcceptPrivacy();
				oxo.NavigationToggle();
				oxo.login();
				oxo.loginOxo("AccountDetails");
				oxo.NavigationToggle();
				//oxo.clickBaby_Toddler();
				oxo.Beverage();
				oxo.addproducts("1");
				oxo.clickViewCart();
				oxo.CheckPost();
		   
	  }
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
		
		@AfterTest
		public void clearBrowser() throws Exception
		{
			Common.closeAll();
			

		}
		
		/*@BeforeMethod
		  public void startTest() throws Exception {
			 System.setProperty("configFile", "Oxo\\config.properties");
			 Login.signIn("chrome","Galaxy S5");
	   }*/
		@BeforeTest
		@Parameters({"device"})  
		  public void startTest(String Device) throws Exception {
			System.setProperty("configFile", "Oxo\\config.properties");
			Login.signIn("chrome",Device);
		  }

	}

