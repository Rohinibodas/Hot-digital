package TestExecute.Hydroflask.Smoke;

import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestComponent.Hydroflask.HydroHelper;
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

 

public class Test_HF_0001_taxguest {
    String datafile = "Hydroflask//HydroTestData.xlsx";    
    HydroHelper Hydro=new HydroHelper(datafile);
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    
    public void gUESTTAX() throws Exception {
    	try {

			String addressFile = "Hydroflask//HydroAddressTax.xlsx";
	     	Hydro.prepareTaxData("HydroflaskTaxDetails_Guest.xlsx");
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
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
//			Hydro.Guestshipping(streetAddress,City,Zipcode,state);
			Hydro.addDeliveryAddress("Address",streetAddress,City,Zipcode,state);
			 HashMap<String,String> data=Hydro.taxValidation(tax);
			Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
			String OrderId="";

			Hydro.writeResultstoXLSx(OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("Totalammountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
            //honeyWell.writeResultstoXLSx("OrderId");
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
       System.setProperty("configFile", "Hydroflask\\config.properties");
       
        Login.signIn();
        Hydro.acceptPrivecy();       
        
    }
	@AfterTest
	public void clearBrowser()
	{
		
		//Common.closeAll();

	}

}
