package tatto;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
//import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import java.util.*;

public class TestVaibhav {
		@Test
		public void verifyJSONArrayResponse() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vaibhav\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		JsonArray jsonArray = new JsonArray();
		jsonArray = given().baseUri("https://api.thecatapi.com")
				.basePath("/v1/images/search")
				.get().as(JsonArray.class);

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
}
