package jake.freedev.com.volley_offical.util;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * author: yujie.zhang
 * date: 2018/11/5 11:13
 * content: //公共数据类
 */
public class Const {

    /**
     * 创建请求队列，统一在这里定义，需要修改只需要修改这里即可
     * 一个activity页面一般创建一个队列就足够了
     * 或者整个APP创建一个队列（这样的话采用単例模式管理）
     * @param context
     * @return
     */
    public static RequestQueue newRequestQueue(Context context){
        return Volley.newRequestQueue(context);
    }
}
