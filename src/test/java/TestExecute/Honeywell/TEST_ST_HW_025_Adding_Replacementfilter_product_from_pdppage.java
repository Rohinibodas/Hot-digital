
package TestExecute.Honeywell;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;

import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HW_025_Adding_Replacementfilter_product_from_pdppage {
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	
	public void Adding_Replacementfilter_to_product_from_pdppage() throws Exception {

		try {
			honeyWell.loginHoneywell("AccountDetails");
			honeyWell.verifyingHomePage();
			honeyWell.click_Airpurifiers();
			honeyWell.replacementfilter("productnameRegester");
			honeyWell.clickAddtoBag();
			honeyWell.clickminicartButton();
		
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
	Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		 //System.setProperty("configFile", "Honeywell\\config.properties");
		  Login.signIn();
		 
		  
	  }

}
