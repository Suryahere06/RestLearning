package com.iPivot.RestLearning.Test;

//TestNG
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
 //import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
 import org.json.simple.JSONObject;
 import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
//Rest
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
 import io.restassured.path.json.JsonPath;

//ExtentReports
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
 import com.relevantcodes.extentreports.LogStatus;

public class RestAPICode {
	
	static ExtentReports report;
	static ExtentTest test;
	static int i=1;
 	
	@BeforeClass
	public  static void startTest() {

		System.out.println("inside before class");
		report=new ExtentReports(System.getProperty("user.dir")+"//ExtentReport//ExtentReportResults.html");

		System.out.println("after before class");

}
	
	
	@BeforeGroups(groups = "weather",alwaysRun = true)
	public  void beforeeachtestcase1()
	{
		System.out.println("Starting Test"+Integer.toString(i));

		test=report.startTest("weathertest"+Integer.toString(i));


	}
	@BeforeGroups(groups = "login",alwaysRun = true)
	public  void beforeeachtestcase2()
	{
		System.out.println("Starting Test"+Integer.toString(i));

		test=report.startTest("logincase"+Integer.toString(i));


	}
	
	
	@AfterGroups(groups = "weather",alwaysRun = true)
	public  void aftereachtest1()
	{
		report.endTest(test);

		System.out.println("ending Test"+Integer.toString(i));
		i++;

	}
	@AfterGroups(groups = "login",alwaysRun = true)
	public  void aftereachtest2()
	{
		report.endTest(test);

		System.out.println("ending Test"+Integer.toString(i));
		i++;

	}
	


	@DataProvider(name="locations")
	public String[] TestWeather() {
		
		return new String[]{"chennai","newark","coimbatore","switzerland","singapore"};
	
	}
	
	
	
	@Test(groups= "weather", dataProvider="locations",alwaysRun = true)

	public void getWeather(String input) {
		 System.out.println("weather");
		//i++;
		try {
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest=RestAssured.given();
		Response httpResponse=httpRequest.request(Method.GET,"/"+input);
		
		
		//timeout 
			AssertJUnit.assertEquals(httpResponse.getStatusCode(), 200);
		
		//Read as Json
		JsonPath jsonPathEvaluator = httpResponse.jsonPath();
			AssertJUnit.assertEquals("56 Percent", jsonPathEvaluator.getString("Humidity"));
		
		

	test.log(LogStatus.PASS, httpResponse.prettyPrint());

		}
		catch(AssertionError e)
		{
			test.log(LogStatus.FAIL, e.fillInStackTrace());
			// AssertJUnit.fail();

			
		}
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, e.fillInStackTrace());
			 //AssertJUnit.fail();

			
		}
	}
	 
	@Test (groups = "login",alwaysRun = true)
    public void RegistrationSuccessful() {
	//	test=report.startTest("logintest"+Integer.toString(i));
        System.out.println("login");
        System.out.println("Fail here1");

        RestAssured.baseURI = "http://bookstore.toolsqa.com";
        System.out.println("Fail here2");

        RequestSpecification request = RestAssured.given();

        try
        {
        JSONObject requestParams = new JSONObject();
        System.out.println("Fail here3");

        /*I have put a unique username and password as below,
        you can enter any as per your liking. */
       // requestParams.put("UserName", "TOOLSQA-Test");
       // requestParams.put("Password", "Test@@123");
        


        request.body(requestParams.toJSONString());
        Response response = request.post("/Account/v1/User");

        AssertJUnit.assertEquals(response.getStatusCode(), 201);
        // We will need the userID in the response body for our tests, please save it in a local variable
        String userID = response.getBody().jsonPath().getString("userID");
        System.out.println(userID);
		test.log(LogStatus.PASS, response.prettyPrint());
        }
        catch(AssertionError e)
		{
			test.log(LogStatus.FAIL, e.fillInStackTrace());
			// AssertJUnit.fail();

			
		}
    }

	
	@AfterClass
	public  void endTest() {

		report.flush();
		System.out.println("End of Class");

}
	
}