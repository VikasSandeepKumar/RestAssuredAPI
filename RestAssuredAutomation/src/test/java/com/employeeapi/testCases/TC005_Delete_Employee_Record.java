package com.employeeapi.testCases;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_Record extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;

	String firstname = RestUtils.first_name();
	String lastname = RestUtils.last_name();
	String email = RestUtils.email();
	String id = RestUtils.id();

	@BeforeClass
	void createEmployee() throws InterruptedException {
		logger.info("*******Started TC005*******");
		RestAssured.baseURI = "https://reqres.in/api/users/2";
		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET, "/api/users/"+id);

		JsonPath jsonPathEvaluator = response.jsonPath();

		String id = jsonPathEvaluator.get("[0].id");
		response = httpRequest.request(Method.DELETE, "/delete/"+id); //Pass ID to Delete

		Thread.sleep(5000);
	}
	
	@Test
	void CheckResponseBody() {

		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains("Succesfully deleted Record"), false);
	}
	
	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode(); // Getting Status Code
		Assert.assertEquals(statusCode, 204);
	}
	
	@Test
	void checkResponseTime() {
		long responseTime = response.getTime(); // Getting Status Time
		Assert.assertTrue(responseTime < 2000);

	}

	@Test
	void checkStatusLine() {
		String statusLine = response.getStatusLine(); // Getting Status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 204 No Content");
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

	@AfterClass
	void tearDown() {
		logger.info("******** Finished TC005********");
	}

}
