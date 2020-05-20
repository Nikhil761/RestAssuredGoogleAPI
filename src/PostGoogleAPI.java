
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Payload.Payload;


public class PostGoogleAPI {

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response=given().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(Payload.addPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().log().all().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", equalTo("Apache/2.4.18 (Ubuntu)"))
		.extract().response().asString();
		//System.out.println(response);
		
		//Taking string as a input and convert into json. Parsing json response 
		JsonPath js=new JsonPath(response);
		String placeid=js.getString("place_id");
		System.out.println("Created place id is "+placeid);
		
		//get API
		given().queryParam("place_id",placeid).queryParam("key","qaclick123").header("Content-Type","application/json")
		.when().get("/maps/api/place/get/json")
		// equalto will work only hamcrest class is imported
		.then().assertThat().log().all().statusCode(200).body("name", equalTo("Frontline house"));
		

	}

}
