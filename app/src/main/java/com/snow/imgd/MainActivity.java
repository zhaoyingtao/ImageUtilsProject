package com.snow.imgd;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

import com.snow.img.ImageUtil;

public class MainActivity extends AppCompatActivity {
//    private String imgUrl = "http://b-ssl.duitang.com/uploads/item/201804/30/20180430215916_zrwau.jpg";
    private String imgUrl = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2F611%2F031213123016%2F130312123016-3-1200.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646964747&t=20c4a59aff7bf7eeaeadfdc82ae222c5";
    public static final String TEST_IMAGE = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2F611%2F031213123016%2F130312123016-3-1200.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1646964747&t=20c4a59aff7bf7eeaeadfdc82ae222c5";
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
