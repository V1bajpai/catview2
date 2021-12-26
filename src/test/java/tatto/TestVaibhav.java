package tatto;

import static io.restassured.RestAssured.*;
//import org.junit.jupiter.api.Test;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.*;

//public class TestVaibhav {
//		@Test
//		public void verifyJSONArrayResponse() {
//		System.setProperty("webdriver.chrome.driver", "C:\\browserdriver\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//
//		JsonArray jsonArray = new JsonArray();
//		jsonArray = given().baseUri("https://api.thecatapi.com")
//				.basePath("/v1/images/search")
//				.get().as(JsonArray.class);
//
//		for (int i = 0; i < jsonArray.size(); i++) {
//			JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
//			String urlcat=jsonObject.get("url").getAsString();
//			System.out.println(urlcat);
//			driver.manage().window().maximize();
//			driver.get(urlcat);
//
//		}
////		System.out.println("Do you want to another search: ");
////		System.out.println("Press-1 for Continue");
////		System.out.println("Press-2 for Stop");
////		Scanner sc=new Scanner(System.in);
////		int n=sc.nextInt();
////		if(n==1) {
////			verifyJSONArrayResponse();
////		}
//	}
//}

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
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

		// Testcase- There should be only one node
		List<String> arrayItem = Collections.singletonList(JsonPath.read(json, "$").toString());
		Assert.assertEquals(1, arrayItem.size());

		// Testcase- Array's item should be present
		String firstArrayItem = JsonPath.read(json, "$[0]").toString();
		System.out.println(firstArrayItem);
		Assert.assertNotEquals(0, firstArrayItem.length());

		// Testcase- breeds array should be empty
		List<String> breeds = Collections.singletonList(JsonPath.read(json, "$[0].breeds").toString());
		Assert.assertNotEquals(0, breeds.size());

		// Testcase- id should not be empty
		String id = JsonPath.read(json, "$[0].id").toString();
		Assert.assertNotEquals(0, id.length());

		// Testcase- Url should not be empty
		String url = JsonPath.read(json, "$[0].url").toString();
		Assert.assertNotEquals(0, url.length());

		// Testcase- width should be present
		int width = JsonPath.read(json, "$[0].width");
		Assert.assertNotEquals(0, width);

		// Testcase- height should be present
		int height = JsonPath.read(json, "$[0].height");
		Assert.assertNotEquals(0, height);
		System.out.println("Github Mgmt");
	}

//	@Test
//	@DisplayName("Get request- For your uploaded images")
//	public void getYourImage(){
//		String caturl="https://api.thecatapi.com/v1/images";
//		String json = given().when().get(caturl).asString();
//
//		//Testcase- Url should be present
//		String url = JsonPath.read(json, "$[0].url").toString();
//		Assert.assertNotEquals(0, url.length());
//
//		//Testcase- created_at field should be present
//		String created_at = JsonPath.read(json, "$[0].created_at").toString();
//		Assert.assertNotEquals(0, created_at.length());
//
//		//Testcase- original_filename should be present
//		String original_filename = JsonPath.read(json, "$[0].original_filename").toString();
//		Assert.assertNotEquals(0, original_filename.length());
//	}

	@Test
	@DisplayName("Get request: For getting image by image_id")
	public void imageById(){
		String imgUrl= "https://api.thecatapi.com/v1/images/bcs";
		String json = given().when().get(imgUrl).asString();

		//Testcase- Id should be present
		String id = JsonPath.read(json, "$.id").toString();
		Assert.assertEquals("bcs", id);

		// Testcase- Url should not be empty
		String url = JsonPath.read(json, "$.url").toString();
		Assert.assertNotEquals(0,url.length());

		// Testcase- width should be present
		int width = JsonPath.read(json, "$.width");
		Assert.assertNotEquals(0,width);

		// Testcase- height should be present
		int height = JsonPath.read(json, "$.height");
		Assert.assertNotEquals(0,height);
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
		// Testcase- status code should be OK-200
		Assert.assertEquals(200, statusCode);
		System.out.println("Github Mgmt");

	}

	@Test
	@DisplayName("Get request: Analysis Report")
	public void getAnalysisReport(){
		String analysisUrl="https://api.thecatapi.com/v1/images/bcs/analysis";
		String json = given().when().get(analysisUrl).asString();

		//Testcase- Image id should be same
		String imageid= JsonPath.read(json, "$[0].image_id");
		Assert.assertEquals("bcs", imageid);

		//Testcase- Vendor should be present
		String vendor= JsonPath.read(json, "$[0].vendor");
		Assert.assertEquals("AWS Rekognition", vendor);

		//Testcase-
		String behavior = JsonPath.read(json, "$[0].labels[0].Name").toString();
		Assert.assertEquals("Pet", behavior);

		//Testcase-
		String species = JsonPath.read(json, "$[0].labels[1].Name").toString();
		Assert.assertEquals("Mammal", species);

		//Testcase-
		String typeofcreature = JsonPath.read(json, "$[0].labels[2].Name").toString();
		Assert.assertEquals("Animal", typeofcreature);

		//Testcase-
		String typeOfAnimal = JsonPath.read(json, "$[0].labels[3].Name").toString();
		Assert.assertEquals("Cat", typeOfAnimal);

		//Testcase-
		String ageCategory = JsonPath.read(json, "$[0].labels[4].Name").toString();
		Assert.assertEquals("Kitten", ageCategory);

		//Testcase-
		String cloth = JsonPath.read(json, "$[0].labels[5].Name").toString();
		Assert.assertEquals("Blanket", cloth);

		//Testcase-
		String manxtype = JsonPath.read(json, "$[0].labels[6].Name").toString();
		Assert.assertEquals("Manx", manxtype);

		//Testcase-
		String Furtype = JsonPath.read(json, "$[0].labels[7].Name").toString();
		Assert.assertEquals("Fur", Furtype);

		//Testcase-
		String ferretname = JsonPath.read(json, "$[0].labels[8].Name").toString();
		Assert.assertEquals("Ferret", ferretname);

		//Testcase-
		String status = JsonPath.read(json, "$[0].labels[9].Name").toString();
		Assert.assertEquals("Sleeping", status);

		System.out.println("Tre===");
		System.out.println("Tre===");



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
