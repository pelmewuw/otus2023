package com.otus.pages;

import com.otus.annotations.Path;
import com.otus.di.GuiseScooped;
import org.openqa.selenium.WebDriver;

@Path("/lesson")
public class CoursePage extends BasePage<CoursePage>{


    public CoursePage(GuiseScooped guiseScooped) {
      super(guiseScooped);
    }

}
