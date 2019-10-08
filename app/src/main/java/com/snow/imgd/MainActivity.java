package com.snow.imgd;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.snow.img.ImageFilletDirection;
import com.snow.img.ImageUtil;
import com.snow.img.ZoomImageView;

public class MainActivity extends AppCompatActivity {
    private String imgUrl = "http://pic16.nipic.com/20111006/6239936_092702973000_2.jpg";
    public static final String TEST_IMAGE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561094715086&di=619ebeb53d8024d675f52f09ff59916f&imgtype=0&src=http%3A%2F%2Fpic22.nipic.com%2F20120626%2F7755371_230304612000_2.jpg";
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
//        //通过url获取到Drawable
//        ImageUtil.imageLoadUrlToDrawable(this, TEST_IMAGE, new ImageUtil.LoadUrlToDrawableListener() {
//            @Override
//            public void imageDrawable(Drawable drawable) {
//                findViewById(R.id.root_cl).setBackgroundDrawable(drawable);
//            }
//        });
        //通过URL加载获得Bitmap文件
        ImageUtil.imageLoadUrlToBitmap(this, imgUrl, new ImageUtil.LoadUrlToBitmapListener() {
            @Override
            public void imageBitmap(Bitmap resource) {
                ImageView imageViews = findViewById(R.id.imageView_bitmap);
                imageViews.setImageBitmap(resource);
            }

        });
        //加载圆图片
//        ImageUtil.imageLoadCircle(this, imgUrl,imageView);
        //加载各种圆角图片
//        ImageUtil.imageLoadFillet(this, imgUrl, imageView, num2Dip(this, 20));
//        ImageUtil.imageLoadFillet(this, imgUrl,imageView,num2Dip(this,20), ImageFilletDirection.Top);
//        ImageUtil.imageLoadFillet(this, imgUrl,imageView,num2Dip(this,20), ImageFilletDirection.Bottom);
//        ImageUtil.imageLoadFillet(this, imgUrl,imageView,num2Dip(this,20), ImageFilletDirection.Left);
//        ImageUtil.imageLoadFillet(this, imgUrl,imageView,num2Dip(this,20), ImageFilletDirection.Right);
        //可缩放的图片
//        ZoomImageView zoomImageView = findViewById(R.id.scale_iv);
//        zoomImageView.setMaxScale(16f);
//        zoomImageView.setCanScale(false);
//        ImageUtil.imageLoad(this, TEST_IMAGE, zoomImageView);
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
