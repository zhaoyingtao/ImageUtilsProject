package com.snow.imgd;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.snow.img.GlideImageUtil;
import com.snow.img.ZoomImageView;

public class MainActivity extends AppCompatActivity {
    //    private String imgUrl = "http://b-ssl.duitang.com/uploads/item/201804/30/20180430215916_zrwau.jpg";
    private String imgUrl = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2F611%2F031213123016%2F130312123016-3-1200.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646964747&t=20c4a59aff7bf7eeaeadfdc82ae222c5";
    public static final String TEST_IMAGE = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2F611%2F031213123016%2F130312123016-3-1200.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646964747&t=20c4a59aff7bf7eeaeadfdc82ae222c5";
    ImageView imageView;
    ImageView imageView01;
    ImageView imageView02;
    ImageView imageView03;
    ImageView imageView04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        imageView01 = findViewById(R.id.image01);
        imageView02 = findViewById(R.id.image02);
        imageView03 = findViewById(R.id.image03);
        imageView04 = findViewById(R.id.image04);
//        //通过url获取到Drawable
        Glide.with(this)
                .asDrawable()//强制Glide返回一个Drawable对象
                .load(TEST_IMAGE)
                .into(new SimpleTarget<Drawable>() {

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setBackgroundDrawable(resource);
                    }
                });
        //通过URL加载获得Bitmap文件
        Glide.with(this)
                .asBitmap()//强制Glide返回一个Bitmap对象
                .load(imgUrl)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        ImageView imageViews = findViewById(R.id.imageView_bitmap);
                        imageViews.setImageBitmap(resource);
                    }
                });
        testImage();
        //可缩放的图片
        ZoomImageView zoomImageView = findViewById(R.id.scale_iv);
        zoomImageView.setMaxScale(16f);
        zoomImageView.setCanScale(true);
        GlideImageUtil.withParams(this, TEST_IMAGE, zoomImageView)
                .setDefaultPic(R.mipmap.ic_launcher)
                .build();
    }

    private void testImage() {
        GlideImageUtil.withParams(this, imgUrl, imageView01)
                .setDefaultPic(R.mipmap.ic_launcher)
                .build();
        GlideImageUtil.withParams(this, imgUrl, imageView02)
                .setImgStyleType(GlideImageUtil.IMAGE_STYLE_CIRCLE)
                .setDefaultPic(R.mipmap.ic_launcher)
                .build();
        GlideImageUtil.withParams(this, imgUrl, imageView03)
                .setImgStyleType(GlideImageUtil.IMAGE_STYLE_FILLET)
                .setImgFilletSize(num2Dip(this, 12))
                .build();
        GlideImageUtil.withParams(this, imgUrl, imageView04)
                .setImgStyleType(GlideImageUtil.IMAGE_STYLE_FILLET)
                .setImgFilletDirection(GlideImageUtil.IMAGE_FILLET_RIGHT)
                .setImgFilletSize(num2Dip(this, 12))
                .build();
    }

    /**
     * 数字转化为dp
     *
     * @param num
     * @return
     */
    public int num2Dip(Context mContext, int num) {
        return mContext.getResources().getDimensionPixelSize(R.dimen.base_dip) * num;
    }
}
