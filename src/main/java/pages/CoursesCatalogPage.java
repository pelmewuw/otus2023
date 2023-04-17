package pages;

import annotations.Path;
import org.openqa.selenium.WebDriver;

@Path("/courses")
//@Urls(
//    @UrlTemplate(name = "category", value = "?categories=%1")
//)
public class CoursesCatalogPage extends BasePage<CoursesCatalogPage> {

  public CoursesCatalogPage(WebDriver driver) {
    super(driver);
  }
}
