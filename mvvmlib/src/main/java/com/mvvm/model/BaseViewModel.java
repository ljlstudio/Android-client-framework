package com.mvvm.model;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class BaseViewModel<M extends BaseModel> extends AndroidViewModel implements IBaseViewModel, Consumer<Disposable> {

    protected M model;
    //弱引用持有
    private WeakReference<LifecycleProvider> lifecycle;
    //管理RxJava，主要针对RxJava异步操作造成的内存泄漏
    private CompositeDisposable mCompositeDisposable;
    private BaseLiveData liveData;

    public BaseViewModel(@NonNull Application application) {
        this(application, null);
    }

    public BaseViewModel(@NonNull Application application, M model) {
        super(application);
        this.model = model;
        mCompositeDisposable = new CompositeDisposable();
    }

    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 注入RxLifecycle生命周期
     *
     * @param lifecycle
     */
    public void injectLifecycleProvider(LifecycleProvider lifecycle) {
        this.lifecycle = new WeakReference<>(lifecycle);
    }

    public LifecycleProvider getLifecycleProvider() {
        return lifecycle.get();
    }


    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (model != null) {
            model.onCleared();
        }
        //ViewModel销毁时会执行，同时取消所有异步任务
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void accept(Disposable disposable) {
        addSubscribe(disposable);
    }

    public BaseLiveData getLiveData() {
        if (liveData == null) {
            liveData = new BaseLiveData();
        }
        return liveData;
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<? extends Activity> clz) {
        startActivity(clz, null);
    }

    /**
     * @param clz  clz 所跳转的目的Activity类
     * @param code 启动requestCode
     */
    public void startActivity(Class<? extends Activity> clz, int code) {
        startActivity(clz, null, code);
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<? extends Activity> clz, Bundle bundle) {
        startActivity(clz, bundle, ParameterField.REQEUST_DEFAULT);
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<? extends Activity> clz, Bundle bundle, int requestCode) {
        Map<String, Object> params = new HashMap<>();
        params.put(ParameterField.CLASS, clz);
        params.put(ParameterField.REQUEST, requestCode);
        params.put(ParameterField.BUNDLE, bundle);
        liveData.startActivityEvent.postValue(params);
    }


    public void startActivityForFragment(Class<? extends Activity> clz, Bundle bundle, int requestCode) {
        Map<String, Object> params = new HashMap<>();
        params.put(ParameterField.CLASS, clz);
        params.put(ParameterField.REQUEST, requestCode);
        params.put(ParameterField.BUNDLE, bundle);
        liveData.getStartActivityForFragment().postValue(params);
    }

    /**
     * 关闭界面
     */
    public void finish() {
        liveData.finishEvent.postValue(null);
    }

    /**
     * 携带code的 finish
     */
    public void finishFragmentResult() {
        liveData.getResultFragment().postValue(null);
    }

    /**
     * 返回上一层
     */
    public void onBackPressed() {
        liveData.onBackPressedEvent.postValue(null);
    }



    public static final class ParameterField {
        public static String CLASS = "CLASS";
        public static String CANONICAL_NAME = "CANONICAL_NAME";
        public static String BUNDLE = "BUNDLE";
        public static String REQUEST = "REQUEST";
        public static int REQEUST_DEFAULT = 1;
    }
}
