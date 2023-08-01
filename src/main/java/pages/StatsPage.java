package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

public class StatsPage extends AbsBasePage{

  @Step("Страница статистики открыта")
  public StatsPage statsPageIsOpened(){
    $("[text='WORDS WRITTEN']").shouldBe(Condition.visible);
    return this;
  }
}
