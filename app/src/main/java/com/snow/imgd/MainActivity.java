package com.snow.imgd;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.snow.img.ImageUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageUtil.imageLoadUrlToDrawable(this, "http://img3.imgtn.bdimg.com/it/u=3165522988,2394891213&fm=26&gp=0.jpg", new ImageUtil.LoadUrlToDrawableListener() {
            @Override
            public void imageDrawable(Drawable drawable) {
                findViewById(R.id.root_cl).setBackgroundDrawable(drawable);
            }
        });
    }
}
