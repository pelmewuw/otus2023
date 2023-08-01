package components;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import data.MenuItemData;
import io.qameta.allure.Step;

public class MenuComponents {
  private String menuItem = "[text='%s']";
  @Step("Пункт меню {name} отображается")
  public MenuComponents menuItemShouldBeVisible(MenuItemData name) {
    $(String.format(menuItem,name.getName())).shouldBe(Condition.visible);
    return this;
  }

  @Step("Клик на пункт меню {name}")
  public MenuComponents menuItemClick(MenuItemData name){
    $(String.format(menuItem,name.getName())).click();
    return this;
  }


}
