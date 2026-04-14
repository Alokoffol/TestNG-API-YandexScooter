package e2e.listeners;

import io.qameta.allure.Allure;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Allure.addAttachment("Test failed", "Test: " + result.getName());
        System.out.println(" Тест упал: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Тест пройден: " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Запуск теста: " + result.getName());
    }
}