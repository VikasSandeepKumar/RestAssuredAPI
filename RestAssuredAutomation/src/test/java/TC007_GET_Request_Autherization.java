import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveAuthProvider;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_GET_Request_Autherization {

	@Test
	public void Autherization() {

		// Specify base URI
		RestAssured.baseURI = "https://www.google.com";
		
		//Basic Authentication
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("Tools QA");
		authScheme.setPassword("Passowrd");
		
		RestAssured.authentication=authScheme;
		
		
		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		Response response = httpRequest.request(Method.GET, "/");

		// Status Code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is:" + statusCode);
		Assert.assertEquals(statusCode, 200);

		// Status line verification
		String statusLine = response.getStatusLine();
		System.out.println(statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" + responseBody);

	}

}
