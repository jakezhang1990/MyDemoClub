package jake.freedev.com.volley_my_custom;

/**
 * author: yujie.zhang
 * date: 2018/10/26 10:56
 * content: //TODO
 */
public interface HttpService {
    /**
     * 设置url
     * @param url
     */
    void setUrl(String url);

    /**
     * 执行请求
     */
    void excute();

    /**
     * 设置回调接口
     */
    void setHttpCallBack();
}
