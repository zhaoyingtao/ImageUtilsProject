package com.snow.img;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

/**
 * author : zyt
 * e-mail : 632105276@qq.com
 * date   : 2019-09-19
 * desc   : 通过Glide加载图片
 */

public class ImageUtil {
    /**
     * 使用Glide加载图片==正常，方形
     */
    public static void imageLoad(Context mContext, String url, ImageView imageView) {
        imageLoad(mContext, url, imageView, -1);
    }

    /**
     * 使用Glide加载图片
     *
     * @param mContext
     * @param url        图片地址
     * @param imageView  显示的ImageView
     * @param defaultPic 默认图片可接收 R.mipmap.xxx   R.color.xxx  R.drawable.xxx
     */
    public static void imageLoad(Context mContext, String url, ImageView imageView, int defaultPic) {
        if (imageView == null || verificationNull(mContext,url)) {
            return;
        }
        RequestOptions options = new RequestOptions()
                .placeholder(defaultPic)
                .error(defaultPic);
        if (url.startsWith("http")) {//网络图片
            Glide.with(mContext).load(url).apply(options).into(imageView);
        } else {//本地图片
            Glide.with(mContext).load(new File(url)).apply(options).into(imageView);
        }
    }

    public static void imageLoadCircle(Context mContext, String url, ImageView imageView) {
        imageLoadCircle(mContext, url, imageView, -1);
    }

    /**
     * 使用Glide加载圆形图片
     *
     * @param mContext
     * @param url
     * @param imageView
     * @param defaultPic 默认图片,加载出错  可接收 R.mipmap.xxx   R.color.xxx  R.drawable.xxx
     */
    public static void imageLoadCircle(Context mContext, String url, ImageView imageView, int defaultPic) {
        if (imageView == null || verificationNull(mContext,url)) {
            return;
        }
        RequestOptions options = RequestOptions.bitmapTransform(new CircleCrop())
                .placeholder(defaultPic)
                .error(defaultPic);
        if (url.startsWith("http")) {//网络图片
            Glide.with(mContext).load(url).apply(options).into(imageView);
        } else {//本地图片
            Glide.with(mContext).load(new File(url)).apply(options).into(imageView);
        }
    }

    /**
     * 加载圆角图片 ====建议直接使用这个
     */
    public static void imageLoadFillet(Context mContext, String url, ImageView imageView, int filletSize) {
        imageLoadFillet(mContext, url, imageView, filletSize, ImageFilletDirection.All, -1);
    }

    public static void imageLoadFillet(Context mContext, String url, ImageView imageView, int filletSize, int defaultPic) {
        imageLoadFillet(mContext, url, imageView, filletSize, ImageFilletDirection.All, defaultPic);
    }

    public static void imageLoadFillet(Context mContext, String url, ImageView imageView, int filletSize,
                                       ImageFilletDirection direction) {
        imageLoadFillet(mContext, url, imageView, filletSize, direction, -1);
    }

    /**
     * 加载圆角图片
     *
     * @param mContext
     * @param url
     * @param filletSize 圆角大小 dp
     * @param direction  圆角方向 ImageFilletDirection   All, Left, Top, Right, Bottom
     * @param imageView
     * @param defaultPic
     */
    public static void imageLoadFillet(Context mContext, String url, ImageView imageView, int filletSize, ImageFilletDirection direction,
                                       int defaultPic) {
        if (imageView == null ||verificationNull(mContext,url)) {
            return;
        }
        CornerTransform transformation = new CornerTransform(mContext, filletSize);
        switch (direction) {
            case All:
                transformation.setExceptCorner(false, false, false, false);
                break;
            case Left:
                transformation.setExceptCorner(false, true, false, true);
                break;
            case Top:
                transformation.setExceptCorner(false, false, true, true);
                break;
            case Right:
                transformation.setExceptCorner(true, false, true, false);
                break;
            case Bottom:
                transformation.setExceptCorner(true, true, false, false);
                break;
            default:
                transformation.setExceptCorner(true, true, true, true);
                break;
        }
        RequestOptions options = RequestOptions.bitmapTransform(transformation)
                .placeholder(defaultPic)
                .error(defaultPic);
        if (url.startsWith("http")) {//网络图片
            Glide.with(mContext).load(url).apply(options).into(imageView);
        } else {//本地图片
            Glide.with(mContext).load(new File(url)).apply(options).into(imageView);
        }
    }


    /**
     * 加载Gif图
     *
     * @param gifDrawable R.drawable.xxxxx
     * @param imageView
     * @param loopTimes   循环次数 GlideDrawable.LOOP_INTRINSIC／GlideDrawable.LOOP_FOREVER／大于0的数
     */
    public static void imageLoadGif(Context mContext, int gifDrawable, ImageView imageView, final int loopTimes) {
        if (mContext == null || imageView == null) {
            return;
        }
        Glide.with(mContext).load(gifDrawable).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                if (resource instanceof GifDrawable) {
                    //加载次数
                    ((GifDrawable) resource).setLoopCount(loopTimes);
                }
                return false;
            }

        }).into(imageView);
    }

    /**
     * 通过URL加载获得Drawable文件，可以设置任意布局背景
     *
     * @param mContext
     * @param imgUrl
     * @param drawableListener
     */
    public static void imageLoadUrlToDrawable(Context mContext, String imgUrl, final LoadUrlToDrawableListener drawableListener) {
        if (verificationNull(mContext,imgUrl)) {
            return;
        }
        Glide.with(mContext)
                .asDrawable()//强制Glide返回一个Drawable对象
                .load(imgUrl)
                .into(new SimpleTarget<Drawable>() {

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        if (drawableListener != null) {
                            drawableListener.imageDrawable(resource);
                        }
                    }
                });
    }

    /**
     * 通过URL加载获得Bitmap文件
     *
     * @param mContext
     * @param imgUrl
     * @param drawableListener
     */
    public static void imageLoadUrlToBitmap(Context mContext, String imgUrl, final LoadUrlToBitmapListener drawableListener) {
        if (verificationNull(mContext,imgUrl)) {
            return;
        }
        Glide.with(mContext)
                .asBitmap()//强制Glide返回一个Bitmap对象
                .load(imgUrl)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        if (drawableListener != null) {
                            drawableListener.imageBitmap(resource);
                        }
                    }
                });
    }

    /**
     * 验证是否为空或者activity已经关闭
     * @param mContext
     * @param imgUrl
     * @return
     */
    private static boolean verificationNull(Context mContext, String imgUrl) {
        if (mContext == null || TextUtils.isEmpty(imgUrl)) {
            return true;
        }
        if (mContext instanceof Activity) {
            if (((Activity) mContext).isDestroyed()) {
                return true;
            }
        }
        return false;

    }
    public interface LoadUrlToBitmapListener {
        void imageBitmap(Bitmap resource);
    }

    public interface LoadUrlToDrawableListener {
        void imageDrawable(Drawable drawable);
    }
}
