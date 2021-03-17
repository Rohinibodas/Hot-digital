package TestExecute.DryBar;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_018_VerifyingMiniCartPage {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	
  @Test
  public void verifyingMiniCartPage() throws Exception {
	try{
		drybar.Accept();
		drybar.verifyingHomePage();
		//drybar.Guestuser_PDP();
		 drybar.Search_productname("ProductName");
		  drybar.Verify_PDP();
	  //drybar.clickHairProducts();
	  //drybar.selectproduct("ProductName");
	  drybar.increaseProductQuantity("5");
	  drybar.clickAddtoBag();
	  drybar.clickminiCartButton();
	  drybar.updateProductInMinicart("2");
	  drybar.click_View_editcart();
	 // drybar.edit_ShopingCart();
	  drybar.changeQuntity_UpdateProduct("5");
	  //drybar.click_ContinueShopping();
	//  drybar.addproductInMiniCartPage();
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
