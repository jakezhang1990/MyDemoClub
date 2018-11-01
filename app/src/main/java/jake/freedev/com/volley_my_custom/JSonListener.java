package jake.freedev.com.volley_my_custom;

/**
 * author: yujie.zhang
 * date: 2018/10/26 11:05
 * content: //响应的具体json类的回调,回调给发起请求的activity处,泛型是具体的json类
 */
public interface JSonListener<T> {
    void onSuccess(T t);

    void onError();
}
