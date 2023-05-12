package com.otus.pages;

import com.google.inject.Inject;
import com.otus.annotations.Path;
import com.otus.di.GuiseScooped;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Path("/lesson")
public class CoursePage extends BasePage<CoursePage> {

  @Inject
  public CoursePage(GuiseScooped guiseScooped) {
    super(guiseScooped);
  }


  @FindBy(tagName = "h1")
  public WebElement courseHeader;
  public void coursePageIsOpen(){
    String courseName=courseHeader.getText();
    Assertions.assertNotEquals("Авторские онлайн‑курсы для профессионалов", courseName);
  }


  public void coursePageIsOpen(String name){
    Assertions.assertEquals(name.trim(), courseHeader.getText());
  }

}
