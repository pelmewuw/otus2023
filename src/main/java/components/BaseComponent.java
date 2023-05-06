package components;

import org.openqa.selenium.WebDriver;
import pageobject.PageObject;

public abstract class BaseComponent<T> extends PageObject<T> {

  public BaseComponent(WebDriver driver){
    super(driver);
  }

}
