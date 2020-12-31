package TestExecute.Oxo.Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestComponent.oxo.OxoMobileHelper;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class ContentLinks_Validation {
	
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoMobileHelper oxo=new OxoMobileHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

  public void ContentLinks_Validation() {
		try{
			oxo.closetheadd();
			//oxo.NavigationToggle();
			//oxo.HelpToggle();
			oxo.ContactUS();
			oxo.FAQ();
			oxo.ProductRegistration();
			oxo.VoluntaryRecall();
			oxo.PrivacyPolicy();
			oxo.TermsandConditions();
			oxo.TrackOrder();
			oxo.ShippingInformation();
			oxo.BetterGuarantee();
			oxo.GoodTipsBlog();
			oxo.InventorSubmissions();
			oxo.InvestorRelations();
			oxo.Careers();
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
