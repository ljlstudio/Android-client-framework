package com.mvp.model;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<P extends BasePresenter, V extends ViewDataBinding> extends AppCompatActivity {

    private P presenter;
    private V dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        presenter = (P) initPresenter();
        initBinding(dataBinding);
    }

    protected abstract void initBinding(ViewDataBinding dataBinding);

    protected abstract BasePresenter initPresenter();


    public abstract int getLayoutId();


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
    protected void onDestroy() {
        super.onDestroy();
        if (dataBinding != null) {
            dataBinding.unbind();
        }
        if (presenter != null) {
            presenter.onDestroy();
        }
    }
}
