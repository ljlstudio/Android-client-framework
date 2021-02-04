package com.example.mvp_clean;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    private P presenter;
    private FragmentActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = initPresenter();
        initView();
    }


    protected abstract void initView();

    /**
     * 懒加载，配合最新的 BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT 使用
     */
    protected abstract void lazyLoad();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (FragmentActivity) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
        lazyLoad();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    protected abstract P initPresenter();

    protected abstract int getLayoutId();


}
