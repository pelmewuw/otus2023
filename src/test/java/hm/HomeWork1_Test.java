package hm;
/*
ДЗ #1: Автотест со своими ожиданиями
Необходимо создать проект в Maven'e и реализовать:
Фабрику (WebDriverFactory), которая будет получать значение из окружения и запускать соответствующий браузер
Браузеры: Chrome, Firefox, Opera
Реализовать подсветку элементов перед нажатием, после нажатия вернуть данные в исходное состояние
На главно странице Otus'a снизу найти список курсов(популярные курсы, специализации, рекомендации) и реализовать:
- Метод фильтр по названию курса
- Метод выбора курса, стартующего раньше всех/позже всех (при совпадении дат - выбрать любой) при помощи reduce
Реализовать движение мыши при помощи и выбор курса при помощи библиотеки Actions
*/

import annotations.Driver;
import components.CourseItemComponent;
import extensions.UIExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.MainPage;

import java.net.MalformedURLException;
import java.net.URI;

@ExtendWith(UIExtension.class)
public class HomeWork1_Test {

  @Driver
  private WebDriver driver;

  @BeforeEach
  public void init() throws MalformedURLException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(CapabilityType.BROWSER_NAME, System.getProperty("browser", "chrome"));
    capabilities.setCapability(CapabilityType.BROWSER_VERSION, System.getProperty("browser.version", "112.0"));
    capabilities.setCapability("enableVNC", true); //для просмотра сессии
    driver = new RemoteWebDriver(URI.create("http://127.0.0.1:8080/wd/hub").toURL(), capabilities);
  }

  @Test
  public void selectCourseByName() {
    new MainPage(driver)
        .open();

    new CourseItemComponent(driver)
        .findAndClickCourseByName("Специализация Python")
        .headerShouldBeSameAs("Python Developer");
  }

  @Test
  public void selectCourseByEarlyDate() {
    new MainPage(driver)
        .open();

    new CourseItemComponent(driver)
        .findAndClickCourseByDate("early");
  }

  @Test
  public void selectCourseByLastDate() {
    new MainPage(driver)
        .open();

    new CourseItemComponent(driver)
        .findAndClickCourseByDate("last");
  }

}
