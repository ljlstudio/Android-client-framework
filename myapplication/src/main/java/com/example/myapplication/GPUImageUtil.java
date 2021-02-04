package com.example.myapplication;

import android.graphics.Bitmap;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBulgeDistortionFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSaturationFilter;

public class GPUImageUtil {

    private static GPUImageFilter mGPUImageFilter;

    private static final float progress = 0.1f;


    /**
     * 处理图片
     *
     * @param mGPUImage
     * @param mBitmap    原图
     * @param filterType 选择滤镜类型
     * @return 返回处理好后的图片
     */
    public static Bitmap getGPUImage(GPUImage mGPUImage, Bitmap mBitmap, int filterType) {
        mGPUImage.setImage(mBitmap);
        mGPUImage.setFilter(GPUFilter.getGPUImageFilter(filterType));
        mBitmap = mGPUImage.getBitmapWithFilterApplied();
        return mBitmap;
    }

    public static Bitmap getFilterGPUImage(GPUImage mGPUImage, Bitmap mBitmap, GPUImageFilter filter) {
        mGPUImage.setImage(mBitmap);
        mGPUImage.setFilter(filter);
        mBitmap = mGPUImage.getBitmapWithFilterApplied();
        return mBitmap;
    }

    public static GPUImageFilter getGPUImageFilter(int filterType) {
        return GPUFilter.getGPUImageFilter(filterType);
    }



}