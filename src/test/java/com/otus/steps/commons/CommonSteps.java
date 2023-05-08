package com.otus.steps.commons;

import com.google.inject.Inject;
import com.otus.di.GuiseScooped;
import com.otus.driver.DriverFactory;
import com.otus.pages.MainPage;
import io.cucumber.java.ru.Пусть;


public class CommonSteps {

  @Inject
  public GuiseScooped guiseScooped;
  @Inject
  private DriverFactory driverFactory;
  @Inject
  private MainPage mainPage;

  @Пусть("Открыть браузер {string}")
  public void openBrowser(String browserName){
    guiseScooped.driver = driverFactory.getDriver(browserName);
    mainPage.open();
  }

}
