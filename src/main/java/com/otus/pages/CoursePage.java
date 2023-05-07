package com.otus.pages;

import com.otus.annotations.Path;
import com.otus.di.GuiseScooped;

@Path("/lesson")
public class CoursePage extends BasePage<CoursePage> {


  public CoursePage(GuiseScooped guiseScooped) {
    super(guiseScooped);
  }

}
