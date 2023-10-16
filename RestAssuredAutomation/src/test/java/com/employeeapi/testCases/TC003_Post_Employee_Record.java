package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Post_Employee_Record extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	String firstname = RestUtils.first_name();
	String lastname = RestUtils.last_name();
	String email = RestUtils.email();
	String id = RestUtils.id();

	@BeforeClass
	void createEmployee() throws InterruptedException {
		logger.info("*******Started TC003*******");
		RestAssured.baseURI = "https://reqres.in/api/users";
		httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", firstname);
		requestParams.put("LastName", lastname);
		requestParams.put("EmailId", email);
		requestParams.put("ID", id);

		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");

		// Add the JSON to the Body of the Request
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.POST, "/create");

		Thread.sleep(5000);
	}

	@Test
	void CheckResponseBody() {

		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains(firstname), true);
		Assert.assertEquals(responseBody.contains(lastname), true);
		Assert.assertEquals(responseBody.contains(email), true);
		Assert.assertEquals(responseBody.contains(id), true);
	}

	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode(); // Getting Status Code
		Assert.assertEquals(statusCode, 201);
	}

	@Test
	void checkResponseTime() {
		long responseTime = response.getTime(); // Getting Status Time
		Assert.assertTrue(responseTime < 2000);

	}

	@Test
	void checkStatusLine() {
		String statusLine = response.getStatusLine(); // Getting Status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");

	}

	@Test
	void checkContentType() {
		String contentType = response.header("Content-Type"); // Getting Status Time
		Assert.assertEquals(contentType, "application/json; charset=utf-8");

	}

	@Test
	void checkserverType() {
		String serverType = response.header("Server"); // Getting Server
		Assert.assertEquals(serverType, "cloudflare");

	}

	@Test
	void checkCacheControl() {
		String cachecontrol = response.header("CF-Cache-Status"); // Getting Status Cache Control
		Assert.assertEquals(cachecontrol, "DYNAMIC");

	}

	@Test
	void checkVia() {
		String cacheVia = response.header("Via"); // Getting Status Via
		Assert.assertEquals(cacheVia, "1.1 vegur");

	}

	@Test
	void checkConnection() {
		String checkConnection = response.header("Connection"); // Getting Connection Status
		Assert.assertEquals(checkConnection, "keep-alive");

	}

	@Test
	void checkContentLength() {

		String checkContentLength = response.header("Content-Length");
		logger.info("Content Length is ==>" + checkContentLength);

		if (Integer.parseInt(checkContentLength) < 100)
			logger.warn("Content Length is less than 100");

		Assert.assertFalse(Integer.parseInt(checkContentLength) > 1000);

	}

	@AfterClass
	void tearDown() {
		logger.info("******** Finished TC003********");
	}

}
