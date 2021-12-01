package TestExecute.vicks;

import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
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

 

public class TEST_ST_VK_2_Regtax {
    String datafile = "Vicks//Vickstestdata.xlsx";    
	VicksHelper vicks = new VicksHelper(datafile);
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    
    public void Regtax() throws Exception {
    	try {
			String addressFile = "Vicks//StreetAddresswithZipcode.xlsx";

	     	vicks.prepareRegTaxData("vicksTaxDetails_Reg.xlsx");
	     	vicks.loginVicks("AccountDetails");
	     	Map<String, List<Map<String, String>>> addressVal=new HashMap<>();
			ExcelReader excelData=new ExcelReader(addressFile);
			addressVal=excelData.getStateAddressValue();
			Set<String> stateKeys=addressVal.keySet();
			for(String state:stateKeys)
			{	
			for(int i=0;i<addressVal.get(state).size();i++)
			{
//				int i=2;
		
			try {
			Map<String, String> add=addressVal.get(state).get(i);
			String streetAddress=addressVal.get(state).get(i).get("StreetAddress");
			String City=addressVal.get(state).get(i).get("City");
			String Zipcode=addressVal.get(state).get(i).get("Zipcode");
			String tax=addressVal.get(state).get(i).get("TaxRate");	  
			String Website=vicks.URL();
			vicks.Verifyhomepage();
//			vicks.Agreandproceed();
			vicks.Humidifiers_Vaporizers();
			vicks.productselect();
			vicks.addtocart();
			vicks.mincat();
			vicks.checkout();
            vicks.RegisterShippingAddress(streetAddress,City,Zipcode,state);
            HashMap<String,String> data=vicks.Taxcalucaltion(tax,state);
            vicks.paymentDetails("PaymentDetails");
           String OrderId=vicks.regPlaceOrder();
//           vicks.Logout();
//            String OrderId="123";
         
            
    	 System.out.print(data);

			vicks.regwriteResultstoXLSx(Website,OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),state,Zipcode,data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
           
			}
			
			
		catch (Exception e) {
			Common.closeAll();
			startTest();
//			Assert.fail(e.getMessage(), e);
				} 
			}}}
			catch (Exception e) {
				
				Assert.fail(e.getMessage(), e);
	}
}
    	
	@BeforeMethod
	public void startTest() throws Exception {
		System.setProperty("configFile", "Vicks\\config.properties");
		Login.signIn();
        
    }
	@AfterTest
	public void clearBrowser()
	{
		
//		Common.closeAll();

	}

}
