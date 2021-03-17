package TestExecute.Oxo.Mobile.Production;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestComponent.oxo.OxoMobileHelper;
import TestComponent.oxo.OxoMobileLiveHelper;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class ValidationBlogs_Article_Links {
	
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoMobileLiveHelper oxo=new OxoMobileLiveHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

  public void ValidationBlogs_Article_Links() {
		try{
			oxo.closetheadd();
			oxo.AcceptPrivacy();
	
			//oxo.Instagram();
			//oxo.Facebook();
			oxo.pinterest();
			oxo.Twitter();
			oxo.YouTube();
  
	   
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
