package lesson.courses;

import annotations.Driver;
import components.CategoriesFilterComponent;
import data.CoursesCategoryData;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class CoursesCatalog_Test {

  @Driver
  private WebDriver driver;

  @Test
  public void checkboxCategoryCourses() {
    new MainPage(driver)
        .open()
        .clickCategoryCourseLinkByName(CoursesCategoryData.Programming)
        .headerShouldBeSameAs("Каталог");
    new CategoriesFilterComponent(driver).checkboxStatusShouldBeSameAs(CoursesCategoryData.Programming, true);
  }


}
