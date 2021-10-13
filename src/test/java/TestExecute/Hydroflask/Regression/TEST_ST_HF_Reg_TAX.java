package TestExecute.Hydroflask.Regression;

import org.testng.annotations.Test;


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
   
 

public class TEST_ST_HF_Reg_TAX {
    String datafile = "Hydroflask//HydroTestData.xlsx";    
    HydroHelper Hydro=new HydroHelper(datafile);
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    
    public void Regtax() throws Exception {
    	try {

			String addressFile = "Hydroflask//HydroAddressTax.xlsx";
	     	Hydro.regprepareTaxData("HydroflaskTaxDetails_Reg.xlsx");
	     	Hydro.loginHydroflaskAccount("AccountDetails");
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
			String Website=Hydro.URL();
			Hydro.acceptPrivecy();
//			Hydro.orderSubmit("Bottles");
//			Hydro.Customize_Bottle_Standed("24 oz");
			Hydro.searchproduct_addtocart("32 oz Tumbler Lid");
			Hydro.checkOut();
//			Hydro.Guestshipping(streetAddress,City,Zipcode,state);
			Hydro.regaddDeliveryAddress("Address",streetAddress,City,Zipcode,state);
			 HashMap<String,String> data=Hydro.taxValidation(tax,state);
			String OrderId=Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
//			String OrderId="12345";

			Hydro.regwriteResultstoXLSx(Website,OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),state,Zipcode,data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
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
       Hydro.ClosADD();
       
        
    }
	@AfterTest
	public void clearBrowser()
	{
		
//		Common.closeAll();

	}

}
