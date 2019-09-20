# ImageUtilsProject
Glide图片加载封装，包含圆角图片加载、圆图片加载、本地网络图片加载、封装ImageView具有缩放功能等   

[ ![Download](https://api.bintray.com/packages/zhaoyingtao/maven/image_utils/images/download.svg) ](https://bintray.com/zhaoyingtao/maven/image_utils/_latestVersion)

### 集成方法：   
`
compile 'com.bintray.library:image_utils:1.1.0'
`

#### 调用方法：   

加载图片的方法都有多个重载方法，根据需求传不同的参数
```
//通过url获取到Drawable
ImageUtil.imageLoadUrlToDrawable(this, imgUrl, new ImageUtil.LoadUrlToDrawableListener() {
    @Override
    public void imageDrawable(Drawable drawable) {
        findViewById(R.id.root_cl).setBackgroundDrawable(drawable);
    }
});
//普通加载图片，方形
 ImageUtil.imageLoad(this, TEST_IMAGE, zoomImageView);
 
 //加载圆图片
ImageUtil.imageLoadCircle(this, imgUrl,imageView);

 //加载各种圆角图片
ImageUtil.imageLoadFillet(this, imgUrl, imageView, num2Dip(this, 20));
mageUtil.imageLoadFillet(this, imgUrl,imageView,num2Dip(this,20), ImageFilletDirection.Top);
ImageUtil.imageLoadFillet(this, imgUrl,imageView,num2Dip(this,20), ImageFilletDirection.Bottom);
ImageUtil.imageLoadFillet(this, imgUrl,imageView,num2Dip(this,20), ImageFilletDirection.Left);
ImageUtil.imageLoadFillet(this, imgUrl,imageView,num2Dip(this,20), ImageFilletDirection.Right);


```
####  可缩放图
```
xml文件
<com.snow.img.ZoomImageView
        android:id="@+id/scale_iv"
        android:layout_width="match_parent"
        android:layout_height="400dp" />

 //可缩放的图片
  ZoomImageView zoomImageView = findViewById(R.id.scale_iv);
  zoomImageView.setMaxScale(16f);
  zoomImageView.setCanScale(false);
  ImageUtil.imageLoad(this, TEST_IMAGE, zoomImageView);
```
