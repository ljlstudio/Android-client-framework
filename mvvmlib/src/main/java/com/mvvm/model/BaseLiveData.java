package com.mvvm.model;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.Map;

public class BaseLiveData extends MutableLiveData {
    //跳转带参数
    public MutableLiveData<Map<String, Object>> startActivityEvent;
    //关闭页面
    public MutableLiveData<Void> finishEvent;
    //back操作
    public MutableLiveData<Void> onBackPressedEvent;
    //setResult
    public MutableLiveData<Map<String, String>> setResultEvent;
    public MutableLiveData<Integer> finishResult;
    public MutableLiveData<Map<String, Object>> startActivityForFragment;
    public MutableLiveData<Map<String, Object>> setResultFragment;


    public MutableLiveData<Map<String, Object>> getResultFragment() {
        return setResultFragment = createLiveData(setResultFragment);
    }

    public MutableLiveData<Map<String, Object>> getStartActivityForFragment() {
        return startActivityForFragment = createLiveData(startActivityForFragment);
    }

    public MutableLiveData<Integer> getFinishResult() {
        return finishResult = createLiveData(finishResult);
    }

    public MutableLiveData<Map<String, Object>> getStartActivityEvent() {
        return startActivityEvent = createLiveData(startActivityEvent);
    }

    public MutableLiveData<Map<String, String>> getSetResultEvent() {
        return setResultEvent = createLiveData(setResultEvent);
    }

    public MutableLiveData<Void> getFinishEvent() {
        return finishEvent = createLiveData(finishEvent);
    }

    public MutableLiveData<Void> getOnBackPressedEvent() {
        return onBackPressedEvent = createLiveData(onBackPressedEvent);
    }

    private <T> MutableLiveData<T> createLiveData(MutableLiveData<T> liveData) {
        if (liveData == null) {
            liveData = new MutableLiveData<>();
        }
        return liveData;
    }

    @Override
    public void observe(LifecycleOwner owner, Observer observer) {
        super.observe(owner, observer);
    }
}
