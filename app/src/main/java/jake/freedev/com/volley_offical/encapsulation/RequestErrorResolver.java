package jake.freedev.com.volley_offical.encapsulation;

/**
 * author: yujie.zhang
 * date: 2018/10/31 14:57
 * content: //请求出错处理器，对出错信息监听
 */
public interface RequestErrorResolver {
    static final int WHEN_UNKNOWN = 0;
    static final int WHEN_CONNECTION = 1;
    static final int WHEN_SEND = 2;
    static final int WHEN_RECIVE = 3;
    static final int WHEN_STATUS = 4;

    void resolveError(int when, Throwable throwable, int code);
}
