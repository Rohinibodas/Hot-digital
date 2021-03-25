package TestExecute.DryBar.Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestLib.Common;
import TestLib.Login;

public class Createaccount {
	
	//String datafile = "DryBar//OxoTestData.xlsx";	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarMobile drybar=new DryBarMobile(datafile);


	
	
	
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void createaccount() throws Exception {
	//  drybar.clickMyaccount();
	  try{
		  drybar.Accept();
		  drybar.verifyingHomePage();
	  drybar.CreateAccount("AccountDetails");
	  }
		catch (Exception e) {
			e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		} 
	  
  }
  
  
  
  @AfterTest
	public void clearBrowser()
	{
		//Common.closeAll();

	}
	
 @BeforeMethod
  public void startTest() throws Exception {
	 System.setProperty("configFile", "DryBar\\config.properties");
	 Login.signIn("chrome","iPad");
 }
}
 
/*@BeforeTest
@Parameters({"device"})  
  public void startTest(String Device) throws Exception {
	System.setProperty("configFile", "DryBar\\config.properties");
	Login.signIn("chrome",Device);
  }
}*/
