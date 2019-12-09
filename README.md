# ImageUtilsProject
Glide图片加载封装，包含圆角图片加载、圆图片加载、本地网络图片加载、封装ImageView具有缩放功能等   

[ ![Download](https://api.bintray.com/packages/zhaoyingtao/maven/image_utils/images/download.svg) ](https://bintray.com/zhaoyingtao/maven/image_utils/_latestVersion)

![看效果图](https://img-blog.csdnimg.cn/20191202154028339.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMxNzk2NjUx,size_16,color_FFFFFF,t_70#pic_center)

看完效果图来看看使用方法吧：

### 集成方法：   
`api 'com.bintray.library:image_utils:1.1.1'`

#### 调用方法：   
```
方形图加载：
ImageUtil.imageLoad(this, TEST_IMAGE, imageView);

 //加载圆图片
 ImageUtil.imageLoadCircle(this, TEST_IMAGE, imageView03);

 //加载各种圆角图片====圆角需要传dp值
 ImageUtil.imageLoadFillet(this, TEST_IMAGE, imageView04, num2Dip(this, 20));
 ImageUtil.imageLoadFillet(this, TEST_IMAGE, imageView05, num2Dip(this, 20), ImageFilletDirection.Top);
 ImageUtil.imageLoadFillet(this, TEST_IMAGE, imageView06, num2Dip(this, 20), ImageFilletDirection.Bottom);
 ImageUtil.imageLoadFillet(this, TEST_IMAGE, imageView07,num2Dip(this,20), ImageFilletDirection.Left);
 ImageUtil.imageLoadFillet(this, TEST_IMAGE, imageView08,num2Dip(this,20), ImageFilletDirection.Right);
```
获取drawable或者bitmap的方法
```
 //通过url获取到Drawable
 ImageUtil.imageLoadUrlToDrawable(this, TEST_IMAGE, new ImageUtil.LoadUrlToDrawableListener() {
            @Override
            public void imageDrawable(Drawable drawable) {
                imageView01.setBackgroundDrawable(drawable);
            }
        });
 //通过URL加载获得Bitmap文件
  ImageUtil.imageLoadUrlToBitmap(this, TEST_IMAGE, new ImageUtil.LoadUrlToBitmapListener() {
            @Override
            public void imageBitmap(Bitmap resource) {
                imageView02.setImageBitmap(resource);
            }

        });
```
#### 自定义支持缩放功能的imageView：
```
  <com.snow.img.ZoomImageView
         android:id="@+id/scale_iv"
         android:layout_width="match_parent"
         android:layout_height="@dimen/margin_500"
         android:layout_marginTop="@dimen/margin_020" />




 //可缩放的图片
  ZoomImageView zoomImageView = findViewById(R.id.scale_iv);
  zoomImageView.setMaxScale(16f);//缩放倍数
//zoomImageView.setCanScale(false);//是否支持缩放
 ImageUtil.imageLoad(this, TEST_IMAGE, zoomImageView);

直接将数字转化为dp：

    /**
     * 数字转化为dp
     *
     * @param num
     * @return
     */
    public int num2Dip(Context mContext, int num) {
        return mContext.getResources().getDimensionPixelSize(R.dimen.base_dip) * num;
    }
```

最后可以去看看 ImageUtil 类，很多方法都有重载方法，可以设置默认图片、加载失败、是否使用缓存等功能；

使用的glide版本是com.github.bumptech.glide:glide:4.9.0


感觉有用给个star支持下！
