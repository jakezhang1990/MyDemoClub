package jake.freedev.com.volley_offical;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * author: yujie.zhang
 * date: 2018/10/26 17:38
 * content: //RequestQueue与ImageLoader単例获取
 */
public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private Context mCtx;

    private MySingleton(Context context){
        mCtx=context;
        mRequestQueue=getRequestQueue();
        mImageLoader=getImageLoader();
    }

    /**
     * 实例化単例
     * 懒汉式-饿汉式-待完善
     * TODO
     * @param context
     * @return
     */
    public static synchronized MySingleton getInstance(Context context){
        if (mInstance==null){
            mInstance=new MySingleton(context);
        }
        return mInstance;
    }
    /**
     * 创建请求队列
     * @return
     */
    public RequestQueue getRequestQueue() {
        if(mRequestQueue==null){
            //整个APP只创建一个请求线程池
            mRequestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return  mRequestQueue;
    }

    /**
     * 创建图片加载器
     * @return
     */
    public ImageLoader getImageLoader() {
        if (mImageLoader==null){
            mImageLoader=new ImageLoader(mRequestQueue,new LruImageCache());
        }
        return mImageLoader;
    }

    /**
     * 添加请求到请求队列
     * @param req
     * @param <T>
     */
    public <T> void addRequestToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }
}
