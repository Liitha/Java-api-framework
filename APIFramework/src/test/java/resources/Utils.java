package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.StandardSocketOptions;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import javax.swing.text.html.StyleSheet;

public class Utils {

	public static RequestSpecification req;
	private String oauth2Token;


	public RequestSpecification requestSpecification() throws IOException
	{
		String authtoken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjNFRTkzMkM5MTIwRDk1RTZFODQzOTBBQTRGRkUzREQ4MTgwNzYzNEEiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJQdWt5eVJJTmxlYm9RNUNxVF80OTJCZ0hZMG8ifQ.eyJuYmYiOjE3MTcxNzE0ODYsImV4cCI6MTcxNzI1Nzg4NiwiaXNzIjoiaHR0cHM6Ly9zdGFmZmFjY291bnQuZGV2LmFwcHJpdmVyLmNvcnAvaWRlbnRpdHkiLCJhdWQiOlsiaHR0cHM6Ly9zdGFmZmFjY291bnQuZGV2LmFwcHJpdmVyLmNvcnAvaWRlbnRpdHkvcmVzb3VyY2VzIiwiU3RhZmZBcGlDbGFpbXMiXSwiY2xpZW50X2lkIjoiYXBwcml2ZXJJbnRlcm5hbFN3YWdnZXIiLCJzdWIiOiJsaWtpdGhhcEBvcGVudGV4dGNvcnBvcmF0aW9uLm1haWwub25taWNyb3NvZnQuY29tIiwiYXV0aF90aW1lIjoxNzE3MTcxNDg2LCJpZHAiOiJBY3RpdmVEaXJlY3RvcnkiLCJDdXN0b21lcklEIjoiMTAwMDIiLCJlbWFpbCI6Imxpa2l0aGFwQG9wZW50ZXh0Y29ycG9yYXRpb24ubWFpbC5vbm1pY3Jvc29mdC5jb20iLCJyb2xlIjpbIlNUQUZGX1BSSUNJTkdfQURNSU4iLCJTVEFGRl9TQUxFUyIsIlNUQUZGX0JJTExJTkciLCJTVEFGRl9ERVZFTE9QRVIiLCJTVEFGRl9IT1NUSU5HIiwiU1RBRkZfU1VQUE9SVCIsIlNUQUZGX1VTRVIiXSwic2NvcGUiOlsiU3RhZmZBcGlDbGFpbXMiXSwiYW1yIjpbImV4dGVybmFsIl19.Y7rA8Rpea5PCQ61t6aT1Y_5zVDDGSWj_E0GqHaqM6xd8g-7RZEKXbKFNayoPmhXohYJnosxI1RD095CeYEPRyunDSRP1_DZIKnVSuEugCZy1CEMd2ZtcJrH9niSshvF2mafy2sbC5gtFnft0mivjV9qngjtoQS1GL1uPNBYsUCQE8hRKQZpcgQgcUd99h1gn8ycn3lRi3Y2x3U7lJvYXOW_J9dlsrLX1U-buoV7OXdN5mgQxt2xi-8_JFyGxe1Y0YNChcIoqenhX2iT9L77ZhHGRWEOV3DF0k-I29tqVub-FTbeO4c4fGJySHbyRPZi7Hjn9QQBZhQUALYZK7E3JAg";

		oauth2Token = authtoken;


		// This is rahulshetty academy base URI
//		if(req==null)
//		{
//		PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
//		 req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
//				 .addFilter(RequestLoggingFilter.logRequestTo(log))
//				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
//		.setContentType(ContentType.JSON).build();
//		 return req;
//		}
//		return req;


		if(req==null)
		{
			System.out.println("req is null");
			System.out.println(getGlobalValue("baseURL"));
			PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
			req= new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
					.addHeader("Authorization", "Bearer " + oauth2Token)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build()
					.relaxedHTTPSValidation();

			return req;
		}
		return req;

	}
		

	
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop =new Properties();
		FileInputStream fis =new FileInputStream("C:\\Users\\likithap\\Learnings\\APIFramework\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	
	public String getJsonPath(Response response,String key)
	{
		  String resp=response.asString();
		JsonPath  js = new JsonPath(resp);
		return js.get(key).toString();
	}


}
