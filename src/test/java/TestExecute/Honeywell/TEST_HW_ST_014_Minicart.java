package TestExecute.Honeywell;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Login;

public class TEST_HW_ST_014_Minicart {
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void  minicartValidation() {
		try {
			honeyWell.verifyingHomePage();
			honeyWell.click_Airpurifiers();
			
			honeyWell.adding_product_toCart("productnameRegester");
			honeyWell.clickminicartButton();
		honeyWell.update_product_miniCartBag("2");
		honeyWell.clickminicartButton();
		honeyWell.removeproductinBagPage();
		}
catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
  }
	@AfterTest
	public void clearBrowser()
	{
	//Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Honeywell\\config.properties");
		  Login.signIn();
		 
		  
	  }
}
