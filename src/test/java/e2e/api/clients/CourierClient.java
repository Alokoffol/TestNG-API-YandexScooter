package e2e.api.clients;

import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.ValidatableResponse;
import e2e.api.endpoints.Endpoints;
import e2e.api.models.CourierCredentials;

import static io.restassured.RestAssured.given;

public class CourierClient {

    private static final RestAssuredConfig LONG_TIMEOUT_CONFIG = RestAssuredConfig.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam("http.connection.timeout", 30000)
                    .setParam("http.socket.timeout", 30000));

    private static final RestAssuredConfig DEFAULT_CONFIG = RestAssuredConfig.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam("http.connection.timeout", 10000)
                    .setParam("http.socket.timeout", 10000));

    public ValidatableResponse create(CourierCredentials credentials) {
        return given()
                .config(LONG_TIMEOUT_CONFIG)
                .header("Content-type", "application/json")
                .body(credentials)
                .when()
                .post(Endpoints.BASE_URL + Endpoints.COURIER_CREATE)
                .then();
    }

    public ValidatableResponse login(CourierCredentials credentials) {
        return given()
                .config(DEFAULT_CONFIG)
                .header("Content-type", "application/json")
                .body(credentials)
                .when()
                .post(Endpoints.BASE_URL + Endpoints.COURIER_LOGIN)
                .then();
    }

    public ValidatableResponse delete(int id) {
        return given()
                .config(DEFAULT_CONFIG)
                .header("Content-type", "application/json")
                .when()
                .delete(Endpoints.BASE_URL + Endpoints.COURIER_DELETE + id)
                .then();
    }
}