package com.otus.steps.components;

import com.google.inject.Inject;
import com.otus.components.CourseItemComponent;
import io.cucumber.java.ru.Если;


public class CourseItemComponentsSteps {

  @Inject
  public CourseItemComponent courseItemComponent;

  @Если("^Кликнуть на (.*) плитку курса$")
  public void clickLastEarlyCourseItem(String earlyLast) throws InterruptedException {
    courseItemComponent.findAndClickCourseByDate(earlyLast);
  }

  @Если("^Кликнуть на плитку курса с названием (.*)$")
  public void clickCourseComponentByName(String name) {
    courseItemComponent.findAndClickCourseByName(name);
  }

  @Если("^Кликнуть по курсу с самой (.*) ценой$")
  public void clickCourseItemByMinMaxCost(String cost) {
    courseItemComponent.findAndClickCourseItemByMinMaxCost(cost);
  }
}
