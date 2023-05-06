package pages;

import annotations.Path;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobject.PageObject;


public abstract class BasePage<T> extends PageObject<T> {

  public BasePage(WebDriver driver) {
    super(driver);
  }

  private String baseUrl = System.getProperty("webdriver.base.url", "https://otus.ru");

  @FindBy(tagName = "h1")
  private WebElement header;

  public T headerShouldBeSameAs(String header) {
    Assertions.assertEquals(header, this.header.getText());
    return (T) this;
  }

  private String getPath() {
    Path path = getClass().getAnnotation(Path.class);
    if (path != null) {
      return path.value();
    }
    return "";

  }

  public T open() {
    driver.get(baseUrl + getPath());
    return (T) this;
  }


  @FindBy(css = ".cookies__button")
  private WebElement acceptCookiesBtn;
  public void acceptCookies(){
    baseWaiters.waitForCondition(ExpectedConditions.elementToBeClickable(acceptCookiesBtn));
    if (acceptCookiesBtn.isDisplayed()) acceptCookiesBtn.click();
    baseWaiters.waitInVisibleElement(acceptCookiesBtn);
  }
}
