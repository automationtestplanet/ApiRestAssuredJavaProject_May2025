package services;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.NewUserData;
import models.PartialUpdateUserData;
import models.SingleUserData;
import models.UpdatedUserData;
import api.ApiRequests;

public class UserService extends ApiRequests {

    private final String USER_SERVICE = "/api/users";

    public Map getAllUsersDetails(Map<String, String> queryParams, int expectedStatusCode) {
        try {
            return getWithQueryParams(USER_SERVICE, queryParams).then().statusCode(expectedStatusCode).and().extract()
                    .as(Map.class);
        } catch (Exception e) {
            System.out.println("Exception occured while getting all users: " + e.getMessage());
            return null;
        }
    }

    public Map getAllUsersDetails(Map<String, String> headers, Map<String, String> queryParams, int expectedStatusCode) {
        try {
            return getWithHeadersAndQueryParams(USER_SERVICE, headers,queryParams).then().statusCode(expectedStatusCode).and().extract()
                    .as(Map.class);
        } catch (Exception e) {
            System.out.println("Exception occured while getting all users: " + e.getMessage());
            return null;
        }
    }

    public SingleUserData getSingleUserDetails(Map<String, String> pathParameters, int expectedStatusCode) {
        try {
            ObjectMapper objMap = new ObjectMapper();
            LinkedHashMap<String, String> responseObject = getWithPathParams(USER_SERVICE, pathParameters).then()
                    .statusCode(expectedStatusCode).and().extract().jsonPath().getJsonObject("data");
            String responseString = objMap.writeValueAsString(responseObject);
            return objMap.readValue(responseString, SingleUserData.class);
        } catch (Exception e) {
            System.out.println("Exception occured while getting Single user: " + e.getMessage());
            return null;
        }

    }

    public NewUserData addUserDetails(Map<String, String> headers, Object requestBody, int statusCode) {
        try {
            return postWithHeaders(USER_SERVICE, headers, requestBody).then().statusCode(statusCode).and().extract()
                    .as(NewUserData.class);
        } catch (Exception e) {
            System.out.println("Exception occurred while Creating the User: " + e.getMessage());
            return null;
        }

    }

    public UpdatedUserData updateUserDetails(Map<String, String> pathParameters, Map<String, String> headers,
                                             Object requestBody, int statusCode) {
        try {
            return putWithPathParametersAndHeaders(USER_SERVICE, pathParameters, headers, requestBody).then()
                    .statusCode(statusCode).and().extract().as(UpdatedUserData.class);
        } catch (Exception e) {
            System.out.println("Exception occured while Updating the User: " + e.getMessage());
            return null;
        }

    }

    public PartialUpdateUserData updatePartialUserData(Map<String, String> pathParameters, Map<String, String> headers,
                                                       String requestBody, int statusCode) {
        try {
            return patchWithPathParametersAndHeaders(USER_SERVICE, pathParameters, headers, requestBody).then()
                    .statusCode(statusCode).and().extract().as(PartialUpdateUserData.class);
        } catch (Exception e) {
            System.out.println("Exception occured while partial Updateing the User: " + e.getMessage());
            return null;
        }
    }

    public String deleteUser(Map<String, String> pathParameters, Map<String, String> headers, int statusCode) {
        return deleteWithPathParametersAndHeaders(USER_SERVICE, pathParameters, headers).then().statusCode(statusCode)
                .extract().asString();
    }

}
