package com.otus.steps.pages;

import com.google.inject.Inject;
import com.otus.pages.CoursePage;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;

public class CoursePageSteps {

  @Inject
  public CoursePage coursePage;

  @Step("откроется страница курса")
  @Тогда("откроется страница курса")
  public void coursePageIsOpened(){
    coursePage.coursePageIsOpen();
  }

  @Step("откроется страница курса с названием {courseName}")
  @Тогда("^откроется страница курса с названием (.*)$")
  public void coursePageIsOpenedByName(String courseName){
    coursePage.coursePageIsOpen(courseName);
  }

}
