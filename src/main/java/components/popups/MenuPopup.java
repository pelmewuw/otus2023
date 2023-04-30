package components.popups;

import annotations.Popup;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Popup("css:.js-header3-popup:not([style*='none'])")
public class MenuPopup extends BasePopup<MenuPopup> {

  public MenuPopup(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = "[data-name='learning'] > .js-header3-popup-container .header3__card-recommendation-header-sub-chunk")
  private WebElement recommendationBlock;

  public MenuPopup recommendationBlockVisible(){
    Assertions.assertTrue(this.baseWaiters.waitVisibleElement(recommendationBlock));
    return this;
  }


}
