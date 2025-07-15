package step.definitions;

import data.DataUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import models.NewUserData;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import services.UserService;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserServiceStepDefs extends BaseStepDefinition {

    UserService userService;
    String requestBody;
    NewUserData newUserData;
    Map allUsersData;


    @Given("the user a UserService endpoint")
    public void theUserAUserServiceEndpoint() {
        userService = new UserService();
    }

    @When("the user call {string} endpoint with payload {string}")
    public void theUserCallEndpointWithPayload(String string, String requestFileName) {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Accept", "*/*");
        headers.put("x-api-key", "reqres-free-v1");
        requestBody = DataUtils.readDataFromJson(properties.getProperty("test.data.path") + "//" + requestFileName);
        newUserData = userService.addUserDetails(headers, requestBody, 201);
    }

    @Then("new user details must be created")
    public void newUserDetailsMustBeCreated() {
        Assert.assertTrue(requestBody.contains(newUserData.getName()));
        Assert.assertTrue(requestBody.contains(newUserData.getJob()));
        Assert.assertNotNull(newUserData.getId());
    }

    @When("the user call {string} endpoint with queryParam {string} and value {string}")
    public void theUserCallEndpointWithQueryParamAndValue(String string, String paramName, String paramValue) {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("x-api-key", "reqres-free-v1");

        Map<String, String> queryParams = new LinkedHashMap<>();
        queryParams.put(paramName, paramValue);
        allUsersData = userService.getAllUsersDetails(headers,queryParams, 200);
    }

    @Then("all user details must be fetched from page number {string}")
    public void allUserDetailsMustBeFetchedFromPageNumber(String pageNumber) {
        Assert.assertTrue(allUsersData.get("page").toString().contains(pageNumber));
    }

}
