package com.otus.components;

import com.google.inject.Inject;
import com.otus.di.GuiseScooped;
import com.otus.pageobject.PageObject;

public abstract class BaseComponent<T> extends PageObject<T> {

  @Inject
  public BaseComponent(GuiseScooped guiseScooped){
    super(guiseScooped);
  }

}
