import org.hamcrest.CoreMatchers;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;


public class Basics {

	@Test
	public  void test1() {
		
		//provide base url
		RestAssured.baseURI="https://maps.googleapis.com";
		
		given().
		       param("location","-33.8670522,151.1957362").
		       param("radius","1500").
		       param("key","AIzaSyD6kVupa2gfMfeWakLMc3YAyFQEtASNGys").
		       //header("ddd","fsddsds").
		       //cookie("","").
		       //body()
	     when().
		       get("/maps/api/place/nearbysearch/json").
		       //you are validating response header type - CONTENT TYPE	
		       then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
		       //.body("results[0].geometry.location.lat",CoreMatchers.equalTo("-33.8688197"));
		       .body("results[0].name", CoreMatchers.equalTo("Sydney")).and()
		       .body("results[0].place_id", CoreMatchers.equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and()
		       .header("server", "scaffolding on HTTPServer2");


	}

}
