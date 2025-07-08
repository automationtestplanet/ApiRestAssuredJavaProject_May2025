package in.reqres;

import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;

public class BaseTest {

	@BeforeTest(alwaysRun = true)
	public void setBaseUrl() {
		RestAssured.baseURI = "https://reqres.in";
	}

}
