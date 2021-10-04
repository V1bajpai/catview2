package tatto;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.*;

public class TestVaibhav {
		@Test
		public void verifyJSONArrayResponse() {
		System.setProperty("webdriver.chrome.driver", "C:\\browserdriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		
		//1. Get Request operations
		JsonArray jsonArray = new JsonArray();
		jsonArray = given().baseUri("https://api.thecatapi.com")
				.basePath("/v1/images/search")
				.get().as(JsonArray.class);

		//2. Read data and producing o/p on browser
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
			String urlcat=jsonObject.get("url").getAsString();
			System.out.println(urlcat);
			driver.manage().window().maximize();
			driver.get(urlcat);
		}
		System.out.println("Do you want to another search: ");
		System.out.println("Press-1 for Continue");
		System.out.println("Press-2 for Stop");
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		if(n==1) {
			verifyJSONArrayResponse();
		}		
	}
		
		
		// Post Request for voting
		@Test
		public void RegistrationSuccessful()
		{		
			RestAssured.baseURI ="https://api.thecatapi.com";
			String payload = "{\r\n"
					+ "  \"image_id\": \"asf2\",\r\n"
					+ "  \"sub_id\": \"my-user-1234\",\r\n"
					+ "  \"value\": 1\r\n"
					+ "}";
			RequestSpecification request = RestAssured.given();
			request.header("Content-type", "application/json");
			
			Response response = request.body(payload).post("/v1/votes");
			
			int statusCode = response.getStatusCode();
			System.out.println("Response status code is: "+ statusCode);
			Assert.assertEquals(200, statusCode);
		}
		

}
