package com.otus.pageobject;

import com.otus.actions.BaseActions;
import com.google.inject.Inject;
import com.otus.di.GuiseScooped;
import com.otus.waiters.BaseWaiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public abstract class PageObject<T> {

  //  protected WebDriver driver;
  @Inject
  protected BaseActions actions;
  @Inject
  public GuiseScooped guiseScooped;
  @Inject
  public BaseWaiters baseWaiters;

  public PageObject(GuiseScooped guiseScooped) {
    this.guiseScooped = guiseScooped;
    this.actions = new BaseActions(this.guiseScooped);
    PageFactory.initElements(this.guiseScooped.driver, this);
  }

  public WebElement $(By locator){
    return
        this.guiseScooped.driver.findElement(locator);
  }
}
