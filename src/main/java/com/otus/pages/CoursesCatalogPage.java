package com.otus.pages;

import com.google.inject.Inject;
import com.otus.annotations.Path;
import com.otus.di.GuiseScooped;

@Path("/courses")
//@Urls(
//    @UrlTemplate(name = "category", value = "?categories=%1")
//)
public class CoursesCatalogPage extends BasePage<CoursesCatalogPage> {

  @Inject
  public CoursesCatalogPage(GuiseScooped guiseScooped) {
    super(guiseScooped);
  }
}
