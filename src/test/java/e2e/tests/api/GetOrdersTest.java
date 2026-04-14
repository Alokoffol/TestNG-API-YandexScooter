package e2e.tests.api;

import e2e.api.steps.OrderSteps;
import e2e.base.BaseApiTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

@Epic("Яндекс.Самокат")
@Feature("Заказы")
public class GetOrdersTest extends BaseApiTest {

    private OrderSteps orderSteps = new OrderSteps();

    @Test(description = "Получение списка заказов")
    @Story("Список заказов")
    @Severity(SeverityLevel.NORMAL)
    public void getOrdersListReturnsSuccess() {
        orderSteps.getOrders()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}