package com.otus.actions;

import com.google.inject.Inject;
import com.otus.di.GuiseScooped;
import org.openqa.selenium.support.PageFactory;

public class BaseActions<T>  {

  @Inject
  protected GuiseScooped guiseScooped;

  @Inject
  public BaseActions(GuiseScooped guiseScooped){
    PageFactory.initElements(guiseScooped.driver,this);
  }
}
