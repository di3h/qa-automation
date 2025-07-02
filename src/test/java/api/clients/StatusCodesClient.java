package api.clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class StatusCodesClient {

    private final static String BASE_URL = "https://the-internet.herokuapp.com";

    public Response getStatusCodeResponse(int code) {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .when()
                .get("/status_codes/" + code)
                .then()
                .extract()
                .response();
    }

}
