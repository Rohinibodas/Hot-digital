package TestExecute.Honeywell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Common;
import TestLib.Login;
import Utilities.ExcelReader;

public class TEST_ST_HW_047_RegUser_Tax {
	  String datafile = "Honeywell\\HoneywellTestData.xlsx";    
	    Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	    
	    public void RegUserTax() throws Exception {
	    	try {
//	    		String addressFile = "Honeywell//DryBarTaxAddress.xlsx";
//				String addressFile = "Honeywell//StreetAddresswithZipcode (2).xlsx";
	    		String addressFile = "Honeywell//newAddressForHoneywell_ks.xlsx";

//	    		String addressFile = "Honeywell//StreetAddresswithZipcode.xlsx";
//				String addressFile = "Honeywell//guestaxad.xlsx";
//	    		String addressFile = "Honeywell//HoneywellproTaxAddress.xlsx";
				honeyWell.prepareTaxDataReg("HoneywellTaxDetails_Reg.xlsx");	
//		     	honeyWell.prepareTaxData("HoneywellTaxDetails_Reg.xlsx");
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
	            honeyWell.verifyingHomePage();
	        	 String Website="Honeywell";
//	            honeyWell.loginHoneywell("AccountDetails");
//	             String Website=honeyWell.URL();
//	            honeyWell.accept();
	        	honeyWell.loginTax("AccountDetails");
	            honeyWell.click_fans();
	    
	            honeyWell.adding_product_toCart("productnameRegester1");
	            honeyWell.clickminicartButton();
	            honeyWell.clickminicartcheckout();
//	            honeyWell.GuestShippingAddress(streetAddress,City,Zipcode,state);
	            honeyWell.RegisterShippingAddress("Address",streetAddress,City,Zipcode,state);
//	            HashMap<String,String> data=honeyWell.Taxcalucaltion(tax);
//	            HashMap<String,String> data=honeyWell.TaxcalucaltionN(tax,state);  
	            HashMap<String,String> data=honeyWell.taxValidation(tax,state);

	            honeyWell.creditCard_payment("CCVisa");
	           String OrderId=honeyWell.order_VerifyingReg();
//	          String OrderId="123456789";
	         
	            
	    	 System.out.print(data);
//				honeyWell.writeResultstoXLSxReg(OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("Totalammountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
//	    	 honeyWell.writeResultstoXLSxN(Website,OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),state,Zipcode,data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
	    	 honeyWell.writeResultstoXLSxReg(Website,OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),state,Zipcode,data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
	 		
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
		  public void startTest() throws Exception {https://www.honeywellpluggedin.com/checkout/#payment
			 System.setProperty("configFile", "Honeywell\\config.properties");
			  Login.signIn();
			 
			  
		  }
		@AfterTest
		public void clearBrowser()
		{
			
			Common.closeAll();

		}

}
