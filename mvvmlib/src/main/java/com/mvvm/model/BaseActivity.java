package com.mvvm.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import io.reactivex.annotations.Nullable;


public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends RxAppCompatActivity implements IBaseView {

    protected V binding;
    protected VM viewModel;
    private int viewModelId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding(savedInstanceState);
        //页面接受的参数方法
        initParam();
        //私有的ViewModel与View的契约事件回调逻辑
        registerLiveData();
        //页面事件监听的方法，用于ViewModel层转到View层的事件注册
        initViewObservable();

    }

    private void registerLiveData() {
        //跳入新页面
        viewModel.getLiveData().getStartActivityEvent().observe(this, new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(@Nullable Map<String, Object> params) {
                Class<? extends Activity> clz = (Class<? extends Activity>) params.get(BaseViewModel.ParameterField.CLASS);
                Intent intent = new Intent(BaseActivity.this, clz);
                Object bundle = params.get(BaseViewModel.ParameterField.BUNDLE);
                if (bundle instanceof Bundle) {
                    intent.putExtras((Bundle) bundle);
                }
                startActivityForResult(intent, (int) params.get(BaseViewModel.ParameterField.REQUEST));
            }
        });
        viewModel.getLiveData().getFinishResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                setResult(integer);
                finish();
            }
        });

        //关闭界面
        viewModel.getLiveData().getFinishEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                finish();
            }
        });
        //关闭上一层
        viewModel.getLiveData().getOnBackPressedEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                onBackPressed();
            }
        });

        viewModel.getLiveData().getSetResultEvent().observe(this, new Observer<Map<String, String>>() {
            @Override
            public void onChanged(Map<String, String> params) {
                Intent intent = new Intent();
                if (null != params && params.size() > 0) {
                    Set<String> strings = params.keySet();
                    for (String string : strings) {
                        intent.putExtra(string, params.get(string));
                    }
                }
                setResult(Activity.RESULT_OK, intent);
            }
        });
    }

    /**
     * 销毁注销RXBUS
     */
    @Override
    protected void onDestroy() {
        if (binding != null) {
            binding.unbind();
        }
        super.onDestroy();
    }

    private static InputMethodManager getInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    /**
     * 注入绑定
     */
    private void initViewDataBinding(Bundle savedInstanceState) {
        //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState));
        viewModelId = initVariableId();
        Class modelClass;
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
        } else {
            //如果没有指定泛型参数，则默认使用BaseViewModel
            modelClass = BaseViewModel.class;
        }
        viewModel = (VM) createViewModel(this, modelClass);
        //关联ViewModel
        binding.setVariable(viewModelId, viewModel);
        //支持LiveData绑定xml，数据改变，UI自动会更新
        binding.setLifecycleOwner(this);
        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
        //注入RxLifecycle生命周期
        viewModel.injectLifecycleProvider(this);
    }


    /**
     * 刷新布局
     */
    public void refreshLayout() {
        if (viewModel != null) {
            binding.setVariable(viewModelId, viewModel);
        }
    }


    /**
     * =====================================================================
     **/
    @Override
    public void initParam() {

    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int initContentView(Bundle savedInstanceState);

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    @Override
    public abstract void initViewObservable();

    /**
     * 创建ViewModel 如果 需要自己定义ViewModel 直接复写此方法
     *
     * @param cls
     * @param <T>
     * @return
     */
    public <T extends ViewModel> T createViewModel(FragmentActivity activity, Class<T> cls) {
        return ViewModelProviders.of(activity).get(cls);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModel.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 表示页面是否需要登录
     *
     * @return
     */
    public boolean needLogin() {
        return false;
    }
}
