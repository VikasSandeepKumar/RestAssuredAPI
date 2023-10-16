package datadriventesting;

import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.junit.runner.Request;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_AddNewEmployees {

	@Test(dataProvider = "empdataprovider")
	void addNewEmployees(String ename, String eage, String esal) {

		RestAssured.baseURI = "https://reqres.in/api";

		RequestSpecification httpRequest = RestAssured.given();

		// here we created data which can send along with the post request
		JSONObject requestParams = new JSONObject();

		requestParams.put("name", ename);
		requestParams.put("salary", esal);
		requestParams.put("age", eage);

		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-type", "application/json", "");

		// Add the JSon to the body of the request
		httpRequest.body(requestParams.toJSONString());

		// Post Request
		Response response = httpRequest.request(Method.POST, "/create");

		// Capture response body to perform validations
		String responseBody = response.getBody().asString();

		System.out.println("Response Body : " + responseBody);

		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(esal), true);
		Assert.assertEquals(responseBody.contains(eage), true);

		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

	@DataProvider(name = "empdataprovider")
	String[][] getEmpData() throws IOException {
		
		//Read data from Excel
		String path = System.getProperty("user.dir") + "src/test/java/datadriventesting/empdate.xlsx";

		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colnum = XLUtils.getCellCount(path, "Sheet1", 1);

		String empdate[][] = new String[rownum][colnum];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 1; j < colnum; j++) {
				empdate[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}

		// String empdata[][]= { {"abc123", "30000", "40"}, {"xyz123", "40000", "55"} };

		return (getEmpData());
	}
}
