# OkHttp网络请求框架：

## 梯子

https://github.com/getlantern/download/wiki

android系统提供了两种HTTP通信类，HttpURLConnection和HttpClient。目前在高版本中4.4开始HttpClient已经被废弃了。

Google官方归结使用HttpURLConnection，但是和HttpClient相比功能少很多，需要进行手动封装。

OkHttp内部依赖Okio，他重写了java的部分内容，比如缓存等，是square公司开源的。

OkHttp最低要求java1.7，android要求在2.3以上的平台使用。

OkHttp可以支持：

- get请求
- post请求
- 文件上传
- 文件下载
- 加载图片
- 支持请求回调，直接返回对象、对象集合
- 支持session的保持

但是使用OkHttp进行Https的请求，需要进行证书的配置，其实也很简单，请求的时候，设置忽略证书验证就可以了。

![](F:\BaiduYunDownload\okhttp\OKhttp请求https协议设置.png)

OkHttp是一个性对程序的解决方案。

> OkHttp官网：http://square.github.io/okhttp/ 
>
> GitHub源码地址：https://github.com/square/okhttp
>
> getDemo：https://raw.githubusercontent.com/square/okhttp/master/samples/guide/src/main/java/okhttp3/guide/GetExample.java
>
> postDemo：https://raw.githubusercontent.com/square/okhttp/master/samples/guide/src/main/java/okhttp3/guide/PostExample.java
>
> API网址：http://square.github.io/okhttp/3.x/okhttp/

#### Examples

##### GET A URL

This program downloads a URL and print its contents as a string。

```java
OkHttpClient client = new OkHttpClient();

String run(String url) throws IOException {
  Request request = new Request.Builder()
      .url(url)
      .build();

  Response response = client.newCall(request).execute();
  return response.body().string();
}
```

##### POST TO A SERVER

This program posts data to a service.

```java
public static final MediaType JSON
    = MediaType.parse("application/json; charset=utf-8");

OkHttpClient client = new OkHttpClient();

String post(String url, String json) throws IOException {
  RequestBody body = RequestBody.create(JSON, json);
  Request request = new Request.Builder()
      .url(url)
      .post(body)
      .build();
  Response response = client.newCall(request).execute();
  return response.body().string();
}
```

gradle

```java
implementation 'com.squareup.okhttp3:okhttp:3.11.0'
    //或者
implementation 'com.squareup.okhttp3:okhttp:(insert latest version)'
    //或者
implementation 'com.squareup.okhttp3:okhttp:3.+'
```

依赖Okio的gradle(但是默认，依赖了OkHttp就会自动导入依赖Okio，不需要单独导入Okio的依赖了)

```java
implementation 'com.squareup.okio:okio:1.5.0'
```

