package services;

import api.ApiRequests;

import java.util.Map;

public class RestfulBrokerService extends ApiRequests {

    public final static String RESTFUL_BROKER_SERVICE = "/booking";

    public Map updateBookingDetails(String userName, String password, Map<String, String> pathParameters, Map<String, String> headers,
                                    Object requestBody, int statusCode) {
        try {
            return putWithAuthenticationPathParametersAndHeaders(RESTFUL_BROKER_SERVICE, userName, password, pathParameters, headers, requestBody).then()
                    .statusCode(statusCode).and().extract().response().as(Map.class);
        } catch (Exception e) {
            System.out.println("Exception occured while Updating the User: " + e.getMessage());
            return null;
        }

    }
}
