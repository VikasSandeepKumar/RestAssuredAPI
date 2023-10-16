package com.employeeapi.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employee extends TestBase {

	@BeforeClass
	void getAllEmployees() throws InterruptedException {
		logger.info("********Started TC001_GET_All_Employees********");

		RestAssured.baseURI = "https://reqres.in";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/api/users?page=2");

		Thread.sleep(3);
	}

	@Test
	void checkResponseBody() {
		logger.info("******** Checking Response Body********");

		String responseBody = response.getBody().asString();
		logger.info("Response Body==>" + responseBody);
		Assert.assertTrue(responseBody != null);
	}

	@Test
	void checkResponseCode() {
		logger.info("******** Checking Response Code********");

		int statusCode = response.getStatusCode(); // Get status Code
		logger.info("Status code is ==>" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void checkResponseTime() {
		logger.info("******** Checking Response Time********");

		long responseTime = response.getTime(); // Get status Line
		logger.info("Response Time is ==>" + responseTime);
		Assert.assertTrue(responseTime < 2000);
	}

	@Test
	void checkstatusLine() {
		logger.info("******** Checking Status Line********");

		String statusLine = response.getStatusLine(); // Get status Line
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	void checkserverType() {
		logger.info("******** Checking Server Type********");

		String serverType = response.getHeader("Server");
		logger.info("Server type is ==>" + serverType);
		Assert.assertEquals(serverType, "cloudflare");
	}

	@Test
	void checkContentLength() {
		logger.info("******** Checking Content Length********");

		String contentLength = response.header("Content-Length");
		logger.info("Content Length is ==>" + contentLength);

		if (Integer.parseInt(contentLength) < 100)
			logger.warn("Content Length is less than 100");

		Assert.assertTrue(Integer.parseInt(contentLength) > 1000);

	}
	
	@Test
	void checkCookies()
	{
		logger.info("******** Checking Cookies********");
		
		String cookie = response.getCookie("PHPSESSID");
		
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("******** Finished TC001********");
	}
	
	

}
