package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_033_Add_to_cart_from_PLP {
	
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);

  @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Add_to_cart_from_PLP
()  {
	  try {
		  
	  drybar.Accept();
	  drybar.verifyingHomePage();
	  drybar.clickHairProducts();
	  drybar.SelectShampoos();
	  drybar.add_to_cart();
	  
	  
	  
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
		 //System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }

}



