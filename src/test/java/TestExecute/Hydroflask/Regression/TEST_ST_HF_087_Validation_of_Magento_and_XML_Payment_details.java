package TestExecute.Hydroflask.Regression;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_087_Validation_of_Magento_and_XML_Payment_details {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validation_of_Magento_and_XML_Payment_details() throws Throwable {

		try {
				  Hydro.orderSubmit("Bottles");
				  Hydro.searchproduct_addtocart("32 oz Tumbler Lid"); //
				  Hydro.serachproduct_addtocart("Wide Mouth Accessory Bundle");
				  Hydro.viewcart(); Hydro.checkOut(); Hydro.addDeliveryAddress("Address");
				  Hydro.promationCode("Promationcode");
				  Hydro.updatePaymentAndSubmitOrder("Ccmastercard"); String
				  order=Hydro.Verify_order();
				 
			  
		  	//String order="401001638779";
			  Hydro.HydroAdminlogin("AdminLogins");
			//Thread.sleep(120000);
			  Hydro.Navigate_Order_Details_Page(order);
			  HashMap<String, HashMap<String, String>> productinformation = Hydro.productinfromation();
			  Hydro.selectManulExport(order);
			  Hydro.productinfromationvalidation(productinformation, order);
		
		}

		catch (Exception e) {
			e.printStackTrace();

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
		 Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		//System.setProperty("configFile", "Hydroflask\\config.properties");
		Login.signIn("chrome");
		 Hydro.acceptPrivecy();
		  Hydro.ClosADD();
	}

}
