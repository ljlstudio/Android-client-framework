package com.example.mvp_clean;

import androidx.core.util.Consumer;

import java.io.Serializable;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseModel<P extends BasePresenter> implements Serializable, Consumer<Disposable> {

    private P presenter;
    private  CompositeDisposable compositeDisposable;

    public BaseModel(P presenter) {
        this.presenter = presenter;
        compositeDisposable = new CompositeDisposable();
    }
    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
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
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }
}
