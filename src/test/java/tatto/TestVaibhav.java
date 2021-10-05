package tatto;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TestVaibhav {
	private static final String json = null;

	@Test
	public void verifyJSONArrayResponse() {

		// 1. Get Request operations
		JsonArray jsonArray = new JsonArray();
		jsonArray = given().baseUri("https://api.thecatapi.com").basePath("/v1/images/search").get()
				.as(JsonArray.class);

		// 2. Read data and producing o/p on browser
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
			String urlcat = jsonObject.get("url").getAsString();
			System.out.println(urlcat);
		}

	}

	// Post Request for voting
	@Test
	public void voteTocat() {
		RestAssured.baseURI = "https://api.thecatapi.com";
		File file = new File("C:\\Users\\Vaibhav\\eclipse-workspace\\testingfor\\src\\test\\resources\\schema.json");

		RequestSpecification request = RestAssured.given();
		request.header("Content-type", "application/json");

		Response response = request.body(file).post("/v1/votes");

		int statusCode = response.getStatusCode();
		ResponseBody msg= response.getBody();
		System.out.println(msg);
		// Testcase-1
		Assert.assertEquals(200, statusCode);

	}

		@Test
		public void testjsonpath() {
			String endpoint = "https://api.thecatapi.com/v1/images/search";
			String json = given().when().get(endpoint).asString();
			System.out.println(json);
			String firstArrayItem = JsonPath.read(json, "$[0]").toString();
			System.out.println(firstArrayItem);
	
	}
	
	
//	@Test
//	public void postRequest() {
//		RestAssured.baseURI = "https://api.thecatapi.com";
//		File file = new File("C:\\Users\\Vaibhav\\eclipse-workspace\\testingfor\\src\\test\\resources\\schema.json");
//
//		given().body(file).with().contentType("application/json").then().expect().statusCode(200).when()
//				.post("/v1/votes");
//	}

//		@Test
//		public void vote() {
//			File file = new File("C:\\Users\\Vaibhav\\eclipse-workspace\\testingfor\\src\\test\\resources\\schema.json");
//			RestAssured.baseURI = "https://api.thecatapi.com";
//			RequestSpecification httpRequest = RestAssured.given();
//			Response response = httpRequest.body(file).post("/v1/votes");
//
//			// First get the JsonPath object instance from the Response interface
//			JsonPath jsonPathEvaluator = response.jsonPath();
//			int msg=jsonPathEvaluator.get("id");
//			System.out.println(msg);
//		}
}
