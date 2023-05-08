package com.otus.components;

import com.google.inject.Inject;
import com.otus.di.GuiseScooped;
import com.otus.listeners.MouseListener;
import com.otus.pages.MainPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseItemComponent extends BaseComponent<CourseItemComponent> {

  @Inject
  public CourseItemComponent(GuiseScooped guiseScooped) {
    super(guiseScooped);
  }

  @FindBy(css=".lessons__new-item")
  private List<WebElement> courseItemsList;

  private String courseNameTemplateLocator = "//div[contains(text(),'%s')]";


  public MainPage findAndClickCourseByName(String courseName) {
    String locator = String.format(courseNameTemplateLocator, courseName);
    try {
      moveAndClickElement($(By.xpath(locator)));
      return new MainPage(guiseScooped);
    } catch (UnsupportedOperationException e) {
      throw new UnsupportedOperationException("Курс: \"" + courseName + "\" не найден");
    }

  }


  @FindBy(css = ".lessons__new-item-start")
  private List<WebElement> courseDateList;

  public void findAndClickCourseByDate(String earlyLast) {
    WebElement el = courseDateList.stream()
        .reduce((e1, e2) -> {
          LocalDate date1 = getDateFromString(e1.getText());
          LocalDate date2 = getDateFromString(e2.getText());
          switch (earlyLast) {
            case "раннюю":
              return date1.isBefore(date2) ? e1 : e2;
            case "позднюю":
              return date1.isAfter(date2) ? e1 : e2;
            default:
              return null;
          }
        }).get();
    System.out.println("Choose date is: " + el.getText());
    moveAndClickElement(el);
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
    mouseListener.scrollIntoView(element, guiseScooped.driver);
    mouseListener.beforeClickOn(element, guiseScooped.driver);
    baseWaiters.waitVisibleElement(element);
    element.click();
  }

  public void findAndClickCourseItemByMinMaxCost(String string){
    Map<WebElement, Integer> courseAndPrice = new HashMap<WebElement, Integer>();
    courseItemsList.forEach(e->{
      int coursePrice = getCoursePrice(getCourseUrl(e.getAttribute("href")));
      if (coursePrice != -100) courseAndPrice.put(e,coursePrice);

      int price =0;
      switch (string){
        case "дорогой":
          price = Collections.max(courseAndPrice.values());
          break;
        case "дешевой":
          price = Collections.min(courseAndPrice.values());
          break;
      }
      int finalPrice = price;
      courseAndPrice.entrySet().stream()
          .filter(r -> r.getValue() == finalPrice)
          .findFirst().get().getKey().click();
    });
  }


  private String getCourseUrl(String href) {
    if (href.startsWith("/"))
      return guiseScooped.driver.getCurrentUrl() + href.substring(1);
    else
      return href;
  }
  private int getCoursePrice(String url){
    try {
      Document coursePage = Jsoup.connect(url).get();
      Elements element = coursePage.selectXpath("//div[contains(./p, 'Стоимость обучения')]/div/div");
      return  Integer.parseInt(element.text());
    }
    catch (Exception ignored){
      System.out.println("Не удалсь получить стоимость по ссылке" + url);
    }
    return -100;
  }
}
