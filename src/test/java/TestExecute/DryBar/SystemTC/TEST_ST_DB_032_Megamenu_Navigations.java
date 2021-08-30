package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_032_Megamenu_Navigations {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);

  @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void _Megamenu_Navigations()  {
	  try {
		  
	  drybar.Accept();
	  drybar.verifyingHomePage();
	  drybar.Hair_ProductsMegamenuValidations("HairproductsMegaMenu");
	  drybar.Hair_ToolsMegamenuValidations("HairtoolsMegaMenu");
	  drybar.Benefits_MegamenuValidations("BenefitsMegaMenu");
	  drybar.New_MegamenuValidations("NewMegaMenu"); 
	  drybar.Inspo_MegamenuValidations("HowToInspoMegaMenu");
	  
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
		// System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }

}
