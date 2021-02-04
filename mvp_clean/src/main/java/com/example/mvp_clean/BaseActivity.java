package com.example.mvp_clean;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        presenter = (P) initPresenter();
        initView();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    /**
     * 初始化Presenter
     *
     * @return
     */
    protected abstract BasePresenter initPresenter();


    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化view
     */
    public abstract void initView();

    /**
     * 返回布局id
     *
     * @return
     */
    public abstract int getLayoutId();

}
