package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_023_Add_NewAddress {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Add_Address_for_RegisteredUser()throws Exception {
		try {
			
		drybar.Accept();
		drybar.verifyingHomePage();
	    drybar.navigateMyAccount();
	   drybar.loginApplication("AccountDetails");
	   drybar.AddressBook();
	   drybar.Add_NewAddress("ShippingAddress");
	 
		
		  
		}
		 catch (Exception e) {
				e.printStackTrace();
				
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
		 System.setProperty("configFile", "DryBar\\config.properties");
		 Login.signIn("chrome");
	 // Login.signIn("chrome","iPhone X");
		 
		 
		  
	  }

}
