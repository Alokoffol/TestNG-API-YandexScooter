package e2e.generators;

import e2e.api.models.CourierCredentials;
import e2e.api.models.Order;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataGenerator {

    private static final Random RANDOM = new Random();

    public static CourierCredentials generateRandomCourier() {
        String login = "courier_" + System.currentTimeMillis() + "_" + RANDOM.nextInt(10000);
        String password = "pass_" + RANDOM.nextInt(10000);
        String firstName = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
        return new CourierCredentials(login, password, firstName);
    }


    private static final String[] FIRST_NAMES = {"Иван", "Пётр", "Алексей", "Дмитрий", "Сергей", "Анна", "Мария", "Елена"};
    private static final String[] LAST_NAMES = {"Иванов", "Петров", "Сидоров", "Смирнов", "Кузнецов"};
    private static final String[] ADDRESSES = {
            "Абрамцевская улица", "Автозаводский 3-й проезд", "Ланинский переулок",
            "Лианозовский проезд", "Элеваторная улица", "Дмитрия Разумовского улица",
            "Внуковское шоссе", "Тверская улица", "Арбат", "Кутузовский проспект"
    };
    private static final String[] METRO_STATIONS = {
            "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"
    };
    private static final String[] PHONE_PREFIXES = {
            "+7 900", "+7 901", "+7 902", "+7 903", "+7 904", "+7 905", "+7 906", "+7 909"
    };
    private static final String[] COMMENTS = {
            "Позвонить за 5 минут", "Оставить у двери", "Домофон не работает",
            "Осторожно, собака", "Без комментариев", "Срочно!"
    };

    private static String generateRandomPhone() {
        String prefix = PHONE_PREFIXES[RANDOM.nextInt(PHONE_PREFIXES.length)];
        int number = 1000000 + RANDOM.nextInt(9000000);
        return prefix + " " + number;
    }

    private static String generateRandomDeliveryDate() {
        LocalDate date = LocalDate.now().plusDays(ThreadLocalRandom.current().nextInt(1, 31));
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private static int generateRandomRentTime() {
        return 1 + RANDOM.nextInt(7);  // от 1 до 7
    }

    public static Order generateRandomOrder() {
        String firstName = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
        String address = ADDRESSES[RANDOM.nextInt(ADDRESSES.length)];
        String metroStation = METRO_STATIONS[RANDOM.nextInt(METRO_STATIONS.length)];
        String phone = generateRandomPhone();
        int rentTime = generateRandomRentTime();
        String deliveryDate = generateRandomDeliveryDate();
        String comment = COMMENTS[RANDOM.nextInt(COMMENTS.length)];

        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment);
    }

}