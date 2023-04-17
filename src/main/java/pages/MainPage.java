package pages;


import annotations.Path;
import data.CoursesCategoryData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Path("/")
public class MainPage extends BasePage<MainPage> {

  public MainPage(WebDriver driver) {
    super(driver);
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

    return new CoursesCatalogPage(driver);
  }

}
