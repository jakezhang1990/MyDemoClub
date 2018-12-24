项目说明
======

个人demo的项目，个人笔记。

积少成多。。。

                                                                by September.

## 技术博客

> csdn博客地址 ：  https://blog.csdn.net/jakezhang1990
>
> sina博客地址 ：  http://blog.sina.com.cn/u/2096900933

把网址转换为二维码的工具网址： https://cli.im/text?8b9243346b85c2b0d72e183255be6681


使用到的框架
--------

安卓框架
```xml
** volley框架 **
2015年5月28日已经停止大规模支持更新了，但是Volley是开源的，可以根据自我实际需要进行定制；
RequestQueue线程池管理Http请求，通过高效的算法并发执行http请求，非常适合频繁的小数据量的网络请求，
但是对于流媒体以及文件下载等操作效果就很差了，因为Volley默认讲所有的Request请求都缓存了，
缓存线程，网络请求的线程，主线程，之间高效切换开发者不需要干预Volley已经封装好了。
http请求首先会从缓存中去寻找，如果缓存中没有才会进入网络请求队列等待线程池进行并发请求，请求到结果后，
会将请求结果缓存并传递到主线程。
ImageLoader对图片加载、缓存处理等都进行了优化和封装。
```

```xml

Picasso框架
OkHttp框架
```
安卓基础笔记
```groovy
四大组件；
RecyclerView 简单使用、横向、纵向滑动、瀑布流效果、侧滑删除、定点刷新。

```
```groovy
RecyclerView
特性：
1，一个新的替代ListView、GridView的控件。
2.高度解耦。
3.自带了性能优化。ViewHolder。
可以横向滑动，也可以垂直滑动，数据反转等，是通过LayoutManager来管理的。【LayoutManager布局摆放管理器(线性摆放、瀑布流)】
```
Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].


java基础笔记
-------------

```xml
 java基础
```

```groovy
 java高级：

```

## 常用设计模式

```
工厂模式（懒汉式、饿汉式）；
代理模式、动态代理；

```

