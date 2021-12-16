package TestExecute.Stinger;

import org.testng.annotations.Test;

import TestComponent.Stinger.StingerHelper;
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

 

public class TEST_ST_SR_Stinger_TAX_RegisterUser {
	String datafile = "Stinger//StingerTestData.xlsx";
	StingerHelper Stinger=new StingerHelper(datafile);
	 @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    
    public void gUESTTAX() throws Exception {
    	try {
			String addressFile = "Stinger//StreetAddresswithZipcode.xlsx";
			Stinger.prepareTaxData("StingerTaxDetails_Guest.xlsx");
	     	Map<String, List<Map<String, String>>> addressVal=new HashMap<>();
			ExcelReader excelData=new ExcelReader(addressFile);
			addressVal=excelData.getStateAddressValue();
			Set<String> stateKeys=addressVal.keySet();
			for(String state:stateKeys)
			{	
			for(int i=1;i<addressVal.get(state).size();i++)
			{
		
			try {
			String Website=Stinger.URL();
			Map<String, String> add=addressVal.get(state).get(i);
			String streetAddress=addressVal.get(state).get(i).get("StreetAddress");
			String City=addressVal.get(state).get(i).get("City");
			String Zipcode=addressVal.get(state).get(i).get("Zipcode");
			String tax=addressVal.get(state).get(i).get("TaxRate");	  
			//String Website=Stinger.URL();
			Stinger.AccountCreation("AccountDetails");
			Stinger.categoryMenuItem();
			Stinger.Addtocart();
			Stinger.checkoutPage();
			Stinger.newtaxship1(streetAddress,City,Zipcode,state);
//			Stinger.shipping_Address("Address");
			HashMap<String,String> data= Stinger.taxValidation(tax,state);
			Stinger.addPaymentDetails("PaymentDetails");
			String OrderId=Stinger.order_Verifying();
            
         
            
    	System.out.print(data);
		//	Stinger.writeResultstoXLSx(OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("Totalammountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
			 Stinger.writeResultstoXLSx1(Website,OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),state,Zipcode,data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));

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
    	System.setProperty("configFile", "Stinger\\config_Stinger_Staging.properties");
		  Login.signIn();
		 
		  
	  }
	@AfterTest
	public void clearBrowser()
	{
		
//		Common.closeAll();

	}

}




