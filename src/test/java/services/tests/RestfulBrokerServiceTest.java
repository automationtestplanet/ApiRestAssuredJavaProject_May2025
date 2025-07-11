package services.tests;

import data.DataUtils;
import io.restassured.RestAssured;
import models.UpdateBookingDetails;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import services.RestfulBrokerService;

import java.util.LinkedHashMap;
import java.util.Map;

public class RestfulBrokerServiceTest {

    RestfulBrokerService restfulBrokerService = new RestfulBrokerService();

    @BeforeTest(alwaysRun = true)
    public void setBaseUrl() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void updateBookingsTest() {
        Map<String, String> pathParam = new LinkedHashMap<>();
        pathParam.put("id", "1");
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Accept", "*/*");
        String updateBookingsRequest = DataUtils.readDataFromJson("TestJsonFiles//UpdateBookings.json");
        var response = restfulBrokerService.updateBookingDetails("admin", "password123", pathParam, headers, updateBookingsRequest, 200);
        Assert.assertEquals(response.get("firstname"), "Venkat");
        Assert.assertEquals(response.get("lastname"), "BV");
    }
}
