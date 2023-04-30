package menu;

import annotations.Driver;
import components.menu.HeaderMenuComponent;
import components.popups.MenuPopup;
import data.HeaderMenuData;
import data.ModalStateData;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class Menu_Test {

  @Driver
  private WebDriver driver;

  @Test
  public void checkCourseBlockInLearningMenu(){
    new MainPage(driver)
        .open();

    new MenuPopup(driver)
        .modalState(ModalStateData.INVISIBLE);

    new HeaderMenuComponent(driver)
        .moveToHeaderMenu(HeaderMenuData.LEARNING)
        .modalState(ModalStateData.VISIBLE)
        .recommendationBlockVisible();
  }
}
