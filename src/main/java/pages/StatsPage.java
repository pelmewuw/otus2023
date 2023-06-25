package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

public class StatsPage extends AbsBasePage{

  public StatsPage statsPageIsOpened(){
    $("[text='WORDS WRITTEN']").shouldBe(Condition.visible);
    return this;
  }
}
