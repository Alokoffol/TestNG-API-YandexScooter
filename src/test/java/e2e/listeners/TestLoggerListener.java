package e2e.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestLoggerListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("=".repeat(50));
        System.out.println("[START] " + result.getName());
        System.out.println("   Время: " + new java.util.Date());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long duration = result.getEndMillis() - result.getStartMillis();
        System.out.println("[PASS] " + result.getName() + " (" + duration + " ms)");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[FAIL] " + result.getName());
        System.out.println("   Причина: " + result.getThrowable().getMessage());
    }
}