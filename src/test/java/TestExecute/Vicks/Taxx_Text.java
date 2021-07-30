package TestExecute.Vicks;


		import org.testng.Assert;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;

	 

	import TestComponent.Vicks.VicksHelper;
	import TestLib.Common;
	import TestLib.Login;

	 


	public class Taxx_Text {

	String datafile = "Vicks//VICKSTAX.xlsx";
	VicksHelper vicks=new VicksHelper(datafile);
	@Test(priority=1)
	public void taxGuest () throws Exception {

	try {

	vicks.Verifyhomepage();
	vicks.Humidifiers_Vaporizers();
	//vicks.temp();
	vicks.productselect();
	//vicks.tempproduct();
	vicks.addtocart();
	vicks.mincat();
	vicks.checkout();
	vicks.shippingaddress("Guest (South Carolina)");
	vicks.Taxcalucaltion("Guest (South Carolina)");
	vicks.paymentDetails("CCVisa");
	vicks.PlaceOrder();
	}
	catch (Exception e) {
	Assert.fail(e.getMessage(), e);
	}
	}

	 


	@AfterTest
	public void clearBrowser()
	{
	//Common.closeAll();

	 

	}

	@BeforeMethod
	public void startTest() throws Exception {
	System.setProperty("configFile", "Vickshumdifier\\config.properties");
	Login.signIn();


	}

	 

	}

