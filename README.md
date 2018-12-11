RecyclerViewStudy

学习RecyclerView

floating绘制字体仍有问题

待加入item侧滑删除功能

关于ItemDecoration的getItemOffsets(Rect outRect)参数outRect的一个见解.



比如设置了outRect.top = 20;那么上方紫色区域的高度就是20;也就是outRect的某个值对应某个方向上那个区域的高度,而不是坐标.
如图:
![rect](http://aliyunzixunbucket.oss-cn-beijing.aliyuncs.com/png/getItemOffsets_test1.png?x-oss-process=image/resize,p_100/auto-orient,1/quality,q_90/format,jpg/watermark,image_eXVuY2VzaGk=,t_100,g_se,x_0,y_0)(图片来源于网络,如有侵权,请联系我删除)

![itemdecoration](https://upload-images.jianshu.io/upload_images/7866586-1e7be4fe57c27c65.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
position

 RecyclerView及其相关的类定义了 许多获取position的方法

屏幕适配问题

 屏幕适配大致分为:

1. 尺寸适配
   > 我们在屏幕上面看到的是像素个数,也就是分辨率,比如1920×1080.单位是px.也就是说在长或者宽的方向上有1920或者1080像素个数.
          而布局文件中设置的是dp,也就是dip,英文全称是denisty  independence pixels.密度无关像素的意思.
          ***代码中通过android系统API获取的尺寸是根据当前屏幕的dpi转化后的,也就是px.布局文件是以160dpi为基准来设置尺寸的.***
          举例:以480dpi为基准的设计图标注尺寸为48px,那么在布局中怎么设置对应的dp呢.
        在布局文件中设置的尺寸为16dp.
          那么在代码中,(大多是自定义view时要获取),获取到的实际尺寸呢?这个要看当前设备的dpi是多少
          通过如下方法获取
          ```
                  int scaledDensity =  Context.getResource().getDisplayMetrics().scaledDensity;
                  int result = scaledDensity × 16;
          ```
          这样就获取到当前屏幕需要正确显示的尺寸.在代码中设置即可.
    ***所以,尺寸适配,大都是针对在代码中动态设置的尺寸,这样的尺寸要根据当前设备的dpi来做对应的转化,而布局中的尺寸是以160dpi为基准设置的,在代码中通过系统API获取时,会自动做对应的转化,这个是不用适配的.***
2. 图片适配
3. 布局适配



# 添加单击事件和长按事件

相关类
  GestureDector
  GestureDectorCompat
  ViewConfiguration
  VelocityTracker


#参考博客
https://github.com/idealcn/EMvp
[RecyclerView源码解读和优化](https://www.jianshu.com/p/52791ac320f6)
[一种优雅的方式实现RecyclerView多布局](https://blog.csdn.net/xuehuayous/article/details/80021325)
[getAdapterPosition和getLayoutPosition](https://stackoverflow.com/questions/29684154/recyclerview-viewholder-getlayoutposition-vs-getadapterposition)
