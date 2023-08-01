package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

public class ExercisePage extends AbsBasePage{

  @Step("Страница упражнений открыта")
  public ExercisePage exercisePageIsOpened(){
    $("[text='Learn 5 new words today']").shouldBe(Condition.visible);
    return this;
  }

  @Step("Клик кноп")
  public ChatPage clickStartButton(){
    $("[text=Start]").click();
    return new ChatPage();
  }
}
