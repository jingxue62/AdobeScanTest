package org.example;

import info.debatty.java.stringsimilarity.Levenshtein;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        System.out.println("Testing...");
        int numOfPics = 10;
        // Initialization, installation and connection
        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("emulator-5554")
                .setAutomationName("UIAutomator2")
                .setApp("com-adobe-scan-android.apk")
                .setAppPackage("com.adobe.scan.android")
                .setAppActivity("com.adobe.scan.android.SplashActivity");
        options.setCapability("autoGrantPermissions", true);
        AndroidDriver driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"), options
        );

        /*
        For inspection:
        This is the Json for the inspector "inspector.appiumpro.com"
                launch appium with command "appium --allow-cors"
        {
          "appium:deviceName": "emulator-5554",
          "appium:automationName": "UIAutomator2",
          "appium:app": "/Users/<username>/Projects/ocrTest/OCRTest/com.adobe.scan.android.apk",
          "platformName": "android",
          "appium:autoGrantPermissions": true
        }
        */
        try {
            // After the app started, wait for a short while (initialization)
            Thread.sleep(5000);
            // "Mobile app usage" popup dialog, click "continue"
            driver.findElement(By.id("com.adobe.scan.android:id/user_data_usage_notice_continue_button")).click();
            System.out.println("Clicked on: continue button");
            // "Welcome to Adobe Scan" popup, click "Let's go"
            Thread.sleep(2000);
            driver.findElement(By.id("com.adobe.scan.android:id/continue_button")).click();
            System.out.println("Clicked on: let's go button");
            // "Allow Adobe Scan to take pictures and record video" dialog, click "while using the app"
//            Thread.sleep(2000);
//            driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
            // "Allow Adobe Scan to send you notifications" dialog, click Allow
//            Thread.sleep(2000);
//            driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
            // Select all photos
            Thread.sleep(2000); // wait for ready
            // - click the Photo icon
            var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            var tapPoint = new Point(105, 1890);
            var tap = new Sequence(finger, 1);
            tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                    PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
            tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            tap.addAction(new Pause(finger, Duration.ofMillis(50)));
            tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(List.of(tap));
            System.out.println("Clicked on: Pictures icon");
            // - click the popup dialog, click Allow all (Adobe Scan to access)
            Thread.sleep(2000);
//            driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
            // - select all the photos
            Thread.sleep(2000); // wait for a while to load photos
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\").instance(1)")).click(); // disable crop
            System.out.println("Clicked on: disable crop");
            Thread.sleep(1000);
            for (int i = 1; i <= numOfPics; i++) {
                String accessibilityId = String.format("%d.png", i);
                driver.findElement(AppiumBy.accessibilityId(accessibilityId)).click();
                System.out.println("Clicked on: " + i + ".png");
                Thread.sleep(500);
            }

            driver.findElement(AppiumBy.accessibilityId("Done")).click();
            Thread.sleep(1000); // wait for confirmation
            // Unexpected error message dialog
            try {
                WebElement button = driver.findElement(AppiumBy.id("com.adobe.scan.android:id/dialog_positive_button"));
                button.click();
                Thread.sleep(2000);
            } catch (NoSuchElementException e) {
                System.out.println("Element not found, skipping...");
            }

            Levenshtein levenshtein = new Levenshtein();
            double totalDistance = 0.0;
            int totalExpectedTextLength = 0;

            // Scan and get tests
            for (int i = 1; i <= numOfPics; i++) {
                System.out.println("========================================");
                System.out.println("Test Case No. " + i + " :");
                // Scan the selected image
                tapPoint = new Point(970, 82);
                tap = new Sequence(finger, 1);
                tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                        PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
                tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                tap.addAction(new Pause(finger, Duration.ofMillis(50)));
                tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                driver.perform(List.of(tap));
                // Wait for a short while for the completion
                Thread.sleep(4000);
                // Click to get the result
                tapPoint = new Point(469, 257);
                tap = new Sequence(finger, 1);
                tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                        PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
                tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                tap.addAction(new Pause(finger, Duration.ofMillis(50)));
                tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                driver.perform(List.of(tap));
                // Click the note
                for (int j = 0; j < 3; j++) {
                    tapPoint = new Point(162, 2137);
                    tap = new Sequence(finger, 1);
                    tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                            PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
                    tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    tap.addAction(new Pause(finger, Duration.ofMillis(50)));
                    tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                    driver.perform(List.of(tap));
                    Thread.sleep(1000);
                }
                // getText and calculate the rate
                String outputText = "";
                String expectedText = "";
                try {
                    outputText = driver.findElement(By.id("com.android.systemui:id/edit_text")).getText();
                    System.out.println("Extracted Text: " + outputText);
                    expectedText = ResultsExpected.getOutput(i);
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    System.out.println("edit_text not found, retrying...");
                    Thread.sleep(3000);
                    // Click to refresh
                    tapPoint = new Point(453, 700);
                    tap = new Sequence(finger, 1);
                    tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                            PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
                    tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    tap.addAction(new Pause(finger, Duration.ofMillis(50)));
                    tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                    driver.perform(List.of(tap));
                    Thread.sleep(1000);
                    outputText = driver.findElement(By.id("com.android.systemui:id/edit_text")).getText();
                    System.out.println("OUTPUT: " + outputText);
                    expectedText = ResultsExpected.getOutput(i);
                }

                // Accuracy calculation
                assert expectedText != null;
                double distance = levenshtein.distance(expectedText, outputText);
                double diff_percent = distance / (double) expectedText.length() * 100;


                System.out.println("Levenshtein distance: " + distance);
                System.out.println("Accuracy (%): " + (100 - diff_percent));

                totalDistance += distance;
                totalExpectedTextLength += expectedText.length();
                // Return to Scanner
                driver.findElement(AppiumBy.id("com.android.systemui:id/done_button")).click();

                if (i == numOfPics) break;

                Thread.sleep(2000);
                // Choose the next one
                finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
                tapPoint = new Point(67, 1582);
                tap = new Sequence(finger, 1);
                tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                        PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
                tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                tap.addAction(new Pause(finger, Duration.ofMillis(50)));
                tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
                driver.perform(List.of(tap));
                Thread.sleep(2000);
            }
            System.out.println("========================================");
            double finalDistance = totalDistance / (double) totalExpectedTextLength;
            double finalAccuracy = 100 - (finalDistance / (double) numOfPics) * 100;
            System.out.println("Accuracy of final " + finalAccuracy);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}
