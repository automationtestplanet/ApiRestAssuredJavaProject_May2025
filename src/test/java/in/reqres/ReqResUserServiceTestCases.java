package in.reqres;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqResUserServiceTestCases {
	@Test
	public void getAllUsers() {

		Response response = RestAssured.given()
				.queryParam("page", "2")
				.when()
				.get("https://reqres.in/api/users").then()
				.extract().response();
		
		Assert.assertEquals(response.getStatusCode(), 200);		
		Assert.assertNotNull(response.body().asString());		
		Assert.assertTrue(response.jsonPath().get("page").toString().contains("2"));
		
//		System.out.println(response.getStatusCode());		
//		response.body().prettyPrint();
	}
	
	@Test
	public void getSingleUser() {

		Response response = RestAssured.given()
				.pathParam("id", "2")
				.when()
				.get("https://reqres.in/api/users/{id}").then().extract().response();
		
		Assert.assertEquals(response.getStatusCode(), 200);		
		Assert.assertNotNull(response.body().asString());		
		Assert.assertTrue(response.jsonPath().get("data.id").toString().contains("2"));
	}
	
	@Test
	public void postUser() {		
		String requestBoy = "{\r\n"
				+ "    \"name\": \"Vennkat\",\r\n"
				+ "    \"job\": \"Automation Test Engineer\"\r\n"
				+ "}";

		Response response = RestAssured.given()
//				.header("Content-Type","application/json")
				.header("Accept","*/*")
				.header("x-api-key","reqres-free-v1")
				.contentType(ContentType.JSON)
				.body(requestBoy)
				.when()
				.post("https://reqres.in/api/users")
				.then().extract().response();
		
		Assert.assertEquals(response.getStatusCode(), 201);		
		Assert.assertNotNull(response.body().asString());	
		
		Assert.assertTrue(response.jsonPath().get("name").toString().equals("Vennkat"));
		Assert.assertTrue(response.jsonPath().get("job").toString().equals("Automation Test Engineer"));
		Assert.assertNotNull(response.jsonPath().get("id").toString());
	}
	
	@Test
	public void putUser() {		
		String requestBoy = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"Manual Test Engineer\"\r\n"
				+ "}";

		Response response = RestAssured.given()
				.pathParam("id", "2")
				.header("Accept","*/*")
				.header("x-api-key","reqres-free-v1")
				.contentType(ContentType.JSON)
				.body(requestBoy)
				.when()
				.put("https://reqres.in/api/users/{id}")
				.then().extract().response();
		
		Assert.assertEquals(response.getStatusCode(), 200);		
		Assert.assertNotNull(response.body().asString());	
		
		Assert.assertTrue(response.jsonPath().get("name").toString().equals("morpheus"));
		Assert.assertTrue(response.jsonPath().get("job").toString().equals("Manual Test Engineer"));
	}
	
	@Test
	public void patchUser() {		
		String requestBoy = "{\r\n"
				+ "    \"job\": \"Quality Assurance Engineer\"\r\n"
				+ "}";

		Response response = RestAssured.given()
				.pathParam("id", "2")
				.header("Accept","*/*")
				.header("x-api-key","reqres-free-v1")
				.contentType(ContentType.JSON)
				.body(requestBoy)
				.when()
				.patch("https://reqres.in/api/users/{id}")
				.then().extract().response();
		
		Assert.assertEquals(response.getStatusCode(), 200);		
		Assert.assertNotNull(response.body().asString());	
		response.body().prettyPrint();
		
		Assert.assertTrue(response.jsonPath().get("job").toString().equals("Quality Assurance Engineer"));
	}
	
	
	@Test
	public void deleteUser() {		
		Response response = RestAssured.given()
				.pathParam("id", "2")
				.header("x-api-key","reqres-free-v1")
				.when()
				.delete("https://reqres.in/api/users/{id}")
				.then().extract().response();
		
		Assert.assertEquals(response.getStatusCode(), 204);	
	}

}
