package jake.freedev.com.volley_offical;

import com.android.volley.RequestQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jake.freedev.com.HTTPurl;
import jake.freedev.com.entity.CourseCardBean;
import jake.freedev.com.volley_offical.encapsulation.VolleyRequest;
import jake.freedev.com.volley_offical.encapsulation.listener.JsonResolver;
import jake.freedev.com.volley_offical.encapsulation.listener.JsonResponseListener;
import jake.freedev.com.volley_offical.encapsulation.listener.RequestErrorResolver;

/**
 * author: yujie.zhang
 * date: 2018/11/6 10:51
 * content: //所有的网络请求，统一封装在这里，在发起的地方直接调用这里的方法名即可
 */
public class HttpUtilsVolley {

    //返回结果是个json数组
    public static void getCourseCard(RequestQueue requestQueue, int cardType, String cityCode, int storeAreaId,
                                     JsonResolver<Object> listener, RequestErrorResolver errorResolver, int code) {
        Map<String, Object> params = new HashMap<>();
        params.put("cardType", cardType);
        params.put("cityCode", cityCode);
        params.put("storeAreaId", storeAreaId);
        requestQueue.add(VolleyRequest.post(HTTPurl.url_jsonArray_path, params,
                new JsonResponseListener<List<CourseCardBean>>(listener) {
                }, null, code));
    }

    //返回结果是个jsonObject对象

}
