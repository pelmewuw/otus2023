package com.otus.components.popups;

import com.otus.data.ModalStateData;

public interface IPopup<T> {
//  T modalNotVisible();
//  T modalVisible();

  T modalState(ModalStateData modalStateData);
}
