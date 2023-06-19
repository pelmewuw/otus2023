package com.otus.hooks;

import com.google.inject.Inject;
import com.otus.di.GuiseScooped;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

  @Inject
  private GuiseScooped guiseScooped;

  @After
  public void close(){
    if (guiseScooped.driver != null){
      guiseScooped.driver.close();
      guiseScooped.driver.quit();
    }
  }
}
