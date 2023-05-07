package com.otus.pages;


import com.google.inject.Inject;
import com.otus.annotations.Path;
import com.otus.data.CoursesCategoryData;
import com.otus.di.GuiseScooped;
import org.openqa.selenium.By;

@Path("/")
public class MainPage extends BasePage<MainPage> {

  @Inject
  public MainPage(GuiseScooped guiseScooped) {
    super(guiseScooped);
  }

  // статический
  //  @FindBy(css = ".course-categories__nav a[title='Программирование']")
  //  private WebElement navCategoryLink;
  // шаблонный
  //  private String navCategoryLinkTemplateSelector =".course-categories__nav a[title='%s']"; //if css
  //  private String navCategoryLinkTemplateLocator =".course-categories__nav a[title='%s']"; //if xpath
  // динамический
  //  private String navCategoryDynamicLinkTemplateSelector=".course-categories__nav a[title='Программирование']";

  private String navCategoryLinkTemplateSelector = ".course-categories__nav a[title='%s']";

  public CoursesCatalogPage clickCategoryCourseLinkByName(CoursesCategoryData coursesCategoryData) {
    String selector = String.format(navCategoryLinkTemplateSelector, coursesCategoryData.getName());

    driver.findElement(By.cssSelector(selector)).click();

    return new CoursesCatalogPage(new GuiseScooped());
  }

}
