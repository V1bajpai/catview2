package tatto;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.*;

public class TestVaibhav {
		@Test
		public void verifyJSONArrayResponse() {
		System.setProperty("webdriver.chrome.driver", "C:\\browserdriver\\chromedriver.exe");
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
//		System.out.println("Do you want to another search: ");
//		System.out.println("Press-1 for Continue");
//		System.out.println("Press-2 for Stop");
//		Scanner sc=new Scanner(System.in);
//		int n=sc.nextInt();
//		if(n==1) {
//			verifyJSONArrayResponse();
//		}
	}
}
