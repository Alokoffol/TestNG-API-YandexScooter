package e2e.api.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import e2e.api.clients.OrderClient;
import e2e.api.models.Order;

public class OrderSteps {
    private final OrderClient client = new OrderClient();

    @Step("Создание заказа")
    public ValidatableResponse create(Order order) {
        return client.create(order);
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrders() {
        return client.getOrders();
    }

    @Step("Отмена заказа с track ID: {trackId}")
    public ValidatableResponse cancel(int trackId) {
        return client.cancel(trackId);
    }
}