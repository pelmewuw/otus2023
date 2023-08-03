package com.otus.di;

import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

@ScenarioScoped
public class GuiseScooped {
  //  public WebDriver driver = new DriverFactory().getDriver();
  public WebDriver driver;
  public RemoteWebDriver init(String browserName) throws MalformedURLException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
    capabilities.setCapability(CapabilityType.BROWSER_VERSION, System.getProperty("browser.version", "112.0"));
    capabilities.setCapability("enableVNC", true); //для просмотра сессии
    return new RemoteWebDriver(URI.create(System.getProperty("grid.url","http://127.0.0.1:8080/wd/hub")).toURL(), capabilities);
  }
}
