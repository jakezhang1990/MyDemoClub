package jake.freedev.com.volley_test.encapsulation.listener;

/**
 * author: yujie.zhang
 * date: 2018/10/31 14:57
 * content: //请求出错处理器，对出错信息监听解析
 */
public interface RequestErrorResolver {
     final int WHEN_UNKNOWN = 0;
     final int WHEN_CONNECTION = 1;
     final int WHEN_SEND = 2;
     final int WHEN_RECIVE = 3;
     final int WHEN_STATUS = 4;

    void resolveError(int when, Throwable throwable, int code);
}
