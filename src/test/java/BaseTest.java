import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.concurrent.TimeUnit;

 // Ege Bartu Teker

public class BaseTest {
    protected static AppiumDriver<MobileElement> appiumDriver;
    protected boolean localAndroid = true;
    Logger logger = LogManager.getLogger();

    @BeforeScenario
    public void beforeScenario() throws MalformedURLException {
        System.out.println("----- Test starts on local -----");
        if(localAndroid){
            System.out.println("----- Android test starts on local -----");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.m.qr");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.m.qr.home.onboarding.ui.OnBoardingActivity");
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,3000);
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            appiumDriver = new AndroidDriver<>(url,desiredCapabilities);
            appiumDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        }else{
            System.out.println("----- iOS test starts on local -----");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, "lokalinizde bağlı olan telefonun udid bilgisini gir");
            desiredCapabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "bundle id bilgisini gir");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "lokaldeki telefonun ismini gir");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "lokaldeki telefon version bilgisini gir");
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            appiumDriver = new IOSDriver(url, desiredCapabilities);
            appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

    @AfterScenario
    public void after(){
        appiumDriver.quit();
    }

}
