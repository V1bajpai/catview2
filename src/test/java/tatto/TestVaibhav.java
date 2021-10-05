package tatto;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TestVaibhav {
	private static final String json = null;

	@Test
	@DisplayName("Get request for searching url")
	public void testjsonpath() {
		String endpoint = "https://api.thecatapi.com/v1/images/search";
		String json = given().when().get(endpoint).asString();

		// Testcase-1 There should be only one node
		List<String> arrayItem = Collections.singletonList(JsonPath.read(json, "$").toString());
		Assert.assertEquals(1, arrayItem.size());

		// Testcase-2 Array's item should be present
		String firstArrayItem = JsonPath.read(json, "$[0]").toString();
		System.out.println(firstArrayItem);
		Assert.assertNotEquals(0, firstArrayItem.length());

		// Testcase-3 breeds array should be empty
		List<String> breeds = Collections.singletonList(JsonPath.read(json, "$[0].breeds").toString());
		Assert.assertNotEquals(0, breeds.size());

		// Testcase-4 id should not be empty
		String id = JsonPath.read(json, "$[0].id").toString();
		Assert.assertNotEquals(0, id.length());

		// Testcase-5 Url should not be empty
		String url = JsonPath.read(json, "$[0].url").toString();
		Assert.assertNotEquals(0, url.length());

		// Testcase-6 width should be present
		int width = JsonPath.read(json, "$[0].width");
		Assert.assertNotEquals(0, width);

		// Testcase-7 height should be present
		int height = JsonPath.read(json, "$[0].height");
		Assert.assertNotEquals(0, height);
	}

	@Test
	@DisplayName("Get request- For your uploaded images")
	public void getYourImage(){
		String caturl="https://api.thecatapi.com/v1/images";
		String json = given().when().get(caturl).asString();

		//Testcase-8 Url should be present
		String url = JsonPath.read(json, "$[0].url").toString();
		Assert.assertNotEquals(0, url.length());

		//Testcase-9 created_at field should be present
		String created_at = JsonPath.read(json, "$[0].created_at").toString();
		Assert.assertNotEquals(0, created_at.length());

		//Testcase-10 original_filename should be present
		String original_filename = JsonPath.read(json, "$[0].original_filename").toString();
		Assert.assertNotEquals(0, original_filename.length());
	}

	@Test
	@DisplayName("Post request: Vote a cat")
	public void voteTocat() {
		RestAssured.baseURI = "https://api.thecatapi.com";
		File file = new File("C:\\Users\\Vaibhav\\eclipse-workspace\\testingfor\\src\\test\\resources\\schema.json");
		RequestSpecification request = RestAssured.given();
		request.header("Content-type", "application/json");
		Response response = request.body(file).post("/v1/votes");
		int statusCode = response.getStatusCode();
		// Testcase-11
		Assert.assertEquals(200, statusCode);

	}


}

//	@Test
//	public void verifyJSONArrayResponse() {
//
//		// 1. Get Request operations
//		JsonArray jsonArray = new JsonArray();
//		jsonArray = given().baseUri("https://api.thecatapi.com").basePath("/v1/images/search").get()
//				.as(JsonArray.class);
//
//		// 2. Read data and producing o/p on browser
//		for (int i = 0; i < jsonArray.size(); i++) {
//			JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
//			String urlcat = jsonObject.get("url").getAsString();
////			System.out.println(urlcat.length());
//			if(urlcat=="") {
//				System.out.println("Not found");
//				return;
//			}
//
//		}
//
//	}
