package e2e.tests.api;

import e2e.api.models.CourierCredentials;
import e2e.api.steps.CourierSteps;
import e2e.base.BaseApiTest;
import e2e.generators.TestDataGenerator;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

@Epic("Яндекс.Самокат")
@Feature("Курьеры")
public class LoginCourierTest extends BaseApiTest {

    private CourierSteps courierSteps = new CourierSteps();

    @Test(description = "Курьер может авторизоваться")
    @Story("Логин курьера")
    @Severity(SeverityLevel.CRITICAL)
    public void courierCanLogin() {
        CourierCredentials testCourier = TestDataGenerator.generateRandomCourier();

        courierSteps.create(testCourier).statusCode(201);

        int courierId = courierSteps.login(
                        CourierCredentials.fromLogin(testCourier.getLogin(), testCourier.getPassword()))
                .statusCode(200)
                .body("id", notNullValue())
                .extract().path("id");

        courierSteps.delete(courierId);
    }

    @Test(description = "Неверный логин возвращает ошибку")
    @Story("Логин курьера")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithWrongLoginReturnsError() {
        CourierCredentials testCourier = TestDataGenerator.generateRandomCourier();
        courierSteps.create(testCourier).statusCode(201);

        int courierId = courierSteps.login(
                        CourierCredentials.fromLogin(testCourier.getLogin(), testCourier.getPassword()))
                .extract().path("id");

        courierSteps.login(CourierCredentials.fromLogin("nonexistent_" + System.currentTimeMillis(), testCourier.getPassword()))
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));

        courierSteps.delete(courierId);
    }

    @Test(description = "Неверный пароль возвращает ошибку")
    @Story("Логин курьера")
    @Severity(SeverityLevel.NORMAL)
    public void loginWithWrongPasswordReturnsError() {
        CourierCredentials testCourier = TestDataGenerator.generateRandomCourier();
        courierSteps.create(testCourier).statusCode(201);

        int courierId = courierSteps.login(
                        CourierCredentials.fromLogin(testCourier.getLogin(), testCourier.getPassword()))
                .extract().path("id");

        courierSteps.login(CourierCredentials.fromLogin(testCourier.getLogin(), "wrong_password_" + System.currentTimeMillis()))
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));

        courierSteps.delete(courierId);
    }
}