package components.popups;

import annotations.Popup;
import data.ModalStateData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobject.PageObject;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BasePopup<T> extends PageObject<T> implements IPopup<T> {

  public BasePopup(WebDriver driver) {
    super(driver);
  }

  private By getPopupBy() {
    Popup popup = getClass().getAnnotation(Popup.class);
    if (popup != null) {
      return analyser(popup.value());
    }
    return null;
  }

  private By analyser(String locatorStr){
    Pattern pattern =Pattern.compile("(\\w+):(.*)");
    Matcher matcher = pattern.matcher(locatorStr);
    if (matcher.find()){
      String strategySearch = matcher.group(1);
      String value = matcher.group(2);

      switch (strategySearch){
        case "css":{
          return By.cssSelector(value);
        }
        case "xpath":{
          return By.xpath(value);
        }
        default: return null;
      }
    }
    return null;
  }

  @Override
  public T modalState(ModalStateData modalStateData) {
    ExpectedCondition condition = modalStateData.isState()
        ? ExpectedConditions.visibilityOfElementLocated(getPopupBy())
        : ExpectedConditions.invisibilityOfElementLocated(getPopupBy());
    Assertions.assertTrue(baseWaiters.waitForCondition(condition), String.format("Modal status should be %s", modalStateData.name().toLowerCase(Locale.ROOT)));
    return (T)this;
  }

//  @Override
//  public T modalNotVisible() {
//    this.baseWaiters.waitForCondition(ExpectedConditions.invisibilityOfElementLocated(getPopupBy()));
//    return (T)this;
//  }
//
//  @Override
//  public T modalVisible() {
//    this.baseWaiters.waitForCondition(ExpectedConditions.visibilityOfElementLocated(getPopupBy()));
//    return (T)this;
//  }
}
