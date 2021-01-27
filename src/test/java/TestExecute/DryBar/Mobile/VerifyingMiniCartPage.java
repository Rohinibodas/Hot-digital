package TestExecute.DryBar.Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestLib.Common;
import TestLib.Login;

public class VerifyingMiniCartPage {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarMobile drybar=new DryBarMobile(datafile);
	
  @Test
  public void verifyingMiniCartPage() throws Exception {

try{
drybar.clickHairProducts();
	  drybar.selectproduct("ProductName");
	  drybar.increaseProductQuantity("5");
	  drybar.clickAddtoBag();
	  drybar.clickminiCartButton();
	  drybar.updateProductInMinicart("2");
	  drybar.click_View_editcart();
	//  drybar.edit_ShopingCart();
	  drybar.changeQuntity_UpdateProduct("5");
	  drybar.click_ContinueShopping();
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
	@Parameters({"device"})  
	  public void startTest(String Device) throws Exception {
		System.setProperty("configFile", "DryBar\\config.properties");
		Login.signIn("chrome",Device);
	  
  }
}
