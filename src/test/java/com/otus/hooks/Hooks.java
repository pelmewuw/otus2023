package com.otus.hooks;

import com.otus.di.GuiseScooped;
import io.cucumber.java.After;

import javax.inject.Inject;

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
