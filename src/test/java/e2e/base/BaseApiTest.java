package e2e.base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import e2e.api.endpoints.Endpoints;
import org.testng.annotations.BeforeSuite;

public class BaseApiTest {

    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = Endpoints.BASE_URL;
        RestAssured.filters(new AllureRestAssured());
    }
}