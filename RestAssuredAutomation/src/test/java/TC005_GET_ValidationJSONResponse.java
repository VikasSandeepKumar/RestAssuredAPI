import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_GET_ValidationJSONResponse {

	@Test
	public void GetWeatherDetails() {

		// Specify base URI
		RestAssured.baseURI = "https://www.google.com";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		Response response = httpRequest.request(Method.GET,"/doodles");

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" + responseBody);
		
		Assert.assertEquals(responseBody.contains("doodles"), true);
	}

}
