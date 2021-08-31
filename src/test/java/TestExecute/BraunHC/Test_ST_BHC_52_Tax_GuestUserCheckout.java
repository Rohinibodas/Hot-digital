package TestExecute.BraunHC;

import org.testng.annotations.Test;

import TestComponent.BraunHC.BraunHCHelper;
import TestLib.Common;
import TestLib.Login;
import Utilities.ExcelReader;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class Test_ST_BHC_52_Tax_GuestUserCheckout {
String datafile = "BraunHC//BraunHCTestData.xlsx";	
	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void GuestCategoryCheckoutCC() throws Exception {

		try {
			String addressFile = "BraunHC//StreetAddresswithZipcode.xlsx";
			BraunHC.prepareTaxData("BraunTaxDetails_Guest.xlsx");
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
			BraunHC.AGREEPROCEED();
			//BraunHC.selectCategoryProduct("AccountDetails");
			//BraunHC.Global_search("SearchproductName");
			BraunHC.Select_ProductinThermometers("No Touch Thermometer");
			BraunHC.Addtocart();
			BraunHC.ViewandEditcartPage();
			BraunHC.checkoutPage();
			BraunHC.GuestShippingaddress(streetAddress,City,Zipcode,state);
			//BraunHC.MoneyOrderpayment();
			BraunHC.Taxcalucaltion(tax);
			
			BraunHC.UpdatePaymentAndSubmitOrder("PaymentDetails");
			//BraunHC.GuestOrderSuccesspage();
			BraunHC.RegistereduserOrderSuccesspage();
			
			//writeResultstoXLSx( OrderId, SubTotal, ShippingAmount, TaxAmount,  TotalAmount, ActualTax , tax)
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
	

	@BeforeTest
    public void startTest() throws Exception {
		// System.setProperty("configFile", "BraunHC\\Config_BraunHC_Production.properties");
		// System.setProperty("configFile", "BraunHC\\Config_BraunHC_Staging.properties");
		   	    
    Login.signIn();
    }
	
	
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}
}
