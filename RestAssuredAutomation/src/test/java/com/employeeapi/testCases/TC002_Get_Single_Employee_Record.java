package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Employee_Record extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getEmployeeData() throws InterruptedException
	{
		logger.info("*******Started*******");
		
		RestAssured.baseURI = "https://reqres.in";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/api/users/"+id);
		
		Thread.sleep(3);
	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(id), true);
	}
	
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); //Getting Status Code
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
		long responseTime = response.getTime(); //Getting Status Time
		Assert.assertTrue(responseTime<2000);
		
	}
	
	@Test
	void checkStatusLine()
	{
		String statusLine = response.getStatusLine(); //Getting Status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test
	void checkContentType()
	{
		String contentType = response.header("Content-Type"); //Getting Status Time
		Assert.assertEquals(contentType,"application/json; charset=utf-8");
		
	}
	
	@Test
	void checkserverType()
	{
		String serverType = response.header("Server"); //Getting Server
		Assert.assertEquals(serverType,"cloudflare");
		
	}
	
	@Test
	void checkCacheControl()
	{
		String cachecontrol = response.header("Cache-Control"); //Getting Status Cache Control
		Assert.assertEquals(cachecontrol,"max-age=14400");
		
	}
	
	
	@AfterClass
	void tearDown()
	{
		logger.info("******** Finished TC002********");
	}
	
	

}
