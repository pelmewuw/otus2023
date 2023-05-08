package com.otus.components;

import com.google.inject.Inject;
import com.otus.di.GuiseScooped;
import com.otus.pageobject.PageObject;
import org.openqa.selenium.WebDriver;

public abstract class BaseComponent<T> extends PageObject<T> {

  @Inject
  public BaseComponent(GuiseScooped guiseScooped){
    super(guiseScooped);
  }

}
