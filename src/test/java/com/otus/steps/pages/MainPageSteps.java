package com.otus.steps.pages;

import com.google.inject.Inject;
import com.otus.pages.MainPage;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;

public class MainPageSteps {

  @Inject
  public MainPage mainPage;

  @Если("Открыть главную страницу")
  public void openMainPage() {
    mainPage.open();
  }

  @Тогда("На главной странице отображается заголовок {string}")
  public void mainPageHeaderShouldBeSameAs(String header){
    mainPage.headerShouldBeSameAs(header);
  }

}
