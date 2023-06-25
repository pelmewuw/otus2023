package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

public class ChatPage extends AbsBasePage{

  public ChatPage chatPageIsOpened(){
    $("[text='Type a message...']").shouldBe(Condition.visible);
    return this;
  }

}
