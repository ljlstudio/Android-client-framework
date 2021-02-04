package com.example.myapplication;

import jp.co.cyberagent.android.gpuimage.filter.GPUImage3x3ConvolutionFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImage3x3TextureSamplingFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageAddBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageAlphaBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBoxBlurFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBulgeDistortionFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageCGAColorspaceFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageChromaKeyBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageColorBurnBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageColorDodgeBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageColorInvertFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageCrosshatchFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageDarkenBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageDifferenceBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageDilationFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageDissolveBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageDivideBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageEmbossFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageExclusionBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageExposureFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFalseColorFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageGammaFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageGaussianBlurFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageGlassSphereFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHalftoneFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHardLightBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHazeFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHighlightShadowFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHueFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageKuwaharaFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageLevelsFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageLightenBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageLookupFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageLuminanceThresholdFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageMonochromeFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageMultiplyBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageNonMaximumSuppressionFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageNormalBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageOpacityFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageOverlayBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImagePosterizeFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageRGBDilationFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageRGBFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSaturationFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageScreenBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSharpenFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSketchFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSmoothToonFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSobelEdgeDetectionFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSoftLightBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSourceOverBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSphereRefractionFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSubtractBlendFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSwirlFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageThresholdEdgeDetectionFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageToneCurveFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageToonFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageTransformFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageVignetteFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageWhiteBalanceFilter;

public class GPUFilter {
    public static final int FAST_GAUSSIAN_BLUR_FILTER = 1;//高斯模糊
    public static final int BOX_BLUR_FILTER = 2;//盒装模糊
    public static final int CONVOLUTION_3X3_FILTER = 3;//3x3卷积，高亮大色块变黑，加亮边缘、线条等
    public static final int TEXTURE_3X3_FILTER = 4;
    public static final int ADD_BLEND_FILTER = 5;// 通常用于创建两个图像之间的动画变亮模糊效果
    public static final int ALPHA_BLEND_FILTER = 6; //透明混合,通常用于在背景上应用前景的透明度
    public static final int BRIGHTNESS_FILTER = 7;//亮度
    public static final int EXPOSURE_FILTER = 8;//曝光
    public static final int CONTRAST_FILTER = 9;//对比度
    public static final int SATURATION_FILTER = 10;//饱和度
    public static final int GAMMA_FILTER = 11;//伽马
    public static final int BULGE_DISTORTION_FILTER = 12;//凸起失真，鱼眼效果
    public static final int CGA_COLOR_SPACE_FILTER = 13;//CGA色彩滤镜，形成黑、浅蓝、紫色块的画面
    public static final int CHROMA_KEY_BLENDF_ILTER = 14;//色度键混合
    public static final int COLOR_BURN_BLEND_FILTER = 15;//色彩加深混合
    public static final int COLOR_DODGE_BLEND_FILTER = 16;//色彩减淡混合
    public static final int COLOR_INVERT_FILTER = 17;//反色
    public static final int CROSS_HATCH_FILTER = 18;//交叉线阴影，形成黑白网状画面
    public static final int DARKEN_BLEND_FILTER = 19;//加深混合,通常用于重叠类型
    public static final int DIFFERENCE_BLEND_FILTER = 20;//差异混合,通常用于创建更多变动的颜色
    public static final int DILATION_FILTER = 21;//扩展边缘模糊，变黑白
    public static final int DISSOLVE_BLEND_FILTER = 22;//溶解
    public static final int DIVIDE_BLEND_FILTER = 23;//通常用于创建两个图像之间的动画变暗模糊效果
    public static final int EMBOSS_FILTER = 24;//浮雕效果，带有点3d的感觉
    public static final int EXCLUSION_BLEND_FILTER = 25;//排除混合
    public static final int FALSE_COLOR_FILTER = 26;//色彩替换（替换亮部和暗部色彩）
    public static final int GLASS_SPHERE_FILTER = 27;//水晶球效果
    public static final int GRAYS_CALE_FILTER = 28;//灰度
    public static final int HALF_TONE_FILTER = 29;//点染,图像黑白化，由黑点构成原图的大致图形
    public static final int HARD_LIGHT_BLEND_FILTER = 30;//强光混合,通常用于创建阴影效果
    public static final int HAZE_FILTER = 31;//朦胧加暗
    public static final int HIGH_LIGHT_SHADOW_FILTER = 32;//提亮阴影
    public static final int HUE_FILTER = 33;//色度
    public static final int KUWAHARA_FILTER = 34;//桑原(Kuwahara)滤波,水粉画的模糊效果；处理时间比较长，慎用
    public static final int LEVELS_FILTER = 35;//色阶
    public static final int LIGHTEN_BLEND_FILTER = 36;//减淡混合,通常用于重叠类型
    public static final int LOOKUP_FILTER = 37;//lookup 色彩调整
    public static final int LUMINANCE_THRESHOLD_FILTER = 38;//亮度阈
    public static final int MONO_CHROME_FILTER = 39;//单色
    public static final int MULTIPLY_BLEND_FILTER = 40;//通常用于创建阴影和深度效果
    public static final int NONMAXIMUM_SUPPRESSION_FILTER = 41;//非最大抑制，只显示亮度最高的像素，其他为黑
    public static final int NORMAL_BLEND_FILTER = 42;//正常
    public static final int OPACITY_FILTER = 43;//不透明度
    public static final int OVERLAY_BLEND_FILTER = 44;//叠加,通常用于创建阴影效果
    public static final int POSTERIZE_FILTER = 45;//色调分离，形成噪点效果
    public static final int RGB_DILATION_FILTER = 46;//RGB扩展边缘模糊，有色彩
    public static final int RGB_FILTER = 47;//RGB
    public static final int SCREEN_BLEND_FILTER = 48;//屏幕包裹,通常用于创建亮点和镜头眩光
    public static final int SHARPEN_FILTER = 49;//锐化
    public static final int SKETCH_FILTER = 50;//素描
    public static final int SMOOTH_TOON_FILTER = 51;//相比上面的效果更细腻，上面是粗旷的画风
    public static final int SOBEL_EDGE_DETECTION_FILTER = 52;//Sobel边缘检测算法(白边，黑内容，有点漫画的反色效果)
    public static final int SOFT_LIGHT_BLEND_FILTER = 53;//柔光混合
    public static final int SOURCE_OVER_BLEND_FILTER = 54;//源混合
    public static final int SPHERE_REFRACTION_FILTER = 55;//球形折射，图形倒立
    public static final int SUBTRACT_BLEND_FILTER = 56;//差值混合,通常用于创建两个图像之间的动画变暗模糊效果
    public static final int SWIRL_FILTER = 57;//漩涡，中间形成卷曲的画面
    public static final int THRESHOLD_EDGE_DETECTION_FILTER = 58;//阈值边缘检测（效果与上差别不大）
    public static final int TONE_CURVE_FILTER = 59;//色调曲线
    public static final int TOON_FILTER = 60;//卡通效果（黑色粗线描边）
    public static final int TRANSFORM_FILTER = 61;//形状变化
    public static final int VIGNETTE_FILTER = 62;//晕影，形成黑色圆形边缘，突出中间图像的效果
    public static final int WHITE_BALANCE_FILTER = 63;//白平衡

    public static GPUImageFilter getGPUImageFilter(int filerFlag) {
        GPUImageFilter mGPUImageFilter;
        switch (filerFlag) {
            default:
            case FAST_GAUSSIAN_BLUR_FILTER:
                mGPUImageFilter = new GPUImageGaussianBlurFilter();
                break;
            case BOX_BLUR_FILTER:
                mGPUImageFilter = new GPUImageBoxBlurFilter();
                break;
            case CONVOLUTION_3X3_FILTER:
                mGPUImageFilter = new GPUImage3x3ConvolutionFilter();
                break;
            case TEXTURE_3X3_FILTER:
                mGPUImageFilter = new GPUImage3x3TextureSamplingFilter();
                break;
            case ADD_BLEND_FILTER:
                mGPUImageFilter = new GPUImageAddBlendFilter();
                break;
            case ALPHA_BLEND_FILTER:
                mGPUImageFilter = new GPUImageAlphaBlendFilter();
                break;
            case BRIGHTNESS_FILTER:
                mGPUImageFilter = new GPUImageBrightnessFilter();
                break;
            case EXPOSURE_FILTER:
                mGPUImageFilter = new GPUImageExposureFilter();
                break;
            case CONTRAST_FILTER:
                mGPUImageFilter = new GPUImageContrastFilter();
                break;
            case SATURATION_FILTER:
                mGPUImageFilter = new GPUImageSaturationFilter();
                break;
            case GAMMA_FILTER:
                mGPUImageFilter = new GPUImageGammaFilter();
                break;
            case BULGE_DISTORTION_FILTER:
                mGPUImageFilter = new GPUImageBulgeDistortionFilter();
                break;
            case CGA_COLOR_SPACE_FILTER:
                mGPUImageFilter = new GPUImageCGAColorspaceFilter();
                break;
            case CHROMA_KEY_BLENDF_ILTER:
                mGPUImageFilter = new GPUImageChromaKeyBlendFilter();
                break;
            case COLOR_BURN_BLEND_FILTER:
                mGPUImageFilter = new GPUImageColorBurnBlendFilter();
                break;
            case COLOR_DODGE_BLEND_FILTER:
                mGPUImageFilter = new GPUImageColorDodgeBlendFilter();
                break;
            case COLOR_INVERT_FILTER:
                mGPUImageFilter = new GPUImageColorInvertFilter();
                break;
            case CROSS_HATCH_FILTER:
                mGPUImageFilter = new GPUImageCrosshatchFilter();
                break;
            case DARKEN_BLEND_FILTER:
                mGPUImageFilter = new GPUImageDarkenBlendFilter();
                break;
            case DIFFERENCE_BLEND_FILTER:
                mGPUImageFilter = new GPUImageDifferenceBlendFilter();
                break;
            case DILATION_FILTER:
                mGPUImageFilter = new GPUImageDilationFilter();
                break;
            case DISSOLVE_BLEND_FILTER:
                mGPUImageFilter = new GPUImageDissolveBlendFilter();
                break;
            case DIVIDE_BLEND_FILTER:
                mGPUImageFilter = new GPUImageDivideBlendFilter();
                break;
            case EMBOSS_FILTER:
                mGPUImageFilter = new GPUImageEmbossFilter();
                break;
            case EXCLUSION_BLEND_FILTER:
                mGPUImageFilter = new GPUImageExclusionBlendFilter();
                break;
            case FALSE_COLOR_FILTER:
                mGPUImageFilter = new GPUImageFalseColorFilter();
                break;
            case GLASS_SPHERE_FILTER:
                mGPUImageFilter = new GPUImageGlassSphereFilter();
                break;
            case GRAYS_CALE_FILTER:
                mGPUImageFilter = new GPUImageGrayscaleFilter();
                break;
            case HALF_TONE_FILTER:
                mGPUImageFilter = new GPUImageHalftoneFilter();
                break;
            case HARD_LIGHT_BLEND_FILTER:
                mGPUImageFilter = new GPUImageHardLightBlendFilter();
                break;
            case HAZE_FILTER:
                mGPUImageFilter = new GPUImageHazeFilter();
                break;
            case HIGH_LIGHT_SHADOW_FILTER:
                mGPUImageFilter = new GPUImageHighlightShadowFilter();
                break;
            case HUE_FILTER:
                mGPUImageFilter = new GPUImageHueFilter();
                break;
            case KUWAHARA_FILTER:
                mGPUImageFilter = new GPUImageKuwaharaFilter();
                break;
            case LEVELS_FILTER:
                mGPUImageFilter = new GPUImageLevelsFilter();
                break;
            case LIGHTEN_BLEND_FILTER:
                mGPUImageFilter = new GPUImageLightenBlendFilter();
                break;
            case LOOKUP_FILTER:
                mGPUImageFilter = new GPUImageLookupFilter();
                break;
            case LUMINANCE_THRESHOLD_FILTER:
                mGPUImageFilter = new GPUImageLuminanceThresholdFilter();
                break;
            case MONO_CHROME_FILTER:
                mGPUImageFilter = new GPUImageMonochromeFilter();
                break;
            case MULTIPLY_BLEND_FILTER:
                mGPUImageFilter = new GPUImageMultiplyBlendFilter();
                break;
            case NONMAXIMUM_SUPPRESSION_FILTER:
                mGPUImageFilter = new GPUImageNonMaximumSuppressionFilter();
                break;
            case NORMAL_BLEND_FILTER:
                mGPUImageFilter = new GPUImageNormalBlendFilter();
                break;
            case OPACITY_FILTER:
                mGPUImageFilter = new GPUImageOpacityFilter();
                break;
            case OVERLAY_BLEND_FILTER:
                mGPUImageFilter = new GPUImageOverlayBlendFilter();
                break;
            case POSTERIZE_FILTER:
                mGPUImageFilter = new GPUImagePosterizeFilter();
                break;
            case RGB_DILATION_FILTER:
                mGPUImageFilter = new GPUImageRGBDilationFilter();
                break;
            case RGB_FILTER:
                mGPUImageFilter = new GPUImageRGBFilter();
                break;
            case SCREEN_BLEND_FILTER:
                mGPUImageFilter = new GPUImageScreenBlendFilter();
                break;
            case SHARPEN_FILTER:
                mGPUImageFilter = new GPUImageSharpenFilter();
                break;
            case SKETCH_FILTER:
                mGPUImageFilter = new GPUImageSketchFilter();
                break;
            case SMOOTH_TOON_FILTER:
                mGPUImageFilter = new GPUImageSmoothToonFilter();
                break;
            case SOBEL_EDGE_DETECTION_FILTER:
                mGPUImageFilter = new GPUImageSobelEdgeDetectionFilter();
                break;
            case SOFT_LIGHT_BLEND_FILTER:
                mGPUImageFilter = new GPUImageSoftLightBlendFilter();
                break;
            case SOURCE_OVER_BLEND_FILTER:
                mGPUImageFilter = new GPUImageSourceOverBlendFilter();
                break;
            case SPHERE_REFRACTION_FILTER:
                mGPUImageFilter = new GPUImageSphereRefractionFilter();
                break;
            case SUBTRACT_BLEND_FILTER:
                mGPUImageFilter = new GPUImageSubtractBlendFilter();
                break;
            case SWIRL_FILTER:
                mGPUImageFilter = new GPUImageSwirlFilter();
                break;
            case THRESHOLD_EDGE_DETECTION_FILTER:
                mGPUImageFilter = new GPUImageThresholdEdgeDetectionFilter();
                break;
            case TONE_CURVE_FILTER:
                mGPUImageFilter = new GPUImageToneCurveFilter();
                break;
            case TOON_FILTER:
                mGPUImageFilter = new GPUImageToonFilter();
                break;
            case TRANSFORM_FILTER:
                mGPUImageFilter = new GPUImageTransformFilter();
                break;
            case VIGNETTE_FILTER:
                mGPUImageFilter = new GPUImageVignetteFilter();
                break;
            case WHITE_BALANCE_FILTER:
                mGPUImageFilter = new GPUImageWhiteBalanceFilter();
                break;
        }

        return mGPUImageFilter;
    }

}
