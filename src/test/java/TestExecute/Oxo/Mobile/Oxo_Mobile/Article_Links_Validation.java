package TestExecute.Oxo.Mobile.Oxo_Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.oxo.OXO_Mobilehelper;
import TestComponent.oxo.OxoHelper;
import TestComponent.oxo.OxoMobileHelper;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class Article_Links_Validation {
	String datafile = "oxo//OxoTestData.xlsx";	
	OXO_Mobilehelper oxo=new OXO_Mobilehelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	  public void OXO_Creat_account() {
			try{
				
				oxo.acceptPrivecy();
			//	oxo.GoodtipsBlocks();
			//	oxo.OXOURLValidation("Links");
				oxo.Artical_Links("Artical");
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
	
   /*@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Oxo\\config.properties");
		 Login.signIn("chrome","iPhone X");
	 }*/
	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Oxo//mobile_config.properties");
		Login.mobilesignIn("ios");
	  }
}
