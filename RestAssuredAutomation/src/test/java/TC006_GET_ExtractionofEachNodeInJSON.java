import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_GET_ExtractionofEachNodeInJSON {
	
	
	@Test
	public void GetWeatherDetails() {

		// Specify base URI
		RestAssured.baseURI = "https://reqres.in";

		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		Response response = httpRequest.request(Method.GET,"/api/users/2");

		JsonPath jPath = response.jsonPath();
		
		 /*System.out.println(jPath.get("id"));
		 System.out.println(jPath.get("email"));
		 System.out.println(jPath.get("first_name"));
		 System.out.println(jPath.get("last_name"));*/
		
		 
		 
		
	}

}
