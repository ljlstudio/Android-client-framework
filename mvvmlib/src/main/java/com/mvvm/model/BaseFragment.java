package com.mvvm.model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.trello.rxlifecycle2.components.support.RxFragment;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends RxFragment implements IBaseView {

    protected V binding;
    protected VM viewModel;
    private int viewModelId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParam();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, initContentView(inflater, container, savedInstanceState), container, false);
        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binding != null) {
            binding.unbind();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding();
        //私有的ViewModel与View的契约事件回调逻辑
        registerLiveData();
        //页面数据初始化方法
//        initData();
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable();

    }


    @Override
    public void onResume() {
        super.onResume();
        lazyLoad();
    }


    @Override
    public void onStop() {
        super.onStop();
        stopLoad();
    }


    /**
     * 注入绑定
     */
    private void initViewDataBinding() {
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

        binding.setVariable(viewModelId, viewModel);
        //支持LiveData绑定xml，数据改变，UI自动会更新
        binding.setLifecycleOwner(this);
        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
        //注入RxLifecycle生命周期
        viewModel.injectLifecycleProvider(this);
    }

    /**
     * =====================================================================
     **/
    //注册ViewModel与View的契约UI回调事件
    protected void registerLiveData() {

        viewModel.getLiveData().getStartActivityForFragment().observe(this, new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(Map<String, Object> params) {
                Class<? extends Activity> clz = (Class<? extends Activity>) params.get(BaseViewModel.ParameterField.CLASS);
                Intent intent = new Intent(getActivity(), clz);
                if (params != null) {
                    Object bundle = params.get(BaseViewModel.ParameterField.BUNDLE);
                    if (bundle instanceof Bundle) {
                        intent.putExtras((Bundle) bundle);
                    }
                }
                BaseFragment.this.startActivityForResult(intent, (int) params.get(BaseViewModel.ParameterField.REQUEST));
            }
        });

        //跳入新页面
        viewModel.getLiveData().getStartActivityEvent().observe(this, new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(@Nullable Map<String, Object> params) {
                Class<?> clz = (Class<?>) params.get(BaseViewModel.ParameterField.CLASS);
                Bundle bundle = (Bundle) params.get(BaseViewModel.ParameterField.BUNDLE);
                startActivity(clz, bundle);
            }
        });

        //关闭界面
        viewModel.getLiveData().getFinishEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                getActivity().finish();
            }
        });


        viewModel.getLiveData().getResultFragment().observe(this, new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(Map<String, Object> params) {
                Intent intent = new Intent();
                if (null != params && params.size() > 0) {
                    Set<String> strings = params.keySet();
                    for (String string : strings) {
                        intent.putExtra(string, (Bundle) params.get(string));
                    }
                }
                getActivity().setResult(Activity.RESULT_OK, intent);
            }
        });
        //关闭上一层
        viewModel.getLiveData().getOnBackPressedEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                getActivity().onBackPressed();
            }
        });
    }


    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(getContext(), clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(getContext(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    /**
     * =====================================================================
     **/

    //刷新布局
    public void refreshLayout() {
        if (viewModel != null) {
            binding.setVariable(viewModelId, viewModel);
        }
    }

    @Override
    public void initParam() {

    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    public abstract void stopLoad();

    /**
     * 懒加载，配合最新的 BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT 使用
     */
    public abstract void lazyLoad();

    @Override
    public abstract void initViewObservable();

    public boolean isBackPressed() {
        return false;
    }

    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
     */
    public <T extends ViewModel> T createViewModel(Fragment fragment, Class<T> cls) {
        return ViewModelProviders.of(fragment).get(cls);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModel.onActivityResult(requestCode, resultCode, data);
    }
}
