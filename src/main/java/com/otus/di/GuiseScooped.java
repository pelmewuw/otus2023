package com.otus.di;

import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class GuiseScooped {
  //  public WebDriver driver = new DriverFactory().getDriver();
  public WebDriver driver;
}
