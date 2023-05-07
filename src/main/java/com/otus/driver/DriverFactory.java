package com.otus.driver;

import com.otus.driver.impl.ChromeWebDriver;
import com.otus.driver.impl.FirefoxWebDriver;
import com.otus.driver.impl.OperaWebDriver;
import com.otus.exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Locale;

public class DriverFactory implements IDriverFactory {

  //  private String browserType = System.getProperty("browser").toLowerCase(Locale.ROOT);

  @Override
  public EventFiringWebDriver getDriver(String browserName) {
    switch (browserName) {
      case "chrome": {
        WebDriverManager.chromedriver().setup();
        return new EventFiringWebDriver(new ChromeWebDriver().newDriver());
      }
      case "firefox": {
        WebDriverManager.firefoxdriver().setup();
        return new EventFiringWebDriver(new FirefoxWebDriver().newDriver());
      }
      case "opera": {
        WebDriverManager.operadriver().setup();
        return new EventFiringWebDriver(new OperaWebDriver().newDriver());
      }
      default:
        try {
          throw new DriverTypeNotSupported(browserName);
        } catch (DriverTypeNotSupported ex) {
          ex.printStackTrace();
          return null;
        }
    }
  }
}
