package TestExecute.Honeywell;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HW_003_footer_links_validation {
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	
	public void registeredUser_Checkout_CreditCard_VISA() throws Exception {

		try {
	
			honeyWell.verifyingHomePage();
			honeyWell.productsupport();	
			honeyWell.fottorValidations_Shop("FooterNames");
			honeyWell.footerValidations_aboutUs("FooterNames");
			honeyWell.fotterValidations_HelenOfTroy("FooterNames");
			honeyWell.privacypolicy();
			honeyWell.Termsofuse();
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
		// System.setProperty("configFile", "Honeywell\\config.properties");
		  Login.signIn();
		 
		  
	  }
}
