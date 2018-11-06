package jake.freedev.com.volley_offical.encapsulation.listener;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

import jake.freedev.com.volley_offical.util.DateUtils;

/**
 * author: yujie.zhang
 * date: 2018/11/5 18:38
 * content: //TODO
 */
public class JsonResponseListener<T> implements TextResponseListener {

    /**
     * 日期解析的gsonBuilder
     */
    private static final GsonBuilder gsonBuilder=new GsonBuilder().registerTypeAdapter(Date.class,
            new JsonDeserializer<Date>() {
                @Override
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    if (json.isJsonNull())
                        return null;
                    try {
                        return DateUtils.parse(json.getAsString());
                    } catch (ParseException e) {
                        throw new JsonParseException(e);
                    }
                }
            });

    protected GsonBuilder getGsonBuilder() {
        return gsonBuilder;
    }




    private Gson gson;
    private Type type;
    private WeakReference<JsonResolver<?>> weakLst;
    private JsonResolver<?> lst;

    public JsonResponseListener() {
        this((Type) null);
    }

    public JsonResponseListener(Type type) {
        this(type, null);
    }

    public JsonResponseListener(JsonResolver<?> listener) {
        this(null, listener);
    }

    public JsonResponseListener(Type type, final JsonResolver<?> listener, boolean weak) {

        this.type=type;
        setListener(listener,weak);
    }

    public JsonResponseListener(Type type, JsonResolver<?> listener) {
        this(type, listener, (listener instanceof View || listener instanceof Context));
    }





    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        if (type == null) {
            type = getGenericType();
        }
        return type;
    }

    protected Type getGenericType() {
        return getGenericType(JsonResponseListener.class);
    }

    protected Type getGenericType(Class<?> basicClass) {
        Class<?> dectClass = this.getClass();
        Class<?> supClass;
        while (!basicClass.equals(supClass = dectClass.getSuperclass())) {
            dectClass = supClass;
        }
        ParameterizedType pType = (ParameterizedType) dectClass.getGenericSuperclass();
        return pType.getActualTypeArguments()[0];
    }





    public JsonResolver<?> getListener() {
        if (weakLst != null)
            return weakLst.get();
        return lst;
    }

    public void setListener(JsonResolver<?> listener, boolean weak) {
        if (weak) {
            weakLst = new WeakReference<JsonResolver<?>>(listener);
            lst = null;
        } else {
            lst = listener;
            weakLst = null;
        }
    }






    @Override
    public void onResponse(String response, int code) {
        try {
            response(parseJson(response), code);
        } catch (Throwable e) {
            onException(response, e, code);
        }
    }
    //封装解析
    public void response(T response, int code) {
        JsonResolver listener = getListener();
        if (listener != null)
            listener.resolve(response, code);
    }
    //封装异常
    public void onException(String response, Throwable e, int code) {
        Log.e("总解析监听处：","处理json数据出错[code=" + code + "]", e);
    }
    //转化普通string为jsonString
    protected T parseJson(String json) {
        if (TextUtils.isEmpty(json))
            return null;

        if (gson == null)
            gson = getGsonBuilder().create();
        return gson.fromJson(json, getType());
    }
}
