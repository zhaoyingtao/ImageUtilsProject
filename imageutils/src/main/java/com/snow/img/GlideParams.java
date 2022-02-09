package com.snow.img;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by zhyt
 * Date: 2021/12/6
 * Describe:
 */
public class GlideParams {
    private Context mContext;
    private String url;
    private ImageView imageView;
    private int imgStyleType = GlideImageUtil.IMAGE_STYLE_DEFAULT;
    private int imgFilletDirection = GlideImageUtil.IMAGE_FILLET_ALL;
    private int imgFilletSize = 0;//圆角大小 dp
    private int defaultPic = -1;
    private int errorPic = -1;


    public GlideParams(Context mContext, String url, ImageView imageView) {
        this.mContext = mContext;
        this.url = url;
        this.imageView = imageView;
    }

    public int getImgStyleType() {
        return imgStyleType;
    }

    public GlideParams setImgStyleType(int imgStyleType) {
        this.imgStyleType = imgStyleType;
        return this;
    }

    public int getImgFilletDirection() {
        return imgFilletDirection;
    }

    public GlideParams setImgFilletDirection(int imgFilletDirection) {
        this.imgFilletDirection = imgFilletDirection;
        return this;
    }

    public int getImgFilletSize() {
        return imgFilletSize;
    }

    public GlideParams setImgFilletSize(int imgFilletSize) {
        this.imgFilletSize = imgFilletSize;
        return this;
    }

    public int getDefaultPic() {
        if (defaultPic > 0) {
            return defaultPic;
        }
        return errorPic;
    }

    public GlideParams setDefaultPic(int defaultPic) {
        this.defaultPic = defaultPic;
        return this;
    }

    public int getErrorPic() {
        if (errorPic > 0) {
            return errorPic;
        }
        return defaultPic;
    }

    public GlideParams setErrorPic(int errorPic) {
        this.errorPic = errorPic;
        return this;
    }

    public Context getContext() {
        return mContext;
    }

    public String getUrl() {
        return url;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void build() {
        GlideImageUtil.imageLoad(this);
    }
}
