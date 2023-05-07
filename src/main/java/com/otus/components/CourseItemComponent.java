package com.otus.components;

import com.otus.di.GuiseScooped;
import com.otus.listeners.MouseListener;
import com.otus.pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseItemComponent extends BaseComponent<CourseItemComponent> {

  public CourseItemComponent(GuiseScooped guiseScooped) {
    super(guiseScooped);
  }

  private String courseNameTemplateLocator = "//div[contains(text(),'%s')]";


  public MainPage findAndClickCourseByName(String courseName) {
    String locator = String.format(courseNameTemplateLocator, courseName);
    try {
      moveAndClickElement($(By.xpath(locator)));
      return new MainPage(new GuiseScooped());
    } catch (UnsupportedOperationException e) {
      throw new UnsupportedOperationException("Курс: \"" + courseName + "\" не найден");
    }

  }


  @FindBy(css = ".lessons__new-item-start")
  private List<WebElement> courseDateList;

  public WebElement findAndClickCourseByDate(String earlyLast) {
    WebElement el = courseDateList.stream()
        .reduce((e1, e2) -> {
          LocalDate date1 = getDateFromString(e1.getText());
          LocalDate date2 = getDateFromString(e2.getText());
          switch (earlyLast) {
            case "early":
              return date1.isBefore(date2) ? e1 : e2;
            case "last":
              return date1.isAfter(date2) ? e1 : e2;
            default:
              return null;
          }
        }).get();
    System.out.println("Choose date is: " + el.getText());
    moveAndClickElement(el);
    return el;
  }

  public LocalDate getDateFromString(String stringDate) {

    int day = 1;
    int year = LocalDate.now().getYear();
    String month = "";

    Pattern dayPattern = Pattern.compile("[0-3]?[0-9]");
    Matcher dayMatcher = dayPattern.matcher(stringDate);
    Pattern monthPattern = Pattern.compile("\\b(янв)|(фев)|(мар)|(апр)|(мая)|(июн)|(июл)|(авг)|(сен)|(окт)|(ноя)|(дек)");
    Matcher monthMatcher = monthPattern.matcher(stringDate);

    if (dayMatcher.find()) {
      day = Integer.parseInt(dayMatcher.group(0));
    }
    if (monthMatcher.find()) {
      month = monthMatcher.group(0);
    }

    String result = year + "-" + month + "-" + (day > 9 ? day : "0" + day);


    return LocalDate.parse(result, DateTimeFormatter.ofPattern("uuuu-MMM-dd").withLocale(new Locale("ru", "RU")));

  }

  public void moveAndClickElement(WebElement element){
    MouseListener mouseListener = new MouseListener();
    mouseListener.scrollIntoView(element, driver);
    mouseListener.beforeClickOn(element, driver);
    baseWaiters.waitVisibleElement(element);
    actions
        .moveToElement(element)
        .click()
        .build().perform();
  }
}
