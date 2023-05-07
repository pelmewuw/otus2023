package com.otus.pages;

import com.google.inject.Inject;
import com.otus.annotations.Path;
import com.otus.di.GuiseScooped;

@Path("/")
public class MainPage extends BasePage<MainPage> {

  @Inject
  public MainPage(GuiseScooped guiseScooped) {
    super(guiseScooped);
  }

}
