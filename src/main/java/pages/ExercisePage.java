package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;

public class ExercisePage extends AbsBasePage{

  public ExercisePage exercisePageIsOpened(){
    $("[text='Learn 5 new words today']").shouldBe(Condition.visible);
    return this;
  }

  public ChatPage clickStartButton(){
    $("[text=Start]").click();
    return new ChatPage();
  }
}
