package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

public class GrammarPage extends AbsBasePage{

  @Step("Страница грамматики открыта")
  public GrammarPage grammarPageIsOpened(){
    $("[text='BASIC']").shouldBe(Condition.visible);
    return this;
  }

  @Step("Клик на кнопку старт")
  public ChatPage clickStartButton(){
    $$("[text=Start]").first().click();
    return new ChatPage();
  }
}
