package stepDefinations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.List;


import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import PojoTransportRuleAll.SharedState;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apiguardian.api.API;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	private String oauth2Token;

	private String ID;
	


	@And("transportRuleAllAPI payload")
	public void transport_rule_all_api_payload() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("this is transport rule payload method");
		initializeRequest();
		res.body(data.DisableAllTransportRule());
//		res= given().spec(requestSpecification()).header("Authorization", "Bearer " + oauth2Token).contentType("application/json").body(data.DisableAllTransportRule()).relaxedHTTPSValidation();
	}

	@Given("{string} request")
	public void get_request(String request){
		System.out.println("get request");
		Response res= given().header("Authorization", "Bearer " + oauth2Token).relaxedHTTPSValidation().when().get(request).then().extract().response();
	}
	@Given("Add Place Payload with {string}  {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecification())
				.body(data.addPlacePayLoad(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions
//constructor will be called with value of resource which you pass
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());


		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());

	}

	public void initializeRequest() throws IOException {
		if(req == null){
			req = requestSpecification();

		}
		res = given().spec(req);
	}


	@When("user calls {string} with {string} https request")
	public void user_calls_with_https_request(String resource, String RequestMethod) throws IOException {
	APIResources TransportRuleAPI= APIResources.valueOf(resource);  // value of resource-> in resource parameter we are passing "transportRuleAllAPI" -> value of we turn the value from enum
	System.out.println(TransportRuleAPI.getResource());

	if(RequestMethod.equalsIgnoreCase("POST")) {
		response = res.when().post(TransportRuleAPI.getResource());
		System.out.println("POST API----");
		System.out.println(response.asString());
		ID = response.jsonPath().getString("data.id");
		System.out.println("Extracted ID: " + ID);
		SharedState.getInstance().setId(ID);

//		response= res.when().post(TransportRuleAPI.getResource());
//		initializeRequest(); // Initialize request with token
//		res.body(data.DisableAllTransportRule()); // Add payload for POST
		//----
//		response = res.when().post(TransportRuleAPI.getResource());
//		System.out.println("POST API----");
//		System.out.println(response.asString());
		//----
	}
	else if(RequestMethod.equalsIgnoreCase("GET")) {
		initializeRequest();
		System.out.println("before get ---");
		System.out.println(TransportRuleAPI.getResourceWithId(ID));
//		response = res.given().spec(new RequestSpecification()).baseUri("").when()
		response = res.when().get(TransportRuleAPI.getResourceWithId(ID));
		System.out.println("GET API---");
		System.out.println(response.asString());
	}
		//----
//		initializeRequest(); // Initialize request with token
//		response = res.when().get(TransportRuleAPI.getResourceWithId(ID));
//
//		String Transportruleallrespose = response.asString();
//		System.out.println("GET API---");
//		System.out.println(Transportruleallrespose);

//		if(response.jsonPath().get("data.id") != null){
//			ID = response.jsonPath().getString("data.id");
//		}
//		else {
//			ID = null;
//		}
		//----
//		ID = response.jsonPath().getString("data.id"); // data.id is because of the api body that i have thats why
		SharedState.getInstance().setId(ID);

		assertNotNull("ID should not be null", ID);
		System.out.println("Extracted ID: " + ID);
	}




	@Then("the response should have {string}")
	public void the_response_should_have(String key) {
		// Write code here that turns the phrase above into concrete action
		System.out.println("get id function key"+ key);
		ID = SharedState.getInstance().getId();

		ID = response.jsonPath().getString(key);
		assertNotNull("ID should not be null", ID );
	}

	@And("Stored id is available")
	public void stored_id_is_available() {
		ID = SharedState.getInstance().getId();
		System.out.println("ID IS "+ID);
		assertNotNull("ID should not be null for the subsequent test", ID);
		// Write code here that turns the phrase above into concrete actions
		}

	@Given("Stored get id is available")
	public void Stored_get_id_is_available() {
		ID = SharedState.getInstance().getId();
		System.out.println("this is get api");
		System.out.println("ID IS "+ID);
		assertNotNull("ID should not be null for the subsequent test", ID);
		// Write code here that turns the phrase above into concrete actions
	}

	@When("user calls the {string} endpoint with stored {string}")
	public void user_calls_the_endpoint_with_stored(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions

	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);


	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(getJsonPath(response, keyValue), Expectedvalue);
	}


	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {

		// requestSpec
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}


	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		// Write code here that turns the phrase above into concrete actions

		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}

	@Given("Connect to access token")
	public void Connect_to_access_token() {
		// Write code here that turns the phrase above into concrete actions
		String authtoken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjNFRTkzMkM5MTIwRDk1RTZFODQzOTBBQTRGRkUzREQ4MTgwNzYzNEEiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJQdWt5eVJJTmxlYm9RNUNxVF80OTJCZ0hZMG8ifQ.eyJuYmYiOjE3MTcxNzE0ODYsImV4cCI6MTcxNzI1Nzg4NiwiaXNzIjoiaHR0cHM6Ly9zdGFmZmFjY291bnQuZGV2LmFwcHJpdmVyLmNvcnAvaWRlbnRpdHkiLCJhdWQiOlsiaHR0cHM6Ly9zdGFmZmFjY291bnQuZGV2LmFwcHJpdmVyLmNvcnAvaWRlbnRpdHkvcmVzb3VyY2VzIiwiU3RhZmZBcGlDbGFpbXMiXSwiY2xpZW50X2lkIjoiYXBwcml2ZXJJbnRlcm5hbFN3YWdnZXIiLCJzdWIiOiJsaWtpdGhhcEBvcGVudGV4dGNvcnBvcmF0aW9uLm1haWwub25taWNyb3NvZnQuY29tIiwiYXV0aF90aW1lIjoxNzE3MTcxNDg2LCJpZHAiOiJBY3RpdmVEaXJlY3RvcnkiLCJDdXN0b21lcklEIjoiMTAwMDIiLCJlbWFpbCI6Imxpa2l0aGFwQG9wZW50ZXh0Y29ycG9yYXRpb24ubWFpbC5vbm1pY3Jvc29mdC5jb20iLCJyb2xlIjpbIlNUQUZGX1BSSUNJTkdfQURNSU4iLCJTVEFGRl9TQUxFUyIsIlNUQUZGX0JJTExJTkciLCJTVEFGRl9ERVZFTE9QRVIiLCJTVEFGRl9IT1NUSU5HIiwiU1RBRkZfU1VQUE9SVCIsIlNUQUZGX1VTRVIiXSwic2NvcGUiOlsiU3RhZmZBcGlDbGFpbXMiXSwiYW1yIjpbImV4dGVybmFsIl19.Y7rA8Rpea5PCQ61t6aT1Y_5zVDDGSWj_E0GqHaqM6xd8g-7RZEKXbKFNayoPmhXohYJnosxI1RD095CeYEPRyunDSRP1_DZIKnVSuEugCZy1CEMd2ZtcJrH9niSshvF2mafy2sbC5gtFnft0mivjV9qngjtoQS1GL1uPNBYsUCQE8hRKQZpcgQgcUd99h1gn8ycn3lRi3Y2x3U7lJvYXOW_J9dlsrLX1U-buoV7OXdN5mgQxt2xi-8_JFyGxe1Y0YNChcIoqenhX2iT9L77ZhHGRWEOV3DF0k-I29tqVub-FTbeO4c4fGJySHbyRPZi7Hjn9QQBZhQUALYZK7E3JAg";
//		requestSpecification.auth().oauth2(authtoken);
		oauth2Token = authtoken;

		//given().baseUri("https://staffaccount.dev.appriver.corp/identity/connect/authorize").body("")
	}

	@Given("Entering the get method")
	public void Entering_get_meth(){
		System.out.println("entering get method");
	}

	@When("Make TransportruleAll api request")
	public void make_transportrule_all_api_request() {
		Response TPALL=given().header("Authorization", "Bearer " + oauth2Token)
				.body("{\n" +
						"  \"data\": {\n" +
						"    \"type\": \"disableRestoreAllTransportRules\",\n" +
						"    \"attributes\": {\n" +
						"  \"operation\": \"Disable\",\n" +
						"      \"requestor\": \"abalila@appriver.com\",\n" +
						"      \"requestId\": \"7c065850-1a1c-43de-8d9a-95bf85eb2e32\"\n" +
						"    }\n" +
						"  }\n" +
						"}").relaxedHTTPSValidation()
				.when().post("https://o365securityaudit.dev.appriver.corp/api/v1/disableRestoreAllTransportRules").then().statusCode(200).extract().response();
		String TPALLRES= TPALL.asString();
		System.out.println(TPALLRES);

	}


//	@When("Make a api request")
//	public void makeapireq() {
//		Response responsepostapi = given()
//				.header("Authorization", "Bearer " + oauth2Token).body("{\n" +
//						"\n" +
//						"  \"data\": {\n" +
//						"\n" +
//						"    \"type\": \"disableRestoreSingleTransportRules\",\n" +
//						"\n" +
//						"    \"attributes\": {\n" +
//						"\n" +
//						"      \"ruleName\": \"rule1\",\n" +
//						"\n" +
//						"      \"operation\": \"restore\",\n" +
//						"\n" +
//						"      \"requestor\": \"abalila@appriver.com\",\n" +
//						"\n" +
//						"      \"requestId\": \"bafcb262-981c-46c7-a425-9e3e35f4fcd5\"\n" +
//						"\n" +
//						"    }\n" +
//						"\n" +
//						"  }\n" +
//						"\n" +
//						"}").relaxedHTTPSValidation()
//				.when().post("https://o365securityaudit.dev.appriver.corp/api/v1/disableRestoreSingleTransportRules").then().statusCode(200).extract().response();
//
//		String finalrespose = responsepostapi.asString();
//		System.out.println(finalrespose);
//	}


	@Then("Get the {string} from the {string} https request")
	public void get_the_from_the_https_request(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
}


