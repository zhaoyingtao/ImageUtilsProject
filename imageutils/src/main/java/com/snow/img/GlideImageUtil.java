package com.snow.img;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

/**
 * Created by zhyt
 * Date: 2021/12/6
 * Describe:
 */
public class GlideImageUtil {
    //图片的样式类型 ==方形
    public final static int IMAGE_STYLE_DEFAULT = 0;
    //图片的样式类型 ==圆形
    public final static int IMAGE_STYLE_CIRCLE = 1;
    //图片的样式类型 ==圆角
    public final static int IMAGE_STYLE_FILLET = 2;
    //图片的样式类型为圆角时，圆角方向
    public final static int IMAGE_FILLET_ALL = 3;
    public final static int IMAGE_FILLET_LEFT = 4;
    public final static int IMAGE_FILLET_RIGHT = 5;
    public final static int IMAGE_FILLET_TOP = 6;
    public final static int IMAGE_FILLET_BOTTOM = 7;


    public static GlideParams withParams(Context mContext, String url, ImageView imageView) {
        return new GlideParams(mContext, url, imageView);
    }

    public static void imageLoad(GlideParams params) {
        if (params == null) {
            return;
        }
        Context mContext = params.getContext();
        String url = params.getUrl();
        ImageView imageView = params.getImageView();
        int errorPic = params.getErrorPic();
        int defaultPic = params.getDefaultPic();
        int imgFilletSize = params.getImgFilletSize();
        int imgFilletDirection = params.getImgFilletDirection();
        if (imageView == null || verificationNull(mContext)) {
            return;
        }
        //如果url为空，就给一个错误的链接，让其加载默认图
        if (TextUtils.isEmpty(url)) {
            url = "http://xxx.com";
        }
        if (params.getImgStyleType() == IMAGE_STYLE_CIRCLE) {
            imageLoadCircle(mContext, url, imageView, defaultPic, errorPic);
        } else if (params.getImgStyleType() == IMAGE_STYLE_FILLET) {
            imageLoadFillet(mContext, url, imageView, imgFilletSize, imgFilletDirection, defaultPic, errorPic);
        } else {
            imageLoadDefault(mContext, url, imageView, defaultPic, errorPic);
        }
    }

    /**
     * 加载图片。。。方形
     *
     * @param mContext
     * @param url
     * @param imageView
     * @param defaultPic
     * @param errorPic
     */
    private static void imageLoadDefault(Context mContext, String url, ImageView imageView, int defaultPic, int errorPic) {
        RequestOptions options = new RequestOptions()
                .placeholder(defaultPic)
                .error(errorPic);
        loadImage(mContext, url, imageView, options);
    }

    /**
     * 加载圆形图片（类似头像）
     *
     * @param mContext
     * @param url
     * @param imageView
     * @param defaultPic
     * @param errorPic
     */
    private static void imageLoadCircle(Context mContext, String url, ImageView imageView, int defaultPic, int errorPic) {
        RequestOptions options = RequestOptions.bitmapTransform(new CircleCrop())
                .placeholder(defaultPic)
                .error(errorPic);
        loadImage(mContext, url, imageView, options);
    }

    /**
     * 加载圆角图片，可以设置圆角大小、圆角方向
     *
     * @param mContext
     * @param url
     * @param imageView
     * @param filletSize 圆角大小 dp
     * @param direction
     * @param defaultPic
     * @param errorPic
     */
    private static void imageLoadFillet(Context mContext, String url, ImageView imageView, int filletSize, int direction,
                                        int defaultPic, int errorPic) {
        CornerTransform transformation = new CornerTransform(mContext, filletSize);
        switch (direction) {
            case IMAGE_FILLET_ALL:
                transformation.setExceptCorner(false, false, false, false);
                break;
            case IMAGE_FILLET_LEFT:
                transformation.setExceptCorner(false, true, false, true);
                break;
            case IMAGE_FILLET_TOP:
                transformation.setExceptCorner(false, false, true, true);
                break;
            case IMAGE_FILLET_RIGHT:
                transformation.setExceptCorner(true, false, true, false);
                break;
            case IMAGE_FILLET_BOTTOM:
                transformation.setExceptCorner(true, true, false, false);
                break;
            default:
                transformation.setExceptCorner(true, true, true, true);
                break;
        }
        RequestOptions options = RequestOptions.bitmapTransform(transformation)
                .placeholder(defaultPic)
                .error(errorPic);
        loadImage(mContext, url, imageView, options);
    }

    private static void loadImage(Context mContext, String url, ImageView imageView, RequestOptions options) {
        if (!TextUtils.isEmpty(url) && !url.startsWith("http")) {//本地图片
            Glide.with(mContext).load(new File(url)).apply(options).into(imageView);
        } else {//网络图片
            Glide.with(mContext).load(url).apply(options).into(imageView);
        }
    }

    /**
     * 验证是否为空或者activity已经关闭
     *
     * @param mContext
     * @return
     */
    private static boolean verificationNull(Context mContext) {
        if (mContext == null) {
            return true;
        }
        if (mContext instanceof Activity) {
            if (((Activity) mContext).isDestroyed()) {
                return true;
            }
        }
        return false;

    }
}
