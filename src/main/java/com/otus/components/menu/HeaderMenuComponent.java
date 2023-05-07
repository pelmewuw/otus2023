package com.otus.components.menu;

import com.otus.components.BaseComponent;
import com.otus.components.popups.MenuPopup;
import com.otus.data.HeaderMenuData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

public class HeaderMenuComponent extends BaseComponent<BaseComponent> {

  public HeaderMenuComponent(WebDriver driver){
    super(driver);
  }

  private String headerMenuSelectorTemplate = "[data-name='%s']";

  public MenuPopup moveToHeaderMenu(HeaderMenuData headerMenuData){
    String selector = String.format(headerMenuSelectorTemplate, headerMenuData.name().toLowerCase(Locale.ROOT));
    actions.moveToElement($(By.cssSelector(selector))).build().perform();
    return new MenuPopup(driver);
  }
}
