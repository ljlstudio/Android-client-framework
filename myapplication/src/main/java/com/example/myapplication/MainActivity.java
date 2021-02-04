package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageView;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBoxBlurFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageExposureFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageGammaFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageGaussianBlurFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHazeFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageOpacityFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSaturationFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSharpenFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageToonFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageWhiteBalanceFilter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private GPUImage gpuImage;
    private GPUImageView gpuImageView;
    public static String[] imageSpinner = {
            "列了17种效果"
            , "高斯模糊"
            , "盒装模糊"
            , "亮度"
            , "曝光"
            , "对比度"
            , "饱和度"
            , "伽马"
            , "不透明度"
            , "锐化"
            , "素描"
            , "卡通效果"
            , "朦胧加暗"
            , "晕影"
            , "色调曲线"
            , "凸起失真"
            , "交叉线阴影"

    };
    private Bitmap oldBitmap;
    private SeekBar seekBar;
    int filter = GPUFilter.FAST_GAUSSIAN_BLUR_FILTER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seek_bar);
        seekBar.setMax(100);
        gpuImageView = findViewById(R.id.gpu_image_later);
        Spinner spinner = findViewById(R.id.function_spinner);
        gpuImage = new GPUImage(this);
        seekBar.setOnSeekBarChangeListener(this);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, imageSpinner);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        oldBitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.fengjin);
        gpuImageView.setImage(oldBitmap);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            return;
        }

        String item = imageSpinner[position];
        seekBar.setProgress(0);

        switch (item) {
            case "高斯模糊":
                filter = GPUFilter.FAST_GAUSSIAN_BLUR_FILTER;
                break;
            case "盒装模糊":
                filter = GPUFilter.BOX_BLUR_FILTER;
                break;
            case "亮度":
                filter = GPUFilter.BRIGHTNESS_FILTER;
                break;
            case "曝光":
                filter = GPUFilter.EXPOSURE_FILTER;
                break;
            case "对比度":
                filter = GPUFilter.CONTRAST_FILTER;
                break;
            case "饱和度":
                filter = GPUFilter.SATURATION_FILTER;
                break;
            case "伽马":
                filter = GPUFilter.GAMMA_FILTER;
                break;
            case "不透明度":
                filter = GPUFilter.OPACITY_FILTER;
                break;
            case "锐化":
                filter = GPUFilter.SHARPEN_FILTER;
                break;
            case "素描":
                filter = GPUFilter.SKETCH_FILTER;
                break;
            case "卡通效果":
                filter = GPUFilter.TOON_FILTER;
                break;
            case "朦胧加暗":
                filter = GPUFilter.HAZE_FILTER;
                break;
            case "晕影":
                filter = GPUFilter.VIGNETTE_FILTER;
                break;
            case "色调曲线":
                filter = GPUFilter.TONE_CURVE_FILTER;
                break;
            case "凸起失真":
                filter = GPUFilter.BULGE_DISTORTION_FILTER;
                break;
            case "交叉线阴影":
                filter = GPUFilter.CROSS_HATCH_FILTER;
                break;
        }
        try {
            //注意名字要与图片名字一致
            Bitmap gpuImage1 = GPUImageUtil.getGPUImage(gpuImage, oldBitmap, filter);
            gpuImageView.setImage(gpuImage1);
        } catch (Exception ignored) {


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        GPUImageFilter gpuImageFilter = GPUImageUtil.getGPUImageFilter(filter);
        if (gpuImageFilter instanceof GPUImageExposureFilter) {
            //曝光
            GPUImageExposureFilter gpuImageExposureFilter = (GPUImageExposureFilter) gpuImageFilter;
            gpuImageExposureFilter.setExposure(progress / 100f);
        } else if (gpuImageFilter instanceof GPUImageContrastFilter) {
            //对比度
            GPUImageContrastFilter gpuImageContrastFilter = (GPUImageContrastFilter) gpuImageFilter;
            gpuImageContrastFilter.setContrast(progress / 100f);
        } else if (gpuImageFilter instanceof GPUImageBoxBlurFilter) {
            //盒装模糊
            GPUImageBoxBlurFilter gpuImageBoxBlurFilter = (GPUImageBoxBlurFilter) gpuImageFilter;
            gpuImageBoxBlurFilter.setBlurSize(progress / 100f);
        }else if (gpuImageFilter instanceof GPUImageBrightnessFilter){
            //亮度
            GPUImageBrightnessFilter gpuImageBrightnessFilter= (GPUImageBrightnessFilter) gpuImageFilter;
            gpuImageBrightnessFilter.setBrightness(progress/100f);
        }else if (gpuImageFilter instanceof GPUImageGaussianBlurFilter){
            //高斯模糊
            GPUImageGaussianBlurFilter gpuImageGaussianBlurFilter= (GPUImageGaussianBlurFilter) gpuImageFilter;
            gpuImageGaussianBlurFilter.setBlurSize(progress/100f);
        }else if (gpuImageFilter instanceof GPUImageSaturationFilter){
            //饱和度
            GPUImageSaturationFilter gpuImageSaturationFilter= (GPUImageSaturationFilter) gpuImageFilter;
            gpuImageSaturationFilter.setSaturation(progress/100f);
        }else if (gpuImageFilter instanceof GPUImageGammaFilter){
            //伽马
            GPUImageGammaFilter gpuImageGammaFilter= (GPUImageGammaFilter) gpuImageFilter;
            gpuImageGammaFilter.setGamma(progress/100f);
        }else if (gpuImageFilter instanceof GPUImageOpacityFilter){
            //不透明度
            GPUImageOpacityFilter gpuImageOpacityFilter= (GPUImageOpacityFilter) gpuImageFilter;
            gpuImageOpacityFilter.setOpacity(progress/100f);
        }else if (gpuImageFilter instanceof GPUImageSharpenFilter){
            //锐化
            GPUImageSharpenFilter gpuImageSharpenFilter= (GPUImageSharpenFilter) gpuImageFilter;
            gpuImageSharpenFilter.setSharpness(progress/100f);
        }else if (gpuImageFilter instanceof GPUImageToonFilter){
            //卡通效果
            GPUImageToonFilter gpuImageToonFilter= (GPUImageToonFilter) gpuImageFilter;
            gpuImageToonFilter.setThreshold(progress/100f);
            gpuImageToonFilter.setQuantizationLevels(progress);
        }else if (gpuImageFilter instanceof GPUImageHazeFilter){
            //朦胧加暗
            GPUImageHazeFilter gpuImageHazeFilter= (GPUImageHazeFilter) gpuImageFilter;
            gpuImageHazeFilter.setDistance(progress/100f);
        }

        Bitmap gpuImage1 = GPUImageUtil.getFilterGPUImage(gpuImage, oldBitmap, gpuImageFilter);
        gpuImageView.setImage(gpuImage1);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
