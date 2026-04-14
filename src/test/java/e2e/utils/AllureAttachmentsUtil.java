package e2e.utils;

import io.qameta.allure.Allure;

public class AllureAttachmentsUtil {

    /*Этот метод добавляет текстовую информацию в Allure отчёт,
     чтобы при просмотре отчёта было видно, какие данные использовались в тесте.*/
    public static void attachText(String name, String content) {
        Allure.addAttachment(name, "text/plain", content);
    }

    public static void attachJson(String name, String json) {
        Allure.addAttachment(name, "application/json", json);
    }
}