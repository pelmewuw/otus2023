package com.otus.actions;

import com.google.inject.Inject;
import com.otus.di.GuiseScooped;
import com.otus.waiters.BaseWaiters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class CommonActions <T>{

  protected WebDriver driver;
  protected BaseWaiters baseWaiters;

  @Inject
  public CommonActions(GuiseScooped guiseScooped){
    this.driver = guiseScooped.driver;
    PageFactory.initElements(driver, this);

    baseWaiters = new BaseWaiters(driver);
  }
}
