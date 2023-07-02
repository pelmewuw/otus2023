package extensions;

import com.codeborne.selenide.Configuration;
import driver.AppiumSelenideDriver;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;


public class AppiumExtension implements BeforeAllCallback {
  @Override
  public void beforeAll(ExtensionContext extensionContext) throws Exception {
    Configuration.browserSize = null;
    Configuration.browser = AppiumSelenideDriver.class.getName();
    Configuration.timeout = Long.parseLong(System.getProperty("run_timeout","10000"));
  }
}
