package e2e.tests.api;

import e2e.api.models.CourierCredentials;
import e2e.api.steps.CourierSteps;
import e2e.base.BaseApiTest;
import e2e.generators.TestDataGenerator;
import e2e.utils.AllureAttachmentsUtil;
import io.qameta.allure.*;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

@Epic("Яндекс.Самокат")
@Feature("Курьеры")
public class CreateCourierTest extends BaseApiTest {

    private CourierSteps courierSteps = new CourierSteps();

    @Test(description = "Курьера можно создать")
    @Story("Создание курьера")
    @Severity(SeverityLevel.CRITICAL)
    public void courierCanBeCreated() {
        CourierCredentials courier = TestDataGenerator.generateRandomCourier();

        courierSteps.create(courier)
                .statusCode(201)
                .body("ok", equalTo(true));

        AllureAttachmentsUtil.attachText("Создаваемый курьер",
                "Логин: " + courier.getLogin() + "\n" +
                        "Пароль: " + courier.getPassword() + "\n" +
                        "Имя: " + courier.getFirstName());

        ValidatableResponse loginResponse = courierSteps.login(
                CourierCredentials.fromLogin(courier.getLogin(), courier.getPassword()));

        int courierId = loginResponse.extract().path("id");
        Allure.addAttachment("Created", "Курьер ID: " + courierId);

        courierSteps.delete(courierId);
    }

    @Test(description = "Нельзя создать двух одинаковых курьеров")
    @Story("Создание курьера")
    @Severity(SeverityLevel.NORMAL)
    public void cannotCreateDuplicateCourier() {

        CourierCredentials courier = TestDataGenerator.generateRandomCourier();

        courierSteps.create(courier).statusCode(201);

        ValidatableResponse loginResponse = courierSteps.login(
                CourierCredentials.fromLogin(courier.getLogin(), courier.getPassword()));
        int courierId = loginResponse.extract().path("id");

        courierSteps.create(courier)
                .statusCode(409)
                .body("message", containsString("Этот логин уже используется"));

        courierSteps.delete(courierId);
    }

    @Test(description = "Создание курьера без обязательных полей")
    @Story("Создание курьера")
    @Severity(SeverityLevel.NORMAL)
    public void cannotCreateCourierWithoutLogin() {

        CourierCredentials invalidCourier = new CourierCredentials(null, "pass123", "Name");

        courierSteps.create(invalidCourier)
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }
}