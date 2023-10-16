import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_GET_Request {

	@Test
	void googlemaptest() {

		// Specify base URI
		RestAssured.baseURI = "https://maps.googleapis.com";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		Response response = httpRequest.request(Method.GET, "/maps/api/place/nearbysearch/xml?location=33.8670522,151.1957362&radius=1500%type=supermarket&key=AlzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" + responseBody);

		
		String contenttype	= response.header("Content-Type"); //Capture Details from header from response
		System.out.println("Content type is:" +contenttype);
		Assert.assertEquals(contenttype, "application/xml; charset=UTF-8");
		
		
		String contentencoding	= response.header("Content-Encoding"); //Capture Details of Content-Encoding header
		System.out.println("Content type is:" +contentencoding);
		Assert.assertEquals(contentencoding, "gzip");
		
		
		
		
	}

}
