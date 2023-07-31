package com.otus.steps.components;

import com.google.inject.Inject;
import com.otus.components.CourseItemComponent;
import io.cucumber.java.ru.Если;
import io.qameta.allure.Step;


public class CourseItemComponentsSteps {

  @Inject
  public CourseItemComponent courseItemComponent;

  @Step("Кликнуть на {earlyLast} плитку курса")
  @Если("^Кликнуть на (.*) плитку курса$")
  public void clickLastEarlyCourseItem(String earlyLast) throws InterruptedException {
    courseItemComponent.findAndClickCourseByDate(earlyLast);
  }

  @Step("Кликнуть на плитку курса с названием {name}")
  @Если("^Кликнуть на плитку курса с названием (.*)$")
  public void clickCourseComponentByName(String name) {
    courseItemComponent.findAndClickCourseByName(name);
  }

  @Step("Кликнуть по курсу с самой {cost} ценой")
  @Если("^Кликнуть по курсу с самой (.*) ценой$")
  public void clickCourseItemByMinMaxCost(String cost) {
    courseItemComponent.findAndClickCourseItemByMinMaxCost(cost);
  }
}
