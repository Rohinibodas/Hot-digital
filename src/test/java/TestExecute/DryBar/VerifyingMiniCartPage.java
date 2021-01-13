package TestExecute.DryBar;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Login;

public class VerifyingMiniCartPage {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	
  @Test
  public void f() throws Exception {
	  drybar.clickHairProducts();
	  drybar.selectproduct("ProductName");
	  drybar.increaseProductQuantity("5");
	  drybar.clickAddtoBag();
	  drybar.clickminiCartButton();
	  drybar.updateProductInMinicart("2");
	  drybar.click_View_editcart();
	  drybar.edit_ShopingCart();
	  drybar.changeQuntity_UpdateProduct("5");
	  drybar.click_ContinueShopping();
	//  drybar.addproductInMiniCartPage();
  }
  
  @AfterTest
 	public void clearBrowser()
 	{
 		//Common.closeAll();

 	}
 	
 	@BeforeTest
 	  public void startTest() throws Exception {
 		 System.setProperty("configFile", "DryBar\\config.properties");
 		  Login.signIn();
 		 
 		  
 	  }

}
