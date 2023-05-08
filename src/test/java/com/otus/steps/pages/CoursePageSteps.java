package com.otus.steps.pages;

import com.google.inject.Inject;
import com.otus.pages.CoursePage;
import io.cucumber.java.ru.Тогда;

public class CoursePageSteps {

  @Inject
  public CoursePage coursePage;

  @Тогда("откроется страница курса")
  public void coursePageIsOpened(){
    coursePage.coursePageIsOpen();
  }

  @Тогда("^откроется страница курса с названием (.*)$")
  public void coursePageIsOpenedByName(String courseName){
    coursePage.coursePageIsOpen(courseName);
  }

}
