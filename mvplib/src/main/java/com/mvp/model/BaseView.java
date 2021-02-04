package com.mvp.model;

import java.io.Serializable;

public interface BaseView<T extends BasePresenter> extends Serializable {
    void setPresenter(T presenter);
}
