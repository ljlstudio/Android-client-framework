package com.example.mvp_clean;

public interface BasePresenter {

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
