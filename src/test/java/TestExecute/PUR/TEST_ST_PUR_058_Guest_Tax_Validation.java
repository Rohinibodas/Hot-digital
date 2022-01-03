package TestExecute.PUR;

import java.util.HashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.PUR.PurHelper;
import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;
import Utilities.ExcelReader;

public class TEST_ST_PUR_058_Guest_Tax_Validation {
	String datafile = "PUR//PUR_TestData.xlsx";	
	PurHelper PUR=new PurHelper(datafile);

	@Test(priority = 1)

	public void categoryGuestcheckout(){

		try {
            
			String addressFile = "PUR//PURAddressTax.xlsx";
			PUR.prepareTaxData("PURTaxDetails_Guest.xlsx");
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
			
			PUR.Mouseover();
			PUR.Productselection();
			PUR.navigateMinicart();
			PUR.checkoutPage();
			PUR.ShippingAddress("Address",streetAddress,City,Zipcode,state);
			PUR.Address_POPUP();
			
			HashMap<String,String> data=PUR.Taxcalucaltion(tax);
			PUR.updatePaymentAndSubmitOrder("PaymentDetails");
			
			
			
			
			 String OrderId=PUR.order_Verifying();
			PUR.writeResultstoXLSx(OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("Totalammountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
           
			}
			
			
		catch (Exception e) {
			//Common.closeAll();
			startTest();
			//Assert.fail(e.getMessage(), e);
		} 
			}
			}}
			catch (Exception e) {
				
				Assert.fail(e.getMessage(), e);
	}}
	
	@BeforeMethod
	  public void startTest() throws Exception {
		// System.setProperty("configFile", "PUR\\Config_PUR_Staging.properties");
		  Login.signIn();
}
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}

}