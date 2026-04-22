# TestNG-API-YandexScooter

## Описание

API тесты сервиса **Яндекс.Самокат** (https://qa-scooter.praktikum-services.ru).

Проект демонстрирует:
- ✅ Тестирование API курьеров (создание, логин, удаление)
- ✅ Тестирование API заказов (создание, получение списка, отмена)
- ✅ Параметризованные тесты (цвета самоката)
- ✅ TestNG с параллельным запуском
- ✅ Allure отчеты

## Технологии

| Технология | Назначение |
|------------|------------|
| Java 11 | Язык программирования |
| TestNG | Фреймворк для тестирования |
| RestAssured | HTTP клиент для API |
| Allure | Отчеты о тестировании |
| GitHub Actions | CI/CD |

## Запуск тестов локально

```bash
mvn clean test
```
## Структура проекта
```
src/test/java/e2e/
├── api/clients/      # HTTP клиенты
├── api/endpoints/    # URL эндпоинты
├── api/models/       # DTO модели
├── api/steps/        # Шаги с Allure
├── base/             # Базовый класс
├── generators/       # Генерация данных
├── listeners/        # TestNG слушатели
└── tests/            # Тесты
```

## GitHub Pages

Отчет Allure доступен по ссылке:

https://alokoffol.github.io/TestNG-API-YandexScooter/
