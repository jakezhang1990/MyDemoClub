package jake.freedev.com.volley_my_custom;

import java.io.InputStream;

/**
 * author: yujie.zhang
 * date: 2018/10/26 11:02
 * content: //TODO
 */
public interface HttpListener {
    /**
     * 请求成功，处理响应返回的inputstream
     * @param inputStream
     */
    void onSucess(InputStream inputStream);

    void onError();
}
