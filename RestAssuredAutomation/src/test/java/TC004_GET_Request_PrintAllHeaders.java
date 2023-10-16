import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_GET_Request_PrintAllHeaders {

	@Test
	public void GetWeatherDetails() {

		// Specify base URI
		RestAssured.baseURI = "https://reqres.in";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		Response response = httpRequest.request(Method.GET,
				"/api/users?page=2");

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" + responseBody);
		
		Headers allheaders = response.headers(); //Capture all the headers from response
		
		for(Header header:allheaders)
		{
			System.out.println(header.getName()+""+header.getValue());
			
		}

	}

}
