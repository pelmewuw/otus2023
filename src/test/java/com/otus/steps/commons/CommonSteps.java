package com.otus.steps.commons;

import com.otus.di.GuiseScooped;
import com.otus.driver.DriverFactory;
import io.cucumber.java.ru.Пусть;

import javax.inject.Inject;

public class CommonSteps {

  @Inject
  public GuiseScooped guiseScooped;

  @Пусть("Открываем браузер {string}")
  public void openBrowser(String browserName){
    guiseScooped.driver = new DriverFactory().getDriver(browserName);
  }
}
