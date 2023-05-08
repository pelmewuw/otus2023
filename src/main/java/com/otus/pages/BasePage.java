package com.otus.pages;

import com.google.inject.Inject;
import com.otus.actions.BaseActions;
import com.otus.annotations.Path;
import com.otus.di.GuiseScooped;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.otus.pageobject.PageObject;


public abstract class BasePage<T> extends BaseActions<T> {
  @Inject
  public BasePage(GuiseScooped guiseScooped) {
    super(guiseScooped);
  }

  private String baseUrl = System.getProperty("webdriver.base.url", "https://otus.ru");

  @FindBy(tagName = "h1")
  private WebElement header;

  public T headerShouldBeSameAs(String header) {
    Assertions.assertEquals(header, this.header.getText());
    return (T) this;
  }

  private String getPath() {
    Path path = getClass().getAnnotation(Path.class);
    if (path != null) {
      return path.value();
    }
    return "";

  }

  public T open() {
    guiseScooped.driver.get(baseUrl + getPath());

    return (T) this;
  }
}