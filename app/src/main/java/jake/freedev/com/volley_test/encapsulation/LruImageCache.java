package jake.freedev.com.volley_test.encapsulation;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;
import android.util.Log;
import com.android.volley.toolbox.ImageLoader;

/**
 * ImageLoader mImageLoader = new ImageLoader(mRequestQueue,
    new ImageLoader.ImageCache() {
         private final LruCache<String, Bitmap>
         cache = new LruCache<String, Bitmap>(20);

 @Override public Bitmap getBitmap(String url) {
 return cache.get(url);
 }

 @Override public void putBitmap(String url, Bitmap bitmap) {
 cache.put(url, bitmap);
 }
 });
 * author: yujie.zhang
 * date: 2018/10/26 17:47
 * content: //图片加载；创建ImageLoader图片加载对象的时候，需要传入图片缓存ImageLoader.ImageCache对象才能创建ImageLoader对象；
  * 但是，ImageLoader.ImageCache图片缓存对象的创建
  * 需要通过LruCache<String,Bitmap>对象指定ImageLoader.ImageCache这个缓存对象的大小。
 * 也就是说，图片加载类ImageLoader对象的创建，需要依赖ImageLoader.ImageCache接口类的对象提供图片缓存功能，而这个图片缓存功能又需要依赖
 * LruCache<String,Bitmap>类的对象指定缓存空间的大小。
 * 所以：可以将指定缓存大小的LruCache<String,Bitmap>类与开启缓存的ImageLoader.ImageCache类的功能都继承在一个类中。
 */
public class LruImageCache extends LruCache<String,Bitmap> implements ImageLoader.ImageCache {
    private final String TAG=this.getClass().getSimpleName();
    /**
     * 使用系统分配给APP的最大内存的1/16做缓存
     */
    public LruImageCache(){
        this((int)Runtime.getRuntime().maxMemory()>>4);
    }

    public LruImageCache(int maxSize) {
        super(maxSize);
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url,bitmap);
    }

    @Override
    protected int sizeOf(@NonNull String key, @NonNull Bitmap value) {
        Log.e(TAG,"LruCache sizeOf="+(value.getRowBytes()*value.getHeight()));
        return value.getRowBytes()*value.getHeight();
    }
}
