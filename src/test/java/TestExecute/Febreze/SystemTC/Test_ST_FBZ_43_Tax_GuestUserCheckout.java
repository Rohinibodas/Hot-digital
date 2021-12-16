package TestExecute.Febreze.SystemTC;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Febreze.FebrezeHelper;
import TestLib.Common;
import TestLib.Login;
import Utilities.ExcelReader;

public class Test_ST_FBZ_43_Tax_GuestUserCheckout {
	String datafile = "Febreze//FebrezeTestData.xlsx";
	FebrezeHelper febreze = new FebrezeHelper(datafile);
	@Test(priority=1)
		public void GuestCategoryCheckoutCC() throws Exception {

			try {
				//String addressFile = "Febreze//febrez-ks (1).xlsx";
				String addressFile = "Febreze//StreetAddresswithZipcode.xlsx";
				febreze.prepareTaxData("FebrezeTaxDetails.xlsx");
				Map<String, List<Map<String, String>>> addressVal=new HashMap<>();
				ExcelReader excelData=new ExcelReader(addressFile);
				addressVal=excelData.getStateAddressValue();
				Set<String> stateKeys=addressVal.keySet();
				for(String state:stateKeys)
				{	
				for(int i=0;i<addressVal.get(state).size();i++)
				{
				try {
				Map<String, String> add=addressVal.get(state).get(i);
				String streetAddress=addressVal.get(state).get(i).get("StreetAddress");
				String City=addressVal.get(state).get(i).get("City");
				String Zipcode=addressVal.get(state).get(i).get("Zipcode");
				String tax=addressVal.get(state).get(i).get("TaxRate");
				String Website=febreze.URL();
				febreze.Acceptcookies();
				febreze.browsersearch("ProductName");
			    febreze.productselection();
			    febreze.Navigateminicart();
			    febreze.NavigateMycartpage();
			    febreze.ProductQuantityincreaseMycart("UpdateQuantity");
			    febreze.checkoutpage();
			    febreze.ShippingAddress("Address",streetAddress,City,Zipcode,state);
			   // febreze.shipping_Address("Guest_shipping");
			   HashMap<String,String> data=febreze.taxValidation(tax,state);
			 // febreze.AddPaymentdetails("PaymentDetails");
	        //  String OrderId=febreze.order_Verifying();
			  febreze.writeResultstoXLSx(Website,data.get("subtotlaValue"),data.get("shippingammountvalue"),state,Zipcode,data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
			
				}
				
				catch (Exception e) {
					Common.closeAll();
					startTest();
					//Assert.fail(e.getMessage(), e);
				} 
					}
					}}
					catch (Exception e) {
						
						Assert.fail(e.getMessage(), e);
			}}
			
	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}

	@BeforeMethod
	public void startTest() throws Exception {
		//System.setProperty("configFile", "Febreze\\Config_Febreze_Staging.properties");
		System.setProperty("configFile", "Febreze\\Config_Febreze_prod.properties");
		
		Login.signIn();

	}

}
