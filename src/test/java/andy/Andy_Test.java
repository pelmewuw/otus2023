package andy;

import com.codeborne.selenide.Selenide;
import components.MenuComponents;
import data.MenuItemData;
import extensions.AppiumExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.*;

@ExtendWith(AppiumExtension.class)
public class Andy_Test {

  @BeforeEach
  @DisplayName("Пропуск начальной страницы и алерта")
  public void startPageCloseIfDisplayed() {
    StartPage startPage = new StartPage();
    startPage.open();
    if (startPage.startPageShouldBeOpened()){
      startPage.clickNextButton()
          .clickNextButton()
          .clickSkipButton();
      if (startPage.alertPopupShouldBeVisible()){
        startPage.clickAlertPopupOkButton()
            .alertPopupShouldBeInVisible();
      }
    }
  }

  @AfterEach
  @DisplayName("Закрытие драйвера")
  public void tearDown() {
    Selenide.closeWebDriver();
  }

  @Test
  @DisplayName("Проверка отображения элементов меню")
  public void menuItemsVisibleTest() {
    new MenuComponents()
        .menuItemShouldBeVisible(MenuItemData.CHAT_ITEM)
        .menuItemShouldBeVisible(MenuItemData.EXERCISE_ITEM)
        .menuItemShouldBeVisible(MenuItemData.GRAMMAR_ITEM)
        .menuItemShouldBeVisible(MenuItemData.STATS_ITEM);
  }

  @Test
  @DisplayName("Проверка открытия страниц")
  public void menuItemOpenTest(){
    new MenuComponents()
        .menuItemClick(MenuItemData.CHAT_ITEM);
    new ChatPage()
        .chatPageIsOpened();

    new MenuComponents()
        .menuItemClick(MenuItemData.EXERCISE_ITEM);
    new ExercisePage()
        .exercisePageIsOpened();

    new MenuComponents()
        .menuItemClick(MenuItemData.GRAMMAR_ITEM);
    new GrammarPage()
        .grammarPageIsOpened();

    new MenuComponents()
        .menuItemClick(MenuItemData.STATS_ITEM);
    new StatsPage()
        .statsPageIsOpened();
  }

  @Test
  @DisplayName("Проверка запуска упражнений")
  public void startExerciseTest(){
    new MenuComponents()
        .menuItemClick(MenuItemData.EXERCISE_ITEM);
    new ExercisePage()
        .exercisePageIsOpened()
        .clickStartButton()
        .chatPageIsOpened();
  }

  @Test
  @DisplayName("Проверка запуска упражнений грамматики")
  public void startGrammarTest(){
    new MenuComponents()
        .menuItemClick(MenuItemData.GRAMMAR_ITEM);
    new GrammarPage()
        .grammarPageIsOpened()
        .clickStartButton()
        .chatPageIsOpened();
  }

}
