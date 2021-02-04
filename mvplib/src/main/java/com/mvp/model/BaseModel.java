package com.mvp.model;

import androidx.core.util.Consumer;

import java.io.Serializable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class BaseModel<T extends BasePresenter> implements Serializable, Consumer<Disposable> {

    private T presenter;
    private CompositeDisposable mCompositeDisposable;

    public BaseModel(T presenter) {
        this.presenter = presenter;
        mCompositeDisposable = new CompositeDisposable();
    }

    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void accept(Disposable disposable) {
        addSubscribe(disposable);
    }


    /**
     * 清除数据
     */
    protected void onCleared() {
        //ViewModel销毁时会执行，同时取消所有异步任务
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }
}
