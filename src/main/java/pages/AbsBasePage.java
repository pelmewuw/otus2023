package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public abstract class AbsBasePage<T> {

  @Step("Открыть стартовую страницу")
  public T open(){
    Selenide.open();
    return (T) this;
  }

}
