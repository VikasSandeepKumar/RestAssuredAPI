import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_Request {

	@Test
	void RegistrationSuccessful() {

		// Specify base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Request Payload sending along with POST Request
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "JohnXYZ");
		requestParams.put("LastName", "XYZ");
		requestParams.put("UserName", "John");
		requestParams.put("Password", "John123");
		requestParams.put("Email", "John@gmail.com");

		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(requestParams.toJSONString()); //attach above data to the request

		// Response Object
		Response response = httpRequest.request(Method.POST, "/register");
		

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" + responseBody);

		// Status Code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is:" + statusCode);
		Assert.assertEquals(statusCode, 201);

		String successCode =response.jsonPath().get("SuccessCode");
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");

	}

}
