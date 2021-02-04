package com.mvp.model;

import java.io.Serializable;

public interface BasePresenter extends Serializable {
    void onResume();

    void onStop();

    void onDestroy();

}
