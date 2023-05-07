package pages;

import annotations.Path;
import org.openqa.selenium.WebDriver;

@Path("/lesson")
public class CoursePage extends BasePage<CoursePage> {


  public CoursePage(WebDriver driver) {
    super(driver);
  }

}
