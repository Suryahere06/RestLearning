package com.iPivot.RestLearning.RestLearning;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PracticeAPI {
	
	
	static ExtentReports report;
	static ExtentTest test;
	static int i=1;
	
	@BeforeClass
	public void startTest(){
		report=new ExtentReports(System.getProperty("user.dir")+"//ExtentReport//ExtentReportResults.html");
		test=report.startTest("WeatherTest"+Integer.toString(i));

	}

	@DataProvider(name="Weather")
	public String[] TestData() {
		return new String[] {"Switzerland","Coimbatore","Paris","Greece"};
	}
	
	
	@Test(dataProvider="Weather")
	public void getWeather(String location) {
		
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest=RestAssured.given();
		Response httpResponse=httpRequest.request(Method.GET,"/"+location);
		
		AssertJUnit.assertEquals(httpResponse.getStatusCode(),200);
		test.log(LogStatus.PASS, httpResponse.prettyPrint());
	}

	
	
	
}
