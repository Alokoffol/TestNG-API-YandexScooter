package e2e.utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "colorVariants")
    public static Object[][] colorVariants() {
        return new Object[][]{
                {new String[]{"BLACK"}, "только BLACK"},
                {new String[]{"GREY"}, "только GREY"},
                {new String[]{"BLACK", "GREY"}, "оба цвета"},
                {new String[]{}, "без цвета"},
                {null, "null вместо цвета"}
        };
    }

    @DataProvider(name = "invalidCourierData")
    public static Object[][] invalidCourierData() {
        return new Object[][]{
                {null, "pass", "Name", "Нет логина"},
                {"login", null, "Name", "Нет пароля"},
                {"login", "pass", null, "Нет имени"},
                {"", "pass", "Name", "Пустой логин"},
                {"login", "", "Name", "Пустой пароль"}
        };
    }
}