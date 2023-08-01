package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

public class ChatPage extends AbsBasePage{

  @Step("Страница чата открыта")
  public ChatPage chatPageIsOpened(){
    $("[text='Type a message...']").shouldBe(Condition.visible);
    return this;
  }

}
