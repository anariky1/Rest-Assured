import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;


public class Basics2 {
	
	@Test
	public  void PostData() {
		//provide base url
	   RestAssured.baseURI="https://maps.googleapis.com";
	   
	   given().
	   queryParam("key","AIzaSyD6kVupa2gfMfeWakLMc3YAyFQEtASNGys").
	   body("{"+
		  "\"location\": {"+
		  "\"lat\": -33.8669710,"+
		   "\"lng\": 151.1958750"+
		  "},"+
		 "\"accuracy\": 50,"+
		  "\"name\": \"Google Shoes!\","+
		  "\"phone_number\": \"(02) 9374 4000\","+
		  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
		 "\"types\": [\"shoe_store\"],"+
		  "\"website\": \"http://www.google.com.au/\","+
		  "\"language\": \"en-AU\""+
		"}").
		when().
		post("maps/api/place/add/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
		.body("status", equalTo("OK"));
	   
	   //create a place api and delete that
	   //i need to integrate both the test.
	   //i need to grab the place id from the place creation and send to delete api

	}
}


