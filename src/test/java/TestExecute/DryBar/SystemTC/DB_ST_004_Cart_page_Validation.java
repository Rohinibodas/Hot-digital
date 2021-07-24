package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_004_Cart_page_Validation {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	
  @Test
  public void verifyingMiniCartPage() throws Exception {
	try{
		drybar.Accept();
		drybar.verifyingHomePage();
		 drybar.clickHairProducts();
		  drybar.SelectShampoos();
		  drybar.Selectproduct();
		  drybar.Accept();
		  drybar.Verify_PDP();
	  drybar.increaseProductQuantity("5");
	  drybar.clickAddtoBag();
	  drybar.clickminiCartButton();
	  drybar.updateProductInMinicart("2");
	  drybar.click_View_editcart();
	  drybar.changeQuntity_UpdateProduct("5");
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
 		  Login.signIn();
 		 
 		  
 	  }
 	

}
