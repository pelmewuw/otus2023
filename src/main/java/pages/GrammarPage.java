package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;

public class GrammarPage extends AbsBasePage{

  public GrammarPage grammarPageIsOpened(){
    $("[text='BASIC']").shouldBe(Condition.visible);
    return this;
  }

  public ChatPage clickStartButton(){
    $$("[text=Start]").first().click();
    return new ChatPage();
  }
}
