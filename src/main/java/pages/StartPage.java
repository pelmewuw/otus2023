package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class StartPage extends AbsBasePage<StartPage>{

  public StartPage  clickNextButton() {
    $("[text=Next]").click();
    return this;
  }

  public Boolean startPageShouldBeOpened(){
    return $(By.id("android:id/content")).isDisplayed();
  }

  public StartPage clickSkipButton(){
    $("[text=Skip >]").click();
    return this;
  }


  private SelenideElement alertPopup = $(By.id("android:id/title_template"));
  public Boolean alertPopupShouldBeVisible(){
    return alertPopup.shouldBe(Condition.visible).isDisplayed();
  }
  public StartPage alertPopupShouldBeInVisible(){
    alertPopup.shouldBe(Condition.not(Condition.visible));
    return this;
  }

  public StartPage clickAlertPopupOkButton(){
    $(By.id("android:id/button1")).click();
    return this;
  }

}
