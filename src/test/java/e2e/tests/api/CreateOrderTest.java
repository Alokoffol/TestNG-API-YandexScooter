package e2e.tests.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import e2e.api.models.Order;
import e2e.api.steps.OrderSteps;
import e2e.base.BaseApiTest;
import e2e.generators.TestDataGenerator;
import e2e.utils.AllureAttachmentsUtil;
import io.qameta.allure.*;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

@Epic("Яндекс.Самокат")
@Feature("Заказы")
public class CreateOrderTest extends BaseApiTest {

    private OrderSteps orderSteps = new OrderSteps();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private int trackId = -1;

    @DataProvider(name = "colorVariants")
    public Object[][] colorVariants() {
        return new Object[][]{
                {new String[]{"BLACK"}, "только BLACK"},
                {new String[]{"GREY"}, "только GREY"},
                {new String[]{"BLACK", "GREY"}, "оба цвета"},
                {new String[]{}, "без цвета"},
                {null, "null вместо цвета"}
        };
    }

    @AfterMethod
    public void tearDown() {
        if (trackId != -1) {
            orderSteps.cancel(trackId);
        }
    }

    @Test(dataProvider = "colorVariants",
            description = "Создание заказа с разными вариантами цветов")
    @Story("Создание заказа")
    @Severity(SeverityLevel.CRITICAL)
    public void createOrderWithDifferentColors(String[] colors, String description) {
        Allure.addAttachment("Вариант цветов", description);

        Order order = TestDataGenerator.generateRandomOrder();
        order.setColor(colors);

        String orderJson = gson.toJson(order);
        AllureAttachmentsUtil.attachJson("Тело запроса заказа", orderJson);

        ValidatableResponse response = orderSteps.create(order);

        response.statusCode(201)
                .body("track", notNullValue());

        trackId = response.extract().path("track");
        Allure.addAttachment("Created", "Track ID: " + trackId);
    }
}