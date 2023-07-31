package com.otus.steps.commons;

import com.google.inject.Inject;
import com.otus.di.GuiseScooped;
import com.otus.pages.MainPage;
import io.cucumber.java.ru.Пусть;
import io.qameta.allure.Step;

import java.net.MalformedURLException;


public class CommonSteps {

  @Inject
  public GuiseScooped guiseScooped;
  @Inject
  private MainPage mainPage;

  @Step("Открыть браузер {browserName}")
  @Пусть("Открыть браузер {string}")
  public void openBrowser(String browserName) throws MalformedURLException {
    guiseScooped.driver = guiseScooped.init(browserName);
    mainPage.open();
  }

}
