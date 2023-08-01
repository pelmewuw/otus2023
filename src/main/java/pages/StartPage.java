package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class StartPage extends AbsBasePage<StartPage>{

  @Step("Клик на кнопку next")
  public StartPage  clickNextButton() {
    $("[text=Next]").click();
    return this;
  }

  @Step("Стартовая страница открыта")
  public Boolean startPageShouldBeOpened(){
    return $(By.id("android:id/content")).isDisplayed();
  }

  @Step("Клик на кнопку skip")
  public StartPage clickSkipButton(){
    $("[text=Skip >]").click();
    return this;
  }


  private SelenideElement alertPopup = $(By.id("android:id/title_template"));
  @Step("Попап окно алерта отображается")
  public Boolean alertPopupShouldBeVisible(){
    return alertPopup.shouldBe(Condition.visible).isDisplayed();
  }
  @Step("Попап окно алерта не отображается")
  public StartPage alertPopupShouldBeInVisible(){
    alertPopup.shouldBe(Condition.not(Condition.visible));
    return this;
  }

  @Step("Клик на кнопку ok в попап окне алерта")
  public StartPage clickAlertPopupOkButton(){
    $(By.id("android:id/button1")).click();
    return this;
  }

}
