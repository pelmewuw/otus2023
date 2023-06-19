package com.otus.hooks;

import com.google.inject.Inject;
import com.otus.di.GuiseScooped;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;


public class Hooks {
  private WebDriver driver;
  @Before
  public void init() throws MalformedURLException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(CapabilityType.BROWSER_NAME, System.getProperty("browser", "chrome"));
    capabilities.setCapability(CapabilityType.BROWSER_VERSION, System.getProperty("browser.version", "112.0"));
    capabilities.setCapability("enableVNC", true); //для просмотра сессии
    driver = new RemoteWebDriver(URI.create("http://127.0.0.1:8080/wd/hub").toURL(), capabilities);
  }
  @Inject
  private GuiseScooped guiseScooped;

  @After
  public void close(){
    if (guiseScooped.driver != null){
      guiseScooped.driver.close();
      guiseScooped.driver.quit();
    }
  }
}
