package jake.freedev.com.volley_offical.encapsulation.listener;

/**
 * author: yujie.zhang
 * date: 2018/11/5 18:50
 * content: //Json结果处理
 */
public interface JsonResolver<T> {
    void resolve(T response,int code);
}
