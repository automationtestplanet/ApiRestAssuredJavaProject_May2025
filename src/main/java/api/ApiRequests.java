package api;

import java.io.ObjectInputFilter;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.config.Config;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiRequests {

    public Response getWithQueryParams(String endPoint, Map<String, String> queryParams) {
        return RestAssured.given().queryParams(queryParams).when().get(endPoint).then().extract().response();
    }

    public Response getWithHeadersAndQueryParams(String endPoint, Map<String, String> headers, Map<String, String> queryParams) {
        return RestAssured.given().queryParams(queryParams).headers(headers).when().get(endPoint).then().extract().response();
    }

    public Response getWithPathParams(String endPoint, Map<String, String> pathParams) {
        String pathParameter = "";
        for (String pathParam : pathParams.keySet()) {
            pathParameter = pathParam;
        }

        return RestAssured.given().pathParams(pathParams).when().get(endPoint + "/{" + pathParameter + "}").then()
                .extract().response();
    }

    public Response postWithHeaders(String endPoint, Map<String, String> headers, Object requestBody) {
        return RestAssured.given().headers(headers).contentType(ContentType.JSON).body(requestBody).when()
                .post(endPoint).then().extract().response();
    }

    public Response putWithPathParametersAndHeaders(String endPoint, Map<String, String> pathParams,
                                                    Map<String, String> headers, Object requestBody) {

        String pathParameter = "";
        for (String pathParam : pathParams.keySet()) {
            pathParameter = pathParam;
        }

        return RestAssured.given().pathParams(pathParams).headers(headers).contentType(ContentType.JSON)
                .body(requestBody).when().put(endPoint + "/{" + pathParameter + "}").then().extract().response();

    }

    public Response patchWithPathParametersAndHeaders(String endPoint, Map<String, String> pathParams,
                                                      Map<String, String> headers, String requestBody) {

        String pathParameter = "";
        for (String pathParam : pathParams.keySet()) {
            pathParameter = pathParam;
        }

        return RestAssured.given().pathParams(pathParams).headers(headers).contentType(ContentType.JSON)
                .body(requestBody).when().patch(endPoint + "/{" + pathParameter + "}").then().extract().response();
    }

    public Response deleteWithPathParametersAndHeaders(String endPoint, Map<String, String> pathParams,
                                                       Map<String, String> headers) {

        String pathParameter = "";
        for (String pathParam : pathParams.keySet()) {
            pathParameter = pathParam;
        }

        return RestAssured.given().pathParams(pathParams).headers(headers).when()
                .delete(endPoint + "/{" + pathParameter + "}").then().extract().response();
    }

    public Response putWithAuthenticationPathParametersAndHeaders(String endPoint, String userName, String password, Map<String, String> pathParams,
                                                                  Map<String, String> headers, Object requestBody) {
        String pathParameter = "";
        for (String pathParam : pathParams.keySet()) {
            pathParameter = pathParam;
        }
        return RestAssured.given().auth().preemptive().basic(userName, password).pathParams(pathParams).headers(headers).contentType(ContentType.JSON)
                .body(requestBody).when().put(endPoint + "/{" + pathParameter + "}").then().extract().response();

    }

}
