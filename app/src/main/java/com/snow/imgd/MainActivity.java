package com.snow.imgd;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.snow.img.ImageFilletDirection;
import com.snow.img.ImageUtil;

public class MainActivity extends AppCompatActivity {
private String imgUrl = "http://img3.imgtn.bdimg.com/it/u=3165522988,2394891213&fm=26&gp=0.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageUtil.imageLoadUrlToDrawable(this, imgUrl, new ImageUtil.LoadUrlToDrawableListener() {
            @Override
            public void imageDrawable(Drawable drawable) {
                findViewById(R.id.root_cl).setBackgroundDrawable(drawable);
            }
        });

        ImageView imageView = findViewById(R.id.imageView);
        //加载圆图片
//        ImageUtil.imageLoadCircle(this, imgUrl,imageView);
        //加载各种圆角图片
        ImageUtil.imageLoadFillet(this, imgUrl,imageView,num2Dip(this,20));
//        ImageUtil.imageLoadFillet(this, imgUrl,imageView,num2Dip(this,20), ImageFilletDirection.Top);
//        ImageUtil.imageLoadFillet(this, imgUrl,imageView,num2Dip(this,20), ImageFilletDirection.Bottom);
//        ImageUtil.imageLoadFillet(this, imgUrl,imageView,num2Dip(this,20), ImageFilletDirection.Left);
//        ImageUtil.imageLoadFillet(this, imgUrl,imageView,num2Dip(this,20), ImageFilletDirection.Right);

    }
    /**
     * 数字转化为dp
     *
     * @param px
     * @return
     */
    public int num2Dip(Context mContext, int px) {
        return mContext.getResources().getDimensionPixelSize(R.dimen.base_dip) * px;
    }
}
