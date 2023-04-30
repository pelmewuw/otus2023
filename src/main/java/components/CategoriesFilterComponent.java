package components;

import data.CoursesCategoryData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoriesFilterComponent extends BaseComponent{

  public CategoriesFilterComponent(WebDriver driver){
    super(driver);
  }

  private String checkboxInputLocatorTemplate = "//label[text()='%s']/..//input[@type='checkbox']";

  public CategoriesFilterComponent checkboxStatusShouldBeSameAs(CoursesCategoryData coursesCategoryData, boolean expectedStatus){
    String locator = String.format(checkboxInputLocatorTemplate, coursesCategoryData.getName());
    Assertions.assertEquals($(By.xpath(locator)).isSelected(),expectedStatus, "Checkbox status is wrong");
    return this;
  }

}
