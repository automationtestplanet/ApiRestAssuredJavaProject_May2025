package services.tests;

import data.DataUtils;
import in.reqres.BaseTest;
import io.restassured.RestAssured;
import models.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import services.UserService;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserServiceTest extends BaseTest {

    UserService userService = new UserService();

    @BeforeTest(alwaysRun = true)
    public void setBaseUrl() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void getAllUsersTest() {
        Map<String, String> queryParams = new LinkedHashMap<>();
        queryParams.put("page", "2");
        Map allUsersData = userService.getAllUsersDetails(queryParams, 200);
        Assert.assertTrue(allUsersData.get("page").toString().contains(queryParams.get("page")));
    }

    @Test
    public void getSingleUserTest() {
        Map<String, String> pathParam = new LinkedHashMap<>();
        pathParam.put("id", "2");
        SingleUserData singleUserData = userService.getSingleUserDetails(pathParam, 200);
        Assert.assertEquals(Integer.parseInt(pathParam.get("id")), singleUserData.getId());
        Assert.assertNotNull(singleUserData.getEmail());
    }

    @Test
    public void addNewUserTest() {
        NewUserDetails newUserDetails = new NewUserDetails();
        newUserDetails.setName("Vennkat");
        newUserDetails.setJob("Automation Test Engineer");
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Accept", "*/*");
        headers.put("x-api-key", "reqres-free-v1");
        NewUserData newUserData = userService.addUserDetails(headers, newUserDetails, 201);
        Assert.assertEquals(newUserData.getName(), newUserDetails.getName());
        Assert.assertEquals(newUserData.getJob(), newUserDetails.getJob());
        Assert.assertNotNull(newUserData.getId());
    }

    @Test
    public void updateUserTest() {
//        NewUserDetails newUserDetails = DataUtils.getNewUserDetails(System.getProperty("user.dir") + "//src//test//resources//TestJsonFiles//UpdateUser.json");
        String newUserDetails = DataUtils.readDataFromJson("TestJsonFiles//UpdateUser.json");
        Map<String, String> pathParam = new LinkedHashMap<>();
        pathParam.put("id", "2");
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Accept", "*/*");
        headers.put("x-api-key", "reqres-free-v1");
        UpdatedUserData updatedUserData = userService.updateUserDetails(pathParam, headers, newUserDetails, 200);
        assert newUserDetails != null;
//        Assert.assertEquals(updatedUserData.getName(), newUserDetails.getName());
//        Assert.assertEquals(updatedUserData.getJob(), newUserDetails.getJob());
        Assert.assertTrue(newUserDetails.contains(updatedUserData.getName()));
        Assert.assertTrue(newUserDetails.contains(updatedUserData.getJob()));
    }

    @Test
    public void updatePartialUserDataTest() {
        String partialUpdateDetails = DataUtils.readDataFromJson("TestJsonFiles//PartialUpdateUser.json");
        String requestBoy = String.format(partialUpdateDetails, "Quality Assurance Engineer");
        Map<String, String> pathParam = new LinkedHashMap<>();
        pathParam.put("id", "2");
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Accept", "*/*");
        headers.put("x-api-key", "reqres-free-v1");
        PartialUpdateUserData partialUpdateData = userService.updatePartialUserData(pathParam, headers, requestBoy, 200);
        Assert.assertEquals(partialUpdateData.getJob(), "Quality Assurance Engineer");
    }

    @Test
    public void deleteUserTest() {
        Map<String, String> pathParam = new LinkedHashMap<>();
        pathParam.put("id", "2");
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("x-api-key", "reqres-free-v1");
        String responseStr = userService.deleteUser(pathParam, headers, 204);
        Assert.assertEquals(responseStr, "");
    }

}
