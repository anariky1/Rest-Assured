import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.annotations.Test;


public class basics3 {
	
	@Test
	public void AddandDeletePlace(){
		//provide base url
		   RestAssured.baseURI="https://maps.googleapis.com";
		   String b="{"+
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
					"}";
		   Response res=given().
		   queryParam("key","AIzaSyD6kVupa2gfMfeWakLMc3YAyFQEtASNGys").
		   body(b).
			when().
			post("maps/api/place/add/json").
			then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
			.body("status", equalTo("OK")).
			extract().response();
		   
		   //grab the place id from response
		   String responseString = res.asString();
		   System.out.println(responseString);
		   JsonPath js = new JsonPath(responseString);
		  String placeid =js.get("place_id");
		  System.out.println(placeid);
		  
		  //place this place id in the delete request
		  given().
		  queryParam("key","AIzaSyD6kVupa2gfMfeWakLMc3YAyFQEtASNGys").
		  body("{"+
             "\"place_id\": \""+placeid+"\""+
             "}").
          when().
          post("maps/api/place/delete/json").
          then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
          body("status",equalTo("OK"));
          
             		
          
		   

	}

}
