package driver.impl;

import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Config;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public interface IDriver {
  String REMOTE_URL = System.getProperty("webdriver.remote.url");
  //  boolean HEADLESS = Boolean.valueOf(System.getProperty("webdriver.headless"));

  public RemoteWebDriver newDriver();

  default URL getRemoteUrl() {
    try {
      return new URL(REMOTE_URL);
    } catch (MalformedURLException e) {
      return null;
    }
  }

  default void downloadLocalWebDriver(DriverManagerType driverType) throws DriverTypeNotSupported {
    Config wdmConfig = WebDriverManager.globalConfig();
    wdmConfig.setAvoidBrowserDetection(true);

    String browserVersion = System.getProperty("browser.version", "114");

    if (!browserVersion.isEmpty()) {
      switch (driverType) {
        case CHROME:
          wdmConfig.setChromeDriverVersion(browserVersion);
          break;
        case  FIREFOX:
          wdmConfig.setFirefoxVersion(browserVersion);
          break;
        case OPERA:
          wdmConfig.setOperaDriverVersion(browserVersion);
          break;
        default:
          throw new DriverTypeNotSupported(driverType.name());
      }
    }
    WebDriverManager.getInstance(driverType).setup();
  }
}
