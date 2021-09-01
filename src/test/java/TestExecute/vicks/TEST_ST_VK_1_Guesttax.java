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

 

public class TEST_ST_VK_1_Guesttax {
    String datafile = "Vicks//Vickstestdata.xlsx";    
	VicksHelper vicks = new VicksHelper(datafile);
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    
    public void gUESTTAX() throws Exception {
    	try {
			String addressFile = "vicks//28 tax prod.xlsx";
//			String addressFile = "Honeywell//guestaxad.xlsx";
	     	vicks.prepareTaxData("vicksTaxDetails_Guest.xlsx");
	     	Map<String, List<Map<String, String>>> addressVal=new HashMap<>();
			ExcelReader excelData=new ExcelReader(addressFile);
			addressVal=excelData.getStateAddressValue();
			Set<String> stateKeys=addressVal.keySet();
			for(String state:stateKeys)
			{	
		/*	for(int i=0;i<addressVal.get(state).size();i++)
			{*/
				int i=2;
		
			try {
			Map<String, String> add=addressVal.get(state).get(i);
			String streetAddress=addressVal.get(state).get(i).get("StreetAddress");
			String City=addressVal.get(state).get(i).get("City");
			String Zipcode=addressVal.get(state).get(i).get("Zipcode");
			String tax=addressVal.get(state).get(i).get("TaxRate");	    
			vicks.Verifyhomepage();
			vicks.Agreandproceed();
			vicks.Humidifiers_Vaporizers();
			vicks.productselect();
			vicks.addtocart();
			vicks.mincat();
			vicks.checkout();
            vicks.GuestShippingAddress(streetAddress,City,Zipcode,state);
            HashMap<String,String> data=vicks.Taxcalucaltion(tax);
            //vicks.paymentDetails("PaymentDetails");
           //String OrderId=vicks.PlaceOrder();
            String OrderId="123";
         
            
    	 System.out.print(data);

			vicks.writeResultstoXLSx(OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("Totalammountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
           
			}
			
			
		catch (Exception e) {
			Common.closeAll();
			startTest();
			//Assert.fail(e.getMessage(), e);
		} 
			}
			}
			catch (Exception e) {
				
				Assert.fail(e.getMessage(), e);
	}
}
	
	@BeforeMethod
	public void startTest() throws Exception {
//		System.setProperty("configFile", "Vicks\\config.properties");
		Login.signIn();
        
    }
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}

}
