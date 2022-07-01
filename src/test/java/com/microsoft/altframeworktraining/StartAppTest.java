package com.microsoft.altframeworktraining;

import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


import com.microsoft.appcenter.appium.Factory;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import org.junit.rules.TestWatcher;

public class StartAppTest {
    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    private static EnhancedAndroidDriver<MobileElement> driver;

    public static EnhancedAndroidDriver<MobileElement> startApp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "foo");
        capabilities.setCapability(MobileCapabilityType.APP, "app-release_20220527.apk");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 7913);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

        URL url = new URL("http://localhost:4723/wd/hub");

        return Factory.createAndroidDriver(url, capabilities);
    }

    @BeforeClass
    public static void before() throws Exception {
        driver = startApp();
        Thread.sleep(10000);
    }


    @Test
    public void verifyApplicationIsLaunch() throws MalformedURLException, InterruptedException {
        Thread.sleep(10000);
        boolean status=driver.findElementByXPath("//android.widget.Button/android.widget.TextView[@text='Get Started']").isDisplayed();
        driver.label("Application Status"+status);
        Assert.assertTrue(status);
    }

    @Test()
    public void verifyGetStartedButtonDisplayed() {
        String buttonText =driver.findElementByXPath("//android.widget.Button/android.widget.TextView[@text='Get Started']").getText();
        driver.label("Button Text"+buttonText);
        Assert.assertEquals(buttonText,"Get Started");
    }

    @AfterClass
    public static void after() throws Exception {
        if (driver != null) {
            driver.label("Stopping App");
            driver.quit();
        }
    }
}
