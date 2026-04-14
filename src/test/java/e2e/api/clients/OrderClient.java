package e2e.api.clients;

import e2e.api.models.Order;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.ValidatableResponse;
import e2e.api.endpoints.Endpoints;

import static io.restassured.RestAssured.given;

public class OrderClient {

    private static final RestAssuredConfig LONG_TIMEOUT_CONFIG = RestAssuredConfig.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                    .setParam("http.connection.timeout", 30000)
                    .setParam("http.socket.timeout", 30000));


    public ValidatableResponse create(Order order) {
        return given()
                .config(LONG_TIMEOUT_CONFIG)
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(Endpoints.BASE_URL + Endpoints.ORDER_CREATE)
                .then();
    }

    public ValidatableResponse getOrders() {
        return given()
                .config(LONG_TIMEOUT_CONFIG)
                .get(Endpoints.BASE_URL + Endpoints.ORDER_GET_LIST)
                .then();
    }

    public ValidatableResponse cancel(int trackId) {
        return given()
                .config(LONG_TIMEOUT_CONFIG)
                .header("Content-type", "application/json")
                .when()
                .put(Endpoints.BASE_URL + Endpoints.ORDER_CANCEL + "?track=" + trackId)
                .then();
    }
}