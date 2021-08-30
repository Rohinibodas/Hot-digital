package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_034_Validation_of_Category_listingpage {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);

  @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Validation_of_Category_listingpage()  {
	  try {
		  
	  drybar.Accept();
	  drybar.verifyingHomePage();
	  drybar.clickHairProducts();
	  drybar.Hair_Products_CategoryValidations("HairproductsMegaMenu");
	  drybar.Hair_Tools_CategoryValidations("HairtoolsMegaMenu");
	  drybar.Benefits_CategoryValidations("BenefitsMegaMenu");
	  drybar.Inspo_CategoryValidations("HowToInspoMegaMenu");	  
	  
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

