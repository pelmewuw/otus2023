package driver;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumSelenideDriver implements WebDriverProvider {

  @Nonnull
  @Override
  public WebDriver createDriver(@Nonnull Capabilities capabilities) {
    UiAutomator2Options options = new UiAutomator2Options();
    options.merge(capabilities);
    options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
    options.setPlatformName(System.getProperty("platform_name","Android"));
    options.setDeviceName(System.getProperty("device_name","otus"));
    options.setPlatformVersion(System.getProperty("platform_version","5.1"));

    //options.setApp(System.getProperty("user.dir") + "\\app\\Andy-253457-d7ad79.apk");
    options.setAppPackage("com.pyankoff.andy");
    options.setAppActivity(".MainActivity");


    try {
      return new AndroidDriver(new URL(System.getProperty("base_url","http://127.0.0.1:4723/wd/hub")), options);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }
}
