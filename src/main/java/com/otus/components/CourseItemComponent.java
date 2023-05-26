package com.otus.components;

import com.google.inject.Inject;
import com.otus.di.GuiseScooped;
import com.otus.listeners.MouseListener;
import com.otus.pages.MainPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

  @FindBy(css = ".lessons__new-item")
  private List<WebElement> courseItemsList;

  private String courseNameTemplateLocator = "//div[contains(text(),'%s')]";


  public MainPage findAndClickCourseByName(String courseName) {
    String locator = String.format(courseNameTemplateLocator, courseName);
    try {
      moveAndClickElement($(By.xpath(locator)));
      return new MainPage(guiseScooped);
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("Курс: \"" + courseName + "\" не найден");
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
    System.out.println("Выбран курс: " + el.getText().replaceAll("Рассрочка|Выгодное предложение|Успеть!", ""));
    moveAndClickElement(el);
  }

  public LocalDate getDateFromString(String stringDate) {

    int day = 1;
    int year = LocalDate.now().getYear();
    String month = "января";

    Pattern dayPattern = Pattern.compile("[0-3]?[0-9]");
    Matcher dayMatcher = dayPattern.matcher(stringDate);
    String ruMonth="января|февраля|марта|апреля|мая|июня|июля|августа|сентября|октября|ноября|декабря";
    String engMonth="January|February|March|April|May|June|July|August|September|October|November|December";
    Pattern monthPattern = Pattern.compile(ruMonth+engMonth);
    Matcher monthMatcher = monthPattern.matcher(stringDate);

    if (dayMatcher.find()) {
      day = Integer.parseInt(dayMatcher.group(0));
    }
    if (monthMatcher.find()) {
      month = monthMatcher.group(0);
    }

    String result = year + "-" + month + "-" + (day > 9 ? day : "0" + day);
    if (ruMonth.contains(month.toLowerCase(Locale.ROOT))) {
      return LocalDate.parse(result, DateTimeFormatter.ofPattern("yyyy-MMMM-dd").withLocale(new Locale("ru", "RUS")));
    } else return LocalDate.parse(result, DateTimeFormatter.ofPattern("yyyy-MMMM-dd").withLocale(new Locale("en", "EN")));

  }

  public void moveAndClickElement(WebElement element) {
    MouseListener mouseListener = new MouseListener();
    mouseListener.scrollIntoView(element, guiseScooped.driver);
    mouseListener.beforeClickOn(element, guiseScooped.driver);
    baseWaiters.waitVisibleElement(element);
    element.click();
  }

  public void findAndClickCourseItemByMinMaxCost(String string) {
    Map<WebElement, Integer> courseAndPrice = new HashMap<WebElement, Integer>();

    courseItemsList.forEach(e -> {
      int coursePrice = getCoursePrice(getCourseUrl(e.getAttribute("href")));
      if (coursePrice != -100) courseAndPrice.put(e, coursePrice);
    });
    int price = 0;
    switch (string) {
      case "дорогой":
        price = Collections.max(courseAndPrice.values());
        break;
      case "дешевой":
        price = Collections.min(courseAndPrice.values());
        break;
    }
    int finalPrice = price;

    WebElement resItem = courseAndPrice.entrySet().stream()
        .filter(r -> r.getValue() == finalPrice)
        .findFirst().get().getKey();
    System.out.println("Выбран курс: " + resItem.getText().replaceAll("Рассрочка|Выгодное предложение|Успеть!", ""));
    moveAndClickElement(resItem);
  }


  private String getCourseUrl(String href) {
    if (href.contains("promo"))
      return href.replace("promo", "lessons");
    else
      return href;
  }

  String nobrElementLocator="//div[@class='course-bottom-bar-meta__value']/nobr";
  String priceElementLocator="//div[./span[contains(text(),'/мес')]] | //div[./p[text()='Стоимость обучения']/following::div]/div";

  private int getCoursePrice(String url) {
    try {
      Document coursePage = Jsoup.connect(url).get();
      Elements element = coursePage.selectXpath(nobrElementLocator+"|(//div[./div[text()='Стоимость обучения']]/following::div/div[contains(text(),'₽')])[1]|"+priceElementLocator);
      String res = element.text().replaceAll("[^0-9]", "");
      System.out.println("Стоимость по ссылке: " + url + " = " + Integer.parseInt(res));
      return Integer.parseInt(res);
    } catch (Exception ignored) {
      System.err.println("Не найдена стоимость по ссылке: " + url);
    }
    return -100;
  }
}
