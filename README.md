# RecyclerViewStudy
学习RecyclerView

### floating绘制字体仍有问题

### 待加入item侧滑删除功能


### 关于ItemDecoration的getItemOffsets(Rect outRect)参数outRect的一个见解.
![图1](https://github.com/idealcn/RecyclerViewStudy/blob/master/img/pic1.png?raw=true)
比如设置了outRect.top = 20;那么上方紫色区域的高度就是20;也就是outRect的某个值对应某个方向上那个区域的高度,而不是坐标.


### position
    > RecyclerView及其相关的类定义了 许多获取position的方法
