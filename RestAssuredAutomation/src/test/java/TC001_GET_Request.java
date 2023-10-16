import org.junit.runner.Request;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Request {
	
	@Test
	void getDetails() {
		
		//Specify base URI
		RestAssured.baseURI="https://www.google.com";
		
		//Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response Object
		Response response = httpRequest.request(Method.GET,"/doodles");
		
		
		//Print response in console window
		String responseBody =response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		
		
		// Status Code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is:" +statusCode);
		Assert.assertEquals(statusCode, 200);
		
		
		//Status line verification
		String statusLine = response.getStatusLine();
		System.out.println(statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}

}
