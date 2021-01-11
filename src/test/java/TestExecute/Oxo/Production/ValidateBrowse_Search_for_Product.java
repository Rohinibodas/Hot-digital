package TestExecute.Oxo.Production;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelperLive;
import TestLib.Common;
import TestLib.Login;

public class ValidateBrowse_Search_for_Product {
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoHelperLive oxo=new OxoHelperLive(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

  public void ValidateBrowse_Search_for_Product() {
		try{
			oxo.closetheadd();
			oxo.acceptPrivecy();
			oxo.loginOxo("AccountDetails");
		    oxo.validatingSearchBoxWithOutData();
		    oxo.validatingSearchBoxWithNumberText("9 cup");
		    oxo.validatingSearchProductInformation("Nylon Potato Masher");
			
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
	
	@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Oxo\\config.properties");
		
		  Login.signIn();

	}

}