package TestExecute.Redesign_Hottools;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//importTestComponent.Redesign_Hottools.Redesign_HottoolsHelper;
import TestComponent.Redesign_Hottools.Redesign_HottoolsHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_HT_RD_008_Change_Email_for_Registereduser {
	
	String datafile = "Redesign_Hottools//Redesign_HottoolsTestData.xlsx";	
	Redesign_HottoolsHelper Redesign_Hottools=new Redesign_HottoolsHelper(datafile);
	
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Change_Email_for_Registereduser() throws Exception {
		try{
			
			

			Redesign_Hottools.Accept();
			Redesign_Hottools.verifyingHomePage();
		    Redesign_Hottools.navigateMyAccount();
		   Redesign_Hottools.loginApplication("AccountDetails");
		  Redesign_Hottools.AccountInformation();
		  Redesign_Hottools.change_Email("AccountDetails");
			  
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
	
	@BeforeTest
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Redesign_Hottools\\config.properties");
		  Login.signIn();
		 
		  
	  }

}
