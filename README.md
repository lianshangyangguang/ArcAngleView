# ArcAngleView
[![](https://jitpack.io/v/lianshangyangguang/ArcAngleView.svg)](https://jitpack.io/#lianshangyangguang/ArcAngleView)  

使用：<br>
配置文件中：  
``` 
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
     }
    }                                          
    加入依赖
    dependencies {
    compile 'com.github.lianshangyangguang:ArcAngleView:v1.1'
    }
```

布局中使用如下：

```
<com.gwell.view.library.ArcAngleView
        android:id="@+id/view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
         />
```
代码中使用如下：

```
ArcAngleView view = (ArcAngleView)findViewById(R.id.view);
view.setAngle(0.7f);//参数为0-1 float类型小数，即可根据百分比转到相应角度,带动画
view.setCurrentAngle(0.7f);//参数为0-1 float类型小数，即可根据百分比转到相应角度,不带动画
  
  ```
  
