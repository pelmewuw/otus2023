package com.otus.waiters;

import com.google.inject.Inject;
import com.otus.di.GuiseScooped;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseWaiters {

  private WebDriverWait webDriverWait;

  @Inject
  private GuiseScooped guiseScooped;

  @Inject
  public BaseWaiters(GuiseScooped guiseScooped){
    this.webDriverWait = new WebDriverWait(guiseScooped.driver, Integer.parseInt((System.getProperty("waiter_timeout_second", "5"))));
  }

  public boolean waitForCondition(ExpectedCondition expectedCondition){
    try {
      webDriverWait.until(expectedCondition);
      return true;
    } catch (TimeoutException ignore) {
      return false;
    }
  }

  public boolean waitVisibleElement(WebElement element){
    return waitForCondition(ExpectedConditions.visibilityOf(element));
  }

  public boolean waitInVisibleElement(WebElement element){
    return waitForCondition(ExpectedConditions.invisibilityOf(element));
  }
}
