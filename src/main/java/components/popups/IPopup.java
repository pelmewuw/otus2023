package components.popups;

import data.ModalStateData;

public interface IPopup<T> {
//  T modalNotVisible();
//  T modalVisible();

  T modalState(ModalStateData modalStateData);
}
